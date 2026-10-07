"""Resolve explicit diagnostic targets without expanding a study population."""
from __future__ import annotations

from dataclasses import dataclass
from pathlib import Path

from verification.specification_evaluation.manifest import InputError, Population, sha256


@dataclass(frozen=True)
class DiagnosticCase:
    """Identify a raw source and the original whose frozen specification it uses."""

    program: str
    role: str
    mutant_id: str | None
    selection: str | None
    raw: Path

    @property
    def key(self) -> str:
        """Return a readable identifier for diagnostic summaries."""
        return f"{self.program}/{self.mutant_id if self.role == 'mutant' else 'original'}"

    def folder(self, root: Path, language: str) -> Path:
        """Locate this case's language-specific diagnostic artifacts."""
        role = 'original' if self.role == 'original' else f'mutant_{self.mutant_id}'
        return root / 'cases' / self.program / role / language


def add_selectors(parser) -> None:
    """Expose the same repeatable target selectors in both diagnostic CLIs."""
    parser.add_argument('--program', action='append', default=[], metavar='NAME',
                        help='verify this original program; repeat for several originals')
    parser.add_argument('--case', action='append', default=[], metavar='PROGRAM/ID',
                        help='verify an eligible mutant, or PROGRAM/original; repeat for several cases')
    parser.add_argument('--source', type=Path, action='append', default=[], metavar='PATH',
                        help='verify a raw .c/.java source file using its program\'s frozen specification')


def select_cases(population: Population, language: str, programs: list[str],
                 cases: list[str], sources: list[Path]) -> list[DiagnosticCase]:
    """Select originals, retained mutants, or explicitly supplied external raw sources.

    External sources must retain the original filename. They get separate
    diagnostic IDs and never become members of the experiment population.
    Overlapping selectors are deduplicated, preserving the requested order.
    """
    available = {}
    for program in population.programs:
        case = DiagnosticCase(program, 'original', None, None,
                              population.original(program, language).resolve())
        available[case.key] = case
    for pair in population.pairs:
        raw = pair.java if language == 'java' else pair.c
        case = DiagnosticCase(pair.program, 'mutant', pair.mutant_id, pair.selection, raw.resolve())
        available[case.key] = case
    by_path = {case.raw: case for case in available.values()}
    selected = {}

    def include(key: str) -> None:
        """Resolve one named case and reject typos before invoking any verifier."""
        if key not in available:
            raise InputError(f'Unknown original or eligible mutant: {key}')
        selected.setdefault(key, available[key])

    for program in programs:
        include(f'{program}/original')
    for key in cases:
        include(key)
    suffix = '.java' if language == 'java' else '.c'
    for supplied in sources:
        path = supplied.resolve()
        if not path.is_file() or path.suffix != suffix:
            raise InputError(f'Expected an existing raw {suffix} source file: {supplied}')
        if path in by_path:
            case = by_path[path]
        else:
            if path.stem not in population.programs:
                raise InputError(f'Source filename must name a study program: {path.name}')
            # Full content hash prevents two different external variants from
            # sharing an output directory. These are diagnostic targets only.
            case = DiagnosticCase(path.stem, 'mutant', 'external_' + sha256(path),
                                  'external_diagnostic_source', path)
        selected.setdefault(case.key, case)
    return list(selected.values())
