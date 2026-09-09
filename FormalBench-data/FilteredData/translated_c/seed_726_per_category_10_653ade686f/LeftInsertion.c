#include "java_arrays.h"
#include <stdint.h>

static int32_t java_add(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left + (uint32_t)right);
}

static int32_t java_sub(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left - (uint32_t)right);
}

static int32_t java_div(int32_t dividend, int32_t divisor) {
    if (dividend == INT32_MIN && divisor == -1) {
        return INT32_MIN;
    }
    return dividend / divisor;
}

int32_t leftInsertion(JIntArray a, int32_t x) {
    int32_t left = 0;
    int32_t right = java_sub(jarray_length(a), 1);
    while (left <= right) {
        int32_t mid = java_div(java_add(left, right), 2);
        int32_t value = jarray_get(a, mid);
        if (value == x) {
            return mid;
        } else if (value < x) {
            left = java_add(mid, 1);
        } else {
            right = java_sub(mid, 1);
        }
    }
    return left;
}
