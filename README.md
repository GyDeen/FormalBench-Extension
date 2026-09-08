# Differential testing

The package is divided into three stages:

- `generation/` generates EvoSuite tests and extracts portable inputs.
- `execution/` generates harnesses, executes Java/C, and collects sanitizer evidence.
- `comparison/` compares results and applies explicit manual assessments.

## EvoSuite input extraction

`generation/extract_test_inputs.py` extracts only the inputs and ordered
target-program calls from EvoSuite `*_ESTest.java` files. It ignores
scaffolding, constructors, JUnit assertions, and expected return values or
exceptions.

The extraction implementation is separated by responsibility:

- `java_input_parser.py` handles the supported Java syntax and type conversion.
- `input_extractor.py` builds fixtures, references, and ordered call records.
- `extract_test_inputs.py` provides the command-line and JSON output interface.

Run it with:

```bash
python3 -m differential_testing.generation.extract_test_inputs \
  FormalBench-data/FilteredData/evosuite/<run-id>/evosuite-tests \
  --output differential_testing/generation/test_inputs.json
```

## JSON format

Each entry in `tests` represents one non-constructor JUnit test:

```json
{
  "id": "Frequency_ESTest.test0",
  "source_file": "Frequency_ESTest.java",
  "fixtures": [
    {
      "id": "intArray0",
      "type": "int32[]",
      "value": [0, 582]
    }
  ],
  "steps": [
    {
      "id": "call_0",
      "class": "Frequency",
      "function": "frequency",
      "arguments": [
        {"type": "int32[]", "ref": "intArray0"},
        {"type": "int32", "value": 0}
      ],
      "result": {"id": "int0", "type": "int32"}
    }
  ]
}
```

- `value` holds a concrete scalar, `null`, or a materialized array. Java's
  default array elements are written explicitly.
- `ref` points to a fixture or an earlier result binding. Reusing the same
  reference preserves array aliasing.
- `steps` preserve call order. A later step may use an earlier step's result
  through its result `id`.
- `result` records a binding only. It does not contain an expected value.

## Java/C result comparison

First execute the extracted inputs against the corresponding function-only
Java and C sources:

```bash
FormalBench-Extension % python3 -m differential_testing.execution.run_java_c \
  --inputs differential_testing/generation/test_inputs-653ade686f.json \
  --java-dir FormalBench-data/FilteredData/selected_java/seed_726_per_category_10_653ade686f \
  --c-dir FormalBench-data/FilteredData/translated_c/seed_726_per_category_10_653ade686f \
  --output-dir differential_testing/results/653ade686f
```

This creates `java_results.json` and `c_results.json`. Each call is isolated
and has a two-second timeout by default; use `--timeout SECONDS` to change it.
If an EvoSuite class name differs from the selected source filename, the
runner locates the unique source containing the called function.

The C harness distinguishes null arrays (`{NULL, 0}`) from empty arrays
(non-null storage, length zero), including matrix rows. JSON output uses
`null` for null storage and `[]` for non-null, zero-length storage. Translated
functions must follow the same convention for returned arrays; a returned
`{NULL, 0}` is interpreted as null, not empty. This preserves nullness but
does not introduce Java-style exceptions: reading a C wrapper's `length`
still succeeds even when its storage pointer is null.

Execution responsibilities are separated across:

- `run_java_c.py`: command-line parsing and JSON file output.
- `input_manifest.py`: input validation and grouping.
- `java_harness.py` and `c_harness.py`: language-specific source generation.
- `execution_orchestrator.py`: source discovery, compilation, and isolation.

`comparison/compare_java_c.py` compares normalized execution results produced
by Java and C runners:

```bash
python3 -m differential_testing.comparison.compare_java_c \
  --inputs differential_testing/generation/test_inputs.json \
  --java-results differential_testing/results/java_results.json \
  --c-results differential_testing/results/c_results.json \
  --output comparison.json
```

Each runner must produce this structure:

```json
{
  "schema_version": "1.0",
  "kind": "test_execution_results",
  "language": "java",
  "tests": [
    {
      "id": "Frequency_ESTest.test0",
      "steps": [
        {
          "id": "call_0",
          "status": "returned",
          "return": {"type": "int32", "value": 1},
          "state_after": [
            {"id": "intArray0", "type": "int32[]", "value": [0, 582]}
          ]
        }
      ]
    }
  ]
}
```

Use `language: "c"` for the C result file. For a failed call, use a canonical
error instead of `return`, for example:

