"""Freeze original specifications, verify originals, then replay selected pairs."""

from __future__ import annotations

import hashlib
import json
import shlex
import subprocess
from datetime import datetime, timezone
from collections import Counter
from concurrent.futures import ThreadPoolExecutor, as_completed
from queue import Queue, Empty
from pathlib import Path
from threading import Event
from typing import Any

from .annotations import Transfer, apply_specification, extract_specification
from .manifest import InputError, Population, sha256
from .verifiers import (
    Settings,
    executable_info,
    run_verifier,
    stage_c_support,
    support_hashes,
)


def digest(value: Any) -> str:
    return hashlib.sha256(json.dumps(value, sort_keys=True, separators=(",", ":")).encode()).hexdigest()


def write_json(path: Path, value: Any) -> None:
    """Replace a JSON artifact atomically so interrupted writes stay recoverable."""
    path.parent.mkdir(parents=True, exist_ok=True)
    temporary = path.with_name(path.name + ".tmp")
    temporary.write_text(json.dumps(value, indent=2, sort_keys=True) + "\n", encoding="utf-8")
    temporary.replace(path)


def read_json(path: Path) -> Any:
    return json.loads(path.read_text(encoding="utf-8"))


def run_case_queues(tasks: dict[str, list[Any]], workers: dict[str, int]) -> None:
    """Drain independent language queues with bounded workers and graceful stop."""
    stop = Event()
    queues = {language: Queue() for language in tasks}
    for language, items in tasks.items():
        for task in items:
            queues[language].put(task)

    def worker(language):
        while not stop.is_set():
            try:
                task = queues[language].get_nowait()
            except Empty:
                return
            try:
                task()
            except BaseException:
                stop.set()
                raise

    executor = ThreadPoolExecutor(max_workers=sum(workers[lang] for lang in tasks))
    futures = [executor.submit(worker, lang) for lang in tasks for _ in range(workers[lang])]
    try:
        for future in as_completed(futures):
            future.result()
    except BaseException:
        stop.set()
        for future in futures:
            future.cancel()
        raise
    finally:
        executor.shutdown(wait=True, cancel_futures=True)


def read_frozen_spec(path: Path, program: str, language: str) -> dict[str, Any]:
    """Reject a JSON file filed under the wrong program or language."""
    specification = read_json(path)
    if (not isinstance(specification, dict)
            or specification.get("program") != program
            or specification.get("language") != language):
        raise InputError(f"Frozen specification names the wrong program/language: {path}")
    return specification


def _spec_path(spec_directory: Path | None, program: str, language: str) -> Path | None:
    if spec_directory is None:
        return None
    return spec_directory / f"{program}.{ 'java' if language == 'java' else 'c' }"


def _generate_spec(command_template: str, source: Path, target: Path,
                   log: Path) -> None:
    """Run an external spec generator on one original and retain its command log."""
    if "{source}" not in command_template or "{output}" not in command_template:
        raise InputError("Generator command must contain both {source} and {output}")
    target.parent.mkdir(parents=True, exist_ok=True)
    command = [part.replace("{source}", str(source)).replace("{output}", str(target))
               for part in shlex.split(command_template)]
    before = sha256(source)
    try:
        result = subprocess.run(command, capture_output=True, text=True, timeout=1800, check=False)
    except (OSError, subprocess.TimeoutExpired) as error:
        raise InputError(f"Specification generator failed for {source.name}: {error}") from error
    write_json(log, {"command": command, "exit_code": result.returncode,
                     "stdout": result.stdout, "stderr": result.stderr})
    if result.returncode != 0 or not target.is_file():
        raise InputError(f"Specification generator did not produce {target}; see {log}")
    if sha256(source) != before:
        raise InputError(f"Generator modified executable original: {source}")


