"""Materialize selected Java sources for test and mutant generation."""

from __future__ import annotations

import hashlib
import json
import re
from pathlib import Path
from typing import Any


def sample_run_id(sample: list[dict[str, Any]], seed: int, per_category: int) -> str:
    """Build a stable directory name for one exact sampled dataset."""
    # Include source code as well as class names so edited programs cannot
    # accidentally reuse compiled artifacts from an older sample.
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
        # Match Java's package-to-directory convention before invoking javac.
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


