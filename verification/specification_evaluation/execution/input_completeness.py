"""Reconstruct input-level completeness from saved runtime observations.

Every row is a (language, program, mutant, admitted input) pair. Missing trials
remain missing, including inputs skipped after the old first-witness stop.
"""
from __future__ import annotations
import argparse
from collections import Counter
import json
from pathlib import Path

PRIMARY = 'frozen_return_value_or_array_contents'
DEFAULT = Path(__file__).resolve().parents[1] / 'results/execution_based'


def key(inputs):
    return json.dumps(inputs, sort_keys=True, separators=(',', ':'))


def read(path):
    return json.loads(path.read_text())


def trials(path):
    if not path.exists():
        return {}
    rows = {}
    for number, line in enumerate(path.read_text().splitlines(), 1):
        row = json.loads(line)
        identity = key(row['inputs'])
        if identity in rows:
            raise ValueError(f'Duplicate input: {path}:{number}')
        rows[identity] = (row, f'{path}:{number}')
    return rows


def run_observation(trial, language):
    # The consolidated historical C archive uses a flat execution record;
    # newer C runs wrap it in observation. Java always wraps its RAC result.
    if language == 'c' and 'status' in trial:
        return trial
    return trial.get('observation', {})


def returned(run, language):
    if run.get('status') != 'returned':
        return False, None
    if language == 'java':
        return ('return' in run), run.get('return')
    observation = run.get('observation', {})
    return ('result' in observation), observation.get('result')


def classify(language, original, mutant):
    """Separate output differences from specification decisions and failures."""
    if original is None:
        return {'classification': 'missing_original_trial'}
    original_run = run_observation(original, language)
    ok, original_value = returned(original_run, language)
    original_passed = (not original.get('failed_clauses') if language == 'c'
                       else original_run.get('category', 'returned') == 'returned')
    if not ok or not original_passed or original.get('preconditions_satisfied') is not True:
        return {'classification': 'original_not_passing'}
    if mutant is None:
        return {'classification': 'missing_mutant_trial', 'original_output': original_value}
    if mutant.get('preconditions_satisfied') is not True:
        return {'classification': 'admissibility_unconfirmed'}
    run = run_observation(mutant, language)
    audit = mutant.get('audit', {})
    actual = audit.get('uninstrumented_mutant', run) if language == 'java' else run
    has_output, value = returned(actual, language)
    row = {'original_output': original_value}
    if not has_output:
        category = actual.get('category', actual.get('status', 'unknown'))
        row.update(classification='safety_failure' if category in
                   {'runtime_safety', 'runtime_safety_failure'} else 'nonreturning_or_unresolved',
                   execution_status=category)
        return row
    row.update(mutant_output=value, output_differs=value != original_value)
    if language == 'java':
        category = run.get('category', run.get('status'))
        rejected = (category == 'postcondition_violation'
                    and audit.get('fresh_interpreted_rac', {}).get('category') == category
                    and audit.get('original_passed') is True)
        accepted = category == 'returned'
    else:
        failed = mutant.get('failed_clauses', [])
        replay = audit.get('sanitized_replay', {})
        rejected = PRIMARY in failed and (mutant.get('validated') is True or (
            audit.get('original_passed') is True
            and PRIMARY in audit.get('replayed_failed_clauses', [])
            and replay.get('status') == 'returned'
            and replay.get('observation') == run.get('observation')))
        accepted = run.get('status') == 'returned' and PRIMARY not in failed
    row['postcondition_decision'] = 'rejected' if rejected else 'accepted' if accepted else 'unresolved'
    if value == original_value:
        row['classification'] = 'same_output' if accepted else 'same_output_contract_failure'
    elif rejected:
        row['classification'] = 'different_output_rejected'
    elif accepted:
        row['classification'] = 'different_output_accepted'
    else:
        row['classification'] = 'different_output_unresolved'
    return row


def statistics(counts):
    counts = dict(counts)
    rejected = counts.get('different_output_rejected', 0)
    accepted = counts.get('different_output_accepted', 0)
    differing = rejected + accepted + counts.get('different_output_unresolved', 0)
    unresolved = sum(n for k, n in counts.items() if k not in {
        'different_output_rejected', 'different_output_accepted', 'same_output', 'safety_failure'})
    return {'counts': counts, 'expected_input_pairs': sum(counts.values()),
            'known_different_returned_outputs': differing,
            'decided_different_outputs': rejected + accepted,
            'observed_rejection_rate': rejected / (rejected + accepted) if rejected + accepted else None,
            'unresolved_input_pairs': unresolved,
            'all_input_pairs_resolved': unresolved == 0}


