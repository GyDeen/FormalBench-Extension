#include "../contracts/int32.acsl.h"

void check_wrap(void)
{
    int32_t add = java_add(INT32_MAX, 1);
    int32_t sub = java_sub(INT32_MIN, 1);
    int32_t mul = java_mul(INT32_MIN, -1);
    int32_t square = java_mul(65536, 65536);
    //@ assert add_wrap: add == INT32_MIN;
    //@ assert sub_wrap: sub == INT32_MAX;
    //@ assert mul_wrap: mul == INT32_MIN;
    //@ assert mul_low_bits: square == 0;
}

/*@ assigns \nothing; */
void check_symbolic(int32_t left, int32_t right)
{
    int32_t add = java_add(left, right);
    int32_t sub = java_sub(left, right);
    int32_t mul = java_mul(left, right);
    //@ assert add_contract: add == (int32_t)((integer)left + right);
    //@ assert sub_contract: sub == (int32_t)((integer)left - right);
    //@ assert mul_contract: mul == (int32_t)((integer)left * right);
}
