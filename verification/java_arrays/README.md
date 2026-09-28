# Java array support-contract validation

This verification workspace is separate from the production runtime. Production
files and include paths under `runtime/java_arrays/` stay unchanged.

| Location | Purpose |
| --- | --- |
| `contracts/` | Handwritten ACSL declarations and representation predicates |
| `generated/` | Reproducibly expanded production types, bodies, and implementation driver |
| `clients/` | Small clients including contracts without implementation bodies |
| `scripts/` | Preparation/check runners and the compatible Why3 prover configuration |
| `results/` | Tracked Markdown summaries; ignored local proof logs and diagnostic runs |

The [latest local run summary](results/local-verification-summary-2026-09-16.md)
records the AMD Ryzen 7 7700 results and the corrected solver diagnostics.

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

Run commands from the repository root (`/mnt/d/ResearchProject` in the local
WSL environment):

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

### Verified local toolchain (2026-09-16)

The current local environment is Windows with WSL 2, Ubuntu 24.04.5 LTS
(`ResearchUbuntuNoble`), and an AMD Ryzen 7 7700. No Docker image is required.

| Tool | Installed version used for the recorded full runs |
| --- | --- |
| Frama-C | 33.0 (Arsenic), installed through opam |
| Why3 | 1.8.2 |
| Alt-Ergo | 2.4.3-free; explicitly configure the `alt_ergo` driver as version 2.4.3 |
| Z3 | 4.8.12 (`/usr/bin/z3`) |
| OCaml | 4.14.1, opam switch `default` |
| Python | 3.12.3 |

Z3 5.1.0 is also installed separately, but it was used only for a focused
exported-task diagnostic, not the completed implementation/client suite.
Do not label those full results as Z3 5.1.0 runs. The earlier Apple M3 setup
used Frama-C 33.0, Z3 5.1.0, `macos_arm`, and `Typed`; its findings are retained
in the [historical macOS summary](results/contract-update-status.md).

From PowerShell, enter the existing WSL environment:

```powershell
wsl.exe -d ResearchUbuntuNoble -u root
```

Then run the remaining commands in Bash:

```bash
cd /mnt/d/ResearchProject
eval "$(opam env --switch=default)"
frama-c -version
why3 --version
alt-ergo --version
z3 --version
python3 --version
frama-c -wp-why3-extra-config verification/java_arrays/scripts/why3-alt-ergo-2.4.3.conf \
  -wp-list-provers
```

Why3's automatic detection does not correctly identify the `2.4.3-free`
version string. The original `Alt-Ergo:` entry had an empty version and used
an incompatible driver, producing parser errors. Use the source-controlled
[compatibility configuration](scripts/why3-alt-ergo-2.4.3.conf) and explicitly
select `Alt-Ergo:2.4.3,Z3:4.8.12`. With the opam switch on `PATH`, the configuration
uses its `alt-ergo` executable without a machine-specific absolute path.
The unversioned automatic entry may still be listed; do not select that entry.

### Limits and runner behavior

The local FormalBench-style runs used `x86_64`, `Typed+ref`, four concurrent
prover processes, no proof cache, 10 seconds per prover goal, and a 300-second
wall-clock limit per target. Later row-client experiments used 60 seconds per
goal. WP's default solver memory limit was 1000 MB per process.

The 300-second process and 10-second goal limits match the checked-in
FormalBench experiment/evaluation defaults and Frama-C command, respectively.
They do not reproduce its entire verifier configuration or toolchain. The
low-level FormalBench verifier API has a separate 1800-second default.

`scripts/check_support.py` now defaults to this local WSL configuration:
`x86_64`, `Typed+ref`, the checked-in Why3 compatibility file, and explicit
`Alt-Ergo:2.4.3,Z3:4.8.12` selection. It uses a 10-second goal limit, 300-second
target limit, 1000 MB solver memory limit, four concurrent prover processes,
and no proof cache. Qed simplification remains enabled. These defaults apply
to new runs; they do not change the configuration of previously recorded runs.

Override resources with `--timeout`, `--run-timeout`, `--memlimit`, and `--wp-par`.
Use `--why3-extra-config PATH` for another configuration, or
`--no-why3-extra-config --provers ...` for a different installed toolchain.
The runner checks the configuration file and uses it for both prover discovery
and verification. The default path is resolved from the script location, not
the shell's working directory.

