#include "java_arrays.h"
#include <stdint.h>

/*@
  assigns \nothing;
  ensures \result == (int32_t)((integer)left + right);
*/
static int32_t java_add(int32_t left, int32_t right) { return (int32_t)((uint32_t)left + (uint32_t)right); }
/*@
  assigns \nothing;
  ensures \result == (int32_t)((integer)left - right);
*/
static int32_t java_sub(int32_t left, int32_t right) { return (int32_t)((uint32_t)left - (uint32_t)right); }
/*@
  assigns \nothing;
  ensures \result == (int32_t)((integer)left * right);
*/
static int32_t java_mul(int32_t left, int32_t right) { return (int32_t)((uint32_t)left * (uint32_t)right); }

/*@
  assigns \nothing;
  ensures \result == (int32_t)(((integer)n - k + 1) * ((integer)n - k + 1) * ((integer)n - k + 1));
*/
int32_t noOfCubes(int32_t n, int32_t k) {
    int32_t no = INT32_C(0);
    no = java_mul(java_mul(java_add(java_sub(n, k), INT32_C(1)), java_mul(java_sub(n, k), INT32_C(1))), java_add(java_sub(n, k), INT32_C(1)));
    return no;
}
