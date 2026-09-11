#include "java_arrays.h"
#include <inttypes.h>
#include <stdint.h>
#include <stdio.h>

static int32_t java_add(int32_t left, int32_t right) { return (int32_t)((uint32_t)left + (uint32_t)right); }
static int32_t java_sub(int32_t left, int32_t right) { return (int32_t)((uint32_t)left - (uint32_t)right); }
static int32_t java_mul(int32_t left, int32_t right) { return (int32_t)((uint32_t)left * (uint32_t)right); }

int32_t sumOfPrimes(int32_t n) {
    int32_t sum = INT32_C(0);
    JBoolArray isPrime = jbool_array_new(java_sub(n, INT32_C(1)));
    for (int32_t __fill_index_1 = 0; __fill_index_1 < jbool_array_length(isPrime); __fill_index_1 = java_add(__fill_index_1, INT32_C(1))) {
        jbool_array_set(isPrime, __fill_index_1, true);
    }
    for (int32_t i = INT32_C(2); (i <= n); i = java_add(i, INT32_C(1))) {
        if (jbool_array_get(isPrime, i)) {
            printf("%" PRId32 "\n", i);
            sum = java_add(sum, i);
            for (int32_t j = java_mul(i, i); (j <= n); j = java_add(j, i)) {
                bool __value_2 = false;
                jbool_array_set(isPrime, j, __value_2);
            }
        }
    }
    return sum;
}
