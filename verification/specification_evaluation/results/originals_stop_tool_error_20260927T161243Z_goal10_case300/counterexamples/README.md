# Integrated C counterexample evidence

Unique coverage: **50 originals and 977/977 mutants**. The full-population replay search completed and passed its independent audit.

Validated mutant failures: **710 postcondition violations and 190 safety failures**. Every accepted witness reproduced under the stronger sanitizer audit and passed its original control.

The original verifier results are preserved in [summary_verification.json](../summary_verification.json). The parent [summary.json](../summary.json) adds this evidence as `c_counterexample_evidence`; it does not rewrite WP unknown verdicts. Passing finite replay trials is not proof. No counterexample search is currently running.

## Category coverage

| Category | Eligible | Searched | Postcondition violations | Safety failures | Searched, no validated failure | Not searched |
| --- | --- | --- | --- | --- | --- | --- |
| branch | 193 | 193 | 143 | 42 | 8 | 0 |
| multi_path_loop | 239 | 239 | 171 | 28 | 40 | 0 |
| nested | 219 | 219 | 131 | 68 | 20 | 0 |
| sequential | 143 | 143 | 143 | 0 | 0 | 0 |
| single_path_loop | 183 | 183 | 122 | 52 | 9 | 0 |

## Archived runs

- [c_counterexamples_refreshed_all_c_20261001_search_only](c_counterexamples_refreshed_all_c_20261001_search_only/README.md): 1027/1027 cases; complete.

## Source versions

No current C source changes were detected relative to the refreshed experiment records. Refreshed originals: MoveFirst, MultiplyElements, NextPowerOf2, PairWise. Their replay results use the refreshed frozen contracts.

## Witnesses

