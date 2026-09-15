# Java array support-contract validation

This verification workspace is separate from the production runtime. Production
files and include paths under `runtime/java_arrays/` stay unchanged.

| Location | Purpose |
| --- | --- |
| `contracts/` | Handwritten ACSL declarations and representation predicates |
| `generated/` | Reproducibly expanded production types, bodies, and implementation driver |
| `clients/` | Small clients including contracts without implementation bodies |
| `results/` | Proof logs, assumptions, versions, and status summaries |

## Scope and trust boundary

The evaluation covers normal execution: nonnegative representable allocation
sizes, non-null receivers for length/get/set, and in-range indices. Nullable
validity predicates allow null *stored rows* and null checks; they do not make
dereferencing null safe. Helper contracts additionally specify negative-size,
null-reference, index-error, and allocation-overflow exits. Public array contracts
still cover normal inputs only; the helper cases do not establish public error
coverage. Existing runtime error tests are regression checks only.

The public constructors and the internal allocator are **Trusted**, not Proved.
Their summaries provide successful allocation through non-null/fresh
postconditions and `exits \false` clauses. Size bounds exclude multiplication
overflow for constructors. The allocator separately specifies overflow as exit
74, while representable positive requests still assume successful allocation;
actual malloc exhaustion is excluded. This is an assumption about available memory, not a proof that the
production allocator always succeeds. Constructor summaries also assume typed
initialization, metadata/storage separation, and rectangular row independence.
Clients receive these summaries without bodies. No allocator implementation proof
or replacement production allocator is required.

`array_error` is a trusted non-returning summary with an exit-code guarantee.
It does not claim diagnostic I/O writes nothing. Check helpers have complete,
disjoint normal/error behaviors, with a no-write guarantee only on normal paths.
Their selected implementation proofs now include both behaviors, conditional on
the trusted error summary; diagnostic I/O and allocator internals are not proved.
Cleanup is outside evaluation. Its contracts require freeable allocation
bases; validity alone does not authorize freeing stack objects or interior
pointers. Callers remain responsible for avoiding later use through aliases.

Keep the implementation, contracts, selected memory/arithmetic models, machine
model, and prover configuration fixed for original benchmarks and their mutants.
Library mutation is not required, and these checks do not define a completeness
score.

## Representation guarantees

All array predicates separately require readable initialized metadata and
writable initialized element/row-slot storage. Empty arrays remain non-null
objects with null data pointers.

The matrix predicates permit null, empty, shared, and jagged rows. They separate
outer metadata and pointer slots from row metadata and element buffers. Row
buffers are also separated from every stored row's metadata, allowing element
updates to preserve matrix validity. They do not require distinct row handles
or pairwise disjoint row buffers. The row-setter compatibility precondition
establishes the same storage facts for the incoming row, including a row already
stored in another slot.

Only rectangular constructors promise pairwise distinct row objects and disjoint
row buffers. Constructors describe all allocated blocks and their freshness;
setters assign exactly one element or pointer slot. Row replacement neither
copies nor frees the previous row.

