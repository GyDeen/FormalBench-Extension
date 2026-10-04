"""Map saved C replay failures to frozen clauses and paired Java evidence.

Offline only: no compiler, solver, verifier, or native/Java execution. Predicate
checks use the saved observation and the hash-pinned replay oracle expectations.
WP verdicts and OpenJML diagnostic states are copied, never inferred or changed.
"""
import argparse
from collections import Counter
import csv
import hashlib
import json
from pathlib import Path
import re

ROOT = Path(__file__).resolve().parent
DEFAULT = ROOT / 'results/originals_stop_tool_error_20260927T161243Z_goal10_case300'
DEFAULT_ANALYSIS = ROOT / 'goal_assertion_results/short_budget_goal10_case300'


def analysis_directory(study):
    return DEFAULT_ANALYSIS if study.name == DEFAULT.name else ROOT / 'goal_assertion_results' / study.name


def read(path):
    return json.loads(path.read_text(encoding='utf-8-sig'))


def digest(path):
    return hashlib.sha256(path.read_bytes()).hexdigest()


def write_json(path, data):
    path.write_text(json.dumps(data, indent=2, ensure_ascii=False) + '\n', encoding='utf-8')


def clauses(text, keyword='ensures'):
    # Clause boundaries are line-leading keywords, not quantifier semicolons.
    text = re.sub(r'(?m)^\s*@\s?', '', text)
    pattern = r'(?m)^\s*(requires|ensures|assigns|assignable|allocates|frees|terminates|exits|signals_only|signals|diverges|measured_by)\b'
    starts = list(re.finditer(pattern, text))
    answer = []
    for i, match in enumerate(starts):
        if match.group(1) != keyword:
            continue
        end = starts[i + 1].start() if i + 1 < len(starts) else len(text)
        value = text[match.end():end]
        # Behavior separators and comment endings are not part of the clause.
        value = re.split(r'\n\s*(?:also|public\s+(?:normal|exceptional)_behavior)|\*/|@\*/|//@', value)[0]
        answer.append(value.strip().rstrip(';').strip())
    return answer


def java_entry_clauses(spec, entry):
    texts = []
    for annotation in spec['annotations']:
        if annotation.get('target') == 'function' and annotation.get('function') == entry:
            # Some function annotations embed model-method specifications.
            # Only the contract after the last model declaration belongs to entry.
            texts.append(re.split(r'\bmodel\b[^;]*;', annotation['text'])[-1])
    return clauses('\n'.join(texts))


# Reviewed semantic roles for the paired frozen contracts. These are candidate
# correspondences, not claims of logical equivalence or shared counterexamples.
SPECIAL_JAVA = {
    'CombSort': {1: 1, 2: 2, 3: 3},
    'CountingSort': {1: 1, 2: 1, 3: 1, 5: 1, 6: 2, 7: 3},
    'RadixSort': {2: 2, 3: 3, 4: 1},
    'FindPoints': {1: 1, 2: 1, 4: 2, 5: 3, 6: 4},
    'ParabolaVertex': {1: 1, 2: 1, 3: 2, 4: 3},
    'SumList': {1: 1, 2: 2, 3: 1, 5: 3},
    'MultiplyElements': {1: 1, 2: 2, 3: 1, 5: 3},
    'PairWise': {1: 1, 2: 2, 3: 1, 5: 3, 6: 4},
    'MoveFirst': {1: 1, 2: 2, 3: 3, 4: 3, 5: 4, 6: 5},
    'CountOddSquares': {1: 1, 2: 2, 3: 3},
    'CountUnsetBits': {1: 1, 2: 2},
    'FindPeak': {i: i for i in range(1, 6)},
    'LeftInsertion': {i: i for i in range(1, 4)},
    'MaximumSegments': {1: 1, 2: 2},
    'MaxVolume': {i: i for i in range(1, 5)},
    'NextPowerOf2': {i: i for i in range(1, 5)},
    'SqrtRoot': {i: i for i in range(1, 5)},
}