def freeze_specs(population: Population, programs: tuple[str, ...], output: Path,
                 java_specs: Path | None, c_specs: Path | None,
                 java_generator: str | None, c_generator: str | None) -> dict[tuple[str, str], Path]:
    """Freeze one structured JML/ACSL JSON specification per original.

    Verification and mutant replay must use these exact files, not regenerated
    or repaired specifications after a proof result is known.
    """
    manifest_path = output / "frozen_specs" / "manifest.json"
    if manifest_path.is_file():
        existing_manifest = read_json(manifest_path)
        if not isinstance(existing_manifest, dict) or existing_manifest.get("schema_version") != "2.0":
            raise InputError("Existing frozen manifest predates structured JSON; use a new output directory")
    frozen: dict[tuple[str, str], Path] = {}
    entries: list[dict[str, Any]] = []
    for program in programs:
        for language, source_dir, generator in (
            ("java", java_specs, java_generator), ("c", c_specs, c_generator)
        ):
            raw = population.original(program, language)
            raw_text = raw.read_text(encoding="utf-8")
            destination = output / "frozen_specs" / language / f"{program}.json"
            candidate = _spec_path(source_dir, program, language)
            if not destination.is_file():
                # A supplied annotation takes precedence; otherwise generate
                # from this language's original, never from any mutant.
                if candidate is None or not candidate.is_file():
                    if generator is None:
                        raise InputError(f"Missing annotated {language} original for {program}")
                    candidate = output / "generated_specs" / language / raw.name
                    if not candidate.is_file():
                        _generate_spec(generator, raw, candidate,
                                       output / "generated_specs" / language / f"{program}.command.json")
                annotated = candidate.read_text(encoding="utf-8")
                specification = extract_specification(raw_text, annotated, program, language)
                apply_specification(raw_text, specification, raw_text)
                write_json(destination, specification)
            else:
                # Resuming reads the frozen JSON; supplied annotated source,
                # if present, must still extract to exactly the same records.
                specification = read_frozen_spec(destination, program, language)
                apply_specification(raw_text, specification, raw_text)
                if candidate is not None and candidate.is_file():
                    supplied = extract_specification(raw_text, candidate.read_text(encoding="utf-8"),
                                                      program, language)
                    if supplied != specification:
                        raise InputError(f"Supplied specification changed after freezing: {candidate}")
            frozen[(program, language)] = destination
            entries.append({"program": program, "language": language,
                            "original": str(raw), "original_sha256": sha256(raw),
                            "frozen_spec": str(destination), "spec_sha256": sha256(destination),
                            "annotation_count": len(specification["annotations"])})
    if manifest_path.is_file():
        # Extend a pilot run with new programs, but refuse changed entries.
        previous = read_json(manifest_path)
        previous_entries = {(entry["program"], entry["language"]): entry
                            for entry in previous.get("specifications", [])}
        for entry in entries:
            old = previous_entries.get((entry["program"], entry["language"]))
            # Allow the output folder to be renamed between stages. The
            # frozen-spec path changes with that folder, but the source and
            # specification contents must remain identical.
            if (old is not None
                    and {key: value for key, value in old.items() if key != "frozen_spec"}
                    != {key: value for key, value in entry.items() if key != "frozen_spec"}):
                raise InputError(f"Frozen specification or original changed: {entry['program']} {entry['language']}")
        previous_entries.update({(entry["program"], entry["language"]): entry for entry in entries})
        entries = list(previous_entries.values())
    write_json(manifest_path, {"schema_version": "2.0", "specifications": sorted(
        entries, key=lambda entry: (entry["program"], entry["language"]))})
    return frozen


def validate_transfers(population: Population, programs: tuple[str, ...],
                       java_specs: Path | None, c_specs: Path | None,
                       frozen_root: Path | None = None) -> dict[str, int]:
    """Dry-run JSON attachment for originals and all selected mutants."""
    counts = Counter()
    specifications: dict[tuple[str, str], dict[str, Any]] = {}
    for program in programs:
        for language, directory in (("java", java_specs), ("c", c_specs)):
            raw = population.original(program, language).read_text(encoding="utf-8")
            if frozen_root is not None:
                structured = read_frozen_spec(frozen_root / language / f"{program}.json",
                                               program, language)
            else:
                spec = _spec_path(directory, program, language)
                if spec is None or not spec.is_file():
                    raise InputError(f"Missing annotated {language} original for {program}")
                structured = extract_specification(raw, spec.read_text(encoding="utf-8"),
                                                    program, language)
            apply_specification(raw, structured, raw)
            specifications[(program, language)] = structured
            counts[f"original_{language}"] += 1
    for pair in population.pairs:
        if pair.program not in programs:
            continue
        for language, mutant in (("java", pair.java), ("c", pair.c)):
            apply_specification(population.original(pair.program, language).read_text(encoding="utf-8"),
                                specifications[(pair.program, language)],
                                mutant.read_text(encoding="utf-8"))
            counts[f"mutant_{language}"] += 1
    return dict(sorted(counts.items()))


