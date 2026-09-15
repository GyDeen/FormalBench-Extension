#ifndef FORMALBENCH_VERIFICATION_JDOUBLEARRAY2_ACSL_H
#define FORMALBENCH_VERIFICATION_JDOUBLEARRAY2_ACSL_H

#include "jdoublearray.acsl.h"

/* Storage separation is independent of row identity: shared rows are allowed.
 * Call this predicate only with valid outer storage and a valid nullable row. */
/*@
  predicate jdoublearray2_row_compatible{L}(JDoubleArray2 a, JDoubleArray row) =
    row == \null ||
    (
      \separated(a, row) &&
      (a->length > 0 ==>
        \separated(a->data + (0 .. a->length - 1), row)) &&
      (row->length > 0 ==>
        \separated(a, row->data + (0 .. row->length - 1)) &&
        (a->length > 0 ==>
          \separated(a->data + (0 .. a->length - 1), row->data + (0 .. row->length - 1)))) &&
      (\forall integer i;
        0 <= i < a->length && a->data[i] != \null ==>
          (row->length > 0 ==>
            \separated(a->data[i], row->data + (0 .. row->length - 1))) &&
          (a->data[i]->length > 0 ==>
            \separated(row, a->data[i]->data + (0 .. a->data[i]->length - 1))))
    );
*/

/*@
  predicate jdoublearray2_valid{L}(JDoubleArray2 a) =
    a == \null ||
    (
      \valid_read(a) &&
      \initialized(&a->length) &&
      \initialized(&a->data) &&
      a->length >= 0 &&
      (
        (a->length == 0 && a->data == \null) ||
        (
          a->length > 0 &&
          \valid(a->data + (0 .. a->length - 1)) &&
          \initialized(a->data + (0 .. a->length - 1)) &&
          \separated(a, a->data + (0 .. a->length - 1))
        )
      ) &&
      (
        \forall integer i;
          0 <= i < a->length ==>
            jdoublearray_valid{L}(a->data[i]) &&
            jdoublearray2_row_compatible{L}(a, a->data[i])
      )
    );
*/

/* TRUSTED constructor summary; allocation succeeds for representable sizes. */
/*@
  requires length >= 0;
  requires length <= SIZE_MAX / sizeof(JDoubleArray);

  assigns \nothing;
  allocates \result, \result->data;
  exits \false;

  ensures \result != \null;
  ensures \fresh(\result, sizeof(*\result));
  ensures length > 0 ==>
    \fresh(\result->data, length * sizeof(JDoubleArray));
  ensures jdoublearray2_valid(\result);
  ensures \result->length == length;

  ensures \forall integer i;
    0 <= i < length ==> \result->data[i] == \null;
*/
JDoubleArray2 jdouble_array2_new_rows(int32_t length);

/* TRUSTED constructor summary, including freshness of every allocated row. */
/*@
  requires rows >= 0;
  requires columns >= 0;
  requires rows <= SIZE_MAX / sizeof(JDoubleArray);
  requires columns <= SIZE_MAX / sizeof(double);

  assigns \nothing;
  allocates \result, \result->data, \result->data[0 .. rows - 1],
    { \result->data[i]->data | integer i; 0 <= i < rows };
  exits \false;

  ensures \result != \null;
  ensures \fresh(\result, sizeof(*\result));
  ensures rows > 0 ==> \fresh(\result->data, rows * sizeof(JDoubleArray));
  ensures \forall integer i; 0 <= i < rows ==>
    \fresh(\result->data[i], sizeof(*\result->data[i]));
  ensures \forall integer i; 0 <= i < rows && columns > 0 ==>
    \fresh(\result->data[i]->data, columns * sizeof(double));
  ensures jdoublearray2_valid(\result);
  ensures \result->length == rows;

  ensures \forall integer i;
    0 <= i < rows ==>
      \result->data[i] != \null &&
      \result->data[i]->length == columns;

  ensures \forall integer i, j;
    0 <= i < rows &&
    0 <= j < columns ==>
      \result->data[i]->data[j] == 0.0;

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
JDoubleArray2 jdouble_array2_new(int32_t rows, int32_t columns);

/*@
  assigns \nothing;
  allocates \nothing;
  frees \nothing;
  ensures \result <==> array == \null;
*/
bool jdouble_array2_is_null(JDoubleArray2 array);

/*@
  requires array != \null;
  requires jdoublearray2_valid(array);
  assigns \nothing;
  ensures \result == array->length;
*/
int32_t jdouble_array2_length(JDoubleArray2 array);

/*@
  requires array != \null;
  requires jdoublearray2_valid(array);
  requires 0 <= index < array->length;
  assigns \nothing;
  ensures \result == array->data[index];
*/
JDoubleArray jdouble_array2_get(JDoubleArray2 array, int32_t index);

/*@
  requires array != \null;
  requires jdoublearray2_valid(array);
  requires 0 <= index < array->length;
  requires jdoublearray_valid(row);
  requires jdoublearray2_row_compatible(array, row);

  assigns array->data[index];

  ensures jdoublearray2_valid(array);
  ensures array->data[index] == row;
  ensures \result == row;
*/
JDoubleArray jdouble_array2_set(
    JDoubleArray2 array, int32_t index, JDoubleArray row);


/*@
  requires jdoublearray2_valid(array);
  requires array != \null ==> \freeable(array);
  requires array != \null && array->data != \null ==> \freeable(array->data);

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
void jdouble_array2_free(JDoubleArray2 array);

#endif
