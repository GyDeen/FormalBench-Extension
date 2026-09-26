"""Freeze original specifications, verify originals, then replay selected pairs."""

from __future__ import annotations

import hashlib
import json
import shlex
import subprocess
from collections import Counter
from concurrent.futures import ThreadPoolExecutor
from pathlib import Path
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
            if old is not None and old != entry:
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
    return {"java_prover": settings.java_prover, "c_provers": settings.c_provers,
            "timeout": settings.timeout, "goal_timeout": settings.goal_timeout,
            "memory_model": settings.memory_model, "machdep": settings.machdep,
            "wp_memlimit": settings.wp_memlimit, "wp_par": settings.wp_par,
            "why3_extra_config": str(settings.why3_extra_config) if settings.why3_extra_config else None,
            "why3_extra_config_sha256": sha256(settings.why3_extra_config)
            if settings.why3_extra_config else None}


def _store_record(case_dir: Path, record: dict[str, Any]) -> dict[str, Any]:
    write_json(case_dir / "record.json", record)
    return record


def evaluate_case(output: Path, population: Population, program: str, role: str,
                  mutant_id: str | None, selection: str | None, language: str,
                  raw: Path, frozen_spec: Path, settings: Settings,
                  executables: dict[str, dict[str, Any]], support: dict[str, str]) -> dict[str, Any]:
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
    fingerprint = digest(base)
    prior = case_dir / "record.json"
    if prior.is_file():
        # Resume only when source, frozen specification, tool and settings
        # still match the previously recorded case.
        record = read_json(prior)
        if record.get("input_fingerprint") != fingerprint:
            raise InputError(f"Existing case has different inputs: {case_dir}")
        if record.get("outcome") != "not run":
            return record
    record = {**base, "input_fingerprint": fingerprint}
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
                           "placements": result.annotations, "method": "frozen structured JSON"}
    except InputError as error:
        # Unsafe placement is an evaluation failure, not a killed mutant.
        return _store_record(case_dir, {**record, "outcome": "syntax/tool failure",
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
    verification = run_verifier(language, source, case_dir, settings, executables)
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
                   stage: str = "all", parallel_languages: bool = False) -> dict[str, Any]:
    """Execute the report's ordered stages against FormalBench-data targets.

    ``prepare`` only freezes independently produced original specifications;
    JArray contracts are never evaluation targets or population members.
    """
    if stage not in {"prepare", "originals", "mutants", "all"}:
        raise InputError(f"Unknown evaluation stage: {stage}")

    def run_language_tasks(tasks: list[Any]) -> list[Any]:
        if parallel_languages and len(tasks) > 1:
            with ThreadPoolExecutor(max_workers=2) as executor:
                futures = [executor.submit(task) for task in tasks]
                return [future.result() for future in futures]
        return [task() for task in tasks]

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
    executables = {"java": executable_info(settings.openjml, "--version"),
                   "c": executable_info(settings.frama_c, "-version")}
    support = support_hashes()
    run = {"schema_version": "2.0", "selection_manifest": str(population.manifest),
           "selection_manifest_sha256": population.manifest_sha256,
           "java_originals": str(population.java_originals), "c_originals": str(population.c_originals),
           "settings": _settings_record(settings), "verifiers": executables,
           "parallel_languages": parallel_languages,
           "jarray_support_sha256": support,
           "evaluation_targets": "FormalBench-data original programs and retained Java/C mutant pairs",
           "c_support_role": "Fixed trusted JArray declarations/contracts; not scored as benchmark targets"}
    run_path = output / "run.json"
    if run_path.is_file() and read_json(run_path) != run:
        raise InputError("Existing output was created with different inputs, tools, or settings")
    write_json(run_path, run)
    # Originals are verified first to report consistency independently. In
    # mutant-only mode, their prior records must exist and still match inputs.
    for program in programs:
        tasks = []
        for language in ("java", "c"):
            if stage in {"originals", "all"}:
                def run_original(language: str = language) -> tuple[str, dict[str, Any]]:
                    record = evaluate_case(
                        output, population, program, "original", None, None, language,
                        population.original(program, language), frozen[(program, language)],
                        settings, executables, support)
                    return language, record
            else:
                def run_original(language: str = language) -> tuple[str, dict[str, Any]]:
                    path = _case_dir(output, program, "original", None, language) / "record.json"
                    if not path.is_file():
                        raise InputError(f"Verify the {language} original before its mutants: {program}")
                    record = evaluate_case(
                        output, population, program, "original", None, None, language,
                        population.original(program, language), frozen[(program, language)],
                        settings, executables, support)
                    return language, record
            tasks.append(run_original)
        run_language_tasks(tasks)
    if stage == "originals":
        return summarize(output, population)
    # The manifest supplies the eligible pair IDs. --max-pairs only truncates
    # a pilot; it does not alter the population denominator in the summary.
    pairs = [pair for pair in population.pairs if pair.program in programs]
    if max_pairs is not None:
        pairs = pairs[:max_pairs]
    for pair in pairs:
        tasks = []
        for language, source in (("java", pair.java), ("c", pair.c)):
            def run_mutant(language: str = language, source: Path = source) -> dict[str, Any]:
                return evaluate_case(output, population, pair.program, "mutant", pair.mutant_id,
                                     pair.selection, language, source, frozen[(pair.program, language)],
                                     settings, executables, support)
            tasks.append(run_mutant)
        run_language_tasks(tasks)
    return summarize(output, population)
