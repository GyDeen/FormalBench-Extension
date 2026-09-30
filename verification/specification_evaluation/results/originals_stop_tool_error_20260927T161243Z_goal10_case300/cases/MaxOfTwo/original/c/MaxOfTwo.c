#include "java_arrays.h"
#include <stdint.h>

/*@
  assigns \nothing;
  ensures \result == (x > y ? x : y);
*/
int32_t maxOfTwo(int32_t x, int32_t y) {
    return x > y ? x : y;
}
