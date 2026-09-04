#include <math.h>
#include <stdint.h>

int32_t countOddSquares(int32_t n, int32_t m) {
    int32_t count = 0;
    for (int32_t i = n; i <= m; i++) {
        double square = sqrt((double)i);
        if (fmod(square, 1.0) == 0.0 && i % 1 == 0) {
            count++;
        }
    }
    return count;
}
