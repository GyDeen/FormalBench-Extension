#include <stdint.h>

int32_t sumNums(int32_t x, int32_t y, int32_t m, int32_t n) {
    int32_t sum = (int32_t)((uint32_t)x + (uint32_t)y);
    if (sum >= m && sum <= n) {
        return 20;
    } else {
        return sum;
    }
}