Initialization and allocation semantics follow the
[ACSL reference, Sections 2.7 and 2.13](https://www.frama-c.com/download/acsl-1.22.pdf).
Floating-point proof results also depend on the selected WP floating-point model;
these clients do not establish coverage for NaNs, infinities, or signed-zero
distinctions.

## Reproducible preparation

Run commands from `FormalBench-Extension/`:

```bash
python3 verification/java_arrays/scripts/prepare.py --check
python3 verification/java_arrays/scripts/check_baseline.py
```

The first checks the production hashes and generated representation without
rewriting files. If regeneration is necessary, run `scripts/prepare.py` without
`--check`. It expands `DEFINE_ARRAY` from the production macro body, extracts
concrete types, and retains production function bodies. There is no handwritten
duplicate implementation. The second command runs existing runtime cases against
the generated baseline; it is not a proof of transformation equivalence.

## Implementation–contract agreement

### Installed macOS setup and recorded workflow

Frama-C 33.0 (Arsenic) and Z3 5.1.0 are now installed on this Apple Silicon Mac.
Frama-C came from the official macOS ARM package; Z3 was installed with Homebrew.
The package's default launcher could not find its bundled data. The installed
`/usr/local/bin/frama-c` launcher was corrected to execute
`/Applications/Frama-C.app/Contents/Resources/bin/frama-c`. A future reinstall
may overwrite that launcher.

```bash
frama-c -version
frama-c -wp-list-provers
python3 verification/java_arrays/scripts/check_support.py
```

The runner selects the documented implementation methods and runs all five
contract-only client files, with Z3, the `Typed` memory model, the `macos_arm`
machine model, a 30-second per-goal prover timeout, and a 180-second per-target
wall-clock limit. It records source hashes, versions, commands, stdout/stderr,
and raw per-goal WP reports in a fresh `results/support_<UTC timestamp>/` folder.
It never runs library mutants or changes contracts. Its exit code reports
execution errors/timeouts, not complete proof success; inspect the reports.

Why3 launches a local solver service using a Unix socket. In the Codex sandbox
the first external-prover run failed to connect to this service. The successful
Z3 runs used approved execution outside that sandbox. This is unrelated to the
ACSL's validity. Ordinary Terminal runs do not use that Codex sandbox.

See `results/contract-update-status.md` for the findings and remaining limits.

### Direct commands

Verify selected actual bodies using the baseline driver. For example, with an
installed, configured Z3 prover:

```bash
jarray_methods='check_length,check_reference,check_index,jarray_is_null,jarray_length,jarray_get,jarray_set,jbool_array_is_null,jbool_array_length,jbool_array_get,jbool_array_set,jdouble_array_is_null,jdouble_array_length,jdouble_array_get,jdouble_array_set,jarray2_is_null,jarray2_length,jarray2_get,jarray2_set,jdouble_array2_is_null,jdouble_array2_length,jdouble_array2_get,jdouble_array2_set'
frama-c -machdep macos_arm -wp -wp-rte -wp-model Typed -wp-prover qed,z3 -wp-timeout 30 \
  -wp-fct "$jarray_methods" \
  verification/java_arrays/generated/drivers/baseline.c
```

The selected list excludes constructors, allocation, error handling, and cleanup.
Their bodies remain mechanically generated; they are not reported as proved.
Record actual command lines, Frama-C and prover versions, model settings, input
hashes, warnings, and individual obligations under `results/`.

## Contract-interface clients

Each client includes contracted declarations and generated types only. Do not
include `generated/baseline.c` or the baseline driver in these runs.

```bash
for client in check_set_get check_scalar_families check_int_rows check_double_rows
do
  frama-c -machdep macos_arm -wp -wp-rte -wp-model Typed -wp-prover qed,z3 -wp-timeout 30 \
    "verification/java_arrays/clients/$client.c"
done

# Run separately: the two named negative assertions must not be proved.
frama-c -machdep macos_arm -wp -wp-rte -wp-model Typed -wp-prover qed,z3 -wp-timeout 30 \
  verification/java_arrays/clients/negative_set_get.c
```

The clients cover constructor initialization, setter return values, set/get
composition, untouched elements, length preservation, an existing valid array,
empty arrays, initially null rows, rectangular independence, shared updates,
row replacement, jagged lengths, and preservation of replaced rows. Function
calls are executable C statements; assertions refer to their stored results.

Inspect obligations and warnings, not just process exit status. The negative
assertions `negative_new` and `negative_existing` deliberately expect 6.0 after
setting 5.0. An unsupported annotation, parse failure, or failed precondition
does not constitute a successful negative control. First establish the
corresponding positive paths. Failure to prove these assertions is a sanity
check, not a completeness measurement.

## Status and stopping condition

| Status | Meaning |
| --- | --- |
| Proved | Selected obligations discharged under recorded assumptions |
| Unknown | Not run or unresolved, including unsupported annotations and timeouts |
| Trusted | Summary used without proving its implementation |
| Violation confirmed | Implementation contradicts the contract, with validated evidence |

See `results/contract-update-status.md` for the current validation record.
Do not infer either a bug or success from an unresolved obligation.

After the clients pass, check a few translated programs with known handwritten
properties. Distinguish missing library guarantees from missing program
annotations and prover limitations. Freeze the support layer once the patterns
used by the benchmarks have sufficient evidence; passing these clients alone
does not establish adequacy for every benchmark.

The existing `generate_mutants.py`, `mutations.json`, and mutation-oriented
`verify.py` remain legacy tooling. They are not required by this support-layer
procedure. In particular, the legacy runner selects constructors and cleanup
as well, so its whole-baseline result is not the scoped accessor/row-operation
status described here.