def _case_dir(output: Path, program: str, role: str, mutant_id: str | None,
              language: str) -> Path:
    return output / "cases" / program / ("original" if role == "original" else f"mutant_{mutant_id}") / language


def _settings_record(settings: Settings) -> dict[str, Any]:
    return {**({"capture_c_counterexamples": True} if settings.capture_c_counterexamples else {}),
            "java_prover": settings.java_prover, "c_provers": settings.c_provers,
            "timeout": settings.timeout, "goal_timeout": settings.goal_timeout,
            "memory_model": settings.memory_model, "machdep": settings.machdep,
            "wp_memlimit": settings.wp_memlimit, "wp_par": settings.wp_par,
            "why3_extra_config": str(settings.why3_extra_config) if settings.why3_extra_config else None,
            "why3_extra_config_sha256": sha256(settings.why3_extra_config)
            if settings.why3_extra_config else None}


def _store_record(case_dir: Path, record: dict[str, Any]) -> dict[str, Any]:
    if record.get("outcome") != "not run":
        record = {**record, "attempt_status": "complete"}
    write_json(case_dir / "record.json", record)
    return record


def _retryable_tool_failure(record: dict[str, Any]) -> bool:
    """An explicit retry covers all tool stages and interrupted attempts.

    One invocation visits each selected case once. Historical archives do not
    block an explicit retry, including after an external tool/configuration fix.
    """
    return (record.get("outcome") == "syntax/tool failure"
            or record.get("outcome") == "not run"
            and record.get("attempt_status") in {"running", "interrupted"})


def _latest_archive(output: Path, program: str, case_dir: Path, language: str):
    root = output / "history" / program / case_dir.parent.name / language
    return next(iter(sorted(root.glob("*/record.json"), reverse=True)), None)


def _reuse_original_for_mutants(output: Path, population: Population, program: str,
                                language: str, frozen_spec: Path, settings: Settings,
                                executables: dict[str, dict[str, Any]],
                                support: dict[str, str]) -> dict[str, Any]:
    """Validate and reuse an original record without invoking its verifier again."""
    case_dir = _case_dir(output, program, "original", None, language)
    record_path = case_dir / "record.json"
    if not record_path.is_file():
        raise InputError(f"Verify the {language} original before its mutants: {program}")
    record = read_json(record_path)
    raw = population.original(program, language)
    expected = {
        "raw_source": str(raw),
        "raw_source_sha256": sha256(raw),
        "frozen_spec_sha256": sha256(frozen_spec),
        "selection_manifest_sha256": population.manifest_sha256,
        "support_sha256": support if language == "c" else {},
        "verifier": executables[language],
        "settings": _settings_record(settings),
    }
    for key, value in expected.items():
        if record.get(key) != value:
            raise InputError(f"Saved {language} original no longer matches {key}: {program}")
    if record.get("outcome") in {None, "not run"}:
        raise InputError(f"Saved {language} original has no completed outcome: {program}")

    # Refactored adapters can be reused only if their attachment to this
    # original is byte-for-byte identical to the source already verified.
    staged_source = case_dir / raw.name
    if not staged_source.is_file():
        raise InputError(f"Saved annotated original is missing: {staged_source}")
    raw_text = raw.read_text(encoding="utf-8")
    specification = read_frozen_spec(frozen_spec, program, language)
    expected_source = apply_specification(raw_text, specification, raw_text).source
    if staged_source.read_text(encoding="utf-8") != expected_source:
        raise InputError(f"Current annotation transfer changes the saved original: {program} {language}")
    if record.get("annotated_source_sha256") != sha256(staged_source):
        raise InputError(f"Saved annotated original hash does not match its record: {program} {language}")
    return record


