#include "java_arrays.h"
#include <inttypes.h>
#include <stdint.h>
#include <stdio.h>

static int32_t java_add(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left + (uint32_t)right);
}

static int32_t java_mul(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left * (uint32_t)right);
}

int32_t sumOfPrimes(int32_t n) {
    int32_t length = java_add(n, 1);
    JBoolArray isPrime = jbool_array_new(length);
    int32_t arrayLength = jbool_array_length(isPrime);
    for (int32_t i = 0; i < arrayLength; i = java_add(i, 1)) {
        jbool_array_set(isPrime, i, true);
    }

    int32_t sum = 0;
    for (int32_t i = 2; i <= n; i = java_add(i, 1)) {
        if (jbool_array_get(isPrime, i)) {
            printf("%" PRId32 "\n", i);
            sum = java_add(sum, i);
            for (int32_t j = java_mul(i, i); j <= n; j = java_add(j, i)) {
                jbool_array_set(isPrime, j, false);
            }
        }
    }
    return sum;
}
