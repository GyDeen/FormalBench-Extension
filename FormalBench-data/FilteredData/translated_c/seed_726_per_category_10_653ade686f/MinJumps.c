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

int32_t minJumps(JIntArray arr, int32_t n) {
    JIntArray dp = jarray_new(n);
    int32_t length = jarray_length(dp);
    for (int32_t i = 0; i < length; i = java_add(i, 1)) {
        jarray_set(dp, i, INT32_MAX);
    }
    jarray_set(dp, 0, 0);

    for (int32_t i = 1; i < n; i = java_add(i, 1)) {
        for (int32_t j = 0; j < i; j = java_add(j, 1)) {
            int32_t reach = java_add(jarray_get(arr, j), j);
            if (reach >= i) {
                int32_t candidate = java_add(jarray_get(dp, j), 1);
                jarray_set(dp, i, java_min(jarray_get(dp, i), candidate));
            }
        }
    }
    return jarray_get(dp, java_sub(n, 1));
}
