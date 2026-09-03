"""Classify, sample, and report on FormalBench program records."""

from __future__ import annotations

import csv
import json
import random
import re
from collections import Counter, defaultdict
from pathlib import Path
from typing import Any, Iterable

CATEGORIES = (
    "sequential",
    "branch",
    "single_path_loop",
    "multi_path_loop",
    "nested",
)

SAMPLING_CATEGORIES = (*CATEGORIES, "recursive")


HARD_EXCLUSION_PATTERNS: tuple[tuple[str, str], ...] = (
    (r"\bString(?:Builder|Buffer)?\b", "uses Java strings"),
    (
        r"\b(?:ArrayList|LinkedList|HashMap|TreeMap|Map|HashSet|TreeSet|Set|"
        r"Collection|Collections|Queue|Deque|PriorityQueue|Iterator)\b",
        "uses Java collections",
    ),
    (
        r"\b(?:Integer|Long|Double|Float|Boolean|Character|Short|Byte)"
        r"(?:\s*\[\s*\])?\s+[A-Za-z_]\w*",
        "declares boxed primitive values",
    ),
    (
        r"\b(?:Integer|Long|Double|Float|Boolean|Character|Short|Byte)"
        r"\s*\.\s*(?:valueOf|decode|compareTo)\s*\(",
        "uses boxed primitive operations",
    ),
    (r"\b(?:BigInteger|BigDecimal)\b", "uses Java arbitrary-precision numbers"),
    (
        r"\b(?:Scanner|BufferedReader|InputStream|OutputStream|File|Files|"
        r"Path|Paths)\b",
        "uses Java I/O APIs",
    ),
    (r"\b(?:try|catch|finally|throw|throws)\b", "uses Java exceptions"),
    (r"\.stream\s*\(|\bStream\s*<|->", "uses streams or lambdas"),
    (
        r"\b(?:Thread|Runnable|synchronized|volatile)\b",
        "uses concurrency features",
    ),
    (
        r"\b(?:Class|Method|Field)\s*<|\.getClass\s*\(",
        "uses reflection or runtime type information",
    ),
    (r"\bObject\b", "uses Object values"),
)


METHOD_DECLARATION_RE = re.compile(
    r"\b(?:public|protected|private)?\s*(?:static\s+)?(?:final\s+)?"
    r"(?:void|boolean|byte|short|int|long|float|double|char|[A-Z]\w*)(?:\[\])*"
    r"\s+([A-Za-z_]\w*)\s*\([^;{}]*\)\s*\{"
)


def remove_comments_imports_and_literals(code: str) -> str:
    """Remove text that should not affect lexical feature detection."""
    cleaned = re.sub(r"/\*.*?\*/", " ", code, flags=re.DOTALL)
    cleaned = re.sub(r"//[^\n]*", " ", cleaned)
    cleaned = re.sub(r"^\s*import\s+[^;]+;\s*$", " ", cleaned, flags=re.MULTILINE)
    cleaned = re.sub(r'"(?:\\.|[^"\\])*"', '""', cleaned)
    cleaned = re.sub(r"'(?:\\.|[^'\\])'", "''", cleaned)
    return cleaned


def detect_non_array_object_creation(code: str) -> bool:
    """Check whether Java code creates an object or non-primitive array."""
    primitive_array_types = {
        "byte",
        "short",
        "int",
        "long",
        "float",
        "double",
        "boolean",
        "char",
    }
    for match in re.finditer(r"\bnew\s+([A-Za-z_]\w*)\s*(\[|\()", code):
        type_name, delimiter = match.groups()
        if delimiter == "(" or type_name not in primitive_array_types:
            return True
    return False


def integer_overflow_risk(code: str) -> str:
    """Record whether integer overflow reasoning may be required."""
    if re.search(r"\+\+|--|(?<!\+)[+](?!\+)|(?<!-)-(?![-=>])|[*%/]", code):
        return "possible"
    return "low"


def extract_features(record: dict[str, Any]) -> dict[str, Any]:
    """Extract translation-relevant features from a program record."""
    code = record["code"]
    lexical_code = remove_comments_imports_and_literals(code)
    category = record["category"]
    methods = METHOD_DECLARATION_RE.findall(lexical_code)
    method_counts = Counter(methods)

    recursive_methods = sorted(
        name
        for name in method_counts
        if len(re.findall(rf"\b{re.escape(name)}\s*\(", lexical_code))
        > method_counts[name]
    )

    has_loop_syntax = bool(re.search(r"\b(?:for|while|do)\b", lexical_code))
    has_array = bool(
        re.search(r"\[\s*\]", lexical_code)
        or re.search(r"\[[^\]]+\]", lexical_code)
    )
    loop_depth = 2 if category == "nested" else 1 if has_loop_syntax else 0

    return {
        "has_if": bool(re.search(r"\bif\s*\(", lexical_code)),
        "has_switch": bool(re.search(r"\bswitch\s*\(", lexical_code)),
        "has_loop": has_loop_syntax,
        "estimated_loop_depth": loop_depth,
        "has_array": has_array,
        "has_multidimensional_array": bool(
            re.search(r"\[\s*\]\s*\[", lexical_code)
        ),
        "has_break_or_continue": bool(
            re.search(r"\b(?:break|continue)\s*;", lexical_code)
        ),
        "has_floating_point": bool(
            re.search(r"\b(?:float|double)\b", lexical_code)
        ),
        "has_math_api": bool(re.search(r"\bMath\s*\.", lexical_code)),
        "has_bitwise_or_shift": bool(
            re.search(
                r">>>|>>|<<|(?<!&)&(?!&)|(?<!\|)\|(?!\|)|\^|~",
                lexical_code,
            )
        ),
        "method_count": len(methods),
        "has_helper_method": len(methods) > 1,
        "has_recursion": bool(recursive_methods),
        "recursive_methods": recursive_methods,
        "overflow_risk": integer_overflow_risk(lexical_code),
    }


