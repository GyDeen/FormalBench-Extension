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
static int32_t java_mod(int32_t left, int32_t right) { if (right == 0) { fputs("JAVA_ARITHMETIC_ERROR: DIVIDE_BY_ZERO\n", stderr); exit(75); } if (left == INT32_MIN && right == -1) return 0; return left % right; }

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
    if ((jarray_length(myArray) == INT32_C(0))) {
        return jarray_new(INT32_C(0));
    }
    int32_t max = jarray_get(myArray, INT32_C(0));
    int32_t min = jarray_get(myArray, INT32_C(0));
    /*@
      loop invariant 0 <= __index_1 <= myArray->length;
      loop invariant min == fb_minval(myArray, __index_1 > 0 ? __index_1 : 1);
      loop invariant max == fb_maxval(myArray, __index_1 > 0 ? __index_1 : 1);
      loop assigns __index_1, min, max;
      loop variant myArray->length - __index_1;
    */
    for (int32_t __index_1 = 0; __index_1 < jarray_length(myArray); __index_1 = java_add(__index_1, INT32_C(1))) {
        int32_t num = jarray_get(myArray, __index_1);
        if ((num > max)) {
            max = num;
        }
        if ((num < min)) {
            min = num;
        }
    }
    int32_t range = java_add(java_sub(max, min), INT32_C(1));
    JIntArray countArray = jarray_new(range);
    /*@
      loop invariant 0 <= i <= myArray->length;
      loop invariant jintarray_valid_nonnull(countArray) && countArray->length == range;
      loop invariant \forall integer v; 0 <= v < range ==> countArray->data[v] == fb_occ(myArray, i, (integer)min + v);
      loop assigns i, countArray->data[0 .. range - 1];
      loop variant myArray->length - i;
    */
    for (int32_t i = INT32_C(0); (i < jarray_length(myArray)); i = java_add(i, INT32_C(1))) {
        jarray_set(countArray, java_mod(jarray_get(myArray, i), min), java_add(jarray_get(countArray, java_mod(jarray_get(myArray, i), min)), INT32_C(1)));
    }
    int32_t index = INT32_C(0);
    JIntArray result = jarray_new(jarray_length(myArray));
    /*@
      loop invariant 0 <= i <= countArray->length && countArray->length == range && 0 <= index <= myArray->length;
      loop invariant jintarray_valid_nonnull(result) && result->length == myArray->length;
      loop invariant index == fb_less{Pre}(myArray, myArray->length, (integer)min + i);
      loop invariant \forall integer p, q; 0 <= p < q < index ==> result->data[p] <= result->data[q];
      loop invariant \forall integer v; fb_occ(result, index, v) == (v < (integer)min + i ? fb_occ{Pre}(myArray, myArray->length, v) : 0);
      loop assigns i, index, result->data[0 .. myArray->length - 1];
      loop variant countArray->length - i;
    */
    for (int32_t i = INT32_C(0); (i < jarray_length(countArray)); i = java_add(i, INT32_C(1))) {
        /*@
          loop invariant 0 <= j <= countArray->data[i] && 0 <= index <= myArray->length;
          loop invariant jintarray_valid_nonnull(result) && result->length == myArray->length;
          loop invariant index == fb_less{Pre}(myArray, myArray->length, (integer)min + i) + j;
          loop invariant \forall integer p, q; 0 <= p < q < index ==> result->data[p] <= result->data[q];
          loop invariant \forall integer v; fb_occ(result, index, v) == (v < (integer)min + i ? fb_occ{Pre}(myArray, myArray->length, v) : v == (integer)min + i ? j : 0);
          loop assigns j, index, result->data[0 .. myArray->length - 1];
          loop variant countArray->data[i] - j;
        */
        for (int32_t j = INT32_C(0); (j < jarray_get(countArray, i)); j = java_add(j, INT32_C(1))) {
            int32_t __index_2 = index;
            index = java_add(index, INT32_C(1));
            int32_t __value_3 = java_add(i, min);
            jarray_set(result, __index_2, __value_3);
        }
    }
    return result;
}
