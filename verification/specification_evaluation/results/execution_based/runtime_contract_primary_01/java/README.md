# Java runtime-contract mutant detection

The oracle is the frozen generated JML, not original output differences or EvoSuite assertions. Primary detections are postcondition failures reproduced in a fresh interpreted JVM, with an admitted input, a passing original RAC execution, and a normally returning uninstrumented mutant. Safety and other JML failures are separate.

| Program | Selected mutants | Evaluated | Postcondition detections | Safety | Original RAC | Admissible inputs |
|---|---:|---:|---:|---:|---|---:|
| CountIntgralPoints | 20 | 20 | 20 | 4 | original_passed_all_inputs | 4209 |
| DiameterCircle | 4 | 4 | 4 | 2 | original_passed_all_inputs | 16 |
| DogAge | 27 | 27 | 27 | 0 | original_passed_all_inputs | 16 |
| FindPoints | 22 | 22 | 22 | 0 | original_passed_all_inputs | 4212 |
| FindRectNum | 8 | 8 | 8 | 0 | original_passed_all_inputs | 16 |
| HexagonalNum | 12 | 12 | 12 | 2 | original_passed_all_inputs | 16 |
| MaxOfTwo | 2 | 2 | 2 | 0 | original_passed_all_inputs | 135 |
| NoOfCubes | 33 | 33 | 33 | 10 | original_passed_all_inputs | 134 |
| OddBitSetNumber | 17 | 17 | 17 | 0 | original_passed_all_inputs | 42 |
| SquarePerimeter | 4 | 4 | 4 | 2 | original_passed_all_inputs | 16 |
| SumNums | 13 | 13 | 13 | 2 | original_passed_all_inputs | 4210 |
| TestThreeEqual | 22 | 22 | 22 | 0 | original_passed_all_inputs | 614 |
| VolumeCube | 8 | 8 | 8 | 0 | original_passed_all_inputs | 16 |

Runtime-evaluable mutants: **192/192 (100.00%)**.
Unavailable mutants: **0**. They are unresolved, not satisfied contracts.

## Procedure and coverage

- Eligible originals: programs proved by the recorded source OpenJML ESC consistency run. Programs with incompatible RAC instrumentation remain visible as unavailable.
- Saved EvoSuite first calls are tried before the withdrawn C scalar bounded recipe: seed 726; all tuples over {-2,-1,0,1,2,3,4,7}; 120 seeded tuples including int boundaries; additional single-bit inputs for OddBitSetNumber. The old unused array-generation RNG consumption is preserved. Duplicate tuples are tried once. No solver candidates or EvoSuite assertions are used.
- Selected frozen entry contracts have no explicit requires and accept primitive int arguments. Input files record unique candidates and admissible counts before execution; zero inputs are excluded by contract preconditions.
- Frozen annotations are inserted at the matching entry method, with only explicitly recorded runtime omissions applied symmetrically to originals and mutants. The full frozen contract and runtime copy are saved separately. Signature mismatches, internal annotations, or pre-existing annotations fail visibly. No generated repair is applied. No hash validation or hash provenance is added.
- Each input uses a fresh classloader to restore mutant static state. Search timeout is 0.5 seconds per input after JVM startup; witness replay is 2 seconds in a fresh JVM with -Xint. Three timeouts stop a case. Search stops at the first reproduced postcondition violation; safety encountered earlier is retained. These counts do not exhaustively enumerate every failure category after detection.
- 4 workers, bounded CPU affinity, 192 MB per JVM. Compile timeout 60 seconds. Java arithmetic follows executable int operations and the frozen code_java_math/java_math clauses. No ESC solver is used in this run.
- Compiler diagnostics for unsupported or non-executable constructs are retained and block execution. Successful compilation is supplemented with an artificial failing postcondition control for each executable program, entry-precondition and frame controls, and original executions over the full pool.
- Freshness is explicitly unchecked in the runtime projection for both original and mutants; other return non-null, length and content postconditions remain. Frozen JML is unchanged.
- Loop invariants, variants, and user assertions are absent from these frozen contracts. Their support is not established by this run.
- The assignable-nothing negative control compiled successfully but returned normally after a forbidden static-field write. Frame checking is therefore NOT confirmed in installed OpenJML 21.0.27, even though no unsupported warning appeared. This restriction is recorded in every case coverage record; only postcondition detections form the primary measure. See calibration/entry_and_frame/result.json.

## Artifacts

run.json records configuration and installed versions; inputs/ records every admitted input and provenance; calibration/ contains positive detection and precondition/frame controls; cases/ contains exact frozen JML, compile diagnostics, per-input trials, witnesses and independent replays; summary.json and mutants.csv provide counts.

This measure is test-based mutation completeness, conditioned on executable clauses and input coverage. It is not numerically interchangeable with the old ESC/WP or FormalBench verifier-outcome measure. The archived C results remain untouched.

## Integrated FindPoints adaptation

FindPoints is reported as a separate program row with freshness unchecked for both original and mutants. Its full frozen JML is retained. The other twelve program results are reused unchanged. No executions were repeated for this integration. Full-contract baseline evidence is preserved in baseline_before_freshness_integration/.
