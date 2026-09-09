#ifndef FORMALBENCH_JAVA_ARRAYS_H
#define FORMALBENCH_JAVA_ARRAYS_H

#include <stdbool.h>
#include <stddef.h>
#include <stdint.h>

/* Opaque reference handles: NULL is Java null; allocated empty arrays are not NULL.
 * Copying a handle aliases the same array. Use only these operations for access.
 */
typedef struct JIntArrayObject *JIntArray;
typedef struct JDoubleArrayObject *JDoubleArray;
typedef struct JIntArray2Object *JIntArray2;
typedef struct JDoubleArray2Object *JDoubleArray2;

enum JArrayExitCode {
    JARRAY_NULL_REFERENCE_ERROR = 71,
    JARRAY_INDEX_OUT_OF_BOUNDS = 72,
    JARRAY_NEGATIVE_ARRAY_SIZE = 73,
    JARRAY_OUT_OF_MEMORY = 74
};

/* new: negative length -> exit 73; allocation failure -> exit 74.
 * length: null -> exit 71.
 * get/set: null -> exit 71, otherwise index < 0 or index >= length -> exit 72.
 * set returns the assigned value (Java assignment-expression semantics).
 * is_null never fails. New numeric elements are zero.
 * free is infrastructure cleanup, not a Java operation: free each allocation
 * once, after all aliases are no longer needed. free(NULL) is allowed.
 */
JIntArray jarray_new(int32_t length);
bool jarray_is_null(JIntArray array);
int32_t jarray_length(JIntArray array);
int32_t jarray_get(JIntArray array, int32_t index);
int32_t jarray_set(JIntArray array, int32_t index, int32_t value);
void jarray_free(JIntArray array);

JDoubleArray jdouble_array_new(int32_t length);
bool jdouble_array_is_null(JDoubleArray array);
int32_t jdouble_array_length(JDoubleArray array);
double jdouble_array_get(JDoubleArray array, int32_t index);
double jdouble_array_set(JDoubleArray array, int32_t index, double value);
void jdouble_array_free(JDoubleArray array);

/* new_rows(n) models new int[n][]: rows initially reference NULL.
 * new(rows, columns) models new int[rows][columns]: distinct zero-filled rows.
 * Both dimensions are checked for negativity, even when rows == 0.
 * get/set check the outer array only. A null row is a valid stored value.
 * set aliases its row argument; it does not copy or free the old row.
 * free releases the outer array ONLY: rows can be shared or referenced elsewhere.
 */
JIntArray2 jarray2_new_rows(int32_t rows);
JIntArray2 jarray2_new(int32_t rows, int32_t columns);
bool jarray2_is_null(JIntArray2 array);
int32_t jarray2_length(JIntArray2 array);
JIntArray jarray2_get(JIntArray2 array, int32_t index);
JIntArray jarray2_set(JIntArray2 array, int32_t index, JIntArray row);
void jarray2_free(JIntArray2 array);

/* Like int[][] above: rows start null and outer cleanup does not free rows. */
JDoubleArray2 jdouble_array2_new_rows(int32_t rows);
bool jdouble_array2_is_null(JDoubleArray2 array);
int32_t jdouble_array2_length(JDoubleArray2 array);
JDoubleArray jdouble_array2_get(JDoubleArray2 array, int32_t index);
JDoubleArray jdouble_array2_set(JDoubleArray2 array, int32_t index, JDoubleArray row);
void jdouble_array2_free(JDoubleArray2 array);

#endif
