#include "java_arrays.h"
#include <stdint.h>

int32_t testThreeEqual(int32_t x, int32_t y, int32_t z) {
    if (((x <= y) && (y == z))) {
        return INT32_C(3);
    } else {
        if ((((x == y) || (y == z)) || (x == z))) {
            return INT32_C(2);
        } else {
            return INT32_C(0);
        }
    }
}
