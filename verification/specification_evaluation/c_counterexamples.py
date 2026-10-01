"""Calibrate WP model capture and replay counterexamples for proved C baselines.

This supplemental run preserves the original experiment. WP proof status and
concrete replay verdict are separate evidence. An early incremental SMT query
may supply candidate values, but never establishes rejection without replay.
"""
from __future__ import annotations

import argparse
from collections import Counter
from concurrent.futures import ThreadPoolExecutor, as_completed
from dataclasses import asdict
from datetime import datetime, timezone
import itertools
import json
import os
from pathlib import Path
import random
import re
import shutil
import subprocess
import time

from .manifest import REPO, sha256
from .verifiers import Settings, run_verifier
from .workflow import read_json, write_json

BASE = REPO/'verification/specification_evaluation/results/originals_stop_tool_error_20260927T161243Z_goal10_case300'
SPECS = {
    'MaxOfTwo': 'f0be896ef1003dba076aa900da8e72c1ba1dc6658bf7d6c61930c397c0b12ade',
    'OddBitSetNumber': '26c35a8df64242b283d772ba1b155b6e721f608fb5c93db6179fc9b698a1c7bf',
    'SumNums': 'c5578ae8a1bc6553f4042363ca9294bcf18c64dd480872444f5e463149ad5746',
    'TestThreeEqual': '5b2e35adc9fe18a60d4dacfab61fa4b5a0591020c47f619ff2cbfb4e17deeb92',
    'CountList': 'ada941695475e9dfaf5b57a7e34c853007a832dd88be67d9177073171054db1b',
    'MaxSubArraySum': 'a58d9bd62440187a778ac6be58f8e72136ad1f9adf1db09e495a2274cc092df6',
}
PARAMS = {'MaxOfTwo': ('x', 'y'), 'OddBitSetNumber': ('n',),
          'SumNums': ('x', 'y', 'm', 'n'), 'TestThreeEqual': ('x', 'y', 'z')}
ENTRY = {'MaxOfTwo': 'maxOfTwo', 'OddBitSetNumber': 'oddBitSetNumber', 'SumNums': 'sumNums',
         'TestThreeEqual': 'testThreeEqual', 'CountList': 'countList', 'MaxSubArraySum': 'maxSubArraySum'}


def int32(value):
    return (value + 2**31) % 2**32 - 2**31


def oracle(program, inputs):
    """Executable equivalents of the six hash-pinned frozen postconditions."""
    if program == 'MaxOfTwo':
        return max(inputs['x'], inputs['y'])
    if program == 'TestThreeEqual':
        x, y, z = (inputs[n] for n in PARAMS[program])
        return 3 if x == y == z else 2 if x == y or y == z or x == z else 0
    if program == 'SumNums':
        value = int32(inputs['x'] + inputs['y'])
        return 20 if inputs['m'] <= value <= inputs['n'] else value
    if program == 'OddBitSetNumber':
        n = inputs['n'] & 0xffffffff
        result = n
        for mask, shift in ((0xaaaaaaaa, 1), (0xcccccccc, 2), (0xf0f0f0f0, 4), (0xff00ff00, 8), (0xffff0000, 16)):
            result |= (n & mask) >> shift
        return int32(result)
    if program == 'CountList':
        return sum(bool(row) for row in inputs['inputArray'])
    if program == 'MaxSubArraySum':
        if inputs['size'] <= 0:
            return 1
        tail, best, start, length = 0, 0, 0, 1
        for k, value in enumerate(inputs['a'][:inputs['size']], 1):
            candidate = int32(tail + value)
            if best < candidate:
                length = k - start
            best = max(best, candidate)
            if candidate < 0:
                start = k
            tail = max(0, candidate)
        return length
    raise ValueError(program)


def admissible(program, inputs):
    if program in PARAMS:
        return set(inputs) == set(PARAMS[program]) and all(type(v) is int and -(2**31) <= v < 2**31 for v in inputs.values())
    if program == 'CountList':
        rows = inputs.get('inputArray')
        return isinstance(rows, list) and all(isinstance(row, list) and all(type(v) is int and -(2**31) <= v < 2**31 for v in row) for row in rows)
    a, size = inputs.get('a'), inputs.get('size')
    return type(size) is int and -(2**31) <= size < 2**31 and (size <= 0 or isinstance(a, list) and size <= len(a)) and (a is None or isinstance(a, list) and all(type(v) is int and -(2**31) <= v < 2**31 for v in a))