def evaluate_case(output: Path, population: Population, program: str, role: str,
                  mutant_id: str | None, selection: str | None, language: str,
                  raw: Path, frozen_spec: Path, settings: Settings,
                  executables: dict[str, dict[str, Any]], support: dict[str, str],
                  retry_tool_failure: bool = False,
                  capture_java_workload: bool = False) -> dict[str, Any]:
    """Verify one FormalBench source and keep its complete result."""
    case_dir = _case_dir(output, program, role, mutant_id, language)
    case_dir.mkdir(parents=True, exist_ok=True)
    base = {"program": program, "role": role, "mutant_id": mutant_id,
            "selection": selection, "language": language,
            "raw_source": str(raw), "raw_source_sha256": sha256(raw),
            "frozen_spec": str(frozen_spec), "frozen_spec_sha256": sha256(frozen_spec),
            "selection_manifest_sha256": population.manifest_sha256,
            "support_sha256": support if language == "c" else {},
            "verifier": executables[language], "settings": _settings_record(settings)}
    if language == "c":
        base["adapter_sha256"] = {name: sha256(Path(__file__).parent / name)
                                  for name in ("annotations.py", "c_bindings.py", "c_structure.py", "verifiers.py")}
    else:
        base["adapter_sha256"] = {name: sha256(Path(__file__).parent / name)
                                  for name in ("annotations.py", "c_bindings.py", "c_structure.py", "java_compat.py", "verifiers.py")}
    if language == "java" and capture_java_workload:
        base["java_workload_capture"] = {
            name: sha256(Path(__file__).parent / name)
            for name in ("java_workload.py", "java_solver_trace.py")}
    if language == "c" and settings.capture_c_counterexamples:
        base["c_counterexample_capture"] = {
            "c_counterexamples.py": sha256(Path(__file__).with_name("c_counterexamples.py"))}
    fingerprint = digest(base)
    prior = case_dir / "record.json"
    recovery = (_latest_archive(output, program, case_dir, language)
                if retry_tool_failure and not prior.is_file() else None)
    if prior.is_file() or recovery:
        # Resume only when source, frozen specification, tool and settings
        # still match the previously recorded case.
        record = read_json(prior if prior.is_file() else recovery)
        if retry_tool_failure:
            if not recovery and not _retryable_tool_failure(record):
                return record
            for key in ("raw_source_sha256", "frozen_spec_sha256", "selection_manifest_sha256",
                        "support_sha256", "settings"):
                if record.get(key) != base[key]:
                    raise InputError(f"Retry would change the frozen study inputs: {key}: {case_dir}")
            stamp = datetime.now(timezone.utc).strftime("%Y%m%dT%H%M%S%fZ")
            archive = output / "history" / program / case_dir.parent.name / language / stamp
            archive.parent.mkdir(parents=True, exist_ok=True)
            case_dir.rename(archive)
            case_dir.mkdir(parents=True)
            prior = case_dir / "record.json"
        elif record.get("input_fingerprint") != fingerprint:
            raise InputError(f"Existing case has different inputs: {case_dir}")
        elif record.get("outcome") != "not run":
            return record
    record = {**base, "input_fingerprint": fingerprint}
    # Keep a recoverable current record while the previous attempt is archived.
    _store_record(case_dir, {**record, "outcome": "not run", "attempt_status": "running",
                             "reason": "Evaluation in progress", "goals": []})
    raw_original = population.original(program, language).read_text(encoding="utf-8")
    specification = read_frozen_spec(frozen_spec, program, language)
    try:
        # Both the original and its mutants are built from untouched source
        # plus the same annotation text in the frozen JSON file.
        result: Transfer = apply_specification(raw_original, specification,
                                               raw.read_text(encoding="utf-8"))
        annotated_source = result.source
        transfer_record = {"annotation_count": len(result.annotations),
                           "executable_token_sha256": result.executable_token_sha256,
                           "placements": result.annotations,
                           "omitted_annotations": result.omitted_annotations,
                           "removed_loop_annotations": sum(a.get("target") == "loop"
                                                            for a in result.omitted_annotations),
                           "internal_annotation_coverage": (
                               "partial_due_to_deleted_loop" if any(a.get("target") == "loop"
                                                                    for a in result.omitted_annotations)
                               else "complete"),
                           "removed_temporary_policy": "original pure initializer with stable dependencies",
                           "method": "frozen structured JSON with audited C declaration bindings" if language == "c"
                           else "frozen structured JSON"}
    except InputError as error:
        # Unsafe placement is an evaluation failure, not a killed mutant.
        return _store_record(case_dir, {**record, "outcome": "syntax/tool failure",
                                         "failure_stage": "annotation_transfer",
                                         "reason": f"Annotation transfer failed: {error}", "goals": []})
    source = case_dir / raw.name
    if source.is_file() and source.read_text(encoding="utf-8") != annotated_source:
        raise InputError(f"Staged source changed after freezing: {source}")
    if not source.is_file():
        source.write_text(annotated_source, encoding="utf-8")
    write_json(case_dir / "transfer.json", transfer_record)
    if language == "c":
        # These headers are fixed assumptions for the translated C program;
        # no JArray implementation/client is submitted as a scored case.
        staged_support = stage_c_support(case_dir)
        if staged_support != support:
            raise InputError("Staged C contracts differ from the fixed support hashes")
    try:
        if language == "java" and capture_java_workload:
            from .java_workload import run_java_workload
            verification = run_java_workload(source, case_dir, settings, executables)
        else:
            verification = run_verifier(language, source, case_dir, settings, executables)
    except KeyboardInterrupt:
        _store_record(case_dir, {**record, "transfer": transfer_record, "outcome": "not run",
                                 "attempt_status": "interrupted", "reason": "Verification interrupted",
                                 "goals": []})
        raise
    return _store_record(case_dir, {**record, "annotated_source": str(source),
                                     "annotated_source_sha256": sha256(source),
                                     "transfer": transfer_record, **verification})


