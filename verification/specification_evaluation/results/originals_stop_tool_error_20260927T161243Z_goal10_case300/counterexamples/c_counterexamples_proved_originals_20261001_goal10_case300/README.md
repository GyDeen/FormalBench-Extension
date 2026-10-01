# C counterexample follow-up

Source experiment: `originals_stop_tool_error_20260927T161243Z_goal10_case300`. Completed 86/86 cases. The original experiment is unchanged.

## Method

WP uses the same frozen sources/contracts and 10-second goal / 300-second process budgets, with Z3 model generation enabled. Original WP outcomes remain separate from concrete replay outcomes. Full model text and generated queries are retained locally.

WP can time out because later quantified library axioms make model completion difficult. Preliminary incremental SMT queries are used only to propose scalar inputs, and are explicitly labelled partial. Their SAT results never establish rejection. A fixed bounded search (seed 726) supplies additional candidates, including valid arrays. Passing any finite search does not prove a mutant.

A functional rejection requires inputs satisfying the hash-pinned original preconditions, successful execution of the unchanged C function, no UBSan diagnostic, and a returned value violating an executable equivalent of the frozen postcondition. Runtime safety failures are reported separately. Replay oracles cover only these six frozen specifications; changed hashes are refused.

Java verdicts in the source run are tool-reported outcomes, not replay-validated outcomes. The supplemental C counts must not be treated as a directly matched Java/C proof-rejection rate.

## Outcomes

| Population | WP outcomes | WP plus validated replay outcomes |
| --- | --- | --- |
| original | {"proved": 6} | {"proved": 6} |
| mutant | {"unknown/timeout": 80} | {"precondition/RTE failure": 1, "specification violation": 79} |

## By program

| Program | Original outcomes | Mutant outcomes |
| --- | --- | --- |
| CountList | {"proved": 1} | {"specification violation": 3} |
| MaxOfTwo | {"proved": 1} | {"specification violation": 2} |
| MaxSubArraySum | {"proved": 1} | {"precondition/RTE failure": 1, "specification violation": 22} |
| OddBitSetNumber | {"proved": 1} | {"specification violation": 17} |
| SumNums | {"proved": 1} | {"specification violation": 13} |
| TestThreeEqual | {"proved": 1} | {"specification violation": 22} |

## Validated functional witnesses

