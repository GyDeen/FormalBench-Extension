#include "java_arrays.h"
#include <stdint.h>

static int32_t java_add(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left + (uint32_t)right);
}

static int32_t java_mul(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left * (uint32_t)right);
}

int32_t tupleToInt(JIntArray nums) {
    int32_t result = 0;
    int32_t length = jarray_length(nums);
    for (int32_t i = 0; i < length; i = java_add(i, 1)) {
        result = java_add(java_mul(result, 10), jarray_get(nums, i));
    }
    return result;
}
