# Original consistency

OpenJML ESC and Frama-C WP evaluate originals only. Primary mutant completeness uses runtime checks of frozen contracts; safety is excluded from its postcondition count.

| Program | Java | C |
|---|---|---|
| CombSort | unknown/timeout | unknown/timeout |
| CountIntgralPoints | proved | unknown/timeout |
| CountList | unknown/timeout | proved |
| CountOddSquares | unknown/timeout | unknown/timeout |
| CountUnsetBits | unknown/timeout | unknown/timeout |
| CountWays | unknown/timeout | unknown/timeout |
| CountingSort | unknown/timeout | unknown/timeout |
| DealnnoyNum | unknown/timeout | unknown/timeout |
| DiameterCircle | proved | unknown/timeout |
| DiffEvenOdd | unknown/timeout | unknown/timeout |
| DogAge | proved | unknown/timeout |
| Fibonacci | unknown/timeout | unknown/timeout |
| FindPeak | unknown/timeout | unknown/timeout |
| FindPoints | proved | unknown/timeout |
| FindRectNum | proved | unknown/timeout |
| HexagonalNum | proved | unknown/timeout |
| LeftInsertion | unknown/timeout | unknown/timeout |
| MaxDifference | unknown/timeout | unknown/timeout |
| MaxOfTwo | proved | proved |
| MaxProduct | unknown/timeout | unknown/timeout |
| MaxSubArraySum | unknown/timeout | proved |
| MaxSumOfThreeConsecutive | unknown/timeout | unknown/timeout |
| MaxSumSubseq | unknown/timeout | unknown/timeout |
| MaxVolume | unknown/timeout | unknown/timeout |
| MaximumSegments | unknown/timeout | unknown/timeout |
| MinCoins | unknown/timeout | unknown/timeout |
| MinCost | unknown/timeout | unknown/timeout |
| MinJumps | unknown/timeout | unknown/timeout |
| MoveFirst | unknown/timeout | unknown/timeout |
| MultiplyElements | unknown/timeout | unknown/timeout |
| NewmanPrime | unknown/timeout | unknown/timeout |
| NextPowerOf2 | unknown/timeout | unknown/timeout |
| NoOfCubes | proved | unknown/timeout |
| OddBitSetNumber | proved | proved |
| OddLengthSum | unknown/timeout | unknown/timeout |
| PairWise | unknown/timeout | unknown/timeout |
| ParabolaVertex | precondition/RTE failure | unknown/timeout |
| ParallelogramPerimeter | specification violation | unknown/timeout |
| RadixSort | unknown/timeout | unknown/timeout |
| SqrtRoot | unknown/timeout | unknown/timeout |
| SquarePerimeter | proved | unknown/timeout |
| SumList | unknown/timeout | unknown/timeout |
| SumNums | proved | proved |
| SumOfPrimes | unknown/timeout | unknown/timeout |
| SumOfSubarrayProd | unknown/timeout | unknown/timeout |
| SumRangeList | unknown/timeout | unknown/timeout |
| TestThreeEqual | proved | proved |
| TriangleArea | specification violation | unknown/timeout |
| TupleToInt | unknown/timeout | unknown/timeout |
| VolumeCube | proved | unknown/timeout |

Current runtime completeness: [primary results](../../execution_based/runtime_contract_primary_01/README.md). Historical verifier mutant evidence is retained separately and does not contribute to runtime completeness.
