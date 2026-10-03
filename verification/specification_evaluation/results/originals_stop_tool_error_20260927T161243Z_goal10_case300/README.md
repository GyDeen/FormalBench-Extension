# Final paired specification verification experiment

Run: `originals_stop_tool_error_20260927T161243Z_goal10_case300`. This report covers 50 selected originals in each language and 977 retained Java/C mutant pairs (2,054 case records). 2,004 case records are complete; 50 have deferred verification. Completed counterexample searches are reported separately from WP proof outcomes.

## Result summary

| Population / evidence | Total | Proved | Specification/postcondition failures | Safety failures | Inconclusive | Tool failures | Deferred verification |
| --- | --- | --- | --- | --- | --- | --- | --- |
| Java originals / OpenJML | 50 | 13 | 2 | 1 | 34 | 0 | 0 |
| C originals / WP | 50 | 6 | 0 | 0 | 44 | 0 | 0 |
| Java mutants / OpenJML | 977 | 0 | 146 | 34 | 797 | 0 | 0 |
| C mutants / validated native replay | 977 | 0 | 710 | 190 | 77 | 0 | 0 |

All 977 C mutants were searched: **710 postcondition violations and 190 safety failures (900 validated failures, 92.12%)**; 77 have no validated failure. All 50 original controls have replay evidence with no validated failure. Finite passing trials are inconclusive and do not count as proofs. Java diagnostics and C execution witnesses use different evidence types.

The four corrected C originals completed WP re-verification and remain unknown because some goals are unresolved. The C counterexample search is complete; 50 mutant WP invocations remain deferred after the source repairs. Historical search subsets are included once. See [results_summary.json](results_summary.json) for the consolidated outcomes, category statistics, successful-case time/goal distributions and JArray blockers.

## Recorded verifier outcomes

| Population | Language | Total | Proved | Specification violation | Precondition/RTE failure | Unknown/timeout | Syntax/tool failure | Not run |
| --- | --- | --- | --- | --- | --- | --- | --- | --- |
| Originals | Java | 50 | 13 | 2 | 1 | 34 | 0 | 0 |
| Originals | C | 50 | 6 | 0 | 0 | 44 | 0 | 0 |
| Mutants | Java | 977 | 0 | 146 | 34 | 797 | 0 | 0 |
| Mutants | C | 977 | 0 | 0 | 0 | 922 | 5 | 50 |

Java proved 13/50 originals (26.00%); C proved 6/50 (12.00%). No mutant was fully proved in either language. Java reports 146 specification violations (146/977 = 14.94%), including 121 among the 192 mutants whose Java original proved (63.02%). The C WP records contain zero explicit invalid verdicts. The completed native search supplies independently validated C failures, reported above and in the integrated evidence section. WP unknown verdicts remain recorded as unknown.

| Paired mutant outcome | Count |
| --- | --- |
| not run | 50 |
| precondition/RTE failure | 34 |
| tool failure | 5 |
| unknown/timeout | 888 |

There are 0 decisive pairs; agreement on decisive pairs is N/A. The 55 C sort reruns replaced old annotation/tool failures with `unknown/timeout`. After the four C original repairs, 50 mutant WP runs remain deferred and 5 retain syntax/tool failures. Java originals were rerun with workload recording. See [summary.json](summary.json), [run.json](run.json), and [sort_c_integration.json](sort_c_integration.json).

### Integrated C counterexample evidence

The full-population C replay search is archived inside this experiment. **50/50 originals and 977/977 mutants** have replay evidence. **710 mutant postcondition violations and 190 safety failures** were independently validated with passing original controls. Another 77 searched mutants have no validated failure; 0 mutants have no counterexample search evidence. The search is complete. Finite passing trials do not prove a program.

| C mutant evidence view | Proved | Postcondition violations | Safety failures | Unknown/timeout | Syntax/tool failure | Not run |
| --- | --- | --- | --- | --- | --- | --- |
| Original WP outcomes | 0 | 0 | 0 | 922 | 5 | 50 |
| WP plus validated concrete replay | 0 | 710 | 190 | 73 | 0 | 4 |

