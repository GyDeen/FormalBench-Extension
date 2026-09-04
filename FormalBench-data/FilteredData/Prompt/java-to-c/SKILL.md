---
name: java-to-c
description: Translate the filtered FormalBench Java methods into C for differential testing, preserving return values, mutable-array state, control flow, and Java numeric semantics. Use for Java-to-C translation or repair tasks in this project; do not use for general redesign or optimization.
---

# Java to C

Translate the supplied Java program into complete, compilable C suitable for this project's differential-testing harness. Treat the Java implementation, not its Javadoc or likely intent, as the reference behavior.

## Required outcome

- Preserve the result of every callable method and the final contents of every mutable array argument.
- Preserve call order, alias-visible mutations, recursion, loop structure, branches, and short-circuit evaluation where practical.
- Preserve method names unless the supplied C harness requires an explicit mapping.
- Translate only constructs needed to represent the Java program. Do not optimize, simplify, correct apparent bugs, hard-code observed outputs, add logging, or add unrelated behavior.
- Return only the complete C source, with no Markdown fences or explanation.

## Translation decisions

Inspect the complete Java source before writing C, including helper methods and mutations performed before a return.

- Use fixed-width C types for Java integral primitives: `int8_t`, `int16_t`, `uint16_t`, `int32_t`, and `int64_t` as appropriate. Use `stdbool.h` for `boolean`.
- Keep Java integer promotion, narrowing casts, truncating division, remainder-sign behavior, and 32/64-bit wraparound in mind. Avoid relying on undefined signed overflow in C when an admissible execution can overflow.
- Implement Java shifts deliberately: mask shift distances (`& 31` or `& 63`), distinguish arithmetic `>>` from logical `>>>`, and perform bit operations through matching-width unsigned values when necessary.
- Preserve the placement of Java casts. For example, `(int)Math.sqrt(3) * r` is not equivalent to `(int)(sqrt(3) * r)`.
- Map `Math` operations to type-compatible `<math.h>` behavior, preserving Java evaluation order and casts. Do not replace floating-point expressions with algebraically equivalent forms.
- Represent Java arrays using the ABI expected by the supplied harness. Carry explicit lengths wherever C cannot recover Java's `.length`; for multidimensional arrays, preserve each row's length rather than assuming a rectangular matrix.
- Java primitive arrays are zero-initialized. Use zero-initialized C storage when the Java code depends on default values.
- Distinguish returning a newly allocated array from returning or mutating an input array. Preserve null and empty-array branches, ownership, aliasing, and post-call input state observable by the harness.
- Translate enhanced `for` loops in element order. Preserve `break`, `continue`, `switch` fall-through, and recursive call argument order.
- Translate constants such as `Integer.MIN_VALUE` and `Integer.MAX_VALUE` with the corresponding fixed-width limits.
- Ignore unused Java imports. Translate only the small Java-library operations exercised by the retained program, such as `Math.min`, `Math.max`, `Math.abs`, `Math.sqrt`, `Arrays.fill`, and `System.arraycopy`, without importing a general compatibility layer.

## Harness compatibility

When a runner, header, function prototype, or input schema is supplied, follow it exactly. The project compares normalized return values and `state_after` for mutable fixtures, so a numerically correct return value alone is insufficient if the Java method mutates an input.

If no ABI is supplied, emit a self-contained translation with explicit array lengths and small result structs where a C pointer alone cannot express a returned array's length. Do not invent console input, a demonstration `main`, or JSON serialization unless requested.

## Final check

Before responding, verify mentally that every Java return path and observable mutation has a C counterpart, all helper functions are present, required standard headers are included, and the result is valid C rather than C++.
