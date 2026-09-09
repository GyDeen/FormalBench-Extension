from __future__ import annotations

import shutil
import subprocess
from pathlib import Path


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
    # Compile the exact selected file list rather than scanning the directory;
    # this prevents stale files from entering the EvoSuite target set.
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
    # EvoSuite writes several relative output directories, so anchor them under
    # this run's unique directory.
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