```json
{
  "id": "call_0",
  "status": "error",
  "error": {"kind": "null_dereference", "message": "optional diagnostic"}
}
```

The comparison checks return values, canonical errors, and `state_after` so
in-place array mutations are included. Diagnostic error messages are not
compared. Floating-point values use configurable relative and absolute
tolerances.

## Sanitizer investigation and manual assessment

Run the comparison pipeline after collecting ordinary execution results:

```bash
python3 -m differential_testing.comparison.run_comparison \
  --inputs differential_testing/generation/test_inputs-653ade686f.json \
  --java-results differential_testing/results/653ade686f/java_results.json \
  --c-results differential_testing/results/653ade686f/c_results.json \
  --comparison differential_testing/results/653ade686f/comparison.json \
  --c-dir FormalBench-data/FilteredData/translated_c/seed_726_per_category_10_653ade686f \
  --output-dir differential_testing/results/653ade686f
```

The output directory contains:

- `sanitizer_report.csv`: one review row per mismatching Java-error call.
- `comparison_final.json`: the comparison with any explicit manual assessments.

`--comparison` reuses the existing `comparison.json` without modifying it.
Omit this option to compute the ordinary comparison and save `comparison_raw.json`.

The pipeline selects mismatching steps with Java `status: "error"`, without
interpreting error kinds. It compiles selected C tests using Clang with
`-fsanitize=address,undefined -fno-omit-frame-pointer -g` and replays each
target's test prefix in an isolated process. Use `--timeout SECONDS`,
`--compiler PATH`, or `--keep-build` as needed. Leak detection is disabled
because the generated harness does not free all fixtures; memory-access and
undefined-behavior diagnostics remain enabled. Stdout, exit codes, timeouts,
and failure statuses are retained without semantic mapping. Report `stderr`
contains only runtime-error messages and sanitizer error/summary lines, with
duplicates removed; stack traces, symbolizer warnings, and memory dumps are omitted.

`input_id` combines the test and call IDs, for example
`FindPeak_ESTest.test2.call_0`. Use it to locate the inputs in the input manifest.
The report omits source paths, harness code, compilation records, and commands.
Earlier calls may mutate inputs or fail before the target is reached; sanitizer
diagnostics may also originate in harness serialization. Review the test prefix
and diagnostic locations before attributing a failure to the target call.

The CSV columns `program`, `input_id`, `java_error_type`, `c_output`, and `stderr`
show the evidence to review. `java_error_type` contains the reported exception
class (such as `java.lang.NullPointerException`) or the runner's error kind
(such as `runner_protocol_error`). Full Java output remains in `java_results.json`.
C output cells contain JSON so values, types, and post-call state survive export
and import. Multiline diagnostics are
quoted as a single CSV cell. Additional columns preserve sanitizer execution
status, exit code, stdout, timeout, ASan/UBSan options, execution errors, and the
raw comparison digest. Source files, harness code, compilation records, and
commands are omitted.

`status` and `reason` start empty. Fill these two cells after manual review;
`sanitizer_status` is execution evidence and is separate from your decision.
For example, enter `exception_equivalent` in `status` and
`Both failures originate from the same invalid array access.` in `reason`.

Allowed review statuses are `exception_equivalent`, `mismatch`, and `unclear`,
each requiring a nonempty reason. Blank statuses remain mismatches. Sanitizer
text never automatically establishes equivalence. Save the reviewed CSV and
merge it without rerunning execution:

```bash
python3 -m differential_testing.comparison.merge_sanitizer_results \
  --comparison differential_testing/results/653ade686f/comparison.json \
  --sanitizer-report differential_testing/results/653ade686f/sanitizer_report.csv \
  --output differential_testing/results/653ade686f/comparison_final.json
```

The final report retains original step differences and all three evidence
records. Test status prioritizes remaining `mismatch`, then `unclear`, then
`exception_equivalent`, then `match`. Missing or extra results remain mismatches.
Summary counts keep exact matches separate from exception equivalence;
`overall_equivalent` accepts both, while `overall_match` requires exact matches.
The commands exit with status 1 when mismatches or unclear cases remain.

The pipeline refuses to overwrite existing audit files. Choose a new output
directory for new execution evidence; use the merge command to update only the
final report. A comparison digest rejects reports from a different raw comparison.

The standalone `execution.run_c_sanitizer` command also writes review CSV:
pass an extracted `--cases` JSON, the existing `--comparison` JSON, `--c-dir`,
and `--output sanitizer_report.csv`.
