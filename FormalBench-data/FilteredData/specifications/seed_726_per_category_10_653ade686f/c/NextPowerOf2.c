#include "java_arrays.h"
#include <stdint.h>

/*@
  assigns \nothing;
  ensures \result == (int32_t)(2 * (integer)value);
*/
static int32_t java_shl_one(int32_t value) {
    return (int32_t)((uint32_t)value << 1);
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
    if (n == 0) {
        return 1;
    }
    int32_t i = 1;
    /*@
      loop invariant i == 0 || i == INT32_MIN || (1 <= i <= 1073741824 && (i & (i - 1)) == 0);
      loop invariant n <= 1073741824 ==> 1 <= i <= 1073741824;
      loop invariant n <= 1073741824 ==> i == 1 || i / 2 < n;
      loop assigns i;
    */
    while (i < n) {
        i = java_shl_one(i);
    }
    return i;
}
