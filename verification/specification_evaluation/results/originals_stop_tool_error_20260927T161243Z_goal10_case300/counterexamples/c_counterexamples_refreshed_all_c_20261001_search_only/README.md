# C counterexample search across the full experiment

Completed **1027/1027** cases. Source: `originals_stop_tool_error_20260927T161243Z_goal10_case300`.

This is a supplemental counterexample search. Existing Frama-C outcomes are reused; no Frama-C proof run is invoked. The original experiment is unchanged. Candidate inputs are checked against the hash-pinned frozen requires clauses, then executed on the exact recorded C source with UBSan. Any reported failure is independently reproduced at O1 with ASan and UBSan. Correct-original observations are retained for each mutant witness; failures also present in the original are counted separately.

The search checks frozen return values, array contents, input frames, return identity and selected allocation properties. It does not check every ACSL clause or reconstruct every WP loop invariant. Passing finite trials is not proof. Timeouts, stricter compiler failures and missing native output are unresolved. Cases whose annotation transfer failed use their hash-pinned raw C source for replay and retain the original WP tool-failure classification. Replays construct valid JArray inputs and execute the fixed runtime; this can expose program errors while the JArray proof encoding remains unresolved.

## Results

| Population | WP plus independently validated replay |
| --- | --- |
| original | {"not run": 4, "proved": 6, "unknown/timeout": 40} |
| mutant | {"not run": 4, "precondition/RTE failure": 190, "specification violation": 710, "unknown/timeout": 73} |

Mutant failures with a passing original on the same witness: **900**. Original also fails or cannot be checked: **0**.

## Program coverage

| Program | Original replay | Mutant replay |
| --- | --- | --- |
| CombSort | {"no_validated_violation": 1} | {"no_validated_violation": 6, "validated_safety_failure": 6, "validated_violation": 18} |
| CountIntgralPoints | {"no_validated_violation": 1} | {"validated_violation": 20} |
| CountList | {"no_validated_violation": 1} | {"validated_violation": 3} |
| CountOddSquares | {"no_validated_violation": 1} | {"validated_violation": 6} |
| CountUnsetBits | {"no_validated_violation": 1} | {"no_validated_violation": 2, "validated_violation": 9} |
| CountWays | {"no_validated_violation": 1} | {"no_validated_violation": 3, "validated_safety_failure": 14, "validated_violation": 25} |
| CountingSort | {"no_validated_violation": 1} | {"validated_safety_failure": 20, "validated_violation": 10} |
| DealnnoyNum | {"no_validated_violation": 1} | {"no_validated_violation": 2, "validated_safety_failure": 13, "validated_violation": 17} |
| DiameterCircle | {"no_validated_violation": 1} | {"validated_violation": 4} |
| DiffEvenOdd | {"no_validated_violation": 1} | {"validated_violation": 31} |
| DogAge | {"no_validated_violation": 1} | {"validated_violation": 27} |
| Fibonacci | {"no_validated_violation": 1} | {"no_validated_violation": 4, "validated_safety_failure": 7, "validated_violation": 5} |
| FindPeak | {"no_validated_violation": 1} | {"no_validated_violation": 8, "validated_safety_failure": 3, "validated_violation": 19} |
| FindPoints | {"no_validated_violation": 1} | {"validated_violation": 22} |
| FindRectNum | {"no_validated_violation": 1} | {"validated_violation": 8} |
| HexagonalNum | {"no_validated_violation": 1} | {"validated_violation": 12} |
| LeftInsertion | {"no_validated_violation": 1} | {"no_validated_violation": 14, "validated_safety_failure": 3, "validated_violation": 10} |
| MaxDifference | {"no_validated_violation": 1} | {"validated_safety_failure": 1, "validated_violation": 6} |
| MaxOfTwo | {"no_validated_violation": 1} | {"validated_violation": 2} |
| MaxProduct | {"no_validated_violation": 1} | {"validated_safety_failure": 3, "validated_violation": 17} |
| MaxSubArraySum | {"no_validated_violation": 1} | {"validated_safety_failure": 1, "validated_violation": 22} |
| MaxSumOfThreeConsecutive | {"no_validated_violation": 1} | {"validated_safety_failure": 8, "validated_violation": 24} |
| MaxSumSubseq | {"no_validated_violation": 1} | {"validated_safety_failure": 12, "validated_violation": 15} |
| MaxVolume | {"no_validated_violation": 1} | {"no_validated_violation": 5, "validated_violation": 21} |
| MaximumSegments | {"no_validated_violation": 1} | {"no_validated_violation": 2, "validated_safety_failure": 19, "validated_violation": 37} |
| MinCoins | {"no_validated_violation": 1} | {"no_validated_violation": 1, "validated_safety_failure": 1, "validated_violation": 13} |
| MinCost | {"no_validated_violation": 1} | {"validated_safety_failure": 14, "validated_violation": 27} |
| MinJumps | {"no_validated_violation": 1} | {"no_validated_violation": 2, "validated_safety_failure": 4, "validated_violation": 11} |
| MoveFirst | {"no_validated_violation": 1} | {"validated_safety_failure": 12} |
| MultiplyElements | {"no_validated_violation": 1} | {"validated_safety_failure": 8, "validated_violation": 11} |
| NewmanPrime | {"no_validated_violation": 1} | {"no_validated_violation": 2, "validated_safety_failure": 10, "validated_violation": 7} |
| NextPowerOf2 | {"no_validated_violation": 1} | {"no_validated_violation": 4, "validated_violation": 2} |
| NoOfCubes | {"no_validated_violation": 1} | {"validated_violation": 33} |
| OddBitSetNumber | {"no_validated_violation": 1} | {"validated_violation": 17} |
| OddLengthSum | {"no_validated_violation": 1} | {"no_validated_violation": 2, "validated_safety_failure": 1, "validated_violation": 23} |
| PairWise | {"no_validated_violation": 1} | {"validated_safety_failure": 8, "validated_violation": 5} |
| ParabolaVertex | {"no_validated_violation": 1} | {"validated_violation": 35} |
| ParallelogramPerimeter | {"no_validated_violation": 1} | {"validated_violation": 16} |
| RadixSort | {"no_validated_violation": 1} | {"validated_safety_failure": 16, "validated_violation": 9} |
| SqrtRoot | {"no_validated_violation": 1} | {"no_validated_violation": 15, "validated_violation": 24} |
| SquarePerimeter | {"no_validated_violation": 1} | {"validated_violation": 4} |
| SumList | {"no_validated_violation": 1} | {"validated_safety_failure": 1, "validated_violation": 5} |
| SumNums | {"no_validated_violation": 1} | {"validated_violation": 13} |
| SumOfPrimes | {"no_validated_violation": 1} | {"no_validated_violation": 4, "validated_safety_failure": 5, "validated_violation": 7} |
| SumOfSubarrayProd | {"no_validated_violation": 1} | {"no_validated_violation": 1, "validated_violation": 2} |
| SumRangeList | {"no_validated_violation": 1} | {"validated_violation": 3} |
| TestThreeEqual | {"no_validated_violation": 1} | {"validated_violation": 22} |
| TriangleArea | {"no_validated_violation": 1} | {"validated_violation": 14} |
| TupleToInt | {"no_validated_violation": 1} | {"validated_violation": 9} |
| VolumeCube | {"no_validated_violation": 1} | {"validated_violation": 8} |

## Counterexamples