def failed_postconditions(program, contract, witness):
    """Return only clauses falsified by available saved concrete observations.

    An expected-value mismatch is translated to the pinned oracle's corresponding
    functional clause. Structural properties are checked separately. Missing
    pointer/allocation history never becomes a freshness violation.
    """
    x = witness['inputs']; o = witness['observation']; r = o['result']
    e = witness['expected']; labels = witness.get('failed_clauses', [])
    failures = {}

    def fail(index, role, reason, condition=True):
        if condition:
            assert 1 <= index <= len(clauses(contract))
            failures[index] = {'semantic_role': role, 'evidence': reason}

    mismatch = 'frozen_return_value_or_array_contents' in labels
    if program not in SPECIAL_JAVA:
        assert len(clauses(contract)) == 1 and '\\result ==' in clauses(contract)[0], program
        fail(1, 'functional_return_value', 'Saved hash-pinned functional oracle mismatch', mismatch)
    elif program == 'CombSort':
        a = o['state']['nums']
        fail(1, 'result_identity', 'Saved result does not alias input', not o['aliases'].get('nums'))
        fail(2, 'functional_array_contents', 'Saved post-state differs from pinned fb_value expectation', a != e)
        fail(3, 'maximum_last_element', 'Saved post-state has an element greater than the last element', bool(a) and any(v > a[-1] for v in a))
    elif program in {'CountingSort', 'RadixSort'}:
        if r is None:
            fail(1, 'result_nonnull', 'Saved null return')
        elif isinstance(r, list):
            a = next(iter(x.values()))
            if program == 'CountingSort':
                fail(2, 'result_length', 'Saved length differs from input length', len(r) != len(a))
            fail(6 if program == 'CountingSort' else 2, 'sorted_contents', 'Saved adjacent inversion', any(u > v for u, v in zip(r, r[1:])))
            fail(7 if program == 'CountingSort' else 3, 'multiset_preservation', 'Saved output/input occurrence counts differ', Counter(r) != Counter(a))
    elif program in {'FindPoints', 'ParabolaVertex'}:
        fail(1, 'result_shape', 'Saved result is null or has length other than two', r is None or len(r) != 2)
        if isinstance(r, list) and len(r) >= 2:
            if program == 'FindPoints':
                i = 4 if x['l1'] < x['l2'] and x['r1'] < x['r2'] else 5 if x['l1'] > x['l2'] and x['r1'] > x['r2'] else 6
                fail(i, 'functional_array_contents', 'Active branch differs from saved pinned coordinate expectation', r[:2] != e[:2])
            else:
                fail(3, 'vertex_x', 'Saved x coordinate differs from pinned expectation', r[0] != e[0])
                fail(4, 'vertex_y', 'Saved y coordinate differs from pinned expectation', r[1] != e[1])
    elif program in {'SumList', 'MultiplyElements', 'PairWise'}:
        fail(1, 'result_nonnull', 'Saved null return', r is None)
        if isinstance(r, list):
            fail(2, 'result_length', 'Saved output length differs from pinned expected length', len(r) != len(e))
            # Only observed indices where the input-side expression is defined.
            bad = any(u != v for u, v in zip(r, e))
            if program == 'PairWise':
                bad = any(not isinstance(row, list) or len(row) != 2 for row in r) or bad
            fail(5, 'functional_array_contents', 'Saved defined output element/row differs from pinned expectation', bad)
            if program == 'PairWise':
                fail(6, 'row_separation', 'Saved rows_separate is false', len(r) > 1 and not o.get('rows_separate', True))
    elif program == 'MoveFirst':
        a = x['testArray']
        if a is None:
            fail(1, 'null_result', 'Null input returned nonnull', r is not None)
        elif not a:
            fail(2, 'empty_result_identity', 'Saved result does not alias empty input', not o['aliases'].get('testArray'))
        else:
            fail(3, 'result_shape', 'Saved output is null or has wrong length', r is None or len(r) != len(a))
            if isinstance(r, list) and r:
                fail(5, 'rotation_head', 'Saved head differs from old last input', r[0] != a[-1])
                fail(6, 'rotation_tail', 'Saved defined tail element differs from old input', any(r[k] != a[k-1] for k in range(1, min(len(r), len(a)))))
    elif program == 'CountOddSquares':
        fail(1, 'input_limit', 'Saved input m equals INT32_MAX', x['m'] == 2**31-1)
        fail(2, 'functional_return_value', 'Saved pinned oracle mismatch', mismatch)
        fail(3, 'result_bounds', 'Saved result outside [0,46341]', not 0 <= r <= 46341)
        fail(4, 'errno_EDOM', 'Saved active errno EDOM clause fails', 'errno_EDOM' in labels)
        fail(5, 'errno_frame', 'Saved active errno frame clause fails', 'errno_frame' in labels)
    elif program == 'CountUnsetBits':
        fail(1, 'input_limit', 'Saved input equals INT32_MAX', x['n'] == 2**31-1)
        fail(2, 'functional_return_value', 'Saved pinned oracle mismatch', mismatch)
    elif program == 'FindPeak':
        n = x['n']; a = o['state']['arr']
        fail(1 if n <= 1 else 2, 'functional_return_value', 'Active result clause differs from pinned expectation', mismatch)
        if n > 1:
            fail(3, 'result_bounds', 'Saved result outside [0,n)', not 0 <= r < n)
            if 0 <= r < n:
                fail(4, 'left_peak_neighbor', 'Saved peak is less than left neighbor', r > 0 and a[r] < a[r-1])
                fail(5, 'right_peak_neighbor', 'Saved peak is less than right neighbor', r < n-1 and a[r] < a[r+1])
    elif program == 'LeftInsertion':
        a = o['state']['a']
        fail(1, 'functional_return_value', 'Saved pinned search expectation mismatch', mismatch)
        fail(2, 'result_bounds', 'Saved result outside [0,length]', not 0 <= r <= len(a))
        if a == sorted(a) and 0 <= r <= len(a):
            fail(3, 'sorted_partition', 'Saved left/right insertion partition fails', any(v > x['x'] for v in a[:r]) or any(v < x['x'] for v in a[r:]))
    elif program == 'MaximumSegments':
        fail(1, 'functional_return_value', 'Saved pinned segments expectation mismatch', mismatch)
        fail(2, 'result_bounds', 'Saved result outside [-1,n]', not -1 <= r <= x['n'])
    elif program == 'MaxVolume':
        s = x['s']
        fail(1, 'input_limit', 'Saved input equals INT32_MAX', s == 2**31-1)
        fail(2, 'result_bounds', 'Saved negative volume', r < 0)
        if s <= 128:
            values = [((l*b*(s-l-b)+2**31) % 2**32)-2**31 for l in range(1, s+1) for b in range(1, s-l+2)]
            fail(3, 'maximal_volume', 'A concrete admissible volume is greater than saved result', any(v > r for v in values))
            fail(4, 'attainable_volume', 'Nonzero saved result is absent from all finite admissible volumes', r != 0 and r not in values)
    elif program == 'NextPowerOf2':
        fail(1, 'input_limit', 'Saved input exceeds 2^30', x['n'] > 2**30)
        fail(2, 'result_bounds', 'Saved result violates range or lower bound', not (1 <= r <= 2**30 and r >= x['n']))
        # Negative outputs already falsify clause 2. Avoid assuming a bitwise
        # width for negative logic integers in an independently evaluated clause.
        fail(3, 'power_of_two', 'Saved nonnegative bitwise power-of-two predicate fails', r >= 0 and (r & (r-1)) != 0)
        fail(4, 'minimal_power_of_two', 'Saved result violates minimality', r != 1 and int(r / 2) >= x['n'])
    elif program == 'SqrtRoot':
        n = x['num']
        fail(1, 'input_limit', 'Saved input equals INT32_MAX', n == 2**31-1)
        fail(2 if n < 0 else 3, 'functional_return_value', 'Active root result differs from pinned expectation', mismatch and n < 2**31-1)
        fail(4, 'root_bounds', 'Saved square-root bounds fail', 0 <= n <= 46340 and not (r >= 0 and r*r <= n < (r+1)**2))
    if 'fresh_result' in labels:
        index = {'CountingSort': 3, 'SumList': 3, 'MultiplyElements': 3, 'PairWise': 3, 'FindPoints': 2, 'ParabolaVertex': 2, 'MoveFirst': 4}[program]
        fail(index, 'fresh_result_object', 'Saved result aliases an existing input object')
    if program == 'RadixSort':
        fail(4, 'result_identity', 'Saved result does not alias nums', not o['aliases'].get('nums'))
    return failures


