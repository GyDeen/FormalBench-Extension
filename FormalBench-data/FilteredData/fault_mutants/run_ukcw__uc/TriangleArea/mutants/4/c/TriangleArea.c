#include "java_arrays.h"
#include <math.h>
#include <stdint.h>

static int32_t java_mul(int32_t left, int32_t right) { return (int32_t)((uint32_t)left * (uint32_t)right); }

int32_t triangleArea(int32_t r) {
    int32_t area = INT32_C(0);
    if ((r < INT32_C(0))) {
        ;
    } else {
        if ((r == INT32_C(0))) {
            area = INT32_C(0);
        } else {
            area = java_mul(java_mul(((int32_t)(sqrt(INT32_C(3)))), r), r);
        }
    }
    return area;
}
