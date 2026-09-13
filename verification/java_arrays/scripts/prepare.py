#!/usr/bin/env python3
"""Deterministically expand the production Java-array macro for verification."""

from __future__ import annotations

import argparse
import hashlib
import json
import re
from pathlib import Path


WORKSPACE = Path(__file__).resolve().parents[1]
PROJECT = WORKSPACE.parents[1]
RUNTIME = PROJECT / "runtime" / "java_arrays"
HEADER = RUNTIME / "java_arrays.h"
IMPLEMENTATION = RUNTIME / "java_arrays.c"
GENERATED = WORKSPACE / "generated"
TYPES = GENERATED / "types.h"
BASELINE = GENERATED / "baseline.c"
BASELINE_DRIVER = GENERATED / "drivers" / "baseline.c"
MANIFEST = GENERATED / "manifest.json"
MACRO_NAME = "DEFINE_ARRAY"


def sha256_text(text: str) -> str:
    return hashlib.sha256(text.encode("utf-8")).hexdigest()


def relative(path: Path) -> str:
    return path.relative_to(PROJECT).as_posix()


def continuation_end(lines: list[str], start: int) -> int:
    index = start
    while lines[index].rstrip("\n").rstrip().endswith("\\"):
        index += 1
    return index


def split_arguments(arguments: str) -> list[str]:
    result: list[str] = []
    current: list[str] = []
    depth = 0
    for character in arguments:
        if character == "," and depth == 0:
            result.append("".join(current).strip())
            current = []
            continue
        if character in "([{" :
            depth += 1
        elif character in ")]}" :
            depth -= 1
        current.append(character)
    result.append("".join(current).strip())
    return result


def substitute_macro_line(line: str, values: dict[str, str]) -> str:
    for parameter, value in values.items():
        line = line.replace(f"{parameter}##", value)
        line = line.replace(f"##{parameter}", value)
    for parameter, value in values.items():
        line = re.sub(rf"\b{re.escape(parameter)}\b", value, line)
    return line.rstrip()


def extract_macro(source: str) -> tuple[list[str], list[str], int, int]:
    lines = source.splitlines(keepends=True)
    start = next(
        index for index, line in enumerate(lines)
        if line.startswith(f"#define {MACRO_NAME}(")
    )
    end = continuation_end(lines, start)
    declaration = lines[start]
    parameters_text = declaration.split("(", 1)[1].split(")", 1)[0]
    parameters = [parameter.strip() for parameter in parameters_text.split(",")]
    body: list[str] = []
    for line in lines[start + 1:end + 1]:
        physical = line.rstrip("\n").rstrip()
        if physical.endswith("\\"):
            physical = physical[:-1].rstrip()
        body.append(physical)
    if not body or not body[0].lstrip().startswith("struct OBJECT"):
        raise ValueError(f"{MACRO_NAME} must begin with the generated structure")
    return parameters, body, start, end


def extract_invocations(
    lines: list[str], parameters: list[str], after_macro: int,
) -> tuple[list[dict[str, str]], int]:
    invocations: list[dict[str, str]] = []
    undef_index = -1
    pattern = re.compile(rf"^{MACRO_NAME}\((.*)\)\s*$")
    for index in range(after_macro + 1, len(lines)):
        stripped = lines[index].strip()
        match = pattern.match(stripped)
        if match:
            arguments = split_arguments(match.group(1))
            if len(arguments) != len(parameters):
                raise ValueError(f"Invalid {MACRO_NAME} invocation: {stripped}")
            invocations.append(dict(zip(parameters, arguments, strict=True)))
        elif stripped == f"#undef {MACRO_NAME}":
            undef_index = index
            break
    if not invocations or undef_index < 0:
        raise ValueError(f"Could not find {MACRO_NAME} invocations and #undef")
    return invocations, undef_index


def extract_header_types(header: str) -> tuple[list[str], list[str], str]:
    includes = re.findall(r"^#include <[^>]+>$", header, flags=re.MULTILINE)
    typedefs = re.findall(
        r"^typedef struct J\w+Object \*J\w+;$", header, flags=re.MULTILINE,
    )
    enum_match = re.search(
        r"enum JArrayExitCode\s*\{.*?\n\};", header, flags=re.DOTALL,
    )
    if not includes or not typedefs or enum_match is None:
        raise ValueError("Production header layout is not recognized")
    return includes, typedefs, enum_match.group(0)