def safety_kind(witness, audit_execution=None):
    text = witness.get('stderr', '') + '\n' + (audit_execution or {}).get('stderr', '')
    for marker, name in [('INDEX_OUT_OF_BOUNDS', 'array_bounds'), ('NEGATIVE_ARRAY_SIZE', 'negative_allocation_size'), ('NULL_REFERENCE_ERROR', 'null_reference')]:
        if marker in text:
            return name
    if 'AddressSanitizer: stack-overflow' in text:
        return 'stack_overflow'
    return 'unlocated_abnormal_exit'


def safety_candidates(goals, kind):
    answer = []
    array = r'j(?:array2?|(?:double|bool)_array2?)'
    for g in goals:
        name = g['goal']
        if kind == 'array_bounds' and re.search(r'_call_' + array + r'_(?:get|set)(?:_\d+)?_requires_3$', name):
            answer.append(g)
        elif kind == 'null_reference' and re.search(r'_call_' + array + r'_(?:get|set|length)(?:_\d+)?_requires$', name):
            answer.append(g)
        elif kind == 'negative_allocation_size' and (re.search(r'_call_' + array + r'_new(?:_rows)?(?:_\d+)?_requires$', name) or
                re.search(r'_call_j(?:array2|double_array2)_new(?:_\d+)?_requires_2$', name)):
            answer.append(g)
        elif kind == 'stack_overflow' and g.get('property') in {g.get('function', '') + '_variant', g.get('function', '') + '_terminates'}:
            answer.append(g)
    return answer


