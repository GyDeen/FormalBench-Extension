from __future__ import annotations

import argparse
import hashlib
import json
import os
import re
import shlex
import shutil
import subprocess
import sys
from collections import Counter
from datetime import datetime, timezone
from pathlib import Path
from typing import Any

WORKSPACE = Path(__file__).resolve().parents[1]
PROJECT = WORKSPACE.parents[1]
GENERATED = WORKSPACE / "generated"
RESULTS = WORKSPACE / "results"
MANIFEST = GENERATED / "manifest.json"
BASELINE_DRIVER = GENERATED / "drivers" / "baseline.c"
CONTRACTS = (
    WORKSPACE / "contracts" / "helpers.acsl.h",
    WORKSPACE / "contracts" / "jintarray.acsl.h",
    WORKSPACE / "contracts" / "jboolarray.acsl.h",
    WORKSPACE / "contracts" / "jdoublearray.acsl.h",
    WORKSPACE / "contracts" / "jintarray2.acsl.h",
    WORKSPACE / "contracts" / "jdoublearray2.acsl.h",
)

PUBLIC_FUNCTIONS = (
    "jarray_new",
    "jarray_is_null",
    "jarray_length",
    "jarray_get",
    "jarray_set",
    "jarray_free",
    "jbool_array_new",
    "jbool_array_is_null",
    "jbool_array_length",
    "jbool_array_get",
    "jbool_array_set",
    "jbool_array_free",
    "jdouble_array_new",
    "jdouble_array_is_null",
    "jdouble_array_length",
    "jdouble_array_get",
    "jdouble_array_set",
    "jdouble_array_free",
    "jarray2_new_rows",
    "jarray2_new",
    "jarray2_is_null",
    "jarray2_length",
    "jarray2_get",
    "jarray2_set",
    "jarray2_free",
    "jdouble_array2_new_rows",
    "jdouble_array2_new",
    "jdouble_array2_is_null",
    "jdouble_array2_length",
    "jdouble_array2_get",
    "jdouble_array2_set",
    "jdouble_array2_free",
)
HELPER_FUNCTIONS = (
    "array_error",
    "check_length",
    "check_reference",
    "check_index",
    "allocate",
)
TARGET_FUNCTIONS = HELPER_FUNCTIONS + PUBLIC_FUNCTIONS
INCONCLUSIVE_VERDICTS = {"none", "failed", "unknown", "stepout", "timeout"}


class InputError(RuntimeError):
    """Verification input or manifest is inconsistent."""


def sha256_file(path: Path) -> str:
    digest = hashlib.sha256()
    with path.open("rb") as stream:
        for chunk in iter(lambda: stream.read(1024 * 1024), b""):
            digest.update(chunk)
    return digest.hexdigest()


def relative(path: Path) -> str:
    try:
        return path.resolve().relative_to(PROJECT.resolve()).as_posix()
    except ValueError:
        return str(path.resolve())


def write_json(path: Path, value: Any) -> None:
    path.write_text(json.dumps(value, indent=2, sort_keys=True) + "\n", encoding="utf-8")


def require_hash(entry: dict[str, Any], label: str) -> Path:
    try:
        path = PROJECT / entry["path"]
        expected = entry["sha256"]
    except (KeyError, TypeError) as error:
        raise InputError(f"Malformed manifest entry for {label}") from error
    if not path.is_file():
        raise InputError(f"Missing {label}: {relative(path)}")
    actual = sha256_file(path)
    if actual != expected:
        raise InputError(
            f"Hash mismatch for {label}: expected {expected}, found {actual}; "
            "regenerate verification inputs first."
        )
    return path


