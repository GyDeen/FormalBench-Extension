"""Run fixed OpenJML and Frama-C configurations with explicit outcome classes."""

from __future__ import annotations

import hashlib
import json
import re
import shlex
import shutil
import subprocess
import time
from dataclasses import dataclass
from pathlib import Path
from typing import Any

from .manifest import REPO, InputError, sha256

CONTRACT_ROOT = REPO / "verification/java_arrays"
CONTRACTS = (
    "helpers.acsl.h", "jintarray.acsl.h", "jboolarray.acsl.h",
    "jdoublearray.acsl.h", "jintarray2.acsl.h", "jdoublearray2.acsl.h",
)
OUTCOMES = (
    "proved", "specification violation", "precondition/RTE failure",
    "syntax/tool failure", "unknown/timeout", "not run",
)
SUPPORT_GOAL = re.compile(r"requires|precondition|\brte\b|runtime|division_by_zero|overflow|out_of_bounds|valid_access", re.I)
TOOL_FAILURE = re.compile(r"(?:error:|\bsyntax error\b|\bparse error\b|\baborted\b|unsupported|internal jml bug|unknown option)", re.I)
PROVER_FAILURE = re.compile(r"(?:\bwhy3 error\b|\brunning prover\b[^\n]*\bfailed\b|\bunknown prover\b|\bno prover\b)", re.I)
JAVA_WARNING = re.compile(r"^(?:(?P<file>.*?):(?P<line>\d+):\s*)?warning:\s*(?P<message>.+)$", re.M)


@dataclass(frozen=True)
class Settings:
    openjml: str
    frama_c: str
    java_prover: str
    c_provers: str
    timeout: int
    goal_timeout: int
    memory_model: str
    machdep: str
    wp_memlimit: int
    wp_par: int
    why3_extra_config: Path | None


def executable_info(name: str, version_flag: str) -> dict[str, Any]:
    """Resolve the exact verifier binary and record its hash and version."""
    resolved = shutil.which(name)
    if resolved is None:
        raise InputError(f"Verifier executable not found: {name}")
    path = Path(resolved).resolve()
    try:
        response = subprocess.run([str(path), version_flag], capture_output=True, text=True,
                                  timeout=20, check=False)
        version = (response.stdout + response.stderr).strip()
        version_exit = response.returncode
    except (OSError, subprocess.TimeoutExpired) as error:
        version = str(error)
        version_exit = None
    return {"path": str(path), "sha256": sha256(path), "version_command": [str(path), version_flag],
            "version_exit_code": version_exit, "version_output": version}


def support_hashes() -> dict[str, str]:
    """Fixed JArray declarations assumed by C target proofs."""
    sources = [CONTRACT_ROOT / "generated/types.h"]
    sources.extend(CONTRACT_ROOT / "contracts" / name for name in CONTRACTS)
    for source in sources:
        if not source.is_file():
            raise InputError(f"Missing fixed JArray support file: {source}")
    return {**{source.relative_to(CONTRACT_ROOT).as_posix(): sha256(source) for source in sources},
            "java_arrays.h": hashlib.sha256(_shim_text().encode()).hexdigest()}


def _shim_text() -> str:
    """Provide the include name used by translations, backed by ACSL only."""
    content = "#ifndef PAIRED_EVALUATION_JAVA_ARRAYS_H\n#define PAIRED_EVALUATION_JAVA_ARRAYS_H\n"
    content += "#include <limits.h>\n"
    content += "".join(f'#include "contracts/{name}"\n' for name in CONTRACTS)
    return content + "#endif\n"


def stage_c_support(case_dir: Path) -> dict[str, str]:
    """Copy read-only JArray interfaces next to one translated C program."""
    hashes = support_hashes()
    for relative, expected in hashes.items():
        if relative == "java_arrays.h":
            continue
        target = case_dir / relative
        target.parent.mkdir(parents=True, exist_ok=True)
        if target.exists():
            # If the Jarray being modified, abort
            if sha256(target) != expected:
                raise InputError(f"Frozen JArray support changed: {target}")
        else:
            shutil.copy2(CONTRACT_ROOT / relative, target)
    shim = case_dir / "java_arrays.h"
    content = _shim_text()
    if shim.exists() and shim.read_text(encoding="utf-8") != content:
        raise InputError(f"Frozen JArray contract shim changed: {shim}")
    if not shim.exists():
        shim.write_text(content, encoding="utf-8")
    if sha256(shim) != hashes["java_arrays.h"]:
        raise InputError(f"Frozen JArray contract shim changed: {shim}")
    return hashes


