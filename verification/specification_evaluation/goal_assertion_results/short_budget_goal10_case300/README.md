# Goal/assertion results

This is a separate, result-only analysis of the saved 10-second solver / 300-second case experiment. The [consistency and completeness report](../../results/originals_stop_tool_error_20260927T161243Z_goal10_case300/README.md) contains the authoritative program-level outcomes. No verifier or program was rerun during this separation.

## Java recording limitations

The captures retain generated OpenJML assertion IDs and raw check kinds. They do **not** reconstruct an exact source-clause type and location for every assertion. Clause-type tables contain inferred families: a single source clause can generate several checks, implicit checks need not have an explicit source clause, and an `Assignable` check does not identify whether its owner was `assignable` or `loop_writes`. These tables must not be presented as an exact JML-clause mapping.

A complete proved/violated/unknown verdict for every assertion inside an unresolved Java method cannot be reconstructed from these saved runs. Assertions in a method finishing with `no warnings` are covered by that method proof. For other methods, the records retain selected diagnostics, but the remaining individual verdicts were not recorded. An unresolved program can still contain proved methods. Absence of a diagnostic does not prove an assertion, and lack of whole-method coverage does not establish that the assertion failed or was individually unknown.

The 186 covered original assertions comprise 150 checks in 50 proved constructors and 36 checks in 12 proved benchmark methods. The 2,931 covered mutant assertions belong to 977 proved constructors. These are method-proof coverage counts, not individual assertion-proof rates. C WP entries have individual recorded verdicts, so the Java and C status columns measure different evidence.

The original assertion attempt verified 12 originals; the authoritative program-level run verified 13. FindPoints explains the difference. Assertion captures cover all 50 originals and all 977 mutants. The authoritative verified-original completeness subset remains 192 mutants; the 12 instrumented-run verified originals have 170 mutants, with another 22 from FindPoints.

Only result records, counts, tables, manifests, audits, and documentation are tracked here. Source code, frozen contract copies, raw logs, solver queries/responses, and local history are excluded. Saved raw records retain historical execution paths; derived manifests identify current files and explicit path roots.

## Saved records

- [Original instrumented records](original_verification/java_goal_recording_20261002T100041Z/README.md).
- [Mutant instrumented records](mutant_verification/java_workload_20260929T133038Z_workers2/README.md).
- [Result tables and snapshots](goal_kind_status/README.md).
- [Historical reporting context](program_context/README.md).

<!-- goal-kind-status:start -->
## Short-budget goal/assertion kinds and status

Short-budget saved results: 50 originals and 977 retained mutants per language; 10 seconds per goal, 300 seconds per case; no verifier rerun.

C tables count individually reported WP goal entries. Java tables count generated assertions and the method-level proof evidence available for them; these are different units. Constructors are included.

The Java original assertion table covers **all 50 originals** (9,387 generated assertions). Its counts and method-proof coverage come from the instrumented attempt `original_verification/java_goal_recording_20261002T100041Z/instrumented_cases`, in which **12/50 originals** were fully verified. The authoritative program-level results remain **13/50**. **FindPoints** was proved in the authoritative run but returned unknown/timeout in the instrumented attempt. The 12 verified originals are an outcome of that attempt, not a filter on the assertion table.

The Java mutant assertion table covers **all 977 mutants from all 50 originals**, using the separate [archived instrumented mutant attempt](mutant_verification/java_workload_20260929T133038Z_workers2/README.md). Its run metadata, individual case records, workload counts, and summaries are retained in this separate analysis directory. Paths in the portable workload snapshot are relative to this analysis directory; historical paths inside raw records are preserved as provenance. It is not restricted to mutants of the 12 instrumented-run verified originals. For completeness analysis conditioned on a verified original, the authoritative subset remains **192 mutants from 13 originals**. The 12 originals verified in the instrumented attempt have 170 mutants; FindPoints contributes the remaining 22. These alternative subsets must not be substituted for one another. Canonical mutant outcomes and diagnostics remain authoritative; archived assertion counts and method-proof coverage are supplemental and do not provide a complete individual-assertion verdict partition.

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

### Java originals (50): clause types and generated-check evidence

