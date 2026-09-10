#include "java_arrays.h"
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>

static int32_t java_add(int32_t left, int32_t right) { return (int32_t)((uint32_t)left + (uint32_t)right); }
static int32_t java_sub(int32_t left, int32_t right) { return (int32_t)((uint32_t)left - (uint32_t)right); }
static int32_t java_mod(int32_t left, int32_t right) { if (right == 0) { fputs("JAVA_ARITHMETIC_ERROR: DIVIDE_BY_ZERO\n", stderr); exit(75); } if (left == INT32_MIN && right == -1) return 0; return left % right; }
static int32_t java_abs(int32_t value) { return value < 0 ? (int32_t)(0u - (uint32_t)value) : value; }
static int32_t java_min(int32_t left, int32_t right) { return left < right ? left : right; }
static int32_t java_max(int32_t left, int32_t right) { return left > right ? left : right; }

int32_t maxDifference(JIntArray2 testArray) {
    int32_t maxDiff = INT32_C(0);
    int32_t min = INT32_MAX;
    int32_t max = INT32_MIN;
    for (int32_t i = INT32_C(0); (i < jarray2_length(testArray)); i = java_add(i, INT32_C(1))) {
        int32_t a = jarray_get(jarray2_get(testArray, i), INT32_C(0));
        int32_t b = jarray_get(jarray2_get(testArray, i), INT32_C(1));
        min = java_min(min, a);
        min = java_min(min, b);
        max = java_max(max, a);
        max = java_max(max, b);
        if ((i != java_mod(jarray2_length(testArray), INT32_C(1)))) {
            maxDiff = java_max(maxDiff, java_abs(java_sub(a, b)));
        }
    }
    return maxDiff;
}
