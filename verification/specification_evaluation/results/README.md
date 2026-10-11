# Final completeness results

Only the final mutant results for language-specific fully verified originals are active here. Safety failures are excluded from both specification-detection numerators.

| Method | Java mean across programs | C mean across programs | Java pooled detections | C pooled detections | Summary |
|---|---:|---:|---:|---:|---|
| Verifier-origin detection | 62.14% | 6.86% | 121/192 (63.02%) | 7/80 (8.75%) | [Verifier outcome breakdown](verifier_based/README.md#outcome-breakdown) |
| Execution-based postcondition detection | 100.00% | 99.28% | 192/192 (100.00%) | 79/80 (98.75%) | [Execution README](execution_based/README.md) |

The primary aggregation is the mean across programs, giving each of the 13 Java or 6 C eligible programs equal weight: $C = \frac{1}{P}\sum_{i=1}^{P}\frac{D_i}{M_i}$, where $D_i$ is the number of detections and $M_i$ is the number of eligible or runtime-evaluated mutants for program $i$. Pooled detections are descriptive. Each method's README defines its detection policy, supported clauses and per-program results. The execution result reports FindPoints separately because freshness is unchecked in both original and mutant runtime copies.

Each method has one consolidated result, with its final case evidence and summary. Pilots, intermediate runs, duplicate adaptation results and full-population diagnostics are retained locally under output/intermediate_completeness_results_20261008/, outside this result tree. No experiments were rerun during consolidation. Historical commands retain their original paths.
