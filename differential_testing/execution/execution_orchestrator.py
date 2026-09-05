"""Toolchain discovery, harness compilation, and isolated test execution."""

from __future__ import annotations

import json
import re
import shutil
import signal
import subprocess
from dataclasses import dataclass
from pathlib import Path
from typing import Any

from .c_harness import generate_c_harness
from .input_manifest import RunnerError, group_tests_by_class
from .java_harness import generate_java_harness


@dataclass(frozen=True)
class Toolchain:
    java: str
    javac: str
    cc: str

    @classmethod
    def discover(cls) -> Toolchain:
        java = shutil.which("java")
        javac = shutil.which("javac")
        cc = shutil.which("cc") or shutil.which("gcc") or shutil.which("clang")
        if not java or not javac:
            raise RuntimeError("java and javac must be available on PATH")
        if not cc:
            raise RuntimeError(
                "A C compiler (cc, gcc, or clang) must be available on PATH"
            )
        return cls(java=java, javac=javac, cc=cc)


@dataclass(frozen=True)
class BuiltRunner:
    command: list[str] | None
    compile_error: str | None = None


def _find_source(
    directory: Path, expected_class: str, suffix: str, functions: set[str]
) -> tuple[Path | None, str | None]:
    exact = directory / f"{expected_class}{suffix}"
    if exact.is_file():
        return exact, None
    # Some generated tests retain an older class name. Function-based lookup
    # links them to a renamed source only when that match is unambiguous.
    candidates = [
        path
        for path in sorted(directory.glob(f"*{suffix}"))
        if _contains_functions(path, functions)
    ]
    if len(candidates) == 1:
        return candidates[0], None
    if not candidates:
        names = ", ".join(sorted(functions))
        return None, f"Source not found for {expected_class} ({names})"
    paths = ", ".join(str(path) for path in candidates)
    return None, f"Ambiguous sources for {expected_class}: {paths}"


def _contains_functions(path: Path, functions: set[str]) -> bool:
    source = path.read_text(encoding="utf-8")
    return all(
        re.search(rf"\b{re.escape(function)}\s*\(", source)
        for function in functions
    )


def _java_metadata(path: Path) -> tuple[str, dict[str, str]]:
    source = path.read_text(encoding="utf-8")
    class_match = re.search(r"\bclass\s+([A-Za-z_$][A-Za-z0-9_$]*)\b", source)
    if not class_match:
        raise RunnerError(f"No Java class declaration found in {path}")
    java_to_canonical = {
        "int": "int32",
        "int[]": "int32[]",
        "int[][]": "int32[][]",
        "double[]": "float64[]",
    }
    signatures: dict[str, str] = {}
    # Return types are needed for EvoSuite calls whose result was discarded in
    # the original test and therefore absent from the input manifest.
    signature_pattern = re.compile(
        r"\bpublic\s+static\s+"
        r"(?P<type>int(?:\s*\[\s*\]){0,2}|double\s*\[\s*\]|void)\s+"
        r"(?P<function>[A-Za-z_$][A-Za-z0-9_$]*)\s*\("
    )
    for match in signature_pattern.finditer(source):
        java_type = re.sub(r"\s+", "", match.group("type"))
        if java_type in java_to_canonical:
            signatures[match.group("function")] = java_to_canonical[java_type]
    return class_match.group(1), signatures


def _compile(command: list[str]) -> tuple[bool, str]:
    result = subprocess.run(command, text=True, capture_output=True, check=False)
    diagnostic = (result.stdout + result.stderr).strip()
    return result.returncode == 0, diagnostic


def _build_java_runner(
    source: Path | None,
    source_error: str | None,
    tests: list[dict[str, Any]],
    output_dir: Path,
    toolchain: Toolchain,
) -> tuple[BuiltRunner, dict[str, str]]:
    output_dir.mkdir(parents=True, exist_ok=True)
    if source is None:
        return BuiltRunner(None, source_error or "Java source not found"), {}
    class_name, return_types = _java_metadata(source)
    runner_name, runner_source = generate_java_harness(
        class_name, tests, return_types
    )
    runner_path = output_dir / f"{runner_name}.java"
    runner_path.write_text(runner_source, encoding="utf-8")
    succeeded, diagnostic = _compile(
        [toolchain.javac, "-d", str(output_dir), str(source), str(runner_path)]
    )
    if not succeeded:
        # Compilation failures become per-step results rather than aborting the
        # rest of the dataset.
        return BuiltRunner(None, diagnostic), return_types
    command = [toolchain.java, "-cp", str(output_dir), runner_name]
    return BuiltRunner(command), return_types