def load_and_validate_manifest() -> dict[str, Any]:
    if not MANIFEST.is_file():
        raise InputError("Missing generated/manifest.json; run scripts/prepare.py first.")
    manifest = json.loads(MANIFEST.read_text(encoding="utf-8"))
    if manifest.get("schema_version") != "1.0":
        raise InputError("Unsupported generated manifest schema")
    sources = manifest.get("sources", {})
    generated = manifest.get("generated", {})
    require_hash(sources.get("header", {}), "production header")
    require_hash(sources.get("implementation", {}), "production implementation")
    require_hash(generated.get("types", {}), "generated types")
    require_hash(generated.get("baseline", {}), "generated baseline")
    if not BASELINE_DRIVER.is_file():
        raise InputError("Missing generated baseline driver; run scripts/prepare.py first.")
    for contract in CONTRACTS:
        if not contract.is_file():
            raise InputError(f"Missing fixed contract: {relative(contract)}")
    seen: set[str] = set()
    for mutant in manifest.get("mutants", []):
        mutant_id = mutant.get("id")
        if not isinstance(mutant_id, str) or not re.fullmatch(r"\d{4}", mutant_id):
            raise InputError(f"Invalid mutant ID in manifest: {mutant_id!r}")
        if mutant_id in seen:
            raise InputError(f"Duplicate mutant ID in manifest: {mutant_id}")
        seen.add(mutant_id)
        require_hash(mutant.get("source", {}), f"mutant {mutant_id} source")
        require_hash(mutant.get("driver", {}), f"mutant {mutant_id} driver")
    return manifest


def select_mutants(manifest: dict[str, Any], requested: list[str]) -> list[dict[str, Any]]:
    mutants = manifest.get("mutants", [])
    if not requested:
        return mutants
    by_id = {mutant["id"]: mutant for mutant in mutants}
    unknown = sorted(set(requested) - by_id.keys())
    if unknown:
        raise InputError(f"Unknown mutant ID(s): {', '.join(unknown)}")
    requested_set = set(requested)
    return [mutant for mutant in mutants if mutant["id"] in requested_set]


def report_records(document: Any) -> list[dict[str, Any]]:
    if isinstance(document, list):
        records = document
    elif isinstance(document, dict):
        records = next(
            (
                document[key] for key in ("goals", "records", "results")
                if isinstance(document.get(key), list)
            ),
            None,
        )
        if records is None:
            raise ValueError("WP JSON report does not contain a goal list")
    else:
        raise ValueError("WP JSON report must be an array or object")
    if not all(isinstance(record, dict) for record in records):
        raise ValueError("WP JSON goal entries must be objects")
    return records


def goal_category(record: dict[str, Any]) -> str:
    function = str(record.get("function", ""))
    text = " ".join(
        str(record.get(key, "")) for key in ("goal", "property", "behavior")
    ).lower()
    if not function:
        function = next(
            (candidate for candidate in TARGET_FUNCTIONS if candidate.lower() in text),
            "",
        )
    if "loop" in text or "invariant" in text or "variant" in text:
        return "loop_support"
    if "rte" in text or "runtime" in text or "assert" in text:
        return "runtime_safety"
    if function in PUBLIC_FUNCTIONS:
        return "public_contract"
    if function in HELPER_FUNCTIONS:
        return "helper_contract"
    return "auxiliary"


def explicit_violation(record: dict[str, Any]) -> bool:
    if str(record.get("verdict", "")).lower() == "invalid":
        return True
    counterexample = record.get("counterexample")
    return isinstance(counterexample, dict) and counterexample.get("validated") is True


def normalize_goal(record: dict[str, Any]) -> dict[str, Any]:
    verdict = str(record.get("verdict", "none")).lower()
    passed = record.get("passed") is True
    if explicit_violation(record):
        state = "violated"
    elif passed:
        state = "proved"
    else:
        state = "inconclusive"
    return {
        "goal": record.get("goal"),
        "property": record.get("property"),
        "file": record.get("file"),
        "line": record.get("line"),
        "function": record.get("function"),
        "behavior": record.get("behavior"),
        "category": goal_category(record),
        "state": state,
        "verdict": verdict,
        "passed": passed,
        "smoke": record.get("smoke", False),
        "provers": record.get("provers", []),
        "subgoals": record.get("subgoals", 1),
        "proved_subgoals": record.get("proved", 0),
        "failed_subgoals": record.get("failed", 0),
        "timeout_subgoals": record.get("timeout", 0),
        "unknown_subgoals": record.get("unknown", 0),
    }


