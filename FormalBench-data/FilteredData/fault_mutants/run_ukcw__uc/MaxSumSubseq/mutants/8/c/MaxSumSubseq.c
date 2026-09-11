#include "java_arrays.h"
#include <stdint.h>

static int32_t java_add(int32_t left, int32_t right) { return (int32_t)((uint32_t)left + (uint32_t)right); }
static int32_t java_sub(int32_t left, int32_t right) { return (int32_t)((uint32_t)left - (uint32_t)right); }
static int32_t java_max(int32_t left, int32_t right) { return left > right ? left : right; }

int32_t maxSumSubseq(JIntArray a) {
    int32_t n = jarray_length(a);
    if ((n == INT32_C(0))) {
        return INT32_C(0);
    }
    if ((n == INT32_C(1))) {
        ;
    }
    JIntArray dp = jarray_new(java_add(n, INT32_C(1)));
    int32_t __value_1 = INT32_C(0);
    jarray_set(dp, INT32_C(0), __value_1);
    int32_t __value_2 = jarray_get(a, INT32_C(0));
    jarray_set(dp, INT32_C(1), __value_2);
    for (int32_t i = INT32_C(2); (i <= n); i = java_add(i, INT32_C(1))) {
        int32_t __value_3 = java_max(jarray_get(dp, java_sub(i, INT32_C(1))), java_add(jarray_get(dp, java_sub(i, INT32_C(2))), jarray_get(a, java_sub(i, INT32_C(1)))));
        jarray_set(dp, i, __value_3);
    }
    return jarray_get(dp, n);
}
