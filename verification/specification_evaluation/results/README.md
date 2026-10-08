# Final completeness results

Only the final mutant results for language-specific fully verified originals are active here. Safety failures are excluded from both specification-detection numerators.

| Method | Java | C | Summary |
|---|---:|---:|---|
| Verifier-origin detection | 121/192 (63.02%) | 7/80 (8.75%) | [Verifier outcome breakdown](verifier_based/README.md#outcome-breakdown) |
| Execution-based postcondition detection | 192/192 (100.00%) | 79/80 (98.75%) | [Execution README](execution_based/README.md) |

These are pooled descriptive rates. Each method's README defines its detection policy, program-level aggregation, supported clauses and per-program results. The execution result reports FindPoints separately because freshness is unchecked in both original and mutant runtime copies.

Each method has one consolidated result, with its final case evidence and summary. Pilots, intermediate runs, duplicate adaptation results and full-population diagnostics are retained locally under output/intermediate_completeness_results_20261008/, outside this result tree. No experiments were rerun during consolidation. Historical commands retain their original paths.