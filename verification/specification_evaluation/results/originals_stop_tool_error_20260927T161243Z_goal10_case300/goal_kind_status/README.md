# Goal/assertion analysis files

The tables, counts, evidence definitions, and attempt provenance are maintained in the [main experiment report](../README.md#short-budget-goalassertion-kinds-and-status).

- [Kind/status summary](goal_kinds.json): totals and source/checksum provenance.
- [C goals](c_goals.csv).
- [Java original assertions and method-proof coverage](java_assertions.csv), [raw kind totals](java_assertion_kinds.csv), and [clause-type totals](java_clause_types.csv).
- [Java mutant workload snapshot](java_mutant_workload_snapshot.json), [raw kind totals](java_mutant_assertion_kinds.csv), [clause-type totals](java_mutant_clause_types.csv), and [diagnostics](java_mutant_diagnostics.csv).
- [Original run records](../original_verification/java_goal_recording_20261002T100041Z/README.md) and [mutant run records](../mutant_verification/java_workload_20260929T133038Z_workers2/README.md).

`section.md` is the report-generation fragment embedded in the main README; edit that fragment when changing the analysis text or tables.
