"""Parse complete model blocks emitted by Frama-C WP without executing tools.
"""
from __future__ import annotations

import re
from typing import Any


def extract_models(stdout: str, goals: list[dict[str, Any]]) -> list[dict[str, Any]]:
    """Retain WP's full model blocks; models alone never change goal status."""
    result = []
    for block in re.split(r'(?m)(?=^Goal )', stdout):
        if not block.startswith('Goal ') or not re.search(r'(?m)^Prover .+\(Model\)', block):
            continue
        header = re.search(r'^Goal (.+?) \("?[^"\n]+"?, line (\d+)\) in \'([^\']+)\':', block)
        values = dict(re.findall(r'(?m)^Model (\S+) = (.+)$', block))
        goal_matches = [g['goal'] for g in goals if header and g.get('function') == header[3] and g.get('line') == int(header[2])]
        result.append({'kind': header[1] if header else None, 'function': header[3] if header else None,
                       'line': int(header[2]) if header else None, 'goal_matches': goal_matches,
                       'values': values, 'text': block.split('------------------------------------------------------------')[0].strip()})
    return result
