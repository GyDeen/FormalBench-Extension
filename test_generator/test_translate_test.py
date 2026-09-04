"""Tests for EvoSuite input extraction."""

from __future__ import annotations

import tempfile
import unittest
from pathlib import Path

from test_generator.input_extractor import extract_file

SAMPLE = """
public class Demo_ESTest extends Demo_ESTest_scaffolding {
  public void test0() throws Throwable {
    int[][] intArray0 = new int[1][2];
    int[] intArray1 = new int[3];
    intArray1[0] = 7;
    intArray0[0] = intArray1;
    int int0 = Demo.run(intArray0, intArray0, 2);
    Demo.followUp(int0);
  }

  public void test1() throws Throwable {
    Demo.run((int[][]) null, (int[][]) null, (-1));
  }

  public void test2() throws Throwable {
    Demo demo0 = new Demo();
  }
}
"""


class ExtractInputsTest(unittest.TestCase):
    def test_arrays_aliases_null_and_dependent_calls(self) -> None:
        with tempfile.TemporaryDirectory() as directory:
            path = Path(directory) / "Demo_ESTest.java"
            path.write_text(SAMPLE, encoding="utf-8")
            tests = extract_file(path)

        self.assertEqual([test["id"] for test in tests], [
            "Demo_ESTest.test0",
            "Demo_ESTest.test1",
        ])
        first = tests[0]
        self.assertEqual(first["fixtures"][1]["value"], [7, 0, 0])
        self.assertEqual(first["fixtures"][0]["value"], [{"ref": "intArray1"}])
        self.assertEqual(
            first["steps"][0]["arguments"][:2],
            [
                {"type": "int32[][]", "ref": "intArray0"},
                {"type": "int32[][]", "ref": "intArray0"},
            ],
        )
        self.assertEqual(
            first["steps"][1]["arguments"],
            [{"type": "int32", "ref": "int0"}],
        )
        self.assertEqual(
            tests[1]["steps"][0]["arguments"][0],
            {"type": "int32[][]", "value": None},
        )


if __name__ == "__main__":
    unittest.main()
