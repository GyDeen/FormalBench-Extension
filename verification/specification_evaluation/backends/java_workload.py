"""Measure OpenJML assertions, method VCs, and solver queries as distinct units.

Assertion counts are generated checks, not independently discharged WP goals.
Solver queries include feasibility and counterexample follow-up queries.
"""
from collections import Counter
import json
import os
from pathlib import Path
import re
import shlex
import subprocess
import time

from verification.specification_evaluation.manifest import InputError, sha256
from verification.specification_evaluation.backends.verifiers import (build_command, classify_java, JAVA_UNSUPPORTED, _stop_process_group)

BB = re.compile(r'^BasicBlock2 FORM of (?P<method>[^\n]+)\n(?P<body>.*?)(?=^\s*$|\Z)', re.M | re.S)
ASSERTION = re.compile(r'^\s+assert\s+(\w+)\s+([^;\n]+);', re.M)
START = re.compile(r'^Starting proof of (.+) with prover ', re.M)
FINISH = re.compile(r'^Completed proof of (.+) with prover .* - (.+?)(?: \[[^\]]+\])?$', re.M)


def workload_report(stdout, traces, timed_out=False, returncode=0):
    """Parse only explicit BB assertions, never SMT axioms or warning counts."""
    methods = []
    for match in BB.finditer(stdout):
        assertions = [{'kind': kind, 'expression': expression.strip()}
                      for kind, expression in ASSERTION.findall(match['body'])]
        methods.append({'method': match['method'], 'assertion_count': len(assertions),
                        'assertions_by_kind': dict(Counter(a['kind'] for a in assertions)),
                        'assertions': assertions})
    diagnostics = BB.sub('', stdout)
    starts = START.findall(diagnostics)
    finishes = FINISH.findall(diagnostics)
    scripts = []
    for path in sorted(traces.glob('*.smt2')):
        text = path.read_text(errors='replace')
        # Commands emitted by the installed SMT driver each occupy their own line.
        count = len(re.findall(r'^\s*\(check-sat(?:-assuming)?(?:\s|\))', text, re.M))
        meta = path.with_suffix('.json')
        metadata = json.loads(meta.read_text()) if meta.exists() else None
        response_path = path.with_suffix('.responses')
        responses = response_path.read_text(errors='replace') if response_path.exists() else ''
        results = Counter(re.findall(r'^(sat|unsat|unknown)\s*$', responses, re.M))
        scripts.append({'file': f'solver-traces/{path.name}', 'sha256': sha256(path),
                        'responses_file': f'solver-traces/{response_path.name}',
                        'check_sat_count': count, 'check_sat_results': dict(results),
                        'all_queries_answered': count > 0 and count == sum(results.values()),
                        'solver_exit_code': metadata.get('exit_code') if metadata else None})
    complete = (not timed_out and returncode in (0, 6) and bool(methods)
                and len(starts) == len(finishes) == len(methods)
                and 'TOTAL METHODS:' in diagnostics and bool(scripts)
                and all(s['all_queries_answered'] for s in scripts))
    return {
        'schema_version': '1.0', 'capture_complete': complete,
        'generated_assertion_count': sum(m['assertion_count'] for m in methods) if methods else None,
        'generated_method_vc_count': len(methods) if methods else None,
        'method_proof_starts': len(starts), 'method_proof_completions': len(finishes),
        'solver_invocation_count': len(scripts),
        'solver_check_sat_count': sum(s['check_sat_count'] for s in scripts) if scripts else None,
        'solver_check_sat_results': dict(sum((Counter(s['check_sat_results']) for s in scripts), Counter())),
        'assertions_by_kind': dict(sum((Counter(m['assertions_by_kind']) for m in methods), Counter())),
        'methods': methods, 'solver_traces': scripts,
        'units': {
            'generated_assertion_count': 'assert statements in emitted OpenJML basic-block method VCs; includes implicit checks and constructors',
            'generated_method_vc_count': 'emitted basic-block method VC sections; retries/splits can repeat a method',
            'solver_check_sat_count': 'check-sat commands forwarded to the solver, including feasibility and counterexample follow-up queries',
            'comparability': 'none of these is directly equivalent to a Frama-C WP report entry',
        },
    }, diagnostics


