#ifndef FORMALBENCH_VERIFICATION_JINTARRAY2_ACSL_H
#define FORMALBENCH_VERIFICATION_JINTARRAY2_ACSL_H

#include "jintarray.acsl.h"

/* The outer-array predicate permits null, shared, empty, and jagged rows. */
/*@
  predicate jintarray2_valid{L}(JIntArray2 a) =
    a != \null && \valid_read(a) &&
    a->length >= 0 && (
      (a->length == 0 && a->data == \null) ||
      (a->length > 0 &&
       \valid(a->data + (0 .. a->length - 1)) &&
       \separated(a, a->data + (0 .. a->length - 1)))
    );
*/

/*@
  requires rows >= 0;
  assigns \nothing;
  allocates \result, \result->data;
  ensures jintarray2_valid(\result);
  ensures \result->length == rows;
  ensures \forall integer k; 0 <= k < rows ==> \result->data[k] == \null;
*/
JIntArray2 jarray2_new_rows(int32_t rows);

/*@
  requires rows >= 0;
  requires columns >= 0;
  assigns \nothing;
  ensures jintarray2_valid(\result);
  ensures \result->length == rows;
  ensures \forall integer i; 0 <= i < rows ==>
    jintarray_valid(\result->data[i]) && \result->data[i]->length == columns;
  ensures \forall integer i, j;
    0 <= i && i < j && j < rows ==> \separated(\result->data[i], \result->data[j]);
  ensures \forall integer i, j;
    0 <= i < rows && 0 <= j < columns ==> \result->data[i]->data[j] == 0;
*/
JIntArray2 jarray2_new(int32_t rows, int32_t columns);

/*@
  assigns \nothing;
  allocates \nothing;
  frees \nothing;
  ensures \result <==> array == \null;
*/
bool jarray2_is_null(JIntArray2 array);

/*@
  requires jintarray2_valid(array);
  assigns \nothing;
  ensures \result == array->length;
*/
int32_t jarray2_length(JIntArray2 array);

/*@
  requires jintarray2_valid(array);
  requires 0 <= index < array->length;
  assigns \nothing;
  ensures \result == array->data[index];
*/
JIntArray jarray2_get(JIntArray2 array, int32_t index);

/*@
  requires jintarray2_valid(array);
  requires 0 <= index < array->length;
  assigns array->data[index];
  ensures jintarray2_valid(array);
  ensures array->data[index] == row;
  ensures \result == row;
*/
JIntArray jarray2_set(JIntArray2 array, int32_t index, JIntArray row);

/* Frees the outer storage only; row ownership remains with the caller. */
/*@
  requires array == \null || jintarray2_valid(array);
  assigns \nothing;
  frees array, array->data;
*/
void jarray2_free(JIntArray2 array);

#endif
