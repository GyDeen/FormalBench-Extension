"""Primary Java/C runtime contract mutant evaluation and shared summary."""
from __future__ import annotations
import argparse
from pathlib import Path
import statistics
from verification.specification_evaluation.workflow import read_json,write_json


def collect(output):
    languages={}
    for language in ('java','c'):
        path=Path(output)/language/'summary.json'
        if not path.exists():continue
        summary=read_json(path)
        rates=[r['postcondition_detected']/r['runtime_evaluated_mutants'] for r in summary['per_program'] if r['runtime_evaluated_mutants']]
        groups=[]
        omitted=summary.get('runtime_omissions',{})
        for adapted in (False,True):
            rows=[r for r in summary['per_program'] if bool(omitted.get(r['program']))==adapted]
            if not rows:continue
            totals={key:sum(r[key] for r in rows) for key in ('selected_mutants','runtime_evaluated_mutants','postcondition_detected','safety_detected','unavailable_mutants')}
            groups.append({'label':'freshness unchecked' if adapted else 'unchanged runtime contracts',
                           'programs':[r['program'] for r in rows],
                           'runtime_evaluable_originals':sum(r['runtime_evaluated_mutants']>0 for r in rows),**totals})
        languages[language]={**summary,'mean_program_detection_rate':statistics.mean(rates) if rates else None,
                             'program_mean_denominator':len(rates),'evaluation_groups':groups}
    result={'metric':'runtime contract postcondition mutant-detection rate','languages':languages,
            'primary_aggregation':'mean of program-level rates among runtime-evaluable verified originals',
            'pooled_rates':'descriptive; runtime compatibility exclusions reported separately',
            'safety_contributes_to_primary':False,
            'complete':bool(languages) and all(v.get('complete') for v in languages.values())}
    write_json(Path(output)/'summary.json',result)
    write_report(Path(output),result)
    return result


