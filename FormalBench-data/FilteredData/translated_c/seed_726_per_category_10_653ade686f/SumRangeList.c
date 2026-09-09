#include "java_arrays.h"
#include <stdint.h>

static int32_t java_add(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left + (uint32_t)right);
}

int32_t sumRangeArray(JIntArray nums, int32_t m, int32_t n) {
    int32_t sum = 0;
    for (int32_t i = m; i <= n; i = java_add(i, 1)) {
        sum = java_add(sum, jarray_get(nums, i));
    }
    return sum;
}
