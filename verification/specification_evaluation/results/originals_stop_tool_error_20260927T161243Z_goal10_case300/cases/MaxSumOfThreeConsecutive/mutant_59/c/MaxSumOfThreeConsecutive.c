#include "java_arrays.h"
#include <stdint.h>

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
  ensures \result == (left > right ? left : right);
*/
static int32_t java_max(int32_t left, int32_t right) { return left > right ? left : right; }

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
    if ((n >= INT32_C(1))) {
        int32_t __value_1 = jarray_get(arr, INT32_C(0));
        jarray_set(sum, INT32_C(0), __value_1);
    }
    if ((n >= INT32_C(2))) {
        int32_t __value_2 = java_add(jarray_get(sum, INT32_C(0)), jarray_get(arr, INT32_C(1)));
        jarray_set(sum, INT32_C(1), __value_2);
    }
    if ((n > INT32_C(2))) {
        int32_t __value_3 = java_max(jarray_get(sum, INT32_C(1)), java_max(java_add(jarray_get(arr, INT32_C(1)), jarray_get(arr, INT32_C(2))), java_add(jarray_get(arr, INT32_C(0)), jarray_get(arr, INT32_C(2)))));
        jarray_set(sum, INT32_C(2), __value_3);
    }
    /*@
      loop invariant 3 <= i && (n < 3 ? i == 3 : i <= n);
      loop invariant jintarray_valid_nonnull(sum) && sum->length == n;
      loop invariant \forall integer k; 0 <= k < i && k < n ==> sum->data[k] == fb_three(arr, k);
      loop assigns i, sum->data[0 .. n - 1];
      loop variant (integer)n - i;
    */
    for (int32_t i = INT32_C(3); (i < n); i = java_add(i, INT32_C(1))) {
        int32_t __value_4 = java_max(java_max(jarray_get(sum, java_sub(i, INT32_C(1))), java_add(jarray_get(sum, java_sub(i, INT32_C(2))), jarray_get(arr, i))), java_add(java_add(jarray_get(arr, i), jarray_get(arr, java_sub(i, INT32_C(1)))), jarray_get(sum, java_sub(i, INT32_C(3)))));
        jarray_set(sum, i, __value_4);
    }
    return jarray_get(sum, java_add(n, INT32_C(1)));
}
