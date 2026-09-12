from __future__ import annotations

import argparse
import json
from dataclasses import dataclass
from pathlib import Path
from typing import Any


class ReportFormatError(ValueError):
    """Raised when an input JSON file is not a supported report."""


@dataclass(frozen=True)
class TestReport:
    """The test records for one mutant or one original-program report."""

    label: str | None
    tests: list[dict[str, Any]]


_REPORT_FIELDS = ("comparison", "final_equivalence", "report", "result")
_BEHAVIOUR_FIELDS = (
    "status",
    "output",
    "outcome",
    "result",
    "return",
    "state_after",
    "error",
)


def _read_json(path: Path) -> dict[str, Any]:
    try:
        value = json.loads(path.read_text(encoding="utf-8"))
    except (OSError, json.JSONDecodeError) as exc:
        raise ReportFormatError(f"Cannot read JSON report {path}: {exc}") from exc
    if not isinstance(value, dict):
        raise ReportFormatError(f"JSON report {path} must contain an object")
    return value


def _report_document(value: Any, context: str) -> dict[str, Any] | None:
    """Return a dictionary containing ``tests``, if one is present."""
    if isinstance(value, dict) and isinstance(value.get("tests"), list):
        return value
    if isinstance(value, dict):
        for field in _REPORT_FIELDS:
            nested = value.get(field)
            if isinstance(nested, dict) and isinstance(nested.get("tests"), list):
                return nested
    return None


def _test_records(document: dict[str, Any], context: str) -> list[dict[str, Any]]:
    tests = document.get("tests")
    if not isinstance(tests, list):
        raise ReportFormatError(f"{context} must contain a list named 'tests'")
    records: list[dict[str, Any]] = []
    seen: set[str] = set()
    for index, test in enumerate(tests):
        if not isinstance(test, dict):
            raise ReportFormatError(f"{context}.tests[{index}] must be an object")
        test_id = test.get("id")
        if not isinstance(test_id, str) or not test_id:
            raise ReportFormatError(
                f"{context}.tests[{index}] must have a non-empty string 'id'"
            )
        if test_id in seen:
            raise ReportFormatError(f"{context} contains duplicate test id {test_id!r}")
        seen.add(test_id)
        records.append(test)
    return records


def _label(mutant: dict[str, Any], index: int) -> str:
    for field in ("mutant_id", "mutant", "id", "name"):
        value = mutant.get(field)
        if isinstance(value, (str, int)) and str(value):
            return str(value)
    return f"mutant_{index + 1}"


def _load_origin(path: Path) -> TestReport:
    document = _read_json(path)
    report = _report_document(document, f"origin report {path}")
    if report is None:
        raise ReportFormatError(
            f"Origin report {path} must contain 'tests' or a supported nested report"
        )
    return TestReport(None, _test_records(report, f"origin report {path}"))


def _load_mutants(path: Path) -> list[TestReport]:
    document = _read_json(path)
    direct = _report_document(document, f"mutant report {path}")
    if direct is not None:
        return [TestReport(None, _test_records(direct, f"mutant report {path}"))]

    entries = document.get("mutants")
    if isinstance(entries, dict):
        entries = [
            {"mutant_id": mutant_id, **value}
            for mutant_id, value in entries.items()
            if isinstance(value, dict)
        ]
    if not isinstance(entries, list) or not entries:
        raise ReportFormatError(
            f"Mutant report {path} must contain 'tests' or a non-empty 'mutants' list"
        )

    reports: list[TestReport] = []
    for index, entry in enumerate(entries):
        if not isinstance(entry, dict):
            raise ReportFormatError(f"mutant report {path}.mutants[{index}] must be an object")
        report = _report_document(entry, f"mutant report {path}.mutants[{index}]")
        if report is None:
            raise ReportFormatError(
                f"mutant report {path}.mutants[{index}] must contain 'tests' "
                "or a supported nested report"
            )
        reports.append(
            TestReport(
                _label(entry, index),
                _test_records(report, f"mutant report {path}.mutants[{index}]"),
            )
        )
    return reports


def _index_steps(test: dict[str, Any], context: str) -> dict[str, dict[str, Any]]:
    steps = test.get("steps", [])
    if not isinstance(steps, list):
        raise ReportFormatError(f"{context}.steps must be a list")
    indexed: dict[str, dict[str, Any]] = {}
    for index, step in enumerate(steps):
        if not isinstance(step, dict):
            raise ReportFormatError(f"{context}.steps[{index}] must be an object")
        step_id = step.get("id")
        if not isinstance(step_id, str) or not step_id:
            raise ReportFormatError(
                f"{context}.steps[{index}] must have a non-empty string 'id'"
            )
        if step_id in indexed:
            raise ReportFormatError(f"{context} contains duplicate step id {step_id!r}")
        indexed[step_id] = step
    return indexed


def _behaviour_signature(record: dict[str, Any]) -> dict[str, Any]:
    """Keep observable outcome fields and discard report/audit metadata."""
    return {
        field: record[field]
        for field in _BEHAVIOUR_FIELDS
        if field in record
    }


