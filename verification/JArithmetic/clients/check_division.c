#include "../contracts/int32.acsl.h"

void check_division(void)
{
    int32_t negative = java_div(-7, 3);
    int32_t negative_divisor = java_div(7, -3);
    int32_t exceptional_overflow = java_div(INT32_MIN, -1);
    //@ assert truncate_negative: negative == -2;
    //@ assert truncate_divisor: negative_divisor == -2;
    //@ assert divide_overflow: exceptional_overflow == INT32_MIN;
}

/*@
  requires divisor != 0;
  assigns \nothing;
*/
void check_symbolic_division(int32_t dividend, int32_t divisor)
{
    int32_t result = java_div(dividend, divisor);
    //@ assert divide_contract: result == (int32_t)((integer)dividend / divisor);
}
