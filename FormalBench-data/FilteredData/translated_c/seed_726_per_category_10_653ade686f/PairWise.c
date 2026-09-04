#include <stddef.h>
#include <stdint.h>
#include <stdlib.h>

typedef struct {
    int32_t *data;
    size_t length;
} Int32Array;

typedef struct {
    Int32Array *rows;
    size_t length;
} Int32Matrix;

Int32Matrix pairWise(Int32Array l1) {
    if (l1.length < 2) {
        return (Int32Matrix){NULL, 0};
    }

    size_t row_count = l1.length - 1;
    Int32Array *rows = calloc(row_count, sizeof(*rows));
    if (rows == NULL) {
        return (Int32Matrix){NULL, 0};
    }
    for (size_t i = 0; i < row_count; i++) {
        rows[i].data = calloc(2, sizeof(*rows[i].data));
        rows[i].length = 2;
        if (rows[i].data == NULL) {
            for (size_t j = 0; j < i; j++) free(rows[j].data);
            free(rows);
            return (Int32Matrix){NULL, 0};
        }
        rows[i].data[0] = l1.data[i];
        rows[i].data[1] = l1.data[i + 1];
    }
    return (Int32Matrix){rows, row_count};
}
