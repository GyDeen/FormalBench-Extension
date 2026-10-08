# Final verifier-based completeness

Only mutants of fully verified originals are included. Each language uses its own verified cohort. Safety and precondition failures do not contribute to specification detection.

| Language | Verified originals | Eligible mutants | Specification detections | Pooled rate | Mean across programs |
|---|---:|---:|---:|---:|---:|
| Java | 13 | 192 | 121 | 63.02% | 62.14% |
| C | 6 | 80 | 7 | 8.75% | 6.86% |

The program mean averages each original program's detected/eligible proportion with equal weight. The pooled rate divides total detections by total eligible mutants.

## Outcome breakdown

| Language | Eligible mutants | Specification detections | Excluded precondition/RTE cases | Validated safety detections | Unresolved cases |
|---|---:|---:|---:|---:|---:|
| Java | 192 | 121 | 17 | Not separately validated | 54 |
| C | 80 | 7 | Not separately classified | 0 | 73 |

Java's 121 specification, 17 precondition/RTE and 54 unknown/timeout outcomes partition its 192 mutants. C's 7 validated specification detections and 73 unresolved cases partition its 80 mutants. The safety column is a separate flag and does not contribute to detection. All selected mutants have records; unresolved cases are not counted as satisfied contracts. No confidence interval or significance claim is attached to this consolidation.

## Java: per-program outcomes

| Program | Eligible mutants | Specification detections | Excluded precondition/RTE | Unknown/timeout | Detection rate |
|---|---:|---:|---:|---:|---:|
| CountIntgralPoints | 20 | 6 | 3 | 11 | 30.00% |
| DiameterCircle | 4 | 2 | 2 | 0 | 50.00% |
| DogAge | 27 | 27 | 0 | 0 | 100.00% |
| FindPoints | 22 | 21 | 0 | 1 | 95.45% |
| FindRectNum | 8 | 2 | 1 | 5 | 25.00% |
| HexagonalNum | 12 | 5 | 1 | 6 | 41.67% |
| MaxOfTwo | 2 | 2 | 0 | 0 | 100.00% |
| NoOfCubes | 33 | 2 | 3 | 28 | 6.06% |
| OddBitSetNumber | 17 | 17 | 0 | 0 | 100.00% |
| SquarePerimeter | 4 | 2 | 2 | 0 | 50.00% |
| SumNums | 13 | 11 | 2 | 0 | 84.62% |
| TestThreeEqual | 22 | 22 | 0 | 0 | 100.00% |
| VolumeCube | 8 | 2 | 3 | 3 | 25.00% |
| **Total** | **192** | **121** | **17** | **54** | **63.02%** |

## C: per-program outcomes

| Program | Eligible mutants | Raw WP candidate flags | Validated specification detections | Validated safety detections | Unresolved | Detection rate |
|---|---:|---:|---:|---:|---:|---:|
| CountList | 3 | 0 | 0 | 0 | 3 | 0.00% |
| MaxOfTwo | 2 | 0 | 0 | 0 | 2 | 0.00% |
| MaxSubArraySum | 23 | 0 | 0 | 0 | 23 | 0.00% |
| OddBitSetNumber | 17 | 11 | 7 | 0 | 10 | 41.18% |
| SumNums | 13 | 0 | 0 | 0 | 13 | 0.00% |
| TestThreeEqual | 22 | 0 | 0 | 0 | 22 | 0.00% |
| **Total** | **80** | **11** | **7** | **0** | **73** | **8.75%** |

Raw WP candidate flags are diagnostic evidence, not additional detections. Only validated specification detections contribute to the numerator.

## Detection definitions

Java uses the saved OpenJML ESC specification-violation category: verifier diagnostics for specification obligations, rather than independently validated concrete executions. The excluded combined precondition/RTE category contains 17 cases; 54 cases are unknown/timeout. The recorded goal categories and messages remain in each record.

C uses the final Frama-C/WP model-validation result. Eleven raw WP candidate flags were reported; seven mutants have independently replayed frozen-specification violations from fully reconstructible verifier models on admitted inputs. Only those seven count. No EvoSuite or bounded generated inputs participate in this result. The remaining 73 have no validated specification detection; unresolved or unsupported models are not treated as specification satisfaction. No validated safety detections were recorded.

These operational definitions differ: Java records ESC diagnostics, whereas C validates verifier-generated model inputs through executable checks. This result must remain separate from the symmetric test-based runtime evaluation.

## Recorded evaluation

Java: OpenJML 21.0.27 ESC with bundled Z3 4.10.2, nullable-by-default, 10-second goal budget and 300-second case budget. C: Frama-C 33.0 (Arsenic), WP/Z3 4.8.12, Typed+ref memory model, x86_64, 10-second goal budget, 300-second case budget, wp-par 2, wp-memlimit 1000. C replay uses GCC 13.3.0 and a 2-second execution timeout. Exact recorded configurations and commands are retained.

The C executable checks cover supported return-value/content clauses, observable input-array frames and selected identity, separation and errno clauses. Loop invariants, variants, statement assertions and complete write/allocation sets are unchecked. Coverage and replay attempts remain explicit in each C record.

## Evidence layout

This directory contains one consolidated final result. Its three subfolders hold supporting evidence, rather than separate runs:

| Folder | Contents |
|---|---|
| [cases/](cases/) | 192 Java and 80 C mutant records, plus 19 original proof records, grouped by program and language |
| [configuration/](configuration/) | Three configuration files recording original/Java ESC verification, C WP verification and C model replay |
| [frozen_specs/](frozen_specs/) | The unchanged specifications for 13 verified Java originals and 6 verified C originals |

The program and mutant folders under cases/ retain the requested record and WP reports. They are the evidence behind the tables above. Pilots, intermediate run folders and duplicate results are archived locally under output/intermediate_completeness_results_20261008/ and are excluded from the active result tree and staged additions.

[Combined summary](summary.json) | [Mutant rows](mutants.csv)

Each C case contains the final validated record.json, the raw wp-record.json and wp-report.json. Original proof records and unchanged frozen specifications are retained only for the eligible cohorts. Historical command paths are preserved as executed. Intermediate runs were moved to the local output archive; this consolidation did not rerun verification or execution.
