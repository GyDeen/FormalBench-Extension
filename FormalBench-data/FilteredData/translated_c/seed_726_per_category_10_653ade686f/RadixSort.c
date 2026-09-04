#include <stddef.h>
#include <stdint.h>
#include <stdlib.h>

typedef struct {
    int32_t *data;
    size_t length;
} Int32Array;

static int32_t java_sub(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left - (uint32_t)right);
}

static int32_t java_add(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left + (uint32_t)right);
}

Int32Array radixSort(Int32Array nums) {
    if (nums.length == 0) {
        return nums;
    }

    int32_t max = nums.data[0];
    int32_t min = nums.data[0];
    for (size_t i = 0; i < nums.length; i++) {
        int32_t num = nums.data[i];
        if (num > max) max = num;
        if (num < min) min = num;
    }

    int32_t range = java_add(java_sub(max, min), 1);
    if (range <= 0) {
        return nums;
    }
    int32_t *bucket = calloc((size_t)range, sizeof(*bucket));
    if (bucket == NULL) {
        return nums;
    }
    for (size_t i = 0; i < nums.length; i++) {
        int32_t index = java_sub(nums.data[i], min);
        bucket[index] = java_add(bucket[index], 1);
    }

    size_t pos = 0;
    for (int32_t i = 0; i < range; i++) {
        for (int32_t j = 0; j < bucket[i]; j++) {
            nums.data[pos++] = java_add(i, min);
        }
    }
    free(bucket);
    return nums;
}
