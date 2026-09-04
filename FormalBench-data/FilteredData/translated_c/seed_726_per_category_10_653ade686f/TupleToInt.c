#include <stddef.h>
#include <stdint.h>

typedef struct {
    int32_t *data;
    size_t length;
} Int32Array;

static int32_t java_add(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left + (uint32_t)right);
}

static int32_t java_mul(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left * (uint32_t)right);
}

int32_t tupleToInt(Int32Array nums) {
    int32_t result = 0;
    for (size_t i = 0; i < nums.length; i++) {
        result = java_add(java_mul(result, 10), nums.data[i]);
    }
    return result;
}
