# Final paired specification verification experiment

Run: `originals_stop_tool_error_20260927T161243Z_goal10_case300`. This report covers 50 selected originals in each language and 977 retained Java/C mutant pairs (2,054 case records). Every case has a completed recorded outcome. Completion does not imply that all programs were proved.

## Result summary

| Population | Language | Total | Proved | Specification violation | Precondition/RTE failure | Unknown/timeout | Syntax/tool failure |
| --- | --- | --- | --- | --- | --- | --- | --- |
| Originals | Java | 50 | 13 | 2 | 1 | 34 | 0 |
| Originals | C | 50 | 6 | 0 | 0 | 44 | 0 |
| Mutants | Java | 977 | 0 | 146 | 34 | 797 | 0 |
| Mutants | C | 977 | 0 | 0 | 0 | 922 | 55 |

Java proved 13/50 originals (26.00%); C proved 6/50 (12.00%). No mutant was fully proved in either language. Java reports 146 specification violations (146/977 = 14.94%), including 121 among the 192 mutants whose Java original proved (63.02%). C reports no confirmed specification violations, including 0/80 mutants whose C original proved. Unknown and tool failures remain in the eligible denominator; zero confirmed C rejections does not establish successful verification of those mutants.

| Paired mutant outcome | Count |
| --- | --- |
| precondition/RTE failure | 34 |
| tool failure | 55 |
| unknown/timeout | 888 |

There are 0 decisive pairs; agreement on decisive pairs is N/A. The 55 C sort reruns replaced old annotation/tool failures with `unknown/timeout`, reducing C tool failures from 110 to 55. Original Java results were preserved. See [summary.json](summary.json), [run.json](run.json), and [sort_c_integration.json](sort_c_integration.json).

## Configuration and interpretation

The sample uses seed 726 with ten originals in each of five dataset categories. Only retained, previously screened mutant pairs are evaluated. Specifications are frozen independently per language and transferred to unchanged executable sources.

The Java verifier reports `openjml 21.0.27` and uses the `z3-4.3.X` driver with bundled solver `Z3 version 4.10.2 - 64 bit`. The C verifier reports `33.0 (Arsenic)` and uses Alt-Ergo:2.4.3,Z3:4.8.12, `Typed+ref`, and `x86_64`. Budgets are 10 seconds per solver goal and 300 seconds per process, with 1000 MB WP memory and 4 parallel WP jobs per C worker. Final scheduling used two C workers and one Java worker; the integrated sort rerun used two C workers. Timings reflect recorded invocations under these schedules, not an isolated speed benchmark.

`proved` means all applicable reported checks completed successfully. A specification violation is distinct from a precondition/RTE failure. Syntax/transfer/tool failures are evaluation failures, not detected behavioral faults. `unknown/timeout` establishes neither acceptance nor rejection. Primary specification rejection rates require a proved original; other original-outcome strata remain available in [summary.json](summary.json).

## Time for successfully verified cases

The success cohort is strictly `record.json: outcome == proved`. Time is the recorded verifier-process wall time (`elapsed_seconds`), including startup and Java constructor checking. It excludes annotation transfer and preparation. Only originals qualify: Java n=13, C n=6; successful-mutant timing statistics are N/A (n=0).

| Language | n | Total s | Mean s | Median s | Sample SD s | Q1 s | Q3 s | P95 s | Min s | Max s |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| Java | 13 | 51.6750 | 3.9750 | 3.9070 | 0.1961 | 3.8140 | 4.1060 | 4.3162 | 3.7790 | 4.4140 |
| C | 6 | 9.5490 | 1.5915 | 0.8585 | 1.4017 | 0.6705 | 2.2390 | 3.6378 | 0.5570 | 3.9680 |

Median uses the average of the middle two values for even n. SD is the sample standard deviation (n-1); Q1, Q3 and P95 use linear interpolation at (n-1)p. N/A denotes an empty sample or unavailable measurement, never a measured zero.

Java and C have different successful-program cohorts. These descriptive times do not measure a paired language speed difference.

## Goal counts for the same successful cohort

C counts are classified WP JSON goal entries, including specification, termination, safety and callee-precondition obligations. Java warning diagnostics are **not** a goal count: an empty Java `goals` array means no recorded warnings. Java generated assertions, method VCs and solver queries are separate units and must not be equated with C WP entries.

