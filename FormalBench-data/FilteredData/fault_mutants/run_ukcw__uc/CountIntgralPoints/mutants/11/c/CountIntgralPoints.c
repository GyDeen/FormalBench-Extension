#include "java_arrays.h"
#include <stdint.h>

static int32_t java_add(int32_t left, int32_t right) { return (int32_t)((uint32_t)left + (uint32_t)right); }
static int32_t java_sub(int32_t left, int32_t right) { return (int32_t)((uint32_t)left - (uint32_t)right); }
static int32_t java_mul(int32_t left, int32_t right) { return (int32_t)((uint32_t)left * (uint32_t)right); }

int32_t countIntgralPoints(int32_t x1, int32_t y1, int32_t x2, int32_t y2) {
    return java_mul(java_sub(java_sub(y2, y1), INT32_C(1)), java_sub(java_add(x2, x1), INT32_C(1)));
}