SAFETY_JAVA = {
    'array_bounds': ['PossiblyNegativeIndex', 'PossiblyTooLargeIndex'],
    'negative_allocation_size': ['PossiblyNegativeSize'],
    'null_reference': ['PossiblyNullDeReference'],
    'unlocated_abnormal_exit': [],
    'stack_overflow': ['TerminationDecreases', 'TerminationNonNegative'],
}


def csv_file(path, rows, columns):
    with path.open('w', encoding='utf-8', newline='') as stream:
        writer = csv.DictWriter(stream, columns)
        writer.writeheader()
        for row in rows:
            writer.writerow({k: json.dumps(row.get(k), ensure_ascii=False) if isinstance(row.get(k), (dict, list)) else row.get(k) for k in columns})


def build(study, analysis=None):
    analysis = analysis or analysis_directory(study)
    contracts_path = ROOT / 'c_replay_contracts.json'
    contracts = read(contracts_path)
    source_hashes = {}
    helper_hashes = {f'contracts/{name}': digest(ROOT.parent / 'java_arrays/contracts' / name)
                     for name in ['jintarray.acsl.h', 'jintarray2.acsl.h', 'jdoublearray.acsl.h', 'jdoublearray2.acsl.h', 'jboolarray.acsl.h']}

    def saved(path, expected=None):
        sha = digest(path)
        if expected:
            assert sha == expected, path
        source_hashes[path.relative_to(ROOT.parent.parent).as_posix()] = sha
        return read(path)

    replay_summary = saved(study / 'counterexamples/summary.json')
    snapshot = saved(analysis / 'goal_kind_status/java_mutant_workload_snapshot.json')
    workloads = {(r['program'], str(r['mutant_id'])): r for r in snapshot['cases']}
    with (analysis / 'goal_kind_status/java_mutant_diagnostics.csv').open(encoding='utf-8-sig', newline='') as stream:
        diagnostics = {}
        for row in csv.DictReader(stream):
            diagnostics.setdefault((row['program'], str(row['mutant_id'])), []).append(row)
    diagnostics_path = analysis / 'goal_kind_status/java_mutant_diagnostics.csv'
    source_hashes[diagnostics_path.relative_to(ROOT.parent.parent).as_posix()] = digest(diagnostics_path)
    catalog = {}
    for p, c in contracts.items():
        c_spec = saved(study / f'frozen_specs/c/{p}.json', c['frozen_spec_sha256'])
        # Ensure the extracted entry contract is present in the frozen annotations.
        frozen_text = '\n'.join(a['text'] for a in c_spec['annotations'] if a.get('function') == c['entry'] and a.get('target') == 'function')
        assert all(re.sub(r'\s+', '', t) in re.sub(r'\s+', '', frozen_text) for t in clauses(c['contract'])), p
        j_spec = saved(study / f'frozen_specs/java/{p}.json')
        cc = clauses(c['contract']); jc = java_entry_clauses(j_spec, c['entry'])
        assert jc, p
        mapping = SPECIAL_JAVA.get(p, {1: 1} if len(cc) == len(jc) == 1 else {})
        assert all(1 <= i <= len(cc) and 1 <= j <= len(jc) for i, j in mapping.items()), p
        catalog[p] = {'entry': c['entry'], 'c_postconditions': cc, 'java_postconditions': jc,
                      'java_semantic_candidates': {str(i): j for i, j in mapping.items()},
                      'c_spec_sha256': c['frozen_spec_sha256'], 'java_spec_sha256': digest(study / f'frozen_specs/java/{p}.json')}
    cases = []; edges = []
    for row in sorted(replay_summary['records'], key=lambda r: (r['program'], int(r['mutant_id'] or 0))):
        if row['role'] != 'mutant':
            continue
        p = row['program']; mid = str(row['mutant_id']); key = (p, mid); c = contracts[p]; cat = catalog[p]
        cp = study / row['current_verification_record']; cr = saved(cp, row['current_verification_record_sha256'])
        rp = study / row['record']; rr = saved(rp, row['record_sha256']); replay = saved(rp.with_name('replay.json'))
        jp = study / f'cases/{p}/mutant_{mid}/java/record.json'; jr = saved(jp, workloads[key]['canonical_record_sha256'])
        assert cr['frozen_spec_sha256'] == cat['c_spec_sha256'] == rr['frozen_spec_sha256']
        for name, sha in helper_hashes.items():
            if name in cr.get('support_sha256', {}):
                assert cr['support_sha256'][name] == sha, (p, mid, name)
        assert jr['frozen_spec_sha256'] == cat['java_spec_sha256']
        assert (cr['program'], str(cr['mutant_id'])) == key == (jr['program'], str(jr['mutant_id']))
        diags = diagnostics.get(key, [])
        # Verify diagnostic rows against authoritative record messages/states.
        assert [(d['message'], d['state']) for d in diags] == [(d['message'], d['state']) for d in jr['goals']]
        w = replay.get('witness'); case_edges = []
        if row['validated']:
            assert w and w['validated'] and w['preconditions_satisfied']
            assert replay['independent_audit']['passed'] and replay['independent_audit']['baseline_passed']
            assert w['inputs'] == row['inputs'] and row['original_passed_on_witness']
            if w['status'] == 'returned':
                for index, evidence in failed_postconditions(p, c['contract'], w).items():
                    prop = c['entry'] + '_ensures' + (f'_{index}' if index > 1 else '')
                    goals = [g for g in cr['goals'] if g['function'] == c['entry'] and g['property'] == prop]
                    jindex = cat['java_semantic_candidates'].get(str(index))
                    case_edges.append({'kind': 'postcondition', 'clause_index': index, 'clause': cat['c_postconditions'][index-1],
                        'property': prop, 'mapping_status': 'falsified_clause_with_recorded_goal' if goals else 'falsified_clause_goal_not_recorded',
                        'evidence_basis': 'offline_saved_observation', **evidence, 'c_goals': goals,
                        'java_alignment': 'semantic_candidate' if jindex else 'no_direct_clause_counterpart',
                        'java_clause_index': jindex, 'java_clause': cat['java_postconditions'][jindex-1] if jindex else None,
                        'java_relevant_kinds': ['Postcondition'] if jindex else []})
                for label in w.get('failed_clauses', []):
                    if label.startswith('assigns_input_frame:'):
                        case_edges.append({'kind': 'frame', 'semantic_role': label, 'mapping_status': 'frame_split_goal_candidates',
                            'evidence_basis': 'saved_frame_failure', 'c_goals': [g for g in cr['goals'] if g['function'] == c['entry'] and '_assigns' in g['property']],
                            'java_alignment': 'family_only', 'java_relevant_kinds': ['Assignable']})
                if not case_edges:
                    case_edges.append({'kind': 'postcondition', 'semantic_role': 'functional_return_value_or_array_contents',
                        'mapping_status': 'failed_oracle_without_unique_clause', 'evidence_basis': 'saved_failed_clause_label', 'c_goals': [],
                        'java_alignment': 'family_only', 'java_relevant_kinds': ['Postcondition']})
            else:
                audit_execution = replay['independent_audit']['execution']
                kind = safety_kind(w, audit_execution); goals = safety_candidates(cr['goals'], kind)
                audit_stderr = audit_execution.get('stderr', '')
                audit_error = next((line.strip() for line in audit_stderr.splitlines() if 'ERROR:' in line or 'runtime error:' in line), None)
                # Harness line numbers cannot be treated as annotated WP line numbers.
                frames = list(dict.fromkeys(re.findall(r'\bin (\w+) ([^\n]+?\.c:\d+)', audit_stderr)))[:4]
                case_edges.append({'kind': 'safety', 'semantic_role': kind, 'evidence_basis': 'saved_execution_safety_failure',
                    'mapping_status': ('related_termination_goal_candidates' if kind == 'stack_overflow' else 'compatible_call_precondition_candidates') if goals else 'safety_goal_not_located',
                    'audit_error': audit_error, 'audit_trace_frames': [{'function': f, 'harness_location': loc} for f, loc in frames],
                    'goal_candidate_interpretation': 'Stack overflow is a resource failure; termination goals are related context, not proven failures or proof of nontermination.' if kind == 'stack_overflow' else 'Compatible helper preconditions; failing call site not established.',
                    'c_goals': goals, 'java_alignment': 'family_only' if SAFETY_JAVA[kind] else 'unlocated',
                    'java_relevant_kinds': SAFETY_JAVA[kind]})
        for edge in case_edges:
            relevant = [d for d in diags if d['kind'] in edge['java_relevant_kinds']]
            edge.update({'program': p, 'mutant_id': mid, 'java_relevant_diagnostics': relevant,
                         'java_confirmed_same_family_diagnostic': any(d['state'] == 'violated' for d in relevant),
                         'java_clause_specific_diagnostic_link': 'not_established',
                         'c_wp_reported_violation': any(g['state'] == 'violated' for g in edge['c_goals'])})
            edges.append(edge)
        cases.append({'program': p, 'mutant_id': mid, 'category': row['category'], 'c_validated_failure': row['validated'],
            'c_replay_outcome': row['outcome'], 'c_verification_outcome': cr['outcome'], 'java_verification_outcome': jr['outcome'],
            'c_record': cp.relative_to(study).as_posix(), 'java_record': jp.relative_to(study).as_posix(),
            'replay_record': rp.relative_to(study).as_posix(), 'replay_evidence': rp.with_name('replay.json').relative_to(study).as_posix(),
            'c_raw_source_sha256': cr['raw_source_sha256'], 'java_raw_source_sha256': jr['raw_source_sha256'],
            'inputs': w['inputs'] if row['validated'] else None, 'expected': w.get('expected') if row['validated'] else None,
            'observation': w.get('observation') if row['validated'] else None,
            'saved_failed_labels': w.get('failed_clauses', []) if row['validated'] else [],
            'safety_stderr': w.get('stderr') if row['validated'] and w['status'] != 'returned' else None,
            'safety_exit_code': w.get('exit_code') if row['validated'] and w['status'] != 'returned' else None,
            'java_generated_assertions': workloads[key]['generated_assertions'],
            'java_assertion_capture_complete': workloads[key]['capture_complete'], 'java_diagnostics': diags,
            'mapping_count': len(case_edges),
            'has_falsified_clause': any(e['mapping_status'].startswith('falsified_clause') for e in case_edges),
            'has_recorded_goal_for_falsified_clause': any(e['mapping_status'] == 'falsified_clause_with_recorded_goal' for e in case_edges),
            'has_confirmed_java_same_family_diagnostic': any(e['java_confirmed_same_family_diagnostic'] for e in case_edges)})
    assert len(cases) == 977 and sum(c['c_validated_failure'] for c in cases) == 900
    matrix = Counter((c['c_replay_outcome'] if c['c_validated_failure'] else 'no_validated_failure', c['java_verification_outcome']) for c in cases)
    stats = {'mutant_pairs': len(cases), 'validated_c_failures': 900,
        'cases_with_falsified_postcondition': sum(c['has_falsified_clause'] for c in cases),
        'cases_with_recorded_goal_for_falsified_clause': sum(c['has_recorded_goal_for_falsified_clause'] for c in cases),
        'cases_with_confirmed_java_same_family_diagnostic': sum(c['has_confirmed_java_same_family_diagnostic'] for c in cases),
        'mapping_edges_by_status': dict(Counter(e['mapping_status'] for e in edges)),
        'safety_cases_by_kind': dict(Counter(e['semantic_role'] for e in edges if e['kind'] == 'safety')),
        'distinct_mapped_postcondition_goals_by_wp_state': dict(Counter(g['state'] for e in edges if e['kind'] == 'postcondition' and e['mapping_status'].startswith('falsified_clause') for g in e['c_goals'])),
        'cross_language_case_matrix': [{'c_evidence': c, 'java_outcome': j, 'cases': n} for (c, j), n in sorted(matrix.items())]}
    return {'schema_version': 1, 'no_experiment_rerun': True, 'budget': {'goal_seconds': 10, 'case_seconds': 300},
        'interpretation': 'C native replay clause falsification is distinct from a WP invalid verdict. Java comparisons are paired-case semantic candidates or assertion families; the C witness was not executed in Java and Java diagnostics are not linked to individual Java clauses. Conditional WP proofs can depend on unproved intermediate obligations.',
        'source_experiment': study.relative_to(ROOT.parent.parent).as_posix(), 'source_path_root': 'repository',
        'contracts_file': contracts_path.relative_to(ROOT.parent.parent).as_posix(), 'contracts_sha256': digest(contracts_path),
        'helper_contracts': [{'path': 'verification/java_arrays/' + name, 'sha256': sha} for name, sha in helper_hashes.items()],
        'source_files': [{'path': p, 'sha256': h} for p, h in sorted(source_hashes.items())],
        'summary': stats, 'clause_catalog': catalog, 'cases': cases, 'mappings': edges}


