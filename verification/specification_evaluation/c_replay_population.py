"""Search/replay C counterexamples without rerunning Frama-C verification."""
from __future__ import annotations
import argparse
from collections import Counter
from concurrent.futures import ThreadPoolExecutor, as_completed
from datetime import datetime, timezone
import json
import os
from pathlib import Path
import re
import shutil
import subprocess
import time

from .c_counterexamples import BASE
from .c_replay_oracles import CONTRACTS, ARRAY_MUTATORS, admissible, searchable, candidates, expected, parameters, violations, saved_test_candidates
from .manifest import REPO, sha256
from .workflow import read_json, write_json

REFRESH_PROGRAMS=('MoveFirst','MultiplyElements','NextPowerOf2','PairWise')
HELPERS = r'''
#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>
#include <inttypes.h>
#include <errno.h>
#include "RUNTIME_HEADER"
static int32_t replay_integer(char**v,int*at){return (int32_t)strtoll(v[(*at)++],NULL,10);}
static JIntArray replay_array(char**v,int*at){int32_t n=replay_integer(v,at);if(n<0)return NULL;JIntArray a=jarray_new(n);for(int32_t i=0;i<n;i++)jarray_set(a,i,replay_integer(v,at));return a;}
static JIntArray2 replay_matrix(char**v,int*at){int32_t n=replay_integer(v,at);if(n<0)return NULL;JIntArray2 a=jarray2_new_rows(n);for(int32_t i=0;i<n;i++)jarray2_set(a,i,replay_array(v,at));return a;}
static void replay_print_array(JIntArray a){if(a==NULL){printf("null");return;}int32_t n=jarray_length(a);printf("[");for(int32_t i=0;i<n;i++){if(i)printf(",");printf("%"PRId32,jarray_get(a,i));}printf("]");}
static void replay_print_matrix(JIntArray2 a){if(a==NULL){printf("null");return;}int32_t n=jarray2_length(a);printf("[");for(int32_t i=0;i<n;i++){if(i)printf(",");replay_print_array(jarray2_get(a,i));}printf("]");}
static void replay_print_double_array(JDoubleArray a){if(a==NULL){printf("null");return;}int32_t n=jdouble_array_length(a);printf("[");for(int32_t i=0;i<n;i++){if(i)printf(",");printf("%.17g",jdouble_array_get(a,i));}printf("]");}
'''


def native_harness(program, source):
    runtime_header = str(REPO/'runtime/java_arrays/java_arrays.h')
    source = source.replace('#include "java_arrays.h"', '#include "'+runtime_header+'"')
    code = HELPERS.replace('RUNTIME_HEADER',runtime_header)+source+'\nint main(int argc,char**argv){(void)argc;int at=1;\n'
    for kind,name in parameters(program):
        parser = 'replay_integer' if kind == 'int32_t' else 'replay_matrix' if kind == 'JIntArray2' else 'replay_array'
        code += f'{kind} {name}={parser}(argv,&at);\n'
    kind = CONTRACTS[program]['return_type']
    args = ','.join(name for _,name in parameters(program))
    code += f'errno=0;{kind} result={CONTRACTS[program]["entry"]}({args});int result_errno=errno;\n'
    code += 'printf("\\n@@REPLAY {\\"result\\":");'
    printers = {'int32_t':'printf("%"PRId32,result);','JIntArray':'replay_print_array(result);','JIntArray2':'replay_print_matrix(result);','JDoubleArray':'replay_print_double_array(result);'}
    code += printers[kind]+'printf(",\\"state\\":{");'
    arrparams = [(k,n) for k,n in parameters(program) if k.startswith('J')]
    for index,(k,n) in enumerate(arrparams):
        code += f'printf("{"," if index else ""}\\"{n}\\":");'+('replay_print_matrix' if k=='JIntArray2' else 'replay_print_array')+f'({n});'
    code += 'printf("},\\"aliases\\":{");'
    for index,(_,n) in enumerate(arrparams):
        flag = f'(void*)result==(void*){n}' if kind.startswith('J') else '0'
        code += f'printf("{"," if index else ""}\\"{n}\\":%s",({flag})?"true":"false");'
    if kind == 'JIntArray2':
        code += 'int separate=1;if(result!=NULL){int32_t n=jarray2_length(result);for(int32_t i=0;i<n;i++)for(int32_t j=i+1;j<n;j++)if(jarray2_get(result,i)==jarray2_get(result,j))separate=0;} '
    else: code += 'int separate=1;'
    code += 'printf("},\\"rows_separate\\":%s,\\"errno\\":%d,\\"EDOM\\":%d}\\n",separate?"true":"false",result_errno,EDOM);return 0;}\n'
    return code


