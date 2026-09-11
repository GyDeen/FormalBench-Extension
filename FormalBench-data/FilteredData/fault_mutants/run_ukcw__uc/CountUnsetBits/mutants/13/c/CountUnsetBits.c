#include "java_arrays.h"
#include <stdint.h>

static int32_t java_add(int32_t left, int32_t right) { return (int32_t)((uint32_t)left + (uint32_t)right); }
static int32_t java_and(int32_t left, int32_t right) { return (int32_t)((uint32_t)left & (uint32_t)right); }
static int32_t java_shl(int32_t value, int32_t distance) { return (int32_t)((uint32_t)value << ((uint32_t)distance & 31u)); }

int32_t countUnsetBits(int32_t n) {
    int32_t cnt = INT32_C(0);
    for (int32_t i = INT32_C(1); (i <= n); i = java_add(i, INT32_C(1))) {
        int32_t temp = i;
        while ((temp != INT32_C(0))) {
            if ((java_and(temp, INT32_C(1)) == INT32_C(0))) {
                cnt = java_add(cnt, INT32_C(1));
            }
            temp = java_shl(temp, INT32_C(1));
        }
    }
    return cnt;
}
