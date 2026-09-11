"""Translate the small Java benchmark/mutant subset into C.

The generated files are deliberately function-only translations.  They are
compiled by the differential runner, which supplies the call harness and the
``java_arrays`` runtime.  This translator is intentionally limited to the
constructs used by the FormalBench benchmark sources; it is not a general
Java compiler.
"""

from __future__ import annotations

import argparse
import re
from dataclasses import dataclass, field
from pathlib import Path
from typing import Any


@dataclass(frozen=True)
class Type:
    base: str
    dims: int = 0

    @property
    def is_array(self) -> bool:
        return self.dims > 0


INT = Type("int")
DOUBLE = Type("double")
BOOL = Type("boolean")
VOID = Type("void")
UNKNOWN = Type("unknown")


@dataclass(frozen=True)
class Token:
    value: str


@dataclass
class Expr:
    kind: str
    value: Any = None
    left: Expr | None = None
    right: Expr | None = None
    extra: Any = None


@dataclass
class Statement:
    kind: str
    value: Any = None
    body: list[Statement] | None = None
    otherwise: list[Statement] | None = None
    extra: Any = None


@dataclass
class Method:
    name: str
    return_type: Type
    parameters: list[tuple[Type, str]]
    body: list[Statement]


_TOKEN_RE = re.compile(
    r"//[^\n]*|/\*.*?\*/|"
    r"(?:0[xX][0-9a-fA-F]+|(?:\d+\.\d*|\.\d+)(?:[eE][+-]?\d+)?[fFdD]?|\d+[lL]?)|"
    r"[A-Za-z_$][A-Za-z0-9_$]*|"
    r">>>=?|>>=?|<<=?|==|!=|<=|>=|&&|\|\||\+\+|--|\+=|-=|\*=|/=|%=|&=|\|=|\^=|"
    r"[^\s]",
    re.S,
)


def tokenize(source: str) -> list[Token]:
    values: list[Token] = []
    for match in _TOKEN_RE.finditer(source):
        value = match.group(0)
        if value.startswith("//") or value.startswith("/*"):
            continue
        values.append(Token(value))
    return values


class ParseError(ValueError):
    pass


