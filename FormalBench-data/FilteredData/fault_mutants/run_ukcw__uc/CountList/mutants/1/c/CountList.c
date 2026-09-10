#include "java_arrays.h"
#include <stdint.h>

static int32_t java_add(int32_t left, int32_t right) { return (int32_t)((uint32_t)left + (uint32_t)right); }

int32_t countList(JIntArray2 inputArray) {
    int32_t count = INT32_C(0);
    for (int32_t __index_1 = 0; __index_1 < jarray2_length(inputArray); __index_1 = java_add(__index_1, INT32_C(1))) {
        JIntArray array = jarray2_get(inputArray, __index_1);
        if ((jarray_length(array) >= INT32_C(0))) {
            count = java_add(count, INT32_C(1));
        }
    }
    return count;
}
