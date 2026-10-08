"""Freeze original specifications, verify originals, then replay selected pairs."""
from __future__ import annotations
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
from verification.specification_evaluation.specifications.annotations import Transfer, apply_specification, extract_specification
from verification.specification_evaluation.manifest import InputError, Population
from verification.specification_evaluation.backends.verifiers import Settings, executable_info, run_verifier, stage_c_support, support_files

def write_json(path: Path, value: Any) -> None:
    """Replace a JSON artifact atomically so interrupted writes stay recoverable."""
    path.parent.mkdir(parents=True, exist_ok=True)
    temporary = path.with_name(path.name + '.tmp')
    temporary.write_text(json.dumps(value, indent=2, sort_keys=True) + '\n', encoding='utf-8')
    temporary.replace(path)

def read_json(path: Path) -> Any:
    return json.loads(path.read_text(encoding='utf-8'))

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
    executor = ThreadPoolExecutor(max_workers=sum((workers[lang] for lang in tasks)))
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
    if not isinstance(specification, dict) or specification.get('program') != program or specification.get('language') != language:
        raise InputError(f'Frozen specification names the wrong program/language: {path}')
    return specification

def _spec_path(spec_directory: Path | None, program: str, language: str) -> Path | None:
    if spec_directory is None:
        return None
    return spec_directory / f'{program}.{('java' if language == 'java' else 'c')}'

def _generate_spec(command_template: str, source: Path, target: Path, log: Path) -> None:
    """Run an external spec generator on one original and retain its command log."""
    if '{source}' not in command_template or '{output}' not in command_template:
        raise InputError('Generator command must contain both {source} and {output}')
    target.parent.mkdir(parents=True, exist_ok=True)
    command = [part.replace('{source}', str(source)).replace('{output}', str(target)) for part in shlex.split(command_template)]
    before = source.read_bytes()
    try:
        result = subprocess.run(command, capture_output=True, text=True, timeout=1800, check=False)
    except (OSError, subprocess.TimeoutExpired) as error:
        raise InputError(f'Specification generator failed for {source.name}: {error}') from error
    write_json(log, {'command': command, 'exit_code': result.returncode, 'stdout': result.stdout, 'stderr': result.stderr})
    if result.returncode != 0 or not target.is_file():
        raise InputError(f'Specification generator did not produce {target}; see {log}')
    if source.read_bytes() != before:
        raise InputError(f'Generator modified executable original: {source}')

