#include "java_arrays.h"
#include <stdint.h>

static int32_t java_add(int32_t left, int32_t right) { return (int32_t)((uint32_t)left + (uint32_t)right); }
static int32_t java_sub(int32_t left, int32_t right) { return (int32_t)((uint32_t)left - (uint32_t)right); }

JIntArray countingSort(JIntArray myArray) {
    if ((jarray_length(myArray) == INT32_C(0))) {
        return jarray_new(INT32_C(0));
    }
    int32_t max = jarray_get(myArray, INT32_C(0));
    int32_t min = jarray_get(myArray, INT32_C(0));
    for (int32_t __index_1 = 0; __index_1 < jarray_length(myArray); __index_1 = java_add(__index_1, INT32_C(1))) {
        int32_t num = jarray_get(myArray, __index_1);
        if ((num > max)) {
            max = num;
        }
        if ((num < min)) {
            min = num;
        }
    }
    int32_t range = java_add(java_sub(max, min), INT32_C(1));
    JIntArray countArray = jarray_new(range);
    for (int32_t i = INT32_C(0); (i < jarray_length(myArray)); i = java_add(i, INT32_C(1))) {
        jarray_set(countArray, java_sub(jarray_get(myArray, i), min), java_add(jarray_get(countArray, java_sub(jarray_get(myArray, i), min)), INT32_C(1)));
    }
    int32_t index = INT32_C(0);
    JIntArray result = jarray_new(jarray_length(myArray));
    for (int32_t i = INT32_C(0); (i < jarray_length(countArray)); i = java_add(i, INT32_C(1))) {
        for (int32_t j = INT32_C(0); (j != jarray_get(countArray, i)); j = java_add(j, INT32_C(1))) {
            int32_t __index_2 = index;
            index = java_add(index, INT32_C(1));
            int32_t __value_3 = java_add(i, min);
            jarray_set(result, __index_2, __value_3);
        }
    }
    return result;
}