These are clause-family labels inferred from the saved OpenJML assertion kinds, following the [OpenJML check definitions](https://www.openjml.org/documentation/checks.shtml). Preconditions at method entry are assumptions; the asserted precondition checks below concern called methods. Counts include constructor/library contracts, model-method calls and implicit checks, rather than only explicit benchmark clauses. One source clause can produce multiple checks: loop invariants have before-loop, loop-body and loop-exit checks; loop variants have decrease and non-negativity checks. A raw Assignable check alone does not identify whether its owning source clause was assignable or loop_writes.

The coverage/status definitions remain the same as in the raw-kind table below. Zero proved-method coverage does not establish that every check of that family is unproved; individual outcomes within other methods were not recorded.

| Clause type | JML syntax / meaning | Generated checks | Covered by proved method VCs | In other method VCs | Reported violation diagnostics |
|---|---|---:|---:|---:|---:|
| Callee preconditions | requires | 784 | 50 | 734 | 0 |
| Preconditions of calls within specifications | requires; specification-expression well-definedness | 2255 | 0 | 2255 | 0 |
| Normal postconditions | ensures | 132 | 12 | 120 | 2 |
| Exceptional postconditions | signals; also implicit exception constraints of normal_behavior | 101 | 62 | 39 | 0 |
| Allowed exception types | signals_only | 135 | 62 | 73 | 0 |
| Loop invariants | loop_invariant / maintaining | 375 | 0 | 375 | 0 |
| Loop variants | decreases / decreasing (decrease and non-negativity checks) | 82 | 0 | 82 | 0 |
| Recursive termination metrics | measured_by (decrease and non-negativity checks) | 16 | 0 | 16 | 0 |
| Write/frame conditions | assignable / loop_writes; raw kind does not identify the owning clause | 150 | 0 | 150 | 0 |
| Read permissions | accessible | 517 | 0 | 517 | 0 |
| Class invariants | invariant | 1 | 0 | 1 | 0 |
| Explicit assertions | assert (Java or JML) | 1 | 0 | 1 | 0 |
| Java runtime safety | implicit null, bounds, allocation-size and division checks | 789 | 0 | 789 | 1 |
| Specification-expression well-definedness | implicit checks within JML expressions | 4049 | 0 | 4049 | 0 |
| **Total** | | **9387** | **186** | **9201** | **3** |

No verifier was rerun to obtain these families. These are generated-check counts, not source-clause counts or a complete per-clause verdict partition. The detailed assertion and mutant-diagnostic CSVs now include a clause_type column; [clause-type totals](goal_kind_status/java_clause_types.csv) are also available.

#### Java originals: raw assertion-kind details

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

### Java mutants (977): clause types and generated-check evidence

Generated assertion captures **are available** for all 977 mutants in the earlier short-budget workload archive. All 977 captures were matched to the canonical mutant records by raw-source, frozen-specification and annotated-source hashes, settings, and verifier identity. This earlier run used two Java workers and its earlier recorder version; its workload counts and method proof coverage are supplemental. Canonical mutant outcomes and diagnostics are preserved. Complete solver captures: 958/977; records missing generated counts: 0.

The generated/coverage columns below come from that archived recording run. The violation and unknown-diagnostic columns come from the canonical short-budget mutant records. They describe different attempts and do not form a complete assertion-level verdict partition. Unknown diagnostic counts are not counts of all unknown assertions.

| Clause type | Generated checks | Covered by proved method VCs | In other method VCs | Canonical violation diagnostics | Canonical unknown/unconfirmed diagnostics |
|---|---:|---:|---:|---:|---:|
| Callee preconditions | 19429 | 977 | 18452 | 0 | 6 |
| Preconditions of calls within specifications | 55303 | 0 | 55303 | 2 | 0 |
| Normal postconditions | 2804 | 0 | 2804 | 175 | 1 |
| Exceptional postconditions | 1970 | 977 | 993 | 0 | 0 |
| Allowed exception types | 2698 | 977 | 1721 | 0 | 0 |
| Loop invariants | 8712 | 0 | 8712 | 1 | 5 |
| Loop variants | 1934 | 0 | 1934 | 0 | 1 |
| Recursive termination metrics | 362 | 0 | 362 | 6 | 15 |
| Write/frame conditions | 4071 | 0 | 4071 | 0 | 0 |
| Read permissions | 14165 | 0 | 14165 | 0 | 0 |
| Class invariants | 15 | 0 | 15 | 0 | 0 |
| Explicit assertions | 16 | 0 | 16 | 0 | 0 |
| Java runtime safety | 24272 | 0 | 24272 | 35 | 82 |
| Specification-expression well-definedness | 96345 | 0 | 96345 | 1 | 40 |
| Diagnostic without a recorded clause kind | N/A | N/A | N/A | 0 | 787 |
| **Mutant total** | **232096** | **2931** | **229165** | **220** | **937** |

The counts are retained in a portable [mutant workload snapshot](goal_kind_status/java_mutant_workload_snapshot.json), [mutant clause-type totals](goal_kind_status/java_mutant_clause_types.csv), and [mutant raw assertion-kind totals](goal_kind_status/java_mutant_assertion_kinds.csv). README regeneration does not require the archived raw logs. No verification was rerun.

#### Java mutants: canonical raw diagnostic details


The canonical record.json files do not embed generated-assertion captures; those were recovered from the matching archived recording run above. This table retains the canonical diagnostics by raw kind. Multiple diagnostics can belong to one mutant, so these counts differ from mutant outcome counts.

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

<!-- counterexample-goal-mapping:start -->
## C witness-to-goal and Java comparison (short budget)

This offline mapping covers all **977 mutant pairs** using saved results at **10 seconds per goal / 300 seconds per case**. No verifier, compiler, or replay was run. C provides 900 validated execution failures: 710 functional/specification failures and 190 safety failures. Another 77 mutants have no validated witness; this does not establish correctness.

**710 mutants** have a concrete falsified C postcondition identified from the saved observation; **685** also have a recorded WP goal for at least one such clause. The clause index is resolved against the hash-pinned entry contract and the goal's property name. Goal states are retained exactly as saved; WP itself reported zero violated mutant goals.

| C replay evidence | Java specification violation | Java safety failure | Java unknown/timeout | Total |
|---|---:|---:|---:|---:|
| specification violation | 141 | 34 | 535 | 710 |
| precondition/RTE failure | 5 | 0 | 185 | 190 |
| no_validated_failure | 0 | 0 | 77 | 77 |

Safety evidence consists of 144 array-bounds failures, 12 negative-allocation-size failures, 3 null-reference failures, and 31 stack overflows identified by the saved independent audit. Compatible runtime-helper preconditions are listed for **129 cases** as **candidates**, never as individually falsified goals. Stack-overflow cases include related termination/recursive-variant goals where recorded (31 cases); stack overflow does not establish nontermination or a falsified variant. Another 30 safety cases have no located recorded goal. Audit harness source lines are retained as trace evidence and are not equated to annotated WP source lines. Final output cannot locate loop-invariant or loop-variant failures.

Java alignment records corresponding source-clause **semantic candidates**, saved generated-assertion counts, and canonical diagnostics for the same mutant ID. **179 C-failing mutants** have a confirmed Java diagnostic in at least one corresponding assertion family. This is a family-level comparison: no individual Java assertion verdict, same-input Java replay, or logical equivalence of the two predicates is established. Unknown Java diagnostics are not counted as confirmed failures. C-specific errno and native buffer properties may have no direct Java counterpart.

Example: **CombSort mutant 11**, input `nums=[1,0]`, returned `[1,0]` instead of `[0,1]`. Its saved post-state falsifies C postconditions 2 (the `fb_value` content specification) and 3 (every element is at most the last element). These map to `typed_ref_combSort_ensures_2` and `typed_ref_combSort_ensures_3`, with their recorded WP states retained. Java postconditions 2 and 3 are semantic counterparts, while the saved Java case is unknown/timeout.

The 745 recorded postcondition goals mapped here retain **465 unknown** and **280 proved** WP states. A mapped goal can be recorded as proved while a concrete execution falsifies its clause: that goal can rely on loop invariants or callee contracts whose separate obligations remain unproved. This mapping does not establish which intermediate assumption failed and does not change any verification verdict.

Artifacts: [per-mutant comparison CSV](counterexample_goal_mapping/cases.csv), [one-row-per-goal comparison CSV](counterexample_goal_mapping/goals.csv), [clause/goal mapping CSV](counterexample_goal_mapping/mappings.csv), and [full witnesses, clause catalog, goal states and hash provenance](counterexample_goal_mapping/mapping.json). Counts of mapping edges differ from mutant counts because one witness can falsify several clauses and one candidate mapping can list several goals.

<!-- counterexample-goal-mapping:end -->
