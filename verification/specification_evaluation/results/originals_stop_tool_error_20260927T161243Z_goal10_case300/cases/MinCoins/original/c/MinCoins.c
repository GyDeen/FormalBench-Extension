#include "java_arrays.h"
#include <stdint.h>

/*@
assigns \nothing;
ensures \result == (int32_t)((integer)left + right);
*/
static int32_t java_add(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left + (uint32_t)right);
}

/*@
assigns \nothing;
ensures \result == (int32_t)((integer)left - right);
*/
static int32_t java_sub(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left - (uint32_t)right);
}

/*@
logic integer fb_inc_cost(integer x) = x == INT32_MAX ? x : x + 1;
logic integer fb_cost{L}(JIntArray a, integer m, integer v, integer j) =
  v == 0 ? 0 : v < 0 || j <= 0 ? INT32_MAX : a->data[j - 1] > v ? fb_cost(a, m, v, j - 1) :
  \min(fb_cost(a, m, v, j - 1), fb_inc_cost(fb_cost(a, m, v - a->data[j - 1], m)));
*/
/*@
requires v <= 0 || m <= 0 || (jintarray_valid_nonnull(coins) && m <= coins->length && (\forall integer k; 0 <= k < m ==> coins->data[k] > 0));
decreases \max(0, v);
assigns \nothing;
ensures \result == (v == 0 ? 0 : v < 0 || m <= 0 ? INT32_MAX : fb_cost(coins, m, v, m));
*/
int32_t minCoins(JIntArray coins, int32_t m, int32_t v) {
    if (v == 0) {
        return 0;
    }
    if (v < 0) {
        return INT32_MAX;
    }

    int32_t minCoinsValue = INT32_MAX;
    /*@
    loop invariant 0 <= i <= \max(0, m);
    loop invariant m > 0 ==> minCoinsValue == fb_cost(coins, m, v, i);
    loop invariant m <= 0 ==> minCoinsValue == INT32_MAX;
    loop assigns i, minCoinsValue;
    loop variant \max(0, m) - i;
    */
    for (int32_t i = 0; i < m; i = java_add(i, 1)) {
        int32_t coin = jarray_get(coins, i);
        int32_t remainingValue = java_sub(v, coin);
        if (remainingValue >= 0) {
            int32_t totalCoins = minCoins(coins, m, remainingValue);
            if (totalCoins != INT32_MAX) {
                totalCoins = java_add(totalCoins, 1);
            }
            if (totalCoins < minCoinsValue) {
                minCoinsValue = totalCoins;
            }
        }
    }
    return minCoinsValue;
}
