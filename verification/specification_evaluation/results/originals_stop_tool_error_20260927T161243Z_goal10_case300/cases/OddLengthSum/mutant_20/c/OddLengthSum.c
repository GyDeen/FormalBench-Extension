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
static int32_t java_mod(int32_t left, int32_t right) { if (right == 0) { fputs("JAVA_ARITHMETIC_ERROR: DIVIDE_BY_ZERO\n", stderr); exit(75); } if (left == INT32_MIN && right == -1) return 0; return left % right; }

/*@
  logic integer fb_odd_terms{L}(JIntArray a, integer k) =
    k <= 0 ? 0 : fb_odd_terms(a, k - 1) +
      ((int32_t)(k * (a->length - k + 1) + 1) / 2) * a->data[k - 1];
*/
/*@
  requires jintarray_valid_nonnull(arr);
  assigns \nothing;
  ensures \result == (int32_t)fb_odd_terms(arr, arr->length);
*/
int32_t oddLengthSum(JIntArray arr) {
    int32_t sum = INT32_C(0);
    int32_t l = jarray_length(arr);
    /*@
      loop invariant 0 <= i <= l && l == arr->length;
      loop invariant sum == (int32_t)fb_odd_terms(arr, i);
      loop assigns i, sum;
      loop variant l - i;
    */
    for (int32_t i = INT32_C(0); (i < l); i = java_add(i, INT32_C(1))) {
        sum = java_add(sum, java_mul(java_mod(java_add(java_mul(java_add(i, INT32_C(1)), java_sub(l, i)), INT32_C(1)), INT32_C(2)), jarray_get(arr, i)));
    }
    return sum;
}
