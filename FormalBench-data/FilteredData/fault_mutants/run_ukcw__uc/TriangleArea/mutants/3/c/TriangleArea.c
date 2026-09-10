#include "java_arrays.h"
#include <math.h>
#include <stdint.h>

static int32_t java_mul(int32_t left, int32_t right) { return (int32_t)((uint32_t)left * (uint32_t)right); }
static int32_t java_neg(int32_t value) { return (int32_t)(0u - (uint32_t)value); }

int32_t triangleArea(int32_t r) {
    int32_t area = INT32_C(0);
    if (false) {
        return java_neg(INT32_C(1));
    } else {
        if ((r == INT32_C(0))) {
            area = INT32_C(0);
        } else {
            area = java_mul(java_mul(((int32_t)(sqrt(INT32_C(3)))), r), r);
        }
    }
    return area;
}
