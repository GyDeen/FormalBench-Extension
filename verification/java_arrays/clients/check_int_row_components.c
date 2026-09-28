/* Isolated diagnostics for preservation after a rectangular-row element write.
 * One assertion per isolated function: components do not assume other checks.
 * The final, explicitly marked guided experiment uses sequential assertions.
 * Stop before later getters can contribute additional callee preconditions.
 * Constructor/accessor contracts are used without implementation bodies.
 */
#include "../contracts/jintarray2.acsl.h"

void check_second_metadata_valid(void)
{
    JIntArray2 a = jarray2_new(2, 2);
    JIntArray first = jarray2_get(a, 0);
    JIntArray second = jarray2_get(a, 1);
    jarray_set(first, 0, 5);
    //@ assert second_metadata_valid: jintarray_metadata_valid(second);
}

void check_second_metadata_initialized(void)
{
    JIntArray2 a = jarray2_new(2, 2);
    JIntArray first = jarray2_get(a, 0);
    JIntArray second = jarray2_get(a, 1);
    jarray_set(first, 0, 5);
    //@ assert second_metadata_initialized: jintarray_metadata_initialized(second);
}

void check_second_buffer_valid(void)
{
    JIntArray2 a = jarray2_new(2, 2);
    JIntArray first = jarray2_get(a, 0);
    JIntArray second = jarray2_get(a, 1);
    jarray_set(first, 0, 5);
    //@ assert second_buffer_valid: jintarray_buffer_valid(second);
}

void check_second_buffer_initialized(void)
{
    JIntArray2 a = jarray2_new(2, 2);
    JIntArray first = jarray2_get(a, 0);
    JIntArray second = jarray2_get(a, 1);
    jarray_set(first, 0, 5);
    //@ assert second_buffer_initialized: jintarray_buffer_initialized(second);
}

void check_second_storage_separated(void)
{
    JIntArray2 a = jarray2_new(2, 2);
    JIntArray first = jarray2_get(a, 0);
    JIntArray second = jarray2_get(a, 1);
    jarray_set(first, 0, 5);
    //@ assert second_storage_separated: jintarray_storage_separated(second);
}

void check_second_valid(void)
{
    JIntArray2 a = jarray2_new(2, 2);
    JIntArray first = jarray2_get(a, 0);
    JIntArray second = jarray2_get(a, 1);
    jarray_set(first, 0, 5);
    //@ assert second_valid: jintarray_valid(second);
}

void check_a_metadata_valid(void)
{
    JIntArray2 a = jarray2_new(2, 2);
    JIntArray first = jarray2_get(a, 0);
    JIntArray second = jarray2_get(a, 1);
    jarray_set(first, 0, 5);
    //@ assert a_metadata_valid: jintarray2_metadata_valid(a);
}

void check_a_metadata_initialized(void)
{
    JIntArray2 a = jarray2_new(2, 2);
    JIntArray first = jarray2_get(a, 0);
    JIntArray second = jarray2_get(a, 1);
    jarray_set(first, 0, 5);
    //@ assert a_metadata_initialized: jintarray2_metadata_initialized(a);
}

void check_a_buffer_valid(void)
{
    JIntArray2 a = jarray2_new(2, 2);
    JIntArray first = jarray2_get(a, 0);
    JIntArray second = jarray2_get(a, 1);
    jarray_set(first, 0, 5);
    //@ assert a_buffer_valid: jintarray2_buffer_valid(a);
}

void check_a_buffer_initialized(void)
{
    JIntArray2 a = jarray2_new(2, 2);
    JIntArray first = jarray2_get(a, 0);
    JIntArray second = jarray2_get(a, 1);
    jarray_set(first, 0, 5);
    //@ assert a_buffer_initialized: jintarray2_buffer_initialized(a);
}

void check_a_storage_separated(void)
{
    JIntArray2 a = jarray2_new(2, 2);
    JIntArray first = jarray2_get(a, 0);
    JIntArray second = jarray2_get(a, 1);
    jarray_set(first, 0, 5);
    //@ assert a_storage_separated: jintarray2_storage_separated(a);
}

void check_a_rows_valid(void)
{
    JIntArray2 a = jarray2_new(2, 2);
    JIntArray first = jarray2_get(a, 0);
    JIntArray second = jarray2_get(a, 1);
    jarray_set(first, 0, 5);
    //@ assert a_rows_valid: jintarray2_rows_valid(a);
}

void check_a_rows_compatible(void)
{
    JIntArray2 a = jarray2_new(2, 2);
    JIntArray first = jarray2_get(a, 0);
    JIntArray second = jarray2_get(a, 1);
    jarray_set(first, 0, 5);
    //@ assert a_rows_compatible: jintarray2_rows_compatible(a);
}

void check_a_valid(void)
{
    JIntArray2 a = jarray2_new(2, 2);
    JIntArray first = jarray2_get(a, 0);
    JIntArray second = jarray2_get(a, 1);
    jarray_set(first, 0, 5);
    //@ assert a_valid: jintarray2_valid(a);
}

void check_row0_valid(void)
{
    JIntArray2 a = jarray2_new(2, 2);
    JIntArray first = jarray2_get(a, 0);
    JIntArray second = jarray2_get(a, 1);
    jarray_set(first, 0, 5);
    //@ assert row0_valid: jintarray_valid(a->data[0]);
}

void check_row1_valid(void)
{
    JIntArray2 a = jarray2_new(2, 2);
    JIntArray first = jarray2_get(a, 0);
    JIntArray second = jarray2_get(a, 1);
    jarray_set(first, 0, 5);
    //@ assert row1_valid: jintarray_valid(a->data[1]);
}

void check_row0_compatible(void)
{
    JIntArray2 a = jarray2_new(2, 2);
    JIntArray first = jarray2_get(a, 0);
    JIntArray second = jarray2_get(a, 1);
    jarray_set(first, 0, 5);
    //@ assert row0_compatible: jintarray2_row_compatible(a, a->data[0]);
}

void check_row1_compatible(void)
{
    JIntArray2 a = jarray2_new(2, 2);
    JIntArray first = jarray2_get(a, 0);
    JIntArray second = jarray2_get(a, 1);
    jarray_set(first, 0, 5);
    //@ assert row1_compatible: jintarray2_row_compatible(a, a->data[1]);
}

/* A guided composition experiment. Unlike the isolated tests above, these
 * assertions are sequential; claim success only if EVERY obligation proves. */
void check_guided_matrix_valid(void)
{
    JIntArray2 a = jarray2_new(2, 2);
    JIntArray first = jarray2_get(a, 0);
    JIntArray second = jarray2_get(a, 1);
    jarray_set(first, 0, 5);
    //@ assert shape: a->length == 2 && a->data[0] == first && a->data[1] == second;
    //@ assert first_valid: jintarray_valid(first);
    //@ assert second_valid: jintarray_valid(second);
    //@ assert first_compatible: jintarray2_row_compatible(a, first);
    //@ assert second_compatible: jintarray2_row_compatible(a, second);
    //@ assert rows_valid: jintarray2_rows_valid(a);
    //@ assert rows_compatible: jintarray2_rows_compatible(a);
    //@ assert matrix_valid: jintarray2_valid(a);
}