def native_args(program, inputs):
    args=[]
    def array(a): return [-1] if a is None else [len(a),*a]
    for kind,name in parameters(program):
        value=inputs[name]
        if kind=='int32_t': args.append(value)
        elif kind=='JIntArray': args.extend(array(value))
        elif value is None: args.append(-1)
        else:
            args.append(len(value))
            for row in value: args.extend(array(row))
    return list(map(str,args))


def execute(binary, program, inputs, timeout=0.5):
    started=time.monotonic()
    try:
        response=subprocess.run([str(binary),*native_args(program,inputs)],capture_output=True,text=True,timeout=timeout,
            env={**os.environ,'ASAN_OPTIONS':'detect_leaks=0:halt_on_error=1','UBSAN_OPTIONS':'halt_on_error=1:print_stacktrace=1'})
    except subprocess.TimeoutExpired:
        return {'status':'timeout','elapsed_seconds':time.monotonic()-started}
    result={'exit_code':response.returncode,'stdout':response.stdout,'stderr':response.stderr,'elapsed_seconds':time.monotonic()-started}
    if response.returncode in (71,72,73) or 'runtime error:' in response.stderr or 'ERROR: AddressSanitizer:' in response.stderr:
        result['status']='runtime_safety'
    elif response.returncode==0:
        values=re.findall(r'(?m)^@@REPLAY (.+)$',response.stdout)
        try:
            result['observation']=json.loads(values[-1])
            result['status']='returned' if not response.stderr.strip() else 'diagnostic'
        except (IndexError,ValueError): result['status']='unavailable_output'
    else: result['status']='abnormal_exit'
    return result


def compile_native(directory, program, audit=False):
    suffix='audit' if audit else 'search'
    harness=directory/'native_replay.c'; binary=directory/f'native_replay_{suffix}'
    command=['gcc','-std=c11','-O1' if audit else '-O0','-g','-Wall','-Werror=return-type']
    if audit: command+=['-Werror=uninitialized','-Werror=maybe-uninitialized']
    command+=['-fsanitize=address,undefined' if audit else '-fsanitize=undefined','-fno-sanitize-recover=all',str(harness),
        str(REPO/'runtime/java_arrays/java_arrays.c'),'-lm','-o',str(binary)]
    response=subprocess.run(command,capture_output=True,text=True,timeout=60)
    record={'command':command,'exit_code':response.returncode,'stdout':response.stdout,'stderr':response.stderr,
        'harness_sha256':sha256(harness),'binary_sha256':sha256(binary) if response.returncode==0 else None}
    write_json(directory/f'native_{suffix}_compile.json',record)
    return binary if response.returncode==0 else None


def prepare(source, output, origin):
    baseline=read_json(origin/'record.json'); p=baseline['program']
    assert baseline['frozen_spec_sha256']==CONTRACTS[p]['frozen_spec_sha256'],p
    assert sha256(source/'frozen_specs/c'/f'{p}.json')==baseline['frozen_spec_sha256']
    target=output/origin.relative_to(source); target.mkdir(parents=True,exist_ok=True)
    annotated=origin/f'{p}.c'
    if annotated.exists():
        assert sha256(annotated)==baseline['annotated_source_sha256']
        actual=annotated; kind='frozen_annotated_source'
    else:
        actual=Path(baseline['raw_source']); kind='raw_source_after_annotation_transfer_failure'
        assert sha256(actual)==baseline['raw_source_sha256']
    shutil.copy2(actual,target/f'{p}.c')
    (target/'native_replay.c').write_text(native_harness(p,actual.read_text()))
    return baseline,target,kind


