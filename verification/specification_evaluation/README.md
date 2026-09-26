# Paired specification evaluation

The evaluation targets are the 50 selected **FormalBench-data originals** and
their 977 retained, behaviour-changing **Java/C mutant pairs**. The adapter
reads `fault_mutants/run_ukcw__uc/selection_manifest.json` and never generates
new mutants. JArray is only the fixed library interface assumed by translated
C programs; its implementation and validation clients are **not** evaluated
as study programs, mutants, or entries in the paired score.

## Inputs

Supply one annotated original per selected program and language:

```
java_specs/<Class>.java    JML comments in the original Java source
c_specs/<Class>.c          ACSL comments in the original translated C source
```

The same LLM and report prompt should independently annotate each Java
original and its C translation. The generator must see only the target-language
original, not its counterpart or any mutant. For C it may see the relevant
read-only JArray declarations and contracts. The adapter cannot enforce model
identity or prompt context; retain that generation provenance alongside the
output. The annotations must leave executable source tokens unchanged. During
`prepare`, the adapter extracts them into one frozen JSON file per program and
language, for example `frozen_specs/java/CombSort.json`. An
external generator can instead be given through `--java-generator` and
`--c-generator`; each is an argument template containing `{source}` and
`{output}`. The command is run without a shell, and its output is validated
before extraction. This permits use of FormalBench's specification-generation
workflow without coupling the verifier to a particular model or credentials.

Each JSON file stores the program and language, the original executable-token
hash, and an ordered `annotations` array. Every entry records its exact JML or
ACSL `text`, `target` (`function`, `loop`, or `statement`), owning `function`
when identifiable, a `loop_id` for loop annotations, and token-context anchors.
For example:

```json
{
  "schema_version": "1.0",
  "program": "CombSort",
  "language": "java",
  "original_executable_token_sha256": "...",
  "annotations": [
    {
      "id": "annotation_1",
      "target": "function",
      "function": "combSort",
      "loop_id": null,
      "boundary_token": 3,
      "next_token": "int",
      "following_tokens": ["int", "["],
      "text": "//@ ensures ...;"
    }
  ]
}
```

The token fields above illustrate the format; actual anchors come from the
original source. JSON becomes the authoritative specification after `prepare`.
Later stages need only the same output directory, not the annotated-source
directories or generator.

From the repository root, prepare and freeze all original specifications first:

```bash
python3 -m verification.specification_evaluation run \
  --stage prepare \
  --manifest FormalBench-data/FilteredData/fault_mutants/run_ukcw__uc/selection_manifest.json \
  --java-specs java_specs --c-specs c_specs \
  --output verification/specification_evaluation/results/study_01
```

Dry-run placement of that same frozen JSON on every selected original and
mutant, without invoking a verifier:

```bash
python3 -m verification.specification_evaluation check \
  --output verification/specification_evaluation/results/study_01
```

Before preparation, `check --java-specs java_specs --c-specs c_specs` can also
validate the annotated inputs; it extracts the same JSON structure in memory.

Next, attach the frozen JSON annotations to untouched originals and verify
them, then attach the same annotation text to eligible mutants. Both commands
use the same output directory and verifier
settings:

```bash
python3 -m verification.specification_evaluation run --stage originals \
  --output verification/specification_evaluation/results/study_01
python3 -m verification.specification_evaluation run --stage mutants \
  --output verification/specification_evaluation/results/study_01
```

`--stage all` combines the three phases. Use `--program CombSort --max-pairs 2`
for a pilot, then rerun with the same output directory to complete the population.
Missing OpenJML, Frama-C, or
provers must be installed/configured before a real run. Defaults use OpenJML
with CVC4 and Frama-C WP with the JArray study's `x86_64`, `Typed+ref`,
Alt-Ergo/Z3, per-goal time, and memory settings. Pass the compatible Why3
configuration with `--why3-extra-config` when required by the installed
Frama-C/Why3 versions.

## Transfer and verification rules

JML/ACSL comments are the only specification text transferred. The adapter
first checks that the supplied annotated original has the same executable
tokens as its raw original, then extracts the comments once into JSON. For
each original or mutant, it attaches those frozen comments to untouched source,
uses token and function/loop anchors rather than line numbers, and checks that
the resulting file retains every executable source token. An
ambiguous transfer produces a `syntax/tool failure` record; it is never counted
as a rejected mutant.

Every translated C **program** case gets a frozen copy of the fixed JArray
ACSL headers, generated array types, and a local `java_arrays.h` shim. Frama-C
runs WP and RTE on that FormalBench-data C program with
callee precondition goals enabled, initialization filtering disabled, and a
machine-readable report. `proved` requires every reported WP goal to pass;
timed-out or otherwise unresolved `requires` goals remain `unknown/timeout`.
An explicit invalid precondition or RTE goal is recorded separately from a
specification violation. OpenJML proof-failure warnings are recorded as Java
verification failures, with precondition and runtime-safety warnings separated.

The run directory contains frozen JSON specs, an exact command and logs per case,
source/contract/tool hashes, per-goal WP results, one `record.json` for every
processed original and mutant language, and `summary.json`. Originals are
verified first to measure consistency. Every selected mutant is then evaluated
whether or not its corresponding original proved, provided the frozen
annotations can be transferred safely. Original outcomes are retained as
strata in the summary so mutant detection rates can be compared across
consistency outcomes. Existing completed records are reused only when their
input fingerprints still match; prior `not run` records are retried. The
summary compares Java and C for the documented eligible IDs;
tool failures, support failures, unknowns, and unfinished pairs never inflate
the mutant-rejection numerator. It reports the detected fraction of all 977
eligible pairs separately from the conditional fraction of resolved cases;
unknowns remain visible in the eligible denominator. The 275 excluded pairs in the selection
manifest are never scheduled: 48 failed cross-language agreement and 227 had
no observed difference from their originals (including 21 sanitizer-supported
stack-overflow pairs).
