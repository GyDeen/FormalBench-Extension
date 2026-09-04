#include <stddef.h>
#include <stdint.h>

typedef struct {
    int32_t *data;
    size_t length;
} Int32Array;

int32_t findPeak(Int32Array arr, int32_t n) {
    int32_t left = 0;
    int32_t right = n - 1;
    while (left < right) {
        int32_t mid = left + (right - left) / 2;
        if (arr.data[mid] < arr.data[mid + 1]) {
            left = mid + 1;
        } else {
            right = mid;
        }
    }
    return left;
}
