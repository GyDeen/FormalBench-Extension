"""Collect sanitizer evidence for mismatching Java/C mutant executions."""

from __future__ import annotations

import argparse
import csv
import json
import math
import shutil
import tempfile
from collections import Counter
from concurrent.futures import ThreadPoolExecutor, as_completed
from pathlib import Path
from typing import Any

from ..execution.run_c_sanitizer import run_sanitizer_checks
from .extract_exception_mismatches import extract_exception_mismatches
from .sanitizer_report import (
    critical_stderr,
    document_digest,
    java_error_type,
)

CSV_FIELDS = (
    "mutant", "program", "input_id", "java_error_type", "c_output",
    "stderr", "status", "reason", "sanitizer_status", "exit_code",
    "stdout", "timed_out", "ASAN_OPTIONS", "UBSAN_OPTIONS",
    "execution_error", "diagnostic", "comparison_sha256",
)


def _read(path: Path) -> dict[str, Any]:
    return json.loads(path.read_text(encoding="utf-8"))


def _mutant_key(result_dir: Path) -> str:
    return f"{result_dir.parent.name}/{result_dir.name}"


def _load_jobs(results_dir: Path, mutants_dir: Path) -> tuple[list[dict[str, Any]], int, int]:
    jobs: list[dict[str, Any]] = []
    comparison_paths = sorted(results_dir.glob("*/*/comparison.json"))
    for comparison_path in comparison_paths:
        result_dir = comparison_path.parent
        class_name = result_dir.parent.name
        mutant_id = result_dir.name
        java_results = _read(result_dir / "java_results.json")
        c_results = _read(result_dir / "c_results.json")
        inputs = _read(result_dir / "test_inputs.json")
        comparison = _read(comparison_path)
        cases = extract_exception_mismatches(
            comparison, java_results, c_results, inputs,
        )
        if not cases:
            continue
        c_dir = mutants_dir / class_name / "mutants" / mutant_id / "c"
        if not c_dir.is_dir():
            raise FileNotFoundError(f"C mutant directory not found: {c_dir}")
        if any("input_test" not in case for case in cases):
            raise ValueError(f"Missing replay input for {comparison_path}")
        jobs.append({
            "mutant": _mutant_key(result_dir),
            "result_dir": result_dir,
            "comparison_path": comparison_path,
            "comparison": comparison,
            "cases": cases,
            "c_dir": c_dir,
        })
    mismatching_mutants = sum(
        not _read(path)["summary"]["overall_match"]
        for path in comparison_paths
    )
    return jobs, len(comparison_paths), mismatching_mutants


def _run_job(job: dict[str, Any], timeout: float, compiler: str) -> dict[str, Any]:
    with tempfile.TemporaryDirectory(
        prefix=f"mutant-sanitizer-{job['mutant'].replace('/', '-')}-"
    ) as temporary:
        sanitized = run_sanitizer_checks(
            job["cases"], job["c_dir"], Path(temporary),
            timeout, compiler=compiler,
        )
    report_cases: list[dict[str, Any]] = []
    comparison_hash = document_digest(job["comparison"])
    sanitized_by_key = {(item["test"], item["step"]): item for item in sanitized}
    for case in job["cases"]:
        key = (case["test"], case["step"])
        evidence = sanitized_by_key[key]
        environment = evidence.get("environment", {})
        report_cases.append({
            "mutant": job["mutant"],
            "program": (
                case["input_test"]["steps"][0]["class"]
                if "input_test" in case
                else case["test"].split(".")[0].removesuffix("_ESTest")
            ),
            "input_id": f"{case['test']}.{case['step']}",
            "java": case["java"],
            "c_normal": case["c_normal"],
            "c_sanitized": {
                **{name: evidence[name] for name in (
                    "status", "exit_code", "stdout", "timed_out",
                    "execution_error", "diagnostic",
                ) if name in evidence},
                "environment": dict(environment),
                "stderr": critical_stderr(evidence.get("stderr", "")),
            },
            "assessment": None,
            "comparison_sha256": comparison_hash,
            "comparison_path": str(job["comparison_path"]),
            "c_source_dir": str(job["c_dir"]),
        })
    return {"mutant": job["mutant"], "cases": report_cases}


