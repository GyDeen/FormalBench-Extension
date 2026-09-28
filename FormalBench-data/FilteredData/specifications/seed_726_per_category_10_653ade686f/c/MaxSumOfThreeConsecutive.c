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
  logic integer fb_three{L}(JIntArray a, integer k) =
    k <= 0 ? a->data[0] : k == 1 ? (int32_t)((integer)a->data[0] + a->data[1]) :
    k == 2 ? \max(fb_three(a, 1), \max((int32_t)((integer)a->data[1] + a->data[2]), (int32_t)((integer)a->data[0] + a->data[2]))) :
    \max(\max(fb_three(a, k - 1), (int32_t)(fb_three(a, k - 2) + a->data[k])),
         (int32_t)((integer)a->data[k] + a->data[k - 1] + fb_three(a, k - 3)));
*/
/*@
  requires jintarray_valid_nonnull(arr) && 1 <= n <= arr->length;
  assigns \nothing;
  ensures \result == fb_three(arr, n - 1);
*/
int32_t maxSumOfThreeConsecutive(JIntArray arr, int32_t n) {
    JIntArray sum = jarray_new(n);
    if (n >= 1) {
        jarray_set(sum, 0, jarray_get(arr, 0));
    }
    if (n >= 2) {
        jarray_set(sum, 1, java_add(jarray_get(sum, 0), jarray_get(arr, 1)));
    }
    if (n > 2) {
        int32_t candidate1 = jarray_get(sum, 1);
        int32_t candidate2 = java_add(jarray_get(arr, 1), jarray_get(arr, 2));
        int32_t candidate3 = java_add(jarray_get(arr, 0), jarray_get(arr, 2));
        jarray_set(sum, 2, java_max(candidate1, java_max(candidate2, candidate3)));
    }
    /*@
      loop invariant 3 <= i && (n < 3 ? i == 3 : i <= n);
      loop invariant jintarray_valid_nonnull(sum) && sum->length == n;
      loop invariant \forall integer k; 0 <= k < i && k < n ==> sum->data[k] == fb_three(arr, k);
      loop assigns i, sum->data[0 .. n - 1];
      loop variant (integer)n - i;
    */
    for (int32_t i = 3; i < n; i = java_add(i, 1)) {
        int32_t first = jarray_get(sum, java_sub(i, 1));
        int32_t second = java_add(jarray_get(sum, java_sub(i, 2)), jarray_get(arr, i));
        int32_t third = java_add(java_add(jarray_get(arr, i), jarray_get(arr, java_sub(i, 1))),
                                 jarray_get(sum, java_sub(i, 3)));
        jarray_set(sum, i, java_max(java_max(first, second), third));
    }
    return jarray_get(sum, java_sub(n, 1));
}