Java counts below come from 13 complete captures in `java_workload_20260929T133038Z_workers2`, matched to the successful final-run cases by raw-source, frozen-specification and annotated-source hashes, settings and verifier identity. Final-run verdicts and timing measurements remain authoritative; instrumented replay timing and outcomes are not substituted. Missing capture programs: none. Generated method VCs and total assertions include constructors; the program-assertion row removes constructors.

| Metric per successful case | n | Total | Mean | Median | Sample SD | Q1 | Q3 | P95 | Min | Max |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| Java: all generated assertions | 13 | 140 | 10.769 | 6 | 17.196 | 6.000 | 6.000 | 30.800 | 6 | 68 |
| Java: program assertions, excluding constructors | 13 | 101 | 7.769 | 3 | 17.196 | 3.000 | 3.000 | 27.800 | 3 | 65 |
| Java: generated method VCs | 13 | 26 | 2 | 2 | 0.000 | 2.000 | 2.000 | 2.000 | 2 | 2 |
| Java: solver check-sat queries | 13 | 26 | 2 | 2 | 0.000 | 2.000 | 2.000 | 2.000 | 2 | 2 |
| C: WP goal entries | 6 | 90 | 15 | 8.500 | 14.283 | 4.750 | 25.000 | 34.500 | 3 | 36 |

### Successful original details

| Program | Category | Language | Wall s | C WP goals | Java assertions | Java method VCs |
| --- | --- | --- | --- | --- | --- | --- |
| CountIntgralPoints | sequential | java | 3.887 | N/A | 6 | 2 |
| CountList | multi_path_loop | c | 2.647 | 30 | N/A | N/A |
| DiameterCircle | sequential | java | 3.867 | N/A | 6 | 2 |
| DogAge | branch | java | 3.915 | N/A | 6 | 2 |
| FindPoints | branch | java | 4.106 | N/A | 68 | 2 |
| FindRectNum | sequential | java | 3.811 | N/A | 6 | 2 |
| HexagonalNum | sequential | java | 3.814 | N/A | 6 | 2 |
| MaxOfTwo | sequential | c | 0.557 | 3 | N/A | N/A |
| MaxOfTwo | sequential | java | 4.251 | N/A | 6 | 2 |
| MaxSubArraySum | multi_path_loop | c | 3.968 | 36 | N/A | N/A |
| NoOfCubes | sequential | java | 4.414 | N/A | 6 | 2 |
| OddBitSetNumber | sequential | c | 0.660 | 7 | N/A | N/A |
| OddBitSetNumber | sequential | java | 4.126 | N/A | 6 | 2 |
| SquarePerimeter | sequential | java | 3.779 | N/A | 6 | 2 |
| SumNums | branch | c | 1.015 | 10 | N/A | N/A |
| SumNums | branch | java | 3.907 | N/A | 6 | 2 |
| TestThreeEqual | branch | c | 0.702 | 4 | N/A | N/A |
| TestThreeEqual | branch | java | 4.004 | N/A | 6 | 2 |
| VolumeCube | sequential | java | 3.794 | N/A | 6 | 2 |

## Category results

Category labels come from the saved selection manifest, not from reclassifying translated code. Each category contains ten originals in each language; mutant totals vary after eligibility screening.

| Category | Originals per language | Retained mutant pairs |
| --- | --- | --- |
| sequential | 10 | 143 |
| branch | 10 | 193 |
| single_path_loop | 10 | 183 |
| multi_path_loop | 10 | 239 |
| nested | 10 | 219 |

### Original outcomes by category

| Category | Language | Total | Proved | Specification violation | Precondition/RTE | Unknown/timeout | Syntax/tool |
| --- | --- | --- | --- | --- | --- | --- | --- |
| sequential | java | 10 | 9 | 0 | 1 | 0 | 0 |
| sequential | c | 10 | 2 | 0 | 0 | 8 | 0 |
| branch | java | 10 | 4 | 2 | 0 | 4 | 0 |
| branch | c | 10 | 2 | 0 | 0 | 8 | 0 |
| single_path_loop | java | 10 | 0 | 0 | 0 | 10 | 0 |
| single_path_loop | c | 10 | 0 | 0 | 0 | 10 | 0 |
| multi_path_loop | java | 10 | 0 | 0 | 0 | 10 | 0 |
| multi_path_loop | c | 10 | 2 | 0 | 0 | 8 | 0 |
| nested | java | 10 | 0 | 0 | 0 | 10 | 0 |
| nested | c | 10 | 0 | 0 | 0 | 10 | 0 |

