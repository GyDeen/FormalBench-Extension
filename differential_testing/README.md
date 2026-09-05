# Differential testing

The package is divided into two stages:

- `generation/` generates EvoSuite tests and extracts portable inputs.
- `execution/` contains Java/C execution and comparison logic.

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
python3 -m differential_testing.execution.run_java_c \
  --inputs differential_testing/generation/test_inputs.json \
  --java-dir FormalBench-data/FilteredData/selected_java/<run-id> \
  --c-dir FormalBench-data/FilteredData/translated_c/<run-id> \
  --output-dir differential_testing/results
```

This creates `java_results.json` and `c_results.json`. Each call is isolated
and has a two-second timeout by default; use `--timeout SECONDS` to change it.
If an EvoSuite class name differs from the selected source filename, the
runner locates the unique source containing the called function.

Execution responsibilities are separated across:

- `run_java_c.py`: command-line parsing and JSON file output.
- `input_manifest.py`: input validation and grouping.
- `java_harness.py` and `c_harness.py`: language-specific source generation.
- `execution_orchestrator.py`: source discovery, compilation, and isolation.

`execution/compare_java_c.py` compares normalized execution results produced
by Java and C runners:

```bash
python3 -m differential_testing.execution.compare_java_c \
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
