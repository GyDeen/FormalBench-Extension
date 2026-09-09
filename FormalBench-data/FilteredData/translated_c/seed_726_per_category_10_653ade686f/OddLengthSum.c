#include "java_arrays.h"
#include <stdint.h>

static int32_t java_add(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left + (uint32_t)right);
}

static int32_t java_sub(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left - (uint32_t)right);
}

static int32_t java_mul(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left * (uint32_t)right);
}

static int32_t java_div(int32_t dividend, int32_t divisor) {
    if (dividend == INT32_MIN && divisor == -1) {
        return INT32_MIN;
    }
    return dividend / divisor;
}

int32_t oddLengthSum(JIntArray arr) {
    int32_t sum = 0;
    int32_t l = jarray_length(arr);
    for (int32_t i = 0; i < l; i = java_add(i, 1)) {
        int32_t count = java_add(
            java_mul(java_add(i, 1), java_sub(l, i)), 1);
        int32_t occurrences = java_div(count, 2);
        sum = java_add(sum, java_mul(occurrences, jarray_get(arr, i)));
    }
    return sum;
}
