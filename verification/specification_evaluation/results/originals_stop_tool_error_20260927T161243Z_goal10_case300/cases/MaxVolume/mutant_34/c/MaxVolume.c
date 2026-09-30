#include "java_arrays.h"
#include <stdint.h>

/*@
assigns \nothing;
ensures \result == (int32_t)((integer)left + right);
*/
static int32_t java_add(int32_t left, int32_t right) { return (int32_t)((uint32_t)left + (uint32_t)right); }
/*@
assigns \nothing;
ensures \result == (int32_t)((integer)left - right);
*/
static int32_t java_sub(int32_t left, int32_t right) { return (int32_t)((uint32_t)left - (uint32_t)right); }
/*@
assigns \nothing;
ensures \result == (int32_t)((integer)left * right);
*/
static int32_t java_mul(int32_t left, int32_t right) { return (int32_t)((uint32_t)left * (uint32_t)right); }

/*@
terminates s < INT32_MAX;
assigns \nothing;
ensures s < INT32_MAX;
ensures \result >= 0;
ensures \forall integer l, b; 1 <= l <= s && 1 <= b <= s - l + 1 ==> (int32_t)(l * b * (s - l - b)) <= \result;
ensures \result == 0 || (\exists integer l, b; 1 <= l <= s && 1 <= b <= s - l + 1 && \result == (int32_t)(l * b * (s - l - b)));
*/
int32_t maxVolume(int32_t s) {
    int32_t maxVolumeValue = INT32_C(0);
    /*@
    loop invariant maxVolumeValue >= 0;
    loop invariant s < INT32_MAX ==> 1 <= l <= (s < 1 ? 1 : (integer)s + 1);
    loop invariant s < INT32_MAX ==> (\forall integer p, q; 1 <= p < l && 1 <= q <= s - p + 1 ==> (int32_t)(p * q * (s - p - q)) <= maxVolumeValue);
    loop invariant s < INT32_MAX ==> maxVolumeValue == 0 || (\exists integer p, q; 1 <= p < l && 1 <= q <= s - p + 1 && maxVolumeValue == (int32_t)(p * q * (s - p - q)));
    loop assigns l, maxVolumeValue;
    */
    for (int32_t l = INT32_C(1); (l <= s); l = java_add(l, INT32_C(1))) {
        /*@
        loop invariant maxVolumeValue >= 0;
        loop invariant s < INT32_MAX ==> 1 <= b <= (integer)((int32_t)((integer)(((int32_t)((integer)(s) - (l)))) + (1))) + 1;
        loop invariant s < INT32_MAX ==> (\forall integer p, q; 1 <= p <= l && 1 <= q <= s - p + 1 && (p < l || q < b) ==> (int32_t)(p * q * (s - p - q)) <= maxVolumeValue);
        loop invariant s < INT32_MAX ==> maxVolumeValue == 0 || (\exists integer p, q; 1 <= p <= l && 1 <= q <= s - p + 1 && (p < l || q < b) && maxVolumeValue == (int32_t)(p * q * (s - p - q)));
        loop assigns b, maxVolumeValue;
        */
        for (int32_t b = INT32_C(1); (b <= java_add(java_sub(s, l), INT32_C(1))); b = java_add(b, INT32_C(1))) {
            int32_t h = java_sub(java_sub(s, l), b);
            int32_t volume = java_mul(java_mul(l, b), h);
            if ((volume > maxVolumeValue)) {
                ;
            }
        }
    }
    return maxVolumeValue;
}
