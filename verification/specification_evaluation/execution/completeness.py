"""Primary strict completeness over every differing returned mutant output."""
from __future__ import annotations
import argparse
from pathlib import Path

from .input_completeness import DEFAULT, reconstruct
from verification.specification_evaluation.workflow import write_json


def runtime_directory(output, runtime_output=None):
    """Keep raw trials separate from the primary reconstructed result."""
    output = Path(output).resolve()
    runtime = (Path(runtime_output).resolve() if runtime_output is not None
               else output.with_name(output.name + '_runtime'))
    if runtime == output or runtime in output.parents or output in runtime.parents:
        raise ValueError('Runtime and primary output directories must be separate')
    if runtime == DEFAULT.resolve() or DEFAULT.resolve() in runtime.parents:
        raise ValueError('Use a fresh runtime directory outside the historical archive')
    if output == DEFAULT.resolve() or DEFAULT.resolve() in output.parents:
        raise ValueError('Use a fresh primary output directory outside the historical archive')
    return runtime


def collect(output, runtime_output=None):
    """Derive strict verdicts from all trials; missing checks stay incomplete."""
    output = Path(output).resolve()
    runtime = runtime_directory(output, runtime_output)
    result = reconstruct(runtime, output)
    result.update(
        primary_aggregation='mutants rejecting every differing returned output across the complete input pool',
        safety_contributes_to_primary=False,
        complete=result['full_input_evaluation_complete'],
    )
    write_json(output / 'summary.json', result)
    return result


def run_completeness(study, output, languages=('java', 'c'), programs=None,
                     workers=4, manifest=None, max_pairs=None, runtime_output=None):
    """Execute full input pools, then publish the strict completeness result."""
    from .java_completeness import run_java
    from .c_completeness import run_c
    if workers < 1:
        raise ValueError('Workers must be positive')
    languages = tuple(languages)
    if not languages or any(language not in ('java', 'c') for language in languages):
        raise ValueError('Select Java, C, or both languages')
    runtime = runtime_directory(output, runtime_output)
    for language in languages:
        if language == 'java':
            run_java(study, runtime / language, programs, workers, manifest, max_pairs,
                     omit_fresh=True, exhaustive=True)
        else:
            run_c(study, runtime / language, programs, workers, manifest, max_pairs,
                  exhaustive=True)
    return collect(output, runtime)


def main():
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument('--study', type=Path, required=True)
    parser.add_argument('--output', type=Path, required=True)
    parser.add_argument('--runtime-output', type=Path,
                        help='raw evidence directory; defaults to OUTPUT_runtime')
    parser.add_argument('--language', choices=('java', 'c'), action='append')
    parser.add_argument('--program', action='append')
    parser.add_argument('--workers', type=int, default=4)
    args = parser.parse_args()
    if args.workers < 1:
        parser.error('--workers must be positive')
    run_completeness(args.study, args.output, args.language or ('java', 'c'),
                     args.program, args.workers, runtime_output=args.runtime_output)


if __name__ == '__main__':
    main()
