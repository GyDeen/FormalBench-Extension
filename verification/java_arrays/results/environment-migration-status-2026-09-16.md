# WSL verification migration handoff (2026-09-16)

Status updated on 2026-09-17 (Australia/Sydney): the remaining migration work
is complete, with regression tests and a small WSL integration check passing.
Changes remain in the working tree; this task did not commit or push them.
The original stopping-point record below is retained for context.

## Completion checks (2026-09-17)

- Fixed configuration-flag mutual exclusion by applying the default after
  argument parsing. Explicit configuration and disable flags are now rejected
  together, including the default path and either argument order.
- All **11 regression tests passed** in the existing WSL/opam environment.
- The `check_predicate_equivalence` integration check proved **10/10 reported
  WP goals**, with no unresolved goals, in 2.18 seconds. Its console reports
  20/20 including automatic goals. This checks runner integration, not the
  unresolved implementation or row proofs.
- Run ID: `support_20260916T161957_494904Z` (UTC timestamp; September 17 in
  Australia/Sydney). Metadata confirms Frama-C 33.0, Alt-Ergo 2.4.3, Z3 4.8.12,
  `x86_64`, `Typed+ref`, the compatibility configuration and its hash, and
  limits of 10 seconds/goal, 300 seconds/target, 1000 MB/process, four provers.
- All three prechecks passed: Frama-C version, configured prover discovery,
  and generated-source consistency. WP still warns that alignment and invalid
  function-pointer-call RTE guards are unsupported.
- Corrected `.gitignore` so the scripts allowlist does not expose generated
  Python bytecode. Confirmed both bytecode and raw smoke-test results are
  ignored; local artifacts were retained.
- Reviewed the changes and passed `git diff --check`. Production sources,
  contracts, and pre-existing generated-file changes were left untouched.
- No full implementation/client suite was rerun. Prior full-run findings in
  [the local summary](local-verification-summary-2026-09-16.md) are unchanged.

## Changes made

- Updated `scripts/check_support.py` from the historical macOS defaults to the
  local WSL setup: `x86_64`, `Typed+ref`, explicit `Alt-Ergo:2.4.3,Z3:4.8.12`,
  and the existing checked-in `scripts/why3-alt-ergo-2.4.3.conf`.
- Set defaults to 10 seconds per prover goal, 300 seconds per target, 1000 MB
  per prover process, four concurrent prover processes, and no proof cache.
  A 60-second goal budget remains selectable with `--timeout 60`.
- Added configurable memory, parallelism, and Why3 configuration options.
  Prover discovery and proof commands now use the same configuration file;
  metadata records its path and SHA-256 hash.
- Generation checks now use the running Python interpreter. Failed prechecks
  stop before proof targets start. Missing Frama-C reports WSL/opam guidance.
- Updated `README.md` with current runner defaults and PowerShell/WSL commands.
- Updated `clients/check_int_row_components.md` with local execution commands
  and explicit historical labels for its original Mac results.
- Labeled `results/contract-update-status.md` as historical and made its Mac
  reproduction flags explicit. Historical proof counts were not changed.
- Added `scripts/test_check_support.py` with 11 lightweight regression tests.

Production sources and ACSL contracts were not changed. Existing generated-file
changes and the unrelated `FormalBench-Extension-Report/` directory were left
untouched. Existing raw-result ignore rules were preserved; this handoff is a
curated top-level Markdown summary.

## Checks at the original stopping point (2026-09-16)

- In `ResearchUbuntuNoble` WSL, `prepare.py --check` passed: generated baseline
  and manifest source data are current.
- Regression tests: **10 passed, 1 failed**. The failing test is
  `test_invalid_arguments_are_rejected`, specifically passing both
  `--why3-extra-config` with the default configuration path and
  `--no-why3-extra-config`. The parser accepted this combination instead of
  rejecting it. This remains unfixed at the stopping point.
- `git diff --check` passed. Git emitted line-ending conversion warnings during
  diff inspection, consistent with the Windows checkout configuration.
- No verification proofs, full implementation/client runs, or runtime baseline
  tests were launched during this migration task. Existing proof results remain
  those in [the local run summary](local-verification-summary-2026-09-16.md).

## Original follow-up checklist (completed on 2026-09-17)

1. Fix the mutually exclusive configuration-flag validation, including the
   case where the explicit configuration equals its default.
2. Rerun the lightweight tests:

   ```bash
   python3 -m unittest discover -s verification/java_arrays/scripts -p 'test_check_support.py' -v
   ```

3. Validate the updated runner against the installed toolchain with a small
   smoke test if requested, then review the final diff. No full-suite rerun has
   been requested as part of this handoff.

Run commands inside the existing WSL distribution with the default opam switch
active, as described in [the verification README](../README.md).
