"""Parse complete model blocks emitted by Frama-C WP without executing tools.
"""
from __future__ import annotations

import re
from typing import Any


def extract_models(stdout: str, goals: list[dict[str, Any]]) -> list[dict[str, Any]]:
    """Retain WP-reported counterexamples without changing proof verdicts.

    Source coordinates and obligation kind identify candidate goal IDs when WP's
    human-readable model block does not include an ID. Ambiguity is retained.
    Console goal IDs take priority over coordinate matching.
    """
    result = []
    console_models = {}
    for line in stdout.splitlines():
        if '(Model)' not in line or not line.startswith('[wp]'):
            continue
        ids = [g['goal'] for g in goals if g.get('goal') and
               re.search(r'(?<![\w])' + re.escape(g['goal']) + r'(?![\w])', line)]
        for goal_id in ids:
            console_models[goal_id] = line
    for block in re.split(r'(?m)(?=^Goal )', stdout):
        if not block.startswith('Goal ') or not re.search(r'(?m)^Prover .+\(Model\)', block):
            continue
        header = re.search(r'^Goal (.+?) \((?:file\s+)?(.+?), line (\d+)\) in \'([^\']+)\':', block)
        values = dict(re.findall(r'(?m)^Model (\S+) = (.+)$', block))
        filename = header[2].strip('"') if header else None
        candidates = [g for g in goals if header and g.get('function') == header[4]
                      and g.get('line') == int(header[3])
                      and (not g.get('file') or _basename(g['file']) == _basename(filename))]
        kind = header[1].lower() if header else ''
        families = (('post-condition', 'ensures'), ('pre-condition', 'requires'),
                    ('assertion', 'assert'), ('invariant', 'invariant'),
                    ('variant', 'variant'), ('assigns', 'assigns'),
                    ('terminat', 'terminat'))
        for description, property_kind in families:
            if description in kind:
                candidates = [g for g in candidates if property_kind in
                              str(g.get('property') or g.get('goal', '')).lower()]
                break
        if 'invariant' in kind:
            phase = 'preserved' if 'preserv' in kind else 'established' if 'establish' in kind else None
            if phase:
                candidates = [g for g in candidates if phase in str(g.get('goal', '')).lower()]
        ids_with_models = [g['goal'] for g in candidates if g['goal'] in console_models]
        goal_matches = ids_with_models or [g['goal'] for g in candidates]
        result.append({'kind': header[1] if header else None, 'function': header[4] if header else None,
                       'file': filename, 'line': int(header[3]) if header else None,
                       'goal_matches': goal_matches,
                       'goal_matching': 'unique' if len(goal_matches) == 1 else 'ambiguous' if goal_matches else 'unmatched',
                       'source': 'wp_model_block', 'values': values,
                       'text': block.split('------------------------------------------------------------')[0].strip()})
    covered = {goal_id for model in result for goal_id in model['goal_matches']}
    for goal_id, line in console_models.items():
        if goal_id not in covered:
            result.append({'goal_matches': [goal_id], 'goal_matching': 'unique',
                           'source': 'wp_console_model_marker', 'values': {}, 'text': line})
    return result


def _basename(path: str) -> str:
    return path.replace('\\', '/').rsplit('/', 1)[-1]
