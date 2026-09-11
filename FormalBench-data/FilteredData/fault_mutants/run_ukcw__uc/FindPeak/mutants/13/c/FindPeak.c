#include "java_arrays.h"
#include <stdint.h>

static int32_t java_add(int32_t left, int32_t right) { return (int32_t)((uint32_t)left + (uint32_t)right); }
static int32_t java_sub(int32_t left, int32_t right) { return (int32_t)((uint32_t)left - (uint32_t)right); }
static int32_t java_mul(int32_t left, int32_t right) { return (int32_t)((uint32_t)left * (uint32_t)right); }

int32_t findPeak(JIntArray arr, int32_t n) {
    int32_t left = INT32_C(0);
    int32_t right = java_sub(n, INT32_C(1));
    while ((left < right)) {
        int32_t mid = java_add(left, java_mul(java_sub(right, left), INT32_C(2)));
        if ((jarray_get(arr, mid) < jarray_get(arr, java_add(mid, INT32_C(1))))) {
            left = java_add(mid, INT32_C(1));
        } else {
            right = mid;
        }
    }
    return left;
}
