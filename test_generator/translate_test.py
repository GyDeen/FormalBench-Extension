"""Extract semantic program inputs from EvoSuite-generated Java tests.

The extractor intentionally ignores EvoSuite scaffolding and JUnit assertions.
It records only fixture construction and ordered calls to the target program.
"""

from __future__ import annotations

import argparse
import copy
import json
import re
from collections.abc import Sequence
from pathlib import Path
from typing import Any

TEST_METHOD_RE = re.compile(
    r"\bpublic\s+void\s+(?P<name>[A-Za-z_]\w*)\s*\(\s*\)"
    r"\s*throws\s+Throwable\s*\{"
)
DECLARATION_RE = re.compile(
    r"^(?P<type>(?:byte|short|int|long|float|double|boolean|char)"
    r"(?:\s*\[\s*\])*)\s+(?P<name>[A-Za-z_]\w*)\s*=\s*(?P<rhs>.+)$",
    re.DOTALL,
)
ARRAY_ASSIGNMENT_RE = re.compile(
    r"^(?P<name>[A-Za-z_]\w*)(?P<indices>(?:\s*\[[^]]+\])+)$"
)
ARRAY_CREATION_RE = re.compile(
    r"^new\s+(?P<base>byte|short|int|long|float|double|boolean|char)"
    r"(?P<dimensions>(?:\s*\[[^]]*\])+)$",
    re.DOTALL,
)
CAST_NULL_RE = re.compile(
    r"^\(\s*(?P<type>(?:byte|short|int|long|float|double|boolean|char)"
    r"(?:\s*\[\s*\])*)\s*\)\s*null$"
)

TYPE_NAMES = {
    "byte": "int8",
    "short": "int16",
    "int": "int32",
    "long": "int64",
    "float": "float32",
    "double": "float64",
    "boolean": "boolean",
    "char": "char",
}

DEFAULT_VALUES: dict[str, Any] = {
    "byte": 0,
    "short": 0,
    "int": 0,
    "long": 0,
    "float": 0.0,
    "double": 0.0,
    "boolean": False,
    "char": "\u0000",
}


class ExtractionError(ValueError):
    """Raised when an EvoSuite input expression cannot be represented safely."""


def _canonical_type(java_type: str) -> str:
    compact = re.sub(r"\s+", "", java_type)
    dimensions = compact.count("[]")
    base = compact.replace("[]", "")
    if base not in TYPE_NAMES:
        raise ExtractionError(f"Unsupported Java input type: {java_type!r}")
    return TYPE_NAMES[base] + "[]" * dimensions


def _strip_comments(source: str) -> str:
    source = re.sub(r"/\*.*?\*/", " ", source, flags=re.DOTALL)
    return re.sub(r"//[^\n]*", " ", source)


def _matching_delimiter(source: str, start: int, opening: str, closing: str) -> int:
    depth = 0
    quote: str | None = None
    escaped = False
    for index in range(start, len(source)):
        character = source[index]
        if quote:
            if escaped:
                escaped = False
            elif character == "\\":
                escaped = True
            elif character == quote:
                quote = None
            continue
        if character in {'"', "'"}:
            quote = character
        elif character == opening:
            depth += 1
        elif character == closing:
            depth -= 1
            if depth == 0:
                return index
    raise ExtractionError(f"Unmatched {opening!r} in generated Java source")


def _extract_test_methods(source: str) -> list[tuple[str, str]]:
    clean_source = _strip_comments(source)
    methods: list[tuple[str, str]] = []
    for match in TEST_METHOD_RE.finditer(clean_source):
        open_brace = match.end() - 1
        close_brace = _matching_delimiter(clean_source, open_brace, "{", "}")
        methods.append((match.group("name"), clean_source[open_brace + 1 : close_brace]))
    return methods


def _split_statements(body: str) -> list[str]:
    statements: list[str] = []
    start = 0
    quote: str | None = None
    escaped = False
    for index, character in enumerate(body):
        if quote:
            if escaped:
                escaped = False
            elif character == "\\":
                escaped = True
            elif character == quote:
                quote = None
            continue
        if character in {'"', "'"}:
            quote = character
        elif character == ";":
            statement = body[start:index].strip()
            if statement:
                statements.append(statement)
            start = index + 1
    return statements