def write_report(output,result):
    lines=['# Primary runtime contract mutant detection','',
           'Only independently reproduced postcondition violations on admitted inputs contribute to the primary numerator. The frozen generated specification supplies the oracle; any runtime omission is explicitly recorded and applied to both original and mutants. Safety failures are separate.','',
           '| Evaluation | Verified originals | Evaluable originals | Selected mutants | Evaluable mutants | Postcondition detections | Pooled rate | Separate safety flags |',
           '|---|---:|---:|---:|---:|---:|---:|---:|']
    def add_row(label,originals,evaluable,data):
        n=data['runtime_evaluated_mutants']
        rate=f"{data['postcondition_detected']/n:.2%}" if n else 'unavailable'
        lines.append(f"| {label} | {originals} | {evaluable} | {data['selected_mutants']} | {n} | {data['postcondition_detected']} | {rate} | {data['safety_detected']} |")
    for lang,data in result['languages'].items():
        groups=data['evaluation_groups']
        if len(groups)>1:
            for group in groups:
                label=lang.capitalize()+' - '+(', '.join(group['programs'])+' (`\\fresh` unchecked)' if group['label']=='freshness unchecked' else 'unchanged contracts')
                add_row(label,len(group['programs']),group['runtime_evaluable_originals'],group)
        add_row(lang.capitalize()+(' total' if len(groups)>1 else ''),len(data['per_program']),data['program_mean_denominator'],data)
    lines+=['', 'Component rows partition the Java cohort; the Java total includes each mutant once. The separate FindPoints row identifies its reduced runtime-checking coverage.','']
    for lang,data in result['languages'].items():
        rate=data['mean_program_detection_rate']
        if rate is not None:lines.append(f"The primary mean across {data['program_mean_denominator']} runtime-evaluable {lang.capitalize()} programs is {rate:.2%}.")
    lines+=['', 'These are language-specific verified cohorts. No confidence interval or significance claim is made here.']
    if 'java' in result['languages'] and 'c' in result['languages']:
        paired={lang:{r['program']:r for r in result['languages'][lang]['per_program']} for lang in ('java','c')}
        shared=set(paired['java'])&set(paired['c'])
        if shared:
            numbers={lang:(sum(paired[lang][p]['postcondition_detected'] for p in shared),sum(paired[lang][p]['runtime_evaluated_mutants'] for p in shared)) for lang in ('java','c')}
            lines.append(f"The {len(shared)} shared verified programs have {numbers['java'][0]}/{numbers['java'][1]} Java and {numbers['c'][0]}/{numbers['c'][1]} C detections among evaluable mutants.")
    java=result['languages'].get('java',{})
    if java.get('runtime_omissions',{}).get('FindPoints'):
        lines+=['', 'FindPoints uses a runtime copy with only `\\fresh(\\result)` omitted. Non-nullness, length and all three content branches remain; the full frozen JML is unchanged. Freshness is unchecked for both original and mutants. Original-input checks, deliberately failing controls and independent witness replays are retained in the case artifacts.']
    if java.get('integration'):
        lines+=['', 'FindPoints evidence is integrated into java/cases/FindPoints/, alongside the unchanged results for the other twelve Java programs. No execution was repeated for this integration. Its earlier blocked compilation and the standalone adaptation run are retained only in the local intermediate-result archive.']
    c_run=output/'c/run.json'
    if c_run.exists() and read_json(c_run).get('execution_rerun') is False:
        lines+=['', 'C evidence reuses the withdrawn execution run, with saved observations, independent sanitized replay, preconditions and original controls rechecked against unchanged frozen contracts and input pools. No new C experiment was run. Separately declared logical definitions participate in contract matching.']
    lines+=['', 'Inputs are saved EvoSuite JSON first calls followed by the shared bounded seed-726 recipe. This mixed-input result is not an exhaustive EvoSuite-only evaluation.']
    for lang,data in result['languages'].items():
        if data.get('primary_witness_origins'):
            lines.append(f"{lang.capitalize()} primary witness origins: {data['primary_witness_origins']}.")
    lines+=['',
            'Java uses OpenJML RAC and fresh interpreted-JVM witness replay; C uses executable ACSL equivalents and independently sanitized native replay. Original-output differences and EvoSuite assertions are not detection oracles.','',
            'The Java frame negative control was not detected, so assignable coverage is not established. C loop invariants, variants, statement assertions and full write/allocation sets remain unchecked. Safety flags can overlap postcondition detections and are not exhaustive after a primary witness stops search.','',
            'Original proofs, full frozen contracts and historical verifier-mutant evidence are preserved separately under results/verifier_based/. Verifier-mutant evidence does not contribute to these runtime counts. Active evaluation code and result metadata use no hash validation or digest provenance.','',
            '[Java per-program results](java/summary.json) | [C per-program results](c/summary.json) | [Combined summary](summary.json) | [Evidence audit](audit.json)']
    (output/'README.md').write_text('\n'.join(lines)+'\n')


def run_completeness(study,output,languages=('java','c'),programs=None,workers=4,manifest=None,max_pairs=None):
    from .java_completeness import run_java
    from .c_completeness import run_c
    for language in languages:
        if language=='java':
            run_java(study,Path(output)/language,programs,workers,manifest,max_pairs,omit_fresh=True)
        else:
            run_c(study,Path(output)/language,programs,workers,manifest,max_pairs)
    return collect(output)


def main():
    parser=argparse.ArgumentParser(description=__doc__)
    parser.add_argument('--study',type=Path,required=True)
    parser.add_argument('--output',type=Path,required=True)
    parser.add_argument('--language',choices=('java','c'),action='append')
    parser.add_argument('--program',action='append')
    parser.add_argument('--workers',type=int,default=4)
    args=parser.parse_args()
    if args.workers<1:parser.error('--workers must be positive')
    run_completeness(args.study,args.output,args.language or ('java','c'),args.program,args.workers)


if __name__=='__main__':main()
