"""Join original outcomes and raw sanitizer evidence for manual review."""

from __future__ import annotations

import hashlib
import json
import re
from copy import deepcopy
from pathlib import Path
from typing import Any

REPORT_KIND = "java_exception_c_sanitizer_investigation"
ASSESSMENT_STATUSES = {"exception_equivalent", "mismatch", "unclear"}
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


def write_json(path: Path, document: Any, *, exclusive: bool = False) -> None:
    path.parent.mkdir(parents=True, exist_ok=True)
    with path.open("x" if exclusive else "w", encoding="utf-8") as stream:
        json.dump(document, stream, indent=2, ensure_ascii=False)
        stream.write("\n")


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
