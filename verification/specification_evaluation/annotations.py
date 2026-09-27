"""Transfer JML/ACSL comments while preserving every executable source token."""

from __future__ import annotations

import difflib
import hashlib
import re
from dataclasses import dataclass
from typing import Any

from .manifest import InputError

LEX = re.compile(
    r'"(?:\\.|[^"\\])*"|\'(?:\\.|[^\'\\])*\'|//[^\n]*|/\*[\s\S]*?\*/|'
    r'\s+|[A-Za-z_$][A-Za-z_0-9$]*|0[xX][0-9A-Fa-f]+|\d+(?:\.\d+)?|.',
    re.DOTALL,
)
LOOP_CLAUSE = re.compile(r"\b(?:loop[_\s]+(?:invariant|variant|assigns|decreases)|maintaining)\b", re.I)
DECREASES_CLAUSE = re.compile(r"\bdecreases\b", re.I)


@dataclass(frozen=True)
class Token:
    text: str
    start: int


@dataclass(frozen=True)
class Annotation:
    text: str
    boundary: int
    is_loop: bool


@dataclass(frozen=True)
class Transfer:
    source: str
    annotations: tuple[dict, ...]
    executable_token_sha256: str
    omitted_annotations: tuple[dict, ...] = ()


def scan(source: str) -> tuple[list[Token], list[Annotation]]:
    """Separate executable tokens from JML/ACSL comments and record anchors."""
    tokens: list[Token] = []
    annotations: list[Annotation] = []
    for match in LEX.finditer(source):
        value = match.group()
        if value.isspace():
            continue
        if value.startswith(("//", "/*")):
            if value.startswith(("//@", "/*@")):
                prefix = source[source.rfind("\n", 0, match.start()) + 1:match.start()]
                if prefix.strip():
                    raise InputError("Annotation must begin on its own line for safe transfer")
                annotations.append(Annotation(value, len(tokens), bool(LOOP_CLAUSE.search(value))))
            continue
        tokens.append(Token(value, match.start()))
    return tokens, annotations


def token_digest(tokens: list[Token]) -> str:
    payload = "\0".join(token.text for token in tokens).encode("utf-8")
    return hashlib.sha256(payload).hexdigest()


def validate_original(raw: str, annotated: str) -> tuple[list[Token], list[Annotation]]:
    """Require an annotated original to differ only by specification comments."""
    raw_tokens, raw_annotations = scan(raw)
    annotated_tokens, annotations = scan(annotated)
    if raw_annotations:
        raise InputError("The unannotated original already contains JML/ACSL comments")
    if not annotations:
        raise InputError("Annotated original contains no JML/ACSL comments")
    if [token.text for token in raw_tokens] != [token.text for token in annotated_tokens]:
        raise InputError("Annotated original changes executable source tokens")
    return raw_tokens, annotations


def _function_spans(tokens: list[Token], source: str) -> list[tuple[str, int, int, int]]:
    """Find ordinary function bodies for auditable annotation ownership.

    Token alignment remains the placement authority; this lightweight scan
    records and checks function names without claiming to parse all Java/C.
    """
    parentheses: dict[int, int] = {}
    stack: list[int] = []
    for index, token in enumerate(tokens):
        if token.text == "(":
            stack.append(index)
        elif token.text == ")" and stack:
            parentheses[index] = stack.pop()
    braces: dict[int, int] = {}
    stack = []
    for index, token in enumerate(tokens):
        if token.text == "{":
            stack.append(index)
        elif token.text == "}" and stack:
            braces[stack.pop()] = index
    excluded = {"if", "for", "while", "switch", "catch", "synchronized", "new"}
    spans: list[tuple[str, int, int, int]] = []
    for opening, closing in braces.items():
        cursor = opening - 1
        while cursor >= 0 and tokens[cursor].text not in {";", "{", "}"}:
            if tokens[cursor].text == ")" and cursor in parentheses:
                name_index = parentheses[cursor] - 1
                if name_index >= 0:
                    name = tokens[name_index].text
                    if (re.fullmatch(r"[A-Za-z_$][A-Za-z_0-9$]*", name)
                            and name not in excluded
                            and (name_index == 0 or tokens[name_index - 1].text != "new")):
                        header = name_index
                        while header > 0:
                            previous = header - 1
                            if tokens[previous].text in {";", "{", "}"}:
                                break
                            if tokens[previous].text == "#":
                                # An include/define is line-based, not a C
                                # declaration delimiter. Start after its line.
                                line_end = source.find("\n", tokens[previous].start)
                                header = next((index for index in range(previous + 1, name_index + 1)
                                               if tokens[index].start > line_end), name_index)
                                break
                            header -= 1
                        spans.append((name, header, opening, closing))
                break
            cursor -= 1
    return spans


