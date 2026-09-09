#include "java_arrays.h"
#include <stdint.h>

static int32_t java_add(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left + (uint32_t)right);
}

static int32_t java_sub(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left - (uint32_t)right);
}

static int32_t java_mul(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left * (uint32_t)right);
}

int32_t countWays(int32_t n) {
    JIntArray A = jarray_new(java_add(n, 1));
    JIntArray B = jarray_new(java_add(n, 1));
    jarray_set(A, 0, 1);
    jarray_set(A, 1, 0);
    jarray_set(B, 0, 0);
    jarray_set(B, 1, 1);
    for (int32_t i = 2; i <= n; i = java_add(i, 1)) {
        int32_t a_previous = jarray_get(A, java_sub(i, 2));
        int32_t b_previous = jarray_get(B, java_sub(i, 1));
        int32_t a_value = java_add(a_previous, java_mul(2, b_previous));
        int32_t b_value = java_add(jarray_get(A, java_sub(i, 1)),
                                   jarray_get(B, java_sub(i, 2)));
        jarray_set(A, i, a_value);
        jarray_set(B, i, b_value);
    }
    return jarray_get(A, n);
}
