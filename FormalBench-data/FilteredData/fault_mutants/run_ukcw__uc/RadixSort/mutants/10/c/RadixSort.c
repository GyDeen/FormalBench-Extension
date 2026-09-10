#include "java_arrays.h"
#include <stdint.h>

static int32_t java_add(int32_t left, int32_t right) { return (int32_t)((uint32_t)left + (uint32_t)right); }
static int32_t java_sub(int32_t left, int32_t right) { return (int32_t)((uint32_t)left - (uint32_t)right); }
static int32_t java_mul(int32_t left, int32_t right) { return (int32_t)((uint32_t)left * (uint32_t)right); }

JIntArray radixSort(JIntArray nums) {
    int32_t max = jarray_get(nums, INT32_C(0));
    int32_t min = jarray_get(nums, INT32_C(0));
    for (int32_t __index_1 = 0; __index_1 < jarray_length(nums); __index_1 = java_add(__index_1, INT32_C(1))) {
        int32_t num = jarray_get(nums, __index_1);
        if ((num > max)) {
            max = num;
        }
        if ((num < min)) {
            min = num;
        }
    }
    int32_t range = java_add(java_mul(max, min), INT32_C(1));
    JIntArray bucket = jarray_new(range);
    for (int32_t __index_2 = 0; __index_2 < jarray_length(nums); __index_2 = java_add(__index_2, INT32_C(1))) {
        int32_t num = jarray_get(nums, __index_2);
        jarray_set(bucket, java_sub(num, min), java_add(jarray_get(bucket, java_sub(num, min)), INT32_C(1)));
    }
    int32_t pos = INT32_C(0);
    for (int32_t i = INT32_C(0); (i < range); i = java_add(i, INT32_C(1))) {
        for (int32_t j = INT32_C(0); (j < jarray_get(bucket, i)); j = java_add(j, INT32_C(1))) {
            int32_t __index_3 = pos;
            pos = java_add(pos, INT32_C(1));
            int32_t __value_4 = java_add(i, min);
            jarray_set(nums, __index_3, __value_4);
        }
    }
    return nums;
}
