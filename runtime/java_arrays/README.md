# Java array compatibility library
The API uses opaque reference handles. `NULL` represents Java null; `new(0)`
returns a non-null empty array. Assigning a handle shares the same array, and
`a == b` compares reference identity, including for empty arrays. Length is
immutable, numeric elements start at zero, and reads and writes check nullness
before bounds. No invalid input is converted into a normal return value.
See the [Java array specification](https://docs.oracle.com/javase/specs/jls/se25/html/jls-10.html).

## Use

```c
#include "java_arrays.h"

int main(void) {
    JIntArray nums = jarray_new(3);
    jarray_set(nums, 0, 7);
    int32_t sum = 0;
    for (int32_t i = 0; i < jarray_length(nums); ++i) {
        sum += jarray_get(nums, i);
    }
    jarray_free(nums);
    return sum == 7 ? 0 : 1;
}
```

Build from `FormalBench-Extension`:

```bash
cc -std=c11 -Wall -Wextra -Werror -pedantic \
  -I runtime/java_arrays your_program.c runtime/java_arrays/java_arrays.c \
  -o your_program
```

| Java type | Handle | Operations |
| --- | --- | --- |
| `int[]` | `JIntArray` | `jarray_new`, `jarray_length`, `jarray_get`, `jarray_set`, `jarray_is_null`, `jarray_free` |
| `double[]` | `JDoubleArray` | Same suffixes with `jdouble_array_` |
| `int[][]` | `JIntArray2` | Same suffixes with `jarray2_`, plus `jarray2_new_rows` |

`jarray2_new_rows(n)` models `new int[n][]`: every row initially is null.
`jarray2_new(n, m)` models `new int[n][m]`: each row is a distinct array.
Both dimensions must be nonnegative, including when the outer length is zero.
Rows can be null, empty, different lengths, or shared. For an element access,
use `jarray_get(jarray2_get(matrix, row), column)`. Row assignment uses
`jarray2_set(matrix, row, values)` and preserves aliases.

## Failure contract

The first failing library operation prints one line to **stderr** and calls
`exit(code)`. There is no error recovery or catchable Java exception. Successful
operations print nothing. Fixed exit codes and messages allow a future harness
to recognize failures without parsing a crash or sanitizer trace.

| Condition | Exit code | stderr line |
| --- | --- | --- |
| Null array passed to length/get/set | 71 | `JAVA_ARRAY_ERROR: NULL_REFERENCE_ERROR (java.lang.NullPointerException)` |
| Negative index or index at/beyond length | 72 | `JAVA_ARRAY_ERROR: INDEX_OUT_OF_BOUNDS (java.lang.ArrayIndexOutOfBoundsException)` |
| Negative allocation dimension | 73 | `JAVA_ARRAY_ERROR: NEGATIVE_ARRAY_SIZE (java.lang.NegativeArraySizeException)` |
| Allocation failure or allocation-size overflow | 74 | `JAVA_ARRAY_ERROR: OUT_OF_MEMORY (java.lang.OutOfMemoryError)` |

In particular, `jarray_length(NULL)` fails with 71, while the length of an empty
array returns zero. Reading index zero from an empty array fails with 72.
`is_null(NULL)` returns true without failing. `set` returns its assigned value.

## Lifetime and translation constraints

Create arrays through the library and use its accessors; do not fabricate or
dereference handles. `free` is an explicit C infrastructure operation, not part
of Java program semantics. Free each allocation exactly once, after its last
use through every alias. `jarray2_free` frees only the outer array, because its
rows may be shared; free each distinct row separately when it is no longer used.
Replacing a row does not free it. `free(NULL)` is a no-op. There is no garbage
collector, use-after-free protection, or general Java object-array support.

Preserve the original control flow. Do not add early returns, validation, or
fallback values to repair invalid inputs. C does not guarantee Java's expression
evaluation order: evaluate side-effecting expressions in separate statements in
Java order. For simple array assignment, evaluate the array, index, and RHS
before calling `set`; for compound assignment, perform the checked `get` before
evaluating the RHS. Accessor calls alone cannot enforce surrounding evaluation
order. Likewise, this library does not implement Java integer overflow, casts,
division semantics, or exceptions outside array operations.

## Validation

```bash
python3 runtime/java_arrays/tests/test_java_arrays.py
```

The tests compile the library with strict C11 warnings, verify successful values
and aliasing, and run invalid operations in separate processes to check exact
exit codes and error messages.
