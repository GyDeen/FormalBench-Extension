#include "java_arrays.h"
#include <stdint.h>

/*@
assigns \nothing;
ensures \result == (int32_t)((integer)left + right);
*/
static int32_t java_add(int32_t left, int32_t right) { return (int32_t)((uint32_t)left + (uint32_t)right); }
static int32_t java_and(int32_t left, int32_t right) { return (int32_t)((uint32_t)left & (uint32_t)right); }
static int32_t java_shr(int32_t value, int32_t distance) { return value >> ((uint32_t)distance & 31u); }

/*@
logic integer fb_zero_bits(integer x) = x <= 0 ? 0 : fb_zero_bits(x / 2) + (x % 2 == 0 ? 1 : 0);
logic integer fb_total_zeros(integer n) = n <= 0 ? 0 : fb_total_zeros(n - 1) + fb_zero_bits(n);
*/
/*@
terminates n < INT32_MAX;
assigns \nothing;
ensures n < INT32_MAX;
ensures \result == (int32_t)fb_total_zeros(n);
*/
int32_t countUnsetBits(int32_t n) {
    int32_t cnt = INT32_C(0);
    /*@
    loop invariant n < INT32_MAX ==> 1 <= i <= (n < 1 ? 1 : (integer)n + 1);
    loop invariant n < INT32_MAX ==> cnt == (int32_t)fb_total_zeros(i - 1);
    loop assigns i, cnt;
    */
    for (int32_t i = INT32_C(1); (i < n); i = java_add(i, INT32_C(1))) {
        int32_t temp = i;
        /*@
        loop invariant n < INT32_MAX ==> 0 <= temp <= i;
        loop invariant n < INT32_MAX ==> cnt == (int32_t)(fb_total_zeros(i) - fb_zero_bits(temp));
        loop assigns temp, cnt;
        */
        while ((temp != INT32_C(0))) {
            if ((java_and(temp, INT32_C(1)) == INT32_C(0))) {
                cnt = java_add(cnt, INT32_C(1));
            }
            temp = java_shr(temp, INT32_C(1));
        }
    }
    return cnt;
}
