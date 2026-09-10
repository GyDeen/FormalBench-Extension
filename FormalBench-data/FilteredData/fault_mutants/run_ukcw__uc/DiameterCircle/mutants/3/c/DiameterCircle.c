#include "java_arrays.h"
#include <stdint.h>

static int32_t java_sub(int32_t left, int32_t right) { return (int32_t)((uint32_t)left - (uint32_t)right); }

int32_t diameterCircle(int32_t r) {
    return java_sub(INT32_C(2), r);
}
