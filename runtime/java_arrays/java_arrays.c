#include "java_arrays.h"

#include <stdio.h>
#include <stdlib.h>

static _Noreturn void array_error(enum JArrayExitCode code) {
    const char *message;
    switch (code) {
    case JARRAY_NULL_REFERENCE_ERROR:
        message = "NULL_REFERENCE_ERROR (java.lang.NullPointerException)";
        break;
    case JARRAY_INDEX_OUT_OF_BOUNDS:
        message = "INDEX_OUT_OF_BOUNDS (java.lang.ArrayIndexOutOfBoundsException)";
        break;
    case JARRAY_NEGATIVE_ARRAY_SIZE:
        message = "NEGATIVE_ARRAY_SIZE (java.lang.NegativeArraySizeException)";
        break;
    default:
        message = "OUT_OF_MEMORY (java.lang.OutOfMemoryError)";
        break;
    }
    fprintf(stderr, "JAVA_ARRAY_ERROR: %s\n", message);
    exit((int)code);
}

static void check_length(int32_t length) {
    if (length < 0) {
        array_error(JARRAY_NEGATIVE_ARRAY_SIZE);
    }
}

static void check_reference(const void *array) {
    if (array == NULL) {
        array_error(JARRAY_NULL_REFERENCE_ERROR);
    }
}

static void check_index(int32_t length, int32_t index) {
    if (index < 0 || index >= length) {
        array_error(JARRAY_INDEX_OUT_OF_BOUNDS);
    }
}

static void *allocate(size_t count, size_t size) {
    if (count == 0) {
        return NULL;
    }
    if (count > SIZE_MAX / size) {
        array_error(JARRAY_OUT_OF_MEMORY);
    }
    void *result = malloc(count * size);
    if (result == NULL) {
        array_error(JARRAY_OUT_OF_MEMORY);
    }
    return result;
}

/* Typed initialization avoids assuming all-bits-zero is a null pointer or
 * floating-point zero. The object allocation gives empty arrays unique identity.
 */
#define DEFINE_ARRAY(OBJECT, HANDLE, VALUE, PREFIX, NEW, ZERO)                       \
    struct OBJECT { int32_t length; VALUE *data; };                            \
    HANDLE NEW(int32_t length) {                                     \
        check_length(length);                                                \
        HANDLE array = allocate(1, sizeof(*array));                           \
        array->length = length;                                              \
        array->data = allocate((size_t)length, sizeof(*array->data));          \
        for (int32_t i = 0; i < length; ++i) { array->data[i] = ZERO; }        \
        return array;                                                        \
    }                                                                        \
    bool PREFIX##_is_null(HANDLE array) { return array == NULL; }              \
    int32_t PREFIX##_length(HANDLE array) {                                   \
        check_reference(array);                                              \
        return array->length;                                                \
    }                                                                        \
    VALUE PREFIX##_get(HANDLE array, int32_t index) {                          \
        check_reference(array);                                              \
        check_index(array->length, index);                                    \
        return array->data[index];                                           \
    }                                                                        \
    VALUE PREFIX##_set(HANDLE array, int32_t index, VALUE value) {             \
        check_reference(array);                                              \
        check_index(array->length, index);                                    \
        array->data[index] = value;                                           \
        return value;                                                        \
    }                                                                        \
    void PREFIX##_free(HANDLE array) {                                        \
        if (array != NULL) { free(array->data); free(array); }                 \
    }

DEFINE_ARRAY(JIntArrayObject, JIntArray, int32_t, jarray, jarray_new, 0)
DEFINE_ARRAY(JDoubleArrayObject, JDoubleArray, double, jdouble_array, jdouble_array_new, 0.0)
DEFINE_ARRAY(JIntArray2Object, JIntArray2, JIntArray, jarray2, jarray2_new_rows, NULL)
DEFINE_ARRAY(JDoubleArray2Object, JDoubleArray2, JDoubleArray, jdouble_array2, jdouble_array2_new_rows, NULL)
#undef DEFINE_ARRAY

JIntArray2 jarray2_new(int32_t rows, int32_t columns) {
    check_length(rows);
    check_length(columns);
    JIntArray2 array = jarray2_new_rows(rows);
    for (int32_t i = 0; i < rows; ++i) {
        jarray2_set(array, i, jarray_new(columns));
    }
    return array;
}

JDoubleArray2 jdouble_array2_new(int32_t rows, int32_t columns) {
    check_length(rows);
    check_length(columns);
    JDoubleArray2 array = jdouble_array2_new_rows(rows);
    for (int32_t i = 0; i < rows; ++i) {
        jdouble_array2_set(array, i, jdouble_array_new(columns));
    }
    return array;
}
