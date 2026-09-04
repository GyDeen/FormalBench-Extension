#include <stdint.h>

int32_t findRectNum(int32_t n) {
    return (int32_t)((uint32_t)n * (uint32_t)((int32_t)((uint32_t)n + 1u)));
}
