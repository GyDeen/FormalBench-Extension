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
    if ((jarray_is_null(testArray) || (jarray_length(testArray) == INT32_C(0)))) {
        return testArray;
    }

    JIntArray res = jarray_new(jarray_length(testArray));
    int32_t __value_1 = jarray_get(testArray, java_sub(jarray_length(testArray), INT32_C(1)));
    jarray_set(res, INT32_C(0), __value_1);
    /*@
      loop invariant 0 <= __copy_index_2 <= testArray->length - 1;
      loop invariant jintarray_valid_nonnull(res) && res->length == testArray->length;
      loop invariant res->data[0] == testArray->data[testArray->length - 1];
      loop invariant \forall integer k; 1 <= k <= __copy_index_2 ==> res->data[k] == testArray->data[k - 1];
      loop assigns __copy_index_2, res->data[1 .. testArray->length - 1];
      loop variant testArray->length - 1 - __copy_index_2;
    */
    for (int32_t __copy_index_2 = 0; __copy_index_2 < java_sub(jarray_length(testArray), INT32_C(1)); __copy_index_2 = java_add(__copy_index_2, INT32_C(1))) {
        jarray_set(res, java_add(INT32_C(1), __copy_index_2), jarray_get(testArray, java_add(INT32_C(0), __copy_index_2)));
    }
    return res;
}
