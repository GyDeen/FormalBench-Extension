#include "java_arrays.h"
#include <stdbool.h>
#include <stdint.h>

/*@
assigns \nothing;
ensures \result == (int32_t)((integer)left + right);
*/
static int32_t java_add(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left + (uint32_t)right);
}

/*@
logic integer fb_shrink(integer g) = (integer)(double)((real)g / (double)1.3);
logic integer fb_steps(integer g) = g <= 1 ? 1 : 1 + fb_steps(fb_shrink(g));
logic integer fb_gap(integer n, integer t) = t <= 0 ? n : fb_shrink(fb_gap(n, t - 1));
logic integer fb_value{L}(JIntArray a, integer t, integer k, integer p) =
  t <= 0 ? a->data[p] : k <= 0 || fb_gap(a->length, t) == 0 ?
    fb_value(a, t - 1, a->length - fb_gap(a->length, t - 1), p) :
  p == k - 1 ? \min(fb_value(a, t, k - 1, k - 1), fb_value(a, t, k - 1, k - 1 + fb_gap(a->length, t))) :
  p == k - 1 + fb_gap(a->length, t) ? \max(fb_value(a, t, k - 1, k - 1), fb_value(a, t, k - 1, k - 1 + fb_gap(a->length, t))) :
  fb_value(a, t, k - 1, p);
*/
/*@
requires jintarray_valid_nonnull(nums);
assigns nums->data[0 .. nums->length - 1];
allocates \nothing;
frees \nothing;
ensures \result == nums && jintarray_valid_nonnull(nums);
ensures \forall integer p; 0 <= p < nums->length ==>
  nums->data[p] == fb_value{Pre}(nums, fb_steps(nums->length), nums->length, p);
ensures nums->length > 0 ==> (\forall integer p; 0 <= p < nums->length ==> nums->data[p] <= nums->data[nums->length - 1]);
*/
JIntArray combSort(JIntArray nums) {
    int32_t n = jarray_length(nums);
    int32_t gap = n;
    bool swapped = true;
    /*@ ghost int32_t fb_pass = 0; */

    /*@
    loop invariant jintarray_valid_nonnull(nums) && nums->length == n;
    loop invariant 0 <= fb_pass <= fb_steps(n) && gap == fb_gap(n, fb_pass) && 0 <= gap <= n;
    loop invariant \forall integer p; 0 <= p < n ==> nums->data[p] == fb_value{Pre}(nums, fb_pass, n - gap, p);
    loop assigns gap, swapped, nums->data[0 .. n - 1], fb_pass;
    loop variant 2 * (integer)gap + (swapped ? 1 : 0);
    */
    while (gap > 1 || swapped) {
        gap = (int32_t)((double)gap / 1.3);
        swapped = false;
        /*@ ghost fb_pass = fb_pass + 1; */

        /*@
        loop invariant jintarray_valid_nonnull(nums) && nums->length == n;
        loop invariant 0 <= i <= n - gap;
        loop invariant 1 <= fb_pass <= fb_steps(n) && gap == fb_gap(n, fb_pass);
        loop invariant \forall integer p; 0 <= p < n ==> nums->data[p] == fb_value{Pre}(nums, fb_pass, i, p);
        loop assigns i, swapped, nums->data[0 .. n - 1];
        loop variant (integer)n - gap - i;
        */
        for (int32_t i = 0; java_add(i, gap) < n; i = java_add(i, 1)) {
            int32_t next = java_add(i, gap);
            int32_t left = jarray_get(nums, i);
            int32_t right = jarray_get(nums, next);
            if (left > right) {
                jarray_set(nums, i, right);
                jarray_set(nums, next, left);
                swapped = true;
            }
        }
    }
    return nums;
}
