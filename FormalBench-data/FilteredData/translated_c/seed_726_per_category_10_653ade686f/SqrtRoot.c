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

int32_t sqrtRoot(int32_t num) {
    if (num < 0) {
        return -1;
    }
    int32_t left = 0;
    int32_t right = num;
    while (left <= right) {
        int32_t mid = java_add(left, java_div(java_sub(right, left), 2));
        int32_t square = java_mul(mid, mid);
        if (square == num) {
            return mid;
        } else if (square < num) {
            left = java_add(mid, 1);
        } else {
            right = java_sub(mid, 1);
        }
    }
    return right;
}
