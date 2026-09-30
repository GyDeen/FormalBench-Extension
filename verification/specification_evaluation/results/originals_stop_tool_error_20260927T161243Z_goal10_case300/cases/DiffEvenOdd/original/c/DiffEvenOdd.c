#include "java_arrays.h"
#include <stdint.h>

/*@
  assigns \nothing;
  ensures \result == (int32_t)((integer)left - right);
*/
static int32_t java_sub(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left - (uint32_t)right);
}

/*@
  assigns \nothing;
  ensures \result == (int32_t)((integer)left + right);
*/
static int32_t java_add(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left + (uint32_t)right);
}

/*@
  logic integer fb_first{L}(JIntArray a, integer k, integer odd) =
    k <= 0 ? -1 : fb_first(a, k - 1, odd) != -1 ? fb_first(a, k - 1, odd) :
    ((odd != 0 ? a->data[k - 1] % 2 != 0 && a->data[k - 1] != -1 : a->data[k - 1] % 2 == 0) ? a->data[k - 1] : -1);
*/
/*@
  requires jintarray_valid_nonnull(array);
  assigns \nothing;
  ensures \result == (int32_t)(fb_first(array, array->length, 0) - fb_first(array, array->length, 1));
*/
int32_t diffEvenOdd(JIntArray array) {
    int32_t firstEven = -1;
    int32_t firstOdd = -1;
    int32_t length = jarray_length(array);

    /*@
      loop invariant 0 <= i <= length && length == array->length;
      loop invariant firstEven == fb_first(array, i, 0) && firstOdd == fb_first(array, i, 1);
      loop assigns i, firstEven, firstOdd;
      loop variant length - i;
    */
    for (int32_t i = 0; i < length; i = java_add(i, 1)) {
        int32_t num = jarray_get(array, i);
        if (firstEven == -1 && num % 2 == 0) {
            firstEven = num;
        }
        if (firstOdd == -1 && num % 2 != 0) {
            firstOdd = num;
        }
        if (firstEven != -1 && firstOdd != -1) {
            break;
        }
    }
    return java_sub(firstEven, firstOdd);
}
