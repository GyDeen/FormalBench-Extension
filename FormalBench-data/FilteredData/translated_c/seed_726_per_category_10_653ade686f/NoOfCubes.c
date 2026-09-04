#include <stdint.h>

int32_t noOfCubes(int32_t n, int32_t k) {
    int32_t side = (int32_t)((uint32_t)n - (uint32_t)k + 1u);
    return (int32_t)((uint32_t)side * (uint32_t)side * (uint32_t)side);
}
