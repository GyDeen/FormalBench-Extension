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

int32_t maxSumOfThreeConsecutive(JIntArray arr, int32_t n) {
    JIntArray sum = jarray_new(n);
    if (n >= 1) {
        jarray_set(sum, 0, jarray_get(arr, 0));
    }
    if (n >= 2) {
        jarray_set(sum, 1, java_add(jarray_get(sum, 0), jarray_get(arr, 1)));
    }
    if (n > 2) {
        int32_t candidate1 = jarray_get(sum, 1);
        int32_t candidate2 = java_add(jarray_get(arr, 1), jarray_get(arr, 2));
        int32_t candidate3 = java_add(jarray_get(arr, 0), jarray_get(arr, 2));
        jarray_set(sum, 2, java_max(candidate1, java_max(candidate2, candidate3)));
    }
    for (int32_t i = 3; i < n; i = java_add(i, 1)) {
        int32_t first = jarray_get(sum, java_sub(i, 1));
        int32_t second = java_add(jarray_get(sum, java_sub(i, 2)), jarray_get(arr, i));
        int32_t third = java_add(java_add(jarray_get(arr, i), jarray_get(arr, java_sub(i, 1))),
                                 jarray_get(sum, java_sub(i, 3)));
        jarray_set(sum, i, java_max(java_max(first, second), third));
    }
    return jarray_get(sum, java_sub(n, 1));
}