### Mutant outcomes by category

| Category | Language | Total | Proved | Specification violation | Precondition/RTE | Unknown/timeout | Syntax/tool |
| --- | --- | --- | --- | --- | --- | --- | --- |
| sequential | java | 143 | 0 | 40 | 31 | 72 | 0 |
| sequential | c | 143 | 0 | 0 | 0 | 143 | 0 |
| branch | java | 193 | 0 | 105 | 2 | 86 | 0 |
| branch | c | 193 | 0 | 0 | 0 | 181 | 12 |
| single_path_loop | java | 183 | 0 | 0 | 0 | 183 | 0 |
| single_path_loop | c | 183 | 0 | 0 | 0 | 144 | 39 |
| multi_path_loop | java | 239 | 0 | 1 | 1 | 237 | 0 |
| multi_path_loop | c | 239 | 0 | 0 | 0 | 238 | 1 |
| nested | java | 219 | 0 | 0 | 0 | 219 | 0 |
| nested | c | 219 | 0 | 0 | 0 | 216 | 3 |

### Successful-case time and goal statistics by category

These rows use the same fully proved cohort as above; no successfully verified mutants contribute. Java goal columns count generated assertions (including constructors); C goal columns count WP entries. These units differ.

Time statistics (seconds):

| Category | Language | n | Total | Mean | Median | Sample SD | Q1 | Q3 | P95 | Min | Max |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| sequential | java | 9 | 35.7430 | 3.9714 | 3.8670 | 0.2332 | 3.8110 | 4.1260 | 4.3488 | 3.7790 | 4.4140 |
| sequential | c | 2 | 1.2170 | 0.6085 | 0.6085 | 0.0728 | 0.5828 | 0.6342 | 0.6549 | 0.5570 | 0.6600 |
| branch | java | 4 | 15.9320 | 3.9830 | 3.9595 | 0.0930 | 3.9130 | 4.0295 | 4.0907 | 3.9070 | 4.1060 |
| branch | c | 2 | 1.7170 | 0.8585 | 0.8585 | 0.2213 | 0.7802 | 0.9367 | 0.9993 | 0.7020 | 1.0150 |
| single_path_loop | java | 0 | N/A | N/A | N/A | N/A | N/A | N/A | N/A | N/A | N/A |
| single_path_loop | c | 0 | N/A | N/A | N/A | N/A | N/A | N/A | N/A | N/A | N/A |
| multi_path_loop | java | 0 | N/A | N/A | N/A | N/A | N/A | N/A | N/A | N/A | N/A |
| multi_path_loop | c | 2 | 6.6150 | 3.3075 | 3.3075 | 0.9341 | 2.9772 | 3.6378 | 3.9019 | 2.6470 | 3.9680 |
| nested | java | 0 | N/A | N/A | N/A | N/A | N/A | N/A | N/A | N/A | N/A |
| nested | c | 0 | N/A | N/A | N/A | N/A | N/A | N/A | N/A | N/A | N/A |

Goal-count statistics (Java assertions / C WP entries):

| Category | Language | n | Total | Mean | Median | Sample SD | Q1 | Q3 | P95 | Min | Max |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| sequential | java | 9 | 54 | 6 | 6 | 0.000 | 6.000 | 6.000 | 6.000 | 6 | 6 |
| sequential | c | 2 | 10 | 5 | 5.000 | 2.828 | 4.000 | 6.000 | 6.800 | 3 | 7 |
| branch | java | 4 | 86 | 21.500 | 6.000 | 31.000 | 6.000 | 21.500 | 58.700 | 6 | 68 |
| branch | c | 2 | 14 | 7 | 7.000 | 4.243 | 5.500 | 8.500 | 9.700 | 4 | 10 |
| single_path_loop | java | 0 | N/A | N/A | N/A | N/A | N/A | N/A | N/A | N/A | N/A |
| single_path_loop | c | 0 | N/A | N/A | N/A | N/A | N/A | N/A | N/A | N/A | N/A |
| multi_path_loop | java | 0 | N/A | N/A | N/A | N/A | N/A | N/A | N/A | N/A | N/A |
| multi_path_loop | c | 2 | 66 | 33 | 33.000 | 4.243 | 31.500 | 34.500 | 35.700 | 30 | 36 |
| nested | java | 0 | N/A | N/A | N/A | N/A | N/A | N/A | N/A | N/A | N/A |
| nested | c | 0 | N/A | N/A | N/A | N/A | N/A | N/A | N/A | N/A | N/A |

