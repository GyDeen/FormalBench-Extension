# Java array library verification workspace

This directory isolates verification artifacts from the production array
runtime. Translated programs continue to include and link only:

- `runtime/java_arrays/java_arrays.h`
- `runtime/java_arrays/java_arrays.c`

Nothing under `verification/java_arrays/` is a production dependency.

## Scope and boundary

| Component | Reads | Writes |
| --- | --- | --- |
| Preparation | Production `java_arrays.h` and `java_arrays.c` | `generated/types.h`, `generated/baseline.c`, the baseline driver, and manifest |
| Mutation generation | Generated baseline and `mutations.json` | Generated mutant implementations, mutant drivers, and manifest entries |
| Frama-C runner | Generated drivers and fixed contracts | `results/` only |
| Contract refinement | Files under `contracts/` | Contract files only |

The production runtime is an input to this workspace and is never rewritten by
the preparation or mutation scripts. Contracts, concrete structure layouts,
drivers, and mutant sources remain verification-only.

## Deterministic preparation

Run from `FormalBench-Extension`:

```bash
python3 verification/java_arrays/scripts/prepare.py
```

The preparation script reads the production header and implementation, expands
each `DEFINE_ARRAY` invocation using the macro body present in the production
source, moves the resulting concrete structure definitions into
`generated/types.h`, and emits the expanded function bodies in
`generated/baseline.c`. It does not preprocess system headers or maintain a
second handwritten implementation.

The manifest records source hashes, generated hashes, and transformation
settings. Check that committed/generated inputs are current without modifying
them with:

```bash
python3 verification/java_arrays/scripts/prepare.py --check
```

If production sources change after mutants have been generated, preparation
refuses to leave stale mutants in place. Regenerate intentionally with:

```bash
python3 verification/java_arrays/scripts/prepare.py --reset-mutants
python3 verification/java_arrays/scripts/generate_mutants.py
```

## Mutation inputs

`mutations.json` is a deterministic mutation plan. Each entry names one
function and replaces one exact fragment inside that function body. The
generator rejects duplicate IDs, missing or ambiguous fragments, edits outside
the selected body, and replacements that alter brace balance.

```bash
python3 verification/java_arrays/scripts/generate_mutants.py
```

Every output contains one implementation and has a matching driver. Contracts,
types, function names, and signatures are not mutation targets. The included
`0001` mutation is a small integration fixture; additional mutation producers
can emit the same JSON plan format.

## Transformation check

The existing runtime test driver can be linked once against the production
implementation and once against the generated baseline. The following checks
the baseline using the same successful and error cases as the runtime tests:

```bash
python3 verification/java_arrays/scripts/check_baseline.py
```

This is a regression check for the deterministic transformation, not a proof of
semantic equivalence.

## Contracts and verification

The include order in every generated driver is:

1. generated concrete type definitions;
2. helper contracts and predicates/contracts;
3. annotated public declarations;
4. exactly one implementation body.

Fixed public contracts are split by array family:

- `contracts/jintarray.acsl.h` for `int[]`;
- `contracts/jboolarray.acsl.h` for `boolean[]`;
- `contracts/jdoublearray.acsl.h` for `double[]`;
- `contracts/jintarray2.acsl.h` for `int[][]`;
- `contracts/jdoublearray2.acsl.h` for `double[][]`.

`contracts/helpers.acsl.h` supplies matching `static` helper declarations. The
two-dimensional predicates validate outer storage while deliberately allowing
null, shared, empty, and jagged rows. Full two-dimensional constructors add the
stronger guarantees that their newly allocated rows are non-null, distinct,
correctly sized, and zero-initialized.

Run the baseline first and, only if it is proved, verify every manifest mutant:

```bash
python3 verification/java_arrays/scripts/verify.py
```

The default prover is Frama-C's internal `qed` prover. Select installed external
provers when stronger automation is needed:

```bash
python3 verification/java_arrays/scripts/verify.py \
  --provers qed,alt-ergo \
  --wp-timeout 30
```

Useful selection and inspection modes are:

```bash
# Validate inputs and print commands without running Frama-C.
python3 verification/java_arrays/scripts/verify.py --dry-run

# Verify only the baseline.
python3 verification/java_arrays/scripts/verify.py --baseline-only

# Verify selected mutants after the baseline.
python3 verification/java_arrays/scripts/verify.py --mutant 0001

# Collect raw mutant results even when the baseline is inconclusive.
python3 verification/java_arrays/scripts/verify.py --allow-inconclusive-baseline
```

Each invocation creates a new `results/run_<UTC timestamp>/` directory. Every
target receives its own command metadata, stdout/stderr logs, raw WP JSON report,
and normalized outcome. `summary.json` records tool/version settings, all input
hashes, the baseline gate, and separate lists for proved satisfaction,
demonstrated violations, inconclusive outcomes, and skipped mutants.

The classification is deliberately conservative. A WP timeout, unknown result,
prover failure, or candidate counterexample is **inconclusive**, not a contract
violation. A run is classified as a demonstrated violation only when report
data contains an explicit `invalid` verdict or a validated counterexample flag.
A loop-support obligation can prevent an overall proof, but its failure is
recorded separately and is never relabelled as a demonstrated public-contract
violation.

The run metadata makes the allocation model, `exit`/non-returning error paths,
integer and `size_t` bounds, and Frama-C/WP versions and options auditable.
Verification of translated benchmark programs against these contracts remains
a separate integration step.
