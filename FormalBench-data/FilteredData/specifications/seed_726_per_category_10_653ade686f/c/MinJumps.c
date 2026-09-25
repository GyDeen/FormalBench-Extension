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
  ensures \result == (left < right ? left : right);
*/
static int32_t java_min(int32_t left, int32_t right) {
    return left < right ? left : right;
}

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
    int32_t length = jarray_length(dp);
    /*@
      loop invariant 0 <= i <= length && length == n;
      loop invariant jintarray_valid_nonnull(dp) && dp->length == n;
      loop invariant \forall integer k; 0 <= k < i ==> dp->data[k] == INT32_MAX;
      loop assigns i, dp->data[0 .. n - 1];
      loop variant n - i;
    */
    for (int32_t i = 0; i < length; i = java_add(i, 1)) {
        jarray_set(dp, i, INT32_MAX);
    }
    jarray_set(dp, 0, 0);

    /*@
      loop invariant 1 <= i <= n;
      loop invariant jintarray_valid_nonnull(dp) && dp->length == n;
      loop invariant \forall integer k; 0 <= k < i ==> dp->data[k] == (k == 0 ? 0 : fb_jumps(arr, k, k));
      loop invariant \forall integer k; i <= k < n ==> dp->data[k] == INT32_MAX;
      loop assigns i, dp->data[0 .. n - 1];
      loop variant n - i;
    */
    for (int32_t i = 1; i < n; i = java_add(i, 1)) {
        /*@
          loop invariant 0 <= j <= i;
          loop invariant jintarray_valid_nonnull(dp) && dp->length == n;
          loop invariant dp->data[i] == fb_jumps(arr, i, j);
          loop assigns j, dp->data[i];
          loop variant i - j;
        */
        for (int32_t j = 0; j < i; j = java_add(j, 1)) {
            int32_t reach = java_add(jarray_get(arr, j), j);
            if (reach >= i) {
                int32_t candidate = java_add(jarray_get(dp, j), 1);
                jarray_set(dp, i, java_min(jarray_get(dp, i), candidate));
            }
        }
    }
    return jarray_get(dp, java_sub(n, 1));
}
