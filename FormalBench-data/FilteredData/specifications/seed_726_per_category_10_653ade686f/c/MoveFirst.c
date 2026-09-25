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
  requires jintarray_valid(testArray);
  assigns \nothing;
  frees \nothing;
  ensures testArray == \null ==> \result == \null;
  ensures testArray != \null && testArray->length == 0 ==> \result == testArray;
  ensures testArray != \null && testArray->length > 0 ==> jintarray_valid_nonnull(\result) && \result->length == testArray->length;
  ensures testArray != \null && testArray->length > 0 ==> \fresh(\result, sizeof(*\result)) && \fresh(\result->data, \result->length * sizeof(int32_t));
  ensures testArray != \null && testArray->length > 0 ==> \result->data[0] == testArray->data[testArray->length - 1];
  ensures testArray != \null && testArray->length > 0 ==> (\forall integer k; 1 <= k < testArray->length ==> \result->data[k] == testArray->data[k - 1]);
*/
JIntArray moveFirst(JIntArray testArray) {
    if (jarray_is_null(testArray) || jarray_length(testArray) == 0) {
        return testArray;
    }

    int32_t length = jarray_length(testArray);
    JIntArray res = jarray_new(length);
    jarray_set(res, 0, jarray_get(testArray, java_sub(length, 1)));
    int32_t copyLength = java_sub(length, 1);
    /*@
      loop invariant 0 <= i <= copyLength;
      loop invariant copyLength == length - 1 && length == testArray->length;
      loop invariant jintarray_valid_nonnull(res) && res->length == length;
      loop invariant res->data[0] == testArray->data[length - 1];
      loop invariant \forall integer k; 1 <= k <= i ==> res->data[k] == testArray->data[k - 1];
      loop assigns i, res->data[1 .. length - 1];
      loop variant copyLength - i;
    */
    for (int32_t i = 0; i < copyLength; i = java_add(i, 1)) {
        jarray_set(res, java_add(i, 1), jarray_get(testArray, i));
    }
    return res;
}
