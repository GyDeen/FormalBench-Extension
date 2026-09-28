"""Language-neutral token indexes for function and control-flow structure."""
from __future__ import annotations

from .manifest import InputError


def pairs(tokens):
    """Map each matched opening delimiter to its closing token index."""
    result, stack = {}, []
    for index, token in enumerate(tokens):
        if token in {"(", "{", "["}:
            stack.append(index)
        elif token in {")", "}", "]"} and stack:
            opening = stack.pop()
            result[opening] = index
    return result


def statement_end(tokens, brackets, start):
    """Find a statement's last token, including nested control bodies."""
    if tokens[start] == "{":
        return brackets[start]
    if tokens[start] in {"for", "while", "if"}:
        return statement_end(tokens, brackets, brackets[start + 1] + 1)
    index = start
    while index < len(tokens):
        if tokens[index] == ";":
            return index
        index = brackets.get(index, index) + 1
    raise InputError("Cannot determine statement scope")


class FunctionStructure:
    """Index a function's source-token span, loop headers, and loop ends."""

    def __init__(self, tokens, span):
        """Build indexes from tokens and a function's name/header/body/end span."""
        self.tokens = tokens
        self.name, self.header, self.body, self.end = span
        self.brackets = pairs(tokens)
        self.loops = [index for index in range(self.body, self.end)
                      if tokens[index] in {"for", "while", "do"}]
        self.loop_ends = {index: statement_end(tokens, self.brackets, index)
                          for index in self.loops if tokens[index] != "do"}
