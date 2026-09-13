#ifndef FORMALBENCH_VERIFICATION_JBOOLARRAY_ACSL_H
#define FORMALBENCH_VERIFICATION_JBOOLARRAY_ACSL_H

#include "../generated/types.h"

/*@
  predicate jboolarray_valid{L}(JBoolArray a) =
    \valid_read(a) && a->length >= 0 && 
    (
      (a->length == 0 && a->data == \null) || (a->length > 0 && \valid(a->data + (0 .. a->length - 1)) &&
      \separated(a, a->data + (0 .. a->length - 1)))
    );
*/

/*@
  requires length >= 0;
  assigns \nothing;
  allocates \result, \result->data;
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
  requires jboolarray_valid(array);
  assigns \nothing;
  ensures \result == array->length;
*/
int32_t jbool_array_length(JBoolArray array);

/*@
  requires jboolarray_valid(array);
  requires 0 <= index < array->length;
  assigns \nothing;
  ensures \result == array->data[index];
*/
bool jbool_array_get(JBoolArray array, int32_t index);

/*@
  requires jboolarray_valid(array);
  requires 0 <= index < array->length;
  assigns array->data[index];
  ensures jboolarray_valid(array);
  ensures array->data[index] == value;
  ensures \result == value;
*/
bool jbool_array_set(JBoolArray array, int32_t index, bool value);

/*@
  requires array == \null || jboolarray_valid(array);
  assigns \nothing;
  frees array, array->data;
*/
void jbool_array_free(JBoolArray array);

#endif
