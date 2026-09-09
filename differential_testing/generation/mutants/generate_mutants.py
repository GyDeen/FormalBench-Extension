from __future__ import annotations

import argparse
import json
import os
import re
import subprocess
import tempfile
from pathlib import Path

DEFAULT_MML = (
    Path(__file__).resolve().parents[3]
    / "FormalBench/FormalBench/config/major.mml.bin"
)


def find_java_sources(directory: Path, class_names: list[str] | None) -> list[Path]:
    """Read the existing selection without copying or rewriting its sources."""
    if not directory.is_dir():
        raise ValueError(f"Java source directory not found: {directory}")
    sources = sorted(directory.resolve().rglob("*.java"))
    if class_names:
        wanted = set(class_names)
        sources = [source for source in sources if source.stem in wanted]
        missing = wanted - {source.stem for source in sources}
        if missing:
            raise ValueError("Classes absent from Java directory: " + ", ".join(sorted(missing)))
    if not sources:
        raise ValueError(f"No Java sources found in {directory}")
    seen = set()
    for source in sources:
        name = source.stem
        if not re.fullmatch(r"[A-Za-z_$][A-Za-z0-9_$]*", name):
            raise ValueError(f"Invalid Java class filename: {source.name}")
        if name in seen:
            raise ValueError(f"Duplicate class filename in Java directory: {name}")
        seen.add(name)
    return sources


def generate_one(source: Path, output: Path, major: Path, mml: Path,
                 env: dict[str, str], timeout: int) -> dict:
    """Run the same export command as FormalBench in an isolated class directory."""
    output.mkdir(parents=True, exist_ok=False)
    command = [str(major), "--mml", str(mml), str(source), "--export", "export.mutants"]
    log = output / "major.log"
    result = {
        "class_name": source.stem,
        "log": str(log),
    }
    with log.open("w", encoding="utf-8") as stream:
        try:
            # Major mutates during source compilation. Keep its instrumented
            # bytecode out of both selected_java and the original compiled_classes.
            with tempfile.TemporaryDirectory(prefix="major-classes-") as classes:
                process = subprocess.run(command + ["-d", classes], cwd=output,
                                         env=env, stdout=stream,
                                         stderr=subprocess.STDOUT, timeout=timeout, check=False)
            result["status"] = "success" if process.returncode == 0 else "failed"
        except (OSError, subprocess.TimeoutExpired) as error:
            result["status"] = "failed"
            stream.write(f"\n{error}\n")
    mutant_dir = output / "mutants"
    sources = sorted(mutant_dir.rglob("*.java"))
    result["mutant_count"] = len({path.relative_to(mutant_dir).parts[0] for path in sources})
    for path in sources:
        relative = path.relative_to(mutant_dir)
        mutant = mutant_dir / relative.parts[0]
        destination = mutant / "java" / Path(*relative.parts[1:])
        destination.parent.mkdir(parents=True, exist_ok=True)
        path.rename(destination)
        (mutant / "c").mkdir(exist_ok=True)
    if result["status"] == "success" and not sources:
        result["status"] = "no_mutants"
    return result


def main(argv: list[str] | None = None) -> None:
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("--java-dir", type=Path, required=True,
                        help="Existing selected Java source directory; searched recursively")
    parser.add_argument("--class-name", action="append",
                        help="Restrict selection to this class; repeat for multiple classes")
    parser.add_argument("--major-bin", type=Path, required=True,
                        help="Path to Major 3.0.1 bin/major executable")
    parser.add_argument("--mml", type=Path, default=DEFAULT_MML,
                        help="Compiled mutation configuration (default: FormalBench's)")
    parser.add_argument("--java-home", type=Path,
                        help="JDK used by Major; sets JAVA_HOME and prepends its bin to PATH")
    parser.add_argument("--output-dir", type=Path,
                        default=Path("FormalBench-data/FilteredData/fault_mutants"))
    parser.add_argument("--timeout", type=int, default=300,
                        help="Maximum Major runtime per original class in seconds (default: 300)")
    args = parser.parse_args(argv)
    if args.timeout <= 0:
        parser.error("--timeout must be positive")
    major, mml = args.major_bin.resolve(), args.mml.resolve()
    if not major.is_file() or not os.access(major, os.X_OK):
        parser.error(f"Major executable not found or not executable: {major}")
    if not mml.is_file():
        parser.error(f"Mutation configuration not found: {mml}")
    env = os.environ.copy()
    if args.java_home:
        java_home = args.java_home.resolve()
        if not all((java_home / "bin" / tool).is_file() for tool in ("java", "javac")):
            parser.error(f"JDK must contain bin/java and bin/javac: {java_home}")
        env["JAVA_HOME"] = str(java_home)
        env["PATH"] = str(java_home / "bin") + os.pathsep + env.get("PATH", "")
    try:
        sources = find_java_sources(args.java_dir, args.class_name)
    except (OSError, ValueError) as error:
        parser.error(str(error))

    args.output_dir.mkdir(parents=True, exist_ok=True)
    # Fresh runs prevent failures or changed tool configurations from reusing
    # stale mutants. Major reads the original files directly.
    run_dir = Path(tempfile.mkdtemp(prefix="run_", dir=args.output_dir.resolve()))
    summary = []
    summary_path = run_dir / "mutant_generation_summary.json"
    for source in sources:
        result = generate_one(source, run_dir / source.stem, major, mml, env, args.timeout)
        summary.append(result)
        summary_path.write_text(json.dumps(summary, indent=2) + "\n", encoding="utf-8")
        print(f"{source.stem}: {result['status']} ({result['mutant_count']} mutants)", flush=True)
    print(f"Summary: {summary_path}")
    if any(result["status"] == "failed" for result in summary):
        raise SystemExit(1)


if __name__ == "__main__":
    main()
