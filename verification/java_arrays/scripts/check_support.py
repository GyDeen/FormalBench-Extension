from __future__ import annotations

import argparse
import hashlib
import json
import re
import shutil
import subprocess
import time
from datetime import datetime, timezone
from pathlib import Path

WORKSPACE = Path(__file__).resolve().parents[1]
PROJECT = WORKSPACE.parents[1]
METHODS = ["check_length", "check_reference", "check_index"] + [
    f"{prefix}_{method}"
    for prefix in ("jarray", "jbool_array", "jdouble_array", "jarray2", "jdouble_array2")
    for method in ("is_null", "length", "get", "set")
]
CLIENTS = ["check_predicate_equivalence", "check_set_get", "check_scalar_families", "check_int_rows",
           "check_double_rows", "negative_set_get"]


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("--frama-c", default="frama-c")
    parser.add_argument("--provers", default="qed,z3")
    parser.add_argument("--model", default="Typed")
    parser.add_argument("--machdep", default="macos_arm")
    parser.add_argument("--timeout", type=int, default=30)
    parser.add_argument("--run-timeout", type=int, default=180)
    parser.add_argument("--target", action="append",
                        choices=["implementation", *CLIENTS, "check_int_row_components"])
    parser.add_argument("--no-filter-init", action="store_true",
                        help="Retain initialization hypotheses in WP diagnostics")
    parser.add_argument("--functions", help="Override selected implementation functions (comma separated)")
    args = parser.parse_args()
    executable = shutil.which(args.frama_c)
    if executable is None:
        parser.error(f"Frama-C executable not found: {args.frama_c}")
    stamp = datetime.now(timezone.utc).strftime("%Y%m%dT%H%M%S_%fZ")
    output = WORKSPACE / "results" / f"support_{stamp}"
    output.mkdir(parents=True)
    inputs = sorted((WORKSPACE / "contracts").glob("*.h"))
    inputs += sorted((WORKSPACE / "clients").glob("*.c"))
    inputs += [WORKSPACE / "generated" / name for name in
               ("types.h", "baseline.c", "drivers/baseline.c")]
    inputs.append(Path(__file__).resolve())
    metadata = {
        "timestamp_utc": stamp, "settings": vars(args),
        "frama_c": executable, "inputs": {}, "runs": [],
        "interpretation": "Goal counts are not an overall verification verdict. "
        "Inspect warnings, unsupported features and negative-control obligations.",
    }
    for path in inputs:
        relative = str(path.relative_to(PROJECT))
        metadata["inputs"][relative] = hashlib.sha256(path.read_bytes()).hexdigest()
    for name, command in (
        ("frama_c_version", [executable, "-version"]),
        ("provers", [executable, "-wp-list-provers"]),
        ("generation_check", ["python3", "verification/java_arrays/scripts/prepare.py", "--check"]),
    ):
        result = subprocess.run(command, cwd=PROJECT, capture_output=True, text=True)
        metadata[name] = {"command": command, "returncode": result.returncode,
                          "output": result.stdout + result.stderr}
    (output / "metadata.json").write_text(json.dumps(metadata, indent=2) + "\n")
    print(f"Results: {output.relative_to(PROJECT)}", flush=True)
    for target in args.target or ["implementation", *CLIENTS]:
        target_dir = output / target
        target_dir.mkdir()
        source = (WORKSPACE / "generated/drivers/baseline.c" if target == "implementation"
                  else WORKSPACE / "clients" / f"{target}.c")
        command = [executable, "-machdep", args.machdep, "-wp", "-wp-rte",
                   "-wp-model", args.model, "-wp-prover", args.provers,
                   "-wp-timeout", str(args.timeout), "-wp-par", "4",
                   "-wp-cache", "none", "-wp-report-json", str(target_dir / "wp.json"),
                   "-wp-deprecated-report-json", str(target_dir / "goals.json")]
        if target == "implementation":
            command += ["-wp-fct", args.functions or ",".join(METHODS)]
        if args.no_filter_init:
            command += ["-wp-no-filter-init"]
        command += [str(source.relative_to(PROJECT))]
        (target_dir / "command.json").write_text(json.dumps(command, indent=2) + "\n")
        print(f"Running {target}...", flush=True)
        started = time.monotonic()
        timed_out = False
        with (target_dir / "stdout.txt").open("w") as stdout, (target_dir / "stderr.txt").open("w") as stderr:
            process = subprocess.Popen(command, cwd=PROJECT, stdout=stdout, stderr=stderr)
            try:
                process.wait(timeout=args.run_timeout)
            except subprocess.TimeoutExpired:
                timed_out = True
                process.terminate()
                try:
                    process.wait(timeout=5)
                except subprocess.TimeoutExpired:
                    process.kill()
                    process.wait()
        text = (target_dir / "stdout.txt").read_text() + (target_dir / "stderr.txt").read_text()
        matches = re.findall(r"Proved goals:\s+(\d+)\s*/\s*(\d+)", text)
        counts = {"proved": int(matches[-1][0]), "total": int(matches[-1][1])} if matches else None
        record = {"target": target, "returncode": process.returncode, "timed_out": timed_out,
                  "elapsed_seconds": round(time.monotonic() - started, 2), "counts": counts,
                  "warning_lines": [line for line in text.splitlines()
                                    if "Warning:" in line or "not yet implemented" in line]}
        report = target_dir / "wp.json"
        if report.exists():
            goals = json.loads(report.read_text())
            if isinstance(goals, list):
                record["reported_goals"] = {
                    "proved": sum(goal.get("passed") is True for goal in goals),
                    "total": len(goals),
                }
                record["unproved"] = [
                    {key: goal.get(key) for key in ("function", "property", "verdict")}
                    for goal in goals if goal.get("passed") is not True
                ]
        metadata["runs"].append(record)
        (output / "metadata.json").write_text(json.dumps(metadata, indent=2) + "\n")
        print(json.dumps(record), flush=True)
    return int(any(run["returncode"] != 0 for run in metadata["runs"]))


if __name__ == "__main__":
    raise SystemExit(main())
