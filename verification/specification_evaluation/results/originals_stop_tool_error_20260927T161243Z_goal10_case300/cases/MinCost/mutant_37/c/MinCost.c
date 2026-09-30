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
  ensures \result == (left < right ? left : right);
*/
static int32_t java_min(int32_t left, int32_t right) { return left < right ? left : right; }

/*@
  logic integer fb_cost{L}(JIntArray2 a, integer r, integer c) =
    r <= 0 && c <= 0 ? a->data[0]->data[0] :
    r <= 0 ? (int32_t)(fb_cost(a, 0, c - 1) + a->data[0]->data[c]) :
    c <= 0 ? (int32_t)(fb_cost(a, r - 1, 0) + a->data[r]->data[0]) :
    (int32_t)(\min(fb_cost(a, r - 1, c - 1), \min(fb_cost(a, r - 1, c), fb_cost(a, r, c - 1))) + a->data[r]->data[c]);
*/
/*@
  requires jintarray2_valid_nonnull(cost) && 0 <= m < cost->length && n >= 0;
  requires \forall integer k; 0 <= k <= m ==> cost->data[k] != \null && n < cost->data[k]->length;
  assigns \nothing;
  ensures \result == fb_cost(cost, m, n);
*/
int32_t minCost(JIntArray2 cost, int32_t m, int32_t n) {
    JIntArray2 tc = jarray2_new(java_add(m, INT32_C(1)), java_add(n, INT32_C(1)));
    int32_t __value_1 = jarray_get(jarray2_get(cost, INT32_C(0)), INT32_C(0));
    jarray_set(jarray2_get(tc, INT32_C(0)), INT32_C(0), __value_1);
    /*@
      loop invariant 1 <= i <= (integer)m + 1;
      loop invariant jintarray2_valid_nonnull(tc);
      loop invariant \forall integer r; 0 <= r < i ==> tc->data[r]->data[0] == fb_cost(cost, r, 0);
      loop assigns i, tc->data[0 .. m]->data[0];
      loop variant (integer)m - i + 1;
    */
    for (int32_t i = INT32_C(1); (i <= m); i = java_add(i, INT32_C(1))) {
        int32_t __value_2 = java_add(jarray_get(jarray2_get(tc, java_sub(i, INT32_C(1))), INT32_C(0)), jarray_get(jarray2_get(cost, i), INT32_C(0)));
        jarray_set(jarray2_get(tc, i), INT32_C(0), __value_2);
    }
    /*@
      loop invariant 1 <= j <= (integer)n + 1;
      loop invariant jintarray2_valid_nonnull(tc);
      loop invariant \forall integer c; 0 <= c < j ==> tc->data[0]->data[c] == fb_cost(cost, 0, c);
      loop assigns j, tc->data[0]->data[0 .. n];
      loop variant (integer)n - j + 1;
    */
    for (int32_t j = INT32_C(1); (j <= n); j = java_add(j, INT32_C(1))) {
        int32_t __value_3 = java_add(jarray_get(jarray2_get(tc, INT32_C(0)), java_sub(j, INT32_C(1))), jarray_get(jarray2_get(cost, INT32_C(0)), j));
        jarray_set(jarray2_get(tc, INT32_C(0)), j, __value_3);
    }
    /*@
      loop invariant 1 <= i <= (integer)m + 1;
      loop invariant jintarray2_valid_nonnull(tc);
      loop invariant \forall integer r, c; 0 <= r < i && 0 <= c <= n ==> tc->data[r]->data[c] == fb_cost(cost, r, c);
      loop invariant \forall integer r; i <= r <= m ==> tc->data[r]->data[0] == fb_cost(cost, r, 0);
      loop assigns i, tc->data[0 .. m]->data[0 .. n];
      loop variant (integer)m - i + 1;
    */
    for (int32_t i = INT32_C(1); (i <= m); i = java_add(i, INT32_C(1))) {
        /*@
          loop invariant 1 <= j <= (integer)n + 1;
          loop invariant jintarray2_valid_nonnull(tc);
          loop invariant \forall integer c; 0 <= c < j ==> tc->data[i]->data[c] == fb_cost(cost, i, c);
          loop assigns j, tc->data[i]->data[0 .. n];
          loop variant (integer)n - j + 1;
        */
        for (int32_t j = INT32_C(1); (j < n); j = java_add(j, INT32_C(1))) {
            int32_t __value_4 = java_add(java_min(jarray_get(jarray2_get(tc, java_sub(i, INT32_C(1))), java_sub(j, INT32_C(1))), java_min(jarray_get(jarray2_get(tc, java_sub(i, INT32_C(1))), j), jarray_get(jarray2_get(tc, i), java_sub(j, INT32_C(1))))), jarray_get(jarray2_get(cost, i), j));
            jarray_set(jarray2_get(tc, i), j, __value_4);
        }
    }
    return jarray_get(jarray2_get(tc, m), n);
}