def build_command(language: str, source: Path, case_dir: Path, settings: Settings,
                  executables: dict[str, dict[str, Any]]) -> list[str]:
    """Build commands for program sources, never JArray validation clients."""
    if language == "java":
        # OpenJML checks the annotated FormalBench Java program.
        return [executables["java"]["path"], "--esc", "--nullable-by-default",
                f"--prover={settings.java_prover}", str(source)]
    # WP checks the translated program; RTE and callee requires remain
    # enabled, including calls into fixed JArray contracts.
    command = [executables["c"]["path"], "-machdep", settings.machdep,
               "-wp", "-wp-rte", "-wp-no-filter-init", "-wp-model", settings.memory_model,
               "-wp-prover", settings.c_provers, "-wp-timeout", str(settings.goal_timeout),
               "-wp-memlimit", str(settings.wp_memlimit), "-wp-par", str(settings.wp_par),
               "-wp-cache", "none", "-wp-report-json", str(case_dir / "wp-report.json")]
    if settings.why3_extra_config is not None:
        command.extend(["-wp-why3-extra-config", str(settings.why3_extra_config)])
    command.append(str(source))
    return command


def _goal_records(report: Any) -> list[dict[str, Any]]:
    """Normalize supported WP JSON containers to a per-goal list."""
    if isinstance(report, list):
        records = report
    elif isinstance(report, dict):
        records = next((report[key] for key in ("goals", "records", "results")
                        if isinstance(report.get(key), list)), None)
    else:
        records = None
    if not isinstance(records, list) or not all(isinstance(record, dict) for record in records):
        raise ValueError("WP report does not contain a goal list")
    return records


def classify_c(returncode: int | None, timed_out: bool, stdout: str, stderr: str,
               report: Any | None) -> tuple[str, list[dict[str, Any]], str]:
    """Require all WP goals to pass; never count unresolved goals as rejection."""
    if timed_out:
        return "unknown/timeout", [], "Frama-C process exceeded its time budget"
    output = stdout + "\n" + stderr
    if TOOL_FAILURE.search(output) and (report is None or returncode != 0):
        return "syntax/tool failure", [], "Frama-C reported a parse, tool, or unsupported-feature error"
    if report is None:
        return ("syntax/tool failure" if returncode else "unknown/timeout"), [], "No WP goal report was produced"
    try:
        records = _goal_records(report)
    except ValueError as error:
        return "syntax/tool failure", [], str(error)
    goals: list[dict[str, Any]] = []
    for record in records:
        # Only an explicit invalid verdict/validated counterexample supports a
        # violation label. A failed or timed-out proof stays unknown.
        verdict = str(record.get("verdict", "none")).lower()
        counterexample = record.get("counterexample")
        invalid = verdict == "invalid" or (isinstance(counterexample, dict)
                                           and counterexample.get("validated") is True)
        proved = record.get("passed") is True and verdict in {"valid", "passed"}
        category = "precondition/RTE" if SUPPORT_GOAL.search(" ".join(str(record.get(key, ""))
                                      for key in ("goal", "property", "behavior"))) else "specification"
        # The category prevents a JArray-call precondition or runtime-safety
        # issue from being counted as a functional-specification rejection.
        goals.append({"goal": record.get("goal"), "property": record.get("property"),
                      "function": record.get("function"), "file": record.get("file"),
                      "line": record.get("line"), "category": category, "verdict": verdict,
                      "state": "violated" if invalid else "proved" if proved else "unknown",
                      "proved_subgoals": record.get("proved", 0),
                      "failed_subgoals": record.get("failed", 0),
                      "timeout_subgoals": record.get("timeout", 0),
                      "unknown_subgoals": record.get("unknown", 0),
                      "provers": record.get("provers", [])})
    if not goals:
        return "unknown/timeout", goals, "WP reported no proof goals"
    if any(goal["state"] == "violated" and goal["category"] == "precondition/RTE" for goal in goals):
        return "precondition/RTE failure", goals, "A precondition or runtime-safety goal was invalid"
    if any(goal["state"] == "violated" for goal in goals):
        return "specification violation", goals, "A specification goal had explicit invalidity evidence"
    if returncode == 0 and all(goal["state"] == "proved" for goal in goals):
        return "proved", goals, "Every reported WP goal was proved"
    if PROVER_FAILURE.search(output):
        # A failed Why3/prover invocation is a tool failure, even if Frama-C
        # emitted a JSON report containing unproved goals.
        return "syntax/tool failure", goals, "Frama-C could not run a configured prover"
    if returncode not in (0, None) and TOOL_FAILURE.search(output):
        return "syntax/tool failure", goals, "Frama-C reported a tool error"
    return "unknown/timeout", goals, "At least one WP goal remains unresolved"


