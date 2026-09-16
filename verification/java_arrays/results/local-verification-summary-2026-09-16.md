# Local JArray verification summary - 2026-09-16

The support layer is **partially verified**. The latest completed implementation
run proved 81/193 reported WP goals. The latest double-row client proved 103/119
after correcting the Alt-Ergo driver; the latest integer-row client proved
111/119. These are the most recent results per target, not one suite run with
a uniform solver configuration. No implementation-contract violation was
confirmed by these runs.

## Environment and scope

- Machine: AMD Ryzen 7 7700, Windows, WSL 2 (`ResearchUbuntuNoble`),
  Ubuntu 24.04.5 LTS; about 15.5 GB RAM available to WSL in the diagnostic review.
- Tools: Frama-C 33.0 (Arsenic), Why3 1.8.2, Alt-Ergo 2.4.3-free,
  Z3 4.8.12, OCaml 4.14.1, Python 3.12.3; opam switch `default`.
- Common full-run settings: `-machdep x86_64 -wp -wp-rte -wp-model Typed+ref`,
  four prover processes, no proof cache, default 1000 MB per solver, and
  300 seconds per target process. Per-goal limits are recorded below.
- Z3 5.1.0 was used only for a focused exported-task diagnostic. It was not the
  Z3 version used by the completed implementation/client runs.
- Implementation scope: 23 selected helpers and array accessors/row operations.
  Constructors, allocation/error infrastructure, and cleanup bodies remain
  outside the proved implementation scope.
- Clients include contracts and generated types without implementation bodies.
  They test use of the interface under its trusted constructor assumptions.

The three `support_*` runs below passed the generated-input consistency check.
During the later diagnostic review, the six contract files and double-row client
still matched the input hashes recorded in the 60-second run. The corrected
driver experiments changed prover configuration, not the client or contracts.

The [verification README](../README.md) documents the toolchain and executable
commands. The 300-second process/10-second goal budget follows the checked-in
FormalBench defaults, but the complete verifier flags and toolchain differ.
This record does not establish a performance comparison against the Apple M3.

## Most recent completed result per target

Counts use `reported_goals`/per-goal `wp.json`, excluding extra console-only
control-flow goals. All listed processes completed within their 300-second wall
limit and exited zero; this does not imply complete proof success.

| Target | Seconds per goal | Proved | Remaining reported verdicts | Runtime | Evidence |
| --- | ---: | ---: | --- | ---: | --- |
| Implementation | 10 | 81/193 | 112 timeout | 294.71 s | A |
| Predicate equivalence | 10 | 10/10 | None | 2.18 s | B |
| Set/get | 10 | 59/59 | None | 2.48 s | B |
| Scalar families | 10 | 51/51 | None | 2.59 s | B |
| Integer rows | 60 | 111/119 | 8 timeout | 131.50 s | C |
| Double rows | 10 | 103/119 | 16 timeout | 80.92 s | D: corrected driver, initialization filtering disabled |
| Negative set/get control | 10 | 23/25 | 1 failed, 1 unknown, both intentional negative assertions | 3.84 s | B |

A/B/C used automatic `Alt-Ergo,Z3` selection. Alt-Ergo's version was detected
as empty and its driver was incompatible with the installed executable. D
explicitly selected the compatible `Alt-Ergo:2.4.3` driver and Z3 4.8.12.
The implementation and other clients have **not** been rerun with that corrected
configuration. Do not treat their earlier counts as corrected-driver results.
The optional `check_int_row_components.c` diagnostic has no new local run in
this record; the six standard client files are accounted for above.

The implementation console says 91/203 because it additionally counts five
automatic termination and five unreachable-exit goals. Its JSON goal report is
81/193. Predicate equivalence similarly has a console count of 20/20 versus
10/10 reported goals. These are different counting conventions, not missing
or newly proved program assertions.

The negative assertions deliberately expect 6.0 after setting 5.0. Both remain
unproved, and the other 23 obligations proved. A solver `failed`/`unknown`
result is not itself a validated counterexample or a proof of inconsistency.

## Effect of time limits and the corrected driver

All rows below use the same respective client source/contracts and a 300-second
target limit. The corrected-driver review added detailed proof output; some
diagnostics overlapped, so their runtimes are not a controlled CPU benchmark.

