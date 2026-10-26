"""Check fixed arithmetic implementations and modular ACSL clients with Frama-C WP."""
from __future__ import annotations
import argparse
import json
import os
import signal
import shutil
import subprocess
import sys
import time
from pathlib import Path
from prepare import WORKSPACE, PROJECT, HELPERS, prepare

sys.path.insert(0, str(PROJECT))
from verification.specification_evaluation.backends.verifiers import classify_c

TARGETS = ('implementation', 'check_wrap', 'check_division', 'check_order_shift', 'negative_controls')


def launch(command, timeout):
    started = time.monotonic()
    process = subprocess.Popen(command, stdout=subprocess.PIPE, stderr=subprocess.PIPE,
                               text=True, start_new_session=(os.name == 'posix'))
    timed_out = False
    try:
        stdout, stderr = process.communicate(timeout=timeout)
    except subprocess.TimeoutExpired:
        timed_out = True
        if os.name == 'posix':
            try:
                os.killpg(process.pid, signal.SIGKILL)
            except ProcessLookupError:
                pass
        else:
            process.kill()
        stdout, stderr = process.communicate()
    return process.returncode, stdout, stderr, timed_out, round(time.monotonic()-started, 3)


def positive(value):
    number = int(value)
    if number <= 0:
        raise argparse.ArgumentTypeError('must be greater than zero')
    return number


def main():
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument('--frama-c', default='frama-c')
    parser.add_argument('--provers', default='Alt-Ergo:2.4.3,Z3:4.8.12')
    parser.add_argument('--why3-extra-config', type=Path,
                        default=PROJECT/'verification/java_arrays/scripts/why3-alt-ergo-2.4.3.conf')
    parser.add_argument('--timeout', type=positive, default=10)
    parser.add_argument('--run-timeout', type=positive, default=300)
    parser.add_argument('--wp-par', type=positive, default=2)
    parser.add_argument('--memlimit', type=positive, default=1000)
    parser.add_argument('--machdep', default='x86_64')
    parser.add_argument('--model', default='Typed+ref')
    parser.add_argument('--target', choices=TARGETS, action='append')
    parser.add_argument('--output', type=Path, default=WORKSPACE/'results/local/wp')
    args = parser.parse_args()
    try:
        prepare(check=True)
        executable = shutil.which(args.frama_c)
        if executable is None or not args.why3_extra_config.is_file():
            raise ValueError('Frama-C and the selected Why3 configuration are required')
        output = args.output.resolve()
        output.mkdir(parents=True, exist_ok=True)
        version = launch([executable, '-version'], 20)
        if version[0] != 0:
            raise ValueError('Could not identify Frama-C')
        run = {'tool': version[1].strip(), 'settings': vars(args).copy(),
               'role': 'Fixed support validation; helper goals are not benchmark specification outcomes',
               'contracts': (WORKSPACE/'contracts/int32.acsl.h').read_text(encoding='utf-8'),
               'helpers': list(HELPERS), 'targets': args.target or list(TARGETS)}
        run['settings'] = {k:str(v) if isinstance(v,Path) else v for k,v in run['settings'].items()}
        (output/'run.json').write_text(json.dumps(run, indent=2)+'\n', encoding='utf-8')
        records = []
        for target in run['targets']:
            directory = output/target
            directory.mkdir(parents=True, exist_ok=True)
            source = WORKSPACE/'generated/helpers.c' if target == 'implementation' else WORKSPACE/f'clients/{target}.c'
            command = [executable, '-machdep', args.machdep, '-wp', '-wp-rte', '-wp-model', args.model,
                       '-wp-why3-extra-config', str(args.why3_extra_config.resolve()),
                       '-wp-prover', args.provers, '-wp-timeout', str(args.timeout),
                       '-wp-memlimit', str(args.memlimit), '-wp-par', str(args.wp_par),
                       '-wp-cache', 'none', '-wp-report-json', str(directory/'wp-report.json')]
            if target == 'implementation':
                command += ['-wp-fct', ','.join(HELPERS)]
            command.append(str(source))
            code, stdout, stderr, timed_out, elapsed = launch(command, args.run_timeout)
            (directory/'stdout.log').write_text(stdout, encoding='utf-8')
            (directory/'stderr.log').write_text(stderr, encoding='utf-8')
            report_path = directory/'wp-report.json'
            report = json.loads(report_path.read_text(encoding='utf-8')) if report_path.is_file() else None
            outcome, goals, reason = classify_c(code, timed_out, stdout, stderr, report)
            control = None
            if target == 'negative_controls':
                wrong_wrap = [g for g in goals if 'negative_no_wrap' in str(g.get('goal'))+' '+str(g.get('property'))]
                zero_division = [g for g in goals if 'negative_division_zero' in str(g.get('function'))+' '+str(g.get('goal'))
                                 and 'requires' in str(g.get('goal'))+' '+str(g.get('property'))]
                control = {'wrong_wrap_not_proved': bool(wrong_wrap) and all(g['state'] != 'proved' for g in wrong_wrap),
                           'zero_division_not_proved': bool(zero_division) and all(g['state'] != 'proved' for g in zero_division)}
            successful = (outcome == 'proved') if control is None else (
                code == 0 and not timed_out and outcome != 'syntax/tool failure' and all(control.values()))
            record = {'target': target, 'source': str(source), 'command': command, 'exit_code': code,
                      'timed_out': timed_out, 'elapsed_seconds': elapsed, 'outcome': outcome,
                      'reason': reason, 'goals': goals, 'expected_result_met': successful,
                      'negative_controls': control}
            (directory/'record.json').write_text(json.dumps(record, indent=2)+'\n', encoding='utf-8')
            records.append(record)
            print(f'{target}: {outcome}; {sum(g["state"] == "proved" for g in goals)}/{len(goals)} goals proved', flush=True)
        summary = {'complete': True, 'all_expected_results_met': all(r['expected_result_met'] for r in records),
                   'implementation_proved': next((r['outcome']=='proved' for r in records if r['target']=='implementation'), None),
                   'records': records, 'interpretation': 'Client proofs assume fixed contracts; implementation status is separate'}
        (output/'summary.json').write_text(json.dumps(summary, indent=2)+'\n', encoding='utf-8')
        return 0 if summary['all_expected_results_met'] else 1
    except (OSError, ValueError) as error:
        print(f'JArithmetic support check failed: {error}', file=sys.stderr)
        return 2


if __name__ == '__main__':
    raise SystemExit(main())
