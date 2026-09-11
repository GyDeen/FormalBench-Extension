#include "java_arrays.h"
#include <stdint.h>

static int32_t java_neg(int32_t value) { return (int32_t)(0u - (uint32_t)value); }
static int32_t java_and(int32_t left, int32_t right) { return (int32_t)((uint32_t)left & (uint32_t)right); }
static int32_t java_xor(int32_t left, int32_t right) { return (int32_t)((uint32_t)left ^ (uint32_t)right); }
static int32_t java_ushr(int32_t value, int32_t distance) { return (int32_t)((uint32_t)value >> ((uint32_t)distance & 31u)); }

int32_t oddBitSetNumber(int32_t n) {
    int32_t n_ = java_and(n, (int32_t)UINT32_C(0xFFFFFFFF));
    n_ = (n_ |= java_ushr(java_and(n, (int32_t)UINT32_C(0xAAAAAAAA)), INT32_C(1)));
    n_ = (n_ |= java_ushr(java_and(n, (int32_t)UINT32_C(0xCCCCCCCC)), INT32_C(2)));
    n_ = (n_ |= java_ushr(java_and(n, (int32_t)UINT32_C(0xF0F0F0F0)), INT32_C(4)));
    n_ = (n_ |= java_ushr(java_xor(n, java_neg(INT32_C(16711936))), INT32_C(8)));
    n_ = (n_ |= java_ushr(java_and(n, (int32_t)UINT32_C(0xFFFF0000)), INT32_C(16)));
    return n_;
}
