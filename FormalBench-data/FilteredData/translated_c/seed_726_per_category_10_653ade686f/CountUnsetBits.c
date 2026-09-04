#include <stdint.h>

int32_t countUnsetBits(int32_t n) {
    int32_t cnt = 0;
    for (int32_t i = 1; i <= n; i++) {
        int32_t temp = i;
        while (temp != 0) {
            if ((temp & 1) == 0) {
                cnt++;
            }
            temp = temp >> 1;
        }
    }
    return cnt;
}
