#include <stddef.h>
#include <stdint.h>
#include <stdlib.h>

typedef struct {
    int32_t *data;
    size_t length;
} Int32Array;

static int32_t java_add(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left + (uint32_t)right);
}

int32_t minJumps(Int32Array arr, int32_t n) {
    if (n <= 0) {
        return 0;
    }
    int32_t *dp = malloc((size_t)n * sizeof(*dp));
    if (dp == NULL) {
        return INT32_MAX;
    }
    for (int32_t i = 0; i < n; i++) dp[i] = INT32_MAX;
    dp[0] = 0;

    for (int32_t i = 1; i < n; i++) {
        for (int32_t j = 0; j < i; j++) {
            int32_t reach = java_add(arr.data[j], j);
            if (reach >= i) {
                int32_t candidate = java_add(dp[j], 1);
                if (candidate < dp[i]) dp[i] = candidate;
            }
        }
    }
    int32_t result = dp[n - 1];
    free(dp);
    return result;
}
