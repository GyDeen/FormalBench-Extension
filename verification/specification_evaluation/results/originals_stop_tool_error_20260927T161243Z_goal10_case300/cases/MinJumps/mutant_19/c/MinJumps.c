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
static int32_t java_div(int32_t left, int32_t right) { if (right == 0) { fputs("JAVA_ARITHMETIC_ERROR: DIVIDE_BY_ZERO\n", stderr); exit(75); } if (left == INT32_MIN && right == -1) return INT32_MIN; return left / right; }
/*@
  assigns \nothing;
  ensures \result == (left < right ? left : right);
*/
static int32_t java_min(int32_t left, int32_t right) { return left < right ? left : right; }

/*@
  logic integer fb_jumps{L}(JIntArray a, integer i, integer j) =
    i <= 0 ? 0 : j <= 0 ? INT32_MAX : \min(fb_jumps(a, i, j - 1),
    (int32_t)((integer)a->data[j - 1] + j - 1) >= i ? (int32_t)(fb_jumps(a, j - 1, j - 1) + 1) : INT32_MAX);
*/
/*@
  requires n >= 1 && (n == 1 || (jintarray_valid_nonnull(arr) && n - 1 <= arr->length));
  assigns \nothing;
  ensures \result == (n == 1 ? 0 : fb_jumps(arr, n - 1, n - 1));
*/
int32_t minJumps(JIntArray arr, int32_t n) {
    JIntArray dp = jarray_new(n);
    /*@
      loop invariant 0 <= __fill_index_1 <= (dp->length) && (dp->length) == n;
      loop invariant jintarray_valid_nonnull(dp) && dp->length == n;
      loop invariant \forall integer k; 0 <= k < __fill_index_1 ==> dp->data[k] == INT32_MAX;
      loop assigns __fill_index_1, dp->data[0 .. n - 1];
      loop variant n - __fill_index_1;
    */
    for (int32_t __fill_index_1 = 0; __fill_index_1 < jarray_length(dp); __fill_index_1 = java_add(__fill_index_1, INT32_C(1))) {
        jarray_set(dp, __fill_index_1, INT32_MAX);
    }
    int32_t __value_2 = INT32_C(0);
    jarray_set(dp, INT32_C(0), __value_2);
    /*@
      loop invariant 1 <= i <= n;
      loop invariant jintarray_valid_nonnull(dp) && dp->length == n;
      loop invariant \forall integer k; 0 <= k < i ==> dp->data[k] == (k == 0 ? 0 : fb_jumps(arr, k, k));
      loop invariant \forall integer k; i <= k < n ==> dp->data[k] == INT32_MAX;
      loop assigns i, dp->data[0 .. n - 1];
      loop variant n - i;
    */
    for (int32_t i = INT32_C(1); (i < n); i = java_add(i, INT32_C(1))) {
        /*@
          loop invariant 0 <= j <= i;
          loop invariant jintarray_valid_nonnull(dp) && dp->length == n;
          loop invariant dp->data[i] == fb_jumps(arr, i, j);
          loop assigns j, dp->data[i];
          loop variant i - j;
        */
        for (int32_t j = INT32_C(0); (j < i); j = java_add(j, INT32_C(1))) {
            if ((java_add(jarray_get(arr, j), j) >= i)) {
                int32_t __value_3 = java_min(jarray_get(dp, i), java_div(jarray_get(dp, j), INT32_C(1)));
                jarray_set(dp, i, __value_3);
            }
        }
    }
    return jarray_get(dp, java_sub(n, INT32_C(1)));
}
