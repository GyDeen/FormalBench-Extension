"""Generation of C harness source from normalized test inputs."""

from __future__ import annotations

import json
from pathlib import Path
from typing import Any

from .input_manifest import RunnerError, identifier

C_TYPES = {
    "int32": "int32_t",
    "int32[]": "Int32Array",
    "int32[][]": "Int32Matrix",
    "float64[]": "DoubleArray",
}

C_PRINT_INT32 = r'''
static void print_int32(int32_t value) {
    printf("%" PRId32, value);
}
'''

C_PRINT_INT32_ARRAY = r'''
static void print_int32_array(Int32Array value) {
    if (value.data == NULL) { fputs("null", stdout); return; }
    fputc('[', stdout);
    for (size_t i = 0; i < value.length; i++) {
        if (i != 0) fputc(',', stdout);
        print_int32(value.data[i]);
    }
    fputc(']', stdout);
}
'''

C_PRINT_INT32_MATRIX = r'''
static void print_int32_matrix(Int32Matrix value) {
    if (value.rows == NULL) { fputs("null", stdout); return; }
    fputc('[', stdout);
    for (size_t i = 0; i < value.length; i++) {
        if (i != 0) fputc(',', stdout);
        print_int32_array(value.rows[i]);
    }
    fputc(']', stdout);
}
'''

C_PRINT_DOUBLE_ARRAY = r'''
static void print_double_array(DoubleArray value) {
    if (value.data == NULL) { fputs("null", stdout); return; }
    fputc('[', stdout);
    for (size_t i = 0; i < value.length; i++) {
        if (i != 0) fputc(',', stdout);
        if (isfinite(value.data[i])) printf("%.17g", value.data[i]);
        else if (isnan(value.data[i])) fputs("\"NaN\"", stdout);
        else if (value.data[i] < 0) fputs("\"-Infinity\"", stdout);
        else fputs("\"Infinity\"", stdout);
    }
    fputc(']', stdout);
}
'''


def _integer(value: int) -> str:
    if value == -(2**31):
        return "INT32_MIN"
    if value == 2**31 - 1:
        return "INT32_MAX"
    return f"INT32_C({value})"


def _argument(argument: dict[str, Any]) -> str:
    if "ref" in argument:
        return identifier(argument["ref"], "argument reference")
    if argument["type"] == "int32":
        return _integer(argument["value"])
    if argument.get("value") is not None:
        raise RunnerError("Inline non-null C arrays must be represented as fixtures")
    # A NULL storage pointer denotes null; empty arrays have non-NULL storage.
    # The wrapper's length alone cannot distinguish the two.
    return f"({C_TYPES[argument['type']]}){{NULL, 0}}"


def _fixture_setup(fixtures: list[dict[str, Any]]) -> list[str]:
    lines = [
        f"  {C_TYPES[fixture['type']]} {fixture['id']} = {{NULL, 0}};"
        for fixture in fixtures
    ]
    # Referenced rows must be initialized before their containing matrix is
    # copied into an Int32Matrix wrapper.
    ordered = sorted(fixtures, key=lambda item: item["type"].count("[]"))
    for fixture in ordered:
        fixture_id = fixture["id"]
        value = fixture.get("value")
        if value is None:
            continue
        if fixture["type"] == "int32[]":
            lines.extend(_int32_array_setup(fixture_id, value))
        elif fixture["type"] == "float64[]":
            lines.extend(_double_array_setup(fixture_id, value))
        elif fixture["type"] == "int32[][]":
            lines.extend(_int32_matrix_setup(fixture_id, value))
        else:
            raise RunnerError(f"Unsupported fixture type {fixture['type']}")
    return lines


def _int32_array_setup(name: str, value: list[int]) -> list[str]:
    # C has no portable zero-sized stack array. Reserve one unused element for
    # empty arrays so their storage pointer stays distinct from null. The
    # logical length remains zero; this does not provide Java bounds checks.
    items = ", ".join(_integer(item) for item in value) or "0"
    return [
        f"  int32_t {name}_data[] = {{{items}}};",
        f"  {name} = (Int32Array){{{name}_data, {len(value)}}};",
    ]


def _double_array_setup(name: str, value: list[float]) -> list[str]:
    items = ", ".join(repr(float(item)) for item in value) or "0.0"
    return [
        f"  double {name}_data[] = {{{items}}};",
        f"  {name} = (DoubleArray){{{name}_data, {len(value)}}};",
    ]


def _int32_matrix_setup(name: str, value: list[Any]) -> list[str]:
    lines: list[str] = []
    row_names: list[str] = []
    for index, row in enumerate(value):
        if isinstance(row, dict) and set(row) == {"ref"}:
            # Reuse the row wrapper so the C harness mirrors Java aliasing.
            row_names.append(identifier(row["ref"], "matrix row reference"))
            continue
        row_name = f"{name}_row_{index}"
        row_names.append(row_name)
        lines.append(f"  Int32Array {row_name} = {{NULL, 0}};")
        if row is not None:
            lines.extend(_int32_array_setup(row_name, row))
    # Null rows remain null, but an empty row or outer matrix gets non-NULL
    # storage, just like a one-dimensional empty array.
    rows = ", ".join(row_names) or "{NULL, 0}"
    lines.extend(
        [
            f"  Int32Array {name}_rows[] = {{{rows}}};",
            f"  {name} = (Int32Matrix){{{name}_rows, {len(row_names)}}};",
        ]
    )
    return lines


