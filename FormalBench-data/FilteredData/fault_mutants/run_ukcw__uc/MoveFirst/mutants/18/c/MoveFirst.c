#include "java_arrays.h"
#include <stdint.h>

static int32_t java_sub(int32_t left, int32_t right) { return (int32_t)((uint32_t)left - (uint32_t)right); }

JIntArray moveFirst(JIntArray testArray) {
    if ((jarray_is_null(testArray) || (jarray_length(testArray) == INT32_C(0)))) {
        return testArray;
    }
    JIntArray res = jarray_new(jarray_length(testArray));
    int32_t __value_1 = jarray_get(testArray, java_sub(jarray_length(testArray), INT32_C(1)));
    jarray_set(res, INT32_C(0), __value_1);
    ;
    return res;
}