def summarize_goals(records: list[dict[str, Any]]) -> dict[str, Any]:
    goals = [normalize_goal(record) for record in records]
    state_counts = Counter(goal["state"] for goal in goals)
    category_counts: dict[str, dict[str, int]] = {}
    for goal in goals:
        category_counts.setdefault(goal["category"], Counter())[goal["state"]] += 1
    if not goals:
        status = "inconclusive"
        reason = "WP report contained no proof goals"
    elif state_counts["violated"]:
        status = "violated"
        reason = "at least one goal has explicit validated violation evidence"
    elif state_counts["proved"] == len(goals):
        status = "proved"
        reason = "all reported proof goals passed"
    else:
        status = "inconclusive"
        reason = "one or more proof goals were not proved"
    return {
        "status": status,
        "reason": reason,
        "goal_count": len(goals),
        "state_counts": dict(sorted(state_counts.items())),
        "category_counts": {
            category: dict(sorted(counts.items()))
            for category, counts in sorted(category_counts.items())
        },
        "goals": goals,
    }


def build_command(args: argparse.Namespace, driver: Path, report: Path) -> list[str]:
    command = [
        args.frama_c,
        "-wp",
        "-wp-status",
        "-wp-fct", ",".join(TARGET_FUNCTIONS),
        "-wp-prover", args.provers,
        "-wp-timeout", str(args.wp_timeout),
        "-wp-model", args.memory_model,
        "-wp-report-json", str(report),
    ]
    if not args.no_rte:
        command.append("-wp-rte")
    command.extend(args.extra_arg)
    command.append(str(driver))
    return command


def run_target(
    args: argparse.Namespace,
    name: str,
    kind: str,
    driver: Path,
    output_dir: Path,
) -> dict[str, Any]:
    output_dir.mkdir(parents=True, exist_ok=False)
    report_path = output_dir / "wp-report.json"
    command = build_command(args, driver, report_path)
    command_record = {
        "target": name,
        "kind": kind,
        "driver": relative(driver),
        "driver_sha256": sha256_file(driver),
        "command": command,
        "command_shell_display": shlex.join(command),
        "cwd": relative(PROJECT),
    }
    write_json(output_dir / "command.json", command_record)
    timed_out = False
    execution_error: str | None = None
    try:
        completed = subprocess.run(
            command,
            cwd=PROJECT,
            capture_output=True,
            text=True,
            timeout=args.process_timeout,
            check=False,
            env=os.environ.copy(),
        )
        return_code: int | None = completed.returncode
        stdout = completed.stdout
        stderr = completed.stderr
    except subprocess.TimeoutExpired as error:
        return_code = None
        timed_out = True
        stdout = error.stdout.decode(errors="replace") if isinstance(error.stdout, bytes) else error.stdout or ""
        stderr = error.stderr.decode(errors="replace") if isinstance(error.stderr, bytes) else error.stderr or ""
        execution_error = f"Frama-C process exceeded {args.process_timeout} seconds"
    except OSError as error:
        return_code = None
        stdout = ""
        stderr = ""
        execution_error = str(error)
    (output_dir / "stdout.log").write_text(stdout, encoding="utf-8")
    (output_dir / "stderr.log").write_text(stderr, encoding="utf-8")

    parse_error: str | None = None
    records: list[dict[str, Any]] = []
    if report_path.is_file():
        try:
            records = report_records(json.loads(report_path.read_text(encoding="utf-8")))
        except (OSError, json.JSONDecodeError, ValueError) as error:
            parse_error = str(error)
    else:
        parse_error = "Frama-C did not create wp-report.json"
    proof = summarize_goals(records)
    if execution_error or return_code not in (0, None) or parse_error:
        proof["status"] = "inconclusive"
        proof["reason"] = execution_error or parse_error or f"Frama-C exited with {return_code}"
    outcome = {
        **command_record,
        "return_code": return_code,
        "timed_out": timed_out,
        "execution_error": execution_error,
        "report_error": parse_error,
        "proof": proof,
    }
    write_json(output_dir / "outcome.json", outcome)
    return outcome


