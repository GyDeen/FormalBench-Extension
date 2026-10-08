"""Primary C runtime postcondition detection using EvoSuite and bounded inputs."""
from __future__ import annotations
from collections import Counter
from concurrent.futures import ThreadPoolExecutor, as_completed
import json
import os
from pathlib import Path
import time
from verification.specification_evaluation.manifest import DEFAULT_C, REPO
from verification.specification_evaluation.workflow import read_json, write_json
from .c_contracts import CONTRACTS, parameters, admissible, searchable, violations, matches_frozen_contract
from .c_native import native_harness, compile_native, execute
from .inputs import candidates

PRIMARY_CLAUSE = 'frozen_return_value_or_array_contents'
POLICY = 'c-runtime-frozen-postconditions-evosuite-bounded-v1'
COVERAGE = {'primary': 'frozen return-value/content postconditions',
            'other_checks': 'observable input-array frames, result identity and selected allocation checks',
            'unchecked': ['loop invariants', 'loop variants', 'statement assertions', 'full allocation/free sets', 'full write sets'],
            'unsupported_policy': 'visible evaluation failure; never treated as satisfied'}


def pool(program, tests):
    rows=[]; params=parameters(program)
    for test in tests['tests']:
        steps=test.get('steps',[])
        if not steps or steps[0]['class']!=program or steps[0]['function']!=CONTRACTS[program]['entry']:continue
        fixtures={x['id']:x['value'] for x in test.get('fixtures',[]) if 'value' in x}
        if len(steps[0]['arguments'])!=len(params):raise ValueError('Saved argument count mismatch')
        args=steps[0]['arguments']
        row={name:a['value'] if 'value' in a else fixtures[a['ref']] for (_,name),a in zip(params,args)}
        rows.append((row,'saved_test:'+test['id']))
    saved=len(rows); bounded=candidates(program,params)
    rows += [(x,'bounded_search_seed726') for x in bounded]
    seen=set(); accepted=[];excluded=Counter()
    for x,origin in rows:
        key=json.dumps(x,sort_keys=True)
        if key in seen:continue
        seen.add(key)
        if not admissible(program,x):excluded['outside_frozen_entry_domain']+=1;continue
        if not searchable(program,x):excluded['search_resource_limit']+=1;continue
        accepted.append({'inputs':x,'origin':origin,'preconditions_satisfied':True})
    return accepted, {'saved_first_calls':saved,'bounded_unique_candidates':len(bounded),
                      'unique_candidates':len(seen),'admissible_inputs':len(accepted),'excluded':dict(excluded)}


def case(study, output, program, mid, raw, inputs, counts, baseline=None, require_binary=False):
    role='original' if mid is None else 'mutant_'+mid
    directory=output/'cases'/program/role/'c';directory.mkdir(parents=True,exist_ok=True)
    path=directory/'record.json'
    if path.exists():
        old=read_json(path)
        usable_binary=old.get('search_binary') and Path(old['search_binary']).exists()
        if old.get('complete') and old.get('policy')==POLICY and (not require_binary or usable_binary):return old
    record={'policy':POLICY,'program':program,'mutant_id':mid,'role':role,'language':'c',
            'coverage':COVERAGE,'specification_detected':False,'safety_detected':False,
            'other_contract_detected':False,'input_pool':counts,'checked_inputs':0,'timeouts':0,
            'witness':None,'safety_witness':None,'source':str(raw),'evaluation_failures':0}
    started=time.monotonic()
    try:
        frozen=read_json(study/f'frozen_specs/c/{program}.json')
        if not matches_frozen_contract(program, frozen):
            raise ValueError('No executable checker matches the unchanged frozen entry contract')
        write_json(directory/'frozen_contract.json',frozen)
        (directory/(program+'.c')).write_text(raw.read_text())
        (directory/'native_replay.c').write_text(native_harness(program,raw.read_text()))
        if baseline and baseline['status']!='original_passed_all_inputs':raise ValueError('Original runtime checking unavailable or contradictory')
        binary=compile_native(directory,program)
        if binary is None:raise ValueError('Native compilation failed')
        audit_binary=None; other=None; categories=Counter()
        with (directory/'trials.jsonl').open('w') as stream:
            for candidate in inputs:
                x=candidate['inputs'];run=execute(binary,program,x,.5)
                failed=violations(program,x,run['observation']) if run['status']=='returned' else []
                trial={**candidate,'observation':run,'failed_clauses':failed}
                record['checked_inputs']+=1;categories[run['status']]+=1
                if failed or run['status']=='runtime_safety':
                    if audit_binary is None:audit_binary=compile_native(directory,program,True)
                    if audit_binary is None:
                        record['evaluation_failures']+=1
                    else:
                        replay=execute(audit_binary,program,x,2)
                        replay_failed=violations(program,x,replay['observation']) if replay['status']=='returned' else []
                        original=execute(Path(baseline['search_binary']),program,x,2) if baseline else None
                        baseline_ok=original is not None and original['status']=='returned' and not violations(program,x,original['observation'])
                        trial['audit']={'sanitized_replay':replay,'replayed_failed_clauses':replay_failed,
                                        'original':original,'original_passed':baseline_ok}
                        admitted=baseline_ok if baseline else True
                        if admitted and PRIMARY_CLAUSE in failed and PRIMARY_CLAUSE in replay_failed and run['observation']==replay['observation']:
                            record['specification_detected']=True;record['witness']=trial
                        if admitted and run['status']=='runtime_safety' and replay['status']=='runtime_safety':
                            record['safety_detected']=True
                            if record['safety_witness'] is None:record['safety_witness']=trial
                        if admitted and any(c!=PRIMARY_CLAUSE and c in replay_failed for c in failed):
                            record['other_contract_detected']=True;other=trial
                elif run['status'] not in {'returned','timeout'}:record['evaluation_failures']+=1
                stream.write(json.dumps(trial)+'\n');stream.flush()
                if record['specification_detected'] or mid is None and (failed or run['status']!='returned'):break
                if run['status']=='timeout':
                    record['timeouts']+=1
                    if record['timeouts']>=3:break
        record['search_binary']=str(binary);record['other_contract_witness']=other
        record['search_exhausted']=record['checked_inputs']==len(inputs);record['observation_categories']=dict(categories)
        if record['specification_detected']:record['status']='validated_postcondition_violation'
        elif mid is None:
            record['status']='original_passed_all_inputs' if record['search_exhausted'] and not record['evaluation_failures'] and categories==Counter(returned=len(inputs)) else 'original_runtime_contradiction_or_evaluation_failure'
        elif record['safety_detected']:record['status']='validated_safety_failure'
        elif record['evaluation_failures'] or record['timeouts']:record['status']='search_unresolved'
        else:record['status']='no_postcondition_violation_found'
    except Exception as error:
        record.update(status='evaluation_error',reason=str(error))
    record.update(complete=True,elapsed_seconds=round(time.monotonic()-started,3))
    write_json(path,record);print('C',program,role,record['status'],flush=True)
    return record


