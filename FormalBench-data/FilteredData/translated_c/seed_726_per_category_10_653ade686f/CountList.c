#include <stddef.h>
#include <stdint.h>

typedef struct {
    int32_t *data;
    size_t length;
} Int32Array;

typedef struct {
    Int32Array *rows;
    size_t length;
} Int32Matrix;

int32_t countList(Int32Matrix inputArray) {
    int32_t count = 0;
    for (size_t i = 0; i < inputArray.length; i++) {
        if (inputArray.rows[i].length > 0) {
            count++;
        }
    }
    return count;
}
