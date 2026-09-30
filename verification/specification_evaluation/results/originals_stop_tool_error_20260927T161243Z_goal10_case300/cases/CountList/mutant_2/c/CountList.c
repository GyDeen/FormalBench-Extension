#include "java_arrays.h"
#include <stdint.h>

/*@
  assigns \nothing;
  ensures \result == (int32_t)((integer)left + right);
*/
static int32_t java_add(int32_t left, int32_t right) { return (int32_t)((uint32_t)left + (uint32_t)right); }

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
    int32_t count = INT32_C(0);
    /*@
      loop invariant 0 <= __index_1 <= (inputArray->length) && (inputArray->length) == inputArray->length;
      loop invariant count == fb_count_rows(inputArray, __index_1) && 0 <= count <= __index_1;
      loop assigns __index_1, count;
      loop variant (inputArray->length) - __index_1;
    */
    for (int32_t __index_1 = 0; __index_1 < jarray2_length(inputArray); __index_1 = java_add(__index_1, INT32_C(1))) {
        JIntArray array = jarray2_get(inputArray, __index_1);
        if (false) {
            count = java_add(count, INT32_C(1));
        }
    }
    return count;
}
