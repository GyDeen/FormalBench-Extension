#include "java_arrays.h"
#include <stdint.h>

JIntArray findPoints(int32_t l1, int32_t r1, int32_t l2, int32_t r2) {
    JIntArray res = jarray_new(2);
    if (l1 < l2 && r1 < r2) {
        jarray_set(res, 0, l1 < r1 ? l1 : r1);
        jarray_set(res, 1, r2 > l2 ? r2 : l2);
    } else if (l1 > l2 && r1 > r2) {
        jarray_set(res, 0, l2 < r2 ? l2 : r2);
        jarray_set(res, 1, l1 > r1 ? l1 : r1);
    } else {
        jarray_set(res, 0, l1);
        jarray_set(res, 1, r1);
    }
    return res;
}
