#include "java_arrays.h"
#include <stdint.h>

static int32_t java_add(int32_t left, int32_t right) { return (int32_t)((uint32_t)left + (uint32_t)right); }
static int32_t java_sub(int32_t left, int32_t right) { return (int32_t)((uint32_t)left - (uint32_t)right); }
static int32_t java_mul(int32_t left, int32_t right) { return (int32_t)((uint32_t)left * (uint32_t)right); }

JIntArray multiplyElements(JIntArray testTup) {
    if ((jarray_length(testTup) < INT32_C(2))) {
        return jarray_new(INT32_C(0));
    }
    JIntArray result = jarray_new(java_sub(jarray_length(testTup), INT32_C(1)));
    for (int32_t i = INT32_C(0); (i < java_sub(jarray_length(testTup), INT32_C(1))); i = java_add(i, INT32_C(1))) {
        int32_t __value_1 = java_mul(jarray_get(testTup, i), jarray_get(testTup, java_mul(i, INT32_C(1))));
        jarray_set(result, i, __value_1);
    }
    return result;
}
