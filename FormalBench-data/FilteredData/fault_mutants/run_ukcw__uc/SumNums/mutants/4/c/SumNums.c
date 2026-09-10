#include "java_arrays.h"
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>

static int32_t java_div(int32_t left, int32_t right) { if (right == 0) { fputs("JAVA_ARITHMETIC_ERROR: DIVIDE_BY_ZERO\n", stderr); exit(75); } if (left == INT32_MIN && right == -1) return INT32_MIN; return left / right; }

int32_t sumNums(int32_t x, int32_t y, int32_t m, int32_t n) {
    int32_t sum = java_div(x, y);
    if (((sum >= m) && (sum <= n))) {
        return INT32_C(20);
    } else {
        return sum;
    }
}