def _split_top_level(source: str, delimiter: str = ",") -> list[str]:
    parts: list[str] = []
    start = 0
    depths = {"(": 0, "[": 0, "{": 0}
    pairs = {")": "(", "]": "[", "}": "{"}
    quote: str | None = None
    escaped = False
    for index, character in enumerate(source):
        if quote:
            if escaped:
                escaped = False
            elif character == "\\":
                escaped = True
            elif character == quote:
                quote = None
            continue
        if character in {'"', "'"}:
            quote = character
        elif character in depths:
            depths[character] += 1
        elif character in pairs:
            depths[pairs[character]] -= 1
        elif character == delimiter and not any(depths.values()):
            parts.append(source[start:index].strip())
            start = index + 1
    final = source[start:].strip()
    if final:
        parts.append(final)
    return parts


def _strip_outer_parentheses(expression: str) -> str:
    expression = expression.strip()
    while expression.startswith("("):
        try:
            closing = _matching_delimiter(expression, 0, "(", ")")
        except ExtractionError:
            break
        if closing != len(expression) - 1:
            break
        expression = expression[1:-1].strip()
    return expression


def _parse_literal(expression: str) -> tuple[str, Any] | None:
    expression = _strip_outer_parentheses(expression).replace("_", "")
    if expression == "true":
        return "boolean", True
    if expression == "false":
        return "boolean", False
    if expression in {"Integer.MIN_VALUE", "Long.MIN_VALUE"}:
        return ("int32", -(2**31)) if expression.startswith("Integer") else ("int64", -(2**63))
    if expression in {"Integer.MAX_VALUE", "Long.MAX_VALUE"}:
        return ("int32", 2**31 - 1) if expression.startswith("Integer") else ("int64", 2**63 - 1)
    if re.fullmatch(r"[-+]?\d+[lL]", expression):
        return "int64", int(expression[:-1], 10)
    if re.fullmatch(r"[-+]?\d+", expression):
        return "int32", int(expression, 10)
    if re.fullmatch(r"[-+]?(?:\d+\.\d*|\d*\.\d+)(?:[eE][-+]?\d+)?[fF]?", expression):
        value_type = "float32" if expression[-1:] in {"f", "F"} else "float64"
        return value_type, float(expression.rstrip("fF"))
    if re.fullmatch(r"'(?:\\.|[^'\\])'", expression):
        content = expression[1:-1]
        decoded = bytes(content, "utf-8").decode("unicode_escape")
        return "char", decoded
    return None


def _materialize_array(base: str, dimensions: list[str]) -> Any:
    if not dimensions:
        return copy.deepcopy(DEFAULT_VALUES[base])
    if dimensions[0] == "":
        return None
    size_literal = _parse_literal(dimensions[0])
    if size_literal is None or not isinstance(size_literal[1], int):
        raise ExtractionError(f"Array size is not a constant integer: {dimensions[0]!r}")
    size = size_literal[1]
    if size < 0:
        raise ExtractionError(f"Cannot materialize a negative array size: {size}")
    return [_materialize_array(base, dimensions[1:]) for _ in range(size)]


def _parse_array_creation(expression: str) -> tuple[str, Any] | None:
    match = ARRAY_CREATION_RE.fullmatch(expression.strip())
    if not match:
        return None
    dimensions = re.findall(r"\[\s*([^]]*)\s*\]", match.group("dimensions"))
    java_type = match.group("base") + "[]" * len(dimensions)
    return _canonical_type(java_type), _materialize_array(match.group("base"), dimensions)


def _find_target_call(expression: str, target_class: str) -> tuple[str, list[str]] | None:
    match = re.search(
        rf"\b{re.escape(target_class)}\s*\.\s*(?P<method>[A-Za-z_]\w*)\s*\(",
        expression,
    )
    if not match:
        return None
    open_parenthesis = match.end() - 1
    close_parenthesis = _matching_delimiter(expression, open_parenthesis, "(", ")")
    arguments_source = expression[open_parenthesis + 1 : close_parenthesis]
    arguments = _split_top_level(arguments_source) if arguments_source.strip() else []
    return match.group("method"), arguments


def _resolve_reference(
    expression: str, bindings: dict[str, dict[str, Any]]
) -> dict[str, Any]:
    expression = _strip_outer_parentheses(expression)
    cast_null = CAST_NULL_RE.fullmatch(expression)
    if cast_null:
        return {"type": _canonical_type(cast_null.group("type")), "value": None}
    if expression == "null":
        return {"type": "unknown", "value": None}
    literal = _parse_literal(expression)
    if literal:
        return {"type": literal[0], "value": literal[1]}
    if re.fullmatch(r"[A-Za-z_]\w*", expression) and expression in bindings:
        binding = bindings[expression]
        if binding["kind"] == "constant":
            return {"type": binding["type"], "value": binding["value"]}
        return {"type": binding["type"], "ref": binding["id"]}
    raise ExtractionError(f"Unsupported test input expression: {expression!r}")