def _build_c_runner(
    source: Path | None,
    source_error: str | None,
    tests: list[dict[str, Any]],
    return_types: dict[str, str],
    output_dir: Path,
    toolchain: Toolchain,
) -> BuiltRunner:
    output_dir.mkdir(parents=True, exist_ok=True)
    if source is None:
        return BuiltRunner(None, source_error or "C source not found")
    runner_path = output_dir / "runner.c"
    executable = output_dir / "runner"
    runner_path.write_text(
        generate_c_harness(source, tests, return_types), encoding="utf-8"
    )
    succeeded, diagnostic = _compile(
        [
            toolchain.cc,
            "-std=c11",
            "-O0",
            str(runner_path),
            "-lm",
            "-o",
            str(executable),
        ]
    )
    if not succeeded:
        return BuiltRunner(None, diagnostic)
    return BuiltRunner([str(executable)])


def _failure(kind: str, message: str) -> dict[str, Any]:
    return {"status": "error", "error": {"kind": kind, "message": message}}


def _run_process(command: list[str], timeout: float) -> dict[str, Any]:
    # Every target step gets its own process so native crashes, recursion, and
    # hangs cannot prevent later tests from being attempted.
    try:
        result = subprocess.run(
            command, text=True, capture_output=True, timeout=timeout, check=False
        )
    except subprocess.TimeoutExpired:
        return _failure("timeout", f"Exceeded {timeout:g} seconds")
    if result.returncode != 0:
        return _process_failure(result)
    try:
        outcome = json.loads(result.stdout)
    except json.JSONDecodeError as error:
        message = f"Invalid runner JSON: {error}; stdout={result.stdout!r}"
        if result.stderr.strip():
            message += f"; stderr={result.stderr.strip()!r}"
        return _failure("runner_protocol_error", message)
    if not isinstance(outcome, dict) or outcome.get("status") not in {
        "returned",
        "error",
    }:
        return _failure("runner_protocol_error", "Runner returned an invalid outcome")
    return outcome


def _process_failure(result: subprocess.CompletedProcess[str]) -> dict[str, Any]:
    if result.returncode < 0:
        number = -result.returncode
        try:
            name = signal.Signals(number).name
        except ValueError:
            name = f"signal {number}"
        return _failure("process_crash", f"Terminated by {name}")
    message = result.stderr.strip() or f"Exited with status {result.returncode}"
    return _failure("process_error", message)


def _collect_results(
    tests: list[dict[str, Any]], runner: BuiltRunner, timeout: float
) -> list[dict[str, Any]]:
    records: list[dict[str, Any]] = []
    for test_index, test in enumerate(tests):
        steps: list[dict[str, Any]] = []
        for step_index, step in enumerate(test["steps"]):
            if runner.compile_error is not None:
                outcome = _failure("compile_error", runner.compile_error)
            else:
                assert runner.command is not None
                # The generated harness replays the test prefix and emits only
                # the requested step's outcome.
                command = [*runner.command, str(test_index), str(step_index)]
                outcome = _run_process(command, timeout)
            steps.append({"id": step["id"], **outcome})
        records.append({"id": test["id"], "steps": steps})
    return records


def _result_document(
    language: str, records: list[dict[str, Any]]
) -> dict[str, Any]:
    return {
        "schema_version": "1.0",
        "kind": "test_execution_results",
        "language": language,
        "tests": records,
    }


def run_all(
    document: dict[str, Any],
    java_dir: Path,
    c_dir: Path,
    build_dir: Path,
    timeout: float,
) -> tuple[dict[str, Any], dict[str, Any]]:
    """Build matching runners and execute every input step in isolation."""
    toolchain = Toolchain.discover()
    java_records: list[dict[str, Any]] = []
    c_records: list[dict[str, Any]] = []

    for expected_class, tests in group_tests_by_class(document).items():
        functions = {step["function"] for test in tests for step in test["steps"]}
        java_source, java_error = _find_source(
            java_dir, expected_class, ".java", functions
        )
        c_source, c_error = _find_source(c_dir, expected_class, ".c", functions)
        java_runner, return_types = _build_java_runner(
            java_source,
            java_error,
            tests,
            build_dir / "java" / expected_class,
            toolchain,
        )
        c_runner = _build_c_runner(
            c_source,
            c_error,
            tests,
            return_types,
            build_dir / "c" / expected_class,
            toolchain,
        )
        java_records.extend(_collect_results(tests, java_runner, timeout))
        c_records.extend(_collect_results(tests, c_runner, timeout))

    return (
        _result_document("java", java_records),
        _result_document("c", c_records),
    )
