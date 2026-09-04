#include <stddef.h>
#include <stdint.h>
#include <stdlib.h>

typedef struct {
    int32_t *data;
    size_t length;
} Int32Array;

static int32_t java_mul(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left * (uint32_t)right);
}

Int32Array multiplyElements(Int32Array testTup) {
    if (testTup.length < 2) {
        return (Int32Array){NULL, 0};
    }

    size_t length = testTup.length - 1;
    int32_t *result = calloc(length, sizeof(*result));
    if (result == NULL) {
        return (Int32Array){NULL, 0};
    }
    for (size_t i = 0; i < length; i++) {
        result[i] = java_mul(testTup.data[i], testTup.data[i + 1]);
    }
    return (Int32Array){result, length};
}
