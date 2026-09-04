#include <stddef.h>
#include <stdint.h>
#include <stdlib.h>

int32_t countWays(int32_t n) {
    int32_t *A = calloc((size_t)n + 1, sizeof(*A));
    int32_t *B = calloc((size_t)n + 1, sizeof(*B));
    if (A == NULL || B == NULL) {
        free(A);
        free(B);
        return 0;
    }
    A[0] = 1;
    A[1] = 0;
    B[0] = 0;
    B[1] = 1;
    for (int32_t i = 2; i <= n; i++) {
        A[i] = A[i - 2] + 2 * B[i - 1];
        B[i] = A[i - 1] + B[i - 2];
    }
    int32_t result = A[n];
    free(A);
    free(B);
    return result;
}
