# EvoSuite input extraction

`translate_test.py` extracts only the inputs and ordered target-program calls
from EvoSuite `*_ESTest.java` files. It ignores scaffolding, constructors, JUnit
assertions, and expected return values or exceptions.

Run it with:

```bash
python3 test_generator/translate_test.py \
  FormalBench-data/FilteredJava/evosuite/<run-id>/evosuite-tests \
  --output test_generator/test_inputs.json
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
