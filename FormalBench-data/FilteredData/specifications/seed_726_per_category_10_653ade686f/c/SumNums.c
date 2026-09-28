#include "java_arrays.h"
#include <stdint.h>

/*@
  assigns \nothing;
  ensures \result == (int32_t)((integer)left + right);
*/
static int32_t java_add(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left + (uint32_t)right);
}

/*@
  assigns \nothing;
  ensures \result == (m <= (int32_t)((integer)x + y) && (int32_t)((integer)x + y) <= n ? 20 : (int32_t)((integer)x + y));
*/
int32_t sumNums(int32_t x, int32_t y, int32_t m, int32_t n) {
    int32_t sum = java_add(x, y);
    if (sum >= m && sum <= n) {
        return 20;
    }
    return sum;
}
