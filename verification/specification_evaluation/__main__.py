from __future__ import annotations

import argparse
import json
import sys
from pathlib import Path

from .manifest import (
    DEFAULT_C,
    DEFAULT_JAVA,
    DEFAULT_MANIFEST,
    InputError,
    load_population,
)
from .verifiers import Settings
from .workflow import run_population, validate_transfers


def positive(value: str) -> int:
    number = int(value)
    if number <= 0:
        raise argparse.ArgumentTypeError("must be positive")
    return number


def arguments() -> argparse.Namespace:
    """Expose a transfer-only check and the ordered evaluation stages."""
    parser = argparse.ArgumentParser(description=__doc__)
    subcommands = parser.add_subparsers(dest="action", required=True)
    for action in ("check", "run"):
        sub = subcommands.add_parser(action)
        sub.add_argument("--manifest", type=Path, default=DEFAULT_MANIFEST,
                         help="fixed eligible-pair selection_manifest.json")
        sub.add_argument("--java-originals", type=Path, default=DEFAULT_JAVA)
        sub.add_argument("--c-originals", type=Path, default=DEFAULT_C)
        sub.add_argument("--java-specs", type=Path,
                         help="directory of annotated original <Class>.java files")
        sub.add_argument("--c-specs", type=Path,
                         help="directory of annotated original <Class>.c files")
        sub.add_argument("--program", action="append", default=[],
                         help="restrict to a program; repeat for several programs")
    check = subcommands.choices["check"]
    check.add_argument("--output", type=Path,
                       help="read already frozen JSON specifications from this run directory")
    run = subcommands.choices["run"]
    run.add_argument("--output", type=Path, required=True, help="new or resumable run directory")
    run.add_argument("--stage", choices=("prepare", "originals", "mutants", "all"),
                     default="all", help="ordered study phase; all runs them in sequence")
    run.add_argument("--java-generator", help="argument template with {source} and {output}")
    run.add_argument("--c-generator", help="argument template with {source} and {output}")
    run.add_argument("--max-pairs", type=positive, help="limit mutant pairs for a pilot run")
    run.add_argument("--openjml", default="openjml")
    run.add_argument("--frama-c", default="frama-c")
    run.add_argument("--java-prover", default="cvc4")
    run.add_argument("--c-provers", default="Alt-Ergo:2.4.3,Z3:4.8.12")
    run.add_argument("--timeout", type=positive, default=300, help="process limit in seconds")
    run.add_argument("--goal-timeout", type=positive, default=10, help="Frama-C WP goal limit")
    run.add_argument("--memory-model", default="Typed+ref")
    run.add_argument("--machdep", default="x86_64")
    run.add_argument("--wp-memlimit", type=positive, default=1000)
    run.add_argument("--wp-par", type=positive, default=4)
    run.add_argument("--why3-extra-config", type=Path)
    return parser.parse_args()


def main() -> int:
    """Validate the selected population before dispatching any stage."""
    args = arguments()
    try:
        population = load_population(args.manifest, args.java_originals, args.c_originals)
        unknown = sorted(set(args.program) - set(population.programs))
        if unknown:
            raise InputError(f"Unknown original programs: {', '.join(unknown)}")
        programs = tuple(program for program in population.programs
                         if not args.program or program in args.program)
        if args.action == "check":
            # Check annotation placement across every eligible mutant without
            # invoking OpenJML or Frama-C.
            if args.output is None and (args.java_specs is None or args.c_specs is None):
                raise InputError("check requires --output or both --java-specs and --c-specs")
            result = validate_transfers(population, programs, args.java_specs, args.c_specs,
                                        args.output / "frozen_specs" if args.output else None)
            print(json.dumps(result, indent=2))
            return 0
        frozen = args.output / "frozen_specs" / "manifest.json"
        # Later stages can reuse frozen specifications without regenerating
        # them or requiring the original spec directories to remain present.
        if not frozen.is_file():
            if args.java_specs is None and args.java_generator is None:
                raise InputError("First stage requires --java-specs or --java-generator")
            if args.c_specs is None and args.c_generator is None:
                raise InputError("First stage requires --c-specs or --c-generator")
        settings = Settings(
            args.openjml, args.frama_c, args.java_prover, args.c_provers,
            args.timeout, args.goal_timeout, args.memory_model, args.machdep,
            args.wp_memlimit, args.wp_par,
            args.why3_extra_config.resolve() if args.why3_extra_config else None,
        )
        summary = run_population(population, programs, args.output,
                                 args.java_specs.resolve() if args.java_specs else None,
                                 args.c_specs.resolve() if args.c_specs else None,
                                 args.java_generator, args.c_generator, settings,
                                 args.max_pairs, args.stage)
        print(json.dumps({key: summary[key] for key in
                          ("eligible_pair_count", "originals", "mutants", "pairs", "complete")},
                         indent=2))
        return 0
    except (InputError, OSError, ValueError) as error:
        print(f"specification evaluation error: {error}", file=sys.stderr)
        return 2


if __name__ == "__main__":
    raise SystemExit(main())
