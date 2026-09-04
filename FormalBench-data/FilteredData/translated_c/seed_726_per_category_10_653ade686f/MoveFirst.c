#include <stddef.h>
#include <stdint.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
    int32_t *data;
    size_t length;
} Int32Array;

Int32Array moveFirst(Int32Array testArray) {
    if (testArray.data == NULL || testArray.length == 0) {
        return testArray;
    }

    int32_t *result = calloc(testArray.length, sizeof(*result));
    if (result == NULL) {
        return (Int32Array){NULL, 0};
    }
    result[0] = testArray.data[testArray.length - 1];
    if (testArray.length > 1) {
        memcpy(result + 1, testArray.data,
               (testArray.length - 1) * sizeof(*result));
    }
    return (Int32Array){result, testArray.length};
}
