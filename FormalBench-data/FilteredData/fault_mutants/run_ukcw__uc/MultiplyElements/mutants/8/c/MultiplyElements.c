#include "java_arrays.h"
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>

static int32_t java_add(int32_t left, int32_t right) { return (int32_t)((uint32_t)left + (uint32_t)right); }
static int32_t java_sub(int32_t left, int32_t right) { return (int32_t)((uint32_t)left - (uint32_t)right); }
static int32_t java_mul(int32_t left, int32_t right) { return (int32_t)((uint32_t)left * (uint32_t)right); }
static int32_t java_div(int32_t left, int32_t right) { if (right == 0) { fputs("JAVA_ARITHMETIC_ERROR: DIVIDE_BY_ZERO\n", stderr); exit(75); } if (left == INT32_MIN && right == -1) return INT32_MIN; return left / right; }

JIntArray multiplyElements(JIntArray testTup) {
    if ((jarray_length(testTup) < INT32_C(2))) {
        return jarray_new(INT32_C(0));
    }
    JIntArray result = jarray_new(java_div(jarray_length(testTup), INT32_C(1)));
    for (int32_t i = INT32_C(0); (i < java_sub(jarray_length(testTup), INT32_C(1))); i = java_add(i, INT32_C(1))) {
        int32_t __value_1 = java_mul(jarray_get(testTup, i), jarray_get(testTup, java_add(i, INT32_C(1))));
        jarray_set(result, i, __value_1);
    }
    return result;
}
