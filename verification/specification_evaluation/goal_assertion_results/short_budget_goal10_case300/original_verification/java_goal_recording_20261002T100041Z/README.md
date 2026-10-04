# Instrumented Java original records

This directory retains the `java_goal_recording_20261002T100041Z` attempt. The [analysis report](../../README.md#java-recording-limitations) contains the results and explains how this attempt differs from the authoritative program-level run.

- `instrumented_cases/<program>/record.json`: each original's saved instrumented outcome and capture metadata.
- `previous_cases/<program>/record.json`: the preserved earlier outcome.
- [Workload snapshot](java_workload_snapshot.json): saved assertion-count data and provenance.
- [Completion audit](completion_audit.json) and [final report audit](final_report_audit.json).

For assertion tables and the original/mutant population definitions, use the [assertion analysis](../../README.md#short-budget-goalassertion-kinds-and-status). Historical summary JSON files in this folder belong to their saved attempts; the analysis report identifies the authoritative results.
