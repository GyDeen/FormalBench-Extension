#include "java_arrays.h"
#include <math.h>
#include <stdint.h>

/*@
assigns \nothing;
ensures \result == (int32_t)((integer)left + right);
*/
static int32_t java_add(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left + (uint32_t)right);
}

/*@
logic integer fb_floor_root(integer x, integer lo, integer hi) =
  lo > hi ? hi :
  ((lo + hi) / 2) * ((lo + hi) / 2) <= x ? fb_floor_root(x, (lo + hi) / 2 + 1, hi) : fb_floor_root(x, lo, (lo + hi) / 2 - 1);
logic integer fb_squares_through(integer x) = x < 0 ? 0 : fb_floor_root(x, 0, 46340) + 1;
*/
/*@
terminates m < INT32_MAX;
assigns errno;
ensures m < INT32_MAX;
ensures \result == (n > m ? 0 : fb_squares_through(m) - fb_squares_through((integer)n - 1));
ensures 0 <= \result <= 46341;
ensures n < 0 && n <= m ==> errno == EDOM;
ensures n >= 0 || n > m ==> errno == \old(errno);
*/
int32_t countOddSquares(int32_t n, int32_t m) {
    int32_t count = 0;
    /*@
    loop invariant m < INT32_MAX ==> n <= i <= (n > m ? n : (integer)m + 1);
    loop invariant m < INT32_MAX ==> count == fb_squares_through((integer)i - 1) - fb_squares_through((integer)n - 1);
    loop assigns i, count, errno;
    */
    for (int32_t i = n; i <= m; i = java_add(i, 1)) {
        double square = sqrt((double)i);
        if (fmod(square, 1.0) == 0.0 && i % 1 == 0) {
            count = java_add(count, 1);
        }
    }
    return count;
}
