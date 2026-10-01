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
Use `--independent-languages` to give Java and C separate mutant queues; each
language starts its next pair as soon as its own verifier finishes. This runs
one C case and one Java case at a time, concurrently. Frama-C's `--wp-par`
continues to control parallel proof goals within that C case.
Add `--c-workers 2` to run two C mutant cases concurrently from a shared queue.
Workers claim distinct cases and immediately take the next case when finished;
Java retains its own worker. This also works for a C-only run. The default is
one C worker. With `--wp-par 4`, two C workers allow up to eight concurrent
proof jobs. Worker counts may change on resume without changing verifier
budgets or frozen inputs; prior scheduling metadata is archived. Stop the old
runner before resuming into the same output directory. Completed case records
are validated and reused. `--java-workers 2` similarly gives Java two workers,
including in a Java-only run. Worker limits also apply to original verification;
all selected originals finish before the mutant stage starts.
Missing OpenJML, Frama-C, or
provers must be installed/configured before a real run. Defaults use OpenJML
with CVC4 and Frama-C WP with the JArray study's `x86_64`, `Typed+ref`,
Alt-Ergo/Z3, per-goal time, and memory settings. Pass the compatible Why3
configuration with `--why3-extra-config` when required by the installed
Frama-C/Why3 versions.

## Java proof workload capture

Use `--language java --java-workers 2 --capture-java-workload` with a **new**
output directory containing a copy of the study's `frozen_specs` directory.
Run `--stage all` to measure both originals and mutants. This keeps historical
results and timings intact, and can run alongside C in its existing directory.
The installed OpenJML compatibility setup must provide an explicit solver binary.

Each executed Java case saves `java-workload.json`, also embedded as
`java_workload` in `record.json`, with separate units:

* `generated_assertion_count`: generated basic-block `assert` statements,
  including implicit safety checks and constructor checks, grouped by kind
  and method. These are checks inside method VCs, not individually proved goals.
* `generated_method_vc_count`: emitted basic-block method verification conditions.
* `solver_check_sat_count`: actual solver queries recorded by the input/output
  proxy, including follow-up counterexample and feasibility queries. Solver
  responses and invocation counts are recorded separately.
* `reported_warning_count`: classified warning diagnostics (the legacy `goals`
  list). This is **not** the number of generated proof obligations.

