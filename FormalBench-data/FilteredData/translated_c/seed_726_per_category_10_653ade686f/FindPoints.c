#include <stdint.h>
#include <stdlib.h>

typedef struct {
    int32_t *data;
    size_t length;
} Int32Array;

Int32Array findPoints(int32_t l1, int32_t r1, int32_t l2, int32_t r2) {
    int32_t *data = calloc(2, sizeof(*data));
    if (data == NULL) {
        return (Int32Array){NULL, 0};
    }

    if (l1 < l2 && r1 < r2) {
        data[0] = l1 < r1 ? l1 : r1;
        data[1] = r2 > l2 ? r2 : l2;
    } else if (l1 > l2 && r1 > r2) {
        data[0] = l2 < r2 ? l2 : r2;
        data[1] = l1 > r1 ? l1 : r1;
    } else {
        data[0] = l1;
        data[1] = r1;
    }
    return (Int32Array){data, 2};
}
