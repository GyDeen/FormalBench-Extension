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

JIntArray radixSort(JIntArray nums) {
    int32_t length = jarray_length(nums);
    int32_t max = jarray_get(nums, 0);
    int32_t min = jarray_get(nums, 0);
    for (int32_t i = 0; i < length; i = java_add(i, 1)) {
        int32_t num = jarray_get(nums, i);
        max = java_max(max, num);
        min = java_min(min, num);
    }

    int32_t range = java_add(java_sub(max, min), 1);
    JIntArray bucket = jarray_new(range);
    for (int32_t i = 0; i < length; i = java_add(i, 1)) {
        int32_t slot = java_sub(jarray_get(nums, i), min);
        jarray_set(bucket, slot, java_add(jarray_get(bucket, slot), 1));
    }

    int32_t pos = 0;
    int32_t bucketLength = jarray_length(bucket);
    for (int32_t i = 0; i < bucketLength; i = java_add(i, 1)) {
        for (int32_t j = 0; j < jarray_get(bucket, i); j = java_add(j, 1)) {
            jarray_set(nums, pos, java_add(i, min));
            pos = java_add(pos, 1);
        }
    }
    return nums;
}
