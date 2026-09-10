#include "java_arrays.h"
#include <stdint.h>

static int32_t java_add(int32_t left, int32_t right) { return (int32_t)((uint32_t)left + (uint32_t)right); }
static int32_t java_sub(int32_t left, int32_t right) { return (int32_t)((uint32_t)left - (uint32_t)right); }

int32_t minCoins(JIntArray coins, int32_t m, int32_t v) {
    if ((v == INT32_C(0))) {
        return INT32_C(0);
    }
    if ((v < INT32_C(0))) {
        return INT32_MAX;
    }
    int32_t minCoinsValue = INT32_MAX;
    for (int32_t i = INT32_C(0); (i < m); i = java_add(i, INT32_C(1))) {
        int32_t coin = jarray_get(coins, i);
        int32_t remainingValue = java_sub(v, coin);
        if ((remainingValue >= INT32_C(0))) {
            int32_t totalCoins = minCoins(coins, m, remainingValue);
            if ((totalCoins < INT32_MAX)) {
                totalCoins = java_add(totalCoins, INT32_C(1));
            }
            if ((totalCoins < minCoinsValue)) {
                minCoinsValue = totalCoins;
            }
        }
    }
    return minCoinsValue;
}
