#include <stdint.h>

static int32_t java_shl1(int32_t value) {
    return (int32_t)((uint32_t)value << 1);
}

int32_t nextPowerOf2(int32_t n) {
    if (n == 0) {
        return 1;
    }
    int32_t i = 1;
    while (i < n) {
        i = java_shl1(i);
    }
    return i;
}