def stored_model_candidates(origin, p):
    """Reuse saved concrete scalar candidates; this stage invokes no WP."""
    if any(kind!='int32_t' for kind,_ in parameters(p)): return []
    result=[]
    old=REPO/'verification/specification_evaluation/results/c_counterexamples_proved_originals_20261001_goal10_case300'/origin.relative_to(BASE)
    if (old/'replay.json').exists():
        replay=read_json(old/'replay.json')
        for attempt in replay.get('attempts',[]):
            if attempt.get('inputs') is not None: result.append((attempt['inputs'],'saved_candidate:'+attempt.get('origin','unknown')))
    return result


def run_case(source, output, origin, controls):
    started=time.monotonic(); baseline,target,source_kind=prepare(source,output,origin); p=baseline['program']
    path=target/'record.json'
    if path.exists():
        old=read_json(path)
        if old.get('attempt_status')=='complete':
            assert old['source_record_sha256']==sha256(origin/'record.json')
            return old
    write_json(target/'status.json',{'status':'searching','program':p,'mutant_id':baseline['mutant_id'],'started_utc':datetime.now(timezone.utc).isoformat()})
    binary=compile_native(target,p)
    trial_inputs=stored_model_candidates(origin,p)+saved_test_candidates(p)+[(x,'bounded_search_seed726') for x in candidates(p)]
    trials=[];seen=set();rejected_candidates=Counter();pending_safety=None;witness=None;independent=None;timeouts=0
    audit_binary=None; audit_failed=False
    if binary:
        for x, candidate_origin in trial_inputs:
            signature=json.dumps(x,sort_keys=True)
            if signature in seen: continue
            seen.add(signature)
            if not admissible(p,x): rejected_candidates['violates_precondition']+=1;continue
            if not searchable(p,x): rejected_candidates['exceeds_search_resource_limit']+=1;continue
            run=execute(binary,p,x)
            trial={'inputs':x,'origin':candidate_origin,'preconditions_satisfied':True,'expected':expected(p,x),**run}
            failed=violations(p,x,run['observation']) if run['status']=='returned' else []
            trial['failed_clauses']=failed
            trials.append(trial)
            if failed or run['status'] in ('runtime_safety','abnormal_exit'):
                if audit_binary is None and not audit_failed:
                    audit_binary=compile_native(target,p,True);audit_failed=audit_binary is None
                if audit_binary:
                    check=execute(audit_binary,p,x,2)
                    check_failed=violations(p,x,check['observation']) if check['status']=='returned' else []
                    reproduced=(bool(failed) and check['status']=='returned' and check['observation']==run['observation'] and check_failed==failed or run['status'] in ('runtime_safety','abnormal_exit') and check['status']=='runtime_safety')
                    control=None
                    if baseline['role']=='mutant' and controls.get(p): control=execute(controls[p],p,x,2)
                    baseline_failures=violations(p,x,control['observation']) if control and control['status']=='returned' else []
                    audit={'passed':bool(reproduced),'execution':check,'failed_clauses':check_failed,'baseline_execution':control,
                        'baseline_failed_clauses':baseline_failures,'baseline_passed':bool(control and control['status']=='returned' and not baseline_failures)}
                    if reproduced:
                        trial['validated']=True
                        if failed: witness=trial;independent=audit;break
                        if pending_safety is None: pending_safety=(trial,audit)
                else: trial['audit_status']='stricter_compilation_failed'
            if run['status']=='timeout':
                timeouts+=1
                if timeouts>=3: break
    if witness: status='validated_violation';outcome='specification violation'
    elif pending_safety: status='validated_safety_failure';outcome='precondition/RTE failure';witness,independent=pending_safety
    else: status='no_validated_violation' if binary else 'compilation_unavailable';outcome=baseline['outcome']
    replay={'status':status,'checked':len(trials),'witness':witness,'independent_audit':independent,'rejected_candidates':dict(rejected_candidates),
        'search_exhausted':bool(binary and not witness and timeouts<3),'timeouts':timeouts,'audit_compilation_failed':audit_failed,
        'limitation':'Finite return/frame/identity checks; passing is not proof; does not validate every ACSL clause or WP loop goal.'}
    write_json(target/'replay.json',{**replay,'attempts':trials})
    record={'program':p,'role':baseline['role'],'mutant_id':baseline['mutant_id'],'language':'c','outcome':outcome,
        'verification_outcome':baseline['outcome'],'verification_reused':True,'verification_rerun':False,'attempt_status':'complete',
        'source_record':str(origin/'record.json'),'source_record_sha256':sha256(origin/'record.json'),
        'frozen_spec_sha256':baseline['frozen_spec_sha256'],'replay_source_kind':source_kind,'replay_source_sha256':sha256(target/f'{p}.c'),
        'search_elapsed_seconds':time.monotonic()-started,'replay':replay}
    write_json(path,record);write_json(target/'status.json',{'status':'complete'})
    print(json.dumps({'case':f"{p}/{baseline['mutant_id'] or 'original'}",'outcome':outcome,'replay':status,'checked':len(trials),'seconds':round(record['search_elapsed_seconds'],2)}),flush=True)
    return record


