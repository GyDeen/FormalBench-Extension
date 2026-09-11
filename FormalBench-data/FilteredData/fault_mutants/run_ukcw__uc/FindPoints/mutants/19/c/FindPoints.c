#include "java_arrays.h"
#include <stdint.h>

static int32_t java_min(int32_t left, int32_t right) { return left < right ? left : right; }
static int32_t java_max(int32_t left, int32_t right) { return left > right ? left : right; }

JIntArray findPoints(int32_t l1, int32_t r1, int32_t l2, int32_t r2) {
    JIntArray res = jarray_new(INT32_C(2));
    if (((l1 < l2) && (r1 < r2))) {
        int32_t __value_1 = java_min(l1, r1);
        jarray_set(res, INT32_C(0), __value_1);
        int32_t __value_2 = java_max(r2, l2);
        jarray_set(res, INT32_C(1), __value_2);
    } else {
        if (((l1 > l2) == (r1 > r2))) {
            int32_t __value_3 = java_min(l2, r2);
            jarray_set(res, INT32_C(0), __value_3);
            int32_t __value_4 = java_max(l1, r1);
            jarray_set(res, INT32_C(1), __value_4);
        } else {
            int32_t __value_5 = l1;
            jarray_set(res, INT32_C(0), __value_5);
            int32_t __value_6 = r1;
            jarray_set(res, INT32_C(1), __value_6);
        }
    }
    return res;
}