The main proof table and successful-verification time/goal statistics retain their original meaning. The combined evidence view records concrete failures while preserving each WP verdict in its case record. Java tool-reported rejections and C replay-validated failures remain different evidence types.

| Category | Eligible C mutants | Searched | Postcondition violations | Safety failures | Searched, unresolved | Not searched |
| --- | --- | --- | --- | --- | --- | --- |
| sequential | 143 | 143 | 143 | 0 | 0 | 0 |
| branch | 193 | 193 | 143 | 42 | 8 | 0 |
| single_path_loop | 183 | 183 | 122 | 52 | 9 | 0 |
| multi_path_loop | 239 | 239 | 171 | 28 | 40 | 0 |
| nested | 219 | 219 | 131 | 68 | 20 | 0 |

The replay evidence uses the refreshed experiment sources and frozen contracts, including MoveFirst, MultiplyElements, NextPowerOf2 and PairWise. Earlier evidence with incompatible source records is excluded.

Replay constructs valid JArray inputs and executes the fixed native runtime. Finding a concrete program fault can therefore succeed even when its WP JArray obligations remain unknown. This does not resolve the JArray proof blockers listed below.

See the [integrated counterexample report](counterexamples/README.md), [per-case evidence](counterexamples/summary.json), and [integration audit](counterexamples/integration_audit.json). The original proof-only summary is preserved byte-for-byte in [summary_verification.json](summary_verification.json); [summary.json](summary.json) now includes `c_counterexample_evidence`.

### Historical C counterexample subset

These 80 mutants are included in the full 977-mutant search and are not added again to the final totals.

A separate C-only follow-up completed the six proved original controls and their 80 mutants. Concrete replay validated **79 functional postcondition violations and 1 runtime-safety failure**. All 80 mutant WP proof outcomes remain `unknown/timeout`; no full WP model was returned. Of the functional witnesses, 51 came from preliminary incremental SMT candidate models and 28 from bounded candidate search. Every witness reproduced with stronger compiler diagnostics, ASan/UBSan, and the proved original as a control. These are execution-validated faults, not WP invalid verdicts. The main results above and their original timings are preserved.

| Category | Supplemental mutants | Validated postcondition violations | Validated safety failures |
| --- | --- | --- | --- |
| sequential | 19 | 19 | 0 |
| branch | 35 | 35 | 0 |
| single_path_loop | 0 | 0 | 0 |
| multi_path_loop | 26 | 25 | 1 |
| nested | 0 | 0 | 0 |

See the [C counterexample follow-up](counterexamples/c_counterexamples_proved_originals_20261001_goal10_case300/README.md) and [independent audit](counterexamples/c_counterexamples_proved_originals_20261001_goal10_case300/audit.json). Java's original tool-reported outcomes and these replay-validated C outcomes use different evidence; their rejection rates must not be directly compared.

### Corrected C original verification

The four corrected originals were rerun with the experiment settings (10 seconds per goal, 300 seconds per process). All annotation preflights and task-generation checks passed. Each result remains `unknown/timeout` because WP goals are unresolved; no process reached its 300-second limit. These results replace the four deferred original records. Mutant WP reruns remain deferred.

| Original | Outcome | Wall seconds | Proved goals | Unresolved goals |
| --- | --- | --- | --- | --- |
| MoveFirst | unknown/timeout | 54.097 | 73 | 9 |
| MultiplyElements | unknown/timeout | 46.061 | 65 | 8 |
| NextPowerOf2 | unknown/timeout | 11.719 | 20 | 2 |
| PairWise | unknown/timeout | 82.640 | 80 | 11 |

See the [original verification integration](original_verification/integration.json) for source, contract and record hashes and the archived invocations.

## Configuration and interpretation

