#!/usr/bin/env python3
"""Run the production runtime cases against the generated expanded baseline."""

from __future__ import annotations

import subprocess
import tempfile
from pathlib import Path


WORKSPACE = Path(__file__).resolve().parents[1]
PROJECT = WORKSPACE.parents[1]
RUNTIME = PROJECT / "runtime" / "java_arrays"
BASELINE = WORKSPACE / "generated" / "baseline.c"

ERROR_CASES = {
    71: ["null_length", "null_get", "null_set", "double_null", "outer_null", "null_row"],
    72: ["negative_get", "upper_get", "upper_set", "empty_get", "double_bounds", "outer_bounds"],
    73: ["negative_size", "double_size", "negative_rows", "negative_columns"],
}


def run(binary: Path, case: str, expected: int) -> None:
    result = subprocess.run([str(binary), case], capture_output=True, text=True, check=False)
    if result.returncode != expected:
        raise RuntimeError(
            f"{case}: expected exit {expected}, got {result.returncode}; "
            f"stdout={result.stdout!r}, stderr={result.stderr!r}"
        )


def main() -> int:
    if not BASELINE.exists():
        raise SystemExit("Run scripts/prepare.py before checking the baseline.")
    with tempfile.TemporaryDirectory(prefix="java-arrays-baseline-") as directory:
        binary = Path(directory) / "array_cases"
        subprocess.run([
            "cc", "-std=c11", "-Wall", "-Wextra", "-Werror", "-pedantic",
            "-I", str(RUNTIME), str(BASELINE), str(RUNTIME / "tests" / "array_cases.c"),
            "-o", str(binary),
        ], check=True)
        run(binary, "valid", 0)
        for code, cases in ERROR_CASES.items():
            for case in cases:
                run(binary, case, code)
    print("Generated baseline passed the production runtime cases.")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
