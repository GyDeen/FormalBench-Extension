#include "java_arrays.h"
#include <stdint.h>

/*@
  assigns \nothing;
  ensures \result == (int32_t)((integer)left + right);
*/
static int32_t java_add(int32_t left, int32_t right) { return (int32_t)((uint32_t)left + (uint32_t)right); }
/*@
  assigns \nothing;
  ensures \result == (int32_t)((integer)left * right);
*/
static int32_t java_mul(int32_t left, int32_t right) { return (int32_t)((uint32_t)left * (uint32_t)right); }

/*@
  logic integer fb_product_end{L}(JIntArray a, integer i, integer j) =
    j <= 0 ? a->data[i] : \max(fb_product_end(a, i, j - 1),
    a->data[i] > a->data[j - 1] ? (int32_t)(fb_product_end(a, j - 1, j - 1) * a->data[i]) : a->data[i]);
  logic integer fb_product_best{L}(JIntArray a, integer k) =
    k <= 1 ? a->data[0] : \max(fb_product_best(a, k - 1), fb_product_end(a, k - 1, k - 1));
*/
/*@
  requires jintarray_valid_nonnull(arr) && 1 <= n <= arr->length;
  assigns \nothing;
  ensures \result == fb_product_best(arr, n);
*/
int32_t maxProduct(JIntArray arr, int32_t n) {
    JIntArray mpis = jarray_new(n);
    /*@
      loop invariant 0 <= i <= n;
      loop invariant jintarray_valid_nonnull(mpis) && mpis->length == n;
      loop invariant \forall integer k; 0 <= k < i ==> mpis->data[k] == arr->data[k];
      loop assigns i, mpis->data[0 .. n - 1];
      loop variant n - i;
    */
    for (int32_t i = INT32_C(0); (i < n); i = java_add(i, INT32_C(1))) {
        int32_t __value_1 = jarray_get(arr, i);
        jarray_set(mpis, i, __value_1);
    }
    /*@
      loop invariant 1 <= i <= n;
      loop invariant jintarray_valid_nonnull(mpis) && mpis->length == n;
      loop invariant \forall integer k; 0 <= k < i ==> mpis->data[k] == fb_product_end(arr, k, k);
      loop invariant \forall integer k; i <= k < n ==> mpis->data[k] == arr->data[k];
      loop assigns i, mpis->data[0 .. n - 1];
      loop variant n - i;
    */
    for (int32_t i = INT32_C(1); (i < n); i = java_add(i, INT32_C(1))) {
        /*@
          loop invariant 0 <= j <= i;
          loop invariant jintarray_valid_nonnull(mpis) && mpis->length == n;
          loop invariant mpis->data[i] == fb_product_end(arr, i, j);
          loop assigns j, mpis->data[i];
          loop variant i - j;
        */
        for (int32_t j = INT32_C(0); (j < i); j = java_add(j, INT32_C(1))) {
            if (((jarray_get(arr, i) > jarray_get(arr, j)) && (jarray_get(mpis, i) < java_mul(jarray_get(mpis, j), jarray_get(arr, i))))) {
                ;
            }
        }
    }
    int32_t max = jarray_get(mpis, INT32_C(0));
    /*@
      loop invariant 1 <= i <= n;
      loop invariant max == fb_product_best(arr, i);
      loop assigns i, max;
      loop variant n - i;
    */
    for (int32_t i = INT32_C(1); (i < n); i = java_add(i, INT32_C(1))) {
        if ((jarray_get(mpis, i) > max)) {
            max = jarray_get(mpis, i);
        }
    }
    return max;
}
