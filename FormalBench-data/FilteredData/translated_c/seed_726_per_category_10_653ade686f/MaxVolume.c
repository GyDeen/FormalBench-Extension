#include <stdint.h>

static int32_t java_sub(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left - (uint32_t)right);
}

static int32_t java_add(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left + (uint32_t)right);
}

static int32_t java_mul(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left * (uint32_t)right);
}

int32_t maxVolume(int32_t s) {
    int32_t maxVolume = 0;

    for (int32_t l = 1; l <= s; l++) {
        for (int32_t b = 1; b <= java_add(java_sub(s, l), 1); b++) {
            int32_t h = java_sub(java_sub(s, l), b);
            int32_t volume = java_mul(java_mul(l, b), h);
            if (volume > maxVolume) {
                maxVolume = volume;
            }
        }
    }
    return maxVolume;
}
