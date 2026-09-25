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
    int32_t length = jarray_length(testTup);
    if (length < 2) {
        return jarray_new(0);
    }
    JIntArray result = jarray_new(java_sub(length, 1));
    int32_t limit = java_sub(length, 1);
    /*@
      loop invariant 0 <= i <= limit && limit == testTup->length - 1;
      loop invariant jintarray_valid_nonnull(result) && result->length == limit;
      loop invariant \forall integer k; 0 <= k < i ==> result->data[k] == (int32_t)((integer)testTup->data[k] * testTup->data[k + 1]);
      loop assigns i, result->data[0 .. limit - 1];
      loop variant limit - i;
    */
    for (int32_t i = 0; i < limit; i = java_add(i, 1)) {
        jarray_set(result, i,
                   java_mul(jarray_get(testTup, i), jarray_get(testTup, java_add(i, 1))));
    }
    return result;
}
