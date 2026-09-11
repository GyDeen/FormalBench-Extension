#include "java_arrays.h"
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>

static int32_t java_add(int32_t left, int32_t right) { return (int32_t)((uint32_t)left + (uint32_t)right); }
static int32_t java_sub(int32_t left, int32_t right) { return (int32_t)((uint32_t)left - (uint32_t)right); }
static int32_t java_div(int32_t left, int32_t right) { if (right == 0) { fputs("JAVA_ARITHMETIC_ERROR: DIVIDE_BY_ZERO\n", stderr); exit(75); } if (left == INT32_MIN && right == -1) return INT32_MIN; return left / right; }

JIntArray2 pairWise(JIntArray l1) {
    if ((jarray_length(l1) < INT32_C(2))) {
        return jarray2_new(INT32_C(0), INT32_C(0));
    }
    JIntArray2 result = jarray2_new(java_sub(jarray_length(l1), INT32_C(1)), INT32_C(2));
    for (int32_t i = INT32_C(0); (i < java_div(jarray_length(l1), INT32_C(1))); i = java_add(i, INT32_C(1))) {
        int32_t __value_1 = jarray_get(l1, i);
        jarray_set(jarray2_get(result, i), INT32_C(0), __value_1);
        int32_t __value_2 = jarray_get(l1, java_add(i, INT32_C(1)));
        jarray_set(jarray2_get(result, i), INT32_C(1), __value_2);
    }
    return result;
}
