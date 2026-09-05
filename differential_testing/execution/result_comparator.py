from __future__ import annotations

import math
from typing import Any


class ComparisonInputError(ValueError):
    """Raised when an input or execution-result document is malformed."""


_MISSING = object()


def _index_by_id(records: Any, label: str) -> dict[str, dict[str, Any]]:
    if not isinstance(records, list):
        raise ComparisonInputError(f"{label} must be a JSON array")
    indexed: dict[str, dict[str, Any]] = {}
    for position, record in enumerate(records):
        if not isinstance(record, dict) or not isinstance(record.get("id"), str):
            raise ComparisonInputError(
                f"{label}[{position}] must be an object with a string id"
            )
        record_id = record["id"]
        if record_id in indexed:
            raise ComparisonInputError(f"Duplicate id {record_id!r} in {label}")
        indexed[record_id] = record
    return indexed


def _difference(
    path: str,
    reason: str,
    java_value: Any = _MISSING,
    c_value: Any = _MISSING,
) -> dict[str, Any]:
    difference: dict[str, Any] = {"path": path, "reason": reason}
    if java_value is not _MISSING:
        difference["java"] = java_value
    if c_value is not _MISSING:
        difference["c"] = c_value
    return difference


def _compare_values(
    java_value: Any,
    c_value: Any,
    path: str,
    relative_tolerance: float,
    absolute_tolerance: float,
) -> list[dict[str, Any]]:
    # bool is a subclass of int in Python, so compare it before numeric values.
    if isinstance(java_value, bool) or isinstance(c_value, bool):
        if type(java_value) is not type(c_value) or java_value != c_value:
            return [_difference(path, "values differ", java_value, c_value)]
        return []

    if isinstance(java_value, (int, float)) and isinstance(c_value, (int, float)):
        if isinstance(java_value, float) or isinstance(c_value, float):
            if math.isclose(
                java_value,
                c_value,
                rel_tol=relative_tolerance,
                abs_tol=absolute_tolerance,
            ):
                return []
        elif java_value == c_value:
            return []
        return [_difference(path, "numeric values differ", java_value, c_value)]

    if isinstance(java_value, dict) and isinstance(c_value, dict):
        # Descend recursively so reports identify the exact mismatching field
        # instead of treating a complete structured result as one value.
        differences: list[dict[str, Any]] = []
        all_keys = sorted(set(java_value) | set(c_value))
        for key in all_keys:
            child_path = f"{path}.{key}"
            if key not in java_value:
                differences.append(
                    _difference(child_path, "missing from Java result", c_value=c_value[key])
                )
            elif key not in c_value:
                differences.append(
                    _difference(child_path, "missing from C result", java_value=java_value[key])
                )
            else:
                differences.extend(
                    _compare_values(
                        java_value[key],
                        c_value[key],
                        child_path,
                        relative_tolerance,
                        absolute_tolerance,
                    )
                )
        return differences

    if isinstance(java_value, list) and isinstance(c_value, list):
        differences = []
        common_length = min(len(java_value), len(c_value))
        for index in range(common_length):
            differences.extend(
                _compare_values(
                    java_value[index],
                    c_value[index],
                    f"{path}[{index}]",
                    relative_tolerance,
                    absolute_tolerance,
                )
            )
        if len(java_value) != len(c_value):
            differences.append(
                _difference(
                    path,
                    "array lengths differ",
                    len(java_value),
                    len(c_value),
                )
            )
        return differences

    if type(java_value) is not type(c_value) or java_value != c_value:
        return [_difference(path, "values differ", java_value, c_value)]
    return []


def _compare_optional_field(
    java_step: dict[str, Any],
    c_step: dict[str, Any],
    field: str,
    path: str,
    relative_tolerance: float,
    absolute_tolerance: float,
) -> list[dict[str, Any]]:
    if field not in java_step and field not in c_step:
        return []
    if field not in java_step:
        return [
            _difference(
                f"{path}.{field}",
                "missing from Java result",
                c_value=c_step[field],
            )
        ]
    if field not in c_step:
        return [
            _difference(
                f"{path}.{field}",
                "missing from C result",
                java_value=java_step[field],
            )
        ]
    return _compare_values(
        java_step[field],
        c_step[field],
        f"{path}.{field}",
        relative_tolerance,
        absolute_tolerance,
    )


