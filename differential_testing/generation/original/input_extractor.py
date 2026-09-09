"""Build input-only JSON records from parsed EvoSuite Java tests."""

from __future__ import annotations

import re
from pathlib import Path
from typing import Any

if __package__:
    from .java_input_parser import (
        ExtractionError,
        canonical_type,
        extract_test_methods,
        find_target_call,
        parse_array_creation,
        parse_literal,
        split_statements,
        strip_outer_parentheses,
    )
else:
    from java_input_parser import (
        ExtractionError,
        canonical_type,
        extract_test_methods,
        find_target_call,
        parse_array_creation,
        parse_literal,
        split_statements,
        strip_outer_parentheses,
    )


DECLARATION_RE = re.compile(
    r"^(?P<type>(?:byte|short|int|long|float|double|boolean|char)"
    r"(?:\s*\[\s*\])*)\s+(?P<name>[A-Za-z_]\w*)\s*=\s*(?P<rhs>.+)$",
    re.DOTALL,
)
ARRAY_ASSIGNMENT_RE = re.compile(
    r"^(?P<name>[A-Za-z_]\w*)(?P<indices>(?:\s*\[[^]]+\])+)$"
)
CAST_NULL_RE = re.compile(
    r"^\(\s*(?P<type>(?:byte|short|int|long|float|double|boolean|char)"
    r"(?:\s*\[\s*\])*)\s*\)\s*null$"
)


def _resolve_reference(
    expression: str, bindings: dict[str, dict[str, Any]]
) -> dict[str, Any]:
    """Resolve potential references (using local variables) in Evosuite test input expressions"""
    expression = strip_outer_parentheses(expression)
    cast_null = CAST_NULL_RE.fullmatch(expression)
    if cast_null:
        return {"type": canonical_type(cast_null.group("type")), "value": None}
    if expression == "null":
        return {"type": "unknown", "value": None}
    literal = parse_literal(expression)
    if literal:
        return {"type": literal[0], "value": literal[1]}
    if re.fullmatch(r"[A-Za-z_]\w*", expression) and expression in bindings:
        binding = bindings[expression]
        if binding["kind"] == "constant":
            return {"type": binding["type"], "value": binding["value"]}
        # Keep fixture and result references instead of copying their values.
        # The execution harness can then reproduce Java array aliasing.
        return {"type": binding["type"], "ref": binding["id"]}

    # An element expression such as values[0] is an input value, not an array
    # fixture. Resolve it now because generated harnesses do not interpret Java.
    array_access = re.fullmatch(
        r"(?P<name>[A-Za-z_]\w*)(?P<indices>(?:\s*\[[^]]+\])+)", expression
    )
    if array_access and array_access.group("name") in bindings:
        binding = bindings[array_access.group("name")]
        if "value" not in binding:
            raise ExtractionError(
                f"Cannot statically resolve array access: {expression!r}"
            )
        value = binding["value"]
        indices = re.findall(r"\[\s*([^]]+)\s*\]", array_access.group("indices"))
        for index_expression in indices:
            index = _constant_index(index_expression, bindings)
            # A matrix row may alias another fixture rather than own a copy.
            if isinstance(value, dict) and set(value) == {"ref"}:
                value = bindings[value["ref"]]["value"]
            value = value[index]
        value_type = binding["type"]
        for _ in indices:
            value_type = value_type.removesuffix("[]")
        if isinstance(value, dict) and set(value) == {"ref"}:
            return {"type": value_type, "ref": value["ref"]}
        return {"type": value_type, "value": value}
    raise ExtractionError(f"Unsupported test input expression: {expression!r}")


def _constant_index(expression: str, bindings: dict[str, dict[str, Any]]) -> int:
    resolved = _resolve_reference(expression, bindings)
    if "value" not in resolved or not isinstance(resolved["value"], int):
        raise ExtractionError(
            f"Array index is not a constant integer: {expression!r}"
        )
    return resolved["value"]


def _fixture_for_binding(
    variable: str,
    bindings: dict[str, dict[str, Any]],
    fixtures: dict[str, dict[str, Any]],
) -> dict[str, Any]:
    binding = bindings.get(variable)
    if not binding or binding["kind"] != "fixture":
        raise ExtractionError(
            f"Cannot assign through non-fixture variable {variable!r}"
        )
    return fixtures[binding["id"]]


def _assign_array_element(
    left: str,
    right: str,
    bindings: dict[str, dict[str, Any]],
    fixtures: dict[str, dict[str, Any]],
) -> None:
    match = ARRAY_ASSIGNMENT_RE.fullmatch(left.strip())
    if not match:
        raise ExtractionError(f"Unsupported assignment target: {left!r}")
    fixture = _fixture_for_binding(match.group("name"), bindings, fixtures)
    indices = [
        _constant_index(index, bindings)
        for index in re.findall(
            r"\[\s*([^]]+)\s*\]", match.group("indices")
        )
    ]
    resolved = _resolve_reference(right, bindings)
    assigned: Any = (
        {"ref": resolved["ref"]} if "ref" in resolved else resolved["value"]
    )
    container = fixture["value"]
    # Walk to the owning nested array while following any aliased rows.
    for index in indices[:-1]:
        container = container[index]
        if isinstance(container, dict) and set(container) == {"ref"}:
            container = fixtures[container["ref"]]["value"]
    container[indices[-1]] = assigned


def _referenced_fixture_ids(value: Any) -> set[str]:
    if isinstance(value, dict):
        result = {value["ref"]} if "ref" in value else set()
        for nested in value.values():
            result.update(_referenced_fixture_ids(nested))
        return result
    if isinstance(value, list):
        result: set[str] = set()
        for nested in value:
            result.update(_referenced_fixture_ids(nested))
        return result
    return set()


