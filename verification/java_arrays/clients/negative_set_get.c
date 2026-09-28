#include "../contracts/jdoublearray.acsl.h"

/* Run separately. Both deliberately false assertions must remain unproved. */
void negative_set_get(void)
{
    JDoubleArray a = jdouble_array_new(3);
    jdouble_array_set(a, 1, 5.0);
    double observed = jdouble_array_get(a, 1);
    //@ assert negative_new: observed == 6.0;
}

/*@
  requires a != \null;
  requires jdoublearray_valid(a);
  requires a->length >= 2;
  assigns a->data[1];
*/
void negative_existing_array(JDoubleArray a)
{
    jdouble_array_set(a, 1, 5.0);
    double observed = jdouble_array_get(a, 1);
    //@ assert negative_existing: observed == 6.0;
}