def _owner(spans: list[tuple[str, int, int, int]], boundary: int) -> str | None:
    inside = [span for span in spans if span[2] < boundary < span[3]]
    if inside:
        return max(inside, key=lambda span: span[2])[0]
    following = [span for span in spans if span[1] == boundary]
    return following[0][0] if following else None


def extract_specification(raw_original: str, annotated_original: str,
                          program: str, language: str) -> dict[str, Any]:
    """Extract comments once into a reusable, source-anchored JSON object."""
    tokens, annotations = validate_original(raw_original, annotated_original)
    spans = _function_spans(tokens, raw_original)
    loops = [(index, token.text) for index, token in enumerate(tokens)
             if token.text in {"for", "while", "do"}]
    records: list[dict[str, Any]] = []
    for number, annotation in enumerate(annotations, 1):
        boundary = annotation.boundary
        if boundary >= len(tokens):
            raise InputError("Trailing annotations have no safe statement anchor")
        function = _owner(spans, boundary)
        # A bare ``decreases`` clause can specify a recursive function's
        # termination measure as well as a loop's. Treat it as a loop clause
        # only when the annotation is anchored immediately before a loop.
        is_loop_annotation = annotation.is_loop or (
            bool(DECREASES_CLAUSE.search(annotation.text))
            and tokens[boundary].text in {"for", "while", "do"}
        )
        target = "loop" if is_loop_annotation else "function" if any(
            span[0] == function and span[1] == boundary for span in spans
        ) else "statement"
        loop_id = None
        if target == "loop":
            matching = [position for position, _ in loops if position == boundary]
            if not matching:
                raise InputError("Loop annotation is not directly before a loop")
            same_function = [position for position, _ in loops
                             if _owner(spans, position) == function]
            loop_id = f"loop_{same_function.index(boundary) + 1}"
        records.append({"id": f"annotation_{number}", "target": target,
                        "function": function, "loop_id": loop_id,
                        "boundary_token": boundary, "next_token": tokens[boundary].text,
                        "following_tokens": [token.text for token in tokens[boundary:boundary + 2]],
                        "text": annotation.text})
    return {"schema_version": "1.0", "program": program, "language": language,
            "original_executable_token_sha256": token_digest(tokens),
            "annotations": records}


