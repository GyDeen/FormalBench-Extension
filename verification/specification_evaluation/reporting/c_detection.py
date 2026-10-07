"""Summarize saved verifier-only C mutant detections without executing tools."""
from __future__ import annotations

import argparse
from collections import Counter
import csv
from pathlib import Path

from verification.specification_evaluation.counterexamples.detection import POLICY
from verification.specification_evaluation.workflow import read_json, write_json


def collect(output: Path, population) -> dict:
    rows = []
    originals = {}
    for program in population.programs:
        path = output / 'cases' / program / 'original/c/record.json'
        originals[program] = read_json(path)['outcome'] if path.is_file() else 'not run'
    for pair in population.pairs:
        path = output / 'cases' / pair.program / f'mutant_{pair.mutant_id}/c/record.json'
        record = read_json(path) if path.is_file() else {'outcome': 'not run'}
        detection = record.get('verifier_detection')
        if detection and detection.get('policy') != POLICY:
            raise ValueError(f'Detection policy mismatch: {path}')
        if detection and detection['detected'] != detection['specification_detected']:
            raise ValueError(f'Completeness detection must exclude safety/unclassified-only evidence: {path}')
        status = detection['status'] if detection else 'not run' if record['outcome'] == 'not run' else 'not recorded'
        rows.append({'program': pair.program, 'mutant_id': pair.mutant_id,
                     'original_outcome': originals[pair.program], 'wp_outcome': record['outcome'],
                     'detection_status': status, 'detected': bool(detection and detection['detected']),
                     'specification_detected': bool(detection and detection['specification_detected']),
                     'safety_detected': bool(detection and detection['safety_detected']),
                     'unclassified_detected': bool(detection and detection['unclassified_detected']),
                     'evidence': detection['evidence'] if detection else [],
                     'record': path.relative_to(output).as_posix() if path.is_file() else None})

    def counts(cases):
        completed = sum(r['detection_status'] not in ('not run', 'not recorded') for r in cases)
        detected = sum(r['detected'] for r in cases)
        return {'eligible': len(cases), 'recorded': completed, 'detected': detected,
                'specification_detected': sum(r['specification_detected'] for r in cases),
                'safety_detected': sum(r['safety_detected'] for r in cases),
                'specification_and_safety': sum(r['specification_detected'] and r['safety_detected'] for r in cases),
                'safety_only': sum(r['safety_detected'] and not r['specification_detected'] for r in cases),
                'unclassified_detected': sum(r['unclassified_detected'] for r in cases),
                'statuses': dict(Counter(r['detection_status'] for r in cases)),
                'detection_rate_on_eligible': detected / len(cases) if cases and completed else None}

    return {'policy': POLICY, 'input_sources': ['Frama-C/WP prover models'],
            'complete': all(r['detection_status'] not in ('not run', 'not recorded') for r in rows),
            'original_verifier_outcomes': dict(Counter(originals.values())),
            'evidence_level': 'Verifier-reported goal counterexamples/refutations; no execution replay.',
            'all_mutants': counts(rows),
            'verified_original_cohort': counts([r for r in rows if r['original_outcome'] == 'proved']),
            'per_program': {program: counts([r for r in rows if r['program'] == program])
                            for program in population.programs}, 'records': rows}


def write_report(output: Path, population) -> dict:
    report = collect(output, population)
    folder = output / 'mutant_detection'
    folder.mkdir(parents=True, exist_ok=True)
    write_json(folder / 'summary.json', report)
    columns = ('program', 'mutant_id', 'original_outcome', 'wp_outcome', 'detection_status',
               'detected', 'specification_detected', 'safety_detected', 'unclassified_detected', 'record')
    with (folder / 'mutants.csv').open('w', encoding='utf-8', newline='') as stream:
        writer = csv.DictWriter(stream, fieldnames=columns, extrasaction='ignore')
        writer.writeheader()
        writer.writerows(report['records'])
    lines = ['# C verifier-only mutant detection', '',
             'Completeness counts a mutant once when WP reports a specification-goal counterexample '
             'or explicitly refutes a specification obligation. Safety/precondition failures and '
             'unclassified counterexamples are recorded separately and excluded from the completeness numerator. '
             'A mutant with both specification and safety evidence counts once in completeness, '
             'with the overlap retained. WP proof outcomes remain separate; no execution inputs are generated or replayed.', '',
             '| Cohort | Eligible mutants | Recorded | Completeness detections | Specification | Safety | Both | Completeness rate |',
             '| --- | ---: | ---: | ---: | ---: | ---: | ---: | ---: |']
    for title, key in [('All mutants', 'all_mutants'), ('Verified originals', 'verified_original_cohort')]:
        c = report[key]
        rate = c['detection_rate_on_eligible']
        values = [title, c['eligible'], c['recorded'], c['detected'], c['specification_detected'],
                  c['safety_detected'], c['specification_and_safety'], 'N/A' if rate is None else f'{100*rate:.2f}%']
        lines.append('| ' + ' | '.join(map(str, values)) + ' |')
    lines += ['', 'Unknown, timeout and failed proof attempts without a reported counterexample/refutation '
              'do not count as detections. Missing or historical records without the current detection policy '
              'remain not run/not recorded. Models are verifier evidence, not execution-validated witnesses.', '',
              'Per-mutant rows: [mutants.csv](mutants.csv). Full goal evidence and program-level counts: '
              '[summary.json](summary.json).', '']
    (folder / 'README.md').write_text('\n'.join(lines), encoding='utf-8')
    return report


def main() -> None:
    from verification.specification_evaluation.manifest import DEFAULT_MANIFEST, DEFAULT_JAVA, DEFAULT_C, load_population
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument('--output', type=Path, required=True)
    args = parser.parse_args()
    write_report(args.output.resolve(), load_population(DEFAULT_MANIFEST, DEFAULT_JAVA, DEFAULT_C))


if __name__ == '__main__':
    main()
