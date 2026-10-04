# Instrumented Java mutant records

This directory retains the `java_workload_20260929T133038Z_workers2` attempt. Its coverage and relationship to the authoritative outcomes are described in the [main assertion analysis](../../README.md#short-budget-goalassertion-kinds-and-status).

- [Run configuration](run.json) and [historical attempt summary](summary.json).
- `cases/<program>/<original or mutant_ID>/java/record.json`: each case's saved outcome, diagnostics, settings, and capture metadata.
- `cases/<program>/<original or mutant_ID>/java/java-workload.json`: generated assertion counts and capture completeness.
- [Portable mutant snapshot](../../goal_kind_status/java_mutant_workload_snapshot.json): matched records, assertion counts, and method-proof coverage. Source paths resolve relative to the experiment directory.

The run was moved from `output/verification_run_archive_20261001T080722Z/diagnostic_runs/java_workload_20260929T133038Z_workers2`. Historical paths inside raw records remain as provenance; locate results through this directory and the portable snapshot. Source files, frozen contracts, and raw solver traces remain local and ignored.