def section(report):
    s = report['summary']; rows = s['cross_language_case_matrix']
    matrix = {(r['c_evidence'], r['java_outcome']): r['cases'] for r in rows}
    outcomes = ['specification violation', 'precondition/RTE failure', 'unknown/timeout']
    text = ['## C witness-to-goal and Java comparison (short budget)',
        'This offline mapping covers all **977 mutant pairs** using saved results at **10 seconds per goal / 300 seconds per case**. No verifier, compiler, or replay was run. C provides 900 validated execution failures: 710 functional/specification failures and 190 safety failures. Another 77 mutants have no validated witness; this does not establish correctness.',
        f"**{s['cases_with_falsified_postcondition']} mutants** have a concrete falsified C postcondition identified from the saved observation; **{s['cases_with_recorded_goal_for_falsified_clause']}** also have a recorded WP goal for at least one such clause. The clause index is resolved against the hash-pinned entry contract and the goal's property name. Goal states are retained exactly as saved; WP itself reported zero violated mutant goals.",
        '| C replay evidence | Java specification violation | Java safety failure | Java unknown/timeout | Total |',
        '|---|---:|---:|---:|---:|']
    for c in ['specification violation', 'precondition/RTE failure', 'no_validated_failure']:
        counts = [matrix.get((c, j), 0) for j in outcomes]
        text.append(f"| {c} | " + ' | '.join(str(v) for v in counts) + f" | {sum(counts)} |")
    text += ['', f"Safety evidence consists of {s['safety_cases_by_kind'].get('array_bounds',0)} array-bounds failures, {s['safety_cases_by_kind'].get('negative_allocation_size',0)} negative-allocation-size failures, {s['safety_cases_by_kind'].get('null_reference',0)} null-reference failures, and {s['safety_cases_by_kind'].get('stack_overflow',0)} stack overflows identified by the saved independent audit. Compatible runtime-helper preconditions are listed for **{s['mapping_edges_by_status'].get('compatible_call_precondition_candidates',0)} cases** as **candidates**, never as individually falsified goals. Stack-overflow cases include related termination/recursive-variant goals where recorded ({s['mapping_edges_by_status'].get('related_termination_goal_candidates',0)} cases); stack overflow does not establish nontermination or a falsified variant. Another {s['mapping_edges_by_status'].get('safety_goal_not_located',0)} safety cases have no located recorded goal. Audit harness source lines are retained as trace evidence and are not equated to annotated WP source lines. Final output cannot locate loop-invariant or loop-variant failures.",
        f"Java alignment records corresponding source-clause **semantic candidates**, saved generated-assertion counts, and canonical diagnostics for the same mutant ID. **{s['cases_with_confirmed_java_same_family_diagnostic']} C-failing mutants** have a confirmed Java diagnostic in at least one corresponding assertion family. This is a family-level comparison: no individual Java assertion verdict, same-input Java replay, or logical equivalence of the two predicates is established. Unknown Java diagnostics are not counted as confirmed failures. C-specific errno and native buffer properties may have no direct Java counterpart.",
        'Example: **CombSort mutant 11**, input `nums=[1,0]`, returned `[1,0]` instead of `[0,1]`. Its saved post-state falsifies C postconditions 2 (the `fb_value` content specification) and 3 (every element is at most the last element). These map to `typed_ref_combSort_ensures_2` and `typed_ref_combSort_ensures_3`, with their recorded WP states retained. Java postconditions 2 and 3 are semantic counterparts, while the saved Java case is unknown/timeout.',
        f"The {sum(s['distinct_mapped_postcondition_goals_by_wp_state'].values())} recorded postcondition goals mapped here retain **{s['distinct_mapped_postcondition_goals_by_wp_state'].get('unknown',0)} unknown** and **{s['distinct_mapped_postcondition_goals_by_wp_state'].get('proved',0)} proved** WP states. A mapped goal can be recorded as proved while a concrete execution falsifies its clause: that goal can rely on loop invariants or callee contracts whose separate obligations remain unproved. This mapping does not establish which intermediate assumption failed and does not change any verification verdict.",
        'Artifacts: [per-mutant comparison CSV](counterexample_goal_mapping/cases.csv), [one-row-per-goal comparison CSV](counterexample_goal_mapping/goals.csv), [clause/goal mapping CSV](counterexample_goal_mapping/mappings.csv), and [full witnesses, clause catalog, goal states and hash provenance](counterexample_goal_mapping/mapping.json). Counts of mapping edges differ from mutant counts because one witness can falsify several clauses and one candidate mapping can list several goals.']
    return '\n\n'.join(text[:3]) + '\n\n' + '\n'.join(text[3:8]) + '\n\n' + '\n\n'.join(text[9:]) + '\n'