def run_java_workload(source, case_dir, settings, executables):
    """Run the same Java proof settings with diagnostic output and stdin tracing."""
    command = build_command('java', source, case_dir, settings, executables)
    explicit = [arg for arg in command if arg.startswith('--exec=')]
    if len(explicit) != 1:
        raise InputError('Java workload tracing requires one explicitly configured solver executable')
    solver = explicit[0].split('=', 1)[1]
    wrapper = Path(__file__).with_name('java_solver_trace.py').resolve()
    if not os.access(wrapper, os.X_OK):
        raise InputError(f'Java solver recorder must be executable: chmod +x {wrapper}')
    traces = case_dir/'solver-traces'
    traces.mkdir(exist_ok=True)
    # An interrupted attempt must not contribute stale solver queries.
    for path in traces.iterdir():
        if path.is_file() and path.suffix in {'.smt2', '.json', '.responses'}:
            path.unlink()
    command[command.index(explicit[0])] = '--exec=' + str(wrapper)
    command[-1:-1] = ['--show=bb']
    environment = dict(os.environ, OPENJML_TRACE_DIRECTORY=str(traces), OPENJML_TRACE_SOLVER=solver)
    (case_dir/'command.json').write_text(json.dumps({
        'argv': command, 'shell_display': shlex.join(command), 'cwd': str(case_dir),
        'timeout_seconds': settings.timeout, 'trace_solver': solver,
        'trace_environment': {key: environment[key] for key in ('OPENJML_TRACE_DIRECTORY', 'OPENJML_TRACE_SOLVER')},
    }, indent=2) + '\n')
    start = time.monotonic()
    process = None
    timed_out = False
    try:
        process = subprocess.Popen(command, cwd=case_dir, env=environment,
            stdout=subprocess.PIPE, stderr=subprocess.PIPE, text=True,
            start_new_session=(os.name == 'posix'))
        stdout, stderr = process.communicate(timeout=settings.timeout)
        code = process.returncode
    except subprocess.TimeoutExpired:
        timed_out, code = True, None
        stdout, stderr = _stop_process_group(process)
    except KeyboardInterrupt:
        stdout, stderr = _stop_process_group(process)
        (case_dir/'stdout.log').write_text(stdout)
        (case_dir/'stderr.log').write_text(stderr)
        raise
    except OSError as error:
        code, stdout, stderr = None, '', str(error)
    elapsed = round(time.monotonic() - start, 3)
    (case_dir/'stdout.log').write_text(stdout)
    (case_dir/'stderr.log').write_text(stderr)
    workload, diagnostics = workload_report(stdout, traces, timed_out, code)
    outcome, goals, reason = classify_java(code, timed_out, diagnostics, stderr)
    workload['reported_warning_count'] = len(goals)
    (case_dir/'java-workload.json').write_text(json.dumps(workload, indent=2) + '\n')
    return {'outcome': outcome, 'reason': reason, 'goals': goals,
            'goals_unit': 'reported Java warning diagnostics, not generated proof obligations',
            'java_workload': workload, 'exit_code': code, 'timed_out': timed_out,
            'verification_started': process is not None, 'elapsed_seconds': elapsed,
            'failure_stage': ('unsupported_specification' if JAVA_UNSUPPORTED.search(diagnostics + stderr)
                              else 'verifier') if outcome == 'syntax/tool failure' else None,
            'compiler_normalizations': [line for line in diagnostics.splitlines() if line.startswith('[NumericBitPredicates]')],
            'unsupported_features': sorted(set(JAVA_UNSUPPORTED.findall(diagnostics + stderr))),
            'command': command, 'trace_solver': solver,
            'logs': {'stdout': 'stdout.log', 'stderr': 'stderr.log', 'java_workload': 'java-workload.json'}}
