#include "../contracts/jdoublearray.acsl.h"

void check_set_get(void)
{
    JDoubleArray a = jdouble_array_new(3);
    double initial = jdouble_array_get(a, 1);
    //@ assert initial == 0.0;
    double assigned = jdouble_array_set(a, 1, 5.0);
    //@ assert assigned == 5.0;
    double observed = jdouble_array_get(a, 1);
    //@ assert observed == 5.0;
    double untouched = jdouble_array_get(a, 0);
    //@ assert untouched == 0.0;
    int32_t length = jdouble_array_length(a);
    //@ assert length == 3;
}

/*@
  requires a != \null;
  requires jdoublearray_valid(a);
  requires a->length >= 2;
  assigns a->data[1];
*/
void check_existing_array(JDoubleArray a)
{
    int32_t before_length = jdouble_array_length(a);
    double before = jdouble_array_get(a, 0);
    double assigned = jdouble_array_set(a, 1, 5.0);
    double observed = jdouble_array_get(a, 1);
    double untouched = jdouble_array_get(a, 0);
    int32_t after_length = jdouble_array_length(a);
    //@ assert assigned == 5.0 && observed == 5.0;
    //@ assert untouched == before;
    //@ assert after_length == before_length;
}
