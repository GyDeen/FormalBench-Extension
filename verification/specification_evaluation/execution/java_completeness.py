"""Primary test-based Java mutant completeness over verified originals.

Uses frozen JML, not original outputs or EvoSuite assertions, as the oracle.
The scalar candidate recipe is copied from the withdrawn C execution search.
"""
from __future__ import annotations
import argparse
from collections import Counter
from concurrent.futures import ThreadPoolExecutor, as_completed
from datetime import datetime, timezone
import json
import os
from pathlib import Path
import time

from verification.specification_evaluation.execution.java_inputs import frozen_source, input_pool, replace_entry_body, signature, runtime_specification
from verification.specification_evaluation.execution.java_rac import HARNESS, OPENJML, JAVA, Session, command_run, compile_target

REPO = Path(__file__).resolve().parents[3]
BASE = REPO/'verification/specification_evaluation/results/verifier_based'
ARCHIVE = REPO/'output/c_mutant_results_withdrawn_20261008T004534/withdrawn/verification/specification_evaluation/results/originals_stop_tool_error_20260927T161243Z_goal10_case300/counterexamples'
PILOT_PROGRAMS = ('MaxOfTwo', 'OddBitSetNumber', 'SumNums', 'TestThreeEqual', 'FindPoints')
POLICY = 'java-rac-frozen-postconditions-evosuite-bounded-pilot-v1'


def runtime_policy(omit_fresh=False):
    return POLICY + ('-omit-fresh-v1' if omit_fresh else '')


def write(path, data):
    path.parent.mkdir(parents=True, exist_ok=True)
    temp = path.with_suffix(path.suffix+'.tmp')
    temp.write_text(json.dumps(data, indent=2)+'\n')
    temp.replace(path)


def observe(target, program, entry, names, harness, directory, values, interpreted=True):
    session = Session(target, program, entry, names, harness, directory, interpreted)
    try: return session.execute(values, timeout=2)
    finally: session.close()


