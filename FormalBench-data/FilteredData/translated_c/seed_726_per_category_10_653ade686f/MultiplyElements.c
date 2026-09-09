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

JIntArray multiplyElements(JIntArray testTup) {
    int32_t length = jarray_length(testTup);
    if (length < 2) {
        return jarray_new(0);
    }
    JIntArray result = jarray_new(java_sub(length, 1));
    int32_t limit = java_sub(length, 1);
    for (int32_t i = 0; i < limit; i = java_add(i, 1)) {
        jarray_set(result, i,
                   java_mul(jarray_get(testTup, i), jarray_get(testTup, java_add(i, 1))));
    }
    return result;
}
