#ifndef FORMALBENCH_VERIFICATION_JINTARRAY2_ACSL_H
#define FORMALBENCH_VERIFICATION_JINTARRAY2_ACSL_H

#include "jintarray.acsl.h"

/*@
  predicate jintarray2_valid{L}(JIntArray2 a) =
    a == \null ||
    (
      \valid_read(a) &&
      a->length >= 0 &&
      (
        (a->length == 0 && a->data == \null) ||
        (
          a->length > 0 &&
          \valid(a->data + (0 .. a->length - 1)) &&
          \separated(a, a->data + (0 .. a->length - 1))
        )
      ) &&
      (
        \forall integer i;
          0 <= i < a->length ==>
            jintarray_valid{L}(a->data[i])
      )
    );
*/


/*@
  requires length >= 0;

  assigns \nothing;
  allocates \result, \result->data;

  ensures \result != \null;
  ensures jintarray2_valid(\result);
  ensures \result->length == length;

  ensures \forall integer i;
    0 <= i < length ==> \result->data[i] == \null;
*/
JIntArray2 jarray2_new_rows(int32_t length);


/*@
  requires rows >= 0;
  requires columns >= 0;

  assigns \nothing;

  ensures \result != \null;
  ensures jintarray2_valid(\result);
  ensures \result->length == rows;

  ensures \forall integer i;
    0 <= i < rows ==>
      \result->data[i] != \null &&
      \result->data[i]->length == columns;

  ensures \forall integer i, j;
    0 <= i < rows &&
    0 <= j < columns ==>
      \result->data[i]->data[j] == 0;

  ensures \forall integer i, j;
    0 <= i && i < j && j < rows ==>
      \separated(
        \result->data[i],
        \result->data[j]
      );

  ensures \forall integer i, j;
    0 <= i && i < j && j < rows &&
    columns > 0 ==>
      \separated(
        \result->data[i]->data + (0 .. columns - 1),
        \result->data[j]->data + (0 .. columns - 1)
      );
*/
JIntArray2 jarray2_new(
    int32_t rows,
    int32_t columns
);


/*@
  assigns \nothing;
  allocates \nothing;
  frees \nothing;
  ensures \result <==> array == \null;
*/
bool jarray2_is_null(JIntArray2 array);


/*@
  requires array != \null;
  requires jintarray2_valid(array);
  assigns \nothing;
  ensures \result == array->length;
*/
int32_t jarray2_length(JIntArray2 array);


/*@
  requires array != \null;
  requires jintarray2_valid(array);
  requires 0 <= index < array->length;
  assigns \nothing;
  ensures \result == array->data[index];
*/
JIntArray jarray2_get(JIntArray2 array, int32_t index);

/*@
  requires array != \null;
  requires jintarray2_valid(array);
  requires 0 <= index < array->length;
  requires jintarray_valid(row);

  assigns array->data[index];

  ensures jintarray2_valid(array);
  ensures array->data[index] == row;
  ensures \result == row;
*/
JIntArray jarray2_set(JIntArray2 array,int32_t index,JIntArray row);


/*@
  requires jintarray2_valid(array);

  behavior null_array:
    assumes array == \null;
    assigns \nothing;
    allocates \nothing;
    frees \nothing;

  behavior non_null_array:
    assumes array != \null;
    assigns \nothing;
    allocates \nothing;
    frees array, array->data;

  complete behaviors;
  disjoint behaviors;
*/
void jarray2_free(JIntArray2 array);

#endif