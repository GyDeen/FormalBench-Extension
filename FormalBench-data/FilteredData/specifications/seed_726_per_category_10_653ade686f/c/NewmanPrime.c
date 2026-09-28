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
  ensures \result == (int32_t)((integer)left * right);
*/
static int32_t java_mul(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left * (uint32_t)right);
}

/*@
  assigns \nothing;
  ensures \result == (int32_t)((integer)left + right);
*/
static int32_t java_add(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left + (uint32_t)right);
}

/*@
  logic integer fb_value(integer n) = n <= 1 ? 1 : 2 * fb_value(n - 1) + fb_value(n - 2);
*/
/*@
  requires n >= 0;
  decreases n;
  assigns \nothing;
  ensures \result == (int32_t)fb_value(n);
*/
int32_t newmanPrime(int32_t n) {
    if (n == 0 || n == 1) {
        return 1;
    }
    int32_t first = newmanPrime(java_sub(n, 1));
    int32_t second = newmanPrime(java_sub(n, 2));
    return java_add(java_mul(2, first), second);
}
