"""Run Java mutants and compare their behavior with saved original Java results.

The script reuses the extracted EvoSuite inputs, executes each mutant in an
isolated temporary Java harness, and writes only a summary.  Individual mutant
outputs, generated harnesses, and build files are removed after each mutant.
"""

from __future__ import annotations

import argparse
import json
import tempfile
from pathlib import Path
from typing import Any

from ..execution.execution_orchestrator import (
    Toolchain,
    _build_java_runner,
    _collect_results,
    _find_source,
    _result_document,
)
from ..execution.input_manifest import group_tests_by_class, read_inputs
from .result_comparator import compare_execution_results


def _read_json(path: Path) -> dict[str, Any]:
    try:
        value = json.loads(path.read_text(encoding="utf-8"))
    except (OSError, json.JSONDecodeError) as exc:
        raise ValueError(f"Cannot read JSON report {path}: {exc}") from exc
    if not isinstance(value, dict):
        raise ValueError(f"JSON report {path} must contain an object")
    return value


def _validate_origin_results(path: Path, document: dict[str, Any]) -> None:
    if document.get("kind") != "test_execution_results":
        raise ValueError(
            f"Origin results must have kind 'test_execution_results': {path}"
        )
    if document.get("language") != "java":
        raise ValueError(f"Origin results must contain Java results: {path}")
    if not isinstance(document.get("tests"), list):
        raise ValueError(f"Origin results must contain a tests array: {path}")


def _step_count(tests: list[dict[str, Any]]) -> int:
    return sum(len(test.get("steps", [])) for test in tests)


def _run_java(
    document: dict[str, Any], java_dir: Path, build_dir: Path, timeout: float,
) -> dict[str, Any]:
    """Run Java by composing the existing execution-orchestrator helpers."""
    toolchain = Toolchain.discover()
    java_records: list[dict[str, Any]] = []
    for expected_class, tests in group_tests_by_class(document).items():
        functions = {step["function"] for test in tests for step in test["steps"]}
        java_source, java_error = _find_source(
            java_dir, expected_class, ".java", functions
        )
        java_runner, _ = _build_java_runner(
            java_source,
            java_error,
            tests,
            build_dir / "java" / expected_class,
            toolchain,
        )
        java_records.extend(_collect_results(tests, java_runner, timeout))
    return _result_document("java", java_records)


def _select_tests(
    document: dict[str, Any], java_dir: Path, program: str,
) -> list[dict[str, Any]]:
    """Select tests for a mutant, including unambiguous class-name aliases."""
    grouped = group_tests_by_class(document)
    selected = list(grouped.get(program, []))
    selected_ids = {test["id"] for test in selected}

    for expected_class, tests in grouped.items():
        if expected_class == program:
            continue
        functions = {
            step["function"]
            for test in tests
            for step in test["steps"]
        }
        source, _ = _find_source(java_dir, expected_class, ".java", functions)
        if source is None or source.stem == expected_class:
            continue
        for test in tests:
            if test["id"] not in selected_ids:
                selected.append(test)
                selected_ids.add(test["id"])
    return selected


def _mutant_pairs(mutants_dir: Path, class_names: list[str] | None) -> list[Path]:
    pairs = sorted(
        path
        for path in mutants_dir.glob("*/mutants/*/java")
        if any(path.glob("*.java"))
    )
    if class_names:
        allowed = set(class_names)
        pairs = [path for path in pairs if path.parents[2].name in allowed]
        found = {path.parents[2].name for path in pairs}
        missing = allowed - found
        if missing:
            raise ValueError("No Java mutants found for: " + ", ".join(sorted(missing)))
    return pairs


def _comparison_summary(
    program: str,
    mutant_id: str,
    tests: list[dict[str, Any]],
    comparison: dict[str, Any],
) -> dict[str, Any]:
    summary = comparison["summary"]
    same = bool(summary["overall_match"])
    return {
        "mutant": f"{program}/{mutant_id}",
        "program": program,
        "mutant_id": mutant_id,
        "status": "same_as_origin" if same else "different_from_origin",
        "same_as_origin_across_all_inputs": same,
        "tests_compared": summary["expected_tests"],
        "steps_compared": _step_count(tests),
        "matched_tests": summary["matched_tests"],
        "mismatched_tests": summary["mismatched_tests"],
        "matched_steps": summary["matched_steps"],
        "mismatched_steps": summary["mismatched_steps"],
        "missing_origin_tests": len(summary["missing_java_tests"]),
        "missing_mutant_tests": len(summary["missing_c_tests"]),
        "extra_mutant_tests": len(summary["extra_c_tests"]),
    }


def _unavailable_summary(
    program: str, mutant_id: str, reason: str,
) -> dict[str, Any]:
    return {
        "mutant": f"{program}/{mutant_id}",
        "program": program,
        "mutant_id": mutant_id,
        "status": "unavailable",
        "same_as_origin_across_all_inputs": False,
        "reason": reason,
    }