def summarize(output: Path, population: Population) -> dict[str, Any]:
    """Count proof outcomes over the manifest's eligible paired population."""
    originals = Counter()
    original_outcomes: dict[tuple[str, str], str] = {}
    mutants = {"java": Counter(), "c": Counter()}
    mutants_by_original: dict[str, dict[str, Counter]] = {"java": {}, "c": {}}
    pair_states = Counter()
    detail: list[dict[str, Any]] = []
    for program in population.programs:
        for language in ("java", "c"):
            path = _case_dir(output, program, "original", None, language) / "record.json"
            outcome = read_json(path)["outcome"] if path.is_file() else "not run"
            original_outcomes[(program, language)] = outcome
            originals[(language, outcome)] += 1
    for pair in population.pairs:
        outcomes: dict[str, str] = {}
        pair_originals: dict[str, str] = {}
        for language in ("java", "c"):
            original_outcome = original_outcomes.get((pair.program, language), "not run")
            pair_originals[language] = original_outcome
            path = _case_dir(output, pair.program, "mutant", pair.mutant_id, language) / "record.json"
            outcome = read_json(path)["outcome"] if path.is_file() else "not run"
            outcomes[language] = outcome
            mutants[language][outcome] += 1
            mutants_by_original[language].setdefault(original_outcome, Counter())[outcome] += 1
        values = set(outcomes.values())
        # A pair is decisive only when both tools produced a proof or a
        # specification-rejection outcome. Unknown/support/tool cases stay out
        # of the paired agreement numerator.
        if "not run" in values:
            state = "not run"
        elif "syntax/tool failure" in values:
            state = "tool failure"
        elif "precondition/RTE failure" in values:
            state = "precondition/RTE failure"
        elif "unknown/timeout" in values:
            state = "unknown/timeout"
        elif values == {"specification violation"}:
            state = "both rejected"
        elif values == {"proved"}:
            state = "both proved"
        else:
            state = "Java/C disagreement"
        pair_states[state] += 1
        detail.append({"mutant": pair.key, "selection": pair.selection,
                       "java_original": pair_originals["java"],
                       "c_original": pair_originals["c"],
                       "java": outcomes["java"], "c": outcomes["c"], "pair_state": state})
    summary = {"eligible_pair_count": len(population.pairs),
               "originals": {language: dict(sorted((status, count) for (lang, status), count
                                                   in originals.items() if lang == language))
                             for language in ("java", "c")},
               "mutants": {language: dict(sorted(counts.items())) for language, counts in mutants.items()},
               "mutants_by_original_outcome": {
                   language: {
                       original_outcome: {
                           "outcomes": dict(sorted(counts.items())),
                           "eligible": sum(counts.values()),
                           "resolved": counts["proved"] + counts["specification violation"],
                           "rejected": counts["specification violation"],
                           "rejection_rate_on_resolved": (
                               counts["specification violation"] /
                               (counts["proved"] + counts["specification violation"])
                               if counts["proved"] + counts["specification violation"] else None),
                       }
                       for original_outcome, counts in sorted(groups.items())
                   }
                   for language, groups in mutants_by_original.items()
               },
               "pairs": dict(sorted(pair_states.items())), "pair_records": detail,
               "complete": pair_states["not run"] == 0,
               "interpretation": (
                   "Every eligible mutant is evaluated when annotation transfer succeeds, "
                   "regardless of its original's outcome. Original outcomes are retained "
                   "as strata. Only specification violations count as mutant rejection; "
                   "precondition/RTE, tool, and unknown outcomes are reported separately.")}
    decisive_pairs = (pair_states["both rejected"] + pair_states["both proved"]
                      + pair_states["Java/C disagreement"])
    summary["paired_comparison"] = {
        "decisive_pairs": decisive_pairs,
        "matching_decisive_pairs": pair_states["both rejected"] + pair_states["both proved"],
        "agreement_on_decisive_pairs": ((pair_states["both rejected"] + pair_states["both proved"])
                                         / decisive_pairs) if decisive_pairs else None,
        "unknown_or_support_or_tool_pairs": (pair_states["unknown/timeout"]
                                             + pair_states["precondition/RTE failure"]
                                             + pair_states["tool failure"]),
        "not_run_pairs": pair_states["not run"],
    }
    summary["language_rejection"] = {}
    for language, counts in mutants.items():
        resolved = counts["proved"] + counts["specification violation"]
        # Report both denominators: all eligible pairs show unfinished work;
        # resolved-only rates are conditional and must not replace them.
        summary["language_rejection"][language] = {
            "rejected": counts["specification violation"], "proved": counts["proved"],
            "resolved": resolved,
            "rejection_rate_on_resolved": counts["specification violation"] / resolved if resolved else None,
            "eligible_denominator": len(population.pairs),
            "rejection_rate_on_eligible": counts["specification violation"] / len(population.pairs)
            if population.pairs else None,
        }
    write_json(output / "summary.json", summary)
    return summary


