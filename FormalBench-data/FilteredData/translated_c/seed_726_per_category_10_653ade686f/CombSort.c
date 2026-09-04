#include <stdbool.h>
#include <stddef.h>
#include <stdint.h>

typedef struct {
    int32_t *data;
    size_t length;
} Int32Array;

Int32Array combSort(Int32Array nums) {
    int32_t n = (int32_t)nums.length;
    int32_t gap = n;
    bool swapped = true;

    while (gap > 1 || swapped) {
        gap = (int32_t)((double)gap / 1.3);
        swapped = false;

        for (int32_t i = 0; i + gap < n; i++) {
            if (nums.data[i] > nums.data[i + gap]) {
                int32_t temp = nums.data[i];
                nums.data[i] = nums.data[i + gap];
                nums.data[i + gap] = temp;
                swapped = true;
            }
        }
    }
    return nums;
}