`capture_complete` requires completed method reporting and matching query/reply
counts. Interrupted/error cases retain observed counts with incomplete coverage;
unavailable counts are `null`, not zero. Raw basic-block output stays in
`stdout.log`; solver inputs and replies are in `solver-traces/`. Frama-C WP
report entries are a different unit, so these counts must not be equated across
tools. Diagnostic logging, proxying, and concurrent load can affect timings.
See the [OpenJML proof splitting documentation](https://www.openjml.org/tutorial/SplittingProofs)
and [user guide](https://www.openjml.org/documentation/OpenJMLUserGuide.pdf).

## C counterexample capture and validated replay

`--capture-c-counterexamples` enables `-wp-counter-examples -wp-status` and
retains generated queries under each case's `wp/` directory. Select a solver
with model support, identified by `frama-c -wp-list-provers`; the installed
Z3 4.8.12 configuration supports this. Use a new output directory because the
setting changes the verification protocol. Parsed full models appear in
`counterexample_models`; a model alone does not change a verdict.

WP's top-level JSON proof report does not document an `invalid` verdict. Even
a model-producing proof attempt may remain `Unknown (Model)`. The C rejection
rule requires explicit invalidity or a validated counterexample, while the
Java rule uses classified OpenJML proof-failure diagnostics. Zero C rejections
in the original run is therefore not a comparable measure of Java/C fault
detection. See the [Frama-C 33 WP manual, sections 2.4.10 and 2.7](https://www.frama-c.com/download/frama-c-wp-manual.pdf).

The supplemental runner calibrates on MaxOfTwo before evaluating the six proved
C originals and their 80 mutants:

```bash
python3 -m verification.specification_evaluation.c_counterexamples \
  --calibrate --output verification/specification_evaluation/results/c_counterexamples_calibration
python3 -m verification.specification_evaluation.c_counterexamples \
  --workers 2 --output verification/specification_evaluation/results/c_counterexamples_proved_originals
```

It reuses exact frozen annotated sources and support hashes. Original controls
must prove and agree with executable equivalents of their frozen postconditions.
The replay oracles are restricted to six explicitly pinned specification hashes;
this is not a general ACSL-to-C translator. It retains WP outcomes separately
from replay outcomes. WP's preliminary incremental SMT queries can provide scalar
candidates even when completing the full theory model times out. Those queries
are labelled partial, and their SAT result never establishes rejection. A fixed
bounded search with seed 726 also supplies candidates, including valid arrays.

A functional rejection requires an admissible input, normal execution of the
actual C mutant, no UBSan diagnostic, and a result violating the frozen
postcondition. Runtime-safety witnesses are separate. A passing finite search,
failed model extraction, or replay timeout remains inconclusive. Native compile
commands, witnesses, expected/actual values and candidate origins are saved.
The source experiment is not modified or merged with these supplemental outcomes.
Solver `.smt2` files, tests, compiler binaries and configuration changes need not
be committed with the report.

## Targeted diagnostic runs

Both `diagnose_c_repairs` and `diagnose_java_repairs` accept these repeatable
selectors. Multiple selectors are combined, with duplicate cases run once:

| Selector | Target |
| --- | --- |
| `--program CountingSort` | The original CountingSort program in that language |
| `--case CountingSort/1` | Retained mutant 1 of CountingSort |
| `--case CountingSort/original` | The original CountingSort program |
| `--source /path/to/CountingSort.c` | That raw source file (use `.java` for Java) |

Run from the repository root inside the verifier's Linux environment, for example:

```bash
python3 -m verification.specification_evaluation.diagnose_c_repairs \
  --study verification/specification_evaluation/results/originals_stop_tool_error_20260927T161243Z_goal10_case300 \
  --output verification/specification_evaluation/results/diagnose_countingsort_c \
  --program CountingSort --goal-timeout 5 --timeout 60

python3 -m verification.specification_evaluation.diagnose_java_repairs \
  --study verification/specification_evaluation/results/originals_stop_tool_error_20260927T161243Z_goal10_case300 \
  --output verification/specification_evaluation/results/diagnose_countingsort_java \
  --source FormalBench-data/FilteredData/selected_java/seed_726_per_category_10_653ade686f/CountingSort.java
```

The output directory must be new. Defaults are 5 seconds per goal and 60 seconds
per verifier invocation. Explicit selections run regardless of prior outcome.
Java retains its previous default of rerunning all recorded syntax/tool failures
when no selector is supplied; C requires at least one selector. C's annotation
preflight now covers only the selected cases, followed by task generation and
verification for those cases.

`--source` expects an **unannotated** source file. Known study source paths retain
their original or mutant identity. An external raw file must have the original
program's filename, such as `CountingSort.c`, so its frozen specification can be
found. It is recorded under a distinct `mutant_external_<content hash>` directory
with `selection: external_diagnostic_source`; it is not added to the study's
eligible population. Use Linux paths in WSL, such as `/mnt/d/...` for `D:\...`.
Diagnostics reuse the study's frozen specifications and do not resume its batch.

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

Saved C counterexamples can be integrated into their source experiment without
restarting verification or replay:

```bash
python3 -m verification.specification_evaluation.integrate_c_counterexamples \
  --output verification/specification_evaluation/results/originals_stop_tool_error_20260927T161243Z_goal10_case300
```

The committed experiment contains result information only: per-case verification
records and WP reports, replay records and witnesses, audits, frozen contracts,
and summaries. Canonical sources remain under `FormalBench-data/`. Generated
source snapshots, harnesses, compiler metadata and binaries stay in ignored
local output storage. Commands and transfer results are embedded in the case
records, so their separate copies are excluded as well.

README/statistics regeneration uses the retained results and Java workload
snapshot. The integration commands below require the complete local working
artifacts of the input run; a results-only checkout does not include those files.

The integration preserves the original proof-only summary in
`summary_verification.json`, archives completed case evidence under
`counterexamples/`, and adds `c_counterexample_evidence` to `summary.json`.
Overlapping case coverage is deduplicated. Stopped searches remain partial;
current original-source changes are recorded separately. The combined C evidence
view does not turn a WP unknown verdict into a WP invalid verdict. Regenerate the
experiment README/statistics with `summarize_experiment` after integration.

The default evidence run is the completed full-population replay search
`c_counterexamples_refreshed_all_c_20261001_search_only`. It covers 50 originals
and 977 mutants using the refreshed frozen contracts. Use repeatable
`--evidence-run` arguments to choose other saved runs.

The four corrected-original C verification invocations can be promoted after
checking that their sources, frozen contracts, settings, trusted support and
verifier match the experiment:

```bash
python3 -m verification.specification_evaluation.integrate_c_originals \
  --output verification/specification_evaluation/results/originals_stop_tool_error_20260927T161243Z_goal10_case300 \
  --rerun verification/specification_evaluation/results/originals_stop_tool_error_20260927T161243Z_goal10_case300/original_verification/c_corrected_originals_verification_20261001
```

This archives the original invocation records and the replaced pending records
under `original_verification/`, updates the four original case records, and
recounts the proof summary. It does not rerun mutant verification. The README
and statistics generator includes deferred `not run` cases explicitly.

Corrected C translation reports and completed C search outputs have been
consolidated inside the final experiment. `c_run_archive.json` records their
former and current result locations and historical copy/move hash checks.
The committed archive retains result information. Raw-run integration requires
the complete local generated input artifacts; report regeneration reads the
retained JSON results and workload snapshot.

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
