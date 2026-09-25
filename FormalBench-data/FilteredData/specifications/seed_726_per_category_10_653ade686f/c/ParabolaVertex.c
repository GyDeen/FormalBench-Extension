#include "java_arrays.h"
#include <math.h>
#include <stdint.h>

/*@
requires a != 0;
assigns \nothing;
allocates \result, \result->data;
frees \nothing;
ensures jdoublearray_valid_nonnull(\result) && \result->length == 2;
ensures \fresh(\result, sizeof(*\result)) && \fresh(\result->data, 2 * sizeof(double));
ensures \result->data[0] == (double)(-(real)b / (2.0 * (real)a));
ensures \result->data[1] == (double)((double)((double)(4.0 * (real)a * c) - (double)((real)b * b)) / (4.0 * (real)a));
*/
JDoubleArray parabolaVertex(int32_t a, int32_t b, int32_t c) {
    JDoubleArray vertex = jdouble_array_new(2);
    double x = -(double)b / (2.0 * (double)a);
    double four_a_c = 4.0 * (double)a;
    four_a_c = four_a_c * (double)c;
    double b_squared = (double)b * (double)b;
    double y = (four_a_c - b_squared) / (4.0 * (double)a);
    jdouble_array_set(vertex, 0, x);
    jdouble_array_set(vertex, 1, y);
    return vertex;
}
