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
  ensures \result == (left > right ? left : right);
*/
static int32_t java_max(int32_t left, int32_t right) {
    return left > right ? left : right;
}

/*@
  assigns \nothing;
  ensures \result == (left < right ? left : right);
*/
static int32_t java_min(int32_t left, int32_t right) {
    return left < right ? left : right;
}

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
    int32_t length = jarray_length(nums);
    int32_t max = jarray_get(nums, 0);
    int32_t min = jarray_get(nums, 0);
    /*@
      loop invariant 0 <= i <= length && length == nums->length;
      loop invariant min == fb_minval(nums, i > 0 ? i : 1);
      loop invariant max == fb_maxval(nums, i > 0 ? i : 1);
      loop assigns i, min, max;
      loop variant length - i;
    */
    for (int32_t i = 0; i < length; i = java_add(i, 1)) {
        int32_t num = jarray_get(nums, i);
        max = java_max(max, num);
        min = java_min(min, num);
    }

    int32_t range = java_add(java_sub(max, min), 1);
    JIntArray bucket = jarray_new(range);
    /*@
      loop invariant 0 <= i <= length;
      loop invariant jintarray_valid_nonnull(bucket) && bucket->length == range;
      loop invariant \forall integer v; 0 <= v < range ==> bucket->data[v] == fb_occ(nums, i, (integer)min + v);
      loop assigns i, bucket->data[0 .. range - 1];
      loop variant length - i;
    */
    for (int32_t i = 0; i < length; i = java_add(i, 1)) {
        int32_t slot = java_sub(jarray_get(nums, i), min);
        jarray_set(bucket, slot, java_add(jarray_get(bucket, slot), 1));
    }

    int32_t pos = 0;
    int32_t bucketLength = jarray_length(bucket);
    /*@
      loop invariant 0 <= i <= bucketLength && bucketLength == range && 0 <= pos <= length;
      loop invariant jintarray_valid_nonnull(nums) && nums->length == length;
      loop invariant pos == fb_less{Pre}(nums, length, (integer)min + i);
      loop invariant \forall integer p, q; 0 <= p < q < pos ==> nums->data[p] <= nums->data[q];
      loop invariant \forall integer v; fb_occ(nums, pos, v) == (v < (integer)min + i ? fb_occ{Pre}(nums, length, v) : 0);
      loop assigns i, pos, nums->data[0 .. length - 1];
      loop variant bucketLength - i;
    */
    for (int32_t i = 0; i < bucketLength; i = java_add(i, 1)) {
        /*@
          loop invariant 0 <= j <= bucket->data[i] && 0 <= pos <= length;
          loop invariant jintarray_valid_nonnull(nums) && nums->length == length;
          loop invariant pos == fb_less{Pre}(nums, length, (integer)min + i) + j;
          loop invariant \forall integer p, q; 0 <= p < q < pos ==> nums->data[p] <= nums->data[q];
          loop invariant \forall integer v; fb_occ(nums, pos, v) == (v < (integer)min + i ? fb_occ{Pre}(nums, length, v) : v == (integer)min + i ? j : 0);
          loop assigns j, pos, nums->data[0 .. length - 1];
          loop variant bucket->data[i] - j;
        */
        for (int32_t j = 0; j < jarray_get(bucket, i); j = java_add(j, 1)) {
            jarray_set(nums, pos, java_add(i, min));
            pos = java_add(pos, 1);
        }
    }
    return nums;
}