def sexprs(text):
    """Read SMT output without evaluating solver-provided expressions as code."""
    tokens = re.findall(r'\(|\)|\|[^|]*\||"(?:[^"\\]|\\.)*"|[^\s()]+', text)
    stack, output = [], []
    for token in tokens:
        if token == '(':
            node = []
            (stack[-1] if stack else output).append(node)
            stack.append(node)
        elif token == ')':
            if not stack:
                raise ValueError('Unbalanced SMT output')
            stack.pop()
        else:
            (stack[-1] if stack else output).append(token)
    if stack:
        raise ValueError('Unbalanced SMT output')
    return output


def model_integer(value):
    if isinstance(value, str) and re.fullmatch(r'-?\d+', value):
        return int(value)
    if isinstance(value, list) and len(value) == 2 and value[0] == '-':
        return -model_integer(value[1])
    raise ValueError('Non-integral or unavailable model value')


def extract_models(stdout, goals):
    """Retain WP's full model blocks; models alone never change goal status."""
    result = []
    for block in re.split(r'(?m)(?=^Goal )', stdout):
        if not block.startswith('Goal ') or not re.search(r'(?m)^Prover .+\(Model\)', block):
            continue
        header = re.search(r'^Goal (.+?) \("?[^"\n]+"?, line (\d+)\) in \'([^\']+)\':', block)
        values = dict(re.findall(r'(?m)^Model (\S+) = (.+)$', block))
        goal_matches = [g['goal'] for g in goals if header and g.get('function') == header[3] and g.get('line') == int(header[2])]
        result.append({'kind': header[1] if header else None, 'function': header[3] if header else None,
                       'line': int(header[2]) if header else None, 'goal_matches': goal_matches,
                       'values': values, 'text': block.split('------------------------------------------------------------')[0].strip()})
    return result


def candidate_models(case_dir, program, full_models):
    """Get candidate scalar inputs from full models, then early SMT queries.

    Early queries can omit later library axioms. They are deliberately labelled
    partial and their SAT result is not a proof of a program defect.
    """
    candidates, evidence = [], []
    if program not in PARAMS:
        return candidates, [{'status': 'heap_model_reconstruction_unavailable'}]
    names = PARAMS[program]
    for number, model in enumerate(full_models):
        try:
            values = {n: model_integer(sexprs(model['values'][n])[0]) for n in names}
        except (ValueError, KeyError, IndexError):
            continue
        if admissible(program, values):
            candidates.append((values, f'wp_full_model:{number}'))
    for index, path in enumerate(sorted((case_dir/'wp').rglob('*.smt2'))):
        text = path.read_text()
        if '(check-sat)' not in text:
            continue
        prefix = text.split('(check-sat)', 1)[0]
        symbols = {}
        for match in re.finditer(r';; "([^"\n]+)"\s*\(declare-fun ([^\s()]+) \(\) Int\)', prefix):
            symbols[match[1]] = match[2]
        if not all(n in symbols for n in names):
            continue
        target = case_dir/'model_candidates'/f'query_{index}.smt2'
        target.parent.mkdir(exist_ok=True)
        target.write_text(prefix + '(check-sat)\n(get-value (' + ' '.join(symbols[n] for n in names) + '))\n')
        command = ['z3', '-T:2', '-smt2', str(target)]
        started = time.monotonic()
        try:
            response = subprocess.run(command, capture_output=True, text=True, timeout=4)
            raw = {'command': command, 'exit_code': response.returncode, 'stdout': response.stdout,
                   'stderr': response.stderr, 'elapsed_seconds': time.monotonic() - started,
                   'query_sha256': sha256(target), 'wp_query': path.relative_to(case_dir).as_posix(),
                   'scope': 'prefix through first check-sat; may omit later theory axioms'}
            forms = sexprs(response.stdout)
            if forms and forms[0] == 'sat' and response.returncode == 0:
                pairs = next(v for v in forms[1:] if isinstance(v, list) and v and isinstance(v[0], list))
                values_by_symbol = {pair[0]: model_integer(pair[1]) for pair in pairs}
                values = {n: values_by_symbol[symbols[n]] for n in names}
                if admissible(program, values):
                    candidates.append((values, f'wp_partial_model:{index}'))
                    raw['inputs'] = values
                    raw['status'] = 'candidate_available'
            raw.setdefault('status', 'no_usable_candidate')
        except (subprocess.TimeoutExpired, ValueError, StopIteration, KeyError, IndexError) as error:
            raw = {'status': 'unavailable', 'error': str(error), 'query_sha256': sha256(target)}
        write_json(target.with_suffix('.json'), raw)
        evidence.append(raw)
    return candidates, evidence


