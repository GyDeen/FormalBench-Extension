"""Generation of C harness source from normalized test inputs."""

from __future__ import annotations

import json
from pathlib import Path
from typing import Any

from .input_manifest import RunnerError, identifier

C_TYPES = {
    "int32": "int32_t",
    "int32[]": "JIntArray",
    "int32[][]": "JIntArray2",
    "float64[]": "JDoubleArray",
}

C_PRINT_INT32 = r'''
static void print_int32(int32_t value) {
    printf("%" PRId32, value);
}
'''

C_PRINT_INT32_ARRAY = r'''
static void print_int32_array(JIntArray value) {
    if (jarray_is_null(value)) { fputs("null", stdout); return; }
    fputc('[', stdout);
    for (int32_t i = 0; i < jarray_length(value); i++) {
        if (i != 0) fputc(',', stdout);
        print_int32(jarray_get(value, i));
    }
    fputc(']', stdout);
}
'''

C_PRINT_INT32_MATRIX = r'''
static void print_int32_matrix(JIntArray2 value) {
    if (jarray2_is_null(value)) { fputs("null", stdout); return; }
    fputc('[', stdout);
    for (int32_t i = 0; i < jarray2_length(value); i++) {
        if (i != 0) fputc(',', stdout);
        print_int32_array(jarray2_get(value, i));
    }
    fputc(']', stdout);
}
'''

C_PRINT_DOUBLE_ARRAY = r'''
static void print_double_array(JDoubleArray value) {
    if (jdouble_array_is_null(value)) { fputs("null", stdout); return; }
    fputc('[', stdout);
    for (int32_t i = 0; i < jdouble_array_length(value); i++) {
        if (i != 0) fputc(',', stdout);
        if (isfinite(jdouble_array_get(value, i))) printf("%.17g", jdouble_array_get(value, i));
        else if (isnan(jdouble_array_get(value, i))) fputs("\"NaN\"", stdout);
        else if (jdouble_array_get(value, i) < 0) fputs("\"-Infinity\"", stdout);
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
    return "NULL"


def _fixture_setup(fixtures: list[dict[str, Any]]) -> list[str]:
    lines = [
        f"  {C_TYPES[fixture['type']]} {fixture['id']} = NULL;"
        for fixture in fixtures
    ]
    # Initialize referenced rows before constructing their containing matrix.
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
    return [f"  {name} = jarray_new({len(value)});"] + [
        f"  jarray_set({name}, {index}, {_integer(item)});"
        for index, item in enumerate(value)
    ]


def _double_array_setup(name: str, value: list[float]) -> list[str]:
    return [f"  {name} = jdouble_array_new({len(value)});"] + [
        f"  jdouble_array_set({name}, {index}, {repr(float(item))});"
        for index, item in enumerate(value)
    ]


def _int32_matrix_setup(name: str, value: list[Any]) -> list[str]:
    lines = [f"  {name} = jarray2_new_rows({len(value)});"]
    for index, row in enumerate(value):
        if isinstance(row, dict) and set(row) == {"ref"}:
            row_name = identifier(row["ref"], "matrix row reference")
        else:
            row_name = f"{name}_row_{index}"
            lines.append(f"  JIntArray {row_name} = NULL;")
            if row is not None:
                lines.extend(_int32_array_setup(row_name, row))
        lines.append(f"  jarray2_set({name}, {index}, {row_name});")
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
#include "java_arrays.h"
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
