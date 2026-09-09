#include "java_arrays.h"
#include <stdint.h>

static int32_t java_add(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left + (uint32_t)right);
}

static int32_t java_sub(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left - (uint32_t)right);
}

static int32_t java_mul(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left * (uint32_t)right);
}

int32_t dogAge(int32_t hAge) {
    int32_t dogYears;
    if (hAge >= 0) {
        dogYears = java_add(java_mul(java_sub(hAge, 2), 4), 21);
    } else {
        dogYears = java_add(java_mul(java_add(hAge, 2), 4), 21);
    }
    return dogYears;
}
