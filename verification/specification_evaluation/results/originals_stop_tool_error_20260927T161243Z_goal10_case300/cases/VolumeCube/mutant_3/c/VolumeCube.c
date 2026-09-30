#include "java_arrays.h"
#include <stdint.h>

static int32_t java_sub(int32_t left, int32_t right) { return (int32_t)((uint32_t)left - (uint32_t)right); }
/*@
  assigns \nothing;
  ensures \result == (int32_t)((integer)left * right);
*/
static int32_t java_mul(int32_t left, int32_t right) { return (int32_t)((uint32_t)left * (uint32_t)right); }

/*@
  assigns \nothing;
  ensures \result == (int32_t)((integer)l * l * l);
*/
int32_t volumeCube(int32_t l) {
    return java_sub(l, java_mul(l, l));
}
