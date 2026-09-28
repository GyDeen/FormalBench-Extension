#ifndef FORMALBENCH_VERIFICATION_JAVA_ARRAY_HELPERS_ACSL_H
#define FORMALBENCH_VERIFICATION_JAVA_ARRAY_HELPERS_ACSL_H

#include "../generated/types.h"

/* These declarations intentionally retain the implementation's static linkage.
 * Error behaviors leave the write footprint unspecified because array_error
 * writes diagnostics to stderr. Normal behaviors have no such side effects. */

/* Trusted non-returning error summary. */
/*@
  terminates \true;
  ensures \false;
  exits \exit_status == code;
*/
static _Noreturn void array_error(enum JArrayExitCode code);

/*@
  behavior normal:
    assumes length >= 0;
    assigns \nothing;
    exits \false;

  behavior negative_length:
    assumes length < 0;
    ensures \false;
    exits \exit_status == JARRAY_NEGATIVE_ARRAY_SIZE;

  complete behaviors;
  disjoint behaviors;
*/
static void check_length(int32_t length);

/*@
  behavior normal:
    assumes array != \null;
    assigns \nothing;
    exits \false;

  behavior null_reference:
    assumes array == \null;
    ensures \false;
    exits \exit_status == JARRAY_NULL_REFERENCE_ERROR;

  complete behaviors;
  disjoint behaviors;
*/
static void check_reference(const void *array);

/*@
  behavior normal:
    assumes 0 <= index < length;
    assigns \nothing;
    exits \false;

  behavior out_of_bounds:
    assumes index < 0 || index >= length;
    ensures \false;
    exits \exit_status == JARRAY_INDEX_OUT_OF_BOUNDS;

  complete behaviors;
  disjoint behaviors;
*/
static void check_index(int32_t length, int32_t index);

/* Trusted allocation boundary: representable positive requests succeed.
 * Actual malloc exhaustion is excluded by this assumption. Overflow is a
 * separate error case; size == 0 remains outside the supported inputs. */
/*@
  requires size > 0;

  behavior empty:
    assumes count == 0;
    assigns \nothing;
    allocates \nothing;
    frees \nothing;
    ensures \result == \null;
    exits \false;

  behavior normal:
    assumes count > 0;
    assumes count <= SIZE_MAX / size;
    assigns \nothing;
    allocates \result;
    frees \nothing;
    ensures \result != \null;
    ensures \fresh(\result, count * size);
    exits \false;

  behavior size_overflow:
    assumes count > SIZE_MAX / size;
    ensures \false;
    exits \exit_status == JARRAY_OUT_OF_MEMORY;

  complete behaviors;
  disjoint behaviors;
*/
static void *allocate(size_t count, size_t size);

#endif
