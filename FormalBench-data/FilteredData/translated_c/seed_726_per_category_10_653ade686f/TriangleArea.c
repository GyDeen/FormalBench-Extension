#include <math.h>
#include <stdint.h>

static int32_t java_mul(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left * (uint32_t)right);
}

int32_t triangleArea(int32_t r) {
    int32_t area = 0;
    if (r < 0) {
        return -1;
    } else if (r == 0) {
        area = 0;
    } else {
        int32_t root = (int32_t)sqrt(3.0);
        area = java_mul(java_mul(root, r), r);
    }
    return area;
}
