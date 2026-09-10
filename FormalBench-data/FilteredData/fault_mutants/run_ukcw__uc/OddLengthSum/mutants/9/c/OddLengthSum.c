#include "java_arrays.h"
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>

static int32_t java_add(int32_t left, int32_t right) { return (int32_t)((uint32_t)left + (uint32_t)right); }
static int32_t java_mul(int32_t left, int32_t right) { return (int32_t)((uint32_t)left * (uint32_t)right); }
static int32_t java_div(int32_t left, int32_t right) { if (right == 0) { fputs("JAVA_ARITHMETIC_ERROR: DIVIDE_BY_ZERO\n", stderr); exit(75); } if (left == INT32_MIN && right == -1) return INT32_MIN; return left / right; }

int32_t oddLengthSum(JIntArray arr) {
    int32_t sum = INT32_C(0);
    int32_t l = jarray_length(arr);
    for (int32_t i = INT32_C(0); (i < l); i = java_add(i, INT32_C(1))) {
        sum = java_add(sum, java_mul(java_div(java_add(java_mul(java_add(i, INT32_C(1)), java_mul(l, i)), INT32_C(1)), INT32_C(2)), jarray_get(arr, i)));
    }
    return sum;
}
