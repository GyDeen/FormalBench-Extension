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
  ensures \result == (int32_t)((integer)left * right);
*/
static int32_t java_mul(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left * (uint32_t)right);
}

/*@
  logic integer fb_way(integer k, integer side) =
    k <= 0 ? (side == 0 ? 1 : 0) : k == 1 ? (side == 0 ? 0 : 1) :
    side == 0 ? fb_way(k - 2, 0) + 2 * fb_way(k - 1, 1) : fb_way(k - 1, 0) + fb_way(k - 2, 1);
  logic integer fb_A(integer k) = fb_way(k, 0);
  logic integer fb_B(integer k) = fb_way(k, 1);
*/
/*@
  requires 1 <= n < INT32_MAX;
  assigns \nothing;
  ensures \result == (int32_t)fb_A(n);
*/
int32_t countWays(int32_t n) {
    JIntArray A = jarray_new(java_add(n, 1));
    JIntArray B = jarray_new(java_add(n, 1));
    jarray_set(A, 0, 1);
    jarray_set(A, 1, 0);
    jarray_set(B, 0, 0);
    jarray_set(B, 1, 1);
    /*@
      loop invariant 2 <= i <= (integer)n + 1;
      loop invariant jintarray_valid_nonnull(A) && A->length == n + 1;
      loop invariant jintarray_valid_nonnull(B) && B->length == n + 1;
      loop invariant \forall integer k; 0 <= k < i ==> A->data[k] == (int32_t)fb_A(k) && B->data[k] == (int32_t)fb_B(k);
      loop assigns i, A->data[0 .. n], B->data[0 .. n];
      loop variant (integer)n - i + 1;
    */
    for (int32_t i = 2; i <= n; i = java_add(i, 1)) {
        int32_t a_previous = jarray_get(A, java_sub(i, 2));
        int32_t b_previous = jarray_get(B, java_sub(i, 1));
        int32_t a_value = java_add(a_previous, java_mul(2, b_previous));
        int32_t b_value = java_add(jarray_get(A, java_sub(i, 1)),
                                   jarray_get(B, java_sub(i, 2)));
        jarray_set(A, i, a_value);
        jarray_set(B, i, b_value);
    }
    return jarray_get(A, n);
}
