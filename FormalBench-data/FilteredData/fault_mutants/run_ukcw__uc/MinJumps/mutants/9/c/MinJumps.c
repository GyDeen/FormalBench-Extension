#include "java_arrays.h"
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>

static int32_t java_add(int32_t left, int32_t right) { return (int32_t)((uint32_t)left + (uint32_t)right); }
static int32_t java_sub(int32_t left, int32_t right) { return (int32_t)((uint32_t)left - (uint32_t)right); }
static int32_t java_mod(int32_t left, int32_t right) { if (right == 0) { fputs("JAVA_ARITHMETIC_ERROR: DIVIDE_BY_ZERO\n", stderr); exit(75); } if (left == INT32_MIN && right == -1) return 0; return left % right; }
static int32_t java_min(int32_t left, int32_t right) { return left < right ? left : right; }

int32_t minJumps(JIntArray arr, int32_t n) {
    JIntArray dp = jarray_new(n);
    for (int32_t __fill_index_1 = 0; __fill_index_1 < jarray_length(dp); __fill_index_1 = java_add(__fill_index_1, INT32_C(1))) {
        jarray_set(dp, __fill_index_1, INT32_MAX);
    }
    int32_t __value_2 = INT32_C(0);
    jarray_set(dp, INT32_C(0), __value_2);
    for (int32_t i = INT32_C(1); (i < n); i = java_add(i, INT32_C(1))) {
        for (int32_t j = INT32_C(0); (j < i); j = java_add(j, INT32_C(1))) {
            if ((java_mod(jarray_get(arr, j), j) >= i)) {
                int32_t __value_3 = java_min(jarray_get(dp, i), java_add(jarray_get(dp, j), INT32_C(1)));
                jarray_set(dp, i, __value_3);
            }
        }
    }
    return jarray_get(dp, java_sub(n, INT32_C(1)));
}
