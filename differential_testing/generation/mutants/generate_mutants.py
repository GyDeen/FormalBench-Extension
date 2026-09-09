from __future__ import annotations

import argparse
import hashlib
import json
import os
import re
import subprocess
import tempfile
from pathlib import Path

from ..java_sources import extract_selected_java

DEFAULT_MML = (
    Path(__file__).resolve().parents[3]
    / "FormalBench/FormalBench/config/major.mml.bin"
)


def load_selection(path: Path, class_names: list[str] | None) -> list[dict]:
    records = [json.loads(line) for line in path.read_text(encoding="utf-8").splitlines()
               if line.strip()]
    if class_names:
        wanted = set(class_names)
        records = [record for record in records if record.get("class_name") in wanted]
        missing = wanted - {record["class_name"] for record in records}
        if missing:
            raise ValueError("Classes absent from selection: " + ", ".join(sorted(missing)))
    if not records:
        raise ValueError("Selection contains no programs")
    seen = set()
    for record in records:
        name = record.get("class_name")
        if not isinstance(name, str) or not re.fullmatch(r"[A-Za-z_$][A-Za-z0-9_$]*", name):
            raise ValueError(f"Invalid Java class name: {name!r}")
        if name in seen:
            raise ValueError(f"Duplicate class in selection: {name}")
        seen.add(name)
        if not isinstance(record.get("code"), str) or not record["code"].strip():
            raise ValueError(f"Missing Java source code for {name}")
        record.setdefault("category", "unknown")
    return records


def generate_one(source: Path, output: Path, major: Path, mml: Path,
                 env: dict[str, str], timeout: int) -> dict:
    """Run the same export command as FormalBench in an isolated class directory."""
    output.mkdir(parents=True, exist_ok=False)
    command = [str(major), "--mml", str(mml), str(source), "--export", "export.mutants"]
    log = output / "major.log"
    result = {
        "class_name": source.stem,
        "source_file": str(source),
        "command": command,
        "working_directory": str(output),
        "log": str(log),
    }
    with log.open("w", encoding="utf-8") as stream:
        try:
            process = subprocess.run(command, cwd=output, env=env, stdout=stream,
                                     stderr=subprocess.STDOUT, timeout=timeout, check=False)
            result["return_code"] = process.returncode
            result["status"] = "success" if process.returncode == 0 else "failed"
        except (OSError, subprocess.TimeoutExpired) as error:
            result["status"] = "failed"
            result["error"] = str(error)
            stream.write(f"\n{error}\n")
    mutant_dir = output / "mutants"
    sources = sorted(mutant_dir.rglob("*.java"))
    result["mutant_source_files"] = [str(path) for path in sources]
    result["mutant_count"] = len({path.relative_to(mutant_dir).parts[0] for path in sources})
    if result["status"] == "success" and not sources:
        result["status"] = "no_mutants"
    return result


def main(argv: list[str] | None = None) -> None:
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("--selection", type=Path, required=True,
                        help="JSONL containing original class_name and code fields")
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
        records = load_selection(args.selection, args.class_name)
    except (OSError, ValueError) as error:
        parser.error(str(error))

    args.output_dir.mkdir(parents=True, exist_ok=True)
    # Fresh runs prevent failures or changed tool configurations from reusing
    # stale mutants. Source IDs remain content-derived via the shared extractor.
    run_dir = Path(tempfile.mkdtemp(prefix="run_", dir=args.output_dir.resolve()))
    _, sources, manifest = extract_selected_java(records, run_dir, seed=0, per_category=0)
    summary = {
        "selection": str(args.selection.resolve()),
        "selection_manifest": str(manifest),
        "major_executable": str(major),
        "mml": str(mml),
        "mml_sha256": hashlib.sha256(mml.read_bytes()).hexdigest(),
        "java_home": env.get("JAVA_HOME"),
        "programs": [],
    }
    summary_path = run_dir / "mutant_generation_summary.json"
    for source in sources:
        result = generate_one(source, run_dir / source.stem, major, mml, env, args.timeout)
        summary["programs"].append(result)
        summary_path.write_text(json.dumps(summary, indent=2) + "\n", encoding="utf-8")
        print(f"{source.stem}: {result['status']} ({result['mutant_count']} mutants)", flush=True)
    print(f"Summary: {summary_path}")
    if any(result["status"] == "failed" for result in summary["programs"]):
        raise SystemExit(1)


if __name__ == "__main__":
    main()
