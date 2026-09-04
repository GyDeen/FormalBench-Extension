#include <stdint.h>
#include <stdlib.h>

typedef struct {
    int32_t *data;
    size_t length;
} Int32Array;

static int32_t java_add(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left + (uint32_t)right);
}

static int32_t max2(int32_t left, int32_t right) {
    return left > right ? left : right;
}

int32_t maxSumOfThreeConsecutive(Int32Array arr, int32_t n) {
    if (n <= 0) {
        return 0;
    }

    int32_t *sum = calloc((size_t)n, sizeof(*sum));
    if (sum == NULL) {
        return 0;
    }
    if (n >= 1) {
        sum[0] = arr.data[0];
    }
    if (n >= 2) {
        sum[1] = java_add(sum[0], arr.data[1]);
    }
    if (n > 2) {
        int32_t first = sum[1];
        int32_t second = java_add(arr.data[1], arr.data[2]);
        int32_t third = java_add(arr.data[0], arr.data[2]);
        sum[2] = max2(first, max2(second, third));
    }
    for (int32_t i = 3; i < n; i++) {
        int32_t first = sum[i - 1];
        int32_t second = java_add(sum[i - 2], arr.data[i]);
        int32_t third = java_add(java_add(arr.data[i], arr.data[i - 1]), sum[i - 3]);
        sum[i] = max2(max2(first, second), third);
    }
    int32_t result = sum[n - 1];
    free(sum);
    return result;
}