def run(args: argparse.Namespace) -> dict[str, Any]:
    inputs = read_inputs(args.inputs)
    origin_results = _read_json(args.origin_results)
    _validate_origin_results(args.origin_results, origin_results)
    if not args.mutants_dir.is_dir():
        raise ValueError(f"Mutants directory not found: {args.mutants_dir}")

    pairs = _mutant_pairs(args.mutants_dir, args.class_name)
    if not pairs:
        raise ValueError(f"No Java mutant directories found in {args.mutants_dir}")

    origin_tests = origin_results["tests"]
    results: list[dict[str, Any]] = []
    for index, java_dir in enumerate(pairs, start=1):
        program = java_dir.parents[2].name
        mutant_id = java_dir.parent.name
        tests = _select_tests(inputs, java_dir, program)
        label = f"{program}/{mutant_id}"
        if not tests:
            results.append(_unavailable_summary(program, mutant_id, "no_matching_inputs"))
            if args.verbose:
                print(
                    f"[{index}/{len(pairs)}] {label}: "
                    "unavailable (no matching inputs)",
                    flush=True,
                )
            continue

        mutant_inputs = {**inputs, "tests": tests}
        test_ids = {test["id"] for test in tests}
        origin_subset = {
            **origin_results,
            "tests": [
                test for test in origin_results["tests"]
                if test.get("id") in test_ids
            ],
        }
        try:
            with tempfile.TemporaryDirectory(
                prefix=f"mutant-origin-{program}-{mutant_id}-"
            ) as temporary:
                mutant_results = _run_java(
                    mutant_inputs,
                    java_dir,
                    Path(temporary),
                    args.timeout,
                )
            comparison = compare_execution_results(
                mutant_inputs,
                origin_subset,
                mutant_results,
            )
            result = _comparison_summary(program, mutant_id, tests, comparison)
            results.append(result)
            if args.verbose:
                print(
                    f"[{index}/{len(pairs)}] {label}: {result['status']}",
                    flush=True,
                )
        except Exception as exc:  # keep the remaining mutants running
            results.append(_unavailable_summary(program, mutant_id, "execution_error"))
            if args.verbose:
                print(
                    f"[{index}/{len(pairs)}] {label}: "
                    f"unavailable ({type(exc).__name__})",
                    flush=True,
                )

    same = sum(
        result["status"] == "same_as_origin" for result in results
    )
    different = sum(
        result["status"] == "different_from_origin" for result in results
    )
    unavailable = len(results) - same - different
    summary = {
        "origin_tests": len(origin_tests),
        "origin_steps": _step_count(origin_tests),
        "mutants_discovered": len(pairs),
        "mutants_compared": same + different,
        "same_as_origin": same,
        "different_from_origin": different,
        "unavailable": unavailable,
        "all_compared_mutants_different_from_origin": (
            different == len(pairs) and unavailable == 0
        ),
    }
    return {
        "schema_version": "1.0",
        "kind": "mutant_origin_execution_summary",
        "method": (
            "Each Java mutant was executed on the extracted inputs for its "
            "program and compared with the saved original Java outcomes."
        ),
        "summary": summary,
        "mutants": results,
    }


def main(argv: list[str] | None = None) -> None:
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("--inputs", type=Path, required=True)
    parser.add_argument(
        "--mutants-dir",
        type=Path,
        required=True,
        help="Fault-mutant run directory containing class/mutants/id/java",
    )
    parser.add_argument(
        "--origin-results",
        "--origin-java-results",
        dest="origin_results",
        type=Path,
        required=True,
        help="Saved original Java results.json, not comparison_final.json",
    )
    parser.add_argument("--output", "-o", type=Path, required=True)
    parser.add_argument("--class-name", action="append")
    parser.add_argument("--timeout", type=float, default=2.0)
    parser.add_argument(
        "--verbose",
        action="store_true",
        help="Print per-mutant progress to stdout",
    )
    args = parser.parse_args(argv)
    if args.timeout <= 0:
        parser.error("--timeout must be positive")

    try:
        result = run(args)
    except ValueError as exc:
        parser.error(str(exc))

    args.output.parent.mkdir(parents=True, exist_ok=True)
    args.output.write_text(
        json.dumps(result, indent=2, ensure_ascii=False) + "\n",
        encoding="utf-8",
    )
    summary = result["summary"]
    if args.verbose:
        print(
            f"Compared {summary['mutants_compared']} mutants: "
            f"{summary['different_from_origin']} different from origin, "
            f"{summary['same_as_origin']} same, "
            f"{summary['unavailable']} unavailable."
        )
    if summary["unavailable"]:
        raise SystemExit(1)


if __name__ == "__main__":
    main()
