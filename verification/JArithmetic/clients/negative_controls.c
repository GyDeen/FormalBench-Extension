#include "../contracts/int32.acsl.h"

void negative_wrap(void)
{
    int32_t result = java_add(INT32_MAX, 1);
    //@ assert negative_no_wrap: result == INT32_MAX;
}

void negative_division_zero(void)
{
    int32_t result = java_div(1, 0);
    (void)result;
}
