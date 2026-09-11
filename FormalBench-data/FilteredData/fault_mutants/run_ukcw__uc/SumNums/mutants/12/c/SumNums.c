#include "java_arrays.h"
#include <stdint.h>

static int32_t java_add(int32_t left, int32_t right) { return (int32_t)((uint32_t)left + (uint32_t)right); }

int32_t sumNums(int32_t x, int32_t y, int32_t m, int32_t n) {
    int32_t sum = java_add(x, y);
    if (false) {
        return INT32_C(20);
    } else {
        return sum;
    }
}
