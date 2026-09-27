"""Conservative rebinding of C declarations referenced by frozen ACSL.

Translations can rename declarations or inline an immutable temporary. This
module handles those representation changes, never edits executable code, and
rejects ambiguous bindings instead of guessing a new specification.
"""
from __future__ import annotations

import re
from dataclasses import dataclass

from .manifest import InputError

IDENT = re.compile(r"[A-Za-z_][A-Za-z_0-9]*\Z")
TYPES = {"int32_t", "uint32_t", "int64_t", "uint64_t", "int", "double", "float",
         "bool", "size_t", "JIntArray", "JBoolArray", "JDoubleArray",
         "JIntArray2", "JDoubleArray2"}


def pairs(tokens):
    result, stack = {}, []
    for i, t in enumerate(tokens):
        if t in {"(", "{", "["}:
            stack.append(i)
        elif t in {")", "}", "]"} and stack:
            opening = stack.pop()
            result[opening] = i
    return result


def statement_end(tokens, brackets, start):
    if tokens[start] == "{":
        return brackets[start]
    if tokens[start] in {"for", "while", "if"}:
        return statement_end(tokens, brackets, brackets[start + 1] + 1)
    i = start
    while i < len(tokens):
        if tokens[i] == ";":
            return i
        i = brackets.get(i, i) + 1
    raise InputError("Cannot determine C statement scope")


def normalize(tokens):
    # INT32_C(1) and 1 have the same int32_t value in these translations.
    result, i = [], 0
    while i < len(tokens):
        if (tokens[i] == "INT32_C" and tokens[i + 1:i + 2] == ["("]
                and i + 3 < len(tokens) and tokens[i + 2].isdigit()
                and tokens[i + 3] == ")"):
            result.append(tokens[i + 2]); i += 4
        else:
            result.append(tokens[i]); i += 1
    return result


@dataclass
class Declaration:
    name: str
    type: str
    index: int
    end: int
    scope: tuple
    initializer: list[str]
    parameter: bool = False


class Function:
    def __init__(self, tokens, span):
        self.tokens = tokens
        self.name, self.header, self.body, self.end = span
        self.brackets = pairs(tokens)
        self.loops = [i for i in range(self.body, self.end)
                      if tokens[i] in {"for", "while", "do"}]
        self.loop_ends = {i: statement_end(tokens, self.brackets, i) for i in self.loops
                          if tokens[i] != "do"}
        self.declarations = []
        # Only ordinary, named scalar/pointer-typedef parameters are supported.
        name_index = next(i for i in range(self.header, self.body)
                          if tokens[i] == self.name and tokens[i + 1] == "(")
        opening = name_index + 1
        for i in range(opening + 1, self.brackets[opening]):
            if tokens[i] in TYPES and IDENT.fullmatch(tokens[i + 1]):
                self.declarations.append(Declaration(tokens[i + 1], tokens[i], i,
                                                     self.end, (), [], True))
        for i in range(self.body + 1, self.end - 2):
            if (tokens[i] not in TYPES or not IDENT.fullmatch(tokens[i + 1])
                    or tokens[i + 2] not in {"=", ";"}):
                continue
            end = i + 2
            while end < self.end and tokens[end] != ";":
                end = self.brackets.get(end, end) + 1
            scope = tuple(n for n, loop in enumerate(self.loops)
                          if loop < i <= self.loop_ends.get(loop, loop))
            # Block bounds prevent using a declaration after it leaves scope.
            blocks = [close for begin, close in self.brackets.items()
                      if tokens[begin] == "{" and begin < i < close]
            scope_end = min(blocks + [self.loop_ends[self.loops[n]] for n in scope])
            init = tokens[i + 3:end] if tokens[i + 2] == "=" else []
            self.declarations.append(Declaration(tokens[i + 1], tokens[i], i,
                                                 scope_end, scope, init))

    def visible(self, boundary):
        # A for-loop annotation can refer to its initializer's variable.
        limit = boundary
        if self.tokens[boundary] == "for":
            limit = self.brackets[boundary + 1]
        visible = {}
        for d in self.declarations:
            if d.parameter or (d.index < limit and boundary <= d.end):
                visible[d.name] = d
        return visible


