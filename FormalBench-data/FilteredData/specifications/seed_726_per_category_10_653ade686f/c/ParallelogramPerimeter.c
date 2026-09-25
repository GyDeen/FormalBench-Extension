#include "java_arrays.h"
#include <stdint.h>

/*@
  assigns \nothing;
  ensures \result == (int32_t)((integer)left * right);
*/
static int32_t java_mul(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left * (uint32_t)right);
}

/*@
  assigns \nothing;
  ensures \result == (b <= 0 || h <= 0 ? 0 : (int32_t)(2 * (integer)b * h));
*/
int32_t parallelogramPerimeter(int32_t b, int32_t h) {
    if (b <= 0 || h <= 0) {
        return 0;
    }
    return java_mul(2, java_mul(b, h));
}