| Case | Candidate origin | Inputs | Expected | Actual |
| --- | --- | --- | --- | --- |
| [CountList/2](cases/CountList/mutant_2/c/replay.json) | bounded_search_seed726 | `{"inputArray": [[0]]}` | 1 | 0 |
| [CountList/1](cases/CountList/mutant_1/c/replay.json) | bounded_search_seed726 | `{"inputArray": [[]]}` | 0 | 1 |
| [MaxOfTwo/1](cases/MaxOfTwo/mutant_1/c/replay.json) | wp_partial_model:0 | `{"x": 0, "y": 1}` | 1 | 0 |
| [CountList/3](cases/CountList/mutant_3/c/replay.json) | bounded_search_seed726 | `{"inputArray": [[0]]}` | 1 | 0 |
| [MaxOfTwo/3](cases/MaxOfTwo/mutant_3/c/replay.json) | wp_partial_model:0 | `{"x": 0, "y": -1}` | 0 | -1 |
| [MaxSubArraySum/4](cases/MaxSubArraySum/mutant_4/c/replay.json) | bounded_search_seed726 | `{"a": [0, 1], "size": 2}` | 2 | 1 |
| [MaxSubArraySum/6](cases/MaxSubArraySum/mutant_6/c/replay.json) | bounded_search_seed726 | `{"a": [0, 0], "size": 2}` | 1 | 2 |
| [MaxSubArraySum/7](cases/MaxSubArraySum/mutant_7/c/replay.json) | bounded_search_seed726 | `{"a": [0, 1], "size": 2}` | 2 | 1 |
| [MaxSubArraySum/8](cases/MaxSubArraySum/mutant_8/c/replay.json) | bounded_search_seed726 | `{"a": [1, 0], "size": 2}` | 1 | 2 |
| [MaxSubArraySum/9](cases/MaxSubArraySum/mutant_9/c/replay.json) | bounded_search_seed726 | `{"a": [-1, 1], "size": 2}` | 1 | 2 |
| [MaxSubArraySum/10](cases/MaxSubArraySum/mutant_10/c/replay.json) | bounded_search_seed726 | `{"a": [-1, 1], "size": 2}` | 1 | 0 |
| [MaxSubArraySum/12](cases/MaxSubArraySum/mutant_12/c/replay.json) | bounded_search_seed726 | `{"a": [0, 1], "size": 2}` | 2 | 1 |
| [MaxSubArraySum/13](cases/MaxSubArraySum/mutant_13/c/replay.json) | bounded_search_seed726 | `{"a": [-1, 0, 1], "size": 3}` | 2 | 1 |
| [MaxSubArraySum/14](cases/MaxSubArraySum/mutant_14/c/replay.json) | bounded_search_seed726 | `{"a": [-1, 0, 1], "size": 3}` | 2 | 1 |
| [MaxSubArraySum/15](cases/MaxSubArraySum/mutant_15/c/replay.json) | bounded_search_seed726 | `{"a": [-1, 1], "size": 2}` | 1 | 2 |
| [MaxSubArraySum/16](cases/MaxSubArraySum/mutant_16/c/replay.json) | bounded_search_seed726 | `{"a": [-1, 1], "size": 2}` | 1 | 2 |
| [MaxSubArraySum/17](cases/MaxSubArraySum/mutant_17/c/replay.json) | bounded_search_seed726 | `{"a": [-1, 1], "size": 2}` | 1 | 3 |
| [MaxSubArraySum/18](cases/MaxSubArraySum/mutant_18/c/replay.json) | bounded_search_seed726 | `{"a": [-1, 1], "size": 2}` | 1 | 2 |
| [MaxSubArraySum/19](cases/MaxSubArraySum/mutant_19/c/replay.json) | bounded_search_seed726 | `{"a": [-1, 1], "size": 2}` | 1 | 2 |
| [MaxSubArraySum/21](cases/MaxSubArraySum/mutant_21/c/replay.json) | bounded_search_seed726 | `{"a": [-1, 1], "size": 2}` | 1 | 2 |
| [MaxSubArraySum/20](cases/MaxSubArraySum/mutant_20/c/replay.json) | bounded_search_seed726 | `{"a": [-1, 0, 1], "size": 3}` | 2 | 1 |
| [MaxSubArraySum/22](cases/MaxSubArraySum/mutant_22/c/replay.json) | bounded_search_seed726 | `{"a": [-1, 1], "size": 2}` | 1 | 3 |
| [MaxSubArraySum/24](cases/MaxSubArraySum/mutant_24/c/replay.json) | bounded_search_seed726 | `{"a": null, "size": 0}` | 1 | 0 |
| [MaxSubArraySum/23](cases/MaxSubArraySum/mutant_23/c/replay.json) | bounded_search_seed726 | `{"a": [-1, 1], "size": 2}` | 1 | 2 |
| [MaxSubArraySum/26](cases/MaxSubArraySum/mutant_26/c/replay.json) | bounded_search_seed726 | `{"a": null, "size": 0}` | 1 | -1 |
| [MaxSubArraySum/25](cases/MaxSubArraySum/mutant_25/c/replay.json) | bounded_search_seed726 | `{"a": null, "size": 0}` | 1 | 0 |
| [MaxSubArraySum/27](cases/MaxSubArraySum/mutant_27/c/replay.json) | bounded_search_seed726 | `{"a": null, "size": 0}` | 1 | 0 |
| [OddBitSetNumber/1](cases/OddBitSetNumber/mutant_1/c/replay.json) | wp_partial_model:1 | `{"n": 0}` | 0 | -1 |
| [OddBitSetNumber/2](cases/OddBitSetNumber/mutant_2/c/replay.json) | wp_partial_model:1 | `{"n": 0}` | 0 | -1 |
| [OddBitSetNumber/3](cases/OddBitSetNumber/mutant_3/c/replay.json) | wp_partial_model:1 | `{"n": 0}` | 0 | 1431655765 |
| [OddBitSetNumber/4](cases/OddBitSetNumber/mutant_4/c/replay.json) | wp_partial_model:1 | `{"n": 0}` | 0 | 1431655765 |
| [OddBitSetNumber/5](cases/OddBitSetNumber/mutant_5/c/replay.json) | wp_partial_model:25 | `{"n": -2147483610}` | -394231753 | -1467973529 |
| [OddBitSetNumber/7](cases/OddBitSetNumber/mutant_7/c/replay.json) | wp_partial_model:18 | `{"n": -2147483610}` | -394231753 | -1467973593 |
| [OddBitSetNumber/8](cases/OddBitSetNumber/mutant_8/c/replay.json) | wp_partial_model:1 | `{"n": 0}` | 0 | 858993459 |
| [OddBitSetNumber/9](cases/OddBitSetNumber/mutant_9/c/replay.json) | wp_partial_model:1 | `{"n": 0}` | 0 | 858993459 |
| [OddBitSetNumber/12](cases/OddBitSetNumber/mutant_12/c/replay.json) | wp_partial_model:18 | `{"n": -2147483610}` | -394231753 | -931102665 |
| [OddBitSetNumber/10](cases/OddBitSetNumber/mutant_10/c/replay.json) | wp_partial_model:25 | `{"n": -2147483610}` | -394231753 | -931102665 |
| [OddBitSetNumber/13](cases/OddBitSetNumber/mutant_13/c/replay.json) | wp_partial_model:1 | `{"n": 0}` | 0 | 252645135 |
| [OddBitSetNumber/14](cases/OddBitSetNumber/mutant_14/c/replay.json) | wp_partial_model:1 | `{"n": 0}` | 0 | 252645135 |
| [OddBitSetNumber/18](cases/OddBitSetNumber/mutant_18/c/replay.json) | wp_partial_model:1 | `{"n": 0}` | 0 | 16711935 |
| [OddBitSetNumber/19](cases/OddBitSetNumber/mutant_19/c/replay.json) | wp_partial_model:1 | `{"n": 0}` | 0 | 16711935 |
| [OddBitSetNumber/20](cases/OddBitSetNumber/mutant_20/c/replay.json) | wp_partial_model:25 | `{"n": -2147483610}` | -394231753 | -402620361 |
| [OddBitSetNumber/23](cases/OddBitSetNumber/mutant_23/c/replay.json) | wp_partial_model:1 | `{"n": 0}` | 0 | 65535 |
| [SumNums/1](cases/SumNums/mutant_1/c/replay.json) | bounded_search_seed726 | `{"m": -4, "n": -4, "x": -2, "y": -2}` | 20 | 0 |
| [SumNums/2](cases/SumNums/mutant_2/c/replay.json) | wp_partial_model:1 | `{"m": 0, "n": 0, "x": -2, "y": 0}` | -2 | 20 |
| [SumNums/3](cases/SumNums/mutant_3/c/replay.json) | bounded_search_seed726 | `{"m": -4, "n": -4, "x": -2, "y": -2}` | 20 | 0 |
| [SumNums/4](cases/SumNums/mutant_4/c/replay.json) | bounded_search_seed726 | `{"m": -4, "n": -4, "x": -2, "y": -2}` | 20 | 1 |
| [OddBitSetNumber/24](cases/OddBitSetNumber/mutant_24/c/replay.json) | wp_partial_model:1 | `{"n": 0}` | 0 | 65535 |
| [SumNums/5](cases/SumNums/mutant_5/c/replay.json) | wp_partial_model:1 | `{"m": 20, "n": 21, "x": 21, "y": 0}` | 20 | 21 |
| [SumNums/6](cases/SumNums/mutant_6/c/replay.json) | wp_partial_model:1 | `{"m": 21, "n": 21, "x": 21, "y": 0}` | 20 | 21 |
| [SumNums/7](cases/SumNums/mutant_7/c/replay.json) | wp_partial_model:1 | `{"m": 22, "n": 21, "x": 21, "y": 0}` | 21 | 20 |
| [SumNums/8](cases/SumNums/mutant_8/c/replay.json) | wp_partial_model:1 | `{"m": 0, "n": 0, "x": 0, "y": 0}` | 20 | 0 |
| [SumNums/9](cases/SumNums/mutant_9/c/replay.json) | wp_partial_model:1 | `{"m": 0, "n": 22, "x": 21, "y": 0}` | 20 | 21 |
| [SumNums/10](cases/SumNums/mutant_10/c/replay.json) | wp_partial_model:1 | `{"m": 0, "n": 20, "x": 21, "y": 0}` | 21 | 20 |
| [SumNums/12](cases/SumNums/mutant_12/c/replay.json) | wp_partial_model:1 | `{"m": 0, "n": 21, "x": 21, "y": 0}` | 20 | 21 |
| [SumNums/13](cases/SumNums/mutant_13/c/replay.json) | wp_partial_model:1 | `{"m": 0, "n": 20, "x": 21, "y": 0}` | 21 | 20 |
| [SumNums/14](cases/SumNums/mutant_14/c/replay.json) | wp_partial_model:1 | `{"m": 22, "n": 21, "x": 21, "y": 0}` | 21 | 20 |
| [TestThreeEqual/1](cases/TestThreeEqual/mutant_1/c/replay.json) | wp_partial_model:0 | `{"x": -1, "y": 0, "z": 0}` | 2 | 3 |
| [TestThreeEqual/3](cases/TestThreeEqual/mutant_3/c/replay.json) | wp_partial_model:0 | `{"x": 0, "y": 0, "z": 0}` | 3 | 2 |
| [TestThreeEqual/4](cases/TestThreeEqual/mutant_4/c/replay.json) | wp_partial_model:0 | `{"x": 0, "y": 0, "z": 1}` | 2 | 3 |
| [TestThreeEqual/5](cases/TestThreeEqual/mutant_5/c/replay.json) | wp_partial_model:0 | `{"x": 0, "y": 0, "z": -1}` | 2 | 3 |
| [TestThreeEqual/6](cases/TestThreeEqual/mutant_6/c/replay.json) | wp_partial_model:0 | `{"x": 0, "y": 0, "z": 0}` | 3 | 2 |
| [TestThreeEqual/7](cases/TestThreeEqual/mutant_7/c/replay.json) | wp_partial_model:0 | `{"x": 2, "y": 1, "z": 0}` | 0 | 3 |
| [TestThreeEqual/8](cases/TestThreeEqual/mutant_8/c/replay.json) | wp_partial_model:0 | `{"x": 0, "y": 0, "z": 0}` | 3 | 2 |
| [TestThreeEqual/9](cases/TestThreeEqual/mutant_9/c/replay.json) | wp_partial_model:0 | `{"x": 0, "y": 0, "z": -1}` | 2 | 3 |
| [TestThreeEqual/10](cases/TestThreeEqual/mutant_10/c/replay.json) | wp_partial_model:0 | `{"x": 1, "y": 0, "z": 0}` | 2 | 3 |
| [TestThreeEqual/11](cases/TestThreeEqual/mutant_11/c/replay.json) | wp_partial_model:0 | `{"x": 0, "y": 1, "z": -1}` | 0 | 2 |
| [TestThreeEqual/13](cases/TestThreeEqual/mutant_13/c/replay.json) | wp_partial_model:0 | `{"x": 0, "y": 0, "z": -1}` | 2 | 0 |
| [TestThreeEqual/15](cases/TestThreeEqual/mutant_15/c/replay.json) | wp_partial_model:0 | `{"x": -2, "y": 0, "z": -1}` | 0 | 2 |
| [TestThreeEqual/16](cases/TestThreeEqual/mutant_16/c/replay.json) | wp_partial_model:0 | `{"x": -1, "y": 0, "z": 0}` | 2 | 0 |
| [TestThreeEqual/18](cases/TestThreeEqual/mutant_18/c/replay.json) | wp_partial_model:0 | `{"x": -1, "y": 0, "z": 0}` | 2 | 0 |
| [TestThreeEqual/19](cases/TestThreeEqual/mutant_19/c/replay.json) | wp_partial_model:0 | `{"x": 0, "y": 0, "z": -1}` | 2 | 0 |
| [TestThreeEqual/20](cases/TestThreeEqual/mutant_20/c/replay.json) | wp_partial_model:0 | `{"x": 1, "y": 2, "z": 0}` | 0 | 2 |
| [TestThreeEqual/21](cases/TestThreeEqual/mutant_21/c/replay.json) | wp_partial_model:0 | `{"x": 0, "y": 2, "z": 1}` | 0 | 2 |
| [TestThreeEqual/22](cases/TestThreeEqual/mutant_22/c/replay.json) | wp_partial_model:0 | `{"x": 0, "y": 1, "z": -1}` | 0 | 2 |
| [TestThreeEqual/23](cases/TestThreeEqual/mutant_23/c/replay.json) | wp_partial_model:0 | `{"x": 0, "y": -1, "z": 0}` | 2 | 0 |
| [TestThreeEqual/25](cases/TestThreeEqual/mutant_25/c/replay.json) | wp_partial_model:0 | `{"x": 0, "y": -1, "z": 0}` | 2 | 0 |
| [TestThreeEqual/26](cases/TestThreeEqual/mutant_26/c/replay.json) | wp_partial_model:0 | `{"x": 1, "y": 0, "z": 0}` | 2 | 0 |
| [TestThreeEqual/27](cases/TestThreeEqual/mutant_27/c/replay.json) | wp_partial_model:0 | `{"x": 1, "y": 2, "z": 0}` | 0 | 2 |

## Evidence

See `run.json`, `summary.json`, case `record.json`, `wp-report.json`, `native_compile.json`, and `replay.json`. Solver `.smt2` files and native binaries are diagnostic artifacts and need not be committed.
## Independent audit

All **79 functional counterexamples and one runtime-safety witness** reproduced under a second compilation with `-O1`, uninitialized-value diagnostics, ASan and UBSan. For every witness, the proved original executed cleanly and returned the value required by the frozen postcondition. The six original controls also passed all 889 finite replay trials. See [audit.json](audit.json) for compiler/solver identities, source hashes and per-witness checks.

No full WP model was captured. The 51 scalar functional witnesses proposed by preliminary SMT models and the 28 found by bounded search became confirmed faults only after executable replay. The 80 WP mutant proof outcomes remain unknown/timeout. These supplemental outcomes are separate from the original Java/C proof comparison.

To repeat the independent audit from the repository root:

```bash
python3 -m verification.specification_evaluation.audit_c_counterexamples \
  --output verification/specification_evaluation/results/c_counterexamples_proved_originals_20261001_goal10_case300
```
