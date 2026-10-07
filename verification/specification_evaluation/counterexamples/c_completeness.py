"""Run C completeness using only Frama-C WP counterexamples and refutations."""
from __future__ import annotations

import argparse
import json
from pathlib import Path
import shutil

from verification.specification_evaluation.counterexamples.detection import POLICY
from verification.specification_evaluation.backends.verifiers import Settings
from verification.specification_evaluation.manifest import (
    DEFAULT_MANIFEST, DEFAULT_JAVA, DEFAULT_C, REPO, InputError, load_population)
from verification.specification_evaluation.reporting.c_detection import write_report
from verification.specification_evaluation.workflow import read_json, run_population, write_json

DEFAULT_STUDY = REPO / 'verification/specification_evaluation/results/originals_stop_tool_error_20260927T161243Z_goal10_case300'


def prepare(output: Path, study: Path, population) -> None:
    """Copy existing frozen contracts into an isolated run; never generate any."""
    output, study = output.resolve(), study.resolve()
    if output == study or study in output.parents or output in study.parents:
        raise InputError('Use a separate output directory outside the source study')
    inputs = [study / 'frozen_specs' / language / f'{program}.json'
              for program in population.programs for language in ('java', 'c')]
    if any(not path.is_file() for path in inputs):
        raise InputError('Source study must contain all frozen original contracts')
    metadata = {'mode': 'c_verifier_only_completeness', 'policy': POLICY,
                'source_study': str(study), 'selection_manifest': str(population.manifest),
                'selection_manifest_sha256': population.manifest_sha256,
                'input_sources': ['Frama-C/WP prover models'],
                'independent_input_generation': False, 'partial_smt_queries': False,
                'native_replay': False}
    metadata_path = output / 'completeness_run.json'
    if output.exists() and not metadata_path.is_file():
        raise InputError('Use a fresh output directory or resume a verifier-only completeness run')
    if metadata_path.is_file() and read_json(metadata_path) != metadata:
        raise InputError('Existing completeness run uses different inputs or detection policy')
    for source in inputs:
        destination = output / source.relative_to(study)
        if destination.is_file() and destination.read_bytes() != source.read_bytes():
            raise InputError(f'Frozen contract changed: {destination}')
    output.mkdir(parents=True, exist_ok=True)
    for source in inputs:
        destination = output / source.relative_to(study)
        destination.parent.mkdir(parents=True, exist_ok=True)
        if not destination.exists():
            shutil.copy2(source, destination)
    write_json(metadata_path, metadata)


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument('--study', type=Path, default=DEFAULT_STUDY)
    parser.add_argument('--output', type=Path, required=True)
    parser.add_argument('--program', action='append', default=[])
    parser.add_argument('--max-pairs', type=int)
    parser.add_argument('--frama-c', default='frama-c')
    parser.add_argument('--c-provers', default='Z3:4.8.12')
    parser.add_argument('--goal-timeout', type=int, default=10)
    parser.add_argument('--timeout', type=int, default=300)
    parser.add_argument('--c-workers', type=int, default=1)
    parser.add_argument('--wp-par', type=int, default=2)
    parser.add_argument('--why3-extra-config', type=Path)
    args = parser.parse_args()
    if min(args.goal_timeout, args.timeout, args.c_workers, args.wp_par) <= 0 or (args.max_pairs is not None and args.max_pairs <= 0):
        parser.error('Budgets and worker/pair limits must be positive')
    try:
        population = load_population(DEFAULT_MANIFEST, DEFAULT_JAVA, DEFAULT_C)
        if set(args.program) - set(population.programs):
            raise InputError('Unknown program selector')
        programs = tuple(p for p in population.programs if not args.program or p in args.program)
        prepare(args.output, args.study, population)
        settings = Settings('openjml', args.frama_c, 'cvc4', args.c_provers,
                            args.timeout, args.goal_timeout, 'Typed+ref', 'x86_64',
                            1000, args.wp_par, args.why3_extra_config, True)
        try:
            run_population(population, programs, args.output, None, None, None, None,
                           settings, max_pairs=args.max_pairs, stage='all', languages=('c',),
                           c_workers=args.c_workers)
        finally:
            report = write_report(args.output.resolve(), population)
        print(json.dumps({key: report[key] for key in ('policy', 'all_mutants', 'verified_original_cohort')}, indent=2))
        return 0
    except (InputError, OSError, ValueError) as error:
        parser.error(str(error))


if __name__ == '__main__':
    raise SystemExit(main())
