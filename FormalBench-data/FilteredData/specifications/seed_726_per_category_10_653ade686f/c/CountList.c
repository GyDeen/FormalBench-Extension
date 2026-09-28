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
  logic integer fb_count_rows{L}(JIntArray2 a, integer k) =
    k <= 0 ? 0 : fb_count_rows(a, k - 1) + (a->data[k - 1]->length > 0 ? 1 : 0);
*/
/*@
  requires jintarray2_valid_nonnull(inputArray);
  requires \forall integer k; 0 <= k < inputArray->length ==> inputArray->data[k] != \null;
  assigns \nothing;
  ensures \result == fb_count_rows(inputArray, inputArray->length);
*/
int32_t countList(JIntArray2 inputArray) {
    int32_t count = 0;
    int32_t length = jarray2_length(inputArray);
    /*@
      loop invariant 0 <= i <= length && length == inputArray->length;
      loop invariant count == fb_count_rows(inputArray, i) && 0 <= count <= i;
      loop assigns i, count;
      loop variant length - i;
    */
    for (int32_t i = 0; i < length; i = java_add(i, 1)) {
        JIntArray array = jarray2_get(inputArray, i);
        if (jarray_length(array) > 0) {
            count = java_add(count, 1);
        }
    }
    return count;
}
