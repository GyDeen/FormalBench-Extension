#ifndef FORMALBENCH_VERIFICATION_JBOOLARRAY_ACSL_H
#define FORMALBENCH_VERIFICATION_JBOOLARRAY_ACSL_H

#include "../generated/types.h"

/* Component predicates describe non-null storage; use them together through
 * valid_nonnull. The public valid wrapper retains nullable-array semantics. */
/*@
  predicate jboolarray_metadata_valid{L}(JBoolArray a) =
    \valid_read(a) && a->length >= 0;

  predicate jboolarray_metadata_initialized{L}(JBoolArray a) =
    \initialized(&a->length) && \initialized(&a->data);

  predicate jboolarray_buffer_valid{L}(JBoolArray a) =
    (a->length == 0 && a->data == \null) ||
    (a->length > 0 && \valid(a->data + (0 .. a->length - 1)));

  predicate jboolarray_buffer_initialized{L}(JBoolArray a) =
    a->length > 0 ==>
      \initialized(a->data + (0 .. a->length - 1));

  predicate jboolarray_storage_separated{L}(JBoolArray a) =
    a->length > 0 ==>
      \separated(a, a->data + (0 .. a->length - 1));

  predicate jboolarray_valid_nonnull{L}(JBoolArray a) =
    a != \null &&
    jboolarray_metadata_valid{L}(a) &&
    jboolarray_metadata_initialized{L}(a) &&
    jboolarray_buffer_valid{L}(a) &&
    jboolarray_buffer_initialized{L}(a) &&
    jboolarray_storage_separated{L}(a);

  predicate jboolarray_valid{L}(JBoolArray a) =
    a == \null || jboolarray_valid_nonnull{L}(a);
*/


/*@
  requires length >= 0;
  requires length <= SIZE_MAX / sizeof(bool);
  assigns \nothing;
  allocates \result, \result->data;
  exits \false;
  ensures \result != \null;
  ensures \fresh(\result, sizeof(*\result));
  ensures length > 0 ==>
    \fresh(\result->data, length * sizeof(bool));
  ensures jboolarray_valid(\result);
  ensures \result->length == length;
  ensures \forall integer k; 0 <= k < length ==> \result->data[k] == \false;
*/
JBoolArray jbool_array_new(int32_t length);

/*@
  assigns \nothing;
  allocates \nothing;
  frees \nothing;
  ensures \result <==> array == \null;
*/
bool jbool_array_is_null(JBoolArray array);

/*@
  requires array != \null;
  requires jboolarray_valid(array);
  assigns \nothing;
  ensures \result == array->length;
*/
int32_t jbool_array_length(JBoolArray array);

/*@
  requires array != \null;
  requires jboolarray_valid(array);
  requires 0 <= index < array->length;
  assigns \nothing;
  ensures \result == array->data[index];
*/
bool jbool_array_get(JBoolArray array, int32_t index);

/*@
  requires array != \null;
  requires jboolarray_valid(array);
  requires 0 <= index < array->length;
  assigns array->data[index];
  ensures jboolarray_valid(array);
  ensures array->data[index] == value;
  ensures \result == value;
*/
bool jbool_array_set(JBoolArray array, int32_t index, bool value);

/*@
  requires jboolarray_valid(array);

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
void jbool_array_free(JBoolArray array);

#endif
