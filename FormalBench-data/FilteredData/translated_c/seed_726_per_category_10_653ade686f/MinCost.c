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

static int32_t java_add(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left + (uint32_t)right);
}

static int32_t min2(int32_t left, int32_t right) {
    return left < right ? left : right;
}

int32_t minCost(Int32Matrix cost, int32_t m, int32_t n) {
    if (m < 0 || n < 0) {
        return 0;
    }

    size_t rows = (size_t)m + 1;
    size_t columns = (size_t)n + 1;
    Int32Array *tc = calloc(rows, sizeof(*tc));
    if (tc == NULL) {
        return 0;
    }
    for (size_t i = 0; i < rows; i++) {
        tc[i].data = calloc(columns, sizeof(*tc[i].data));
        tc[i].length = columns;
        if (tc[i].data == NULL) {
            for (size_t j = 0; j < i; j++) free(tc[j].data);
            free(tc);
            return 0;
        }
    }

    tc[0].data[0] = cost.rows[0].data[0];
    for (int32_t i = 1; i <= m; i++) {
        tc[i].data[0] = java_add(tc[i - 1].data[0], cost.rows[i].data[0]);
    }
    for (int32_t j = 1; j <= n; j++) {
        tc[0].data[j] = java_add(tc[0].data[j - 1], cost.rows[0].data[j]);
    }
    for (int32_t i = 1; i <= m; i++) {
        for (int32_t j = 1; j <= n; j++) {
            int32_t diagonal = tc[i - 1].data[j - 1];
            int32_t from_above = tc[i - 1].data[j];
            int32_t from_left = tc[i].data[j - 1];
            int32_t best = min2(diagonal, min2(from_above, from_left));
            tc[i].data[j] = java_add(best, cost.rows[i].data[j]);
        }
    }
    int32_t result = tc[m].data[n];
    for (size_t i = 0; i < rows; i++) free(tc[i].data);
    free(tc);
    return result;
}
