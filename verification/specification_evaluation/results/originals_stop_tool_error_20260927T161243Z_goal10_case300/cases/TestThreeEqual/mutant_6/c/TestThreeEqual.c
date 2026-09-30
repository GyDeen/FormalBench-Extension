#include "java_arrays.h"
#include <stdint.h>

/*@
  assigns \nothing;
  ensures \result == (x == y && y == z ? 3 : (x == y || y == z || x == z ? 2 : 0));
*/
int32_t testThreeEqual(int32_t x, int32_t y, int32_t z) {
    if (((x == y) && false)) {
        return INT32_C(3);
    } else {
        if ((((x == y) || (y == z)) || (x == z))) {
            return INT32_C(2);
        } else {
            return INT32_C(0);
        }
    }
}
