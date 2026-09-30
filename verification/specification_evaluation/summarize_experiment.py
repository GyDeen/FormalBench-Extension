"""Generate a source-grounded README and statistics for a completed paired run."""
from __future__ import annotations

import argparse
from collections import Counter, defaultdict
from datetime import datetime, timezone
import hashlib
import json
import math
from pathlib import Path
import re
import statistics

from .annotations import scan
from .manifest import REPO, sha256

CATEGORIES = ('sequential', 'branch', 'single_path_loop', 'multi_path_loop', 'nested')
OUTCOMES = ('proved', 'specification violation', 'precondition/RTE failure', 'unknown/timeout', 'syntax/tool failure')


def read(path):
    return json.loads(path.read_text(encoding='utf-8'))


def percentile(values, fraction):
    values = sorted(values)
    position = (len(values) - 1) * fraction
    lower = math.floor(position)
    upper = math.ceil(position)
    return values[lower] + (values[upper] - values[lower]) * (position - lower)


def distribution(values):
    values = list(values)
    if not values:
        return {'n': 0, 'total': None, 'mean': None, 'median': None, 'sample_sd': None,
                'q1': None, 'q3': None, 'p95': None, 'min': None, 'max': None}
    return {'n': len(values), 'total': sum(values), 'mean': statistics.mean(values),
            'median': statistics.median(values),
            'sample_sd': statistics.stdev(values) if len(values) > 1 else None,
            'q1': percentile(values, .25), 'q3': percentile(values, .75),
            'p95': percentile(values, .95), 'min': min(values), 'max': max(values)}


def constructor(method):
    names = method.split('(')[0].split('.')
    return len(names) >= 2 and names[-1] == names[-2]


def fmt(value, digits=3):
    if value is None:
        return 'N/A'
    return f'{value:,.{digits}f}' if isinstance(value, float) else f'{value:,}'


def pct(numerator, denominator):
    return f'{100 * numerator / denominator:.2f}%' if denominator else 'N/A'


def table(headers, rows):
    return '\n'.join(['| ' + ' | '.join(headers) + ' |',
                      '| ' + ' | '.join('---' for _ in headers) + ' |'] +
                     ['| ' + ' | '.join(map(str, row)) + ' |' for row in rows])


