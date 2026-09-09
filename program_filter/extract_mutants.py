from __future__ import annotations

import argparse
import json
import re
from collections import defaultdict
from pathlib import Path

from differential_testing.generation.java_test_generation import extract_selected_java


def path_component(value: str) -> str:
    """Require dataset identifiers to remain within their output directory."""
    if not isinstance(value, str) or not re.fullmatch(r"[A-Za-z0-9_$-]+", value):
        raise ValueError(f"Invalid dataset identifier: {value!r}")
    return value


def main(argv: list[str] | None = None) -> None:
    parser = argparse.ArgumentParser(description=__doc__)
    selection = parser.add_mutually_exclusive_group(required=True)
    selection.add_argument(
        "--selection", type=Path, help="Selected programs JSONL, e.g. pilot_sample.jsonl"
    )
    selection.add_argument(
        "--class-name", action="append", help="Original class name; repeat to select multiple"
    )
    parser.add_argument(
        "--diverse-dir", type=Path, default=Path("FormalBench-data/diverse")
    )
    parser.add_argument(
        "--output-dir", type=Path,
        default=Path("FormalBench-data/FilteredData/selected_mutants"),
    )
    args = parser.parse_args(argv)

    if args.selection:
        records = [
            json.loads(line)
            for line in args.selection.read_text(encoding="utf-8").splitlines()
            if line.strip()
        ]
        selected = {record["class_name"]: record.get("category", "unknown") for record in records}
    else:
        selected = dict.fromkeys(args.class_name, "unknown")
    if not selected:
        parser.error("Selection contains no classes")

    mapping = json.loads((args.diverse_dir / "natural.json").read_text(encoding="utf-8"))
    missing = sorted(name for name in selected if not mapping.get(name))
    if len(missing) == len(selected):
        parser.error("No mapped mutants for: " + ", ".join(missing))

    # Read all requested sources before writing, so missing files cannot leave
    # an apparently successful partial extraction. Preserve original/rule identity.
    groups = defaultdict(dict)
    for original, category in sorted(selected.items()):
        path_component(original)
        for rule, mutant in mapping.get(original, []):
            path_component(rule)
            path_component(mutant)
            source = args.diverse_dir / rule / f"{mutant}.java"
            groups[original, rule][mutant] = {
                "class_name": mutant,
                "category": category,
                "code": source.read_text(encoding="utf-8"),
            }

    entries = []
    for (original, rule), mutants in sorted(groups.items()):
        # No sampling is performed. Zero values are neutral bookkeeping for the
        # shared extractor's content-derived run ID and selection manifest.
        java_dir, files, manifest = extract_selected_java(
            list(mutants.values()), args.output_dir / original / rule,
            seed=0, per_category=0,
        )
        entries.append({
            "original_class": original,
            "rule": rule,
            "mutant_count": len(files),
            "java_source": str(java_dir.resolve()),
            "selection_manifest": str(manifest.resolve()),
        })

    summary = {
        "diverse_directory": str(args.diverse_dir.resolve()),
        "selected_classes": sorted(selected),
        "classes_without_mapped_mutants": missing,
        "mutant_count": sum(entry["mutant_count"] for entry in entries),
        "groups": entries,
    }
    summary_path = args.output_dir / "extraction_summary.json"
    summary_path.write_text(json.dumps(summary, indent=2) + "\n", encoding="utf-8")
    print(f"Extracted {summary['mutant_count']} mutants for {len(selected)} classes.")
    print(f"Summary: {summary_path}")
    if missing:
        print("Skipped classes without mapped mutants: " + ", ".join(missing))


if __name__ == "__main__":
    main()
