# Fresh C cohort comparison

The same 80 mutants were evaluated. Verification settings match: **True**.

| Evidence | Earlier | Fresh |
| --- | ---: | ---: |
| WP-reported specification detections | 6/80 | 11/80 |
| Execution-validated specification detections | 4/80 | 7/80 |
| Fresh validated safety failures (excluded from completeness) | ¡ª | 0/80 |

| Program | Mutants | Earlier WP | Fresh WP | Earlier validated | Fresh validated |
| --- | ---: | ---: | ---: | ---: | ---: |
| CountList | 3 | 0 | 0 | 0 | 0 |
| MaxOfTwo | 2 | 0 | 0 | 0 | 0 |
| MaxSubArraySum | 23 | 0 | 0 | 0 | 0 |
| OddBitSetNumber | 17 | 6 | 11 | 4 | 7 |
| SumNums | 13 | 0 | 0 | 0 | 0 |
| TestThreeEqual | 22 | 0 | 0 | 0 | 0 |

Only full WP model parameter assignments were replayed. No EvoSuite or independently generated inputs were used. Safety-only outcomes never count as completeness detections. Loop instrumentation and complete frame checking remain unsupported; a passing replay does not establish specification validity.

[Comparison data](comparison.json); [fresh replay results](summary.json).
