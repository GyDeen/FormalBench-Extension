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
    JIntArray A = jarray_new(java_add(n, INT32_C(1)));
    JIntArray B = jarray_new(java_add(n, INT32_C(1)));
    int32_t __value_1 = INT32_C(1);
    jarray_set(A, INT32_C(0), __value_1);
    int32_t __value_2 = INT32_C(0);
    jarray_set(A, INT32_C(1), __value_2);
    int32_t __value_3 = INT32_C(0);
    jarray_set(B, INT32_C(0), __value_3);
    int32_t __value_4 = INT32_C(1);
    jarray_set(B, INT32_C(1), __value_4);
    /*@
      loop invariant 2 <= i <= (integer)n + 1;
      loop invariant jintarray_valid_nonnull(A) && A->length == n + 1;
      loop invariant jintarray_valid_nonnull(B) && B->length == n + 1;
      loop invariant \forall integer k; 0 <= k < i ==> A->data[k] == (int32_t)fb_A(k) && B->data[k] == (int32_t)fb_B(k);
      loop assigns i, A->data[0 .. n], B->data[0 .. n];
      loop variant (integer)n - i + 1;
    */
    for (int32_t i = INT32_C(2); (i <= n); i = java_add(i, INT32_C(1))) {
        int32_t __value_5 = java_add(jarray_get(A, java_sub(i, INT32_C(2))), java_mul(INT32_C(2), jarray_get(B, java_sub(i, INT32_C(1)))));
        jarray_set(A, i, __value_5);
        int32_t __value_6 = java_add(jarray_get(A, java_sub(i, INT32_C(1))), jarray_get(B, java_mod(i, INT32_C(2))));
        jarray_set(B, i, __value_6);
    }
    return jarray_get(A, n);
}
