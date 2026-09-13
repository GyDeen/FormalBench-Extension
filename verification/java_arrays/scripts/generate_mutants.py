#!/usr/bin/env python3
"""Generate body-only verification mutants from an exact JSON mutation plan."""

from __future__ import annotations

import argparse
import hashlib
import json
import re
from pathlib import Path


WORKSPACE = Path(__file__).resolve().parents[1]
PROJECT = WORKSPACE.parents[1]
GENERATED = WORKSPACE / "generated"
BASELINE = GENERATED / "baseline.c"
MANIFEST = GENERATED / "manifest.json"
DEFAULT_PLAN = WORKSPACE / "mutations.json"


def sha256_text(text: str) -> str:
    return hashlib.sha256(text.encode("utf-8")).hexdigest()


def relative(path: Path) -> str:
    return path.relative_to(PROJECT).as_posix()


def function_body_span(source: str, function: str) -> tuple[int, int]:
    pattern = re.compile(
        rf"(?m)^[^#\n]*\b{re.escape(function)}\s*\([^;\n]*\)\s*\{{"
    )
    matches = list(pattern.finditer(source))
    if len(matches) != 1:
        raise ValueError(f"Expected one definition of {function}, found {len(matches)}")
    opening = source.find("{", matches[0].start(), matches[0].end())
    depth = 0
    in_string: str | None = None
    escaped = False
    index = opening
    while index < len(source):
        character = source[index]
        following = source[index:index + 2]
        if in_string:
            if escaped:
                escaped = False
            elif character == "\\":
                escaped = True
            elif character == in_string:
                in_string = None
        elif following == "/*":
            end = source.find("*/", index + 2)
            if end < 0:
                raise ValueError("Unterminated block comment")
            index = end + 1
        elif following == "//":
            end = source.find("\n", index + 2)
            index = len(source) if end < 0 else end
        elif character in ('"', "'"):
            in_string = character
        elif character == "{":
            depth += 1
        elif character == "}":
            depth -= 1
            if depth == 0:
                return opening + 1, index
        index += 1
    raise ValueError(f"Unterminated definition of {function}")


def validate_plan(document: dict) -> list[dict]:
    if document.get("schema_version") != "1.0":
        raise ValueError("Mutation plan schema_version must be 1.0")
    mutations = document.get("mutations")
    if not isinstance(mutations, list):
        raise ValueError("Mutation plan must contain a mutations list")
    ids: set[str] = set()
    for mutation in mutations:
        required = {"id", "function", "description", "find", "replace"}
        if not isinstance(mutation, dict) or not required <= mutation.keys():
            raise ValueError(f"Every mutation requires {sorted(required)}")
        mutant_id = mutation["id"]
        if not re.fullmatch(r"\d{4}", mutant_id):
            raise ValueError(f"Mutation ID must contain four digits: {mutant_id!r}")
        if mutant_id in ids:
            raise ValueError(f"Duplicate mutation ID: {mutant_id}")
        ids.add(mutant_id)
        if not mutation["find"] or mutation["find"] == mutation["replace"]:
            raise ValueError(f"Mutation {mutant_id} must make one non-empty match change")
        if mutation["find"].count("{") - mutation["find"].count("}") != (
            mutation["replace"].count("{") - mutation["replace"].count("}")
        ):
            raise ValueError(f"Mutation {mutant_id} changes brace balance")
    return mutations


def mutate(baseline: str, mutation: dict) -> str:
    body_start, body_end = function_body_span(baseline, mutation["function"])
    body = baseline[body_start:body_end]
    occurrences = body.count(mutation["find"])
    if occurrences != 1:
        raise ValueError(
            f"Mutation {mutation['id']} expected one fragment in "
            f"{mutation['function']}, found {occurrences}"
        )
    changed_body = body.replace(mutation["find"], mutation["replace"], 1)
    return baseline[:body_start] + changed_body + baseline[body_end:]


def driver(mutant_id: str) -> str:
    return f"""/* Generated verification driver: fixed contracts + one mutant. */
#include "../../contracts/helpers.acsl.h"
#include "../../contracts/jintarray.acsl.h"
#include "../mutants/{mutant_id}.c"
"""


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("--plan", type=Path, default=DEFAULT_PLAN)
    parser.add_argument("--check", action="store_true", help="check without writing")
    args = parser.parse_args()

    if not BASELINE.exists() or not MANIFEST.exists():
        raise SystemExit("Run scripts/prepare.py before generating mutants.")
    baseline = BASELINE.read_text(encoding="utf-8")
    manifest = json.loads(MANIFEST.read_text(encoding="utf-8"))
    expected_hash = manifest.get("generated", {}).get("baseline", {}).get("sha256")
    if expected_hash != sha256_text(baseline):
        raise SystemExit("Generated baseline does not match manifest; rerun prepare.py.")

    plan_path = args.plan.resolve()
    document = json.loads(plan_path.read_text(encoding="utf-8"))
    mutations = validate_plan(document)
    outputs: list[tuple[Path, str, Path, str, dict]] = []
    for mutation in mutations:
        mutant_source = mutate(baseline, mutation)
        mutant_id = mutation["id"]
        source_path = GENERATED / "mutants" / f"{mutant_id}.c"
        driver_path = GENERATED / "drivers" / f"mutant_{mutant_id}.c"
        record = {
            "id": mutant_id,
            "function": mutation["function"],
            "description": mutation["description"],
            "plan": relative(plan_path),
            "plan_sha256": sha256_text(plan_path.read_text(encoding="utf-8")),
            "source": {"path": relative(source_path), "sha256": sha256_text(mutant_source)},
            "driver": {"path": relative(driver_path), "sha256": sha256_text(driver(mutant_id))},
        }
        outputs.append((source_path, mutant_source, driver_path, driver(mutant_id), record))

    if args.check:
        problems: list[str] = []
        for source_path, source, driver_path, driver_source, _ in outputs:
            for path, expected in ((source_path, source), (driver_path, driver_source)):
                if not path.exists() or path.read_text(encoding="utf-8") != expected:
                    problems.append(f"missing or stale: {relative(path)}")
        if manifest.get("mutants", []) != [output[4] for output in outputs]:
            problems.append("manifest mutant records are stale")
        if problems:
            print("\n".join(problems))
            return 1
        print("Generated mutants and manifest records are current.")
        return 0

    expected_names = {output[0].name for output in outputs}
    expected_drivers = {output[2].name for output in outputs}
    (GENERATED / "mutants").mkdir(parents=True, exist_ok=True)
    (GENERATED / "drivers").mkdir(parents=True, exist_ok=True)
    for path in (GENERATED / "mutants").glob("*.c"):
        if path.name not in expected_names:
            raise SystemExit(f"Refusing to overwrite plan with unmanaged mutant: {relative(path)}")
    for path in (GENERATED / "drivers").glob("mutant_*.c"):
        if path.name not in expected_drivers:
            raise SystemExit(f"Refusing to overwrite plan with unmanaged driver: {relative(path)}")
    for source_path, source, driver_path, driver_source, _ in outputs:
        source_path.write_text(source, encoding="utf-8")
        driver_path.write_text(driver_source, encoding="utf-8")
    manifest["mutants"] = [output[4] for output in outputs]
    MANIFEST.write_text(json.dumps(manifest, indent=2, sort_keys=True) + "\n", encoding="utf-8")
    print(f"Generated {len(outputs)} verification mutant(s).")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
