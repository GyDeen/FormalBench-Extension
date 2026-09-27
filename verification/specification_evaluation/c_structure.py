"""Narrow, audited C anchors for deleted loops and empty statements."""
import re

from .c_bindings import Function, normalize, pairs, statement_end
from .manifest import InputError


def _parents(function, loop):
    return tuple(i for i in function.loops
                 if i < loop < function.loop_ends.get(i, i))


def _header(function, loop):
    tokens = function.tokens
    if tokens[loop] not in {"for", "while"}:
        raise InputError("Deleted-loop mapping supports only for/while loops")
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


def loop_correspondence(before, after, original_span, target_span):
    """Map retained loops; accept only unambiguous leaf-loop deletions.

    With unchanged loop counts, retain the established ordinal/nesting rule.
    With deletions, every surviving header must match uniquely and in order.
    A removed leaf loop must have a null statement at its former sibling slot.
    """
    original, target = Function(before, original_span), Function(after, target_span)
    if len(original.loops) == len(target.loops):
        mapping = dict(zip(original.loops, target.loops))
    elif len(original.loops) > len(target.loops):
        signatures = {i: _header(original, i) for i in original.loops}
        mapping = {}
        for j in target.loops:
            candidates = [i for i, signature in signatures.items() if signature == _header(target, j)]
            if len(candidates) != 1 or candidates[0] in mapping:
                raise InputError("Cannot uniquely map surviving C loop headers")
            mapping[candidates[0]] = j
        if list(mapping) != sorted(mapping):
            raise InputError("C loop deletion changes surviving loop order")
    else:
        raise InputError("C mutant introduces loops without annotation correspondence")
    for old, new in mapping.items():
        parents = _parents(original, old)
        if (before[old] != after[new] or any(p not in mapping for p in parents)
                or tuple(mapping[p] for p in parents) != _parents(target, new)):
            raise InputError("C annotation transfer requires preserved loop nesting")
    deleted = {}
    for old in original.loops:
        if old in mapping:
            continue
        if any(old in _parents(original, child) for child in original.loops):
            raise InputError("Cannot omit annotations for a deleted non-leaf C loop")
        following = next((i for i in original.loops if i > old
                          and _parents(original, i) == _parents(original, old)), None)
        if following not in mapping:
            raise InputError("Deleted C loop has no preserved following sibling")
        # Only inert local declarations may have moved between these siblings.
        gap = normalize(before[original.loop_ends[old] + 1:following])
        declarations = []
        while gap:
            if (len(gap) < 5 or gap[0] != "int32_t" or gap[2] != "="
                    or not gap[3].isdigit() or gap[4] != ";"):
                raise InputError("Deleted C loop has an ambiguous following statement")
            declarations.append(gap[:5])
            gap = gap[5:]
        next_target = mapping[following]
        null = next_target - 1
        if after[null] != ";" or after[null - 1] not in {";", "{", "}"}:
            raise InputError("Deleted C loop is not replaced by a distinct empty statement")
        for declaration in declarations:
            candidates = [d for d in target.visible(next_target).values()
                          if d.type == declaration[0] and d.name == declaration[1]
                          and normalize(d.initializer) == declaration[3:4]]
            if len(candidates) != 1:
                raise InputError("Moved declaration after a deleted C loop is not preserved")
        if null in deleted.values():
            raise InputError("Several deleted C loops share an ambiguous empty statement")
        deleted[old] = null
    return mapping, deleted


def empty_statement_anchor(before, after, index, mapping):
    """Keep an assertion before a call replaced by a single null statement."""
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