def build(output, workload, selection):
    summary, config = read(output/'summary.json'), read(output/'run.json')
    manifest = read(selection)
    categories = {item['class_name']: item['category'] for item in manifest['programs']}
    assert len(categories) == 50 and Counter(categories.values()) == Counter({c: 10 for c in CATEGORIES})
    records = []
    paths = {}
    for path in sorted((output/'cases').glob('*/*/*/record.json')):
        record = read(path)
        assert record['program'] in categories and record['attempt_status'] == 'complete'
        assert record['outcome'] in OUTCOMES
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
    pairs = summary['pair_records']
    assert len(pairs) == 977
    for row in pairs:
        program, mutant_id = row['mutant'].split('/')
        for language in ('java', 'c'):
            assert row[language] == read(paths[(program, 'mutant', mutant_id, language)])['outcome']

    # Java workload counts are supplemental measurements, not replacements for
    # the final experiment's verdicts or wall-clock timings.
    java_workloads = {}
    missing_workloads = []
    for record in records:
        if record['language'] != 'java' or record['outcome'] != 'proved':
            continue
        key = (record['program'], record['role'], record['mutant_id'], 'java')
        relative = paths[key].relative_to(output)
        auxiliary = workload/relative
        if not auxiliary.exists():
            missing_workloads.append(record['program'])
            continue
        captured = read(auxiliary)
        for field in ('raw_source_sha256', 'frozen_spec_sha256', 'annotated_source_sha256', 'settings', 'verifier'):
            assert captured[field] == record[field], (record['program'], field)
        report = captured.get('java_workload', {})
        if not report.get('capture_complete'):
            missing_workloads.append(record['program'])
            continue
        methods = [{'method': m['method'], 'assertion_count': m['assertion_count'],
                    'constructor': constructor(m['method'])} for m in report['methods']]
        java_workloads[key] = {
            'program': record['program'], 'role': record['role'], 'mutant_id': record['mutant_id'],
            'generated_assertions': report['generated_assertion_count'],
            'generated_method_vcs': report['generated_method_vc_count'],
            'program_assertions': sum(m['assertion_count'] for m in methods if not m['constructor']),
            'solver_queries': report['solver_check_sat_count'], 'methods': methods,
            'source_record': str(auxiliary), 'source_record_sha256': sha256(auxiliary),
            'raw_source_sha256': captured['raw_source_sha256'],
            'frozen_spec_sha256': captured['frozen_spec_sha256'],
            'annotated_source_sha256': captured['annotated_source_sha256'],
            'capture_complete': True, 'auxiliary_outcome': captured['outcome'],
        }

    successful = {}
    for language in ('java', 'c'):
        cohort = [r for r in records if r['language'] == language and r['outcome'] == 'proved']
        by_role = {role: distribution(r['elapsed_seconds'] for r in cohort if r['role'] == role)
                   for role in ('original', 'mutant')}
        goals = (distribution(len(r['goals']) for r in cohort) if language == 'c'
                 else {name: distribution(j[name] for j in java_workloads.values()) for name in
                       ('generated_assertions', 'generated_method_vcs', 'program_assertions', 'solver_queries')})
        successful[language] = {'wall_seconds': distribution(r['elapsed_seconds'] for r in cohort),
                                'by_role': by_role, 'goals': goals,
                                'programs': [r['program'] for r in cohort]}

    category_results = []
    for category in CATEGORIES:
        programs = [name for name, value in categories.items() if value == category]
        cohort = [r for r in records if r['program'] in programs]
        entry = {'category': category, 'programs': programs, 'original_count': len(programs),
                 'mutant_pairs': sum(row['mutant'].split('/')[0] in programs for row in pairs)}
        for language in ('java', 'c'):
            language_records = [r for r in cohort if r['language'] == language]
            proved = [r for r in language_records if r['outcome'] == 'proved']
            verified_mutants = [r for r in language_records if r['role'] == 'mutant'
                                and originals[(r['program'], language)]['outcome'] == 'proved']
            goal_samples = ([len(r['goals']) for r in proved] if language == 'c' else
                            [java_workloads[(r['program'], r['role'], r['mutant_id'], 'java')]['generated_assertions']
                             for r in proved if (r['program'], r['role'], r['mutant_id'], 'java') in java_workloads])
            entry[language] = {
                role: dict(Counter(r['outcome'] for r in language_records if r['role'] == role))
                for role in ('original', 'mutant')}
            entry[language].update(successful_wall_seconds=distribution(r['elapsed_seconds'] for r in proved),
                                   successful_goal_counts=distribution(goal_samples),
                                   primary_eligible=len(verified_mutants),
                                   primary_rejected=sum(r['outcome'] == 'specification violation' for r in verified_mutants))
        category_results.append(entry)

    header = (REPO/'runtime/java_arrays/java_arrays.h').read_text()
    apis = sorted(set(re.findall(r'\b((?:jarray2?|jdouble_array2?|jbool_array)_[A-Za-z0-9_]+)\s*\(', header)))
    api_pattern = '|'.join(sorted(map(re.escape, apis), key=len, reverse=True))
    goal_api = re.compile(r'^(?P<api>' + api_pattern + r')_requires(?:_(?P<index>\d+))?$')
    # Property suffixes count repeated properties globally. The WP goal name
    # records both the source call ordinal and its actual requires clause.
    goal_clause = re.compile(r'_call_(?P<api>' + api_pattern +
                             r')(?:_\d+)?_requires(?:_(?P<index>\d+))?$')
    clauses = {}
    contract_hashes = {}
    for path in (REPO/'verification/java_arrays/contracts').glob('*.acsl.h'):
        relative = 'contracts/' + path.name
        contract_hashes[relative] = sha256(path)
        assert contract_hashes[relative] == config['jarray_support_sha256'][relative], relative
        text = path.read_text()
        for match in re.finditer(r'\b(' + api_pattern + r')\s*\(', text):
            end = text.rfind('*/', 0, match.start())
            start = text.rfind('/*@', 0, end)
            if start >= 0 and end >= 0 and not text[end + 2:match.start()].strip().startswith('/*'):
                requirements = re.findall(r'\brequires\s+(.+?);', text[start:end], re.S)
                clauses[match.group(1)] = [' '.join(value.split()) for value in requirements]

    jarray_cases = []
    api_totals = defaultdict(Counter)
    clause_totals = defaultdict(Counter)
    for record in records:
        if record['language'] != 'c':
            continue
        for relative, digest in contract_hashes.items():
            assert record['support_sha256'][relative] == digest, (record['program'], relative)
        source = Path(record['raw_source'])
        tokens = scan(source.read_text())[0]
        called = sorted({token.text for index, token in enumerate(tokens[:-1])
                         if token.text in apis and tokens[index + 1].text == '('})
        jgoals, non_jarray_unknown = [], []
        for goal in record['goals']:
            match = goal_api.fullmatch(goal.get('property') or '')
            if match:
                clause = goal_clause.search(goal['goal'])
                assert clause and clause['api'] == match['api'], goal
                api, index = match['api'], int(clause['index'] or 1)
                assert 1 <= index <= len(clauses.get(api, [])), (api, index, goal)
                api_totals[api]['total'] += 1
                api_totals[api][goal['state']] += 1
                clause_totals[(api, index)]['total'] += 1
                clause_totals[(api, index)][goal['state']] += 1
                jgoals.append({'api': api, 'clause': index, 'goal': goal['goal'], 'state': goal['state']})
            elif goal['state'] != 'proved':
                non_jarray_unknown.append(goal['goal'])
        unresolved = [goal for goal in jgoals if goal['state'] != 'proved']
        key = (record['program'], record['role'], record['mutant_id'], 'c')
        jarray_cases.append({'program': record['program'], 'category': categories[record['program']],
                             'role': record['role'], 'mutant_id': record['mutant_id'], 'outcome': record['outcome'],
                             'record': paths[key].relative_to(output).as_posix(), 'called_apis': called,
                             'reported_goal_count': len(record['goals']), 'goal_coverage_available': bool(record['goals']),
                             'verification_started': bool(record.get('verification_started')),
                             'timed_out': record.get('timed_out'),
                             'jarray_goal_count': len(jgoals), 'unresolved_jarray_goal_count': len(unresolved),
                             'other_unresolved_goal_count': len(non_jarray_unknown),
                             'jarray_blocker': bool(unresolved),
                             'only_jarray_blocker': bool(unresolved) and not non_jarray_unknown,
                             'unresolved_jarray_goals': unresolved})
    jarray_groups = {}
    for role in ('original', 'mutant'):
        cohort = [case for case in jarray_cases if case['role'] == role]
        jarray_groups[role] = {
            'cases': len(cohort), 'calling_jarray': sum(bool(c['called_apis']) for c in cohort),
            'with_recorded_goals': sum(c['goal_coverage_available'] for c in cohort),
            'calling_jarray_without_goals': sum(bool(c['called_apis']) and not c['goal_coverage_available'] for c in cohort),
            'no_goal_cases': sum(not c['goal_coverage_available'] for c in cohort),
            'no_goal_outcomes': dict(Counter(c['outcome'] for c in cohort if not c['goal_coverage_available'])),
            'calling_jarray_proved': sum(bool(c['called_apis']) and c['outcome'] == 'proved' for c in cohort),
            'no_jarray_proved': sum(not c['called_apis'] and c['outcome'] == 'proved' for c in cohort),
            'cases_with_unresolved_jarray': sum(c['jarray_blocker'] for c in cohort),
            'cases_with_only_unresolved_jarray': sum(c['only_jarray_blocker'] for c in cohort),
            'jarray_goals': sum(c['jarray_goal_count'] for c in cohort),
            'unresolved_jarray_goals': sum(c['unresolved_jarray_goal_count'] for c in cohort),
        }
    api_rows = []
    for api, counts in sorted(api_totals.items()):
        affected = [c for c in jarray_cases if any(g['api'] == api for g in c['unresolved_jarray_goals'])]
        api_rows.append({'api': api, **dict(counts), 'blocked_originals': sum(c['role'] == 'original' for c in affected),
                         'blocked_mutants': sum(c['role'] == 'mutant' for c in affected)})
    clause_rows = []
    for (api, index), counts in sorted(clause_totals.items()):
        if counts['unknown'] or counts['violated']:
            requirements = clauses.get(api, [])
            clause_rows.append({'api': api, 'clause': index, **dict(counts),
                                'requirement': requirements[index - 1] if len(requirements) >= index else 'Unmapped'})
    source_digest = hashlib.sha256('\n'.join(f'{path.relative_to(output).as_posix()}:{sha256(path)}'
                                           for path in sorted(paths.values())).encode()).hexdigest()
    result = {'generated_utc': datetime.now(timezone.utc).isoformat(), 'run': output.name,
              'summary_sha256': sha256(output/'summary.json'), 'record_inventory_sha256': source_digest,
              'category_source': selection.relative_to(REPO).as_posix(), 'category_source_sha256': sha256(selection),
              'category_mapping': categories, 'statistical_method': 'median from statistics.median; sample SD n-1; quartiles and p95 linear interpolation at (n-1)*p',
              'success_criterion': 'final run record outcome == proved; timing is elapsed_seconds from this run',
              'successful': successful, 'categories': category_results,
              'java_workload': {'source_run': workload.name, 'matched_cases': list(java_workloads.values()),
                                'missing_programs': missing_workloads},
              'jarray': {'groups': jarray_groups, 'apis': api_rows, 'unresolved_clauses': clause_rows,
                         'contract_sha256': contract_hashes, 'cases': jarray_cases}}
    return result, summary, config, records, paths


