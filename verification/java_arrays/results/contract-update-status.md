# Frama-C support-contract validation — 2026-09-15 (Melbourne)

This is a historical macOS study record, not the current machine's setup or
results. Use the [WSL verification instructions](../README.md) and
[AMD Ryzen 7 7700 run summary](local-verification-summary-2026-09-16.md) for
the current environment. The versions, paths, and counts below are retained
as historical evidence.

The contracts at the time of these runs parse and support the scalar client patterns, but the
support layer is **not ready to freeze**. Row-client and implementation proofs
remain unresolved. No confirmed implementation–contract violation was found.
Contracts and production sources were not edited during these proof runs.

## Installed environment

- Frama-C 33.0 (Arsenic), from the
  [official macOS ARM package](https://www.frama-c.com/html/get-frama-c.html).
- Z3 5.1.0, installed with Homebrew and detected by Frama-C's bundled Why3.
- Apple Silicon; `-machdep macos_arm -wp-model Typed`.
- Main run: `qed,z3`, 30 seconds per prover goal, four parallel prover
  processes, 180 seconds per target. No proof-cache reuse.
- Python 3.14.7 and Apple Clang 21.0.0.

The package installed a launcher pointing to a binary unable to locate its
bundled data files. `/usr/local/bin/frama-c` was corrected to execute
`/Applications/Frama-C.app/Contents/Resources/bin/frama-c`.
Both `frama-c -version` and `frama-c -wp-list-provers` now work.

An initial sandboxed run could not connect to Why3's local Unix socket and was
interrupted. Subsequent Z3 runs used approved execution outside the Codex
sandbox. The failed socket run is execution evidence only.

## Implementation method status

The following statuses apply individually to every method listed in a row.
Proved means the selected obligations passed under the recorded assumptions,
not that allocator or diagnostic implementation bodies were verified.

| Methods | Status | Evidence |
| --- | --- | --- |
| `check_length`, `check_reference`, `check_index` | Proved | Each has 9/9 reported WP goals, including normal/error behaviors |
| `jarray_is_null`, `jbool_array_is_null`, `jdouble_array_is_null`, `jarray2_is_null`, `jdouble_array2_is_null` | Proved | Each has 2/2 reported WP goals |
| `jarray_length`, `jarray_get`, `jarray_set` | Unknown | Unresolved implementation obligations |
| `jbool_array_length`, `jbool_array_get`, `jbool_array_set` | Unknown | Unresolved implementation obligations |
| `jdouble_array_length`, `jdouble_array_get`, `jdouble_array_set` | Unknown | Unresolved implementation obligations |
| `jarray2_length`, `jarray2_get`, `jarray2_set` | Unknown | Unresolved implementation obligations |
| `jdouble_array2_length`, `jdouble_array2_get`, `jdouble_array2_set` | Unknown | Unresolved implementation obligations |
| All seven constructors | Trusted | Summaries used without proving their bodies |
| `allocate`, `array_error` | Trusted | Infrastructure assumptions |
| All five public free methods | Trusted | Cleanup outside evaluation; not exercised |

The full default-model implementation attempt reached the 180-second target
limit. A separate Qed-only run completed with 78/193 reported WP goals proved
(the console additionally counts 10 automatic termination/unreachable
properties, yielding 88/203). This is not an implementation pass.
The focused helper/null-check run proved all 37 reported WP goals; its console
count is 47/47 for the same reason.

## Contract-only client results

All client runs include declarations and types without library bodies.

| Client | Result |
| --- | --- |
| `check_set_get.c` | 59/59 goals proved, including an existing valid array |
| `check_scalar_families.c` | 51/51 goals proved |
| `check_int_rows.c` | 112/119 goals proved; 7 timed out — Unknown |
| `check_double_rows.c` | Unknown; reached the 180-second target limit with unresolved row obligations; no complete WP JSON report |
| `negative_set_get.c` | Both named false assertions remain unproved; all 23 other goals passed in the focused run |

The two negative-control timeouts are the intentionally false assertions
`negative_new` and `negative_existing`. The corresponding positive paths
and preconditions passed. This is the expected sanity-check outcome, not a
proof of general consistency or completeness.

The integer-row failures include validity of the independent row after an
element update, matrix validity before a length call, row-setter validity and
compatibility preconditions, validity of the displaced row, and preservation
of the replacement row after setting its slot to null. Assertions proved after
an unresolved call precondition do not establish an end-to-end client proof.

## Material warnings and limitations

1. WP reports that constructor `\fresh` clauses are unsupported, using the
   diagnostic “Allocation, initialization and danglingness not yet implemented.”
   The tested Bytes model reports the same freshness limitation. Declaring
   constructors Trusted does not make unsupported clauses available to WP.
2. The Typed implementation run rejects conversions from array-object pointers
   to the `const void *` argument of `check_reference` as incompatible
   pointer casts. Affected goals are marked “Stronger”; their failure is not
   evidence of a C bug.
3. The Bytes diagnostic removed those cast warnings but left many implementation
   obligations unresolved and reached its 90-second target limit. Its scalar
   client result was 53/59 at a 5-second per-goal timeout. It is not a validated
   replacement configuration.
4. Frama-C generates missing termination/exit clauses for some body-less
   declarations. WP also warns about pointer-result dependencies and unspecified
   error-path write footprints. These defaults and assumptions need explicit
   treatment before freezing the interface.
5. WP skips alignment and function-pointer-validity RTE guards in this setup.
   Successful counts cover the generated obligations only. Floating-point
   special values, public error-input behavior, cleanup, allocator internals,
   and full benchmark integration are not established by these clients.

A diagnostic attempt to inline the check helpers did not resolve the Typed
cast limitation and produced an additional unsupported-statement-contract
warning; that option was not adopted.

Do not remove initialization or separation requirements just to obtain green
proofs. The next refinement should establish a WP-supported allocation/interface
model and a sound way to verify the generic null-check calls, then resolve the
remaining row obligations and make declaration defaults explicit. No contract
or benchmark-support version has been frozen.

## Reproduction and evidence

The per-run proof logs, JSON reports and obligation dumps were intentionally
removed after the summary tables were retained. They can be regenerated from
the repository root with the commands below; each fresh run writes a new
timestamped directory under `verification/java_arrays/results/`.

On the original macOS toolchain, from the repository root, pass the historical
settings explicitly (the runner now defaults to the local WSL configuration):

```bash
python3 verification/java_arrays/scripts/check_support.py \
  --machdep macos_arm --model Typed --provers qed,z3 --no-why3-extra-config \
  --timeout 30 --run-timeout 180
python3 verification/java_arrays/scripts/check_support.py \
  --machdep macos_arm --model Typed --provers qed,z3 --no-why3-extra-config \
  --target implementation \
  --functions check_length,check_reference,check_index,jarray_is_null,jbool_array_is_null,jdouble_array_is_null,jarray2_is_null,jdouble_array2_is_null \
  --timeout 5 --run-timeout 45
```

The retained tables above are the study record; raw per-goal artifacts are not
part of the retained result set. A timed-out target may not emit a completed
JSON report.

Preparation consistency and the generated baseline's production runtime cases
also passed. These regression checks do not prove source-transformation
equivalence or validate ACSL by themselves.