def bounded_candidates(program):
    """Fixed, reproducible fallback search; passing these inputs is not proof."""
    rng = random.Random(726)
    small = (-2, -1, 0, 1, 2)
    if program == 'MaxOfTwo':
        values = small + (-2**31, 2**31-1)
        return [dict(zip(PARAMS[program], v)) for v in itertools.product(values, repeat=2)]
    if program == 'TestThreeEqual':
        return [dict(zip(PARAMS[program], v)) for v in itertools.product(small, repeat=3)]
    if program == 'OddBitSetNumber':
        values = list(small) + [-2**31, 2**31-1] + [int32(1 << bit) for bit in range(32)] + [int32(rng.getrandbits(32)) for _ in range(100)]
        return [{'n': n} for n in values]
    if program == 'SumNums':
        inputs = []
        for x, y in itertools.product(small + (-2**31, 2**31-1), repeat=2):
            s = int32(x+y)
            for m, n in ((s,s), (int32(s-1), s), (s, int32(s+1)), (int32(s+1), int32(s-1)), (-2,2)):
                inputs.append(dict(x=x,y=y,m=m,n=n))
        return inputs
    if program == 'CountList':
        return [{'inputArray': [[0]*n for n in lengths]} for count in range(5)
                for lengths in itertools.product((0,1,2), repeat=count)]
    result = [{'a': None, 'size': 0}, {'a': None, 'size': -1}]
    for count in range(5):
        for values in itertools.product((-1,0,1), repeat=count):
            result.append({'a': list(values), 'size': count})
    for _ in range(100):
        a = [rng.choice((-2**31, -10, -1, 0, 1, 10, 2**31-1)) for _ in range(rng.randrange(1,7))]
        result.append({'a': a, 'size': rng.randrange(len(a)+1)})
    return result


def native_harness(program, source):
    runtime_header = REPO/'runtime/java_arrays/java_arrays.h'
    source = source.replace('#include "java_arrays.h"', '#include "' + str(runtime_header) + '"')
    base = '#include <stdio.h>\n#include <stdlib.h>\n#include <inttypes.h>\n' + source + '\n'
    if program in PARAMS:
        args = ','.join(f'(int32_t)strtoll(argv[{i}],NULL,10)' for i in range(1,len(PARAMS[program])+1))
        setup = ''
    elif program == 'CountList':
        setup = 'int32_t n=atoi(argv[1]); JIntArray2 a=jarray2_new_rows(n); int at=2; for(int32_t i=0;i<n;i++){int32_t len=atoi(argv[at++]); JIntArray row=jarray_new(len); for(int32_t j=0;j<len;j++) jarray_set(row,j,(int32_t)strtoll(argv[at++],NULL,10)); jarray2_set(a,i,row);}'
        args = 'a'
    else:
        setup = 'int32_t size=atoi(argv[1]),len=atoi(argv[2]); JIntArray a=len<0?NULL:jarray_new(len); for(int32_t i=0;i<len;i++)jarray_set(a,i,(int32_t)strtoll(argv[3+i],NULL,10));'
        args = 'a,size'
    return base + 'int main(int argc,char**argv){(void)argc;' + setup + '\nint32_t result=' + ENTRY[program] + '(' + args + '); printf("\\n{\\"result\\":%" PRId32 "}\\n",result); return 0;}\n'


def native_args(program, inputs):
    if program in PARAMS:
        return [str(inputs[n]) for n in PARAMS[program]]
    if program == 'CountList':
        a = inputs['inputArray']
        return [str(len(a)), *[str(v) for row in a for v in [len(row), *row]]]
    a = inputs['a']
    return [str(inputs['size']), str(len(a)) if a is not None else '-1', *map(str,a or [])]


