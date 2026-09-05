"""Command-line interface for Java/C differential execution."""

from __future__ import annotations

import argparse
import json
import tempfile
from collections.abc import Sequence
from pathlib import Path
from typing import Any

from .execution_orchestrator import run_all
from .input_manifest import read_inputs


def parse_args(argv: Sequence[str] | None = None) -> argparse.Namespace:
    parser = argparse.ArgumentParser(
        description="Run extracted test inputs against corresponding Java and C programs."
    )
    parser.add_argument("--inputs", type=Path, required=True)
    parser.add_argument("--java-dir", type=Path, required=True)
    parser.add_argument("--c-dir", type=Path, required=True)
    parser.add_argument("--output-dir", "-o", type=Path, required=True)
    parser.add_argument(
        "--timeout",
        type=float,
        default=2.0,
        help="Maximum seconds for each isolated call (default: 2)",
    )
    parser.add_argument(
        "--keep-build",
        action="store_true",
        help="Keep generated harnesses and binaries under OUTPUT_DIR/build",
    )
    return parser.parse_args(argv)


def _write_json(path: Path, document: dict[str, Any]) -> None:
    path.write_text(
        json.dumps(document, indent=2, ensure_ascii=False) + "\n",
        encoding="utf-8",
    )


def _execute(args: argparse.Namespace, build_dir: Path) -> int:
    document = read_inputs(args.inputs)
    java_results, c_results = run_all(
        document,
        args.java_dir,
        args.c_dir,
        build_dir,
        args.timeout,
    )
    _write_json(args.output_dir / "java_results.json", java_results)
    _write_json(args.output_dir / "c_results.json", c_results)
    return len(document["tests"])


def main(argv: Sequence[str] | None = None) -> None:
    args = parse_args(argv)
    if args.timeout <= 0:
        raise SystemExit("--timeout must be positive")
    args.output_dir.mkdir(parents=True, exist_ok=True)

    if args.keep_build:
        build_dir = args.output_dir / "build"
        build_dir.mkdir(parents=True, exist_ok=True)
        test_count = _execute(args, build_dir)
    else:
        # Harness sources and binaries are implementation details unless the
        # caller asks to retain them for debugging.
        with tempfile.TemporaryDirectory(prefix="formalbench-runners-") as temporary:
            test_count = _execute(args, Path(temporary))

    print(f"Wrote {test_count} Java/C test results to {args.output_dir}")


if __name__ == "__main__":
    main()
