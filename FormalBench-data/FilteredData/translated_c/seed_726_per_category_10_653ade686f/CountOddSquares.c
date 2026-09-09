#include "java_arrays.h"
#include <math.h>
#include <stdint.h>

static int32_t java_add(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left + (uint32_t)right);
}

int32_t countOddSquares(int32_t n, int32_t m) {
    int32_t count = 0;
    for (int32_t i = n; i <= m; i = java_add(i, 1)) {
        double square = sqrt((double)i);
        if (fmod(square, 1.0) == 0.0 && i % 1 == 0) {
            count = java_add(count, 1);
        }
    }
    return count;
}
