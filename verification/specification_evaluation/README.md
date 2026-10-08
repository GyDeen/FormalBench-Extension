# Specification evaluation

Original **consistency** uses OpenJML ESC for Java and Frama-C WP for C.
Primary mutant **completeness** now uses execution of the unchanged frozen
contracts with saved EvoSuite first calls and the shared bounded-input procedure.
The measure is **runtime contract postcondition mutant-detection rate**.

| Package | Responsibility |
|---|---|
| `__main__.py`, `workflow.py`, `manifest.py` | Population, frozen contracts, original verification and ordered stages |
| `specifications/` | Token-preserving specification freezing and attachment |
| `backends/` | Original OpenJML/Frama-C consistency checks and compatibility assets |
| `execution/completeness.py` | Primary Java/C runtime evaluation and combined summary |
| `execution/java_completeness.py`, `java_rac.py`, `java_inputs.py` | JML RAC, original controls and independently reproduced Java witnesses |
| `execution/c_completeness.py`, `c_contracts.py`, `c_native.py` | Frozen ACSL executable checks and native sanitizer replay |
| `execution/inputs.py` | Shared seed-726 bounded-input recipe |
| `reporting/` | Original consistency and runtime result reporting |
| `tests/` | Unit checks without launching external processes |
| `results/verifier_based/` | Final verifier completeness, eligible original proofs and frozen contracts |
| `results/execution_based/` | Final primary Java and C execution completeness |

No content digests are generated, stored or validated by the active evaluation
pipeline. Resume uses completed case records and their policy identifiers.
Frozen annotation text and executable-token equality remain checked directly;
these checks protect contract transfer and do not compute digests. Use a fresh
output directory when changing frozen contracts, input suites or experiment settings.

## Primary workflow

Freeze original specifications and verify originals using the existing ordered CLI:

```bash
python3 -m verification.specification_evaluation run --stage prepare \
  --java-specs java_specs --c-specs c_specs --output results/study
python3 -m verification.specification_evaluation run --stage originals \
  --openjml /root/tools/openjml/openjml --frama-c frama-c \
  --java-prover z3-4.3.X --output verification/specification_evaluation/results/study
python3 -m verification.specification_evaluation run --stage mutants \
  --java-workers 4 --c-workers 4 --output verification/specification_evaluation/results/study
```

The mutant stage runs execution checks, and
selects only programs whose corresponding original verification record is `proved`.
It writes `mutant_detection/java/` and `mutant_detection/c/` beneath that study.
Each language's verified cohort is evaluated separately; jointly verified
programs form a distinct matched subset.

For an existing consistency study, use a separate runtime result directory:

```bash
python3 -m verification.specification_evaluation.execution.completeness \
  --study verification/specification_evaluation/results/verifier_based \
  --output verification/specification_evaluation/results/execution_based \
  --workers 4
```

Add `--language java` or `--language c` to select one language and repeat
`--program NAME` for a smaller cohort. Resource limits and installed versions
are recorded in the execution results. Runtime tools currently use the installed
OpenJML/JDK under `/root/tools/openjml` and GCC on PATH. The preserved study uses
the fixed 50 originals and 977 screened Java/C mutant pairs; no new mutants are generated.

## Detection policy

Inputs come from saved EvoSuite JSON **first calls**, followed by the C
search's bounded recipe. Scalar tuples cover {-2,-1,0,1,2,3,4,7}, followed by
120 seeded tuples including int boundaries; OddBitSetNumber also gets single-bit
inputs. Array/matrix candidates retain the same bounded recipe. Duplicates are
removed. No solver models or EvoSuite assertions are used as the oracle.

Entry preconditions and input types are checked before a trial contributes
evidence. C resource exclusions are counted separately. Current verified Java
contracts have primitive-int inputs and no explicit `requires`; a new nontrivial
precondition fails visibly until its evaluator is validated.

A primary detection requires an admitted input, a frozen postcondition failure,
an independent replay reproducing it, and an original passing the same contract
checks. Java uses RAC with a fresh classloader per input and a fresh interpreted
JVM for replay. C uses its documented executable equivalents of the frozen ACSL
return-value/content postconditions, O0/UBSan search and O1/ASan+UBSan replay.
The original's output and mutation-screening assertions are not detection oracles.

Safety, frame and other clause failures are recorded separately. A mutant with
both a postcondition violation and a safety failure counts once in the primary
numerator. Search stops at its first validated postcondition witness; the other
failure flags are not exhaustive after that point. Per-input timeout is 0.5 s,
independent replay timeout is 2 s, and three timeouts stop a case.

Unsupported clauses stay in the frozen specification and remain visible.
The full `FindPoints` contract cannot compile under installed OpenJML 21.0.27
RAC because of `\fresh`; the main evaluation uses the explicitly recorded
runtime omission described below and reports FindPoints separately.
A deliberately forbidden frame write also goes undetected in the RAC
control, so this configuration does not establish `assignable` coverage. C loop
invariants, variants, statement assertions and full write/allocation sets remain
unmonitored. Runtime passes do not establish these unchecked clauses.

An explicit Java runtime adaptation can omit the known `\fresh(\result)`
conjunct from **both** original and mutant copies. The non-null, length and
content postconditions remain; full frozen JML stays intact and the omitted
expression is recorded as unchecked. Use a separate working directory outside the final result tree:

```bash
python3 -m verification.specification_evaluation.execution.java_completeness \
  --program FindPoints --omit-fresh --workers 4 \
  --output output/runtime_contract_java_omit_fresh_pilot
```

## Current evidence

[Primary runtime results](results/execution_based/README.md) contain
the completed Java verified cohort and the revalidated archived C execution
cohort. The C evidence was imported without another C experiment. Every import
was checked against the current frozen contract and its saved independently
reproduced observations.

The [final verifier completeness](results/verifier_based/README.md) retains
Java ESC diagnostics and validated C WP model evidence for the verified cohorts,
alongside their original proof records and frozen contracts. Intermediate runs,
pilots and duplicate results are archived under
`output/intermediate_completeness_results_20261008/`. Verifier evidence never
contributes to the primary runtime counts. The earlier C execution archive remains under
`output/c_mutant_results_withdrawn_20261008T004534/`.
