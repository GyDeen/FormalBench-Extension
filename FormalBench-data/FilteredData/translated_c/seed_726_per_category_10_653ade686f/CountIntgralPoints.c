#include <stdint.h>

int32_t countIntgralPoints(int32_t x1, int32_t y1, int32_t x2, int32_t y2) {
    return (int32_t)((int64_t)(y2 - y1 - 1) * (x2 - x1 - 1));
}
