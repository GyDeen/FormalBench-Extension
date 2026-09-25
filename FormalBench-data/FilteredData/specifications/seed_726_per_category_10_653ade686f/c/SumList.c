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
  ensures \result == (left < right ? left : right);
*/
static int32_t java_min(int32_t left, int32_t right) {
    return left < right ? left : right;
}

/*@
  requires jintarray_valid_nonnull(arr1) && jintarray_valid_nonnull(arr2);
  assigns \nothing;
  allocates \result, \result->data;
  frees \nothing;
  ensures jintarray_valid_nonnull(\result);
  ensures \result->length == (arr1->length < arr2->length ? arr1->length : arr2->length);
  ensures \fresh(\result, sizeof(*\result));
  ensures \result->length > 0 ==> \fresh(\result->data, \result->length * sizeof(int32_t));
  
  ensures \forall integer k; 0 <= k < \result->length ==> \result->data[k] == (int32_t)((integer)arr1->data[k] + arr2->data[k]);
*/
JIntArray sumList(JIntArray arr1, JIntArray arr2) {
    int32_t length1 = jarray_length(arr1);
    int32_t length2 = jarray_length(arr2);
    int32_t minLength = java_min(length1, length2);
    JIntArray result = jarray_new(minLength);
    /*@
      loop invariant 0 <= i <= minLength;
      loop invariant jintarray_valid_nonnull(result) && result->length == minLength;
      loop invariant \forall integer k; 0 <= k < i ==> result->data[k] == (int32_t)((integer)arr1->data[k] + arr2->data[k]);
      loop assigns i, result->data[0 .. minLength - 1];
      loop variant minLength - i;
    */
    for (int32_t i = 0; i < minLength; i = java_add(i, 1)) {
        int32_t value = java_add(jarray_get(arr1, i), jarray_get(arr2, i));
        jarray_set(result, i, value);
    }
    return result;
}
