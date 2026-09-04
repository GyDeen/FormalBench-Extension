#include <stddef.h>
#include <stdint.h>
#include <stdlib.h>

typedef struct {
    int32_t *data;
    size_t length;
} Int32Array;

static int32_t java_mul(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left * (uint32_t)right);
}

int32_t maxProduct(Int32Array arr, int32_t n) {
    if (n <= 0) {
        return 0;
    }

    int32_t *mpis = calloc((size_t)n, sizeof(*mpis));
    if (mpis == NULL) {
        return 0;
    }
    for (int32_t i = 0; i < n; i++) {
        mpis[i] = arr.data[i];
    }
    for (int32_t i = 1; i < n; i++) {
        for (int32_t j = 0; j < i; j++) {
            int32_t product = java_mul(mpis[j], arr.data[i]);
            if (arr.data[i] > arr.data[j] && mpis[i] < product) {
                mpis[i] = product;
            }
        }
    }
    int32_t max = mpis[0];
    for (int32_t i = 1; i < n; i++) {
        if (mpis[i] > max) {
            max = mpis[i];
        }
    }
    free(mpis);
    return max;
}
