#include "java_arrays.h"
#include <stdint.h>

static int32_t java_mul(int32_t left, int32_t right) { return (int32_t)((uint32_t)left * (uint32_t)right); }

int32_t findRectNum(int32_t n) {
    return java_mul(n, java_mul(n, INT32_C(1)));
}
