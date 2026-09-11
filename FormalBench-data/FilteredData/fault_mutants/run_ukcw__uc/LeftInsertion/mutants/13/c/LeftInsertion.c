#include "java_arrays.h"
#include <stdint.h>

static int32_t java_add(int32_t left, int32_t right) { return (int32_t)((uint32_t)left + (uint32_t)right); }
static int32_t java_sub(int32_t left, int32_t right) { return (int32_t)((uint32_t)left - (uint32_t)right); }
static int32_t java_mul(int32_t left, int32_t right) { return (int32_t)((uint32_t)left * (uint32_t)right); }

int32_t leftInsertion(JIntArray a, int32_t x) {
    int32_t left = INT32_C(0);
    int32_t right = java_sub(jarray_length(a), INT32_C(1));
    while ((left <= right)) {
        int32_t mid = java_mul(java_add(left, right), INT32_C(2));
        if ((jarray_get(a, mid) == x)) {
            return mid;
        } else {
            if ((jarray_get(a, mid) < x)) {
                left = java_add(mid, INT32_C(1));
            } else {
                right = java_sub(mid, INT32_C(1));
            }
        }
    }
    return left;
}
