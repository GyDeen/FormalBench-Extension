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
  ensures \result == (int32_t)((integer)left - right);
*/
static int32_t java_sub(int32_t left, int32_t right) { return (int32_t)((uint32_t)left - (uint32_t)right); }
static int32_t java_div(int32_t left, int32_t right) { if (right == 0) { fputs("JAVA_ARITHMETIC_ERROR: DIVIDE_BY_ZERO\n", stderr); exit(75); } if (left == INT32_MIN && right == -1) return INT32_MIN; return left / right; }

/*@
  logic integer fb_minval{L}(JIntArray a, integer k) = k <= 1 ? a->data[0] : \min(fb_minval(a, k - 1), a->data[k - 1]);
  logic integer fb_maxval{L}(JIntArray a, integer k) = k <= 1 ? a->data[0] : \max(fb_maxval(a, k - 1), a->data[k - 1]);
  logic integer fb_occ{L}(JIntArray a, integer k, integer v) = k <= 0 ? 0 : fb_occ(a, k - 1, v) + (a->data[k - 1] == v ? 1 : 0);
  logic integer fb_less{L}(JIntArray a, integer k, integer v) = k <= 0 ? 0 : fb_less(a, k - 1, v) + (a->data[k - 1] < v ? 1 : 0);
*/
/*@
  requires jintarray_valid_nonnull(nums) && nums->length > 0 && fb_maxval(nums, nums->length) - fb_minval(nums, nums->length) < INT32_MAX;
  assigns nums->data[0 .. nums->length - 1];
  
  ensures jintarray_valid_nonnull(\result);
  ensures \forall integer p, q; 0 <= p < q < \result->length ==> \result->data[p] <= \result->data[q];
  ensures \forall integer v; fb_occ(\result, \result->length, v) == fb_occ{Pre}(nums, nums->length, v);
  ensures \result == nums;
*/
JIntArray radixSort(JIntArray nums) {
    int32_t max = jarray_get(nums, INT32_C(0));
    int32_t min = jarray_get(nums, INT32_C(0));
    /*@
      loop invariant 0 <= __index_1 <= nums->length;
      loop invariant min == fb_minval(nums, __index_1 > 0 ? __index_1 : 1);
      loop invariant max == fb_maxval(nums, __index_1 > 0 ? __index_1 : 1);
      loop assigns __index_1, min, max;
      loop variant nums->length - __index_1;
    */
    for (int32_t __index_1 = 0; __index_1 < jarray_length(nums); __index_1 = java_add(__index_1, INT32_C(1))) {
        int32_t num = jarray_get(nums, __index_1);
        if ((num > max)) {
            max = num;
        }
        if ((num < min)) {
            min = num;
        }
    }
    int32_t range = java_add(java_sub(max, min), INT32_C(1));
    JIntArray bucket = jarray_new(range);
    /*@
      loop invariant 0 <= __index_2 <= nums->length;
      loop invariant jintarray_valid_nonnull(bucket) && bucket->length == range;
      loop invariant \forall integer v; 0 <= v < range ==> bucket->data[v] == fb_occ(nums, __index_2, (integer)min + v);
      loop assigns __index_2, bucket->data[0 .. range - 1];
      loop variant nums->length - __index_2;
    */
    for (int32_t __index_2 = 0; __index_2 < jarray_length(nums); __index_2 = java_add(__index_2, INT32_C(1))) {
        int32_t num = jarray_get(nums, __index_2);
        jarray_set(bucket, java_sub(num, min), java_add(jarray_get(bucket, java_sub(num, min)), INT32_C(1)));
    }
    int32_t pos = INT32_C(0);
    /*@
      loop invariant 0 <= i <= bucket->length && bucket->length == range && 0 <= pos <= nums->length;
      loop invariant jintarray_valid_nonnull(nums);
      loop invariant pos == fb_less{Pre}(nums, nums->length, (integer)min + i);
      loop invariant \forall integer p, q; 0 <= p < q < pos ==> nums->data[p] <= nums->data[q];
      loop invariant \forall integer v; fb_occ(nums, pos, v) == (v < (integer)min + i ? fb_occ{Pre}(nums, nums->length, v) : 0);
      loop assigns i, pos, nums->data[0 .. nums->length - 1];
      loop variant bucket->length - i;
    */
    for (int32_t i = INT32_C(0); (i < range); i = java_add(i, INT32_C(1))) {
        /*@
          loop invariant 0 <= j <= bucket->data[i] && 0 <= pos <= nums->length;
          loop invariant jintarray_valid_nonnull(nums);
          loop invariant pos == fb_less{Pre}(nums, nums->length, (integer)min + i) + j;
          loop invariant \forall integer p, q; 0 <= p < q < pos ==> nums->data[p] <= nums->data[q];
          loop invariant \forall integer v; fb_occ(nums, pos, v) == (v < (integer)min + i ? fb_occ{Pre}(nums, nums->length, v) : v == (integer)min + i ? j : 0);
          loop assigns j, pos, nums->data[0 .. nums->length - 1];
          loop variant bucket->data[i] - j;
        */
        for (int32_t j = INT32_C(0); (j < jarray_get(bucket, i)); j = java_add(j, INT32_C(1))) {
            int32_t __index_3 = pos;
            pos = java_add(pos, INT32_C(1));
            int32_t __value_4 = java_div(i, min);
            jarray_set(nums, __index_3, __value_4);
        }
    }
    return nums;
}
