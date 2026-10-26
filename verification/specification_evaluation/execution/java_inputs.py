"""The withdrawn C search's scalar input recipe, before Java domain filtering."""
from __future__ import annotations
import itertools
import json
import random
import re
from copy import deepcopy


def runtime_specification(spec, omit_fresh=False):
    """Create an explicit runtime projection without changing the frozen JML.

    Only the known standalone conjunct in FindPoints is supported. Other uses
    of freshness fail visibly rather than changing an arbitrary expression.
    """
    runtime = deepcopy(spec)
    runtime['runtime_omissions'] = []
    runtime['omit_fresh_requested'] = omit_fresh
    if not omit_fresh:
        return runtime
    pattern = re.compile(r'(ensures\s+\\result\s*!=\s*null)\s*&&\s*(\\fresh\s*\(\s*\\result\s*\))(?=\s*&&\s*\\result\.length\s*==\s*2\s*;)')
    for annotation in runtime['annotations']:
        original = annotation['text']
        updated, count = pattern.subn(r'\1', original)
        if '\\fresh' in updated:
            raise ValueError('Unsupported freshness expression; runtime omission is limited to the known result conjunct')
        if count:
            runtime['runtime_omissions'].append({
                'annotation_id': annotation.get('id'), 'expression': r'\fresh(\result)',
                'reason': 'Unsupported by installed RAC; unchecked for both original and mutants',
                'original_text': original, 'runtime_text': updated})
        annotation['text'] = updated
    return runtime


def signature(source, entry):
    match = re.search(r'(?m)^[ \t]*public\s+static\s+(int(?:\[\])?)\s+' + re.escape(entry) + r'\s*\(([^)]*)\)\s*\{', source)
    if not match:
        raise ValueError('Expected one public static int/int[] entry method')
    parameters = []
    for text in match[2].split(','):
        item = re.fullmatch(r'\s*int\s+([A-Za-z_$][\w$]*)\s*', text)
        if not item:
            raise ValueError('This pilot supports scalar int parameters only')
        parameters.append(item[1])
    return match, match[1], parameters


def bounded(program, names):
    rng = random.Random(726)
    # Preserve the old generator's RNG consumption for its unused array pool.
    for _ in range(60):
        for _ in range(rng.randrange(1, 8)):
            rng.choice((-3, -1, 0, 1, 2, 4, 2**31-1, -2**31))
    small = (-2, -1, 0, 1, 2, 3, 4, 7)
    rows = [dict(zip(names, values)) for values in itertools.product(small, repeat=len(names))]
    rows += [{name: rng.choice(small + (-2**31, 2**31-1, 16, 31, 32, 63)) for name in names} for _ in range(120)]
    if program == 'OddBitSetNumber':
        rows += [{'n': ((1 << bit) + 2**31) % 2**32 - 2**31} for bit in range(32)]
    return rows


def input_pool(program, entry, names, tests, annotations):
    # A non-empty requires clause is intentionally rejected rather than guessed.
    # The five selected frozen contracts have no explicit entry preconditions.
    if any(re.search(r'\brequires\b', a['text']) for a in annotations):
        raise ValueError('Nontrivial entry preconditions need a separately validated evaluator')
    rows = []
    for test in tests['tests']:
        steps = test.get('steps', [])
        if not steps or steps[0]['class'] != program or steps[0]['function'] != entry:
            continue
        fixtures = {v['id']: v['value'] for v in test.get('fixtures', []) if 'value' in v}
        args = steps[0]['arguments']
        if len(args) != len(names):
            raise ValueError('Saved test argument count does not match entry')
        try:
            values = {n: a['value'] if 'value' in a else fixtures[a['ref']] for n, a in zip(names, args)}
        except KeyError as error:
            raise ValueError('Unresolved saved first-call fixture') from error
        rows.append((values, 'saved_test:' + test['id']))
    saved_count = len(rows)
    generated = bounded(program, names)
    rows += [(v, 'bounded_search_seed726') for v in generated]
    seen, accepted, invalid = set(), [], 0
    for values, origin in rows:
        key = json.dumps(values, sort_keys=True)
        if key in seen:
            continue
        seen.add(key)
        if set(values) != set(names) or not all(type(v) is int and -2**31 <= v < 2**31 for v in values.values()):
            invalid += 1
            continue
        accepted.append({'inputs': values, 'origin': origin, 'preconditions_satisfied': True})
    return accepted, {'saved_first_calls': saved_count, 'bounded_before_deduplication': len(generated),
                      'unique_candidates': len(seen), 'admissible_inputs': len(accepted),
                      'invalid_java_int_inputs': invalid, 'excluded_by_entry_preconditions': 0,
                      'entry_precondition': 'true (no explicit requires; all parameters primitive int)',
                      'admissible_by_origin': {kind: sum(v['origin'].startswith(prefix) for v in accepted)
                                               for kind, prefix in [('evosuite', 'saved_test:'), ('bounded', 'bounded_search')]}}


def frozen_source(raw, annotations, entry):
    if any(a['target'] != 'function' or a['function'] != entry for a in annotations):
        raise ValueError('Pilot does not relocate internal or helper annotations')
    if re.search(r'/\*@|//@', raw):
        raise ValueError('Raw source already has JML annotations')
    match, return_type, names = signature(raw, entry)
    comments = '\n'.join(a['text'] for a in annotations) + '\n'
    return raw[:match.start()] + comments + raw[match.start():], return_type, names


def replace_entry_body(source, entry, statement):
    match, _, _ = signature(source, entry)
    start, depth = match.end()-1, 1
    cursor = start+1
    while depth and cursor < len(source):
        if source[cursor] == '{': depth += 1
        elif source[cursor] == '}': depth -= 1
        cursor += 1
    if depth:
        raise ValueError('Unbalanced control source')
    return source[:start+1] + '\n' + statement + '\n' + source[cursor-1:]