def search_case(program, mutant_id, raw, spec, pool, pool_info, output, harness, baseline=None, calibration=None, prior=None):
    policy = runtime_policy(bool(spec.get('runtime_omissions')))
    role = 'original' if mutant_id is None else 'mutant_'+mutant_id
    directory = output/'cases'/program/role/'java'
    record_path = directory/'record.json'
    if record_path.exists():
        old = json.loads(record_path.read_text())
        if old.get('policy') == policy and old.get('complete'):
            print('RESUME', program, role, old['status'], flush=True); return old
    started = time.monotonic()
    directory.mkdir(parents=True, exist_ok=True)
    entry = spec['annotations'][0]['function']
    record = {'policy': policy, 'program': program, 'mutant_id': mutant_id, 'role': role,
              'frozen_specification': str(BASE/f'frozen_specs/java/{program}.json'),
              'source': str(raw), 'input_pool': pool_info, 'specification_detected': False,
              'safety_detected': False, 'other_jml_detected': False, 'witness': None,
              'checked_inputs': 0, 'timeouts': 0, 'evaluation_failures': 0,
              'language': 'java', 'runtime_omissions': spec.get('runtime_omissions', [])}
    if baseline and baseline['status'] != 'original_passed_all_inputs':
        record.update(status='blocked_by_original_runtime_evaluation',
                      reason=baseline['status'], coverage=baseline.get('coverage'))
        record.update(complete=True, elapsed_seconds=0)
        write(record_path, record); return record
    try:
        source, ret, names = frozen_source(raw.read_text(), spec['annotations'], entry)
        original_signature = signature((BASE/f'cases/{program}/original/java/{program}.java').read_text(), entry)
        if (ret, names) != (original_signature[1], original_signature[2]):
            raise ValueError('Mutant entry signature differs from frozen original')
        saved_source = directory/(program+'.java'); saved_source.write_text(source)
        frozen = json.loads((BASE/f'frozen_specs/java/{program}.json').read_text())
        write(directory/'frozen_contract.json', frozen)
        write(directory/'runtime_contract.json', spec)
        compile_record = compile_target(saved_source, directory/'rac_classes')
        text = '\n'.join(a['text'] for a in spec['annotations'])
        record['coverage'] = {'frozen_ensures_clauses': text.count('ensures '),
                              'explicit_requires_clauses': text.count('requires '),
                              'assignable_clauses': text.count('assignable '),
                              'loop_clauses': 0, 'clauses_removed': [],
                              'unchecked_expressions': spec.get('runtime_omissions', []),
                              'rac_compilation_usable': compile_record['usable'],
                              'unsupported_diagnostics': compile_record['unsupported_diagnostics'],
                              'postcondition_negative_control': calibration.get(program) if calibration else None,
                              'scope': 'Executable RAC checks; frame support assessed separately in calibration.'}
        if not compile_record['usable']:
            record['status'] = 'rac_compilation_or_coverage_failure'
            record['reason'] = 'Runtime copy has a compiler error or unsupported diagnostic; see recorded omissions and diagnostics.'
        elif calibration and calibration[program]['status'] != 'postcondition_check_confirmed':
            record['status'] = 'postcondition_calibration_failure'
        else:
            plain = compile_target(saved_source, directory/'plain_classes', rac=False)
            if not plain['usable']: raise ValueError('Java-only compilation failed; cannot audit actual execution')
            session = Session(directory/'rac_classes', program, entry, names, harness, directory)
            statuses, safety_witness, other_witness = Counter(), None, None
            record['search_command'] = session.command
            try:
                with (directory/'trials.jsonl').open('w') as trials:
                    for candidate in pool:
                        run = session.execute(candidate['inputs'])
                        record['checked_inputs'] += 1
                        statuses[run['category']] += 1
                        trial = {**candidate, 'observation':run}
                        kind = run['category']
                        if kind in {'postcondition_violation', 'other_jml_failure', 'runtime_safety_failure', 'exception_contract_failure'}:
                            audit = observe(directory/'rac_classes',program,entry,names,harness,directory,candidate['inputs'])
                            original_target = directory/'rac_classes' if baseline is None else output/'cases'/program/'original/java/rac_classes'
                            original = observe(original_target,program,entry,names,harness,directory/'baseline_audit',candidate['inputs']) if baseline else None
                            actual = observe(directory/'plain_classes',program,entry,names,harness,directory/'plain_audit',candidate['inputs'])
                            audit_record = {'fresh_interpreted_rac':audit, 'original_rac':original, 'uninstrumented_mutant':actual,
                                            'original_passed': original is not None and original['category']=='returned'}
                            trial['audit'] = audit_record
                            reproduced = audit['category']==kind
                            if baseline is not None: reproduced = reproduced and audit_record['original_passed']
                            if kind=='postcondition_violation': reproduced = reproduced and actual['category']=='returned'
                            if reproduced:
                                if kind=='postcondition_violation':
                                    record['specification_detected']=True;record['witness']=trial
                                elif kind in {'runtime_safety_failure','exception_contract_failure'}:
                                    # A normal_behavior rejection counts as safety only if plain Java also fails.
                                    if actual['category']=='runtime_safety_failure':
                                        record['safety_detected']=True
                                        if safety_witness is None: safety_witness=trial
                                    else:
                                        record['evaluation_failures']+=1
                                else:
                                    record['other_jml_detected']=True
                                    if other_witness is None: other_witness=trial
                            else: record['evaluation_failures']+=1
                        elif kind in {'evaluation_error','precondition_failure','specification_evaluation_failure','execution_failure'}:
                            record['evaluation_failures']+=1
                        trials.write(json.dumps(trial)+'\n');trials.flush()
                        if record['specification_detected'] or (baseline is None and kind != 'returned'):
                            break
                        if kind=='timeout':
                            record['timeouts']+=1
                            if record['timeouts']>=3: break
                            session.close();session=Session(directory/'rac_classes',program,entry,names,harness,directory)
            finally: session.close()
            record['observation_categories']=dict(statuses)
            record['safety_witness']=safety_witness;record['other_jml_witness']=other_witness
            record['search_exhausted']=record['checked_inputs']==len(pool)
            if record['specification_detected']: record['status']='validated_postcondition_violation'
            elif baseline is None:
                record['status']='original_passed_all_inputs' if record['search_exhausted'] and statuses==Counter(returned=len(pool)) else 'original_runtime_contradiction_or_evaluation_failure'
            elif record['safety_detected']: record['status']='validated_safety_failure'
            elif record['other_jml_detected']: record['status']='validated_other_jml_failure'
            elif record['evaluation_failures'] or record['timeouts']: record['status']='search_unresolved'
            else: record['status']='no_postcondition_violation_found'
    except Exception as error:
        record['status']='evaluation_error';record['reason']=str(error)
    record.update(complete=True, elapsed_seconds=round(time.monotonic()-started,3))
    write(record_path,record)
    print('DONE', program,role,record['status'],'inputs='+str(record['checked_inputs']),flush=True)
    return record


