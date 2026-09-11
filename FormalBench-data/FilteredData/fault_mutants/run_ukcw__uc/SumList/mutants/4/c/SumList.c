#include "java_arrays.h"
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>

static int32_t java_add(int32_t left, int32_t right) { return (int32_t)((uint32_t)left + (uint32_t)right); }
static int32_t java_mod(int32_t left, int32_t right) { if (right == 0) { fputs("JAVA_ARITHMETIC_ERROR: DIVIDE_BY_ZERO\n", stderr); exit(75); } if (left == INT32_MIN && right == -1) return 0; return left % right; }
static int32_t java_min(int32_t left, int32_t right) { return left < right ? left : right; }

JIntArray sumList(JIntArray arr1, JIntArray arr2) {
    int32_t minLength = java_min(jarray_length(arr1), jarray_length(arr2));
    JIntArray result = jarray_new(minLength);
    for (int32_t i = INT32_C(0); (i < minLength); i = java_add(i, INT32_C(1))) {
        int32_t __value_1 = java_mod(jarray_get(arr1, i), jarray_get(arr2, i));
        jarray_set(result, i, __value_1);
    }
    return result;
}