def run_population(population: Population, programs: tuple[str, ...], output: Path,
                   java_specs: Path | None, c_specs: Path | None,
                   java_generator: str | None, c_generator: str | None,
                   settings: Settings, max_pairs: int | None = None,
                   stage: str = "all", parallel_languages: bool = False,
                   languages: tuple[str, ...] = ("java", "c"),
                   retry_tool_failures: bool = False, c_workers: int = 1,
                   java_workers: int = 1, capture_java_workload: bool = False) -> dict[str, Any]:
    """Execute the report's ordered stages against FormalBench-data targets.

    ``prepare`` only freezes independently produced original specifications;
    JArray contracts are never evaluation targets or population members.
    """
    if stage not in {"prepare", "originals", "mutants", "all"}:
        raise InputError(f"Unknown evaluation stage: {stage}")
    if c_workers < 1 or java_workers < 1:
        raise InputError("worker counts must be positive")

    def run_language_tasks(tasks: list[Any]) -> list[Any]:
        try:
            if parallel_languages and len(tasks) > 1:
                with ThreadPoolExecutor(max_workers=2) as executor:
                    futures = [executor.submit(task) for task in tasks]
                    return [future.result() for future in futures]
            return [task() for task in tasks]
        except KeyboardInterrupt:
            summarize(output, population)
            raise

    output = output.resolve()
    if output.exists() and not (output / "run.json").is_file():
        unexpected = {path.name for path in output.iterdir()} - {"frozen_specs", "generated_specs", "summary.json"}
        if unexpected:
            raise InputError(f"Output directory contains unrelated files: {output}")
    output.mkdir(parents=True, exist_ok=True)
    frozen = freeze_specs(population, programs, output, java_specs, c_specs,
                          java_generator, c_generator)
    if stage == "prepare":
        # No verifier is needed until the complete original specs are frozen.
        return summarize(output, population)
    executables = {language: executable_info(
        settings.openjml if language == "java" else settings.frama_c,
        "--version" if language == "java" else "-version") for language in languages}
    support = support_hashes()
    run = {"schema_version": "2.0", "selection_manifest": str(population.manifest),
           "selection_manifest_sha256": population.manifest_sha256,
           "java_originals": str(population.java_originals), "c_originals": str(population.c_originals),
           "settings": _settings_record(settings), "verifiers": executables,
           "parallel_languages": parallel_languages,
           "c_workers": c_workers,
           "java_workers": java_workers,
           "jarray_support_sha256": support,
           "evaluation_targets": "FormalBench-data original programs and retained Java/C mutant pairs",
           "c_support_role": "Fixed trusted JArray declarations/contracts; not scored as benchmark targets"}
    run_path = output / "run.json"
    if capture_java_workload:
        run["capture_java_workload"] = True
    migrate_legacy_run_metadata = False
    if retry_tool_failures:
        if not run_path.is_file():
            raise InputError("Tool-failure retry requires an existing run")
        previous = read_json(run_path)
        for key in ("selection_manifest_sha256", "java_originals", "c_originals",
                    "settings", "jarray_support_sha256"):
            if previous.get(key) != run[key]:
                raise InputError(f"Tool-failure retry must preserve study inputs: {key}")
        stamp = datetime.now(timezone.utc).strftime("%Y%m%dT%H%M%S%fZ")
        write_json(output / "history" / f"retry_{stamp}.json",
                   {**run, "retry_tool_failures": True, "languages": languages,
                    "programs": programs, "stage": stage})
    else:
        if run_path.is_file():
            previous = read_json(run_path)
            if previous != run:
                previous_without_schedule = {
                    key: value for key, value in previous.items() if key not in {"parallel_languages", "c_workers", "java_workers"}
                }
                current_without_schedule = {
                    key: value for key, value in run.items() if key not in {"parallel_languages", "c_workers", "java_workers"}
                }
                independent_resume = (
                    previous_without_schedule == current_without_schedule
                )
                if independent_resume:
                    stamp = datetime.now(timezone.utc).strftime("%Y%m%dT%H%M%S%fZ")
                    write_json(output / "history" / f"schedule_before_{stamp}.json", previous)
                    write_json(run_path, run)
                else:
                    # Older originals-only runs recorded the selected population
                    # and stop policy, but not the manifest and source-folder
                    # fingerprints at run level. Permit that schema only for a
                    # mutant continuation with identical programs, tools, support
                    # files, and limits. Per-original hashes are checked below
                    # before the updated metadata is written.
                    legacy_match = (
                        stage == "mutants"
                        and previous.get("programs") == list(programs)
                        and set(previous.get("languages", [])) == set(languages)
                        and previous.get("stop_condition") == "first syntax/tool failure or not-run outcome"
                        and previous.get("parallel_languages") == parallel_languages
                        and previous.get("settings") == run["settings"]
                        and previous.get("verifiers") == run["verifiers"]
                        and previous.get("jarray_support_sha256") == run["jarray_support_sha256"]
                    )
                    if not legacy_match:
                        raise InputError("Existing output was created with different inputs, tools, or settings")
                    migrate_legacy_run_metadata = True
            else:
                write_json(run_path, run)
        else:
            write_json(run_path, run)

    def selected_retry(program, role, mutant_id, language):
        if not retry_tool_failures:
            return True
        case_dir = _case_dir(output, program, role, mutant_id, language)
        path = case_dir / "record.json"
        if not path.is_file():
            return _latest_archive(output, program, case_dir, language) is not None
        return _retryable_tool_failure(read_json(path))
    if stage == "mutants" and not retry_tool_failures:
        # Check every saved original before starting any mutant, so a transfer
        # mismatch cannot leave the mutant population only partly evaluated.
        for program in programs:
            for language in languages:
                _reuse_original_for_mutants(
                    output, population, program, language, frozen[(program, language)],
                    settings, executables, support)
        if migrate_legacy_run_metadata:
            write_json(run_path, run)
    # Originals are verified first to report consistency independently. In
    # mutant-only mode, their prior records must exist and still match inputs.
    worker_counts = {"java": java_workers, "c": c_workers}
    concurrent_originals = any(worker_counts[language] > 1 for language in languages)
    original_queues = {language: [] for language in languages}
    for program in programs:
        tasks = []
        for language in languages:
            if retry_tool_failures and (stage == "mutants" or not selected_retry(program, "original", None, language)):
                continue
            if stage in {"originals", "all"}:
                def run_original(language: str = language, program: str = program) -> tuple[str, dict[str, Any]]:
                    record = evaluate_case(
                        output, population, program, "original", None, None, language,
                        population.original(program, language), frozen[(program, language)],
                        settings, executables, support, retry_tool_failures, capture_java_workload)
                    return language, record
            elif stage == "mutants":
                def run_original(language: str = language, program: str = program) -> tuple[str, dict[str, Any]]:
                    """Validate and reuse the completed original for this mutant stage."""
                    record = _reuse_original_for_mutants(
                        output, population, program, language, frozen[(program, language)],
                        settings, executables, support)
                    return language, record
            else:
                def run_original(language: str = language, program: str = program) -> tuple[str, dict[str, Any]]:
                    path = _case_dir(output, program, "original", None, language) / "record.json"
                    if not path.is_file():
                        raise InputError(f"Verify the {language} original before its mutants: {program}")
                    record = evaluate_case(
                        output, population, program, "original", None, None, language,
                        population.original(program, language), frozen[(program, language)],
                        settings, executables, support)
                    return language, record
            if concurrent_originals:
                original_queues[language].append(run_original)
            else:
                tasks.append(run_original)
        run_language_tasks(tasks)
    if concurrent_originals:
        try:
            run_case_queues(original_queues, worker_counts)
        except KeyboardInterrupt:
            summarize(output, population)
            raise
    if stage == "originals":
        return summarize(output, population)
    # The manifest supplies the eligible pair IDs. --max-pairs only truncates
    # a pilot; it does not alter the population denominator in the summary.
    pairs = [pair for pair in population.pairs if pair.program in programs]
    if max_pairs is not None:
        pairs = pairs[:max_pairs]
    if ((parallel_languages and len(languages) > 1) or ("c" in languages and c_workers > 1)
            or ("java" in languages and java_workers > 1)):
        stop_language_queues = Event()
        queues = {language: Queue() for language in languages}
        for language in languages:
            for pair in pairs:
                queues[language].put(pair)

        def run_language_queue(language: str) -> None:
            """Claim each case once; workers advance without a per-case barrier."""
            while not stop_language_queues.is_set():
                try:
                    pair = queues[language].get_nowait()
                except Empty:
                    return
                if not selected_retry(pair.program, "mutant", pair.mutant_id, language):
                    continue
                source = pair.java if language == "java" else pair.c
                evaluate_case(output, population, pair.program, "mutant", pair.mutant_id,
                              pair.selection, language, source, frozen[(pair.program, language)],
                              settings, executables, support, retry_tool_failures, capture_java_workload)

        workers = [language for language in languages
                   for _ in range(c_workers if language == "c" else java_workers)]
        executor = ThreadPoolExecutor(max_workers=len(workers))
        futures = [executor.submit(run_language_queue, language) for language in workers]
        try:
            for future in as_completed(futures):
                future.result()
        except BaseException as error:
            stop_language_queues.set()
            for future in futures:
                future.cancel()
            executor.shutdown(wait=True, cancel_futures=True)
            if isinstance(error, KeyboardInterrupt):
                summarize(output, population)
            raise
        else:
            executor.shutdown(wait=True)
    else:
        for pair in pairs:
            tasks = []
            for language, source in (("java", pair.java), ("c", pair.c)):
                if language not in languages or not selected_retry(pair.program, "mutant", pair.mutant_id, language):
                    continue
                def run_mutant(language: str = language, source: Path = source) -> dict[str, Any]:
                    return evaluate_case(output, population, pair.program, "mutant", pair.mutant_id,
                                         pair.selection, language, source, frozen[(pair.program, language)],
                                         settings, executables, support, retry_tool_failures, capture_java_workload)
                tasks.append(run_mutant)
            run_language_tasks(tasks)
    return summarize(output, population)