def render_outputs(header: str, implementation: str) -> tuple[str, str, str]:
    parameters, macro_body, macro_start, macro_end = extract_macro(implementation)
    lines = implementation.splitlines(keepends=True)
    invocations, undef_index = extract_invocations(lines, parameters, macro_end)

    structures: list[str] = []
    expansions: list[str] = []
    for values in invocations:
        expanded = [substitute_macro_line(line, values) for line in macro_body]
        structures.append(expanded[0].strip())
        expansions.append("\n".join(expanded[1:]).strip())

    prefix = "".join(lines[:macro_start])
    prefix = prefix.replace('#include "java_arrays.h"', '#include "types.h"', 1)
    suffix = "".join(lines[undef_index + 1:]).lstrip("\n")
    baseline = prefix.rstrip() + "\n\n" + "\n\n".join(expansions)
    if suffix:
        baseline += "\n\n" + suffix.rstrip()
    baseline += "\n"

    includes, typedefs, enum_definition = extract_header_types(header)
    types = "\n".join([
        "#ifndef FORMALBENCH_VERIFICATION_JAVA_ARRAY_TYPES_H",
        "#define FORMALBENCH_VERIFICATION_JAVA_ARRAY_TYPES_H",
        "",
        *includes,
        "",
        "/* Generated from production opaque handles and DEFINE_ARRAY invocations. */",
        *typedefs,
        "",
        enum_definition,
        "",
        *structures,
        "",
        "#endif",
        "",
    ])

    driver = """/* Generated verification driver: fixed contracts + one implementation. */
#include "../../contracts/helpers.acsl.h"
#include "../../contracts/jintarray.acsl.h"
#include "../baseline.c"
"""
    return types, baseline, driver


def source_manifest(header: str, implementation: str, types: str, baseline: str) -> dict:
    return {
        "schema_version": "1.0",
        "generator": relative(Path(__file__)),
        "sources": {
            "header": {"path": relative(HEADER), "sha256": sha256_text(header)},
            "implementation": {
                "path": relative(IMPLEMENTATION),
                "sha256": sha256_text(implementation),
            },
        },
        "settings": {
            "macro": MACRO_NAME,
            "macro_expansion": "deliberate_explicit_functions",
            "system_header_preprocessing": False,
            "structure_destination": relative(TYPES),
        },
        "generated": {
            "types": {"path": relative(TYPES), "sha256": sha256_text(types)},
            "baseline": {
                "path": relative(BASELINE),
                "sha256": sha256_text(baseline),
            },
        },
        "mutants": [],
    }


def load_manifest() -> dict | None:
    if not MANIFEST.exists():
        return None
    return json.loads(MANIFEST.read_text(encoding="utf-8"))


def generated_mutant_paths() -> list[Path]:
    return sorted((GENERATED / "mutants").glob("*.c")) + sorted(
        (GENERATED / "drivers").glob("mutant_*.c")
    )


def check_current(types: str, baseline: str, driver: str, manifest: dict) -> list[str]:
    problems: list[str] = []
    expected_files = ((TYPES, types), (BASELINE, baseline), (BASELINE_DRIVER, driver))
    for path, expected in expected_files:
        if not path.exists():
            problems.append(f"missing {relative(path)}")
        elif path.read_text(encoding="utf-8") != expected:
            problems.append(f"stale {relative(path)}")
    current = load_manifest()
    if current is None:
        problems.append(f"missing {relative(MANIFEST)}")
    else:
        for key in ("sources", "settings", "generated"):
            if current.get(key) != manifest.get(key):
                problems.append(f"stale manifest field: {key}")
    return problems


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("--check", action="store_true", help="check without writing")
    parser.add_argument(
        "--reset-mutants", action="store_true",
        help="remove generated mutant inputs when refreshing a changed baseline",
    )
    args = parser.parse_args()
    if args.check and args.reset_mutants:
        parser.error("--check and --reset-mutants cannot be combined")

    header = HEADER.read_text(encoding="utf-8")
    implementation = IMPLEMENTATION.read_text(encoding="utf-8")
    types, baseline, driver = render_outputs(header, implementation)
    manifest = source_manifest(header, implementation, types, baseline)

    if args.check:
        problems = check_current(types, baseline, driver, manifest)
        if problems:
            for problem in problems:
                print(problem)
            return 1
        print("Generated baseline and manifest source data are current.")
        return 0

    old_manifest = load_manifest()
    mutants = generated_mutant_paths()
    old_baseline_hash = (
        old_manifest or {}
    ).get("generated", {}).get("baseline", {}).get("sha256")
    baseline_changed = old_baseline_hash not in (None, sha256_text(baseline))
    if mutants and baseline_changed and not args.reset_mutants:
        raise SystemExit(
            "Production inputs changed while generated mutants exist; rerun with "
            "--reset-mutants, then regenerate mutants."
        )
    if args.reset_mutants:
        for path in mutants:
            path.unlink()
    elif old_manifest and not baseline_changed:
        manifest["mutants"] = old_manifest.get("mutants", [])

    TYPES.parent.mkdir(parents=True, exist_ok=True)
    BASELINE_DRIVER.parent.mkdir(parents=True, exist_ok=True)
    (GENERATED / "mutants").mkdir(parents=True, exist_ok=True)
    TYPES.write_text(types, encoding="utf-8")
    BASELINE.write_text(baseline, encoding="utf-8")
    BASELINE_DRIVER.write_text(driver, encoding="utf-8")
    MANIFEST.write_text(json.dumps(manifest, indent=2, sort_keys=True) + "\n", encoding="utf-8")
    print(f"Generated {relative(TYPES)} and {relative(BASELINE)}.")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
