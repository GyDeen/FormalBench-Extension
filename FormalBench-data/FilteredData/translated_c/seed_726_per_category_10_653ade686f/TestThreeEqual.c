#include <stdint.h>

int32_t testThreeEqual(int32_t x, int32_t y, int32_t z) {
    if (x == y && y == z) {
        return 3;
    } else if (x == y || y == z || x == z) {
        return 2;
    } else {
        return 0;
    }
}
