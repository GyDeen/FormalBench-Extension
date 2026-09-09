#include "java_arrays.h"
#include <stdint.h>

static int32_t java_add(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left + (uint32_t)right);
}

static int32_t java_sub(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left - (uint32_t)right);
}

static int32_t java_max(int32_t left, int32_t right) {
    return left > right ? left : right;
}

int32_t maxSumSubseq(JIntArray a) {
    int32_t n = jarray_length(a);
    if (n == 0) return 0;
    if (n == 1) return jarray_get(a, 0);

    JIntArray dp = jarray_new(java_add(n, 1));
    jarray_set(dp, 0, 0);
    jarray_set(dp, 1, jarray_get(a, 0));
    for (int32_t i = 2; i <= n; i = java_add(i, 1)) {
        int32_t skip = jarray_get(dp, java_sub(i, 1));
        int32_t take = java_add(jarray_get(dp, java_sub(i, 2)),
                                jarray_get(a, java_sub(i, 1)));
        jarray_set(dp, i, java_max(skip, take));
    }
    return jarray_get(dp, n);
}
