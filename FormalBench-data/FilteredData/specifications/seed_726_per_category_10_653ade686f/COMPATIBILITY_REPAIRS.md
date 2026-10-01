# Original Java compatibility revisions, 2026-09-27

Nine supplied Java specifications were revised after original-only diagnostics.
Executable source tokens, method signatures, and the original input domains
were preserved. Historical frozen specifications and results were not replaced.

| Programs | Revision |
| --- | --- |
| MaxProduct, MinJumps | Explicit bigint casts in mixed numeric conditional arms avoid an OpenJML internal type assertion. |
| CountList | A recursive prefix count replaces unsupported `\num_of`. |
| CountingSort, RadixSort | Recursive prefix counts replace `\num_of`; enhanced-for counters use the equivalent `\count + 0` expression. |
| OddLengthSum | A recursive prefix sum replaces `\sum`, preserving Java overflow arithmetic inside each term. |
| SumRangeList | A recursive half-open range sum replaces `\sum`, preserving empty ranges and using bigint bounds. |
| SumOfSubarrayProd | Recursive products, row sums, and sums of rows replace nested `\sum` and `\product`. |
| MinCost | The unsupported column frame `tc[*][0]` becomes `tc[*][*]`; an invariant states that all other columns remain zero. The external method frame remains unchanged. |

The aggregate definitions retain the empty sum/count value zero and empty
product value one. Each recursive step adds or multiplies exactly the final
term of its finite range. The nested subarray aggregate partitions the same
index pairs by their starting index. Existing final integer wrapping remains
in place. Sorting invariants bind the current threshold outside `\old`, so
only the input array values are evaluated in the old state.

The MinCost loop frame is broader than before. Its additional invariant
preserves the relevant values at loop boundaries; it does not assert the same
write-event restriction as the former column frame.

## Recheck

Each changed original was independently frozen, checked with `--check`, and
run through ESC with OpenJML 21.0.27, Z3 4.10.2, the current compatibility
plugin, 10 seconds per goal and 300 seconds per program.

- All nine syntax/type checks exited successfully.
- All nine ESC attempts completed without the previous tool crashes or
  unsupported-feature diagnostics.
- All nine proof outcomes remain unknown/timeout; none is a fully proved
  program. In particular, an unknown-validity/no-model message is not a
  confirmed specification rejection.

Local diagnostic evidence is under
`verification/specification_evaluation/results/original_repairs_20260927_goal10_case300/`.
These diagnostic artifacts remain ignored by Git. The specification manifest
records revised hashes, individual record paths, and unresolved proof status.
The full batch remains stopped.

## Four original C translation revisions, 2026-10-01

MoveFirst, MultiplyElements, PairWise, and NextPowerOf2 were retranslated from
unchanged Java originals using the Java-to-C skill and the shared mutant emitter.
Only those four raw C originals and their annotated counterparts were updated;
the other 46 raw C originals were verified byte-for-byte unchanged.

Direct JArray length expressions replace the original-only length/limit caches.
The System.arraycopy expansion and general masked-distance shift helper use the
same structure as the shared translator. The refreshed ACSL retains behavioral
postconditions and input domains, with invariants adapted to the actual source.
The new annotations were frozen before checking any corresponding mutants.

All 15 existing differential tests passed: all 16 calls match Java with no
observed differences or timeouts. No boundary cases were added. All four
originals pass direct ACSL parsing and WP/RTE task generation. Original proof
checks at 10 seconds per goal / 300 seconds per case completed without tool
failures, but all four remain unknown/timeout due to unresolved obligations.

47 of the 50 retained C mutants pass refreshed transfer and task generation.
NextPowerOf2/8, /9, and /10 no longer define the left-shift helper after actual
Java mutations replace or delete the shift; these three still fail helper
contract attachment. Their solver proofs were not rerun in this diagnostic.

Evidence: `output/c_translation_repairs_20261001/verification_summary.json`.
New frozen inputs and original records:
`verification/specification_evaluation/results/translation_repairs_20261001_goal10_case300/`.
Historical results and frozen specifications retain their old source provenance.
