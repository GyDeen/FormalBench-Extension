# Completed strict completeness check — 26 October 2026

The resumed Java run completed the remaining 20 mutants. Both language cohorts now have a complete check of the fixed saved/bounded input pools. No evaluation process remains running. The earlier stopped checkpoint is retained as historical evidence.

A strict pass requires every normally returned mutant output that differs from the original to be rejected by the frozen executable postcondition, across the full input pool. A single accepted faulty output would fail the mutant.

| Language | Mutants checked | Strict passes | Strict failures | No differing normal return | Unresolved mutants |
|---|---:|---:|---:|---:|---:|
| java | 192 | 192 | 0 | 0 | 0 |
| c | 80 | 79 | 0 | 1 | 0 |

| Language | Mutant/input pairs checked | Differing returns rejected | Differing returns accepted | Matching returns | Safety failures | Unresolved pairs |
|---|---:|---:|---:|---:|---:|---:|
| java | 251496 | 120662 | 0 | 126852 | 3982 | 0 |
| c | 89890 | 31385 | 0 | 55745 | 2760 | 0 |

All 192 Java mutants pass. For C, all 79 mutants with differing normally returned outputs pass; MaxSubArraySum mutant 1 has matching returns or safety failures, with no differing normally returned output. All 80 C mutants were examined. The C safety-only case is not counted as a return-output pass.

The strict rate among mutants with differing normal returns is \(100\%\) in both languages. Unlike the old first-witness result, this checks every such differing output in the fixed pool: 120,662 Java outputs and 31,385 C outputs, with none accepted.

These are results over finite input pools and executable return/content postconditions. Safety failures remain separate. Java FindPoints freshness is unchecked symmetrically; Java frame coverage is unconfirmed; C uses executable equivalents of the frozen return/content clauses. The results do not establish universal completeness or full-clause coverage.

## Validation

- All 341,386 expected mutant/input pairs were examined, with zero missing or unresolved pairs. All 272 mutant case records mark their full input pools as exhausted.
- Zero recorded timeouts or evaluation failures. All 13 Java and six C originals passed their full pools.
- Input pools exactly match the historical archive; all 13,652 Java and 6,077 C original outputs match archived returned values.
- Frozen contract copies match the verifier archive by direct JSON comparison. No digest validation was used.
- Twelve regression checks pass, including mixed accepted/rejected faulty outputs, missing-input handling and default-workflow integration.
- OpenJML 21.0.27 macOS arm64 was used from the project-local tool directory. C exhaustive search/replay limits were two/five seconds. Java replay uses separate interpreted processes per case with fresh classloaders per input. The generated case records retain these runtime settings.

## Run the strict check

Run from the repository root with Python 3, a C compiler with AddressSanitizer/UndefinedBehaviorSanitizer support, and OpenJML 21.0.27. Set `OPENJML_HOME` to the OpenJML installation directory; otherwise the runner defaults to `/root/tools/openjml`.

```sh
OPENJML_HOME=/path/to/openjml python3 -m verification.specification_evaluation.execution.completeness \
  --study verification/specification_evaluation/results/verifier_based \
  --runtime-output output/strict_completeness_runtime \
  --output output/strict_completeness_dataset \
  --workers 4
```

The runner uses the retained verified-original cohorts, frozen contracts, mutant selection manifest, and saved/bounded input recipe already in the repository. It attempts every mutant input after a rejection. Completed case records are reused on resume; interrupted cases rerun from their first input. Optional `--language java` or `--language c` and repeated `--program NAME` restrict the run.

`--runtime-output` stores raw trials and controls. `--output` stores the Markdown report, per-mutant summary and per-input JSONL dataset. These generated records remain local and are not included in this commit. Use separate directories from the historical archive.

For existing runtime evidence, reconstruct the strict result without executing programs:

```sh
python3 -m verification.specification_evaluation.execution.input_completeness \
  --source output/strict_completeness_runtime \
  --output output/strict_completeness_reconstructed
```

Regression checks:

```sh
python3 -m unittest verification.specification_evaluation.execution.input_completeness_checks
```
