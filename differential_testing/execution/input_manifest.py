"""Loading and validation for differential-testing input manifests."""

from __future__ import annotations

import json
import re
from collections import defaultdict
from pathlib import Path
from typing import Any


class RunnerError(ValueError):
    """Raised when an input manifest cannot be converted into harness code."""


SUPPORTED_TYPES = {"int32", "int32[]", "int32[][]", "float64[]"}


def identifier(value: Any, label: str) -> str:
    """Validate and return an identifier safe to place in generated source."""
    # Manifest identifiers are interpolated into Java and C harnesses, so reject
    # punctuation that could change the generated program.
    if not isinstance(value, str) or not re.fullmatch(
        r"[A-Za-z_$][A-Za-z0-9_$]*", value
    ):
        raise RunnerError(f"Invalid {label}: {value!r}")
    return value


def check_type(value_type: Any) -> str:
    """Validate a type from the shared Java/C JSON vocabulary."""
    if value_type not in SUPPORTED_TYPES:
        raise RunnerError(
            f"Unsupported type {value_type!r}; supported types are "
            f"{', '.join(sorted(SUPPORTED_TYPES))}"
        )
    return value_type


def read_inputs(path: Path) -> dict[str, Any]:
    """Read and validate the top-level input document."""
    try:
        document = json.loads(path.read_text(encoding="utf-8"))
    except json.JSONDecodeError as error:
        raise RunnerError(f"Invalid JSON in {path}: {error}") from error
    if not isinstance(document, dict) or document.get("kind") != "evosuite_test_inputs":
        raise RunnerError("Input file must have kind 'evosuite_test_inputs'")
    if not isinstance(document.get("tests"), list):
        raise RunnerError("Input file must contain a tests array")
    return document


def group_tests_by_class(
    document: dict[str, Any],
) -> dict[str, list[dict[str, Any]]]:
    """Validate test records and group them by their target class."""
    grouped: dict[str, list[dict[str, Any]]] = defaultdict(list)
    for test in document["tests"]:
        steps = test.get("steps")
        if not isinstance(steps, list) or not steps:
            raise RunnerError(f"Test {test.get('id')!r} has no steps")
        class_names = {step.get("class") for step in steps}
        # One generated runner includes one source file; cross-class test
        # sequences would require a different compilation and dispatch model.
        if len(class_names) != 1:
            raise RunnerError(f"Test {test.get('id')!r} calls multiple classes")
        class_name = identifier(next(iter(class_names)), "class name")
        for fixture in test.get("fixtures", []):
            check_type(fixture.get("type"))
            identifier(fixture.get("id"), "fixture id")
        for step in steps:
            identifier(step.get("id"), "step id")
            identifier(step.get("function"), "function name")
            for argument in step.get("arguments", []):
                check_type(argument.get("type"))
            if "result" in step:
                check_type(step["result"].get("type"))
                identifier(step["result"].get("id"), "result id")
        grouped[class_name].append(test)
    return dict(grouped)
