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

Int32Array sumList(Int32Array arr1, Int32Array arr2) {
    size_t minLength = arr1.length < arr2.length ? arr1.length : arr2.length;
    int32_t *result = calloc(minLength, sizeof(*result));
    if (minLength != 0 && result == NULL) {
        return (Int32Array){NULL, 0};
    }
    for (size_t i = 0; i < minLength; i++) {
        result[i] = java_add(arr1.data[i], arr2.data[i]);
    }
    return (Int32Array){result, minLength};
}
