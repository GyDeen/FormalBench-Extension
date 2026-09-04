#include <stddef.h>
#include <stdint.h>

typedef struct {
    int32_t *data;
    size_t length;
} Int32Array;

static int32_t java_sub(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left - (uint32_t)right);
}

static int32_t java_add(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left + (uint32_t)right);
}

int32_t minCoins(Int32Array coins, int32_t m, int32_t v) {
    if (v == 0) {
        return 0;
    }

    if (v < 0) {
        return INT32_MAX;
    }

    int32_t minCoinsValue = INT32_MAX;
    for (int32_t i = 0; i < m; i++) {
        int32_t remainingValue = java_sub(v, coins.data[i]);
        if (remainingValue >= 0) {
            int32_t totalCoins = minCoins(coins, m, remainingValue);
            if (totalCoins != INT32_MAX) {
                totalCoins = java_add(totalCoins, 1);
            }
            if (totalCoins < minCoinsValue) {
                minCoinsValue = totalCoins;
            }
        }
    }
    return minCoinsValue;
}
