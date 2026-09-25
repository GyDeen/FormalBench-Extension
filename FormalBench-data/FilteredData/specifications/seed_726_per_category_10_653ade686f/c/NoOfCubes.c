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
  ensures \result == (int32_t)((integer)left - right);
*/
static int32_t java_sub(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left - (uint32_t)right);
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
  ensures \result == (int32_t)(((integer)n - k + 1) * ((integer)n - k + 1) * ((integer)n - k + 1));
*/
int32_t noOfCubes(int32_t n, int32_t k) {
    int32_t side = java_add(java_sub(n, k), 1);
    int32_t no = java_mul(java_mul(side, side), side);
    return no;
}
