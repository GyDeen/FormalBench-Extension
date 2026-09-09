#include "java_arrays.h"
#include <stdint.h>

static int32_t java_sub(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left - (uint32_t)right);
}

static int32_t java_add(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left + (uint32_t)right);
}

int32_t dealnnoyNum(int32_t n, int32_t m) {
    if (m == 0 || n == 0) {
        return 1;
    }
    int32_t first = dealnnoyNum(java_sub(m, 1), n);
    int32_t second = dealnnoyNum(java_sub(m, 1), java_sub(n, 1));
    int32_t third = dealnnoyNum(m, java_sub(n, 1));
    return java_add(java_add(first, second), third);
}
