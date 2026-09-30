#include "java_arrays.h"
#include <stdint.h>

/*@
  assigns \nothing;
  ensures \result == (x == y && y == z ? 3 : (x == y || y == z || x == z ? 2 : 0));
*/
int32_t testThreeEqual(int32_t x, int32_t y, int32_t z) {
    if (x == y && y == z) {
        return 3;
    } else if (x == y || y == z || x == z) {
        return 2;
    }
    return 0;
}
