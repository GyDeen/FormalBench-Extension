# Saved evaluation results

| Result set | Method | Use |
|---|---|---|
| [Verifier-based results](verifier_based/README.md) | OpenJML ESC and Frama-C WP, with recorded solver diagnostics | Original consistency and historical verifier-mutant evidence |
| [Execution-based completeness](execution_based/README.md) | Runtime checks of admitted EvoSuite and bounded inputs against supported frozen postconditions | Primary mutant completeness |

These result sets are separate. Verifier outcomes and solver-model diagnostics do not contribute to the runtime detection numerator. Safety failures are recorded separately.

The main runtime result is Java 192/192 and C 79/80. FindPoints has its own row marking freshness unchecked for original and mutants. Full frozen specifications and verifier evidence remain preserved.

[Result manifest](result_manifest.json)
