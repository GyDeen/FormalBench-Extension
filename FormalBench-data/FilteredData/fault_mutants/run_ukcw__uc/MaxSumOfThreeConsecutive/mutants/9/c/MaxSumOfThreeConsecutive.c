#include "java_arrays.h"
#include <stdint.h>

static int32_t java_add(int32_t left, int32_t right) { return (int32_t)((uint32_t)left + (uint32_t)right); }
static int32_t java_sub(int32_t left, int32_t right) { return (int32_t)((uint32_t)left - (uint32_t)right); }
static int32_t java_mul(int32_t left, int32_t right) { return (int32_t)((uint32_t)left * (uint32_t)right); }
static int32_t java_max(int32_t left, int32_t right) { return left > right ? left : right; }

int32_t maxSumOfThreeConsecutive(JIntArray arr, int32_t n) {
    JIntArray sum = jarray_new(n);
    if ((n >= INT32_C(1))) {
        int32_t __value_1 = jarray_get(arr, INT32_C(0));
        jarray_set(sum, INT32_C(0), __value_1);
    }
    if ((n >= INT32_C(2))) {
        int32_t __value_2 = java_mul(jarray_get(sum, INT32_C(0)), jarray_get(arr, INT32_C(1)));
        jarray_set(sum, INT32_C(1), __value_2);
    }
    if ((n > INT32_C(2))) {
        int32_t __value_3 = java_max(jarray_get(sum, INT32_C(1)), java_max(java_add(jarray_get(arr, INT32_C(1)), jarray_get(arr, INT32_C(2))), java_add(jarray_get(arr, INT32_C(0)), jarray_get(arr, INT32_C(2)))));
        jarray_set(sum, INT32_C(2), __value_3);
    }
    for (int32_t i = INT32_C(3); (i < n); i = java_add(i, INT32_C(1))) {
        int32_t __value_4 = java_max(java_max(jarray_get(sum, java_sub(i, INT32_C(1))), java_add(jarray_get(sum, java_sub(i, INT32_C(2))), jarray_get(arr, i))), java_add(java_add(jarray_get(arr, i), jarray_get(arr, java_sub(i, INT32_C(1)))), jarray_get(sum, java_sub(i, INT32_C(3)))));
        jarray_set(sum, i, __value_4);
    }
    return jarray_get(sum, java_sub(n, INT32_C(1)));
}