| Case | Kind | Input | Failed clauses | Original passes |
| --- | --- | --- | --- | --- |
| [CombSort/11](cases/CombSort/mutant_11/c/replay.json) | validated_violation | `{"nums": [1, 0]}` | frozen_return_value_or_array_contents | True |
| [CombSort/16](cases/CombSort/mutant_16/c/replay.json) | validated_safety_failure | `{"nums": [1202, 0, 0]}` | runtime safety | True |
| [CombSort/15](cases/CombSort/mutant_15/c/replay.json) | validated_safety_failure | `{"nums": [1202, 0, 0]}` | runtime safety | True |
| [CombSort/14](cases/CombSort/mutant_14/c/replay.json) | validated_safety_failure | `{"nums": [1202, 0, 0]}` | runtime safety | True |
| [CombSort/22](cases/CombSort/mutant_22/c/replay.json) | validated_violation | `{"nums": [1202, 0, 0]}` | frozen_return_value_or_array_contents | True |
| [CombSort/21](cases/CombSort/mutant_21/c/replay.json) | validated_violation | `{"nums": [1202, 0, 0]}` | frozen_return_value_or_array_contents | True |
| [CombSort/17](cases/CombSort/mutant_17/c/replay.json) | validated_safety_failure | `{"nums": [1202, 0, 0]}` | runtime safety | True |
| [CombSort/24](cases/CombSort/mutant_24/c/replay.json) | validated_violation | `{"nums": [1202, 0, 0]}` | frozen_return_value_or_array_contents | True |
| [CombSort/25](cases/CombSort/mutant_25/c/replay.json) | validated_violation | `{"nums": [1202, 0, 0]}` | frozen_return_value_or_array_contents | True |
| [CombSort/27](cases/CombSort/mutant_27/c/replay.json) | validated_violation | `{"nums": [1202, 0, 0]}` | frozen_return_value_or_array_contents | True |
| [CombSort/28](cases/CombSort/mutant_28/c/replay.json) | validated_violation | `{"nums": [1202, 0, 0]}` | frozen_return_value_or_array_contents | True |
| [CombSort/19](cases/CombSort/mutant_19/c/replay.json) | validated_safety_failure | `{"nums": [1202, 0, 0]}` | runtime safety | True |
| [CombSort/29](cases/CombSort/mutant_29/c/replay.json) | validated_violation | `{"nums": [1202, 0, 0]}` | frozen_return_value_or_array_contents | True |
| [CombSort/23](cases/CombSort/mutant_23/c/replay.json) | validated_safety_failure | `{"nums": [1202, 0, 0]}` | runtime safety | True |
| [CombSort/30](cases/CombSort/mutant_30/c/replay.json) | validated_violation | `{"nums": [-1, 1, 0]}` | frozen_return_value_or_array_contents | True |
| [CombSort/31](cases/CombSort/mutant_31/c/replay.json) | validated_violation | `{"nums": [1202, 0, 0]}` | frozen_return_value_or_array_contents | True |
| [CombSort/32](cases/CombSort/mutant_32/c/replay.json) | validated_violation | `{"nums": [1202, 0, 0]}` | frozen_return_value_or_array_contents | True |
| [CombSort/33](cases/CombSort/mutant_33/c/replay.json) | validated_violation | `{"nums": [1202, 0, 0]}` | frozen_return_value_or_array_contents | True |
| [CombSort/34](cases/CombSort/mutant_34/c/replay.json) | validated_violation | `{"nums": [1202, 0, 0]}` | frozen_return_value_or_array_contents | True |
| [CombSort/36](cases/CombSort/mutant_36/c/replay.json) | validated_violation | `{"nums": [1202, 0, 0]}` | frozen_return_value_or_array_contents | True |
| [CombSort/35](cases/CombSort/mutant_35/c/replay.json) | validated_violation | `{"nums": [-1, 0, -1]}` | frozen_return_value_or_array_contents | True |
| [CombSort/37](cases/CombSort/mutant_37/c/replay.json) | validated_violation | `{"nums": [1202, 0, 0]}` | frozen_return_value_or_array_contents | True |
| [CombSort/4](cases/CombSort/mutant_4/c/replay.json) | validated_violation | `{"nums": [1202, 0, 0]}` | frozen_return_value_or_array_contents | True |
| [CombSort/8](cases/CombSort/mutant_8/c/replay.json) | validated_violation | `{"nums": [1202, 0, 0]}` | frozen_return_value_or_array_contents | True |
| [CountIntgralPoints/1](cases/CountIntgralPoints/mutant_1/c/replay.json) | validated_violation | `{"x1": 0, "x2": 1471, "y1": 2733, "y2": -1112}` | frozen_return_value_or_array_contents | True |
| [CountIntgralPoints/10](cases/CountIntgralPoints/mutant_10/c/replay.json) | validated_violation | `{"x1": 0, "x2": 1, "y1": 1, "y2": -1264}` | frozen_return_value_or_array_contents | True |
| [CountIntgralPoints/11](cases/CountIntgralPoints/mutant_11/c/replay.json) | validated_violation | `{"x1": 890, "x2": 890, "y1": 1845, "y2": 1}` | frozen_return_value_or_array_contents | True |
| [CountIntgralPoints/12](cases/CountIntgralPoints/mutant_12/c/replay.json) | validated_violation | `{"x1": 890, "x2": 890, "y1": 1845, "y2": 1}` | frozen_return_value_or_array_contents | True |
| [CountIntgralPoints/13](cases/CountIntgralPoints/mutant_13/c/replay.json) | validated_violation | `{"x1": 0, "x2": 1471, "y1": 2733, "y2": -1112}` | frozen_return_value_or_array_contents | True |
| [CountIntgralPoints/14](cases/CountIntgralPoints/mutant_14/c/replay.json) | validated_violation | `{"x1": 0, "x2": 1, "y1": 1, "y2": -1264}` | frozen_return_value_or_array_contents | True |
| [CountIntgralPoints/15](cases/CountIntgralPoints/mutant_15/c/replay.json) | validated_violation | `{"x1": 0, "x2": 1, "y1": 1, "y2": -1264}` | frozen_return_value_or_array_contents | True |
| [CountIntgralPoints/16](cases/CountIntgralPoints/mutant_16/c/replay.json) | validated_violation | `{"x1": 0, "x2": 1, "y1": 1, "y2": -1264}` | frozen_return_value_or_array_contents | True |
| [CountIntgralPoints/17](cases/CountIntgralPoints/mutant_17/c/replay.json) | validated_violation | `{"x1": 0, "x2": 1471, "y1": 2733, "y2": -1112}` | frozen_return_value_or_array_contents | True |
| [CountIntgralPoints/18](cases/CountIntgralPoints/mutant_18/c/replay.json) | validated_violation | `{"x1": 0, "x2": 1, "y1": 1, "y2": -1264}` | frozen_return_value_or_array_contents | True |
| [CountIntgralPoints/19](cases/CountIntgralPoints/mutant_19/c/replay.json) | validated_violation | `{"x1": 0, "x2": 1, "y1": 1, "y2": -1264}` | frozen_return_value_or_array_contents | True |
| [CountIntgralPoints/2](cases/CountIntgralPoints/mutant_2/c/replay.json) | validated_violation | `{"x1": 0, "x2": 1471, "y1": 2733, "y2": -1112}` | frozen_return_value_or_array_contents | True |
| [CountIntgralPoints/20](cases/CountIntgralPoints/mutant_20/c/replay.json) | validated_violation | `{"x1": 0, "x2": 1471, "y1": 2733, "y2": -1112}` | frozen_return_value_or_array_contents | True |
| [CountIntgralPoints/3](cases/CountIntgralPoints/mutant_3/c/replay.json) | validated_violation | `{"x1": 0, "x2": 1471, "y1": 2733, "y2": -1112}` | frozen_return_value_or_array_contents | True |
| [CountIntgralPoints/4](cases/CountIntgralPoints/mutant_4/c/replay.json) | validated_violation | `{"x1": 0, "x2": 1471, "y1": 2733, "y2": -1112}` | frozen_return_value_or_array_contents | True |
| [CountIntgralPoints/5](cases/CountIntgralPoints/mutant_5/c/replay.json) | validated_violation | `{"x1": 0, "x2": 1471, "y1": 2733, "y2": -1112}` | frozen_return_value_or_array_contents | True |
| [CountIntgralPoints/6](cases/CountIntgralPoints/mutant_6/c/replay.json) | validated_violation | `{"x1": 0, "x2": 1471, "y1": 2733, "y2": -1112}` | frozen_return_value_or_array_contents | True |
| [CountIntgralPoints/7](cases/CountIntgralPoints/mutant_7/c/replay.json) | validated_violation | `{"x1": 0, "x2": 1471, "y1": 2733, "y2": -1112}` | frozen_return_value_or_array_contents | True |
| [CountIntgralPoints/8](cases/CountIntgralPoints/mutant_8/c/replay.json) | validated_violation | `{"x1": 0, "x2": 1471, "y1": 2733, "y2": -1112}` | frozen_return_value_or_array_contents | True |
| [CountIntgralPoints/9](cases/CountIntgralPoints/mutant_9/c/replay.json) | validated_violation | `{"x1": -2, "x2": -1, "y1": -2, "y2": -2}` | frozen_return_value_or_array_contents | True |
| [CountList/1](cases/CountList/mutant_1/c/replay.json) | validated_violation | `{"inputArray": [[]]}` | frozen_return_value_or_array_contents | True |
| [CountList/2](cases/CountList/mutant_2/c/replay.json) | validated_violation | `{"inputArray": [[0, 0, 0, 0, 0, 0, 0, 0, 0]]}` | frozen_return_value_or_array_contents | True |
| [CountList/3](cases/CountList/mutant_3/c/replay.json) | validated_violation | `{"inputArray": [[0, 0, 0, 0, 0, 0, 0, 0, 0]]}` | frozen_return_value_or_array_contents | True |
| [CountOddSquares/1](cases/CountOddSquares/mutant_1/c/replay.json) | validated_violation | `{"m": -2, "n": -2}` | errno_EDOM | True |
| [CountOddSquares/12](cases/CountOddSquares/mutant_12/c/replay.json) | validated_violation | `{"m": 0, "n": -2}` | frozen_return_value_or_array_contents | True |
| [CountOddSquares/13](cases/CountOddSquares/mutant_13/c/replay.json) | validated_violation | `{"m": 0, "n": -2}` | frozen_return_value_or_array_contents | True |
| [CountOddSquares/17](cases/CountOddSquares/mutant_17/c/replay.json) | validated_violation | `{"m": 0, "n": -2}` | frozen_return_value_or_array_contents | True |
| [CountOddSquares/2](cases/CountOddSquares/mutant_2/c/replay.json) | validated_violation | `{"m": -1, "n": -2}` | errno_EDOM | True |
| [CountOddSquares/22](cases/CountOddSquares/mutant_22/c/replay.json) | validated_violation | `{"m": 0, "n": -2}` | frozen_return_value_or_array_contents | True |
| [CountUnsetBits/1](cases/CountUnsetBits/mutant_1/c/replay.json) | validated_violation | `{"n": 55}` | frozen_return_value_or_array_contents | True |
| [CountUnsetBits/10](cases/CountUnsetBits/mutant_10/c/replay.json) | validated_violation | `{"n": 55}` | frozen_return_value_or_array_contents | True |
| [CountUnsetBits/11](cases/CountUnsetBits/mutant_11/c/replay.json) | validated_violation | `{"n": 55}` | frozen_return_value_or_array_contents | True |
| [CountUnsetBits/12](cases/CountUnsetBits/mutant_12/c/replay.json) | validated_violation | `{"n": 55}` | frozen_return_value_or_array_contents | True |
| [CountUnsetBits/13](cases/CountUnsetBits/mutant_13/c/replay.json) | validated_violation | `{"n": 55}` | frozen_return_value_or_array_contents | True |
| [CountUnsetBits/2](cases/CountUnsetBits/mutant_2/c/replay.json) | validated_violation | `{"n": 55}` | frozen_return_value_or_array_contents | True |
| [CountUnsetBits/4](cases/CountUnsetBits/mutant_4/c/replay.json) | validated_violation | `{"n": 55}` | frozen_return_value_or_array_contents | True |
| [CountUnsetBits/7](cases/CountUnsetBits/mutant_7/c/replay.json) | validated_violation | `{"n": 55}` | frozen_return_value_or_array_contents | True |
| [CountUnsetBits/8](cases/CountUnsetBits/mutant_8/c/replay.json) | validated_violation | `{"n": 55}` | frozen_return_value_or_array_contents | True |
| [CountWays/1](cases/CountWays/mutant_1/c/replay.json) | validated_safety_failure | `{"n": 1}` | runtime safety | True |
| [CountWays/12](cases/CountWays/mutant_12/c/replay.json) | validated_violation | `{"n": 2}` | frozen_return_value_or_array_contents | True |
| [CountWays/13](cases/CountWays/mutant_13/c/replay.json) | validated_violation | `{"n": 2}` | frozen_return_value_or_array_contents | True |
| [CountWays/14](cases/CountWays/mutant_14/c/replay.json) | validated_violation | `{"n": 4}` | frozen_return_value_or_array_contents | True |
| [CountWays/16](cases/CountWays/mutant_16/c/replay.json) | validated_violation | `{"n": 4}` | frozen_return_value_or_array_contents | True |
| [CountWays/17](cases/CountWays/mutant_17/c/replay.json) | validated_safety_failure | `{"n": 2}` | runtime safety | True |
| [CountWays/18](cases/CountWays/mutant_18/c/replay.json) | validated_safety_failure | `{"n": 2}` | runtime safety | True |
| [CountWays/19](cases/CountWays/mutant_19/c/replay.json) | validated_violation | `{"n": 2}` | frozen_return_value_or_array_contents | True |
| [CountWays/2](cases/CountWays/mutant_2/c/replay.json) | validated_safety_failure | `{"n": 1}` | runtime safety | True |
| [CountWays/20](cases/CountWays/mutant_20/c/replay.json) | validated_violation | `{"n": 2}` | frozen_return_value_or_array_contents | True |
| [CountWays/21](cases/CountWays/mutant_21/c/replay.json) | validated_violation | `{"n": 2}` | frozen_return_value_or_array_contents | True |
| [CountWays/22](cases/CountWays/mutant_22/c/replay.json) | validated_safety_failure | `{"n": 2}` | runtime safety | True |
| [CountWays/23](cases/CountWays/mutant_23/c/replay.json) | validated_violation | `{"n": 2}` | frozen_return_value_or_array_contents | True |
| [CountWays/24](cases/CountWays/mutant_24/c/replay.json) | validated_violation | `{"n": 2}` | frozen_return_value_or_array_contents | True |
| [CountWays/25](cases/CountWays/mutant_25/c/replay.json) | validated_violation | `{"n": 2}` | frozen_return_value_or_array_contents | True |
| [CountWays/26](cases/CountWays/mutant_26/c/replay.json) | validated_violation | `{"n": 2}` | frozen_return_value_or_array_contents | True |
| [CountWays/28](cases/CountWays/mutant_28/c/replay.json) | validated_violation | `{"n": 2}` | frozen_return_value_or_array_contents | True |
| [CountWays/29](cases/CountWays/mutant_29/c/replay.json) | validated_violation | `{"n": 2}` | frozen_return_value_or_array_contents | True |
| [CountWays/3](cases/CountWays/mutant_3/c/replay.json) | validated_safety_failure | `{"n": 1}` | runtime safety | True |
| [CountWays/30](cases/CountWays/mutant_30/c/replay.json) | validated_violation | `{"n": 2}` | frozen_return_value_or_array_contents | True |
| [CountWays/31](cases/CountWays/mutant_31/c/replay.json) | validated_violation | `{"n": 2}` | frozen_return_value_or_array_contents | True |
| [CountWays/32](cases/CountWays/mutant_32/c/replay.json) | validated_violation | `{"n": 2}` | frozen_return_value_or_array_contents | True |
| [CountWays/33](cases/CountWays/mutant_33/c/replay.json) | validated_violation | `{"n": 3}` | frozen_return_value_or_array_contents | True |
| [CountWays/34](cases/CountWays/mutant_34/c/replay.json) | validated_violation | `{"n": 3}` | frozen_return_value_or_array_contents | True |
| [CountWays/35](cases/CountWays/mutant_35/c/replay.json) | validated_safety_failure | `{"n": 2}` | runtime safety | True |
| [CountWays/36](cases/CountWays/mutant_36/c/replay.json) | validated_violation | `{"n": 3}` | frozen_return_value_or_array_contents | True |
| [CountWays/37](cases/CountWays/mutant_37/c/replay.json) | validated_violation | `{"n": 32}` | frozen_return_value_or_array_contents | True |
| [CountWays/38](cases/CountWays/mutant_38/c/replay.json) | validated_safety_failure | `{"n": 2}` | runtime safety | True |
| [CountWays/39](cases/CountWays/mutant_39/c/replay.json) | validated_safety_failure | `{"n": 2}` | runtime safety | True |
| [CountWays/4](cases/CountWays/mutant_4/c/replay.json) | validated_safety_failure | `{"n": 1}` | runtime safety | True |
| [CountWays/40](cases/CountWays/mutant_40/c/replay.json) | validated_violation | `{"n": 3}` | frozen_return_value_or_array_contents | True |
| [CountWays/42](cases/CountWays/mutant_42/c/replay.json) | validated_violation | `{"n": 4}` | frozen_return_value_or_array_contents | True |
| [CountWays/43](cases/CountWays/mutant_43/c/replay.json) | validated_violation | `{"n": 4}` | frozen_return_value_or_array_contents | True |
| [CountWays/45](cases/CountWays/mutant_45/c/replay.json) | validated_violation | `{"n": 4}` | frozen_return_value_or_array_contents | True |
| [CountWays/5](cases/CountWays/mutant_5/c/replay.json) | validated_safety_failure | `{"n": 1}` | runtime safety | True |
| [CountWays/6](cases/CountWays/mutant_6/c/replay.json) | validated_safety_failure | `{"n": 1}` | runtime safety | True |
| [CountWays/7](cases/CountWays/mutant_7/c/replay.json) | validated_safety_failure | `{"n": 1}` | runtime safety | True |
| [CountWays/8](cases/CountWays/mutant_8/c/replay.json) | validated_safety_failure | `{"n": 1}` | runtime safety | True |
| [CountWays/9](cases/CountWays/mutant_9/c/replay.json) | validated_violation | `{"n": 2}` | frozen_return_value_or_array_contents | True |
| [CountingSort/1](cases/CountingSort/mutant_1/c/replay.json) | validated_violation | `{"myArray": [0]}` | frozen_return_value_or_array_contents | True |
| [CountingSort/10](cases/CountingSort/mutant_10/c/replay.json) | validated_safety_failure | `{"myArray": [1, 0]}` | runtime safety | True |
| [CountingSort/11](cases/CountingSort/mutant_11/c/replay.json) | validated_safety_failure | `{"myArray": [1, 0]}` | runtime safety | True |
| [CountingSort/13](cases/CountingSort/mutant_13/c/replay.json) | validated_safety_failure | `{"myArray": [1, 0]}` | runtime safety | True |
| [CountingSort/12](cases/CountingSort/mutant_12/c/replay.json) | validated_safety_failure | `{"myArray": [2, 1]}` | runtime safety | True |
| [CountingSort/14](cases/CountingSort/mutant_14/c/replay.json) | validated_safety_failure | `{"myArray": [-1]}` | runtime safety | True |
| [CountingSort/15](cases/CountingSort/mutant_15/c/replay.json) | validated_safety_failure | `{"myArray": [-1, 0, 1]}` | runtime safety | True |
| [CountingSort/16](cases/CountingSort/mutant_16/c/replay.json) | validated_safety_failure | `{"myArray": [0]}` | runtime safety | True |
| [CountingSort/17](cases/CountingSort/mutant_17/c/replay.json) | validated_safety_failure | `{"myArray": [0]}` | runtime safety | True |
| [CountingSort/2](cases/CountingSort/mutant_2/c/replay.json) | validated_safety_failure | `{"myArray": []}` | runtime safety | True |
| [CountingSort/18](cases/CountingSort/mutant_18/c/replay.json) | validated_safety_failure | `{"myArray": [0]}` | runtime safety | True |
| [CountingSort/19](cases/CountingSort/mutant_19/c/replay.json) | validated_safety_failure | `{"myArray": [0]}` | runtime safety | True |
| [CountingSort/22](cases/CountingSort/mutant_22/c/replay.json) | validated_violation | `{"myArray": [2, 1]}` | frozen_return_value_or_array_contents | True |
| [CountingSort/23](cases/CountingSort/mutant_23/c/replay.json) | validated_violation | `{"myArray": [1, 0]}` | frozen_return_value_or_array_contents | True |
| [CountingSort/20](cases/CountingSort/mutant_20/c/replay.json) | validated_safety_failure | `{"myArray": [0]}` | runtime safety | True |
| [CountingSort/26](cases/CountingSort/mutant_26/c/replay.json) | validated_violation | `{"myArray": [-1]}` | frozen_return_value_or_array_contents | True |
| [CountingSort/25](cases/CountingSort/mutant_25/c/replay.json) | validated_violation | `{"myArray": [-1, -1, 0]}` | frozen_return_value_or_array_contents | True |
| [CountingSort/3](cases/CountingSort/mutant_3/c/replay.json) | validated_safety_failure | `{"myArray": []}` | runtime safety | True |
| [CountingSort/24](cases/CountingSort/mutant_24/c/replay.json) | validated_safety_failure | `{"myArray": [-1]}` | runtime safety | True |
| [CountingSort/32](cases/CountingSort/mutant_32/c/replay.json) | validated_violation | `{"myArray": [-1]}` | frozen_return_value_or_array_contents | True |
| [CountingSort/33](cases/CountingSort/mutant_33/c/replay.json) | validated_violation | `{"myArray": [-1]}` | frozen_return_value_or_array_contents | True |
| [CountingSort/34](cases/CountingSort/mutant_34/c/replay.json) | validated_violation | `{"myArray": [-1]}` | frozen_return_value_or_array_contents | True |
| [CountingSort/27](cases/CountingSort/mutant_27/c/replay.json) | validated_safety_failure | `{"myArray": [0]}` | runtime safety | True |
| [CountingSort/35](cases/CountingSort/mutant_35/c/replay.json) | validated_violation | `{"myArray": [-1]}` | frozen_return_value_or_array_contents | True |
| [CountingSort/30](cases/CountingSort/mutant_30/c/replay.json) | validated_safety_failure | `{"myArray": [0]}` | runtime safety | True |
| [CountingSort/36](cases/CountingSort/mutant_36/c/replay.json) | validated_violation | `{"myArray": [-1]}` | frozen_return_value_or_array_contents | True |
| [CountingSort/4](cases/CountingSort/mutant_4/c/replay.json) | validated_safety_failure | `{"myArray": [1, 0]}` | runtime safety | True |
| [CountingSort/6](cases/CountingSort/mutant_6/c/replay.json) | validated_safety_failure | `{"myArray": [0, 1]}` | runtime safety | True |
| [CountingSort/7](cases/CountingSort/mutant_7/c/replay.json) | validated_safety_failure | `{"myArray": [0, 1]}` | runtime safety | True |
| [CountingSort/8](cases/CountingSort/mutant_8/c/replay.json) | validated_safety_failure | `{"myArray": [0, 1]}` | runtime safety | True |
| [DealnnoyNum/10](cases/DealnnoyNum/mutant_10/c/replay.json) | validated_violation | `{"m": 1, "n": 1}` | frozen_return_value_or_array_contents | True |
| [DealnnoyNum/12](cases/DealnnoyNum/mutant_12/c/replay.json) | validated_violation | `{"m": 2, "n": 1}` | frozen_return_value_or_array_contents | True |
| [DealnnoyNum/17](cases/DealnnoyNum/mutant_17/c/replay.json) | validated_violation | `{"m": 3, "n": 1}` | frozen_return_value_or_array_contents | True |
| [DealnnoyNum/13](cases/DealnnoyNum/mutant_13/c/replay.json) | validated_safety_failure | `{"m": 1, "n": 1}` | runtime safety | True |
| [DealnnoyNum/15](cases/DealnnoyNum/mutant_15/c/replay.json) | validated_safety_failure | `{"m": 1, "n": 1}` | runtime safety | True |
| [DealnnoyNum/19](cases/DealnnoyNum/mutant_19/c/replay.json) | validated_violation | `{"m": 3, "n": 1}` | frozen_return_value_or_array_contents | True |
| [DealnnoyNum/11](cases/DealnnoyNum/mutant_11/c/replay.json) | validated_safety_failure | `{"m": 0, "n": 0}` | runtime safety | True |
| [DealnnoyNum/2](cases/DealnnoyNum/mutant_2/c/replay.json) | validated_violation | `{"m": 1, "n": 1}` | frozen_return_value_or_array_contents | True |
| [DealnnoyNum/18](cases/DealnnoyNum/mutant_18/c/replay.json) | validated_safety_failure | `{"m": 3, "n": 1}` | runtime safety | True |
| [DealnnoyNum/21](cases/DealnnoyNum/mutant_21/c/replay.json) | validated_violation | `{"m": 2, "n": 1}` | frozen_return_value_or_array_contents | True |
| [DealnnoyNum/23](cases/DealnnoyNum/mutant_23/c/replay.json) | validated_violation | `{"m": 2, "n": 1}` | frozen_return_value_or_array_contents | True |
| [DealnnoyNum/24](cases/DealnnoyNum/mutant_24/c/replay.json) | validated_violation | `{"m": 1, "n": 1}` | frozen_return_value_or_array_contents | True |
| [DealnnoyNum/25](cases/DealnnoyNum/mutant_25/c/replay.json) | validated_violation | `{"m": 1, "n": 1}` | frozen_return_value_or_array_contents | True |
| [DealnnoyNum/26](cases/DealnnoyNum/mutant_26/c/replay.json) | validated_violation | `{"m": 1, "n": 1}` | frozen_return_value_or_array_contents | True |
| [DealnnoyNum/27](cases/DealnnoyNum/mutant_27/c/replay.json) | validated_violation | `{"m": 1, "n": 1}` | frozen_return_value_or_array_contents | True |
| [DealnnoyNum/28](cases/DealnnoyNum/mutant_28/c/replay.json) | validated_violation | `{"m": 3, "n": 1}` | frozen_return_value_or_array_contents | True |
| [DealnnoyNum/22](cases/DealnnoyNum/mutant_22/c/replay.json) | validated_safety_failure | `{"m": 2, "n": 1}` | runtime safety | True |
| [DealnnoyNum/29](cases/DealnnoyNum/mutant_29/c/replay.json) | validated_safety_failure | `{"m": 1, "n": 1}` | runtime safety | True |
| [DealnnoyNum/30](cases/DealnnoyNum/mutant_30/c/replay.json) | validated_safety_failure | `{"m": 1, "n": 1}` | runtime safety | True |
| [DealnnoyNum/32](cases/DealnnoyNum/mutant_32/c/replay.json) | validated_violation | `{"m": 1, "n": 1}` | frozen_return_value_or_array_contents | True |
| [DealnnoyNum/31](cases/DealnnoyNum/mutant_31/c/replay.json) | validated_safety_failure | `{"m": 1, "n": 1}` | runtime safety | True |
| [DealnnoyNum/3](cases/DealnnoyNum/mutant_3/c/replay.json) | validated_safety_failure | `{"m": 0, "n": -1}` | runtime safety | True |
| [DealnnoyNum/33](cases/DealnnoyNum/mutant_33/c/replay.json) | validated_violation | `{"m": 1, "n": 1}` | frozen_return_value_or_array_contents | True |
| [DealnnoyNum/34](cases/DealnnoyNum/mutant_34/c/replay.json) | validated_violation | `{"m": 1, "n": 1}` | frozen_return_value_or_array_contents | True |
| [DealnnoyNum/35](cases/DealnnoyNum/mutant_35/c/replay.json) | validated_violation | `{"m": 1, "n": 1}` | frozen_return_value_or_array_contents | True |
| [DealnnoyNum/5](cases/DealnnoyNum/mutant_5/c/replay.json) | validated_violation | `{"m": 1, "n": 1}` | frozen_return_value_or_array_contents | True |
| [DealnnoyNum/7](cases/DealnnoyNum/mutant_7/c/replay.json) | validated_safety_failure | `{"m": 0, "n": 0}` | runtime safety | True |
| [DealnnoyNum/6](cases/DealnnoyNum/mutant_6/c/replay.json) | validated_safety_failure | `{"m": -2, "n": 0}` | runtime safety | True |
| [DealnnoyNum/8](cases/DealnnoyNum/mutant_8/c/replay.json) | validated_safety_failure | `{"m": -2, "n": 0}` | runtime safety | True |
| [DiameterCircle/1](cases/DiameterCircle/mutant_1/c/replay.json) | validated_violation | `{"r": 2125}` | frozen_return_value_or_array_contents | True |
| [DealnnoyNum/9](cases/DealnnoyNum/mutant_9/c/replay.json) | validated_safety_failure | `{"m": 0, "n": -1}` | runtime safety | True |
| [DiameterCircle/2](cases/DiameterCircle/mutant_2/c/replay.json) | validated_violation | `{"r": 0}` | frozen_return_value_or_array_contents | True |
| [DiameterCircle/3](cases/DiameterCircle/mutant_3/c/replay.json) | validated_violation | `{"r": 0}` | frozen_return_value_or_array_contents | True |
| [DiameterCircle/4](cases/DiameterCircle/mutant_4/c/replay.json) | validated_violation | `{"r": 2125}` | frozen_return_value_or_array_contents | True |
| [DiffEvenOdd/1](cases/DiffEvenOdd/mutant_1/c/replay.json) | validated_violation | `{"array": [-2030, 0, 0]}` | frozen_return_value_or_array_contents | True |
| [DiffEvenOdd/10](cases/DiffEvenOdd/mutant_10/c/replay.json) | validated_violation | `{"array": [1135, 0]}` | frozen_return_value_or_array_contents | True |
| [DiffEvenOdd/12](cases/DiffEvenOdd/mutant_12/c/replay.json) | validated_violation | `{"array": [1135, 0]}` | frozen_return_value_or_array_contents | True |
| [DiffEvenOdd/13](cases/DiffEvenOdd/mutant_13/c/replay.json) | validated_violation | `{"array": [1135, 0]}` | frozen_return_value_or_array_contents | True |
| [DiffEvenOdd/14](cases/DiffEvenOdd/mutant_14/c/replay.json) | validated_violation | `{"array": [-2030, 0, 0]}` | frozen_return_value_or_array_contents | True |
| [DiffEvenOdd/15](cases/DiffEvenOdd/mutant_15/c/replay.json) | validated_violation | `{"array": [1135, 0]}` | frozen_return_value_or_array_contents | True |
| [DiffEvenOdd/18](cases/DiffEvenOdd/mutant_18/c/replay.json) | validated_violation | `{"array": [1135, 0]}` | frozen_return_value_or_array_contents | True |
| [DiffEvenOdd/19](cases/DiffEvenOdd/mutant_19/c/replay.json) | validated_violation | `{"array": [-2030, 0, 0]}` | frozen_return_value_or_array_contents | True |
| [DiffEvenOdd/20](cases/DiffEvenOdd/mutant_20/c/replay.json) | validated_violation | `{"array": [-2030, 0, 0]}` | frozen_return_value_or_array_contents | True |
| [DiffEvenOdd/21](cases/DiffEvenOdd/mutant_21/c/replay.json) | validated_violation | `{"array": [-2030, 0, 0]}` | frozen_return_value_or_array_contents | True |
| [DiffEvenOdd/22](cases/DiffEvenOdd/mutant_22/c/replay.json) | validated_violation | `{"array": [-2030, 0, 0]}` | frozen_return_value_or_array_contents | True |
| [DiffEvenOdd/23](cases/DiffEvenOdd/mutant_23/c/replay.json) | validated_violation | `{"array": [1135, 0]}` | frozen_return_value_or_array_contents | True |
| [DiffEvenOdd/24](cases/DiffEvenOdd/mutant_24/c/replay.json) | validated_violation | `{"array": [-221, 0, 0, 0, 0, 0, 0, 0, 0]}` | frozen_return_value_or_array_contents | True |
| [DiffEvenOdd/25](cases/DiffEvenOdd/mutant_25/c/replay.json) | validated_violation | `{"array": [-2030, 0, 0]}` | frozen_return_value_or_array_contents | True |
| [DiffEvenOdd/26](cases/DiffEvenOdd/mutant_26/c/replay.json) | validated_violation | `{"array": [1135, 0]}` | frozen_return_value_or_array_contents | True |
| [DiffEvenOdd/27](cases/DiffEvenOdd/mutant_27/c/replay.json) | validated_violation | `{"array": [1135, 0]}` | frozen_return_value_or_array_contents | True |
| [DiffEvenOdd/28](cases/DiffEvenOdd/mutant_28/c/replay.json) | validated_violation | `{"array": [-2030, 0, 0]}` | frozen_return_value_or_array_contents | True |
| [DiffEvenOdd/3](cases/DiffEvenOdd/mutant_3/c/replay.json) | validated_violation | `{"array": [1135, 0]}` | frozen_return_value_or_array_contents | True |
| [DiffEvenOdd/30](cases/DiffEvenOdd/mutant_30/c/replay.json) | validated_violation | `{"array": [1135, 0]}` | frozen_return_value_or_array_contents | True |
| [DiffEvenOdd/33](cases/DiffEvenOdd/mutant_33/c/replay.json) | validated_violation | `{"array": [1135, 0]}` | frozen_return_value_or_array_contents | True |
| [DiffEvenOdd/4](cases/DiffEvenOdd/mutant_4/c/replay.json) | validated_violation | `{"array": [-2030, 0, 0]}` | frozen_return_value_or_array_contents | True |
| [DiffEvenOdd/40](cases/DiffEvenOdd/mutant_40/c/replay.json) | validated_violation | `{"array": [1135, 0]}` | frozen_return_value_or_array_contents | True |
| [DiffEvenOdd/42](cases/DiffEvenOdd/mutant_42/c/replay.json) | validated_violation | `{"array": [1135, 0]}` | frozen_return_value_or_array_contents | True |
| [DiffEvenOdd/43](cases/DiffEvenOdd/mutant_43/c/replay.json) | validated_violation | `{"array": [1135, 0]}` | frozen_return_value_or_array_contents | True |
| [DiffEvenOdd/44](cases/DiffEvenOdd/mutant_44/c/replay.json) | validated_violation | `{"array": [1135, 0]}` | frozen_return_value_or_array_contents | True |
| [DiffEvenOdd/45](cases/DiffEvenOdd/mutant_45/c/replay.json) | validated_violation | `{"array": [1135, 0]}` | frozen_return_value_or_array_contents | True |
| [DiffEvenOdd/5](cases/DiffEvenOdd/mutant_5/c/replay.json) | validated_violation | `{"array": [1135, 0]}` | frozen_return_value_or_array_contents | True |
| [DiffEvenOdd/6](cases/DiffEvenOdd/mutant_6/c/replay.json) | validated_violation | `{"array": [1135, 0]}` | frozen_return_value_or_array_contents | True |
| [DiffEvenOdd/7](cases/DiffEvenOdd/mutant_7/c/replay.json) | validated_violation | `{"array": [-2030, 0, 0]}` | frozen_return_value_or_array_contents | True |
| [DiffEvenOdd/8](cases/DiffEvenOdd/mutant_8/c/replay.json) | validated_violation | `{"array": [-221, 0, 0, 0, 0, 0, 0, 0, 0]}` | frozen_return_value_or_array_contents | True |
| [DiffEvenOdd/9](cases/DiffEvenOdd/mutant_9/c/replay.json) | validated_violation | `{"array": [1135, 0]}` | frozen_return_value_or_array_contents | True |
| [DogAge/1](cases/DogAge/mutant_1/c/replay.json) | validated_violation | `{"hAge": 2125}` | frozen_return_value_or_array_contents | True |
| [DogAge/10](cases/DogAge/mutant_10/c/replay.json) | validated_violation | `{"hAge": 0}` | frozen_return_value_or_array_contents | True |
| [DogAge/11](cases/DogAge/mutant_11/c/replay.json) | validated_violation | `{"hAge": 0}` | frozen_return_value_or_array_contents | True |
| [DogAge/12](cases/DogAge/mutant_12/c/replay.json) | validated_violation | `{"hAge": 0}` | frozen_return_value_or_array_contents | True |
| [DogAge/13](cases/DogAge/mutant_13/c/replay.json) | validated_violation | `{"hAge": 0}` | frozen_return_value_or_array_contents | True |
| [DogAge/14](cases/DogAge/mutant_14/c/replay.json) | validated_violation | `{"hAge": 0}` | frozen_return_value_or_array_contents | True |
| [DogAge/15](cases/DogAge/mutant_15/c/replay.json) | validated_violation | `{"hAge": 0}` | frozen_return_value_or_array_contents | True |
| [DogAge/16](cases/DogAge/mutant_16/c/replay.json) | validated_violation | `{"hAge": -3882}` | frozen_return_value_or_array_contents | True |
| [DogAge/17](cases/DogAge/mutant_17/c/replay.json) | validated_violation | `{"hAge": -3882}` | frozen_return_value_or_array_contents | True |
| [DogAge/18](cases/DogAge/mutant_18/c/replay.json) | validated_violation | `{"hAge": -3882}` | frozen_return_value_or_array_contents | True |
| [DogAge/19](cases/DogAge/mutant_19/c/replay.json) | validated_violation | `{"hAge": -3882}` | frozen_return_value_or_array_contents | True |
| [DogAge/2](cases/DogAge/mutant_2/c/replay.json) | validated_violation | `{"hAge": 0}` | frozen_return_value_or_array_contents | True |
| [DogAge/20](cases/DogAge/mutant_20/c/replay.json) | validated_violation | `{"hAge": -3882}` | frozen_return_value_or_array_contents | True |
| [DogAge/21](cases/DogAge/mutant_21/c/replay.json) | validated_violation | `{"hAge": -3882}` | frozen_return_value_or_array_contents | True |
| [DogAge/22](cases/DogAge/mutant_22/c/replay.json) | validated_violation | `{"hAge": -3882}` | frozen_return_value_or_array_contents | True |
| [DogAge/23](cases/DogAge/mutant_23/c/replay.json) | validated_violation | `{"hAge": -3882}` | frozen_return_value_or_array_contents | True |
| [DogAge/24](cases/DogAge/mutant_24/c/replay.json) | validated_violation | `{"hAge": -3882}` | frozen_return_value_or_array_contents | True |
| [DogAge/25](cases/DogAge/mutant_25/c/replay.json) | validated_violation | `{"hAge": -3882}` | frozen_return_value_or_array_contents | True |
| [DogAge/26](cases/DogAge/mutant_26/c/replay.json) | validated_violation | `{"hAge": -3882}` | frozen_return_value_or_array_contents | True |
| [DogAge/27](cases/DogAge/mutant_27/c/replay.json) | validated_violation | `{"hAge": -3882}` | frozen_return_value_or_array_contents | True |
| [DogAge/3](cases/DogAge/mutant_3/c/replay.json) | validated_violation | `{"hAge": -3882}` | frozen_return_value_or_array_contents | True |
| [DogAge/4](cases/DogAge/mutant_4/c/replay.json) | validated_violation | `{"hAge": 0}` | frozen_return_value_or_array_contents | True |
| [DogAge/5](cases/DogAge/mutant_5/c/replay.json) | validated_violation | `{"hAge": 0}` | frozen_return_value_or_array_contents | True |
| [DogAge/6](cases/DogAge/mutant_6/c/replay.json) | validated_violation | `{"hAge": 0}` | frozen_return_value_or_array_contents | True |
| [DogAge/7](cases/DogAge/mutant_7/c/replay.json) | validated_violation | `{"hAge": 0}` | frozen_return_value_or_array_contents | True |
| [DogAge/8](cases/DogAge/mutant_8/c/replay.json) | validated_violation | `{"hAge": 0}` | frozen_return_value_or_array_contents | True |
| [DogAge/9](cases/DogAge/mutant_9/c/replay.json) | validated_violation | `{"hAge": 0}` | frozen_return_value_or_array_contents | True |
| [Fibonacci/11](cases/Fibonacci/mutant_11/c/replay.json) | validated_violation | `{"n": 4}` | frozen_return_value_or_array_contents | True |
| [Fibonacci/10](cases/Fibonacci/mutant_10/c/replay.json) | validated_safety_failure | `{"n": 2}` | runtime safety | True |
| [Fibonacci/12](cases/Fibonacci/mutant_12/c/replay.json) | validated_safety_failure | `{"n": 2}` | runtime safety | True |
| [Fibonacci/13](cases/Fibonacci/mutant_13/c/replay.json) | validated_safety_failure | `{"n": 2}` | runtime safety | True |
| [Fibonacci/14](cases/Fibonacci/mutant_14/c/replay.json) | validated_violation | `{"n": 2}` | frozen_return_value_or_array_contents | True |
| [Fibonacci/2](cases/Fibonacci/mutant_2/c/replay.json) | validated_violation | `{"n": 1}` | frozen_return_value_or_array_contents | True |
| [Fibonacci/3](cases/Fibonacci/mutant_3/c/replay.json) | validated_safety_failure | `{"n": 0}` | runtime safety | True |
| [Fibonacci/5](cases/Fibonacci/mutant_5/c/replay.json) | validated_violation | `{"n": 3}` | frozen_return_value_or_array_contents | True |
| [Fibonacci/7](cases/Fibonacci/mutant_7/c/replay.json) | validated_violation | `{"n": 2}` | frozen_return_value_or_array_contents | True |
| [Fibonacci/6](cases/Fibonacci/mutant_6/c/replay.json) | validated_safety_failure | `{"n": 1}` | runtime safety | True |
| [Fibonacci/8](cases/Fibonacci/mutant_8/c/replay.json) | validated_safety_failure | `{"n": 2}` | runtime safety | True |
| [Fibonacci/9](cases/Fibonacci/mutant_9/c/replay.json) | validated_safety_failure | `{"n": 2}` | runtime safety | True |
| [FindPeak/1](cases/FindPeak/mutant_1/c/replay.json) | validated_violation | `{"arr": [0, 1], "n": 2}` | frozen_return_value_or_array_contents, peak_neighbors | True |
| [FindPeak/12](cases/FindPeak/mutant_12/c/replay.json) | validated_violation | `{"arr": [-1, 0, 1], "n": 2}` | frozen_return_value_or_array_contents | True |
| [FindPeak/10](cases/FindPeak/mutant_10/c/replay.json) | validated_violation | `{"arr": [-1, -3, 2147483647, 1, -3, -2147483648, 2], "n": 7}` | frozen_return_value_or_array_contents | True |
| [FindPeak/15](cases/FindPeak/mutant_15/c/replay.json) | validated_violation | `{"arr": [3, 1, 2], "n": 3}` | frozen_return_value_or_array_contents | True |
| [FindPeak/16](cases/FindPeak/mutant_16/c/replay.json) | validated_violation | `{"arr": [3, 1, 2], "n": 3}` | frozen_return_value_or_array_contents | True |
| [FindPeak/17](cases/FindPeak/mutant_17/c/replay.json) | validated_violation | `{"arr": [3, 1, 2], "n": 3}` | frozen_return_value_or_array_contents | True |
| [FindPeak/13](cases/FindPeak/mutant_13/c/replay.json) | validated_safety_failure | `{"arr": [1, 0], "n": 2}` | runtime safety | True |
| [FindPeak/14](cases/FindPeak/mutant_14/c/replay.json) | validated_safety_failure | `{"arr": [1, 0], "n": 2}` | runtime safety | True |
| [FindPeak/19](cases/FindPeak/mutant_19/c/replay.json) | validated_violation | `{"arr": [3, 1, 2], "n": 3}` | frozen_return_value_or_array_contents | True |
| [FindPeak/2](cases/FindPeak/mutant_2/c/replay.json) | validated_violation | `{"arr": [0, 1], "n": 1}` | frozen_return_value_or_array_contents | True |
| [FindPeak/20](cases/FindPeak/mutant_20/c/replay.json) | validated_violation | `{"arr": [0, 1], "n": 2}` | frozen_return_value_or_array_contents, peak_neighbors | True |
| [FindPeak/21](cases/FindPeak/mutant_21/c/replay.json) | validated_violation | `{"arr": [0, 1], "n": 2}` | frozen_return_value_or_array_contents, peak_neighbors | True |
| [FindPeak/22](cases/FindPeak/mutant_22/c/replay.json) | validated_violation | `{"arr": [4, 3, 2, 1], "n": 3}` | frozen_return_value_or_array_contents, peak_neighbors | True |
| [FindPeak/24](cases/FindPeak/mutant_24/c/replay.json) | validated_violation | `{"arr": [1, 0], "n": 2}` | frozen_return_value_or_array_contents, peak_neighbors | True |
| [FindPeak/23](cases/FindPeak/mutant_23/c/replay.json) | validated_violation | `{"arr": [0, 1], "n": 2}` | frozen_return_value_or_array_contents, peak_neighbors | True |
| [FindPeak/25](cases/FindPeak/mutant_25/c/replay.json) | validated_violation | `{"arr": [1, 1, 1], "n": 2}` | frozen_return_value_or_array_contents | True |
| [FindPeak/26](cases/FindPeak/mutant_26/c/replay.json) | validated_violation | `{"arr": [0, 1], "n": 2}` | frozen_return_value_or_array_contents, peak_neighbors | True |
| [FindPeak/3](cases/FindPeak/mutant_3/c/replay.json) | validated_violation | `{"arr": [0, 1], "n": 0}` | frozen_return_value_or_array_contents | True |
| [FindPeak/18](cases/FindPeak/mutant_18/c/replay.json) | validated_safety_failure | `{"arr": [-1, 0, 1], "n": 3}` | runtime safety | True |
| [FindPeak/4](cases/FindPeak/mutant_4/c/replay.json) | validated_violation | `{"arr": [0, 1], "n": 1}` | frozen_return_value_or_array_contents | True |
| [FindPoints/1](cases/FindPoints/mutant_1/c/replay.json) | validated_violation | `{"l1": -1628, "l2": -2185, "r1": 0, "r2": 1471}` | frozen_return_value_or_array_contents | True |
| [FindPeak/9](cases/FindPeak/mutant_9/c/replay.json) | validated_violation | `{"arr": [3, 1, 2], "n": 3}` | frozen_return_value_or_array_contents | True |
| [FindPeak/6](cases/FindPeak/mutant_6/c/replay.json) | validated_violation | `{"arr": [0, 1], "n": 1}` | frozen_return_value_or_array_contents | True |
| [FindPoints/10](cases/FindPoints/mutant_10/c/replay.json) | validated_violation | `{"l1": -1628, "l2": -2185, "r1": 0, "r2": 1471}` | frozen_return_value_or_array_contents | True |
| [FindPoints/11](cases/FindPoints/mutant_11/c/replay.json) | validated_violation | `{"l1": 0, "l2": 648, "r1": -1746, "r2": 2433}` | frozen_return_value_or_array_contents | True |
| [FindPoints/12](cases/FindPoints/mutant_12/c/replay.json) | validated_violation | `{"l1": 0, "l2": 648, "r1": -1746, "r2": 2433}` | frozen_return_value_or_array_contents | True |
| [FindPoints/13](cases/FindPoints/mutant_13/c/replay.json) | validated_violation | `{"l1": 0, "l2": 1471, "r1": 2733, "r2": -1112}` | frozen_return_value_or_array_contents | True |
| [FindPoints/15](cases/FindPoints/mutant_15/c/replay.json) | validated_violation | `{"l1": 1471, "l2": 0, "r1": 1471, "r2": 0}` | frozen_return_value_or_array_contents | True |
| [FindPoints/16](cases/FindPoints/mutant_16/c/replay.json) | validated_violation | `{"l1": -1628, "l2": -2185, "r1": 0, "r2": 1471}` | frozen_return_value_or_array_contents | True |
| [FindPoints/17](cases/FindPoints/mutant_17/c/replay.json) | validated_violation | `{"l1": 0, "l2": -450, "r1": 0, "r2": 0}` | frozen_return_value_or_array_contents | True |
| [FindPoints/18](cases/FindPoints/mutant_18/c/replay.json) | validated_violation | `{"l1": 1471, "l2": 0, "r1": 1471, "r2": 0}` | frozen_return_value_or_array_contents | True |
| [FindPoints/19](cases/FindPoints/mutant_19/c/replay.json) | validated_violation | `{"l1": -4430, "l2": -1679, "r1": 2733, "r2": 2733}` | frozen_return_value_or_array_contents | True |
| [FindPoints/20](cases/FindPoints/mutant_20/c/replay.json) | validated_violation | `{"l1": 1471, "l2": 0, "r1": 1471, "r2": 0}` | frozen_return_value_or_array_contents | True |
| [FindPoints/21](cases/FindPoints/mutant_21/c/replay.json) | validated_violation | `{"l1": 0, "l2": -450, "r1": 0, "r2": 0}` | frozen_return_value_or_array_contents | True |
| [FindPoints/22](cases/FindPoints/mutant_22/c/replay.json) | validated_violation | `{"l1": 0, "l2": 1471, "r1": 2733, "r2": -1112}` | frozen_return_value_or_array_contents | True |
| [FindPoints/25](cases/FindPoints/mutant_25/c/replay.json) | validated_violation | `{"l1": -4430, "l2": -1679, "r1": 2733, "r2": 2733}` | frozen_return_value_or_array_contents | True |
| [FindPoints/24](cases/FindPoints/mutant_24/c/replay.json) | validated_violation | `{"l1": 1471, "l2": 0, "r1": 1471, "r2": 0}` | frozen_return_value_or_array_contents | True |
| [FindPoints/26](cases/FindPoints/mutant_26/c/replay.json) | validated_violation | `{"l1": -4430, "l2": -1679, "r1": 2733, "r2": 2733}` | frozen_return_value_or_array_contents | True |
| [FindPoints/3](cases/FindPoints/mutant_3/c/replay.json) | validated_violation | `{"l1": 0, "l2": 648, "r1": -1746, "r2": 2433}` | frozen_return_value_or_array_contents | True |
| [FindPoints/4](cases/FindPoints/mutant_4/c/replay.json) | validated_violation | `{"l1": 0, "l2": 1471, "r1": 2733, "r2": -1112}` | frozen_return_value_or_array_contents | True |
| [FindPoints/6](cases/FindPoints/mutant_6/c/replay.json) | validated_violation | `{"l1": 0, "l2": 648, "r1": -1746, "r2": 2433}` | frozen_return_value_or_array_contents | True |
| [FindPoints/7](cases/FindPoints/mutant_7/c/replay.json) | validated_violation | `{"l1": 1471, "l2": 0, "r1": 1471, "r2": 0}` | frozen_return_value_or_array_contents | True |
| [FindPoints/8](cases/FindPoints/mutant_8/c/replay.json) | validated_violation | `{"l1": 0, "l2": 648, "r1": -1746, "r2": 2433}` | frozen_return_value_or_array_contents | True |
| [FindPoints/9](cases/FindPoints/mutant_9/c/replay.json) | validated_violation | `{"l1": 0, "l2": 1471, "r1": 2733, "r2": -1112}` | frozen_return_value_or_array_contents | True |
| [FindRectNum/1](cases/FindRectNum/mutant_1/c/replay.json) | validated_violation | `{"n": 1446006}` | frozen_return_value_or_array_contents | True |
| [FindRectNum/2](cases/FindRectNum/mutant_2/c/replay.json) | validated_violation | `{"n": 1446006}` | frozen_return_value_or_array_contents | True |
| [FindRectNum/3](cases/FindRectNum/mutant_3/c/replay.json) | validated_violation | `{"n": 1446006}` | frozen_return_value_or_array_contents | True |
| [FindRectNum/5](cases/FindRectNum/mutant_5/c/replay.json) | validated_violation | `{"n": 1446006}` | frozen_return_value_or_array_contents | True |
| [FindRectNum/4](cases/FindRectNum/mutant_4/c/replay.json) | validated_violation | `{"n": 1446006}` | frozen_return_value_or_array_contents | True |
| [FindRectNum/6](cases/FindRectNum/mutant_6/c/replay.json) | validated_violation | `{"n": 0}` | frozen_return_value_or_array_contents | True |
| [FindRectNum/8](cases/FindRectNum/mutant_8/c/replay.json) | validated_violation | `{"n": 1446006}` | frozen_return_value_or_array_contents | True |
| [FindRectNum/7](cases/FindRectNum/mutant_7/c/replay.json) | validated_violation | `{"n": 0}` | frozen_return_value_or_array_contents | True |
| [HexagonalNum/1](cases/HexagonalNum/mutant_1/c/replay.json) | validated_violation | `{"n": 10032960}` | frozen_return_value_or_array_contents | True |
| [HexagonalNum/10](cases/HexagonalNum/mutant_10/c/replay.json) | validated_violation | `{"n": 0}` | frozen_return_value_or_array_contents | True |
| [HexagonalNum/11](cases/HexagonalNum/mutant_11/c/replay.json) | validated_violation | `{"n": 0}` | frozen_return_value_or_array_contents | True |
| [HexagonalNum/12](cases/HexagonalNum/mutant_12/c/replay.json) | validated_violation | `{"n": 10032960}` | frozen_return_value_or_array_contents | True |
| [HexagonalNum/2](cases/HexagonalNum/mutant_2/c/replay.json) | validated_violation | `{"n": 10032960}` | frozen_return_value_or_array_contents | True |
| [HexagonalNum/3](cases/HexagonalNum/mutant_3/c/replay.json) | validated_violation | `{"n": 10032960}` | frozen_return_value_or_array_contents | True |
| [HexagonalNum/4](cases/HexagonalNum/mutant_4/c/replay.json) | validated_violation | `{"n": 10032960}` | frozen_return_value_or_array_contents | True |
| [HexagonalNum/5](cases/HexagonalNum/mutant_5/c/replay.json) | validated_violation | `{"n": 10032960}` | frozen_return_value_or_array_contents | True |
| [HexagonalNum/6](cases/HexagonalNum/mutant_6/c/replay.json) | validated_violation | `{"n": 10032960}` | frozen_return_value_or_array_contents | True |
| [HexagonalNum/7](cases/HexagonalNum/mutant_7/c/replay.json) | validated_violation | `{"n": 10032960}` | frozen_return_value_or_array_contents | True |
| [HexagonalNum/8](cases/HexagonalNum/mutant_8/c/replay.json) | validated_violation | `{"n": 10032960}` | frozen_return_value_or_array_contents | True |
| [HexagonalNum/9](cases/HexagonalNum/mutant_9/c/replay.json) | validated_violation | `{"n": 10032960}` | frozen_return_value_or_array_contents | True |
| [LeftInsertion/12](cases/LeftInsertion/mutant_12/c/replay.json) | validated_violation | `{"a": [0, 0], "x": 0}` | frozen_return_value_or_array_contents | True |
| [LeftInsertion/10](cases/LeftInsertion/mutant_10/c/replay.json) | validated_safety_failure | `{"a": [0, 0, 0, 0, 0], "x": -1}` | runtime safety | True |
| [LeftInsertion/16](cases/LeftInsertion/mutant_16/c/replay.json) | validated_violation | `{"a": [0, 582], "x": 1}` | frozen_return_value_or_array_contents, sorted_partition | True |
| [LeftInsertion/15](cases/LeftInsertion/mutant_15/c/replay.json) | validated_violation | `{"a": [3, 1, 2], "x": 1}` | frozen_return_value_or_array_contents | True |
| [LeftInsertion/17](cases/LeftInsertion/mutant_17/c/replay.json) | validated_violation | `{"a": [0, 0, 0, 0, 0], "x": -1}` | frozen_return_value_or_array_contents, sorted_partition | True |
| [LeftInsertion/2](cases/LeftInsertion/mutant_2/c/replay.json) | validated_violation | `{"a": [0, 0], "x": 0}` | frozen_return_value_or_array_contents | True |
| [LeftInsertion/20](cases/LeftInsertion/mutant_20/c/replay.json) | validated_violation | `{"a": [0, 0, 0, 0, 0], "x": -1}` | frozen_return_value_or_array_contents, sorted_partition | True |
| [LeftInsertion/22](cases/LeftInsertion/mutant_22/c/replay.json) | validated_violation | `{"a": [0, 582], "x": 1}` | frozen_return_value_or_array_contents, sorted_partition | True |
| [LeftInsertion/3](cases/LeftInsertion/mutant_3/c/replay.json) | validated_violation | `{"a": [0, 0], "x": 0}` | frozen_return_value_or_array_contents | True |
| [LeftInsertion/4](cases/LeftInsertion/mutant_4/c/replay.json) | validated_violation | `{"a": [0, 0], "x": 0}` | frozen_return_value_or_array_contents | True |
| [LeftInsertion/6](cases/LeftInsertion/mutant_6/c/replay.json) | validated_violation | `{"a": [0, 582], "x": 1}` | frozen_return_value_or_array_contents, sorted_partition | True |
| [MaxDifference/12](cases/MaxDifference/mutant_12/c/replay.json) | validated_violation | `{"testArray": [[1, 2], [3, 4]]}` | frozen_return_value_or_array_contents | True |
| [MaxDifference/1](cases/MaxDifference/mutant_1/c/replay.json) | validated_safety_failure | `{"testArray": [[0, 0, 0, 0, 0], [0, 0, 0, 0, 0]]}` | runtime safety | True |
| [MaxDifference/14](cases/MaxDifference/mutant_14/c/replay.json) | validated_violation | `{"testArray": [[2, -1], [4, 3], [0, 0]]}` | frozen_return_value_or_array_contents | True |
| [MaxDifference/15](cases/MaxDifference/mutant_15/c/replay.json) | validated_violation | `{"testArray": [[1, 2], [3, 4]]}` | frozen_return_value_or_array_contents | True |
| [MaxDifference/17](cases/MaxDifference/mutant_17/c/replay.json) | validated_violation | `{"testArray": [[1, 2], [3, 4]]}` | frozen_return_value_or_array_contents | True |
| [LeftInsertion/13](cases/LeftInsertion/mutant_13/c/replay.json) | validated_safety_failure | `{"a": [0, 0, 0, 0, 0], "x": -1}` | runtime safety | True |
| [MaxDifference/18](cases/MaxDifference/mutant_18/c/replay.json) | validated_violation | `{"testArray": [[1, 2], [3, 4]]}` | frozen_return_value_or_array_contents | True |
| [MaxDifference/7](cases/MaxDifference/mutant_7/c/replay.json) | validated_violation | `{"testArray": [[2, -1], [4, 3], [0, 0]]}` | frozen_return_value_or_array_contents | True |
| [MaxOfTwo/1](cases/MaxOfTwo/mutant_1/c/replay.json) | validated_violation | `{"x": 1, "y": 462}` | frozen_return_value_or_array_contents | True |
| [MaxOfTwo/3](cases/MaxOfTwo/mutant_3/c/replay.json) | validated_violation | `{"x": -1, "y": -347}` | frozen_return_value_or_array_contents | True |
| [MaxProduct/13](cases/MaxProduct/mutant_13/c/replay.json) | validated_violation | `{"arr": [2, 1313], "n": 2}` | frozen_return_value_or_array_contents | True |
| [LeftInsertion/14](cases/LeftInsertion/mutant_14/c/replay.json) | validated_safety_failure | `{"a": [0, 0, 0, 0, 0], "x": -1}` | runtime safety | True |
| [MaxProduct/14](cases/MaxProduct/mutant_14/c/replay.json) | validated_violation | `{"arr": [2, 1313], "n": 2}` | frozen_return_value_or_array_contents | True |
| [MaxProduct/16](cases/MaxProduct/mutant_16/c/replay.json) | validated_violation | `{"arr": [2, 1313], "n": 2}` | frozen_return_value_or_array_contents | True |
| [MaxProduct/17](cases/MaxProduct/mutant_17/c/replay.json) | validated_violation | `{"arr": [2, 1313], "n": 2}` | frozen_return_value_or_array_contents | True |
| [MaxProduct/18](cases/MaxProduct/mutant_18/c/replay.json) | validated_violation | `{"arr": [0, 7, 0, 0, 0, 0, 0, 0, 0, 0], "n": 7}` | frozen_return_value_or_array_contents | True |
| [MaxProduct/20](cases/MaxProduct/mutant_20/c/replay.json) | validated_violation | `{"arr": [2, 1313], "n": 2}` | frozen_return_value_or_array_contents | True |
| [MaxProduct/22](cases/MaxProduct/mutant_22/c/replay.json) | validated_violation | `{"arr": [2, 1313], "n": 2}` | frozen_return_value_or_array_contents | True |
| [MaxProduct/23](cases/MaxProduct/mutant_23/c/replay.json) | validated_violation | `{"arr": [0, 7, 0, 0, 0, 0, 0, 0, 0, 0], "n": 7}` | frozen_return_value_or_array_contents | True |
| [MaxProduct/25](cases/MaxProduct/mutant_25/c/replay.json) | validated_violation | `{"arr": [2, 1313], "n": 2}` | frozen_return_value_or_array_contents | True |
| [MaxProduct/26](cases/MaxProduct/mutant_26/c/replay.json) | validated_violation | `{"arr": [2, 1313], "n": 2}` | frozen_return_value_or_array_contents | True |
| [MaxProduct/27](cases/MaxProduct/mutant_27/c/replay.json) | validated_violation | `{"arr": [2, 1313], "n": 2}` | frozen_return_value_or_array_contents | True |
| [MaxProduct/28](cases/MaxProduct/mutant_28/c/replay.json) | validated_violation | `{"arr": [2, 1313], "n": 2}` | frozen_return_value_or_array_contents | True |
| [MaxProduct/29](cases/MaxProduct/mutant_29/c/replay.json) | validated_violation | `{"arr": [2, 1313], "n": 2}` | frozen_return_value_or_array_contents | True |
| [MaxProduct/33](cases/MaxProduct/mutant_33/c/replay.json) | validated_violation | `{"arr": [0, 7, 0, 0, 0, 0, 0, 0, 0, 0], "n": 7}` | frozen_return_value_or_array_contents | True |
| [MaxProduct/35](cases/MaxProduct/mutant_35/c/replay.json) | validated_violation | `{"arr": [2, 1313], "n": 2}` | frozen_return_value_or_array_contents | True |
| [MaxProduct/36](cases/MaxProduct/mutant_36/c/replay.json) | validated_violation | `{"arr": [2, 1313], "n": 2}` | frozen_return_value_or_array_contents | True |
| [MaxProduct/4](cases/MaxProduct/mutant_4/c/replay.json) | validated_violation | `{"arr": [-1, 0, 0, 0, 0, 0], "n": 1}` | frozen_return_value_or_array_contents | True |
| [MaxProduct/2](cases/MaxProduct/mutant_2/c/replay.json) | validated_safety_failure | `{"arr": [0, -340, 0, 0, 0, 0, 0, 0, 0], "n": 6}` | runtime safety | True |
| [MaxSubArraySum/10](cases/MaxSubArraySum/mutant_10/c/replay.json) | validated_violation | `{"a": [-1, 0, 0, 437, 0, 0], "size": 5}` | frozen_return_value_or_array_contents | True |
| [MaxSubArraySum/12](cases/MaxSubArraySum/mutant_12/c/replay.json) | validated_violation | `{"a": [-1, 0, 0, 437, 0, 0], "size": 5}` | frozen_return_value_or_array_contents | True |
| [MaxProduct/6](cases/MaxProduct/mutant_6/c/replay.json) | validated_safety_failure | `{"arr": [0, -340, 0, 0, 0, 0, 0, 0, 0], "n": 6}` | runtime safety | True |
| [MaxSubArraySum/1](cases/MaxSubArraySum/mutant_1/c/replay.json) | validated_safety_failure | `{"a": [0, 0, 0, 0, 0, 0], "size": -1}` | runtime safety | True |
| [MaxProduct/31](cases/MaxProduct/mutant_31/c/replay.json) | validated_safety_failure | `{"arr": [0, -340, 0, 0, 0, 0, 0, 0, 0], "n": 6}` | runtime safety | True |
| [MaxSubArraySum/13](cases/MaxSubArraySum/mutant_13/c/replay.json) | validated_violation | `{"a": [-1, 0, 0, 437, 0, 0], "size": 5}` | frozen_return_value_or_array_contents | True |
| [MaxSubArraySum/14](cases/MaxSubArraySum/mutant_14/c/replay.json) | validated_violation | `{"a": [-1, 0, 0, 437, 0, 0], "size": 5}` | frozen_return_value_or_array_contents | True |
| [MaxSubArraySum/15](cases/MaxSubArraySum/mutant_15/c/replay.json) | validated_violation | `{"a": [-1, 0, 0, 437, 0, 0], "size": 5}` | frozen_return_value_or_array_contents | True |
| [MaxSubArraySum/16](cases/MaxSubArraySum/mutant_16/c/replay.json) | validated_violation | `{"a": [-1, 0, 0, 437, 0, 0], "size": 5}` | frozen_return_value_or_array_contents | True |
| [MaxSubArraySum/17](cases/MaxSubArraySum/mutant_17/c/replay.json) | validated_violation | `{"a": [-1, 0, 0, 437, 0, 0], "size": 5}` | frozen_return_value_or_array_contents | True |
| [MaxSubArraySum/18](cases/MaxSubArraySum/mutant_18/c/replay.json) | validated_violation | `{"a": [-1, 0, 0, 437, 0, 0], "size": 5}` | frozen_return_value_or_array_contents | True |
| [MaxSubArraySum/19](cases/MaxSubArraySum/mutant_19/c/replay.json) | validated_violation | `{"a": [-1, 0, 0, 437, 0, 0], "size": 5}` | frozen_return_value_or_array_contents | True |
| [MaxSubArraySum/20](cases/MaxSubArraySum/mutant_20/c/replay.json) | validated_violation | `{"a": [-1, 0, 0, 437, 0, 0], "size": 5}` | frozen_return_value_or_array_contents | True |
| [MaxSubArraySum/21](cases/MaxSubArraySum/mutant_21/c/replay.json) | validated_violation | `{"a": [-1, 0, 0, 437, 0, 0], "size": 5}` | frozen_return_value_or_array_contents | True |
| [MaxSubArraySum/22](cases/MaxSubArraySum/mutant_22/c/replay.json) | validated_violation | `{"a": [-1, 0, 0, 437, 0, 0], "size": 5}` | frozen_return_value_or_array_contents | True |
| [MaxSubArraySum/23](cases/MaxSubArraySum/mutant_23/c/replay.json) | validated_violation | `{"a": [-1, 0, 0, 437, 0, 0], "size": 5}` | frozen_return_value_or_array_contents | True |
| [MaxSubArraySum/24](cases/MaxSubArraySum/mutant_24/c/replay.json) | validated_violation | `{"a": [-1, 0, 0, 437, 0, 0], "size": 5}` | frozen_return_value_or_array_contents | True |
| [MaxSubArraySum/25](cases/MaxSubArraySum/mutant_25/c/replay.json) | validated_violation | `{"a": [-1, 0, 0, 437, 0, 0], "size": 5}` | frozen_return_value_or_array_contents | True |
| [MaxSubArraySum/26](cases/MaxSubArraySum/mutant_26/c/replay.json) | validated_violation | `{"a": [-1, 0, 0, 437, 0, 0], "size": 5}` | frozen_return_value_or_array_contents | True |
| [MaxSubArraySum/27](cases/MaxSubArraySum/mutant_27/c/replay.json) | validated_violation | `{"a": [-1, 0, 0, 437, 0, 0], "size": 5}` | frozen_return_value_or_array_contents | True |
| [MaxSubArraySum/4](cases/MaxSubArraySum/mutant_4/c/replay.json) | validated_violation | `{"a": [-1, 0, 0, 437, 0, 0], "size": 5}` | frozen_return_value_or_array_contents | True |
| [MaxSubArraySum/6](cases/MaxSubArraySum/mutant_6/c/replay.json) | validated_violation | `{"a": [-1, 0, 0, 437, 0, 0], "size": 5}` | frozen_return_value_or_array_contents | True |
| [MaxSubArraySum/7](cases/MaxSubArraySum/mutant_7/c/replay.json) | validated_violation | `{"a": [-1, 0, 0, 437, 0, 0], "size": 5}` | frozen_return_value_or_array_contents | True |
| [MaxSubArraySum/8](cases/MaxSubArraySum/mutant_8/c/replay.json) | validated_violation | `{"a": [-1, 0, 0, 437, 0, 0], "size": 5}` | frozen_return_value_or_array_contents | True |
| [MaxSubArraySum/9](cases/MaxSubArraySum/mutant_9/c/replay.json) | validated_violation | `{"a": [-1, 0, 0, 437, 0, 0], "size": 5}` | frozen_return_value_or_array_contents | True |
| [MaxSumOfThreeConsecutive/10](cases/MaxSumOfThreeConsecutive/mutant_10/c/replay.json) | validated_violation | `{"arr": [0, -340, 0, 0, 0, 0, 0, 0, 0], "n": 2}` | frozen_return_value_or_array_contents | True |
| [MaxSumOfThreeConsecutive/11](cases/MaxSumOfThreeConsecutive/mutant_11/c/replay.json) | validated_violation | `{"arr": [0, -340, 0, 0, 0, 0, 0, 0, 0], "n": 2}` | frozen_return_value_or_array_contents | True |
| [MaxSumOfThreeConsecutive/12](cases/MaxSumOfThreeConsecutive/mutant_12/c/replay.json) | validated_violation | `{"arr": [0, -340, 0, 0, 0, 0, 0, 0, 0], "n": 2}` | frozen_return_value_or_array_contents | True |
| [MaxSumOfThreeConsecutive/15](cases/MaxSumOfThreeConsecutive/mutant_15/c/replay.json) | validated_violation | `{"arr": [1, 0, 0, 0, 0, 0], "n": 3}` | frozen_return_value_or_array_contents | True |
| [MaxSumOfThreeConsecutive/16](cases/MaxSumOfThreeConsecutive/mutant_16/c/replay.json) | validated_violation | `{"arr": [-1, 0, 1], "n": 3}` | frozen_return_value_or_array_contents | True |
| [MaxSumOfThreeConsecutive/18](cases/MaxSumOfThreeConsecutive/mutant_18/c/replay.json) | validated_violation | `{"arr": [0, 0, -1, 0, 0, 0], "n": 3}` | frozen_return_value_or_array_contents | True |
| [MaxSumOfThreeConsecutive/19](cases/MaxSumOfThreeConsecutive/mutant_19/c/replay.json) | validated_violation | `{"arr": [-1, 0, 1], "n": 3}` | frozen_return_value_or_array_contents | True |
| [MaxSumOfThreeConsecutive/2](cases/MaxSumOfThreeConsecutive/mutant_2/c/replay.json) | validated_violation | `{"arr": [-1], "n": 1}` | frozen_return_value_or_array_contents | True |
| [MaxSumOfThreeConsecutive/20](cases/MaxSumOfThreeConsecutive/mutant_20/c/replay.json) | validated_violation | `{"arr": [3, 1, 2], "n": 3}` | frozen_return_value_or_array_contents | True |
| [MaxSumOfThreeConsecutive/22](cases/MaxSumOfThreeConsecutive/mutant_22/c/replay.json) | validated_violation | `{"arr": [0, 0, -1, 0, 0, 0], "n": 3}` | frozen_return_value_or_array_contents | True |
| [MaxSumOfThreeConsecutive/14](cases/MaxSumOfThreeConsecutive/mutant_14/c/replay.json) | validated_safety_failure | `{"arr": [0, -340, 0, 0, 0, 0, 0, 0, 0], "n": 2}` | runtime safety | True |
| [MaxSumOfThreeConsecutive/23](cases/MaxSumOfThreeConsecutive/mutant_23/c/replay.json) | validated_violation | `{"arr": [3, 1, 2], "n": 3}` | frozen_return_value_or_array_contents | True |
| [MaxSumOfThreeConsecutive/24](cases/MaxSumOfThreeConsecutive/mutant_24/c/replay.json) | validated_violation | `{"arr": [1, 0, 0, 0, 0, 0], "n": 3}` | frozen_return_value_or_array_contents | True |
| [MaxSumOfThreeConsecutive/13](cases/MaxSumOfThreeConsecutive/mutant_13/c/replay.json) | validated_safety_failure | `{"arr": [0, 0, 0, 0], "n": 1}` | runtime safety | True |
| [MaxSumOfThreeConsecutive/36](cases/MaxSumOfThreeConsecutive/mutant_36/c/replay.json) | validated_violation | `{"arr": [4, 3, 2, 1], "n": 4}` | frozen_return_value_or_array_contents | True |
| [MaxSumOfThreeConsecutive/4](cases/MaxSumOfThreeConsecutive/mutant_4/c/replay.json) | validated_violation | `{"arr": [-1], "n": 1}` | frozen_return_value_or_array_contents | True |
| [MaxSumOfThreeConsecutive/39](cases/MaxSumOfThreeConsecutive/mutant_39/c/replay.json) | validated_violation | `{"arr": [4, 3, 2, 1], "n": 4}` | frozen_return_value_or_array_contents | True |
| [MaxSumOfThreeConsecutive/44](cases/MaxSumOfThreeConsecutive/mutant_44/c/replay.json) | validated_violation | `{"arr": [-1, -1, -1, -1], "n": 4}` | frozen_return_value_or_array_contents | True |
| [MaxSumOfThreeConsecutive/47](cases/MaxSumOfThreeConsecutive/mutant_47/c/replay.json) | validated_violation | `{"arr": [-1, -1, -1, -1], "n": 4}` | frozen_return_value_or_array_contents | True |
| [MaxSumOfThreeConsecutive/52](cases/MaxSumOfThreeConsecutive/mutant_52/c/replay.json) | validated_violation | `{"arr": [-1, -1, -1, -1], "n": 4}` | frozen_return_value_or_array_contents | True |
| [MaxSumOfThreeConsecutive/55](cases/MaxSumOfThreeConsecutive/mutant_55/c/replay.json) | validated_violation | `{"arr": [-1, -1, -1, -1], "n": 4}` | frozen_return_value_or_array_contents | True |
| [MaxSumOfThreeConsecutive/57](cases/MaxSumOfThreeConsecutive/mutant_57/c/replay.json) | validated_violation | `{"arr": [0, -340, 0, 0, 0, 0, 0, 0, 0], "n": 2}` | frozen_return_value_or_array_contents | True |
| [MaxSumOfThreeConsecutive/25](cases/MaxSumOfThreeConsecutive/mutant_25/c/replay.json) | validated_safety_failure | `{"arr": [0, 0, 0, 0], "n": 1}` | runtime safety | True |
| [MaxSumOfThreeConsecutive/56](cases/MaxSumOfThreeConsecutive/mutant_56/c/replay.json) | validated_violation | `{"arr": [4, 3, 2, 1], "n": 4}` | frozen_return_value_or_array_contents | True |
| [MaxSumOfThreeConsecutive/6](cases/MaxSumOfThreeConsecutive/mutant_6/c/replay.json) | validated_violation | `{"arr": [0, -340, 0, 0, 0, 0, 0, 0, 0], "n": 2}` | frozen_return_value_or_array_contents | True |
| [MaxSumOfThreeConsecutive/26](cases/MaxSumOfThreeConsecutive/mutant_26/c/replay.json) | validated_safety_failure | `{"arr": [0, 0, -1, 0, 0, 0], "n": 3}` | runtime safety | True |
| [MaxSumOfThreeConsecutive/7](cases/MaxSumOfThreeConsecutive/mutant_7/c/replay.json) | validated_safety_failure | `{"arr": [0, 0, 0, 0], "n": 1}` | runtime safety | True |
| [MaxSumOfThreeConsecutive/8](cases/MaxSumOfThreeConsecutive/mutant_8/c/replay.json) | validated_violation | `{"arr": [0, -340, 0, 0, 0, 0, 0, 0, 0], "n": 2}` | frozen_return_value_or_array_contents | True |
| [MaxSumOfThreeConsecutive/9](cases/MaxSumOfThreeConsecutive/mutant_9/c/replay.json) | validated_violation | `{"arr": [0, -340, 0, 0, 0, 0, 0, 0, 0], "n": 2}` | frozen_return_value_or_array_contents | True |
| [MaxSumOfThreeConsecutive/59](cases/MaxSumOfThreeConsecutive/mutant_59/c/replay.json) | validated_safety_failure | `{"arr": [0, 0, -1, 0, 0, 0], "n": 3}` | runtime safety | True |
| [MaxSumOfThreeConsecutive/58](cases/MaxSumOfThreeConsecutive/mutant_58/c/replay.json) | validated_safety_failure | `{"arr": [0, 0, -1, 0, 0, 0], "n": 3}` | runtime safety | True |
| [MaxSumOfThreeConsecutive/60](cases/MaxSumOfThreeConsecutive/mutant_60/c/replay.json) | validated_safety_failure | `{"arr": [0, 0, -1, 0, 0, 0], "n": 3}` | runtime safety | True |
| [MaxSumSubseq/14](cases/MaxSumSubseq/mutant_14/c/replay.json) | validated_violation | `{"a": [-937, -1, 0]}` | frozen_return_value_or_array_contents | True |
| [MaxSumSubseq/10](cases/MaxSumSubseq/mutant_10/c/replay.json) | validated_safety_failure | `{"a": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | runtime safety | True |
| [MaxSumSubseq/15](cases/MaxSumSubseq/mutant_15/c/replay.json) | validated_violation | `{"a": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | frozen_return_value_or_array_contents | True |
| [MaxSumSubseq/16](cases/MaxSumSubseq/mutant_16/c/replay.json) | validated_violation | `{"a": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | frozen_return_value_or_array_contents | True |
| [MaxSumSubseq/18](cases/MaxSumSubseq/mutant_18/c/replay.json) | validated_violation | `{"a": [-937, -1, 0]}` | frozen_return_value_or_array_contents | True |
| [MaxSumSubseq/19](cases/MaxSumSubseq/mutant_19/c/replay.json) | validated_violation | `{"a": [-937, -1, 0]}` | frozen_return_value_or_array_contents | True |
| [MaxSumSubseq/11](cases/MaxSumSubseq/mutant_11/c/replay.json) | validated_safety_failure | `{"a": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | runtime safety | True |
| [MaxSumSubseq/12](cases/MaxSumSubseq/mutant_12/c/replay.json) | validated_safety_failure | `{"a": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | runtime safety | True |
| [MaxSumSubseq/2](cases/MaxSumSubseq/mutant_2/c/replay.json) | validated_violation | `{"a": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | frozen_return_value_or_array_contents | True |
| [MaxSumSubseq/21](cases/MaxSumSubseq/mutant_21/c/replay.json) | validated_violation | `{"a": [-937, -1, 0]}` | frozen_return_value_or_array_contents | True |
| [MaxSumSubseq/25](cases/MaxSumSubseq/mutant_25/c/replay.json) | validated_violation | `{"a": [-937, -1, 0]}` | frozen_return_value_or_array_contents | True |
| [MaxSumSubseq/26](cases/MaxSumSubseq/mutant_26/c/replay.json) | validated_violation | `{"a": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | frozen_return_value_or_array_contents | True |
| [MaxSumSubseq/20](cases/MaxSumSubseq/mutant_20/c/replay.json) | validated_safety_failure | `{"a": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | runtime safety | True |
| [MaxSumSubseq/23](cases/MaxSumSubseq/mutant_23/c/replay.json) | validated_safety_failure | `{"a": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | runtime safety | True |
| [MaxSumSubseq/24](cases/MaxSumSubseq/mutant_24/c/replay.json) | validated_safety_failure | `{"a": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | runtime safety | True |
| [MaxSumSubseq/3](cases/MaxSumSubseq/mutant_3/c/replay.json) | validated_safety_failure | `{"a": []}` | runtime safety | True |
| [MaxSumSubseq/27](cases/MaxSumSubseq/mutant_27/c/replay.json) | validated_safety_failure | `{"a": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | runtime safety | True |
| [MaxSumSubseq/30](cases/MaxSumSubseq/mutant_30/c/replay.json) | validated_violation | `{"a": [0, 1]}` | frozen_return_value_or_array_contents | True |
| [MaxSumSubseq/31](cases/MaxSumSubseq/mutant_31/c/replay.json) | validated_violation | `{"a": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | frozen_return_value_or_array_contents | True |
| [MaxSumSubseq/32](cases/MaxSumSubseq/mutant_32/c/replay.json) | validated_violation | `{"a": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | frozen_return_value_or_array_contents | True |
| [MaxSumSubseq/28](cases/MaxSumSubseq/mutant_28/c/replay.json) | validated_safety_failure | `{"a": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | runtime safety | True |
| [MaxSumSubseq/29](cases/MaxSumSubseq/mutant_29/c/replay.json) | validated_safety_failure | `{"a": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | runtime safety | True |
| [MaxSumSubseq/33](cases/MaxSumSubseq/mutant_33/c/replay.json) | validated_violation | `{"a": [0, 1]}` | frozen_return_value_or_array_contents | True |
| [MaxSumSubseq/34](cases/MaxSumSubseq/mutant_34/c/replay.json) | validated_violation | `{"a": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | frozen_return_value_or_array_contents | True |
| [MaxSumSubseq/6](cases/MaxSumSubseq/mutant_6/c/replay.json) | validated_violation | `{"a": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | frozen_return_value_or_array_contents | True |
| [MaxVolume/13](cases/MaxVolume/mutant_13/c/replay.json) | validated_violation | `{"s": 55}` | frozen_return_value_or_array_contents | True |
| [MaxSumSubseq/4](cases/MaxSumSubseq/mutant_4/c/replay.json) | validated_safety_failure | `{"a": []}` | runtime safety | True |
| [MaxVolume/15](cases/MaxVolume/mutant_15/c/replay.json) | validated_violation | `{"s": 55}` | frozen_return_value_or_array_contents | True |
| [MaxVolume/16](cases/MaxVolume/mutant_16/c/replay.json) | validated_violation | `{"s": 55}` | frozen_return_value_or_array_contents | True |
| [MaxVolume/17](cases/MaxVolume/mutant_17/c/replay.json) | validated_violation | `{"s": 55}` | frozen_return_value_or_array_contents | True |
| [MaxVolume/18](cases/MaxVolume/mutant_18/c/replay.json) | validated_violation | `{"s": 55}` | frozen_return_value_or_array_contents | True |
| [MaxVolume/19](cases/MaxVolume/mutant_19/c/replay.json) | validated_violation | `{"s": 55}` | frozen_return_value_or_array_contents | True |
| [MaxVolume/2](cases/MaxVolume/mutant_2/c/replay.json) | validated_violation | `{"s": 55}` | frozen_return_value_or_array_contents | True |
| [MaxVolume/20](cases/MaxVolume/mutant_20/c/replay.json) | validated_violation | `{"s": 55}` | frozen_return_value_or_array_contents | True |
| [MaxSumSubseq/9](cases/MaxSumSubseq/mutant_9/c/replay.json) | validated_safety_failure | `{"a": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | runtime safety | True |
| [MaxVolume/21](cases/MaxVolume/mutant_21/c/replay.json) | validated_violation | `{"s": 55}` | frozen_return_value_or_array_contents | True |
| [MaxVolume/22](cases/MaxVolume/mutant_22/c/replay.json) | validated_violation | `{"s": 55}` | frozen_return_value_or_array_contents | True |
| [MaxVolume/23](cases/MaxVolume/mutant_23/c/replay.json) | validated_violation | `{"s": 55}` | frozen_return_value_or_array_contents | True |
| [MaxVolume/24](cases/MaxVolume/mutant_24/c/replay.json) | validated_violation | `{"s": 55}` | frozen_return_value_or_array_contents | True |
| [MaxVolume/25](cases/MaxVolume/mutant_25/c/replay.json) | validated_violation | `{"s": 55}` | frozen_return_value_or_array_contents | True |
| [MaxVolume/26](cases/MaxVolume/mutant_26/c/replay.json) | validated_violation | `{"s": 55}` | frozen_return_value_or_array_contents | True |
| [MaxVolume/28](cases/MaxVolume/mutant_28/c/replay.json) | validated_violation | `{"s": 55}` | frozen_return_value_or_array_contents | True |
| [MaxVolume/29](cases/MaxVolume/mutant_29/c/replay.json) | validated_violation | `{"s": 55}` | frozen_return_value_or_array_contents | True |
| [MaxVolume/33](cases/MaxVolume/mutant_33/c/replay.json) | validated_violation | `{"s": 55}` | frozen_return_value_or_array_contents | True |
| [MaxVolume/31](cases/MaxVolume/mutant_31/c/replay.json) | validated_violation | `{"s": 55}` | frozen_return_value_or_array_contents | True |
| [MaxVolume/34](cases/MaxVolume/mutant_34/c/replay.json) | validated_violation | `{"s": 55}` | frozen_return_value_or_array_contents | True |
| [MaxVolume/7](cases/MaxVolume/mutant_7/c/replay.json) | validated_violation | `{"s": 55}` | frozen_return_value_or_array_contents | True |
| [MaxVolume/8](cases/MaxVolume/mutant_8/c/replay.json) | validated_violation | `{"s": 55}` | frozen_return_value_or_array_contents | True |
| [MaximumSegments/14](cases/MaximumSegments/mutant_14/c/replay.json) | validated_violation | `{"a": 1, "b": 2, "c": 2, "n": 1}` | frozen_return_value_or_array_contents | True |
| [MaximumSegments/16](cases/MaximumSegments/mutant_16/c/replay.json) | validated_violation | `{"a": 1, "b": 2, "c": 2, "n": 1}` | frozen_return_value_or_array_contents | True |
| [MaximumSegments/19](cases/MaximumSegments/mutant_19/c/replay.json) | validated_violation | `{"a": 2, "b": 2, "c": 2, "n": 3}` | frozen_return_value_or_array_contents | True |
| [MaximumSegments/12](cases/MaximumSegments/mutant_12/c/replay.json) | validated_safety_failure | `{"a": 2, "b": 1, "c": 1, "n": 1}` | runtime safety | True |
| [MaximumSegments/15](cases/MaximumSegments/mutant_15/c/replay.json) | validated_safety_failure | `{"a": 1, "b": 1, "c": 1, "n": 1}` | runtime safety | True |
| [MaximumSegments/22](cases/MaximumSegments/mutant_22/c/replay.json) | validated_violation | `{"a": 2, "b": 2, "c": 2, "n": 3}` | frozen_return_value_or_array_contents | True |
| [MaximumSegments/1](cases/MaximumSegments/mutant_1/c/replay.json) | validated_safety_failure | `{"a": 1, "b": 1, "c": 1, "n": 0}` | runtime safety | True |
| [MaximumSegments/25](cases/MaximumSegments/mutant_25/c/replay.json) | validated_violation | `{"a": 1, "b": 2, "c": 2, "n": 1}` | frozen_return_value_or_array_contents | True |
| [MaximumSegments/20](cases/MaximumSegments/mutant_20/c/replay.json) | validated_safety_failure | `{"a": 2, "b": 1, "c": 1, "n": 1}` | runtime safety | True |
| [MaximumSegments/2](cases/MaximumSegments/mutant_2/c/replay.json) | validated_safety_failure | `{"a": 1, "b": 1, "c": 1, "n": 0}` | runtime safety | True |
| [MaximumSegments/34](cases/MaximumSegments/mutant_34/c/replay.json) | validated_violation | `{"a": 2, "b": 1, "c": 2, "n": 1}` | frozen_return_value_or_array_contents | True |
| [MaximumSegments/23](cases/MaximumSegments/mutant_23/c/replay.json) | validated_safety_failure | `{"a": 2, "b": 1, "c": 1, "n": 1}` | runtime safety | True |
| [MaximumSegments/26](cases/MaximumSegments/mutant_26/c/replay.json) | validated_safety_failure | `{"a": 1, "b": 1, "c": 1, "n": 1}` | runtime safety | True |
| [MaximumSegments/37](cases/MaximumSegments/mutant_37/c/replay.json) | validated_violation | `{"a": 2, "b": 1, "c": 2, "n": 1}` | frozen_return_value_or_array_contents | True |
| [MaximumSegments/39](cases/MaximumSegments/mutant_39/c/replay.json) | validated_violation | `{"a": 2, "b": 1, "c": 2, "n": 1}` | frozen_return_value_or_array_contents | True |
| [MaximumSegments/38](cases/MaximumSegments/mutant_38/c/replay.json) | validated_safety_failure | `{"a": 1, "b": 1, "c": 1, "n": 1}` | runtime safety | True |
| [MaximumSegments/35](cases/MaximumSegments/mutant_35/c/replay.json) | validated_safety_failure | `{"a": 1, "b": 2, "c": 1, "n": 1}` | runtime safety | True |
| [MaximumSegments/40](cases/MaximumSegments/mutant_40/c/replay.json) | validated_violation | `{"a": 2, "b": 1, "c": 2, "n": 1}` | frozen_return_value_or_array_contents | True |
| [MaximumSegments/3](cases/MaximumSegments/mutant_3/c/replay.json) | validated_safety_failure | `{"a": 1, "b": 1, "c": 1, "n": 0}` | runtime safety | True |
| [MaximumSegments/42](cases/MaximumSegments/mutant_42/c/replay.json) | validated_violation | `{"a": 2, "b": 2, "c": 2, "n": 3}` | frozen_return_value_or_array_contents | True |
| [MaximumSegments/44](cases/MaximumSegments/mutant_44/c/replay.json) | validated_violation | `{"a": 2, "b": 1, "c": 2, "n": 1}` | frozen_return_value_or_array_contents | True |
| [MaximumSegments/45](cases/MaximumSegments/mutant_45/c/replay.json) | validated_violation | `{"a": 2, "b": 2, "c": 2, "n": 3}` | frozen_return_value_or_array_contents | True |
| [MaximumSegments/48](cases/MaximumSegments/mutant_48/c/replay.json) | validated_violation | `{"a": 1, "b": 1, "c": 1, "n": 1}` | frozen_return_value_or_array_contents | True |
| [MaximumSegments/4](cases/MaximumSegments/mutant_4/c/replay.json) | validated_safety_failure | `{"a": 1, "b": 1, "c": 1, "n": 0}` | runtime safety | True |
| [MaximumSegments/50](cases/MaximumSegments/mutant_50/c/replay.json) | validated_violation | `{"a": 1, "b": 1, "c": 1, "n": 1}` | frozen_return_value_or_array_contents | True |
| [MaximumSegments/51](cases/MaximumSegments/mutant_51/c/replay.json) | validated_violation | `{"a": 2, "b": 1, "c": 2, "n": 1}` | frozen_return_value_or_array_contents | True |
| [MaximumSegments/43](cases/MaximumSegments/mutant_43/c/replay.json) | validated_safety_failure | `{"a": 1, "b": 2, "c": 1, "n": 1}` | runtime safety | True |
| [MaximumSegments/52](cases/MaximumSegments/mutant_52/c/replay.json) | validated_violation | `{"a": 2, "b": 1, "c": 2, "n": 1}` | frozen_return_value_or_array_contents | True |
| [MaximumSegments/53](cases/MaximumSegments/mutant_53/c/replay.json) | validated_violation | `{"a": 2, "b": 1, "c": 2, "n": 1}` | frozen_return_value_or_array_contents | True |
| [MaximumSegments/54](cases/MaximumSegments/mutant_54/c/replay.json) | validated_violation | `{"a": 2, "b": 1, "c": 2, "n": 1}` | frozen_return_value_or_array_contents | True |
| [MaximumSegments/46](cases/MaximumSegments/mutant_46/c/replay.json) | validated_safety_failure | `{"a": 1, "b": 2, "c": 1, "n": 1}` | runtime safety | True |
| [MaximumSegments/55](cases/MaximumSegments/mutant_55/c/replay.json) | validated_violation | `{"a": 2, "b": 1, "c": 2, "n": 1}` | frozen_return_value_or_array_contents | True |
| [MaximumSegments/57](cases/MaximumSegments/mutant_57/c/replay.json) | validated_violation | `{"a": 2, "b": 2, "c": 1, "n": 1}` | frozen_return_value_or_array_contents | True |
| [MaximumSegments/49](cases/MaximumSegments/mutant_49/c/replay.json) | validated_safety_failure | `{"a": 1, "b": 1, "c": 1, "n": 1}` | runtime safety | True |
| [MaximumSegments/6](cases/MaximumSegments/mutant_6/c/replay.json) | validated_violation | `{"a": 1, "b": 1, "c": 1, "n": 1}` | frozen_return_value_or_array_contents | True |
| [MaximumSegments/60](cases/MaximumSegments/mutant_60/c/replay.json) | validated_violation | `{"a": 2, "b": 2, "c": 1, "n": 1}` | frozen_return_value_or_array_contents | True |
| [MaximumSegments/62](cases/MaximumSegments/mutant_62/c/replay.json) | validated_violation | `{"a": 2, "b": 2, "c": 1, "n": 1}` | frozen_return_value_or_array_contents | True |
| [MaximumSegments/63](cases/MaximumSegments/mutant_63/c/replay.json) | validated_violation | `{"a": 2, "b": 2, "c": 1, "n": 1}` | frozen_return_value_or_array_contents | True |
| [MaximumSegments/65](cases/MaximumSegments/mutant_65/c/replay.json) | validated_violation | `{"a": 2, "b": 2, "c": 2, "n": 3}` | frozen_return_value_or_array_contents | True |
| [MaximumSegments/67](cases/MaximumSegments/mutant_67/c/replay.json) | validated_violation | `{"a": 2, "b": 2, "c": 1, "n": 1}` | frozen_return_value_or_array_contents | True |
| [MaximumSegments/58](cases/MaximumSegments/mutant_58/c/replay.json) | validated_safety_failure | `{"a": 1, "b": 1, "c": 2, "n": 1}` | runtime safety | True |
| [MaximumSegments/61](cases/MaximumSegments/mutant_61/c/replay.json) | validated_safety_failure | `{"a": 1, "b": 1, "c": 1, "n": 1}` | runtime safety | True |
| [MaximumSegments/68](cases/MaximumSegments/mutant_68/c/replay.json) | validated_violation | `{"a": 2, "b": 2, "c": 2, "n": 3}` | frozen_return_value_or_array_contents | True |
| [MaximumSegments/7](cases/MaximumSegments/mutant_7/c/replay.json) | validated_violation | `{"a": 1, "b": 1, "c": 1, "n": 2}` | frozen_return_value_or_array_contents | True |
| [MaximumSegments/70](cases/MaximumSegments/mutant_70/c/replay.json) | validated_violation | `{"a": 2, "b": 2, "c": 1, "n": 2}` | frozen_return_value_or_array_contents | True |
| [MaximumSegments/71](cases/MaximumSegments/mutant_71/c/replay.json) | validated_violation | `{"a": 1, "b": 1, "c": 1, "n": 1}` | frozen_return_value_or_array_contents | True |
| [MaximumSegments/73](cases/MaximumSegments/mutant_73/c/replay.json) | validated_violation | `{"a": 1, "b": 1, "c": 1, "n": 1}` | frozen_return_value_or_array_contents | True |
| [MaximumSegments/66](cases/MaximumSegments/mutant_66/c/replay.json) | validated_safety_failure | `{"a": 1, "b": 1, "c": 2, "n": 1}` | runtime safety | True |
| [MaximumSegments/74](cases/MaximumSegments/mutant_74/c/replay.json) | validated_violation | `{"a": 2, "b": 2, "c": 1, "n": 1}` | frozen_return_value_or_array_contents | True |
| [MaximumSegments/75](cases/MaximumSegments/mutant_75/c/replay.json) | validated_violation | `{"a": 2, "b": 2, "c": 1, "n": 1}` | frozen_return_value_or_array_contents | True |
| [MaximumSegments/76](cases/MaximumSegments/mutant_76/c/replay.json) | validated_violation | `{"a": 2, "b": 2, "c": 1, "n": 1}` | frozen_return_value_or_array_contents | True |
| [MaximumSegments/77](cases/MaximumSegments/mutant_77/c/replay.json) | validated_violation | `{"a": 2, "b": 2, "c": 1, "n": 1}` | frozen_return_value_or_array_contents | True |
| [MaximumSegments/78](cases/MaximumSegments/mutant_78/c/replay.json) | validated_violation | `{"a": 2, "b": 2, "c": 1, "n": 1}` | frozen_return_value_or_array_contents | True |
| [MaximumSegments/9](cases/MaximumSegments/mutant_9/c/replay.json) | validated_violation | `{"a": 2, "b": 2, "c": 2, "n": 1}` | frozen_return_value_or_array_contents | True |
| [MinCoins/1](cases/MinCoins/mutant_1/c/replay.json) | validated_violation | `{"coins": null, "m": -1, "v": -1}` | frozen_return_value_or_array_contents | True |
| [MaximumSegments/69](cases/MaximumSegments/mutant_69/c/replay.json) | validated_safety_failure | `{"a": 1, "b": 1, "c": 2, "n": 1}` | runtime safety | True |
| [MinCoins/12](cases/MinCoins/mutant_12/c/replay.json) | validated_violation | `{"coins": [1], "m": 1, "v": 2}` | frozen_return_value_or_array_contents | True |
| [MaximumSegments/72](cases/MaximumSegments/mutant_72/c/replay.json) | validated_safety_failure | `{"a": 1, "b": 1, "c": 1, "n": 1}` | runtime safety | True |
| [MinCoins/10](cases/MinCoins/mutant_10/c/replay.json) | validated_violation | `{"coins": [1], "m": 0, "v": 1}` | frozen_return_value_or_array_contents | True |
| [MinCoins/13](cases/MinCoins/mutant_13/c/replay.json) | validated_violation | `{"coins": [2], "m": 1, "v": 2}` | frozen_return_value_or_array_contents | True |
| [MinCoins/2](cases/MinCoins/mutant_2/c/replay.json) | validated_violation | `{"coins": [], "m": 0, "v": 1}` | frozen_return_value_or_array_contents | True |
| [MinCoins/16](cases/MinCoins/mutant_16/c/replay.json) | validated_violation | `{"coins": [1], "m": 1, "v": 2}` | frozen_return_value_or_array_contents | True |
| [MinCoins/15](cases/MinCoins/mutant_15/c/replay.json) | validated_violation | `{"coins": [2], "m": 1, "v": 1}` | frozen_return_value_or_array_contents | True |
| [MinCoins/3](cases/MinCoins/mutant_3/c/replay.json) | validated_violation | `{"coins": [], "m": 0, "v": 0}` | frozen_return_value_or_array_contents | True |
| [MinCoins/4](cases/MinCoins/mutant_4/c/replay.json) | validated_violation | `{"coins": [], "m": 0, "v": 0}` | frozen_return_value_or_array_contents | True |
| [MinCoins/21](cases/MinCoins/mutant_21/c/replay.json) | validated_violation | `{"coins": [2], "m": 1, "v": 3}` | frozen_return_value_or_array_contents | True |
| [MinCoins/5](cases/MinCoins/mutant_5/c/replay.json) | validated_violation | `{"coins": [1], "m": 1, "v": 1}` | frozen_return_value_or_array_contents | True |
| [MinCoins/8](cases/MinCoins/mutant_8/c/replay.json) | validated_violation | `{"coins": [-1], "m": 1, "v": -1}` | frozen_return_value_or_array_contents | True |
| [MinCoins/7](cases/MinCoins/mutant_7/c/replay.json) | validated_violation | `{"coins": [-1], "m": 1, "v": -1}` | frozen_return_value_or_array_contents | True |
| [MinCost/14](cases/MinCost/mutant_14/c/replay.json) | validated_violation | `{"cost": [[1, 2], [3, 4]], "m": 1, "n": 0}` | frozen_return_value_or_array_contents | True |
| [MinCost/11](cases/MinCost/mutant_11/c/replay.json) | validated_violation | `{"cost": [[2, -1], [4, 3], [0, 0]], "m": 2, "n": 0}` | frozen_return_value_or_array_contents | True |
| [MinCost/16](cases/MinCost/mutant_16/c/replay.json) | validated_violation | `{"cost": [[1, 2], [3, 4]], "m": 1, "n": 0}` | frozen_return_value_or_array_contents | True |
| [MinCost/17](cases/MinCost/mutant_17/c/replay.json) | validated_violation | `{"cost": [[1, 2], [3, 4]], "m": 1, "n": 0}` | frozen_return_value_or_array_contents | True |
| [MinCost/18](cases/MinCost/mutant_18/c/replay.json) | validated_violation | `{"cost": [[1, 2], [3, 4]], "m": 1, "n": 0}` | frozen_return_value_or_array_contents | True |
| [MinCost/19](cases/MinCost/mutant_19/c/replay.json) | validated_violation | `{"cost": [[1, 2], [3, 4]], "m": 1, "n": 0}` | frozen_return_value_or_array_contents | True |
| [MinCost/15](cases/MinCost/mutant_15/c/replay.json) | validated_safety_failure | `{"cost": [[0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0]], "m": 4, "n": 4}` | runtime safety | True |
| [MinCost/20](cases/MinCost/mutant_20/c/replay.json) | validated_violation | `{"cost": [[1, 2], [3, 4]], "m": 1, "n": 0}` | frozen_return_value_or_array_contents | True |
| [MinCost/21](cases/MinCost/mutant_21/c/replay.json) | validated_violation | `{"cost": [[1, 2], [3, 4]], "m": 1, "n": 0}` | frozen_return_value_or_array_contents | True |
| [MinCost/23](cases/MinCost/mutant_23/c/replay.json) | validated_violation | `{"cost": [[4, 0, 4]], "m": 0, "n": 2}` | frozen_return_value_or_array_contents | True |
| [MinCost/29](cases/MinCost/mutant_29/c/replay.json) | validated_violation | `{"cost": [[1, 2]], "m": 0, "n": 1}` | frozen_return_value_or_array_contents | True |
| [MinCost/2](cases/MinCost/mutant_2/c/replay.json) | validated_safety_failure | `{"cost": [[0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0]], "m": 4, "n": 4}` | runtime safety | True |
| [MinCost/30](cases/MinCost/mutant_30/c/replay.json) | validated_violation | `{"cost": [[1, 2]], "m": 0, "n": 1}` | frozen_return_value_or_array_contents | True |
| [MinCost/31](cases/MinCost/mutant_31/c/replay.json) | validated_violation | `{"cost": [[1, 2]], "m": 0, "n": 1}` | frozen_return_value_or_array_contents | True |
| [MinCost/32](cases/MinCost/mutant_32/c/replay.json) | validated_violation | `{"cost": [[1, 2]], "m": 0, "n": 1}` | frozen_return_value_or_array_contents | True |
| [MinCost/27](cases/MinCost/mutant_27/c/replay.json) | validated_safety_failure | `{"cost": [[0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0]], "m": 4, "n": 4}` | runtime safety | True |
| [MinCost/33](cases/MinCost/mutant_33/c/replay.json) | validated_violation | `{"cost": [[1, 2]], "m": 0, "n": 1}` | frozen_return_value_or_array_contents | True |
| [MinCost/34](cases/MinCost/mutant_34/c/replay.json) | validated_violation | `{"cost": [[1, 2], [3, 4]], "m": 1, "n": 1}` | frozen_return_value_or_array_contents | True |
| [MinCost/35](cases/MinCost/mutant_35/c/replay.json) | validated_violation | `{"cost": [[2, -1], [4, 3], [0, 0]], "m": 2, "n": 1}` | frozen_return_value_or_array_contents | True |
| [MinCost/37](cases/MinCost/mutant_37/c/replay.json) | validated_violation | `{"cost": [[1, 2], [3, 4]], "m": 1, "n": 1}` | frozen_return_value_or_array_contents | True |
| [MinCost/38](cases/MinCost/mutant_38/c/replay.json) | validated_violation | `{"cost": [[-1, -1, 0], [2, -2, 1]], "m": 1, "n": 2}` | frozen_return_value_or_array_contents | True |
| [MinCost/3](cases/MinCost/mutant_3/c/replay.json) | validated_safety_failure | `{"cost": [[0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0]], "m": 4, "n": 4}` | runtime safety | True |
| [MinCost/39](cases/MinCost/mutant_39/c/replay.json) | validated_safety_failure | `{"cost": [[0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0]], "m": 4, "n": 4}` | runtime safety | True |
| [MinCost/42](cases/MinCost/mutant_42/c/replay.json) | validated_safety_failure | `{"cost": [[0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0]], "m": 4, "n": 4}` | runtime safety | True |
| [MinCost/49](cases/MinCost/mutant_49/c/replay.json) | validated_violation | `{"cost": [[1, 2], [3, 4]], "m": 1, "n": 1}` | frozen_return_value_or_array_contents | True |
| [MinCost/4](cases/MinCost/mutant_4/c/replay.json) | validated_safety_failure | `{"cost": [[0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0]], "m": 4, "n": 4}` | runtime safety | True |
| [MinCost/46](cases/MinCost/mutant_46/c/replay.json) | validated_safety_failure | `{"cost": [[0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0]], "m": 4, "n": 4}` | runtime safety | True |
| [MinCost/51](cases/MinCost/mutant_51/c/replay.json) | validated_violation | `{"cost": [[1, 2], [3, 4]], "m": 1, "n": 1}` | frozen_return_value_or_array_contents | True |
| [MinCost/53](cases/MinCost/mutant_53/c/replay.json) | validated_violation | `{"cost": [[1, 2], [3, 4]], "m": 1, "n": 1}` | frozen_return_value_or_array_contents | True |
| [MinCost/50](cases/MinCost/mutant_50/c/replay.json) | validated_safety_failure | `{"cost": [[0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0]], "m": 4, "n": 4}` | runtime safety | True |
| [MinCost/55](cases/MinCost/mutant_55/c/replay.json) | validated_violation | `{"cost": [[1, 2], [3, 4]], "m": 1, "n": 1}` | frozen_return_value_or_array_contents | True |
| [MinCost/56](cases/MinCost/mutant_56/c/replay.json) | validated_violation | `{"cost": [[1, 2], [3, 4]], "m": 1, "n": 1}` | frozen_return_value_or_array_contents | True |
| [MinCost/57](cases/MinCost/mutant_57/c/replay.json) | validated_violation | `{"cost": [[1, 2], [3, 4]], "m": 1, "n": 1}` | frozen_return_value_or_array_contents | True |
| [MinCost/5](cases/MinCost/mutant_5/c/replay.json) | validated_safety_failure | `{"cost": [[0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0]], "m": 4, "n": 4}` | runtime safety | True |
| [MinCost/59](cases/MinCost/mutant_59/c/replay.json) | validated_violation | `{"cost": [[1, 2], [3, 4]], "m": 1, "n": 1}` | frozen_return_value_or_array_contents | True |
| [MinCost/54](cases/MinCost/mutant_54/c/replay.json) | validated_safety_failure | `{"cost": [[0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0]], "m": 4, "n": 4}` | runtime safety | True |
| [MinCost/60](cases/MinCost/mutant_60/c/replay.json) | validated_violation | `{"cost": [[1, 2], [3, 4]], "m": 1, "n": 1}` | frozen_return_value_or_array_contents | True |
| [MinCoins/14](cases/MinCoins/mutant_14/c/replay.json) | validated_safety_failure | `{"coins": [1], "m": 1, "v": 1}` | runtime safety | True |
| [MinCost/9](cases/MinCost/mutant_9/c/replay.json) | validated_violation | `{"cost": [[1, 2]], "m": 0, "n": 0}` | frozen_return_value_or_array_contents | True |
| [MinJumps/1](cases/MinJumps/mutant_1/c/replay.json) | validated_violation | `{"arr": [0, 2, 0, 0, 0, 0, 0, 0], "n": 9}` | frozen_return_value_or_array_contents | True |
| [MinCost/6](cases/MinCost/mutant_6/c/replay.json) | validated_safety_failure | `{"cost": [[0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0]], "m": 4, "n": 4}` | runtime safety | True |
| [MinCost/7](cases/MinCost/mutant_7/c/replay.json) | validated_safety_failure | `{"cost": [[0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0]], "m": 4, "n": 4}` | runtime safety | True |
| [MinJumps/13](cases/MinJumps/mutant_13/c/replay.json) | validated_violation | `{"arr": [0, 0, 0, 0, 15, 0, 0, 0], "n": 9}` | frozen_return_value_or_array_contents | True |
| [MinCost/8](cases/MinCost/mutant_8/c/replay.json) | validated_safety_failure | `{"cost": [[0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0]], "m": 4, "n": 4}` | runtime safety | True |
| [MinJumps/15](cases/MinJumps/mutant_15/c/replay.json) | validated_violation | `{"arr": [0, 2, 0, 0, 0, 0, 0, 0], "n": 9}` | frozen_return_value_or_array_contents | True |
| [MinJumps/16](cases/MinJumps/mutant_16/c/replay.json) | validated_violation | `{"arr": [0, 0, 0, 0, 15, 0, 0, 0], "n": 9}` | frozen_return_value_or_array_contents | True |
| [MinJumps/17](cases/MinJumps/mutant_17/c/replay.json) | validated_violation | `{"arr": [0, 0, 0, 0, 15, 0, 0, 0], "n": 9}` | frozen_return_value_or_array_contents | True |
| [MinJumps/18](cases/MinJumps/mutant_18/c/replay.json) | validated_violation | `{"arr": [0, 0, 0, 0, 15, 0, 0, 0], "n": 9}` | frozen_return_value_or_array_contents | True |
| [MinJumps/19](cases/MinJumps/mutant_19/c/replay.json) | validated_violation | `{"arr": [0, 0, 0, 0, 15, 0, 0, 0], "n": 9}` | frozen_return_value_or_array_contents | True |
| [MinJumps/2](cases/MinJumps/mutant_2/c/replay.json) | validated_violation | `{"arr": null, "n": 1}` | frozen_return_value_or_array_contents | True |
| [MinJumps/20](cases/MinJumps/mutant_20/c/replay.json) | validated_violation | `{"arr": [0, 0, 0, 0, 15, 0, 0, 0], "n": 9}` | frozen_return_value_or_array_contents | True |
| [MinJumps/21](cases/MinJumps/mutant_21/c/replay.json) | validated_violation | `{"arr": [0, 2, 0, 0, 0, 0, 0, 0], "n": 9}` | frozen_return_value_or_array_contents | True |
| [MinJumps/22](cases/MinJumps/mutant_22/c/replay.json) | validated_safety_failure | `{"arr": [0, 2, 0, 0, 0, 0, 0, 0], "n": 9}` | runtime safety | True |
| [MinJumps/23](cases/MinJumps/mutant_23/c/replay.json) | validated_safety_failure | `{"arr": [0, 2, 0, 0, 0, 0, 0, 0], "n": 9}` | runtime safety | True |
| [MinJumps/24](cases/MinJumps/mutant_24/c/replay.json) | validated_safety_failure | `{"arr": [0, 2, 0, 0, 0, 0, 0, 0], "n": 9}` | runtime safety | True |
| [MinJumps/7](cases/MinJumps/mutant_7/c/replay.json) | validated_violation | `{"arr": [0, 1], "n": 2}` | frozen_return_value_or_array_contents | True |
| [MoveFirst/1](cases/MoveFirst/mutant_1/c/replay.json) | validated_safety_failure | `{"testArray": null}` | runtime safety | True |
| [MinJumps/4](cases/MinJumps/mutant_4/c/replay.json) | validated_safety_failure | `{"arr": [0, 2, 0, 0, 0, 0, 0, 0], "n": 9}` | runtime safety | True |
| [MoveFirst/10](cases/MoveFirst/mutant_10/c/replay.json) | validated_safety_failure | `{"testArray": [0, 0, 0]}` | runtime safety | True |
| [MoveFirst/11](cases/MoveFirst/mutant_11/c/replay.json) | validated_safety_failure | `{"testArray": [0, 0, 0]}` | runtime safety | True |
| [MoveFirst/12](cases/MoveFirst/mutant_12/c/replay.json) | validated_safety_failure | `{"testArray": [0, 0, 0]}` | runtime safety | True |
| [MoveFirst/15](cases/MoveFirst/mutant_15/c/replay.json) | validated_safety_failure | `{"testArray": [0, 0, 0]}` | runtime safety | True |
| [MoveFirst/16](cases/MoveFirst/mutant_16/c/replay.json) | validated_safety_failure | `{"testArray": [0, 0, 0]}` | runtime safety | True |
| [MoveFirst/3](cases/MoveFirst/mutant_3/c/replay.json) | validated_safety_failure | `{"testArray": []}` | runtime safety | True |
| [MoveFirst/17](cases/MoveFirst/mutant_17/c/replay.json) | validated_safety_failure | `{"testArray": [0, 0, 0]}` | runtime safety | True |
| [MoveFirst/4](cases/MoveFirst/mutant_4/c/replay.json) | validated_safety_failure | `{"testArray": null}` | runtime safety | True |
| [MoveFirst/5](cases/MoveFirst/mutant_5/c/replay.json) | validated_safety_failure | `{"testArray": []}` | runtime safety | True |
| [MoveFirst/6](cases/MoveFirst/mutant_6/c/replay.json) | validated_safety_failure | `{"testArray": null}` | runtime safety | True |
| [MoveFirst/8](cases/MoveFirst/mutant_8/c/replay.json) | validated_safety_failure | `{"testArray": []}` | runtime safety | True |
| [MultiplyElements/1](cases/MultiplyElements/mutant_1/c/replay.json) | validated_violation | `{"testTup": [0, 2, 0]}` | frozen_return_value_or_array_contents | True |
| [MultiplyElements/10](cases/MultiplyElements/mutant_10/c/replay.json) | validated_safety_failure | `{"testTup": [0, 2, 0]}` | runtime safety | True |
| [MultiplyElements/11](cases/MultiplyElements/mutant_11/c/replay.json) | validated_safety_failure | `{"testTup": [0, 2, 0]}` | runtime safety | True |
| [MultiplyElements/12](cases/MultiplyElements/mutant_12/c/replay.json) | validated_safety_failure | `{"testTup": [0, 2, 0]}` | runtime safety | True |
| [MultiplyElements/14](cases/MultiplyElements/mutant_14/c/replay.json) | validated_safety_failure | `{"testTup": [0, 2, 0]}` | runtime safety | True |
| [MultiplyElements/17](cases/MultiplyElements/mutant_17/c/replay.json) | validated_violation | `{"testTup": [0, 2, 0]}` | frozen_return_value_or_array_contents | True |
| [MultiplyElements/19](cases/MultiplyElements/mutant_19/c/replay.json) | validated_violation | `{"testTup": [0, 2, 0]}` | frozen_return_value_or_array_contents | True |
| [MultiplyElements/2](cases/MultiplyElements/mutant_2/c/replay.json) | validated_violation | `{"testTup": [1, 0]}` | frozen_return_value_or_array_contents | True |
| [MultiplyElements/20](cases/MultiplyElements/mutant_20/c/replay.json) | validated_violation | `{"testTup": [2, 1]}` | frozen_return_value_or_array_contents | True |
| [MultiplyElements/21](cases/MultiplyElements/mutant_21/c/replay.json) | validated_violation | `{"testTup": [0, 2, 0]}` | frozen_return_value_or_array_contents | True |
| [MultiplyElements/22](cases/MultiplyElements/mutant_22/c/replay.json) | validated_violation | `{"testTup": [0, 2, 0]}` | frozen_return_value_or_array_contents | True |
| [MultiplyElements/23](cases/MultiplyElements/mutant_23/c/replay.json) | validated_violation | `{"testTup": [3, 1, 2]}` | frozen_return_value_or_array_contents | True |
| [MultiplyElements/3](cases/MultiplyElements/mutant_3/c/replay.json) | validated_safety_failure | `{"testTup": []}` | runtime safety | True |
| [MultiplyElements/4](cases/MultiplyElements/mutant_4/c/replay.json) | validated_safety_failure | `{"testTup": []}` | runtime safety | True |
| [MultiplyElements/18](cases/MultiplyElements/mutant_18/c/replay.json) | validated_safety_failure | `{"testTup": [0, 2, 0]}` | runtime safety | True |
| [MultiplyElements/6](cases/MultiplyElements/mutant_6/c/replay.json) | validated_violation | `{"testTup": [0, 2, 0]}` | frozen_return_value_or_array_contents | True |
| [MultiplyElements/7](cases/MultiplyElements/mutant_7/c/replay.json) | validated_violation | `{"testTup": [0, 2, 0]}` | frozen_return_value_or_array_contents | True |
| [MultiplyElements/8](cases/MultiplyElements/mutant_8/c/replay.json) | validated_violation | `{"testTup": [0, 2, 0]}` | frozen_return_value_or_array_contents | True |
| [NewmanPrime/11](cases/NewmanPrime/mutant_11/c/replay.json) | validated_violation | `{"n": 3}` | frozen_return_value_or_array_contents | True |
| [NewmanPrime/10](cases/NewmanPrime/mutant_10/c/replay.json) | validated_safety_failure | `{"n": 0}` | runtime safety | True |
| [NewmanPrime/12](cases/NewmanPrime/mutant_12/c/replay.json) | validated_safety_failure | `{"n": 2}` | runtime safety | True |
| [MultiplyElements/5](cases/MultiplyElements/mutant_5/c/replay.json) | validated_safety_failure | `{"testTup": [0, 2, 0]}` | runtime safety | True |
| [NewmanPrime/13](cases/NewmanPrime/mutant_13/c/replay.json) | validated_safety_failure | `{"n": 2}` | runtime safety | True |
| [NewmanPrime/19](cases/NewmanPrime/mutant_19/c/replay.json) | validated_violation | `{"n": 4}` | frozen_return_value_or_array_contents | True |
| [NewmanPrime/14](cases/NewmanPrime/mutant_14/c/replay.json) | validated_safety_failure | `{"n": 2}` | runtime safety | True |
| [NewmanPrime/2](cases/NewmanPrime/mutant_2/c/replay.json) | validated_violation | `{"n": 2}` | frozen_return_value_or_array_contents | True |
| [NewmanPrime/20](cases/NewmanPrime/mutant_20/c/replay.json) | validated_safety_failure | `{"n": 2}` | runtime safety | True |
| [NewmanPrime/22](cases/NewmanPrime/mutant_22/c/replay.json) | validated_violation | `{"n": 7}` | frozen_return_value_or_array_contents | True |
| [NewmanPrime/21](cases/NewmanPrime/mutant_21/c/replay.json) | validated_safety_failure | `{"n": 2}` | runtime safety | True |
| [NewmanPrime/23](cases/NewmanPrime/mutant_23/c/replay.json) | validated_violation | `{"n": 2}` | frozen_return_value_or_array_contents | True |
| [NewmanPrime/5](cases/NewmanPrime/mutant_5/c/replay.json) | validated_violation | `{"n": 2}` | frozen_return_value_or_array_contents | True |
| [NewmanPrime/3](cases/NewmanPrime/mutant_3/c/replay.json) | validated_safety_failure | `{"n": 0}` | runtime safety | True |
| [NewmanPrime/6](cases/NewmanPrime/mutant_6/c/replay.json) | validated_safety_failure | `{"n": 1}` | runtime safety | True |
| [NewmanPrime/7](cases/NewmanPrime/mutant_7/c/replay.json) | validated_safety_failure | `{"n": 1}` | runtime safety | True |
| [NewmanPrime/9](cases/NewmanPrime/mutant_9/c/replay.json) | validated_violation | `{"n": 2}` | frozen_return_value_or_array_contents | True |
| [NewmanPrime/8](cases/NewmanPrime/mutant_8/c/replay.json) | validated_safety_failure | `{"n": 0}` | runtime safety | True |
| [NextPowerOf2/2](cases/NextPowerOf2/mutant_2/c/replay.json) | validated_violation | `{"n": 4096}` | frozen_return_value_or_array_contents | True |
| [NextPowerOf2/6](cases/NextPowerOf2/mutant_6/c/replay.json) | validated_violation | `{"n": 4096}` | frozen_return_value_or_array_contents | True |
| [NoOfCubes/1](cases/NoOfCubes/mutant_1/c/replay.json) | validated_violation | `{"k": 462, "n": 1}` | frozen_return_value_or_array_contents | True |
| [NoOfCubes/10](cases/NoOfCubes/mutant_10/c/replay.json) | validated_violation | `{"k": 462, "n": 1}` | frozen_return_value_or_array_contents | True |
| [NoOfCubes/11](cases/NoOfCubes/mutant_11/c/replay.json) | validated_violation | `{"k": 462, "n": 1}` | frozen_return_value_or_array_contents | True |
| [NoOfCubes/12](cases/NoOfCubes/mutant_12/c/replay.json) | validated_violation | `{"k": 462, "n": 1}` | frozen_return_value_or_array_contents | True |
| [NoOfCubes/13](cases/NoOfCubes/mutant_13/c/replay.json) | validated_violation | `{"k": 0, "n": 0}` | frozen_return_value_or_array_contents | True |
| [NoOfCubes/14](cases/NoOfCubes/mutant_14/c/replay.json) | validated_violation | `{"k": 0, "n": 0}` | frozen_return_value_or_array_contents | True |
| [NoOfCubes/15](cases/NoOfCubes/mutant_15/c/replay.json) | validated_violation | `{"k": 0, "n": 0}` | frozen_return_value_or_array_contents | True |
| [NoOfCubes/16](cases/NoOfCubes/mutant_16/c/replay.json) | validated_violation | `{"k": 0, "n": 0}` | frozen_return_value_or_array_contents | True |
| [NoOfCubes/17](cases/NoOfCubes/mutant_17/c/replay.json) | validated_violation | `{"k": 0, "n": 0}` | frozen_return_value_or_array_contents | True |
| [NoOfCubes/18](cases/NoOfCubes/mutant_18/c/replay.json) | validated_violation | `{"k": 0, "n": 0}` | frozen_return_value_or_array_contents | True |
| [NoOfCubes/19](cases/NoOfCubes/mutant_19/c/replay.json) | validated_violation | `{"k": 0, "n": 0}` | frozen_return_value_or_array_contents | True |
| [NoOfCubes/2](cases/NoOfCubes/mutant_2/c/replay.json) | validated_violation | `{"k": 462, "n": 1}` | frozen_return_value_or_array_contents | True |
| [NoOfCubes/20](cases/NoOfCubes/mutant_20/c/replay.json) | validated_violation | `{"k": 462, "n": 1}` | frozen_return_value_or_array_contents | True |
| [NoOfCubes/21](cases/NoOfCubes/mutant_21/c/replay.json) | validated_violation | `{"k": 462, "n": 1}` | frozen_return_value_or_array_contents | True |
| [NoOfCubes/22](cases/NoOfCubes/mutant_22/c/replay.json) | validated_violation | `{"k": 462, "n": 1}` | frozen_return_value_or_array_contents | True |
| [NoOfCubes/23](cases/NoOfCubes/mutant_23/c/replay.json) | validated_violation | `{"k": 462, "n": 1}` | frozen_return_value_or_array_contents | True |
| [NoOfCubes/24](cases/NoOfCubes/mutant_24/c/replay.json) | validated_violation | `{"k": 462, "n": 1}` | frozen_return_value_or_array_contents | True |
| [NoOfCubes/25](cases/NoOfCubes/mutant_25/c/replay.json) | validated_violation | `{"k": 0, "n": 0}` | frozen_return_value_or_array_contents | True |
| [NoOfCubes/26](cases/NoOfCubes/mutant_26/c/replay.json) | validated_violation | `{"k": 0, "n": 0}` | frozen_return_value_or_array_contents | True |
| [NoOfCubes/27](cases/NoOfCubes/mutant_27/c/replay.json) | validated_violation | `{"k": 0, "n": 0}` | frozen_return_value_or_array_contents | True |
| [NoOfCubes/28](cases/NoOfCubes/mutant_28/c/replay.json) | validated_violation | `{"k": 0, "n": 0}` | frozen_return_value_or_array_contents | True |
| [NoOfCubes/29](cases/NoOfCubes/mutant_29/c/replay.json) | validated_violation | `{"k": 0, "n": 0}` | frozen_return_value_or_array_contents | True |
| [NoOfCubes/3](cases/NoOfCubes/mutant_3/c/replay.json) | validated_violation | `{"k": 462, "n": 1}` | frozen_return_value_or_array_contents | True |
| [NoOfCubes/30](cases/NoOfCubes/mutant_30/c/replay.json) | validated_violation | `{"k": 0, "n": 0}` | frozen_return_value_or_array_contents | True |
| [NoOfCubes/31](cases/NoOfCubes/mutant_31/c/replay.json) | validated_violation | `{"k": 0, "n": 0}` | frozen_return_value_or_array_contents | True |
| [NoOfCubes/32](cases/NoOfCubes/mutant_32/c/replay.json) | validated_violation | `{"k": 462, "n": 1}` | frozen_return_value_or_array_contents | True |
| [NoOfCubes/33](cases/NoOfCubes/mutant_33/c/replay.json) | validated_violation | `{"k": 0, "n": 0}` | frozen_return_value_or_array_contents | True |
| [NoOfCubes/4](cases/NoOfCubes/mutant_4/c/replay.json) | validated_violation | `{"k": 462, "n": 1}` | frozen_return_value_or_array_contents | True |
| [NoOfCubes/5](cases/NoOfCubes/mutant_5/c/replay.json) | validated_violation | `{"k": 0, "n": 0}` | frozen_return_value_or_array_contents | True |
| [NoOfCubes/6](cases/NoOfCubes/mutant_6/c/replay.json) | validated_violation | `{"k": 0, "n": 0}` | frozen_return_value_or_array_contents | True |
| [NoOfCubes/7](cases/NoOfCubes/mutant_7/c/replay.json) | validated_violation | `{"k": 0, "n": 0}` | frozen_return_value_or_array_contents | True |
| [NoOfCubes/8](cases/NoOfCubes/mutant_8/c/replay.json) | validated_violation | `{"k": 0, "n": 0}` | frozen_return_value_or_array_contents | True |
| [NoOfCubes/9](cases/NoOfCubes/mutant_9/c/replay.json) | validated_violation | `{"k": 462, "n": 1}` | frozen_return_value_or_array_contents | True |
| [OddBitSetNumber/1](cases/OddBitSetNumber/mutant_1/c/replay.json) | validated_violation | `{"n": 0}` | frozen_return_value_or_array_contents | True |
| [OddBitSetNumber/10](cases/OddBitSetNumber/mutant_10/c/replay.json) | validated_violation | `{"n": 2125}` | frozen_return_value_or_array_contents | True |
| [OddBitSetNumber/12](cases/OddBitSetNumber/mutant_12/c/replay.json) | validated_violation | `{"n": 2125}` | frozen_return_value_or_array_contents | True |
| [OddBitSetNumber/13](cases/OddBitSetNumber/mutant_13/c/replay.json) | validated_violation | `{"n": 0}` | frozen_return_value_or_array_contents | True |
| [OddBitSetNumber/14](cases/OddBitSetNumber/mutant_14/c/replay.json) | validated_violation | `{"n": 0}` | frozen_return_value_or_array_contents | True |
| [OddBitSetNumber/18](cases/OddBitSetNumber/mutant_18/c/replay.json) | validated_violation | `{"n": 0}` | frozen_return_value_or_array_contents | True |
| [OddBitSetNumber/19](cases/OddBitSetNumber/mutant_19/c/replay.json) | validated_violation | `{"n": 0}` | frozen_return_value_or_array_contents | True |
| [OddBitSetNumber/2](cases/OddBitSetNumber/mutant_2/c/replay.json) | validated_violation | `{"n": 0}` | frozen_return_value_or_array_contents | True |
| [OddBitSetNumber/20](cases/OddBitSetNumber/mutant_20/c/replay.json) | validated_violation | `{"n": 2125}` | frozen_return_value_or_array_contents | True |
| [OddBitSetNumber/23](cases/OddBitSetNumber/mutant_23/c/replay.json) | validated_violation | `{"n": 0}` | frozen_return_value_or_array_contents | True |
| [OddBitSetNumber/24](cases/OddBitSetNumber/mutant_24/c/replay.json) | validated_violation | `{"n": 0}` | frozen_return_value_or_array_contents | True |
| [OddBitSetNumber/3](cases/OddBitSetNumber/mutant_3/c/replay.json) | validated_violation | `{"n": 0}` | frozen_return_value_or_array_contents | True |
| [OddBitSetNumber/4](cases/OddBitSetNumber/mutant_4/c/replay.json) | validated_violation | `{"n": 0}` | frozen_return_value_or_array_contents | True |
| [OddBitSetNumber/5](cases/OddBitSetNumber/mutant_5/c/replay.json) | validated_violation | `{"n": 2125}` | frozen_return_value_or_array_contents | True |
| [OddBitSetNumber/7](cases/OddBitSetNumber/mutant_7/c/replay.json) | validated_violation | `{"n": 2125}` | frozen_return_value_or_array_contents | True |
| [OddBitSetNumber/8](cases/OddBitSetNumber/mutant_8/c/replay.json) | validated_violation | `{"n": 0}` | frozen_return_value_or_array_contents | True |
| [OddBitSetNumber/9](cases/OddBitSetNumber/mutant_9/c/replay.json) | validated_violation | `{"n": 0}` | frozen_return_value_or_array_contents | True |
| [OddLengthSum/10](cases/OddLengthSum/mutant_10/c/replay.json) | validated_violation | `{"arr": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | frozen_return_value_or_array_contents | True |
| [OddLengthSum/12](cases/OddLengthSum/mutant_12/c/replay.json) | validated_violation | `{"arr": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | frozen_return_value_or_array_contents | True |
| [OddLengthSum/13](cases/OddLengthSum/mutant_13/c/replay.json) | validated_violation | `{"arr": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | frozen_return_value_or_array_contents | True |
| [OddLengthSum/14](cases/OddLengthSum/mutant_14/c/replay.json) | validated_violation | `{"arr": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | frozen_return_value_or_array_contents | True |
| [OddLengthSum/15](cases/OddLengthSum/mutant_15/c/replay.json) | validated_violation | `{"arr": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | frozen_return_value_or_array_contents | True |
| [OddLengthSum/16](cases/OddLengthSum/mutant_16/c/replay.json) | validated_violation | `{"arr": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | frozen_return_value_or_array_contents | True |
| [OddLengthSum/17](cases/OddLengthSum/mutant_17/c/replay.json) | validated_violation | `{"arr": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | frozen_return_value_or_array_contents | True |
| [OddLengthSum/18](cases/OddLengthSum/mutant_18/c/replay.json) | validated_violation | `{"arr": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | frozen_return_value_or_array_contents | True |
| [OddLengthSum/19](cases/OddLengthSum/mutant_19/c/replay.json) | validated_violation | `{"arr": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | frozen_return_value_or_array_contents | True |
| [OddLengthSum/20](cases/OddLengthSum/mutant_20/c/replay.json) | validated_violation | `{"arr": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | frozen_return_value_or_array_contents | True |
| [OddLengthSum/21](cases/OddLengthSum/mutant_21/c/replay.json) | validated_violation | `{"arr": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | frozen_return_value_or_array_contents | True |
| [OddLengthSum/22](cases/OddLengthSum/mutant_22/c/replay.json) | validated_violation | `{"arr": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | frozen_return_value_or_array_contents | True |
| [OddLengthSum/23](cases/OddLengthSum/mutant_23/c/replay.json) | validated_violation | `{"arr": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | frozen_return_value_or_array_contents | True |
| [OddLengthSum/24](cases/OddLengthSum/mutant_24/c/replay.json) | validated_violation | `{"arr": [-1]}` | frozen_return_value_or_array_contents | True |
| [OddLengthSum/25](cases/OddLengthSum/mutant_25/c/replay.json) | validated_violation | `{"arr": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | frozen_return_value_or_array_contents | True |
| [OddLengthSum/26](cases/OddLengthSum/mutant_26/c/replay.json) | validated_violation | `{"arr": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | frozen_return_value_or_array_contents | True |
| [OddLengthSum/27](cases/OddLengthSum/mutant_27/c/replay.json) | validated_violation | `{"arr": [2]}` | frozen_return_value_or_array_contents | True |
| [OddLengthSum/28](cases/OddLengthSum/mutant_28/c/replay.json) | validated_violation | `{"arr": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | frozen_return_value_or_array_contents | True |
| [OddLengthSum/4](cases/OddLengthSum/mutant_4/c/replay.json) | validated_violation | `{"arr": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | frozen_return_value_or_array_contents | True |
| [OddLengthSum/2](cases/OddLengthSum/mutant_2/c/replay.json) | validated_safety_failure | `{"arr": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | runtime safety | True |
| [OddLengthSum/5](cases/OddLengthSum/mutant_5/c/replay.json) | validated_violation | `{"arr": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | frozen_return_value_or_array_contents | True |
| [OddLengthSum/6](cases/OddLengthSum/mutant_6/c/replay.json) | validated_violation | `{"arr": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | frozen_return_value_or_array_contents | True |
| [OddLengthSum/7](cases/OddLengthSum/mutant_7/c/replay.json) | validated_violation | `{"arr": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | frozen_return_value_or_array_contents | True |
| [OddLengthSum/9](cases/OddLengthSum/mutant_9/c/replay.json) | validated_violation | `{"arr": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | frozen_return_value_or_array_contents | True |
| [PairWise/1](cases/PairWise/mutant_1/c/replay.json) | validated_violation | `{"l1": [0, 0, 0]}` | frozen_return_value_or_array_contents | True |
| [PairWise/10](cases/PairWise/mutant_10/c/replay.json) | validated_safety_failure | `{"l1": [0, 0]}` | runtime safety | True |
| [PairWise/11](cases/PairWise/mutant_11/c/replay.json) | validated_safety_failure | `{"l1": [0, 0]}` | runtime safety | True |
| [PairWise/12](cases/PairWise/mutant_12/c/replay.json) | validated_safety_failure | `{"l1": [0, 0]}` | runtime safety | True |
| [PairWise/2](cases/PairWise/mutant_2/c/replay.json) | validated_violation | `{"l1": [0, 0]}` | frozen_return_value_or_array_contents | True |
| [PairWise/3](cases/PairWise/mutant_3/c/replay.json) | validated_safety_failure | `{"l1": []}` | runtime safety | True |
| [PairWise/4](cases/PairWise/mutant_4/c/replay.json) | validated_safety_failure | `{"l1": []}` | runtime safety | True |
| [PairWise/14](cases/PairWise/mutant_14/c/replay.json) | validated_safety_failure | `{"l1": [0, 0]}` | runtime safety | True |
| [PairWise/19](cases/PairWise/mutant_19/c/replay.json) | validated_safety_failure | `{"l1": [0, 0]}` | runtime safety | True |
| [PairWise/6](cases/PairWise/mutant_6/c/replay.json) | validated_violation | `{"l1": [0, 0]}` | frozen_return_value_or_array_contents | True |
| [PairWise/7](cases/PairWise/mutant_7/c/replay.json) | validated_violation | `{"l1": [0, 0]}` | frozen_return_value_or_array_contents | True |
| [PairWise/8](cases/PairWise/mutant_8/c/replay.json) | validated_violation | `{"l1": [0, 0]}` | frozen_return_value_or_array_contents | True |
| [ParabolaVertex/1](cases/ParabolaVertex/mutant_1/c/replay.json) | validated_violation | `{"a": 3514, "b": 3514, "c": 3514}` | frozen_return_value_or_array_contents | True |
| [ParabolaVertex/10](cases/ParabolaVertex/mutant_10/c/replay.json) | validated_violation | `{"a": 3514, "b": 3514, "c": 3514}` | frozen_return_value_or_array_contents | True |
| [ParabolaVertex/11](cases/ParabolaVertex/mutant_11/c/replay.json) | validated_violation | `{"a": 3514, "b": 3514, "c": 3514}` | frozen_return_value_or_array_contents | True |
| [ParabolaVertex/12](cases/ParabolaVertex/mutant_12/c/replay.json) | validated_violation | `{"a": 3514, "b": 3514, "c": 3514}` | frozen_return_value_or_array_contents | True |
| [ParabolaVertex/13](cases/ParabolaVertex/mutant_13/c/replay.json) | validated_violation | `{"a": 3514, "b": 3514, "c": 3514}` | frozen_return_value_or_array_contents | True |
| [ParabolaVertex/14](cases/ParabolaVertex/mutant_14/c/replay.json) | validated_violation | `{"a": 3514, "b": 3514, "c": 3514}` | frozen_return_value_or_array_contents | True |
| [ParabolaVertex/15](cases/ParabolaVertex/mutant_15/c/replay.json) | validated_violation | `{"a": 3514, "b": 3514, "c": 3514}` | frozen_return_value_or_array_contents | True |
| [PairWise/5](cases/PairWise/mutant_5/c/replay.json) | validated_safety_failure | `{"l1": [0, 0]}` | runtime safety | True |
| [ParabolaVertex/16](cases/ParabolaVertex/mutant_16/c/replay.json) | validated_violation | `{"a": 3514, "b": 3514, "c": 3514}` | frozen_return_value_or_array_contents | True |
| [ParabolaVertex/17](cases/ParabolaVertex/mutant_17/c/replay.json) | validated_violation | `{"a": 3514, "b": 3514, "c": 3514}` | frozen_return_value_or_array_contents | True |
| [ParabolaVertex/18](cases/ParabolaVertex/mutant_18/c/replay.json) | validated_violation | `{"a": 3514, "b": 3514, "c": 3514}` | frozen_return_value_or_array_contents | True |
| [ParabolaVertex/19](cases/ParabolaVertex/mutant_19/c/replay.json) | validated_violation | `{"a": 3514, "b": 3514, "c": 3514}` | frozen_return_value_or_array_contents | True |
| [ParabolaVertex/2](cases/ParabolaVertex/mutant_2/c/replay.json) | validated_violation | `{"a": 3514, "b": 3514, "c": 3514}` | frozen_return_value_or_array_contents | True |
| [ParabolaVertex/20](cases/ParabolaVertex/mutant_20/c/replay.json) | validated_violation | `{"a": 3514, "b": 3514, "c": 3514}` | frozen_return_value_or_array_contents | True |
| [ParabolaVertex/21](cases/ParabolaVertex/mutant_21/c/replay.json) | validated_violation | `{"a": 3514, "b": 3514, "c": 3514}` | frozen_return_value_or_array_contents | True |
| [ParabolaVertex/22](cases/ParabolaVertex/mutant_22/c/replay.json) | validated_violation | `{"a": 3514, "b": 3514, "c": 3514}` | frozen_return_value_or_array_contents | True |
| [ParabolaVertex/23](cases/ParabolaVertex/mutant_23/c/replay.json) | validated_violation | `{"a": 3514, "b": 3514, "c": 3514}` | frozen_return_value_or_array_contents | True |
| [ParabolaVertex/24](cases/ParabolaVertex/mutant_24/c/replay.json) | validated_violation | `{"a": 3514, "b": 3514, "c": 3514}` | frozen_return_value_or_array_contents | True |
| [ParabolaVertex/25](cases/ParabolaVertex/mutant_25/c/replay.json) | validated_violation | `{"a": 3514, "b": 3514, "c": 3514}` | frozen_return_value_or_array_contents | True |
| [ParabolaVertex/26](cases/ParabolaVertex/mutant_26/c/replay.json) | validated_violation | `{"a": 3514, "b": 3514, "c": 3514}` | frozen_return_value_or_array_contents | True |
| [ParabolaVertex/27](cases/ParabolaVertex/mutant_27/c/replay.json) | validated_violation | `{"a": 3514, "b": 3514, "c": 3514}` | frozen_return_value_or_array_contents | True |
| [ParabolaVertex/28](cases/ParabolaVertex/mutant_28/c/replay.json) | validated_violation | `{"a": 3514, "b": 3514, "c": 3514}` | frozen_return_value_or_array_contents | True |
| [ParabolaVertex/29](cases/ParabolaVertex/mutant_29/c/replay.json) | validated_violation | `{"a": 3514, "b": 3514, "c": 3514}` | frozen_return_value_or_array_contents | True |
| [ParabolaVertex/3](cases/ParabolaVertex/mutant_3/c/replay.json) | validated_violation | `{"a": 3514, "b": 3514, "c": 3514}` | frozen_return_value_or_array_contents | True |
| [ParabolaVertex/30](cases/ParabolaVertex/mutant_30/c/replay.json) | validated_violation | `{"a": 3514, "b": 3514, "c": 3514}` | frozen_return_value_or_array_contents | True |
| [ParabolaVertex/31](cases/ParabolaVertex/mutant_31/c/replay.json) | validated_violation | `{"a": 3514, "b": 3514, "c": 3514}` | frozen_return_value_or_array_contents | True |
| [ParabolaVertex/32](cases/ParabolaVertex/mutant_32/c/replay.json) | validated_violation | `{"a": 3514, "b": 3514, "c": 3514}` | frozen_return_value_or_array_contents | True |
| [ParabolaVertex/33](cases/ParabolaVertex/mutant_33/c/replay.json) | validated_violation | `{"a": 3514, "b": 3514, "c": 3514}` | frozen_return_value_or_array_contents | True |
| [ParabolaVertex/34](cases/ParabolaVertex/mutant_34/c/replay.json) | validated_violation | `{"a": 3514, "b": 3514, "c": 3514}` | frozen_return_value_or_array_contents | True |
| [ParabolaVertex/35](cases/ParabolaVertex/mutant_35/c/replay.json) | validated_violation | `{"a": 3514, "b": 3514, "c": 3514}` | frozen_return_value_or_array_contents | True |
| [ParabolaVertex/4](cases/ParabolaVertex/mutant_4/c/replay.json) | validated_violation | `{"a": 3514, "b": 3514, "c": 3514}` | frozen_return_value_or_array_contents | True |
| [ParabolaVertex/5](cases/ParabolaVertex/mutant_5/c/replay.json) | validated_violation | `{"a": 3514, "b": 3514, "c": 3514}` | frozen_return_value_or_array_contents | True |
| [ParabolaVertex/6](cases/ParabolaVertex/mutant_6/c/replay.json) | validated_violation | `{"a": 3514, "b": 3514, "c": 3514}` | frozen_return_value_or_array_contents | True |
| [ParabolaVertex/7](cases/ParabolaVertex/mutant_7/c/replay.json) | validated_violation | `{"a": 3514, "b": 3514, "c": 3514}` | frozen_return_value_or_array_contents | True |
| [ParabolaVertex/8](cases/ParabolaVertex/mutant_8/c/replay.json) | validated_violation | `{"a": 3514, "b": 3514, "c": 3514}` | frozen_return_value_or_array_contents | True |
| [ParabolaVertex/9](cases/ParabolaVertex/mutant_9/c/replay.json) | validated_violation | `{"a": 3514, "b": 3514, "c": 3514}` | frozen_return_value_or_array_contents | True |
| [ParallelogramPerimeter/10](cases/ParallelogramPerimeter/mutant_10/c/replay.json) | validated_violation | `{"b": 1709539028, "h": 1}` | frozen_return_value_or_array_contents | True |
| [ParallelogramPerimeter/11](cases/ParallelogramPerimeter/mutant_11/c/replay.json) | validated_violation | `{"b": 1, "h": -972}` | frozen_return_value_or_array_contents | True |
| [ParallelogramPerimeter/12](cases/ParallelogramPerimeter/mutant_12/c/replay.json) | validated_violation | `{"b": 1709539028, "h": 1}` | frozen_return_value_or_array_contents | True |
| [ParallelogramPerimeter/13](cases/ParallelogramPerimeter/mutant_13/c/replay.json) | validated_violation | `{"b": 1709539028, "h": 1}` | frozen_return_value_or_array_contents | True |
| [ParallelogramPerimeter/14](cases/ParallelogramPerimeter/mutant_14/c/replay.json) | validated_violation | `{"b": 1709539028, "h": 1}` | frozen_return_value_or_array_contents | True |
| [ParallelogramPerimeter/15](cases/ParallelogramPerimeter/mutant_15/c/replay.json) | validated_violation | `{"b": 1901, "h": 1901}` | frozen_return_value_or_array_contents | True |
| [ParallelogramPerimeter/16](cases/ParallelogramPerimeter/mutant_16/c/replay.json) | validated_violation | `{"b": 1709539028, "h": 1}` | frozen_return_value_or_array_contents | True |
| [ParallelogramPerimeter/17](cases/ParallelogramPerimeter/mutant_17/c/replay.json) | validated_violation | `{"b": 1709539028, "h": 1}` | frozen_return_value_or_array_contents | True |
| [ParallelogramPerimeter/18](cases/ParallelogramPerimeter/mutant_18/c/replay.json) | validated_violation | `{"b": 1709539028, "h": 1}` | frozen_return_value_or_array_contents | True |
| [ParallelogramPerimeter/19](cases/ParallelogramPerimeter/mutant_19/c/replay.json) | validated_violation | `{"b": 1709539028, "h": 1}` | frozen_return_value_or_array_contents | True |
| [ParallelogramPerimeter/2](cases/ParallelogramPerimeter/mutant_2/c/replay.json) | validated_violation | `{"b": -741, "h": 1381865608}` | frozen_return_value_or_array_contents | True |
| [ParallelogramPerimeter/3](cases/ParallelogramPerimeter/mutant_3/c/replay.json) | validated_violation | `{"b": 1709539028, "h": 1}` | frozen_return_value_or_array_contents | True |
| [ParallelogramPerimeter/5](cases/ParallelogramPerimeter/mutant_5/c/replay.json) | validated_violation | `{"b": 1, "h": -972}` | frozen_return_value_or_array_contents | True |
| [ParallelogramPerimeter/6](cases/ParallelogramPerimeter/mutant_6/c/replay.json) | validated_violation | `{"b": 1709539028, "h": 1}` | frozen_return_value_or_array_contents | True |
| [ParallelogramPerimeter/8](cases/ParallelogramPerimeter/mutant_8/c/replay.json) | validated_violation | `{"b": 1, "h": -972}` | frozen_return_value_or_array_contents | True |
| [ParallelogramPerimeter/9](cases/ParallelogramPerimeter/mutant_9/c/replay.json) | validated_violation | `{"b": -741, "h": 1381865608}` | frozen_return_value_or_array_contents | True |
| [RadixSort/10](cases/RadixSort/mutant_10/c/replay.json) | validated_safety_failure | `{"nums": [1, 0]}` | runtime safety | True |
| [RadixSort/11](cases/RadixSort/mutant_11/c/replay.json) | validated_safety_failure | `{"nums": [-1]}` | runtime safety | True |
| [RadixSort/12](cases/RadixSort/mutant_12/c/replay.json) | validated_safety_failure | `{"nums": [-1, 0, 1]}` | runtime safety | True |
| [RadixSort/13](cases/RadixSort/mutant_13/c/replay.json) | validated_safety_failure | `{"nums": [0]}` | runtime safety | True |
| [RadixSort/17](cases/RadixSort/mutant_17/c/replay.json) | validated_violation | `{"nums": [2, 1]}` | frozen_return_value_or_array_contents | True |
| [RadixSort/18](cases/RadixSort/mutant_18/c/replay.json) | validated_violation | `{"nums": [1, 0]}` | frozen_return_value_or_array_contents | True |
| [RadixSort/14](cases/RadixSort/mutant_14/c/replay.json) | validated_safety_failure | `{"nums": [0]}` | runtime safety | True |
| [RadixSort/15](cases/RadixSort/mutant_15/c/replay.json) | validated_safety_failure | `{"nums": [0]}` | runtime safety | True |
| [RadixSort/16](cases/RadixSort/mutant_16/c/replay.json) | validated_safety_failure | `{"nums": [0]}` | runtime safety | True |
| [RadixSort/21](cases/RadixSort/mutant_21/c/replay.json) | validated_violation | `{"nums": [1, 0]}` | frozen_return_value_or_array_contents | True |
| [RadixSort/20](cases/RadixSort/mutant_20/c/replay.json) | validated_violation | `{"nums": [-1, -1, 0]}` | frozen_return_value_or_array_contents | True |
| [RadixSort/28](cases/RadixSort/mutant_28/c/replay.json) | validated_violation | `{"nums": [-1]}` | frozen_return_value_or_array_contents | True |
| [RadixSort/19](cases/RadixSort/mutant_19/c/replay.json) | validated_safety_failure | `{"nums": [-1]}` | runtime safety | True |
| [RadixSort/29](cases/RadixSort/mutant_29/c/replay.json) | validated_violation | `{"nums": [-1]}` | frozen_return_value_or_array_contents | True |
| [RadixSort/30](cases/RadixSort/mutant_30/c/replay.json) | validated_violation | `{"nums": [-1]}` | frozen_return_value_or_array_contents | True |
| [RadixSort/23](cases/RadixSort/mutant_23/c/replay.json) | validated_safety_failure | `{"nums": [0]}` | runtime safety | True |
| [RadixSort/26](cases/RadixSort/mutant_26/c/replay.json) | validated_safety_failure | `{"nums": [0]}` | runtime safety | True |
| [RadixSort/31](cases/RadixSort/mutant_31/c/replay.json) | validated_violation | `{"nums": [-1]}` | frozen_return_value_or_array_contents | True |
| [RadixSort/3](cases/RadixSort/mutant_3/c/replay.json) | validated_safety_failure | `{"nums": [0, 1]}` | runtime safety | True |
| [RadixSort/32](cases/RadixSort/mutant_32/c/replay.json) | validated_violation | `{"nums": [1, 0]}` | frozen_return_value_or_array_contents | True |
| [RadixSort/5](cases/RadixSort/mutant_5/c/replay.json) | validated_safety_failure | `{"nums": [0, 1]}` | runtime safety | True |
| [RadixSort/4](cases/RadixSort/mutant_4/c/replay.json) | validated_safety_failure | `{"nums": [0, 1]}` | runtime safety | True |
| [RadixSort/7](cases/RadixSort/mutant_7/c/replay.json) | validated_safety_failure | `{"nums": [1, 0]}` | runtime safety | True |
| [RadixSort/8](cases/RadixSort/mutant_8/c/replay.json) | validated_safety_failure | `{"nums": [1, 0]}` | runtime safety | True |
| [SqrtRoot/1](cases/SqrtRoot/mutant_1/c/replay.json) | validated_violation | `{"num": 2125}` | frozen_return_value_or_array_contents | True |
| [SqrtRoot/13](cases/SqrtRoot/mutant_13/c/replay.json) | validated_violation | `{"num": 2125}` | frozen_return_value_or_array_contents | True |
| [SqrtRoot/14](cases/SqrtRoot/mutant_14/c/replay.json) | validated_violation | `{"num": 0}` | frozen_return_value_or_array_contents | True |
| [SqrtRoot/15](cases/SqrtRoot/mutant_15/c/replay.json) | validated_violation | `{"num": 0}` | frozen_return_value_or_array_contents | True |
| [RadixSort/9](cases/RadixSort/mutant_9/c/replay.json) | validated_safety_failure | `{"nums": [2, 1]}` | runtime safety | True |
| [SqrtRoot/17](cases/SqrtRoot/mutant_17/c/replay.json) | validated_violation | `{"num": 2125}` | frozen_return_value_or_array_contents | True |
| [SqrtRoot/18](cases/SqrtRoot/mutant_18/c/replay.json) | validated_violation | `{"num": 2125}` | frozen_return_value_or_array_contents | True |
| [SqrtRoot/2](cases/SqrtRoot/mutant_2/c/replay.json) | validated_violation | `{"num": 0}` | frozen_return_value_or_array_contents | True |
| [SqrtRoot/20](cases/SqrtRoot/mutant_20/c/replay.json) | validated_violation | `{"num": 16}` | frozen_return_value_or_array_contents | True |
| [SqrtRoot/24](cases/SqrtRoot/mutant_24/c/replay.json) | validated_violation | `{"num": 2125}` | frozen_return_value_or_array_contents | True |
| [SqrtRoot/23](cases/SqrtRoot/mutant_23/c/replay.json) | validated_violation | `{"num": 16}` | frozen_return_value_or_array_contents | True |
| [SqrtRoot/25](cases/SqrtRoot/mutant_25/c/replay.json) | validated_violation | `{"num": 2125}` | frozen_return_value_or_array_contents | True |
| [SqrtRoot/26](cases/SqrtRoot/mutant_26/c/replay.json) | validated_violation | `{"num": 0}` | frozen_return_value_or_array_contents | True |
| [SqrtRoot/28](cases/SqrtRoot/mutant_28/c/replay.json) | validated_violation | `{"num": 2125}` | frozen_return_value_or_array_contents | True |
| [SqrtRoot/29](cases/SqrtRoot/mutant_29/c/replay.json) | validated_violation | `{"num": 2125}` | frozen_return_value_or_array_contents | True |
| [SqrtRoot/3](cases/SqrtRoot/mutant_3/c/replay.json) | validated_violation | `{"num": -3882}` | frozen_return_value_or_array_contents | True |
| [SqrtRoot/30](cases/SqrtRoot/mutant_30/c/replay.json) | validated_violation | `{"num": 2125}` | frozen_return_value_or_array_contents | True |
| [SqrtRoot/31](cases/SqrtRoot/mutant_31/c/replay.json) | validated_violation | `{"num": 2125}` | frozen_return_value_or_array_contents | True |
| [SqrtRoot/32](cases/SqrtRoot/mutant_32/c/replay.json) | validated_violation | `{"num": 2125}` | frozen_return_value_or_array_contents | True |
| [SqrtRoot/34](cases/SqrtRoot/mutant_34/c/replay.json) | validated_violation | `{"num": 2125}` | frozen_return_value_or_array_contents | True |
| [SqrtRoot/4](cases/SqrtRoot/mutant_4/c/replay.json) | validated_violation | `{"num": -3882}` | frozen_return_value_or_array_contents | True |
| [SqrtRoot/40](cases/SqrtRoot/mutant_40/c/replay.json) | validated_violation | `{"num": 2125}` | frozen_return_value_or_array_contents | True |
| [SqrtRoot/5](cases/SqrtRoot/mutant_5/c/replay.json) | validated_violation | `{"num": 2125}` | frozen_return_value_or_array_contents | True |
| [SqrtRoot/6](cases/SqrtRoot/mutant_6/c/replay.json) | validated_violation | `{"num": 2125}` | frozen_return_value_or_array_contents | True |
| [SqrtRoot/9](cases/SqrtRoot/mutant_9/c/replay.json) | validated_violation | `{"num": 2125}` | frozen_return_value_or_array_contents | True |
| [SquarePerimeter/1](cases/SquarePerimeter/mutant_1/c/replay.json) | validated_violation | `{"a": 2125}` | frozen_return_value_or_array_contents | True |
| [SquarePerimeter/2](cases/SquarePerimeter/mutant_2/c/replay.json) | validated_violation | `{"a": 0}` | frozen_return_value_or_array_contents | True |
| [SquarePerimeter/3](cases/SquarePerimeter/mutant_3/c/replay.json) | validated_violation | `{"a": 0}` | frozen_return_value_or_array_contents | True |
| [SquarePerimeter/4](cases/SquarePerimeter/mutant_4/c/replay.json) | validated_violation | `{"a": 2125}` | frozen_return_value_or_array_contents | True |
| [SumList/4](cases/SumList/mutant_4/c/replay.json) | validated_violation | `{"arr1": [0], "arr2": [-1]}` | frozen_return_value_or_array_contents | True |
| [SumList/5](cases/SumList/mutant_5/c/replay.json) | validated_violation | `{"arr1": [0], "arr2": [-1]}` | frozen_return_value_or_array_contents | True |
| [SumList/6](cases/SumList/mutant_6/c/replay.json) | validated_violation | `{"arr1": [0], "arr2": [-1]}` | frozen_return_value_or_array_contents | True |
| [SumList/7](cases/SumList/mutant_7/c/replay.json) | validated_violation | `{"arr1": [0], "arr2": [-1]}` | frozen_return_value_or_array_contents | True |
| [SumList/8](cases/SumList/mutant_8/c/replay.json) | validated_violation | `{"arr1": [0], "arr2": [-1]}` | frozen_return_value_or_array_contents | True |
| [SumNums/1](cases/SumNums/mutant_1/c/replay.json) | validated_violation | `{"m": 748, "n": 20, "x": 20, "y": -450}` | frozen_return_value_or_array_contents | True |
| [SumNums/10](cases/SumNums/mutant_10/c/replay.json) | validated_violation | `{"m": -1537, "n": -3324, "x": 1, "y": -1}` | frozen_return_value_or_array_contents | True |
| [SumNums/12](cases/SumNums/mutant_12/c/replay.json) | validated_violation | `{"m": -3882, "n": 20, "x": -3882, "y": 0}` | frozen_return_value_or_array_contents | True |
| [SumNums/13](cases/SumNums/mutant_13/c/replay.json) | validated_violation | `{"m": -1537, "n": -3324, "x": 1, "y": -1}` | frozen_return_value_or_array_contents | True |
| [SumNums/14](cases/SumNums/mutant_14/c/replay.json) | validated_violation | `{"m": 748, "n": 20, "x": 20, "y": -450}` | frozen_return_value_or_array_contents | True |
| [SumNums/2](cases/SumNums/mutant_2/c/replay.json) | validated_violation | `{"m": -1537, "n": -3324, "x": 1, "y": -1}` | frozen_return_value_or_array_contents | True |
| [SumNums/3](cases/SumNums/mutant_3/c/replay.json) | validated_violation | `{"m": -1537, "n": -3324, "x": 1, "y": -1}` | frozen_return_value_or_array_contents | True |
| [SumNums/4](cases/SumNums/mutant_4/c/replay.json) | validated_violation | `{"m": -1537, "n": -3324, "x": 1, "y": -1}` | frozen_return_value_or_array_contents | True |
| [SumNums/5](cases/SumNums/mutant_5/c/replay.json) | validated_violation | `{"m": -450, "n": 0, "x": 0, "y": 0}` | frozen_return_value_or_array_contents | True |
| [SumNums/6](cases/SumNums/mutant_6/c/replay.json) | validated_violation | `{"m": -3882, "n": 20, "x": -3882, "y": 0}` | frozen_return_value_or_array_contents | True |
| [SumNums/7](cases/SumNums/mutant_7/c/replay.json) | validated_violation | `{"m": 748, "n": 20, "x": 20, "y": -450}` | frozen_return_value_or_array_contents | True |
| [SumNums/8](cases/SumNums/mutant_8/c/replay.json) | validated_violation | `{"m": -450, "n": 0, "x": 0, "y": 0}` | frozen_return_value_or_array_contents | True |
| [SumNums/9](cases/SumNums/mutant_9/c/replay.json) | validated_violation | `{"m": -3882, "n": 20, "x": -3882, "y": 0}` | frozen_return_value_or_array_contents | True |
| [SumOfPrimes/1](cases/SumOfPrimes/mutant_1/c/replay.json) | validated_safety_failure | `{"n": 2}` | runtime safety | True |
| [SumOfPrimes/10](cases/SumOfPrimes/mutant_10/c/replay.json) | validated_violation | `{"n": 2}` | frozen_return_value_or_array_contents | True |
| [SumOfPrimes/14](cases/SumOfPrimes/mutant_14/c/replay.json) | validated_violation | `{"n": 3}` | frozen_return_value_or_array_contents | True |
| [SumOfPrimes/15](cases/SumOfPrimes/mutant_15/c/replay.json) | validated_violation | `{"n": 4}` | frozen_return_value_or_array_contents | True |
| [SumOfPrimes/16](cases/SumOfPrimes/mutant_16/c/replay.json) | validated_violation | `{"n": 7}` | frozen_return_value_or_array_contents | True |
| [SumList/2](cases/SumList/mutant_2/c/replay.json) | validated_safety_failure | `{"arr1": [], "arr2": []}` | runtime safety | True |
| [SumOfPrimes/17](cases/SumOfPrimes/mutant_17/c/replay.json) | validated_safety_failure | `{"n": 2}` | runtime safety | True |
| [SumOfPrimes/2](cases/SumOfPrimes/mutant_2/c/replay.json) | validated_safety_failure | `{"n": -1}` | runtime safety | True |
| [SumOfPrimes/4](cases/SumOfPrimes/mutant_4/c/replay.json) | validated_safety_failure | `{"n": -1}` | runtime safety | True |
| [SumOfPrimes/3](cases/SumOfPrimes/mutant_3/c/replay.json) | validated_safety_failure | `{"n": -1}` | runtime safety | True |
| [SumOfPrimes/5](cases/SumOfPrimes/mutant_5/c/replay.json) | validated_violation | `{"n": 2}` | frozen_return_value_or_array_contents | True |
| [SumOfPrimes/7](cases/SumOfPrimes/mutant_7/c/replay.json) | validated_violation | `{"n": 3}` | frozen_return_value_or_array_contents | True |
| [SumOfPrimes/6](cases/SumOfPrimes/mutant_6/c/replay.json) | validated_violation | `{"n": 2}` | frozen_return_value_or_array_contents | True |
| [SumOfSubarrayProd/7](cases/SumOfSubarrayProd/mutant_7/c/replay.json) | validated_violation | `{"arr": [1129, 0, 0, 0], "n": 1}` | frozen_return_value_or_array_contents | True |
| [SumOfSubarrayProd/8](cases/SumOfSubarrayProd/mutant_8/c/replay.json) | validated_violation | `{"arr": [1129, 0, 0, 0], "n": 1}` | frozen_return_value_or_array_contents | True |
| [SumRangeList/1](cases/SumRangeList/mutant_1/c/replay.json) | validated_violation | `{"m": 0, "n": 0, "nums": [-1]}` | frozen_return_value_or_array_contents | True |
| [SumRangeList/4](cases/SumRangeList/mutant_4/c/replay.json) | validated_violation | `{"m": 0, "n": 0, "nums": [-1]}` | frozen_return_value_or_array_contents | True |
| [SumRangeList/2](cases/SumRangeList/mutant_2/c/replay.json) | validated_violation | `{"m": 0, "n": 1, "nums": [1, 0]}` | frozen_return_value_or_array_contents | True |
| [TestThreeEqual/1](cases/TestThreeEqual/mutant_1/c/replay.json) | validated_violation | `{"x": -850, "y": 0, "z": 0}` | frozen_return_value_or_array_contents | True |
| [TestThreeEqual/10](cases/TestThreeEqual/mutant_10/c/replay.json) | validated_violation | `{"x": -850, "y": 0, "z": 0}` | frozen_return_value_or_array_contents | True |
| [TestThreeEqual/11](cases/TestThreeEqual/mutant_11/c/replay.json) | validated_violation | `{"x": 0, "y": 665, "z": 2}` | frozen_return_value_or_array_contents | True |
| [TestThreeEqual/13](cases/TestThreeEqual/mutant_13/c/replay.json) | validated_violation | `{"x": 3, "y": 3, "z": 0}` | frozen_return_value_or_array_contents | True |
| [TestThreeEqual/15](cases/TestThreeEqual/mutant_15/c/replay.json) | validated_violation | `{"x": 0, "y": 665, "z": 2}` | frozen_return_value_or_array_contents | True |
| [TestThreeEqual/16](cases/TestThreeEqual/mutant_16/c/replay.json) | validated_violation | `{"x": -850, "y": 0, "z": 0}` | frozen_return_value_or_array_contents | True |
| [TestThreeEqual/18](cases/TestThreeEqual/mutant_18/c/replay.json) | validated_violation | `{"x": -850, "y": 0, "z": 0}` | frozen_return_value_or_array_contents | True |
| [TestThreeEqual/19](cases/TestThreeEqual/mutant_19/c/replay.json) | validated_violation | `{"x": 3, "y": 3, "z": 0}` | frozen_return_value_or_array_contents | True |
| [TestThreeEqual/20](cases/TestThreeEqual/mutant_20/c/replay.json) | validated_violation | `{"x": 0, "y": 665, "z": 2}` | frozen_return_value_or_array_contents | True |
| [TestThreeEqual/21](cases/TestThreeEqual/mutant_21/c/replay.json) | validated_violation | `{"x": 0, "y": 665, "z": 2}` | frozen_return_value_or_array_contents | True |
| [TestThreeEqual/22](cases/TestThreeEqual/mutant_22/c/replay.json) | validated_violation | `{"x": -1413, "y": 2, "z": -2487}` | frozen_return_value_or_array_contents | True |
| [TestThreeEqual/23](cases/TestThreeEqual/mutant_23/c/replay.json) | validated_violation | `{"x": 0, "y": -419, "z": 0}` | frozen_return_value_or_array_contents | True |
| [TestThreeEqual/25](cases/TestThreeEqual/mutant_25/c/replay.json) | validated_violation | `{"x": 0, "y": -419, "z": 0}` | frozen_return_value_or_array_contents | True |
| [TestThreeEqual/26](cases/TestThreeEqual/mutant_26/c/replay.json) | validated_violation | `{"x": 3, "y": 3, "z": 0}` | frozen_return_value_or_array_contents | True |
| [TestThreeEqual/27](cases/TestThreeEqual/mutant_27/c/replay.json) | validated_violation | `{"x": 0, "y": 665, "z": 2}` | frozen_return_value_or_array_contents | True |
| [TestThreeEqual/3](cases/TestThreeEqual/mutant_3/c/replay.json) | validated_violation | `{"x": 0, "y": 0, "z": 0}` | frozen_return_value_or_array_contents | True |
| [TestThreeEqual/4](cases/TestThreeEqual/mutant_4/c/replay.json) | validated_violation | `{"x": -1413, "y": -1413, "z": 3}` | frozen_return_value_or_array_contents | True |
| [TestThreeEqual/5](cases/TestThreeEqual/mutant_5/c/replay.json) | validated_violation | `{"x": 3, "y": 3, "z": 0}` | frozen_return_value_or_array_contents | True |
| [TestThreeEqual/6](cases/TestThreeEqual/mutant_6/c/replay.json) | validated_violation | `{"x": 0, "y": 0, "z": 0}` | frozen_return_value_or_array_contents | True |
| [TestThreeEqual/7](cases/TestThreeEqual/mutant_7/c/replay.json) | validated_violation | `{"x": 0, "y": 665, "z": 2}` | frozen_return_value_or_array_contents | True |
| [TestThreeEqual/8](cases/TestThreeEqual/mutant_8/c/replay.json) | validated_violation | `{"x": 0, "y": 0, "z": 0}` | frozen_return_value_or_array_contents | True |
| [TestThreeEqual/9](cases/TestThreeEqual/mutant_9/c/replay.json) | validated_violation | `{"x": 3, "y": 3, "z": 0}` | frozen_return_value_or_array_contents | True |
| [TriangleArea/1](cases/TriangleArea/mutant_1/c/replay.json) | validated_violation | `{"r": 2125}` | frozen_return_value_or_array_contents | True |
| [TriangleArea/10](cases/TriangleArea/mutant_10/c/replay.json) | validated_violation | `{"r": 2125}` | frozen_return_value_or_array_contents | True |
| [TriangleArea/11](cases/TriangleArea/mutant_11/c/replay.json) | validated_violation | `{"r": 2125}` | frozen_return_value_or_array_contents | True |
| [TriangleArea/12](cases/TriangleArea/mutant_12/c/replay.json) | validated_violation | `{"r": 2125}` | frozen_return_value_or_array_contents | True |
| [TriangleArea/13](cases/TriangleArea/mutant_13/c/replay.json) | validated_violation | `{"r": 2125}` | frozen_return_value_or_array_contents | True |
| [TriangleArea/14](cases/TriangleArea/mutant_14/c/replay.json) | validated_violation | `{"r": 2125}` | frozen_return_value_or_array_contents | True |
| [TriangleArea/15](cases/TriangleArea/mutant_15/c/replay.json) | validated_violation | `{"r": 2125}` | frozen_return_value_or_array_contents | True |
| [TriangleArea/16](cases/TriangleArea/mutant_16/c/replay.json) | validated_violation | `{"r": 2125}` | frozen_return_value_or_array_contents | True |
| [TriangleArea/17](cases/TriangleArea/mutant_17/c/replay.json) | validated_violation | `{"r": 2125}` | frozen_return_value_or_array_contents | True |
| [TriangleArea/2](cases/TriangleArea/mutant_2/c/replay.json) | validated_violation | `{"r": 0}` | frozen_return_value_or_array_contents | True |
| [TriangleArea/3](cases/TriangleArea/mutant_3/c/replay.json) | validated_violation | `{"r": -3882}` | frozen_return_value_or_array_contents | True |
| [TriangleArea/4](cases/TriangleArea/mutant_4/c/replay.json) | validated_violation | `{"r": -3882}` | frozen_return_value_or_array_contents | True |
| [TriangleArea/6](cases/TriangleArea/mutant_6/c/replay.json) | validated_violation | `{"r": 2125}` | frozen_return_value_or_array_contents | True |
| [TriangleArea/9](cases/TriangleArea/mutant_9/c/replay.json) | validated_violation | `{"r": 2125}` | frozen_return_value_or_array_contents | True |
| [TupleToInt/1](cases/TupleToInt/mutant_1/c/replay.json) | validated_violation | `{"nums": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | frozen_return_value_or_array_contents | True |
| [TupleToInt/2](cases/TupleToInt/mutant_2/c/replay.json) | validated_violation | `{"nums": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | frozen_return_value_or_array_contents | True |
| [TupleToInt/3](cases/TupleToInt/mutant_3/c/replay.json) | validated_violation | `{"nums": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | frozen_return_value_or_array_contents | True |
| [TupleToInt/4](cases/TupleToInt/mutant_4/c/replay.json) | validated_violation | `{"nums": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | frozen_return_value_or_array_contents | True |
| [TupleToInt/5](cases/TupleToInt/mutant_5/c/replay.json) | validated_violation | `{"nums": [-1]}` | frozen_return_value_or_array_contents | True |
| [TupleToInt/6](cases/TupleToInt/mutant_6/c/replay.json) | validated_violation | `{"nums": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | frozen_return_value_or_array_contents | True |
| [TupleToInt/7](cases/TupleToInt/mutant_7/c/replay.json) | validated_violation | `{"nums": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | frozen_return_value_or_array_contents | True |
| [TupleToInt/8](cases/TupleToInt/mutant_8/c/replay.json) | validated_violation | `{"nums": [-1]}` | frozen_return_value_or_array_contents | True |
| [TupleToInt/9](cases/TupleToInt/mutant_9/c/replay.json) | validated_violation | `{"nums": [0, 0, 1856, 0, 0, 0, 0, 0, 0]}` | frozen_return_value_or_array_contents | True |
| [VolumeCube/1](cases/VolumeCube/mutant_1/c/replay.json) | validated_violation | `{"l": -3882}` | frozen_return_value_or_array_contents | True |
| [VolumeCube/2](cases/VolumeCube/mutant_2/c/replay.json) | validated_violation | `{"l": -3882}` | frozen_return_value_or_array_contents | True |
| [VolumeCube/3](cases/VolumeCube/mutant_3/c/replay.json) | validated_violation | `{"l": -3882}` | frozen_return_value_or_array_contents | True |
| [VolumeCube/4](cases/VolumeCube/mutant_4/c/replay.json) | validated_violation | `{"l": -3882}` | frozen_return_value_or_array_contents | True |
| [VolumeCube/5](cases/VolumeCube/mutant_5/c/replay.json) | validated_violation | `{"l": -3882}` | frozen_return_value_or_array_contents | True |
| [VolumeCube/6](cases/VolumeCube/mutant_6/c/replay.json) | validated_violation | `{"l": -3882}` | frozen_return_value_or_array_contents | True |
| [VolumeCube/7](cases/VolumeCube/mutant_7/c/replay.json) | validated_violation | `{"l": -3882}` | frozen_return_value_or_array_contents | True |
| [VolumeCube/8](cases/VolumeCube/mutant_8/c/replay.json) | validated_violation | `{"l": -3882}` | frozen_return_value_or_array_contents | True |

## Reproduce

Run `python3 -m verification.specification_evaluation.c_replay_population --output verification/specification_evaluation/results/c_counterexamples_refreshed_all_c_20261001_search_only --workers 2`. Completed cases resume only if the recorded runner, oracle, runtime and source hashes match. Solver traces and binaries stay local.
