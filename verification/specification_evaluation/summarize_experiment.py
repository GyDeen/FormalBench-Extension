"""Report program-level consistency and mutation-based completeness from saved results."""
from __future__ import annotations
import argparse
from collections import Counter
from datetime import datetime, timezone
import json
import math
from pathlib import Path
import statistics
from .manifest import REPO

CATEGORIES = ('sequential', 'branch', 'single_path_loop', 'multi_path_loop', 'nested')
OUTCOMES = ('proved', 'specification violation', 'precondition/RTE failure', 'unknown/timeout', 'syntax/tool failure', 'not run')


def read(path):
    return json.loads(path.read_text(encoding='utf-8-sig'))


def percentile(values, fraction):
    values = sorted(values)
    position = (len(values) - 1) * fraction
    lower, upper = math.floor(position), math.ceil(position)
    return values[lower] + (values[upper] - values[lower]) * (position - lower)


def distribution(values):
    values = list(values)
    if not values:
        return dict.fromkeys(('total', 'mean', 'median', 'sample_sd', 'q1', 'q3', 'p95', 'min', 'max')) | {'n': 0}
    return {'n': len(values), 'total': sum(values), 'mean': statistics.mean(values), 'median': statistics.median(values),
            'sample_sd': statistics.stdev(values) if len(values) > 1 else None,
            'q1': percentile(values, .25), 'q3': percentile(values, .75), 'p95': percentile(values, .95),
            'min': min(values), 'max': max(values)}


def fmt(value, digits=3):
    return 'N/A' if value is None else f'{value:,.{digits}f}' if isinstance(value, float) else f'{value:,}'


def pct(numerator, denominator):
    return f'{100 * numerator / denominator:.2f}%' if denominator else 'N/A'


def table(headers, rows):
    return '\n'.join(['| ' + ' | '.join(headers) + ' |', '| ' + ' | '.join('---' for _ in headers) + ' |'] +
                     ['| ' + ' | '.join(map(str, row)) + ' |' for row in rows])


def build(output, selection):
    summary, config = read(output / 'summary.json'), read(output / 'run.json')
    categories = {item['class_name']: item['category'] for item in read(selection)['programs']}
    assert len(categories) == 50 and Counter(categories.values()) == Counter({c: 10 for c in CATEGORIES})
    records, paths = [], {}
    for path in sorted((output / 'cases').glob('*/*/*/record.json')):
        record = read(path)
        assert record['program'] in categories and record['outcome'] in OUTCOMES
        key = (record['program'], record['role'], record['mutant_id'], record['language'])
        assert key not in paths
        paths[key] = path
        records.append(record)
    assert len(records) == 2 * (50 + summary['eligible_pair_count']) == 2054
    for language in ('java', 'c'):
        for role in ('original', 'mutant'):
            counts = Counter(r['outcome'] for r in records if r['language'] == language and r['role'] == role)
            assert dict(counts) == summary['originals' if role == 'original' else 'mutants'][language]
    originals = {(r['program'], r['language']): r for r in records if r['role'] == 'original'}
    assert len(summary['pair_records']) == 977
    for pair in summary['pair_records']:
        program, mutant_id = pair['mutant'].split('/')
        for language in ('java', 'c'):
            assert pair[language] == read(paths[(program, 'mutant', mutant_id, language)])['outcome']
    replay_path = output / 'counterexamples/summary.json'
    replay_summary = read(replay_path) if replay_path.is_file() else None
    replay_records = [r for r in replay_summary['records'] if r['role'] == 'mutant'] if replay_summary else []
    if replay_summary:
        assert len(replay_records) == summary['eligible_pair_count']

    def replay_counts(rows):
        return dict(Counter(r['outcome'] if r['validated'] else 'no validated failure' for r in rows))

    successful, primary = {}, {}
    for language in ('java', 'c'):
        proved = [r for r in records if r['language'] == language and r['outcome'] == 'proved']
        successful[language] = {'wall_seconds': distribution(r['elapsed_seconds'] for r in proved),
                                'by_role': {role: distribution(r['elapsed_seconds'] for r in proved if r['role'] == role)
                                            for role in ('original', 'mutant')}, 'programs': [r['program'] for r in proved]}
        mutants = [r for r in records if r['language'] == language and r['role'] == 'mutant'
                   and originals[(r['program'], language)]['outcome'] == 'proved']
        primary[language] = {'originals': sum(r['outcome'] == 'proved' for (p, l), r in originals.items() if l == language),
                             'eligible': len(mutants), 'verifier_outcomes': dict(Counter(r['outcome'] for r in mutants))}
        if language == 'c' and replay_summary:
            rows = [r for r in replay_records if originals[(r['program'], 'c')]['outcome'] == 'proved']
            assert len(rows) == len(mutants)
            primary[language]['validated_replay_outcomes'] = replay_counts(rows)
    category_results = []
    for category in CATEGORIES:
        programs = [p for p, c in categories.items() if c == category]
        entry = {'category': category, 'programs': programs, 'original_count': len(programs),
                 'mutant_pairs': sum(pair['mutant'].split('/')[0] in programs for pair in summary['pair_records'])}
        for language in ('java', 'c'):
            rows = [r for r in records if r['program'] in programs and r['language'] == language]
            entry[language] = {role: dict(Counter(r['outcome'] for r in rows if r['role'] == role)) for role in ('original', 'mutant')}
        if replay_summary:
            entry['c_replay'] = replay_counts(r for r in replay_records if r['program'] in programs)
        category_results.append(entry)
    data = {'generated_utc': datetime.now(timezone.utc).isoformat(), 'run': output.name,
            'scope': 'Program-level consistency and mutation-based completeness only.',
            'category_source': selection.relative_to(REPO).as_posix(),
            'successful': successful, 'categories': category_results, 'verified_original_subsets': primary,
            'completion': {'completed_case_records': sum(r['attempt_status'] == 'complete' for r in records),
                           'total_case_records': len(records), 'not_run_case_records': sum(r['outcome'] == 'not run' for r in records)},
            'statistical_method': 'Sample SD n-1; quartiles and p95 use linear interpolation at (n-1)*p.',
            'interpretation': 'Java diagnostics and C execution witnesses are different evidence. Unknown verification '
                              'and finite searches without a validated failure establish neither correctness nor completeness.'}
    if replay_summary:
        data['c_counterexample_search'] = {'source': 'counterexamples/summary.json',
                                         'mutant_outcomes': replay_counts(replay_records), 'searched_mutants': len(replay_records),
                                         'searched_originals': sum(r['role'] == 'original' for r in replay_summary['records'])}
    return data, summary, config, records, paths