def replace_names(text, bindings):
    if not bindings:
        return text
    # Do not guess around a binder: capture would change the frozen predicate.
    bound = set()
    for m in re.finditer(r"\\(?:forall|exists)\s+\w+\s+([^;]+);", text):
        bound.update(re.findall(r"[A-Za-z_][A-Za-z_0-9]*", m.group(1)))
    bound.update(re.findall(r"\\let\s+([A-Za-z_]\w*)\s*=", text))
    if re.search(r"\b(?:logic|predicate|inductive)\b", text):
        raise InputError("C rebinding of a logic declaration requires an explicit scope mapping")
    for old, new in bindings.items():
        if old in bound or set(re.findall(r"[A-Za-z_][A-Za-z_0-9]*", new)) & bound:
            raise InputError(f"C annotation rebinding would capture a logic variable: {old}")

    def substitute(match):
        name = match.group()
        prefix = text[:match.start()].rstrip()
        suffix = text[match.end():].lstrip()
        if (prefix.endswith(("\\", "->"))
                or prefix.endswith(".") and not prefix.endswith("..")
                or suffix.startswith("(")):
            return name
        return bindings.get(name, name)
    # Strings are left untouched, and identifiers are replaced simultaneously.
    return re.sub(r'"(?:\\.|[^"\\])*"|[A-Za-z_][A-Za-z_0-9]*', substitute, text)


def _pure_expression(tokens, bindings):
    """Expand only the trusted wrapping helpers and array-length getters."""
    tokens = normalize(tokens)
    brackets = pairs(tokens)
    if len(tokens) == 1:
        value = tokens[0]
        if value.isdigit() or IDENT.fullmatch(value):
            return bindings.get(value, value)
    if len(tokens) >= 4 and tokens[1] == "(" and brackets.get(1) == len(tokens) - 1:
        name, args, start, i = tokens[0], [], 2, 2
        while i < len(tokens) - 1:
            if tokens[i] == ",":
                args.append(tokens[start:i]); start = i + 1
            i = brackets.get(i, i) + 1
        args.append(tokens[start:-1])
        if name in {"java_add", "java_sub", "java_mul"} and len(args) == 2:
            left, right = (_pure_expression(arg, bindings) for arg in args)
            op = {"java_add": "+", "java_sub": "-", "java_mul": "*"}[name]
            return f"((int32_t)((integer)({left}) {op} ({right})))"
        if name in {"jarray_length", "jbool_array_length", "jdouble_array_length"} and len(args) == 1:
            return f"({_pure_expression(args[0], bindings)}->length)"
    raise InputError("Removed C temporary does not have a supported pure initializer")


