#include "java_arrays.h"
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>

static int32_t java_add(int32_t left, int32_t right) { return (int32_t)((uint32_t)left + (uint32_t)right); }
static int32_t java_sub(int32_t left, int32_t right) { return (int32_t)((uint32_t)left - (uint32_t)right); }
static int32_t java_div(int32_t left, int32_t right) { if (right == 0) { fputs("JAVA_ARITHMETIC_ERROR: DIVIDE_BY_ZERO\n", stderr); exit(75); } if (left == INT32_MIN && right == -1) return INT32_MIN; return left / right; }

JIntArray moveFirst(JIntArray testArray) {
    if ((jarray_is_null(testArray) || (jarray_length(testArray) == INT32_C(0)))) {
        return testArray;
    }
    JIntArray res = jarray_new(jarray_length(testArray));
    int32_t __value_1 = jarray_get(testArray, java_sub(jarray_length(testArray), INT32_C(1)));
    jarray_set(res, INT32_C(0), __value_1);
    for (int32_t __copy_index_2 = 0; __copy_index_2 < java_div(jarray_length(testArray), INT32_C(1)); __copy_index_2 = java_add(__copy_index_2, INT32_C(1))) {
        jarray_set(res, java_add(INT32_C(1), __copy_index_2), jarray_get(testArray, java_add(INT32_C(0), __copy_index_2)));
    }
    return res;
}
