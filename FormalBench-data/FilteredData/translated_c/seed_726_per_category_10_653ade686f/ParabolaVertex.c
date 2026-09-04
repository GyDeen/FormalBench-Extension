#include <stddef.h>
#include <stdint.h>
#include <stdlib.h>

typedef struct {
    double *data;
    size_t length;
} DoubleArray;

DoubleArray parabolaVertex(int32_t a, int32_t b, int32_t c) {
    double *vertex = calloc(2, sizeof(*vertex));
    if (vertex == NULL) {
        return (DoubleArray){NULL, 0};
    }
    vertex[0] = -(double)b / (2.0 * (double)a);
    vertex[1] = (4.0 * (double)a * (double)c - (double)b * (double)b)
              / (4.0 * (double)a);
    return (DoubleArray){vertex, 2};
}