def freeze_specs(population: Population, programs: tuple[str, ...], output: Path, java_specs: Path | None, c_specs: Path | None, java_generator: str | None, c_generator: str | None) -> dict[tuple[str, str], Path]:
    """Freeze one structured JML/ACSL JSON specification per original.

    Verification and mutant replay must use these exact files, not regenerated
    or repaired specifications after a proof result is known.
    """
    manifest_path = output / 'frozen_specs' / 'manifest.json'
    if manifest_path.is_file():
        existing_manifest = read_json(manifest_path)
        if not isinstance(existing_manifest, dict) or existing_manifest.get('schema_version') != '2.0':
            raise InputError('Existing frozen manifest predates structured JSON; use a new output directory')
    frozen: dict[tuple[str, str], Path] = {}
    entries: list[dict[str, Any]] = []
    for program in programs:
        for language, source_dir, generator in (('java', java_specs, java_generator), ('c', c_specs, c_generator)):
            raw = population.original(program, language)
            raw_text = raw.read_text(encoding='utf-8')
            destination = output / 'frozen_specs' / language / f'{program}.json'
            candidate = _spec_path(source_dir, program, language)
            if not destination.is_file():
                if candidate is None or not candidate.is_file():
                    if generator is None:
                        raise InputError(f'Missing annotated {language} original for {program}')
                    candidate = output / 'generated_specs' / language / raw.name
                    if not candidate.is_file():
                        _generate_spec(generator, raw, candidate, output / 'generated_specs' / language / f'{program}.command.json')
                annotated = candidate.read_text(encoding='utf-8')
                specification = extract_specification(raw_text, annotated, program, language)
                apply_specification(raw_text, specification, raw_text)
                write_json(destination, specification)
            else:
                specification = read_frozen_spec(destination, program, language)
                apply_specification(raw_text, specification, raw_text)
                if candidate is not None and candidate.is_file():
                    supplied = extract_specification(raw_text, candidate.read_text(encoding='utf-8'), program, language)
                    if supplied != specification:
                        raise InputError(f'Supplied specification changed after freezing: {candidate}')
            frozen[program, language] = destination
            entries.append({'program': program, 'language': language, 'original': str(raw), 'frozen_spec': str(destination), 'annotation_count': len(specification['annotations'])})
    if manifest_path.is_file():
        previous = read_json(manifest_path)
        previous_entries = {(entry['program'], entry['language']): entry for entry in previous.get('specifications', [])}
        for entry in entries:
            old = previous_entries.get((entry['program'], entry['language']))
            if old is not None and {key: value for key, value in old.items() if key != 'frozen_spec'} != {key: value for key, value in entry.items() if key != 'frozen_spec'}:
                raise InputError(f'Frozen specification or original changed: {entry['program']} {entry['language']}')
        previous_entries.update({(entry['program'], entry['language']): entry for entry in entries})
        entries = list(previous_entries.values())
    write_json(manifest_path, {'schema_version': '2.0', 'specifications': sorted(entries, key=lambda entry: (entry['program'], entry['language']))})
    return frozen

def validate_transfers(population: Population, programs: tuple[str, ...], java_specs: Path | None, c_specs: Path | None, frozen_root: Path | None=None) -> dict[str, int]:
    """Dry-run JSON attachment for originals and all selected mutants."""
    counts = Counter()
    specifications: dict[tuple[str, str], dict[str, Any]] = {}
    for program in programs:
        for language, directory in (('java', java_specs), ('c', c_specs)):
            raw = population.original(program, language).read_text(encoding='utf-8')
            if frozen_root is not None:
                structured = read_frozen_spec(frozen_root / language / f'{program}.json', program, language)
            else:
                spec = _spec_path(directory, program, language)
                if spec is None or not spec.is_file():
                    raise InputError(f'Missing annotated {language} original for {program}')
                structured = extract_specification(raw, spec.read_text(encoding='utf-8'), program, language)
            apply_specification(raw, structured, raw)
            specifications[program, language] = structured
            counts[f'original_{language}'] += 1
    for pair in population.pairs:
        if pair.program not in programs:
            continue
        for language, mutant in (('java', pair.java), ('c', pair.c)):
            apply_specification(population.original(pair.program, language).read_text(encoding='utf-8'), specifications[pair.program, language], mutant.read_text(encoding='utf-8'))
            counts[f'mutant_{language}'] += 1
    return dict(sorted(counts.items()))

def _case_dir(output: Path, program: str, role: str, mutant_id: str | None, language: str) -> Path:
    return output / 'cases' / program / ('original' if role == 'original' else f'mutant_{mutant_id}') / language

def _settings_record(settings: Settings) -> dict[str, Any]:
    return {**({'capture_c_counterexamples': True} if settings.capture_c_counterexamples else {}), 'java_prover': settings.java_prover, 'c_provers': settings.c_provers, 'timeout': settings.timeout, 'goal_timeout': settings.goal_timeout, 'memory_model': settings.memory_model, 'machdep': settings.machdep, 'wp_memlimit': settings.wp_memlimit, 'wp_par': settings.wp_par, 'why3_extra_config': str(settings.why3_extra_config) if settings.why3_extra_config else None}

def _store_record(case_dir: Path, record: dict[str, Any]) -> dict[str, Any]:
    if record.get('outcome') != 'not run':
        record = {**record, 'attempt_status': 'complete'}
    write_json(case_dir / 'record.json', record)
    return record

