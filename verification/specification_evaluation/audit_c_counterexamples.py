"""Independently replay stored C witnesses with stricter compiler diagnostics."""
from __future__ import annotations

import argparse
from collections import Counter
import json
import os
from pathlib import Path
import re
import shutil
import subprocess

from .c_counterexamples import admissible, native_args, oracle
from .manifest import REPO, sha256
from .workflow import read_json, write_json


def execute(binary, program, inputs):
    response = subprocess.run([str(binary), *native_args(program, inputs)], capture_output=True,
                              text=True, timeout=3, env={**os.environ,
                              'ASAN_OPTIONS':'detect_leaks=0:halt_on_error=1',
                              'UBSAN_OPTIONS':'halt_on_error=1:print_stacktrace=1'})
    results = re.findall(r'(?m)^\{"result":(-?\d+)\}$', response.stdout)
    return {'exit_code':response.returncode,'stdout':response.stdout,'stderr':response.stderr,
            'actual':int(results[-1]) if results else None}


def audit(output, allow_incomplete=False):
    summary, config = read_json(output/'summary.json'), read_json(output/'run.json')
    assert summary['complete'] or allow_incomplete, 'Wait for the full run before auditing'
    source = Path(config['source_run'])
    verification_summary=source/'summary_verification.json'
    if not verification_summary.is_file():
        verification_summary=source/'summary.json'
    assert sha256(verification_summary) == config['source_summary_sha256']
    assert sha256(source/'run.json') == config['source_run_sha256']
    assert sha256(Path(__file__).with_name('c_counterexamples.py')) == config['runner_sha256']
    for name, digest in config['runtime_sha256'].items():
        assert sha256(REPO/'runtime/java_arrays'/name) == digest
    records = sorted((output/'cases').glob('*/*/c/record.json'))
    assert len(records) == summary['expected_cases'] or allow_incomplete
    checks, binaries, controls = [], {}, []

    def compile_case(directory):
        if directory in binaries:
            return binaries[directory]
        compilation = read_json(directory/'native_compile.json')
        harness = directory/'native_replay.c'
        assert sha256(harness) == compilation['harness_sha256']
        executable = directory/'native_replay_audit'
        command = ['gcc','-std=c11','-O1','-g','-Wall','-Wextra','-Werror=return-type',
                   '-Werror=uninitialized','-Werror=maybe-uninitialized',
                   '-fsanitize=undefined,address','-fno-sanitize-recover=all', str(harness),
                   str(REPO/'runtime/java_arrays/java_arrays.c'),'-lm','-o',str(executable)]
        result = subprocess.run(command,capture_output=True,text=True,timeout=60)
        write_json(directory/'native_audit_compile.json',{'command':command,'exit_code':result.returncode,
                   'stdout':result.stdout,'stderr':result.stderr,'harness_sha256':sha256(harness)})
        if result.returncode:
            raise AssertionError(f'Stricter replay compilation failed: {directory}: {result.stderr}')
        binaries[directory] = executable
        return executable

    for path in records:
        record = read_json(path)
        origin = Path(record['source_record'])
        assert sha256(origin) == record['source_record_sha256']
        assert sha256(path.parent/f"{record['program']}.c") == record['annotated_source_sha256']
        replay = record['replay']
        if record['role']=='original':
            trials = read_json(path.parent/'replay.json')['attempts']
            assert replay['status']=='no_validated_violation' and trials
            for trial in trials:
                assert admissible(record['program'],trial['inputs'])
                assert trial.get('exit_code')==0 and not trial.get('stderr')
                assert trial['actual']==trial['expected']==oracle(record['program'],trial['inputs'])
            controls.append({'program':record['program'],'trials':len(trials),'all_passed':True})
        if replay['status'] not in ('validated_violation','validated_safety_failure'):
            continue
        witness = replay.get('witness') or replay['safety_witness']
        inputs = witness['inputs']
        assert admissible(record['program'], inputs)
        expected = oracle(record['program'], inputs)
        baseline_dir = output/'cases'/record['program']/'original/c'
        baseline = execute(compile_case(baseline_dir), record['program'], inputs)
        assert baseline['exit_code']==0 and not baseline['stderr'] and baseline['actual']==expected, (record['program'],inputs,baseline,expected)
        mutant = execute(compile_case(path.parent), record['program'], inputs)
        if replay['status']=='validated_violation':
            assert mutant['exit_code']==0 and not mutant['stderr'] and mutant['actual']==witness['actual'] and mutant['actual']!=expected, (path,mutant,witness)
        else:
            assert mutant['exit_code'] in (71,72,73) or 'runtime error:' in mutant['stderr'] or 'ERROR: AddressSanitizer:' in mutant['stderr'], (path,mutant)
        checks.append({'case':f"{record['program']}/{record['mutant_id']}", 'kind':replay['status'],
                       'record_sha256':sha256(path),'inputs':inputs,'expected':expected,
                       'baseline':baseline,'mutant':mutant,'passed':True})
        print(json.dumps({'audited':checks[-1]['case'],'kind':checks[-1]['kind']}),flush=True)
    expected_checks = sum(read_json(p)['replay']['status'] in ('validated_violation','validated_safety_failure') for p in records)
    assert len(checks)==expected_checks
    result={'passed':True,'partial':not summary['complete'],'checks':checks,'verified_counts':dict(Counter(c['kind'] for c in checks)),
            'original_controls':controls,
            'source_experiment_unchanged':True,'audit_sha256':sha256(Path(__file__)),
            'verifiers_sha256':sha256(Path(__file__).with_name('verifiers.py')),
            'summary_sha256':sha256(output/'summary.json'),
            'method':'Recompile at O1 with uninitialized diagnostics, ASan and UBSan; compare each witness with the proved original and frozen-postcondition oracle'}
    result['tools'] = {}
    for name, flag in (('gcc','--version'),('z3','--version')):
        path = Path(shutil.which(name)).resolve()
        version = subprocess.run([str(path),flag],capture_output=True,text=True,timeout=10)
        result['tools'][name] = {'path':str(path),'sha256':sha256(path),'version':version.stdout.splitlines()[0]}
    write_json(output/('audit.json' if summary['complete'] else 'audit_partial.json'),result)
    print(json.dumps({k:v for k,v in result.items() if k!='checks'}),flush=True)


def main():
    parser=argparse.ArgumentParser(description=__doc__)
    parser.add_argument('--output',type=Path,required=True)
    parser.add_argument('--allow-incomplete',action='store_true')
    args=parser.parse_args()
    audit(args.output.resolve(),args.allow_incomplete)


if __name__ == '__main__':
    main()
