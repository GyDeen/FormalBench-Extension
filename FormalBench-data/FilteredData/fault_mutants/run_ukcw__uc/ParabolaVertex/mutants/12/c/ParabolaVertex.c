#include "java_arrays.h"
#include <stdint.h>

static int32_t java_mul(int32_t left, int32_t right) { return (int32_t)((uint32_t)left * (uint32_t)right); }

JDoubleArray parabolaVertex(int32_t a, int32_t b, int32_t c) {
    JDoubleArray vertex = jdouble_array_new(INT32_C(2));
    double __value_1 = ((-(((double)(b)))) / (2.0 * a));
    jdouble_array_set(vertex, INT32_C(0), __value_1);
    double __value_2 = (((((double)(4.0)) + java_mul(a, c)) - (((double)(b)) * b)) / (4.0 * a));
    jdouble_array_set(vertex, INT32_C(1), __value_2);
    return vertex;
}