class Parser:
    _PRECEDENCE = {
        "||": 1,
        "&&": 2,
        "|": 3,
        "^": 4,
        "&": 5,
        "==": 6,
        "!=": 6,
        "<": 7,
        "<=": 7,
        ">": 7,
        ">=": 7,
        "<<": 8,
        ">>": 8,
        ">>>": 8,
        "+": 9,
        "-": 9,
        "*": 10,
        "/": 10,
        "%": 10,
    }

    def __init__(self, source: str):
        self.tokens = tokenize(source)
        self.pos = 0

    def peek(self, offset: int = 0) -> str | None:
        index = self.pos + offset
        return self.tokens[index].value if index < len(self.tokens) else None

    def take(self, expected: str | None = None) -> str:
        value = self.peek()
        if value is None:
            raise ParseError(f"Unexpected end of Java source; expected {expected!r}")
        if expected is not None and value != expected:
            raise ParseError(f"Expected {expected!r}, got {value!r}")
        self.pos += 1
        return value

    def match(self, value: str) -> bool:
        if self.peek() == value:
            self.pos += 1
            return True
        return False

    def parse(self) -> Method:
        while self.peek() is not None and self.peek() != "class":
            self.pos += 1
        if self.peek() is None:
            raise ParseError("No class declaration")
        self.take("class")
        self.take()  # class name
        while self.peek() is not None and self.peek() != "public":
            self.pos += 1
        if self.peek() is None:
            raise ParseError("No public static method")
        self.take("public")
        self.match("static")
        return_type = self.parse_type()
        name = self.take()
        self.take("(")
        parameters: list[tuple[Type, str]] = []
        if self.peek() != ")":
            while True:
                typ = self.parse_type()
                parameter = self.take()
                typ = self.trailing_array_type(typ)
                parameters.append((typ, parameter))
                if not self.match(","):
                    break
        self.take(")")
        body = self.parse_block()
        return Method(name, return_type, parameters, body)

    def parse_type(self) -> Type:
        base = self.take()
        if base not in {"int", "double", "boolean", "void"}:
            raise ParseError(f"Expected primitive type, got {base!r}")
        dimensions = 0
        while self.peek() == "[" and self.peek(1) == "]":
            self.take("[")
            self.take("]")
            dimensions += 1
        return Type(base, dimensions)

    def trailing_array_type(self, typ: Type) -> Type:
        dimensions = typ.dims
        while self.peek() == "[" and self.peek(1) == "]":
            self.take("[")
            self.take("]")
            dimensions += 1
        return Type(typ.base, dimensions)

    def parse_block(self) -> list[Statement]:
        self.take("{")
        result: list[Statement] = []
        while self.peek() not in {"}", None}:
            result.append(self.parse_statement())
        self.take("}")
        return result

    def parse_statement(self) -> Statement:
        token = self.peek()
        if token == ";":
            self.take()
            return Statement("empty")
        if token == "{":
            return Statement("block", body=self.parse_block())
        if token == "if":
            self.take()
            self.take("(")
            condition = self.parse_expression()
            self.take(")")
            body = self.statement_as_block()
            otherwise: list[Statement] | None = None
            if self.match("else"):
                otherwise = self.statement_as_block()
            return Statement("if", value=condition, body=body, otherwise=otherwise)
        if token == "while":
            self.take()
            self.take("(")
            condition = self.parse_expression()
            self.take(")")
            return Statement("while", value=condition, body=self.statement_as_block())
        if token == "for":
            return self.parse_for()
        if token == "return":
            self.take()
            value = None if self.peek() == ";" else self.parse_expression()
            self.take(";")
            return Statement("return", value=value)
        if token == "break":
            self.take()
            self.take(";")
            return Statement("break")
        if token == "continue":
            self.take()
            self.take(";")
            return Statement("continue")
        if self.is_declaration_start():
            return self.parse_declaration(with_semicolon=True)
        expression = self.parse_expression()
        if self.peek() in {"=", "+=", "-=", "*=", "/=", "%=", "&=", "|=", "^="}:
            operator = self.take()
            right = self.parse_expression()
            self.take(";")
            return Statement("assign", value=expression, extra=(operator, right))
        self.take(";")
        return Statement("expr", value=expression)

    def statement_as_block(self) -> list[Statement]:
        if self.peek() == "{":
            return self.parse_block()
        return [self.parse_statement()]

    def parse_for(self) -> Statement:
        self.take("for")
        self.take("(")
        # Enhanced-for syntax has a colon after its loop variable.
        if self.is_declaration_start() and self.find_header_colon():
            typ = self.parse_type()
            name = self.take()
            typ = self.trailing_array_type(typ)
            self.take(":")
            iterable = self.parse_expression()
            self.take(")")
            return Statement("foreach", value=(typ, name, iterable), body=self.statement_as_block())

        init: Statement | None = None
        if self.peek() != ";":
            if self.is_declaration_start():
                init = self.parse_declaration(with_semicolon=False)
            else:
                init_expr = self.parse_expression()
                if self.peek() in {"=", "+=", "-=", "*=", "/=", "%=", "&=", "|=", "^="}:
                    op = self.take()
                    right = self.parse_expression()
                    init = Statement("assign", value=init_expr, extra=(op, right))
                else:
                    init = Statement("expr", value=init_expr)
        self.take(";")
        condition = None if self.peek() == ";" else self.parse_expression()
        self.take(";")
        update: Statement | None = None
        if self.peek() != ")":
            update_expr = self.parse_expression()
            if self.peek() in {"=", "+=", "-=", "*=", "/=", "%=", "&=", "|=", "^="}:
                op = self.take()
                right = self.parse_expression()
                update = Statement("assign", value=update_expr, extra=(op, right))
            else:
                update = Statement("expr", value=update_expr)
        self.take(")")
        return Statement("for", value=(init, condition, update), body=self.statement_as_block())

    def find_header_colon(self) -> bool:
        depth = 0
        index = self.pos
        while index < len(self.tokens):
            value = self.tokens[index].value
            if value in "([{":
                depth += 1
            elif value in ")]}" and depth:
                depth -= 1
            elif value == ":" and depth == 0:
                return True
            elif value == ")" and depth == 0:
                return False
            index += 1
        return False

    def parse_declaration(self, *, with_semicolon: bool) -> Statement:
        typ = self.parse_type()
        declarations: list[tuple[Type, str, Expr | None]] = []
        while True:
            name = self.take()
            item_type = self.trailing_array_type(typ)
            initializer = None
            if self.match("="):
                initializer = self.parse_expression()
            declarations.append((item_type, name, initializer))
            if not self.match(","):
                break
        if with_semicolon:
            self.take(";")
        return Statement("declaration", value=declarations)

    def is_declaration_start(self) -> bool:
        if self.peek() not in {"int", "double", "boolean"}:
            return False
        index = self.pos + 1
        while index + 1 < len(self.tokens) and self.tokens[index].value == "[" and self.tokens[index + 1].value == "]":
            index += 2
        return index < len(self.tokens) and re.fullmatch(r"[A-Za-z_$][A-Za-z0-9_$]*", self.tokens[index].value or "") is not None

    def parse_expression(self, minimum_precedence: int = 0) -> Expr:
        left = self.parse_prefix()
        while True:
            operator = self.peek()
            if operator == "?" and minimum_precedence == 0:
                self.take("?")
                yes = self.parse_expression()
                self.take(":")
                no = self.parse_expression()
                left = Expr("ternary", left=left, right=yes, extra=no)
                continue
            precedence = self._PRECEDENCE.get(operator or "", -1)
            if precedence < minimum_precedence:
                break
            self.take()
            right = self.parse_expression(precedence + 1)
            left = Expr("binary", value=operator, left=left, right=right)
        return left

    def parse_prefix(self) -> Expr:
        token = self.peek()
        if token in {"-", "+", "!", "~"}:
            self.take()
            return Expr("unary", value=token, left=self.parse_prefix())
        if token in {"++", "--"}:
            self.take()
            return Expr("prefix", value=token, left=self.parse_prefix())
        if token == "(" and self.looks_like_cast():
            self.take("(")
            typ = self.parse_type()
            self.take(")")
            return self.parse_postfix(Expr("cast", value=typ, left=self.parse_prefix()))
        if token == "(":
            self.take("(")
            result = self.parse_expression()
            self.take(")")
            return self.parse_postfix(result)
        if token == "new":
            return self.parse_postfix(self.parse_new())
        if token is None:
            raise ParseError("Unexpected end of expression")
        self.take()
        if token in {"true", "false"}:
            result = Expr("literal", value=token)
        elif token == "null":
            result = Expr("null")
        elif re.fullmatch(r"(?:0[xX][0-9a-fA-F]+|\d+[lL]?)", token):
            result = Expr("int_literal", value=token)
        elif re.fullmatch(r"(?:\d+\.\d*|\.\d+)(?:[eE][+-]?\d+)?[fFdD]?", token):
            result = Expr("double_literal", value=token.rstrip("fFdD"))
        elif token.startswith('"'):
            result = Expr("string", value=token)
        else:
            result = Expr("name", value=token)
        return self.parse_postfix(result)

    def parse_postfix(self, result: Expr) -> Expr:
        while True:
            if self.match("["):
                index = self.parse_expression()
                self.take("]")
                result = Expr("index", left=result, right=index)
            elif self.match("."):
                result = Expr("member", value=self.take(), left=result)
            elif self.match("("):
                arguments: list[Expr] = []
                if self.peek() != ")":
                    while True:
                        arguments.append(self.parse_expression())
                        if not self.match(","):
                            break
                self.take(")")
                result = Expr("call", left=result, extra=arguments)
            elif self.peek() in {"++", "--"}:
                result = Expr("postfix", value=self.take(), left=result)
            else:
                return result

    def looks_like_cast(self) -> bool:
        if self.peek() != "(" or self.peek(1) not in {"int", "double", "boolean"}:
            return False
        index = self.pos + 2
        while index + 1 < len(self.tokens) and self.tokens[index].value == "[" and self.tokens[index + 1].value == "]":
            index += 2
        return index < len(self.tokens) and self.tokens[index].value == ")"

    def parse_new(self) -> Expr:
        self.take("new")
        base = self.take()
        if base not in {"int", "double", "boolean"}:
            raise ParseError(f"Unsupported new type {base!r}")
        dimensions: list[Expr | None] = []
        while self.match("["):
            dimensions.append(None if self.peek() == "]" else self.parse_expression())
            self.take("]")
        return Expr("new", value=Type(base, len(dimensions)), extra=dimensions)


