#include "java_arrays.h"
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>

static int32_t java_sub(int32_t left, int32_t right) { return (int32_t)((uint32_t)left - (uint32_t)right); }
static int32_t java_mul(int32_t left, int32_t right) { return (int32_t)((uint32_t)left * (uint32_t)right); }
static int32_t java_div(int32_t left, int32_t right) { if (right == 0) { fputs("JAVA_ARITHMETIC_ERROR: DIVIDE_BY_ZERO\n", stderr); exit(75); } if (left == INT32_MIN && right == -1) return INT32_MIN; return left / right; }

int32_t hexagonalNum(int32_t n) {
    int32_t ans = java_mul(n, java_sub(java_div(INT32_C(2), n), INT32_C(1)));
    return ans;
}
