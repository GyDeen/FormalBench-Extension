#include <stddef.h>
#include <stdint.h>

typedef struct {
    int32_t *data;
    size_t length;
} Int32Array;

static int32_t java_add(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left + (uint32_t)right);
}

int32_t leftInsertion(Int32Array a, int32_t x) {
    int32_t left = 0;
    int32_t right = (int32_t)a.length - 1;
    while (left <= right) {
        int32_t mid = java_add(left, right) / 2;
        if (a.data[mid] == x) {
            return mid;
        } else if (a.data[mid] < x) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    return left;
}
