"""Run unchanged frozen JML with installed OpenJML RAC; no solver or hashes."""
from __future__ import annotations
import json
from pathlib import Path
import queue
import subprocess
import threading
import time
from differential_testing.execution.java_harness import JAVA_SUPPORT

OPENJML = '/root/tools/openjml/openjml'
JAVA = '/root/tools/openjml/jdk/bin/java'
RUNTIME = '/root/tools/openjml/jmlruntime.jar'

HARNESS = 'public class RuntimeHarness {\n' + JAVA_SUPPORT + r'''
 public static void main(String[] args) throws Exception {
  java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
  java.net.URL url = new java.io.File(args[0]).toURI().toURL();
  int count = Integer.parseInt(args[3]);
  System.out.println("READY"); System.out.flush();
  for (String line; (line = reader.readLine()) != null;) {
   try (java.net.URLClassLoader loader = new java.net.URLClassLoader(new java.net.URL[]{url}, RuntimeHarness.class.getClassLoader())) {
    // A new loader restores static state for each independent first-call input.
    Class<?> cls = Class.forName(args[1], true, loader);
    Class<?>[] types = new Class<?>[count]; java.util.Arrays.fill(types, int.class);
    java.lang.reflect.Method method = cls.getDeclaredMethod(args[2], types); method.setAccessible(true);
    String[] parts = line.split(",", -1); Object[] values = new Object[count];
    for(int i=0;i<count;i++) values[i] = Integer.parseInt(parts[i]);
    try {
     Object result = method.invoke(null, values);
     String encoded = result instanceof int[] ? jsonIntArray((int[])result) : String.valueOf(result);
     System.out.println("{\"status\":\"returned\",\"return\":" + encoded + "}");
    } catch (java.lang.reflect.InvocationTargetException wrapped) {
     Throwable e = wrapped.getCause();
     System.out.println("{\"status\":\"failure\",\"class\":" + quote(e.getClass().getName())
      + ",\"message\":" + quote(String.valueOf(e.getMessage())) + ",\"error_kind\":" + quote(errorKind(e))
      + ",\"cause\":" + quote(String.valueOf(e.getCause())) + "}");
    }
   } catch(Throwable e) {
    System.out.println("{\"status\":\"evaluation_error\",\"message\":" + quote(e.toString()) + "}");
   }
   System.out.flush();
  }
 }
}
'''


def command_run(command, directory, label, timeout=60):
    started = time.monotonic()
    try:
        result = subprocess.run(command, capture_output=True, text=True, timeout=timeout)
        record = {'command': command, 'exit_code': result.returncode, 'stdout': result.stdout, 'stderr': result.stderr}
    except subprocess.TimeoutExpired as error:
        record = {'command': command, 'exit_code': None, 'stdout': (error.stdout or b'').decode() if isinstance(error.stdout, bytes) else error.stdout or '', 'stderr': 'compilation timeout'}
    record['elapsed_seconds'] = round(time.monotonic()-started, 3)
    directory.mkdir(parents=True, exist_ok=True)
    (directory/(label+'.json')).write_text(json.dumps(record, indent=2)+'\n')
    return record


def compile_target(source, directory, rac=True):
    directory.mkdir(parents=True, exist_ok=True)
    command = [OPENJML, '--rac' if rac else '--compile', '--nullable-by-default']
    if rac:
        command += ['--show-not-implemented', '--show-not-executable', '--rac-precondition-entry', '--rac-show-source=line']
    command += ['-d', str(directory), str(source)]
    record = command_run(command, directory.parent, 'rac_compile' if rac else 'plain_compile')
    text = record['stdout']+'\n'+record['stderr']
    unsupported = [line.strip() for line in text.splitlines() if any(word in line.lower() for word in ['not implemented', 'not executable', 'notimplemented', 'not supported', 'ignored', 'unexpected exception', 'catastrophic'])]
    record['unsupported_diagnostics'] = unsupported
    record['usable'] = record['exit_code'] == 0 and not unsupported
    (directory.parent/('rac_compile.json' if rac else 'plain_compile.json')).write_text(json.dumps(record, indent=2)+'\n')
    return record


def category(observation):
    if observation['status'] != 'failure':
        return observation['status']
    message, cls = observation.get('message', '').lower(), observation.get('class', '')
    if cls.startswith('org.jmlspecs.runtime.JmlAssertionError'):
        if 'precondition' in message or 'Precondition' in cls:
            return 'precondition_failure'
        if 'postcondition is false' in message:
            return 'postcondition_violation'
        if 'signals condition is false' in message or 'signals_only' in message or 'exception' in message and 'clause' in message:
            return 'exception_contract_failure'
        if any(s in message for s in ['undefined', 'possibly null', 'possibly too', 'division by zero']):
            return 'specification_evaluation_failure'
        return 'other_jml_failure'
    if observation.get('error_kind') in {'null_dereference', 'bounds_error', 'negative_array_size', 'arithmetic_error', 'stack_overflow'}:
        return 'runtime_safety_failure'
    return 'execution_failure'


class Session:
    def __init__(self, target, program, entry, names, harness, directory, interpreted=False):
        self.names = names
        directory.mkdir(parents=True, exist_ok=True)
        self.stderr = (directory/('audit_stderr.log' if interpreted else 'search_stderr.log')).open('a')
        command = [JAVA, '-Xmx192m', '-Dorg.jmlspecs.openjml.rac=exception']
        if interpreted: command += ['-Xint']
        command += ['-cp', str(harness)+':'+RUNTIME, 'RuntimeHarness', str(target), program, entry, str(len(names))]
        self.command = command
        self.process = subprocess.Popen(command, stdin=subprocess.PIPE, stdout=subprocess.PIPE, stderr=self.stderr, text=True, bufsize=1)
        self.lines = queue.Queue()
        def reader():
            for line in self.process.stdout: self.lines.put(line.rstrip('\n'))
            self.lines.put(None)
        threading.Thread(target=reader, daemon=True).start()
        try:
            ready = self.lines.get(timeout=10)
        except queue.Empty:
            self.close(); raise RuntimeError('JVM startup timed out')
        if ready != 'READY':
            self.close(); raise RuntimeError('JVM startup failed: '+str(ready))

    def execute(self, inputs, timeout=.5):
        started = time.monotonic()
        try:
            self.process.stdin.write(','.join(str(inputs[n]) for n in self.names)+'\n'); self.process.stdin.flush()
            line = self.lines.get(timeout=timeout)
            result = json.loads(line) if line is not None else {'status': 'evaluation_error', 'message': 'JVM exited without observation'}
        except queue.Empty:
            self.close(); result = {'status': 'timeout'}
        except (ValueError, TypeError, BrokenPipeError) as error:
            result = {'status': 'evaluation_error', 'message': str(error)}
        result['category'] = category(result)
        result['elapsed_seconds'] = round(time.monotonic()-started, 5)
        return result

    def close(self):
        if self.process.poll() is None:
            self.process.terminate()
            try: self.process.wait(timeout=2)
            except subprocess.TimeoutExpired: self.process.kill(); self.process.wait(timeout=2)
        self.stderr.close()
