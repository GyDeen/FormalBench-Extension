#include "../contracts/jintarray2.acsl.h"

void check_int_null_and_empty_rows(void)
{
    JIntArray2 empty = jarray2_new_rows(0);
    bool is_null = jarray2_is_null(empty);
    int32_t length = jarray2_length(empty);
    //@ assert !is_null && length == 0;
    JIntArray2 a = jarray2_new_rows(2);
    JIntArray first = jarray2_get(a, 0);
    JIntArray second = jarray2_get(a, 1);
    //@ assert first == \null && second == \null;
    JIntArray2 zero_columns = jarray2_new(2, 0);
    JIntArray empty_row = jarray2_get(zero_columns, 0);
    bool row_is_null = jarray_is_null(empty_row);
    int32_t columns = jarray_length(empty_row);
    //@ assert !row_is_null && columns == 0;
}

void check_int_rectangular_independence(void)
{
    JIntArray2 a = jarray2_new(2, 2);
    JIntArray first = jarray2_get(a, 0);
    JIntArray second = jarray2_get(a, 1);
    jarray_set(first, 0, 5);
    int32_t updated = jarray_get(first, 0);
    int32_t independent = jarray_get(second, 0);
    int32_t length = jarray2_length(a);
    //@ assert updated == 5 && independent == 0;
    //@ assert length == 2;
    //@ assert jintarray2_valid(a);
}

void check_int_sharing_and_replacement(void)
{
    JIntArray2 a = jarray2_new(2, 2);
    JIntArray shared = jarray2_get(a, 0);
    JIntArray old_second = jarray2_get(a, 1);
    JIntArray returned = jarray2_set(a, 1, shared);
    //@ assert returned == shared;
    JIntArray alias = jarray2_get(a, 1);
    jarray_set(shared, 0, 5);
    int32_t observed = jarray_get(alias, 0);
    //@ assert observed == 5;

    JIntArray replacement = jarray_new(3);
    jarray2_set(a, 0, replacement);
    JIntArray first = jarray2_get(a, 0);
    JIntArray second = jarray2_get(a, 1);
    int32_t first_length = jarray_length(first);
    int32_t second_length = jarray_length(second);
    int32_t outer_length = jarray2_length(a);
    int32_t retained = jarray_get(second, 0);
    int32_t old_value = jarray_get(old_second, 0);
    //@ assert first == replacement && second == shared;
    //@ assert first_length == 3 && second_length == 2 && outer_length == 2;
    //@ assert retained == 5 && old_value == 0;
    jarray2_set(a, 0, NULL);
    JIntArray null_row = jarray2_get(a, 0);
    //@ assert null_row == \null;
    //@ assert jintarray_valid(replacement);
    //@ assert jintarray2_valid(a);
}