def _retryable_tool_failure(record: dict[str, Any]) -> bool:
    """An explicit retry covers all tool stages and interrupted attempts.

    One invocation visits each selected case once. Historical archives do not
    block an explicit retry, including after an external tool/configuration fix.
    """
    return record.get('outcome') == 'syntax/tool failure' or (record.get('outcome') == 'not run' and record.get('attempt_status') in {'running', 'interrupted'})

def _latest_archive(output: Path, program: str, case_dir: Path, language: str):
    root = output / 'history' / program / case_dir.parent.name / language
    return next(iter(sorted(root.glob('*/record.json'), reverse=True)), None)

def evaluate_case(output: Path, population: Population, program: str, role: str, mutant_id: str | None, selection: str | None, language: str, raw: Path, frozen_spec: Path, settings: Settings, executables: dict[str, dict[str, Any]], support: dict[str, str], retry_tool_failure: bool=False, capture_java_workload: bool=False) -> dict[str, Any]:
    """Verify one FormalBench source and keep its complete result."""
    case_dir = _case_dir(output, program, role, mutant_id, language)
    case_dir.mkdir(parents=True, exist_ok=True)
    base = {'program': program, 'role': role, 'mutant_id': mutant_id, 'selection': selection, 'language': language, 'raw_source': str(raw), 'frozen_spec': str(frozen_spec), 'verifier': executables[language], 'settings': _settings_record(settings)}
    if role != 'original':
        raise InputError('Verifier stages evaluate originals only; use execution.completeness for mutants')
    prior = case_dir / 'record.json'
    if prior.is_file():
        previous = read_json(prior)
        if previous.get('outcome') != 'not run' and (not (retry_tool_failure and _retryable_tool_failure(previous))):
            return previous
        stamp = datetime.now(timezone.utc).strftime('%Y%m%dT%H%M%S%fZ')
        archive = output / 'history' / program / 'original' / language / stamp
        archive.parent.mkdir(parents=True, exist_ok=True)
        case_dir.rename(archive)
        case_dir.mkdir(parents=True)
    record = dict(base)
    _store_record(case_dir, {**record, 'outcome': 'not run', 'attempt_status': 'running', 'reason': 'Evaluation in progress', 'goals': []})
    raw_original = population.original(program, language).read_text(encoding='utf-8')
    specification = read_frozen_spec(frozen_spec, program, language)
    try:
        result: Transfer = apply_specification(raw_original, specification, raw.read_text(encoding='utf-8'))
        annotated_source = result.source
        transfer_record = {'annotation_count': len(result.annotations), 'placements': result.annotations, 'omitted_annotations': result.omitted_annotations, 'removed_loop_annotations': sum((a.get('target') == 'loop' for a in result.omitted_annotations)), 'internal_annotation_coverage': 'partial_due_to_deleted_loop' if any((a.get('target') == 'loop' for a in result.omitted_annotations)) else 'complete', 'removed_temporary_policy': 'original pure initializer with stable dependencies', 'method': 'frozen structured JSON with audited C declaration bindings' if language == 'c' else 'frozen structured JSON'}
    except InputError as error:
        detection = {}
        if language == 'c':
            from verification.specification_evaluation.backends.detection import detection_result
            detection = {'verifier_detection': detection_result('syntax/tool failure', [], [])}
        return _store_record(case_dir, {**record, 'outcome': 'syntax/tool failure', 'failure_stage': 'annotation_transfer', 'reason': f'Annotation transfer failed: {error}', 'goals': [], **detection})
    source = case_dir / raw.name
    if source.is_file() and source.read_text(encoding='utf-8') != annotated_source:
        raise InputError(f'Staged source changed after freezing: {source}')
    if not source.is_file():
        source.write_text(annotated_source, encoding='utf-8')
    write_json(case_dir / 'transfer.json', transfer_record)
    if language == 'c':
        staged_support = stage_c_support(case_dir)
        if staged_support != support:
            raise InputError('Staged C contracts differ from the fixed support files')
    try:
        if language == 'java' and capture_java_workload:
            from verification.specification_evaluation.backends.java_workload import run_java_workload
            verification = run_java_workload(source, case_dir, settings, executables)
        else:
            verification = run_verifier(language, source, case_dir, settings, executables)
    except KeyboardInterrupt:
        _store_record(case_dir, {**record, 'transfer': transfer_record, 'outcome': 'not run', 'attempt_status': 'interrupted', 'reason': 'Verification interrupted', 'goals': []})
        raise
    return _store_record(case_dir, {**record, 'annotated_source': str(source), 'transfer': transfer_record, **verification})

