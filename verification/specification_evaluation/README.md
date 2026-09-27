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
ambiguous transfer produces a `syntax/tool failure` record; it is never counted
as a rejected mutant.

C declaration renames are recorded as explicit bindings. A removed adjacent
scalar loop-bound temporary may be expanded using its **original** pure
initializer, with Java wrapping arithmetic preserved. Its dependencies must
remain in scope, stable throughout the loop, and free of pointer escapes. The
mutant's changed bound is never substituted into the specification.

When a leaf loop is deleted, retained loop headers must match uniquely, in
order and with the same nesting, and the deleted loop must have an identifiable
empty-statement replacement. Only that loop's annotations are omitted, with an
audit entry and `internal_annotation_coverage: partial_due_to_deleted_loop`.
Function contracts and surviving assertions remain attached. This case does
not claim verification of every original internal annotation. An assertion
before a deleted call is retained before its unambiguous empty-statement
replacement. Ambiguous structural changes still fail transfer.

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

## Retrying failures and short diagnostics

`run --retry-tool-failures --language c` retries each selected failure once,
including annotation-transfer failures and interrupted attempts. Prior
attempts are archived and source/specification/support/settings hashes must
still match. Missing current records from older interrupted retries are
recovered using their archived records. New attempts have an explicit running,
interrupted, or complete status; an interruption refreshes the summary.
`failure_stage` distinguishes annotation transfer, parsing/typechecking,
prover invocation, report errors, and other verifier errors.

Use a fresh diagnostic directory for changed budgets; do not mix shorter runs
into the study's 30-second goal / 600-second case results:

```bash
python3 -m verification.specification_evaluation.diagnose_c_repairs \
  --study verification/specification_evaluation/results/pilot10_seed726_20260925_budget600_goal30 \
  --output verification/specification_evaluation/results/c_repair_diagnostic \
  --case MaxVolume/5 --case MaxVolume/14 \
  --case SumOfPrimes/5 --case SumOfPrimes/9 --case ParabolaVertex/2 \
  --goal-timeout 5 --timeout 60
```

This checks transfer across the current 10-program C population, generates WP
tasks without proving them for the selected cases, then runs those cases with
the shorter budgets. It saves provenance and a progressive diagnostic summary.
Passing task generation or eliminating tool errors is not a mutant rejection;
unresolved proof goals remain `unknown/timeout`.

## Java compatibility repairs

For OpenJML **21.0.27**, verifier discovery builds a separate compiler plugin
under `.tools/openjml-compat`. The installed tool, raw programs, and frozen
JSON specifications are not edited. Case records fingerprint the compiler,
plugin source/archive, adapter code, and selected solver.

- Numeric `\count` occurrences in staged JML comments are rendered as
  `(\count + 0)` to avoid the compiler crash on conditional-expression arms.
  Every replacement is recorded in `transfer.json` and `record.json`.
- An assertion preceding a qualified call replaced by a single empty
  statement is retained at that statement, using both neighboring anchors.
  Ambiguous replacements are rejected.
- When using the `z3-4.3.X` driver, the command explicitly selects the bundled
  **Z3 4.10.2** executable to avoid the observed broken-pipe failures. This is
  a solver configuration change and is recorded in verifier provenance.
- After Java type and flow checking, the plugin simplifies only primitive
  `int` local/parameter predicates `(x ^ 1) == 0` to `x == 1` and
  `(x | 1) == 0` to `1 == 0`. This avoids the broken combination of bitvectors
  and mathematical integers for these cases. Fields, calls, boxed values,
  arrays, other constants, and JML clauses are excluded. Source files retain
  every executable token; applied compiler rules are recorded in each result.
  `openjml/predicate_equivalence.smt2` proves both identities for all 32-bit
  inputs and retains a witness distinguishing the mutants from the original
  AND predicate. This is not a general repair for mixed bitvector/bigint proofs.

Progress summaries containing `Error: 0` are not tool errors. Conversely,
`Not implemented for static checking` is an `unsupported_specification`
failure even if the tool later times out. `CountingSort` currently reaches
this limitation for `\num_of` after its compiler crash is repaired. Such
results cannot contribute to specification-rejection scores. An explicit
unknown-validity/no-model diagnostic also takes precedence over preceding
unproved-assertion warnings.

Recheck the study's recorded Java tool failures with short budgets:

```bash
python3 -m verification.specification_evaluation.diagnose_java_repairs \
  --study verification/specification_evaluation/results/pilot10_seed726_20260925_budget600_goal30 \
  --output verification/specification_evaluation/results/java_repair_diagnostic \
  --goal-timeout 5 --timeout 60
```

Use a fresh output directory. The main study's records and 30-second goal /
600-second case budgets remain separate from these diagnostics. Regression
checks, including the compiler plugin integration checks in WSL, are:

```bash
python3 -B -m unittest \
  verification.specification_evaluation.test_java_repairs \
  verification.specification_evaluation.test_java_plugin \
  verification.specification_evaluation.test_c_repairs -v
```