**Rejection-reporting limitation:** the 146 Java specification violations are classified OpenJML proof-failure reports. C requires an explicit invalid verdict or a validated counterexample, but this run did not enable WP counterexample generation. Standard WP JSON reports document proof/unknown/failure/timeout statuses rather than a top-level invalid verdict. The zero C rejection count is therefore not directly comparable to Java fault detection, and does not mean the C mutants satisfy their specifications. Increasing the timeout alone does not fix this evidence mismatch. See the [Frama-C 33 WP manual, sections 2.4.10 and 2.7](https://www.frama-c.com/download/frama-c-wp-manual.pdf).

The sample uses seed 726 with ten originals in each of five dataset categories. Only retained, previously screened mutant pairs are evaluated. Specifications are frozen independently per language and transferred to unchanged executable sources.

The Java verifier reports `openjml 21.0.27` and uses the `z3-4.3.X` driver with bundled solver `Z3 version 4.10.2 - 64 bit`. The C verifier reports `33.0 (Arsenic)` and uses Alt-Ergo:2.4.3,Z3:4.8.12, `Typed+ref`, and `x86_64`. Budgets are 10 seconds per solver goal and 300 seconds per process, with 1000 MB WP memory and 4 parallel WP jobs per C worker. The initial batch used two C workers and one Java worker; the integrated sort rerun used two C workers. Java original workload reruns used 12 concurrent workers. Timings reflect recorded invocations under these schedules, not an isolated speed benchmark.

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

Java counts below come from 13 complete captures in `originals_stop_tool_error_20260927T161243Z_goal10_case300`, matched to the successful final-run cases by raw-source, frozen-specification and annotated-source hashes, settings and verifier identity. Original uninstrumented verdicts and proof timings remain authoritative; instrumented reruns supply workload counts and retain separate outcomes and timings. Missing capture programs: none. Generated method VCs and total assertions include constructors; the program-assertion row removes constructors.

## Java original goal recording

All 50 Java originals have generated workload counts in their case records: 9,387 assertions, 100 method VCs and 164 solver queries. 49/50 solver captures are complete. The 50 reruns used 12 concurrent workers with the same frozen inputs, verifier and proof budgets. The original uninstrumented proof results and timings are preserved for all Java originals. Instrumented verdicts and timings are supplemental; FindPoints remains proved from its original invocation, while its recorded rerun returned unknown. See [per-original counts and outcome changes](java_original_goal_recording.json).

Previous case results and summaries are retained under `original_verification/java_goal_recording_20261002T100041Z`.

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

<!-- goal-kind-status:start -->
## Short-budget goal/assertion kinds and status

Short-budget saved results: 50 originals and 977 retained mutants per language; 10 seconds per goal, 300 seconds per case; no verifier rerun.

C tables count individually reported WP goal entries. Java tables count generated assertions and the method-level proof evidence available for them; these are different units. Java uses the saved instrumented original workload attempt (12 proved programs), while the authoritative original proof results elsewhere remain unchanged (13 proved programs). Constructors are included.

### C WP goal kinds: originals and mutants

| Population | Goal kind | Total | Proved | Violated | Unresolved |
|---|---|---:|---:|---:|---:|
| original | Behavior completeness/disjointness | 2 | 2 | 0 | 0 |
| original | Callee preconditions | 600 | 551 | 0 | 49 |
| original | Exit conditions | 47 | 47 | 0 | 0 |
| original | Explicit assertions | 1 | 1 | 0 | 0 |
| original | Function frame conditions | 946 | 829 | 0 | 117 |
| original | Loop frame conditions | 304 | 304 | 0 | 0 |
| original | Loop invariants | 324 | 247 | 0 | 77 |
| original | Loop/recursion decrease | 96 | 93 | 0 | 3 |
| original | Postconditions | 206 | 145 | 0 | 61 |
| original | Runtime safety checks | 20 | 12 | 0 | 8 |
| original | Termination | 54 | 47 | 0 | 7 |
| **original total** | | **2600** | **2278** | **0** | **322** |
| mutant | Behavior completeness/disjointness | 32 | 32 | 0 | 0 |
| mutant | Callee preconditions | 16253 | 13494 | 0 | 2759 |
| mutant | Exit conditions | 1270 | 994 | 0 | 276 |
| mutant | Explicit assertions | 16 | 16 | 0 | 0 |
| mutant | Function frame conditions | 23310 | 18652 | 0 | 4658 |
| mutant | Loop frame conditions | 8824 | 7898 | 0 | 926 |
| mutant | Loop invariants | 7008 | 4821 | 0 | 2187 |
| mutant | Loop/recursion decrease | 2052 | 1825 | 0 | 227 |
| mutant | Postconditions | 3889 | 2539 | 0 | 1350 |
| mutant | Runtime safety checks | 1147 | 881 | 0 | 266 |
| mutant | Termination | 1773 | 1378 | 0 | 395 |
| **mutant total** | | **65574** | **52530** | **0** | **13044** |

No WP goal is reported violated. This does not contradict the independently validated native C mutant counterexamples: replay outcomes are a different evidence type. Cases without recorded WP goals contribute no goal entries; missing/deferred/tool-failure runs do not count as proved goals.

### Java generated assertion kinds: original workload capture

An assertion in a method VC that finished with `no warnings` is covered by that method proof. The other-method column includes assertions in unresolved, failed, or interrupted method VCs; it is **not** a count of individually unknown or violated assertions. A partially failed method can still contain checks that hold. Reported violation diagnostics are separate observations, not a complete per-assertion verdict partition. Inconclusive warnings are excluded from the violation column.

| Assertion kind | Generated | Covered by proved method VCs | In other method VCs | Reported violation diagnostics |
|---|---:|---:|---:|---:|
| Accessible | 517 | 0 | 517 | 0 |
| Assert | 1 | 0 | 1 | 0 |
| Assignable | 150 | 0 | 150 | 0 |
| ExceptionalPostcondition | 101 | 62 | 39 | 0 |
| ExceptionList | 135 | 62 | 73 | 0 |
| IllegalArgument | 2 | 0 | 2 | 0 |
| InvariantEntrance | 1 | 0 | 1 | 0 |
| LoopDecreases | 41 | 0 | 41 | 0 |
| LoopDecreasesNonNegative | 41 | 0 | 41 | 0 |
| LoopInvariant | 125 | 0 | 125 | 0 |
| LoopInvariantAfterLoop | 125 | 0 | 125 | 0 |
| LoopInvariantBeforeLoop | 125 | 0 | 125 | 0 |
| NullArgument | 2 | 0 | 2 | 0 |
| PossiblyDivideByZero | 3 | 0 | 3 | 1 |
| PossiblyNegativeIndex | 230 | 0 | 230 | 0 |
| PossiblyNegativeSize | 9 | 0 | 9 | 0 |
| PossiblyNullDeReference | 306 | 0 | 306 | 0 |
| PossiblyTooLargeIndex | 241 | 0 | 241 | 0 |
| Postcondition | 132 | 12 | 120 | 2 |
| Precondition | 784 | 50 | 734 | 0 |
| TerminationDecreases | 8 | 0 | 8 | 0 |
| TerminationNonNegative | 8 | 0 | 8 | 0 |
| UndefinedBadCast | 28 | 0 | 28 | 0 |
| UndefinedCalledMethodPrecondition | 2255 | 0 | 2255 | 0 |
| UndefinedDivideByZero | 704 | 0 | 704 | 0 |
| UndefinedNegativeIndex | 439 | 0 | 439 | 0 |
| UndefinedNullDeReference | 2348 | 0 | 2348 | 0 |
| UndefinedTooLargeIndex | 526 | 0 | 526 | 0 |
| **Total** | **9387** | **186** | **9201** | **3** |

Method outcomes were read from saved recorder logs. MaxDifference hit the process timeout; its unfinished method is kept in the other-method column. A complete Java assertion-level proved/violated/unknown partition is unavailable from these results and would require additional verification. No experiment was rerun.

### Java mutant assertion-failure diagnostics by kind

Generated assertion captures are absent for 977 of 977 canonical Java mutants. The table below counts saved diagnostics only; a zero diagnostic count supplies no assertion-level proof count. Multiple diagnostics can belong to one mutant, so these counts differ from mutant outcome counts.

| Diagnostic kind | Reported violation diagnostics | Unknown/unconfirmed diagnostics |
|---|---:|---:|
| LoopDecreasesNonNegative | 0 | 1 |
| LoopInvariant | 1 | 1 |
| LoopInvariantBeforeLoop | 0 | 4 |
| Other diagnostic (no assertion kind) | 0 | 787 |
| PossiblyDivideByZero | 35 | 31 |
| PossiblyNegativeSize | 0 | 1 |
| PossiblyNullDeReference | 0 | 49 |
| PossiblyTooLargeIndex | 0 | 1 |
| Postcondition | 175 | 1 |
| Precondition | 0 | 6 |
| TerminationDecreases | 5 | 5 |
| TerminationNonNegative | 1 | 10 |
| UndefinedCalledMethodPrecondition | 2 | 0 |
| UndefinedNegativeIndex | 0 | 3 |
| UndefinedNullDeReference | 1 | 35 |
| UndefinedTooLargeIndex | 0 | 2 |

Machine-readable details: [kind/status summary](goal_kind_status/goal_kinds.json), [C goal entries](goal_kind_status/c_goals.csv), [Java original assertions and method proof coverage](goal_kind_status/java_assertions.csv), [Java original kind totals](goal_kind_status/java_assertion_kinds.csv), and [Java mutant diagnostics](goal_kind_status/java_mutant_diagnostics.csv). The summary includes record and log hashes for provenance.

<!-- goal-kind-status:end -->

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

| Category | Language | Total | Proved | Specification violation | Precondition/RTE | Unknown/timeout | Syntax/tool | Not run |
| --- | --- | --- | --- | --- | --- | --- | --- | --- |
| sequential | java | 10 | 9 | 0 | 1 | 0 | 0 | 0 |
| sequential | c | 10 | 2 | 0 | 0 | 8 | 0 | 0 |
| branch | java | 10 | 4 | 2 | 0 | 4 | 0 | 0 |
| branch | c | 10 | 2 | 0 | 0 | 8 | 0 | 0 |
| single_path_loop | java | 10 | 0 | 0 | 0 | 10 | 0 | 0 |
| single_path_loop | c | 10 | 0 | 0 | 0 | 10 | 0 | 0 |
| multi_path_loop | java | 10 | 0 | 0 | 0 | 10 | 0 | 0 |
| multi_path_loop | c | 10 | 2 | 0 | 0 | 8 | 0 | 0 |
| nested | java | 10 | 0 | 0 | 0 | 10 | 0 | 0 |
| nested | c | 10 | 0 | 0 | 0 | 10 | 0 | 0 |

### Mutant outcomes by category

| Category | Language | Total | Proved | Specification violation | Precondition/RTE | Unknown/timeout | Syntax/tool | Not run |
| --- | --- | --- | --- | --- | --- | --- | --- | --- |
| sequential | java | 143 | 0 | 40 | 31 | 72 | 0 | 0 |
| sequential | c | 143 | 0 | 0 | 0 | 143 | 0 | 0 |
| branch | java | 193 | 0 | 105 | 2 | 86 | 0 | 0 |
| branch | c | 193 | 0 | 0 | 0 | 181 | 0 | 12 |
| single_path_loop | java | 183 | 0 | 0 | 0 | 183 | 0 | 0 |
| single_path_loop | c | 183 | 0 | 0 | 0 | 144 | 1 | 38 |
| multi_path_loop | java | 239 | 0 | 1 | 1 | 237 | 0 | 0 |
| multi_path_loop | c | 239 | 0 | 0 | 0 | 238 | 1 | 0 |
| nested | java | 219 | 0 | 0 | 0 | 219 | 0 | 0 |
| nested | c | 219 | 0 | 0 | 0 | 216 | 3 | 0 |

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
| original | 50 | 28 | 50 | 0 | 13 | 0 | 582 | 48 |
| mutant | 977 | 618 | 918 | 53 | 400 | 1 | 15501 | 2687 |

**Observed conclusion: JArray call preconditions are a proof blocker in 413 C cases (13 originals and 400 mutants).** They are the only remaining recorded blocker in 1 case. These case counts overlap other proof difficulties; they are not additional outcome categories. Goal-less cases are excluded from blocker attribution, including transfer/tool failures and process timeouts without classified goal coverage. All unresolved JArray goals here are unknown; none is classified as violated.

Among originals, 2/28 callers of JArray proved; 4/22 originals without JArray calls proved. This is an association in the selected program sample; program complexity, invariants and solver limits also vary.

There are 59 mutant cases without classified goal coverage (5 syntax/tool failures, 4 unknown/timeouts and 50 deferred runs). Of these, 53 call JArray; their blocker status is unavailable. Requirement text below comes from trusted headers whose hashes match the run configuration and every C case record.

### JArray preconditions by function

| API | Total goals | Proved | Unknown | Violated | Blocked originals | Blocked mutants |
| --- | --- | --- | --- | --- | --- | --- |
| jarray2_get | 1587 | 1309 | 278 | 0 | 2 | 37 |
| jarray2_length | 34 | 34 | 0 | 0 | 0 | 0 |
| jarray2_new | 156 | 151 | 5 | 0 | 0 | 5 |
| jarray_get | 8382 | 6630 | 1752 | 0 | 9 | 332 |
| jarray_length | 802 | 687 | 115 | 0 | 2 | 85 |
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
| jarray_get | 2 | 592 | `jintarray_valid(array)` |
| jarray_set | 3 | 372 | `0 <= index < array->length` |
| jarray_set | 2 | 165 | `jintarray_valid(array)` |
| jarray2_get | 3 | 152 | `0 <= index < array->length` |
| jarray2_get | 2 | 126 | `jintarray2_valid(array)` |
| jarray_length | 2 | 115 | `jintarray_valid(array)` |
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
| single_path_loop | original | 9 | 6 | 0 | 11 |
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
| [PairWise](cases/PairWise/original/c/record.json) | single_path_loop | 1 | 10 |
| [RadixSort](cases/RadixSort/original/c/record.json) | nested | 6 | 21 |
| [SumList](cases/SumList/original/c/record.json) | single_path_loop | 4 | 7 |

Cases where JArray preconditions are the sole recorded unresolved obligations: [MaxDifference/1](cases/MaxDifference/mutant_1/c/record.json).

## Evidence and regeneration

[statistics.json](statistics.json) contains full-precision statistics, case-level JArray blocker evidence, Java workload-count snapshots and source hashes. Category membership comes from the [selection manifest](../../../../FormalBench-data/FilteredData/selected_java/seed_726_per_category_10_653ade686f/selection_manifest.json). Main case records and WP reports are the outcome evidence. Historic records under `history/` and other diagnostic runs are excluded.

Java original case records retain the uninstrumented proof results and timings, and link to separate instrumented rerun records. Statistics for successfully verified cases combine original proof timings with matching complete workload captures. The snapshots in `statistics.json` preserve the counts used in this README; raw solver traces and test/configuration changes are not required to read the report.

Regenerate from the repository root in the local Linux environment; matching Java workload captures can come from the archived snapshot:

```bash
python3 -m verification.specification_evaluation.summarize_experiment \
  --output verification/specification_evaluation/results/originals_stop_tool_error_20260927T161243Z_goal10_case300 \
  --java-workload verification/specification_evaluation/results/originals_stop_tool_error_20260927T161243Z_goal10_case300
```

## Archived C run reports

Corrected C translation/original verification reports and completed sibling C counterexample runs are archived inside this experiment. See [c_run_archive.json](c_run_archive.json) for source-to-archive mappings and hash checks. Final outcome counts are unchanged; historical execution paths remain recorded as originally used.

## Result artifact layout

This directory retains verification records, WP goal reports, replay results and witnesses, independent audit results, frozen contracts, and statistical summaries. Canonical program sources remain under `FormalBench-data/`; each case record identifies its original `raw_source` and source hash. Generated annotated source copies, replay harnesses, native compiler reports, binaries and duplicated command/transfer files are excluded from Git and kept in ignored local output storage. Commands and annotation-transfer results are already embedded in the verification records. Historical annotated-source and execution paths identify the files used at run time; they are not additional committed source files.

README and statistics regeneration requires the retained result JSON, frozen contracts, canonical repository support contracts, and Java workload count snapshot. Replaying witnesses or reintegrating raw runs requires the local generated working artifacts, or regeneration from canonical inputs; the result-only archive does not include those working files.
