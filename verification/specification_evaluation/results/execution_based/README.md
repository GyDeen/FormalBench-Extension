# Primary runtime contract mutant detection

Only independently reproduced postcondition violations on admitted inputs contribute to the primary numerator. The frozen generated specification supplies the oracle; any runtime omission is explicitly recorded and applied to both original and mutants. Safety failures are separate.

| Evaluation | Verified originals | Evaluable originals | Selected mutants | Evaluable mutants | Postcondition detections | Pooled rate | Separate safety flags |
|---|---:|---:|---:|---:|---:|---:|---:|
| Java - unchanged contracts | 12 | 12 | 170 | 170 | 170 | 100.00% | 22 |
| Java - FindPoints (`\fresh` unchecked) | 1 | 1 | 22 | 22 | 22 | 100.00% | 0 |
| Java total | 13 | 13 | 192 | 192 | 192 | 100.00% | 22 |
| C | 6 | 6 | 80 | 80 | 79 | 98.75% | 1 |

Component rows partition the Java cohort; the Java total includes each mutant once. The separate FindPoints row identifies its reduced runtime-checking coverage.

The primary mean across 13 runtime-evaluable Java programs is 100.00%.
The primary mean across 6 runtime-evaluable C programs is 99.28%.

These are language-specific verified cohorts. No confidence interval or significance claim is made here.
The 4 shared verified programs have 54/54 Java and 54/54 C detections among evaluable mutants.

FindPoints uses a runtime copy with only `\fresh(\result)` omitted. Non-nullness, length and all three content branches remain; the full frozen JML is unchanged. Freshness is unchecked for both original and mutants. Original-input checks, deliberately failing controls and independent witness replays are retained in the case artifacts.

Java uses OpenJML RAC and fresh interpreted-JVM witness replay; C uses executable ACSL equivalents and independently sanitized native replay. Original-output differences and EvoSuite assertions are not detection oracles.

The Java frame negative control was not detected, so assignable coverage is not established. C loop invariants, variants, statement assertions and full write/allocation sets remain unchecked. Safety flags can overlap postcondition detections and are not exhaustive after a primary witness stops search.

Original proofs, full frozen contracts and historical verifier-mutant evidence are preserved separately under results/verifier_based/. Verifier-mutant evidence does not contribute to these runtime counts. Active evaluation code and result metadata use no hash validation or digest provenance.

[Java per-program results](java/summary.json) | [C per-program results](c/summary.json) | [Combined summary](summary.json) | [Evidence audit](audit.json)

| Language | Program | Evaluated mutants | Postcondition detections | Separate safety flags |
|---|---|---:|---:|---:|
| java | CountIntgralPoints | 20 | 20 | 4 |
| java | DiameterCircle | 4 | 4 | 2 |
| java | DogAge | 27 | 27 | 0 |
| java | FindPoints | 22 | 22 | 0 |
| java | FindRectNum | 8 | 8 | 0 |
| java | HexagonalNum | 12 | 12 | 2 |
| java | MaxOfTwo | 2 | 2 | 0 |
| java | NoOfCubes | 33 | 33 | 10 |
| java | OddBitSetNumber | 17 | 17 | 0 |
| java | SquarePerimeter | 4 | 4 | 2 |
| java | SumNums | 13 | 13 | 2 |
| java | TestThreeEqual | 22 | 22 | 0 |
| java | VolumeCube | 8 | 8 | 0 |
| c | CountList | 3 | 3 | 0 |
| c | MaxOfTwo | 2 | 2 | 0 |
| c | MaxSubArraySum | 23 | 22 | 1 |
| c | OddBitSetNumber | 17 | 17 | 0 |
| c | SumNums | 13 | 13 | 0 |
| c | TestThreeEqual | 22 | 22 | 0 |
