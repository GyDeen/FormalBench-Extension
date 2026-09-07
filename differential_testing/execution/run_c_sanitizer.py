"""Replay selected C calls with ASan/UBSan and retain uninterpreted evidence."""

from __future__ import annotations

import argparse
import hashlib
import json
import math
import os
import shutil
import subprocess
import tempfile
from pathlib import Path
from typing import Any

from .c_harness import generate_c_harness
from .execution_orchestrator import _find_source
from .input_manifest import RunnerError

SANITIZER_FLAGS = ["-fsanitize=address,undefined", "-fno-omit-frame-pointer", "-g"]
SANITIZER_ENV = {
    "ASAN_OPTIONS": "detect_leaks=0:abort_on_error=1:symbolize=1",
    "UBSAN_OPTIONS": "print_stacktrace=1",
}


def _text(value: str | bytes | None) -> str:
    return value.decode("utf-8", errors="replace") if isinstance(value, bytes) else value or ""


def run_sanitized_test(
    command: list[str], timeout: float = 2.0,
    *, environment: dict[str, str] | None = None,
) -> dict[str, Any]:
    """Capture process outcomes, including partial output on timeout."""
    try:
        proc = subprocess.run(
            command, capture_output=True, text=True, errors="replace",
            timeout=timeout, env=environment, check=False,
        )
        return {
            "exit_code": proc.returncode, "stdout": proc.stdout,
            "stderr": proc.stderr, "timed_out": False,
        }
    except subprocess.TimeoutExpired as error:
        return {
            "exit_code": None, "stdout": _text(error.stdout),
            "stderr": _text(error.stderr), "timed_out": True,
        }
    except OSError as error:
        return {
            "exit_code": None, "stdout": "", "stderr": "",
            "timed_out": False, "execution_error": str(error),
        }


def run_sanitizer_checks(
    cases: list[dict[str, Any]], c_dir: Path, build_dir: Path,
    timeout: float = 2.0, *, compiler: str = "clang",
) -> list[dict[str, Any]]:
    """Compile each selected test and replay its prefix up to the target call.

    No exception matching or sanitizer diagnostic classification happens here.
    A diagnostic can originate in an earlier prefix call or harness serializer.
    """
    if not math.isfinite(timeout) or timeout <= 0:
        raise ValueError("timeout must be finite and positive")
    if not cases:
        return []
    cc = shutil.which(compiler)
    if cc is None:
        raise RuntimeError(f"Sanitizer compiler not found: {compiler}")
    records = []
    for index, case in enumerate(cases):
        test = case["input_test"]
        class_name = test["steps"][0]["class"]
        step_ids = [step["id"] for step in test["steps"]]
        target = step_ids.index(case["step"])
        record: dict[str, Any] = {
            "test": case["test"], "step": case["step"],
            "replayed_steps": step_ids[:target + 1],
            "environment": dict(SANITIZER_ENV), "timeout_seconds": timeout,
        }
        source, source_error = _find_source(
            c_dir, class_name, ".c", {step["function"] for step in test["steps"]},
        )
        if source is None:
            record.update(status="source_error", diagnostic=source_error)
            records.append(record)
            continue
        work = build_dir.resolve() / f"case_{index:04d}"
        work.mkdir(parents=True, exist_ok=True)
        harness = work / "runner.c"
        binary = work / "runner"
        # A discarded return has no binding in the manifest. Recover the
        # target's type from ordinary C evidence when it is available.
        normal_return = (case.get("c_normal") or {}).get("return", {})
        return_types = {}
        if normal_return.get("type"):
            return_types[test["steps"][target]["function"]] = normal_return["type"]
        try:
            harness_source = generate_c_harness(source, [test], return_types)
        except RunnerError as error:
            record.update(status="harness_error", diagnostic=str(error))
            records.append(record)
            continue
        harness.write_text(harness_source, encoding="utf-8")
        record.update(
            source=str(source.resolve()),
            source_sha256=hashlib.sha256(source.read_bytes()).hexdigest(),
            harness_source=harness_source,
        )
        compile_command = [
            cc, "-std=c11", "-O0", *SANITIZER_FLAGS,
            str(harness), "-lm", "-o", str(binary),
        ]
        compilation = run_sanitized_test(compile_command, 60.0)
        record["compilation"] = {"command": compile_command, **compilation}
        if compilation["exit_code"] != 0:
            record["status"] = "compile_error"
        else:
            command = [str(binary), "0", str(target)]
            outcome = run_sanitized_test(
                command, timeout, environment={**os.environ, **SANITIZER_ENV},
            )
            record.update(command=command, status="executed", **outcome)
        records.append(record)
    return records


def main() -> None:
    # Reporting only serializes evidence; semantic decisions remain in merge.
    from ..comparison.sanitizer_report import build_sanitizer_report, write_sanitizer_csv

    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("--cases", type=Path, required=True)
    parser.add_argument("--comparison", type=Path, required=True, help="Existing raw comparison.json")
    parser.add_argument("--c-dir", type=Path, required=True)
    parser.add_argument("--output", type=Path, required=True, help="Review CSV output path")
    parser.add_argument("--timeout", type=float, default=2.0)
    parser.add_argument("--compiler", default="clang")
    args = parser.parse_args()
    if args.output.exists():
        parser.error("Output already exists; choose a new evidence file")
    cases = json.loads(args.cases.read_text(encoding="utf-8"))["cases"]
    comparison = json.loads(args.comparison.read_text(encoding="utf-8"))
    with tempfile.TemporaryDirectory(prefix="formalbench-sanitizer-") as temporary:
        records = run_sanitizer_checks(
            cases, args.c_dir, Path(temporary), args.timeout, compiler=args.compiler,
        )
    report = build_sanitizer_report(comparison, cases, records)
    write_sanitizer_csv(args.output, report, exclusive=True)


if __name__ == "__main__":
    main()