def _csv_row(case: dict[str, Any]) -> dict[str, Any]:
    evidence = case["c_sanitized"]
    environment = evidence.get("environment", {})
    return {
        "mutant": case["mutant"],
        "program": case["program"],
        "input_id": case["input_id"],
        "java_error_type": java_error_type(case["java"]),
        "c_output": json.dumps(case["c_normal"], ensure_ascii=False),
        "stderr": evidence.get("stderr", ""),
        "status": "",
        "reason": "",
        "sanitizer_status": evidence["status"],
        "exit_code": evidence.get("exit_code"),
        "stdout": evidence.get("stdout", ""),
        "timed_out": str(evidence.get("timed_out", False)).lower(),
        "ASAN_OPTIONS": environment.get("ASAN_OPTIONS", ""),
        "UBSAN_OPTIONS": environment.get("UBSAN_OPTIONS", ""),
        "execution_error": evidence.get("execution_error", ""),
        "diagnostic": evidence.get("diagnostic", ""),
        "comparison_sha256": case["comparison_sha256"],
    }


def _write_outputs(
    output_dir: Path, jobs: list[dict[str, Any]],
    total_mutants: int, mismatching_mutants: int,
) -> None:
    cases = [case for job in jobs for case in job["cases"]]
    cases.sort(key=lambda case: (case["mutant"], case["input_id"]))
    rows = [_csv_row(case) for case in cases]
    output_dir.mkdir(parents=True, exist_ok=True)
    csv_path = output_dir / "sanitizer_report.csv"
    json_path = output_dir / "sanitizer_report.json"
    summary_path = output_dir / "review_summary.json"
    if any(path.exists() for path in (csv_path, json_path, summary_path)):
        raise FileExistsError(
            f"Manual-review outputs already exist in {output_dir}; choose a new directory"
        )
    with csv_path.open("x", encoding="utf-8", newline="") as stream:
        writer = csv.DictWriter(stream, fieldnames=CSV_FIELDS)
        writer.writeheader()
        writer.writerows(rows)
    evidence = {
        "schema_version": "1.0",
        "kind": "mutant_java_exception_c_sanitizer_investigation",
        "summary": {
            "total_mutants": total_mutants,
            "mismatching_mutants": mismatching_mutants,
            "mutants_with_sanitizer_cases": len(jobs),
            "sanitizer_cases": len(cases),
            "manual_review_pending": len(cases),
            "sanitizer_status_counts": dict(
                Counter(case["c_sanitized"]["status"] for case in cases)
            ),
        },
        "replay_note": (
            "Each target replays preceding calls. Diagnostics may originate in the "
            "prefix or harness; review attribution manually."
        ),
        "cases": cases,
    }
    json_path.write_text(json.dumps(evidence, indent=2, ensure_ascii=False) + "\n", encoding="utf-8")
    summary_path.write_text(json.dumps(evidence["summary"], indent=2) + "\n", encoding="utf-8")
    print(f"Wrote {len(cases)} sanitizer cases to {csv_path}")
    print(f"Wrote full evidence to {json_path}")


def main() -> None:
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("--results-dir", type=Path, required=True)
    parser.add_argument("--mutants-dir", type=Path, required=True)
    parser.add_argument("--output-dir", type=Path, required=True)
    parser.add_argument("--timeout", type=float, default=2.0)
    parser.add_argument("--compiler", default="clang")
    parser.add_argument("--workers", type=int, default=4)
    args = parser.parse_args()
    if not math.isfinite(args.timeout) or args.timeout <= 0:
        parser.error("--timeout must be finite and positive")
    if args.workers <= 0:
        parser.error("--workers must be positive")
    if shutil.which(args.compiler) is None:
        parser.error(f"Sanitizer compiler not found: {args.compiler}")
    if not args.results_dir.is_dir():
        parser.error(f"Results directory not found: {args.results_dir}")
    if not args.mutants_dir.is_dir():
        parser.error(f"Mutants directory not found: {args.mutants_dir}")

    jobs, total_mutants, mismatching_mutants = _load_jobs(
        args.results_dir, args.mutants_dir,
    )
    print(
        f"Loaded {total_mutants} mutants; {mismatching_mutants} mismatching; "
        f"{len(jobs)} with sanitizer cases"
    )
    completed: list[dict[str, Any]] = []
    with ThreadPoolExecutor(max_workers=args.workers) as executor:
        futures = {
            executor.submit(_run_job, job, args.timeout, args.compiler): job["mutant"]
            for job in jobs
        }
        for index, future in enumerate(as_completed(futures), start=1):
            mutant = futures[future]
            result = future.result()
            completed.append(result)
            print(
                f"Sanitized {mutant} ({index}/{len(futures)}; "
                f"{len(result['cases'])} cases)", flush=True,
            )
    _write_outputs(args.output_dir, completed, total_mutants, mismatching_mutants)


if __name__ == "__main__":
    main()