def summarize(output: Path, population: Population) -> dict[str, Any]:
    """Original consistency only; mutant evidence comes from runtime checking."""
    originals = {lang: Counter() for lang in ('java', 'c')}
    rows = []
    for program in population.programs:
        row = {'program': program}
        for language in ('java', 'c'):
            path = output / 'cases' / program / 'original' / language / 'record.json'
            outcome = read_json(path).get('outcome', 'not run') if path.is_file() else 'not run'
            originals[language][outcome] += 1
            row[language] = outcome
        rows.append(row)
    primary = output / 'mutant_detection' / 'summary.json'
    summary = {'evaluation': 'original verifier consistency and runtime contract mutant detection', 'eligible_pair_count': len(population.pairs), 'originals': {k: dict(v) for k, v in originals.items()}, 'original_records': rows, 'runtime_completeness': read_json(primary) if primary.is_file() else None, 'complete': all(('not run' not in v for v in originals.values()))}
    write_json(output / 'summary.json', summary)
    return summary

def run_population(population, programs, output, java_specs, c_specs, java_generator, c_generator, settings, max_pairs=None, stage='all', parallel_languages=False, languages=('java', 'c'), retry_tool_failures=False, c_workers=1, java_workers=1, capture_java_workload=False):
    """Freeze contracts, verify originals, then execute eligible mutants."""
    output = Path(output).resolve()
    output.mkdir(parents=True, exist_ok=True)
    if stage not in {'prepare', 'originals', 'mutants', 'all'}:
        raise InputError(f'Unknown stage: {stage}')
    if stage == 'mutants':
        from verification.specification_evaluation.execution.completeness import run_completeness
        run_completeness(output, output / 'mutant_detection', languages, programs, max(java_workers, c_workers), population.manifest, max_pairs)
        return summarize(output, population)
    frozen = freeze_specs(population, programs, output, java_specs, c_specs, java_generator, c_generator)
    if stage == 'prepare':
        return summarize(output, population)
    executables = {language: executable_info(settings.openjml if language == 'java' else settings.frama_c, '--version' if language == 'java' else '-version') for language in languages}
    support = support_files()
    write_json(output / 'run.json', {'schema_version': '3.0', 'selection_manifest': str(population.manifest), 'java_originals': str(population.java_originals), 'c_originals': str(population.c_originals), 'settings': _settings_record(settings), 'verifiers': executables, 'primary_completeness': 'runtime postcondition mutant detection', 'jarray_support_files': support})
    tasks = {language: [] for language in languages}
    for language in languages:
        for program in programs:

            def task(program=program, language=language):
                evaluate_case(output, population, program, 'original', None, None, language, population.original(program, language), frozen[program, language], settings, executables, support, retry_tool_failures, capture_java_workload)
            tasks[language].append(task)
    run_case_queues(tasks, {'java': java_workers, 'c': c_workers})
    if stage == 'all':
        from verification.specification_evaluation.execution.completeness import run_completeness
        run_completeness(output, output / 'mutant_detection', languages, programs, max(java_workers, c_workers), population.manifest, max_pairs)
    return summarize(output, population)
