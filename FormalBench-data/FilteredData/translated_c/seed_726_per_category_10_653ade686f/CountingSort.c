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

static int32_t java_min(int32_t left, int32_t right) {
    return left < right ? left : right;
}

JIntArray countingSort(JIntArray myArray) {
    int32_t length = jarray_length(myArray);
    if (length == 0) {
        return jarray_new(0);
    }

    int32_t max = jarray_get(myArray, 0);
    int32_t min = jarray_get(myArray, 0);
    for (int32_t i = 0; i < length; i = java_add(i, 1)) {
        int32_t num = jarray_get(myArray, i);
        max = java_max(max, num);
        min = java_min(min, num);
    }

    int32_t range = java_add(java_sub(max, min), 1);
    JIntArray countArray = jarray_new(range);
    for (int32_t i = 0; i < length; i = java_add(i, 1)) {
        int32_t slot = java_sub(jarray_get(myArray, i), min);
        int32_t old_count = jarray_get(countArray, slot);
        jarray_set(countArray, slot, java_add(old_count, 1));
    }

    int32_t index = 0;
    JIntArray result = jarray_new(length);
    int32_t countLength = jarray_length(countArray);
    for (int32_t i = 0; i < countLength; i = java_add(i, 1)) {
        for (int32_t j = 0; j < jarray_get(countArray, i); j = java_add(j, 1)) {
            jarray_set(result, index, java_add(i, min));
            index = java_add(index, 1);
        }
    }
    return result;
}
