#ifndef FORMALBENCH_VERIFICATION_JINTARRAY_ACSL_H
#define FORMALBENCH_VERIFICATION_JINTARRAY_ACSL_H

#include "../generated/types.h"

/*@
  predicate jintarray_valid{L}(JIntArray a) =
    a == \null ||
    (
      \valid_read(a) &&
      \initialized(&a->length) &&
      \initialized(&a->data) &&
      a->length >= 0 &&
      (
        (a->length == 0 && a->data == \null) ||
        (a->length > 0 &&
         \valid(a->data + (0 .. a->length - 1)) &&
         \initialized(a->data + (0 .. a->length - 1)) &&
         \separated(a, a->data + (0 .. a->length - 1)))
      )
    );
*/

/* TRUSTED constructor summary: successful allocation for representable sizes.
 * Its implementation is outside the selected accessor/row-operation proofs. */
/*@
  requires length >= 0;
  requires length <= SIZE_MAX / sizeof(int32_t);
  assigns \nothing;
  allocates \result, \result->data;
  exits \false;
  ensures \result != \null;
  ensures \fresh(\result, sizeof(*\result));
  ensures length > 0 ==>
    \fresh(\result->data, length * sizeof(int32_t));
  ensures jintarray_valid(\result);
  ensures \result->length == length;
  ensures \forall integer k; 0 <= k < length ==> \result->data[k] == 0;
*/
JIntArray jarray_new(int32_t length);

/*@
  assigns \nothing;
  allocates \nothing;
  frees \nothing;
  ensures \result <==> array == \null;
*/
bool jarray_is_null(JIntArray array);

/*@
  requires array != \null;
  requires jintarray_valid(array);
  assigns \nothing;
  ensures \result == array->length;
*/
int32_t jarray_length(JIntArray array);

/*@
  requires array != \null;
  requires jintarray_valid(array);
  requires 0 <= index < array->length;
  assigns \nothing;
  ensures \result == array->data[index];
*/
int32_t jarray_get(JIntArray array, int32_t index);

/*@
  requires array != \null;
  requires jintarray_valid(array);
  requires 0 <= index < array->length;
  assigns array->data[index];
  ensures jintarray_valid(array);
  ensures array->data[index] == value;
  ensures \result == value;
*/
int32_t jarray_set(JIntArray array, int32_t index, int32_t value);

/*@
  requires jintarray_valid(array);

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
void jarray_free(JIntArray array);

#endif
