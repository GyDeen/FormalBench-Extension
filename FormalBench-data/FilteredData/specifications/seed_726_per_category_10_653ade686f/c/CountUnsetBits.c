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
    int32_t cnt = 0;
    /*@
    loop invariant n < INT32_MAX ==> 1 <= i <= (n < 1 ? 1 : (integer)n + 1);
    loop invariant n < INT32_MAX ==> cnt == (int32_t)fb_total_zeros(i - 1);
    loop assigns i, cnt;
    */
    for (int32_t i = 1; i <= n; i = java_add(i, 1)) {
        int32_t temp = i;
        /*@
        loop invariant n < INT32_MAX ==> 0 <= temp <= i;
        loop invariant n < INT32_MAX ==> cnt == (int32_t)(fb_total_zeros(i) - fb_zero_bits(temp));
        loop assigns temp, cnt;
        */
        while (temp != 0) {
            if ((temp & 1) == 0) {
                cnt = java_add(cnt, 1);
            }
            temp = temp >> 1;
        }
    }
    return cnt;
}
