# Four original C translation repairs

Only MoveFirst, MultiplyElements, PairWise, and NextPowerOf2 were retranslated.
The translations follow
`FormalBench-data/FilteredData/Prompt/java-to-c/SKILL.md` and use the same emitter
as the retained mutants, starting from unchanged original Java sources.

MoveFirst uses the required accessor-based lowering of System.arraycopy, with
the original null/empty return branch. MultiplyElements and PairWise retain
Java's direct array-length conditions, allocations, and loop bounds. NextPowerOf2
retains its while loop and shifts through the general masked-distance helper.
Only JArray accessors implement array operations; the algorithms do not access
handle internals. Unsigned arithmetic helpers preserve Java integer wraparound.
No additional guards, early returns, or changes to Java sources were introduced.

`previous_originals/` and `previous_specs/` contain the four prior sources and
annotated files. `candidates/` contains only the four new raw translations.
`manifest.json` records all 50 before/after original hashes, confirming that the
other 46 original C files were preserved exactly, plus Java and skill hashes.

The annotated counterparts in the FilteredData specification directory were
updated without changing their executable tokens. Behavioral postconditions and
input domains were retained. Invariants use stable array metadata in place of
removed caches. The shift helper contract covers the general Java shift operation.
Annotations were frozen before any refreshed mutant checks.

`agreement_summary.json` records only the 15 existing screening tests.
All 16 calls match Java, with no mismatches or timeouts. No boundary cases
were added. The selected existing inputs and both execution results are
retained here; the project's screening input file was preserved.

`original_preflight.json` and `task_generation/` record Frama-C parsing and WP/RTE
task-generation checks. Full original proof diagnostics use 10 seconds per goal,
300 seconds per case, four proof jobs per case, and two C workers in the new run:

`verification/specification_evaluation/results/translation_repairs_20261001_goal10_case300/`

All four original proof outcomes remain unknown/timeout; reaching verification
without syntax/tool errors does not establish full proof. `mutant_preflight.json`
checks transfer and task generation for all 50 retained mutants of these four
programs. It does not rerun their solver proofs or establish detection results.
`verification_summary.json` contains the final counts. Historical runs, frozen
specifications, mutant bodies, and their recorded outcomes are preserved.

All four original task-generation checks passed. Of the 50 mutant checks, 47
passed both annotation transfer and task generation. NextPowerOf2 mutants 8,
9, and 10 still fail transfer because their Java mutations replace the left
shift with a right/logical-right shift or delete it; their C translations no
longer define the `java_shl` helper targeted by its frozen contract. These
three are mutation-specific helper-attachment limitations, not original-source
translation mismatches. No annotations were revised after observing them.

`canonical_syntax_checks.json` records a separate direct parse of the four
installed annotated sources. Their entries in `syntax-checks-c.json` and
`syntax-checks-both.json` were refreshed with current hashes and logs, retaining
the old affected index records in `previous_syntax_records.json`.