def _compare_report(mutant: TestReport, origin: TestReport) -> dict[str, Any]:
    mutant_tests = {test["id"]: test for test in mutant.tests}
    origin_tests = {test["id"]: test for test in origin.tests}
    shared_test_ids = sorted(set(mutant_tests) & set(origin_tests))
    mutant_only_test_ids = set(mutant_tests) - set(origin_tests)
    origin_only_test_ids = set(origin_tests) - set(mutant_tests)

    tests_compared = len(shared_test_ids)
    steps_compared = 0
    mismatched_tests = len(mutant_only_test_ids)
    mismatched_steps = sum(
        len(_index_steps(mutant_tests[test_id], f"mutant test {test_id}"))
        for test_id in mutant_only_test_ids
    )

    for test_id in shared_test_ids:
        mutant_test = mutant_tests[test_id]
        origin_test = origin_tests[test_id]
        mutant_steps = _index_steps(mutant_test, f"mutant test {test_id}")
        origin_steps = _index_steps(origin_test, f"origin test {test_id}")
        shared_step_ids = sorted(set(mutant_steps) & set(origin_steps))
        mutant_only_step_ids = set(mutant_steps) - set(origin_steps)
        origin_only_step_ids = set(origin_steps) - set(mutant_steps)
        steps_compared += len(shared_step_ids)
        mismatched_steps += len(mutant_only_step_ids) + len(origin_only_step_ids)

        step_mismatches = sum(
            _behaviour_signature(mutant_steps[step_id])
            != _behaviour_signature(origin_steps[step_id])
            for step_id in shared_step_ids
        )
        mismatched_steps += step_mismatches
        test_mismatch = (
            _behaviour_signature(mutant_test)
            != _behaviour_signature(origin_test)
            or bool(mutant_only_step_ids)
            or bool(origin_only_step_ids)
            or step_mismatches > 0
        )
        mismatched_tests += int(test_mismatch)

    same = (
        tests_compared > 0
        and not mutant_only_test_ids
        and mismatched_tests == 0
        and mismatched_steps == 0
    )
    return {
        "same_across_all_inputs": same,
        "tests_compared": tests_compared,
        "steps_compared": steps_compared,
        "mismatched_tests": mismatched_tests,
        "mismatched_steps": mismatched_steps,
        "mutant_only_tests": len(mutant_only_test_ids),
        "origin_only_tests": len(origin_only_test_ids),
    }


def compare_reports(
    mutant_reports: list[TestReport], origin_report: TestReport,
) -> dict[str, Any]:
    """Build the path-free JSON-compatible comparison report."""
    comparisons = [
        _compare_report(mutant_report, origin_report)
        for mutant_report in mutant_reports
    ]
    if len(comparisons) == 1 and mutant_reports[0].label is None:
        return {
            "schema_version": "1.0",
            "kind": "mutant_origin_behavior_comparison",
            "summary": comparisons[0],
        }

    same_count = sum(item["same_across_all_inputs"] for item in comparisons)
    return {
        "schema_version": "1.0",
        "kind": "mutant_origin_behavior_comparison",
        "summary": {
            "same_across_all_inputs": same_count == len(comparisons),
            "mutants_compared": len(comparisons),
            "same_mutants": same_count,
            "different_mutants": len(comparisons) - same_count,
        },
        "mutants": [
            {"mutant": report.label or f"mutant_{index + 1}", **comparison}
            for index, (report, comparison) in enumerate(
                zip(mutant_reports, comparisons)
            )
        ],
    }


def main(argv: list[str] | None = None) -> None:
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument(
        "--mutant-final",
        "--mutant-final-equivalence",
        dest="mutant_final",
        type=Path,
        required=True,
        help="Mutant final_equivalence.json",
    )
    parser.add_argument(
        "--origin-final",
        "--origin-comparison-final",
        dest="origin_final",
        type=Path,
        required=True,
        help="Original-program comparison_final.json",
    )
    parser.add_argument("--output", "-o", type=Path, required=True)
    args = parser.parse_args(argv)

    try:
        origin = _load_origin(args.origin_final)
        mutants = _load_mutants(args.mutant_final)
        result = compare_reports(mutants, origin)
    except ReportFormatError as exc:
        parser.error(str(exc))

    args.output.parent.mkdir(parents=True, exist_ok=True)
    args.output.write_text(
        json.dumps(result, indent=2, ensure_ascii=False) + "\n",
        encoding="utf-8",
    )
    summary = result["summary"]
    if "mutants_compared" in summary:
        message = (
            f"Compared {summary['mutants_compared']} mutants: "
            f"{summary['same_mutants']} same, {summary['different_mutants']} different."
        )
    else:
        message = (
            f"Compared {summary['tests_compared']} shared tests and "
            f"{summary['steps_compared']} shared steps: "
            f"{'same' if summary['same_across_all_inputs'] else 'different'}."
        )
    print(message)
    if not summary["same_across_all_inputs"]:
        raise SystemExit(1)


if __name__ == "__main__":
    main()
