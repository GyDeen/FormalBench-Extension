"""Run a small, separately recorded C diagnostic without resuming a study."""
from __future__ import annotations

import argparse
import json
import subprocess
from collections import Counter
from pathlib import Path

from verification.specification_evaluation.specifications.annotations import apply_specification
from verification.specification_evaluation.diagnostics.selection import add_selectors, select_cases
from verification.specification_evaluation.manifest import DEFAULT_C, DEFAULT_JAVA, DEFAULT_MANIFEST, InputError, load_population, sha256
from verification.specification_evaluation.backends.verifiers import Settings, build_command, executable_info, stage_c_support, support_hashes
from verification.specification_evaluation.workflow import evaluate_case, read_json, write_json


def main():
    """Preflight C transfers and rerun selected cases in a fresh diagnostic directory."""
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("--study", type=Path, required=True)
    parser.add_argument("--output", type=Path, required=True)
    add_selectors(parser)
    parser.add_argument("--goal-timeout", type=int, default=5)
    parser.add_argument("--timeout", type=int, default=60)
    args = parser.parse_args()
    if args.timeout <= 0 or args.goal_timeout <= 0:
        parser.error("budgets must be positive")
    study, output = args.study.resolve(), args.output.resolve()
    if output.exists():
        parser.error("use a fresh diagnostic output directory")
    config = read_json(study / "run.json")
    pop = load_population(Path(config.get("selection_manifest", DEFAULT_MANIFEST)),
                          Path(config.get("java_originals", DEFAULT_JAVA)),
                          Path(config.get("c_originals", DEFAULT_C)))
    try:
        selected = select_cases(pop, "c", args.program, args.case, args.source)
    except InputError as error:
        parser.error(str(error))
    if not selected:
        parser.error("select at least one --program, --case, or --source")
    for case in selected:
        if not (study / "frozen_specs/c" / f"{case.program}.json").is_file():
            parser.error(f"missing frozen C specification for {case.program}")
    prior = config["settings"]
    settings = Settings("openjml", config["verifiers"]["c"]["path"], prior["java_prover"],
                        prior["c_provers"], args.timeout, args.goal_timeout, prior["memory_model"],
                        prior["machdep"], prior["wp_memlimit"], prior["wp_par"],
                        Path(prior["why3_extra_config"]) if prior["why3_extra_config"] else None)
    executables = {"c": executable_info(settings.frama_c, "-version")}
    support = support_hashes()
    if pop.manifest_sha256 != config["selection_manifest_sha256"] or support != config["jarray_support_sha256"]:
        raise InputError("Diagnostic inputs differ from the frozen study")
    output.mkdir(parents=True)
    metadata = {"kind": "short_budget_diagnostic", "study": str(study), "cases": [c.key for c in selected],
                "sources": {c.key: str(c.raw) for c in selected},
                "goal_timeout": args.goal_timeout, "case_timeout": args.timeout,
                "selection_manifest_sha256": pop.manifest_sha256,
                "verifier": executables["c"], "results": []}
    write_json(output / "summary.json", metadata)
    # Transfer validation is cheap and does not run verifiers or solvers.
    transfers = []
    for pair in selected:
        frozen = study / "frozen_specs/c" / f"{pair.program}.json"
        try:
            result = apply_specification(pop.original(pair.program, "c").read_text(),
                                         read_json(frozen), pair.raw.read_text())
            row = {"case": pair.key, "status": "ok", "omitted_annotations": result.omitted_annotations}
        except InputError as error:
            row = {"case": pair.key, "status": "transfer_failed", "reason": str(error)}
        transfers.append(row)
    write_json(output / "transfer_preflight.json", transfers)
    print("Transfer preflight:", dict(Counter(r["status"] for r in transfers)), flush=True)
    for pair in selected:
        print("Checking", pair.key, flush=True)
        frozen = study / "frozen_specs/c" / f"{pair.program}.json"
        frozen_hash, source_hash = sha256(frozen), sha256(pair.raw)
        folder = pair.folder(output, "c")
        folder.mkdir(parents=True)
        try:
            transferred = apply_specification(pop.original(pair.program, "c").read_text(),
                                               read_json(frozen), pair.raw.read_text())
        except InputError as error:
            metadata["results"].append({"case": pair.key, "failure_stage": "annotation_transfer", "reason": str(error)})
            write_json(output / "summary.json", metadata)
            continue
        source = folder / pair.raw.name
        source.write_text(transferred.source)
        stage_c_support(folder)
        preflight = folder / "task_generation"
        preflight.mkdir()
        command = build_command("c", source, preflight, settings, executables) + ["-wp-gen"]
        write_json(preflight / "command.json", command)
        try:
            check = subprocess.run(command, cwd=folder, capture_output=True, text=True, timeout=args.timeout)
            log = check.stdout + check.stderr
            errors = (check.returncode != 0 or any(term in log for term in
                      ("Why3 Error", "User Error", "Fatal error", "[Failure]", "annot-error")))
        except subprocess.TimeoutExpired as error:
            log, errors = str(error), True
        (preflight / "output.log").write_text(log)
        if errors:
            row = {"case": pair.key, "failure_stage": "task_generation", "outcome": "syntax/tool failure"}
        else:
            record = evaluate_case(output, pop, pair.program, pair.role, pair.mutant_id, pair.selection,
                                   "c", pair.raw, frozen, settings, executables, support)
            row = {"case": pair.key, "task_generation": "passed", "outcome": record["outcome"],
                   "failure_stage": record.get("failure_stage"), "reason": record["reason"],
                   "elapsed_seconds": record.get("elapsed_seconds"), "timed_out": record.get("timed_out"),
                   "goals": dict(Counter(g["state"] for g in record["goals"])),
                   "omitted_annotations": record.get("transfer", {}).get("omitted_annotations", [])}
        if sha256(frozen) != frozen_hash or sha256(pair.raw) != source_hash:
            raise InputError("Diagnostic changed the frozen spec or executable mutant")
        metadata["results"].append(row)
        write_json(output / "summary.json", metadata)
        print(json.dumps(row), flush=True)
    metadata["complete"] = True
    write_json(output / "summary.json", metadata)
    return int(any(r.get("failure_stage") for r in metadata["results"]))


if __name__ == "__main__":
    raise SystemExit(main())
