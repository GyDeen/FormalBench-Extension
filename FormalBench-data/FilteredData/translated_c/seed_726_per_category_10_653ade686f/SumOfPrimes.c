#include <stddef.h>
#include <stdint.h>
#include <stdlib.h>

static int32_t java_mul(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left * (uint32_t)right);
}

static int32_t java_add(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left + (uint32_t)right);
}

int32_t sumOfPrimes(int32_t n) {
    if (n < 0) {
        return 0;
    }

    size_t length = (size_t)n + 1;
    uint8_t *isPrime = calloc(length, sizeof(*isPrime));
    if (isPrime == NULL) {
        return 0;
    }
    for (size_t i = 0; i < length; i++) isPrime[i] = 1;

    int32_t sum = 0;
    for (int32_t i = 2; i <= n; i++) {
        if (isPrime[i]) {
            sum = java_add(sum, i);
            for (int32_t j = java_mul(i, i); j <= n; j = java_add(j, i)) {
                isPrime[j] = 0;
            }
        }
    }
    free(isPrime);
    return sum;
}
