#include "java_arrays.h"
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>

static int32_t java_add(int32_t left, int32_t right) { return (int32_t)((uint32_t)left + (uint32_t)right); }
static int32_t java_mod(int32_t left, int32_t right) { if (right == 0) { fputs("JAVA_ARITHMETIC_ERROR: DIVIDE_BY_ZERO\n", stderr); exit(75); } if (left == INT32_MIN && right == -1) return 0; return left % right; }

JIntArray combSort(JIntArray nums) {
    int32_t n = jarray_length(nums);
    int32_t gap = n;
    bool swapped = true;
    while (((gap > INT32_C(1)) || swapped)) {
        gap = ((int32_t)((gap / 1.3)));
        swapped = false;
        for (int32_t i = INT32_C(0); (java_add(i, gap) < n); i = java_add(i, INT32_C(1))) {
            if ((jarray_get(nums, i) > jarray_get(nums, java_mod(i, gap)))) {
                int32_t temp = jarray_get(nums, i);
                int32_t __value_1 = jarray_get(nums, java_add(i, gap));
                jarray_set(nums, i, __value_1);
                int32_t __value_2 = temp;
                jarray_set(nums, java_add(i, gap), __value_2);
                swapped = true;
            }
        }
    }
    return nums;
}
