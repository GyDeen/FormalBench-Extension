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

int32_t findPeak(JIntArray arr, int32_t n) {
    int32_t left = 0;
    int32_t right = java_sub(n, 1);
    while (left < right) {
        int32_t mid = java_add(left, java_div(java_sub(right, left), 2));
        int32_t next = java_add(mid, 1);
        if (jarray_get(arr, mid) < jarray_get(arr, next)) {
            left = next;
        } else {
            right = mid;
        }
    }
    return left;
}
