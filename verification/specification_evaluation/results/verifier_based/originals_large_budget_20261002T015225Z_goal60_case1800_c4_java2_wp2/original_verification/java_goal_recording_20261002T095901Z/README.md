# Completed larger-budget original verification

All 100 original verification runs completed successfully as a batch. Elapsed batch time: approximately 82 minutes. Limits: 30 minutes per program and 60 seconds per solver call; four C workers with two WP jobs each, plus two Java workers.

| Language | Originals | Proved | Unknown/timeout | Safety diagnostic | Specification violation | Tool failure |
| --- | ---: | ---: | ---: | ---: | ---: | ---: |
| C | 50 | 6 | 44 | 0 | 0 | 0 |
| Java | 50 | 13 | 36 | 1 | 0 | 0 |

No additional original programs proved compared with the baseline. All 100 raw-source, frozen-specification and annotated-source hashes match the baseline.

Three previously unresolved C obligations proved: one in CountingSort and two in CountWays. Both programs still have unresolved obligations. MinCost reached the 30-minute program limit; its new case record has no classified goal coverage, so zero recorded unknown goals must not be interpreted as all goals proved.

Java ParallelogramPerimeter and TriangleArea changed from baseline specification-violation classifications to unknown/no-model diagnostics. This is not proof of correctness. The Java safety diagnostic remains separate from proved or unknown results.

Summed verifier-process time: C 307.3 minutes; Java 6.1 minutes. These are cumulative process times, not elapsed batch time.

See [case comparison](comparison.json), [final job status](status.json), and [run configuration](launch.json). This run evaluated originals only; the pipeline summary's 977 not-run mutants do not mean the originals batch is unfinished.

## Reproducing the run

Copy this run's frozen_specs directory into a fresh output directory, then use the command and environment recorded in [launch.json](launch.json). Use a new --output path. The settings and verifier fingerprints are also retained in [run.json](run.json).

[originals_summary.json](originals_summary.json) records completion of the 100-original stage. Canonical executable sources remain under FormalBench-data. Case records embed commands, annotation placement and diagnostics; generated source copies, headers and logs are kept locally.