def calibrate(program, spec, output, harness):
    entry = spec['annotations'][0]['function']
    source,_,_=frozen_source((REPO/f'FormalBench-data/FilteredData/selected_java/seed_726_per_category_10_653ade686f/{program}.java').read_text(),spec['annotations'],entry)
    _,ret,names=signature(source,entry)
    directory=output/'calibration'/program;directory.mkdir(parents=True,exist_ok=True)
    if ret!='int':
        if program!='FindPoints' or not spec.get('runtime_omissions'):
            return {'status':'unsupported_by_rac_probe','reason':'Full original compilation determines array/fresh support; no clause is removed.'}
        probes=[('nonnull','return null;',dict.fromkeys(names,0)),
                ('length','return new int[]{0};',dict.fromkeys(names,0)),
                ('ascending_content','return new int[]{0,0};',dict(zip(names,(1,3,2,4)))),
                ('descending_content','return new int[]{0,0};',dict(zip(names,(2,4,1,3)))),
                ('default_content','return new int[]{1,0};',dict.fromkeys(names,0))]
        controls=[]
        for label,statement,values in probes:
            folder=directory/label;folder.mkdir(exist_ok=True)
            control=folder/(program+'.java');control.write_text(replace_entry_body(source,entry,statement))
            compiled=compile_target(control,folder/'rac_classes')
            result={'label':label,'inputs':values,'compile_usable':compiled['usable']}
            if compiled['usable']:
                result['observation']=observe(folder/'rac_classes',program,entry,names,harness,folder,values)
            result['confirmed']=result.get('observation',{}).get('category')=='postcondition_violation'
            controls.append(result)
        result={'status':'postcondition_check_confirmed' if all(c['confirmed'] for c in controls) else 'postcondition_check_not_confirmed',
                'controls':controls,'runtime_omissions':spec['runtime_omissions']}
        write(directory/'result.json',result);return result
    # Every selected scalar frozen postcondition rejects MIN_VALUE at zero inputs.
    control=directory/(program+'.java');control.write_text(replace_entry_body(source,entry,'return Integer.MIN_VALUE;'))
    compile_record=compile_target(control,directory/'rac_classes')
    result={'oracle':'Unchanged frozen JML; only this artificial control body is altered.', 'inputs':dict.fromkeys(names,0),
            'compile_usable':compile_record['usable']}
    if compile_record['usable']:
        observation=observe(directory/'rac_classes',program,entry,names,harness,directory,result['inputs'])
        result['observation']=observation
        result['status']='postcondition_check_confirmed' if observation['category']=='postcondition_violation' else 'postcondition_check_not_confirmed'
    else: result['status']='calibration_compilation_failure'
    write(directory/'result.json',result);return result


def generic_calibration(output,harness):
    directory=output/'calibration/entry_and_frame';directory.mkdir(parents=True,exist_ok=True)
    source=directory/'EntryFrame.java'
    source.write_text('''public class EntryFrame {
 public static int state=0;
 /*@ public normal_behavior
   @ requires x >= 0;
   @ assignable \\nothing;
   @ ensures \\result == x;
   @*/
 public static int entry(int x){return x;}
 /*@ public normal_behavior
   @ assignable \\nothing;
   @ ensures \\result == x;
   @*/
 public static int frame(int x){state++;return x;}
}''')
    result={'compile':compile_target(source,directory/'rac_classes')}
    if result['compile']['usable']:
        result['precondition_excluded']=observe(directory/'rac_classes','EntryFrame','entry',['x'],harness,directory,{'x':-1})
        result['precondition_admitted']=observe(directory/'rac_classes','EntryFrame','entry',['x'],harness,directory,{'x':1})
        result['frame_violation']=observe(directory/'rac_classes','EntryFrame','frame',['x'],harness,directory,{'x':1})
    write(directory/'result.json',result);return result


