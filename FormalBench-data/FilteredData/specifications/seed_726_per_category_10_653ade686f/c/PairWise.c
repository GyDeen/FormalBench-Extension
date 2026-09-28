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
    int32_t length = jarray_length(l1);
    if (length < 2) {
        return jarray2_new(0, 0);
    }
    JIntArray2 result = jarray2_new(java_sub(length, 1), 2);
    int32_t limit = java_sub(length, 1);
    /*@
      loop invariant 0 <= i <= limit && limit == l1->length - 1;
      loop invariant jintarray2_valid_nonnull(result) && result->length == limit;
      loop invariant \forall integer k; 0 <= k < limit ==> result->data[k] != \null && result->data[k]->length == 2;
      loop invariant \forall integer k; 0 <= k < i ==> result->data[k]->data[0] == l1->data[k] && result->data[k]->data[1] == l1->data[k + 1];
      loop assigns i, result->data[0 .. limit - 1]->data[0 .. 1];
      loop variant limit - i;
    */
    for (int32_t i = 0; i < limit; i = java_add(i, 1)) {
        JIntArray row = jarray2_get(result, i);
        jarray_set(row, 0, jarray_get(l1, i));
        jarray_set(row, 1, jarray_get(l1, java_add(i, 1)));
    }
    return result;
}
