#include "java_arrays.h"
#include <stdint.h>

static int32_t java_add(int32_t left, int32_t right) { return (int32_t)((uint32_t)left + (uint32_t)right); }
static int32_t java_sub(int32_t left, int32_t right) { return (int32_t)((uint32_t)left - (uint32_t)right); }
static int32_t java_neg(int32_t value) { return (int32_t)(0u - (uint32_t)value); }
static int32_t java_max(int32_t left, int32_t right) { return left > right ? left : right; }

int32_t maximumSegments(int32_t n, int32_t a, int32_t b, int32_t c) {
    JIntArray dp = jarray_new(java_add(n, INT32_C(1)));
    int32_t __value_1 = INT32_C(0);
    jarray_set(dp, INT32_C(0), __value_1);
    for (int32_t i = INT32_C(1); (i <= n); i = java_add(i, INT32_C(1))) {
        int32_t __value_2 = java_neg(INT32_C(1));
        jarray_set(dp, i, __value_2);
        if (((i >= a) && (jarray_get(dp, java_sub(i, a)) != java_neg(INT32_C(1))))) {
            int32_t __value_3 = java_max(jarray_get(dp, i), java_add(jarray_get(dp, java_sub(i, a)), INT32_C(1)));
            jarray_set(dp, i, __value_3);
        }
        if (((i == b) && (jarray_get(dp, java_sub(i, b)) != java_neg(INT32_C(1))))) {
            int32_t __value_4 = java_max(jarray_get(dp, i), java_add(jarray_get(dp, java_sub(i, b)), INT32_C(1)));
            jarray_set(dp, i, __value_4);
        }
        if (((i >= c) && (jarray_get(dp, java_sub(i, c)) != java_neg(INT32_C(1))))) {
            int32_t __value_5 = java_max(jarray_get(dp, i), java_add(jarray_get(dp, java_sub(i, c)), INT32_C(1)));
            jarray_set(dp, i, __value_5);
        }
    }
    return jarray_get(dp, n);
}
