#include "java_arrays.h"
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>

static int32_t java_add(int32_t left, int32_t right) { return (int32_t)((uint32_t)left + (uint32_t)right); }
static int32_t java_mul(int32_t left, int32_t right) { return (int32_t)((uint32_t)left * (uint32_t)right); }
static int32_t java_mod(int32_t left, int32_t right) { if (right == 0) { fputs("JAVA_ARITHMETIC_ERROR: DIVIDE_BY_ZERO\n", stderr); exit(75); } if (left == INT32_MIN && right == -1) return 0; return left % right; }

int32_t tupleToInt(JIntArray nums) {
    int32_t result = INT32_C(0);
    for (int32_t __index_1 = 0; __index_1 < jarray_length(nums); __index_1 = java_add(__index_1, INT32_C(1))) {
        int32_t num = jarray_get(nums, __index_1);
        result = java_mod(java_mul(result, INT32_C(10)), num);
    }
    return result;
}
