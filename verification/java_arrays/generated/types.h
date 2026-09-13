#ifndef FORMALBENCH_VERIFICATION_JAVA_ARRAY_TYPES_H
#define FORMALBENCH_VERIFICATION_JAVA_ARRAY_TYPES_H

#include <stdbool.h>
#include <stddef.h>
#include <stdint.h>

/* Generated from production opaque handles and DEFINE_ARRAY invocations. */
typedef struct JIntArrayObject *JIntArray;
typedef struct JBoolArrayObject *JBoolArray;
typedef struct JDoubleArrayObject *JDoubleArray;
typedef struct JIntArray2Object *JIntArray2;
typedef struct JDoubleArray2Object *JDoubleArray2;

enum JArrayExitCode {
    JARRAY_NULL_REFERENCE_ERROR = 71,
    JARRAY_INDEX_OUT_OF_BOUNDS = 72,
    JARRAY_NEGATIVE_ARRAY_SIZE = 73,
    JARRAY_OUT_OF_MEMORY = 74
};

struct JIntArrayObject { int32_t length; int32_t *data; };
struct JBoolArrayObject { int32_t length; bool *data; };
struct JDoubleArrayObject { int32_t length; double *data; };
struct JIntArray2Object { int32_t length; JIntArray *data; };
struct JDoubleArray2Object { int32_t length; JDoubleArray *data; };

#endif
