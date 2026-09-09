#include "java_arrays.h"
#include <stdint.h>

static int32_t java_add(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left + (uint32_t)right);
}

int32_t countUnsetBits(int32_t n) {
    int32_t cnt = 0;
    for (int32_t i = 1; i <= n; i = java_add(i, 1)) {
        int32_t temp = i;
        while (temp != 0) {
            if ((temp & 1) == 0) {
                cnt = java_add(cnt, 1);
            }
            temp = temp >> 1;
        }
    }
    return cnt;
}
