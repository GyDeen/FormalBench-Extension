#include <stddef.h>
#include <stdint.h>

typedef struct {
    int32_t *data;
    size_t length;
} Int32Array;

static int32_t java_add(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left + (uint32_t)right);
}

int32_t maxSubArraySum(Int32Array a, int32_t size) {
    int32_t max_so_far = 0;
    int32_t max_ending_here = 0;
    int32_t start = 0;
    int32_t end = 0;
    int32_t s = 0;

    for (int32_t i = 0; i < size; i++) {
        max_ending_here = java_add(max_ending_here, a.data[i]);
        if (max_so_far < max_ending_here) {
            max_so_far = max_ending_here;
            start = s;
            end = i;
        }
        if (max_ending_here < 0) {
            max_ending_here = 0;
            s = i + 1;
        }
    }
    return end - start + 1;
}