def proof_bucket(outcome: dict[str, Any]) -> str:
    return {
        "proved": "proved_satisfaction",
        "violated": "demonstrated_violations",
        "inconclusive": "inconclusive",
    }[outcome["proof"]["status"]]


def frama_c_version(executable: str) -> str:
    completed = subprocess.run(
        [executable, "-version"], capture_output=True, text=True, timeout=15, check=False,
    )
    output = (completed.stdout + completed.stderr).strip()
    if completed.returncode != 0:
        raise InputError(f"Unable to query Frama-C version: {output}")
    return output


def parse_args() -> argparse.Namespace:
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("--frama-c", default="frama-c", help="Frama-C executable")
    parser.add_argument("--provers", default="qed", help="comma-separated WP provers")
    parser.add_argument("--wp-timeout", type=int, default=10, help="seconds per prover goal")
    parser.add_argument("--process-timeout", type=int, default=300, help="seconds per Frama-C process")
    parser.add_argument("--memory-model", default="Typed", help="WP memory model")
    parser.add_argument("--no-rte", action="store_true", help="disable WP runtime-error obligations")
    parser.add_argument("--extra-arg", action="append", default=[], help="extra Frama-C argument")
    parser.add_argument("--mutant", action="append", default=[], help="four-digit mutant ID")
    parser.add_argument("--baseline-only", action="store_true")
    parser.add_argument("--allow-inconclusive-baseline", action="store_true")
    parser.add_argument("--dry-run", action="store_true")
    parser.add_argument("--run-id", help="result directory name; defaults to a UTC timestamp")
    args = parser.parse_args()
    if args.wp_timeout <= 0 or args.process_timeout <= 0:
        parser.error("timeouts must be positive")
    if args.baseline_only and args.mutant:
        parser.error("--baseline-only cannot be combined with --mutant")
    for mutant_id in args.mutant:
        if not re.fullmatch(r"\d{4}", mutant_id):
            parser.error(f"invalid mutant ID: {mutant_id!r}")
    if args.run_id and not re.fullmatch(r"[A-Za-z0-9][A-Za-z0-9_.-]*", args.run_id):
        parser.error("--run-id must contain only letters, digits, dots, underscores, or hyphens")
    return args


