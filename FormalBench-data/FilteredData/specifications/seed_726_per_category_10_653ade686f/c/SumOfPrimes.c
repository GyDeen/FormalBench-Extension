#include "java_arrays.h"
#include <inttypes.h>
#include <stdint.h>
#include <stdio.h>

/*@
assigns \nothing;
ensures \result == (int32_t)((integer)left + right);
*/
static int32_t java_add(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left + (uint32_t)right);
}

/*@
assigns \nothing;
ensures \result == (int32_t)((integer)left * right);
*/
static int32_t java_mul(int32_t left, int32_t right) {
    return (int32_t)((uint32_t)left * (uint32_t)right);
}

/*@
predicate fb_prime(integer k) = k >= 2 && (\forall integer d; 2 <= d && d * d <= k ==> k % d != 0);
logic integer fb_prime_sum(integer n) = n < 2 ? 0 : fb_prime_sum(n - 1) + (fb_prime(n) ? n : 0);
predicate fb_unmarked(integer k, integer limit) = \forall integer d; 2 <= d < limit && d * d <= k ==> k % d != 0;
*/
/*@
requires -1 <= n < 46349;
ensures \result == fb_prime_sum(n);
behavior silent:
  assumes n < 2;
  assigns \nothing;
behavior printing:
  assumes n >= 2;
  assigns *__fc_stdout;
complete behaviors;
disjoint behaviors;
*/
int32_t sumOfPrimes(int32_t n) {
    int32_t length = java_add(n, 1);
    JBoolArray isPrime = jbool_array_new(length);
    int32_t arrayLength = jbool_array_length(isPrime);
    /*@
    loop invariant 0 <= i <= arrayLength;
    loop invariant jboolarray_valid_nonnull(isPrime) && isPrime->length == arrayLength && arrayLength == n + 1;
    loop invariant \forall integer k; 0 <= k < i ==> isPrime->data[k];
    loop assigns i, isPrime->data[0 .. arrayLength - 1];
    loop variant arrayLength - i;
    */
    for (int32_t i = 0; i < arrayLength; i = java_add(i, 1)) {
        jbool_array_set(isPrime, i, true);
    }

    int32_t sum = 0;
    /*@
    loop invariant 2 <= i <= \max(2, n + 1);
    loop invariant jboolarray_valid_nonnull(isPrime) && isPrime->length == n + 1;
    loop invariant sum == fb_prime_sum(i - 1);
    loop invariant \forall integer k; 2 <= k <= n ==> (isPrime->data[k] <==> fb_unmarked(k, i));
    loop assigns i, sum, isPrime->data[0 .. n], *__fc_stdout;
    loop variant \max(0, n - i + 1);
    */
    for (int32_t i = 2; i <= n; i = java_add(i, 1)) {
        if (jbool_array_get(isPrime, i)) {
            /*@ assert fb_prime(i); */
            printf("%" PRId32 "\n", i);
            sum = java_add(sum, i);
            /*@
            loop invariant (integer)i * i <= j <= \max((integer)i * i, (integer)n + i);
            loop invariant j % i == 0;
            loop invariant jboolarray_valid_nonnull(isPrime) && isPrime->length == n + 1;
            loop invariant \forall integer k; 2 <= k <= n ==> (isPrime->data[k] <==> (fb_unmarked(k, i) && !((integer)i * i <= k < j && k % i == 0)));
            loop assigns j, isPrime->data[0 .. n];
            loop variant \max(0, n - j + 1);
            */
            for (int32_t j = java_mul(i, i); j <= n; j = java_add(j, i)) {
                jbool_array_set(isPrime, j, false);
            }
        }
    }
    return sum;
}
