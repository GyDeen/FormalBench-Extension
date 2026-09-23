#include "java_arrays.h"
#include <stdint.h>

static int32_t java_add(int32_t left, int32_t right) { return (int32_t)((uint32_t)left + (uint32_t)right); }
static int32_t java_sub(int32_t left, int32_t right) { return (int32_t)((uint32_t)left - (uint32_t)right); }
static int32_t java_mul(int32_t left, int32_t right) { return (int32_t)((uint32_t)left * (uint32_t)right); }

int32_t newmanPrime(int32_t n) {
    if (((n >= INT32_C(0)) || (n == INT32_C(1)))) {
        return INT32_C(1);
    }
    return java_add(java_mul(INT32_C(2), newmanPrime(java_sub(n, INT32_C(1)))), newmanPrime(java_sub(n, INT32_C(2))));
}
