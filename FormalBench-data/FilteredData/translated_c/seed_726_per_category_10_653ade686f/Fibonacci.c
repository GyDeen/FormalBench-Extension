#include "java_arrays.h"
#include <stdint.h>

static int32_t java_sub(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left - (uint32_t)right);
}

static int32_t java_add(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left + (uint32_t)right);
}

int32_t fibonacci(int32_t n) {
    if (n == 0) {
        return 0;
    } else if (n == 1) {
        return 1;
    }
    int32_t left = fibonacci(java_sub(n, 1));
    int32_t right = fibonacci(java_sub(n, 2));
    return java_add(left, right);
}
