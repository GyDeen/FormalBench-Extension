# Experimental Java arithmetic verification

This package records a standalone Frama-C/WP experiment on the existing C
translation helpers using handwritten ACSL contracts. Implementation checks
and conditional client proofs are recorded separately. **This package is not
used as a trusted supporting library in the benchmark evaluation.**

The [recorded proof summary](results/README.md) shows seven helper
implementations proved, one unresolved multiplication postcondition, and
42/42 positive client obligations proved under the fixed contracts.

| Location | Purpose |
|---|---|
| `contracts/int32.acsl.h` | Experimental contracts for the eight existing helper operations |
| `generated/helpers.c` | Bodies mechanically extracted from translated originals |
| `generated/manifest.json` | Per-original helper inventory and source locations |
| `clients/check_*.c` | Small modular clients using declarations and contracts |
| `clients/negative_controls.c` | Incorrect wrap assertion and forbidden zero-divisor call |
| `scripts/prepare.py` | Extraction tooling; optional attachment is not used by the benchmark |
| `scripts/check_support.py` | Frama-C WP implementation and client checks |
| `results/` | Human-readable validation summary; raw local artifacts are ignored |

## Contract scope

| Helper | Fixed result semantics | Entry restriction |
|---|---|---|
| `java_add`, `java_sub`, `java_mul` | Signed 32-bit wrapping addition, subtraction and multiplication | None |
| `java_div` | Division toward zero, including `INT32_MIN / -1 == INT32_MIN` | Divisor must be nonzero |
| `java_abs` | Java integer absolute value, including `abs(INT32_MIN) == INT32_MIN` | None |
| `java_min`, `java_max` | Minimum and maximum of two signed integers | None |
| `java_shl` | Left shift with the distance masked to five bits and a wrapped result | None |

Every helper has an `assigns \nothing` contract. Arithmetic inside ACSL is
mathematical; explicit `int32_t` casts express machine-width wrapping. This
follows the [ACSL integer-cast semantics](https://www.frama-c.com/download/acsl-1.22.pdf)
and [Java integer arithmetic and shift rules](https://docs.oracle.com/en/java/javase/26/docs/specs/jls/jls-15.html).
The recorded machine model is part of the support-layer configuration.

The existing division implementation guards the `INT32_MIN / -1` overflow
case but has no zero-divisor guard. The normal contract consequently requires
a nonzero divisor. It does not claim an implementation of Java's division-by-zero
exception.

## Prepare and validate

From the repository root in the existing WSL environment, with the opam toolchain on PATH:

```bash
python3 verification/JArithmetic/scripts/prepare.py
python3 verification/JArithmetic/scripts/prepare.py --check
python3 verification/JArithmetic/scripts/check_support.py
```

Preparation scans the selected C originals, requires a single executable-token
implementation for each helper name, and extracts those bodies. It introduces
no handwritten replacement runtime and performs no hash checks. `--check`
compares generated text directly with fresh extraction. Frama-C/WP checks the existing translated helper bodies against the fixed ACSL contracts; modular clients check that callers can use those contracts.

WP defaults match the JArray-style configuration: Frama-C with `x86_64`,
`Typed+ref`, Alt-Ergo 2.4.3 and Z3 4.8.12, a 10-second goal timeout, a 300-second
target timeout, 1000 MB per solver and two parallel prover processes. Select a
subset with repeated `--target`, and change budgets with `--timeout`,
`--run-timeout`, `--wp-par` and `--memlimit`. Exact commands, contract text,
goal reports and tool versions are retained under `results/local/`.

## Experimental status

The complete helper set is not proved: `java_mul_ensures` timed out. The client
results are conditional on the declared contracts and do not establish this
missing implementation proof. The contracts are not adopted as trusted
summaries, and this experiment does not justify excluding helper obligations
from benchmark verification or reclassifying unresolved originals as proved.

The optional attachment tooling is retained as an experimental utility; it is
not connected to the benchmark pipeline. Existing frozen specifications,
verification settings and consistency/completeness results are unchanged.
Negative controls are recorded as unproved checks, not counterexamples or
mutant detections.
