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
  logic integer fb_sum{L}(JIntArray a, integer lo, integer hi) =
    hi <= lo ? 0 : fb_sum(a, lo, hi - 1) + a->data[hi - 1];
*/
/*@
  requires m > n || (jintarray_valid_nonnull(nums) && 0 <= m && n < nums->length);
  assigns \nothing;
  ensures \result == (int32_t)fb_sum(nums, m, (integer)n + 1);
*/
int32_t sumRangeArray(JIntArray nums, int32_t m, int32_t n) {
    int32_t sum = 0;
    /*@
      loop invariant m <= i && (m > n ? i == m : i <= (integer)n + 1);
      loop invariant sum == (int32_t)fb_sum(nums, m, i);
      loop assigns i, sum;
      loop variant (integer)n - i + 1;
    */
    for (int32_t i = m; i <= n; i = java_add(i, 1)) {
        sum = java_add(sum, jarray_get(nums, i));
    }
    return sum;
}
