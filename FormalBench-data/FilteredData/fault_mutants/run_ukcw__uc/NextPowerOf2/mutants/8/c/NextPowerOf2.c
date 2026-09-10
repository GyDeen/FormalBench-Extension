#include "java_arrays.h"
#include <stdint.h>

static int32_t java_shr(int32_t value, int32_t distance) { return value >> ((uint32_t)distance & 31u); }

int32_t nextPowerOf2(int32_t n) {
    if ((n == INT32_C(0))) {
        return INT32_C(1);
    }
    int32_t i = INT32_C(1);
    while ((i < n)) {
        i = java_shr(i, INT32_C(1));
    }
    return i;
}