def _constant_index(expression: str, bindings: dict[str, dict[str, Any]]) -> int:
    resolved = _resolve_reference(expression, bindings)
    if "value" not in resolved or not isinstance(resolved["value"], int):
        raise ExtractionError(f"Array index is not a constant integer: {expression!r}")
    return resolved["value"]


def _fixture_for_binding(
    variable: str,
    bindings: dict[str, dict[str, Any]],
    fixtures: dict[str, dict[str, Any]],
) -> dict[str, Any]:
    binding = bindings.get(variable)
    if not binding or binding["kind"] != "fixture":
        raise ExtractionError(f"Cannot assign through non-fixture variable {variable!r}")
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
        for index in re.findall(r"\[\s*([^]]+)\s*\]", match.group("indices"))
    ]
    resolved = _resolve_reference(right, bindings)
    assigned: Any = (
        {"ref": resolved["ref"]} if "ref" in resolved else resolved["value"]
    )
    container = fixture["value"]
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

    for raw_statement in _split_statements(body):
        statement = re.sub(r"^(?:try\s*\{|\})\s*", "", raw_statement.strip())
        declaration = DECLARATION_RE.fullmatch(statement)
        if declaration:
            java_type = declaration.group("type")
            value_type = _canonical_type(java_type)
            variable = declaration.group("name")
            right = declaration.group("rhs").strip()

            array_creation = _parse_array_creation(right)
            if array_creation:
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
                }
                continue

            call = _find_target_call(right, target_class)
            if call:
                step_id = f"call_{len(steps)}"
                step = {
                    "id": step_id,
                    "class": target_class,
                    "function": call[0],
                    "arguments": [
                        _resolve_reference(argument, bindings) for argument in call[1]
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

            literal = _parse_literal(right)
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
                bindings[variable] = {
                    "kind": original["kind"],
                    "id": original["id"],
                    "type": value_type,
                    **({"value": original["value"]} if "value" in original else {}),
                }
                continue

            # Constructor-only statements use non-primitive types and never match
            # DECLARATION_RE. Any primitive declaration reaching here is unknown.
            raise ExtractionError(
                f"{source_file.name}:{method_name}: unsupported declaration {statement!r}"
            )

        assignment_match = re.fullmatch(r"(?P<left>[A-Za-z_]\w*(?:\s*\[[^]]+\])+?)\s*=\s*(?P<right>.+)", statement, re.DOTALL)
        if assignment_match:
            _assign_array_element(
                assignment_match.group("left"),
                assignment_match.group("right"),
                bindings,
                fixtures,
            )
            continue

        call = _find_target_call(statement, target_class)
        if call:
            steps.append(
                {
                    "id": f"call_{len(steps)}",
                    "class": target_class,
                    "function": call[0],
                    "arguments": [
                        _resolve_reference(argument, bindings) for argument in call[1]
                    ],
                }
            )

    if not steps:
        return None

    used_fixture_ids: set[str] = set()
    for step in steps:
        used_fixture_ids.update(_referenced_fixture_ids(step["arguments"]))
    pending = list(used_fixture_ids)
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
    for method_name, body in _extract_test_methods(source):
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


def parse_args(argv: Sequence[str] | None = None) -> argparse.Namespace:
    parser = argparse.ArgumentParser(
        description="Extract input fixtures and target calls from EvoSuite Java tests."
    )
    parser.add_argument("tests_dir", type=Path, help="Directory containing *_ESTest.java")
    parser.add_argument(
        "--output",
        "-o",
        type=Path,
        help="Output JSON path (prints to standard output when omitted)",
    )
    return parser.parse_args(argv)


def main(argv: Sequence[str] | None = None) -> None:
    args = parse_args(argv)
    document = extract_directory(args.tests_dir)
    rendered = json.dumps(document, indent=2, ensure_ascii=False) + "\n"
    if args.output:
        args.output.parent.mkdir(parents=True, exist_ok=True)
        args.output.write_text(rendered, encoding="utf-8")
        print(
            f"Extracted {len(document['tests'])} semantic tests "
            f"from {document['source']['test_file_count']} files to {args.output}"
        )
    else:
        print(rendered, end="")


if __name__ == "__main__":
    main()
