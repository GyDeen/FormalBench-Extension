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

static int32_t java_sub(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left - (uint32_t)right);
}

static int32_t java_abs(int32_t value) {
    if (value == INT32_MIN) {
        return INT32_MIN;
    }
    return value < 0 ? -value : value;
}

int32_t maxDifference(Int32Matrix testArray) {
    int32_t maxDiff = 0;
    int32_t min = INT32_MAX;
    int32_t max = INT32_MIN;

    for (size_t i = 0; i < testArray.length; i++) {
        int32_t a = testArray.rows[i].data[0];
        int32_t b = testArray.rows[i].data[1];

        if (a < min) min = a;
        if (b < min) min = b;
        if (a > max) max = a;
        if (b > max) max = b;

        if (i != testArray.length - 1) {
            int32_t difference = java_abs(java_sub(a, b));
            if (difference > maxDiff) maxDiff = difference;
        }
    }

    return maxDiff;
}
