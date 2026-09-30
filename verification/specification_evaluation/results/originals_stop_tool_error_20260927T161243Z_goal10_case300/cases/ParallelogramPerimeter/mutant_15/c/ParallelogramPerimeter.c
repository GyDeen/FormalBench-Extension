#include "java_arrays.h"
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>

/*@
  assigns \nothing;
  ensures \result == (int32_t)((integer)left * right);
*/
static int32_t java_mul(int32_t left, int32_t right) { return (int32_t)((uint32_t)left * (uint32_t)right); }
static int32_t java_div(int32_t left, int32_t right) { if (right == 0) { fputs("JAVA_ARITHMETIC_ERROR: DIVIDE_BY_ZERO\n", stderr); exit(75); } if (left == INT32_MIN && right == -1) return INT32_MIN; return left / right; }

/*@
  assigns \nothing;
  ensures \result == (b <= 0 || h <= 0 ? 0 : (int32_t)(2 * (integer)b * h));
*/
int32_t parallelogramPerimeter(int32_t b, int32_t h) {
    if (((b <= INT32_C(0)) || (h <= INT32_C(0)))) {
        return INT32_C(0);
    }
    return java_mul(INT32_C(2), java_div(b, h));
}
