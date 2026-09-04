#include <stdint.h>

int32_t fibonacci(int32_t n) {
    if (n == 0) {
        return 0;
    } else if (n == 1) {
        return 1;
    } else {
        int32_t left = fibonacci((int32_t)((uint32_t)n - 1u));
        int32_t right = fibonacci((int32_t)((uint32_t)n - 2u));
        return (int32_t)((uint32_t)left + (uint32_t)right);
    }
}