def summarize(records,originals,output,source,programs):
    per_program=[]
    for p in programs:
        rows=[r for r in records if r['program']==p]
        evaluated=sum(r['checked_inputs']>0 for r in rows)
        per_program.append({'program':p,'selected_mutants':len(rows),'runtime_evaluated_mutants':evaluated,
                            'postcondition_detected':sum(r['specification_detected'] for r in rows),
                            'safety_detected':sum(r['safety_detected'] for r in rows),
                            'unavailable_mutants':len(rows)-evaluated,'original_status':originals[p]['status']})
    evaluated=sum(r['runtime_evaluated_mutants'] for r in per_program);n=sum(r['postcondition_detected'] for r in per_program)
    result={'policy':POLICY,'complete':True,'source_run':str(source),'per_program':per_program,
            'selected_mutants':len(records),'runtime_evaluated_mutants':evaluated,'postcondition_detected':n,
            'safety_detected':sum(r['safety_detected'] for r in records),
            'runtime_evaluable_detection_rate':n/evaluated if evaluated else None,'unavailable_mutants':len(records)-evaluated,
            'coverage':COVERAGE}
    write_json(output/'summary.json',result)
    (output/'README.md').write_text('# C runtime contract mutant detection\n\nPrimary: reproduced frozen return-value/content postcondition violations on admitted inputs. Safety and frame/other-clause failures are separate. Saved EvoSuite first calls precede bounded seed-726 inputs. O0/UBSan search and O1/ASan+UBSan replay use 0.5/2 second limits. Frozen contracts are matched by text; unchecked clauses and evaluation failures remain visible.\n')
    return result


def run_c(study,output,programs=None,workers=4,manifest=None,max_pairs=None):
    study=Path(study).resolve();output=Path(output).resolve();output.mkdir(parents=True,exist_ok=True)
    programs=tuple(sorted(p.parent.parent.parent.name for p in study.glob('cases/*/original/c/record.json')
                          if read_json(p).get('outcome')=='proved' and (not programs or p.parent.parent.parent.name in programs)))
    if not programs:raise ValueError('No fully verified C originals selected')
    manifest_path=Path(manifest) if manifest else REPO/'FormalBench-data/FilteredData/fault_mutants/run_ukcw__uc/selection_manifest.json'
    selected=read_json(manifest_path);tests=read_json(REPO/'differential_testing/generation/test_inputs-653ade686f.json')
    keys=sorted([k for group in selected['retained'].values() for k in group if k.split('/')[0] in programs])
    if max_pairs is not None:keys=keys[:max_pairs]
    pending_programs=set()
    for key in keys:
        p,mid=key.split('/')
        saved_path=output/f'cases/{p}/mutant_{mid}/c/record.json'
        saved=read_json(saved_path) if saved_path.exists() else None
        if not saved or not saved.get('complete') or saved.get('policy')!=POLICY:
            pending_programs.add(p)
    pools,counts={},{}
    for program in programs:
        pools[program],counts[program]=pool(program,tests)
        write_json(output/f'inputs/{program}.json',{'counts':counts[program],'candidates':pools[program]})
    with ThreadPoolExecutor(max_workers=workers) as executor:
        futures={executor.submit(case,study,output,p,None,DEFAULT_C/(p+'.c'),pools[p],counts[p],require_binary=p in pending_programs):p for p in programs}
        originals={futures[f]:f.result() for f in as_completed(futures)}
    with ThreadPoolExecutor(max_workers=workers) as executor:
        futures=[executor.submit(case,study,output,p,mid,manifest_path.parent/f'{p}/mutants/{mid}/c/{p}.c',pools[p],counts[p],originals[p]) for p,mid in (k.split('/') for k in keys)]
        records=[f.result() for f in as_completed(futures)]
    return summarize(records,originals,output,study,programs)
