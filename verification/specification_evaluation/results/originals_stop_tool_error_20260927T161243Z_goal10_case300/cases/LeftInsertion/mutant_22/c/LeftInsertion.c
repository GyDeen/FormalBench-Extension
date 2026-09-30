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
requires right != 0;
assigns \nothing;
ensures \result == (left == INT32_MIN && right == -1 ? INT32_MIN : (integer)left / right);
*/
static int32_t java_div(int32_t left, int32_t right) { if (right == 0) { fputs("JAVA_ARITHMETIC_ERROR: DIVIDE_BY_ZERO\n", stderr); exit(75); } if (left == INT32_MIN && right == -1) return INT32_MIN; return left / right; }

/*@
logic integer fb_mid(integer lo, integer hi) = (int32_t)(lo + hi) / 2;
logic integer fb_search{L}(JIntArray a, integer x, integer lo, integer hi) =
  lo > hi ? lo : fb_mid(lo, hi) < 0 || fb_mid(lo, hi) >= a->length ? -1 :
  a->data[fb_mid(lo, hi)] == x ? fb_mid(lo, hi) : a->data[fb_mid(lo, hi)] < x ?
  fb_search(a, x, fb_mid(lo, hi) + 1, hi) : fb_search(a, x, lo, fb_mid(lo, hi) - 1);
*/
/*@
requires jintarray_valid_nonnull(a);
requires fb_search(a, x, 0, a->length - 1) >= 0;
assigns \nothing;
ensures \result == fb_search(a, x, 0, a->length - 1);
ensures 0 <= \result <= a->length;
ensures (\forall integer p, q; 0 <= p < q < a->length ==> a->data[p] <= a->data[q]) ==>
  (\forall integer k; 0 <= k < \result ==> a->data[k] <= x) &&
  (\forall integer k; \result <= k < a->length ==> x <= a->data[k]);
*/
int32_t leftInsertion(JIntArray a, int32_t x) {
    int32_t left = INT32_C(0);
    int32_t right = java_sub(jarray_length(a), INT32_C(1));
    /*@
    loop invariant 0 <= left <= a->length && -1 <= right < a->length && left <= (integer)right + 1;
    loop invariant fb_search(a, x, 0, a->length - 1) == fb_search(a, x, left, right) && fb_search(a, x, left, right) >= 0;
    loop assigns left, right;
    loop variant (integer)right - left + 1;
    */
    while ((left <= right)) {
        int32_t mid = java_div(java_add(left, right), INT32_C(2));
        if ((jarray_get(a, mid) == x)) {
            return mid;
        } else {
            if (false) {
                left = java_add(mid, INT32_C(1));
            } else {
                right = java_sub(mid, INT32_C(1));
            }
        }
    }
    return left;
}
