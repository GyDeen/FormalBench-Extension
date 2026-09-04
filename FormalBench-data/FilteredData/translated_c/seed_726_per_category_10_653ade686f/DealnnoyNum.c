#include <stdint.h>

int32_t dealnnoyNum(int32_t n, int32_t m) {
    if (m == 0 || n == 0) {
        return 1;
    }
    return dealnnoyNum(m - 1, n) +
           dealnnoyNum(m - 1, n - 1) +
           dealnnoyNum(m, n - 1);
}
