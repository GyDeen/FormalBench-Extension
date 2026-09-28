#include "java_arrays.h"
#include <stdint.h>

/*@
  assigns \nothing;
  allocates \result, \result->data;
  frees \nothing;
  ensures jintarray_valid_nonnull(\result) && \result->length == 2;
  ensures \fresh(\result, sizeof(*\result));
  ensures \fresh(\result->data, 2 * sizeof(int32_t));
  ensures l1 < l2 && r1 < r2 ==> \result->data[0] == (l1 < r1 ? l1 : r1) && \result->data[1] == (r2 > l2 ? r2 : l2);
  ensures l1 > l2 && r1 > r2 ==> \result->data[0] == (l2 < r2 ? l2 : r2) && \result->data[1] == (l1 > r1 ? l1 : r1);
  ensures !(l1 < l2 && r1 < r2) && !(l1 > l2 && r1 > r2) ==> \result->data[0] == l1 && \result->data[1] == r1;
*/
JIntArray findPoints(int32_t l1, int32_t r1, int32_t l2, int32_t r2) {
    JIntArray res = jarray_new(2);
    if (l1 < l2 && r1 < r2) {
        jarray_set(res, 0, l1 < r1 ? l1 : r1);
        jarray_set(res, 1, r2 > l2 ? r2 : l2);
    } else if (l1 > l2 && r1 > r2) {
        jarray_set(res, 0, l2 < r2 ? l2 : r2);
        jarray_set(res, 1, l1 > r1 ? l1 : r1);
    } else {
        jarray_set(res, 0, l1);
        jarray_set(res, 1, r1);
    }
    return res;
}
