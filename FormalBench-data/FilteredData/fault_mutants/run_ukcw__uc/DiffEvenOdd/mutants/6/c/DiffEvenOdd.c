#include "java_arrays.h"
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>

static int32_t java_add(int32_t left, int32_t right) { return (int32_t)((uint32_t)left + (uint32_t)right); }
static int32_t java_sub(int32_t left, int32_t right) { return (int32_t)((uint32_t)left - (uint32_t)right); }
static int32_t java_mod(int32_t left, int32_t right) { if (right == 0) { fputs("JAVA_ARITHMETIC_ERROR: DIVIDE_BY_ZERO\n", stderr); exit(75); } if (left == INT32_MIN && right == -1) return 0; return left % right; }
static int32_t java_neg(int32_t value) { return (int32_t)(0u - (uint32_t)value); }

int32_t diffEvenOdd(JIntArray array) {
    int32_t firstEven = java_neg(INT32_C(1));
    int32_t firstOdd = java_neg(INT32_C(1));
    for (int32_t __index_1 = 0; __index_1 < jarray_length(array); __index_1 = java_add(__index_1, INT32_C(1))) {
        int32_t num = jarray_get(array, __index_1);
        if (((firstEven == java_neg(INT32_C(1))) && (java_sub(num, INT32_C(2)) == INT32_C(0)))) {
            firstEven = num;
        }
        if (((firstOdd == java_neg(INT32_C(1))) && (java_mod(num, INT32_C(2)) != INT32_C(0)))) {
            firstOdd = num;
        }
        if (((firstEven != java_neg(INT32_C(1))) && (firstOdd != java_neg(INT32_C(1))))) {
            break;
        }
    }
    return java_sub(firstEven, firstOdd);
}
