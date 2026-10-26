#include "java_arrays.h"
#include <stdint.h>

/*@
  assigns \nothing;
  ensures \result == (int32_t)((uint32_t)value << ((uint32_t)distance & 31));
*/
static int32_t java_shl(int32_t value, int32_t distance) {
    return (int32_t)((uint32_t)value << ((uint32_t)distance & 31u));
}

/*@
  terminates n <= 1073741824;
  assigns \nothing;
  exits \false;
  ensures n <= 1073741824;
  ensures 1 <= \result <= 1073741824 && \result >= n;
  ensures (\result & (\result - 1)) == 0;
  ensures \result == 1 || \result / 2 < n;
*/
int32_t nextPowerOf2(int32_t n) {
    if ((n == INT32_C(0))) {
        return INT32_C(1);
    }
    int32_t i = INT32_C(1);
    /*@
      loop invariant i == 0 || i == INT32_MIN || (1 <= i <= 1073741824 && (i & (i - 1)) == 0);
      loop invariant n <= 1073741824 ==> 1 <= i <= 1073741824;
      loop invariant n <= 1073741824 ==> i == 1 || i / 2 < n;
      loop assigns i;
    */
    while ((i < n)) {
        i = java_shl(i, INT32_C(1));
    }
    return i;
}
