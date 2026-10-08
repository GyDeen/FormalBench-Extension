# C verifier-only mutant detection

Completeness counts a mutant once when WP reports a specification-goal counterexample or explicitly refutes a specification obligation. Safety/precondition failures and unclassified counterexamples are recorded separately and excluded from the completeness numerator. A mutant with both specification and safety evidence counts once in completeness, with the overlap retained. WP proof outcomes remain separate; no execution inputs are generated or replayed.

| Cohort | Eligible mutants | Recorded | Completeness detections | Specification | Safety | Both | Completeness rate |
| --- | ---: | ---: | ---: | ---: | ---: | ---: | ---: |
| All mutants | 977 | 80 | 11 | 11 | 0 | 0 | 1.13% |
| Verified originals | 80 | 80 | 11 | 11 | 0 | 0 | 13.75% |

Unknown, timeout and failed proof attempts without a reported counterexample/refutation do not count as detections. Missing or historical records without the current detection policy remain not run/not recorded. Models are verifier evidence, not execution-validated witnesses.

Per-mutant rows: [mutants.csv](mutants.csv). Full goal evidence and program-level counts: [summary.json](summary.json).
