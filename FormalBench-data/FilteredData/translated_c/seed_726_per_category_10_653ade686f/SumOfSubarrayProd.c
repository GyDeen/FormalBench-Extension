#include <stddef.h>
#include <stdint.h>

typedef struct {
    int32_t *data;
    size_t length;
} Int32Array;

static int32_t java_add(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left + (uint32_t)right);
}

static int32_t java_mul(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left * (uint32_t)right);
}

int32_t sumOfSubarrayProd(Int32Array arr, int32_t n) {
    int32_t sum = 0;
    for (int32_t i = 0; i < n; i++) {
        int32_t product = 1;
        for (int32_t j = i; j < n; j++) {
            product = java_mul(product, arr.data[j]);
            sum = java_add(sum, product);
        }
    }
    return sum;
}
