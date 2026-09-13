#ifndef FORMALBENCH_VERIFICATION_JDOUBLEARRAY2_ACSL_H
#define FORMALBENCH_VERIFICATION_JDOUBLEARRAY2_ACSL_H

#include "jdoublearray.acsl.h"

/*@
  predicate jdoublearray2_outer_valid{L}(JDoubleArray2 a) =
    \valid_read(a) && a->length >= 0 && 
    (
      (a->length == 0 && a->data == \null) || (a->length > 0 && \valid(a->data + (0 .. a->length - 1)) &&
      \separated(a, a->data + (0 .. a->length - 1)))
    );
*/

/*@
  predicate jdoublearray2_valid{L}(JDoubleArray2 a) =
    jdoublearray2_outer_valid{L}(a) &&
    (
      \forall integer i;
      0 <= i < a->length ==>
        (a->data[i] == \null || jdoublearray_valid{L}(a->data[i]))
    );
*/

/*@
  requires rows >= 0;
  assigns \nothing;
  allocates \result, \result->data;
  ensures jdoublearray2_valid(\result);
  ensures \result->length == rows;
  ensures \forall integer k; 0 <= k < rows ==> \result->data[k] == \null;
*/
JDoubleArray2 jdouble_array2_new_rows(int32_t rows);

/*@
  requires rows >= 0;
  requires columns >= 0;
  assigns \nothing;
  ensures jdoublearray2_valid(\result);
  ensures \result->length == rows;
  ensures \forall integer i; 0 <= i < rows ==>
    jdoublearray_valid(\result->data[i]) && \result->data[i]->length == columns;
  ensures \forall integer i, j;
    0 <= i && i < j && j < rows ==> \separated(\result->data[i], \result->data[j]);
  ensures \forall integer i, j;
    0 <= i < rows && 0 <= j < columns ==> \result->data[i]->data[j] == 0.0;
*/
JDoubleArray2 jdouble_array2_new(int32_t rows, int32_t columns);

/*@
  assigns \nothing;
  allocates \nothing;
  frees \nothing;
  ensures \result <==> array == \null;
*/
bool jdouble_array2_is_null(JDoubleArray2 array);

/*@
  requires jdoublearray2_valid(array);
  assigns \nothing;
  ensures \result == array->length;
*/
int32_t jdouble_array2_length(JDoubleArray2 array);

/*@
  requires jdoublearray2_valid(array);
  requires 0 <= index < array->length;
  assigns \nothing;
  ensures \result == array->data[index];
*/
JDoubleArray jdouble_array2_get(JDoubleArray2 array, int32_t index);

/*@
  requires jdoublearray2_valid(array);
  requires 0 <= index < array->length;
  assigns array->data[index];
  ensures jdoublearray2_valid(array);
  ensures array->data[index] == row;
  ensures \result == row;
*/
JDoubleArray jdouble_array2_set(
    JDoubleArray2 array, int32_t index, JDoubleArray row);

/* Frees the outer storage only; row ownership remains with the caller. */
/*@
  requires array == \null || jdoublearray2_valid(array);
  assigns \nothing;
  frees array, array->data;
*/
void jdouble_array2_free(JDoubleArray2 array);

#endif
