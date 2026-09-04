"""Command-line entry point for EvoSuite input extraction."""

from __future__ import annotations

import argparse
import json
from collections.abc import Sequence
from pathlib import Path

if __package__:
    from .input_extractor import extract_directory, extract_file
else:
    from input_extractor import extract_directory, extract_file  # type: ignore


def parse_args(argv: Sequence[str] | None = None) -> argparse.Namespace:
    parser = argparse.ArgumentParser(
        description="Extract input fixtures and target calls from EvoSuite Java tests."
    )
    parser.add_argument(
        "tests_dir", type=Path, help="Directory containing *_ESTest.java"
    )
    parser.add_argument(
        "--output",
        "-o",
        type=Path,
        help="Output JSON path (prints to standard output when omitted)",
    )
    return parser.parse_args(argv)


def main(argv: Sequence[str] | None = None) -> None:
    args = parse_args(argv)
    document = extract_directory(args.tests_dir)
    rendered = json.dumps(document, indent=2, ensure_ascii=False) + "\n"
    if args.output:
        args.output.parent.mkdir(parents=True, exist_ok=True)
        args.output.write_text(rendered, encoding="utf-8")
        print(
            f"Extracted {len(document['tests'])} semantic tests "
            f"from {document['source']['test_file_count']} files to {args.output}"
        )
    else:
        print(rendered, end="")


if __name__ == "__main__":
    main()
