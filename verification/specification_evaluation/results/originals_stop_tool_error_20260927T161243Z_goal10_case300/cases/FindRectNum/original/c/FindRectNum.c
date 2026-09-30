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
  ensures \result == (int32_t)((integer)left * right);
*/
static int32_t java_mul(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left * (uint32_t)right);
}

/*@
  assigns \nothing;
  ensures \result == (int32_t)((integer)n * (n + 1));
*/
int32_t findRectNum(int32_t n) {
    return java_mul(n, java_add(n, 1));
}
