"""Join original outcomes and raw sanitizer evidence for manual review."""

from __future__ import annotations

import csv
import hashlib
import json
import re
from copy import deepcopy
from pathlib import Path
from typing import Any

REPORT_KIND = "java_exception_c_sanitizer_investigation"
ASSESSMENT_STATUSES = {"exception_equivalent", "mismatch", "unclear"}
CSV_FIELDS = (
    "program", "input_id", "java_error_type", "c_output", "stderr", "status", "reason",
    "sanitizer_status", "exit_code", "stdout", "timed_out",
    "ASAN_OPTIONS", "UBSAN_OPTIONS", "execution_error", "diagnostic",
    "comparison_sha256",
)
SANITIZER_RESULT_FIELDS = (
    "status", "environment", "exit_code", "stdout", "stderr", "timed_out",
    "execution_error", "diagnostic",
)
_DIAGNOSTIC_LINE = re.compile(
    r"runtime error:.*"
    r"|(?:ERROR|SUMMARY): (?:AddressSanitizer|UndefinedBehaviorSanitizer|LeakSanitizer):.*"
    r"|(?:AddressSanitizer|UndefinedBehaviorSanitizer):DEADLYSIGNAL.*"
)


def critical_stderr(stderr: str) -> str:
    """Keep diagnostic headlines verbatim, without traces or memory dumps."""
    messages = []
    for line in stderr.splitlines():
        match = _DIAGNOSTIC_LINE.search(line)
        if match:
            messages.append(match.group().rstrip())
    return "\n".join(dict.fromkeys(messages))


def sanitizer_result(record: dict[str, Any]) -> dict[str, Any]:
    result = deepcopy({
        field: record[field] for field in SANITIZER_RESULT_FIELDS if field in record
    })
    if "stderr" in result:
        result["stderr"] = critical_stderr(result["stderr"])
    return result


def document_digest(document: Any) -> str:
    return hashlib.sha256(json.dumps(
        document, sort_keys=True, ensure_ascii=False, separators=(",", ":"),
    ).encode("utf-8")).hexdigest()


def java_error_type(outcome: dict[str, Any]) -> str:
    """Display the reported exception class, or the runner's error kind."""
    error = outcome["error"]
    if "type" in error:
        return error["type"]
    diagnostic = error.get("raw") or error.get("message", "")
    match = re.match(r"([\w$]+(?:\.[\w$]+)*)(?=:|\s|$)", diagnostic)
    if match and match[1].endswith(("Exception", "Error")):
        return match[1]
    return error["kind"]


def write_json(path: Path, document: Any, *, exclusive: bool = False) -> None:
    path.parent.mkdir(parents=True, exist_ok=True)
    with path.open("x" if exclusive else "w", encoding="utf-8") as stream:
        json.dump(document, stream, indent=2, ensure_ascii=False)
        stream.write("\n")


def write_sanitizer_csv(
    path: Path, report: dict[str, Any], *, exclusive: bool = False,
) -> None:
    """Write evidence with blank status/reason cells until manually assessed."""
    path.parent.mkdir(parents=True, exist_ok=True)
    with path.open("x" if exclusive else "w", encoding="utf-8", newline="") as stream:
        writer = csv.DictWriter(stream, fieldnames=CSV_FIELDS)
        writer.writeheader()
        for case in report["cases"]:
            evidence = sanitizer_result(case["c_sanitized"])
            assessment = case.get("assessment") or {}
            environment = evidence.get("environment", {})
            writer.writerow({
                "program": case["program"], "input_id": case["input_id"],
                "java_error_type": java_error_type(case["java"]),
                "c_output": json.dumps(case["c_normal"], ensure_ascii=False),
                "status": assessment.get("status", ""),
                "reason": assessment.get("reason", ""),
                "sanitizer_status": evidence["status"],
                "exit_code": evidence.get("exit_code"),
                "stdout": evidence.get("stdout", ""),
                "stderr": evidence.get("stderr", ""),
                "timed_out": str(evidence.get("timed_out", False)).lower(),
                "ASAN_OPTIONS": environment.get("ASAN_OPTIONS", ""),
                "UBSAN_OPTIONS": environment.get("UBSAN_OPTIONS", ""),
                "execution_error": evidence.get("execution_error", ""),
                "diagnostic": evidence.get("diagnostic", ""),
                "comparison_sha256": report["comparison_sha256"],
            })


def read_sanitizer_csv(path: Path) -> dict[str, Any]:
    """Load reviewer edits while preserving quoted JSON and multiline diagnostics."""
    with path.open(encoding="utf-8-sig", newline="") as stream:
        rows = list(csv.DictReader(stream))
    cases = []
    for row in rows:
        status = row["status"].strip()
        evidence = {
            "status": row["sanitizer_status"],
            "environment": {name: row[name] for name in ("ASAN_OPTIONS", "UBSAN_OPTIONS")},
            "exit_code": int(row["exit_code"]) if row["exit_code"] else None,
            "stdout": row["stdout"], "stderr": row["stderr"],
            "timed_out": row["timed_out"].lower() == "true",
        }
        for name in ("execution_error", "diagnostic"):
            if row[name]:
                evidence[name] = row[name]
        cases.append({
            "program": row["program"], "input_id": row["input_id"],
            "java": {"status": "error", "error": {"type": row["java_error_type"]}},
            "c_normal": json.loads(row["c_output"]),
            "c_sanitized": evidence,
            "assessment": {"status": status, "reason": row["reason"].strip()} if status else None,
            "comparison_sha256": row["comparison_sha256"],
        })
    return {
        "schema_version": "1.3", "kind": REPORT_KIND,
        "comparison_sha256": rows[0]["comparison_sha256"] if rows else None,
        "cases": cases,
    }


def index_cases(cases: list[dict[str, Any]]) -> dict[tuple[str, str], dict[str, Any]]:
    return {(case["test"], case["step"]): case for case in cases}


def build_sanitizer_report(
    comparison: dict[str, Any], cases: list[dict[str, Any]],
    sanitized_results: list[dict[str, Any]],
) -> dict[str, Any]:
    originals = index_cases(cases)
    sanitized = index_cases(sanitized_results)
    if originals.keys() != sanitized.keys():
        raise ValueError("Sanitizer results must cover exactly the extracted cases")
    return {
        "schema_version": "1.1", "kind": REPORT_KIND,
        "comparison_sha256": document_digest(comparison),
        "replay_note": "Each target replays preceding calls. Diagnostics may originate in the prefix or harness; review attribution manually.",
        "cases": [
            {
                "program": case["input_test"]["steps"][0]["class"]
                if "input_test" in case else case["test"].split(".")[0].removesuffix("_ESTest"),
                "input_id": f"{case['test']}.{case['step']}",
                "java": deepcopy(case["java"]),
                "c_normal": deepcopy(case["c_normal"]),
                "c_sanitized": sanitizer_result(sanitized[key]),
                "assessment": None,
            }
            for key, case in originals.items()
        ],
    }
