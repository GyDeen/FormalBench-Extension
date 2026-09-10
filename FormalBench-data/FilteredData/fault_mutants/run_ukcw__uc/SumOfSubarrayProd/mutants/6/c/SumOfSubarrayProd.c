#include "java_arrays.h"
#include <stdint.h>

static int32_t java_add(int32_t left, int32_t right) { return (int32_t)((uint32_t)left + (uint32_t)right); }
static int32_t java_mul(int32_t left, int32_t right) { return (int32_t)((uint32_t)left * (uint32_t)right); }

int32_t sumOfSubarrayProd(JIntArray arr, int32_t n) {
    int32_t sum = INT32_C(0);
    for (int32_t i = INT32_C(0); (i < n); i = java_add(i, INT32_C(1))) {
        int32_t product = INT32_C(1);
        for (int32_t j = i; false; j = java_add(j, INT32_C(1))) {
            product = java_mul(product, jarray_get(arr, j));
            sum = java_add(sum, product);
        }
    }
    return sum;
}
