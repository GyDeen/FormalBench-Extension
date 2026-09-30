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
  ensures \result == (int32_t)((integer)left * right);
*/
static int32_t java_mul(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left * (uint32_t)right);
}

/*@
  logic integer fb_product{L}(JIntArray a, integer lo, integer hi) =
    hi <= lo ? 1 : fb_product(a, lo, hi - 1) * a->data[hi - 1];
  logic integer fb_products_from{L}(JIntArray a, integer lo, integer hi) =
    hi <= lo ? 0 : fb_products_from(a, lo, hi - 1) + fb_product(a, lo, hi);
  logic integer fb_all_products{L}(JIntArray a, integer n, integer starts) =
    starts <= 0 ? 0 : fb_all_products(a, n, starts - 1) + fb_products_from(a, starts - 1, n);
*/
/*@
  requires n <= 0 || (jintarray_valid_nonnull(arr) && n <= arr->length);
  assigns \nothing;
  ensures \result == (int32_t)fb_all_products(arr, n, n);
*/
int32_t sumOfSubarrayProd(JIntArray arr, int32_t n) {
    int32_t sum = 0;
    /*@
      loop invariant 0 <= i <= (n > 0 ? n : 0);
      loop invariant sum == (int32_t)fb_all_products(arr, n, i);
      loop assigns i, sum;
      loop variant (integer)n - i;
    */
    for (int32_t i = 0; i < n; i = java_add(i, 1)) {
        int32_t product = 1;
        /*@
          loop invariant i <= j <= n;
          loop invariant product == (int32_t)fb_product(arr, i, j);
          loop invariant sum == (int32_t)(fb_all_products(arr, n, i) + fb_products_from(arr, i, j));
          loop assigns j, product, sum;
          loop variant n - j;
        */
        for (int32_t j = i; j < n; j = java_add(j, 1)) {
            product = java_mul(product, jarray_get(arr, j));
            sum = java_add(sum, product);
        }
    }
    return sum;
}
