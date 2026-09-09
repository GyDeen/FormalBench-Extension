---
name: java-to-c
description: Translate Java benchmark programs into C for cross-language semantic-equivalence experiments, using java_arrays.h for every Java array operation and preserving Java control flow, values, mutations, and array failures. Use for Java-to-C translation in this project; do not use for redesign, optimization, or defensive repair.
---

# Java to C semantic translation

You are translating a Java benchmark program into C for a cross-language
semantic-equivalence experiment. Preserve the observable semantics of the
original Java program as closely as possible. Do not improve, repair,
simplify, sanitize, optimize, or defensively modify the original program.

A predefined Java-array compatibility library is available through
`java_arrays.h`. Use it for every Java array operation. The translated source
must include:

```c
#include "java_arrays.h"
#include <stdint.h>
```

Add other standard headers only when the original program requires them.

## Array mappings

For Java integer arrays:

- `int[]` becomes `JIntArray`.
- `new int[n]` becomes `jarray_new(n)`.
- `a.length` becomes `jarray_length(a)`.
- A read `a[i]` becomes `jarray_get(a, i)`.
- A write `a[i] = value` becomes `jarray_set(a, i, value)`.
- `a == null` becomes `jarray_is_null(a)`.
- `a != null` becomes `!jarray_is_null(a)`.
- Java `null` becomes C `NULL`.

For Java double arrays:

- `double[]` becomes `JDoubleArray`.
- `new double[n]` becomes `jdouble_array_new(n)`.
- `.length` becomes `jdouble_array_length(...)`.
- An indexed read becomes `jdouble_array_get(...)`.
- An indexed write becomes `jdouble_array_set(...)`.
- A null test uses `jdouble_array_is_null(...)`.
- Java `null` becomes C `NULL`.

For Java boolean arrays, use `JBoolArray`, `jbool_array_new`,
`jbool_array_length`, `jbool_array_get`, `jbool_array_set`, and
`jbool_array_is_null` with Java `null` represented by C `NULL`.

For Java two-dimensional integer arrays:

- `int[][]` becomes `JIntArray2`.
- `new int[rows][]` becomes `jarray2_new_rows(rows)`.
- `new int[rows][cols]` becomes `jarray2_new(rows, cols)`.
- The outer `.length` becomes `jarray2_length(...)`.
- An outer indexed read becomes `jarray2_get(...)`.
- An outer indexed write becomes `jarray2_set(...)`.

Use the corresponding `jdouble_array2_*` operations for two-dimensional
double arrays when they are present in the supplied library. Preserve each
row as its own Java array reference; do not assume a multidimensional array is
rectangular unless the Java source creates it that way.

## Mandatory semantic constraints

1. Never access the internal representation of `JIntArray`, `JDoubleArray`,
   `JIntArray2`, or any other compatibility-library handle directly.
2. Never use expressions such as `array->data[i]`, `array->length`, or an
   equivalent replacement.
3. Never replace a library operation with manual pointer arithmetic.
4. Do not introduce null checks, bounds checks, length checks, early returns,
   fallback values, or error recovery unless equivalent control flow exists in
   the Java source.
5. Do not transform an exceptional Java execution into a normal C return
   value.
6. Do not add guards such as the following unless the Java source contains the
   corresponding logic:

   ```c
   if (array == NULL) return 0;
   if (length <= 0) return 0;
   if (index < 0) return 0;
   ```

7. Preserve the original control-flow structure, loop conditions, branches,
   recursion, arithmetic, array mutation, and return expressions whenever
   possible.
8. Preserve Java `null` separately from an empty array. `NULL` represents Java
   `null`; `jarray_new(0)` represents a non-null empty array, so
   \(null \neq []\).
9. Let the compatibility library model Java array failures. Do not manually
   catch, suppress, or replace those failures.
10. Do not add memory-management calls inside the translated algorithm unless
    explicitly requested. Cleanup is handled by the test infrastructure.
11. Keep the translated function interface and structure as close as reasonably
    possible to the Java original.
12. Do not generate formal specifications, comments describing expected
    preconditions, or assertions that reveal semantic requirements.

## General translation rules

Inspect the complete Java source before writing C, including helper methods and
mutations performed before a return.

- Preserve observable return values, call order, alias-visible mutations,
  recursion, loop structure, branches, short-circuit behavior, and final
  mutable-array state.
