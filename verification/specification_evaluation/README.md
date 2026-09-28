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
each original or mutant, it attaches annotations derived from those frozen comments to untouched source,
uses token and function/loop anchors rather than line numbers, and checks that
the resulting file retains every executable source token. An
ambiguous transfer produces a `syntax/tool failure` record with
`failure_stage: annotation_transfer`; it is never counted as a rejected mutant.

C declaration renames are recorded as explicit bindings. A removed adjacent
scalar loop-bound temporary may be expanded using its **original** pure
initializer, with Java wrapping arithmetic preserved. Its dependencies must
remain in scope, stable throughout the loop, and free of pointer escapes. The
mutant's changed bound is never substituted into the specification. This
includes an original `jarray2_length` or `jdouble_array2_length` temporary
only when the array's length remains stable. Rename the declaration and its
uses when the mapping is unique; do not rewrite a mutation that changes one
variable use to a different variable. Ambiguous scope, capture, snapshot, or
dependency mappings remain transfer failures.

For either language, when a `for` or `while` leaf loop is unambiguously deleted, retained loop
headers must map uniquely with the same nesting and the deleted loop must
have an identifiable empty-statement replacement before a preserved following
sibling. Only that loop's annotations
are omitted, with an audit entry and
`internal_annotation_coverage: partial_due_to_deleted_loop`. Function
contracts and surviving assertions remain attached and are verified. An
assertion before a deleted call is retained before its unambiguous
empty-statement replacement. If another annotation lies in the deleted loop
body or at the removed loop anchor and cannot be retained, transfer fails.
Ambiguous structural changes still fail transfer.
Deleted `do` loops remain transfer failures until they have a separately
audited correspondence rule.
The omitted loop annotation is a structural attachment-loss **candidate**; it
is not itself evidence that the retained specification rejected the mutant.

A frozen contract referring to `__fc_stdout` retains the Frama-C stdio model
through `-cpp-extra-args=-include stdio.h`, including mutants that remove printf
and its include. This supplies declarations without editing the mutant source.

Every translated C **program** case gets a frozen copy of the fixed JArray
ACSL headers, generated array types, and a local `java_arrays.h` shim. Frama-C
runs WP and RTE on that FormalBench-data C program with
callee precondition goals enabled, initialization filtering disabled, and a
machine-readable report. `proved` requires every reported WP goal to pass;
timed-out or otherwise unresolved `requires` goals remain `unknown/timeout`.
An explicit invalid precondition or RTE goal is recorded separately from a
specification violation. OpenJML proof-failure warnings are recorded as Java
verification failures, with precondition and runtime-safety warnings separated.

## Evidence categories and scores

The unit of scoring is one selected mutant in one language, paired with that
language's original. A parser or annotation-placement failure is never a
behavioral rejection. Use these evidence categories:

| Evidence | Record and treatment |
| --- | --- |
| Specification rejection | A valid transfer reaches the verifier, which rejects an applicable generated specification obligation. `outcome: specification violation`; count in the **primary** rate only if the original proved. |
| Structural rejection | Audited, unambiguous removal of an annotated loop's attachment point, after ruling out renaming/translation differences. Count separately from specification rejection; both may apply to one mutant. Reduced internal coverage is recorded. |
| Safety rejection | A runtime-safety or callee-precondition obligation fails under the original input assumptions. `outcome: precondition/RTE failure`; count separately. |
| Transfer/tool failure | Annotation mapping, unsupported specification syntax, configuration, solver invocation, or verifier implementation prevents meaningful checking. Use `failure_stage` to separate transfer, unsupported specification, and other tool failures; never count as detection. |
| Unknown/timeout | Neither acceptance nor rejection is established within the budget. Keep unresolved in the eligible denominator. |
| Invalid mutant | Ordinary compilation/typechecking of the **unannotated** mutant fails independently of the annotation transfer. Report/exclude it; a verifier parse failure alone does not establish this category. |
