#!/usr/bin/python3
"""Transparent stdin recorder for OpenJML's solver; each launch has its own trace."""
import json
import os
from pathlib import Path
import subprocess
import signal
import sys
from threading import Thread


def main():
    directory = Path(os.environ['OPENJML_TRACE_DIRECTORY'])
    solver = os.environ['OPENJML_TRACE_SOLVER']
    stem = directory / f'solver-{os.getpid()}'
    metadata = {'argv': [solver, *sys.argv[1:]], 'stdin_eof': False}
    # Persist input/output before forwarding; OpenJML may forcibly kill its
    # solver wrapper immediately after receiving the final reply.
    with (stem.with_suffix('.smt2').open('xb', buffering=0) as trace,
          stem.with_suffix('.responses').open('xb', buffering=0) as responses):
        process = subprocess.Popen(metadata['argv'], stdin=subprocess.PIPE, stdout=subprocess.PIPE)

        def terminate(signum, frame):
            # OpenJML normally destroys its solver process after the last reply.
            # Forward that cleanup to the real solver rather than orphaning it.
            metadata['termination_signal'] = signum
            if process.poll() is None:
                process.terminate()

        signal.signal(signal.SIGTERM, terminate)

        def forward():
            try:
                while data := os.read(sys.stdin.fileno(), 65536):
                    trace.write(data)
                    process.stdin.write(data)
                    process.stdin.flush()
                metadata['stdin_eof'] = True
            except (BrokenPipeError, OSError, ValueError):
                pass
            finally:
                try:
                    process.stdin.close()
                except (BrokenPipeError, OSError):
                    pass

        def reply():
            try:
                while data := os.read(process.stdout.fileno(), 65536):
                    responses.write(data)
                    sys.stdout.buffer.write(data)
                    sys.stdout.buffer.flush()
            except (BrokenPipeError, OSError, ValueError):
                if process.poll() is None:
                    process.terminate()

        output_thread = Thread(target=reply, daemon=True)
        output_thread.start()
        thread = Thread(target=forward, daemon=True)
        thread.start()
        metadata['exit_code'] = process.wait()
        output_thread.join(timeout=1)
        # The solver can exit on (exit) before OpenJML closes stdin.
        thread.join(timeout=0.1)
        stem.with_suffix('.json').write_text(json.dumps(metadata, indent=2))
    return metadata['exit_code']


if __name__ == '__main__':
    raise SystemExit(main())
