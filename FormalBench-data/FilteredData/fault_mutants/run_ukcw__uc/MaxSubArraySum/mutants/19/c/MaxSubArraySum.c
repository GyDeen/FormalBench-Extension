#include "java_arrays.h"
#include <stdint.h>

static int32_t java_add(int32_t left, int32_t right) { return (int32_t)((uint32_t)left + (uint32_t)right); }
static int32_t java_sub(int32_t left, int32_t right) { return (int32_t)((uint32_t)left - (uint32_t)right); }

int32_t maxSubArraySum(JIntArray a, int32_t size) {
    int32_t max_so_far = INT32_C(0);
    int32_t max_ending_here = INT32_C(0);
    int32_t start = INT32_C(0);
    int32_t end = INT32_C(0);
    int32_t s = INT32_C(0);
    for (int32_t i = INT32_C(0); (i < size); i = java_add(i, INT32_C(1))) {
        max_ending_here = java_add(max_ending_here, jarray_get(a, i));
        if ((max_so_far < max_ending_here)) {
            max_so_far = max_ending_here;
            start = s;
            end = i;
        }
        if ((max_ending_here < INT32_C(0))) {
            max_ending_here = INT32_C(0);
            ;
        }
    }
    return java_add(java_sub(end, start), INT32_C(1));
}
