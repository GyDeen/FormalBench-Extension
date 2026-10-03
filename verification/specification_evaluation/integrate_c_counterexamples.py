"""Archive completed C replay evidence inside its frozen source experiment.

This is a report-only operation: it starts no verifier, solver or native replay.
Proof verdicts remain in the original fields; concrete evidence is supplemental.
"""
from __future__ import annotations
import argparse
from collections import Counter
from datetime import datetime, timezone
import json
from pathlib import Path
import shutil

from .manifest import REPO, DEFAULT_C, sha256
from .summarize_experiment import distribution
from .workflow import read_json, write_json

DEFAULT_RUNS = ('c_counterexamples_refreshed_all_c_20261001_search_only',)
CASE_FILES = ('record.json','wp-report.json','replay.json')


def original_summary(output, expected_hash):
    for name in ('counterexamples/source_summaries'+'/'+expected_hash+'.json',
                 'summary_verification.json','summary.json'):
        path=output/name
        if path.is_file() and sha256(path)==expected_hash:
            return path
    raise ValueError('Counterexample run does not match the source verification summary')


def integrate(output, evidence_runs):
    archives=output/'counterexamples'
    source_hashes={read_json(run/'run.json')['source_summary_sha256'] for run in evidence_runs}
    snapshots=archives/'source_summaries'
    snapshots.mkdir(parents=True,exist_ok=True)
    snapshot_paths={}
    for digest in source_hashes:
        try: found=original_summary(output,digest)
        except ValueError:
            if sha256(output/'summary.json')!=digest: raise
            found=output/'summary.json'
        snapshot=snapshots/f'{digest}.json'
        if not snapshot.exists(): shutil.copy2(found,snapshot)
        assert sha256(snapshot)==digest
        snapshot_paths[digest]=snapshot
    # The proof-only baseline is the snapshot from the original complete run.
    frozen_summary=output/'summary_verification.json'
    if not frozen_summary.exists(): frozen_summary=snapshot_paths[next(iter(source_hashes))]
    proof=read_json(frozen_summary)
    categories={p['class_name']:p['category'] for p in read_json(REPO/'FormalBench-data/FilteredData/selected_java/seed_726_per_category_10_653ade686f/selection_manifest.json')['programs']}
    selected={};runs=[];copied=0;checks=[];stale=[]
    for run in evidence_runs:
        config=read_json(run/'run.json')
        assert config['source_run_sha256']==sha256(output/'run.json')
        assert sha256(snapshot_paths[config['source_summary_sha256']])==config['source_summary_sha256']
        destination=archives/run.name
        destination.mkdir(parents=True,exist_ok=True)
        complete_records=[]
        full_audit=read_json(run/'audit.json') if (run/'audit.json').is_file() else None
        if full_audit:
            assert full_audit['passed'] and not full_audit.get('partial',False)
            assert full_audit['summary_sha256']==sha256(run/'summary.json')
        for path in sorted((run/'cases').glob('*/*/c/record.json')):
            r=read_json(path)
            if r.get('attempt_status')!='complete': continue
            p,role,mid=r['program'],r['role'],r['mutant_id']
            main=output/'cases'/p/('original' if role=='original' else f'mutant_{mid}')/'c'
            baseline=read_json(main/'record.json')
            if sha256(main/'record.json')!=r['source_record_sha256']:
                snapshot=output/'original_verification/source_records'/f"{r['source_record_sha256']}.json"
                preserved=read_json(snapshot) if snapshot.is_file() and sha256(snapshot)==r['source_record_sha256'] else None
                input_keys=('program','role','mutant_id','language','raw_source_sha256',
                            'annotated_source_sha256','frozen_spec_sha256','support_sha256','settings')
                if not preserved or any(preserved.get(k)!=baseline.get(k) for k in input_keys):
                    stale.append({'case':f'{p}/{mid or "original"}','run':run.name,
                        'reason':'Source experiment case inputs changed after this counterexample run.'})
                    continue
            assert r['frozen_spec_sha256']==baseline['frozen_spec_sha256']
            assert sha256(output/'frozen_specs/c'/f'{p}.json')==r['frozen_spec_sha256']
            replay=r['replay'];status=replay['status'];validated=status in ('validated_violation','validated_safety_failure')
            witness=replay.get('witness') or replay.get('safety_witness')
            if validated:
                assert witness and witness['preconditions_satisfied']
                if full_audit:
                    if isinstance(full_audit['checks'], list):
                        check=next(c for c in full_audit['checks'] if c['case']==f'{p}/{mid}')
                        assert check['passed'] and check['record_sha256']==sha256(path)
                        assert check['inputs']==witness['inputs']
                        baseline_passed=True
                    else:
                        audit=replay['independent_audit']
                        assert full_audit['checks'] >= 1 and audit and audit['passed']
                        assert audit['baseline_passed']
                        baseline_passed=audit['baseline_passed']
                else:
                    audit=replay['independent_audit']
                    assert audit['passed']
                    baseline_passed=audit['baseline_passed']
                checks.append({'case':f'{p}/{mid or "original"}','run':run.name,'record_sha256':sha256(path),'passed':True})
            else: baseline_passed=None
            cpath=path.parent/f'{p}.c'
            assert sha256(cpath)==r.get('replay_source_sha256',r.get('annotated_source_sha256'))
            assert sha256(cpath)==baseline.get('annotated_source_sha256',baseline['raw_source_sha256'])
            for compile_name in ('native_compile.json','native_search_compile.json','native_audit_compile.json'):
                cp=path.parent/compile_name
                if cp.is_file():
                    assert read_json(cp)['harness_sha256']==sha256(path.parent/'native_replay.c')
            target=destination/path.parent.relative_to(run)
            target.mkdir(parents=True,exist_ok=True)
            for name in CASE_FILES:
                src=path.parent/name
                if src.is_file():
                    if src.resolve()!=(target/name).resolve(): shutil.copy2(src,target/name)
                    assert sha256(target/name)==sha256(src)
                    copied+=1
            row={'program':p,'role':role,'mutant_id':mid,'category':categories[p],
                 'replay_status':status,'validated':validated,'outcome':r['outcome'],
                 'verification_outcome':baseline['outcome'],
                 'verification_outcome_at_search':r.get('verification_outcome',baseline['outcome']),
                 'current_verification_record':(main/'record.json').relative_to(output).as_posix(),
                 'current_verification_record_sha256':sha256(main/'record.json'),
                 'original_passed_on_witness':baseline_passed,
                 'record':(target/'record.json').relative_to(output).as_posix(),
                 'record_sha256':sha256(path),'run':run.name,
                 'inputs':witness['inputs'] if validated else None,
                 'expected':witness.get('expected') if validated else None,
                 'actual':witness.get('actual', witness.get('observation',{}).get('result')) if validated else None}
            key=(p,role,mid)
            previous=selected.get(key)
            rank=lambda v: 2 if v['replay_status']=='validated_violation' else 1 if v['validated'] else 0
            if previous is None or rank(row)>=rank(previous): selected[key]=row
            complete_records.append(r)
        for name in ('run.json','summary.json','audit.json','stop.json','README.md'):
            if (run/name).is_file() and (run/name).resolve()!=(destination/name).resolve(): shutil.copy2(run/name,destination/name)
        stop=read_json(run/'stop.json') if (run/'stop.json').is_file() else None
        if stop:
            assert stop['completed_cases']==len(complete_records)
            (destination/'README.md').write_text(
                f'# Stopped C counterexample search\n\nStopped at user request. Archived {len(complete_records)}/{config["expected_cases"]} completed cases. No verification was rerun. Incomplete case directories were excluded.\n\nSee the [combined experiment evidence](../README.md), [stop record](stop.json), and completed case `record.json` / `replay.json` files.\n')
        runs.append({'run':run.name,'complete':bool(full_audit),
                     'status':stop['status'] if stop else 'complete','completed_cases':len(complete_records),
                     'expected_cases':config['expected_cases'],'records_by_role':dict(Counter(r['role'] for r in complete_records)),
                     'validated_counts':dict(Counter(r['replay']['status'] for r in complete_records if r['replay']['status'].startswith('validated_'))),
                     'source_summary_sha256':config['source_summary_sha256'],
                     'archive':destination.relative_to(output).as_posix(),
                     'timing_kind':'counterexample_search_including_compile_and_audit' if config.get('verification_rerun') is False else 'verifier_wall_time_excluding_replay',
                     'timing_seconds':distribution(r.get('search_elapsed_seconds',r.get('elapsed_seconds')) for r in complete_records),
                     'verification_rerun':config.get('verification_rerun',True)})
    records=sorted(selected.values(),key=lambda r:(r['program'],r['role'],str(r['mutant_id'])))
    mutants=[r for r in records if r['role']=='mutant']
    accepted=[r for r in mutants if r['validated'] and r['original_passed_on_witness']]
    latest_source_hash=read_json(evidence_runs[-1]/'run.json')['source_summary_sha256']
    integration_baseline=read_json(snapshot_paths[latest_source_hash])
    combined=Counter(read_json(output/'summary.json')['mutants']['c'])
    for r in accepted:
        combined[r['verification_outcome']]-=1
        combined[r['outcome']]+=1
    combined=+combined
    assert sum(combined.values())==integration_baseline['eligible_pair_count']
    category_rows={}
    for category in sorted(set(categories.values())):
        eligible=[r for r in proof['pair_records'] if categories[r['mutant'].split('/')[0]]==category]
        cohort=[r for r in mutants if r['category']==category]
        category_rows[category]={'eligible_mutants':len(eligible),'searched_mutants':len(cohort),
            'validated_postcondition_violations':sum(r['replay_status']=='validated_violation' and r['original_passed_on_witness'] for r in cohort),
            'validated_safety_failures':sum(r['replay_status']=='validated_safety_failure' and r['original_passed_on_witness'] for r in cohort),
            'searched_without_validated_failure':sum(not r['validated'] for r in cohort),
            'not_searched':len(eligible)-len(cohort)}
    changed=[]
    for p in sorted(categories):
        baseline=read_json(output/'cases'/p/'original/c/record.json')
        current=DEFAULT_C/f'{p}.c'
        if sha256(current)!=baseline['raw_source_sha256']:
            changed.append({'program':p,'archived_original_raw_sha256':baseline['raw_source_sha256'],
                'current_original_raw_sha256':sha256(current),'requires_new_frozen_inputs':True})
    prior_path=archives/'summary.json'
    prior=read_json(prior_path) if prior_path.exists() else {}
    previously_changed=list(prior.get('changed_current_c_originals',[]))
    previously_changed.extend({'program':r['program'],'archived_original_raw_sha256':r['previous_original_raw_sha256']}
                             for r in prior.get('refreshed_c_originals',[])
                             if r['program'] not in {c['program'] for c in previously_changed})
    refreshed=[]
    for item in previously_changed:
        p=item['program'];current_hash=sha256(DEFAULT_C/f'{p}.c')
        if current_hash!=item['archived_original_raw_sha256']:
            refreshed.append({'program':p,'previous_original_raw_sha256':item['archived_original_raw_sha256'],
                'current_original_raw_sha256':current_hash,'current_frozen_spec_sha256':sha256(output/'frozen_specs/c'/f'{p}.json'),
                'current_recorded_wp_outcome':read_json(output/'cases'/p/'original/c/record.json')['outcome']})
    now=datetime.now(timezone.utc).isoformat()
    full_coverage=len(mutants)==977 and sum(r['role']=='original' for r in records)==50
    result={'generated_utc':now,'status':'complete_counterexample_search' if full_coverage else 'partial_counterexample_coverage','complete':full_coverage,
        'source_verification_summary':'summary_verification.json','source_verification_summary_sha256':sha256(frozen_summary),
        'source_summary_snapshots':{h:s.relative_to(output).as_posix() for h,s in snapshot_paths.items()},
        'proof_verdicts_preserved':True,'coverage':{'eligible_originals':50,'searched_originals':sum(r['role']=='original' for r in records),
            'eligible_mutants':977,'searched_mutants':len(mutants),'not_searched_mutants':977-len(mutants),
            'validated_mutant_failures':len(accepted),'searched_without_validated_mutant_failure':sum(not r['validated'] for r in mutants)},
        'validated_mutant_outcomes':dict(Counter(r['outcome'] for r in accepted)),
        'c_mutant_outcomes_with_validated_replay':dict(combined),'by_category':category_rows,
        'runs':runs,'records':records,'stale_evidence_excluded':stale,'changed_current_c_originals':changed,'refreshed_c_originals':refreshed,
        'interpretation':'Supplemental execution-validated C evidence for the archived experiment. WP verdicts are preserved as recorded. Finite passing trials are not proof. Source-record hash mismatches are excluded; corrected originals are searched only under their refreshed frozen contracts. Java and C rejection rates use different evidence.'}
    archives.mkdir(parents=True,exist_ok=True)
    write_json(archives/'summary.json',result)
    write_json(archives/'integration_audit.json',{'passed':True,'created_utc':now,'validated_evidence_records':len(checks),
        'completed_records_archived':sum(r['completed_cases'] for r in runs),'deduplicated_records':len(records),
        'source_verification_summary_sha256':sha256(frozen_summary),'source_run_sha256':sha256(output/'run.json'),
        'summary_sha256':sha256(archives/'summary.json'),'files_copied':copied,'checks':checks,
        'method':'Check source case/specification/harness hashes and existing independent sanitizer replay audits before archiving result JSON; generated sources, harnesses and compiler metadata are not archived; no verification or replay started.'})
    lines=['# Integrated C counterexample evidence','',f'Unique coverage: **{result["coverage"]["searched_originals"]}/50 originals and {len(mutants)}/977 mutants**. '+('The full-population replay search completed and passed its independent audit.' if full_coverage else 'Counterexample coverage is partial.'),'',
        f"Validated mutant failures: **{result['validated_mutant_outcomes'].get('specification violation',0)} postcondition violations and {result['validated_mutant_outcomes'].get('precondition/RTE failure',0)} safety failures**. Every accepted witness reproduced under the stronger sanitizer audit and passed its original control.",'',
        'The original verifier results are preserved in [summary_verification.json](../summary_verification.json). The parent [summary.json](../summary.json) adds this evidence as `c_counterexample_evidence`; it does not rewrite WP unknown verdicts. Passing finite replay trials is not proof. No counterexample search is currently running.','',
        '## Category coverage','','| Category | Eligible | Searched | Postcondition violations | Safety failures | Searched, no validated failure | Not searched |','| --- | --- | --- | --- | --- | --- | --- |']
    for c,v in category_rows.items(): lines.append('| '+c+' | '+' | '.join(str(v[k]) for k in ('eligible_mutants','searched_mutants','validated_postcondition_violations','validated_safety_failures','searched_without_validated_failure','not_searched'))+' |')
    lines+=['','## Archived runs','']
    for r in runs: lines.append(f"- [{r['run']}]({r['run']}/README.md): {r['completed_cases']}/{r['expected_cases']} cases; {r['status']}.")
    lines+=['','## Source versions','',
        ('Current C source changes detected for '+', '.join(v['program'] for v in changed)+'. Evidence with stale source-record hashes was excluded. ' if changed else 'No current C source changes were detected relative to the refreshed experiment records. ')+
        ('Refreshed originals: '+', '.join(v['program'] for v in refreshed)+'. Their replay results use the refreshed frozen contracts.' if refreshed else 'The full replay search used the current frozen source and contract records.'),'',
        '## Witnesses','','| Case | Kind | Inputs | Expected | Actual |','| --- | --- | --- | --- | --- |']
    for r in accepted: lines.append(f"| [{r['program']}/{r['mutant_id']}]({r['record'].removeprefix('counterexamples/').replace('record.json','replay.json')}) | {r['outcome']} | `{json.dumps(r['inputs'],sort_keys=True)}` | `{json.dumps(r['expected'])}` | `{json.dumps(r['actual'])}` |")
    lines+=['','See [summary.json](summary.json) for full-precision timing distributions and per-case provenance, and [integration_audit.json](integration_audit.json) for source checks. Canonical sources remain under FormalBench-data. Generated source snapshots, harnesses, compiler metadata, solver files and native binaries are excluded; accepted witnesses and audit results remain in record.json/replay.json.','']
    (archives/'README.md').write_text('\n'.join(lines))
    snapshot=output/'summary_verification.json'
    if not snapshot.exists(): shutil.copy2(frozen_summary,snapshot)
    assert sha256(snapshot)==result['source_verification_summary_sha256']
    summary=read_json(output/'summary.json')
    summary['c_counterexample_evidence']={k:v for k,v in result.items() if k!='records'}
    summary['c_counterexample_evidence'].update(evidence_summary='counterexamples/summary.json',
        evidence_summary_sha256=sha256(archives/'summary.json'),integration_audit='counterexamples/integration_audit.json')
    write_json(output/'summary.json',summary)
    print(json.dumps({'coverage':result['coverage'],'validated_mutant_outcomes':result['validated_mutant_outcomes'],
        'c_mutant_outcomes_with_validated_replay':dict(combined),'changed_current_c_originals':changed,'archive':str(archives)},indent=2))
    return result


def main():
    parser=argparse.ArgumentParser(description=__doc__)
    parser.add_argument('--output',type=Path,required=True)
    parser.add_argument('--evidence-run',action='append',type=Path,default=[])
    args=parser.parse_args();output=args.output.resolve()
    defaults=[output.parent/name if (output.parent/name).is_dir() else output/'counterexamples'/name for name in DEFAULT_RUNS]
    integrate(output,[p.resolve() for p in args.evidence_run] or defaults)


if __name__=='__main__': main()
