#include "java_arrays.h"
#include <stdint.h>

static int32_t java_add(int32_t left, int32_t right) { return (int32_t)((uint32_t)left + (uint32_t)right); }
static int32_t java_sub(int32_t left, int32_t right) { return (int32_t)((uint32_t)left - (uint32_t)right); }
static int32_t java_mul(int32_t left, int32_t right) { return (int32_t)((uint32_t)left * (uint32_t)right); }
static int32_t java_neg(int32_t value) { return (int32_t)(0u - (uint32_t)value); }

int32_t sqrtRoot(int32_t num) {
    if ((num < INT32_C(0))) {
        return java_neg(INT32_C(1));
    }
    int32_t left = INT32_C(0);
    int32_t right = num;
    while ((left <= right)) {
        int32_t mid = java_add(java_add(left, java_sub(right, left)), INT32_C(2));
        if ((java_mul(mid, mid) == num)) {
            return mid;
        } else {
            if ((java_mul(mid, mid) < num)) {
                left = java_add(mid, INT32_C(1));
            } else {
                right = java_sub(mid, INT32_C(1));
            }
        }
    }
    return right;
}
