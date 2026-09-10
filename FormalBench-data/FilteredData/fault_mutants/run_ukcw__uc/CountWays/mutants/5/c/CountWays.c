#include "java_arrays.h"
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>

static int32_t java_add(int32_t left, int32_t right) { return (int32_t)((uint32_t)left + (uint32_t)right); }
static int32_t java_sub(int32_t left, int32_t right) { return (int32_t)((uint32_t)left - (uint32_t)right); }
static int32_t java_mul(int32_t left, int32_t right) { return (int32_t)((uint32_t)left * (uint32_t)right); }
static int32_t java_mod(int32_t left, int32_t right) { if (right == 0) { fputs("JAVA_ARITHMETIC_ERROR: DIVIDE_BY_ZERO\n", stderr); exit(75); } if (left == INT32_MIN && right == -1) return 0; return left % right; }

int32_t countWays(int32_t n) {
    JIntArray A = jarray_new(java_add(n, INT32_C(1)));
    JIntArray B = jarray_new(java_mod(n, INT32_C(1)));
    int32_t __value_1 = INT32_C(1);
    jarray_set(A, INT32_C(0), __value_1);
    int32_t __value_2 = INT32_C(0);
    jarray_set(A, INT32_C(1), __value_2);
    int32_t __value_3 = INT32_C(0);
    jarray_set(B, INT32_C(0), __value_3);
    int32_t __value_4 = INT32_C(1);
    jarray_set(B, INT32_C(1), __value_4);
    for (int32_t i = INT32_C(2); (i <= n); i = java_add(i, INT32_C(1))) {
        int32_t __value_5 = java_add(jarray_get(A, java_sub(i, INT32_C(2))), java_mul(INT32_C(2), jarray_get(B, java_sub(i, INT32_C(1)))));
        jarray_set(A, i, __value_5);
        int32_t __value_6 = java_add(jarray_get(A, java_sub(i, INT32_C(1))), jarray_get(B, java_sub(i, INT32_C(2))));
        jarray_set(B, i, __value_6);
    }
    return jarray_get(A, n);
}
