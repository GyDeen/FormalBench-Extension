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
/*@
assigns \nothing;
ensures \result == (int32_t)((integer)left * right);
*/
static int32_t java_mul(int32_t left, int32_t right) { return (int32_t)((uint32_t)left * (uint32_t)right); }
/*@
requires right != 0;
assigns \nothing;
ensures \result == (left == INT32_MIN && right == -1 ? INT32_MIN : (integer)left / right);
*/
static int32_t java_div(int32_t left, int32_t right) { if (right == 0) { fputs("JAVA_ARITHMETIC_ERROR: DIVIDE_BY_ZERO\n", stderr); exit(75); } if (left == INT32_MIN && right == -1) return INT32_MIN; return left / right; }
static int32_t java_neg(int32_t value) { return (int32_t)(0u - (uint32_t)value); }

/*@
logic integer fb_mid(integer lo, integer hi) = lo + (hi - lo) / 2;
logic integer fb_root(integer n, integer lo, integer hi) =
  lo > hi ? hi : (int32_t)(fb_mid(lo, hi) * fb_mid(lo, hi)) == n ? fb_mid(lo, hi) :
  (int32_t)(fb_mid(lo, hi) * fb_mid(lo, hi)) < n ? fb_root(n, fb_mid(lo, hi) + 1, hi) : fb_root(n, lo, fb_mid(lo, hi) - 1);
*/
/*@
terminates num < INT32_MAX;
assigns \nothing;
ensures num < INT32_MAX;
ensures num < 0 ==> \result == -1;
ensures 0 <= num < INT32_MAX ==> \result == fb_root(num, 0, num);
ensures 0 <= num <= 46340 ==> 0 <= \result && (integer)\result * \result <= num && num < ((integer)\result + 1) * ((integer)\result + 1);
*/
int32_t sqrtRoot(int32_t num) {
    if ((num < INT32_C(0))) {
        return java_neg(INT32_C(1));
    }
    int32_t left = INT32_C(0);
    int32_t right = num;
    /*@
    loop invariant num < INT32_MAX ==> 0 <= left <= (integer)num + 1 && -1 <= right <= num && left <= (integer)right + 1;
    loop invariant num < INT32_MAX ==> fb_root(num, 0, num) == fb_root(num, left, right);
    loop assigns left, right;
    */
    while ((left <= right)) {
        int32_t mid = java_add(left, java_div(java_sub(right, left), INT32_C(2)));
        if ((java_mul(mid, mid) == num)) {
            return mid;
        } else {
            if ((java_mul(mid, mid) < num)) {
                left = java_add(mid, INT32_C(1));
            } else {
                right = java_mul(mid, INT32_C(1));
            }
        }
    }
    return right;
}
