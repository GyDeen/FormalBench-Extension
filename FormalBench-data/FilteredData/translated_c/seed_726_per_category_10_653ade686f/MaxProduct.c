#include "java_arrays.h"
#include <stdint.h>

static int32_t java_add(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left + (uint32_t)right);
}

static int32_t java_mul(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left * (uint32_t)right);
}

int32_t maxProduct(JIntArray arr, int32_t n) {
    JIntArray mpis = jarray_new(n);
    for (int32_t i = 0; i < n; i = java_add(i, 1)) {
        jarray_set(mpis, i, jarray_get(arr, i));
    }
    for (int32_t i = 1; i < n; i = java_add(i, 1)) {
        for (int32_t j = 0; j < i; j = java_add(j, 1)) {
            int32_t current = jarray_get(arr, i);
            int32_t previous = jarray_get(arr, j);
            int32_t product = java_mul(jarray_get(mpis, j), current);
            if (current > previous && jarray_get(mpis, i) < product) {
                jarray_set(mpis, i, product);
            }
        }
    }
    int32_t max = jarray_get(mpis, 0);
    for (int32_t i = 1; i < n; i = java_add(i, 1)) {
        if (jarray_get(mpis, i) > max) {
            max = jarray_get(mpis, i);
        }
    }
    return max;
}
