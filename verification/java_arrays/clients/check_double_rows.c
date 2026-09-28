#include "../contracts/jdoublearray2.acsl.h"

void check_double_null_and_empty_rows(void)
{
    JDoubleArray2 empty = jdouble_array2_new_rows(0);
    bool is_null = jdouble_array2_is_null(empty);
    int32_t length = jdouble_array2_length(empty);
    //@ assert !is_null && length == 0;
    JDoubleArray2 a = jdouble_array2_new_rows(2);
    JDoubleArray first = jdouble_array2_get(a, 0);
    JDoubleArray second = jdouble_array2_get(a, 1);
    //@ assert first == \null && second == \null;
    JDoubleArray2 zero_columns = jdouble_array2_new(2, 0);
    JDoubleArray empty_row = jdouble_array2_get(zero_columns, 0);
    bool row_is_null = jdouble_array_is_null(empty_row);
    int32_t columns = jdouble_array_length(empty_row);
    //@ assert !row_is_null && columns == 0;
}

void check_double_rectangular_independence(void)
{
    JDoubleArray2 a = jdouble_array2_new(2, 2);
    JDoubleArray first = jdouble_array2_get(a, 0);
    JDoubleArray second = jdouble_array2_get(a, 1);
    jdouble_array_set(first, 0, 5.0);
    double updated = jdouble_array_get(first, 0);
    double independent = jdouble_array_get(second, 0);
    int32_t length = jdouble_array2_length(a);
    //@ assert updated == 5.0 && independent == 0.0;
    //@ assert length == 2;
    //@ assert jdoublearray2_valid(a);
}

void check_double_sharing_and_replacement(void)
{
    JDoubleArray2 a = jdouble_array2_new(2, 2);
    JDoubleArray shared = jdouble_array2_get(a, 0);
    JDoubleArray old_second = jdouble_array2_get(a, 1);
    JDoubleArray returned = jdouble_array2_set(a, 1, shared);
    //@ assert returned == shared;
    JDoubleArray alias = jdouble_array2_get(a, 1);
    jdouble_array_set(shared, 0, 5.0);
    double observed = jdouble_array_get(alias, 0);
    //@ assert observed == 5.0;

    JDoubleArray replacement = jdouble_array_new(3);
    jdouble_array2_set(a, 0, replacement);
    JDoubleArray first = jdouble_array2_get(a, 0);
    JDoubleArray second = jdouble_array2_get(a, 1);
    int32_t first_length = jdouble_array_length(first);
    int32_t second_length = jdouble_array_length(second);
    int32_t outer_length = jdouble_array2_length(a);
    double retained = jdouble_array_get(second, 0);
    double old_value = jdouble_array_get(old_second, 0);
    //@ assert first == replacement && second == shared;
    //@ assert first_length == 3 && second_length == 2 && outer_length == 2;
    //@ assert retained == 5.0 && old_value == 0.0;
    jdouble_array2_set(a, 0, NULL);
    JDoubleArray null_row = jdouble_array2_get(a, 0);
    //@ assert null_row == \null;
    //@ assert jdoublearray_valid(replacement);
    //@ assert jdoublearray2_valid(a);
}