def main() -> int:
    args = parse_args()
    try:
        manifest = load_and_validate_manifest()
        mutants = select_mutants(manifest, args.mutant)
    except (InputError, OSError, json.JSONDecodeError) as error:
        print(f"verification input error: {error}", file=sys.stderr)
        return 1

    targets = [("baseline", "baseline", BASELINE_DRIVER)]
    if not args.baseline_only:
        targets.extend(
            (
                f"mutant_{mutant['id']}",
                "mutant",
                PROJECT / mutant["driver"]["path"],
            )
            for mutant in mutants
        )
    if args.dry_run:
        for name, kind, driver in targets:
            output = (
                RESULTS / "<run-id>" / "baseline"
                if kind == "baseline"
                else RESULTS / "<run-id>" / "mutants" / name.removeprefix("mutant_")
            )
            report = output / "wp-report.json"
            print(shlex.join(build_command(args, driver, report)))
        return 0


    executable = shutil.which(args.frama_c) if os.sep not in args.frama_c else args.frama_c
    if not executable or not Path(executable).is_file():
        print(
            f"Frama-C executable not found: {args.frama_c}; install it or pass --frama-c PATH.",
            file=sys.stderr,
        )
        return 1
    args.frama_c = str(Path(executable).resolve())
    try:
        version = frama_c_version(args.frama_c)
    except (InputError, OSError, subprocess.TimeoutExpired) as error:
        print(str(error), file=sys.stderr)
        return 1

    run_id = args.run_id or datetime.now(timezone.utc).strftime("run_%Y%m%dT%H%M%SZ")
    run_dir = RESULTS / run_id
    try:
        run_dir.mkdir(parents=True, exist_ok=False)
    except FileExistsError:
        print(f"Result run already exists: {relative(run_dir)}", file=sys.stderr)
        return 1

    input_hashes = {
        "manifest": {"path": relative(MANIFEST), "sha256": sha256_file(MANIFEST)},
        "baseline_driver": {
            "path": relative(BASELINE_DRIVER), "sha256": sha256_file(BASELINE_DRIVER),
        },
        "contracts": [
            {"path": relative(contract), "sha256": sha256_file(contract)}
            for contract in CONTRACTS
        ],
    }
    summary: dict[str, Any] = {
        "schema_version": "1.0",
        "run_id": run_id,
        "created_at": datetime.now(timezone.utc).isoformat(),
        "frama_c_version": version,
        "settings": {
            "provers": args.provers,
            "wp_timeout_seconds": args.wp_timeout,
            "process_timeout_seconds": args.process_timeout,
            "memory_model": args.memory_model,
            "rte_obligations": not args.no_rte,
            "target_functions": list(TARGET_FUNCTIONS),
            "extra_arguments": args.extra_arg,
        },
        "classification_policy": {
            "proved": "all WP report goals have passed=true",
            "violated": "an invalid verdict or validated counterexample is explicit in report data",
            "inconclusive": sorted(INCONCLUSIVE_VERDICTS),
            "loop_failures": "recorded separately and never treated alone as a demonstrated violation",
        },
        "inputs": input_hashes,
        "baseline": None,
        "mutants": {
            "proved_satisfaction": [],
            "demonstrated_violations": [],
            "inconclusive": [],
            "skipped": [],
        },
    }

    baseline = run_target(args, "baseline", "baseline", BASELINE_DRIVER, run_dir / "baseline")
    summary["baseline"] = {
        "status": baseline["proof"]["status"],
        "reason": baseline["proof"]["reason"],
        "outcome": relative(run_dir / "baseline" / "outcome.json"),
    }
    baseline_proved = baseline["proof"]["status"] == "proved"

    if not args.baseline_only:
        baseline_inconclusive = baseline["proof"]["status"] == "inconclusive"
        if baseline_proved or (baseline_inconclusive and args.allow_inconclusive_baseline):
            for mutant in mutants:
                mutant_id = mutant["id"]
                output = run_target(
                    args,
                    f"mutant_{mutant_id}",
                    "mutant",
                    PROJECT / mutant["driver"]["path"],
                    run_dir / "mutants" / mutant_id,
                )
                record = {
                    "id": mutant_id,
                    "status": output["proof"]["status"],
                    "reason": output["proof"]["reason"],
                    "interpretable": baseline_proved,
                    "outcome": relative(run_dir / "mutants" / mutant_id / "outcome.json"),
                }
                summary["mutants"][proof_bucket(output)].append(record)
        else:
            summary["mutants"]["skipped"] = [
                {
                    "id": mutant["id"],
                    "reason": "baseline was not proved; use --allow-inconclusive-baseline to collect raw results",
                }
                for mutant in mutants
            ]
    summary["completed_at"] = datetime.now(timezone.utc).isoformat()
    write_json(run_dir / "summary.json", summary)
    print(f"Verification results: {relative(run_dir / 'summary.json')}")
    print(f"Baseline: {summary['baseline']['status']}")
    for bucket, records in summary["mutants"].items():
        print(f"Mutants {bucket}: {len(records)}")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
