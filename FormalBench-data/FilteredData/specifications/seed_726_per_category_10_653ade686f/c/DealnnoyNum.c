#include "java_arrays.h"
#include <stdint.h>

/*@
  assigns \nothing;
  ensures \result == (int32_t)((integer)left - right);
*/
static int32_t java_sub(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left - (uint32_t)right);
}

/*@
  assigns \nothing;
  ensures \result == (int32_t)((integer)left + right);
*/
static int32_t java_add(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left + (uint32_t)right);
}

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
    if (m == 0 || n == 0) {
        return 1;
    }
    int32_t first = dealnnoyNum(java_sub(m, 1), n);
    int32_t second = dealnnoyNum(java_sub(m, 1), java_sub(n, 1));
    int32_t third = dealnnoyNum(m, java_sub(n, 1));
    return java_add(java_add(first, second), third);
}