def _compare_step(
    expected_step: dict[str, Any],
    java_step: dict[str, Any] | None,
    c_step: dict[str, Any] | None,
    relative_tolerance: float,
    absolute_tolerance: float,
) -> dict[str, Any]:
    step_id = expected_step["id"]
    differences: list[dict[str, Any]] = []
    path = f"steps.{step_id}"
    if java_step is None:
        differences.append(_difference(path, "step missing from Java result"))
    if c_step is None:
        differences.append(_difference(path, "step missing from C result"))
    if java_step is None or c_step is None:
        return {
            "id": step_id,
            "status": "mismatch",
            "differences": differences,
        }

    java_status = java_step.get("status")
    c_status = c_step.get("status")
    if not isinstance(java_status, str):
        differences.append(
            _difference(f"{path}.status", "Java result has no string status")
        )
    if not isinstance(c_status, str):
        differences.append(
            _difference(f"{path}.status", "C result has no string status")
        )
    if isinstance(java_status, str) and isinstance(c_status, str):
        if java_status != c_status:
            differences.append(
                _difference(
                    f"{path}.status",
                    "execution statuses differ",
                    java_status,
                    c_status,
                )
            )
        elif java_status == "returned":
            # A declared result is mandatory. For a bare source call, compare a
            # return only when the runners recovered one from its signature.
            if "result" in expected_step:
                differences.extend(
                    _compare_optional_field(
                        java_step,
                        c_step,
                        "return",
                        path,
                        relative_tolerance,
                        absolute_tolerance,
                    )
                )
                if "return" not in java_step and "return" not in c_step:
                    differences.append(
                        _difference(
                            f"{path}.return",
                            "both runners omitted a required return value",
                        )
                    )
            elif "return" in java_step or "return" in c_step:
                differences.extend(
                    _compare_optional_field(
                        java_step,
                        c_step,
                        "return",
                        path,
                        relative_tolerance,
                        absolute_tolerance,
                    )
                )
            differences.extend(
                _compare_optional_field(
                    java_step,
                    c_step,
                    "state_after",
                    path,
                    relative_tolerance,
                    absolute_tolerance,
                )
            )
            if "state_after" not in java_step and "state_after" not in c_step:
                differences.append(
                    _difference(
                        f"{path}.state_after",
                        "both runners omitted required post-call state",
                    )
                )
        else:
            # Diagnostic text is runtime-specific; the canonical error kind is
            # the stable semantic value used for comparison.
            java_error = java_step.get("error", {})
            c_error = c_step.get("error", {})
            java_kind = java_error.get("kind") if isinstance(java_error, dict) else None
            c_kind = c_error.get("kind") if isinstance(c_error, dict) else None
            if java_kind != c_kind:
                differences.append(
                    _difference(
                        f"{path}.error.kind",
                        "canonical error kinds differ",
                        java_kind,
                        c_kind,
                    )
                )

    report = {
        "id": step_id,
        "status": "match" if not differences else "mismatch",
    }
    if differences:
        report["differences"] = differences
    return report


def _validate_document_kind(
    document: dict[str, Any], expected_kind: str, label: str
) -> None:
    if not isinstance(document, dict):
        raise ComparisonInputError(f"{label} must be a JSON object")
    if document.get("kind") != expected_kind:
        raise ComparisonInputError(
            f"{label} kind must be {expected_kind!r}, got {document.get('kind')!r}"
        )


