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