def render(data, summary, config, records, paths):
    settings = config['settings']
    outcome_headers = ['Total', 'Proved', 'Specification failures', 'Safety failures', 'Unknown/timeout', 'Tool failures', 'Not run']
    sections = ['# Consistency and completeness results',
                f"Run: `{data['run']}`. This report covers 50 originals per language and 977 retained Java/C mutant pairs. "
                f"Budgets are {settings['goal_timeout']} seconds per solver query and {settings['timeout']} seconds per process. "
                'Specifications are frozen per original and transferred to its retained mutants.',
                '## Consistency',
                'Consistency is assessed through verification of each original under its generated specification. '
                'The authoritative original program outcomes are:',
                table(['Language', *outcome_headers], [[lang.title(), 50, *[summary['originals'][lang].get(o, 0) for o in OUTCOMES]]
                                                       for lang in ('java', 'c')]),
                'Unknown/timeout is inconclusive. Safety failures are reported separately from specification failures.',
                '### Original outcomes by category',
                table(['Category', 'Language', *outcome_headers],
                      [[e['category'], lang.title(), sum(e[lang]['original'].values()), *[e[lang]['original'].get(o, 0) for o in OUTCOMES]]
                       for e in data['categories'] for lang in ('java', 'c')]),
                '### Time for fully verified originals',
                'Times are saved verifier-process wall times. The successful Java and C cohorts contain different programs; '
                'these descriptive times do not establish a paired language speed difference.',
                table(['Language', 'n', 'Total s', 'Mean s', 'Median s', 'Sample SD s', 'Min s', 'Max s'],
                      [[lang.title(), *[fmt(data['successful'][lang]['by_role']['original'][k]) for k in
                                        ('n', 'total', 'mean', 'median', 'sample_sd', 'min', 'max')]] for lang in ('java', 'c')]),
                '## Completeness',
                'Completeness is assessed by rejection of behaviour-changing mutants under their originals’ frozen specifications. '
                'Java rows use classified OpenJML diagnostics. C proof outcomes and execution-validated failures are distinct.',
                table(['Verifier', *outcome_headers], [[label, 977, *[summary['mutants'][lang].get(o, 0) for o in OUTCOMES]]
                       for lang, label in (('java', 'Java / OpenJML'), ('c', 'C / WP'))])]
    replay = data.get('c_counterexample_search')
    if replay:
        counts = replay['mutant_outcomes']
        sections += ['### Validated C mutant failures',
                     table(['Searched mutants', 'Postcondition violations', 'Safety failures', 'No validated failure'],
                           [[replay['searched_mutants'], counts.get('specification violation', 0),
                             counts.get('precondition/RTE failure', 0), counts.get('no validated failure', 0)]]),
                     f"The saved search covers {replay['searched_originals']}/50 original controls. "
                     'A finite search without a validated failure is inconclusive. Validated C witnesses do not change saved WP verdicts.',
                     'Evidence is retained in the [counterexample report](counterexamples/README.md), '
                     '[per-case replay results](counterexamples/summary.json), and [integration audit](counterexamples/integration_audit.json).']
    rows = []
    for lang in ('java', 'c'):
        subset = data['verified_original_subsets'][lang]
        counts = subset.get('validated_replay_outcomes', subset['verifier_outcomes'])
        rejected = counts.get('specification violation', 0)
        rows.append([lang.title(), subset['originals'], subset['eligible'], rejected, counts.get('precondition/RTE failure', 0),
                     counts.get('no validated failure', counts.get('unknown/timeout', 0)), pct(rejected, subset['eligible'])])
    sections += ['### Mutants whose originals were fully verified',
                 'This subset gives more direct evidence that a specification accepts its original while rejecting a mutant. '
                 'Rates use all eligible mutants in each subset; safety failures are separate from specification/postcondition violations. '
                 'Java diagnostics and C replay witnesses have different meanings, so their rates are descriptive rather than equivalent measurements.',
                 table(['Language', 'Verified originals', 'Eligible mutants', 'Specification/postcondition violations', 'Safety failures', 'Inconclusive', 'Violation rate'], rows),
                 '### Mutant outcomes by category',
                 table(['Category', 'Language', *outcome_headers],
                       [[e['category'], lang.title(), sum(e[lang]['mutant'].values()), *[e[lang]['mutant'].get(o, 0) for o in OUTCOMES]]
                        for e in data['categories'] for lang in ('java', 'c')])]
    if replay:
        sections += [table(['Category', 'Searched C mutants', 'Postcondition violations', 'Safety failures', 'No validated failure'],
                           [[e['category'], e['mutant_pairs'], e['c_replay'].get('specification violation', 0),
                             e['c_replay'].get('precondition/RTE failure', 0), e['c_replay'].get('no validated failure', 0)]
                            for e in data['categories']])]
    sections += ['Authoritative records are `cases/<program>/<original or mutant_ID>/<language>/record.json`. '
                 '[summary.json](summary.json) retains overall outcomes and original-outcome strata; '
                 '[results_summary.json](results_summary.json) and [statistics.json](statistics.json) contain the derived program-level report. '
                 'Generated source files, binaries, and solver traces remain excluded from Git.',
                 'Regenerate this report from saved results without invoking a verifier:',
                 '```bash\npython3 -m verification.specification_evaluation.summarize_experiment \\\n'
                 f"  --output verification/specification_evaluation/results/{data['run']}\n```"]
    return '\n\n'.join(sections) + '\n'


def final_results(data, summary):
    return {**data, 'original_verifier_outcomes': summary['originals'], 'mutant_verifier_outcomes': summary['mutants']}


def main():
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument('--output', type=Path, required=True)
    parser.add_argument('--selection', type=Path, default=REPO / 'FormalBench-data/FilteredData/selected_java/seed_726_per_category_10_653ade686f/selection_manifest.json')
    args = parser.parse_args()
    output = args.output.resolve()
    data, summary, config, records, paths = build(output, args.selection.resolve())
    for name, result in [('statistics.json', data), ('results_summary.json', final_results(data, summary))]:
        (output / name).write_text(json.dumps(result, indent=2, sort_keys=True) + '\n', encoding='utf-8')
    (output / 'README.md').write_text(render(data, summary, config, records, paths), encoding='utf-8')
    print(json.dumps({'run': data['run'], 'originals': summary['originals'], 'verified_original_subsets': data['verified_original_subsets']}, indent=2))


if __name__ == '__main__':
    main()
