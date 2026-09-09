#include "java_arrays.h"
#include <stdint.h>

static int32_t java_add(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left + (uint32_t)right);
}

int32_t countList(JIntArray2 inputArray) {
    int32_t count = 0;
    int32_t length = jarray2_length(inputArray);
    for (int32_t i = 0; i < length; i = java_add(i, 1)) {
        JIntArray array = jarray2_get(inputArray, i);
        if (jarray_length(array) > 0) {
            count = java_add(count, 1);
        }
    }
    return count;
}