def main():
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument('--study', type=Path, default=DEFAULT)
    parser.add_argument('--analysis-output', type=Path)
    args = parser.parse_args(); study = args.study.resolve()
    analysis = (args.analysis_output or analysis_directory(study)).resolve()
    report = build(study, analysis); out = analysis / 'counterexample_goal_mapping'; out.mkdir(parents=True, exist_ok=True)
    write_json(out / 'mapping.json', report)
    csv_file(out / 'cases.csv', report['cases'], ['program', 'mutant_id', 'category', 'c_validated_failure', 'c_replay_outcome', 'c_verification_outcome', 'java_verification_outcome', 'inputs', 'expected', 'saved_failed_labels', 'mapping_count', 'has_falsified_clause', 'has_recorded_goal_for_falsified_clause', 'has_confirmed_java_same_family_diagnostic', 'java_generated_assertions', 'java_assertion_capture_complete', 'java_diagnostics', 'c_record', 'java_record', 'replay_evidence'])
    csv_file(out / 'mappings.csv', report['mappings'], ['program', 'mutant_id', 'kind', 'semantic_role', 'mapping_status', 'clause_index', 'property', 'clause', 'evidence_basis', 'evidence', 'c_goals', 'c_wp_reported_violation', 'java_alignment', 'java_clause_index', 'java_clause', 'java_relevant_kinds', 'java_relevant_diagnostics', 'java_confirmed_same_family_diagnostic', 'java_clause_specific_diagnostic_link'])
    goal_rows = []
    for edge in report['mappings']:
        for goal in edge['c_goals'] or [{}]:
            goal_rows.append({**{k: edge.get(k) for k in ['program', 'mutant_id', 'kind', 'semantic_role', 'mapping_status', 'clause_index', 'clause', 'java_alignment', 'java_clause_index', 'java_clause', 'java_confirmed_same_family_diagnostic']},
                'c_goal': goal.get('goal'), 'c_property': goal.get('property'), 'c_function': goal.get('function'),
                'c_line': goal.get('line'), 'c_wp_state': goal.get('state'), 'c_wp_verdict': goal.get('verdict'),
                'java_confirmed_diagnostic_kinds': sorted({d['kind'] for d in edge['java_relevant_diagnostics'] if d['state'] == 'violated'}),
                'java_unknown_diagnostic_kinds': sorted({d['kind'] for d in edge['java_relevant_diagnostics'] if d['state'] == 'unknown'})})
    csv_file(out / 'goals.csv', goal_rows, ['program', 'mutant_id', 'kind', 'semantic_role', 'mapping_status', 'clause_index', 'clause', 'c_goal', 'c_property', 'c_function', 'c_line', 'c_wp_state', 'c_wp_verdict', 'java_alignment', 'java_clause_index', 'java_clause', 'java_confirmed_same_family_diagnostic', 'java_confirmed_diagnostic_kinds', 'java_unknown_diagnostic_kinds'])
    text = section(report); (out / 'section.md').write_text(text, encoding='utf-8')
    (out / 'README.md').write_text(text.replace('(counterexample_goal_mapping/', '('), encoding='utf-8')
    write_json(out / 'manifest.json', {'no_experiment_rerun': True, 'source_files': report['source_files'],
        'source_experiment': report['source_experiment'], 'source_path_root': 'repository',
        'analysis_code_sha256': digest(Path(__file__)), 'contracts_file': report['contracts_file'], 'contracts_sha256': report['contracts_sha256'],
        'helper_contracts': report['helper_contracts'],
        'artifacts': {name: digest(out / name) for name in ['mapping.json', 'cases.csv', 'mappings.csv', 'goals.csv', 'section.md', 'README.md']}})
    readme = analysis / 'README.md'; old = readme.read_text(encoding='utf-8-sig') if readme.is_file() else '# Goal/assertion analysis\n\n'
    block = '<!-- counterexample-goal-mapping:start -->\n' + text + '\n<!-- counterexample-goal-mapping:end -->\n\n'
    if '<!-- counterexample-goal-mapping:start -->' in old:
        old = re.sub(r'<!-- counterexample-goal-mapping:start -->.*?<!-- counterexample-goal-mapping:end -->\s*', lambda _: block, old, flags=re.S)
    else:
        old += '\n' + block
    readme.write_text(old.rstrip() + '\n', encoding='utf-8')
    audit_path = analysis / 'analysis_audit.json'
    audit = read(audit_path) if audit_path.is_file() else {'no_experiment_rerun': True, 'artifacts': {}}
    audit['counterexample_goal_mapping'] = {'no_experiment_rerun': True, 'source_files_hash_checked': len(report['source_files']), 'summary': report['summary'], 'manifest_sha256': digest(out / 'manifest.json')}
    audit['artifacts']['README.md'] = digest(readme)
    for path in out.iterdir():
        if path.suffix in {'.json', '.csv', '.md'}:
            audit['artifacts'][path.relative_to(analysis).as_posix()] = digest(path)
    write_json(audit_path, audit)
    print(json.dumps(report['summary'], indent=2))


if __name__ == '__main__':
    main()
