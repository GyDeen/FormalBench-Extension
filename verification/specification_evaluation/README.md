# Specification evaluation

Original consistency uses OpenJML ESC for Java and Frama-C WP for C. The primary completeness measurement is now **strict rejection of every differing returned mutant output across the fixed input pool**.

For each admitted input, compare the original and mutant normal return values (arrays by contents). A mutant passes only when the whole input pool has been examined, at least one returned output differs, and the executable frozen postcondition rejects every differing output. One accepted faulty output fails the mutant. Missing trials or unresolved checks leave it incomplete. Mutants with no differing normal return are reported separately.

\[
C_{\mathrm{strict}} = \frac{N_{\mathrm{passed\ mutants}}}{N_{\mathrm{passed\ mutants}} + N_{\mathrm{failed\ mutants}}}.
\]

The strict rate is withheld while any mutant remains incomplete. Safety failures are separate and do not count as differing normal returns or strict passes. Per-input rejection fractions and first-witness detection counts are secondary diagnostics.

## Current results

The [completed strict evaluation](STRICT_COMPLETENESS_RESULTS_20261026.md) covers all 341,386 mutant/input pairs: 192 Java strict passes; 79 C strict passes and one C safety-only case. All 120,662 differing Java returns and all 31,385 differing C returns were rejected. There are no accepted faulty returns or unresolved checks.

Historical first-witness results under `results/execution_based/` and verifier diagnostics under `results/verifier_based/` remain historical evidence. Their mutant-detection scores do not define the current completeness measurement. Only the final Markdown report and runnable scripts are versioned for this strict run; full new execution records remain local.

## Primary workflow

The existing ordered CLI still freezes specifications and verifies originals:

```sh
python3 -m verification.specification_evaluation run --stage prepare \
  --java-specs java_specs --c-specs c_specs --output results/study
python3 -m verification.specification_evaluation run --stage originals \
  --openjml /root/tools/openjml/openjml --frama-c frama-c \
  --java-prover z3-4.3.X --output results/study
python3 -m verification.specification_evaluation run --stage mutants \
  --java-workers 4 --c-workers 4 --output results/study
```

The mutant stage now always runs exhaustive collection followed by strict reconstruction. It writes the primary summary, Markdown report and per-input rows under `mutant_detection/`, with raw trials under the separate sibling `mutant_detection_runtime/`. Workflow completion includes full input coverage when runtime completeness is present.

For the retained verified-original study:

```sh
OPENJML_HOME=/path/to/openjml python3 -m verification.specification_evaluation.execution.completeness \
  --study verification/specification_evaluation/results/verifier_based \
  --output output/strict_completeness \
  --workers 4
```

`--runtime-output PATH` overrides the default sibling `OUTPUT_runtime` directory. Add `--language java` or `--language c`, or repeat `--program NAME`, to select a smaller cohort. Choose fresh directories when changing contracts, inputs or settings. Historical result directories are protected from overwrite by the primary entry point.

The independently usable reconstruction command remains available for existing raw evidence:

```sh
python3 -m verification.specification_evaluation.execution.input_completeness \
  --source output/strict_completeness_runtime \
  --output output/strict_completeness_reconstructed
```

Completed exhaustive case records are reused. Interrupted cases lacking final records rerun from their first input. First-witness cache records have a different policy and cannot substitute for exhaustive collection. The per-language collector summaries retain existential detection counts as diagnostics; the primary `summary.json` contains strict per-mutant verdicts.

## Inputs and executable coverage

Only mutants belonging to fully verified originals enter each language's cohort. The retained study contains 50 originals and 977 screened Java/C mutant pairs; this evaluation generates no new mutants. Verified cohorts differ between languages.

Saved EvoSuite first calls precede the shared seed-726 bounded-input recipe. Preconditions and input types are checked; duplicate inputs and resource exclusions are recorded. All selected programs in this run have no omitted multi-call saved tests. Original controls must pass the full pool. Output differences identify faults relative to the original; the frozen executable specification decides rejection. EvoSuite assertions and solver models are not rejection oracles.

Java uses OpenJML 21.0.27 RAC, fresh classloaders per input and separate interpreted replay processes per mutant case. C uses executable equivalents of frozen ACSL return/content clauses, O0/UBSan search and O1/ASan+UBSan replay. Exhaustive C search/replay limits are two/five seconds; Java search/replay limits are half a second/two seconds. Timeouts remain visible and do not stop exhaustive mutant collection after three occurrences. A failed original control still blocks its mutants.

Set `OPENJML_HOME` to an existing installation directory; the default is `/root/tools/openjml`. A C compiler with the recorded sanitizer support is required. Runtime commands and settings are retained in generated evidence.

Java FindPoints freshness is explicitly unchecked symmetrically for original and mutants; full frozen JML remains unchanged. Non-nullness, length and content clauses remain checked. Java frame coverage is unconfirmed. C loop invariants, variants, assertions and full write/allocation sets remain unchecked. These finite-input results do not establish universal completeness or full-clause coverage.

No content digests are generated or validated. Frozen contracts and source transfer are checked directly; resume uses completed case records and policy identifiers.

## Code and checks

| Module | Responsibility |
|---|---|
| `workflow.py`, `manifest.py`, `specifications/`, `backends/` | Population, frozen contracts and original consistency |
| `execution/completeness.py` | Default exhaustive execution and primary strict result |
| `execution/input_completeness.py` | Per-input comparison, strict verdicts and reconstructed reports |
| `execution/java_completeness.py`, `java_rac.py`, `java_inputs.py` | Java execution, original controls and replay |
| `execution/c_completeness.py`, `c_contracts.py`, `c_native.py` | C executable predicates and sanitized replay |
| `reporting/` | Original consistency and links to strict completeness |

```sh
python3 -m unittest verification.specification_evaluation.execution.input_completeness_checks
```
