"""Command-line entry point for filtering and sampling FormalBench programs."""

from __future__ import annotations

import argparse
import json
from collections import Counter
from pathlib import Path
from typing import Any, Sequence

from .filtering import (
    classify,
    count_by_category,
    count_by_sampling_category,
    load_jsonl,
    stratified_sample,
    write_jsonl,
    write_review_csv,
)
from .java_test_generation import (
    compile_selected_java,
    extract_selected_java,
    run_evosuite,
)


def parse_args(argv: Sequence[str] | None = None) -> argparse.Namespace:
    """Parse command-line options for filtering and sampling programs."""
    parser = argparse.ArgumentParser(
        description="Filter FormalBench JSONL and create a balanced pilot sample."
    )
    parser.add_argument("input", type=Path, help="Path to meta_data.jsonl")
    parser.add_argument(
        "--output-dir",
        type=Path,
        default=Path("program_filter_output"),
        help="Output directory (default: program_filter_output)",
    )
    parser.add_argument(
        "--sample-per-category",
        type=int,
        default=5,
        help=(
            "Eligible programs sampled from each category; "
            "use 0 to disable (default: 5)"
        ),
    )
    parser.add_argument(
        "--seed",
        type=int,
        default=726,
        help="Fixed random seed for reproducibility (default: 726)",
    )
    parser.add_argument(
        "--exclude-review",
        action="store_true",
        help="Also exclude programs carrying any manual-review flag",
    )
    parser.add_argument(
        "--evosuite-jar",
        type=Path,
        help=(
            "Optional path to evosuite.jar; when supplied, generate tests "
            "after compilation"
        ),
    )
    parser.add_argument(
        "--evosuite-java",
        type=Path,
        help=(
            "Optional Java executable used to run EvoSuite "
            "(for example, a Java 11 binary)"
        ),
    )
    parser.add_argument(
        "--search-budget",
        type=int,
        default=60,
        help="EvoSuite search budget in seconds per class (default: 60)",
    )
    parser.add_argument(
        "--java-release",
        default="8",
        help="Java release used to compile selected programs (default: 8)",
    )
    parser.add_argument(
        "--skip-compile",
        action="store_true",
        help="Extract selected .java files without compiling them",
    )
    return parser.parse_args(argv)


def main(argv: Sequence[str] | None = None) -> None:
    """Run the filter, sampling, compilation, and test-generation pipeline."""
    args = parse_args(argv)
    if args.sample_per_category < 0:
        raise SystemExit("--sample-per-category must be zero or greater")
    if args.search_budget <= 0:
        raise SystemExit("--search-budget must be greater than zero")
    if args.evosuite_jar and args.skip_compile:
        raise SystemExit("--evosuite-jar cannot be combined with --skip-compile")

    records = load_jsonl(args.input)
    annotated = [classify(record, args.exclude_review) for record in records]
    eligible = [record for record in annotated if record["eligible"]]
    excluded = [record for record in annotated if not record["eligible"]]
    sample = stratified_sample(eligible, args.sample_per_category, args.seed)

    args.output_dir.mkdir(parents=True, exist_ok=True)
    write_jsonl(args.output_dir / "annotated_programs.jsonl", annotated)
    write_jsonl(args.output_dir / "eligible_programs.jsonl", eligible)
    write_jsonl(args.output_dir / "excluded_programs.jsonl", excluded)
    write_jsonl(args.output_dir / "pilot_sample.jsonl", sample)
    write_review_csv(args.output_dir / "review_table.csv", annotated)

    generated_artifacts: dict[str, Any] = {
        "java_source": None,
        "selection_manifest": None,
        "compiled_class": None,
        "javac_log": None,
        "evosuite_test_directory": None,
        "evosuite_log": None,
    }
    if sample:
        java_dir, java_files, manifest_path = extract_selected_java(
            sample,
            args.output_dir,
            args.seed,
            args.sample_per_category,
        )
        generated_artifacts["java_source"] = str(java_dir.resolve())
        generated_artifacts["selection_manifest"] = str(manifest_path.resolve())

        if not args.skip_compile:
            classes_dir, javac_log = compile_selected_java(
                java_files,
                args.output_dir,
                java_dir.name,
                args.java_release,
            )
            generated_artifacts["compiled_class"] = str(classes_dir.resolve())
            generated_artifacts["javac_log"] = str(javac_log.resolve())

            if args.evosuite_jar:
                test_dir, evosuite_log = run_evosuite(
                    args.evosuite_jar,
                    args.evosuite_java,
                    classes_dir,
                    args.output_dir,
                    java_dir.name,
                    args.seed,
                    args.search_budget,
                )
                generated_artifacts["evosuite_test_directory"] = str(
                    test_dir.resolve()
                )
                generated_artifacts["evosuite_log"] = str(evosuite_log.resolve())

    exclusion_counts = Counter(
        reason for record in excluded for reason in record["exclusion_reasons"]
    )
    review_counts = Counter(
        reason for record in annotated for reason in record["review_reasons"]
    )
    summary = {
        "input": str(args.input.resolve()),
        "seed": args.seed,
        "sample_per_category": args.sample_per_category,
        "exclude_review": args.exclude_review,
        "total": len(records),
        "eligible": len(eligible),
        "excluded": len(excluded),
        "review_required": sum(record["review_required"] for record in annotated),
        "total_by_category": count_by_category(records),
        "eligible_by_category": count_by_category(eligible),
        "total_by_sampling_category": count_by_sampling_category(annotated),
        "eligible_by_sampling_category": count_by_sampling_category(eligible),
        "sample_by_sampling_category": count_by_sampling_category(sample),
        "generated_artifacts": generated_artifacts,
        "exclusion_reason_counts": dict(exclusion_counts.most_common()),
        "review_reason_counts": dict(review_counts.most_common()),
    }
    with (args.output_dir / "summary.json").open("w", encoding="utf-8") as target:
        json.dump(summary, target, indent=2, ensure_ascii=False)
        target.write("\n")

    print(json.dumps(summary, indent=2, ensure_ascii=False))


if __name__ == "__main__":
    main()
