#include <stddef.h>
#include <stdint.h>
#include <stdlib.h>

static int32_t java_add(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left + (uint32_t)right);
}

int32_t maximumSegments(int32_t n, int32_t a, int32_t b, int32_t c) {
    if (n < 0) {
        return -1;
    }

    int32_t *dp = calloc((size_t)n + 1, sizeof(*dp));
    if (dp == NULL) {
        return -1;
    }
    dp[0] = 0;
    for (int32_t i = 1; i <= n; i++) {
        dp[i] = -1;
        if (i >= a && dp[i - a] != -1) {
            int32_t candidate = java_add(dp[i - a], 1);
            if (candidate > dp[i]) dp[i] = candidate;
        }
        if (i >= b && dp[i - b] != -1) {
            int32_t candidate = java_add(dp[i - b], 1);
            if (candidate > dp[i]) dp[i] = candidate;
        }
        if (i >= c && dp[i - c] != -1) {
            int32_t candidate = java_add(dp[i - c], 1);
            if (candidate > dp[i]) dp[i] = candidate;
        }
    }
    int32_t result = dp[n];
    free(dp);
    return result;
}