def _print_value(value_type: str, expression: str) -> str:
    return {
        "int32": f"print_int32({expression})",
        "int32[]": f"print_int32_array({expression})",
        "int32[][]": f"print_int32_matrix({expression})",
        "float64[]": f"print_double_array({expression})",
    }[value_type]


def _escape(value: str) -> str:
    return value.replace("\\", "\\\\").replace('"', '\\"')


def _state(fixtures: list[dict[str, Any]]) -> list[str]:
    lines = ['    fputs(",\\\"state_after\\\":[", stdout);']
    for index, fixture in enumerate(fixtures):
        if index:
            lines.append("    fputc(',', stdout);")
        prefix = json.dumps(
            {"id": fixture["id"], "type": fixture["type"]},
            separators=(",", ":"),
        )[:-1]
        lines.append(f'    fputs("{_escape(prefix)},\\\"value\\\":", stdout);')
        # Serialize the actual C representation, including nested null rows,
        # rather than reconstructing nullness from the original input.
        lines.append(f"    {_print_value(fixture['type'], fixture['id'])};")
        lines.append("    fputc('}', stdout);")
    lines.append('    fputs("]}", stdout);')
    return lines


def _support_for(types: set[str]) -> str:
    blocks: list[str] = []
    if types & {"int32", "int32[]", "int32[][]"}:
        blocks.append(C_PRINT_INT32)
    if types & {"int32[]", "int32[][]"}:
        blocks.append(C_PRINT_INT32_ARRAY)
    if "int32[][]" in types:
        blocks.append(C_PRINT_INT32_MATRIX)
    if "float64[]" in types:
        blocks.append(C_PRINT_DOUBLE_ARRAY)
    return "\n".join(blocks)


def _used_types(
    tests: list[dict[str, Any]], return_types: dict[str, str]
) -> set[str]:
    types = {
        item["type"]
        for test in tests
        for item in (
            list(test.get("fixtures", []))
            + [argument for step in test["steps"] for argument in step["arguments"]]
            + [step["result"] for step in test["steps"] if "result" in step]
        )
    }
    types.update(
        return_types[step["function"]]
        for test in tests
        for step in test["steps"]
        if step["function"] in return_types
    )
    return types


def _effective_result(
    step: dict[str, Any], step_index: int, return_types: dict[str, str]
) -> dict[str, str] | None:
    result = step.get("result")
    if result is not None:
        return result
    # Capture values from bare EvoSuite calls when the Java signature reveals
    # a return type, rather than silently losing useful comparison data.
    result_type = return_types.get(step["function"])
    if result_type is None:
        return None
    return {"id": f"_result_{step_index}", "type": result_type}


def _test_function(
    test: dict[str, Any], test_index: int, return_types: dict[str, str]
) -> str:
    fixtures = test.get("fixtures", [])
    lines = [f"static void run_{test_index}(int target) {{"]
    lines.extend(_fixture_setup(fixtures))
    # Replaying the prefix of the test preserves mutations and dependent call
    # results; the outer process runner isolates each requested target step.
    for step_index, step in enumerate(test["steps"]):
        arguments = ", ".join(_argument(item) for item in step["arguments"])
        invocation = f"{step['function']}({arguments})"
        result = _effective_result(step, step_index, return_types)
        if result is not None:
            lines.append(
                f"  {C_TYPES[result['type']]} {result['id']} = {invocation};"
            )
        else:
            lines.append(f"  (void){invocation};")
        lines.extend(_outcome_block(step_index, result, fixtures))
    lines.append("}")
    return "\n".join(lines)


def _outcome_block(
    step_index: int,
    result: dict[str, str] | None,
    fixtures: list[dict[str, Any]],
) -> list[str]:
    lines = [
        f"  if (target == {step_index}) {{",
        '    fputs("{\\\"status\\\":\\\"returned\\\"", stdout);',
    ]
    if result is not None:
        prefix = _escape(
            json.dumps({"type": result["type"]}, separators=(",", ":"))[:-1]
        )
        lines.extend(
            [
                f'    fputs(",\\\"return\\\":{prefix},\\\"value\\\":", stdout);',
                f"    {_print_value(result['type'], result['id'])};",
                "    fputc('}', stdout);",
            ]
        )
    lines.extend(_state(fixtures))
    lines.extend(["    return;", "  }"])
    return lines


def generate_c_harness(
    source_path: Path,
    tests: list[dict[str, Any]],
    return_types: dict[str, str] | None = None,
) -> str:
    """Return a C runner that includes and invokes one translated source."""
    return_types = return_types or {}
    functions = [
        _test_function(test, index, return_types)
        for index, test in enumerate(tests)
    ]
    dispatch = [
        f"        case {index}: run_{index}(target); return 0;"
        for index in range(len(tests))
    ]
    include_path = _escape(str(source_path.resolve()))
    return f'''#include <inttypes.h>
#include <math.h>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include "{include_path}"

{_support_for(_used_types(tests, return_types))}

{chr(10).join(functions)}

int main(int argc, char **argv) {{
    if (argc != 3) return 2;
    int test = atoi(argv[1]);
    int target = atoi(argv[2]);
    switch (test) {{
{chr(10).join(dispatch)}
        default: return 2;
    }}
}}
'''
