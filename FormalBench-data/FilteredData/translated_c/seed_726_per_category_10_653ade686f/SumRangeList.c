#include <stddef.h>
#include <stdint.h>

typedef struct {
    int32_t *data;
    size_t length;
} Int32Array;

static int32_t java_add(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left + (uint32_t)right);
}

int32_t sumRangeArray(Int32Array nums, int32_t m, int32_t n) {
    int32_t sum = 0;
    for (int32_t i = m; i <= n; i++) {
        sum = java_add(sum, nums.data[i]);
    }
    return sum;
}
