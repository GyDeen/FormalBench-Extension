#ifndef FORMALBENCH_VERIFICATION_JDOUBLEARRAY_ACSL_H
#define FORMALBENCH_VERIFICATION_JDOUBLEARRAY_ACSL_H

#include "../generated/types.h"

/*@
  predicate jdoublearray_valid{L}(JDoubleArray a) =
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
  requires jdoublearray_valid(array);
  assigns \nothing;
  ensures \result == array->length;
*/
int32_t jdouble_array_length(JDoubleArray array);

/*@
  requires jdoublearray_valid(array);
  requires 0 <= index < array->length;
  assigns \nothing;
  ensures \result == array->data[index];
*/
double jdouble_array_get(JDoubleArray array, int32_t index);

/*@
  requires jdoublearray_valid(array);
  requires 0 <= index < array->length;
  assigns array->data[index];
  ensures jdoublearray_valid(array);
  ensures array->data[index] == value;
  ensures \result == value;
*/
double jdouble_array_set(JDoubleArray array, int32_t index, double value);

/*@
  requires array == \null || jdoublearray_valid(array);
  assigns \nothing;
  frees array, array->data;
*/
void jdouble_array_free(JDoubleArray array);

#endif
