"""Check selected Java sources or recorded tool failures outside the full study."""
from __future__ import annotations

import argparse
import json
from pathlib import Path

from .diagnostic_selection import add_selectors, select_cases
from .manifest import DEFAULT_C, DEFAULT_JAVA, DEFAULT_MANIFEST, InputError, load_population, sha256
from .verifiers import Settings, executable_info, java_tool_error
from .workflow import evaluate_case, read_json, write_json


def main():
    """Verify selected Java cases, defaulting to the study's recorded tool failures."""
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("--study", type=Path, required=True)
    parser.add_argument("--output", type=Path, required=True)
    add_selectors(parser)
    parser.add_argument("--goal-timeout", type=int, default=5)
    parser.add_argument("--timeout", type=int, default=60)
    args = parser.parse_args()
    if min(args.goal_timeout, args.timeout) <= 0:
        parser.error("budgets must be positive")
    study, output = args.study.resolve(), args.output.resolve()
    if output.exists():
        parser.error("use a fresh diagnostic output directory")
    config = read_json(study / "run.json")
    pop = load_population(Path(config.get("selection_manifest", DEFAULT_MANIFEST)),
                          Path(config.get("java_originals", DEFAULT_JAVA)),
                          Path(config.get("c_originals", DEFAULT_C)))
    if pop.manifest_sha256 != config["selection_manifest_sha256"]:
        raise InputError("Diagnostic manifest differs from the frozen study")
    explicit = bool(args.program or args.case or args.source)
    case_keys = args.case
    if not explicit:
        failed = [r for path in sorted((study / "cases").glob("*/*/java/record.json"))
                  if (r := read_json(path))["outcome"] == "syntax/tool failure"]
        if not failed:
            parser.error("no recorded Java tool failures; select --program, --case, or --source")
        case_keys = [r['program'] + '/' + ('original' if r['role'] == 'original' else str(r['mutant_id']))
                     for r in failed]
    try:
        selected = select_cases(pop, "java", args.program, case_keys, args.source)
    except InputError as error:
        parser.error(str(error))
    # Validate all requested cases before launching the first verifier.
    previous = {}
    for target in selected:
        frozen = study / "frozen_specs/java" / f"{target.program}.json"
        if not frozen.is_file():
            parser.error(f"missing frozen Java specification for {target.program}")
        old_path = target.folder(study, "java") / "record.json"
        old = read_json(old_path) if old_path.is_file() else None
        if old and (sha256(target.raw) != old['raw_source_sha256']
                    or sha256(frozen) != old['frozen_spec_sha256']):
            raise InputError(f"Saved diagnostic inputs changed: {target.key}")
        previous[target.key] = old
    prior = config["settings"]
    settings = Settings(config["verifiers"]["java"]["path"], "frama-c", prior["java_prover"],
                        prior["c_provers"], args.timeout, args.goal_timeout, prior["memory_model"],
                        prior["machdep"], prior["wp_memlimit"], prior["wp_par"], None)
    executables = {"java": executable_info(settings.openjml, "--version")}
    output.mkdir(parents=True)
    metadata = {"kind": "short_budget_java_tool_repair_diagnostic", "study": str(study),
                "selection_mode": "explicit" if explicit else "recorded_tool_failures",
                "cases": [c.key for c in selected], "sources": {c.key: str(c.raw) for c in selected},
                "goal_timeout": args.goal_timeout, "case_timeout": args.timeout,
                "verifier": executables["java"], "results": [], "complete": False}
    write_json(output / "summary.json", metadata)
    for target in selected:
        program, role, mutant = target.program, target.role, target.mutant_id
        case, raw, old = target.key, target.raw, previous[target.key]
        print("Checking", case, flush=True)
        frozen = study / "frozen_specs/java" / f"{program}.json"
        source_hash, spec_hash = sha256(raw), sha256(frozen)
        record = evaluate_case(output, pop, program, role, mutant, target.selection,
                               "java", raw, frozen, settings, executables, {})
        folder = output / "cases" / program / ("original" if role == "original" else f"mutant_{mutant}") / "java"
        log = "\n".join(p.read_text() for p in (folder / "stdout.log", folder / "stderr.log") if p.exists())
        row = {"case": case, "previous_outcome": old["outcome"] if old else None, "outcome": record["outcome"],
               "failure_stage": record.get("failure_stage"), "elapsed_seconds": record.get("elapsed_seconds"),
               "exit_code": record.get("exit_code"), "timed_out": record.get("timed_out"),
               "tool_error_in_log": java_tool_error(log),
               "esc_started": "Starting proof of" in log,
               "compiler_normalizations": [line for line in log.splitlines() if line.startswith("[NumericBitPredicates]")],
               "record": str(folder / "record.json")}
        if sha256(raw) != source_hash or sha256(frozen) != spec_hash:
            raise InputError("Diagnostic modified the raw program or frozen specification")
        metadata["results"].append(row)
        write_json(output / "summary.json", metadata)
        print(json.dumps(row), flush=True)
    metadata["complete"] = True
    write_json(output / "summary.json", metadata)
    return int(any(r["tool_error_in_log"] or r["outcome"] == "syntax/tool failure"
                   for r in metadata["results"]))


if __name__ == "__main__":
    raise SystemExit(main())