def report(output, records, expected_cases, source, complete=False):
    counts={role:dict(Counter(r['outcome'] for r in records if r['role']==role)) for role in ('original','mutant')}
    validated=[r for r in records if r['replay']['status'].startswith('validated_')]
    programs={p:{role:dict(Counter(r['replay']['status'] for r in records if r['program']==p and r['role']==role)) for role in ('original','mutant')} for p in sorted(CONTRACTS)}
    data={'complete':complete and len(records)==expected_cases,'completed_cases':len(records),'expected_cases':expected_cases,
        'source_run':source.name,'mode':'counterexample_search_only','verification_rerun':False,'outcomes':counts,
        'replay_statuses':dict(Counter(r['replay']['status'] for r in records)),
        'mutant_specific_validated_failures':sum(r['role']=='mutant' and r['replay']['independent_audit']['baseline_passed'] for r in validated),
        'baseline_also_fails_or_unavailable':sum(r['role']=='mutant' and not r['replay']['independent_audit']['baseline_passed'] for r in validated),
        'search_worker_seconds':sum(r['search_elapsed_seconds'] for r in records if isinstance(r.get('search_elapsed_seconds'),(int,float))),'by_program':programs}
    write_json(output/'summary.json',data)
    lines=['# C counterexample search across the full experiment','',f"Completed **{len(records)}/{expected_cases}** cases. Source: `{source.name}`.",'',
        'This is a supplemental counterexample search. Existing Frama-C outcomes are reused; no Frama-C proof run is invoked. The original experiment is unchanged. Candidate inputs are checked against the hash-pinned frozen requires clauses, then executed on the exact recorded C source with UBSan. Any reported failure is independently reproduced at O1 with ASan and UBSan. Correct-original observations are retained for each mutant witness; failures also present in the original are counted separately.', '',
        'The search checks frozen return values, array contents, input frames, return identity and selected allocation properties. It does not check every ACSL clause or reconstruct every WP loop invariant. Passing finite trials is not proof. Timeouts, stricter compiler failures and missing native output are unresolved. Cases whose annotation transfer failed use their hash-pinned raw C source for replay and retain the original WP tool-failure classification. Replays construct valid JArray inputs and execute the fixed runtime; this can expose program errors while the JArray proof encoding remains unresolved.', '',
        '## Results','','| Population | WP plus independently validated replay |','| --- | --- |']
    for role,count in counts.items(): lines.append(f'| {role} | {json.dumps(count,sort_keys=True)} |')
    lines+=['',f"Mutant failures with a passing original on the same witness: **{data['mutant_specific_validated_failures']}**. Original also fails or cannot be checked: **{data['baseline_also_fails_or_unavailable']}**.",'',
        '## Program coverage','','| Program | Original replay | Mutant replay |','| --- | --- | --- |']
    for p,count in programs.items(): lines.append(f"| {p} | {json.dumps(count['original'],sort_keys=True)} | {json.dumps(count['mutant'],sort_keys=True)} |")
    lines+=['','## Counterexamples','','| Case | Kind | Input | Failed clauses | Original passes |','| --- | --- | --- | --- | --- |']
    for r in validated:
        witness=r['replay']['witness']; role='original' if r['role']=='original' else f"mutant_{r['mutant_id']}"
        lines.append(f"| [{r['program']}/{r['mutant_id'] or 'original'}](cases/{r['program']}/{role}/c/replay.json) | {r['replay']['status']} | `{json.dumps(witness['inputs'],sort_keys=True)}` | {', '.join(witness['failed_clauses']) or 'runtime safety'} | {r['replay']['independent_audit']['baseline_passed']} |")
    lines+=['','## Reproduce','',f'Run `python3 -m verification.specification_evaluation.c_replay_population --output {output.relative_to(REPO).as_posix()} --workers 2`. Completed cases resume only if the recorded runner, oracle, runtime and source hashes match. Solver traces and binaries stay local.','']
    (output/'README.md').write_text('\n'.join(lines))
    return data


