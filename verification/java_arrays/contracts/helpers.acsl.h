#ifndef FORMALBENCH_VERIFICATION_JAVA_ARRAY_HELPERS_ACSL_H
#define FORMALBENCH_VERIFICATION_JAVA_ARRAY_HELPERS_ACSL_H

#include "../generated/types.h"

/* These declarations intentionally retain the implementation's static linkage. */

/*@ assigns \nothing; */
static _Noreturn void array_error(enum JArrayExitCode code);

/*@
  requires length >= 0;
  assigns \nothing;
*/
static void check_length(int32_t length);

/*@
  requires array != \null;
  assigns \nothing;
*/
static void check_reference(const void *array);

/*@
  requires 0 <= index < length;
  assigns \nothing;
*/
static void check_index(int32_t length, int32_t index);

/*@
  requires size > 0;
  requires count == 0 || count <= SIZE_MAX / size;
  assigns \nothing;
  allocates \result;
  ensures count == 0 ==> \result == \null;
  ensures count > 0 ==> \fresh(\result, count * size);
*/
static void *allocate(size_t count, size_t size);

#endif
