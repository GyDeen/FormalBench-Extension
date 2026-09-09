"""Compare each Java fault mutant with its C translation using saved inputs."""

from __future__ import annotations

import argparse
import json
import tempfile
from pathlib import Path

from .execution_orchestrator import _find_source, run_all
from .input_manifest import group_tests_by_class, read_inputs
from ..comparison.result_comparator import compare_execution_results


def main(argv: list[str] | None = None) -> None:
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("--inputs", type=Path, required=True)
    parser.add_argument("--mutants-dir", type=Path, required=True,
                        help="Generation run directory containing class/mutants/id/java")
    parser.add_argument("--class-name", action="append", help="Restrict to named original classes")
    parser.add_argument("--java-dir", type=Path,
                        help="Original selected Java directory for resolving EvoSuite class aliases")
    parser.add_argument("--timeout", type=float, default=2.0)
    args = parser.parse_args(argv)
    if args.timeout <= 0:
        parser.error("--timeout must be positive")
    document = read_inputs(args.inputs)
    grouped = group_tests_by_class(document)
    pairs = sorted(args.mutants_dir.glob("*/mutants/*/java"))
    if args.class_name:
        pairs = [path for path in pairs if path.parents[2].name in args.class_name]
        missing = set(args.class_name) - {path.parents[2].name for path in pairs}
        if missing:
            parser.error("No mutant pairs for: " + ", ".join(sorted(missing)))
    if not pairs:
        parser.error("No mutant java/ directories found in --mutants-dir")

    # Resolve any EvoSuite aliases against the original source set, using the
    # same unambiguous function lookup as the ordinary Java/C runner.
    selected_tests = {name: list(tests) for name, tests in grouped.items()}
    source_sets = ([args.java_dir.resolve()] if args.java_dir else
                   sorted((args.mutants_dir / "selected_java").glob("*")))
    if args.java_dir and not args.java_dir.is_dir():
        parser.error(f"Java source directory not found: {args.java_dir}")
    for expected, tests in grouped.items():
        functions = {step["function"] for test in tests for step in test["steps"]}
        matches = []
        for directory in source_sets:
            source, _ = _find_source(directory, expected, ".java", functions)
            if source is not None:
                matches.append(source)
        if len(matches) == 1 and matches[0].stem != expected:
            selected_tests.setdefault(matches[0].stem, []).extend(tests)

    failed = False
    for java_dir in pairs:
        pair = java_dir.parent
        name = java_dir.parents[2].name
        tests = selected_tests.get(name, [])
        if not tests:
            print(f"{name}/{pair.name}: no matching test inputs")
            failed = True
            continue
        if not list((pair / "c").glob("*.c")):
            print(f"{name}/{pair.name}: missing C translation in {pair / 'c'}")
            failed = True
            continue
        inputs = {**document, "tests": tests}
        results = pair / "results"
        results.mkdir(exist_ok=True)
        with tempfile.TemporaryDirectory(prefix="mutant-runners-") as tmp:
            java, c = run_all(inputs, java_dir, pair / "c", Path(tmp), args.timeout)
        report = compare_execution_results(inputs, java, c)
        for filename, data in (("test_inputs.json", inputs), ("java_results.json", java),
                               ("c_results.json", c), ("comparison.json", report)):
            (results / filename).write_text(json.dumps(data, indent=2) + "\n", encoding="utf-8")
        matches = report["summary"]["overall_match"]
        failed |= not matches
        print(f"{name}/{pair.name}: {'match' if matches else 'mismatch'}; results: {results}")
    if failed:
        raise SystemExit(1)


if __name__ == "__main__":
    main()
