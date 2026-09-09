#include "java_arrays.h"
#include <stdint.h>

static int32_t java_add(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left + (uint32_t)right);
}

static int32_t java_sub(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left - (uint32_t)right);
}

JIntArray2 pairWise(JIntArray l1) {
    int32_t length = jarray_length(l1);
    if (length < 2) {
        return jarray2_new(0, 0);
    }
    JIntArray2 result = jarray2_new(java_sub(length, 1), 2);
    int32_t limit = java_sub(length, 1);
    for (int32_t i = 0; i < limit; i = java_add(i, 1)) {
        JIntArray row = jarray2_get(result, i);
        jarray_set(row, 0, jarray_get(l1, i));
        jarray_set(row, 1, jarray_get(l1, java_add(i, 1)));
    }
    return result;
}
