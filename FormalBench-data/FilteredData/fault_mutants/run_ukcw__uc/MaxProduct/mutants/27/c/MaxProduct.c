#include "java_arrays.h"
#include <stdint.h>

static int32_t java_add(int32_t left, int32_t right) { return (int32_t)((uint32_t)left + (uint32_t)right); }
static int32_t java_sub(int32_t left, int32_t right) { return (int32_t)((uint32_t)left - (uint32_t)right); }
static int32_t java_mul(int32_t left, int32_t right) { return (int32_t)((uint32_t)left * (uint32_t)right); }

int32_t maxProduct(JIntArray arr, int32_t n) {
    JIntArray mpis = jarray_new(n);
    for (int32_t i = INT32_C(0); (i < n); i = java_add(i, INT32_C(1))) {
        int32_t __value_1 = jarray_get(arr, i);
        jarray_set(mpis, i, __value_1);
    }
    for (int32_t i = INT32_C(1); (i < n); i = java_add(i, INT32_C(1))) {
        for (int32_t j = INT32_C(0); (j < i); j = java_add(j, INT32_C(1))) {
            if (((jarray_get(arr, i) > jarray_get(arr, j)) && (jarray_get(mpis, i) < java_mul(jarray_get(mpis, j), jarray_get(arr, i))))) {
                int32_t __value_2 = java_sub(jarray_get(mpis, j), jarray_get(arr, i));
                jarray_set(mpis, i, __value_2);
            }
        }
    }
    int32_t max = jarray_get(mpis, INT32_C(0));
    for (int32_t i = INT32_C(1); (i < n); i = java_add(i, INT32_C(1))) {
        if ((jarray_get(mpis, i) > max)) {
            max = jarray_get(mpis, i);
        }
    }
    return max;
}
