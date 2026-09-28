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
  ensures \result == (int32_t)((integer)left * right);
*/
static int32_t java_mul(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left * (uint32_t)right);
}

/*@
  logic integer fb_digits{L}(JIntArray a, integer k) =
    k <= 0 ? 0 : (int32_t)(10 * fb_digits(a, k - 1) + a->data[k - 1]);
*/
/*@
  requires jintarray_valid_nonnull(nums);
  assigns \nothing;
  ensures \result == fb_digits(nums, nums->length);
*/
int32_t tupleToInt(JIntArray nums) {
    int32_t result = 0;
    int32_t length = jarray_length(nums);
    /*@
      loop invariant 0 <= i <= length && length == nums->length;
      loop invariant result == fb_digits(nums, i);
      loop assigns i, result;
      loop variant length - i;
    */
    for (int32_t i = 0; i < length; i = java_add(i, 1)) {
        result = java_add(java_mul(result, 10), jarray_get(nums, i));
    }
    return result;
}
