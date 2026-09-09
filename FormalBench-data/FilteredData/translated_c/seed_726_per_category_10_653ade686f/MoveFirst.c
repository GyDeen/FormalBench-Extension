#include "java_arrays.h"
#include <stdint.h>

static int32_t java_add(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left + (uint32_t)right);
}

static int32_t java_sub(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left - (uint32_t)right);
}

JIntArray moveFirst(JIntArray testArray) {
    if (jarray_is_null(testArray) || jarray_length(testArray) == 0) {
        return testArray;
    }

    int32_t length = jarray_length(testArray);
    JIntArray res = jarray_new(length);
    jarray_set(res, 0, jarray_get(testArray, java_sub(length, 1)));
    int32_t copyLength = java_sub(length, 1);
    for (int32_t i = 0; i < copyLength; i = java_add(i, 1)) {
        jarray_set(res, java_add(i, 1), jarray_get(testArray, i));
    }
    return res;
}