| Case | Kind | Inputs | Expected | Actual |
| --- | --- | --- | --- | --- |
| [CombSort/11](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CombSort/mutant_11/c/replay.json) | specification violation | `{"nums": [1, 0]}` | `[0, 1]` | `[1, 0]` |
| [CombSort/14](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CombSort/mutant_14/c/replay.json) | precondition/RTE failure | `{"nums": [1202, 0, 0]}` | `[0, 0, 1202]` | `null` |
| [CombSort/15](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CombSort/mutant_15/c/replay.json) | precondition/RTE failure | `{"nums": [1202, 0, 0]}` | `[0, 0, 1202]` | `null` |
| [CombSort/16](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CombSort/mutant_16/c/replay.json) | precondition/RTE failure | `{"nums": [1202, 0, 0]}` | `[0, 0, 1202]` | `null` |
| [CombSort/17](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CombSort/mutant_17/c/replay.json) | precondition/RTE failure | `{"nums": [1202, 0, 0]}` | `[0, 0, 1202]` | `null` |
| [CombSort/19](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CombSort/mutant_19/c/replay.json) | precondition/RTE failure | `{"nums": [1202, 0, 0]}` | `[0, 0, 1202]` | `null` |
| [CombSort/21](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CombSort/mutant_21/c/replay.json) | specification violation | `{"nums": [1202, 0, 0]}` | `[0, 0, 1202]` | `[1202, 0, 0]` |
| [CombSort/22](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CombSort/mutant_22/c/replay.json) | specification violation | `{"nums": [1202, 0, 0]}` | `[0, 0, 1202]` | `[1202, 0, 0]` |
| [CombSort/23](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CombSort/mutant_23/c/replay.json) | precondition/RTE failure | `{"nums": [1202, 0, 0]}` | `[0, 0, 1202]` | `null` |
| [CombSort/24](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CombSort/mutant_24/c/replay.json) | specification violation | `{"nums": [1202, 0, 0]}` | `[0, 0, 1202]` | `[1202, 0, 0]` |
| [CombSort/25](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CombSort/mutant_25/c/replay.json) | specification violation | `{"nums": [1202, 0, 0]}` | `[0, 0, 1202]` | `[0, 1202, 0]` |
| [CombSort/27](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CombSort/mutant_27/c/replay.json) | specification violation | `{"nums": [1202, 0, 0]}` | `[0, 0, 1202]` | `[1202, 0, 0]` |
| [CombSort/28](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CombSort/mutant_28/c/replay.json) | specification violation | `{"nums": [1202, 0, 0]}` | `[0, 0, 1202]` | `[1202, 1202, 1202]` |
| [CombSort/29](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CombSort/mutant_29/c/replay.json) | specification violation | `{"nums": [1202, 0, 0]}` | `[0, 0, 1202]` | `[1202, 1202, 1202]` |
| [CombSort/30](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CombSort/mutant_30/c/replay.json) | specification violation | `{"nums": [-1, 1, 0]}` | `[-1, 0, 1]` | `[-1, -1, 1]` |
| [CombSort/31](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CombSort/mutant_31/c/replay.json) | specification violation | `{"nums": [1202, 0, 0]}` | `[0, 0, 1202]` | `[1202, 1202, 1202]` |
| [CombSort/32](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CombSort/mutant_32/c/replay.json) | specification violation | `{"nums": [1202, 0, 0]}` | `[0, 0, 1202]` | `[1202, 1202, 1202]` |
| [CombSort/33](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CombSort/mutant_33/c/replay.json) | specification violation | `{"nums": [1202, 0, 0]}` | `[0, 0, 1202]` | `[1202, 0, 0]` |
| [CombSort/34](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CombSort/mutant_34/c/replay.json) | specification violation | `{"nums": [1202, 0, 0]}` | `[0, 0, 1202]` | `[1202, 0, 0]` |
| [CombSort/35](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CombSort/mutant_35/c/replay.json) | specification violation | `{"nums": [-1, 0, -1]}` | `[-1, -1, 0]` | `[0, -1, -1]` |
| [CombSort/36](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CombSort/mutant_36/c/replay.json) | specification violation | `{"nums": [1202, 0, 0]}` | `[0, 0, 1202]` | `[1202, 0, 0]` |
| [CombSort/37](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CombSort/mutant_37/c/replay.json) | specification violation | `{"nums": [1202, 0, 0]}` | `[0, 0, 1202]` | `[0, 0, 0]` |
| [CombSort/4](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CombSort/mutant_4/c/replay.json) | specification violation | `{"nums": [1202, 0, 0]}` | `[0, 0, 1202]` | `[1202, 0, 0]` |
| [CombSort/8](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CombSort/mutant_8/c/replay.json) | specification violation | `{"nums": [1202, 0, 0]}` | `[0, 0, 1202]` | `[1202, 0, 0]` |
| [CountIntgralPoints/1](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountIntgralPoints/mutant_1/c/replay.json) | specification violation | `{"x1": 0, "x2": 1471, "y1": 2733, "y2": -1112}` | `-5653620` | `-1636110` |
| [CountIntgralPoints/10](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountIntgralPoints/mutant_10/c/replay.json) | specification violation | `{"x1": 0, "x2": 1, "y1": 1, "y2": -1264}` | `0` | `1266` |
| [CountIntgralPoints/11](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountIntgralPoints/mutant_11/c/replay.json) | specification violation | `{"x1": 890, "x2": 890, "y1": 1845, "y2": 1}` | `1845` | `-3282255` |
| [CountIntgralPoints/12](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountIntgralPoints/mutant_12/c/replay.json) | specification violation | `{"x1": 890, "x2": 890, "y1": 1845, "y2": 1}` | `1845` | `0` |
| [CountIntgralPoints/13](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountIntgralPoints/mutant_13/c/replay.json) | specification violation | `{"x1": 0, "x2": 1471, "y1": 2733, "y2": -1112}` | `-5653620` | `0` |
| [CountIntgralPoints/14](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountIntgralPoints/mutant_14/c/replay.json) | specification violation | `{"x1": 0, "x2": 1, "y1": 1, "y2": -1264}` | `0` | `-1266` |
| [CountIntgralPoints/15](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountIntgralPoints/mutant_15/c/replay.json) | specification violation | `{"x1": 0, "x2": 1, "y1": 1, "y2": -1264}` | `0` | `-2532` |
| [CountIntgralPoints/16](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountIntgralPoints/mutant_16/c/replay.json) | specification violation | `{"x1": 0, "x2": 1, "y1": 1, "y2": -1264}` | `0` | `-1266` |
| [CountIntgralPoints/17](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountIntgralPoints/mutant_17/c/replay.json) | specification violation | `{"x1": 0, "x2": 1471, "y1": 2733, "y2": -1112}` | `-5653620` | `-906` |
| [CountIntgralPoints/18](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountIntgralPoints/mutant_18/c/replay.json) | specification violation | `{"x1": 0, "x2": 1, "y1": 1, "y2": -1264}` | `0` | `-1266` |
| [CountIntgralPoints/19](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountIntgralPoints/mutant_19/c/replay.json) | specification violation | `{"x1": 0, "x2": 1, "y1": 1, "y2": -1264}` | `0` | `-1266` |
| [CountIntgralPoints/2](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountIntgralPoints/mutant_2/c/replay.json) | specification violation | `{"x1": 0, "x2": 1471, "y1": 2733, "y2": -1112}` | `-5653620` | `-172505294` |
| [CountIntgralPoints/20](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountIntgralPoints/mutant_20/c/replay.json) | specification violation | `{"x1": 0, "x2": 1471, "y1": 2733, "y2": -1112}` | `-5653620` | `-2` |
| [CountIntgralPoints/3](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountIntgralPoints/mutant_3/c/replay.json) | specification violation | `{"x1": 0, "x2": 1471, "y1": 2733, "y2": -1112}` | `-5653620` | `2381400` |
| [CountIntgralPoints/4](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountIntgralPoints/mutant_4/c/replay.json) | specification violation | `{"x1": 0, "x2": 1471, "y1": 2733, "y2": -1112}` | `-5653620` | `-1470` |
| [CountIntgralPoints/5](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountIntgralPoints/mutant_5/c/replay.json) | specification violation | `{"x1": 0, "x2": 1471, "y1": 2733, "y2": -1112}` | `-5653620` | `0` |
| [CountIntgralPoints/6](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountIntgralPoints/mutant_6/c/replay.json) | specification violation | `{"x1": 0, "x2": 1471, "y1": 2733, "y2": -1112}` | `-5653620` | `-5652150` |
| [CountIntgralPoints/7](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountIntgralPoints/mutant_7/c/replay.json) | specification violation | `{"x1": 0, "x2": 1471, "y1": 2733, "y2": -1112}` | `-5653620` | `-5650680` |
| [CountIntgralPoints/8](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountIntgralPoints/mutant_8/c/replay.json) | specification violation | `{"x1": 0, "x2": 1471, "y1": 2733, "y2": -1112}` | `-5653620` | `-5652150` |
| [CountIntgralPoints/9](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountIntgralPoints/mutant_9/c/replay.json) | specification violation | `{"x1": -2, "x2": -1, "y1": -2, "y2": -2}` | `0` | `2` |
| [CountList/1](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountList/mutant_1/c/replay.json) | specification violation | `{"inputArray": [[]]}` | `0` | `1` |
| [CountList/2](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountList/mutant_2/c/replay.json) | specification violation | `{"inputArray": [[0, 0, 0, 0, 0, 0, 0, 0, 0]]}` | `1` | `0` |
| [CountList/3](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountList/mutant_3/c/replay.json) | specification violation | `{"inputArray": [[0, 0, 0, 0, 0, 0, 0, 0, 0]]}` | `1` | `0` |
| [CountOddSquares/1](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountOddSquares/mutant_1/c/replay.json) | specification violation | `{"m": -2, "n": -2}` | `0` | `0` |
| [CountOddSquares/12](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountOddSquares/mutant_12/c/replay.json) | specification violation | `{"m": 0, "n": -2}` | `1` | `0` |
| [CountOddSquares/13](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountOddSquares/mutant_13/c/replay.json) | specification violation | `{"m": 0, "n": -2}` | `1` | `0` |
| [CountOddSquares/17](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountOddSquares/mutant_17/c/replay.json) | specification violation | `{"m": 0, "n": -2}` | `1` | `0` |
| [CountOddSquares/2](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountOddSquares/mutant_2/c/replay.json) | specification violation | `{"m": -1, "n": -2}` | `0` | `0` |
| [CountOddSquares/22](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountOddSquares/mutant_22/c/replay.json) | specification violation | `{"m": 0, "n": -2}` | `1` | `0` |
| [CountUnsetBits/1](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountUnsetBits/mutant_1/c/replay.json) | specification violation | `{"n": 55}` | `117` | `116` |
| [CountUnsetBits/10](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountUnsetBits/mutant_10/c/replay.json) | specification violation | `{"n": 55}` | `117` | `273` |
| [CountUnsetBits/11](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountUnsetBits/mutant_11/c/replay.json) | specification violation | `{"n": 55}` | `117` | `0` |
| [CountUnsetBits/12](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountUnsetBits/mutant_12/c/replay.json) | specification violation | `{"n": 55}` | `117` | `0` |
| [CountUnsetBits/13](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountUnsetBits/mutant_13/c/replay.json) | specification violation | `{"n": 55}` | `117` | `1682` |
| [CountUnsetBits/2](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountUnsetBits/mutant_2/c/replay.json) | specification violation | `{"n": 55}` | `117` | `0` |
| [CountUnsetBits/4](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountUnsetBits/mutant_4/c/replay.json) | specification violation | `{"n": 55}` | `117` | `0` |
| [CountUnsetBits/7](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountUnsetBits/mutant_7/c/replay.json) | specification violation | `{"n": 55}` | `117` | `55` |
| [CountUnsetBits/8](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountUnsetBits/mutant_8/c/replay.json) | specification violation | `{"n": 55}` | `117` | `0` |
| [CountWays/1](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountWays/mutant_1/c/replay.json) | precondition/RTE failure | `{"n": 1}` | `0` | `null` |
| [CountWays/12](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountWays/mutant_12/c/replay.json) | specification violation | `{"n": 2}` | `3` | `1` |
| [CountWays/13](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountWays/mutant_13/c/replay.json) | specification violation | `{"n": 2}` | `3` | `0` |
| [CountWays/14](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountWays/mutant_14/c/replay.json) | specification violation | `{"n": 4}` | `11` | `0` |
| [CountWays/16](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountWays/mutant_16/c/replay.json) | specification violation | `{"n": 4}` | `11` | `9` |
| [CountWays/17](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountWays/mutant_17/c/replay.json) | precondition/RTE failure | `{"n": 2}` | `3` | `null` |
| [CountWays/18](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountWays/mutant_18/c/replay.json) | precondition/RTE failure | `{"n": 2}` | `3` | `null` |
| [CountWays/19](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountWays/mutant_19/c/replay.json) | specification violation | `{"n": 2}` | `3` | `2` |
| [CountWays/2](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountWays/mutant_2/c/replay.json) | precondition/RTE failure | `{"n": 1}` | `0` | `null` |
| [CountWays/20](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountWays/mutant_20/c/replay.json) | specification violation | `{"n": 2}` | `3` | `1` |
| [CountWays/21](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountWays/mutant_21/c/replay.json) | specification violation | `{"n": 2}` | `3` | `1` |
| [CountWays/22](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountWays/mutant_22/c/replay.json) | precondition/RTE failure | `{"n": 2}` | `3` | `null` |
| [CountWays/23](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountWays/mutant_23/c/replay.json) | specification violation | `{"n": 2}` | `3` | `1` |
| [CountWays/24](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountWays/mutant_24/c/replay.json) | specification violation | `{"n": 2}` | `3` | `1` |
| [CountWays/25](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountWays/mutant_25/c/replay.json) | specification violation | `{"n": 2}` | `3` | `4` |
| [CountWays/26](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountWays/mutant_26/c/replay.json) | specification violation | `{"n": 2}` | `3` | `2` |
| [CountWays/28](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountWays/mutant_28/c/replay.json) | specification violation | `{"n": 2}` | `3` | `1` |
| [CountWays/29](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountWays/mutant_29/c/replay.json) | specification violation | `{"n": 2}` | `3` | `2` |
| [CountWays/3](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountWays/mutant_3/c/replay.json) | precondition/RTE failure | `{"n": 1}` | `0` | `null` |
| [CountWays/30](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountWays/mutant_30/c/replay.json) | specification violation | `{"n": 2}` | `3` | `-1` |
| [CountWays/31](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountWays/mutant_31/c/replay.json) | specification violation | `{"n": 2}` | `3` | `0` |
| [CountWays/32](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountWays/mutant_32/c/replay.json) | specification violation | `{"n": 2}` | `3` | `0` |
| [CountWays/33](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountWays/mutant_33/c/replay.json) | specification violation | `{"n": 3}` | `0` | `2` |
| [CountWays/34](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountWays/mutant_34/c/replay.json) | specification violation | `{"n": 3}` | `0` | `6` |
| [CountWays/35](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountWays/mutant_35/c/replay.json) | precondition/RTE failure | `{"n": 2}` | `3` | `null` |
| [CountWays/36](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountWays/mutant_36/c/replay.json) | specification violation | `{"n": 3}` | `0` | `6` |
| [CountWays/37](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountWays/mutant_37/c/replay.json) | specification violation | `{"n": 32}` | `1117014753` | `57395627` |
| [CountWays/38](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountWays/mutant_38/c/replay.json) | precondition/RTE failure | `{"n": 2}` | `3` | `null` |
| [CountWays/39](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountWays/mutant_39/c/replay.json) | precondition/RTE failure | `{"n": 2}` | `3` | `null` |
| [CountWays/4](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountWays/mutant_4/c/replay.json) | precondition/RTE failure | `{"n": 1}` | `0` | `null` |
| [CountWays/40](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountWays/mutant_40/c/replay.json) | specification violation | `{"n": 3}` | `0` | `2` |
| [CountWays/42](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountWays/mutant_42/c/replay.json) | specification violation | `{"n": 4}` | `11` | `9` |
| [CountWays/43](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountWays/mutant_43/c/replay.json) | specification violation | `{"n": 4}` | `11` | `7` |
| [CountWays/45](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountWays/mutant_45/c/replay.json) | specification violation | `{"n": 4}` | `11` | `3` |
| [CountWays/5](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountWays/mutant_5/c/replay.json) | precondition/RTE failure | `{"n": 1}` | `0` | `null` |
| [CountWays/6](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountWays/mutant_6/c/replay.json) | precondition/RTE failure | `{"n": 1}` | `0` | `null` |
| [CountWays/7](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountWays/mutant_7/c/replay.json) | precondition/RTE failure | `{"n": 1}` | `0` | `null` |
| [CountWays/8](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountWays/mutant_8/c/replay.json) | precondition/RTE failure | `{"n": 1}` | `0` | `null` |
| [CountWays/9](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountWays/mutant_9/c/replay.json) | specification violation | `{"n": 2}` | `3` | `2` |
| [CountingSort/1](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountingSort/mutant_1/c/replay.json) | specification violation | `{"myArray": [0]}` | `[0]` | `[]` |
| [CountingSort/10](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountingSort/mutant_10/c/replay.json) | precondition/RTE failure | `{"myArray": [1, 0]}` | `[0, 1]` | `null` |
| [CountingSort/11](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountingSort/mutant_11/c/replay.json) | precondition/RTE failure | `{"myArray": [1, 0]}` | `[0, 1]` | `null` |
| [CountingSort/12](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountingSort/mutant_12/c/replay.json) | precondition/RTE failure | `{"myArray": [2, 1]}` | `[1, 2]` | `null` |
| [CountingSort/13](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountingSort/mutant_13/c/replay.json) | precondition/RTE failure | `{"myArray": [1, 0]}` | `[0, 1]` | `null` |
| [CountingSort/14](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountingSort/mutant_14/c/replay.json) | precondition/RTE failure | `{"myArray": [-1]}` | `[-1]` | `null` |
| [CountingSort/15](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountingSort/mutant_15/c/replay.json) | precondition/RTE failure | `{"myArray": [-1, 0, 1]}` | `[-1, 0, 1]` | `null` |
| [CountingSort/16](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountingSort/mutant_16/c/replay.json) | precondition/RTE failure | `{"myArray": [0]}` | `[0]` | `null` |
| [CountingSort/17](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountingSort/mutant_17/c/replay.json) | precondition/RTE failure | `{"myArray": [0]}` | `[0]` | `null` |
| [CountingSort/18](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountingSort/mutant_18/c/replay.json) | precondition/RTE failure | `{"myArray": [0]}` | `[0]` | `null` |
| [CountingSort/19](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountingSort/mutant_19/c/replay.json) | precondition/RTE failure | `{"myArray": [0]}` | `[0]` | `null` |
| [CountingSort/2](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountingSort/mutant_2/c/replay.json) | precondition/RTE failure | `{"myArray": []}` | `[]` | `null` |
| [CountingSort/20](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountingSort/mutant_20/c/replay.json) | precondition/RTE failure | `{"myArray": [0]}` | `[0]` | `null` |
| [CountingSort/22](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountingSort/mutant_22/c/replay.json) | specification violation | `{"myArray": [2, 1]}` | `[1, 2]` | `[1, 1]` |
| [CountingSort/23](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountingSort/mutant_23/c/replay.json) | specification violation | `{"myArray": [1, 0]}` | `[0, 1]` | `[0, 0]` |
| [CountingSort/24](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountingSort/mutant_24/c/replay.json) | precondition/RTE failure | `{"myArray": [-1]}` | `[-1]` | `null` |
| [CountingSort/25](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountingSort/mutant_25/c/replay.json) | specification violation | `{"myArray": [-1, -1, 0]}` | `[-1, -1, 0]` | `[-1, 0, 0]` |
| [CountingSort/26](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountingSort/mutant_26/c/replay.json) | specification violation | `{"myArray": [-1]}` | `[-1]` | `[0]` |
| [CountingSort/27](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountingSort/mutant_27/c/replay.json) | precondition/RTE failure | `{"myArray": [0]}` | `[0]` | `null` |
| [CountingSort/3](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountingSort/mutant_3/c/replay.json) | precondition/RTE failure | `{"myArray": []}` | `[]` | `null` |
| [CountingSort/30](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountingSort/mutant_30/c/replay.json) | precondition/RTE failure | `{"myArray": [0]}` | `[0]` | `null` |
| [CountingSort/32](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountingSort/mutant_32/c/replay.json) | specification violation | `{"myArray": [-1]}` | `[-1]` | `[0]` |
| [CountingSort/33](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountingSort/mutant_33/c/replay.json) | specification violation | `{"myArray": [-1]}` | `[-1]` | `[0]` |
| [CountingSort/34](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountingSort/mutant_34/c/replay.json) | specification violation | `{"myArray": [-1]}` | `[-1]` | `[1]` |
| [CountingSort/35](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountingSort/mutant_35/c/replay.json) | specification violation | `{"myArray": [-1]}` | `[-1]` | `[0]` |
| [CountingSort/36](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountingSort/mutant_36/c/replay.json) | specification violation | `{"myArray": [-1]}` | `[-1]` | `[0]` |
| [CountingSort/4](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountingSort/mutant_4/c/replay.json) | precondition/RTE failure | `{"myArray": [1, 0]}` | `[0, 1]` | `null` |
| [CountingSort/6](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountingSort/mutant_6/c/replay.json) | precondition/RTE failure | `{"myArray": [0, 1]}` | `[0, 1]` | `null` |
| [CountingSort/7](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountingSort/mutant_7/c/replay.json) | precondition/RTE failure | `{"myArray": [0, 1]}` | `[0, 1]` | `null` |
| [CountingSort/8](c_counterexamples_refreshed_all_c_20261001_search_only/cases/CountingSort/mutant_8/c/replay.json) | precondition/RTE failure | `{"myArray": [0, 1]}` | `[0, 1]` | `null` |
| [DealnnoyNum/10](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DealnnoyNum/mutant_10/c/replay.json) | specification violation | `{"m": 1, "n": 1}` | `3` | `1` |
| [DealnnoyNum/11](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DealnnoyNum/mutant_11/c/replay.json) | precondition/RTE failure | `{"m": 0, "n": 0}` | `1` | `null` |
| [DealnnoyNum/12](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DealnnoyNum/mutant_12/c/replay.json) | specification violation | `{"m": 2, "n": 1}` | `5` | `3` |
| [DealnnoyNum/13](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DealnnoyNum/mutant_13/c/replay.json) | precondition/RTE failure | `{"m": 1, "n": 1}` | `3` | `null` |
| [DealnnoyNum/15](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DealnnoyNum/mutant_15/c/replay.json) | precondition/RTE failure | `{"m": 1, "n": 1}` | `3` | `null` |
| [DealnnoyNum/17](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DealnnoyNum/mutant_17/c/replay.json) | specification violation | `{"m": 3, "n": 1}` | `7` | `9` |
| [DealnnoyNum/18](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DealnnoyNum/mutant_18/c/replay.json) | precondition/RTE failure | `{"m": 3, "n": 1}` | `7` | `null` |
| [DealnnoyNum/19](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DealnnoyNum/mutant_19/c/replay.json) | specification violation | `{"m": 3, "n": 1}` | `7` | `9` |
| [DealnnoyNum/2](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DealnnoyNum/mutant_2/c/replay.json) | specification violation | `{"m": 1, "n": 1}` | `3` | `1` |
| [DealnnoyNum/21](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DealnnoyNum/mutant_21/c/replay.json) | specification violation | `{"m": 2, "n": 1}` | `5` | `7` |
| [DealnnoyNum/22](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DealnnoyNum/mutant_22/c/replay.json) | precondition/RTE failure | `{"m": 2, "n": 1}` | `5` | `null` |
| [DealnnoyNum/23](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DealnnoyNum/mutant_23/c/replay.json) | specification violation | `{"m": 2, "n": 1}` | `5` | `7` |
| [DealnnoyNum/24](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DealnnoyNum/mutant_24/c/replay.json) | specification violation | `{"m": 1, "n": 1}` | `3` | `1` |
| [DealnnoyNum/25](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DealnnoyNum/mutant_25/c/replay.json) | specification violation | `{"m": 1, "n": 1}` | `3` | `2` |
| [DealnnoyNum/26](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DealnnoyNum/mutant_26/c/replay.json) | specification violation | `{"m": 1, "n": 1}` | `3` | `1` |
| [DealnnoyNum/27](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DealnnoyNum/mutant_27/c/replay.json) | specification violation | `{"m": 1, "n": 1}` | `3` | `2` |
| [DealnnoyNum/28](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DealnnoyNum/mutant_28/c/replay.json) | specification violation | `{"m": 3, "n": 1}` | `7` | `5` |
| [DealnnoyNum/29](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DealnnoyNum/mutant_29/c/replay.json) | precondition/RTE failure | `{"m": 1, "n": 1}` | `3` | `null` |
| [DealnnoyNum/3](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DealnnoyNum/mutant_3/c/replay.json) | precondition/RTE failure | `{"m": 0, "n": -1}` | `1` | `null` |
| [DealnnoyNum/30](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DealnnoyNum/mutant_30/c/replay.json) | precondition/RTE failure | `{"m": 1, "n": 1}` | `3` | `null` |
| [DealnnoyNum/31](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DealnnoyNum/mutant_31/c/replay.json) | precondition/RTE failure | `{"m": 1, "n": 1}` | `3` | `null` |
| [DealnnoyNum/32](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DealnnoyNum/mutant_32/c/replay.json) | specification violation | `{"m": 1, "n": 1}` | `3` | `0` |
| [DealnnoyNum/33](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DealnnoyNum/mutant_33/c/replay.json) | specification violation | `{"m": 1, "n": 1}` | `3` | `2` |
| [DealnnoyNum/34](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DealnnoyNum/mutant_34/c/replay.json) | specification violation | `{"m": 1, "n": 1}` | `3` | `1` |
| [DealnnoyNum/35](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DealnnoyNum/mutant_35/c/replay.json) | specification violation | `{"m": 1, "n": 1}` | `3` | `2` |
| [DealnnoyNum/5](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DealnnoyNum/mutant_5/c/replay.json) | specification violation | `{"m": 1, "n": 1}` | `3` | `1` |
| [DealnnoyNum/6](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DealnnoyNum/mutant_6/c/replay.json) | precondition/RTE failure | `{"m": -2, "n": 0}` | `1` | `null` |
| [DealnnoyNum/7](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DealnnoyNum/mutant_7/c/replay.json) | precondition/RTE failure | `{"m": 0, "n": 0}` | `1` | `null` |
| [DealnnoyNum/8](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DealnnoyNum/mutant_8/c/replay.json) | precondition/RTE failure | `{"m": -2, "n": 0}` | `1` | `null` |
| [DealnnoyNum/9](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DealnnoyNum/mutant_9/c/replay.json) | precondition/RTE failure | `{"m": 0, "n": -1}` | `1` | `null` |
| [DiameterCircle/1](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DiameterCircle/mutant_1/c/replay.json) | specification violation | `{"r": 2125}` | `4250` | `2` |
| [DiameterCircle/2](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DiameterCircle/mutant_2/c/replay.json) | specification violation | `{"r": 0}` | `0` | `2` |
| [DiameterCircle/3](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DiameterCircle/mutant_3/c/replay.json) | specification violation | `{"r": 0}` | `0` | `2` |
| [DiameterCircle/4](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DiameterCircle/mutant_4/c/replay.json) | specification violation | `{"r": 2125}` | `4250` | `0` |
| [DiffEvenOdd/1](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DiffEvenOdd/mutant_1/c/replay.json) | specification violation | `{"array": [-2030, 0, 0]}` | `-2029` | `1` |
| [DiffEvenOdd/10](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DiffEvenOdd/mutant_10/c/replay.json) | specification violation | `{"array": [1135, 0]}` | `-1135` | `-1136` |
| [DiffEvenOdd/12](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DiffEvenOdd/mutant_12/c/replay.json) | specification violation | `{"array": [1135, 0]}` | `-1135` | `-1136` |
| [DiffEvenOdd/13](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DiffEvenOdd/mutant_13/c/replay.json) | specification violation | `{"array": [1135, 0]}` | `-1135` | `0` |
| [DiffEvenOdd/14](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DiffEvenOdd/mutant_14/c/replay.json) | specification violation | `{"array": [-2030, 0, 0]}` | `-2029` | `1` |
| [DiffEvenOdd/15](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DiffEvenOdd/mutant_15/c/replay.json) | specification violation | `{"array": [1135, 0]}` | `-1135` | `-1136` |
| [DiffEvenOdd/18](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DiffEvenOdd/mutant_18/c/replay.json) | specification violation | `{"array": [1135, 0]}` | `-1135` | `1` |
| [DiffEvenOdd/19](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DiffEvenOdd/mutant_19/c/replay.json) | specification violation | `{"array": [-2030, 0, 0]}` | `-2029` | `0` |
| [DiffEvenOdd/20](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DiffEvenOdd/mutant_20/c/replay.json) | specification violation | `{"array": [-2030, 0, 0]}` | `-2029` | `0` |
| [DiffEvenOdd/21](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DiffEvenOdd/mutant_21/c/replay.json) | specification violation | `{"array": [-2030, 0, 0]}` | `-2029` | `0` |
| [DiffEvenOdd/22](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DiffEvenOdd/mutant_22/c/replay.json) | specification violation | `{"array": [-2030, 0, 0]}` | `-2029` | `0` |
| [DiffEvenOdd/23](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DiffEvenOdd/mutant_23/c/replay.json) | specification violation | `{"array": [1135, 0]}` | `-1135` | `1` |
| [DiffEvenOdd/24](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DiffEvenOdd/mutant_24/c/replay.json) | specification violation | `{"array": [-221, 0, 0, 0, 0, 0, 0, 0, 0]}` | `221` | `1` |
| [DiffEvenOdd/25](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DiffEvenOdd/mutant_25/c/replay.json) | specification violation | `{"array": [-2030, 0, 0]}` | `-2029` | `0` |
| [DiffEvenOdd/26](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DiffEvenOdd/mutant_26/c/replay.json) | specification violation | `{"array": [1135, 0]}` | `-1135` | `0` |
| [DiffEvenOdd/27](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DiffEvenOdd/mutant_27/c/replay.json) | specification violation | `{"array": [1135, 0]}` | `-1135` | `1` |
| [DiffEvenOdd/28](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DiffEvenOdd/mutant_28/c/replay.json) | specification violation | `{"array": [-2030, 0, 0]}` | `-2029` | `0` |
| [DiffEvenOdd/3](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DiffEvenOdd/mutant_3/c/replay.json) | specification violation | `{"array": [1135, 0]}` | `-1135` | `-1136` |
| [DiffEvenOdd/30](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DiffEvenOdd/mutant_30/c/replay.json) | specification violation | `{"array": [1135, 0]}` | `-1135` | `1` |
| [DiffEvenOdd/33](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DiffEvenOdd/mutant_33/c/replay.json) | specification violation | `{"array": [1135, 0]}` | `-1135` | `-1136` |
| [DiffEvenOdd/4](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DiffEvenOdd/mutant_4/c/replay.json) | specification violation | `{"array": [-2030, 0, 0]}` | `-2029` | `1` |
| [DiffEvenOdd/40](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DiffEvenOdd/mutant_40/c/replay.json) | specification violation | `{"array": [1135, 0]}` | `-1135` | `-1136` |
| [DiffEvenOdd/42](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DiffEvenOdd/mutant_42/c/replay.json) | specification violation | `{"array": [1135, 0]}` | `-1135` | `0` |
| [DiffEvenOdd/43](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DiffEvenOdd/mutant_43/c/replay.json) | specification violation | `{"array": [1135, 0]}` | `-1135` | `0` |
| [DiffEvenOdd/44](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DiffEvenOdd/mutant_44/c/replay.json) | specification violation | `{"array": [1135, 0]}` | `-1135` | `1135` |
| [DiffEvenOdd/45](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DiffEvenOdd/mutant_45/c/replay.json) | specification violation | `{"array": [1135, 0]}` | `-1135` | `0` |
| [DiffEvenOdd/5](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DiffEvenOdd/mutant_5/c/replay.json) | specification violation | `{"array": [1135, 0]}` | `-1135` | `-1136` |
| [DiffEvenOdd/6](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DiffEvenOdd/mutant_6/c/replay.json) | specification violation | `{"array": [1135, 0]}` | `-1135` | `-1136` |
| [DiffEvenOdd/7](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DiffEvenOdd/mutant_7/c/replay.json) | specification violation | `{"array": [-2030, 0, 0]}` | `-2029` | `1` |
| [DiffEvenOdd/8](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DiffEvenOdd/mutant_8/c/replay.json) | specification violation | `{"array": [-221, 0, 0, 0, 0, 0, 0, 0, 0]}` | `221` | `0` |
| [DiffEvenOdd/9](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DiffEvenOdd/mutant_9/c/replay.json) | specification violation | `{"array": [1135, 0]}` | `-1135` | `0` |
| [DogAge/1](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DogAge/mutant_1/c/replay.json) | specification violation | `{"hAge": 2125}` | `8513` | `8529` |
| [DogAge/10](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DogAge/mutant_10/c/replay.json) | specification violation | `{"hAge": 0}` | `13` | `15` |
| [DogAge/11](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DogAge/mutant_11/c/replay.json) | specification violation | `{"hAge": 0}` | `13` | `21` |
| [DogAge/12](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DogAge/mutant_12/c/replay.json) | specification violation | `{"hAge": 0}` | `13` | `-8` |
| [DogAge/13](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DogAge/mutant_13/c/replay.json) | specification violation | `{"hAge": 0}` | `13` | `-168` |
| [DogAge/14](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DogAge/mutant_14/c/replay.json) | specification violation | `{"hAge": 0}` | `13` | `-29` |
| [DogAge/15](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DogAge/mutant_15/c/replay.json) | specification violation | `{"hAge": 0}` | `13` | `0` |
| [DogAge/16](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DogAge/mutant_16/c/replay.json) | specification violation | `{"hAge": -3882}` | `-15499` | `21` |
| [DogAge/17](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DogAge/mutant_17/c/replay.json) | specification violation | `{"hAge": -3882}` | `-15499` | `-31035` |
| [DogAge/18](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DogAge/mutant_18/c/replay.json) | specification violation | `{"hAge": -3882}` | `-15499` | `-15515` |
| [DogAge/19](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DogAge/mutant_19/c/replay.json) | specification violation | `{"hAge": -3882}` | `-15499` | `-7743` |
| [DogAge/2](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DogAge/mutant_2/c/replay.json) | specification violation | `{"hAge": 0}` | `13` | `29` |
| [DogAge/20](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DogAge/mutant_20/c/replay.json) | specification violation | `{"hAge": -3882}` | `-15499` | `21` |
| [DogAge/21](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DogAge/mutant_21/c/replay.json) | specification violation | `{"hAge": -3882}` | `-15499` | `-3855` |
| [DogAge/22](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DogAge/mutant_22/c/replay.json) | specification violation | `{"hAge": -3882}` | `-15499` | `-3863` |
| [DogAge/23](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DogAge/mutant_23/c/replay.json) | specification violation | `{"hAge": -3882}` | `-15499` | `-949` |
| [DogAge/24](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DogAge/mutant_24/c/replay.json) | specification violation | `{"hAge": -3882}` | `-15499` | `-1` |
| [DogAge/25](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DogAge/mutant_25/c/replay.json) | specification violation | `{"hAge": -3882}` | `-15499` | `-325920` |
| [DogAge/26](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DogAge/mutant_26/c/replay.json) | specification violation | `{"hAge": -3882}` | `-15499` | `-15541` |
| [DogAge/27](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DogAge/mutant_27/c/replay.json) | specification violation | `{"hAge": -3882}` | `-15499` | `-739` |
| [DogAge/3](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DogAge/mutant_3/c/replay.json) | specification violation | `{"hAge": -3882}` | `-15499` | `-15515` |
| [DogAge/4](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DogAge/mutant_4/c/replay.json) | specification violation | `{"hAge": 0}` | `13` | `21` |
| [DogAge/5](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DogAge/mutant_5/c/replay.json) | specification violation | `{"hAge": 0}` | `13` | `21` |
| [DogAge/6](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DogAge/mutant_6/c/replay.json) | specification violation | `{"hAge": 0}` | `13` | `29` |
| [DogAge/7](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DogAge/mutant_7/c/replay.json) | specification violation | `{"hAge": 0}` | `13` | `21` |
| [DogAge/8](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DogAge/mutant_8/c/replay.json) | specification violation | `{"hAge": 0}` | `13` | `19` |
| [DogAge/9](c_counterexamples_refreshed_all_c_20261001_search_only/cases/DogAge/mutant_9/c/replay.json) | specification violation | `{"hAge": 0}` | `13` | `23` |
| [Fibonacci/10](c_counterexamples_refreshed_all_c_20261001_search_only/cases/Fibonacci/mutant_10/c/replay.json) | precondition/RTE failure | `{"n": 2}` | `1` | `null` |
| [Fibonacci/11](c_counterexamples_refreshed_all_c_20261001_search_only/cases/Fibonacci/mutant_11/c/replay.json) | specification violation | `{"n": 4}` | `3` | `2` |
| [Fibonacci/12](c_counterexamples_refreshed_all_c_20261001_search_only/cases/Fibonacci/mutant_12/c/replay.json) | precondition/RTE failure | `{"n": 2}` | `1` | `null` |
| [Fibonacci/13](c_counterexamples_refreshed_all_c_20261001_search_only/cases/Fibonacci/mutant_13/c/replay.json) | precondition/RTE failure | `{"n": 2}` | `1` | `null` |
| [Fibonacci/14](c_counterexamples_refreshed_all_c_20261001_search_only/cases/Fibonacci/mutant_14/c/replay.json) | specification violation | `{"n": 2}` | `1` | `2` |
| [Fibonacci/2](c_counterexamples_refreshed_all_c_20261001_search_only/cases/Fibonacci/mutant_2/c/replay.json) | specification violation | `{"n": 1}` | `1` | `0` |
| [Fibonacci/3](c_counterexamples_refreshed_all_c_20261001_search_only/cases/Fibonacci/mutant_3/c/replay.json) | precondition/RTE failure | `{"n": 0}` | `0` | `null` |
| [Fibonacci/5](c_counterexamples_refreshed_all_c_20261001_search_only/cases/Fibonacci/mutant_5/c/replay.json) | specification violation | `{"n": 3}` | `2` | `1` |
| [Fibonacci/6](c_counterexamples_refreshed_all_c_20261001_search_only/cases/Fibonacci/mutant_6/c/replay.json) | precondition/RTE failure | `{"n": 1}` | `1` | `null` |
| [Fibonacci/7](c_counterexamples_refreshed_all_c_20261001_search_only/cases/Fibonacci/mutant_7/c/replay.json) | specification violation | `{"n": 2}` | `1` | `0` |
| [Fibonacci/8](c_counterexamples_refreshed_all_c_20261001_search_only/cases/Fibonacci/mutant_8/c/replay.json) | precondition/RTE failure | `{"n": 2}` | `1` | `null` |
| [Fibonacci/9](c_counterexamples_refreshed_all_c_20261001_search_only/cases/Fibonacci/mutant_9/c/replay.json) | precondition/RTE failure | `{"n": 2}` | `1` | `null` |
| [FindPeak/1](c_counterexamples_refreshed_all_c_20261001_search_only/cases/FindPeak/mutant_1/c/replay.json) | specification violation | `{"arr": [0, 1], "n": 2}` | `1` | `0` |
| [FindPeak/10](c_counterexamples_refreshed_all_c_20261001_search_only/cases/FindPeak/mutant_10/c/replay.json) | specification violation | `{"arr": [-1, -3, 2147483647, 1, -3, -2147483648, 2], "n": 7}` | `2` | `6` |
| [FindPeak/12](c_counterexamples_refreshed_all_c_20261001_search_only/cases/FindPeak/mutant_12/c/replay.json) | specification violation | `{"arr": [-1, 0, 1], "n": 2}` | `1` | `2` |
| [FindPeak/13](c_counterexamples_refreshed_all_c_20261001_search_only/cases/FindPeak/mutant_13/c/replay.json) | precondition/RTE failure | `{"arr": [1, 0], "n": 2}` | `0` | `null` |
| [FindPeak/14](c_counterexamples_refreshed_all_c_20261001_search_only/cases/FindPeak/mutant_14/c/replay.json) | precondition/RTE failure | `{"arr": [1, 0], "n": 2}` | `0` | `null` |
| [FindPeak/15](c_counterexamples_refreshed_all_c_20261001_search_only/cases/FindPeak/mutant_15/c/replay.json) | specification violation | `{"arr": [3, 1, 2], "n": 3}` | `2` | `0` |
| [FindPeak/16](c_counterexamples_refreshed_all_c_20261001_search_only/cases/FindPeak/mutant_16/c/replay.json) | specification violation | `{"arr": [3, 1, 2], "n": 3}` | `2` | `0` |
| [FindPeak/17](c_counterexamples_refreshed_all_c_20261001_search_only/cases/FindPeak/mutant_17/c/replay.json) | specification violation | `{"arr": [3, 1, 2], "n": 3}` | `2` | `0` |
| [FindPeak/18](c_counterexamples_refreshed_all_c_20261001_search_only/cases/FindPeak/mutant_18/c/replay.json) | precondition/RTE failure | `{"arr": [-1, 0, 1], "n": 3}` | `2` | `null` |
| [FindPeak/19](c_counterexamples_refreshed_all_c_20261001_search_only/cases/FindPeak/mutant_19/c/replay.json) | specification violation | `{"arr": [3, 1, 2], "n": 3}` | `2` | `0` |
| [FindPeak/2](c_counterexamples_refreshed_all_c_20261001_search_only/cases/FindPeak/mutant_2/c/replay.json) | specification violation | `{"arr": [0, 1], "n": 1}` | `0` | `1` |
| [FindPeak/20](c_counterexamples_refreshed_all_c_20261001_search_only/cases/FindPeak/mutant_20/c/replay.json) | specification violation | `{"arr": [0, 1], "n": 2}` | `1` | `0` |
| [FindPeak/21](c_counterexamples_refreshed_all_c_20261001_search_only/cases/FindPeak/mutant_21/c/replay.json) | specification violation | `{"arr": [0, 1], "n": 2}` | `1` | `0` |
| [FindPeak/22](c_counterexamples_refreshed_all_c_20261001_search_only/cases/FindPeak/mutant_22/c/replay.json) | specification violation | `{"arr": [4, 3, 2, 1], "n": 3}` | `0` | `2` |
| [FindPeak/23](c_counterexamples_refreshed_all_c_20261001_search_only/cases/FindPeak/mutant_23/c/replay.json) | specification violation | `{"arr": [0, 1], "n": 2}` | `1` | `0` |
| [FindPeak/24](c_counterexamples_refreshed_all_c_20261001_search_only/cases/FindPeak/mutant_24/c/replay.json) | specification violation | `{"arr": [1, 0], "n": 2}` | `0` | `1` |
| [FindPeak/25](c_counterexamples_refreshed_all_c_20261001_search_only/cases/FindPeak/mutant_25/c/replay.json) | specification violation | `{"arr": [1, 1, 1], "n": 2}` | `0` | `1` |
| [FindPeak/26](c_counterexamples_refreshed_all_c_20261001_search_only/cases/FindPeak/mutant_26/c/replay.json) | specification violation | `{"arr": [0, 1], "n": 2}` | `1` | `0` |
| [FindPeak/3](c_counterexamples_refreshed_all_c_20261001_search_only/cases/FindPeak/mutant_3/c/replay.json) | specification violation | `{"arr": [0, 1], "n": 0}` | `0` | `1` |
| [FindPeak/4](c_counterexamples_refreshed_all_c_20261001_search_only/cases/FindPeak/mutant_4/c/replay.json) | specification violation | `{"arr": [0, 1], "n": 1}` | `0` | `1` |
| [FindPeak/6](c_counterexamples_refreshed_all_c_20261001_search_only/cases/FindPeak/mutant_6/c/replay.json) | specification violation | `{"arr": [0, 1], "n": 1}` | `0` | `1` |
| [FindPeak/9](c_counterexamples_refreshed_all_c_20261001_search_only/cases/FindPeak/mutant_9/c/replay.json) | specification violation | `{"arr": [3, 1, 2], "n": 3}` | `2` | `0` |
| [FindPoints/1](c_counterexamples_refreshed_all_c_20261001_search_only/cases/FindPoints/mutant_1/c/replay.json) | specification violation | `{"l1": -1628, "l2": -2185, "r1": 0, "r2": 1471}` | `[-1628, 0]` | `[-1628, 1471]` |
| [FindPoints/10](c_counterexamples_refreshed_all_c_20261001_search_only/cases/FindPoints/mutant_10/c/replay.json) | specification violation | `{"l1": -1628, "l2": -2185, "r1": 0, "r2": 1471}` | `[-1628, 0]` | `[-1628, 1471]` |
| [FindPoints/11](c_counterexamples_refreshed_all_c_20261001_search_only/cases/FindPoints/mutant_11/c/replay.json) | specification violation | `{"l1": 0, "l2": 648, "r1": -1746, "r2": 2433}` | `[-1746, 2433]` | `[0, 2433]` |
| [FindPoints/12](c_counterexamples_refreshed_all_c_20261001_search_only/cases/FindPoints/mutant_12/c/replay.json) | specification violation | `{"l1": 0, "l2": 648, "r1": -1746, "r2": 2433}` | `[-1746, 2433]` | `[-1746, 0]` |
| [FindPoints/13](c_counterexamples_refreshed_all_c_20261001_search_only/cases/FindPoints/mutant_13/c/replay.json) | specification violation | `{"l1": 0, "l2": 1471, "r1": 2733, "r2": -1112}` | `[0, 2733]` | `[-1112, 2733]` |
| [FindPoints/15](c_counterexamples_refreshed_all_c_20261001_search_only/cases/FindPoints/mutant_15/c/replay.json) | specification violation | `{"l1": 1471, "l2": 0, "r1": 1471, "r2": 0}` | `[0, 1471]` | `[1471, 1471]` |
| [FindPoints/16](c_counterexamples_refreshed_all_c_20261001_search_only/cases/FindPoints/mutant_16/c/replay.json) | specification violation | `{"l1": -1628, "l2": -2185, "r1": 0, "r2": 1471}` | `[-1628, 0]` | `[-2185, 0]` |
| [FindPoints/17](c_counterexamples_refreshed_all_c_20261001_search_only/cases/FindPoints/mutant_17/c/replay.json) | specification violation | `{"l1": 0, "l2": -450, "r1": 0, "r2": 0}` | `[0, 0]` | `[-450, 0]` |
| [FindPoints/18](c_counterexamples_refreshed_all_c_20261001_search_only/cases/FindPoints/mutant_18/c/replay.json) | specification violation | `{"l1": 1471, "l2": 0, "r1": 1471, "r2": 0}` | `[0, 1471]` | `[1471, 1471]` |
| [FindPoints/19](c_counterexamples_refreshed_all_c_20261001_search_only/cases/FindPoints/mutant_19/c/replay.json) | specification violation | `{"l1": -4430, "l2": -1679, "r1": 2733, "r2": 2733}` | `[-4430, 2733]` | `[-1679, 2733]` |
| [FindPoints/20](c_counterexamples_refreshed_all_c_20261001_search_only/cases/FindPoints/mutant_20/c/replay.json) | specification violation | `{"l1": 1471, "l2": 0, "r1": 1471, "r2": 0}` | `[0, 1471]` | `[1471, 1471]` |
| [FindPoints/21](c_counterexamples_refreshed_all_c_20261001_search_only/cases/FindPoints/mutant_21/c/replay.json) | specification violation | `{"l1": 0, "l2": -450, "r1": 0, "r2": 0}` | `[0, 0]` | `[-450, 0]` |
| [FindPoints/22](c_counterexamples_refreshed_all_c_20261001_search_only/cases/FindPoints/mutant_22/c/replay.json) | specification violation | `{"l1": 0, "l2": 1471, "r1": 2733, "r2": -1112}` | `[0, 2733]` | `[-1112, 2733]` |
| [FindPoints/24](c_counterexamples_refreshed_all_c_20261001_search_only/cases/FindPoints/mutant_24/c/replay.json) | specification violation | `{"l1": 1471, "l2": 0, "r1": 1471, "r2": 0}` | `[0, 1471]` | `[0, 0]` |
| [FindPoints/25](c_counterexamples_refreshed_all_c_20261001_search_only/cases/FindPoints/mutant_25/c/replay.json) | specification violation | `{"l1": -4430, "l2": -1679, "r1": 2733, "r2": 2733}` | `[-4430, 2733]` | `[0, 2733]` |
| [FindPoints/26](c_counterexamples_refreshed_all_c_20261001_search_only/cases/FindPoints/mutant_26/c/replay.json) | specification violation | `{"l1": -4430, "l2": -1679, "r1": 2733, "r2": 2733}` | `[-4430, 2733]` | `[-4430, 0]` |
| [FindPoints/3](c_counterexamples_refreshed_all_c_20261001_search_only/cases/FindPoints/mutant_3/c/replay.json) | specification violation | `{"l1": 0, "l2": 648, "r1": -1746, "r2": 2433}` | `[-1746, 2433]` | `[0, -1746]` |
| [FindPoints/4](c_counterexamples_refreshed_all_c_20261001_search_only/cases/FindPoints/mutant_4/c/replay.json) | specification violation | `{"l1": 0, "l2": 1471, "r1": 2733, "r2": -1112}` | `[0, 2733]` | `[0, 1471]` |
| [FindPoints/6](c_counterexamples_refreshed_all_c_20261001_search_only/cases/FindPoints/mutant_6/c/replay.json) | specification violation | `{"l1": 0, "l2": 648, "r1": -1746, "r2": 2433}` | `[-1746, 2433]` | `[0, -1746]` |
| [FindPoints/7](c_counterexamples_refreshed_all_c_20261001_search_only/cases/FindPoints/mutant_7/c/replay.json) | specification violation | `{"l1": 1471, "l2": 0, "r1": 1471, "r2": 0}` | `[0, 1471]` | `[1471, 0]` |
| [FindPoints/8](c_counterexamples_refreshed_all_c_20261001_search_only/cases/FindPoints/mutant_8/c/replay.json) | specification violation | `{"l1": 0, "l2": 648, "r1": -1746, "r2": 2433}` | `[-1746, 2433]` | `[0, -1746]` |
| [FindPoints/9](c_counterexamples_refreshed_all_c_20261001_search_only/cases/FindPoints/mutant_9/c/replay.json) | specification violation | `{"l1": 0, "l2": 1471, "r1": 2733, "r2": -1112}` | `[0, 2733]` | `[0, 1471]` |
| [FindRectNum/1](c_counterexamples_refreshed_all_c_20261001_search_only/cases/FindRectNum/mutant_1/c/replay.json) | specification violation | `{"n": 1446006}` | `-714275110` | `0` |
| [FindRectNum/2](c_counterexamples_refreshed_all_c_20261001_search_only/cases/FindRectNum/mutant_2/c/replay.json) | specification violation | `{"n": 1446006}` | `-714275110` | `-715721116` |
| [FindRectNum/3](c_counterexamples_refreshed_all_c_20261001_search_only/cases/FindRectNum/mutant_3/c/replay.json) | specification violation | `{"n": 1446006}` | `-714275110` | `-717167122` |
| [FindRectNum/4](c_counterexamples_refreshed_all_c_20261001_search_only/cases/FindRectNum/mutant_4/c/replay.json) | specification violation | `{"n": 1446006}` | `-714275110` | `-715721116` |
| [FindRectNum/5](c_counterexamples_refreshed_all_c_20261001_search_only/cases/FindRectNum/mutant_5/c/replay.json) | specification violation | `{"n": 1446006}` | `-714275110` | `1446006` |
| [FindRectNum/6](c_counterexamples_refreshed_all_c_20261001_search_only/cases/FindRectNum/mutant_6/c/replay.json) | specification violation | `{"n": 0}` | `0` | `1` |
| [FindRectNum/7](c_counterexamples_refreshed_all_c_20261001_search_only/cases/FindRectNum/mutant_7/c/replay.json) | specification violation | `{"n": 0}` | `0` | `-1` |
| [FindRectNum/8](c_counterexamples_refreshed_all_c_20261001_search_only/cases/FindRectNum/mutant_8/c/replay.json) | specification violation | `{"n": 1446006}` | `-714275110` | `0` |
| [HexagonalNum/1](c_counterexamples_refreshed_all_c_20261001_search_only/cases/HexagonalNum/mutant_1/c/replay.json) | specification violation | `{"n": 10032960}` | `-1734342464` | `10032960` |
| [HexagonalNum/10](c_counterexamples_refreshed_all_c_20261001_search_only/cases/HexagonalNum/mutant_10/c/replay.json) | specification violation | `{"n": 0}` | `0` | `-1` |
| [HexagonalNum/11](c_counterexamples_refreshed_all_c_20261001_search_only/cases/HexagonalNum/mutant_11/c/replay.json) | specification violation | `{"n": 0}` | `0` | `1` |
| [HexagonalNum/12](c_counterexamples_refreshed_all_c_20261001_search_only/cases/HexagonalNum/mutant_12/c/replay.json) | specification violation | `{"n": 10032960}` | `-1734342464` | `0` |
| [HexagonalNum/2](c_counterexamples_refreshed_all_c_20261001_search_only/cases/HexagonalNum/mutant_2/c/replay.json) | specification violation | `{"n": 10032960}` | `-1734342464` | `-852121792` |
| [HexagonalNum/3](c_counterexamples_refreshed_all_c_20261001_search_only/cases/HexagonalNum/mutant_3/c/replay.json) | specification violation | `{"n": 10032960}` | `-1734342464` | `872187712` |
| [HexagonalNum/4](c_counterexamples_refreshed_all_c_20261001_search_only/cases/HexagonalNum/mutant_4/c/replay.json) | specification violation | `{"n": 10032960}` | `-1734342464` | `-10032960` |
| [HexagonalNum/5](c_counterexamples_refreshed_all_c_20261001_search_only/cases/HexagonalNum/mutant_5/c/replay.json) | specification violation | `{"n": 10032960}` | `-1734342464` | `0` |
| [HexagonalNum/6](c_counterexamples_refreshed_all_c_20261001_search_only/cases/HexagonalNum/mutant_6/c/replay.json) | specification violation | `{"n": 10032960}` | `-1734342464` | `-1724309504` |
| [HexagonalNum/7](c_counterexamples_refreshed_all_c_20261001_search_only/cases/HexagonalNum/mutant_7/c/replay.json) | specification violation | `{"n": 10032960}` | `-1734342464` | `-1714276544` |
| [HexagonalNum/8](c_counterexamples_refreshed_all_c_20261001_search_only/cases/HexagonalNum/mutant_8/c/replay.json) | specification violation | `{"n": 10032960}` | `-1734342464` | `-1724309504` |
| [HexagonalNum/9](c_counterexamples_refreshed_all_c_20261001_search_only/cases/HexagonalNum/mutant_9/c/replay.json) | specification violation | `{"n": 10032960}` | `-1734342464` | `10032960` |
| [LeftInsertion/10](c_counterexamples_refreshed_all_c_20261001_search_only/cases/LeftInsertion/mutant_10/c/replay.json) | precondition/RTE failure | `{"a": [0, 0, 0, 0, 0], "x": -1}` | `0` | `null` |
| [LeftInsertion/12](c_counterexamples_refreshed_all_c_20261001_search_only/cases/LeftInsertion/mutant_12/c/replay.json) | specification violation | `{"a": [0, 0], "x": 0}` | `0` | `1` |
| [LeftInsertion/13](c_counterexamples_refreshed_all_c_20261001_search_only/cases/LeftInsertion/mutant_13/c/replay.json) | precondition/RTE failure | `{"a": [0, 0, 0, 0, 0], "x": -1}` | `0` | `null` |
| [LeftInsertion/14](c_counterexamples_refreshed_all_c_20261001_search_only/cases/LeftInsertion/mutant_14/c/replay.json) | precondition/RTE failure | `{"a": [0, 0, 0, 0, 0], "x": -1}` | `0` | `null` |
| [LeftInsertion/15](c_counterexamples_refreshed_all_c_20261001_search_only/cases/LeftInsertion/mutant_15/c/replay.json) | specification violation | `{"a": [3, 1, 2], "x": 1}` | `1` | `0` |
| [LeftInsertion/16](c_counterexamples_refreshed_all_c_20261001_search_only/cases/LeftInsertion/mutant_16/c/replay.json) | specification violation | `{"a": [0, 582], "x": 1}` | `1` | `0` |
| [LeftInsertion/17](c_counterexamples_refreshed_all_c_20261001_search_only/cases/LeftInsertion/mutant_17/c/replay.json) | specification violation | `{"a": [0, 0, 0, 0, 0], "x": -1}` | `0` | `2` |
| [LeftInsertion/2](c_counterexamples_refreshed_all_c_20261001_search_only/cases/LeftInsertion/mutant_2/c/replay.json) | specification violation | `{"a": [0, 0], "x": 0}` | `0` | `1` |
| [LeftInsertion/20](c_counterexamples_refreshed_all_c_20261001_search_only/cases/LeftInsertion/mutant_20/c/replay.json) | specification violation | `{"a": [0, 0, 0, 0, 0], "x": -1}` | `0` | `5` |
| [LeftInsertion/22](c_counterexamples_refreshed_all_c_20261001_search_only/cases/LeftInsertion/mutant_22/c/replay.json) | specification violation | `{"a": [0, 582], "x": 1}` | `1` | `0` |
| [LeftInsertion/3](c_counterexamples_refreshed_all_c_20261001_search_only/cases/LeftInsertion/mutant_3/c/replay.json) | specification violation | `{"a": [0, 0], "x": 0}` | `0` | `1` |
| [LeftInsertion/4](c_counterexamples_refreshed_all_c_20261001_search_only/cases/LeftInsertion/mutant_4/c/replay.json) | specification violation | `{"a": [0, 0], "x": 0}` | `0` | `1` |
| [LeftInsertion/6](c_counterexamples_refreshed_all_c_20261001_search_only/cases/LeftInsertion/mutant_6/c/replay.json) | specification violation | `{"a": [0, 582], "x": 1}` | `1` | `0` |
| [MaxDifference/1](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxDifference/mutant_1/c/replay.json) | precondition/RTE failure | `{"testArray": [[0, 0, 0, 0, 0], [0, 0, 0, 0, 0]]}` | `0` | `null` |
| [MaxDifference/12](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxDifference/mutant_12/c/replay.json) | specification violation | `{"testArray": [[1, 2], [3, 4]]}` | `1` | `0` |
| [MaxDifference/14](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxDifference/mutant_14/c/replay.json) | specification violation | `{"testArray": [[2, -1], [4, 3], [0, 0]]}` | `3` | `1` |
| [MaxDifference/15](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxDifference/mutant_15/c/replay.json) | specification violation | `{"testArray": [[1, 2], [3, 4]]}` | `1` | `2` |
| [MaxDifference/17](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxDifference/mutant_17/c/replay.json) | specification violation | `{"testArray": [[1, 2], [3, 4]]}` | `1` | `0` |
| [MaxDifference/18](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxDifference/mutant_18/c/replay.json) | specification violation | `{"testArray": [[1, 2], [3, 4]]}` | `1` | `0` |
| [MaxDifference/7](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxDifference/mutant_7/c/replay.json) | specification violation | `{"testArray": [[2, -1], [4, 3], [0, 0]]}` | `3` | `1` |
| [MaxOfTwo/1](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxOfTwo/mutant_1/c/replay.json) | specification violation | `{"x": 1, "y": 462}` | `462` | `1` |
| [MaxOfTwo/3](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxOfTwo/mutant_3/c/replay.json) | specification violation | `{"x": -1, "y": -347}` | `-1` | `-347` |
| [MaxProduct/13](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxProduct/mutant_13/c/replay.json) | specification violation | `{"arr": [2, 1313], "n": 2}` | `2626` | `1313` |
| [MaxProduct/14](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxProduct/mutant_14/c/replay.json) | specification violation | `{"arr": [2, 1313], "n": 2}` | `2626` | `1313` |
| [MaxProduct/16](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxProduct/mutant_16/c/replay.json) | specification violation | `{"arr": [2, 1313], "n": 2}` | `2626` | `1313` |
| [MaxProduct/17](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxProduct/mutant_17/c/replay.json) | specification violation | `{"arr": [2, 1313], "n": 2}` | `2626` | `1313` |
| [MaxProduct/18](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxProduct/mutant_18/c/replay.json) | specification violation | `{"arr": [0, 7, 0, 0, 0, 0, 0, 0, 0, 0], "n": 7}` | `7` | `0` |
| [MaxProduct/2](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxProduct/mutant_2/c/replay.json) | precondition/RTE failure | `{"arr": [0, -340, 0, 0, 0, 0, 0, 0, 0], "n": 6}` | `0` | `null` |
| [MaxProduct/20](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxProduct/mutant_20/c/replay.json) | specification violation | `{"arr": [2, 1313], "n": 2}` | `2626` | `1313` |
| [MaxProduct/22](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxProduct/mutant_22/c/replay.json) | specification violation | `{"arr": [2, 1313], "n": 2}` | `2626` | `1313` |
| [MaxProduct/23](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxProduct/mutant_23/c/replay.json) | specification violation | `{"arr": [0, 7, 0, 0, 0, 0, 0, 0, 0, 0], "n": 7}` | `7` | `0` |
| [MaxProduct/25](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxProduct/mutant_25/c/replay.json) | specification violation | `{"arr": [2, 1313], "n": 2}` | `2626` | `2` |
| [MaxProduct/26](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxProduct/mutant_26/c/replay.json) | specification violation | `{"arr": [2, 1313], "n": 2}` | `2626` | `1315` |
| [MaxProduct/27](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxProduct/mutant_27/c/replay.json) | specification violation | `{"arr": [2, 1313], "n": 2}` | `2626` | `2` |
| [MaxProduct/28](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxProduct/mutant_28/c/replay.json) | specification violation | `{"arr": [2, 1313], "n": 2}` | `2626` | `2` |
| [MaxProduct/29](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxProduct/mutant_29/c/replay.json) | specification violation | `{"arr": [2, 1313], "n": 2}` | `2626` | `1313` |
| [MaxProduct/31](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxProduct/mutant_31/c/replay.json) | precondition/RTE failure | `{"arr": [0, -340, 0, 0, 0, 0, 0, 0, 0], "n": 6}` | `0` | `null` |
| [MaxProduct/33](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxProduct/mutant_33/c/replay.json) | specification violation | `{"arr": [0, 7, 0, 0, 0, 0, 0, 0, 0, 0], "n": 7}` | `7` | `0` |
| [MaxProduct/35](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxProduct/mutant_35/c/replay.json) | specification violation | `{"arr": [2, 1313], "n": 2}` | `2626` | `2` |
| [MaxProduct/36](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxProduct/mutant_36/c/replay.json) | specification violation | `{"arr": [2, 1313], "n": 2}` | `2626` | `2` |
| [MaxProduct/4](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxProduct/mutant_4/c/replay.json) | specification violation | `{"arr": [-1, 0, 0, 0, 0, 0], "n": 1}` | `-1` | `0` |
| [MaxProduct/6](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxProduct/mutant_6/c/replay.json) | precondition/RTE failure | `{"arr": [0, -340, 0, 0, 0, 0, 0, 0, 0], "n": 6}` | `0` | `null` |
| [MaxSubArraySum/1](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSubArraySum/mutant_1/c/replay.json) | precondition/RTE failure | `{"a": [0, 0, 0, 0, 0, 0], "size": -1}` | `1` | `null` |
| [MaxSubArraySum/10](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSubArraySum/mutant_10/c/replay.json) | specification violation | `{"a": [-1, 0, 0, 437, 0, 0], "size": 5}` | `3` | `0` |
| [MaxSubArraySum/12](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSubArraySum/mutant_12/c/replay.json) | specification violation | `{"a": [-1, 0, 0, 437, 0, 0], "size": 5}` | `3` | `1` |
| [MaxSubArraySum/13](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSubArraySum/mutant_13/c/replay.json) | specification violation | `{"a": [-1, 0, 0, 437, 0, 0], "size": 5}` | `3` | `4` |
| [MaxSubArraySum/14](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSubArraySum/mutant_14/c/replay.json) | specification violation | `{"a": [-1, 0, 0, 437, 0, 0], "size": 5}` | `3` | `1` |
| [MaxSubArraySum/15](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSubArraySum/mutant_15/c/replay.json) | specification violation | `{"a": [-1, 0, 0, 437, 0, 0], "size": 5}` | `3` | `4` |
| [MaxSubArraySum/16](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSubArraySum/mutant_16/c/replay.json) | specification violation | `{"a": [-1, 0, 0, 437, 0, 0], "size": 5}` | `3` | `4` |
| [MaxSubArraySum/17](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSubArraySum/mutant_17/c/replay.json) | specification violation | `{"a": [-1, 0, 0, 437, 0, 0], "size": 5}` | `3` | `5` |
| [MaxSubArraySum/18](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSubArraySum/mutant_18/c/replay.json) | specification violation | `{"a": [-1, 0, 0, 437, 0, 0], "size": 5}` | `3` | `4` |
| [MaxSubArraySum/19](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSubArraySum/mutant_19/c/replay.json) | specification violation | `{"a": [-1, 0, 0, 437, 0, 0], "size": 5}` | `3` | `4` |
| [MaxSubArraySum/20](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSubArraySum/mutant_20/c/replay.json) | specification violation | `{"a": [-1, 0, 0, 437, 0, 0], "size": 5}` | `3` | `1` |
| [MaxSubArraySum/21](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSubArraySum/mutant_21/c/replay.json) | specification violation | `{"a": [-1, 0, 0, 437, 0, 0], "size": 5}` | `3` | `4` |
| [MaxSubArraySum/22](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSubArraySum/mutant_22/c/replay.json) | specification violation | `{"a": [-1, 0, 0, 437, 0, 0], "size": 5}` | `3` | `5` |
| [MaxSubArraySum/23](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSubArraySum/mutant_23/c/replay.json) | specification violation | `{"a": [-1, 0, 0, 437, 0, 0], "size": 5}` | `3` | `4` |
| [MaxSubArraySum/24](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSubArraySum/mutant_24/c/replay.json) | specification violation | `{"a": [-1, 0, 0, 437, 0, 0], "size": 5}` | `3` | `0` |
| [MaxSubArraySum/25](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSubArraySum/mutant_25/c/replay.json) | specification violation | `{"a": [-1, 0, 0, 437, 0, 0], "size": 5}` | `3` | `2` |
| [MaxSubArraySum/26](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSubArraySum/mutant_26/c/replay.json) | specification violation | `{"a": [-1, 0, 0, 437, 0, 0], "size": 5}` | `3` | `1` |
| [MaxSubArraySum/27](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSubArraySum/mutant_27/c/replay.json) | specification violation | `{"a": [-1, 0, 0, 437, 0, 0], "size": 5}` | `3` | `2` |
| [MaxSubArraySum/4](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSubArraySum/mutant_4/c/replay.json) | specification violation | `{"a": [-1, 0, 0, 437, 0, 0], "size": 5}` | `3` | `1` |
| [MaxSubArraySum/6](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSubArraySum/mutant_6/c/replay.json) | specification violation | `{"a": [-1, 0, 0, 437, 0, 0], "size": 5}` | `3` | `4` |
| [MaxSubArraySum/7](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSubArraySum/mutant_7/c/replay.json) | specification violation | `{"a": [-1, 0, 0, 437, 0, 0], "size": 5}` | `3` | `1` |
| [MaxSubArraySum/8](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSubArraySum/mutant_8/c/replay.json) | specification violation | `{"a": [-1, 0, 0, 437, 0, 0], "size": 5}` | `3` | `4` |
| [MaxSubArraySum/9](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSubArraySum/mutant_9/c/replay.json) | specification violation | `{"a": [-1, 0, 0, 437, 0, 0], "size": 5}` | `3` | `4` |
| [MaxSumOfThreeConsecutive/10](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSumOfThreeConsecutive/mutant_10/c/replay.json) | specification violation | `{"arr": [0, -340, 0, 0, 0, 0, 0, 0, 0], "n": 2}` | `-340` | `340` |
| [MaxSumOfThreeConsecutive/11](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSumOfThreeConsecutive/mutant_11/c/replay.json) | specification violation | `{"arr": [0, -340, 0, 0, 0, 0, 0, 0, 0], "n": 2}` | `-340` | `0` |
| [MaxSumOfThreeConsecutive/12](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSumOfThreeConsecutive/mutant_12/c/replay.json) | specification violation | `{"arr": [0, -340, 0, 0, 0, 0, 0, 0, 0], "n": 2}` | `-340` | `0` |
| [MaxSumOfThreeConsecutive/13](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSumOfThreeConsecutive/mutant_13/c/replay.json) | precondition/RTE failure | `{"arr": [0, 0, 0, 0], "n": 1}` | `0` | `null` |
| [MaxSumOfThreeConsecutive/14](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSumOfThreeConsecutive/mutant_14/c/replay.json) | precondition/RTE failure | `{"arr": [0, -340, 0, 0, 0, 0, 0, 0, 0], "n": 2}` | `-340` | `null` |
| [MaxSumOfThreeConsecutive/15](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSumOfThreeConsecutive/mutant_15/c/replay.json) | specification violation | `{"arr": [1, 0, 0, 0, 0, 0], "n": 3}` | `1` | `0` |
| [MaxSumOfThreeConsecutive/16](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSumOfThreeConsecutive/mutant_16/c/replay.json) | specification violation | `{"arr": [-1, 0, 1], "n": 3}` | `1` | `0` |
| [MaxSumOfThreeConsecutive/18](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSumOfThreeConsecutive/mutant_18/c/replay.json) | specification violation | `{"arr": [0, 0, -1, 0, 0, 0], "n": 3}` | `0` | `1` |
| [MaxSumOfThreeConsecutive/19](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSumOfThreeConsecutive/mutant_19/c/replay.json) | specification violation | `{"arr": [-1, 0, 1], "n": 3}` | `1` | `0` |
| [MaxSumOfThreeConsecutive/2](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSumOfThreeConsecutive/mutant_2/c/replay.json) | specification violation | `{"arr": [-1], "n": 1}` | `-1` | `0` |
| [MaxSumOfThreeConsecutive/20](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSumOfThreeConsecutive/mutant_20/c/replay.json) | specification violation | `{"arr": [3, 1, 2], "n": 3}` | `5` | `4` |
| [MaxSumOfThreeConsecutive/22](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSumOfThreeConsecutive/mutant_22/c/replay.json) | specification violation | `{"arr": [0, 0, -1, 0, 0, 0], "n": 3}` | `0` | `1` |
| [MaxSumOfThreeConsecutive/23](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSumOfThreeConsecutive/mutant_23/c/replay.json) | specification violation | `{"arr": [3, 1, 2], "n": 3}` | `5` | `4` |
| [MaxSumOfThreeConsecutive/24](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSumOfThreeConsecutive/mutant_24/c/replay.json) | specification violation | `{"arr": [1, 0, 0, 0, 0, 0], "n": 3}` | `1` | `0` |
| [MaxSumOfThreeConsecutive/25](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSumOfThreeConsecutive/mutant_25/c/replay.json) | precondition/RTE failure | `{"arr": [0, 0, 0, 0], "n": 1}` | `0` | `null` |
| [MaxSumOfThreeConsecutive/26](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSumOfThreeConsecutive/mutant_26/c/replay.json) | precondition/RTE failure | `{"arr": [0, 0, -1, 0, 0, 0], "n": 3}` | `0` | `null` |
| [MaxSumOfThreeConsecutive/36](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSumOfThreeConsecutive/mutant_36/c/replay.json) | specification violation | `{"arr": [4, 3, 2, 1], "n": 4}` | `8` | `7` |
| [MaxSumOfThreeConsecutive/39](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSumOfThreeConsecutive/mutant_39/c/replay.json) | specification violation | `{"arr": [4, 3, 2, 1], "n": 4}` | `8` | `7` |
| [MaxSumOfThreeConsecutive/4](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSumOfThreeConsecutive/mutant_4/c/replay.json) | specification violation | `{"arr": [-1], "n": 1}` | `-1` | `0` |
| [MaxSumOfThreeConsecutive/44](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSumOfThreeConsecutive/mutant_44/c/replay.json) | specification violation | `{"arr": [-1, -1, -1, -1], "n": 4}` | `-2` | `-1` |
| [MaxSumOfThreeConsecutive/47](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSumOfThreeConsecutive/mutant_47/c/replay.json) | specification violation | `{"arr": [-1, -1, -1, -1], "n": 4}` | `-2` | `0` |
| [MaxSumOfThreeConsecutive/52](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSumOfThreeConsecutive/mutant_52/c/replay.json) | specification violation | `{"arr": [-1, -1, -1, -1], "n": 4}` | `-2` | `0` |
| [MaxSumOfThreeConsecutive/55](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSumOfThreeConsecutive/mutant_55/c/replay.json) | specification violation | `{"arr": [-1, -1, -1, -1], "n": 4}` | `-2` | `2` |
| [MaxSumOfThreeConsecutive/56](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSumOfThreeConsecutive/mutant_56/c/replay.json) | specification violation | `{"arr": [4, 3, 2, 1], "n": 4}` | `8` | `0` |
| [MaxSumOfThreeConsecutive/57](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSumOfThreeConsecutive/mutant_57/c/replay.json) | specification violation | `{"arr": [0, -340, 0, 0, 0, 0, 0, 0, 0], "n": 2}` | `-340` | `0` |
| [MaxSumOfThreeConsecutive/58](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSumOfThreeConsecutive/mutant_58/c/replay.json) | precondition/RTE failure | `{"arr": [0, 0, -1, 0, 0, 0], "n": 3}` | `0` | `null` |
| [MaxSumOfThreeConsecutive/59](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSumOfThreeConsecutive/mutant_59/c/replay.json) | precondition/RTE failure | `{"arr": [0, 0, -1, 0, 0, 0], "n": 3}` | `0` | `null` |
| [MaxSumOfThreeConsecutive/6](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSumOfThreeConsecutive/mutant_6/c/replay.json) | specification violation | `{"arr": [0, -340, 0, 0, 0, 0, 0, 0, 0], "n": 2}` | `-340` | `0` |
| [MaxSumOfThreeConsecutive/60](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSumOfThreeConsecutive/mutant_60/c/replay.json) | precondition/RTE failure | `{"arr": [0, 0, -1, 0, 0, 0], "n": 3}` | `0` | `null` |
| [MaxSumOfThreeConsecutive/7](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSumOfThreeConsecutive/mutant_7/c/replay.json) | precondition/RTE failure | `{"arr": [0, 0, 0, 0], "n": 1}` | `0` | `null` |
| [MaxSumOfThreeConsecutive/8](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSumOfThreeConsecutive/mutant_8/c/replay.json) | specification violation | `{"arr": [0, -340, 0, 0, 0, 0, 0, 0, 0], "n": 2}` | `-340` | `0` |
| [MaxSumOfThreeConsecutive/9](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSumOfThreeConsecutive/mutant_9/c/replay.json) | specification violation | `{"arr": [0, -340, 0, 0, 0, 0, 0, 0, 0], "n": 2}` | `-340` | `0` |
| [MaxSumSubseq/10](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSumSubseq/mutant_10/c/replay.json) | precondition/RTE failure | `{"a": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | `1856` | `null` |
| [MaxSumSubseq/11](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSumSubseq/mutant_11/c/replay.json) | precondition/RTE failure | `{"a": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | `1856` | `null` |
| [MaxSumSubseq/12](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSumSubseq/mutant_12/c/replay.json) | precondition/RTE failure | `{"a": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | `1856` | `null` |
| [MaxSumSubseq/14](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSumSubseq/mutant_14/c/replay.json) | specification violation | `{"a": [-937, -1, 0]}` | `-1` | `0` |
| [MaxSumSubseq/15](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSumSubseq/mutant_15/c/replay.json) | specification violation | `{"a": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | `1856` | `0` |
| [MaxSumSubseq/16](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSumSubseq/mutant_16/c/replay.json) | specification violation | `{"a": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | `1856` | `0` |
| [MaxSumSubseq/18](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSumSubseq/mutant_18/c/replay.json) | specification violation | `{"a": [-937, -1, 0]}` | `-1` | `0` |
| [MaxSumSubseq/19](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSumSubseq/mutant_19/c/replay.json) | specification violation | `{"a": [-937, -1, 0]}` | `-1` | `0` |
| [MaxSumSubseq/2](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSumSubseq/mutant_2/c/replay.json) | specification violation | `{"a": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | `1856` | `0` |
| [MaxSumSubseq/20](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSumSubseq/mutant_20/c/replay.json) | precondition/RTE failure | `{"a": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | `1856` | `null` |
| [MaxSumSubseq/21](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSumSubseq/mutant_21/c/replay.json) | specification violation | `{"a": [-937, -1, 0]}` | `-1` | `0` |
| [MaxSumSubseq/23](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSumSubseq/mutant_23/c/replay.json) | precondition/RTE failure | `{"a": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | `1856` | `null` |
| [MaxSumSubseq/24](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSumSubseq/mutant_24/c/replay.json) | precondition/RTE failure | `{"a": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | `1856` | `null` |
| [MaxSumSubseq/25](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSumSubseq/mutant_25/c/replay.json) | specification violation | `{"a": [-937, -1, 0]}` | `-1` | `-937` |
| [MaxSumSubseq/26](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSumSubseq/mutant_26/c/replay.json) | specification violation | `{"a": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | `1856` | `0` |
| [MaxSumSubseq/27](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSumSubseq/mutant_27/c/replay.json) | precondition/RTE failure | `{"a": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | `1856` | `null` |
| [MaxSumSubseq/28](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSumSubseq/mutant_28/c/replay.json) | precondition/RTE failure | `{"a": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | `1856` | `null` |
| [MaxSumSubseq/29](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSumSubseq/mutant_29/c/replay.json) | precondition/RTE failure | `{"a": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | `1856` | `null` |
| [MaxSumSubseq/3](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSumSubseq/mutant_3/c/replay.json) | precondition/RTE failure | `{"a": []}` | `0` | `null` |
| [MaxSumSubseq/30](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSumSubseq/mutant_30/c/replay.json) | specification violation | `{"a": [0, 1]}` | `1` | `0` |
| [MaxSumSubseq/31](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSumSubseq/mutant_31/c/replay.json) | specification violation | `{"a": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | `1856` | `0` |
| [MaxSumSubseq/32](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSumSubseq/mutant_32/c/replay.json) | specification violation | `{"a": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | `1856` | `0` |
| [MaxSumSubseq/33](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSumSubseq/mutant_33/c/replay.json) | specification violation | `{"a": [0, 1]}` | `1` | `0` |
| [MaxSumSubseq/34](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSumSubseq/mutant_34/c/replay.json) | specification violation | `{"a": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | `1856` | `0` |
| [MaxSumSubseq/4](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSumSubseq/mutant_4/c/replay.json) | precondition/RTE failure | `{"a": []}` | `0` | `null` |
| [MaxSumSubseq/6](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSumSubseq/mutant_6/c/replay.json) | specification violation | `{"a": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | `1856` | `0` |
| [MaxSumSubseq/9](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxSumSubseq/mutant_9/c/replay.json) | precondition/RTE failure | `{"a": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | `1856` | `null` |
| [MaxVolume/13](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxVolume/mutant_13/c/replay.json) | specification violation | `{"s": 55}` | `6156` | `0` |
| [MaxVolume/15](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxVolume/mutant_15/c/replay.json) | specification violation | `{"s": 55}` | `6156` | `5096` |
| [MaxVolume/16](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxVolume/mutant_16/c/replay.json) | specification violation | `{"s": 55}` | `6156` | `1417248` |
| [MaxVolume/17](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxVolume/mutant_17/c/replay.json) | specification violation | `{"s": 55}` | `6156` | `51319` |
| [MaxVolume/18](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxVolume/mutant_18/c/replay.json) | specification violation | `{"s": 55}` | `6156` | `756` |
| [MaxVolume/19](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxVolume/mutant_19/c/replay.json) | specification violation | `{"s": 55}` | `6156` | `25308` |
| [MaxVolume/2](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxVolume/mutant_2/c/replay.json) | specification violation | `{"s": 55}` | `6156` | `0` |
| [MaxVolume/20](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxVolume/mutant_20/c/replay.json) | specification violation | `{"s": 55}` | `6156` | `1012536` |
| [MaxVolume/21](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxVolume/mutant_21/c/replay.json) | specification violation | `{"s": 55}` | `6156` | `51319` |
| [MaxVolume/22](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxVolume/mutant_22/c/replay.json) | specification violation | `{"s": 55}` | `6156` | `756` |
| [MaxVolume/23](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxVolume/mutant_23/c/replay.json) | specification violation | `{"s": 55}` | `6156` | `364` |
| [MaxVolume/24](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxVolume/mutant_24/c/replay.json) | specification violation | `{"s": 55}` | `6156` | `730` |
| [MaxVolume/25](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxVolume/mutant_25/c/replay.json) | specification violation | `{"s": 55}` | `6156` | `56` |
| [MaxVolume/26](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxVolume/mutant_26/c/replay.json) | specification violation | `{"s": 55}` | `6156` | `729` |
| [MaxVolume/28](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxVolume/mutant_28/c/replay.json) | specification violation | `{"s": 55}` | `6156` | `783` |
| [MaxVolume/29](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxVolume/mutant_29/c/replay.json) | specification violation | `{"s": 55}` | `6156` | `785` |
| [MaxVolume/31](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxVolume/mutant_31/c/replay.json) | specification violation | `{"s": 55}` | `6156` | `-55` |
| [MaxVolume/33](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxVolume/mutant_33/c/replay.json) | specification violation | `{"s": 55}` | `6156` | `0` |
| [MaxVolume/34](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxVolume/mutant_34/c/replay.json) | specification violation | `{"s": 55}` | `6156` | `0` |
| [MaxVolume/7](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxVolume/mutant_7/c/replay.json) | specification violation | `{"s": 55}` | `6156` | `2508` |
| [MaxVolume/8](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaxVolume/mutant_8/c/replay.json) | specification violation | `{"s": 55}` | `6156` | `0` |
| [MaximumSegments/1](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaximumSegments/mutant_1/c/replay.json) | precondition/RTE failure | `{"a": 1, "b": 1, "c": 1, "n": 0}` | `0` | `null` |
| [MaximumSegments/12](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaximumSegments/mutant_12/c/replay.json) | precondition/RTE failure | `{"a": 2, "b": 1, "c": 1, "n": 1}` | `1` | `null` |
| [MaximumSegments/14](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaximumSegments/mutant_14/c/replay.json) | specification violation | `{"a": 1, "b": 2, "c": 2, "n": 1}` | `1` | `-1` |
| [MaximumSegments/15](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaximumSegments/mutant_15/c/replay.json) | precondition/RTE failure | `{"a": 1, "b": 1, "c": 1, "n": 1}` | `1` | `null` |
| [MaximumSegments/16](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaximumSegments/mutant_16/c/replay.json) | specification violation | `{"a": 1, "b": 2, "c": 2, "n": 1}` | `1` | `-1` |
| [MaximumSegments/19](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaximumSegments/mutant_19/c/replay.json) | specification violation | `{"a": 2, "b": 2, "c": 2, "n": 3}` | `-1` | `0` |
| [MaximumSegments/2](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaximumSegments/mutant_2/c/replay.json) | precondition/RTE failure | `{"a": 1, "b": 1, "c": 1, "n": 0}` | `0` | `null` |
| [MaximumSegments/20](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaximumSegments/mutant_20/c/replay.json) | precondition/RTE failure | `{"a": 2, "b": 1, "c": 1, "n": 1}` | `1` | `null` |
| [MaximumSegments/22](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaximumSegments/mutant_22/c/replay.json) | specification violation | `{"a": 2, "b": 2, "c": 2, "n": 3}` | `-1` | `0` |
| [MaximumSegments/23](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaximumSegments/mutant_23/c/replay.json) | precondition/RTE failure | `{"a": 2, "b": 1, "c": 1, "n": 1}` | `1` | `null` |
| [MaximumSegments/25](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaximumSegments/mutant_25/c/replay.json) | specification violation | `{"a": 1, "b": 2, "c": 2, "n": 1}` | `1` | `0` |
| [MaximumSegments/26](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaximumSegments/mutant_26/c/replay.json) | precondition/RTE failure | `{"a": 1, "b": 1, "c": 1, "n": 1}` | `1` | `null` |
| [MaximumSegments/3](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaximumSegments/mutant_3/c/replay.json) | precondition/RTE failure | `{"a": 1, "b": 1, "c": 1, "n": 0}` | `0` | `null` |
| [MaximumSegments/34](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaximumSegments/mutant_34/c/replay.json) | specification violation | `{"a": 2, "b": 1, "c": 2, "n": 1}` | `1` | `-1` |
| [MaximumSegments/35](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaximumSegments/mutant_35/c/replay.json) | precondition/RTE failure | `{"a": 1, "b": 2, "c": 1, "n": 1}` | `1` | `null` |
| [MaximumSegments/37](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaximumSegments/mutant_37/c/replay.json) | specification violation | `{"a": 2, "b": 1, "c": 2, "n": 1}` | `1` | `-1` |
| [MaximumSegments/38](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaximumSegments/mutant_38/c/replay.json) | precondition/RTE failure | `{"a": 1, "b": 1, "c": 1, "n": 1}` | `1` | `null` |
| [MaximumSegments/39](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaximumSegments/mutant_39/c/replay.json) | specification violation | `{"a": 2, "b": 1, "c": 2, "n": 1}` | `1` | `-1` |
| [MaximumSegments/4](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaximumSegments/mutant_4/c/replay.json) | precondition/RTE failure | `{"a": 1, "b": 1, "c": 1, "n": 0}` | `0` | `null` |
| [MaximumSegments/40](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaximumSegments/mutant_40/c/replay.json) | specification violation | `{"a": 2, "b": 1, "c": 2, "n": 1}` | `1` | `-1` |
| [MaximumSegments/42](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaximumSegments/mutant_42/c/replay.json) | specification violation | `{"a": 2, "b": 2, "c": 2, "n": 3}` | `-1` | `0` |
| [MaximumSegments/43](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaximumSegments/mutant_43/c/replay.json) | precondition/RTE failure | `{"a": 1, "b": 2, "c": 1, "n": 1}` | `1` | `null` |
| [MaximumSegments/44](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaximumSegments/mutant_44/c/replay.json) | specification violation | `{"a": 2, "b": 1, "c": 2, "n": 1}` | `1` | `-1` |
| [MaximumSegments/45](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaximumSegments/mutant_45/c/replay.json) | specification violation | `{"a": 2, "b": 2, "c": 2, "n": 3}` | `-1` | `0` |
| [MaximumSegments/46](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaximumSegments/mutant_46/c/replay.json) | precondition/RTE failure | `{"a": 1, "b": 2, "c": 1, "n": 1}` | `1` | `null` |
| [MaximumSegments/48](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaximumSegments/mutant_48/c/replay.json) | specification violation | `{"a": 1, "b": 1, "c": 1, "n": 1}` | `1` | `2` |
| [MaximumSegments/49](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaximumSegments/mutant_49/c/replay.json) | precondition/RTE failure | `{"a": 1, "b": 1, "c": 1, "n": 1}` | `1` | `null` |
| [MaximumSegments/50](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaximumSegments/mutant_50/c/replay.json) | specification violation | `{"a": 1, "b": 1, "c": 1, "n": 1}` | `1` | `2` |
| [MaximumSegments/51](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaximumSegments/mutant_51/c/replay.json) | specification violation | `{"a": 2, "b": 1, "c": 2, "n": 1}` | `1` | `0` |
| [MaximumSegments/52](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaximumSegments/mutant_52/c/replay.json) | specification violation | `{"a": 2, "b": 1, "c": 2, "n": 1}` | `1` | `0` |
| [MaximumSegments/53](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaximumSegments/mutant_53/c/replay.json) | specification violation | `{"a": 2, "b": 1, "c": 2, "n": 1}` | `1` | `-1` |
| [MaximumSegments/54](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaximumSegments/mutant_54/c/replay.json) | specification violation | `{"a": 2, "b": 1, "c": 2, "n": 1}` | `1` | `0` |
| [MaximumSegments/55](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaximumSegments/mutant_55/c/replay.json) | specification violation | `{"a": 2, "b": 1, "c": 2, "n": 1}` | `1` | `-1` |
| [MaximumSegments/57](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaximumSegments/mutant_57/c/replay.json) | specification violation | `{"a": 2, "b": 2, "c": 1, "n": 1}` | `1` | `-1` |
| [MaximumSegments/58](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaximumSegments/mutant_58/c/replay.json) | precondition/RTE failure | `{"a": 1, "b": 1, "c": 2, "n": 1}` | `1` | `null` |
| [MaximumSegments/6](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaximumSegments/mutant_6/c/replay.json) | specification violation | `{"a": 1, "b": 1, "c": 1, "n": 1}` | `1` | `0` |
| [MaximumSegments/60](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaximumSegments/mutant_60/c/replay.json) | specification violation | `{"a": 2, "b": 2, "c": 1, "n": 1}` | `1` | `-1` |
| [MaximumSegments/61](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaximumSegments/mutant_61/c/replay.json) | precondition/RTE failure | `{"a": 1, "b": 1, "c": 1, "n": 1}` | `1` | `null` |
| [MaximumSegments/62](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaximumSegments/mutant_62/c/replay.json) | specification violation | `{"a": 2, "b": 2, "c": 1, "n": 1}` | `1` | `-1` |
| [MaximumSegments/63](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaximumSegments/mutant_63/c/replay.json) | specification violation | `{"a": 2, "b": 2, "c": 1, "n": 1}` | `1` | `-1` |
| [MaximumSegments/65](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaximumSegments/mutant_65/c/replay.json) | specification violation | `{"a": 2, "b": 2, "c": 2, "n": 3}` | `-1` | `0` |
| [MaximumSegments/66](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaximumSegments/mutant_66/c/replay.json) | precondition/RTE failure | `{"a": 1, "b": 1, "c": 2, "n": 1}` | `1` | `null` |
| [MaximumSegments/67](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaximumSegments/mutant_67/c/replay.json) | specification violation | `{"a": 2, "b": 2, "c": 1, "n": 1}` | `1` | `-1` |
| [MaximumSegments/68](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaximumSegments/mutant_68/c/replay.json) | specification violation | `{"a": 2, "b": 2, "c": 2, "n": 3}` | `-1` | `0` |
| [MaximumSegments/69](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaximumSegments/mutant_69/c/replay.json) | precondition/RTE failure | `{"a": 1, "b": 1, "c": 2, "n": 1}` | `1` | `null` |
| [MaximumSegments/7](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaximumSegments/mutant_7/c/replay.json) | specification violation | `{"a": 1, "b": 1, "c": 1, "n": 2}` | `2` | `0` |
| [MaximumSegments/70](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaximumSegments/mutant_70/c/replay.json) | specification violation | `{"a": 2, "b": 2, "c": 1, "n": 2}` | `2` | `1` |
| [MaximumSegments/71](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaximumSegments/mutant_71/c/replay.json) | specification violation | `{"a": 1, "b": 1, "c": 1, "n": 1}` | `1` | `2` |
| [MaximumSegments/72](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaximumSegments/mutant_72/c/replay.json) | precondition/RTE failure | `{"a": 1, "b": 1, "c": 1, "n": 1}` | `1` | `null` |
| [MaximumSegments/73](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaximumSegments/mutant_73/c/replay.json) | specification violation | `{"a": 1, "b": 1, "c": 1, "n": 1}` | `1` | `2` |
| [MaximumSegments/74](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaximumSegments/mutant_74/c/replay.json) | specification violation | `{"a": 2, "b": 2, "c": 1, "n": 1}` | `1` | `0` |
| [MaximumSegments/75](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaximumSegments/mutant_75/c/replay.json) | specification violation | `{"a": 2, "b": 2, "c": 1, "n": 1}` | `1` | `0` |
| [MaximumSegments/76](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaximumSegments/mutant_76/c/replay.json) | specification violation | `{"a": 2, "b": 2, "c": 1, "n": 1}` | `1` | `-1` |
| [MaximumSegments/77](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaximumSegments/mutant_77/c/replay.json) | specification violation | `{"a": 2, "b": 2, "c": 1, "n": 1}` | `1` | `0` |
| [MaximumSegments/78](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaximumSegments/mutant_78/c/replay.json) | specification violation | `{"a": 2, "b": 2, "c": 1, "n": 1}` | `1` | `-1` |
| [MaximumSegments/9](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MaximumSegments/mutant_9/c/replay.json) | specification violation | `{"a": 2, "b": 2, "c": 2, "n": 1}` | `-1` | `0` |
| [MinCoins/1](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinCoins/mutant_1/c/replay.json) | specification violation | `{"coins": null, "m": -1, "v": -1}` | `2147483647` | `0` |
| [MinCoins/10](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinCoins/mutant_10/c/replay.json) | specification violation | `{"coins": [1], "m": 0, "v": 1}` | `2147483647` | `1` |
| [MinCoins/12](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinCoins/mutant_12/c/replay.json) | specification violation | `{"coins": [1], "m": 1, "v": 2}` | `2` | `1` |
| [MinCoins/13](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinCoins/mutant_13/c/replay.json) | specification violation | `{"coins": [2], "m": 1, "v": 2}` | `1` | `2147483647` |
| [MinCoins/14](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinCoins/mutant_14/c/replay.json) | precondition/RTE failure | `{"coins": [1], "m": 1, "v": 1}` | `1` | `null` |
| [MinCoins/15](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinCoins/mutant_15/c/replay.json) | specification violation | `{"coins": [2], "m": 1, "v": 1}` | `2147483647` | `1` |
| [MinCoins/16](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinCoins/mutant_16/c/replay.json) | specification violation | `{"coins": [1], "m": 1, "v": 2}` | `2` | `2147483647` |
| [MinCoins/2](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinCoins/mutant_2/c/replay.json) | specification violation | `{"coins": [], "m": 0, "v": 1}` | `2147483647` | `0` |
| [MinCoins/21](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinCoins/mutant_21/c/replay.json) | specification violation | `{"coins": [2], "m": 1, "v": 3}` | `2147483647` | `-2147483648` |
| [MinCoins/3](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinCoins/mutant_3/c/replay.json) | specification violation | `{"coins": [], "m": 0, "v": 0}` | `0` | `2147483647` |
| [MinCoins/4](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinCoins/mutant_4/c/replay.json) | specification violation | `{"coins": [], "m": 0, "v": 0}` | `0` | `2147483647` |
| [MinCoins/5](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinCoins/mutant_5/c/replay.json) | specification violation | `{"coins": [1], "m": 1, "v": 1}` | `1` | `2147483647` |
| [MinCoins/7](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinCoins/mutant_7/c/replay.json) | specification violation | `{"coins": [-1], "m": 1, "v": -1}` | `2147483647` | `1` |
| [MinCoins/8](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinCoins/mutant_8/c/replay.json) | specification violation | `{"coins": [-1], "m": 1, "v": -1}` | `2147483647` | `1` |
| [MinCost/11](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinCost/mutant_11/c/replay.json) | specification violation | `{"cost": [[2, -1], [4, 3], [0, 0]], "m": 2, "n": 0}` | `6` | `0` |
| [MinCost/14](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinCost/mutant_14/c/replay.json) | specification violation | `{"cost": [[1, 2], [3, 4]], "m": 1, "n": 0}` | `4` | `3` |
| [MinCost/15](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinCost/mutant_15/c/replay.json) | precondition/RTE failure | `{"cost": [[0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0]], "m": 4, "n": 4}` | `0` | `null` |
| [MinCost/16](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinCost/mutant_16/c/replay.json) | specification violation | `{"cost": [[1, 2], [3, 4]], "m": 1, "n": 0}` | `4` | `3` |
| [MinCost/17](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinCost/mutant_17/c/replay.json) | specification violation | `{"cost": [[1, 2], [3, 4]], "m": 1, "n": 0}` | `4` | `1` |
| [MinCost/18](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinCost/mutant_18/c/replay.json) | specification violation | `{"cost": [[1, 2], [3, 4]], "m": 1, "n": 0}` | `4` | `3` |
| [MinCost/19](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinCost/mutant_19/c/replay.json) | specification violation | `{"cost": [[1, 2], [3, 4]], "m": 1, "n": 0}` | `4` | `-2` |
| [MinCost/2](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinCost/mutant_2/c/replay.json) | precondition/RTE failure | `{"cost": [[0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0]], "m": 4, "n": 4}` | `0` | `null` |
| [MinCost/20](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinCost/mutant_20/c/replay.json) | specification violation | `{"cost": [[1, 2], [3, 4]], "m": 1, "n": 0}` | `4` | `0` |
| [MinCost/21](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinCost/mutant_21/c/replay.json) | specification violation | `{"cost": [[1, 2], [3, 4]], "m": 1, "n": 0}` | `4` | `0` |
| [MinCost/23](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinCost/mutant_23/c/replay.json) | specification violation | `{"cost": [[4, 0, 4]], "m": 0, "n": 2}` | `8` | `0` |
| [MinCost/27](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinCost/mutant_27/c/replay.json) | precondition/RTE failure | `{"cost": [[0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0]], "m": 4, "n": 4}` | `0` | `null` |
| [MinCost/29](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinCost/mutant_29/c/replay.json) | specification violation | `{"cost": [[1, 2]], "m": 0, "n": 1}` | `3` | `1` |
| [MinCost/3](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinCost/mutant_3/c/replay.json) | precondition/RTE failure | `{"cost": [[0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0]], "m": 4, "n": 4}` | `0` | `null` |
| [MinCost/30](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinCost/mutant_30/c/replay.json) | specification violation | `{"cost": [[1, 2]], "m": 0, "n": 1}` | `3` | `2` |
| [MinCost/31](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinCost/mutant_31/c/replay.json) | specification violation | `{"cost": [[1, 2]], "m": 0, "n": 1}` | `3` | `-1` |
| [MinCost/32](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinCost/mutant_32/c/replay.json) | specification violation | `{"cost": [[1, 2]], "m": 0, "n": 1}` | `3` | `0` |
| [MinCost/33](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinCost/mutant_33/c/replay.json) | specification violation | `{"cost": [[1, 2]], "m": 0, "n": 1}` | `3` | `0` |
| [MinCost/34](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinCost/mutant_34/c/replay.json) | specification violation | `{"cost": [[1, 2], [3, 4]], "m": 1, "n": 1}` | `5` | `0` |
| [MinCost/35](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinCost/mutant_35/c/replay.json) | specification violation | `{"cost": [[2, -1], [4, 3], [0, 0]], "m": 2, "n": 1}` | `4` | `0` |
| [MinCost/37](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinCost/mutant_37/c/replay.json) | specification violation | `{"cost": [[1, 2], [3, 4]], "m": 1, "n": 1}` | `5` | `0` |
| [MinCost/38](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinCost/mutant_38/c/replay.json) | specification violation | `{"cost": [[-1, -1, 0], [2, -2, 1]], "m": 1, "n": 2}` | `-3` | `0` |
| [MinCost/39](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinCost/mutant_39/c/replay.json) | precondition/RTE failure | `{"cost": [[0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0]], "m": 4, "n": 4}` | `0` | `null` |
| [MinCost/4](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinCost/mutant_4/c/replay.json) | precondition/RTE failure | `{"cost": [[0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0]], "m": 4, "n": 4}` | `0` | `null` |
| [MinCost/42](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinCost/mutant_42/c/replay.json) | precondition/RTE failure | `{"cost": [[0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0]], "m": 4, "n": 4}` | `0` | `null` |
| [MinCost/46](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinCost/mutant_46/c/replay.json) | precondition/RTE failure | `{"cost": [[0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0]], "m": 4, "n": 4}` | `0` | `null` |
| [MinCost/49](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinCost/mutant_49/c/replay.json) | specification violation | `{"cost": [[1, 2], [3, 4]], "m": 1, "n": 1}` | `5` | `4` |
| [MinCost/5](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinCost/mutant_5/c/replay.json) | precondition/RTE failure | `{"cost": [[0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0]], "m": 4, "n": 4}` | `0` | `null` |
| [MinCost/50](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinCost/mutant_50/c/replay.json) | precondition/RTE failure | `{"cost": [[0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0]], "m": 4, "n": 4}` | `0` | `null` |
| [MinCost/51](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinCost/mutant_51/c/replay.json) | specification violation | `{"cost": [[1, 2], [3, 4]], "m": 1, "n": 1}` | `5` | `4` |
| [MinCost/53](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinCost/mutant_53/c/replay.json) | specification violation | `{"cost": [[1, 2], [3, 4]], "m": 1, "n": 1}` | `5` | `4` |
| [MinCost/54](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinCost/mutant_54/c/replay.json) | precondition/RTE failure | `{"cost": [[0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0]], "m": 4, "n": 4}` | `0` | `null` |
| [MinCost/55](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinCost/mutant_55/c/replay.json) | specification violation | `{"cost": [[1, 2], [3, 4]], "m": 1, "n": 1}` | `5` | `4` |
| [MinCost/56](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinCost/mutant_56/c/replay.json) | specification violation | `{"cost": [[1, 2], [3, 4]], "m": 1, "n": 1}` | `5` | `1` |
| [MinCost/57](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinCost/mutant_57/c/replay.json) | specification violation | `{"cost": [[1, 2], [3, 4]], "m": 1, "n": 1}` | `5` | `4` |
| [MinCost/59](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinCost/mutant_59/c/replay.json) | specification violation | `{"cost": [[1, 2], [3, 4]], "m": 1, "n": 1}` | `5` | `0` |
| [MinCost/6](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinCost/mutant_6/c/replay.json) | precondition/RTE failure | `{"cost": [[0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0]], "m": 4, "n": 4}` | `0` | `null` |
| [MinCost/60](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinCost/mutant_60/c/replay.json) | specification violation | `{"cost": [[1, 2], [3, 4]], "m": 1, "n": 1}` | `5` | `0` |
| [MinCost/7](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinCost/mutant_7/c/replay.json) | precondition/RTE failure | `{"cost": [[0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0]], "m": 4, "n": 4}` | `0` | `null` |
| [MinCost/8](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinCost/mutant_8/c/replay.json) | precondition/RTE failure | `{"cost": [[0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0]], "m": 4, "n": 4}` | `0` | `null` |
| [MinCost/9](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinCost/mutant_9/c/replay.json) | specification violation | `{"cost": [[1, 2]], "m": 0, "n": 0}` | `1` | `0` |
| [MinJumps/1](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinJumps/mutant_1/c/replay.json) | specification violation | `{"arr": [0, 2, 0, 0, 0, 0, 0, 0], "n": 9}` | `2147483647` | `0` |
| [MinJumps/13](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinJumps/mutant_13/c/replay.json) | specification violation | `{"arr": [0, 0, 0, 0, 15, 0, 0, 0], "n": 9}` | `-2147483648` | `2147483647` |
| [MinJumps/15](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinJumps/mutant_15/c/replay.json) | specification violation | `{"arr": [0, 2, 0, 0, 0, 0, 0, 0], "n": 9}` | `2147483647` | `1` |
| [MinJumps/16](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinJumps/mutant_16/c/replay.json) | specification violation | `{"arr": [0, 0, 0, 0, 15, 0, 0, 0], "n": 9}` | `-2147483648` | `0` |
| [MinJumps/17](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinJumps/mutant_17/c/replay.json) | specification violation | `{"arr": [0, 0, 0, 0, 15, 0, 0, 0], "n": 9}` | `-2147483648` | `2147483647` |
| [MinJumps/18](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinJumps/mutant_18/c/replay.json) | specification violation | `{"arr": [0, 0, 0, 0, 15, 0, 0, 0], "n": 9}` | `-2147483648` | `2147483646` |
| [MinJumps/19](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinJumps/mutant_19/c/replay.json) | specification violation | `{"arr": [0, 0, 0, 0, 15, 0, 0, 0], "n": 9}` | `-2147483648` | `2147483647` |
| [MinJumps/2](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinJumps/mutant_2/c/replay.json) | specification violation | `{"arr": null, "n": 1}` | `0` | `2147483647` |
| [MinJumps/20](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinJumps/mutant_20/c/replay.json) | specification violation | `{"arr": [0, 0, 0, 0, 15, 0, 0, 0], "n": 9}` | `-2147483648` | `2147483647` |
| [MinJumps/21](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinJumps/mutant_21/c/replay.json) | specification violation | `{"arr": [0, 2, 0, 0, 0, 0, 0, 0], "n": 9}` | `2147483647` | `0` |
| [MinJumps/22](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinJumps/mutant_22/c/replay.json) | precondition/RTE failure | `{"arr": [0, 2, 0, 0, 0, 0, 0, 0], "n": 9}` | `2147483647` | `null` |
| [MinJumps/23](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinJumps/mutant_23/c/replay.json) | precondition/RTE failure | `{"arr": [0, 2, 0, 0, 0, 0, 0, 0], "n": 9}` | `2147483647` | `null` |
| [MinJumps/24](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinJumps/mutant_24/c/replay.json) | precondition/RTE failure | `{"arr": [0, 2, 0, 0, 0, 0, 0, 0], "n": 9}` | `2147483647` | `null` |
| [MinJumps/4](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinJumps/mutant_4/c/replay.json) | precondition/RTE failure | `{"arr": [0, 2, 0, 0, 0, 0, 0, 0], "n": 9}` | `2147483647` | `null` |
| [MinJumps/7](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MinJumps/mutant_7/c/replay.json) | specification violation | `{"arr": [0, 1], "n": 2}` | `2147483647` | `-2147483648` |
| [MoveFirst/1](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MoveFirst/mutant_1/c/replay.json) | precondition/RTE failure | `{"testArray": null}` | `null` | `null` |
| [MoveFirst/10](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MoveFirst/mutant_10/c/replay.json) | precondition/RTE failure | `{"testArray": [0, 0, 0]}` | `[0, 0, 0]` | `null` |
| [MoveFirst/11](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MoveFirst/mutant_11/c/replay.json) | precondition/RTE failure | `{"testArray": [0, 0, 0]}` | `[0, 0, 0]` | `null` |
| [MoveFirst/12](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MoveFirst/mutant_12/c/replay.json) | precondition/RTE failure | `{"testArray": [0, 0, 0]}` | `[0, 0, 0]` | `null` |
| [MoveFirst/15](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MoveFirst/mutant_15/c/replay.json) | precondition/RTE failure | `{"testArray": [0, 0, 0]}` | `[0, 0, 0]` | `null` |
| [MoveFirst/16](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MoveFirst/mutant_16/c/replay.json) | precondition/RTE failure | `{"testArray": [0, 0, 0]}` | `[0, 0, 0]` | `null` |
| [MoveFirst/17](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MoveFirst/mutant_17/c/replay.json) | precondition/RTE failure | `{"testArray": [0, 0, 0]}` | `[0, 0, 0]` | `null` |
| [MoveFirst/3](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MoveFirst/mutant_3/c/replay.json) | precondition/RTE failure | `{"testArray": []}` | `[]` | `null` |
| [MoveFirst/4](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MoveFirst/mutant_4/c/replay.json) | precondition/RTE failure | `{"testArray": null}` | `null` | `null` |
| [MoveFirst/5](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MoveFirst/mutant_5/c/replay.json) | precondition/RTE failure | `{"testArray": []}` | `[]` | `null` |
| [MoveFirst/6](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MoveFirst/mutant_6/c/replay.json) | precondition/RTE failure | `{"testArray": null}` | `null` | `null` |
| [MoveFirst/8](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MoveFirst/mutant_8/c/replay.json) | precondition/RTE failure | `{"testArray": []}` | `[]` | `null` |
| [MultiplyElements/1](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MultiplyElements/mutant_1/c/replay.json) | specification violation | `{"testTup": [0, 2, 0]}` | `[0, 0]` | `[]` |
| [MultiplyElements/10](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MultiplyElements/mutant_10/c/replay.json) | precondition/RTE failure | `{"testTup": [0, 2, 0]}` | `[0, 0]` | `null` |
| [MultiplyElements/11](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MultiplyElements/mutant_11/c/replay.json) | precondition/RTE failure | `{"testTup": [0, 2, 0]}` | `[0, 0]` | `null` |
| [MultiplyElements/12](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MultiplyElements/mutant_12/c/replay.json) | precondition/RTE failure | `{"testTup": [0, 2, 0]}` | `[0, 0]` | `null` |
| [MultiplyElements/14](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MultiplyElements/mutant_14/c/replay.json) | precondition/RTE failure | `{"testTup": [0, 2, 0]}` | `[0, 0]` | `null` |
| [MultiplyElements/17](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MultiplyElements/mutant_17/c/replay.json) | specification violation | `{"testTup": [0, 2, 0]}` | `[0, 0]` | `[0, 4]` |
| [MultiplyElements/18](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MultiplyElements/mutant_18/c/replay.json) | precondition/RTE failure | `{"testTup": [0, 2, 0]}` | `[0, 0]` | `null` |
| [MultiplyElements/19](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MultiplyElements/mutant_19/c/replay.json) | specification violation | `{"testTup": [0, 2, 0]}` | `[0, 0]` | `[0, 4]` |
| [MultiplyElements/2](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MultiplyElements/mutant_2/c/replay.json) | specification violation | `{"testTup": [1, 0]}` | `[0]` | `[]` |
| [MultiplyElements/20](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MultiplyElements/mutant_20/c/replay.json) | specification violation | `{"testTup": [2, 1]}` | `[2]` | `[0]` |
| [MultiplyElements/21](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MultiplyElements/mutant_21/c/replay.json) | specification violation | `{"testTup": [0, 2, 0]}` | `[0, 0]` | `[2, 2]` |
| [MultiplyElements/22](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MultiplyElements/mutant_22/c/replay.json) | specification violation | `{"testTup": [0, 2, 0]}` | `[0, 0]` | `[-2, 2]` |
| [MultiplyElements/23](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MultiplyElements/mutant_23/c/replay.json) | specification violation | `{"testTup": [3, 1, 2]}` | `[3, 2]` | `[3, 0]` |
| [MultiplyElements/3](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MultiplyElements/mutant_3/c/replay.json) | precondition/RTE failure | `{"testTup": []}` | `[]` | `null` |
| [MultiplyElements/4](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MultiplyElements/mutant_4/c/replay.json) | precondition/RTE failure | `{"testTup": []}` | `[]` | `null` |
| [MultiplyElements/5](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MultiplyElements/mutant_5/c/replay.json) | precondition/RTE failure | `{"testTup": [0, 2, 0]}` | `[0, 0]` | `null` |
| [MultiplyElements/6](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MultiplyElements/mutant_6/c/replay.json) | specification violation | `{"testTup": [0, 2, 0]}` | `[0, 0]` | `[0, 0, 0]` |
| [MultiplyElements/7](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MultiplyElements/mutant_7/c/replay.json) | specification violation | `{"testTup": [0, 2, 0]}` | `[0, 0]` | `[0, 0, 0, 0]` |
| [MultiplyElements/8](c_counterexamples_refreshed_all_c_20261001_search_only/cases/MultiplyElements/mutant_8/c/replay.json) | specification violation | `{"testTup": [0, 2, 0]}` | `[0, 0]` | `[0, 0, 0]` |
| [NewmanPrime/10](c_counterexamples_refreshed_all_c_20261001_search_only/cases/NewmanPrime/mutant_10/c/replay.json) | precondition/RTE failure | `{"n": 0}` | `1` | `null` |
| [NewmanPrime/11](c_counterexamples_refreshed_all_c_20261001_search_only/cases/NewmanPrime/mutant_11/c/replay.json) | specification violation | `{"n": 3}` | `7` | `3` |
| [NewmanPrime/12](c_counterexamples_refreshed_all_c_20261001_search_only/cases/NewmanPrime/mutant_12/c/replay.json) | precondition/RTE failure | `{"n": 2}` | `3` | `null` |
| [NewmanPrime/13](c_counterexamples_refreshed_all_c_20261001_search_only/cases/NewmanPrime/mutant_13/c/replay.json) | precondition/RTE failure | `{"n": 2}` | `3` | `null` |
| [NewmanPrime/14](c_counterexamples_refreshed_all_c_20261001_search_only/cases/NewmanPrime/mutant_14/c/replay.json) | precondition/RTE failure | `{"n": 2}` | `3` | `null` |
| [NewmanPrime/19](c_counterexamples_refreshed_all_c_20261001_search_only/cases/NewmanPrime/mutant_19/c/replay.json) | specification violation | `{"n": 4}` | `17` | `15` |
| [NewmanPrime/2](c_counterexamples_refreshed_all_c_20261001_search_only/cases/NewmanPrime/mutant_2/c/replay.json) | specification violation | `{"n": 2}` | `3` | `1` |
| [NewmanPrime/20](c_counterexamples_refreshed_all_c_20261001_search_only/cases/NewmanPrime/mutant_20/c/replay.json) | precondition/RTE failure | `{"n": 2}` | `3` | `null` |
| [NewmanPrime/21](c_counterexamples_refreshed_all_c_20261001_search_only/cases/NewmanPrime/mutant_21/c/replay.json) | precondition/RTE failure | `{"n": 2}` | `3` | `null` |
| [NewmanPrime/22](c_counterexamples_refreshed_all_c_20261001_search_only/cases/NewmanPrime/mutant_22/c/replay.json) | specification violation | `{"n": 7}` | `239` | `169` |
| [NewmanPrime/23](c_counterexamples_refreshed_all_c_20261001_search_only/cases/NewmanPrime/mutant_23/c/replay.json) | specification violation | `{"n": 2}` | `3` | `0` |
| [NewmanPrime/3](c_counterexamples_refreshed_all_c_20261001_search_only/cases/NewmanPrime/mutant_3/c/replay.json) | precondition/RTE failure | `{"n": 0}` | `1` | `null` |
| [NewmanPrime/5](c_counterexamples_refreshed_all_c_20261001_search_only/cases/NewmanPrime/mutant_5/c/replay.json) | specification violation | `{"n": 2}` | `3` | `1` |
| [NewmanPrime/6](c_counterexamples_refreshed_all_c_20261001_search_only/cases/NewmanPrime/mutant_6/c/replay.json) | precondition/RTE failure | `{"n": 1}` | `1` | `null` |
| [NewmanPrime/7](c_counterexamples_refreshed_all_c_20261001_search_only/cases/NewmanPrime/mutant_7/c/replay.json) | precondition/RTE failure | `{"n": 1}` | `1` | `null` |
| [NewmanPrime/8](c_counterexamples_refreshed_all_c_20261001_search_only/cases/NewmanPrime/mutant_8/c/replay.json) | precondition/RTE failure | `{"n": 0}` | `1` | `null` |
| [NewmanPrime/9](c_counterexamples_refreshed_all_c_20261001_search_only/cases/NewmanPrime/mutant_9/c/replay.json) | specification violation | `{"n": 2}` | `3` | `1` |
| [NextPowerOf2/2](c_counterexamples_refreshed_all_c_20261001_search_only/cases/NextPowerOf2/mutant_2/c/replay.json) | specification violation | `{"n": 4096}` | `4096` | `1` |
| [NextPowerOf2/6](c_counterexamples_refreshed_all_c_20261001_search_only/cases/NextPowerOf2/mutant_6/c/replay.json) | specification violation | `{"n": 4096}` | `4096` | `8192` |
| [NoOfCubes/1](c_counterexamples_refreshed_all_c_20261001_search_only/cases/NoOfCubes/mutant_1/c/replay.json) | specification violation | `{"k": 462, "n": 1}` | `-97336000` | `423200` |
| [NoOfCubes/10](c_counterexamples_refreshed_all_c_20261001_search_only/cases/NoOfCubes/mutant_10/c/replay.json) | specification violation | `{"k": 462, "n": 1}` | `-97336000` | `97970800` |
| [NoOfCubes/11](c_counterexamples_refreshed_all_c_20261001_search_only/cases/NoOfCubes/mutant_11/c/replay.json) | specification violation | `{"k": 462, "n": 1}` | `-97336000` | `98182400` |
| [NoOfCubes/12](c_counterexamples_refreshed_all_c_20261001_search_only/cases/NoOfCubes/mutant_12/c/replay.json) | specification violation | `{"k": 462, "n": 1}` | `-97336000` | `211600` |
| [NoOfCubes/13](c_counterexamples_refreshed_all_c_20261001_search_only/cases/NoOfCubes/mutant_13/c/replay.json) | specification violation | `{"k": 0, "n": 0}` | `1` | `0` |
| [NoOfCubes/14](c_counterexamples_refreshed_all_c_20261001_search_only/cases/NoOfCubes/mutant_14/c/replay.json) | specification violation | `{"k": 0, "n": 0}` | `1` | `0` |
| [NoOfCubes/15](c_counterexamples_refreshed_all_c_20261001_search_only/cases/NoOfCubes/mutant_15/c/replay.json) | specification violation | `{"k": 0, "n": 0}` | `1` | `-1` |
| [NoOfCubes/16](c_counterexamples_refreshed_all_c_20261001_search_only/cases/NoOfCubes/mutant_16/c/replay.json) | specification violation | `{"k": 0, "n": 0}` | `1` | `0` |
| [NoOfCubes/17](c_counterexamples_refreshed_all_c_20261001_search_only/cases/NoOfCubes/mutant_17/c/replay.json) | specification violation | `{"k": 0, "n": 0}` | `1` | `0` |
| [NoOfCubes/18](c_counterexamples_refreshed_all_c_20261001_search_only/cases/NoOfCubes/mutant_18/c/replay.json) | specification violation | `{"k": 0, "n": 0}` | `1` | `2` |
| [NoOfCubes/19](c_counterexamples_refreshed_all_c_20261001_search_only/cases/NoOfCubes/mutant_19/c/replay.json) | specification violation | `{"k": 0, "n": 0}` | `1` | `0` |
| [NoOfCubes/2](c_counterexamples_refreshed_all_c_20261001_search_only/cases/NoOfCubes/mutant_2/c/replay.json) | specification violation | `{"k": 462, "n": 1}` | `-97336000` | `97970800` |
| [NoOfCubes/20](c_counterexamples_refreshed_all_c_20261001_search_only/cases/NoOfCubes/mutant_20/c/replay.json) | specification violation | `{"k": 462, "n": 1}` | `-97336000` | `-460` |
| [NoOfCubes/21](c_counterexamples_refreshed_all_c_20261001_search_only/cases/NoOfCubes/mutant_21/c/replay.json) | specification violation | `{"k": 462, "n": 1}` | `-97336000` | `423200` |
| [NoOfCubes/22](c_counterexamples_refreshed_all_c_20261001_search_only/cases/NoOfCubes/mutant_22/c/replay.json) | specification violation | `{"k": 462, "n": 1}` | `-97336000` | `97970800` |
| [NoOfCubes/23](c_counterexamples_refreshed_all_c_20261001_search_only/cases/NoOfCubes/mutant_23/c/replay.json) | specification violation | `{"k": 462, "n": 1}` | `-97336000` | `98182400` |
| [NoOfCubes/24](c_counterexamples_refreshed_all_c_20261001_search_only/cases/NoOfCubes/mutant_24/c/replay.json) | specification violation | `{"k": 462, "n": 1}` | `-97336000` | `211600` |
| [NoOfCubes/25](c_counterexamples_refreshed_all_c_20261001_search_only/cases/NoOfCubes/mutant_25/c/replay.json) | specification violation | `{"k": 0, "n": 0}` | `1` | `0` |
| [NoOfCubes/26](c_counterexamples_refreshed_all_c_20261001_search_only/cases/NoOfCubes/mutant_26/c/replay.json) | specification violation | `{"k": 0, "n": 0}` | `1` | `0` |
| [NoOfCubes/27](c_counterexamples_refreshed_all_c_20261001_search_only/cases/NoOfCubes/mutant_27/c/replay.json) | specification violation | `{"k": 0, "n": 0}` | `1` | `-1` |
| [NoOfCubes/28](c_counterexamples_refreshed_all_c_20261001_search_only/cases/NoOfCubes/mutant_28/c/replay.json) | specification violation | `{"k": 0, "n": 0}` | `1` | `0` |
| [NoOfCubes/29](c_counterexamples_refreshed_all_c_20261001_search_only/cases/NoOfCubes/mutant_29/c/replay.json) | specification violation | `{"k": 0, "n": 0}` | `1` | `0` |
| [NoOfCubes/3](c_counterexamples_refreshed_all_c_20261001_search_only/cases/NoOfCubes/mutant_3/c/replay.json) | specification violation | `{"k": 462, "n": 1}` | `-97336000` | `98182400` |
| [NoOfCubes/30](c_counterexamples_refreshed_all_c_20261001_search_only/cases/NoOfCubes/mutant_30/c/replay.json) | specification violation | `{"k": 0, "n": 0}` | `1` | `2` |
| [NoOfCubes/31](c_counterexamples_refreshed_all_c_20261001_search_only/cases/NoOfCubes/mutant_31/c/replay.json) | specification violation | `{"k": 0, "n": 0}` | `1` | `0` |
| [NoOfCubes/32](c_counterexamples_refreshed_all_c_20261001_search_only/cases/NoOfCubes/mutant_32/c/replay.json) | specification violation | `{"k": 462, "n": 1}` | `-97336000` | `-460` |
| [NoOfCubes/33](c_counterexamples_refreshed_all_c_20261001_search_only/cases/NoOfCubes/mutant_33/c/replay.json) | specification violation | `{"k": 0, "n": 0}` | `1` | `0` |
| [NoOfCubes/4](c_counterexamples_refreshed_all_c_20261001_search_only/cases/NoOfCubes/mutant_4/c/replay.json) | specification violation | `{"k": 462, "n": 1}` | `-97336000` | `211600` |
| [NoOfCubes/5](c_counterexamples_refreshed_all_c_20261001_search_only/cases/NoOfCubes/mutant_5/c/replay.json) | specification violation | `{"k": 0, "n": 0}` | `1` | `0` |
| [NoOfCubes/6](c_counterexamples_refreshed_all_c_20261001_search_only/cases/NoOfCubes/mutant_6/c/replay.json) | specification violation | `{"k": 0, "n": 0}` | `1` | `0` |
| [NoOfCubes/7](c_counterexamples_refreshed_all_c_20261001_search_only/cases/NoOfCubes/mutant_7/c/replay.json) | specification violation | `{"k": 0, "n": 0}` | `1` | `-1` |
| [NoOfCubes/8](c_counterexamples_refreshed_all_c_20261001_search_only/cases/NoOfCubes/mutant_8/c/replay.json) | specification violation | `{"k": 0, "n": 0}` | `1` | `0` |
| [NoOfCubes/9](c_counterexamples_refreshed_all_c_20261001_search_only/cases/NoOfCubes/mutant_9/c/replay.json) | specification violation | `{"k": 462, "n": 1}` | `-97336000` | `423200` |
| [OddBitSetNumber/1](c_counterexamples_refreshed_all_c_20261001_search_only/cases/OddBitSetNumber/mutant_1/c/replay.json) | specification violation | `{"n": 0}` | `0` | `-1` |
| [OddBitSetNumber/10](c_counterexamples_refreshed_all_c_20261001_search_only/cases/OddBitSetNumber/mutant_10/c/replay.json) | specification violation | `{"n": 2125}` | `3679` | `11645` |
| [OddBitSetNumber/12](c_counterexamples_refreshed_all_c_20261001_search_only/cases/OddBitSetNumber/mutant_12/c/replay.json) | specification violation | `{"n": 2125}` | `3679` | `3149` |
| [OddBitSetNumber/13](c_counterexamples_refreshed_all_c_20261001_search_only/cases/OddBitSetNumber/mutant_13/c/replay.json) | specification violation | `{"n": 0}` | `0` | `252645135` |
| [OddBitSetNumber/14](c_counterexamples_refreshed_all_c_20261001_search_only/cases/OddBitSetNumber/mutant_14/c/replay.json) | specification violation | `{"n": 0}` | `0` | `252645135` |
| [OddBitSetNumber/18](c_counterexamples_refreshed_all_c_20261001_search_only/cases/OddBitSetNumber/mutant_18/c/replay.json) | specification violation | `{"n": 0}` | `0` | `16711935` |
| [OddBitSetNumber/19](c_counterexamples_refreshed_all_c_20261001_search_only/cases/OddBitSetNumber/mutant_19/c/replay.json) | specification violation | `{"n": 0}` | `0` | `16711935` |
| [OddBitSetNumber/2](c_counterexamples_refreshed_all_c_20261001_search_only/cases/OddBitSetNumber/mutant_2/c/replay.json) | specification violation | `{"n": 0}` | `0` | `-1` |
| [OddBitSetNumber/20](c_counterexamples_refreshed_all_c_20261001_search_only/cases/OddBitSetNumber/mutant_20/c/replay.json) | specification violation | `{"n": 2125}` | `3679` | `527967` |
| [OddBitSetNumber/23](c_counterexamples_refreshed_all_c_20261001_search_only/cases/OddBitSetNumber/mutant_23/c/replay.json) | specification violation | `{"n": 0}` | `0` | `65535` |
| [OddBitSetNumber/24](c_counterexamples_refreshed_all_c_20261001_search_only/cases/OddBitSetNumber/mutant_24/c/replay.json) | specification violation | `{"n": 0}` | `0` | `65535` |
| [OddBitSetNumber/3](c_counterexamples_refreshed_all_c_20261001_search_only/cases/OddBitSetNumber/mutant_3/c/replay.json) | specification violation | `{"n": 0}` | `0` | `1431655765` |
| [OddBitSetNumber/4](c_counterexamples_refreshed_all_c_20261001_search_only/cases/OddBitSetNumber/mutant_4/c/replay.json) | specification violation | `{"n": 0}` | `0` | `1431655765` |
| [OddBitSetNumber/5](c_counterexamples_refreshed_all_c_20261001_search_only/cases/OddBitSetNumber/mutant_5/c/replay.json) | specification violation | `{"n": 2125}` | `3679` | `6751` |
| [OddBitSetNumber/7](c_counterexamples_refreshed_all_c_20261001_search_only/cases/OddBitSetNumber/mutant_7/c/replay.json) | specification violation | `{"n": 2125}` | `3679` | `2655` |
| [OddBitSetNumber/8](c_counterexamples_refreshed_all_c_20261001_search_only/cases/OddBitSetNumber/mutant_8/c/replay.json) | specification violation | `{"n": 0}` | `0` | `858993459` |
| [OddBitSetNumber/9](c_counterexamples_refreshed_all_c_20261001_search_only/cases/OddBitSetNumber/mutant_9/c/replay.json) | specification violation | `{"n": 0}` | `0` | `858993459` |
| [OddLengthSum/10](c_counterexamples_refreshed_all_c_20261001_search_only/cases/OddLengthSum/mutant_10/c/replay.json) | specification violation | `{"arr": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | `20416` | `31552` |
| [OddLengthSum/12](c_counterexamples_refreshed_all_c_20261001_search_only/cases/OddLengthSum/mutant_12/c/replay.json) | specification violation | `{"arr": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | `20416` | `3712` |
| [OddLengthSum/13](c_counterexamples_refreshed_all_c_20261001_search_only/cases/OddLengthSum/mutant_13/c/replay.json) | specification violation | `{"arr": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | `20416` | `9280` |
| [OddLengthSum/14](c_counterexamples_refreshed_all_c_20261001_search_only/cases/OddLengthSum/mutant_14/c/replay.json) | specification violation | `{"arr": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | `20416` | `-1856` |
| [OddLengthSum/15](c_counterexamples_refreshed_all_c_20261001_search_only/cases/OddLengthSum/mutant_15/c/replay.json) | specification violation | `{"arr": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | `20416` | `0` |
| [OddLengthSum/16](c_counterexamples_refreshed_all_c_20261001_search_only/cases/OddLengthSum/mutant_16/c/replay.json) | specification violation | `{"arr": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | `20416` | `0` |
| [OddLengthSum/17](c_counterexamples_refreshed_all_c_20261001_search_only/cases/OddLengthSum/mutant_17/c/replay.json) | specification violation | `{"arr": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | `20416` | `18560` |
| [OddLengthSum/18](c_counterexamples_refreshed_all_c_20261001_search_only/cases/OddLengthSum/mutant_18/c/replay.json) | specification violation | `{"arr": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | `20416` | `18560` |
| [OddLengthSum/19](c_counterexamples_refreshed_all_c_20261001_search_only/cases/OddLengthSum/mutant_19/c/replay.json) | specification violation | `{"arr": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | `20416` | `18560` |
| [OddLengthSum/2](c_counterexamples_refreshed_all_c_20261001_search_only/cases/OddLengthSum/mutant_2/c/replay.json) | precondition/RTE failure | `{"arr": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | `20416` | `null` |
| [OddLengthSum/20](c_counterexamples_refreshed_all_c_20261001_search_only/cases/OddLengthSum/mutant_20/c/replay.json) | specification violation | `{"arr": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | `20416` | `0` |
| [OddLengthSum/21](c_counterexamples_refreshed_all_c_20261001_search_only/cases/OddLengthSum/mutant_21/c/replay.json) | specification violation | `{"arr": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | `20416` | `81664` |
| [OddLengthSum/22](c_counterexamples_refreshed_all_c_20261001_search_only/cases/OddLengthSum/mutant_22/c/replay.json) | specification violation | `{"arr": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | `20416` | `44544` |
| [OddLengthSum/23](c_counterexamples_refreshed_all_c_20261001_search_only/cases/OddLengthSum/mutant_23/c/replay.json) | specification violation | `{"arr": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | `20416` | `37120` |
| [OddLengthSum/24](c_counterexamples_refreshed_all_c_20261001_search_only/cases/OddLengthSum/mutant_24/c/replay.json) | specification violation | `{"arr": [-1]}` | `-1` | `0` |
| [OddLengthSum/25](c_counterexamples_refreshed_all_c_20261001_search_only/cases/OddLengthSum/mutant_25/c/replay.json) | specification violation | `{"arr": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | `20416` | `1941` |
| [OddLengthSum/26](c_counterexamples_refreshed_all_c_20261001_search_only/cases/OddLengthSum/mutant_26/c/replay.json) | specification violation | `{"arr": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | `20416` | `-1771` |
| [OddLengthSum/27](c_counterexamples_refreshed_all_c_20261001_search_only/cases/OddLengthSum/mutant_27/c/replay.json) | specification violation | `{"arr": [2]}` | `2` | `0` |
| [OddLengthSum/28](c_counterexamples_refreshed_all_c_20261001_search_only/cases/OddLengthSum/mutant_28/c/replay.json) | specification violation | `{"arr": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | `20416` | `0` |
| [OddLengthSum/4](c_counterexamples_refreshed_all_c_20261001_search_only/cases/OddLengthSum/mutant_4/c/replay.json) | specification violation | `{"arr": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | `20416` | `0` |
| [OddLengthSum/5](c_counterexamples_refreshed_all_c_20261001_search_only/cases/OddLengthSum/mutant_5/c/replay.json) | specification violation | `{"arr": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | `20416` | `12992` |
| [OddLengthSum/6](c_counterexamples_refreshed_all_c_20261001_search_only/cases/OddLengthSum/mutant_6/c/replay.json) | specification violation | `{"arr": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | `20416` | `7424` |
| [OddLengthSum/7](c_counterexamples_refreshed_all_c_20261001_search_only/cases/OddLengthSum/mutant_7/c/replay.json) | specification violation | `{"arr": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | `20416` | `12992` |
| [OddLengthSum/9](c_counterexamples_refreshed_all_c_20261001_search_only/cases/OddLengthSum/mutant_9/c/replay.json) | specification violation | `{"arr": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | `20416` | `50112` |
| [PairWise/1](c_counterexamples_refreshed_all_c_20261001_search_only/cases/PairWise/mutant_1/c/replay.json) | specification violation | `{"l1": [0, 0, 0]}` | `[[0, 0], [0, 0]]` | `[]` |
| [PairWise/10](c_counterexamples_refreshed_all_c_20261001_search_only/cases/PairWise/mutant_10/c/replay.json) | precondition/RTE failure | `{"l1": [0, 0]}` | `[[0, 0]]` | `null` |
| [PairWise/11](c_counterexamples_refreshed_all_c_20261001_search_only/cases/PairWise/mutant_11/c/replay.json) | precondition/RTE failure | `{"l1": [0, 0]}` | `[[0, 0]]` | `null` |
| [PairWise/12](c_counterexamples_refreshed_all_c_20261001_search_only/cases/PairWise/mutant_12/c/replay.json) | precondition/RTE failure | `{"l1": [0, 0]}` | `[[0, 0]]` | `null` |
| [PairWise/14](c_counterexamples_refreshed_all_c_20261001_search_only/cases/PairWise/mutant_14/c/replay.json) | precondition/RTE failure | `{"l1": [0, 0]}` | `[[0, 0]]` | `null` |
| [PairWise/19](c_counterexamples_refreshed_all_c_20261001_search_only/cases/PairWise/mutant_19/c/replay.json) | precondition/RTE failure | `{"l1": [0, 0]}` | `[[0, 0]]` | `null` |
| [PairWise/2](c_counterexamples_refreshed_all_c_20261001_search_only/cases/PairWise/mutant_2/c/replay.json) | specification violation | `{"l1": [0, 0]}` | `[[0, 0]]` | `[]` |
| [PairWise/3](c_counterexamples_refreshed_all_c_20261001_search_only/cases/PairWise/mutant_3/c/replay.json) | precondition/RTE failure | `{"l1": []}` | `[]` | `null` |
| [PairWise/4](c_counterexamples_refreshed_all_c_20261001_search_only/cases/PairWise/mutant_4/c/replay.json) | precondition/RTE failure | `{"l1": []}` | `[]` | `null` |
| [PairWise/5](c_counterexamples_refreshed_all_c_20261001_search_only/cases/PairWise/mutant_5/c/replay.json) | precondition/RTE failure | `{"l1": [0, 0]}` | `[[0, 0]]` | `null` |
| [PairWise/6](c_counterexamples_refreshed_all_c_20261001_search_only/cases/PairWise/mutant_6/c/replay.json) | specification violation | `{"l1": [0, 0]}` | `[[0, 0]]` | `[[0, 0], [0, 0]]` |
| [PairWise/7](c_counterexamples_refreshed_all_c_20261001_search_only/cases/PairWise/mutant_7/c/replay.json) | specification violation | `{"l1": [0, 0]}` | `[[0, 0]]` | `[[0, 0], [0, 0], [0, 0]]` |
| [PairWise/8](c_counterexamples_refreshed_all_c_20261001_search_only/cases/PairWise/mutant_8/c/replay.json) | specification violation | `{"l1": [0, 0]}` | `[[0, 0]]` | `[[0, 0], [0, 0]]` |
| [ParabolaVertex/1](c_counterexamples_refreshed_all_c_20261001_search_only/cases/ParabolaVertex/mutant_1/c/replay.json) | specification violation | `{"a": 3514, "b": 3514, "c": 3514}` | `[-0.5, 2635.5]` | `[0.5, 2635.5]` |
| [ParabolaVertex/10](c_counterexamples_refreshed_all_c_20261001_search_only/cases/ParabolaVertex/mutant_10/c/replay.json) | specification violation | `{"a": 3514, "b": 3514, "c": 3514}` | `[-0.5, 2635.5]` | `[0, 2635.5]` |
| [ParabolaVertex/11](c_counterexamples_refreshed_all_c_20261001_search_only/cases/ParabolaVertex/mutant_11/c/replay.json) | specification violation | `{"a": 3514, "b": 3514, "c": 3514}` | `[-0.5, 2635.5]` | `[-0.5, -877.5]` |
| [ParabolaVertex/12](c_counterexamples_refreshed_all_c_20261001_search_only/cases/ParabolaVertex/mutant_12/c/replay.json) | specification violation | `{"a": 3514, "b": 3514, "c": 3514}` | `[-0.5, 2635.5]` | `[-0.5, 0.0002845759817871372]` |
| [ParabolaVertex/13](c_counterexamples_refreshed_all_c_20261001_search_only/cases/ParabolaVertex/mutant_13/c/replay.json) | specification violation | `{"a": 3514, "b": 3514, "c": 3514}` | `[-0.5, 2635.5]` | `[-0.5, -1756.9997154240182]` |
| [ParabolaVertex/14](c_counterexamples_refreshed_all_c_20261001_search_only/cases/ParabolaVertex/mutant_14/c/replay.json) | specification violation | `{"a": 3514, "b": 3514, "c": 3514}` | `[-0.5, 2635.5]` | `[-0.5, -878.4997154240182]` |
| [ParabolaVertex/15](c_counterexamples_refreshed_all_c_20261001_search_only/cases/ParabolaVertex/mutant_15/c/replay.json) | specification violation | `{"a": 3514, "b": 3514, "c": 3514}` | `[-0.5, 2635.5]` | `[-0.5, -878.5]` |
| [ParabolaVertex/16](c_counterexamples_refreshed_all_c_20261001_search_only/cases/ParabolaVertex/mutant_16/c/replay.json) | specification violation | `{"a": 3514, "b": 3514, "c": 3514}` | `[-0.5, 2635.5]` | `[-0.5, -877.25]` |
| [ParabolaVertex/17](c_counterexamples_refreshed_all_c_20261001_search_only/cases/ParabolaVertex/mutant_17/c/replay.json) | specification violation | `{"a": 3514, "b": 3514, "c": 3514}` | `[-0.5, 2635.5]` | `[-0.5, -877.75]` |
| [ParabolaVertex/18](c_counterexamples_refreshed_all_c_20261001_search_only/cases/ParabolaVertex/mutant_18/c/replay.json) | specification violation | `{"a": 3514, "b": 3514, "c": 3514}` | `[-0.5, 2635.5]` | `[-0.5, -878.4997154240182]` |
| [ParabolaVertex/19](c_counterexamples_refreshed_all_c_20261001_search_only/cases/ParabolaVertex/mutant_19/c/replay.json) | specification violation | `{"a": 3514, "b": 3514, "c": 3514}` | `[-0.5, 2635.5]` | `[-0.5, 3514]` |
| [ParabolaVertex/2](c_counterexamples_refreshed_all_c_20261001_search_only/cases/ParabolaVertex/mutant_2/c/replay.json) | specification violation | `{"a": 3514, "b": 3514, "c": 3514}` | `[-0.5, 2635.5]` | `[-1757, 2635.5]` |
| [ParabolaVertex/20](c_counterexamples_refreshed_all_c_20261001_search_only/cases/ParabolaVertex/mutant_20/c/replay.json) | specification violation | `{"a": 3514, "b": 3514, "c": 3514}` | `[-0.5, 2635.5]` | `[-0.5, 3513.5]` |
| [ParabolaVertex/21](c_counterexamples_refreshed_all_c_20261001_search_only/cases/ParabolaVertex/mutant_21/c/replay.json) | specification violation | `{"a": 3514, "b": 3514, "c": 3514}` | `[-0.5, 2635.5]` | `[-0.5, 3514]` |
| [ParabolaVertex/22](c_counterexamples_refreshed_all_c_20261001_search_only/cases/ParabolaVertex/mutant_22/c/replay.json) | specification violation | `{"a": 3514, "b": 3514, "c": 3514}` | `[-0.5, 2635.5]` | `[-0.5, 3513.9999288560048]` |
| [ParabolaVertex/23](c_counterexamples_refreshed_all_c_20261001_search_only/cases/ParabolaVertex/mutant_23/c/replay.json) | specification violation | `{"a": 3514, "b": 3514, "c": 3514}` | `[-0.5, 2635.5]` | `[-0.5, 0]` |
| [ParabolaVertex/24](c_counterexamples_refreshed_all_c_20261001_search_only/cases/ParabolaVertex/mutant_24/c/replay.json) | specification violation | `{"a": 3514, "b": 3514, "c": 3514}` | `[-0.5, 2635.5]` | `[-0.5, 43391560744]` |
| [ParabolaVertex/25](c_counterexamples_refreshed_all_c_20261001_search_only/cases/ParabolaVertex/mutant_25/c/replay.json) | specification violation | `{"a": 3514, "b": 3514, "c": 3514}` | `[-0.5, 2635.5]` | `[-0.5, 4392.5]` |
| [ParabolaVertex/26](c_counterexamples_refreshed_all_c_20261001_search_only/cases/ParabolaVertex/mutant_26/c/replay.json) | specification violation | `{"a": 3514, "b": 3514, "c": 3514}` | `[-0.5, 2635.5]` | `[-0.5, 0.0002845759817871372]` |
| [ParabolaVertex/27](c_counterexamples_refreshed_all_c_20261001_search_only/cases/ParabolaVertex/mutant_27/c/replay.json) | specification violation | `{"a": 3514, "b": 3514, "c": 3514}` | `[-0.5, 2635.5]` | `[-0.5, 9261147]` |
| [ParabolaVertex/28](c_counterexamples_refreshed_all_c_20261001_search_only/cases/ParabolaVertex/mutant_28/c/replay.json) | specification violation | `{"a": 3514, "b": 3514, "c": 3514}` | `[-0.5, 2635.5]` | `[-0.5, 10530.013644115976]` |
| [ParabolaVertex/29](c_counterexamples_refreshed_all_c_20261001_search_only/cases/ParabolaVertex/mutant_29/c/replay.json) | specification violation | `{"a": 3514, "b": 3514, "c": 3514}` | `[-0.5, 2635.5]` | `[-0.5, -10554.013675213675]` |
| [ParabolaVertex/3](c_counterexamples_refreshed_all_c_20261001_search_only/cases/ParabolaVertex/mutant_3/c/replay.json) | specification violation | `{"a": 3514, "b": 3514, "c": 3514}` | `[-0.5, 2635.5]` | `[-0.9994311717861206, 2635.5]` |
| [ParabolaVertex/30](c_counterexamples_refreshed_all_c_20261001_search_only/cases/ParabolaVertex/mutant_30/c/replay.json) | specification violation | `{"a": 3514, "b": 3514, "c": 3514}` | `[-0.5, 2635.5]` | `[-0.5, 32543670558]` |
| [ParabolaVertex/31](c_counterexamples_refreshed_all_c_20261001_search_only/cases/ParabolaVertex/mutant_31/c/replay.json) | specification violation | `{"a": 3514, "b": 3514, "c": 3514}` | `[-0.5, 2635.5]` | `[-0.5, 7028]` |
| [ParabolaVertex/32](c_counterexamples_refreshed_all_c_20261001_search_only/cases/ParabolaVertex/mutant_32/c/replay.json) | specification violation | `{"a": 3514, "b": 3514, "c": 3514}` | `[-0.5, 2635.5]` | `[-0.5, 520698728928]` |
| [ParabolaVertex/33](c_counterexamples_refreshed_all_c_20261001_search_only/cases/ParabolaVertex/mutant_33/c/replay.json) | specification violation | `{"a": 3514, "b": 3514, "c": 3514}` | `[-0.5, 2635.5]` | `[-0.5, 37058644]` |
| [ParabolaVertex/34](c_counterexamples_refreshed_all_c_20261001_search_only/cases/ParabolaVertex/mutant_34/c/replay.json) | specification violation | `{"a": 3514, "b": 3514, "c": 3514}` | `[-0.5, 2635.5]` | `[-0.5, 37030532]` |
| [ParabolaVertex/35](c_counterexamples_refreshed_all_c_20261001_search_only/cases/ParabolaVertex/mutant_35/c/replay.json) | specification violation | `{"a": 3514, "b": 3514, "c": 3514}` | `[-0.5, 2635.5]` | `[-0.5, 0]` |
| [ParabolaVertex/4](c_counterexamples_refreshed_all_c_20261001_search_only/cases/ParabolaVertex/mutant_4/c/replay.json) | specification violation | `{"a": 3514, "b": 3514, "c": 3514}` | `[-0.5, 2635.5]` | `[1.0005694760820045, 2635.5]` |
| [ParabolaVertex/5](c_counterexamples_refreshed_all_c_20261001_search_only/cases/ParabolaVertex/mutant_5/c/replay.json) | specification violation | `{"a": 3514, "b": 3514, "c": 3514}` | `[-0.5, 2635.5]` | `[-6174098, 2635.5]` |
| [ParabolaVertex/6](c_counterexamples_refreshed_all_c_20261001_search_only/cases/ParabolaVertex/mutant_6/c/replay.json) | specification violation | `{"a": 3514, "b": 3514, "c": 3514}` | `[-0.5, 2635.5]` | `[-3514, 2635.5]` |
| [ParabolaVertex/7](c_counterexamples_refreshed_all_c_20261001_search_only/cases/ParabolaVertex/mutant_7/c/replay.json) | specification violation | `{"a": 3514, "b": 3514, "c": 3514}` | `[-0.5, 2635.5]` | `[-24696392, 2635.5]` |
| [ParabolaVertex/8](c_counterexamples_refreshed_all_c_20261001_search_only/cases/ParabolaVertex/mutant_8/c/replay.json) | specification violation | `{"a": 3514, "b": 3514, "c": 3514}` | `[-0.5, 2635.5]` | `[3514, 2635.5]` |
| [ParabolaVertex/9](c_counterexamples_refreshed_all_c_20261001_search_only/cases/ParabolaVertex/mutant_9/c/replay.json) | specification violation | `{"a": 3514, "b": 3514, "c": 3514}` | `[-0.5, 2635.5]` | `[-10542, 2635.5]` |
| [ParallelogramPerimeter/10](c_counterexamples_refreshed_all_c_20261001_search_only/cases/ParallelogramPerimeter/mutant_10/c/replay.json) | specification violation | `{"b": 1709539028, "h": 1}` | `-875889240` | `0` |
| [ParallelogramPerimeter/11](c_counterexamples_refreshed_all_c_20261001_search_only/cases/ParallelogramPerimeter/mutant_11/c/replay.json) | specification violation | `{"b": 1, "h": -972}` | `0` | `-1944` |
| [ParallelogramPerimeter/12](c_counterexamples_refreshed_all_c_20261001_search_only/cases/ParallelogramPerimeter/mutant_12/c/replay.json) | specification violation | `{"b": 1709539028, "h": 1}` | `-875889240` | `0` |
| [ParallelogramPerimeter/13](c_counterexamples_refreshed_all_c_20261001_search_only/cases/ParallelogramPerimeter/mutant_13/c/replay.json) | specification violation | `{"b": 1709539028, "h": 1}` | `-875889240` | `-875889238` |
| [ParallelogramPerimeter/14](c_counterexamples_refreshed_all_c_20261001_search_only/cases/ParallelogramPerimeter/mutant_14/c/replay.json) | specification violation | `{"b": 1709539028, "h": 1}` | `-875889240` | `-875889242` |
| [ParallelogramPerimeter/15](c_counterexamples_refreshed_all_c_20261001_search_only/cases/ParallelogramPerimeter/mutant_15/c/replay.json) | specification violation | `{"b": 1901, "h": 1901}` | `7227602` | `2` |
| [ParallelogramPerimeter/16](c_counterexamples_refreshed_all_c_20261001_search_only/cases/ParallelogramPerimeter/mutant_16/c/replay.json) | specification violation | `{"b": 1709539028, "h": 1}` | `-875889240` | `2` |
| [ParallelogramPerimeter/17](c_counterexamples_refreshed_all_c_20261001_search_only/cases/ParallelogramPerimeter/mutant_17/c/replay.json) | specification violation | `{"b": 1709539028, "h": 1}` | `-875889240` | `1709539030` |
| [ParallelogramPerimeter/18](c_counterexamples_refreshed_all_c_20261001_search_only/cases/ParallelogramPerimeter/mutant_18/c/replay.json) | specification violation | `{"b": 1709539028, "h": 1}` | `-875889240` | `-1709539026` |
| [ParallelogramPerimeter/19](c_counterexamples_refreshed_all_c_20261001_search_only/cases/ParallelogramPerimeter/mutant_19/c/replay.json) | specification violation | `{"b": 1709539028, "h": 1}` | `-875889240` | `0` |
| [ParallelogramPerimeter/2](c_counterexamples_refreshed_all_c_20261001_search_only/cases/ParallelogramPerimeter/mutant_2/c/replay.json) | specification violation | `{"b": -741, "h": 1381865608}` | `0` | `774569136` |
| [ParallelogramPerimeter/3](c_counterexamples_refreshed_all_c_20261001_search_only/cases/ParallelogramPerimeter/mutant_3/c/replay.json) | specification violation | `{"b": 1709539028, "h": 1}` | `-875889240` | `0` |
| [ParallelogramPerimeter/5](c_counterexamples_refreshed_all_c_20261001_search_only/cases/ParallelogramPerimeter/mutant_5/c/replay.json) | specification violation | `{"b": 1, "h": -972}` | `0` | `-1944` |
| [ParallelogramPerimeter/6](c_counterexamples_refreshed_all_c_20261001_search_only/cases/ParallelogramPerimeter/mutant_6/c/replay.json) | specification violation | `{"b": 1709539028, "h": 1}` | `-875889240` | `0` |
| [ParallelogramPerimeter/8](c_counterexamples_refreshed_all_c_20261001_search_only/cases/ParallelogramPerimeter/mutant_8/c/replay.json) | specification violation | `{"b": 1, "h": -972}` | `0` | `-1944` |
| [ParallelogramPerimeter/9](c_counterexamples_refreshed_all_c_20261001_search_only/cases/ParallelogramPerimeter/mutant_9/c/replay.json) | specification violation | `{"b": -741, "h": 1381865608}` | `0` | `774569136` |
| [RadixSort/10](c_counterexamples_refreshed_all_c_20261001_search_only/cases/RadixSort/mutant_10/c/replay.json) | precondition/RTE failure | `{"nums": [1, 0]}` | `[0, 1]` | `null` |
| [RadixSort/11](c_counterexamples_refreshed_all_c_20261001_search_only/cases/RadixSort/mutant_11/c/replay.json) | precondition/RTE failure | `{"nums": [-1]}` | `[-1]` | `null` |
| [RadixSort/12](c_counterexamples_refreshed_all_c_20261001_search_only/cases/RadixSort/mutant_12/c/replay.json) | precondition/RTE failure | `{"nums": [-1, 0, 1]}` | `[-1, 0, 1]` | `null` |
| [RadixSort/13](c_counterexamples_refreshed_all_c_20261001_search_only/cases/RadixSort/mutant_13/c/replay.json) | precondition/RTE failure | `{"nums": [0]}` | `[0]` | `null` |
| [RadixSort/14](c_counterexamples_refreshed_all_c_20261001_search_only/cases/RadixSort/mutant_14/c/replay.json) | precondition/RTE failure | `{"nums": [0]}` | `[0]` | `null` |
| [RadixSort/15](c_counterexamples_refreshed_all_c_20261001_search_only/cases/RadixSort/mutant_15/c/replay.json) | precondition/RTE failure | `{"nums": [0]}` | `[0]` | `null` |
| [RadixSort/16](c_counterexamples_refreshed_all_c_20261001_search_only/cases/RadixSort/mutant_16/c/replay.json) | precondition/RTE failure | `{"nums": [0]}` | `[0]` | `null` |
| [RadixSort/17](c_counterexamples_refreshed_all_c_20261001_search_only/cases/RadixSort/mutant_17/c/replay.json) | specification violation | `{"nums": [2, 1]}` | `[1, 2]` | `[1, 1]` |
| [RadixSort/18](c_counterexamples_refreshed_all_c_20261001_search_only/cases/RadixSort/mutant_18/c/replay.json) | specification violation | `{"nums": [1, 0]}` | `[0, 1]` | `[0, 0]` |
| [RadixSort/19](c_counterexamples_refreshed_all_c_20261001_search_only/cases/RadixSort/mutant_19/c/replay.json) | precondition/RTE failure | `{"nums": [-1]}` | `[-1]` | `null` |
| [RadixSort/20](c_counterexamples_refreshed_all_c_20261001_search_only/cases/RadixSort/mutant_20/c/replay.json) | specification violation | `{"nums": [-1, -1, 0]}` | `[-1, -1, 0]` | `[-1, 0, 0]` |
| [RadixSort/21](c_counterexamples_refreshed_all_c_20261001_search_only/cases/RadixSort/mutant_21/c/replay.json) | specification violation | `{"nums": [1, 0]}` | `[0, 1]` | `[1, 0]` |
| [RadixSort/23](c_counterexamples_refreshed_all_c_20261001_search_only/cases/RadixSort/mutant_23/c/replay.json) | precondition/RTE failure | `{"nums": [0]}` | `[0]` | `null` |
| [RadixSort/26](c_counterexamples_refreshed_all_c_20261001_search_only/cases/RadixSort/mutant_26/c/replay.json) | precondition/RTE failure | `{"nums": [0]}` | `[0]` | `null` |
| [RadixSort/28](c_counterexamples_refreshed_all_c_20261001_search_only/cases/RadixSort/mutant_28/c/replay.json) | specification violation | `{"nums": [-1]}` | `[-1]` | `[0]` |
| [RadixSort/29](c_counterexamples_refreshed_all_c_20261001_search_only/cases/RadixSort/mutant_29/c/replay.json) | specification violation | `{"nums": [-1]}` | `[-1]` | `[0]` |
| [RadixSort/3](c_counterexamples_refreshed_all_c_20261001_search_only/cases/RadixSort/mutant_3/c/replay.json) | precondition/RTE failure | `{"nums": [0, 1]}` | `[0, 1]` | `null` |
| [RadixSort/30](c_counterexamples_refreshed_all_c_20261001_search_only/cases/RadixSort/mutant_30/c/replay.json) | specification violation | `{"nums": [-1]}` | `[-1]` | `[1]` |
| [RadixSort/31](c_counterexamples_refreshed_all_c_20261001_search_only/cases/RadixSort/mutant_31/c/replay.json) | specification violation | `{"nums": [-1]}` | `[-1]` | `[0]` |
| [RadixSort/32](c_counterexamples_refreshed_all_c_20261001_search_only/cases/RadixSort/mutant_32/c/replay.json) | specification violation | `{"nums": [1, 0]}` | `[0, 1]` | `[1, 0]` |
| [RadixSort/4](c_counterexamples_refreshed_all_c_20261001_search_only/cases/RadixSort/mutant_4/c/replay.json) | precondition/RTE failure | `{"nums": [0, 1]}` | `[0, 1]` | `null` |
| [RadixSort/5](c_counterexamples_refreshed_all_c_20261001_search_only/cases/RadixSort/mutant_5/c/replay.json) | precondition/RTE failure | `{"nums": [0, 1]}` | `[0, 1]` | `null` |
| [RadixSort/7](c_counterexamples_refreshed_all_c_20261001_search_only/cases/RadixSort/mutant_7/c/replay.json) | precondition/RTE failure | `{"nums": [1, 0]}` | `[0, 1]` | `null` |
| [RadixSort/8](c_counterexamples_refreshed_all_c_20261001_search_only/cases/RadixSort/mutant_8/c/replay.json) | precondition/RTE failure | `{"nums": [1, 0]}` | `[0, 1]` | `null` |
| [RadixSort/9](c_counterexamples_refreshed_all_c_20261001_search_only/cases/RadixSort/mutant_9/c/replay.json) | precondition/RTE failure | `{"nums": [2, 1]}` | `[1, 2]` | `null` |
| [SqrtRoot/1](c_counterexamples_refreshed_all_c_20261001_search_only/cases/SqrtRoot/mutant_1/c/replay.json) | specification violation | `{"num": 2125}` | `46` | `-1` |
| [SqrtRoot/13](c_counterexamples_refreshed_all_c_20261001_search_only/cases/SqrtRoot/mutant_13/c/replay.json) | specification violation | `{"num": 2125}` | `46` | `271873` |
| [SqrtRoot/14](c_counterexamples_refreshed_all_c_20261001_search_only/cases/SqrtRoot/mutant_14/c/replay.json) | specification violation | `{"num": 0}` | `0` | `46339` |
| [SqrtRoot/15](c_counterexamples_refreshed_all_c_20261001_search_only/cases/SqrtRoot/mutant_15/c/replay.json) | specification violation | `{"num": 0}` | `0` | `-3` |
| [SqrtRoot/17](c_counterexamples_refreshed_all_c_20261001_search_only/cases/SqrtRoot/mutant_17/c/replay.json) | specification violation | `{"num": 2125}` | `46` | `1817567` |
| [SqrtRoot/18](c_counterexamples_refreshed_all_c_20261001_search_only/cases/SqrtRoot/mutant_18/c/replay.json) | specification violation | `{"num": 2125}` | `46` | `-1063` |
| [SqrtRoot/2](c_counterexamples_refreshed_all_c_20261001_search_only/cases/SqrtRoot/mutant_2/c/replay.json) | specification violation | `{"num": 0}` | `0` | `-1` |
| [SqrtRoot/20](c_counterexamples_refreshed_all_c_20261001_search_only/cases/SqrtRoot/mutant_20/c/replay.json) | specification violation | `{"num": 16}` | `4` | `3` |
| [SqrtRoot/23](c_counterexamples_refreshed_all_c_20261001_search_only/cases/SqrtRoot/mutant_23/c/replay.json) | specification violation | `{"num": 16}` | `4` | `3` |
| [SqrtRoot/24](c_counterexamples_refreshed_all_c_20261001_search_only/cases/SqrtRoot/mutant_24/c/replay.json) | specification violation | `{"num": 2125}` | `46` | `32` |
| [SqrtRoot/25](c_counterexamples_refreshed_all_c_20261001_search_only/cases/SqrtRoot/mutant_25/c/replay.json) | specification violation | `{"num": 2125}` | `46` | `1062` |
| [SqrtRoot/26](c_counterexamples_refreshed_all_c_20261001_search_only/cases/SqrtRoot/mutant_26/c/replay.json) | specification violation | `{"num": 0}` | `0` | `-1` |
| [SqrtRoot/28](c_counterexamples_refreshed_all_c_20261001_search_only/cases/SqrtRoot/mutant_28/c/replay.json) | specification violation | `{"num": 2125}` | `46` | `2125` |
| [SqrtRoot/29](c_counterexamples_refreshed_all_c_20261001_search_only/cases/SqrtRoot/mutant_29/c/replay.json) | specification violation | `{"num": 2125}` | `46` | `1062` |
| [SqrtRoot/3](c_counterexamples_refreshed_all_c_20261001_search_only/cases/SqrtRoot/mutant_3/c/replay.json) | specification violation | `{"num": -3882}` | `-1` | `-3882` |
| [SqrtRoot/30](c_counterexamples_refreshed_all_c_20261001_search_only/cases/SqrtRoot/mutant_30/c/replay.json) | specification violation | `{"num": 2125}` | `46` | `2125` |
| [SqrtRoot/31](c_counterexamples_refreshed_all_c_20261001_search_only/cases/SqrtRoot/mutant_31/c/replay.json) | specification violation | `{"num": 2125}` | `46` | `2125` |
| [SqrtRoot/32](c_counterexamples_refreshed_all_c_20261001_search_only/cases/SqrtRoot/mutant_32/c/replay.json) | specification violation | `{"num": 2125}` | `46` | `2125` |
| [SqrtRoot/34](c_counterexamples_refreshed_all_c_20261001_search_only/cases/SqrtRoot/mutant_34/c/replay.json) | specification violation | `{"num": 2125}` | `46` | `-1` |
| [SqrtRoot/4](c_counterexamples_refreshed_all_c_20261001_search_only/cases/SqrtRoot/mutant_4/c/replay.json) | specification violation | `{"num": -3882}` | `-1` | `-3882` |
| [SqrtRoot/40](c_counterexamples_refreshed_all_c_20261001_search_only/cases/SqrtRoot/mutant_40/c/replay.json) | specification violation | `{"num": 2125}` | `46` | `0` |
| [SqrtRoot/5](c_counterexamples_refreshed_all_c_20261001_search_only/cases/SqrtRoot/mutant_5/c/replay.json) | specification violation | `{"num": 2125}` | `46` | `47` |
| [SqrtRoot/6](c_counterexamples_refreshed_all_c_20261001_search_only/cases/SqrtRoot/mutant_6/c/replay.json) | specification violation | `{"num": 2125}` | `46` | `2125` |
| [SqrtRoot/9](c_counterexamples_refreshed_all_c_20261001_search_only/cases/SqrtRoot/mutant_9/c/replay.json) | specification violation | `{"num": 2125}` | `46` | `20841` |
| [SquarePerimeter/1](c_counterexamples_refreshed_all_c_20261001_search_only/cases/SquarePerimeter/mutant_1/c/replay.json) | specification violation | `{"a": 2125}` | `8500` | `4` |
| [SquarePerimeter/2](c_counterexamples_refreshed_all_c_20261001_search_only/cases/SquarePerimeter/mutant_2/c/replay.json) | specification violation | `{"a": 0}` | `0` | `4` |
| [SquarePerimeter/3](c_counterexamples_refreshed_all_c_20261001_search_only/cases/SquarePerimeter/mutant_3/c/replay.json) | specification violation | `{"a": 0}` | `0` | `4` |
| [SquarePerimeter/4](c_counterexamples_refreshed_all_c_20261001_search_only/cases/SquarePerimeter/mutant_4/c/replay.json) | specification violation | `{"a": 2125}` | `8500` | `0` |
| [SumList/2](c_counterexamples_refreshed_all_c_20261001_search_only/cases/SumList/mutant_2/c/replay.json) | precondition/RTE failure | `{"arr1": [], "arr2": []}` | `[]` | `null` |
| [SumList/4](c_counterexamples_refreshed_all_c_20261001_search_only/cases/SumList/mutant_4/c/replay.json) | specification violation | `{"arr1": [0], "arr2": [-1]}` | `[-1]` | `[0]` |
| [SumList/5](c_counterexamples_refreshed_all_c_20261001_search_only/cases/SumList/mutant_5/c/replay.json) | specification violation | `{"arr1": [0], "arr2": [-1]}` | `[-1]` | `[0]` |
| [SumList/6](c_counterexamples_refreshed_all_c_20261001_search_only/cases/SumList/mutant_6/c/replay.json) | specification violation | `{"arr1": [0], "arr2": [-1]}` | `[-1]` | `[1]` |
| [SumList/7](c_counterexamples_refreshed_all_c_20261001_search_only/cases/SumList/mutant_7/c/replay.json) | specification violation | `{"arr1": [0], "arr2": [-1]}` | `[-1]` | `[0]` |
| [SumList/8](c_counterexamples_refreshed_all_c_20261001_search_only/cases/SumList/mutant_8/c/replay.json) | specification violation | `{"arr1": [0], "arr2": [-1]}` | `[-1]` | `[0]` |
| [SumNums/1](c_counterexamples_refreshed_all_c_20261001_search_only/cases/SumNums/mutant_1/c/replay.json) | specification violation | `{"m": 748, "n": 20, "x": 20, "y": -450}` | `-430` | `20` |
| [SumNums/10](c_counterexamples_refreshed_all_c_20261001_search_only/cases/SumNums/mutant_10/c/replay.json) | specification violation | `{"m": -1537, "n": -3324, "x": 1, "y": -1}` | `0` | `20` |
| [SumNums/12](c_counterexamples_refreshed_all_c_20261001_search_only/cases/SumNums/mutant_12/c/replay.json) | specification violation | `{"m": -3882, "n": 20, "x": -3882, "y": 0}` | `20` | `-3882` |
| [SumNums/13](c_counterexamples_refreshed_all_c_20261001_search_only/cases/SumNums/mutant_13/c/replay.json) | specification violation | `{"m": -1537, "n": -3324, "x": 1, "y": -1}` | `0` | `20` |
| [SumNums/14](c_counterexamples_refreshed_all_c_20261001_search_only/cases/SumNums/mutant_14/c/replay.json) | specification violation | `{"m": 748, "n": 20, "x": 20, "y": -450}` | `-430` | `20` |
| [SumNums/2](c_counterexamples_refreshed_all_c_20261001_search_only/cases/SumNums/mutant_2/c/replay.json) | specification violation | `{"m": -1537, "n": -3324, "x": 1, "y": -1}` | `0` | `-1` |
| [SumNums/3](c_counterexamples_refreshed_all_c_20261001_search_only/cases/SumNums/mutant_3/c/replay.json) | specification violation | `{"m": -1537, "n": -3324, "x": 1, "y": -1}` | `0` | `2` |
| [SumNums/4](c_counterexamples_refreshed_all_c_20261001_search_only/cases/SumNums/mutant_4/c/replay.json) | specification violation | `{"m": -1537, "n": -3324, "x": 1, "y": -1}` | `0` | `-1` |
| [SumNums/5](c_counterexamples_refreshed_all_c_20261001_search_only/cases/SumNums/mutant_5/c/replay.json) | specification violation | `{"m": -450, "n": 0, "x": 0, "y": 0}` | `20` | `0` |
| [SumNums/6](c_counterexamples_refreshed_all_c_20261001_search_only/cases/SumNums/mutant_6/c/replay.json) | specification violation | `{"m": -3882, "n": 20, "x": -3882, "y": 0}` | `20` | `-3882` |
| [SumNums/7](c_counterexamples_refreshed_all_c_20261001_search_only/cases/SumNums/mutant_7/c/replay.json) | specification violation | `{"m": 748, "n": 20, "x": 20, "y": -450}` | `-430` | `20` |
| [SumNums/8](c_counterexamples_refreshed_all_c_20261001_search_only/cases/SumNums/mutant_8/c/replay.json) | specification violation | `{"m": -450, "n": 0, "x": 0, "y": 0}` | `20` | `0` |
| [SumNums/9](c_counterexamples_refreshed_all_c_20261001_search_only/cases/SumNums/mutant_9/c/replay.json) | specification violation | `{"m": -3882, "n": 20, "x": -3882, "y": 0}` | `20` | `-3882` |
| [SumOfPrimes/1](c_counterexamples_refreshed_all_c_20261001_search_only/cases/SumOfPrimes/mutant_1/c/replay.json) | precondition/RTE failure | `{"n": 2}` | `2` | `null` |
| [SumOfPrimes/10](c_counterexamples_refreshed_all_c_20261001_search_only/cases/SumOfPrimes/mutant_10/c/replay.json) | specification violation | `{"n": 2}` | `2` | `0` |
| [SumOfPrimes/14](c_counterexamples_refreshed_all_c_20261001_search_only/cases/SumOfPrimes/mutant_14/c/replay.json) | specification violation | `{"n": 3}` | `5` | `2` |
| [SumOfPrimes/15](c_counterexamples_refreshed_all_c_20261001_search_only/cases/SumOfPrimes/mutant_15/c/replay.json) | specification violation | `{"n": 4}` | `5` | `9` |
| [SumOfPrimes/16](c_counterexamples_refreshed_all_c_20261001_search_only/cases/SumOfPrimes/mutant_16/c/replay.json) | specification violation | `{"n": 7}` | `17` | `27` |
| [SumOfPrimes/17](c_counterexamples_refreshed_all_c_20261001_search_only/cases/SumOfPrimes/mutant_17/c/replay.json) | precondition/RTE failure | `{"n": 2}` | `2` | `null` |
| [SumOfPrimes/2](c_counterexamples_refreshed_all_c_20261001_search_only/cases/SumOfPrimes/mutant_2/c/replay.json) | precondition/RTE failure | `{"n": -1}` | `0` | `null` |
| [SumOfPrimes/3](c_counterexamples_refreshed_all_c_20261001_search_only/cases/SumOfPrimes/mutant_3/c/replay.json) | precondition/RTE failure | `{"n": -1}` | `0` | `null` |
| [SumOfPrimes/4](c_counterexamples_refreshed_all_c_20261001_search_only/cases/SumOfPrimes/mutant_4/c/replay.json) | precondition/RTE failure | `{"n": -1}` | `0` | `null` |
| [SumOfPrimes/5](c_counterexamples_refreshed_all_c_20261001_search_only/cases/SumOfPrimes/mutant_5/c/replay.json) | specification violation | `{"n": 2}` | `2` | `0` |
| [SumOfPrimes/6](c_counterexamples_refreshed_all_c_20261001_search_only/cases/SumOfPrimes/mutant_6/c/replay.json) | specification violation | `{"n": 2}` | `2` | `0` |
| [SumOfPrimes/7](c_counterexamples_refreshed_all_c_20261001_search_only/cases/SumOfPrimes/mutant_7/c/replay.json) | specification violation | `{"n": 3}` | `5` | `0` |
| [SumOfSubarrayProd/7](c_counterexamples_refreshed_all_c_20261001_search_only/cases/SumOfSubarrayProd/mutant_7/c/replay.json) | specification violation | `{"arr": [1129, 0, 0, 0], "n": 1}` | `1129` | `1` |
| [SumOfSubarrayProd/8](c_counterexamples_refreshed_all_c_20261001_search_only/cases/SumOfSubarrayProd/mutant_8/c/replay.json) | specification violation | `{"arr": [1129, 0, 0, 0], "n": 1}` | `1129` | `0` |
| [SumRangeList/1](c_counterexamples_refreshed_all_c_20261001_search_only/cases/SumRangeList/mutant_1/c/replay.json) | specification violation | `{"m": 0, "n": 0, "nums": [-1]}` | `-1` | `0` |
| [SumRangeList/2](c_counterexamples_refreshed_all_c_20261001_search_only/cases/SumRangeList/mutant_2/c/replay.json) | specification violation | `{"m": 0, "n": 1, "nums": [1, 0]}` | `1` | `0` |
| [SumRangeList/4](c_counterexamples_refreshed_all_c_20261001_search_only/cases/SumRangeList/mutant_4/c/replay.json) | specification violation | `{"m": 0, "n": 0, "nums": [-1]}` | `-1` | `0` |
| [TestThreeEqual/1](c_counterexamples_refreshed_all_c_20261001_search_only/cases/TestThreeEqual/mutant_1/c/replay.json) | specification violation | `{"x": -850, "y": 0, "z": 0}` | `2` | `3` |
| [TestThreeEqual/10](c_counterexamples_refreshed_all_c_20261001_search_only/cases/TestThreeEqual/mutant_10/c/replay.json) | specification violation | `{"x": -850, "y": 0, "z": 0}` | `2` | `3` |
| [TestThreeEqual/11](c_counterexamples_refreshed_all_c_20261001_search_only/cases/TestThreeEqual/mutant_11/c/replay.json) | specification violation | `{"x": 0, "y": 665, "z": 2}` | `0` | `2` |
| [TestThreeEqual/13](c_counterexamples_refreshed_all_c_20261001_search_only/cases/TestThreeEqual/mutant_13/c/replay.json) | specification violation | `{"x": 3, "y": 3, "z": 0}` | `2` | `0` |
| [TestThreeEqual/15](c_counterexamples_refreshed_all_c_20261001_search_only/cases/TestThreeEqual/mutant_15/c/replay.json) | specification violation | `{"x": 0, "y": 665, "z": 2}` | `0` | `2` |
| [TestThreeEqual/16](c_counterexamples_refreshed_all_c_20261001_search_only/cases/TestThreeEqual/mutant_16/c/replay.json) | specification violation | `{"x": -850, "y": 0, "z": 0}` | `2` | `0` |
| [TestThreeEqual/18](c_counterexamples_refreshed_all_c_20261001_search_only/cases/TestThreeEqual/mutant_18/c/replay.json) | specification violation | `{"x": -850, "y": 0, "z": 0}` | `2` | `0` |
| [TestThreeEqual/19](c_counterexamples_refreshed_all_c_20261001_search_only/cases/TestThreeEqual/mutant_19/c/replay.json) | specification violation | `{"x": 3, "y": 3, "z": 0}` | `2` | `0` |
| [TestThreeEqual/20](c_counterexamples_refreshed_all_c_20261001_search_only/cases/TestThreeEqual/mutant_20/c/replay.json) | specification violation | `{"x": 0, "y": 665, "z": 2}` | `0` | `2` |
| [TestThreeEqual/21](c_counterexamples_refreshed_all_c_20261001_search_only/cases/TestThreeEqual/mutant_21/c/replay.json) | specification violation | `{"x": 0, "y": 665, "z": 2}` | `0` | `2` |
| [TestThreeEqual/22](c_counterexamples_refreshed_all_c_20261001_search_only/cases/TestThreeEqual/mutant_22/c/replay.json) | specification violation | `{"x": -1413, "y": 2, "z": -2487}` | `0` | `2` |
| [TestThreeEqual/23](c_counterexamples_refreshed_all_c_20261001_search_only/cases/TestThreeEqual/mutant_23/c/replay.json) | specification violation | `{"x": 0, "y": -419, "z": 0}` | `2` | `0` |
| [TestThreeEqual/25](c_counterexamples_refreshed_all_c_20261001_search_only/cases/TestThreeEqual/mutant_25/c/replay.json) | specification violation | `{"x": 0, "y": -419, "z": 0}` | `2` | `0` |
| [TestThreeEqual/26](c_counterexamples_refreshed_all_c_20261001_search_only/cases/TestThreeEqual/mutant_26/c/replay.json) | specification violation | `{"x": 3, "y": 3, "z": 0}` | `2` | `0` |
| [TestThreeEqual/27](c_counterexamples_refreshed_all_c_20261001_search_only/cases/TestThreeEqual/mutant_27/c/replay.json) | specification violation | `{"x": 0, "y": 665, "z": 2}` | `0` | `2` |
| [TestThreeEqual/3](c_counterexamples_refreshed_all_c_20261001_search_only/cases/TestThreeEqual/mutant_3/c/replay.json) | specification violation | `{"x": 0, "y": 0, "z": 0}` | `3` | `2` |
| [TestThreeEqual/4](c_counterexamples_refreshed_all_c_20261001_search_only/cases/TestThreeEqual/mutant_4/c/replay.json) | specification violation | `{"x": -1413, "y": -1413, "z": 3}` | `2` | `3` |
| [TestThreeEqual/5](c_counterexamples_refreshed_all_c_20261001_search_only/cases/TestThreeEqual/mutant_5/c/replay.json) | specification violation | `{"x": 3, "y": 3, "z": 0}` | `2` | `3` |
| [TestThreeEqual/6](c_counterexamples_refreshed_all_c_20261001_search_only/cases/TestThreeEqual/mutant_6/c/replay.json) | specification violation | `{"x": 0, "y": 0, "z": 0}` | `3` | `2` |
| [TestThreeEqual/7](c_counterexamples_refreshed_all_c_20261001_search_only/cases/TestThreeEqual/mutant_7/c/replay.json) | specification violation | `{"x": 0, "y": 665, "z": 2}` | `0` | `3` |
| [TestThreeEqual/8](c_counterexamples_refreshed_all_c_20261001_search_only/cases/TestThreeEqual/mutant_8/c/replay.json) | specification violation | `{"x": 0, "y": 0, "z": 0}` | `3` | `2` |
| [TestThreeEqual/9](c_counterexamples_refreshed_all_c_20261001_search_only/cases/TestThreeEqual/mutant_9/c/replay.json) | specification violation | `{"x": 3, "y": 3, "z": 0}` | `2` | `3` |
| [TriangleArea/1](c_counterexamples_refreshed_all_c_20261001_search_only/cases/TriangleArea/mutant_1/c/replay.json) | specification violation | `{"r": 2125}` | `4515625` | `-1` |
| [TriangleArea/10](c_counterexamples_refreshed_all_c_20261001_search_only/cases/TriangleArea/mutant_10/c/replay.json) | specification violation | `{"r": 2125}` | `4515625` | `4515626` |
| [TriangleArea/11](c_counterexamples_refreshed_all_c_20261001_search_only/cases/TriangleArea/mutant_11/c/replay.json) | specification violation | `{"r": 2125}` | `4515625` | `-4515624` |
| [TriangleArea/12](c_counterexamples_refreshed_all_c_20261001_search_only/cases/TriangleArea/mutant_12/c/replay.json) | specification violation | `{"r": 2125}` | `4515625` | `0` |
| [TriangleArea/13](c_counterexamples_refreshed_all_c_20261001_search_only/cases/TriangleArea/mutant_13/c/replay.json) | specification violation | `{"r": 2125}` | `4515625` | `0` |
| [TriangleArea/14](c_counterexamples_refreshed_all_c_20261001_search_only/cases/TriangleArea/mutant_14/c/replay.json) | specification violation | `{"r": 2125}` | `4515625` | `4250` |
| [TriangleArea/15](c_counterexamples_refreshed_all_c_20261001_search_only/cases/TriangleArea/mutant_15/c/replay.json) | specification violation | `{"r": 2125}` | `4515625` | `0` |
| [TriangleArea/16](c_counterexamples_refreshed_all_c_20261001_search_only/cases/TriangleArea/mutant_16/c/replay.json) | specification violation | `{"r": 2125}` | `4515625` | `1` |
| [TriangleArea/17](c_counterexamples_refreshed_all_c_20261001_search_only/cases/TriangleArea/mutant_17/c/replay.json) | specification violation | `{"r": 2125}` | `4515625` | `0` |
| [TriangleArea/2](c_counterexamples_refreshed_all_c_20261001_search_only/cases/TriangleArea/mutant_2/c/replay.json) | specification violation | `{"r": 0}` | `0` | `-1` |
| [TriangleArea/3](c_counterexamples_refreshed_all_c_20261001_search_only/cases/TriangleArea/mutant_3/c/replay.json) | specification violation | `{"r": -3882}` | `-1` | `15069924` |
| [TriangleArea/4](c_counterexamples_refreshed_all_c_20261001_search_only/cases/TriangleArea/mutant_4/c/replay.json) | specification violation | `{"r": -3882}` | `-1` | `0` |
| [TriangleArea/6](c_counterexamples_refreshed_all_c_20261001_search_only/cases/TriangleArea/mutant_6/c/replay.json) | specification violation | `{"r": 2125}` | `4515625` | `0` |
| [TriangleArea/9](c_counterexamples_refreshed_all_c_20261001_search_only/cases/TriangleArea/mutant_9/c/replay.json) | specification violation | `{"r": 2125}` | `4515625` | `2125` |
| [TupleToInt/1](c_counterexamples_refreshed_all_c_20261001_search_only/cases/TupleToInt/mutant_1/c/replay.json) | specification violation | `{"nums": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | `1856000000` | `6` |
| [TupleToInt/2](c_counterexamples_refreshed_all_c_20261001_search_only/cases/TupleToInt/mutant_2/c/replay.json) | specification violation | `{"nums": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | `1856000000` | `1946` |
| [TupleToInt/3](c_counterexamples_refreshed_all_c_20261001_search_only/cases/TupleToInt/mutant_3/c/replay.json) | specification violation | `{"nums": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | `1856000000` | `1766` |
| [TupleToInt/4](c_counterexamples_refreshed_all_c_20261001_search_only/cases/TupleToInt/mutant_4/c/replay.json) | specification violation | `{"nums": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | `1856000000` | `0` |
| [TupleToInt/5](c_counterexamples_refreshed_all_c_20261001_search_only/cases/TupleToInt/mutant_5/c/replay.json) | specification violation | `{"nums": [-1]}` | `-1` | `0` |
| [TupleToInt/6](c_counterexamples_refreshed_all_c_20261001_search_only/cases/TupleToInt/mutant_6/c/replay.json) | specification violation | `{"nums": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | `1856000000` | `0` |
| [TupleToInt/7](c_counterexamples_refreshed_all_c_20261001_search_only/cases/TupleToInt/mutant_7/c/replay.json) | specification violation | `{"nums": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | `1856000000` | `-1856000000` |
| [TupleToInt/8](c_counterexamples_refreshed_all_c_20261001_search_only/cases/TupleToInt/mutant_8/c/replay.json) | specification violation | `{"nums": [-1]}` | `-1` | `0` |
| [TupleToInt/9](c_counterexamples_refreshed_all_c_20261001_search_only/cases/TupleToInt/mutant_9/c/replay.json) | specification violation | `{"nums": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | `1856000000` | `0` |
| [VolumeCube/1](c_counterexamples_refreshed_all_c_20261001_search_only/cases/VolumeCube/mutant_1/c/replay.json) | specification violation | `{"l": -3882}` | `1628097176` | `0` |
| [VolumeCube/2](c_counterexamples_refreshed_all_c_20261001_search_only/cases/VolumeCube/mutant_2/c/replay.json) | specification violation | `{"l": -3882}` | `1628097176` | `15066042` |
| [VolumeCube/3](c_counterexamples_refreshed_all_c_20261001_search_only/cases/VolumeCube/mutant_3/c/replay.json) | specification violation | `{"l": -3882}` | `1628097176` | `-15073806` |
| [VolumeCube/4](c_counterexamples_refreshed_all_c_20261001_search_only/cases/VolumeCube/mutant_4/c/replay.json) | specification violation | `{"l": -3882}` | `1628097176` | `-3882` |
| [VolumeCube/5](c_counterexamples_refreshed_all_c_20261001_search_only/cases/VolumeCube/mutant_5/c/replay.json) | specification violation | `{"l": -3882}` | `1628097176` | `0` |
| [VolumeCube/6](c_counterexamples_refreshed_all_c_20261001_search_only/cases/VolumeCube/mutant_6/c/replay.json) | specification violation | `{"l": -3882}` | `1628097176` | `15066042` |
| [VolumeCube/7](c_counterexamples_refreshed_all_c_20261001_search_only/cases/VolumeCube/mutant_7/c/replay.json) | specification violation | `{"l": -3882}` | `1628097176` | `15073806` |
| [VolumeCube/8](c_counterexamples_refreshed_all_c_20261001_search_only/cases/VolumeCube/mutant_8/c/replay.json) | specification violation | `{"l": -3882}` | `1628097176` | `-3882` |

See [summary.json](summary.json) for full-precision timing distributions and per-case provenance, and [integration_audit.json](integration_audit.json) for source checks. Solver files, native binaries, tests and configuration files are excluded from the archive.

## Earlier full C search

The [973-case earlier search](c_counterexamples_all_c_20261001_workers4/README.md) is retained as historical evidence. The final 1027-case search supersedes it; its counts are not added to the final totals. See [the archive index](../c_run_archive.json).
