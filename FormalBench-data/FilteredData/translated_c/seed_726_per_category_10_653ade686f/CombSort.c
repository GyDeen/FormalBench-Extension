#include "java_arrays.h"
#include <stdbool.h>
#include <stdint.h>

static int32_t java_add(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left + (uint32_t)right);
}

JIntArray combSort(JIntArray nums) {
    int32_t n = jarray_length(nums);
    int32_t gap = n;
    bool swapped = true;

    while (gap > 1 || swapped) {
        gap = (int32_t)((double)gap / 1.3);
        swapped = false;

        for (int32_t i = 0; java_add(i, gap) < n; i = java_add(i, 1)) {
            int32_t next = java_add(i, gap);
            int32_t left = jarray_get(nums, i);
            int32_t right = jarray_get(nums, next);
            if (left > right) {
                jarray_set(nums, i, right);
                jarray_set(nums, next, left);
                swapped = true;
            }
        }
    }
    return nums;
}