### Primary specification rejection by category

The eligible count in this table contains only mutants whose original proved in that language. Rates use all such eligible mutants, including unknown and safety/tool outcomes.

| Category | Language | Eligible under proved original | Specification rejections | Rate |
| --- | --- | --- | --- | --- |
| sequential | java | 108 | 40 | 37.04% |
| sequential | c | 19 | 0 | 0.00% |
| branch | java | 84 | 81 | 96.43% |
| branch | c | 35 | 0 | 0.00% |
| single_path_loop | java | 0 | 0 | N/A |
| single_path_loop | c | 0 | 0 | N/A |
| multi_path_loop | java | 0 | 0 | N/A |
| multi_path_loop | c | 26 | 0 | 0.00% |
| nested | java | 0 | 0 | N/A |
| nested | c | 0 | 0 | N/A |

## Does JArray block C verification?

JArray is the trusted interface assumed by translated C callers. Its implementation is not one of the study programs. A direct JArray blocker here means at least one recorded WP **callee-precondition** goal for a declared JArray API is not proved. "JArray only" means all remaining recorded unproved goals in that case are JArray preconditions. This is a statement about reported obligations under the current budgets, not a counterexample to the JArray implementation or a prediction that extra time will solve them.

| C population | Cases | Call JArray | Goal coverage available | Call JArray but no goals | JArray blocker cases | JArray-only blocker cases | JArray precondition goals | Unresolved JArray goals |
| --- | --- | --- | --- | --- | --- | --- | --- | --- |
| original | 50 | 28 | 50 | 0 | 12 | 0 | 567 | 47 |
| mutant | 977 | 618 | 918 | 53 | 400 | 1 | 15501 | 2687 |

**Observed conclusion: JArray call preconditions are a proof blocker in 412 C cases (12 originals and 400 mutants).** They are the only remaining recorded blocker in 1 case. These case counts overlap other proof difficulties; they are not additional outcome categories. Goal-less cases are excluded from blocker attribution, including transfer/tool failures and process timeouts without classified goal coverage. All unresolved JArray goals here are unknown; none is classified as violated.

Among originals, 2/28 callers of JArray proved; 4/22 originals without JArray calls proved. This is an association in the selected program sample; program complexity, invariants and solver limits also vary.

There are 59 mutant cases without classified goal coverage (55 syntax/tool failures and 4 unknown/timeouts). Of these, 53 call JArray; their blocker status is unavailable. Requirement text below comes from trusted headers whose hashes match the run configuration and every C case record.

### JArray preconditions by function

| API | Total goals | Proved | Unknown | Violated | Blocked originals | Blocked mutants |
| --- | --- | --- | --- | --- | --- | --- |
| jarray2_get | 1584 | 1307 | 277 | 0 | 1 | 37 |
| jarray2_length | 34 | 34 | 0 | 0 | 0 | 0 |
| jarray2_new | 156 | 151 | 5 | 0 | 0 | 5 |
| jarray_get | 8382 | 6628 | 1754 | 0 | 11 | 332 |
| jarray_length | 790 | 677 | 113 | 0 | 0 | 85 |
| jarray_new | 784 | 757 | 27 | 0 | 0 | 27 |
| jarray_set | 3840 | 3291 | 549 | 0 | 5 | 257 |
| jbool_array_get | 51 | 51 | 0 | 0 | 0 | 0 |
| jbool_array_length | 32 | 32 | 0 | 0 | 0 | 0 |
| jbool_array_new | 34 | 30 | 4 | 0 | 0 | 4 |
| jbool_array_set | 99 | 98 | 1 | 0 | 0 | 1 |
| jdouble_array_new | 72 | 72 | 0 | 0 | 0 | 0 |
| jdouble_array_set | 210 | 206 | 4 | 0 | 0 | 2 |

Blocked-case counts overlap between API functions. Goal counts count distinct reported obligations, not unique source calls. They include allocation, nullness, array validity, bounds and matrix row/shape requirements where present.

### Most frequent unresolved JArray clauses

