# Original consistency

OpenJML ESC and Frama-C WP evaluate originals only. Primary mutant completeness uses runtime checks of frozen contracts; safety is excluded from its postcondition count.

| Program | Java | C |
|---|---|---|
| CombSort | unknown/timeout | not run |
| CountIntgralPoints | proved | not run |
| CountList | unknown/timeout | not run |
| CountOddSquares | unknown/timeout | not run |
| CountUnsetBits | unknown/timeout | not run |
| CountWays | unknown/timeout | not run |
| CountingSort | unknown/timeout | not run |
| DealnnoyNum | unknown/timeout | not run |
| DiameterCircle | proved | not run |
| DiffEvenOdd | unknown/timeout | not run |
| DogAge | proved | not run |
| Fibonacci | unknown/timeout | not run |
| FindPeak | unknown/timeout | not run |
| FindPoints | proved | not run |
| FindRectNum | proved | not run |
| HexagonalNum | proved | not run |
| LeftInsertion | unknown/timeout | not run |
| MaxDifference | unknown/timeout | not run |
| MaxOfTwo | proved | not run |
| MaxProduct | unknown/timeout | not run |
| MaxSubArraySum | unknown/timeout | not run |
| MaxSumOfThreeConsecutive | unknown/timeout | not run |
| MaxSumSubseq | unknown/timeout | not run |
| MaxVolume | unknown/timeout | not run |
| MaximumSegments | unknown/timeout | not run |
| MinCoins | unknown/timeout | not run |
| MinCost | unknown/timeout | not run |
| MinJumps | unknown/timeout | not run |
| MoveFirst | proved | not run |
| MultiplyElements | unknown/timeout | not run |
| NewmanPrime | unknown/timeout | not run |
| NextPowerOf2 | unknown/timeout | not run |
| NoOfCubes | proved | not run |
| OddBitSetNumber | proved | not run |
| OddLengthSum | unknown/timeout | not run |
| PairWise | unknown/timeout | not run |
| ParabolaVertex | precondition/RTE failure | not run |
| ParallelogramPerimeter | unknown/timeout | not run |
| RadixSort | unknown/timeout | not run |
| SqrtRoot | unknown/timeout | not run |
| SquarePerimeter | proved | not run |
| SumList | unknown/timeout | not run |
| SumNums | proved | not run |
| SumOfPrimes | unknown/timeout | not run |
| SumOfSubarrayProd | unknown/timeout | not run |
| SumRangeList | unknown/timeout | not run |
| TestThreeEqual | proved | not run |
| TriangleArea | unknown/timeout | not run |
| TupleToInt | unknown/timeout | not run |
| VolumeCube | proved | not run |

Current runtime completeness: [primary results](../../execution_based/runtime_contract_primary_01/README.md). Historical verifier mutant evidence is retained separately and does not contribute to runtime completeness.