def add_dimension(typ: Type, count: int) -> Type:
    return Type(typ.base, typ.dims + count)


def c_type(typ: Type) -> str:
    if typ.dims == 0:
        return {"int": "int32_t", "double": "double", "boolean": "bool", "void": "void"}.get(typ.base, "int32_t")
    if typ.base == "int" and typ.dims == 1:
        return "JIntArray"
    if typ.base == "int" and typ.dims == 2:
        return "JIntArray2"
    if typ.base == "double" and typ.dims == 1:
        return "JDoubleArray"
    if typ.base == "double" and typ.dims == 2:
        return "JDoubleArray2"
    if typ.base == "boolean" and typ.dims == 1:
        return "JBoolArray"
    raise ParseError(f"Unsupported Java array type: {typ}")


def is_numeric(typ: Type) -> bool:
    return typ.base in {"int", "double"} and typ.dims == 0


def array_prefix(typ: Type) -> str:
    if typ.base == "int" and typ.dims == 1:
        return "jarray"
    if typ.base == "int" and typ.dims == 2:
        return "jarray2"
    if typ.base == "double" and typ.dims == 1:
        return "jdouble_array"
    if typ.base == "double" and typ.dims == 2:
        return "jdouble_array2"
    if typ.base == "boolean" and typ.dims == 1:
        return "jbool_array"
    raise ParseError(f"Unsupported array type: {typ}")


