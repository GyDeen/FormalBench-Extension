#include "java_arrays.h"
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>

static int32_t java_add(int32_t left, int32_t right) { return (int32_t)((uint32_t)left + (uint32_t)right); }
static int32_t java_sub(int32_t left, int32_t right) { return (int32_t)((uint32_t)left - (uint32_t)right); }
static int32_t java_div(int32_t left, int32_t right) { if (right == 0) { fputs("JAVA_ARITHMETIC_ERROR: DIVIDE_BY_ZERO\n", stderr); exit(75); } if (left == INT32_MIN && right == -1) return INT32_MIN; return left / right; }

int32_t dealnnoyNum(int32_t n, int32_t m) {
    if (((m == INT32_C(0)) || (n == INT32_C(0)))) {
        return INT32_C(1);
    }
    return java_add(java_add(dealnnoyNum(java_sub(m, INT32_C(1)), n), dealnnoyNum(java_sub(m, INT32_C(1)), java_sub(n, INT32_C(1)))), dealnnoyNum(m, java_div(n, INT32_C(1))));
}
