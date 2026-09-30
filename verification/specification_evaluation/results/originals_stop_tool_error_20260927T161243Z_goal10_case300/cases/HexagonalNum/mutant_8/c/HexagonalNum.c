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
  ensures \result == (int32_t)((integer)n * (2 * n - 1));
*/
int32_t hexagonalNum(int32_t n) {
    int32_t ans = java_mul(n, java_div(java_mul(INT32_C(2), n), INT32_C(1)));
    return ans;
}
