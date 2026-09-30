#include "java_arrays.h"
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>

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
static int32_t java_mod(int32_t left, int32_t right) { if (right == 0) { fputs("JAVA_ARITHMETIC_ERROR: DIVIDE_BY_ZERO\n", stderr); exit(75); } if (left == INT32_MIN && right == -1) return 0; return left % right; }

/*@
  logic integer fb_delannoy(integer n, integer m) =
    n <= 0 || m <= 0 ? 1 : fb_delannoy(m - 1, n) + fb_delannoy(m - 1, n - 1) + fb_delannoy(m, n - 1);
*/
/*@
  requires n == 0 || m == 0 || (n > 0 && m > 0);
  decreases n > 0 && m > 0 ? (integer)n + m : 0;
  assigns \nothing;
  ensures \result == (int32_t)fb_delannoy(n, m);
*/
int32_t dealnnoyNum(int32_t n, int32_t m) {
    if (((m == INT32_C(0)) || (n == INT32_C(0)))) {
        return INT32_C(1);
    }
    return java_add(java_mod(dealnnoyNum(java_sub(m, INT32_C(1)), n), dealnnoyNum(java_sub(m, INT32_C(1)), java_sub(n, INT32_C(1)))), dealnnoyNum(m, java_sub(n, INT32_C(1))));
}
