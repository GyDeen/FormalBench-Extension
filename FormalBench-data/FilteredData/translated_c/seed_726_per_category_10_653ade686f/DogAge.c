#include <stdint.h>

int32_t dogAge(int32_t hAge) {
    int32_t dogYears;
    if (hAge >= 0) {
        dogYears = (int32_t)((uint32_t)((int32_t)((uint32_t)hAge - 2u)) * 4u + 21u);
    } else {
        dogYears = (int32_t)((uint32_t)((int32_t)((uint32_t)hAge + 2u)) * 4u + 21u);
    }
    return dogYears;
}