def compare_execution_results(
    inputs: dict[str, Any],
    java_results: dict[str, Any],
    c_results: dict[str, Any],
    *,
    relative_tolerance: float = 1e-9,
    absolute_tolerance: float = 0.0,
) -> dict[str, Any]:
    """Compare normalized Java and C outcomes against an input manifest."""
    if relative_tolerance < 0 or absolute_tolerance < 0:
        raise ComparisonInputError("Numeric tolerances cannot be negative")
    _validate_document_kind(inputs, "evosuite_test_inputs", "input document")
    _validate_document_kind(
        java_results, "test_execution_results", "Java result document"
    )
    _validate_document_kind(
        c_results, "test_execution_results", "C result document"
    )
    if java_results.get("language") != "java":
        raise ComparisonInputError("Java result language must be 'java'")
    if c_results.get("language") != "c":
        raise ComparisonInputError("C result language must be 'c'")

    expected_tests = _index_by_id(inputs.get("tests"), "input tests")
    java_tests = _index_by_id(java_results.get("tests"), "Java tests")
    c_tests = _index_by_id(c_results.get("tests"), "C tests")
    test_reports: list[dict[str, Any]] = []
    matched_steps = 0
    mismatched_steps = 0

    for test_id, expected_test in expected_tests.items():
        java_test = java_tests.get(test_id)
        c_test = c_tests.get(test_id)
        if java_test is None or c_test is None:
            reasons = []
            if java_test is None:
                reasons.append("missing from Java results")
            if c_test is None:
                reasons.append("missing from C results")
            expected_step_count = len(expected_test.get("steps", []))
            mismatched_steps += expected_step_count
            test_reports.append(
                {
                    "id": test_id,
                    "status": "mismatch",
                    "reasons": reasons,
                    "steps": [],
                }
            )
            continue

        expected_steps = _index_by_id(
            expected_test.get("steps"), f"input test {test_id} steps"
        )
        java_steps = _index_by_id(
            java_test.get("steps"), f"Java test {test_id} steps"
        )
        c_steps = _index_by_id(c_test.get("steps"), f"C test {test_id} steps"
        )
        step_reports = [
            _compare_step(
                expected_step,
                java_steps.get(step_id),
                c_steps.get(step_id),
                relative_tolerance,
                absolute_tolerance,
            )
            for step_id, expected_step in expected_steps.items()
        ]
        extra_java_steps = sorted(set(java_steps) - set(expected_steps))
        extra_c_steps = sorted(set(c_steps) - set(expected_steps))
        # Extra records are mismatches too, even when every expected step agrees.
        matched_steps += sum(step["status"] == "match" for step in step_reports)
        mismatched_steps += sum(
            step["status"] == "mismatch" for step in step_reports
        )
        status = (
            "match"
            if all(step["status"] == "match" for step in step_reports)
            and not extra_java_steps
            and not extra_c_steps
            else "mismatch"
        )
        report: dict[str, Any] = {
            "id": test_id,
            "status": status,
            "steps": step_reports,
        }
        if extra_java_steps:
            report["extra_java_steps"] = extra_java_steps
        if extra_c_steps:
            report["extra_c_steps"] = extra_c_steps
        test_reports.append(report)

    extra_java_tests = sorted(set(java_tests) - set(expected_tests))
    extra_c_tests = sorted(set(c_tests) - set(expected_tests))
    matched_tests = sum(test["status"] == "match" for test in test_reports)
    mismatched_tests = len(test_reports) - matched_tests
    overall_match = (
        mismatched_tests == 0 and not extra_java_tests and not extra_c_tests
    )
    return {
        "schema_version": "1.0",
        "kind": "java_c_comparison",
        "summary": {
            "overall_match": overall_match,
            "expected_tests": len(expected_tests),
            "matched_tests": matched_tests,
            "mismatched_tests": mismatched_tests,
            "matched_steps": matched_steps,
            "mismatched_steps": mismatched_steps,
            "missing_java_tests": sorted(set(expected_tests) - set(java_tests)),
            "missing_c_tests": sorted(set(expected_tests) - set(c_tests)),
            "extra_java_tests": extra_java_tests,
            "extra_c_tests": extra_c_tests,
        },
        "tests": test_reports,
    }
