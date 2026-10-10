#ifndef VERIFICATION_JARITHMETIC_INT32_ACSL_H
#define VERIFICATION_JARITHMETIC_INT32_ACSL_H

#include <stdint.h>
#include <limits.h>

/* Fixed support contracts for the existing translated static helpers.
 * ACSL integer arithmetic is mathematical; the explicit int32_t cast
 * specifies the signed 32-bit wrap of the final result.
 */

/*@
  assigns \nothing;
  ensures \result == (int32_t)((integer)left + right);
*/
static int32_t java_add(int32_t left, int32_t right);

/*@
  assigns \nothing;
  ensures \result == (int32_t)((integer)left - right);
*/
static int32_t java_sub(int32_t left, int32_t right);

/*@
  assigns \nothing;
  ensures \result == (int32_t)((integer)left * right);
*/
static int32_t java_mul(int32_t left, int32_t right);

/* The existing helper does not implement a division-by-zero exception. */
/*@
  requires divisor != 0;
  assigns \nothing;
  ensures \result == (int32_t)((integer)dividend / divisor);
*/
static int32_t java_div(int32_t dividend, int32_t divisor);

/* Java abs(INT32_MIN) is INT32_MIN, not a nonnegative mathematical value. */
/*@
  assigns \nothing;
  ensures \result == (value < 0 ? (int32_t)(-(integer)value) : value);
*/
static int32_t java_abs(int32_t value);

/*@
  assigns \nothing;
  ensures \result == (left < right ? left : right);
*/
static int32_t java_min(int32_t left, int32_t right);

/*@
  assigns \nothing;
  ensures \result == (left > right ? left : right);
*/
static int32_t java_max(int32_t left, int32_t right);

/* The low five bits of distance select a shift in [0,31]. */
/*@
  assigns \nothing;
  ensures \result == (int32_t)((uint32_t)value << ((uint32_t)distance & 31));
*/
static int32_t java_shl(int32_t value, int32_t distance);

#endif