def replay(case_dir, record, candidates):
    program = record['program']
    if record['frozen_spec_sha256'] != SPECS[program]:
        raise ValueError('No replay oracle for changed frozen specification')
    source = case_dir/f'{program}.c'
    harness = case_dir/'native_replay.c'
    harness.write_text(native_harness(program, source.read_text()))
    binary = case_dir/'native_replay'
    command = ['gcc', '-std=c11', '-O0', '-g', '-Wall', '-Werror=return-type',
               '-fsanitize=undefined', '-fno-sanitize-recover=all', str(harness),
               str(REPO/'runtime/java_arrays/java_arrays.c'), '-lm', '-o', str(binary)]
    compilation = subprocess.run(command, capture_output=True, text=True, timeout=60)
    compile_record = {'command': command, 'exit_code': compilation.returncode,
                      'stdout': compilation.stdout, 'stderr': compilation.stderr,
                      'harness_sha256': sha256(harness), 'annotated_source_sha256': sha256(source)}
    write_json(case_dir/'native_compile.json', compile_record)
    if compilation.returncode:
        return {'status': 'unavailable', 'reason': 'Native replay compilation failed', 'checked': 0}
    candidates += [(values, 'bounded_search_seed726') for values in bounded_candidates(program)]
    seen, attempts, safety = set(), [], []
    timeouts = 0
    for inputs, origin in candidates:
        signature = json.dumps(inputs, sort_keys=True)
        if signature in seen or not admissible(program, inputs):
            continue
        seen.add(signature)
        command = [str(binary), *native_args(program, inputs)]
        expected = oracle(program, inputs)
        try:
            response = subprocess.run(command, capture_output=True, text=True, timeout=1,
                                      env={**os.environ,'UBSAN_OPTIONS':'halt_on_error=1:print_stacktrace=1'})
            attempt = {'inputs': inputs, 'origin': origin, 'expected': expected,
                       'exit_code': response.returncode, 'stdout': response.stdout, 'stderr': response.stderr}
            values = re.findall(r'(?m)^\{"result":(-?\d+)\}$', response.stdout)
            if response.returncode == 0 and values:
                actual = int(values[-1])
                attempt.update(actual=actual, preconditions_satisfied=True, sanitizer_clean=not response.stderr.strip())
                if actual != expected and not response.stderr.strip():
                    attempt.update(validated=True, violation='functional_postcondition')
                    write_json(case_dir/'replay.json', {'status':'validated_violation','checked':len(attempts)+1,'witness':attempt,'attempts':attempts+[attempt]})
                    return {'status':'validated_violation','checked':len(attempts)+1,'witness':attempt}
            elif response.returncode in (71,72,73) or 'runtime error:' in response.stderr:
                attempt.update(violation='runtime_safety', preconditions_satisfied=True)
                safety.append(attempt)
            attempts.append(attempt)
        except subprocess.TimeoutExpired:
            attempts.append({'inputs': inputs, 'origin': origin, 'status': 'replay_timeout'})
            timeouts += 1
            if timeouts >= 3:
                break
    result = {'status': 'validated_safety_failure' if safety else 'no_validated_violation',
              'checked': len(attempts), 'safety_witness': safety[0] if safety else None,
              'reason': 'Finite candidate search does not establish proof'}
    write_json(case_dir/'replay.json', {**result, 'attempts': attempts})
    return result


def evaluate(source_run, output, origin, settings, executables):
    baseline = read_json(origin/'record.json')
    relative = origin.relative_to(source_run)
    target = output/relative
    target.mkdir(parents=True, exist_ok=True)
    record_path = target/'record.json'
    if record_path.exists():
        old = read_json(record_path)
        if old.get('attempt_status') == 'complete':
            assert old['source_record_sha256'] == sha256(origin/'record.json')
            return old
    for name in ('contracts', 'generated'):
        shutil.copytree(origin/name, target/name, dirs_exist_ok=True)
    for name in ('java_arrays.h', f"{baseline['program']}.c"):
        shutil.copy2(origin/name, target/name)
    assert sha256(target/f"{baseline['program']}.c") == baseline['annotated_source_sha256']
    for name, digest in baseline['support_sha256'].items():
        assert sha256(target/name) == digest, name
    result = run_verifier('c', target/f"{baseline['program']}.c", target, settings, executables)
    full_models = result.get('counterexample_models', [])
    candidates, queries = candidate_models(target, baseline['program'], full_models)
    model_candidate_count = len(candidates)
    replay_result = replay(target, baseline, candidates)
    outcome = ('specification violation' if replay_result['status'] == 'validated_violation' else
               'precondition/RTE failure' if replay_result['status'] == 'validated_safety_failure' else result['outcome'])
    record = {**result, 'verification_outcome': result['outcome'], 'outcome': outcome,
              'program': baseline['program'], 'role': baseline['role'], 'mutant_id': baseline['mutant_id'],
              'frozen_spec_sha256': baseline['frozen_spec_sha256'], 'raw_source_sha256': baseline['raw_source_sha256'],
              'annotated_source_sha256': baseline['annotated_source_sha256'],
              'source_record': str(origin/'record.json'), 'source_record_sha256': sha256(origin/'record.json'),
              'wp_models': full_models, 'candidate_queries': queries, 'replay': replay_result,
              'attempt_status': 'complete', 'settings': {**asdict(settings),'why3_extra_config':str(settings.why3_extra_config)},
              'verifier': executables['c']}
    write_json(record_path, record)
    print(json.dumps({'case':f"{baseline['program']}/{baseline['mutant_id'] or 'original'}",'wp':result['outcome'],
                      'outcome':outcome,'full_models':len(full_models),'model_candidates':model_candidate_count,
                      'replay':replay_result['status'],'wall_seconds':result['elapsed_seconds']}), flush=True)
    return record


