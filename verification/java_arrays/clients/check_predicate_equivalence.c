/* Regression-only snapshots of the pre-refactoring definitions.
 * No assumed lemmas: each equivalence is a function postcondition to prove.
 * Matrix snapshots use the old scalar definitions as well.
 */
#include "../contracts/jintarray2.acsl.h"
#include "../contracts/jdoublearray2.acsl.h"
#include "../contracts/jboolarray.acsl.h"

/*@
  predicate jintarray_legacy_valid{L}(JIntArray a) =
    a == \null ||
    (
      \valid_read(a) &&
      \initialized(&a->length) &&
      \initialized(&a->data) &&
      a->length >= 0 &&
      (
        (a->length == 0 && a->data == \null) ||
        (a->length > 0 &&
         \valid(a->data + (0 .. a->length - 1)) &&
         \initialized(a->data + (0 .. a->length - 1)) &&
         \separated(a, a->data + (0 .. a->length - 1)))
      )
    );
*/

/*@
  predicate jboolarray_legacy_valid{L}(JBoolArray a) =
    a == \null ||
    (
      \valid_read(a) &&
      \initialized(&a->length) &&
      \initialized(&a->data) &&
      a->length >= 0 &&
      (
        (a->length == 0 && a->data == \null) ||
        (a->length > 0 &&
         \valid(a->data + (0 .. a->length - 1)) &&
         \initialized(a->data + (0 .. a->length - 1)) &&
         \separated(a, a->data + (0 .. a->length - 1)))
      )
    );
*/

/*@
  predicate jdoublearray_legacy_valid{L}(JDoubleArray a) =
    a == \null ||
    (
      \valid_read(a) &&
      \initialized(&a->length) &&
      \initialized(&a->data) &&
      a->length >= 0 &&
      (
        (a->length == 0 && a->data == \null) ||
        (
          a->length > 0 &&
          \valid(a->data + (0 .. a->length - 1)) &&
          \initialized(a->data + (0 .. a->length - 1)) &&
          \separated(a, a->data + (0 .. a->length - 1))
        )
      )
    );
*/

/*@
  predicate jintarray2_legacy_valid{L}(JIntArray2 a) =
    a == \null ||
    (
      \valid_read(a) &&
      \initialized(&a->length) &&
      \initialized(&a->data) &&
      a->length >= 0 &&
      (
        (a->length == 0 && a->data == \null) ||
        (
          a->length > 0 &&
          \valid(a->data + (0 .. a->length - 1)) &&
          \initialized(a->data + (0 .. a->length - 1)) &&
          \separated(a, a->data + (0 .. a->length - 1))
        )
      ) &&
      (
        \forall integer i;
          0 <= i < a->length ==>
            jintarray_legacy_valid{L}(a->data[i]) &&
            jintarray2_row_compatible{L}(a, a->data[i])
      )
    );
*/

/*@
  predicate jdoublearray2_legacy_valid{L}(JDoubleArray2 a) =
    a == \null ||
    (
      \valid_read(a) &&
      \initialized(&a->length) &&
      \initialized(&a->data) &&
      a->length >= 0 &&
      (
        (a->length == 0 && a->data == \null) ||
        (
          a->length > 0 &&
          \valid(a->data + (0 .. a->length - 1)) &&
          \initialized(a->data + (0 .. a->length - 1)) &&
          \separated(a, a->data + (0 .. a->length - 1))
        )
      ) &&
      (
        \forall integer i;
          0 <= i < a->length ==>
            jdoublearray_legacy_valid{L}(a->data[i]) &&
            jdoublearray2_row_compatible{L}(a, a->data[i])
      )
    );
*/

/*@ assigns \nothing;
    ensures jintarray_valid(a) <==> jintarray_legacy_valid(a); */
void check_jintarray_equivalence(JIntArray a) { (void)a; }

/*@ assigns \nothing;
    ensures jboolarray_valid(a) <==> jboolarray_legacy_valid(a); */
void check_jboolarray_equivalence(JBoolArray a) { (void)a; }

/*@ assigns \nothing;
    ensures jdoublearray_valid(a) <==> jdoublearray_legacy_valid(a); */
void check_jdoublearray_equivalence(JDoubleArray a) { (void)a; }

/*@ assigns \nothing;
    ensures jintarray2_valid(a) <==> jintarray2_legacy_valid(a); */
void check_jintarray2_equivalence(JIntArray2 a) { (void)a; }

/*@ assigns \nothing;
    ensures jdoublearray2_valid(a) <==> jdoublearray2_legacy_valid(a); */
void check_jdoublearray2_equivalence(JDoubleArray2 a) { (void)a; }
