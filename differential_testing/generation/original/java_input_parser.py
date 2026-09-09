from __future__ import annotations

import copy
import re
from typing import Any

TEST_METHOD_RE = re.compile(
    r"\bpublic\s+void\s+(?P<name>[A-Za-z_]\w*)\s*\(\s*\)"
    r"\s*throws\s+Throwable\s*\{"
)
ARRAY_CREATION_RE = re.compile(
    r"^new\s+(?P<base>byte|short|int|long|float|double|boolean|char)"
    r"(?P<dimensions>(?:\s*\[[^]]*\])+)$",
    re.DOTALL,
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


def canonical_type(java_type: str) -> str:
    """Convert a supported Java primitive type to the JSON type vocabulary."""
    compact = re.sub(r"\s+", "", java_type)
    dimensions = compact.count("[]")
    base = compact.replace("[]", "")
    if base not in TYPE_NAMES:
        raise ExtractionError(f"Unsupported Java input type: {java_type!r}")
    return TYPE_NAMES[base] + "[]" * dimensions


def _strip_comments(source: str) -> str:
    source = re.sub(r"/\*.*?\*/", " ", source, flags=re.DOTALL)
    return re.sub(r"//[^\n]*", " ", source)


def matching_delimiter(
    source: str, start: int, opening: str, closing: str
) -> int:
    """Find a matching delimiter without counting delimiters inside literals."""
    depth = 0
    quote: str | None = None
    escaped = False
    for index in range(start, len(source)):
        character = source[index]
        # Delimiters inside string and character literals are data, not syntax.
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


def extract_test_methods(source: str) -> list[tuple[str, str]]:
    """Return each EvoSuite test method name and body."""
    clean_source = _strip_comments(source)
    methods: list[tuple[str, str]] = []
    for match in TEST_METHOD_RE.finditer(clean_source):
        open_brace = match.end() - 1
        close_brace = matching_delimiter(clean_source, open_brace, "{", "}")
        body = clean_source[open_brace + 1 : close_brace]
        methods.append((match.group("name"), body))
    return methods


def split_statements(body: str) -> list[str]:
    """Split the simple semicolon-terminated statements emitted by EvoSuite."""
    statements: list[str] = []
    start = 0
    quote: str | None = None
    escaped = False
    for index, character in enumerate(body):
        # EvoSuite statements are simple enough to split on semicolons once
        # quoted semicolons have been excluded.
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
        # Only commas outside nested calls, arrays, and literals separate
        # arguments in the target call.
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


def strip_outer_parentheses(expression: str) -> str:
    """Remove redundant parentheses surrounding a complete expression."""
    expression = expression.strip()
    while expression.startswith("("):
        try:
            closing = matching_delimiter(expression, 0, "(", ")")
        except ExtractionError:
            break
        if closing != len(expression) - 1:
            break
        expression = expression[1:-1].strip()
    return expression


def parse_literal(expression: str) -> tuple[str, Any] | None:
    """Parse a supported primitive Java literal."""
    expression = strip_outer_parentheses(expression)
    if expression == "true":
        return "boolean", True
    if expression == "false":
        return "boolean", False
    if expression in {"Integer.MIN_VALUE", "Long.MIN_VALUE"}:
        if expression.startswith("Integer"):
            return "int32", -(2**31)
        return "int64", -(2**63)
    if expression in {"Integer.MAX_VALUE", "Long.MAX_VALUE"}:
        if expression.startswith("Integer"):
            return "int32", 2**31 - 1
        return "int64", 2**63 - 1
    # Remove Java's numeric separators only after checking named constants;
    # otherwise Integer.MAX_VALUE would become an unrecognizable identifier.
    expression = expression.replace("_", "")
    if re.fullmatch(r"[-+]?\d+[lL]", expression):
        return "int64", int(expression[:-1], 10)
    if re.fullmatch(r"[-+]?\d+", expression):
        return "int32", int(expression, 10)
    float_pattern = r"[-+]?(?:\d+\.\d*|\d*\.\d+)(?:[eE][-+]?\d+)?[fF]?"
    if re.fullmatch(float_pattern, expression):
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
        # An omitted inner dimension, as in new int[2][], starts as null.
        return None
    size_literal = parse_literal(dimensions[0])
    if size_literal is None or not isinstance(size_literal[1], int):
        raise ExtractionError(
            f"Array size is not a constant integer: {dimensions[0]!r}"
        )
    size = size_literal[1]
    if size < 0:
        raise ExtractionError(f"Cannot materialize a negative array size: {size}")
    return [_materialize_array(base, dimensions[1:]) for _ in range(size)]


def parse_array_creation(expression: str) -> tuple[str, Any] | None:
    """Parse and materialize a primitive Java array allocation."""
    match = ARRAY_CREATION_RE.fullmatch(expression.strip())
    if not match:
        return None
    dimensions = re.findall(r"\[\s*([^]]*)\s*\]", match.group("dimensions"))
    java_type = match.group("base") + "[]" * len(dimensions)
    value = _materialize_array(match.group("base"), dimensions)
    return canonical_type(java_type), value


def find_target_call(
    expression: str, target_class: str
) -> tuple[str, list[str]] | None:
    """Find a static call to the target class and split its arguments."""
    match = re.search(
        rf"\b{re.escape(target_class)}\s*\.\s*(?P<method>[A-Za-z_]\w*)\s*\(",
        expression,
    )
    if not match:
        return None
    open_parenthesis = match.end() - 1
    close_parenthesis = matching_delimiter(
        expression, open_parenthesis, "(", ")"
    )
    arguments_source = expression[open_parenthesis + 1 : close_parenthesis]
    # Split after locating the matching parenthesis so nested calls do not
    # accidentally contribute arguments to this call.
    arguments = (
        _split_top_level(arguments_source) if arguments_source.strip() else []
    )
    return match.group("method"), arguments
