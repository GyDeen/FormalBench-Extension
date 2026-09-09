#include "java_arrays.h"
#include <stdint.h>

static int32_t java_add(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left + (uint32_t)right);
}

static int32_t java_sub(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left - (uint32_t)right);
}

static int32_t java_min(int32_t left, int32_t right) {
    return left < right ? left : right;
}

int32_t minCost(JIntArray2 cost, int32_t m, int32_t n) {
    int32_t rows = java_add(m, 1);
    int32_t columns = java_add(n, 1);
    JIntArray2 tc = jarray2_new(rows, columns);

    jarray_set(jarray2_get(tc, 0), 0,
               jarray_get(jarray2_get(cost, 0), 0));
    for (int32_t i = 1; i <= m; i = java_add(i, 1)) {
        int32_t value = java_add(
            jarray_get(jarray2_get(tc, java_sub(i, 1)), 0),
            jarray_get(jarray2_get(cost, i), 0));
        jarray_set(jarray2_get(tc, i), 0, value);
    }
    for (int32_t j = 1; j <= n; j = java_add(j, 1)) {
        int32_t value = java_add(
            jarray_get(jarray2_get(tc, 0), java_sub(j, 1)),
            jarray_get(jarray2_get(cost, 0), j));
        jarray_set(jarray2_get(tc, 0), j, value);
    }
    for (int32_t i = 1; i <= m; i = java_add(i, 1)) {
        for (int32_t j = 1; j <= n; j = java_add(j, 1)) {
            int32_t diagonal = jarray_get(jarray2_get(tc, java_sub(i, 1)), java_sub(j, 1));
            int32_t above = jarray_get(jarray2_get(tc, java_sub(i, 1)), j);
            int32_t left = jarray_get(jarray2_get(tc, i), java_sub(j, 1));
            int32_t best = java_min(diagonal, java_min(above, left));
            int32_t value = java_add(best, jarray_get(jarray2_get(cost, i), j));
            jarray_set(jarray2_get(tc, i), j, value);
        }
    }
    return jarray_get(jarray2_get(tc, m), n);
}
