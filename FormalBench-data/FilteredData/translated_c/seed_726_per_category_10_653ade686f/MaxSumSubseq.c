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

int32_t maxSumSubseq(Int32Array a) {
    int32_t n = (int32_t)a.length;
    if (n == 0) return 0;
    if (n == 1) return a.data[0];

    int32_t *dp = calloc((size_t)n + 1, sizeof(*dp));
    if (dp == NULL) {
        return 0;
    }
    dp[0] = 0;
    dp[1] = a.data[0];

    for (int32_t i = 2; i <= n; i++) {
        int32_t candidate = java_add(dp[i - 2], a.data[i - 1]);
        dp[i] = dp[i - 1] > candidate ? dp[i - 1] : candidate;
    }
    int32_t result = dp[n];
    free(dp);
    return result;
}
