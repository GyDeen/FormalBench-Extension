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
  requires jintarray_valid_nonnull(l1);
  assigns \nothing;
  frees \nothing;
  ensures jintarray2_valid_nonnull(\result);
  ensures \result->length == (l1->length < 2 ? 0 : l1->length - 1);
  ensures \fresh(\result, sizeof(*\result));
  ensures \result->length > 0 ==> \fresh(\result->data, \result->length * sizeof(JIntArray));
  ensures \forall integer k; 0 <= k < \result->length ==>
    jintarray_valid_nonnull(\result->data[k]) && \result->data[k]->length == 2 &&
    \fresh(\result->data[k], sizeof(*\result->data[k])) &&
    \fresh(\result->data[k]->data, 2 * sizeof(int32_t)) &&
    \result->data[k]->data[0] == l1->data[k] && \result->data[k]->data[1] == l1->data[k + 1];
  ensures \forall integer p, q; 0 <= p < q < \result->length ==>
    \separated(\result->data[p], \result->data[q]) &&
    \separated(\result->data[p]->data + (0 .. 1), \result->data[q]->data + (0 .. 1));
*/
JIntArray2 pairWise(JIntArray l1) {
    if ((jarray_length(l1) < INT32_C(2))) {
        return jarray2_new(INT32_C(0), INT32_C(0));
    }
    JIntArray2 result = jarray2_new(java_sub(jarray_length(l1), INT32_C(1)), INT32_C(2));
    /*@
      loop invariant 0 <= i <= l1->length - 1;
      loop invariant jintarray2_valid_nonnull(result) && result->length == l1->length - 1;
      loop invariant \forall integer k; 0 <= k < l1->length - 1 ==> result->data[k] != \null && result->data[k]->length == 2;
      loop invariant \forall integer k; 0 <= k < i ==> result->data[k]->data[0] == l1->data[k] && result->data[k]->data[1] == l1->data[k + 1];
      loop assigns i, result->data[0 .. l1->length - 2]->data[0 .. 1];
      loop variant l1->length - 1 - i;
    */
    for (int32_t i = INT32_C(0); (i < java_sub(jarray_length(l1), INT32_C(1))); i = java_add(i, INT32_C(1))) {
        int32_t __value_1 = jarray_get(l1, i);
        jarray_set(jarray2_get(result, i), INT32_C(0), __value_1);
        int32_t __value_2 = jarray_get(l1, java_add(i, INT32_C(1)));
        jarray_set(jarray2_get(result, i), INT32_C(1), __value_2);
    }
    return result;
}
