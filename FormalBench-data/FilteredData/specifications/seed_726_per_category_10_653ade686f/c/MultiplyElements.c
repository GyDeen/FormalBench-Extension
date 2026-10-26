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
  requires jintarray_valid_nonnull(testTup);
  assigns \nothing;
  allocates \result, \result->data;
  frees \nothing;
  ensures jintarray_valid_nonnull(\result);
  ensures \result->length == (testTup->length < 2 ? 0 : testTup->length - 1);
  ensures \fresh(\result, sizeof(*\result));
  ensures \result->length > 0 ==> \fresh(\result->data, \result->length * sizeof(int32_t));
  
  ensures \forall integer k; 0 <= k < \result->length ==> \result->data[k] == (int32_t)((integer)testTup->data[k] * testTup->data[k + 1]);
*/
JIntArray multiplyElements(JIntArray testTup) {
    if ((jarray_length(testTup) < INT32_C(2))) {
        return jarray_new(INT32_C(0));
    }
    JIntArray result = jarray_new(java_sub(jarray_length(testTup), INT32_C(1)));
    /*@
      loop invariant 0 <= i <= testTup->length - 1;
      loop invariant jintarray_valid_nonnull(result) && result->length == testTup->length - 1;
      loop invariant \forall integer k; 0 <= k < i ==> result->data[k] == (int32_t)((integer)testTup->data[k] * testTup->data[k + 1]);
      loop assigns i, result->data[0 .. testTup->length - 2];
      loop variant testTup->length - 1 - i;
    */
    for (int32_t i = INT32_C(0); (i < java_sub(jarray_length(testTup), INT32_C(1))); i = java_add(i, INT32_C(1))) {
        int32_t __value_1 = java_mul(jarray_get(testTup, i), jarray_get(testTup, java_add(i, INT32_C(1))));
        jarray_set(result, i, __value_1);
    }
    return result;
}
