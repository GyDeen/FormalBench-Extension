"""Compare ordinary results, collect sanitizer evidence, and write audit files."""

from __future__ import annotations

import argparse
import math
import tempfile
from collections.abc import Sequence
from pathlib import Path

from ..execution.run_c_sanitizer import run_sanitizer_checks
from .compare_java_c import _read_json
from .extract_exception_mismatches import extract_exception_mismatches
from .merge_sanitizer_results import merge_sanitizer_assessments
from .result_comparator import compare_execution_results
from .sanitizer_report import build_sanitizer_report, write_json


def main(argv: Sequence[str] | None = None) -> None:
    parser = argparse.ArgumentParser(description=__doc__)
    for name in ("inputs", "java-results", "c-results", "c-dir", "output-dir"):
        parser.add_argument(f"--{name}", type=Path, required=True)
    parser.add_argument("--timeout", type=float, default=2.0)
    parser.add_argument("--compiler", default="clang")
    parser.add_argument("--keep-build", action="store_true")
    parser.add_argument("--relative-tolerance", type=float, default=1e-9)
    parser.add_argument("--absolute-tolerance", type=float, default=0.0)
    args = parser.parse_args(argv)
    if not math.isfinite(args.timeout) or args.timeout <= 0:
        parser.error("--timeout must be finite and positive")
    paths = {name: args.output_dir / f"{name}.json" for name in (
        "comparison_raw", "sanitizer_report", "comparison_final",
    )}
    if any(path.exists() for path in paths.values()):
        parser.error("Audit files already exist; use a new output directory, or merge the reviewed report")
    inputs, java_results, c_results = (
        _read_json(args.inputs), _read_json(args.java_results), _read_json(args.c_results),
    )
    raw = compare_execution_results(
        inputs, java_results, c_results,
        relative_tolerance=args.relative_tolerance,
        absolute_tolerance=args.absolute_tolerance,
    )
    write_json(paths["comparison_raw"], raw, exclusive=True)
    cases = extract_exception_mismatches(raw, java_results, c_results, inputs)
    if args.keep_build:
        sanitized = run_sanitizer_checks(
            cases, args.c_dir, args.output_dir / "build", args.timeout, compiler=args.compiler,
        )
    else:
        with tempfile.TemporaryDirectory(prefix="formalbench-sanitizer-") as temporary:
            sanitized = run_sanitizer_checks(
                cases, args.c_dir, Path(temporary), args.timeout, compiler=args.compiler,
            )
    report = build_sanitizer_report(raw, cases, sanitized)
    write_json(paths["sanitizer_report"], report, exclusive=True)
    final = merge_sanitizer_assessments(raw, report)
    write_json(paths["comparison_final"], final, exclusive=True)
    print(f"Wrote comparison files to {args.output_dir}; {len(cases)} cases await manual assessment.")
    if not final["summary"]["overall_equivalent"]:
        raise SystemExit(1)


if __name__ == "__main__":
    main()