def summarize(records,originals,metadata,output):
    frame = metadata['generic_calibration'].get('frame_violation', {})
    frame_status = 'violation_detected_in_control' if frame.get('category') == 'other_jml_failure' else 'violation_not_detected_in_control; do not claim assignable coverage'
    for record in list(originals.values()) + records:
        if record.get('coverage') is not None:
            record['coverage']['assignable_runtime_check_status'] = frame_status
            record['coverage']['ensures_runtime_check_status'] = 'unavailable' if not record['coverage'].get('rac_compilation_usable') else 'negative-control confirmed for supported postconditions'
            role = record['role']
            write(output/'cases'/record['program']/role/'java/record.json', record)
    rows=[]
    for program in metadata['programs']:
        subset=[r for r in records if r['program']==program]
        executable=[r for r in subset if r.get('search_command') and r['checked_inputs'] > 0]
        detected=sum(r['specification_detected'] for r in subset)
        rows.append({'program':program,'selected_mutants':len(subset),'runtime_evaluated_mutants':len(executable),
                     'postcondition_detected':detected,'safety_detected':sum(r['safety_detected'] for r in subset),
                     'other_jml_detected':sum(r['other_jml_detected'] for r in subset),
                     'unavailable_mutants':len(subset)-len(executable),'original_status':originals[program]['status'],
                     'admissible_inputs':originals[program]['input_pool']['admissible_inputs']})
    executable=sum(r['runtime_evaluated_mutants'] for r in rows);detected=sum(r['postcondition_detected'] for r in rows)
    result={'policy':metadata['policy'],'complete':True,'per_program':rows,'selected_mutants':len(records),
            'runtime_omissions':metadata.get('runtime_omissions',{}),
            'runtime_evaluated_mutants':executable,'postcondition_detected':detected,
            'runtime_evaluable_detection_rate':detected/executable if executable else None,
            'safety_detected':sum(r['safety_detected'] for r in records),'other_jml_detected':sum(r['other_jml_detected'] for r in records),
            'unavailable_mutants':len(records)-executable,'case_statuses':dict(Counter(r['status'] for r in records)),
            'assignable_runtime_check_status':frame_status,
            'elapsed_seconds':round(time.monotonic()-metadata.pop('_started'),3),
            'interpretation':'Evaluable-cohort rate is conditional on RAC compatibility; unavailable mutants are unresolved, not satisfied contracts.'}
    write(output/'summary.json',result)
    import csv
    with (output/'mutants.csv').open('w',newline='') as f:
        writer=csv.DictWriter(f,fieldnames=['program','mutant_id','status','specification_detected','safety_detected','other_jml_detected','checked_inputs','evaluation_failures','timeouts'])
        writer.writeheader();writer.writerows({k:r.get(k) for k in writer.fieldnames} for r in records)
    lines=['# Java runtime-contract mutant detection','',
           'The oracle is the frozen generated JML, not original output differences or EvoSuite assertions. Primary detections are postcondition failures reproduced in a fresh interpreted JVM, with an admitted input, a passing original RAC execution, and a normally returning uninstrumented mutant. Safety and other JML failures are separate.','',
           '| Program | Selected mutants | Evaluated | Postcondition detections | Safety | Original RAC | Admissible inputs |',
           '|---|---:|---:|---:|---:|---|---:|']
    for row in rows:
        lines.append(f"| {row['program']} | {row['selected_mutants']} | {row['runtime_evaluated_mutants']} | {row['postcondition_detected']} | {row['safety_detected']} | {row['original_status']} | {row['admissible_inputs']} |")
    lines+=['',f'Runtime-evaluable mutants: **{detected}/{executable} ({detected/executable:.2%})**.' if executable else 'No mutants runtime-evaluable.',
            f"Unavailable mutants: **{len(records)-executable}**. They are unresolved, not satisfied contracts.",'',
            '## Procedure and coverage','',
            '- Eligible originals: programs proved by the recorded source OpenJML ESC consistency run. Programs with incompatible RAC instrumentation remain visible as unavailable.',
            '- Saved EvoSuite first calls are tried before the withdrawn C scalar bounded recipe: seed 726; all tuples over {-2,-1,0,1,2,3,4,7}; 120 seeded tuples including int boundaries; additional single-bit inputs for OddBitSetNumber. The old unused array-generation RNG consumption is preserved. Duplicate tuples are tried once. No solver candidates or EvoSuite assertions are used.',
            '- Selected frozen entry contracts have no explicit requires and accept primitive int arguments. Input files record unique candidates and admissible counts before execution; zero inputs are excluded by contract preconditions.',
            '- Frozen annotations are inserted at the matching entry method, with only explicitly recorded runtime omissions applied symmetrically to originals and mutants. The full frozen contract and runtime copy are saved separately. Signature mismatches, internal annotations, or pre-existing annotations fail visibly. No generated repair is applied. No hash validation or hash provenance is added.',
            '- Each input uses a fresh classloader to restore mutant static state. Search timeout is 0.5 seconds per input after JVM startup; witness replay is 2 seconds in a fresh JVM with -Xint. Three timeouts stop a case. Search stops at the first reproduced postcondition violation; safety encountered earlier is retained. These counts do not exhaustively enumerate every failure category after detection.',
            f"- {metadata['workers']} workers, bounded CPU affinity, 192 MB per JVM. Compile timeout 60 seconds. Java arithmetic follows executable int operations and the frozen code_java_math/java_math clauses. No ESC solver is used in this run.",
            '- Compiler diagnostics for unsupported or non-executable constructs are retained and block execution. Successful compilation is supplemented with an artificial failing postcondition control for each executable program, entry-precondition and frame controls, and original executions over the full pool.',
            '- Freshness is explicitly unchecked in the runtime projection for both original and mutants; other return non-null, length and content postconditions remain. Frozen JML is unchanged.' if metadata.get('omit_fresh_requested') else '- FindPoints retains its fresh clause. Its original RAC compilation fails on that expression; its mutants are blocked by this original instrumentation failure.',
            '- Loop invariants, variants, and user assertions are absent from these frozen contracts. Their support is not established by this run.',
            '- The assignable-nothing negative control compiled successfully but returned normally after a forbidden static-field write. Frame checking is therefore NOT confirmed in installed OpenJML 21.0.27, even though no unsupported warning appeared. This restriction is recorded in every case coverage record; only postcondition detections form the primary measure. See calibration/entry_and_frame/result.json.',
            '', '## Artifacts','',
            'run.json records configuration and installed versions; inputs/ records every admitted input and provenance; calibration/ contains positive detection and precondition/frame controls; cases/ contains exact frozen JML, compile diagnostics, per-input trials, witnesses and independent replays; summary.json and mutants.csv provide counts.','',
            'This measure is test-based mutation completeness, conditioned on executable clauses and input coverage. It is not numerically interchangeable with the old ESC/WP or FormalBench verifier-outcome measure. The archived C results remain untouched.']
    (output/'README.md').write_text('\n'.join(lines)+'\n')
    metadata.update(complete=True,completed_at=datetime.now(timezone.utc).isoformat(),elapsed_seconds=result['elapsed_seconds'])
    write(output/'run.json',metadata)
    print(json.dumps(result,indent=2),flush=True)
    return result