def refresh_corrected_contracts(source_run):
    """Pin the four migrated originals to their current frozen ACSL contracts."""
    contracts=json.loads((Path(__file__).with_name('c_replay_contracts.json')).read_text(encoding='utf-8-sig'))
    for p in REFRESH_PROGRAMS:
        frozen=read_json(source_run/'frozen_specs/c'/f'{p}.json')
        case=source_run/'cases'/p/'original/c'/f'{p}.c'
        source=case.read_text(encoding='utf-8')
        signature=re.search(r'(?m)^(int32_t|double|JIntArray2?|JDoubleArray)\s+(\w+)\(([^\n]*)\)\s*\{',source)
        assert signature and signature[2]==contracts[p]['entry'],p
        ann=next(a for a in frozen['annotations'] if a['target']=='function' and a['function']==signature[2])
        contract=ann['text']
        contracts[p]={'return_type':signature[1],'entry':signature[2],
            'signature':signature[0]+' {','parameters':[x.strip() for x in signature[3].split(',')],
            'contract':contract,'frozen_spec_sha256':sha256(source_run/'frozen_specs/c'/f'{p}.json')}
        baseline=read_json(source_run/'cases'/p/'original/c/record.json')
        assert baseline['frozen_spec_sha256']==contracts[p]['frozen_spec_sha256']
        assert sha256(case)==baseline['annotated_source_sha256']
    path=Path(__file__).with_name('c_replay_contracts.json')
    write_json(path,contracts)
    from . import c_replay_oracles
    c_replay_oracles.CONTRACTS.clear();c_replay_oracles.CONTRACTS.update(contracts)
    return contracts


def reuse_compatible_witnesses(source, output):
    """Seed unchanged, independently audited witnesses; changed sources rerun."""
    reused=0
    for path in sorted((source/'counterexamples').glob('*/cases/*/*/c/record.json')):
        try: record=read_json(path)
        except (OSError,json.JSONDecodeError): continue
        if record.get('replay',{}).get('status') not in ('validated_violation','validated_safety_failure'):
            continue
        origin=Path(record.get('source_record',''))
        if not origin.is_file() or sha256(origin)!=record.get('source_record_sha256'): continue
        baseline=read_json(origin);p=record['program']
        if baseline['frozen_spec_sha256']!=CONTRACTS[p]['frozen_spec_sha256']: continue
        oldsource=path.parent/f'{p}.c'
        if not oldsource.is_file(): continue
        expected_source=baseline.get('annotated_source_sha256',baseline['raw_source_sha256'])
        if sha256(oldsource)!=expected_source: continue
        target=output/path.parent.relative_to(source)
        target.mkdir(parents=True,exist_ok=True)
        for name in ('record.json','replay.json','native_replay.c','native_compile.json','native_audit_compile.json',f'{p}.c'):
            artifact=path.parent/name
            if artifact.is_file(): shutil.copy2(artifact,target/name)
        reused_record=read_json(target/'record.json')
        reused_record['reused_previously_audited_counterexample']=True
        write_json(target/'record.json',reused_record)
        reused+=1
    return reused


