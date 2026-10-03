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
