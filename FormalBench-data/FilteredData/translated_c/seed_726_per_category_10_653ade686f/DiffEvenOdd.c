#include "java_arrays.h"
#include <stdint.h>

static int32_t java_sub(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left - (uint32_t)right);
}

static int32_t java_add(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left + (uint32_t)right);
}

int32_t diffEvenOdd(JIntArray array) {
    int32_t firstEven = -1;
    int32_t firstOdd = -1;
    int32_t length = jarray_length(array);

    for (int32_t i = 0; i < length; i = java_add(i, 1)) {
        int32_t num = jarray_get(array, i);
        if (firstEven == -1 && num % 2 == 0) {
            firstEven = num;
        }
        if (firstOdd == -1 && num % 2 != 0) {
            firstOdd = num;
        }
        if (firstEven != -1 && firstOdd != -1) {
            break;
        }
    }
    return java_sub(firstEven, firstOdd);
}