def report(output, records, expected, source_run, calibration):
    counts = {role: dict(Counter(r['outcome'] for r in records if r['role'] == role)) for role in ('original','mutant')}
    wp = {role: dict(Counter(r['verification_outcome'] for r in records if r['role'] == role)) for role in ('original','mutant')}
    result = {'complete':len(records)==expected,'expected_cases':expected,'completed_cases':len(records),
              'source_run':source_run.name,'outcomes':counts,'wp_outcomes':wp,'calibration':calibration,
              'full_wp_model_count':sum(len(r['wp_models']) for r in records),
              'functional_witness_origins':dict(Counter(r['replay']['witness']['origin'].split(':')[0] for r in records if r['replay']['status']=='validated_violation')),
              'by_program':{p:{role:dict(Counter(r['outcome'] for r in records if r['program']==p and r['role']==role)) for role in ('original','mutant')} for p in sorted({r['program'] for r in records})}}
    write_json(output/'summary.json', result)
    lines = ['# C counterexample follow-up',
             f"Source experiment: `{source_run.name}`. Completed {len(records)}/{expected} cases. The original experiment is unchanged.",
             '## Method',
             'WP uses the same frozen sources/contracts and 10-second goal / 300-second process budgets, with Z3 model generation enabled. Original WP outcomes remain separate from concrete replay outcomes. Full model text and generated queries are retained locally.',
             'WP can time out because later quantified library axioms make model completion difficult. Preliminary incremental SMT queries are used only to propose scalar inputs, and are explicitly labelled partial. Their SAT results never establish rejection. A fixed bounded search (seed 726) supplies additional candidates, including valid arrays. Passing any finite search does not prove a mutant.',
             'A functional rejection requires inputs satisfying the hash-pinned original preconditions, successful execution of the unchanged C function, no UBSan diagnostic, and a returned value violating an executable equivalent of the frozen postcondition. Runtime safety failures are reported separately. Replay oracles cover only these six frozen specifications; changed hashes are refused.',
             'Java verdicts in the source run are tool-reported outcomes, not replay-validated outcomes. The supplemental C counts must not be treated as a directly matched Java/C proof-rejection rate.',
             '## Outcomes',
             '| Population | WP outcomes | WP plus validated replay outcomes |','| --- | --- | --- |']
    for role in ('original','mutant'):
        lines.append(f'| {role} | {json.dumps(wp[role],sort_keys=True)} | {json.dumps(counts[role],sort_keys=True)} |')
    lines += ['\n## By program','| Program | Original outcomes | Mutant outcomes |','| --- | --- | --- |']
    for p, values in result['by_program'].items():
        lines.append(f"| {p} | {json.dumps(values['original'],sort_keys=True)} | {json.dumps(values['mutant'],sort_keys=True)} |")
    lines += ['\n## Validated functional witnesses','| Case | Candidate origin | Inputs | Expected | Actual |','| --- | --- | --- | --- | --- |']
    for r in records:
        if r['replay']['status'] != 'validated_violation':
            continue
        witness = r['replay']['witness']
        relative = f"cases/{r['program']}/mutant_{r['mutant_id']}/c/replay.json"
        lines.append(f"| [{r['program']}/{r['mutant_id']}]({relative}) | {witness['origin']} | `{json.dumps(witness['inputs'],sort_keys=True)}` | {witness['expected']} | {witness['actual']} |")
    lines += ['\n## Evidence','See `run.json`, `summary.json`, case `record.json`, `wp-report.json`, `native_compile.json`, and `replay.json`. Solver `.smt2` files and native binaries are diagnostic artifacts and need not be committed.']
    text = ''
    for line in lines:
        text += line + ('\n' if line.startswith('|') else '\n\n')
    (output/'README.md').write_text(text)
    return result


