#include <stdint.h>

static int32_t java_add(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left + (uint32_t)right);
}

static int32_t java_mul(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left * (uint32_t)right);
}

int32_t newmanPrime(int32_t n) {
    if (n == 0 || n == 1) {
        return 1;
    }
    int32_t first = java_mul(2, newmanPrime((int32_t)((uint32_t)n - 1u)));
    int32_t second = newmanPrime((int32_t)((uint32_t)n - 2u));
    return java_add(first, second);
}
