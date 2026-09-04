#include <stdint.h>

int32_t hexagonalNum(int32_t n) {
    int32_t doubled = (int32_t)((uint32_t)2 * (uint32_t)n);
    int32_t term = (int32_t)((uint32_t)doubled - 1u);
    return (int32_t)((uint32_t)n * (uint32_t)term);
}
