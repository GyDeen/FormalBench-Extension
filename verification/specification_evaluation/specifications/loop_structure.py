"""Conservative matching and anchoring for loops in source-token streams."""
from __future__ import annotations

import re
from collections.abc import Callable
from typing import Any

from verification.specification_evaluation.manifest import InputError
from verification.specification_evaluation.specifications.token_structure import FunctionStructure, pairs, statement_end


def _parents(function: FunctionStructure, loop: int) -> tuple[int, ...]:
    """Return the token indexes of loops that lexically enclose this loop."""
    return tuple(index for index in function.loops
                 if index < loop < function.loop_ends.get(index, index))


def _header(function: FunctionStructure, loop: int,
            normalize: Callable[[list[str]], list[str]]) -> tuple[Any, ...]:
    """Normalize a for/while header so it can be compared across sources."""
    tokens = function.tokens
    if tokens[loop] not in {"for", "while"}:
        raise InputError("Loop matching supports only for/while loops")
    clauses, start = [], loop + 2
    end = function.brackets[loop + 1]
    cursor = start
    while cursor <= end:
        if cursor == end or tokens[cursor] == ";":
            clause = normalize(tokens[start:cursor])
            while clause and clause[0] == "(" and pairs(clause).get(0) == len(clause) - 1:
                clause = clause[1:-1]
            clauses.append(tuple(clause))
            start = cursor + 1
        cursor = function.brackets.get(cursor, cursor) + 1
    return tokens[loop], tuple(clauses)


def match_loop_structure(before: list[str], after: list[str], original_span: tuple,
                         target_span: tuple, *,
                         normalize_header: Callable[[list[str]], list[str]] | None = None,
                         validate_deleted_gap: Callable[[int, int, int, list[str]], bool] | None = None
                         ) -> tuple[dict[int, int], dict[int, int]]:
    """Map surviving loops and identify safe empty-statement replacements.

    Loop headers must map uniquely and in order, with nesting preserved. Only
    deleted leaf loops followed by a mapped sibling can be omitted. A caller
    may validate intervening tokens using language-specific declaration rules.
    """
    normalize_header = normalize_header or (lambda tokens: tokens)
    original = FunctionStructure(before, original_span)
    target = FunctionStructure(after, target_span)
    if len(original.loops) == len(target.loops):
        mapping = dict(zip(original.loops, target.loops))
    elif len(original.loops) > len(target.loops):
        signatures = {index: _header(original, index, normalize_header)
                     for index in original.loops}
        mapping = {}
        for target_loop in target.loops:
            candidates = [index for index, signature in signatures.items()
                          if signature == _header(target, target_loop, normalize_header)]
            if len(candidates) != 1 or candidates[0] in mapping:
                raise InputError("Cannot uniquely map surviving loop headers")
            mapping[candidates[0]] = target_loop
        if list(mapping) != sorted(mapping):
            raise InputError("Loop deletion changes surviving loop order")
    else:
        raise InputError("Mutant introduces loops without annotation correspondence")

    for old, new in mapping.items():
        parents = _parents(original, old)
        if (before[old] != after[new] or any(parent not in mapping for parent in parents)
                or tuple(mapping[parent] for parent in parents) != _parents(target, new)):
            raise InputError("Loop correspondence requires preserved loop nesting")

    deleted = {}
    for old in original.loops:
        if old in mapping:
            continue
        if any(old in _parents(original, child) for child in original.loops):
            raise InputError("Cannot omit annotations for a deleted non-leaf loop")
        following = next((index for index in original.loops if index > old
                          and _parents(original, index) == _parents(original, old)), None)
        if following not in mapping:
            raise InputError("Deleted loop has no preserved following sibling")
        gap = before[original.loop_ends[old] + 1:following]
        if gap and (validate_deleted_gap is None
                    or not validate_deleted_gap(old, following, mapping[following], gap)):
            raise InputError("Deleted loop has an unvalidated gap before its following sibling")
        target_loop = mapping[following]
        null = target_loop - 1
        if after[null] != ";" or after[null - 1] not in {";", "{", "}"}:
            raise InputError("Deleted loop is not replaced by a distinct empty statement")
        if null in deleted.values():
            raise InputError("Several deleted loops share an ambiguous empty statement")
        deleted[old] = null
    return mapping, deleted


def empty_statement_anchor(before: list[str], after: list[str], index: int,
                           mapping: dict[int, int]) -> int | None:
    """Keep an assertion attached when its following call becomes a null statement."""
    brackets = pairs(before)
    callee_end = index
    while (callee_end + 2 < len(before) and before[callee_end + 1] == "."
           and re.fullmatch(r"[A-Za-z_$][A-Za-z_0-9$]*", before[callee_end + 2])):
        callee_end += 2
    if (callee_end + 1 >= len(before) or before[callee_end + 1] != "("
            or before[index] in {"if", "for", "while", "switch", "sizeof"}):
        return None
    end = statement_end(before, brackets, index)
    left, right = mapping.get(index - 1), mapping.get(end + 1)
    if (left is None or right is None or before[index - 1] not in {"{", ";", "}"}
            or after[left + 1:right] != [";"]):
        return None
    return left + 1