def classify(record: dict[str, Any], exclude_review: bool) -> dict[str, Any]:
    """Classify a program as eligible, excluded, or requiring review."""
    code = record["code"]
    lexical_code = remove_comments_imports_and_literals(code)
    features = extract_features(record)

    exclusion_reasons = [
        reason
        for pattern, reason in HARD_EXCLUSION_PATTERNS
        if re.search(pattern, lexical_code)
    ]
    if detect_non_array_object_creation(lexical_code):
        exclusion_reasons.append("creates Java objects")

    review_reasons: list[str] = []
    if features["has_floating_point"]:
        review_reasons.append("review floating-point semantics")
    if features["has_math_api"]:
        review_reasons.append("check the corresponding C math-library operation")
    if features["has_bitwise_or_shift"]:
        review_reasons.append("check bitwise and shift translation")
    if features["has_helper_method"]:
        review_reasons.append("review helper-method translation")
    if features["has_break_or_continue"]:
        review_reasons.append("review nontrivial loop control")
    if features["has_switch"]:
        review_reasons.append("review switch translation")

    exclusion_reasons = list(dict.fromkeys(exclusion_reasons))
    review_reasons = list(dict.fromkeys(review_reasons))
    eligible = not exclusion_reasons and not (exclude_review and review_reasons)

    return {
        **record,
        "sampling_category": (
            "recursive" if features["has_recursion"] else record["category"]
        ),
        "features": features,
        "eligible": eligible,
        "exclusion_reasons": exclusion_reasons,
        "review_required": bool(review_reasons),
        "review_reasons": review_reasons,
    }


def load_jsonl(path: Path) -> list[dict[str, Any]]:
    """Load and validate program records from a JSON Lines file."""
    records: list[dict[str, Any]] = []
    required = {"code", "class_name", "javadoc", "category"}
    with path.open("r", encoding="utf-8") as source:
        for line_number, line in enumerate(source, start=1):
            if not line.strip():
                continue
            try:
                record = json.loads(line)
            except json.JSONDecodeError as error:
                raise ValueError(
                    f"Invalid JSON on line {line_number}: {error}"
                ) from error
            missing = required - record.keys()
            if missing:
                raise ValueError(
                    f"Line {line_number} is missing fields: {sorted(missing)}"
                )
            if record["category"] not in CATEGORIES:
                raise ValueError(
                    f"Line {line_number} has unexpected category "
                    f"{record['category']!r}"
                )
            records.append(record)
    return records


def write_jsonl(path: Path, records: Iterable[dict[str, Any]]) -> None:
    """Write program records to a UTF-8 JSON Lines file."""
    with path.open("w", encoding="utf-8") as target:
        for record in records:
            target.write(json.dumps(record, ensure_ascii=False) + "\n")


def write_review_csv(path: Path, records: list[dict[str, Any]]) -> None:
    """Write classification results and selected features to a CSV file."""
    fieldnames = (
        "class_name",
        "category",
        "sampling_category",
        "eligible",
        "review_required",
        "exclusion_reasons",
        "review_reasons",
        "overflow_risk",
        "has_if",
        "has_loop",
        "estimated_loop_depth",
        "has_array",
        "has_recursion",
        "method_count",
    )
    with path.open("w", encoding="utf-8", newline="") as target:
        writer = csv.DictWriter(target, fieldnames=fieldnames)
        writer.writeheader()
        for record in records:
            features = record["features"]
            writer.writerow(
                {
                    "class_name": record["class_name"],
                    "category": record["category"],
                    "sampling_category": record["sampling_category"],
                    "eligible": record["eligible"],
                    "review_required": record["review_required"],
                    "exclusion_reasons": "; ".join(record["exclusion_reasons"]),
                    "review_reasons": "; ".join(record["review_reasons"]),
                    "overflow_risk": features["overflow_risk"],
                    "has_if": features["has_if"],
                    "has_loop": features["has_loop"],
                    "estimated_loop_depth": features["estimated_loop_depth"],
                    "has_array": features["has_array"],
                    "has_recursion": features["has_recursion"],
                    "method_count": features["method_count"],
                }
            )


def stratified_sample(
    eligible: list[dict[str, Any]], per_category: int, seed: int
) -> list[dict[str, Any]]:
    """Select a reproducible sample from each program category."""
    if per_category <= 0:
        return []
    grouped: dict[str, list[dict[str, Any]]] = defaultdict(list)
    for record in eligible:
        grouped[record["sampling_category"]].append(record)

    rng = random.Random(seed)
    selected: list[dict[str, Any]] = []
    for category in SAMPLING_CATEGORIES:
        population = sorted(grouped[category], key=lambda item: item["class_name"])
        selected.extend(rng.sample(population, min(per_category, len(population))))
    return selected


def count_by_category(records: Iterable[dict[str, Any]]) -> dict[str, int]:
    """Count program records in each supported category."""
    counts = Counter(record["category"] for record in records)
    return {category: counts[category] for category in CATEGORIES}


def count_by_sampling_category(
    records: Iterable[dict[str, Any]],
) -> dict[str, int]:
    """Count records using the derived categories used for sampling."""
    counts = Counter(record["sampling_category"] for record in records)
    return {category: counts[category] for category in SAMPLING_CATEGORIES}
