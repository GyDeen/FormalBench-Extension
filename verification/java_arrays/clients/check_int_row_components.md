# Isolated integer-row diagnostics (2026-09-16)

The counts below are historical macOS diagnostics, not results from the current
AMD/WSL environment. See the [current setup](../README.md) and
[local run summary](../results/local-verification-summary-2026-09-16.md).

`check_int_row_components.c` reproduces rectangular construction, fetches both
row handles, updates the first row, and checks one component per function. It
stops before later getters can contribute preconditions. Original clients and
library contracts are unchanged. The last function is a separate guided
experiment: later assertions depend on earlier assertions and do not establish
success unless all obligations prove.

For new local diagnostics, enter the WSL/opam environment in the README and
run from `/mnt/d/ResearchProject`. These commands use the current `x86_64`,
`Typed+ref`, corrected Alt-Ergo/Z3 defaults and a 300-second target limit:

```bash
python3 verification/java_arrays/scripts/check_support.py --target check_int_row_components --timeout 60
python3 verification/java_arrays/scripts/check_support.py --target check_int_row_components --no-filter-init --timeout 60
```

These are new-run instructions, not reproductions of the historical counts.
The original commands used `--machdep macos_arm --model Typed --provers qed,z3`,
30 seconds per goal, and 240 seconds per target, without the local extra Why3
configuration. With the updated runner, reproducing that configuration also
requires `--no-why3-extra-config` and the original macOS toolchain.

## Historical results

The initial 14-function comparison used identical 30-second per-goal limits.
Default filtering proved 215/224 obligations; disabling initialization filtering
proved 221/224. Six standalone components changed from unknown to proved.
A printed metadata-validity goal confirmed that default filtering omitted the
constructor's composite matrix-validity hypothesis; disabling it retained it.
The explanatory dumps select assertions only, unlike the full RTE proof runs.

The extended run with filtering disabled proved 305/311 obligations:

| Isolated property after the element update | Result |
| --- | --- |
| Second-row metadata validity and initialization | Proved |
| Second-row buffer validity, initialization and separation | Proved |
| Whole second-row validity | Proved |
| Outer-matrix metadata/buffer validity, initialization and separation | Proved |
| Individual row 0 and row 1 validity | Proved |
| Individual row 0 and row 1 compatibility | Proved |
| Quantified row validity | Timeout |
| Quantified row compatibility | Timeout |
| Whole matrix validity | Timeout |

The guided function also timed out on second-row validity, quantified row
validity, and final matrix validity. This demonstrates context sensitivity;
its other successes do not establish a completed proof. The results localize
the remaining difficulty but do not prove the contracts insufficient. All call
preconditions in the extended run proved; only the six assertions above remain
unresolved. Constructors remain trusted and freshness warnings remain.

Run artifacts under `verification/java_arrays/results/`:

- `support_20260916T051629_456080Z`: initial default-filter run.
- `support_20260916T051641_464318Z`: initial run with filtering disabled.
- `support_20260916T051807_113949Z`: extended checks and
  `check_int_row_components/metadata-*-wp.txt` hypothesis dumps.

Each run records input hashes and commands. The initial source had 14 functions;
the extended source adds four individual-row checks and one guided experiment.
The generation precheck reports the pre-existing missing generated manifest;
no production or generated source was changed by this diagnostic work.
