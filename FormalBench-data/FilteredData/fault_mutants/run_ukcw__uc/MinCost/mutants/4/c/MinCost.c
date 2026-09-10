#include "java_arrays.h"
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>

static int32_t java_add(int32_t left, int32_t right) { return (int32_t)((uint32_t)left + (uint32_t)right); }
static int32_t java_sub(int32_t left, int32_t right) { return (int32_t)((uint32_t)left - (uint32_t)right); }
static int32_t java_div(int32_t left, int32_t right) { if (right == 0) { fputs("JAVA_ARITHMETIC_ERROR: DIVIDE_BY_ZERO\n", stderr); exit(75); } if (left == INT32_MIN && right == -1) return INT32_MIN; return left / right; }
static int32_t java_min(int32_t left, int32_t right) { return left < right ? left : right; }

int32_t minCost(JIntArray2 cost, int32_t m, int32_t n) {
    JIntArray2 tc = jarray2_new(java_div(m, INT32_C(1)), java_add(n, INT32_C(1)));
    int32_t __value_1 = jarray_get(jarray2_get(cost, INT32_C(0)), INT32_C(0));
    jarray_set(jarray2_get(tc, INT32_C(0)), INT32_C(0), __value_1);
    for (int32_t i = INT32_C(1); (i <= m); i = java_add(i, INT32_C(1))) {
        int32_t __value_2 = java_add(jarray_get(jarray2_get(tc, java_sub(i, INT32_C(1))), INT32_C(0)), jarray_get(jarray2_get(cost, i), INT32_C(0)));
        jarray_set(jarray2_get(tc, i), INT32_C(0), __value_2);
    }
    for (int32_t j = INT32_C(1); (j <= n); j = java_add(j, INT32_C(1))) {
        int32_t __value_3 = java_add(jarray_get(jarray2_get(tc, INT32_C(0)), java_sub(j, INT32_C(1))), jarray_get(jarray2_get(cost, INT32_C(0)), j));
        jarray_set(jarray2_get(tc, INT32_C(0)), j, __value_3);
    }
    for (int32_t i = INT32_C(1); (i <= m); i = java_add(i, INT32_C(1))) {
        for (int32_t j = INT32_C(1); (j <= n); j = java_add(j, INT32_C(1))) {
            int32_t __value_4 = java_add(java_min(jarray_get(jarray2_get(tc, java_sub(i, INT32_C(1))), java_sub(j, INT32_C(1))), java_min(jarray_get(jarray2_get(tc, java_sub(i, INT32_C(1))), j), jarray_get(jarray2_get(tc, i), java_sub(j, INT32_C(1))))), jarray_get(jarray2_get(cost, i), j));
            jarray_set(jarray2_get(tc, i), j, __value_4);
        }
    }
    return jarray_get(jarray2_get(tc, m), n);
}