def apply_specification(raw_original: str, specification: dict[str, Any],
                        raw_target: str) -> Transfer:
    """Attach frozen JSON comments to an original or mutant source.

    Matching uses preserved tokens and loop structure, not source line numbers.
    Ambiguous anchors fail instead of silently moving a specification.
    """
    if not isinstance(specification, dict):
        raise InputError("Structured specification must be a JSON object")
    original_tokens, original_annotations = scan(raw_original)
    if original_annotations:
        raise InputError("The original used for JSON attachment already has annotations")
    if specification.get("schema_version") != "1.0" or not isinstance(specification.get("annotations"), list):
        raise InputError("Unsupported structured specification format")
    if specification.get("original_executable_token_sha256") != token_digest(original_tokens):
        raise InputError("Structured specification does not match the original executable tokens")
    records = specification["annotations"]
    if not records:
        raise InputError("Structured specification contains no annotations")
    mutant_tokens, mutant_annotations = scan(raw_target)
    if mutant_annotations:
        raise InputError("Target source already contains JML/ACSL comments")
    before = [token.text for token in original_tokens]
    after = [token.text for token in mutant_tokens]
    # Align unchanged token spans. A mutated expression may move offsets, but
    # nearby preserved statements remain usable annotation anchors.
    alignment = difflib.SequenceMatcher(None, before, after, autojunk=False)
    mapping = {
        original + offset: mutant + offset
        for original, mutant, length in alignment.get_matching_blocks()
        for offset in range(length)
    }
    original_loops = [(index, token.text) for index, token in enumerate(original_tokens)
                      if token.text in {"for", "while", "do"}]
    mutant_loops = [(index, token.text) for index, token in enumerate(mutant_tokens)
                    if token.text in {"for", "while", "do"}]
    # Loop annotations need their loop, not just a nearby matching token; a
    # changed loop structure is therefore rejected rather than guessed at.
    original_spans = _function_spans(original_tokens, raw_original)
    target_spans = _function_spans(mutant_tokens, raw_target)
    placements: list[tuple[int, dict[str, Any], str, bool]] = []
    omitted: list[dict[str, Any]] = []
    c_loop_maps = {}
    seen_ids: set[str] = set()
    for record in records:
        if not isinstance(record, dict) or record.get("target") not in {"function", "loop", "statement"}:
            raise InputError("Malformed structured annotation")
        annotation_id = record.get("id")
        if not isinstance(annotation_id, str) or annotation_id in seen_ids:
            raise InputError("Annotation IDs must be unique strings")
        seen_ids.add(annotation_id)
        index = record.get("boundary_token")
        if not isinstance(index, int) or isinstance(index, bool) or index < 0:
            raise InputError("Invalid annotation token boundary")
        if index >= len(original_tokens):
            raise InputError("Trailing annotations have no safe statement anchor")
        if (not isinstance(record.get("text"), str)
                or not record["text"].startswith(("//@", "/*@"))
                or record.get("following_tokens") != before[index:index + 2]
                or record.get("next_token") != before[index]
                or record.get("function") != _owner(original_spans, index)):
            raise InputError("Structured annotation does not match its original anchor")
        if record["target"] == "loop":
            ordinal = next((n for n, (position, _) in enumerate(original_loops) if position == index), None)
            if ordinal is None:
                raise InputError("Loop annotation is not directly before a preserved loop")
            same_function = [position for position, _ in original_loops
                             if _owner(original_spans, position) == record["function"]]
            if record.get("loop_id") != f"loop_{same_function.index(index) + 1}":
                raise InputError("Structured loop ID does not match the original")
            if specification.get("language") == "c":
                from .c_structure import loop_correspondence
                owner = record["function"]
                if owner not in c_loop_maps:
                    old = [s for s in original_spans if s[0] == owner]
                    new = [s for s in target_spans if s[0] == owner]
                    if len(old) != 1 or len(new) != 1:
                        raise InputError("C loop annotation has no unique function")
                    c_loop_maps[owner] = loop_correspondence(before, after, old[0], new[0])
                loop_mapping, deleted = c_loop_maps[owner]
                if index in deleted:
                    omitted.append({"id": annotation_id, "function": owner,
                                    "target": "loop", "loop_id": record["loop_id"],
                                    "reason": "loop deleted and replaced by empty statement",
                                    "mutant_offset": mutant_tokens[deleted[index]].start})
                    continue
                target_index = loop_mapping[index]
            else:
                if (len(original_loops) != len(mutant_loops)
                        or [kind for _, kind in original_loops] != [kind for _, kind in mutant_loops]):
                    raise InputError("Mutant changes the loop structure needed by an annotation")
                target_index = mutant_loops[ordinal][0]
        else:
            if record.get("loop_id") is not None:
                raise InputError("Non-loop annotation has a loop ID")
            if record["target"] == "function" and not any(
                span[0] == record["function"] and span[1] == index for span in original_spans
            ):
                raise InputError("Function annotation does not precede its declared function")
            # C translations may insert/remove arithmetic helpers. A whole-file
            # token diff can then align one helper's header with another one.
            # The unique named declaration is the function contract's anchor.
            named_target = [span for span in target_spans
                            if span[0] == record["function"]]
            if specification.get("language") == "c" and record["target"] == "function":
                if (not named_target
                        and record["function"] in {"java_add", "java_sub", "java_mul", "java_div", "java_mod", "java_neg"}
                        and record["function"] not in after
                        and not re.search(r"\b(?:logic|predicate|axiomatic|lemma|inductive|ghost)\b", record["text"])):
                    # A translation emits arithmetic helpers only when used.
                    # A removed, unreferenced helper has no declaration or
                    # call to specify; never attach its contract elsewhere.
                    omitted.append({"id": annotation_id, "function": record["function"],
                                    "reason": "unused arithmetic helper absent from target"})
                    continue
                if len(named_target) != 1:
                    raise InputError("C function annotation has no unique named declaration")
                target_index = named_target[0][1]
            else:
                target_index = mapping.get(index)
            reanchored_empty = False
            if (specification.get("language") in {"c", "java"} and record["target"] == "statement"
                    and re.fullmatch(r"(?:/\*@\s*assert\b[\s\S]*\*/|//@\s*assert\b[^\n]*)", record["text"])):
                from .c_structure import empty_statement_anchor
                empty_index = empty_statement_anchor(before, after, index, mapping)
                if empty_index is not None:
                    target_index, reanchored_empty = empty_index, True
                    record = {**record, "anchor_change": "call replaced by empty statement; assertion retained"}
            if target_index is None:
                raise InputError("Annotation's following statement cannot be aligned to mutant")
            context = before[index:index + 2]
            if (after[target_index:target_index + len(context)] != context
                    and not reanchored_empty
                    and not (specification.get("language") == "c" and record["target"] == "function")):
                raise InputError("Annotation statement context changed in mutant")
        if record["function"] is not None and _owner(target_spans, target_index) != record["function"]:
            raise InputError("Annotation moved to a different function")
        if record["target"] == "function" and not any(
            span[0] == record["function"] and span[1] == target_index for span in target_spans
        ):
            raise InputError("Function annotation moved away from its declaration")
        if specification.get("language") == "c" and before != after and record["function"]:
            from .c_bindings import rebind_annotation
            old = [span for span in original_spans if span[0] == record["function"]]
            new = [span for span in target_spans if span[0] == record["function"]]
            if len(old) != 1 or len(new) != 1:
                raise InputError("C rebinding requires unique function definitions")
            loop_mapping = c_loop_maps.get(record["function"], (None, None))[0]
            text, bindings = rebind_annotation(before, after, old[0], new[0], record, target_index,
                                               loop_mapping)
            record = {**record, "text": text, "c_bindings": bindings}
        if specification.get("language") == "java":
            from .java_compat import normalize_annotation
            text, repairs = normalize_annotation(record["text"])
            record = {**record, "text": text, "java_compatibility": repairs}
        position = mutant_tokens[target_index].start
        indentation = raw_target[raw_target.rfind("\n", 0, position) + 1:position]
        # A compact source may put a declaration after a class-opening brace
        # on the same line. Insert a newline before the comment in that case;
        # this changes whitespace, never executable tokens.
        midline = bool(indentation.strip())
        placements.append((position, record, "" if midline else indentation, midline))

    grouped: dict[int, list[tuple[dict[str, Any], str, bool]]] = {}
    for position, record, indentation, midline in placements:
        grouped.setdefault(position, []).append((record, indentation, midline))
    result = raw_target
    # Insert from the end so earlier character offsets remain valid.
    for position in sorted(grouped, reverse=True):
        insertion = ("\n" if grouped[position][0][2] else "") + "".join(
            f"{record['text']}\n{indentation}"
            for record, indentation, _ in grouped[position]
        )
        result = result[:position] + insertion + result[position:]
    result_tokens, result_annotations = scan(result)
    # This final check guards the study's "same executable mutant" rule.
    if ([token.text for token in result_tokens] != after
            or len(result_annotations) + len(omitted) != len(records)):
        raise InputError("Transferred annotations changed executable mutant tokens")
    placement_records = tuple({"id": record["id"], "target": record["target"],
                     "function": record["function"], "loop_id": record["loop_id"],
                     "boundary_token": record["boundary_token"],
                     "mutant_offset": position,
                     "next_token": record["next_token"],
                     **({"anchor_change": record["anchor_change"]} if record.get("anchor_change") else {}),
                     **({"java_compatibility": record["java_compatibility"]} if record.get("java_compatibility") else {}),
                     **({"c_bindings": record["c_bindings"]} if record.get("c_bindings") else {})}
                    for position, record, _, _ in placements)
    return Transfer(result, placement_records, token_digest(mutant_tokens), tuple(omitted))


def transfer(raw_original: str, annotated_original: str, raw_mutant: str) -> Transfer:
    """Compatibility wrapper for callers still supplying annotated source."""
    specification = extract_specification(raw_original, annotated_original, "unknown", "unknown")
    return apply_specification(raw_original, specification, raw_mutant)
