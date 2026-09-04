#include <stdint.h>

int32_t parallelogramPerimeter(int32_t b, int32_t h) {
    if (b <= 0 || h <= 0) {
        return 0;
    }
    int32_t product = (int32_t)((uint32_t)b * (uint32_t)h);
    return (int32_t)((uint32_t)2 * (uint32_t)product);
}
