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

int32_t oddLengthSum(Int32Array arr) {
    int32_t sum = 0;
    int32_t l = (int32_t)arr.length;
    for (int32_t i = 0; i < l; i++) {
        int32_t count = java_add(java_mul(i + 1, l - i), 1) / 2;
        sum = java_add(sum, java_mul(count, arr.data[i]));
    }
    return sum;
}