It runs the selected implementation plus five positive clients and one negative
control by default; repeat `--target` to select a subset.
`check_int_row_components` is an optional diagnostic target.

The runner records input and Why3-configuration hashes, versions, commands,
stdout/stderr, and per-goal WP reports in `results/support_<UTC timestamp>/`.
Failed toolchain or generation prechecks stop the run before any proof targets
start. A zero exit code means the processes completed; it does not mean all
obligations proved. Constructor freshness warnings and unresolved call
preconditions still matter.

### Recommended runner commands

From PowerShell, this command runs just the small predicate-equivalence client
using the existing WSL distribution and opam switch:

```powershell
wsl.exe -d ResearchUbuntuNoble -u root -- bash -lc 'cd /mnt/d/ResearchProject && opam exec --switch=default -- python3 verification/java_arrays/scripts/check_support.py --target check_predicate_equivalence'
```

Inside the activated WSL Bash session described above, use:

```bash
# Selected implementation plus all six standard clients, 10 seconds per goal.
python3 verification/java_arrays/scripts/check_support.py

# Only the two row clients, 60 seconds per goal and 300 seconds per target.
python3 verification/java_arrays/scripts/check_support.py \
  --target check_int_rows --target check_double_rows --timeout 60

# Fast runner tests only; these do not launch proof attempts.
python3 -m unittest discover -s verification/java_arrays/scripts -p 'test_check_support.py' -v
```

The per-goal and per-target limits are independent: raising `--timeout` does
not raise `--run-timeout`. Increase the latter explicitly for a longer target
run. Memory limits are per prover process; account for `--wp-par` when raising
them. These examples do not imply that unresolved obligations will prove.

### Direct commands

After activating the opam switch and preparing the generated files, define the
shared options in the same Bash session. The examples use the tested corrected
driver with a 10-second per-goal budget; change it to 60 for a longer experiment.

```bash
wp_common=(
  -machdep x86_64 -wp -wp-rte -wp-model Typed+ref
  -wp-why3-extra-config verification/java_arrays/scripts/why3-alt-ergo-2.4.3.conf
  -wp-prover Alt-Ergo:2.4.3,Z3:4.8.12
  -wp-timeout 10 -wp-memlimit 1000 -wp-par 4 -wp-cache none
)
jarray_methods='check_length,check_reference,check_index,jarray_is_null,jarray_length,jarray_get,jarray_set,jbool_array_is_null,jbool_array_length,jbool_array_get,jbool_array_set,jdouble_array_is_null,jdouble_array_length,jdouble_array_get,jdouble_array_set,jarray2_is_null,jarray2_length,jarray2_get,jarray2_set,jdouble_array2_is_null,jdouble_array2_length,jdouble_array2_get,jdouble_array2_set'
timeout 300 frama-c "${wp_common[@]}" \
  -wp-fct "$jarray_methods" \
  verification/java_arrays/generated/drivers/baseline.c
```

This is the command for a future corrected-driver implementation run. The
latest recorded implementation result used the earlier automatic solver
selection; it has not been rerun with this configuration.

The selected list excludes constructors, allocation, error handling, and cleanup.
Their bodies remain mechanically generated; they are not reported as proved.
Record actual command lines, Frama-C and prover versions, model settings, input
hashes, warnings, and individual obligations under `results/`.

## Contract-interface clients

Each client includes contracted declarations and generated types only. Do not
include `generated/baseline.c` or the baseline driver in these runs. Reuse
`wp_common` from the same Bash session above:

```bash
for client in check_predicate_equivalence check_set_get check_scalar_families check_int_rows check_double_rows
do
  timeout 300 frama-c "${wp_common[@]}" \
    "verification/java_arrays/clients/$client.c"
done

# Run separately: the two named negative assertions must not be proved.
timeout 300 frama-c "${wp_common[@]}" \
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
| Unresolved | Umbrella term for any obligation without an established proof |
| Unknown | Solver could not determine a result; unrun obligations must also be identified separately |
| Timeout | Per-goal solver budget expired, or the overall target process was stopped at its limit |
| Failed | Prover/translation/execution error, such as parser failure or out of memory; not a counterexample |
| Trusted | Summary used without proving its implementation |
| Violation confirmed | Implementation contradicts the contract, with validated evidence |

See the [latest local summary](results/local-verification-summary-2026-09-16.md)
for the current validation record and the configuration differences between runs.
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
