#include <stdint.h>

int32_t oddBitSetNumber(int32_t n) {
    uint32_t value = (uint32_t)n;
    value |= ((uint32_t)n & UINT32_C(0xAAAAAAAA)) >> 1;
    value |= ((uint32_t)n & UINT32_C(0xCCCCCCCC)) >> 2;
    value |= ((uint32_t)n & UINT32_C(0xF0F0F0F0)) >> 4;
    value |= ((uint32_t)n & UINT32_C(0xFF00FF00)) >> 8;
    value |= ((uint32_t)n & UINT32_C(0xFFFF0000)) >> 16;
    return (int32_t)value;
}