def strict_verdict(counts):
    """One accepted differing output fails; missing checks can never pass."""
    stats = statistics(counts)
    if counts.get('different_output_accepted', 0):
        return 'failed'
    if not stats['all_input_pairs_resolved']:
        return 'incomplete'
    if stats['known_different_returned_outputs'] == 0:
        return 'no_differing_returned_output'
    return 'passed'


def strict_summary(mutants):
    counts = Counter(m['strict_verdict'] for m in mutants)
    eligible = counts['passed'] + counts['failed']
    return {'strict_mutant_verdicts': dict(counts),
            'strict_mutant_rejection_rate': (
                counts['passed'] / eligible if eligible and not counts['incomplete'] else None),
            'strict_rate_complete': not counts['incomplete']}


def reconstruct(source, output):
    source, output = Path(source).resolve(), Path(output).resolve()
    if output == source or source in output.parents or output in source.parents:
        raise ValueError('Use a separate output directory outside the archived source tree')
    output.mkdir(parents=True, exist_ok=True)
    summaries = []
    examples = []
    for language in ('java', 'c'):
        language_root = source / language
        summary_path = language_root / 'summary.json'
        if not summary_path.exists():
            continue
        recorded = read(summary_path)
        total = Counter()
        per_program = []
        selected_count = 0
        for program_record in recorded['per_program']:
            program = program_record['program']
            pool_path = language_root / 'inputs' / (program + '.json')
            pool = read(pool_path)['candidates']
            pool_keys = [key(p['inputs']) for p in pool]
            if len(set(pool_keys)) != len(pool_keys):
                raise ValueError(f'Duplicate candidate in {pool_path}')
            case_root = language_root / 'cases' / program
            original_path = case_root / 'original' / language / 'trials.jsonl'
            original = trials(original_path)
            case_paths = sorted(case_root.glob(f'mutant_*/{language}/record.json'))
            if len(case_paths) != program_record['selected_mutants']:
                raise ValueError(f'Mutant population mismatch: {language}/{program}')
            selected_count += len(case_paths)
            program_counts = Counter()
            mutants = []
            destination = output / language / (program + '.jsonl')
            destination.parent.mkdir(parents=True, exist_ok=True)
            with destination.open('w') as stream:
                for path in case_paths:
                    mid = path.parent.parent.name.removeprefix('mutant_')
                    saved = trials(path.with_name('trials.jsonl'))
                    if set(saved) - set(pool_keys):
                        raise ValueError(f'Trial outside saved input pool: {path}')
                    counts = Counter()
                    for candidate in pool:
                        identity = key(candidate['inputs'])
                        o, op = original.get(identity, (None, None))
                        m, mp = saved.get(identity, (None, None))
                        row = {'language': language, 'program': program, 'mutant_id': mid,
                               **candidate, 'original_evidence': op, 'mutant_evidence': mp,
                               **classify(language, o, m)}
                        counts[row['classification']] += 1
                        stream.write(json.dumps(row) + '\n')
                        if row['classification'] == 'different_output_accepted' and len(examples) < 20:
                            examples.append(row)
                    stats = statistics(counts)
                    mutants.append({'mutant_id': mid, **stats,
                                    'has_accepted_faulty_output': counts['different_output_accepted'] > 0,
                                    'strict_verdict': strict_verdict(counts),
                                    'all_faulty_outputs_rejected_across_input_pool': strict_verdict(counts) == 'passed'})
                    program_counts.update(counts)
            per_program.append({'program': program, **statistics(program_counts), **strict_summary(mutants), 'mutants': mutants})
            total.update(program_counts)
        summaries.append({'language': language, 'selected_mutants': selected_count,
                          **statistics(total),
                          **strict_summary([m for p in per_program for m in p['mutants']]),
                          'per_program': per_program})
    if not summaries:
        raise ValueError('No language summaries found in source')
    result = {'schema_version': '2.0', 'metric': 'mutants rejecting every differing returned output across the input pool',
              'source': str(source), 'archive_reconstruction_complete': True,
              'full_input_evaluation_complete': all(s['all_input_pairs_resolved'] for s in summaries),
              'languages': summaries, 'accepted_faulty_examples': examples,
              'scope': 'Return values and array contents; safety and non-returning executions are separate. '
                       'Rates cover decided observed differences only; missing trials are not successes. '
                       'Original output is the differential reference, frozen postconditions decide rejection.'}
    (output / 'summary.json').write_text(json.dumps(result, indent=2) + '\n')
    lines = ['# Input-level completeness reconstruction', '',
             'For each admitted input, compare the mutant return value/array contents with the original. '
             'A mutant passes only when every differing returned output is rejected and the complete input pool has been examined. One accepted differing output fails it; missing checks leave it incomplete.', '',
             r'\[ C_{\mathrm{strict}} = \frac{N_{\mathrm{passed\ mutants}}}{N_{\mathrm{passed\ mutants}} + N_{\mathrm{failed\ mutants}}} \]', '',
             'The strict rate is withheld while any mutant is incomplete. Mutants with no differing returned output are reported separately.', '',
             '| Language | Mutants | Passed | Failed | Incomplete | No differing returned output | Strict rate |',
             '|---|---:|---:|---:|---:|---:|---:|']
    for s in summaries:
        v = s['strict_mutant_verdicts']; rate = s['strict_mutant_rejection_rate']
        lines.append(f"| {s['language']} | {s['selected_mutants']} | {v.get('passed',0)} | "
                     f"{v.get('failed',0)} | {v.get('incomplete',0)} | {v.get('no_differing_returned_output',0)} | "
                     + (f'{rate:.2%} |' if rate is not None else 'unavailable |'))
    lines += ['', 'Each language/program JSONL contains every expected mutant/input pair, both outputs when available, '
              'the contract decision, and source evidence locations. summary.json retains per-mutant and per-program counts. '
              'No observed difference gives an undefined rate, never a perfect score. '
              'Existing runtime omissions (including Java FindPoints freshness and unconfirmed frame coverage) still apply.', '',
              'A different accepted output is a concrete missed rejection relative to the original program. '
              'A mutant with no such observed output is not certified complete while trials remain missing.']
    (output / 'README.md').write_text('\n'.join(lines) + '\n')
    return result


