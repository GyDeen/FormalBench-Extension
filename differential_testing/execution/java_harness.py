"""Generation of Java harness source from normalized test inputs."""

from __future__ import annotations

import json
from typing import Any

from .input_manifest import RunnerError, identifier


JAVA_TYPES = {
    "int32": "int",
    "int32[]": "int[]",
    "int32[][]": "int[][]",
    "float64[]": "double[]",
}

JAVA_SUPPORT = r'''
  static String quote(String value) {
    StringBuilder out = new StringBuilder("\"");
    for (int i = 0; i < value.length(); i++) {
      char ch = value.charAt(i);
      switch (ch) {
        case '\\': out.append("\\\\"); break;
        case '"': out.append("\\\""); break;
        case '\n': out.append("\\n"); break;
        case '\r': out.append("\\r"); break;
        case '\t': out.append("\\t"); break;
        default:
          if (ch < 32) out.append(String.format("\\u%04x", (int) ch));
          else out.append(ch);
      }
    }
    return out.append('"').toString();
  }

  static String jsonIntArray(int[] value) {
    if (value == null) return "null";
    StringBuilder out = new StringBuilder("[");
    for (int i = 0; i < value.length; i++) {
      if (i != 0) out.append(',');
      out.append(value[i]);
    }
    return out.append(']').toString();
  }

  static String jsonIntMatrix(int[][] value) {
    if (value == null) return "null";
    StringBuilder out = new StringBuilder("[");
    for (int i = 0; i < value.length; i++) {
      if (i != 0) out.append(',');
      out.append(jsonIntArray(value[i]));
    }
    return out.append(']').toString();
  }

  static String jsonDoubleArray(double[] value) {
    if (value == null) return "null";
    StringBuilder out = new StringBuilder("[");
    for (int i = 0; i < value.length; i++) {
      if (i != 0) out.append(',');
      double item = value[i];
      if (Double.isFinite(item)) out.append(item);
      else out.append(quote(Double.toString(item)));
    }
    return out.append(']').toString();
  }

  static void returned(String returnValue, String state) {
    System.out.print("{\"status\":\"returned\"");
    if (returnValue != null) System.out.print(",\"return\":" + returnValue);
    System.out.print(",\"state_after\":" + state + "}");
  }

  static String errorKind(Throwable error) {
    if (error instanceof NullPointerException) return "null_dereference";
    if (error instanceof IndexOutOfBoundsException) return "bounds_error";
    if (error instanceof ArithmeticException) return "arithmetic_error";
    if (error instanceof StackOverflowError) return "stack_overflow";
    if (error instanceof OutOfMemoryError) return "resource_exhausted";
    return "exception";
  }

  static void failed(Throwable error) {
    System.out.print("{\"status\":\"error\",\"error\":{\"kind\":"
        + quote(errorKind(error)) + ",\"message\":" + quote(error.toString()) + "}}");
  }
'''


def _integer(value: int) -> str:
    if value == -(2**31):
        return "Integer.MIN_VALUE"
    if value == 2**31 - 1:
        return "Integer.MAX_VALUE"
    return str(value)


def _array_value(value_type: str, value: Any) -> str:
    if value is None:
        return "null"
    if value_type == "int32[]":
        return "new int[]{" + ", ".join(_integer(item) for item in value) + "}"
    if value_type == "float64[]":
        return "new double[]{" + ", ".join(repr(float(item)) for item in value) + "}"
    if value_type == "int32[][]":
        rows = []
        for row in value:
            if isinstance(row, dict) and set(row) == {"ref"}:
                rows.append(identifier(row["ref"], "fixture reference"))
            else:
                rows.append(_array_value("int32[]", row))
        return "new int[][]{" + ", ".join(rows) + "}"
    raise RunnerError(f"Cannot render Java value of type {value_type}")


def _argument(argument: dict[str, Any]) -> str:
    if "ref" in argument:
        return identifier(argument["ref"], "argument reference")
    if argument["type"] == "int32":
        return _integer(argument["value"])
    return _array_value(argument["type"], argument.get("value"))