- Preserve Java method names unless the supplied C harness requires an explicit
  mapping. Keep every callable helper method needed by the original program.
- Use fixed-width C types for Java integral primitives, especially `int32_t`
  for Java `int`; use `int8_t`, `int16_t`, `uint16_t`, and `int64_t` for other
  Java integral widths as appropriate. Use `stdbool.h` for Java `boolean` when
  needed.
- Preserve Java integer promotion, narrowing casts, truncating division,
  remainder-sign behavior, and fixed-width wraparound. Avoid undefined signed
  overflow in C when an admissible Java execution can overflow.
- Implement Java shifts deliberately: mask shift distances (`& 31` or `& 63`),
  distinguish arithmetic `>>` from logical `>>>`, and use matching-width
  unsigned values for logical operations when necessary.
- Preserve the placement of Java casts. For example, `(int)Math.sqrt(3) * r`
  is not equivalent to `(int)(sqrt(3) * r)`.
- Map required `Math` operations to compatible `<math.h>` behavior without
  algebraically rewriting expressions or changing Java evaluation order.
- Translate only the small Java-library operations exercised by the supplied
  program, such as `Math.min`, `Math.max`, `Math.abs`, `Math.sqrt`,
  `Arrays.fill`, and `System.arraycopy`. Do not import a general-purpose
  compatibility layer beyond the supplied array library.
- Java primitive arrays are zero-initialized. Use the compatibility constructors
  whose elements have the corresponding Java default values.
- Preserve returning a newly allocated array versus returning or mutating an
  input array, including null branches, aliasing, and post-call state.
- Translate enhanced `for` loops in element order. Preserve `break`,
  `continue`, switch fall-through, and recursive argument order.
- Translate constants such as `Integer.MIN_VALUE` and `Integer.MAX_VALUE` using
  the corresponding fixed-width limits.
- Translate only language or library constructs exercised by the supplied
  program. Ignore unused Java imports.
- Do not invent console input, a demonstration `main`, JSON serialization, or
  unrelated compatibility functions unless requested by the supplied ABI.

## Harness and ABI compatibility

When a runner, compatibility header, function prototype, or input schema is
supplied, follow it exactly. The project's differential runner compares
normalized return values and `state_after` for mutable fixtures. A numerically
correct return value is insufficient when the Java method also mutates an input
array.

Represent arrays according to the supplied ABI. With `java_arrays.h`, use the
opaque handles and accessors specified above. If no ABI is supplied, emit a
self-contained translation with explicit array lengths and small result structs
where a C pointer alone cannot express a returned array's length. Preserve the
distinction between a returned newly allocated array and a returned or mutated
input array, including ownership and aliasing as far as the requested ABI
permits.

## Evaluation-order constraint

Preserve Java expression evaluation order. If translating an expression would
otherwise evaluate a side effect in a different order in C, split it into
statements that evaluate subexpressions in Java order before calling an array
accessor. Do not add checks while doing so. The compatibility library should be
the operation that triggers an invalid-array failure.

## Examples

Java:

```java
return a[0];
```

Correct C:

```c
return jarray_get(a, 0);
```

Do not turn it into a normal return for null or empty input:

```c
if (jarray_is_null(a) || jarray_length(a) == 0)
    return 0;
return jarray_get(a, 0);
```

Java:

```java
for (int i = 0; i < a.length; i++) {
    sum += a[i];
}
```

Correct C:

```c
for (int32_t i = 0; i < jarray_length(a); i++) {
    sum += jarray_get(a, i);
}
```

Java:

```java
if (a == null) {
    return 0;
}
```

Correct C:

```c
if (jarray_is_null(a)) {
    return 0;
}
```

The null check is preserved here because it exists in the Java source.

## Output contract

Return only the translated C source code. Do not add Markdown fences,
explanations, formal specifications, precondition comments, or text before or
after the source. Include the required `java_arrays.h` and `<stdint.h>` headers,
plus only other standard headers strictly required by the original program.

Before responding, check that every Java helper and return path has a C
counterpart, observable array mutation is preserved, every Java array operation
uses the compatibility API, required standard headers are included, and the
result is valid C rather than C++. Verify that every return path and helper
function has a counterpart; do not omit methods merely because a common test
input does not call them.
