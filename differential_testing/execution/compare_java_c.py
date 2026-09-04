"""Command-line interface for comparing Java and C test results."""

from __future__ import annotations

import argparse
import json
from collections.abc import Sequence
from pathlib import Path
from typing import Any

if __package__:
    from .result_comparator import compare_execution_results
else:
    from result_comparator import compare_execution_results


def _read_json(path: Path) -> dict[str, Any]:
    try:
        document = json.loads(path.read_text(encoding="utf-8"))
    except json.JSONDecodeError as error:
        raise ValueError(f"Invalid JSON in {path}: {error}") from error
    if not isinstance(document, dict):
        raise ValueError(f"Expected a JSON object in {path}")
    return document


def parse_args(argv: Sequence[str] | None = None) -> argparse.Namespace:
    parser = argparse.ArgumentParser(
        description="Compare normalized Java and C results for extracted tests."
    )
    parser.add_argument("--inputs", type=Path, required=True)
    parser.add_argument("--java-results", type=Path, required=True)
    parser.add_argument("--c-results", type=Path, required=True)
    parser.add_argument("--output", "-o", type=Path, required=True)
    parser.add_argument(
        "--relative-tolerance",
        type=float,
        default=1e-9,
        help="Relative tolerance for floating-point values (default: 1e-9)",
    )
    parser.add_argument(
        "--absolute-tolerance",
        type=float,
        default=0.0,
        help="Absolute tolerance for floating-point values (default: 0)",
    )
    return parser.parse_args(argv)


def main(argv: Sequence[str] | None = None) -> None:
    args = parse_args(argv)
    report = compare_execution_results(
        _read_json(args.inputs),
        _read_json(args.java_results),
        _read_json(args.c_results),
        relative_tolerance=args.relative_tolerance,
        absolute_tolerance=args.absolute_tolerance,
    )
    args.output.parent.mkdir(parents=True, exist_ok=True)
    args.output.write_text(
        json.dumps(report, indent=2, ensure_ascii=False) + "\n",
        encoding="utf-8",
    )
    summary = report["summary"]
    print(
        f"Compared {summary['expected_tests']} tests: "
        f"{summary['matched_tests']} matched, "
        f"{summary['mismatched_tests']} mismatched."
    )
    if not summary["overall_match"]:
        raise SystemExit(1)


if __name__ == "__main__":
    main()
