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
  requires jintarray_valid_nonnull(myArray) && (myArray->length == 0 || fb_maxval(myArray, myArray->length) - fb_minval(myArray, myArray->length) < INT32_MAX);
  assigns \nothing;
  allocates \result, \result->data;
  frees \nothing;
  ensures jintarray_valid_nonnull(\result);
  ensures \result->length == myArray->length;
  ensures \fresh(\result, sizeof(*\result));
  ensures \result->length > 0 ==> \fresh(\result->data, \result->length * sizeof(int32_t));
  
  ensures jintarray_valid_nonnull(\result);
  ensures \forall integer p, q; 0 <= p < q < \result->length ==> \result->data[p] <= \result->data[q];
  ensures \forall integer v; fb_occ(\result, \result->length, v) == fb_occ{Pre}(myArray, myArray->length, v);
*/
JIntArray countingSort(JIntArray myArray) {
    int32_t length = jarray_length(myArray);
    if (length == 0) {
        return jarray_new(0);
    }

    int32_t max = jarray_get(myArray, 0);
    int32_t min = jarray_get(myArray, 0);
    /*@
      loop invariant 0 <= i <= length && length == myArray->length;
      loop invariant min == fb_minval(myArray, i > 0 ? i : 1);
      loop invariant max == fb_maxval(myArray, i > 0 ? i : 1);
      loop assigns i, min, max;
      loop variant length - i;
    */
    for (int32_t i = 0; i < length; i = java_add(i, 1)) {
        int32_t num = jarray_get(myArray, i);
        max = java_max(max, num);
        min = java_min(min, num);
    }

    int32_t range = java_add(java_sub(max, min), 1);
    JIntArray countArray = jarray_new(range);
    /*@
      loop invariant 0 <= i <= length;
      loop invariant jintarray_valid_nonnull(countArray) && countArray->length == range;
      loop invariant \forall integer v; 0 <= v < range ==> countArray->data[v] == fb_occ(myArray, i, (integer)min + v);
      loop assigns i, countArray->data[0 .. range - 1];
      loop variant length - i;
    */
    for (int32_t i = 0; i < length; i = java_add(i, 1)) {
        int32_t slot = java_sub(jarray_get(myArray, i), min);
        int32_t old_count = jarray_get(countArray, slot);
        jarray_set(countArray, slot, java_add(old_count, 1));
    }

    int32_t index = 0;
    JIntArray result = jarray_new(length);
    int32_t countLength = jarray_length(countArray);
    /*@
      loop invariant 0 <= i <= countLength && countLength == range && 0 <= index <= length;
      loop invariant jintarray_valid_nonnull(result) && result->length == length;
      loop invariant index == fb_less{Pre}(myArray, length, (integer)min + i);
      loop invariant \forall integer p, q; 0 <= p < q < index ==> result->data[p] <= result->data[q];
      loop invariant \forall integer v; fb_occ(result, index, v) == (v < (integer)min + i ? fb_occ{Pre}(myArray, length, v) : 0);
      loop assigns i, index, result->data[0 .. length - 1];
      loop variant countLength - i;
    */
    for (int32_t i = 0; i < countLength; i = java_add(i, 1)) {
        /*@
          loop invariant 0 <= j <= countArray->data[i] && 0 <= index <= length;
          loop invariant jintarray_valid_nonnull(result) && result->length == length;
          loop invariant index == fb_less{Pre}(myArray, length, (integer)min + i) + j;
          loop invariant \forall integer p, q; 0 <= p < q < index ==> result->data[p] <= result->data[q];
          loop invariant \forall integer v; fb_occ(result, index, v) == (v < (integer)min + i ? fb_occ{Pre}(myArray, length, v) : v == (integer)min + i ? j : 0);
          loop assigns j, index, result->data[0 .. length - 1];
          loop variant countArray->data[i] - j;
        */
        for (int32_t j = 0; j < jarray_get(countArray, i); j = java_add(j, 1)) {
            jarray_set(result, index, java_add(i, min));
            index = java_add(index, 1);
        }
    }
    return result;
}
