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
logic integer fb_tail{L}(JIntArray a, integer k) = k <= 0 ? 0 : \max(0, (int32_t)(fb_tail(a, k - 1) + a->data[k - 1]));
logic integer fb_best{L}(JIntArray a, integer k) = k <= 0 ? 0 : \max(fb_best(a, k - 1), (int32_t)(fb_tail(a, k - 1) + a->data[k - 1]));
logic integer fb_start{L}(JIntArray a, integer k) = k <= 0 ? 0 : (int32_t)(fb_tail(a, k - 1) + a->data[k - 1]) < 0 ? k : fb_start(a, k - 1);
logic integer fb_length{L}(JIntArray a, integer k) = k <= 0 ? 1 : fb_best(a, k - 1) < (int32_t)(fb_tail(a, k - 1) + a->data[k - 1]) ? k - fb_start(a, k - 1) : fb_length(a, k - 1);
*/
/*@
requires size <= 0 || (jintarray_valid_nonnull(a) && size <= a->length);
assigns \nothing;
ensures \result == (size <= 0 ? 1 : fb_length(a, size));
*/
int32_t maxSubArraySum(JIntArray a, int32_t size) {
    int32_t max_so_far = 0;
    int32_t max_ending_here = 0;
    int32_t start = 0;
    int32_t end = 0;
    int32_t s = 0;

    /*@
    loop invariant 0 <= i <= \max(0, size);
    loop invariant 0 <= s <= i;
    loop invariant 0 <= start <= end <= \max(0, i - 1);
    loop invariant size > 0 ==> max_ending_here == fb_tail(a, i) && max_so_far == fb_best(a, i) && s == fb_start(a, i) && end - start + 1 == fb_length(a, i);
    loop assigns i, max_so_far, max_ending_here, start, end, s;
    loop variant \max(0, size) - i;
    */
    for (int32_t i = 0; i < size; i = java_add(i, 1)) {
        max_ending_here = java_add(max_ending_here, jarray_get(a, i));
        if (max_so_far < max_ending_here) {
            max_so_far = max_ending_here;
            start = s;
            end = i;
        }
        if (max_ending_here < 0) {
            max_ending_here = 0;
            s = java_add(i, 1);
        }
    }
    return java_add(java_sub(end, start), 1);
}