def main():
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument('--source-run',type=Path,default=BASE)
    parser.add_argument('--output',type=Path,required=True)
    parser.add_argument('--calibrate',action='store_true')
    parser.add_argument('--workers',type=int,default=2)
    args = parser.parse_args()
    source, output = args.source_run.resolve(), args.output.resolve()
    if output == source:
        raise ValueError('Supplemental counterexample evidence needs a new output directory')
    output.mkdir(parents=True,exist_ok=True)
    source_config = read_json(source/'run.json')
    settings = Settings('openjml','frama-c','z3-4.3.X','Z3:4.8.12',300,10,'Typed+ref','x86_64',1000,4,
                        Path(source_config['settings']['why3_extra_config']),True)
    assert sha256(settings.why3_extra_config) == source_config['settings']['why3_extra_config_sha256']
    executables = {'c':source_config['verifiers']['c']}
    assert sha256(Path(executables['c']['path'])) == executables['c']['sha256']
    discovery = subprocess.run([executables['c']['path'],'-wp-list-provers','-wp-why3-extra-config',str(settings.why3_extra_config)],capture_output=True,text=True,timeout=30)
    if discovery.returncode or 'counter-examples' not in discovery.stdout:
        raise ValueError('No verified counterexample-capable Z3 configuration')
    summary = read_json(source/'summary.json')
    original_names = sorted({p['mutant'].split('/')[0] for p in summary['pair_records'] if p['c_original']=='proved'})
    assert set(original_names)==set(SPECS)
    tasks = [source/'cases'/p/'original/c' for p in original_names]
    mutants = [p for p in summary['pair_records'] if p['c_original']=='proved']
    if args.calibrate:
        tasks = [source/'cases/MaxOfTwo/original/c']
        mutants = [p for p in mutants if p['mutant'].startswith('MaxOfTwo/')]
        assert len(mutants)==2
    else:
        assert len(mutants)==80
    tasks += [source/'cases'/p['mutant'].split('/')[0]/f"mutant_{p['mutant'].split('/')[1]}"/'c' for p in mutants]
    metadata = {'source_run':str(source),'source_summary_sha256':sha256(source/'summary.json'),
                'source_run_sha256':sha256(source/'run.json'),'created_utc':datetime.now(timezone.utc).isoformat(),
                'settings':{**asdict(settings),'why3_extra_config':str(settings.why3_extra_config)},
                'workers':args.workers,'expected_cases':len(tasks),'calibration':args.calibrate,
                'prover_discovery':discovery.stdout,'verifier':executables['c'],
                'replay_spec_sha256':SPECS,'runner_sha256':sha256(Path(__file__)),
                'runtime_sha256':{n:sha256(REPO/'runtime/java_arrays'/n) for n in ('java_arrays.c','java_arrays.h')}}
    if (output/'run.json').exists():
        old = read_json(output/'run.json')
        for field in ('source_summary_sha256','source_run_sha256','settings','expected_cases','runner_sha256','runtime_sha256'):
            assert old[field]==metadata[field],field
    else:
        write_json(output/'run.json', metadata)
    records=[]
    # All original controls must pass before expanded mutant replay is accepted.
    original_tasks=[t for t in tasks if t.parent.name=='original']
    for task in original_tasks:
        r=evaluate(source,output,task,settings,executables)
        records.append(r)
        report(output,records,len(tasks),source,args.calibrate)
        if r['verification_outcome']!='proved' or r['replay']['status']!='no_validated_violation':
            raise ValueError(f"Original control failed: {r['program']}")
    with ThreadPoolExecutor(max_workers=args.workers) as pool:
        futures=[pool.submit(evaluate,source,output,t,settings,executables) for t in tasks if t not in original_tasks]
        for future in as_completed(futures):
            records.append(future.result())
            report(output,records,len(tasks),source,args.calibrate)
    result=report(output,records,len(tasks),source,args.calibrate)
    if args.calibrate and result['outcomes']['mutant'].get('specification violation')!=2:
        raise ValueError('Known wrong calibration mutants did not yield validated witnesses')
    print(json.dumps({'output':str(output),'summary':result},sort_keys=True),flush=True)


if __name__ == '__main__':
    main()
