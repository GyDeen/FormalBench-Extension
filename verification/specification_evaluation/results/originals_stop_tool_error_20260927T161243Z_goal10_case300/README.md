# Consistency and completeness results

**8 October 2026:** Previous C mutant WP results and all supplementary C replay results have been removed from active reporting. No replacement run has started. Java results, C original verification and frozen specifications are retained.

Run: `originals_stop_tool_error_20260927T161243Z_goal10_case300`. This report covers 50 originals per language and 977 retained Java/C mutant pairs. Budgets are 10 seconds per solver query and 300 seconds per process. Specifications are frozen per original and transferred to its retained mutants.

## Consistency

Consistency is assessed through verification of each original under its generated specification. The authoritative original program outcomes are:

| Language | Total | Proved | Specification failures | Safety failures | Unknown/timeout | Tool failures | Not run |
| --- | --- | --- | --- | --- | --- | --- | --- |
| Java | 50 | 13 | 2 | 1 | 34 | 0 | 0 |
| C | 50 | 6 | 0 | 0 | 44 | 0 | 0 |

Unknown/timeout is inconclusive. Safety failures are reported separately from specification failures.

### Original outcomes by category

| Category | Language | Total | Proved | Specification failures | Safety failures | Unknown/timeout | Tool failures | Not run |
| --- | --- | --- | --- | --- | --- | --- | --- | --- |
| sequential | Java | 10 | 9 | 0 | 1 | 0 | 0 | 0 |
| sequential | C | 10 | 2 | 0 | 0 | 8 | 0 | 0 |
| branch | Java | 10 | 4 | 2 | 0 | 4 | 0 | 0 |
| branch | C | 10 | 2 | 0 | 0 | 8 | 0 | 0 |
| single_path_loop | Java | 10 | 0 | 0 | 0 | 10 | 0 | 0 |
| single_path_loop | C | 10 | 0 | 0 | 0 | 10 | 0 | 0 |
| multi_path_loop | Java | 10 | 0 | 0 | 0 | 10 | 0 | 0 |
| multi_path_loop | C | 10 | 2 | 0 | 0 | 8 | 0 | 0 |
| nested | Java | 10 | 0 | 0 | 0 | 10 | 0 | 0 |
| nested | C | 10 | 0 | 0 | 0 | 10 | 0 | 0 |

### Time for fully verified originals

Times are saved verifier-process wall times. The successful Java and C cohorts contain different programs; these descriptive times do not establish a paired language speed difference.

| Language | n | Total s | Mean s | Median s | Sample SD s | Min s | Max s |
| --- | --- | --- | --- | --- | --- | --- | --- |
| Java | 13 | 51.675 | 3.975 | 3.907 | 0.196 | 3.779 | 4.414 |
| C | 6 | 9.549 | 1.591 | 0.858 | 1.402 | 0.557 | 3.968 |

## Completeness

Completeness is assessed by rejection of behaviour-changing mutants under their originals’ frozen specifications. Java rows use classified OpenJML diagnostics. C mutant results are withdrawn and await a new Frama-C-only run.

| Verifier | Total | Proved | Specification failures | Safety failures | Unknown/timeout | Tool failures | Not run |
| --- | --- | --- | --- | --- | --- | --- | --- |
| Java / OpenJML | 977 | 0 | 146 | 34 | 797 | 0 | 0 |
| C / WP | 977 | 0 | 0 | 0 | 0 | 0 | 977 |

### Mutants whose originals were fully verified

This subset gives more direct evidence that a specification accepts its original while rejecting a mutant. Rates use all eligible mutants in each subset; safety failures are separate from specification/postcondition violations. C completeness is unavailable until a replacement experiment is run.

| Language | Verified originals | Eligible mutants | Specification/postcondition violations | Safety failures | Inconclusive | Violation rate |
| --- | --- | --- | --- | --- | --- | --- |
| Java | 13 | 192 | 121 | 17 | 54 | 63.02% |
| C | 6 | 80 | N/A | N/A | Not run | N/A |

### Mutant outcomes by category

| Category | Language | Total | Proved | Specification failures | Safety failures | Unknown/timeout | Tool failures | Not run |
| --- | --- | --- | --- | --- | --- | --- | --- | --- |
| sequential | Java | 143 | 0 | 40 | 31 | 72 | 0 | 0 |
| sequential | C | 143 | 0 | 0 | 0 | 0 | 0 | 143 |
| branch | Java | 193 | 0 | 105 | 2 | 86 | 0 | 0 |
| branch | C | 193 | 0 | 0 | 0 | 0 | 0 | 193 |
| single_path_loop | Java | 183 | 0 | 0 | 0 | 183 | 0 | 0 |
| single_path_loop | C | 183 | 0 | 0 | 0 | 0 | 0 | 183 |
| multi_path_loop | Java | 239 | 0 | 1 | 1 | 237 | 0 | 0 |
| multi_path_loop | C | 239 | 0 | 0 | 0 | 0 | 0 | 239 |
| nested | Java | 219 | 0 | 0 | 0 | 219 | 0 | 0 |
| nested | C | 219 | 0 | 0 | 0 | 0 | 0 | 219 |

Authoritative records are `cases/<program>/<original or mutant_ID>/<language>/record.json`. [summary.json](summary.json) retains overall outcomes and original-outcome strata; [results_summary.json](results_summary.json) and [statistics.json](statistics.json) contain the derived program-level report. Generated source files, binaries, and solver traces remain excluded from Git.

C mutant case directories are absent after withdrawal. The saved-result generator now treats absent records as `not run` and leaves C completeness unavailable. Regenerate reporting without running verification using `python3 -m verification.specification_evaluation.reporting.experiment --output verification/specification_evaluation/results/originals_stop_tool_error_20260927T161243Z_goal10_case300`.
