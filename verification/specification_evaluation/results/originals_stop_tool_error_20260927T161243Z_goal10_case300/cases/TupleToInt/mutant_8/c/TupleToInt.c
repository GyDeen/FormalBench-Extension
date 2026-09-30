#include "java_arrays.h"
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>

/*@
  assigns \nothing;
  ensures \result == (int32_t)((integer)left + right);
*/
static int32_t java_add(int32_t left, int32_t right) { return (int32_t)((uint32_t)left + (uint32_t)right); }
/*@
  assigns \nothing;
  ensures \result == (int32_t)((integer)left * right);
*/
static int32_t java_mul(int32_t left, int32_t right) { return (int32_t)((uint32_t)left * (uint32_t)right); }
static int32_t java_div(int32_t left, int32_t right) { if (right == 0) { fputs("JAVA_ARITHMETIC_ERROR: DIVIDE_BY_ZERO\n", stderr); exit(75); } if (left == INT32_MIN && right == -1) return INT32_MIN; return left / right; }

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
    int32_t result = INT32_C(0);
    /*@
      loop invariant 0 <= __index_1 <= (nums->length) && (nums->length) == nums->length;
      loop invariant result == fb_digits(nums, __index_1);
      loop assigns __index_1, result;
      loop variant (nums->length) - __index_1;
    */
    for (int32_t __index_1 = 0; __index_1 < jarray_length(nums); __index_1 = java_add(__index_1, INT32_C(1))) {
        int32_t num = jarray_get(nums, __index_1);
        result = java_div(java_mul(result, INT32_C(10)), num);
    }
    return result;
}
