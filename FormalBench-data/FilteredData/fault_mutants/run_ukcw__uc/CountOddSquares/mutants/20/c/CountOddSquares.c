#include "java_arrays.h"
#include <math.h>
#include <stdint.h>

static int32_t java_add(int32_t left, int32_t right) { return (int32_t)((uint32_t)left + (uint32_t)right); }

int32_t countOddSquares(int32_t n, int32_t m) {
    int32_t count = INT32_C(0);
    for (int32_t i = n; (i <= m); i = java_add(i, INT32_C(1))) {
        double square = sqrt(i);
        if ((fmod(square, INT32_C(1)) == INT32_C(0))) {
            count = java_add(count, INT32_C(1));
        }
    }
    return count;
}
