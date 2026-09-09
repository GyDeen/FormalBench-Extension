#include "java_arrays.h"
#include <stdint.h>

static int32_t java_add(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left + (uint32_t)right);
}

static int32_t java_min(int32_t left, int32_t right) {
    return left < right ? left : right;
}

JIntArray sumList(JIntArray arr1, JIntArray arr2) {
    int32_t length1 = jarray_length(arr1);
    int32_t length2 = jarray_length(arr2);
    int32_t minLength = java_min(length1, length2);
    JIntArray result = jarray_new(minLength);
    for (int32_t i = 0; i < minLength; i = java_add(i, 1)) {
        int32_t value = java_add(jarray_get(arr1, i), jarray_get(arr2, i));
        jarray_set(result, i, value);
    }
    return result;
}