class Emitter:
    def __init__(self, method: Method):
        self.method = method
        self.environment = {name: typ for typ, name in method.parameters}
        # C has one ordinary identifier namespace for variables and functions,
        # while Java permits a local variable to have the same name as its
        # enclosing method (for example ``int minCoins = ...`` followed by a
        # recursive ``minCoins(...)`` call).
        self.name_map: dict[str, str] = {}
        self.operations: set[str] = set()
        self.headers: set[str] = {"<stdint.h>"}
        self.temp_number = 0

    def fresh(self, prefix: str = "tmp") -> str:
        self.temp_number += 1
        return f"__{prefix}_{self.temp_number}"

    def type_of(self, expression: Expr | None) -> Type:
        if expression is None:
            return UNKNOWN
        kind = expression.kind
        if kind == "name":
            if expression.value in self.environment:
                return self.environment[expression.value]
            return UNKNOWN
        if kind == "int_literal":
            return INT
        if kind in {"double_literal"}:
            return DOUBLE
        if kind in {"literal"}:
            return BOOL
        if kind == "null":
            return UNKNOWN
        if kind == "string":
            return Type("string")
        if kind == "cast":
            return expression.value
        if kind == "index":
            base = self.type_of(expression.left)
            return Type(base.base, max(base.dims - 1, 0))
        if kind == "member":
            if expression.value == "length":
                return INT
            if expression.left and expression.left.kind == "name" and expression.left.value == "Integer":
                return INT
            return UNKNOWN
        if kind in {"prefix", "postfix", "unary"}:
            return self.type_of(expression.left)
        if kind == "new":
            return expression.value
        if kind == "ternary":
            yes = self.type_of(expression.right)
            no = self.type_of(expression.extra)
            return DOUBLE if DOUBLE in {yes, no} else yes
        if kind == "binary":
            operator = expression.value
            if operator in {"==", "!=", "<", "<=", ">", ">=", "&&", "||"}:
                return BOOL
            left, right = self.type_of(expression.left), self.type_of(expression.right)
            return DOUBLE if DOUBLE in {left, right} else INT
        if kind == "call":
            name = self.call_name(expression.left)
            if name == "Math.sqrt":
                return DOUBLE
            if name in {"Math.min", "Math.max", "Math.abs"}:
                return DOUBLE if any(self.type_of(a).base == "double" for a in expression.extra) else INT
            if name == self.method.name:
                return self.method.return_type
            return UNKNOWN
        return UNKNOWN

    def call_name(self, expression: Expr | None) -> str:
        if expression is None:
            return ""
        if expression.kind == "name":
            return expression.value
        if expression.kind == "member":
            parent = self.call_name(expression.left)
            return f"{parent}.{expression.value}" if parent else expression.value
        return ""

    def emit_int_literal(self, value: str) -> str:
        suffixless = value.rstrip("lL")
        if suffixless.lower().startswith("0x"):
            number = int(suffixless, 16)
            if number > 0x7FFFFFFF:
                return f"(int32_t)UINT32_C(0x{number:08X})"
            return f"INT32_C({number})"
        return f"INT32_C({int(suffixless)})"

    def emit(self, expression: Expr | None) -> str:
        if expression is None:
            return "0"
        kind = expression.kind
        if kind == "name":
            return self.name_map.get(expression.value, expression.value)
        if kind == "int_literal":
            return self.emit_int_literal(expression.value)
        if kind == "double_literal":
            return expression.value
        if kind == "literal":
            return expression.value
        if kind == "null":
            return "NULL"
        if kind == "string":
            return expression.value
        if kind == "cast":
            typ = expression.value
            return f"(({c_type(typ)})({self.emit(expression.left)}))"
        if kind == "new":
            typ: Type = expression.value
            dimensions: list[Expr | None] = expression.extra
            sizes = [self.emit(item) if item is not None else None for item in dimensions]
            if typ.dims == 1:
                return f"{array_prefix(typ)}_new({sizes[0]})"
            if typ.dims == 2:
                if sizes[1] is None:
                    return f"{array_prefix(typ)}_new_rows({sizes[0]})"
                return f"{array_prefix(typ)}_new({sizes[0]}, {sizes[1]})"
            raise ParseError(f"Unsupported new expression: {typ}")
        if kind == "index":
            base_type = self.type_of(expression.left)
            prefix = array_prefix(base_type)
            return f"{prefix}_get({self.emit(expression.left)}, {self.emit(expression.right)})"
        if kind == "member":
            if expression.value == "length":
                return f"{array_prefix(self.type_of(expression.left))}_length({self.emit(expression.left)})"
            if expression.left and expression.left.kind == "name" and expression.left.value == "Integer":
                return "INT32_MIN" if expression.value == "MIN_VALUE" else "INT32_MAX"
            return f"{self.emit(expression.left)}_{expression.value}"
        if kind in {"prefix", "postfix"}:
            operand = self.emit(expression.left)
            typ = self.type_of(expression.left)
            delta = "INT32_C(1)"
            if typ.base == "double":
                return f"({operand} {'+' if expression.value == '++' else '-'} 1.0)"
            self.operations.add("add" if expression.value == "++" else "sub")
            return f"java_{'add' if expression.value == '++' else 'sub'}({operand}, {delta})"
        if kind == "unary":
            operand = self.emit(expression.left)
            typ = self.type_of(expression.left)
            if expression.value == "+":
                return f"(+({operand}))"
            if expression.value == "!":
                return f"!({operand})"
            if expression.value == "~":
                return f"(~({operand}))"
            if typ.base == "double":
                return f"(-({operand}))"
            self.operations.add("neg")
            return f"java_neg({operand})"
        if kind == "binary":
            operator = expression.value
            # Array references are opaque handles.  Comparing one with Java
            # null must use the runtime's null predicate rather than exposing
            # the representation (and it also keeps the rule correct for all
            # array element types).
            if operator in {"==", "!="}:
                if expression.left and expression.left.kind == "null":
                    reference = expression.right
                    reference_type = self.type_of(reference)
                    if reference_type.is_array:
                        predicate = f"{array_prefix(reference_type)}_is_null({self.emit(reference)})"
                        return predicate if operator == "==" else f"!({predicate})"
                if expression.right and expression.right.kind == "null":
                    reference = expression.left
                    reference_type = self.type_of(reference)
                    if reference_type.is_array:
                        predicate = f"{array_prefix(reference_type)}_is_null({self.emit(reference)})"
                        return predicate if operator == "==" else f"!({predicate})"
            left, right = self.emit(expression.left), self.emit(expression.right)
            typ = self.type_of(expression)
            if operator in {"+", "-", "*", "/", "%"} and typ.base == "int":
                operation = {"+": "add", "-": "sub", "*": "mul", "/": "div", "%": "mod"}[operator]
                self.operations.add(operation)
                return f"java_{operation}({left}, {right})"
            if operator in {"<<", ">>", ">>>"}:
                operation = {"<<": "shl", ">>": "shr", ">>>": "ushr"}[operator]
                self.operations.add(operation)
                return f"java_{operation}({left}, {right})"
            if operator in {"&", "|", "^"} and typ.base == "int":
                operation = {"&": "and", "|": "or", "^": "xor"}[operator]
                self.operations.add(operation)
                return f"java_{operation}({left}, {right})"
            if operator == "%" and typ.base == "double":
                self.headers.add("<math.h>")
                return f"fmod({left}, {right})"
            return f"({left} {operator} {right})"
        if kind == "ternary":
            return f"({self.emit(expression.left)} ? {self.emit(expression.right)} : {self.emit(expression.extra)})"
        if kind == "call":
            name = self.call_name(expression.left)
            arguments = ", ".join(self.emit(arg) for arg in expression.extra)
            if name == "Math.sqrt":
                self.headers.add("<math.h>")
                return f"sqrt({arguments})"
            if name in {"Math.min", "Math.max"}:
                typ = self.type_of(expression)
                if typ.base == "double":
                    self.headers.add("<math.h>")
                    return f"f{'min' if name.endswith('min') else 'max'}({arguments})"
                operation = "min" if name.endswith("min") else "max"
                self.operations.add(operation)
                return f"java_{operation}({arguments})"
            if name == "Math.abs":
                typ = self.type_of(expression)
                if typ.base == "double":
                    self.headers.add("<math.h>")
                    return f"fabs({arguments})"
                self.operations.add("abs")
                return f"java_abs({arguments})"
            if name == self.method.name:
                return f"{self.method.name}({arguments})"
            return f"{name.replace('.', '_')}({arguments})"
        raise ParseError(f"Unsupported expression node: {kind}")

    def emit_lvalue_type(self, expression: Expr) -> Type:
        return self.type_of(expression)

    def emit_assignment(self, target: Expr, operator: str, right: Expr) -> list[str]:
        if target.kind == "index":
            base_type = self.type_of(target.left)
            prefix = array_prefix(base_type)
            base = self.emit(target.left)
            index_lines: list[str] = []
            index_expression = target.right
            if index_expression and index_expression.kind == "postfix" and index_expression.left and index_expression.left.kind == "name":
                # Java evaluates ``a[i++] = value`` with the old index and
                # increments ``i`` before evaluating the right-hand side.
                index_name = self.fresh("index")
                variable = self.emit(index_expression.left)
                index_lines.append(f"int32_t {index_name} = {variable};")
                self.operations.add("add" if index_expression.value == "++" else "sub")
                operation = "add" if index_expression.value == "++" else "sub"
                index_lines.append(f"{variable} = java_{operation}({variable}, INT32_C(1));")
                index = index_name
            else:
                index = self.emit(index_expression)
            value = self.emit(right)
            if operator != "=":
                old = f"{prefix}_get({base}, {index})"
                value = self.emit_binary_assignment(operator, old, value, self.type_of(target))
            # Evaluate the right-hand side before the setter call.  The
            # benchmark assignments have no aliasing side effects in indices,
            # but named temporaries also keep C's argument order irrelevant.
            value_name = self.fresh("value")
            lines = list(index_lines)
            lines.append(f"{c_type(self.type_of(target))} {value_name} = {value};")
            lines.append(f"{prefix}_set({base}, {index}, {value_name});")
            return lines
        name = self.emit(target)
        value = self.emit(right)
        if operator != "=":
            value = self.emit_binary_assignment(operator, name, value, self.type_of(target))
        return [f"{name} = {value};"]

    def emit_binary_assignment(self, operator: str, left: str, right: str, typ: Type) -> str:
        symbol = operator[0]
        if typ.base == "int" and symbol in {"+", "-", "*", "/", "%"}:
            operation = {"+": "add", "-": "sub", "*": "mul", "/": "div", "%": "mod"}[symbol]
            self.operations.add(operation)
            return f"java_{operation}({left}, {right})"
        return f"({left} {symbol}= {right})"

    def emit_statement(self, statement: Statement, indent: int = 1) -> list[str]:
        pad = "    " * indent
        kind = statement.kind
        if kind == "empty":
            return [pad + ";"]
        if kind == "block":
            return self.emit_statements(statement.body or [], indent)
        if kind == "declaration":
            lines: list[str] = []
            for typ, name, initializer in statement.value:
                self.environment[name] = typ
                c_name = self.name_map.setdefault(
                    name, f"{name}Value" if name == self.method.name else name
                )
                declaration_type = c_type(typ)
                if initializer is None:
                    lines.append(f"{pad}{declaration_type} {c_name};")
                else:
                    lines.append(f"{pad}{declaration_type} {c_name} = {self.emit(initializer)};")
            return lines
        if kind == "assign":
            operator, right = statement.extra
            return [pad + line for line in self.emit_assignment(statement.value, operator, right)]
        if kind == "expr":
            expression = statement.value
            if expression.kind in {"prefix", "postfix"}:
                return [pad + line for line in self.emit_increment(expression.left, expression.value)]
            if expression.kind == "call":
                name = self.call_name(expression.left)
                args = expression.extra
                if name == "System.out.println":
                    self.headers.update({"<inttypes.h>", "<stdio.h>"})
                    typ = self.type_of(args[0]) if args else INT
                    format_string = '"%.17g\\n"' if typ.base == "double" else '"%" PRId32 "\\n"'
                    return [f"{pad}printf({format_string}, {self.emit(args[0])});"]
                if name == "Arrays.fill":
                    return self.emit_fill(args, indent)
                if name == "System.arraycopy":
                    return self.emit_arraycopy(args, indent)
            return [f"{pad}{self.emit(expression)};"]
        if kind == "return":
            return [f"{pad}return {self.emit(statement.value)};"]
        if kind == "break":
            return [pad + "break;"]
        if kind == "continue":
            return [pad + "continue;"]
        if kind == "if":
            lines = [f"{pad}if ({self.emit(statement.value)}) {{"]
            lines.extend(self.emit_statements(statement.body or [], indent + 1))
            lines.append(f"{pad}}}")
            if statement.otherwise is not None:
                lines[-1] += " else {"
                lines.extend(self.emit_statements(statement.otherwise, indent + 1))
                lines.append(f"{pad}}}")
            return lines
        if kind == "while":
            lines = [f"{pad}while ({self.emit(statement.value)}) {{"]
            lines.extend(self.emit_statements(statement.body or [], indent + 1))
            lines.append(f"{pad}}}")
            return lines
        if kind == "foreach":
            typ, name, iterable = statement.value
            iterable_type = self.type_of(iterable)
            prefix = array_prefix(iterable_type)
            index = self.fresh("index")
            element_type = Type(iterable_type.base, iterable_type.dims - 1)
            self.environment[name] = typ
            lines = [f"{pad}for (int32_t {index} = 0; {index} < {prefix}_length({self.emit(iterable)}); {index} = java_add({index}, INT32_C(1))) {{"]
            self.operations.add("add")
            lines.append(f"{pad}    {c_type(element_type)} {name} = {prefix}_get({self.emit(iterable)}, {index});")
            lines.extend(self.emit_statements(statement.body or [], indent + 1))
            lines.append(f"{pad}}}")
            return lines
        if kind == "for":
            init, condition, update = statement.value
            init_text = self.emit_for_part(init)
            condition_text = self.emit(condition) if condition is not None else "1"
            update_text = self.emit_for_part(update)
            lines = [f"{pad}for ({init_text}; {condition_text}; {update_text}) {{"]
            lines.extend(self.emit_statements(statement.body or [], indent + 1))
            lines.append(f"{pad}}}")
            return lines
        raise ParseError(f"Unsupported statement node: {kind}")

    def emit_increment(self, target: Expr, operator: str) -> list[str]:
        """Emit an increment/decrement used as a standalone statement."""
        delta = "INT32_C(1)"
        operation = "add" if operator == "++" else "sub"
        self.operations.add(operation)
        if target.kind == "index":
            target_type = self.type_of(target)
            prefix = array_prefix(self.type_of(target.left))
            base = self.emit(target.left)
            index = self.emit(target.right)
            old = f"{prefix}_get({base}, {index})"
            value = f"java_{operation}({old}, {delta})" if target_type.base == "int" else f"({old} {'+' if operation == 'add' else '-'} 1.0)"
            return [f"{prefix}_set({base}, {index}, {value});"]
        name = self.emit(target)
        if self.type_of(target).base == "double":
            return [f"{name} = {name} {'+' if operation == 'add' else '-'} 1.0;"]
        return [f"{name} = java_{operation}({name}, {delta});"]

    def emit_for_part(self, statement: Statement | None) -> str:
        if statement is None:
            return ""
        if statement.kind == "declaration":
            typ, name, initializer = statement.value[0]
            self.environment[name] = typ
            c_name = self.name_map.setdefault(
                name, f"{name}Value" if name == self.method.name else name
            )
            return f"{c_type(typ)} {c_name} = {self.emit(initializer)}"
        if statement.kind == "assign":
            operator, right = statement.extra
            return " ".join(self.emit_assignment(statement.value, operator, right)).rstrip(";")
        if statement.kind == "expr":
            expression = statement.value
            if expression.kind in {"prefix", "postfix"} and expression.left and expression.left.kind == "name":
                name = expression.left.value
                self.operations.add("add" if expression.value == "++" else "sub")
                operation = "add" if expression.value == "++" else "sub"
                return f"{name} = java_{operation}({name}, INT32_C(1))"
            return self.emit(expression)
        return ""

    def emit_fill(self, arguments: list[Expr], indent: int) -> list[str]:
        array, value = arguments
        typ = self.type_of(array)
        prefix = array_prefix(typ)
        index = self.fresh("fill_index")
        self.operations.add("add")
        pad = "    " * indent
        return [
            f"{pad}for (int32_t {index} = 0; {index} < {prefix}_length({self.emit(array)}); {index} = java_add({index}, INT32_C(1))) {{",
            f"{pad}    {prefix}_set({self.emit(array)}, {index}, {self.emit(value)});",
            f"{pad}}}",
        ]

    def emit_arraycopy(self, arguments: list[Expr], indent: int) -> list[str]:
        source, source_pos, destination, destination_pos, length = arguments
        typ = self.type_of(source)
        prefix = array_prefix(typ)
        index = self.fresh("copy_index")
        self.operations.add("add")
        pad = "    " * indent
        source_index = f"java_add({self.emit(source_pos)}, {index})"
        destination_index = f"java_add({self.emit(destination_pos)}, {index})"
        return [
            f"{pad}for (int32_t {index} = 0; {index} < {self.emit(length)}; {index} = java_add({index}, INT32_C(1))) {{",
            f"{pad}    {prefix}_set({self.emit(destination)}, {destination_index}, {prefix}_get({self.emit(source)}, {source_index}));",
            f"{pad}}}",
        ]

    def emit_statements(self, statements: list[Statement], indent: int = 1) -> list[str]:
        lines: list[str] = []
        for statement in statements:
            lines.extend(self.emit_statement(statement, indent))
        return lines

    def helper_definitions(self) -> list[str]:
        definitions: dict[str, str] = {
            "add": "static int32_t java_add(int32_t left, int32_t right) { return (int32_t)((uint32_t)left + (uint32_t)right); }",
            "sub": "static int32_t java_sub(int32_t left, int32_t right) { return (int32_t)((uint32_t)left - (uint32_t)right); }",
            "mul": "static int32_t java_mul(int32_t left, int32_t right) { return (int32_t)((uint32_t)left * (uint32_t)right); }",
            "div": "static int32_t java_div(int32_t left, int32_t right) { if (right == 0) { fputs(\"JAVA_ARITHMETIC_ERROR: DIVIDE_BY_ZERO\\n\", stderr); exit(75); } if (left == INT32_MIN && right == -1) return INT32_MIN; return left / right; }",
            "mod": "static int32_t java_mod(int32_t left, int32_t right) { if (right == 0) { fputs(\"JAVA_ARITHMETIC_ERROR: DIVIDE_BY_ZERO\\n\", stderr); exit(75); } if (left == INT32_MIN && right == -1) return 0; return left % right; }",
            "neg": "static int32_t java_neg(int32_t value) { return (int32_t)(0u - (uint32_t)value); }",
            "and": "static int32_t java_and(int32_t left, int32_t right) { return (int32_t)((uint32_t)left & (uint32_t)right); }",
            "or": "static int32_t java_or(int32_t left, int32_t right) { return (int32_t)((uint32_t)left | (uint32_t)right); }",
            "xor": "static int32_t java_xor(int32_t left, int32_t right) { return (int32_t)((uint32_t)left ^ (uint32_t)right); }",
            "shl": "static int32_t java_shl(int32_t value, int32_t distance) { return (int32_t)((uint32_t)value << ((uint32_t)distance & 31u)); }",
            "shr": "static int32_t java_shr(int32_t value, int32_t distance) { return value >> ((uint32_t)distance & 31u); }",
            "ushr": "static int32_t java_ushr(int32_t value, int32_t distance) { return (int32_t)((uint32_t)value >> ((uint32_t)distance & 31u)); }",
            "abs": "static int32_t java_abs(int32_t value) { return value < 0 ? (int32_t)(0u - (uint32_t)value) : value; }",
            "min": "static int32_t java_min(int32_t left, int32_t right) { return left < right ? left : right; }",
            "max": "static int32_t java_max(int32_t left, int32_t right) { return left > right ? left : right; }",
        }
        result: list[str] = []
        if self.operations & {"div", "mod"}:
            self.headers.update({"<stdio.h>", "<stdlib.h>"})
        for name in ("add", "sub", "mul", "div", "mod", "neg", "and", "or", "xor", "shl", "shr", "ushr", "abs", "min", "max"):
            if name in self.operations:
                result.append(definitions[name])
        return result

    def render(self) -> str:
        body = self.emit_statements(self.method.body)
        helpers = self.helper_definitions()
        parts = [
            '#include "java_arrays.h"',
            *[f"#include {header}" for header in sorted(self.headers)],
            "",
            *helpers,
        ]
        if helpers:
            parts.append("")
        parts.extend([
            f"{c_type(self.method.return_type)} {self.method.name}({', '.join(f'{c_type(typ)} {name}' for typ, name in self.method.parameters)}) {{",
            *body,
            "}",
            "",
        ])
        return "\n".join(parts)


def translate(source: Path) -> str:
    method = Parser(source.read_text(encoding="utf-8")).parse()
    return Emitter(method).render()


def translate_tree(mutants_dir: Path, *, class_name: str | None = None) -> tuple[int, int]:
    translated = failed = 0
    for java in sorted(mutants_dir.glob("*/mutants/*/java/*.java")):
        owner = java.parents[3].name
        if class_name and owner != class_name:
            continue
        destination = java.parents[1] / "c" / f"{owner}.c"
        destination.parent.mkdir(parents=True, exist_ok=True)
        try:
            destination.write_text(translate(java), encoding="utf-8")
        except (OSError, ParseError, ValueError) as error:
            failed += 1
            print(f"{java}: {error}")
            continue
        translated += 1
    return translated, failed


def main() -> None:
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("--mutants-dir", type=Path, required=True)
    parser.add_argument("--class-name")
    args = parser.parse_args()
    translated, failed = translate_tree(args.mutants_dir, class_name=args.class_name)
    print(f"Translated {translated} mutant(s); {failed} failed")
    if failed:
        raise SystemExit(1)


if __name__ == "__main__":
    main()