def run_java(study, output, programs=None, workers=4, manifest=None, max_pairs=None, omit_fresh=False):
    global BASE
    BASE = Path(study).resolve()
    output=Path(output).resolve();output.mkdir(parents=True,exist_ok=True)
    if workers < 1: raise ValueError('Workers must be positive')
    verified=sorted(p.parent.parent.parent.name for p in BASE.glob('cases/*/original/java/record.json')
                    if json.loads(p.read_text()).get('outcome')=='proved')
    programs=tuple(p for p in verified if not programs or p in programs)
    if not programs: raise ValueError('No fully verified Java originals selected')
    if hasattr(os,'sched_getaffinity'):
        cpus=sorted(os.sched_getaffinity(0));os.sched_setaffinity(0,cpus[:max(1,len(cpus)*3//4)])
    tests=json.loads((REPO/'differential_testing/generation/test_inputs-653ade686f.json').read_text())
    manifest_path=Path(manifest) if manifest else REPO/'FormalBench-data/FilteredData/fault_mutants/run_ukcw__uc/selection_manifest.json'
    selected=json.loads(manifest_path.read_text())
    keys=sorted([k for group in selected['retained'].values() for k in group if k.split('/')[0] in programs],key=lambda k:(k.split('/')[0],int(k.split('/')[1])))
    if max_pairs is not None: keys=keys[:max_pairs]
    metadata={'policy':runtime_policy(omit_fresh),'omit_fresh_requested':omit_fresh,'runtime_omissions':{},'programs':list(programs),'expected_mutants':len(keys),'source_run':str(BASE),
              'input_source':str(REPO/'differential_testing/generation/test_inputs-653ade686f.json'),
              'input_recipe_source':str(REPO/'output/retired_c_search_scripts_20261008T005329/c_replay_oracles.py'),
              'workers':workers,'cpu_affinity':sorted(os.sched_getaffinity(0)),
              'started_at':datetime.now(timezone.utc).isoformat(),'complete':False,'_started':time.monotonic()}
    harness=output/'harness_classes';harness.mkdir(exist_ok=True)
    harness_source=output/'RuntimeHarness.java';harness_source.write_text(HARNESS)
    compiled=command_run([OPENJML,'--compile','-d',str(harness),str(harness_source)],output,'harness_compile')
    if compiled['exit_code']!=0: raise RuntimeError('Harness compilation failed')
    metadata['tools']={}
    for name,command in [('openjml',[OPENJML,'--version']),('java',[JAVA,'-version'])]:
        version=command_run(command,output,name+'_version',10)
        metadata['tools'][name]={'command':command,'version':(version['stdout']+version['stderr']).strip()}
    write(output/'run.json',{k:v for k,v in metadata.items() if k!='_started'})
    specs,pools,info,calibration={},{},{},{}
    for program in programs:
        original_record=json.loads((BASE/f'cases/{program}/original/java/record.json').read_text())
        if original_record['outcome']!='proved': raise ValueError('Original is not fully verified: '+program)
        specs[program]=runtime_specification(json.loads((BASE/f'frozen_specs/java/{program}.json').read_text()),omit_fresh)
        metadata['runtime_omissions'][program]=specs[program]['runtime_omissions']
        entry=specs[program]['annotations'][0]['function']
        _,_,names=signature((BASE/f'cases/{program}/original/java/{program}.java').read_text(),entry)
        pools[program],info[program]=input_pool(program,entry,names,tests,specs[program]['annotations'])
        write(output/f'inputs/{program}.json',{'program':program,'counts':info[program],'candidates':pools[program]})
        calibration[program]=calibrate(program,specs[program],output,harness)
        print('CALIBRATION',program,calibration[program]['status'],info[program]['admissible_inputs'],'inputs',flush=True)
    metadata['generic_calibration']=generic_calibration(output,harness)
    originals={}
    with ThreadPoolExecutor(max_workers=workers) as executor:
        futures={executor.submit(search_case,p,None,REPO/f'FormalBench-data/FilteredData/selected_java/seed_726_per_category_10_653ade686f/{p}.java',specs[p],pools[p],info[p],output,harness,calibration=calibration):p for p in programs}
        for future in as_completed(futures): originals[futures[future]]=future.result()
    records=[]
    with ThreadPoolExecutor(max_workers=workers) as executor:
        futures=[]
        for key in keys:
            program,mid=key.split('/')
            raw=manifest_path.parent/f'{program}/mutants/{mid}/java/{program}.java'
            futures.append(executor.submit(search_case,program,mid,raw,specs[program],pools[program],info[program],output,harness,originals[program],calibration))
        for future in as_completed(futures): records.append(future.result())
    records.sort(key=lambda r:(r['program'],int(r['mutant_id'])))
    return summarize(records,originals,metadata,output)


def main():
    parser=argparse.ArgumentParser(description=__doc__)
    parser.add_argument('--study',type=Path,default=BASE)
    parser.add_argument('--output',type=Path,default=REPO/'verification/specification_evaluation/results/execution_based/runtime_contract_java_01')
    parser.add_argument('--program',action='append')
    parser.add_argument('--workers',type=int,default=4)
    parser.add_argument('--omit-fresh',action='store_true',help='omit the known freshness conjunct from both original and mutant runtime copies; retain frozen JML')
    args=parser.parse_args()
    run_java(args.study,args.output,args.program,args.workers,omit_fresh=args.omit_fresh)


if __name__=='__main__': main()
