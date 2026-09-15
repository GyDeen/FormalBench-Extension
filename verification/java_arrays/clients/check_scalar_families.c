#include "../contracts/jintarray.acsl.h"
#include "../contracts/jboolarray.acsl.h"
#include "../contracts/jdoublearray.acsl.h"

void check_empty_arrays(void)
{
    JIntArray ints = jarray_new(0);
    JBoolArray bools = jbool_array_new(0);
    JDoubleArray doubles = jdouble_array_new(0);
    bool null_ints = jarray_is_null(ints);
    bool null_bools = jbool_array_is_null(bools);
    bool null_doubles = jdouble_array_is_null(doubles);
    int32_t ni = jarray_length(ints);
    int32_t nb = jbool_array_length(bools);
    int32_t nd = jdouble_array_length(doubles);
    //@ assert !null_ints && !null_bools && !null_doubles;
    //@ assert ni == 0 && nb == 0 && nd == 0;
    bool null_reference = jarray_is_null(NULL);
    //@ assert null_reference;
}

void check_int_bool_set_get(void)
{
    JIntArray ints = jarray_new(2);
    JBoolArray bools = jbool_array_new(2);
    int32_t initial_int = jarray_get(ints, 1);
    bool initial_bool = jbool_array_get(bools, 1);
    //@ assert initial_int == 0 && !initial_bool;
    int32_t assigned_int = jarray_set(ints, 1, 5);
    bool assigned_bool = jbool_array_set(bools, 1, true);
    int32_t observed_int = jarray_get(ints, 1);
    bool observed_bool = jbool_array_get(bools, 1);
    int32_t untouched_int = jarray_get(ints, 0);
    bool untouched_bool = jbool_array_get(bools, 0);
    //@ assert assigned_int == 5 && observed_int == 5;
    //@ assert assigned_bool && observed_bool;
    //@ assert untouched_int == 0 && !untouched_bool;
}
