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
  requires divisor != 0;
  assigns \nothing;
  ensures \result == (int32_t)((integer)dividend / divisor);
*/
static int32_t java_div(int32_t dividend, int32_t divisor) {
    if (dividend == INT32_MIN && divisor == -1) {
        return INT32_MIN;
    }
    return dividend / divisor;
}

/*@
  logic integer fb_peak{L}(JIntArray a, integer lo, integer hi) =
    lo >= hi ? lo : a->data[lo + (hi - lo) / 2] < a->data[lo + (hi - lo) / 2 + 1] ?
    fb_peak(a, lo + (hi - lo) / 2 + 1, hi) : fb_peak(a, lo, lo + (hi - lo) / 2);
*/
/*@
  requires n >= 0;
  requires n <= 1 || (jintarray_valid_nonnull(arr) && n <= arr->length);
  assigns \nothing;
  ensures n <= 1 ==> \result == 0;
  ensures n > 1 ==> \result == fb_peak(arr, 0, n - 1);
  ensures n > 1 ==> 0 <= \result < n;
  ensures n > 1 && \result > 0 ==> arr->data[\result] >= arr->data[\result - 1];
  ensures n > 1 && \result < n - 1 ==> arr->data[\result] >= arr->data[\result + 1];
*/
int32_t findPeak(JIntArray arr, int32_t n) {
    int32_t left = 0;
    int32_t right = java_sub(n, 1);
    /*@
      loop invariant n <= 1 ==> left == 0 && right == n - 1;
      loop invariant n > 1 ==> 0 <= left <= right < n;
      loop invariant n > 1 ==> fb_peak(arr, left, right) == fb_peak(arr, 0, n - 1);
      loop invariant n > 1 && left > 0 ==> arr->data[left] >= arr->data[left - 1];
      loop invariant n > 1 && right < n - 1 ==> arr->data[right] >= arr->data[right + 1];
      loop assigns left, right;
      loop variant (integer)right - left;
    */
    while (left < right) {
        int32_t mid = java_add(left, java_div(java_sub(right, left), 2));
        int32_t next = java_add(mid, 1);
        if (jarray_get(arr, mid) < jarray_get(arr, next)) {
            left = next;
        } else {
            right = mid;
        }
    }
    return left;
}