| Client/configuration | Goal limit | Proved | Failed | Unknown | Timeout | Runtime |
| --- | ---: | ---: | ---: | ---: | ---: | ---: |
| Integer rows, automatic driver (B) | 10 s | 111/119 | 0 | 0 | 8 | 31.76 s |
| Integer rows, automatic driver (C) | 60 s | 111/119 | 0 | 0 | 8 | 131.50 s |
| Double rows, automatic driver (B) | 10 s | 97/119 | 6 | 4 | 12 | 44.48 s |
| Double rows, automatic driver (C) | 60 s | 97/119 | 7 | 5 | 10 | 196.70 s |
| Double rows, corrected driver (D: `corrected_driver_full`) | 10 s | 103/119 | 0 | 0 | 16 | 81.88 s |
| Double rows, corrected driver and no initialization filtering (D: `corrected_driver_no_filter`) | 10 s | 103/119 | 0 | 0 | 16 | 80.92 s |

Increasing the goal limit from 10 to 60 seconds did not increase either row
client's proof count with the original configuration. Correcting the driver
proved six additional double-row goals at 10 seconds. Disabling initialization
filtering produced no further improvement in the full double-row client.

## Double-row failure diagnosis

All seven goals classified `failed` in C were reproduced under the same
60-second goal limit with detailed output (D: `reproduce`, 196.09 seconds).
Each showed both an Alt-Ergo unrecognized-answer error and Z3 out of memory.
`Failed` is an execution/prover error, distinct from `Invalid`; these were not
confirmed false assertions or runtime bugs.

- **Alt-Ergo compatibility:** automatic detection mishandled `2.4.3-free`,
  resulting in an empty version and an incompatible polymorphic SMT-LIB
  driver. Replaying an exported task produced a syntax error. The explicit
  legacy `alt_ergo` driver works with the installed 2.4.3 executable. The
  repository now retains a portable
  [compatibility configuration](../scripts/why3-alt-ergo-2.4.3.conf), equivalent
  to the diagnostic configuration except that it resolves `alt-ergo` on PATH.
- **Z3 memory:** all seven failed goals exhausted the 1000 MB solver allowance.
  A focused independence task with Z3 4.8.12 and 4000 MB avoided immediate OOM
  but timed out at 15 seconds. Z3 5.1.0 still ran out of memory at 1000 MB in
  11.47 seconds. Neither experiment proved the task.
- **Confirmed driver benefit:** with the proper driver, Alt-Ergo proved the
  isolated row-independence assertion in 0.244 seconds. Subsequent full-client
  runs included call preconditions and RTE obligations and proved 103/119.

The original seven failed goals map to these lines in
`clients/check_double_rows.c`:

| Line | Obligation | Latest corrected-driver verdict |
| --- | --- | --- |
| 28 | Matrix validity before reading its length | Timeout |
| 29 | Updated element is 5.0 and the independent row retains 0.0 | Proved |
| 54 | Displaced row is valid before reading `old_value` | Timeout |
| 55 | Replacement and shared-row handles are preserved | Timeout |
| 56 | Replacement, shared-row, and outer lengths are correct | Proved |
| 57 | Shared and displaced rows retain their expected values | Timeout |
| 61 | Removed replacement row remains valid after clearing its slot | Timeout |

An aggregate timeout can mask an error from another prover; Z3 still has memory
failures in some corrected-driver tasks. Consult per-prover diagnostics before
classifying an unresolved goal's cause.

## Remaining limits

The unresolved goals involve quantified row validity, compatibility, and
preservation across element writes and row replacement. Smaller, proved frame
lemmas and explicit instantiation of row predicates are potential next steps;
the evidence does not establish that the contracts themselves are insufficient.

WP ignores constructor `\fresh` clauses in this configuration, so a supported,
justified representation of allocation/freshness is still needed. Missing
termination/exit defaults, pointer-result dependency warnings, and skipped
alignment/function-pointer RTE guards also remain. No proof establishes full
allocator behavior, cleanup, or floating-point special-value coverage.

An assertion proved after an unresolved call precondition remains conditional
on that precondition. Passing these clients alone would not prove the library
bodies or justify freezing the entire benchmark support layer.

## Local evidence and repository retention

These identifiers locate raw artifacts on the originating machine; they are
intentionally not links or dependencies required to read this tracked summary:

- A: `results/support_20260916T125404_522553Z/` (implementation).
- B: `results/support_20260916T130514_171185Z/` (six standard clients).
- C: `results/support_20260916T131317_536086Z/` (60-second row clients).
- D: `results/double_rows_review_20260916/` (error reproduction, corrected
  driver runs, initialization-filter comparisons, and focused solver tests).

Only top-level Markdown summaries under `results/` are eligible for Git tracking.
Raw run directories, reports, solver tasks, and diagnostic scripts are ignored
and retained locally. Reusable source/configuration belongs in `scripts/`.
The [older macOS record](contract-update-status.md) remains historical evidence.