def _value_json(value_type: str, expression: str) -> str:
    if value_type == "int32":
        return f"Integer.toString({expression})"
    helper = {
        "int32[]": "jsonIntArray",
        "int32[][]": "jsonIntMatrix",
        "float64[]": "jsonDoubleArray",
    }[value_type]
    return f"{helper}({expression})"


def _escape(value: str) -> str:
    return value.replace("\\", "\\\\").replace('"', '\\"')


def _state(fixtures: list[dict[str, Any]]) -> str:
    if not fixtures:
        return '"[]"'
    parts: list[str] = []
    for fixture in fixtures:
        # Build JSON as Java expressions because values may have changed after
        # the target call and therefore cannot be serialized here in Python.
        value_json = _value_json(fixture["type"], fixture["id"])
        prefix = json.dumps(
            {"id": fixture["id"], "type": fixture["type"]},
            separators=(",", ":"),
        )[:-1]
        parts.append(f'"{_escape(prefix)},\\\"value\\\":" + {value_json} + "}}"')
    return '"[" + ' + ' + "," + '.join(parts) + ' + "]"'


def _effective_result(
    step: dict[str, Any], step_index: int, return_types: dict[str, str]
) -> dict[str, str] | None:
    result = step.get("result")
    if result is not None:
        return result
    # Bare EvoSuite calls discard their return value. Recover its type from
    # the source signature so differential comparison still observes it.
    result_type = return_types.get(step["function"])
    if result_type is None:
        return None
    return {"id": f"_result_{step_index}", "type": result_type}


def _test_method(
    class_name: str,
    test: dict[str, Any],
    test_index: int,
    return_types: dict[str, str],
) -> str:
    fixtures = test.get("fixtures", [])
    lines = [f"  static void run{test_index}(int target) {{"]
    for fixture in fixtures:
        lines.append(f"    {JAVA_TYPES[fixture['type']]} {fixture['id']} = null;")
    # Initialize lower-dimensional arrays first so matrix entries can reuse
    # the same Java object and preserve aliasing.
    for fixture in sorted(fixtures, key=lambda item: item["type"].count("[]")):
        value = _array_value(fixture["type"], fixture.get("value"))
        lines.append(f"    {fixture['id']} = {value};")

    state_expression = _state(fixtures)
    # A process targeting a later step replays earlier calls first. This keeps
    # mutations and result dependencies while still isolating crashes/timeouts.
    for step_index, step in enumerate(test["steps"]):
        arguments = ", ".join(_argument(item) for item in step["arguments"])
        invocation = f"{class_name}.{step['function']}({arguments})"
        result = _effective_result(step, step_index, return_types)
        return_expression = "null"
        if result is not None:
            result_id = result["id"]
            lines.append(f"    {JAVA_TYPES[result['type']]} {result_id} = {invocation};")
            value_json = _value_json(result["type"], result_id)
            prefix = _escape(
                json.dumps({"type": result["type"]}, separators=(",", ":"))[:-1]
            )
            return_expression = f'"{prefix},\\\"value\\\":" + {value_json} + "}}"'
        else:
            lines.append(f"    {invocation};")
        lines.append(
            f"    if (target == {step_index}) {{ returned({return_expression}, "
            f"{state_expression}); return; }}"
        )
    lines.append('    throw new IllegalArgumentException("Unknown step");')
    lines.append("  }")
    return "\n".join(lines)


def generate_java_harness(
    class_name: str,
    tests: list[dict[str, Any]],
    return_types: dict[str, str] | None = None,
) -> tuple[str, str]:
    """Return the generated runner class name and Java source."""
    return_types = return_types or {}
    runner_name = f"FormalBenchJavaRunner_{class_name}"
    dispatch = [
        f"      case {index}: run{index}(target); return;"
        for index in range(len(tests))
    ]
    methods = [
        _test_method(class_name, test, index, return_types)
        for index, test in enumerate(tests)
    ]
    source = f'''public class {runner_name} {{
{JAVA_SUPPORT}
  static void dispatch(int test, int target) {{
    switch (test) {{
{chr(10).join(dispatch)}
      default: throw new IllegalArgumentException("Unknown test");
    }}
  }}

  public static void main(String[] args) {{
    try {{
      dispatch(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
    }} catch (Throwable error) {{
      failed(error);
    }}
  }}

{chr(10).join(methods)}
}}
'''
    return runner_name, source
