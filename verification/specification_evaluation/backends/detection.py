"""Record verifier-only C detection evidence separately from proof outcomes."""
from __future__ import annotations

from typing import Any

POLICY = 'frama_c_wp_specification_counterexample_or_explicit_invalidity_v2'


def detection_result(outcome: str, goals: list[dict[str, Any]],
                     models: list[dict[str, Any]], *, timed_out: bool = False,
                     source_file: str | None = None) -> dict[str, Any]:
    """Count specification-goal counterexamples/refutations for completeness.

    A failed proof, timeout or unknown alone is not a detection. Models are
    verifier-reported goal counterexamples, not execution-validated witnesses.
    Ambiguous model-to-ID mappings are saved without asserting every candidate
    goal was falsified. All input assignments originate in the verifier.
    """
    by_id = {g['goal']: g for g in goals if g.get('goal') and not g.get('smoke')}
    evidence, excluded = [], []
    for goal_id, goal in by_id.items():
        if goal.get('explicit_invalidity') or goal.get('verdict') == 'invalid':
            evidence.append({'kind': 'explicit_invalidity', 'goal_candidates': [goal_id],
                             'category': goal['category'], 'goal_matching': 'unique'})
    for index, model in enumerate(models):
        candidates = [goal_id for goal_id in model['goal_matches'] if goal_id in by_id]
        if not candidates:
            # A process timeout can prevent the JSON report from being written.
            # Retain a complete WP model block for the actual submitted source
            # without inventing a goal identifier or a goal category.
            model_file = str(model.get('file', '')).replace('\\', '/').rsplit('/', 1)[-1]
            if (not goals and source_file and model_file == source_file
                    and model.get('function') and model.get('line') is not None
                    and 'smoke' not in str(model.get('kind', '')).lower()):
                evidence.append({'kind': 'reported_counterexample', 'model_index': index,
                                 'goal_candidates': [], 'goal_matching': 'description_only',
                                 'category': 'unclassified',
                                 'goal_description': {key: model.get(key) for key in ('kind', 'file', 'line', 'function')}})
                continue
            excluded.append({'model_index': index, 'reason': 'no matching non-smoke WP goal'})
            continue
        if any(by_id[goal_id]['state'] == 'proved' for goal_id in candidates):
            excluded.append({'model_index': index, 'reason': 'model conflicts with a proved candidate goal'})
            continue
        categories = {by_id[goal_id]['category'] for goal_id in candidates}
        evidence.append({'kind': 'reported_counterexample', 'model_index': index,
                         'goal_candidates': candidates, 'goal_matching': model['goal_matching'],
                         'category': next(iter(categories)) if len(categories) == 1 else 'unclassified'})
    # Unsupported/malformed tool output cannot establish study detection.
    if outcome == 'syntax/tool failure':
        evidence = []
    categories = {entry['category'] for entry in evidence}
    detected = 'specification' in categories
    status = ('detected' if detected else 'tool failure' if outcome == 'syntax/tool failure'
              else 'not run' if outcome == 'not run'
              else 'safety failure' if 'precondition/RTE' in categories
              else 'unclassified counterexample' if 'unclassified' in categories
              else 'not detected' if outcome == 'proved'
              else 'inconclusive')
    return {'policy': POLICY, 'source': 'Frama-C/WP', 'detected': detected, 'status': status,
            'specification_detected': detected,
            'safety_detected': 'precondition/RTE' in categories,
            'unclassified_detected': 'unclassified' in categories,
            'evidence': evidence, 'excluded_models': excluded,
            'execution_validated': False, 'timed_out': timed_out}