def _extract_one_test(
    source_file: Path,
    test_class: str,
    target_class: str,
    method_name: str,
    body: str,
) -> dict[str, Any] | None:
    bindings: dict[str, dict[str, Any]] = {}
    fixtures: dict[str, dict[str, Any]] = {}
    fixture_order: list[str] = []
    steps: list[dict[str, Any]] = []

    for raw_statement in split_statements(body):
        statement = re.sub(
            r"^(?:try\s*\{|\})\s*", "", raw_statement.strip()
        )
        declaration = DECLARATION_RE.fullmatch(statement)
        if declaration:
            java_type = declaration.group("type")
            value_type = canonical_type(java_type)
            variable = declaration.group("name")
            right = declaration.group("rhs").strip()

            array_creation = parse_array_creation(right)
            if array_creation:
                # Arrays remain named fixtures because later calls may mutate
                # them or pass the same object through multiple references.
                fixture = {
                    "id": variable,
                    "type": array_creation[0],
                    "value": array_creation[1],
                }
                fixtures[variable] = fixture
                fixture_order.append(variable)
                bindings[variable] = {
                    "kind": "fixture",
                    "id": variable,
                    "type": array_creation[0],
                    "value": fixture["value"],
                }
                continue

            if right == "null" and value_type.endswith("[]"):
                # A named null array still needs a fixture so direct uses of
                # that variable retain its declared array type.
                fixture = {"id": variable, "type": value_type, "value": None}
                fixtures[variable] = fixture
                fixture_order.append(variable)
                bindings[variable] = {
                    "kind": "fixture",
                    "id": variable,
                    "type": value_type,
                    "value": None,
                }
                continue

            call = find_target_call(right, target_class)
            if call:
                # Store the result binding so later calls can consume the
                # earlier return value without evaluating it during extraction.
                step_id = f"call_{len(steps)}"
                step = {
                    "id": step_id,
                    "class": target_class,
                    "function": call[0],
                    "arguments": [
                        _resolve_reference(argument, bindings)
                        for argument in call[1]
                    ],
                    "result": {"id": variable, "type": value_type},
                }
                steps.append(step)
                bindings[variable] = {
                    "kind": "result",
                    "id": variable,
                    "type": value_type,
                    "step": step_id,
                }
                continue

            literal = parse_literal(right)
            if literal:
                bindings[variable] = {
                    "kind": "constant",
                    "id": variable,
                    "type": value_type,
                    "value": literal[1],
                }
                continue

            if right in bindings:
                original = bindings[right]
                # Rebinding preserves the original identity for arrays and
                # earlier call results rather than creating a second fixture.
                bindings[variable] = {
                    "kind": original["kind"],
                    "id": original["id"],
                    "type": value_type,
                    **(
                        {"value": original["value"]}
                        if "value" in original
                        else {}
                    ),
                }
                continue

            raise ExtractionError(
                f"{source_file.name}:{method_name}: "
                f"unsupported declaration {statement!r}"
            )

        assignment_match = re.fullmatch(
            r"(?P<left>[A-Za-z_]\w*(?:\s*\[[^]]+\])+?)\s*="
            r"\s*(?P<right>.+)",
            statement,
            re.DOTALL,
        )
        if assignment_match:
            _assign_array_element(
                assignment_match.group("left"),
                assignment_match.group("right"),
                bindings,
                fixtures,
            )
            continue

        call = find_target_call(statement, target_class)
        if call:
            steps.append(
                {
                    "id": f"call_{len(steps)}",
                    "class": target_class,
                    "function": call[0],
                    "arguments": [
                        _resolve_reference(argument, bindings)
                        for argument in call[1]
                    ],
                }
            )

    if not steps:
        return None

    used_fixture_ids: set[str] = set()
    for step in steps:
        used_fixture_ids.update(_referenced_fixture_ids(step["arguments"]))
    pending = list(used_fixture_ids)
    # Include the transitive fixture closure: a used matrix may reference a
    # row fixture that never appears directly in a method argument.
    while pending:
        fixture_id = pending.pop()
        fixture = fixtures.get(fixture_id)
        if not fixture:
            continue
        nested = _referenced_fixture_ids(fixture["value"]) - used_fixture_ids
        used_fixture_ids.update(nested)
        pending.extend(nested)

    return {
        "id": f"{test_class}.{method_name}",
        "source_file": source_file.name,
        "fixtures": [
            fixtures[fixture_id]
            for fixture_id in fixture_order
            if fixture_id in used_fixture_ids
        ],
        "steps": steps,
    }


def extract_file(path: Path) -> list[dict[str, Any]]:
    """Extract input-only test records from one EvoSuite test file."""
    if path.name.endswith("_scaffolding.java"):
        return []
    test_class = path.stem
    if not test_class.endswith("_ESTest"):
        raise ExtractionError(f"Not an EvoSuite test file: {path}")
    target_class = test_class.removesuffix("_ESTest")
    source = path.read_text(encoding="utf-8")
    extracted: list[dict[str, Any]] = []
    for method_name, body in extract_test_methods(source):
        test = _extract_one_test(
            path, test_class, target_class, method_name, body
        )
        if test:
            extracted.append(test)
    return extracted


def extract_directory(path: Path) -> dict[str, Any]:
    """Extract all semantic program inputs from an EvoSuite output directory."""
    if not path.is_dir():
        raise FileNotFoundError(f"EvoSuite test directory not found: {path}")
    files = sorted(path.glob("*_ESTest.java"))
    tests = [test for source_file in files for test in extract_file(source_file)]
    return {
        "schema_version": "1.0",
        "kind": "evosuite_test_inputs",
        "source": {
            "directory": str(path.resolve()),
            "test_file_count": len(files),
        },
        "tests": tests,
    }
