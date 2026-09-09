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

int32_t maximumSegments(int32_t n, int32_t a, int32_t b, int32_t c) {
    JIntArray dp = jarray_new(java_add(n, 1));
    jarray_set(dp, 0, 0);
    for (int32_t i = 1; i <= n; i = java_add(i, 1)) {
        jarray_set(dp, i, -1);
        if (i >= a && jarray_get(dp, java_sub(i, a)) != -1) {
            jarray_set(dp, i, java_max(jarray_get(dp, i),
                                       java_add(jarray_get(dp, java_sub(i, a)), 1)));
        }
        if (i >= b && jarray_get(dp, java_sub(i, b)) != -1) {
            jarray_set(dp, i, java_max(jarray_get(dp, i),
                                       java_add(jarray_get(dp, java_sub(i, b)), 1)));
        }
        if (i >= c && jarray_get(dp, java_sub(i, c)) != -1) {
            jarray_set(dp, i, java_max(jarray_get(dp, i),
                                       java_add(jarray_get(dp, java_sub(i, c)), 1)));
        }
    }
    return jarray_get(dp, n);
}
