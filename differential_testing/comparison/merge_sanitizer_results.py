"""Apply explicit manual assessments without changing the raw comparison."""

from __future__ import annotations

import argparse
from copy import deepcopy
from pathlib import Path
from typing import Any

from .compare_java_c import _read_json
from .sanitizer_report import (
    ASSESSMENT_STATUSES,
    document_digest,
    read_sanitizer_csv,
    write_json,
)


def merge_sanitizer_assessments(
    comparison: dict[str, Any], sanitizer_report: dict[str, Any],
) -> dict[str, Any]:
    comparison_hash = document_digest(comparison)
    if sanitizer_report["cases"] and sanitizer_report["comparison_sha256"] != comparison_hash:
        raise ValueError("Sanitizer report belongs to a different raw comparison")
    final = deepcopy(comparison)
    final["kind"] = "java_c_comparison_final"
    final["raw_comparison_sha256"] = document_digest(comparison)
    final["sanitizer_report_sha256"] = document_digest(sanitizer_report)
    steps = {
        f"{test['id']}.{step['id']}": step
        for test in final["tests"] for step in test["steps"]
    }
    transitions = {status: 0 for status in ASSESSMENT_STATUSES}
    for case in sanitizer_report["cases"]:
        key = case["input_id"]
        if case.get("comparison_sha256", comparison_hash) != comparison_hash:
            raise ValueError(f"CSV row belongs to a different raw comparison: {key}")
        step = steps.get(key)
        if step is None or step["status"] != "mismatch" or case.get("java", {}).get("status") != "error":
            raise ValueError(f"Case is not a mismatching Java error: {key}")
        assessment = case.get("assessment")
        
        if assessment is not None:
            if assessment["status"] not in ASSESSMENT_STATUSES:
                raise ValueError(f"Invalid manual assessment status for {key}")
            if not assessment["reason"].strip():
                raise ValueError(f"Manual assessment requires a nonempty reason for {key}")
            # Failed builds and missing evidence cannot establish equivalence.
            evidence = case.get("c_sanitized", {})
            if assessment["status"] == "exception_equivalent" and (
                evidence.get("status") != "executed" or evidence.get("execution_error")
            ):
                raise ValueError(f"No sanitized execution available for {key}")
            
        step["original_comparison"] = deepcopy(step)
        step["sanitizer_evidence"] = deepcopy({
            name: case[name] for name in ("program", "input_id", "java", "c_normal", "c_sanitized")
        })
        step["assessment"] = deepcopy(assessment)
        if assessment is not None:
            step["status"] = assessment["status"]
            transitions[assessment["status"]] += 1

    for test in final["tests"]:
        statuses = {step["status"] for step in test["steps"]}
        structural_mismatch = any(test.get(key) for key in (
            "reasons", "extra_java_steps", "extra_c_steps",
        ))
        if structural_mismatch or "mismatch" in statuses:
            test["status"] = "mismatch"
        elif "unclear" in statuses:
            test["status"] = "unclear"
        elif "exception_equivalent" in statuses:
            test["status"] = "exception_equivalent"
        else:
            test["status"] = "match"

    summary = final["summary"]
    # Preserve counts for absent tests, which have no per-step report records.
    summary["mismatched_steps"] -= transitions["exception_equivalent"] + transitions["unclear"]
    summary["exception_equivalent_steps"] = transitions["exception_equivalent"]
    summary["unclear_steps"] = transitions["unclear"]
    for status, field in (
        ("match", "matched_tests"), ("mismatch", "mismatched_tests"),
        ("exception_equivalent", "exception_equivalent_tests"), ("unclear", "unclear_tests"),
    ):
        summary[field] = sum(test["status"] == status for test in final["tests"])
    no_extras = not summary["extra_java_tests"] and not summary["extra_c_tests"]
    summary["overall_match"] = summary["matched_tests"] == summary["expected_tests"] and no_extras
    summary["overall_equivalent"] = (
        summary["mismatched_tests"] == 0 and summary["unclear_tests"] == 0 and no_extras
    )
    return final


def main() -> None:
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("--comparison", type=Path, required=True)
    parser.add_argument("--sanitizer-report", type=Path, required=True, help="Reviewed sanitizer_report.csv")
    parser.add_argument("--output", "-o", type=Path, required=True)
    args = parser.parse_args()
    if args.output.resolve() in {args.comparison.resolve(), args.sanitizer_report.resolve()}:
        parser.error("Final output must not overwrite raw comparison or sanitizer evidence")
    final = merge_sanitizer_assessments(_read_json(args.comparison), read_sanitizer_csv(args.sanitizer_report))
    write_json(args.output, final)
    print(f"Wrote final comparison to {args.output}")
    if not final["summary"]["overall_equivalent"]:
        raise SystemExit(1)


if __name__ == "__main__":
    main()
