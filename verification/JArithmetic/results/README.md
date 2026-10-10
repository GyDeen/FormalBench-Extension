# JArithmetic validation results

The Frama-C/WP support run completed. **Seven of eight helper implementations
proved their selected contracts.** The multiplication postcondition remains
unresolved at the 10-second per-goal budget. This was a deductive verification
run; no Java execution or generated test inputs were used.

**Recorded experiment only: JArithmetic is not used as a trusted supporting
library or integrated into the benchmark evaluator.** Total elapsed time across
the five targets was 37.293 seconds. The runner exited with status 1 because
the helper implementation proof was incomplete.

## Helper implementation breakdown

| Helper | C originals using it | Proved WP goals | Reported WP goals | Status |
|---|---:|---:|---:|---|
| `java_add` | 37 | 2 | 2 | Proved |
| `java_sub` | 27 | 2 | 2 | Proved |
| `java_mul` | 20 | 1 | 2 | Unresolved postcondition |
| `java_div` | 4 | 5 | 5 | Proved, assuming nonzero divisor |
| `java_abs` | 1 | 3 | 3 | Proved |
| `java_min` | 4 | 3 | 3 | Proved |
| `java_max` | 4 | 3 | 3 | Proved |
| `java_shl` | 1 | 3 | 3 | Proved |
| **Total obligations** | | **22** | **23** | **One unresolved** |

Preparation found 45 of the 50 C originals using at least one helper. Every
occurrence of each helper name matched the same executable-token body. Counts
above are individual obligations in the WP JSON report; the console additionally
counts automatic terminating and unreachable-path simplifications.

The unresolved goal is `typed_ref_java_mul_ensures`, corresponding to
`result == (int32_t)((integer)left * right)`. Its report records an Alt-Ergo
timeout, not an explicit refutation. The helper's no-write obligation proved.

## Contract-interface clients and controls

| Target | Proved goals | Reported goals | Outcome |
|---|---:|---:|---|
| Wrapping and symbolic arithmetic clients | 18 | 18 | Proved |
| Division clients | 15 | 15 | Proved |
| Absolute value, min/max and shift clients | 9 | 9 | Proved |
| **Positive client total** | **42** | **42** | **Proved under fixed contracts** |
| Negative controls | 4 | 6 | Both intended obligations remained unproved |

The negative obligations are an incorrect no-wrap assertion and a zero-divisor
call violating `java_div`'s precondition. Both timed out; this confirms only that
these invalid claims were not proved. They are not reported as counterexamples
or completeness detections.

Client proofs assume the declared helper contracts. They do not independently
prove the helper implementations. These conditional results are retained as
experimental evidence; the multiplication contract is not adopted as a trusted
assumption in the benchmark evaluation. No helper obligations are removed and
no unresolved benchmark originals are reclassified on the basis of this run.

## Recorded configuration and evidence

Frama-C 33.0 (Arsenic), `x86_64`, `Typed+ref`, configured provers
Alt-Ergo 2.4.3 and Z3 4.8.12, 10 seconds per goal, 300 seconds per target,
1000 MB per solver and two parallel prover processes. The existing JArray
Why3 configuration was used without modification.

Frama-C reported generic skipped guards for alignment and function pointers;
these scalar helpers contain no pointer access or indirect call. Declaration-only
clients generated default exits/terminates clauses, and clients without their
own frame contracts emitted missing-assigns warnings. These warnings remain in
the saved logs; the reported client result concerns the selected normal-result
obligations, not a separate total-correctness claim.

[Machine-readable overview](summary.json) | [Exact run configuration](local/wp/run.json)
| [Implementation record](local/wp/implementation/record.json)
| [Multiplication WP report](local/wp/implementation/wp-report.json)
| [All target records](local/wp/summary.json)

Raw proof reports and logs are retained locally under the ignored `local/wp/`
directory. These support checks do not change the recorded benchmark consistency
or completeness results.
