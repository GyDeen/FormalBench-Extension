#ifndef FORMALBENCH_VERIFICATION_JDOUBLEARRAY_ACSL_H
#define FORMALBENCH_VERIFICATION_JDOUBLEARRAY_ACSL_H

#include "../generated/types.h"

/* Component predicates describe non-null storage; use them together through
 * valid_nonnull. The public valid wrapper retains nullable-array semantics. */
/*@
  predicate jdoublearray_metadata_valid{L}(JDoubleArray a) =
    \valid_read(a) && a->length >= 0;

  predicate jdoublearray_metadata_initialized{L}(JDoubleArray a) =
    \initialized(&a->length) && \initialized(&a->data);

  predicate jdoublearray_buffer_valid{L}(JDoubleArray a) =
    (a->length == 0 && a->data == \null) ||
    (a->length > 0 && \valid(a->data + (0 .. a->length - 1)));

  predicate jdoublearray_buffer_initialized{L}(JDoubleArray a) =
    a->length > 0 ==>
      \initialized(a->data + (0 .. a->length - 1));

  predicate jdoublearray_storage_separated{L}(JDoubleArray a) =
    a->length > 0 ==>
      \separated(a, a->data + (0 .. a->length - 1));

  predicate jdoublearray_valid_nonnull{L}(JDoubleArray a) =
    a != \null &&
    jdoublearray_metadata_valid{L}(a) &&
    jdoublearray_metadata_initialized{L}(a) &&
    jdoublearray_buffer_valid{L}(a) &&
    jdoublearray_buffer_initialized{L}(a) &&
    jdoublearray_storage_separated{L}(a);

  predicate jdoublearray_valid{L}(JDoubleArray a) =
    a == \null || jdoublearray_valid_nonnull{L}(a);
*/


/*@
  requires length >= 0;
  requires length <= SIZE_MAX / sizeof(double);
  assigns \nothing;
  allocates \result, \result->data;
  exits \false;
  ensures \result != \null;
  ensures \fresh(\result, sizeof(*\result));
  ensures length > 0 ==>
    \fresh(\result->data, length * sizeof(double));
  ensures jdoublearray_valid(\result);
  ensures \result->length == length;
  ensures \forall integer k; 0 <= k < length ==> \result->data[k] == 0.0;
*/
JDoubleArray jdouble_array_new(int32_t length);

/*@
  assigns \nothing;
  allocates \nothing;
  frees \nothing;
  ensures \result <==> array == \null;
*/
bool jdouble_array_is_null(JDoubleArray array);

/*@
  requires array != \null;
  requires jdoublearray_valid(array);
  assigns \nothing;
  ensures \result == array->length;
*/
int32_t jdouble_array_length(JDoubleArray array);

/*@
  requires array != \null;
  requires jdoublearray_valid(array);
  requires 0 <= index < array->length;
  assigns \nothing;
  ensures \result == array->data[index];
*/
double jdouble_array_get(JDoubleArray array, int32_t index);

/*@
  requires array != \null;
  requires jdoublearray_valid(array);
  requires 0 <= index < array->length;
  assigns array->data[index];
  ensures jdoublearray_valid(array);
  ensures array->data[index] == value;
  ensures \result == value;
*/
double jdouble_array_set(JDoubleArray array, int32_t index, double value);

/*@
  requires jdoublearray_valid(array);

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
void jdouble_array_free(JDoubleArray array);

#endif
