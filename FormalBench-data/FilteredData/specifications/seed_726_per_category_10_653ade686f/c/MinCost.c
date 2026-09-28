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
    int32_t rows = java_add(m, 1);
    int32_t columns = java_add(n, 1);
    JIntArray2 tc = jarray2_new(rows, columns);

    jarray_set(jarray2_get(tc, 0), 0,
               jarray_get(jarray2_get(cost, 0), 0));
    /*@
      loop invariant 1 <= i <= (integer)m + 1;
      loop invariant jintarray2_valid_nonnull(tc);
      loop invariant \forall integer r; 0 <= r < i ==> tc->data[r]->data[0] == fb_cost(cost, r, 0);
      loop assigns i, tc->data[0 .. m]->data[0];
      loop variant (integer)m - i + 1;
    */
    for (int32_t i = 1; i <= m; i = java_add(i, 1)) {
        int32_t value = java_add(
            jarray_get(jarray2_get(tc, java_sub(i, 1)), 0),
            jarray_get(jarray2_get(cost, i), 0));
        jarray_set(jarray2_get(tc, i), 0, value);
    }
    /*@
      loop invariant 1 <= j <= (integer)n + 1;
      loop invariant jintarray2_valid_nonnull(tc);
      loop invariant \forall integer c; 0 <= c < j ==> tc->data[0]->data[c] == fb_cost(cost, 0, c);
      loop assigns j, tc->data[0]->data[0 .. n];
      loop variant (integer)n - j + 1;
    */
    for (int32_t j = 1; j <= n; j = java_add(j, 1)) {
        int32_t value = java_add(
            jarray_get(jarray2_get(tc, 0), java_sub(j, 1)),
            jarray_get(jarray2_get(cost, 0), j));
        jarray_set(jarray2_get(tc, 0), j, value);
    }
    /*@
      loop invariant 1 <= i <= (integer)m + 1;
      loop invariant jintarray2_valid_nonnull(tc);
      loop invariant \forall integer r, c; 0 <= r < i && 0 <= c <= n ==> tc->data[r]->data[c] == fb_cost(cost, r, c);
      loop invariant \forall integer r; i <= r <= m ==> tc->data[r]->data[0] == fb_cost(cost, r, 0);
      loop assigns i, tc->data[0 .. m]->data[0 .. n];
      loop variant (integer)m - i + 1;
    */
    for (int32_t i = 1; i <= m; i = java_add(i, 1)) {
        /*@
          loop invariant 1 <= j <= (integer)n + 1;
          loop invariant jintarray2_valid_nonnull(tc);
          loop invariant \forall integer c; 0 <= c < j ==> tc->data[i]->data[c] == fb_cost(cost, i, c);
          loop assigns j, tc->data[i]->data[0 .. n];
          loop variant (integer)n - j + 1;
        */
        for (int32_t j = 1; j <= n; j = java_add(j, 1)) {
            int32_t diagonal = jarray_get(jarray2_get(tc, java_sub(i, 1)), java_sub(j, 1));
            int32_t above = jarray_get(jarray2_get(tc, java_sub(i, 1)), j);
            int32_t left = jarray_get(jarray2_get(tc, i), java_sub(j, 1));
            int32_t best = java_min(diagonal, java_min(above, left));
            int32_t value = java_add(best, jarray_get(jarray2_get(cost, i), j));
            jarray_set(jarray2_get(tc, i), j, value);
        }
    }
    return jarray_get(jarray2_get(tc, m), n);
}
