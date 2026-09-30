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
  assigns \nothing;
  ensures \result == (int32_t)((integer)left - right);
*/
static int32_t java_sub(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left - (uint32_t)right);
}

/*@
  assigns \nothing;
  ensures \result == (left > right ? left : right);
*/
static int32_t java_max(int32_t left, int32_t right) {
    return left > right ? left : right;
}

/*@
  logic integer fb_extend(integer value) = value < 0 ? -1 : value + 1;
  logic integer fb_segments(integer n, integer a, integer b, integer c) =
    n < 0 ? -1 : n == 0 ? 0 :
    \max(fb_extend(fb_segments(n - a, a, b, c)),
         \max(fb_extend(fb_segments(n - b, a, b, c)), fb_extend(fb_segments(n - c, a, b, c))));
*/
/*@
  requires 0 <= n < INT32_MAX && a > 0 && b > 0 && c > 0;
  assigns \nothing;
  ensures \result == fb_segments(n, a, b, c);
  ensures -1 <= \result <= n;
*/
int32_t maximumSegments(int32_t n, int32_t a, int32_t b, int32_t c) {
    JIntArray dp = jarray_new(java_add(n, 1));
    jarray_set(dp, 0, 0);
    /*@
      loop invariant 1 <= i <= (integer)n + 1;
      loop invariant jintarray_valid_nonnull(dp) && dp->length == n + 1;
      loop invariant \forall integer k; 0 <= k < i ==> dp->data[k] == fb_segments(k, a, b, c) && -1 <= dp->data[k] <= k;
      loop assigns i, dp->data[0 .. n];
      loop variant (integer)n - i + 1;
    */
    for (int32_t i = 1; i <= n; i = java_add(i, 1)) {
        jarray_set(dp, i, -1);
        if (i >= a && jarray_get(dp, java_sub(i, a)) != -1) {
            jarray_set(dp, i, java_max(jarray_get(dp, i),
                                       java_add(jarray_get(dp, java_sub(i, a)), 1)));
        }
        if (i >= b && jarray_get(dp, java_sub(i, b)) != -1) {
            jarray_set(dp, i, java_max(jarray_get(dp, i),
                                       java_add(jarray_get(dp, java_sub(i, b)), 1)));
        }
        if (i >= c && jarray_get(dp, java_sub(i, c)) != -1) {
            jarray_set(dp, i, java_max(jarray_get(dp, i),
                                       java_add(jarray_get(dp, java_sub(i, c)), 1)));
        }
    }
    return jarray_get(dp, n);
}
