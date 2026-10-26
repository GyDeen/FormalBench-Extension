#include "../contracts/int32.acsl.h"

void check_order_shift(void)
{
    int32_t abs_min = java_abs(INT32_MIN);
    int32_t abs_negative = java_abs(-7);
    int32_t minimum = java_min(INT32_MIN, INT32_MAX);
    int32_t maximum = java_max(INT32_MIN, INT32_MAX);
    int32_t high_bit = java_shl(1, 31);
    int32_t masked = java_shl(1, 32);
    int32_t negative_distance = java_shl(1, -1);
    //@ assert abs_overflow: abs_min == INT32_MIN;
    //@ assert abs_normal: abs_negative == 7;
    //@ assert minimum_value: minimum == INT32_MIN;
    //@ assert maximum_value: maximum == INT32_MAX;
    //@ assert shift_high_bit: high_bit == INT32_MIN;
    //@ assert shift_mask: masked == 1;
    //@ assert shift_negative: negative_distance == INT32_MIN;
}
