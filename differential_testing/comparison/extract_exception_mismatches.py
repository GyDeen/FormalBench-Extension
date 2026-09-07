"""Select mismatching Java error records without interpreting error kinds."""

from __future__ import annotations

import argparse
from copy import deepcopy
from pathlib import Path
from typing import Any

from .compare_java_c import _read_json
from .result_comparator import _index_by_id


def extract_exception_mismatches(
    comparison: dict[str, Any],
    java_results: dict[str, Any],
    c_results: dict[str, Any],
    inputs: dict[str, Any] | None = None,
) -> list[dict[str, Any]]:
    java_tests = _index_by_id(java_results.get("tests"))
    c_tests = _index_by_id(c_results.get("tests"))
    input_tests = _index_by_id(inputs.get("tests")) if inputs else {}
    cases = []
    for test in comparison["tests"]:
        if test["status"] != "mismatch":
            continue
        test_id = test["id"]
        java_steps = _index_by_id(java_tests.get(test_id, {}).get("steps", []))
        c_steps = _index_by_id(c_tests.get(test_id, {}).get("steps", []))
        input_test = input_tests.get(test_id, {})
        input_steps = _index_by_id(input_test.get("steps", []))
        for step in test["steps"]:
            step_id = step["id"]
            java_step = java_steps.get(step_id)
            if step["status"] != "mismatch" or not java_step or java_step.get("status") != "error":
                continue
            case = {
                "test": test_id,
                "step": step_id,
                "arguments": java_step.get("arguments", input_steps.get(step_id, {}).get("arguments")),
                "java": java_step,
                "c_normal": c_steps.get(step_id),
            }
            if input_test:
                # Keep references and replay context; initial fixtures need not
                # equal actual arguments after earlier calls mutate them.
                case["input_test"] = input_test
            cases.append(deepcopy(case))
    return cases


def main() -> None:
    parser = argparse.ArgumentParser(description=__doc__)
    for name in ("comparison", "java-results", "c-results", "inputs", "output"):
        parser.add_argument(f"--{name}", type=Path, required=True)
    args = parser.parse_args()
    cases = extract_exception_mismatches(
        _read_json(args.comparison), _read_json(args.java_results),
        _read_json(args.c_results), _read_json(args.inputs),
    )
    from .sanitizer_report import write_json
    write_json(args.output, {"cases": cases}, exclusive=True)


if __name__ == "__main__":
    main()
