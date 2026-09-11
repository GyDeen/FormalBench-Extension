#include "java_arrays.h"
#include <stdint.h>

static int32_t java_add(int32_t left, int32_t right) { return (int32_t)((uint32_t)left + (uint32_t)right); }
static int32_t java_mul(int32_t left, int32_t right) { return (int32_t)((uint32_t)left * (uint32_t)right); }

int32_t hexagonalNum(int32_t n) {
    int32_t ans = java_mul(n, java_add(java_mul(INT32_C(2), n), INT32_C(1)));
    return ans;
}