def main():
    parser=argparse.ArgumentParser(description=__doc__)
    parser.add_argument('--source-run',type=Path,default=BASE)
    parser.add_argument('--output',type=Path,required=True)
    parser.add_argument('--workers',type=int,default=2)
    parser.add_argument('--program',action='append',default=[])
    args=parser.parse_args();source=args.source_run.resolve();output=args.output.resolve()
    if args.workers<1 or output==source: raise ValueError('Need positive workers and a separate output directory')
    if set(args.program)-set(CONTRACTS): raise ValueError('Unknown program')
    refresh_corrected_contracts(source)
    output.mkdir(parents=True,exist_ok=True)
    origins=sorted((source/'cases').glob('*/*/c'))
    origins=[p for p in origins if (p/'record.json').exists() and (not args.program or p.parent.parent.name in args.program)]
    assert len(origins)==1027 if not args.program else bool(origins)
    metadata={'created_utc':datetime.now(timezone.utc).isoformat(),'source_run':str(source),'source_run_sha256':sha256(source/'run.json'),
        'source_summary_sha256':sha256(source/'summary.json'),'mode':'counterexample_search_only','verification_rerun':False,
        'workers':args.workers,'expected_cases':len(origins),'programs':args.program or sorted(CONTRACTS),
        'code_sha256':{name:sha256(Path(__file__).with_name(name)) for name in ('c_replay_population.py','c_replay_oracles.py','c_replay_contracts.json','c_counterexamples.py')},
        'runtime_sha256':{name:sha256(REPO/'runtime/java_arrays'/name) for name in ('java_arrays.c','java_arrays.h')}}
    metadata['candidate_tests_sha256']=sha256(REPO/'differential_testing/generation/test_inputs-653ade686f.json')
    metadata['tools']={}
    compiler=Path(shutil.which('gcc')).resolve()
    metadata['tools']['gcc']={'path':str(compiler),'sha256':sha256(compiler),'version':subprocess.run([str(compiler),'--version'],capture_output=True,text=True,timeout=10).stdout.splitlines()[0]}
    if (output/'run.json').exists():
        old=read_json(output/'run.json')
        for field in ('source_run_sha256','source_summary_sha256','expected_cases','programs','code_sha256','runtime_sha256','tools','candidate_tests_sha256'): assert old[field]==metadata[field],field
    else: write_json(output/'run.json',metadata)
    reused=reuse_compatible_witnesses(source,output)
    originals=[p for p in origins if p.parent.name=='original'];mutants=[p for p in origins if p not in originals]
    records=[];controls={};report(output,records,len(origins),source)
    # Establish every original's finite-search behavior, including any defects.
    with ThreadPoolExecutor(max_workers=args.workers) as pool:
        for future in as_completed([pool.submit(run_case,source,output,p,{}) for p in originals]):
            record=future.result();records.append(record);report(output,records,len(origins),source)
    for p in originals:
        _,directory,_=prepare(source,output,p)
        binary=compile_native(directory,p.parent.parent.name,True)
        if binary: controls[p.parent.parent.name]=binary
    with ThreadPoolExecutor(max_workers=args.workers) as pool:
        for future in as_completed([pool.submit(run_case,source,output,p,controls) for p in mutants]):
            records.append(future.result());report(output,records,len(origins),source)
    result=report(output,records,len(origins),source,True)
    write_json(output/'audit.json',{'passed':all(r['replay']['independent_audit']['passed'] for r in records if r['replay']['status'].startswith('validated_')),
        'checks':sum(r['replay']['status'].startswith('validated_') for r in records),'summary_sha256':sha256(output/'summary.json'),
        'method':'Independent O1 ASan/UBSan recompilation and replay of every accepted witness; frozen-contract checks and baseline observation retained.'})
    print(json.dumps({**result,'reused_compatible_validated_witnesses':reused}),flush=True)


if __name__=='__main__': main()
