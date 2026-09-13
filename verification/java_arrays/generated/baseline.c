#include "types.h"

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

JIntArray jarray_new(int32_t length) {
        check_length(length);
        JIntArray array = allocate(1, sizeof(*array));
        array->length = length;
        array->data = allocate((size_t)length, sizeof(*array->data));
        for (int32_t i = 0; i < length; ++i) { array->data[i] = 0; }
        return array;
    }
    bool jarray_is_null(JIntArray array) { return array == NULL; }
    int32_t jarray_length(JIntArray array) {
        check_reference(array);
        return array->length;
    }
    int32_t jarray_get(JIntArray array, int32_t index) {
        check_reference(array);
        check_index(array->length, index);
        return array->data[index];
    }
    int32_t jarray_set(JIntArray array, int32_t index, int32_t value) {
        check_reference(array);
        check_index(array->length, index);
        array->data[index] = value;
        return value;
    }
    void jarray_free(JIntArray array) {
        if (array != NULL) { free(array->data); free(array); }
    }

JBoolArray jbool_array_new(int32_t length) {
        check_length(length);
        JBoolArray array = allocate(1, sizeof(*array));
        array->length = length;
        array->data = allocate((size_t)length, sizeof(*array->data));
        for (int32_t i = 0; i < length; ++i) { array->data[i] = false; }
        return array;
    }
    bool jbool_array_is_null(JBoolArray array) { return array == NULL; }
    int32_t jbool_array_length(JBoolArray array) {
        check_reference(array);
        return array->length;
    }
    bool jbool_array_get(JBoolArray array, int32_t index) {
        check_reference(array);
        check_index(array->length, index);
        return array->data[index];
    }
    bool jbool_array_set(JBoolArray array, int32_t index, bool value) {
        check_reference(array);
        check_index(array->length, index);
        array->data[index] = value;
        return value;
    }
    void jbool_array_free(JBoolArray array) {
        if (array != NULL) { free(array->data); free(array); }
    }

JDoubleArray jdouble_array_new(int32_t length) {
        check_length(length);
        JDoubleArray array = allocate(1, sizeof(*array));
        array->length = length;
        array->data = allocate((size_t)length, sizeof(*array->data));
        for (int32_t i = 0; i < length; ++i) { array->data[i] = 0.0; }
        return array;
    }
    bool jdouble_array_is_null(JDoubleArray array) { return array == NULL; }
    int32_t jdouble_array_length(JDoubleArray array) {
        check_reference(array);
        return array->length;
    }
    double jdouble_array_get(JDoubleArray array, int32_t index) {
        check_reference(array);
        check_index(array->length, index);
        return array->data[index];
    }
    double jdouble_array_set(JDoubleArray array, int32_t index, double value) {
        check_reference(array);
        check_index(array->length, index);
        array->data[index] = value;
        return value;
    }
    void jdouble_array_free(JDoubleArray array) {
        if (array != NULL) { free(array->data); free(array); }
    }

JIntArray2 jarray2_new_rows(int32_t length) {
        check_length(length);
        JIntArray2 array = allocate(1, sizeof(*array));
        array->length = length;
        array->data = allocate((size_t)length, sizeof(*array->data));
        for (int32_t i = 0; i < length; ++i) { array->data[i] = NULL; }
        return array;
    }
    bool jarray2_is_null(JIntArray2 array) { return array == NULL; }
    int32_t jarray2_length(JIntArray2 array) {
        check_reference(array);
        return array->length;
    }
    JIntArray jarray2_get(JIntArray2 array, int32_t index) {
        check_reference(array);
        check_index(array->length, index);
        return array->data[index];
    }
    JIntArray jarray2_set(JIntArray2 array, int32_t index, JIntArray value) {
        check_reference(array);
        check_index(array->length, index);
        array->data[index] = value;
        return value;
    }
    void jarray2_free(JIntArray2 array) {
        if (array != NULL) { free(array->data); free(array); }
    }

JDoubleArray2 jdouble_array2_new_rows(int32_t length) {
        check_length(length);
        JDoubleArray2 array = allocate(1, sizeof(*array));
        array->length = length;
        array->data = allocate((size_t)length, sizeof(*array->data));
        for (int32_t i = 0; i < length; ++i) { array->data[i] = NULL; }
        return array;
    }
    bool jdouble_array2_is_null(JDoubleArray2 array) { return array == NULL; }
    int32_t jdouble_array2_length(JDoubleArray2 array) {
        check_reference(array);
        return array->length;
    }
    JDoubleArray jdouble_array2_get(JDoubleArray2 array, int32_t index) {
        check_reference(array);
        check_index(array->length, index);
        return array->data[index];
    }
    JDoubleArray jdouble_array2_set(JDoubleArray2 array, int32_t index, JDoubleArray value) {
        check_reference(array);
        check_index(array->length, index);
        array->data[index] = value;
        return value;
    }
    void jdouble_array2_free(JDoubleArray2 array) {
        if (array != NULL) { free(array->data); free(array); }
    }

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