def classify_java(returncode: int | None, timed_out: bool, stdout: str,
                  stderr: str) -> tuple[str, list[dict[str, Any]], str]:
    """Separate OpenJML proof warnings from syntax errors and timeouts."""
    if timed_out:
        return "unknown/timeout", [], "OpenJML process exceeded its time budget"
    output = stdout + "\n" + stderr
    if re.search(r"\b(?:timeout|timed out|unknown)\b", output, re.I):
        return "unknown/timeout", [], "OpenJML reported timeout or unknown"
    warnings = [match.groupdict() for match in JAVA_WARNING.finditer(output)]
    goals: list[dict[str, Any]] = []
    for warning in warnings:
        # Keep precondition/runtime warnings outside the specification-
        # rejection count; other ESC proof-failure warnings are recorded.
        message = warning["message"]
        if "prover cannot establish" not in message.lower() and "assertion is false" not in message.lower():
            goals.append({"state": "unknown", "category": "unclassified", **warning})
            continue
        category = "precondition/RTE" if SUPPORT_GOAL.search(message) or re.search(
            r"PossiblyNull|PossiblyNegative|PossiblyTooLarge|ArithmeticOperationRange|Undefined", message, re.I
        ) else "specification"
        goals.append({"state": "violated", "category": category, **warning})
    if re.search(r"\berror:", output, re.I) or "Internal JML bug" in output:
        return "syntax/tool failure", goals, "OpenJML reported a syntax or tool error"
    if any(goal["state"] == "violated" and goal["category"] == "precondition/RTE" for goal in goals):
        return "precondition/RTE failure", goals, "OpenJML reported an unproved precondition or runtime-safety obligation"
    if any(goal["state"] == "violated" for goal in goals):
        return "specification violation", goals, "OpenJML reported an unproved specification obligation"
    if goals:
        return "unknown/timeout", goals, "OpenJML warnings were not classified as proof failures"
    if returncode == 0:
        return "proved", [], "OpenJML ESC completed without errors or warnings"
    return "syntax/tool failure", [], "OpenJML exited unsuccessfully without a proof-failure report"


def run_verifier(language: str, source: Path, case_dir: Path, settings: Settings,
                 executables: dict[str, dict[str, Any]]) -> dict[str, Any]:
    """Run one verifier and retain its command, logs, report and verdict."""
    command = build_command(language, source, case_dir, settings, executables)
    report_path = case_dir / "wp-report.json"
    if language == "c":
        # A previous interrupted attempt must not supply this attempt's goals.
        report_path.unlink(missing_ok=True)
    (case_dir / "command.json").write_text(json.dumps({
        "argv": command, "shell_display": shlex.join(command), "cwd": str(case_dir),
        "timeout_seconds": settings.timeout,
    }, indent=2) + "\n", encoding="utf-8")
    start = time.monotonic()
    timed_out = False
    try:
        # The process limit is separate from Frama-C's per-goal timeout.
        completed = subprocess.run(command, cwd=case_dir, capture_output=True, text=True,
                                   timeout=settings.timeout, check=False)
        returncode, stdout, stderr = completed.returncode, completed.stdout, completed.stderr
    except subprocess.TimeoutExpired as error:
        timed_out = True
        returncode = None
        stdout = error.stdout.decode(errors="replace") if isinstance(error.stdout, bytes) else error.stdout or ""
        stderr = error.stderr.decode(errors="replace") if isinstance(error.stderr, bytes) else error.stderr or ""
    except OSError as error:
        returncode, stdout, stderr = None, "", str(error)
    elapsed = round(time.monotonic() - start, 3)
    (case_dir / "stdout.log").write_text(stdout, encoding="utf-8")
    (case_dir / "stderr.log").write_text(stderr, encoding="utf-8")
    report = None
    report_error = None
    if language == "c" and report_path.is_file():
        try:
            report = json.loads(report_path.read_text(encoding="utf-8"))
        except (OSError, json.JSONDecodeError) as error:
            report_error = str(error)
    if report_error:
        # Malformed tool output cannot be interpreted as a mutant rejection.
        outcome, goals, reason = "syntax/tool failure", [], f"Invalid WP report: {report_error}"
    elif language == "c":
        outcome, goals, reason = classify_c(returncode, timed_out, stdout, stderr, report)
    else:
        outcome, goals, reason = classify_java(returncode, timed_out, stdout, stderr)
    return {"outcome": outcome, "reason": reason, "goals": goals, "exit_code": returncode,
            "timed_out": timed_out, "elapsed_seconds": elapsed, "command": command,
            "logs": {"stdout": "stdout.log", "stderr": "stderr.log",
                     "wp_report": "wp-report.json" if report_path.is_file() else None}}
