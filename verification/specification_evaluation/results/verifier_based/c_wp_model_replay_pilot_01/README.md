# C verifier-model execution validation

Primary detection requires an admitted verifier-generated input to reproduce a supported frozen specification violation at O0/UBSan and O1/ASan+UBSan. Safety-only failures never count. Raw WP evidence is preserved in the source run.

Only full displayed scalar parameter assignments are replayed. Missing values are never supplied. Heap models and uninstrumented loop/assertion/write-set clauses remain unsupported. Successful finite executions are not proofs.

| Cohort | Eligible | Recorded | Specification detections | Safety | Both |
| --- | ---: | ---: | ---: | ---: | ---: |
| All mutants | 977 | 80 | 4 | 0 | 0 |
| Verified originals | 80 | 80 | 4 | 0 | 0 |

[Per-mutant counts](mutants.csv); [summary and coverage](summary.json).
