#ifndef FORMALBENCH_VERIFICATION_JINTARRAY_ACSL_H
#define FORMALBENCH_VERIFICATION_JINTARRAY_ACSL_H

#include "../generated/types.h"

/*@
  predicate jintarray_valid{L}(JIntArray a) =
    a != \null &&
    \valid_read(a) &&
    a->length >= 0 &&
    ((a->length == 0 && a->data == \null) ||
     (a->length > 0 &&
      \valid(a->data + (0 .. a->length - 1)) &&
      \separated(a, a->data + (0 .. a->length - 1))));
*/

/*@
  requires length >= 0;
  assigns \nothing;
  allocates \result, \result->data;
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
  requires jintarray_valid(array);
  assigns \nothing;
  ensures \result == array->length;
*/
int32_t jarray_length(JIntArray array);

/*@
  requires jintarray_valid(array);
  requires 0 <= index < array->length;
  assigns \nothing;
  ensures \result == array->data[index];
*/
int32_t jarray_get(JIntArray array, int32_t index);

/*@
  requires jintarray_valid(array);
  requires 0 <= index < array->length;
  assigns array->data[index];
  ensures jintarray_valid(array);
  ensures array->data[index] == value;
  ensures \result == value;
*/
int32_t jarray_set(JIntArray array, int32_t index, int32_t value);

/*@
  requires array == \null || jintarray_valid(array);
  assigns \nothing;
  frees array, array->data;
*/
void jarray_free(JIntArray array);

#endif