def render(data, summary, config, records, paths):
    sections = ['# Final paired specification verification experiment',
                f"Run: `{data['run']}`. This report covers 50 selected originals in each language and 977 retained Java/C mutant pairs (2,054 case records). Every case has a completed recorded outcome. Completion does not imply that all programs were proved.",
                '## Result summary',
                table(['Population', 'Language', 'Total', 'Proved', 'Specification violation', 'Precondition/RTE failure', 'Unknown/timeout', 'Syntax/tool failure'],
                      [[role.capitalize(), language.capitalize() if language == 'java' else 'C',
                        sum(summary[key][language].values()), *[summary[key][language].get(status, 0) for status in OUTCOMES]]
                       for role, key in (('originals', 'originals'), ('mutants', 'mutants')) for language in ('java', 'c')]),
                'Java proved 13/50 originals (26.00%); C proved 6/50 (12.00%). No mutant was fully proved in either language. Java reports 146 specification violations (146/977 = 14.94%), including 121 among the 192 mutants whose Java original proved (63.02%). C reports no confirmed specification violations, including 0/80 mutants whose C original proved. Unknown and tool failures remain in the eligible denominator; zero confirmed C rejections does not establish successful verification of those mutants.',
                table(['Paired mutant outcome', 'Count'], [[name, count] for name, count in summary['pairs'].items()]),
                f"There are {summary['paired_comparison']['decisive_pairs']} decisive pairs; agreement on decisive pairs is N/A. The 55 C sort reruns replaced old annotation/tool failures with `unknown/timeout`, reducing C tool failures from 110 to 55. Original Java results were preserved. See [summary.json](summary.json), [run.json](run.json), and [sort_c_integration.json](sort_c_integration.json).",
                '## Configuration and interpretation',
                'The sample uses seed 726 with ten originals in each of five dataset categories. Only retained, previously screened mutant pairs are evaluated. Specifications are frozen independently per language and transferred to unchanged executable sources.',
                f"The Java verifier reports `{config['verifiers']['java']['version_output']}` and uses the `z3-4.3.X` driver with bundled solver `{config['verifiers']['java']['compatibility']['solver']['version']}`. The C verifier reports `{config['verifiers']['c']['version_output']}` and uses {config['settings']['c_provers']}, `{config['settings']['memory_model']}`, and `{config['settings']['machdep']}`. Budgets are {config['settings']['goal_timeout']} seconds per solver goal and {config['settings']['timeout']} seconds per process, with {config['settings']['wp_memlimit']} MB WP memory and {config['settings']['wp_par']} parallel WP jobs per C worker. Final scheduling used two C workers and one Java worker; the integrated sort rerun used two C workers. Timings reflect recorded invocations under these schedules, not an isolated speed benchmark.",
                '`proved` means all applicable reported checks completed successfully. A specification violation is distinct from a precondition/RTE failure. Syntax/transfer/tool failures are evaluation failures, not detected behavioral faults. `unknown/timeout` establishes neither acceptance nor rejection. Primary specification rejection rates require a proved original; other original-outcome strata remain available in [summary.json](summary.json).',
                '## Time for successfully verified cases',
                'The success cohort is strictly `record.json: outcome == proved`. Time is the recorded verifier-process wall time (`elapsed_seconds`), including startup and Java constructor checking. It excludes annotation transfer and preparation. Only originals qualify: Java n=13, C n=6; successful-mutant timing statistics are N/A (n=0).',
                table(['Language', 'n', 'Total s', 'Mean s', 'Median s', 'Sample SD s', 'Q1 s', 'Q3 s', 'P95 s', 'Min s', 'Max s'],
                      [[label, *[fmt(data['successful'][lang]['wall_seconds'][key], 4) for key in
                                 ('n', 'total', 'mean', 'median', 'sample_sd', 'q1', 'q3', 'p95', 'min', 'max')]]
                       for lang, label in (('java', 'Java'), ('c', 'C'))]),
                'Median uses the average of the middle two values for even n. SD is the sample standard deviation (n-1); Q1, Q3 and P95 use linear interpolation at (n-1)p. N/A denotes an empty sample or unavailable measurement, never a measured zero.',
                'Java and C have different successful-program cohorts. These descriptive times do not measure a paired language speed difference.',
                '## Goal counts for the same successful cohort',
                'C counts are classified WP JSON goal entries, including specification, termination, safety and callee-precondition obligations. Java warning diagnostics are **not** a goal count: an empty Java `goals` array means no recorded warnings. Java generated assertions, method VCs and solver queries are separate units and must not be equated with C WP entries.',
                f"Java counts below come from {len(data['java_workload']['matched_cases'])} complete captures in `{data['java_workload']['source_run']}`, matched to the successful final-run cases by raw-source, frozen-specification and annotated-source hashes, settings and verifier identity. Final-run verdicts and timing measurements remain authoritative; instrumented replay timing and outcomes are not substituted. Missing capture programs: {', '.join(data['java_workload']['missing_programs']) or 'none'}. Generated method VCs and total assertions include constructors; the program-assertion row removes constructors."]
    goal_rows = []
    for name, label in (('generated_assertions', 'Java: all generated assertions'), ('program_assertions', 'Java: program assertions, excluding constructors'),
                        ('generated_method_vcs', 'Java: generated method VCs'), ('solver_queries', 'Java: solver check-sat queries')):
        values = data['successful']['java']['goals'][name]
        goal_rows.append([label, *[fmt(values[k]) for k in ('n', 'total', 'mean', 'median', 'sample_sd', 'q1', 'q3', 'p95', 'min', 'max')]])
    values = data['successful']['c']['goals']
    goal_rows.append(['C: WP goal entries', *[fmt(values[k]) for k in ('n', 'total', 'mean', 'median', 'sample_sd', 'q1', 'q3', 'p95', 'min', 'max')]])
    sections += [table(['Metric per successful case', 'n', 'Total', 'Mean', 'Median', 'Sample SD', 'Q1', 'Q3', 'P95', 'Min', 'Max'], goal_rows),
                 '### Successful original details']
    rows = []
    captured = {m['raw_source_sha256']: m for m in data['java_workload']['matched_cases']}
    for r in records:
        if r['outcome'] != 'proved':
            continue
        j = captured.get(r['raw_source_sha256']) if r['language'] == 'java' else None
        rows.append([r['program'], data['category_mapping'][r['program']], r['language'], fmt(r['elapsed_seconds']),
                     len(r['goals']) if r['language'] == 'c' else 'N/A',
                     j['generated_assertions'] if j else 'N/A', j['generated_method_vcs'] if j else 'N/A'])
    sections += [table(['Program', 'Category', 'Language', 'Wall s', 'C WP goals', 'Java assertions', 'Java method VCs'], rows),
                 '## Category results',
                 'Category labels come from the saved selection manifest, not from reclassifying translated code. Each category contains ten originals in each language; mutant totals vary after eligibility screening.',
                 table(['Category', 'Originals per language', 'Retained mutant pairs'],
                       [[e['category'], e['original_count'], e['mutant_pairs']] for e in data['categories']]),
                 '### Original outcomes by category']
    for role in ('original', 'mutant'):
        if role == 'mutant':
            sections.append('### Mutant outcomes by category')
        rows = [[e['category'], language, sum(e[language][role].values()), *[e[language][role].get(outcome, 0) for outcome in OUTCOMES]]
                for e in data['categories'] for language in ('java', 'c')]
        sections.append(table(['Category', 'Language', 'Total', 'Proved', 'Specification violation', 'Precondition/RTE', 'Unknown/timeout', 'Syntax/tool'], rows))
    sections += ['### Successful-case time and goal statistics by category',
                 'These rows use the same fully proved cohort as above; no successfully verified mutants contribute. Java goal columns count generated assertions (including constructors); C goal columns count WP entries. These units differ.',
                 'Time statistics (seconds):',
                 table(['Category', 'Language', 'n', 'Total', 'Mean', 'Median', 'Sample SD', 'Q1', 'Q3', 'P95', 'Min', 'Max'],
                       [[e['category'], lang,
                         *[fmt(e[lang]['successful_wall_seconds'][k], 4) for k in
                           ('n', 'total', 'mean', 'median', 'sample_sd', 'q1', 'q3', 'p95', 'min', 'max')]]
                        for e in data['categories'] for lang in ('java', 'c')]),
                 'Goal-count statistics (Java assertions / C WP entries):',
                 table(['Category', 'Language', 'n', 'Total', 'Mean', 'Median', 'Sample SD', 'Q1', 'Q3', 'P95', 'Min', 'Max'],
                       [[e['category'], lang,
                         *[fmt(e[lang]['successful_goal_counts'][k]) for k in
                           ('n', 'total', 'mean', 'median', 'sample_sd', 'q1', 'q3', 'p95', 'min', 'max')]]
                        for e in data['categories'] for lang in ('java', 'c')]),
                 '### Primary specification rejection by category',
                 'The eligible count in this table contains only mutants whose original proved in that language. Rates use all such eligible mutants, including unknown and safety/tool outcomes.',
                 table(['Category', 'Language', 'Eligible under proved original', 'Specification rejections', 'Rate'],
                       [[e['category'], lang, e[lang]['primary_eligible'], e[lang]['primary_rejected'],
                         pct(e[lang]['primary_rejected'], e[lang]['primary_eligible'])]
                        for e in data['categories'] for lang in ('java', 'c')]),
                 '## Does JArray block C verification?',
                 'JArray is the trusted interface assumed by translated C callers. Its implementation is not one of the study programs. A direct JArray blocker here means at least one recorded WP **callee-precondition** goal for a declared JArray API is not proved. "JArray only" means all remaining recorded unproved goals in that case are JArray preconditions. This is a statement about reported obligations under the current budgets, not a counterexample to the JArray implementation or a prediction that extra time will solve them.',
                 table(['C population', 'Cases', 'Call JArray', 'Goal coverage available', 'Call JArray but no goals', 'JArray blocker cases', 'JArray-only blocker cases', 'JArray precondition goals', 'Unresolved JArray goals'],
                       [[role, *[data['jarray']['groups'][role][k] for k in ('cases', 'calling_jarray', 'with_recorded_goals',
                                                                          'calling_jarray_without_goals', 'cases_with_unresolved_jarray',
                                                                          'cases_with_only_unresolved_jarray', 'jarray_goals', 'unresolved_jarray_goals')]]
                        for role in ('original', 'mutant')])]
    groups = data['jarray']['groups']
    blocked = groups['original']['cases_with_unresolved_jarray'] + groups['mutant']['cases_with_unresolved_jarray']
    only = groups['original']['cases_with_only_unresolved_jarray'] + groups['mutant']['cases_with_only_unresolved_jarray']
    sections += [f"**Observed conclusion: JArray call preconditions are a proof blocker in {blocked:,} C cases ({groups['original']['cases_with_unresolved_jarray']} originals and {groups['mutant']['cases_with_unresolved_jarray']} mutants).** They are the only remaining recorded blocker in {only:,} {'case' if only == 1 else 'cases'}. These case counts overlap other proof difficulties; they are not additional outcome categories. Goal-less cases are excluded from blocker attribution, including transfer/tool failures and process timeouts without classified goal coverage. All unresolved JArray goals here are unknown; none is classified as violated.",
                 f"Among originals, {groups['original']['calling_jarray_proved']}/{groups['original']['calling_jarray']} callers of JArray proved; {groups['original']['no_jarray_proved']}/{groups['original']['cases'] - groups['original']['calling_jarray']} originals without JArray calls proved. This is an association in the selected program sample; program complexity, invariants and solver limits also vary.",
                 f"There are {groups['mutant']['no_goal_cases']} mutant cases without classified goal coverage ({groups['mutant']['no_goal_outcomes'].get('syntax/tool failure', 0)} syntax/tool failures and {groups['mutant']['no_goal_outcomes'].get('unknown/timeout', 0)} unknown/timeouts). Of these, {groups['mutant']['calling_jarray_without_goals']} call JArray; their blocker status is unavailable. Requirement text below comes from trusted headers whose hashes match the run configuration and every C case record.",
                 '### JArray preconditions by function',
                 table(['API', 'Total goals', 'Proved', 'Unknown', 'Violated', 'Blocked originals', 'Blocked mutants'],
                       [[e['api'], e.get('total', 0), e.get('proved', 0), e.get('unknown', 0), e.get('violated', 0), e['blocked_originals'], e['blocked_mutants']]
                        for e in data['jarray']['apis']]),
                 'Blocked-case counts overlap between API functions. Goal counts count distinct reported obligations, not unique source calls. They include allocation, nullness, array validity, bounds and matrix row/shape requirements where present.',
                 '### Most frequent unresolved JArray clauses',
                 table(['API', 'Requires clause', 'Unresolved goals', 'Trusted requirement'],
                       [[e['api'], e['clause'], e.get('unknown', 0) + e.get('violated', 0), '`' + e['requirement'].replace('|', '\\|') + '`']
                        for e in sorted(data['jarray']['unresolved_clauses'], key=lambda e: e.get('unknown', 0) + e.get('violated', 0), reverse=True)[:12]]),
                 '### JArray blockers by category',
                 table(['Category', 'Role', 'Cases calling JArray', 'Cases blocked by JArray', 'JArray-only cases', 'Unresolved JArray goals'],
                       [[category, role, sum(bool(c['called_apis']) for c in cohort), sum(c['jarray_blocker'] for c in cohort),
                         sum(c['only_jarray_blocker'] for c in cohort), sum(c['unresolved_jarray_goal_count'] for c in cohort)]
                        for category in CATEGORIES for role in ('original', 'mutant')
                        for cohort in [[c for c in data['jarray']['cases'] if c['category'] == category and c['role'] == role]]]),
                 '### Original cases with a recorded JArray blocker',
                 table(['Program (case record)', 'Category', 'Unresolved JArray goals', 'Other unresolved goals'],
                       [[f"[{c['program']}]({c['record']})", c['category'], c['unresolved_jarray_goal_count'], c['other_unresolved_goal_count']]
                        for c in data['jarray']['cases'] if c['role'] == 'original' and c['jarray_blocker']]),
                 'Cases where JArray preconditions are the sole recorded unresolved obligations: ' +
                 (', '.join(f"[{c['program']}/{c['mutant_id']}]({c['record']})" for c in data['jarray']['cases'] if c['only_jarray_blocker']) or 'none') + '.',
                 '## Evidence and regeneration',
                 '[statistics.json](statistics.json) contains full-precision statistics, case-level JArray blocker evidence, Java workload-count snapshots and source hashes. Category membership comes from the [selection manifest](../../../../FormalBench-data/FilteredData/selected_java/seed_726_per_category_10_653ade686f/selection_manifest.json). Main case records and WP reports are the outcome evidence. Historic records under `history/` and other diagnostic runs are excluded.',
                 'The Java workload run supplies only matching, complete count captures for the successful final cases. Its different aggregate outcomes are not mixed into this experiment. The snapshots in `statistics.json` preserve the counts used in this README; raw solver traces and test/configuration changes are not required to read the report.',
                 'Regenerate from the repository root in the local Linux environment (the matching workload run must be available):',
                 '```bash\npython3 -m verification.specification_evaluation.summarize_experiment \\\n  --output verification/specification_evaluation/results/' + data['run'] + ' \\\n  --java-workload verification/specification_evaluation/results/' + data['java_workload']['source_run'] + '\n```']
    return '\n\n'.join(sections) + '\n'


def main():
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument('--output', type=Path, required=True)
    parser.add_argument('--java-workload', type=Path, required=True)
    parser.add_argument('--selection', type=Path, default=REPO/'FormalBench-data/FilteredData/selected_java/seed_726_per_category_10_653ade686f/selection_manifest.json')
    args = parser.parse_args()
    output, workload, selection = args.output.resolve(), args.java_workload.resolve(), args.selection.resolve()
    data, summary, config, records, paths = build(output, workload, selection)
    (output/'statistics.json').write_text(json.dumps(data, indent=2, sort_keys=True) + '\n', encoding='utf-8')
    (output/'README.md').write_text(render(data, summary, config, records, paths), encoding='utf-8')
    print(json.dumps({'successful_timing': {lang: value['wall_seconds'] for lang, value in data['successful'].items()},
                      'java_workload_matches': len(data['java_workload']['matched_cases']),
                      'jarray': data['jarray']['groups'], 'readme': str(output/'README.md')}, indent=2))


if __name__ == '__main__':
    main()