| API | Requires clause | Unresolved goals | Trusted requirement |
| --- | --- | --- | --- |
| jarray_get | 3 | 1132 | `0 <= index < array->length` |
| jarray_get | 2 | 594 | `jintarray_valid(array)` |
| jarray_set | 3 | 372 | `0 <= index < array->length` |
| jarray_set | 2 | 165 | `jintarray_valid(array)` |
| jarray2_get | 3 | 152 | `0 <= index < array->length` |
| jarray2_get | 2 | 125 | `jintarray2_valid(array)` |
| jarray_length | 2 | 113 | `jintarray_valid(array)` |
| jarray_get | 1 | 28 | `array != \null` |
| jarray_new | 1 | 27 | `length >= 0` |
| jarray_set | 1 | 12 | `array != \null` |
| jarray2_new | 2 | 4 | `columns >= 0` |
| jbool_array_new | 1 | 4 | `length >= 0` |

### JArray blockers by category

| Category | Role | Cases calling JArray | Cases blocked by JArray | JArray-only cases | Unresolved JArray goals |
| --- | --- | --- | --- | --- | --- |
| sequential | original | 1 | 0 | 0 | 0 |
| sequential | mutant | 35 | 2 | 0 | 4 |
| branch | original | 2 | 1 | 0 | 1 |
| branch | mutant | 34 | 22 | 0 | 142 |
| single_path_loop | original | 9 | 5 | 0 | 10 |
| single_path_loop | mutant | 176 | 99 | 0 | 434 |
| multi_path_loop | original | 8 | 0 | 0 | 0 |
| multi_path_loop | mutant | 192 | 120 | 1 | 940 |
| nested | original | 8 | 6 | 0 | 36 |
| nested | mutant | 181 | 157 | 0 | 1167 |

### Original cases with a recorded JArray blocker

| Program (case record) | Category | Unresolved JArray goals | Other unresolved goals |
| --- | --- | --- | --- |
| [CombSort](cases/CombSort/original/c/record.json) | nested | 3 | 9 |
| [CountWays](cases/CountWays/original/c/record.json) | single_path_loop | 1 | 16 |
| [CountingSort](cases/CountingSort/original/c/record.json) | nested | 6 | 23 |
| [MaxProduct](cases/MaxProduct/original/c/record.json) | nested | 4 | 12 |
| [MaxSumOfThreeConsecutive](cases/MaxSumOfThreeConsecutive/original/c/record.json) | single_path_loop | 2 | 11 |
| [MaxSumSubseq](cases/MaxSumSubseq/original/c/record.json) | single_path_loop | 2 | 9 |
| [MinCost](cases/MinCost/original/c/record.json) | nested | 15 | 22 |
| [MinJumps](cases/MinJumps/original/c/record.json) | nested | 2 | 11 |
| [MoveFirst](cases/MoveFirst/original/c/record.json) | branch | 1 | 8 |
| [MultiplyElements](cases/MultiplyElements/original/c/record.json) | single_path_loop | 1 | 7 |
| [RadixSort](cases/RadixSort/original/c/record.json) | nested | 6 | 21 |
| [SumList](cases/SumList/original/c/record.json) | single_path_loop | 4 | 7 |

Cases where JArray preconditions are the sole recorded unresolved obligations: [MaxDifference/1](cases/MaxDifference/mutant_1/c/record.json).

## Evidence and regeneration

[statistics.json](statistics.json) contains full-precision statistics, case-level JArray blocker evidence, Java workload-count snapshots and source hashes. Category membership comes from the [selection manifest](../../../../FormalBench-data/FilteredData/selected_java/seed_726_per_category_10_653ade686f/selection_manifest.json). Main case records and WP reports are the outcome evidence. Historic records under `history/` and other diagnostic runs are excluded.

The Java workload run supplies only matching, complete count captures for the successful final cases. Its different aggregate outcomes are not mixed into this experiment. The snapshots in `statistics.json` preserve the counts used in this README; raw solver traces and test/configuration changes are not required to read the report.

Regenerate from the repository root in the local Linux environment (the matching workload run must be available):

```bash
python3 -m verification.specification_evaluation.summarize_experiment \
  --output verification/specification_evaluation/results/originals_stop_tool_error_20260927T161243Z_goal10_case300 \
  --java-workload verification/specification_evaluation/results/java_workload_20260929T133038Z_workers2
```