def main():
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument('--source', type=Path, default=DEFAULT)
    parser.add_argument('--output', type=Path, required=True)
    parser.add_argument('--execute', action='store_true', help='Collect all inputs in a separate runtime directory before reconstruction')
    parser.add_argument('--study', type=Path, default=DEFAULT.parent / 'verifier_based')
    parser.add_argument('--runtime-output', type=Path)
    parser.add_argument('--language', choices=('java', 'c'), action='append')
    parser.add_argument('--program', action='append')
    parser.add_argument('--workers', type=int, default=4)
    args = parser.parse_args()
    if args.workers < 1:
        parser.error('--workers must be positive')
    if args.execute:
        if args.runtime_output is None:
            parser.error('--execute requires --runtime-output (a separate directory)')
        runtime = args.runtime_output.resolve()
        archive = DEFAULT.resolve()
        if runtime == archive or archive in runtime.parents or runtime in archive.parents:
            parser.error('--runtime-output must be separate from the historical archive')
        if runtime == args.output.resolve() or runtime in args.output.resolve().parents or args.output.resolve() in runtime.parents:
            parser.error('--output and --runtime-output must be separate directories')
        languages = args.language or ['java', 'c']
        if 'java' in languages:
            from .java_rac import OPENJML, JAVA, RUNTIME
            missing = [p for p in (OPENJML, JAVA, RUNTIME) if not Path(p).exists()]
            if missing:
                parser.error('Java runtime unavailable: ' + ', '.join(missing))
        for language in languages:
            if language == 'java':
                from .java_completeness import run_java
                run_java(args.study, runtime / language, args.program, args.workers,
                         omit_fresh=True, exhaustive=True)
            else:
                from .c_completeness import run_c
                run_c(args.study, runtime / language, args.program, args.workers, exhaustive=True)
        args.source = runtime
    result = reconstruct(args.source, args.output)
    print(json.dumps({s['language']: {k: v for k, v in s.items() if k != 'per_program'}
                      for s in result['languages']}, indent=2))


if __name__ == '__main__':
    main()
