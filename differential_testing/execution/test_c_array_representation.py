"""Regression checks for null versus empty at the C call and JSON boundaries."""

from __future__ import annotations

import shutil
import tempfile
import unittest
from pathlib import Path

from differential_testing.execution.run_java_c import run_all


JAVA_SOURCE = """
class Arrays {
  public static int[] echoInt(int[] value) { return value; }
  public static double[] echoDouble(double[] value) { return value; }
  public static int[][] echoMatrix(int[][] value) { return value; }
  public static int kindInt(int[] value) { return value == null ? -1 : value.length; }
  public static int kindDouble(double[] value) { return value == null ? -1 : value.length; }
  public static int kindMatrix(int[][] value) { return value == null ? -1 : value.length; }
  public static int length(int[] value) { return value.length; }
  public static int mutate(int[][] value) {
    value[0][0]++;
    return value[1][0];
  }
}
"""

C_SOURCE = """
#include <stddef.h>
#include <stdint.h>
typedef struct { int32_t *data; size_t length; } Int32Array;
typedef struct { double *data; size_t length; } DoubleArray;
typedef struct { Int32Array *rows; size_t length; } Int32Matrix;
Int32Array echoInt(Int32Array value) { return value; }
DoubleArray echoDouble(DoubleArray value) { return value; }
Int32Matrix echoMatrix(Int32Matrix value) { return value; }
int32_t kindInt(Int32Array value) { return value.data == NULL ? -1 : value.length; }
int32_t kindDouble(DoubleArray value) { return value.data == NULL ? -1 : value.length; }
int32_t kindMatrix(Int32Matrix value) { return value.rows == NULL ? -1 : value.length; }
int32_t length(Int32Array value) { return value.length; }
int32_t mutate(Int32Matrix value) {
  value.rows[0].data[0]++;
  return value.rows[1].data[0];
}
"""


def call(function, argument, result_type=None):
    step = {
        "id": "call_0", "class": "Arrays", "function": function,
        "arguments": [argument],
    }
    if result_type:
        step["result"] = {"id": "result", "type": result_type}
    return step


@unittest.skipUnless(
    shutil.which("java") and shutil.which("javac") and shutil.which("cc"),
    "Java and C toolchains are required",
)
class CArrayRepresentationTest(unittest.TestCase):
    def test_null_empty_nested_rows_and_result_references(self):
        tests = []
        expected = []
        for value_type, suffix, populated in (
            ("int32[]", "Int", [2, 3]),
            ("float64[]", "Double", [1.5, -2.0]),
            ("int32[][]", "Matrix", [None, [], [2, 3]]),
        ):
            for value in (None, [], populated):
                fixture = {"id": "value", "type": value_type, "value": value}
                echo = call("echo" + suffix, {"type": value_type, "ref": "value"}, value_type)
                kind = call("kind" + suffix, {"type": value_type, "ref": "result"})
                kind["id"] = "call_1"
                tests.append({"id": f"Arrays.test{len(tests)}", "fixtures": [fixture], "steps": [echo, kind]})
                expected.append((value, -1 if value is None else len(value)))
            # Inline null takes a different generator path from a null fixture.
            tests.append({
                "id": f"Arrays.test{len(tests)}", "fixtures": [],
                "steps": [call("echo" + suffix, {"type": value_type, "value": None})],
            })
            expected.append((None, None))

        java, c = self.run_inputs(tests)
        for index, (value, kind) in enumerate(expected):
            with self.subTest(test=index):
                for results in (java, c):
                    steps = results["tests"][index]["steps"]
                    self.assertEqual(steps[0]["status"], "returned", steps[0])
                    self.assertEqual(steps[0]["return"]["value"], value)
                    if kind is not None:
                        self.assertEqual(steps[0]["state_after"][0]["value"], value)
                        self.assertEqual(steps[1]["status"], "returned", steps[1])
                        self.assertEqual(steps[1]["return"]["value"], kind)

    def test_matrix_row_references_preserve_aliasing_and_nullness(self):
        fixtures = [
            {"id": "matrix", "type": "int32[][]", "value": [
                {"ref": "row"}, {"ref": "row"}, {"ref": "empty"}, {"ref": "missing"},
            ]},
            {"id": "row", "type": "int32[]", "value": [4]},
            {"id": "empty", "type": "int32[]", "value": []},
            {"id": "missing", "type": "int32[]", "value": None},
        ]
        java, c = self.run_inputs([{
            "id": "Arrays.alias", "fixtures": fixtures,
            "steps": [call("mutate", {"type": "int32[][]", "ref": "matrix"})],
        }])
        for results in (java, c):
            step = results["tests"][0]["steps"][0]
            self.assertEqual(step["status"], "returned", step)
            self.assertEqual(step["return"]["value"], 5)
            self.assertEqual([item["value"] for item in step["state_after"]],
                             [[[5], [5], [], None], [5], [], None])

    def test_representation_does_not_invent_null_dereference_errors(self):
        # Changing input representation must not replace execution with a
        # blanket error for null arguments: C still permits reading this field.
        java, c = self.run_inputs([{
            "id": "Arrays.length", "fixtures": [],
            "steps": [call("length", {"type": "int32[]", "value": None})],
        }])
        self.assertEqual(java["tests"][0]["steps"][0]["error"]["kind"], "null_dereference")
        self.assertEqual(c["tests"][0]["steps"][0]["return"]["value"], 0)

    def run_inputs(self, tests):
        with tempfile.TemporaryDirectory() as temporary:
            root = Path(temporary)
            java_dir, c_dir = root / "java", root / "c"
            java_dir.mkdir()
            c_dir.mkdir()
            (java_dir / "Arrays.java").write_text(JAVA_SOURCE, encoding="utf-8")
            (c_dir / "Arrays.c").write_text(C_SOURCE, encoding="utf-8")
            return run_all({"kind": "evosuite_test_inputs", "tests": tests},
                           java_dir, c_dir, root / "build", 2.0)


if __name__ == "__main__":
    unittest.main()
