"""Check JArray support contracts using the local WSL/opam toolchain."""

from __future__ import annotations

import argparse
import hashlib
import json
import re
import shutil
import subprocess
import sys
import time
from datetime import datetime, timezone
from pathlib import Path

WORKSPACE = Path(__file__).resolve().parents[1]
PROJECT = WORKSPACE.parents[1]
DEFAULT_WHY3_CONFIG = Path(__file__).with_name("why3-alt-ergo-2.4.3.conf")
METHODS = ["check_length", "check_reference", "check_index"] + [
    f"{prefix}_{method}"
    for prefix in ("jarray", "jbool_array", "jdouble_array", "jarray2", "jdouble_array2")
    for method in ("is_null", "length", "get", "set")
]
CLIENTS = ["check_predicate_equivalence", "check_set_get", "check_scalar_families", "check_int_rows",
           "check_double_rows", "negative_set_get"]


def positive_int(value: str) -> int:
    number = int(value)
    if number <= 0:
        raise argparse.ArgumentTypeError("must be greater than zero")
    return number


def parse_args(argv: list[str] | None = None) -> argparse.Namespace:
    parser = argparse.ArgumentParser(description=__doc__,
                                     formatter_class=argparse.ArgumentDefaultsHelpFormatter)
    parser.add_argument("--frama-c", default="frama-c")
    parser.add_argument("--provers", default="Alt-Ergo:2.4.3,Z3:4.8.12",
                        help="Comma-separated WP prover selections; Qed simplification remains enabled")
    parser.add_argument("--model", default="Typed+ref", help="WP memory model")
    parser.add_argument("--machdep", default="x86_64", help="Frama-C target machine model")
    parser.add_argument("--timeout", type=positive_int, default=10,
                        help="Seconds per prover goal")
    parser.add_argument("--run-timeout", type=positive_int, default=300,
                        help="Wall-clock seconds per target")
    parser.add_argument("--memlimit", type=positive_int, default=1000,
                        help="WP solver memory limit in MB per process")
    parser.add_argument("--wp-par", type=positive_int, default=4,
                        help="Maximum concurrent prover processes")
    config = parser.add_mutually_exclusive_group()
    # Apply the default after parsing: argparse can otherwise treat an explicit
    # value identical to its default as absent when checking mutual exclusion.
    config.add_argument("--why3-extra-config", default=argparse.SUPPRESS,
                        help="Why3 configuration file (relative paths use the calling directory; "
                             f"default: {DEFAULT_WHY3_CONFIG})")
    config.add_argument("--no-why3-extra-config", action="store_const", const=None,
                        default=argparse.SUPPRESS, dest="why3_extra_config",
                        help="Use only the machine's Why3 configuration")
    parser.add_argument("--target", action="append",
                        choices=["implementation", *CLIENTS, "check_int_row_components"])
    parser.add_argument("--no-filter-init", action="store_true",
                        help="Retain initialization hypotheses in WP diagnostics")
    parser.add_argument("--functions", help="Override selected implementation functions (comma separated)")
    args = parser.parse_args(argv)
    if not hasattr(args, "why3_extra_config"):
        args.why3_extra_config = str(DEFAULT_WHY3_CONFIG)
    if args.why3_extra_config is not None:
        path = Path(args.why3_extra_config).expanduser().resolve()
        if not path.is_file():
            parser.error(f"Why3 configuration file not found: {path}")
        args.why3_extra_config = str(path)
    return args


def why3_options(args: argparse.Namespace) -> list[str]:
    return (["-wp-why3-extra-config", args.why3_extra_config]
            if args.why3_extra_config is not None else [])


def preflight_commands(args: argparse.Namespace, executable: str) -> list[tuple[str, list[str]]]:
    return [
        ("frama_c_version", [executable, "-version"]),
        ("provers", [executable, *why3_options(args), "-wp-list-provers"]),
        ("generation_check", [sys.executable, "verification/java_arrays/scripts/prepare.py", "--check"]),
    ]


def build_command(args: argparse.Namespace, executable: str, target: str,
                  target_dir: Path) -> list[str]:
    source = (WORKSPACE / "generated/drivers/baseline.c" if target == "implementation"
              else WORKSPACE / "clients" / f"{target}.c")
    command = [executable, "-machdep", args.machdep, "-wp", "-wp-rte",
               "-wp-model", args.model, *why3_options(args), "-wp-prover", args.provers,
               "-wp-timeout", str(args.timeout), "-wp-memlimit", str(args.memlimit),
               "-wp-par", str(args.wp_par), "-wp-cache", "none",
               "-wp-report-json", str(target_dir / "wp.json"),
               "-wp-deprecated-report-json", str(target_dir / "goals.json")]
    if target == "implementation":
        command += ["-wp-fct", args.functions or ",".join(METHODS)]
    if args.no_filter_init:
        command += ["-wp-no-filter-init"]
    command += [str(source.relative_to(PROJECT))]
    return command


def main(argv: list[str] | None = None) -> int:
    args = parse_args(argv)
    executable = shutil.which(args.frama_c)
    if executable is None:
        print(f"Frama-C executable not found: {args.frama_c}. "
              "On this Windows PC, run inside ResearchUbuntuNoble WSL with the "
              "default opam switch active (see verification/java_arrays/README.md).",
              file=sys.stderr)
        return 2
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
    if args.why3_extra_config is not None:
        metadata["why3_extra_config"] = {
            "path": args.why3_extra_config,
            "sha256": hashlib.sha256(Path(args.why3_extra_config).read_bytes()).hexdigest(),
        }
    for path in inputs:
        relative = str(path.relative_to(PROJECT))
        metadata["inputs"][relative] = hashlib.sha256(path.read_bytes()).hexdigest()
    failed_checks = []
    for name, command in preflight_commands(args, executable):
        result = subprocess.run(command, cwd=PROJECT, capture_output=True, text=True)
        metadata[name] = {"command": command, "returncode": result.returncode,
                          "output": result.stdout + result.stderr}
        if result.returncode != 0:
            failed_checks.append(name)
    (output / "metadata.json").write_text(json.dumps(metadata, indent=2) + "\n")
    print(f"Results: {output.relative_to(PROJECT)}", flush=True)
    if failed_checks:
        print(f"Preflight checks failed: {', '.join(failed_checks)}. "
              "See metadata.json; no proof targets were started.", file=sys.stderr)
        return 1
    for target in args.target or ["implementation", *CLIENTS]:
        target_dir = output / target
        target_dir.mkdir()
        command = build_command(args, executable, target, target_dir)
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
    return int(any(run["returncode"] != 0 or run["timed_out"] for run in metadata["runs"]))


if __name__ == "__main__":
    raise SystemExit(main())
