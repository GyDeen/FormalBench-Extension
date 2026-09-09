#include "java_arrays.h"
#include <stdint.h>

static int32_t java_mul(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left * (uint32_t)right);
}

int32_t parallelogramPerimeter(int32_t b, int32_t h) {
    if (b <= 0 || h <= 0) {
        return 0;
    }
    return java_mul(2, java_mul(b, h));
}