def rebind_annotation(before, after, original_span, target_span, record, target_index,
                      loop_mapping=None):
    original, target = Function(before, original_span), Function(after, target_span)
    if record["target"] == "loop" and loop_mapping is None:
        def loop_shape(function):
            return [(function.tokens[i], tuple(n for n, parent in enumerate(function.loops)
                     if parent < i < function.loop_ends.get(parent, parent)))
                    for i in function.loops]
        if loop_shape(original) != loop_shape(target):
            raise InputError("C annotation transfer requires preserved loop nesting")
    if loop_mapping is None:
        loop_mapping = dict(zip(original.loops, target.loops))
    old_visible = original.visible(record["boundary_token"])
    new_visible = target.visible(target_index)
    old_params = [d for d in original.declarations if d.parameter]
    new_params = [d for d in target.declarations if d.parameter]
    if [d.type for d in old_params] != [d.type for d in new_params]:
        raise InputError("C function parameter types changed during annotation transfer")
    bindings = {a.name: b.name for a, b in zip(old_params, new_params) if a.name != b.name}
    text = record["text"]
    referenced = set(re.findall(r"(?<![\\.>])\b[A-Za-z_][A-Za-z_0-9]*\b", text))
    for name, decl in old_visible.items():
        if decl.parameter or name not in referenced:
            continue
        mapped_scope = tuple(target.loops.index(loop_mapping[original.loops[n]])
                             for n in decl.scope if original.loops[n] in loop_mapping)
        if len(mapped_scope) != len(decl.scope):
            raise InputError(f"C annotation references a declaration in a removed loop: {name}")
        candidates = [d for d in new_visible.values()
                      if not d.parameter and d.type == decl.type and d.scope == mapped_scope]
        same = [d for d in candidates if d.name == name]
        if same:
            continue
        # Match a loop index by its own for declaration, even when the mutant
        # changes its initial value (which is precisely what the FS evaluates).
        own_loop = next((i for i in original.loops
                         if before[i] == "for" and i < decl.index < original.brackets[i + 1]), None)
        if own_loop is not None:
            loop = loop_mapping.get(own_loop, -1)
            candidates = [d for d in candidates
                          if after[loop] == "for" and loop < d.index < target.brackets[loop + 1]]
        else:
            candidates = [d for d in candidates
                          if normalize(d.initializer) == normalize(decl.initializer)]
        if len(candidates) == 1:
            bindings[name] = candidates[0].name
            continue
        if candidates:
            raise InputError(f"Ambiguous C declaration rename for {name}")
        # Expand the ORIGINAL initializer, even when the mutant changed its
        # loop guard. Taking the mutant's bound would change the frozen FS.
        # Adjacency, purity and stable dependencies make this an equivalent
        # expression for the original local snapshot at this loop boundary.
        if record["target"] != "loop" or target_index not in target.loop_ends:
            raise InputError(f"C annotation references a removed declaration: {name}")
        declaration_end = decl.index + 2
        while before[declaration_end] != ";":
            declaration_end = original.brackets.get(declaration_end, declaration_end) + 1
        if decl.type != "int32_t" or declaration_end + 1 != record["boundary_token"]:
            raise InputError(f"Removed C temporary is not an adjacent int32_t loop bound: {name}")
        initializer = normalize([bindings.get(t, t) for t in decl.initializer])
        region = after[target_index:target.loop_ends[target_index] + 1]
        for dependency in set(decl.initializer) & set(old_visible):
            mapped = bindings.get(dependency, dependency)
            if mapped not in new_visible:
                raise InputError(f"Original initializer of {name} has an unmapped dependency: {dependency}")
        expression = _pure_expression(decl.initializer, bindings)
        dependencies = set(initializer) & set(new_visible)
        for dependency in dependencies:
            # A pointer captured before the loop can still write a dependency
            # inside it. Scalar snapshot expansion cannot allow that escape.
            if any(after[i] == "&" and after[i + 1] == dependency
                   for i in range(target.body, target.end - 1)):
                raise InputError(f"Inlined C temporary {name} has an escaped dependency: {dependency}")
            for i, token in enumerate(region):
                if token != dependency:
                    continue
                tail = region[i + 1:i + 4]
                if (tail[:1] == ["="] and tail[:2] != ["=", "="]
                        or tail[:2] in [["+", "+"], ["-", "-"], ["+", "="], ["-", "="], ["*", "="], ["/", "="],
                                       ["%", "="], ["&", "="], ["|", "="], ["^", "="]]
                        or tail[:3] in [["<", "<", "="], [">", ">", "="]]
                        or region[max(0, i - 2):i] in [["+", "+"], ["-", "-"]]
                        or "&" in region[max(0, i - 1):i]):
                    raise InputError(f"Inlined C temporary {name} has a modified dependency: {dependency}")
            if new_visible[dependency].type.startswith("J"):
                # Check uses up to loop exit, including earlier aliases. Skip
                # only the dependency's own declaration occurrence.
                declaration = new_visible[dependency]
                for i in range(target.body + 1, target.loop_ends[target_index] + 1):
                    if after[i] != dependency or i == declaration.index + 1:
                        continue
                    # Array metadata is stable only through the fixed length,
                    # get, and set interfaces. Reject aliases, direct field
                    # accesses, frees, and escapes to any other call.
                    allowed = {"jarray_length", "jarray_get", "jarray_set",
                               "jbool_array_length", "jbool_array_get", "jbool_array_set",
                               "jdouble_array_length", "jdouble_array_get", "jdouble_array_set"}
                    if i < 2 or after[i - 1] != "(" or after[i - 2] not in allowed:
                        raise InputError(f"Array dependency of {name} may escape or change: {dependency}")
        bindings[name] = expression
    bindings = {name: value for name, value in bindings.items() if name in referenced and name != value}
    return replace_names(text, bindings), bindings
