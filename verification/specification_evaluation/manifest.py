"""Load the fixed, previously screened Java/C mutant population."""

from __future__ import annotations

import hashlib
import json
import re
from dataclasses import dataclass
from pathlib import Path

REPO = Path(__file__).resolve().parents[2]
DATA = REPO / "FormalBench-data/FilteredData"
DEFAULT_MANIFEST = DATA / "fault_mutants/run_ukcw__uc/selection_manifest.json"
DEFAULT_JAVA = DATA / "selected_java/seed_726_per_category_10_653ade686f"
DEFAULT_C = DATA / "translated_c/seed_726_per_category_10_653ade686f"
IDENTIFIER = re.compile(r"[A-Za-z_][A-Za-z_0-9]*\Z")
MUTANT_ID = re.compile(r"[0-9]+\Z")


class InputError(ValueError):
    """An input violates the selected-pair or specification contract."""


def sha256(path: Path) -> str:
    """Hash a source or manifest without loading a potentially large file at once."""
    digest = hashlib.sha256()
    with path.open("rb") as stream:
        for block in iter(lambda: stream.read(1024 * 1024), b""):
            digest.update(block)
    return digest.hexdigest()


@dataclass(frozen=True)
class Pair:
    program: str
    mutant_id: str
    selection: str
    java: Path
    c: Path

    @property
    def key(self) -> str:
        return f"{self.program}/{self.mutant_id}"


@dataclass(frozen=True)
class Population:
    manifest: Path
    manifest_sha256: str
    java_originals: Path
    c_originals: Path
    programs: tuple[str, ...]
    pairs: tuple[Pair, ...]

    def original(self, program: str, language: str) -> Path:
        """Locate the FormalBench original; JArray is never a population entry."""
        directory, suffix = (self.java_originals, ".java") if language == "java" else (self.c_originals, ".c")
        return directory / f"{program}{suffix}"


def load_population(manifest_path: Path, java_originals: Path, c_originals: Path) -> Population:
    """Accept only screened, complete Java/C pairs listed as retained.
    """
    manifest_path = manifest_path.resolve()
    if not manifest_path.is_file():
        raise InputError(f"Selection manifest not found: {manifest_path}")
    try:
        data = json.loads(manifest_path.read_text(encoding="utf-8"))
    except (OSError, json.JSONDecodeError) as error:
        raise InputError(f"Cannot read selection manifest: {error}") from error
    if data.get("kind") != "paired_completeness_mutant_selection":
        raise InputError("Manifest is not the paired-completeness selection")
    retained = data.get("retained")
    if not isinstance(retained, dict) or set(retained) != {"direct_match", "sanitizer_supported_stack_overflow"}:
        raise InputError("Manifest must contain both retained-pair groups")
    run = manifest_path.parent
    pairs: list[Pair] = []
    seen: set[str] = set()
    # Resolve each listed pair to its two existing source files; reject an
    # incomplete pair before any specification or verifier work begins.
    for retained_type, preserved_mutant in retained.items():
        if not isinstance(preserved_mutant, list):
            raise InputError(f"Retained {retained_type} must be a list")
        for key in preserved_mutant:
            if not isinstance(key, str) or key.count("/") != 1:
                raise InputError(f"Invalid mutant key: {key!r}")
            program, mutant_id = key.split("/")
            if not IDENTIFIER.fullmatch(program) or not MUTANT_ID.fullmatch(mutant_id):
                raise InputError(f"Unsafe mutant key: {key!r}")
            if key in seen:
                raise InputError(f"Duplicate retained pair: {key}")
            seen.add(key)
            base = run / program / "mutants" / mutant_id
            java = base / "java" / f"{program}.java"
            c = base / "c" / f"{program}.c"
            if not java.is_file() or not c.is_file():
                raise InputError(f"Incomplete selected Java/C pair: {key}")
            pairs.append(Pair(program, mutant_id, retained_type, java, c))
    expected = data.get("counts", {}).get("retained_total_pairs")
    if expected != len(pairs):
        raise InputError(f"Manifest count is {expected}, but {len(pairs)} retained pairs were listed")
    excluded = data.get("excluded", {})
    if not isinstance(excluded, dict):
        raise InputError("Excluded-pair groups must be an object")
    overlap = seen & {key for group in excluded.values() if isinstance(group, list) for key in group}
    if overlap:
        raise InputError(f"Pair is both selected and excluded: {min(overlap)}")
    # Originals form the matched Java/C program population, separate from
    # the fixed JArray declarations used only while checking C translations.
    java_originals, c_originals = java_originals.resolve(), c_originals.resolve()
    programs = tuple(sorted(path.stem for path in java_originals.glob("*.java")))
    if not programs:
        raise InputError(f"No original Java programs in {java_originals}")
    if any(not IDENTIFIER.fullmatch(program) for program in programs):
        raise InputError("Original program filename is not a simple class name")
    c_programs = {path.stem for path in c_originals.glob("*.c")}
    if set(programs) != c_programs:
        raise InputError("Original Java and C program sets differ")
    if {pair.program for pair in pairs} - set(programs):
        raise InputError("A selected mutant has no original Java/C program")
    return Population(manifest_path, sha256(manifest_path), java_originals, c_originals,
                      programs, tuple(sorted(pairs, key=lambda pair: (pair.program, int(pair.mutant_id)))))
