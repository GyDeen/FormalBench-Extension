#ifndef FORMALBENCH_VERIFICATION_JINTARRAY2_ACSL_H
#define FORMALBENCH_VERIFICATION_JINTARRAY2_ACSL_H

#include "jintarray.acsl.h"

/* Storage separation is independent of row identity: shared rows are allowed.
 * Call this predicate only with valid outer storage and a valid nullable row. */
/*@
  predicate jintarray2_row_compatible{L}(JIntArray2 a, JIntArray row) =
    row == \null ||
    (
      \separated(a, row) &&
      (a->length > 0 ==>
        \separated(a->data + (0 .. a->length - 1), row)) &&
      (row->length > 0 ==>
        \separated(a, row->data + (0 .. row->length - 1)) &&
        (a->length > 0 ==>
          \separated(a->data + (0 .. a->length - 1),
                     row->data + (0 .. row->length - 1)))) &&
      (\forall integer i;
        0 <= i < a->length && a->data[i] != \null ==>
          (row->length > 0 ==>
            \separated(a->data[i], row->data + (0 .. row->length - 1))) &&
          (a->data[i]->length > 0 ==>
            \separated(row,
              a->data[i]->data + (0 .. a->data[i]->length - 1))))
    );
*/

/* Component predicates describe non-null storage; use them together through
 * valid_nonnull. The public valid wrapper retains nullable-array semantics. */
/*@
  predicate jintarray2_metadata_valid{L}(JIntArray2 a) =
    \valid_read(a) && a->length >= 0;

  predicate jintarray2_metadata_initialized{L}(JIntArray2 a) =
    \initialized(&a->length) && \initialized(&a->data);

  predicate jintarray2_buffer_valid{L}(JIntArray2 a) =
    (a->length == 0 && a->data == \null) ||
    (a->length > 0 && \valid(a->data + (0 .. a->length - 1)));

  predicate jintarray2_buffer_initialized{L}(JIntArray2 a) =
    a->length > 0 ==>
      \initialized(a->data + (0 .. a->length - 1));

  predicate jintarray2_storage_separated{L}(JIntArray2 a) =
    a->length > 0 ==>
      \separated(a, a->data + (0 .. a->length - 1));

  predicate jintarray2_rows_valid{L}(JIntArray2 a) =
    \forall integer i; 0 <= i < a->length ==>
      jintarray_valid{L}(a->data[i]);

  predicate jintarray2_rows_compatible{L}(JIntArray2 a) =
    \forall integer i; 0 <= i < a->length ==>
      jintarray2_row_compatible{L}(a, a->data[i]);

  predicate jintarray2_valid_nonnull{L}(JIntArray2 a) =
    a != \null &&
    jintarray2_metadata_valid{L}(a) &&
    jintarray2_metadata_initialized{L}(a) &&
    jintarray2_buffer_valid{L}(a) &&
    jintarray2_buffer_initialized{L}(a) &&
    jintarray2_storage_separated{L}(a) &&
    jintarray2_rows_valid{L}(a) &&
    jintarray2_rows_compatible{L}(a);

  predicate jintarray2_valid{L}(JIntArray2 a) =
    a == \null || jintarray2_valid_nonnull{L}(a);
*/


/* TRUSTED constructor summary; allocation succeeds for representable sizes. */
/*@
  requires length >= 0;
  requires length <= SIZE_MAX / sizeof(JIntArray);

  assigns \nothing;
  allocates \result, \result->data;
  exits \false;

  ensures \result != \null;
  ensures \fresh(\result, sizeof(*\result));
  ensures length > 0 ==>
    \fresh(\result->data, length * sizeof(JIntArray));
  ensures jintarray2_valid(\result);
  ensures \result->length == length;

  ensures \forall integer i;
    0 <= i < length ==> \result->data[i] == \null;
*/
JIntArray2 jarray2_new_rows(int32_t length);


/* TRUSTED constructor summary, including freshness of every allocated row. */
/*@
  requires rows >= 0;
  requires columns >= 0;
  requires rows <= SIZE_MAX / sizeof(JIntArray);
  requires columns <= SIZE_MAX / sizeof(int32_t);

  assigns \nothing;
  allocates \result, \result->data, \result->data[0 .. rows - 1],
    { \result->data[i]->data | integer i; 0 <= i < rows };
  exits \false;

  ensures \result != \null;
  ensures \fresh(\result, sizeof(*\result));
  ensures rows > 0 ==> \fresh(\result->data, rows * sizeof(JIntArray));
  ensures \forall integer i; 0 <= i < rows ==>
    \fresh(\result->data[i], sizeof(*\result->data[i]));
  ensures \forall integer i; 0 <= i < rows && columns > 0 ==>
    \fresh(\result->data[i]->data, columns * sizeof(int32_t));
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
  requires jintarray2_row_compatible(array, row);

  assigns array->data[index];

  ensures jintarray2_valid(array);
  ensures array->data[index] == row;
  ensures \result == row;
*/
JIntArray jarray2_set(JIntArray2 array,int32_t index,JIntArray row);


/*@
  requires jintarray2_valid(array);

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
void jarray2_free(JIntArray2 array);

#endif
