"""Materialize, compile, and generate tests for sampled Java programs."""

from __future__ import annotations

import hashlib
import json
import re
import shutil
import subprocess
from pathlib import Path
from typing import Any


def sample_run_id(sample: list[dict[str, Any]], seed: int, per_category: int) -> str:
    """Build a stable directory name for one exact sampled dataset."""
    content = "\n".join(
        f"{record['class_name']}\0{record['code']}"
        for record in sorted(sample, key=lambda item: item["class_name"])
    )
    digest = hashlib.sha256(content.encode("utf-8")).hexdigest()[:10]
    return f"seed_{seed}_per_category_{per_category}_{digest}"


def extract_selected_java(
    sample: list[dict[str, Any]], output_dir: Path, seed: int, per_category: int
) -> tuple[Path, list[Path], Path]:
    """Extract selected source records into individual Java files.

    A content-derived run directory prevents stale files from a previous sample
    from being compiled or passed to EvoSuite.
    """
    run_id = sample_run_id(sample, seed, per_category)
    java_dir = output_dir / "selected_java" / run_id
    java_dir.mkdir(parents=True, exist_ok=True)

    java_files: list[Path] = []
    manifest_records: list[dict[str, Any]] = []
    for record in sorted(sample, key=lambda item: item["class_name"]):
        class_name = record["class_name"]
        if not re.fullmatch(r"[A-Za-z_$][A-Za-z0-9_$]*", class_name):
            raise ValueError(f"Unsafe or invalid Java class name: {class_name!r}")

        package_match = re.search(
            r"^\s*package\s+([A-Za-z_]\w*(?:\.[A-Za-z_]\w*)*)\s*;",
            record["code"],
            flags=re.MULTILINE,
        )
        package_name = package_match.group(1) if package_match else ""
        source_dir = (
            java_dir.joinpath(*package_name.split("."))
            if package_name
            else java_dir
        )
        source_dir.mkdir(parents=True, exist_ok=True)
        java_path = source_dir / f"{class_name}.java"
        java_path.write_text(record["code"], encoding="utf-8")
        java_files.append(java_path)
        manifest_records.append(
            {
                "class_name": class_name,
                "qualified_class_name": (
                    f"{package_name}.{class_name}" if package_name else class_name
                ),
                "category": record["category"],
                "source_file": str(java_path.resolve()),
            }
        )

    manifest_path = java_dir / "selection_manifest.json"
    manifest_path.write_text(
        json.dumps(
            {
                "seed": seed,
                "sample_per_category": per_category,
                "program_count": len(sample),
                "programs": manifest_records,
            },
            indent=2,
            ensure_ascii=False,
        )
        + "\n",
        encoding="utf-8",
    )
    return java_dir, java_files, manifest_path


def compile_selected_java(
    java_files: list[Path], output_dir: Path, run_id: str, java_release: str
) -> tuple[Path, Path]:
    """Compile selected sources into the class directory consumed by EvoSuite."""
    javac = shutil.which("javac")
    if not javac:
        raise RuntimeError("javac was not found on PATH")

    classes_dir = output_dir / "compiled_classes" / run_id
    classes_dir.mkdir(parents=True, exist_ok=True)
    log_path = classes_dir / "javac.log"
    command = [
        javac,
        "--release",
        java_release,
        "-d",
        str(classes_dir.resolve()),
        *(str(path.resolve()) for path in java_files),
    ]
    result = subprocess.run(command, text=True, capture_output=True, check=False)
    log_path.write_text(result.stdout + result.stderr, encoding="utf-8")
    if result.returncode != 0:
        raise RuntimeError(
            f"javac failed with exit code {result.returncode}; see {log_path}"
        )
    return classes_dir, log_path


def run_evosuite(
    evosuite_jar: Path,
    evosuite_java: Path | None,
    classes_dir: Path,
    output_dir: Path,
    run_id: str,
    seed: int,
    search_budget: int,
) -> tuple[Path, Path]:
    """Generate tests for every compiled class in the selected class directory."""
    if not evosuite_jar.is_file():
        raise FileNotFoundError(f"EvoSuite JAR not found: {evosuite_jar}")
    if evosuite_java:
        if not evosuite_java.is_file():
            raise FileNotFoundError(
                f"EvoSuite Java executable not found: {evosuite_java}"
            )
        java = str(evosuite_java.resolve())
    else:
        java = shutil.which("java")
    if not java:
        raise RuntimeError("java was not found on PATH")

    evosuite_dir = output_dir / "evosuite" / run_id
    evosuite_dir.mkdir(parents=True, exist_ok=True)
    log_path = evosuite_dir / "evosuite.log"
    command = [
        java,
        "-jar",
        str(evosuite_jar.resolve()),
        "-target",
        str(classes_dir.resolve()),
        "-projectCP",
        str(classes_dir.resolve()),
        "-seed",
        str(seed),
        f"-Dsearch_budget={search_budget}",
        "-Dstopping_condition=MaxTime",
    ]
    result = subprocess.run(
        command,
        cwd=evosuite_dir,
        text=True,
        capture_output=True,
        check=False,
    )
    log_path.write_text(result.stdout + result.stderr, encoding="utf-8")
    if result.returncode != 0:
        raise RuntimeError(
            f"EvoSuite failed with exit code {result.returncode}; see {log_path}"
        )
    return evosuite_dir / "evosuite-tests", log_path
