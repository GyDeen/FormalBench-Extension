# Filtered-data mismatch investigation

Run: `seed_726_per_category_10_653ade686f`.

The original comparison contains 221 tests and 299 step records. Its 55
mismatched tests combine translation differences, invalid C memory accesses,
resource failures, and harness reporting problems. They are not 55 demonstrated
incorrect return-value translations.

## Verification performed

- Replayed the original bodies of all 221 semantic EvoSuite tests against the
  selected Java sources. This diagnostic bypassed JSON input extraction and
  harness generation. It retained local declarations, assignments, target-call
  order, and try/catch blocks. Target-call proxies logged actual argument values,
  return values, and exceptions. JUnit value assertions and verification helpers
  were disabled; `fail()` still threw. This was a call-behavior audit, not a full
  EvoSuite/JUnit run with its instrumentation and scaffolding.
- Used the same two-second process timeout. There were 275 reached calls and 24
  later calls that could not be reached because a preceding call failed or hung.
- Compared the actual arguments of all 275 reached calls with the extracted
  manifest, accounting for argument mutations and result references. No argument
  value differences were observed.
- Recompiled 19 selected C test harnesses with AddressSanitizer and
  UndefinedBehaviorSanitizer to investigate successful-looking C returns and
  native crashes. These were diagnostic builds; existing result files were not
  replaced.

## C returned a value but Java did not

There are 32 such step records, each belonging to a different test:

| Java outcome in the original report | Count | Independent verification |
| --- | ---: | --- |
| Null dereference | 13 | Original Java calls throw NullPointerException too |
| Array bounds error | 11 | Original Java calls throw ArrayIndexOutOfBoundsException too |
| Negative array size, recorded as generic exception | 7 | Original Java calls throw NegativeArraySizeException too |
| Runner protocol error | 1 | Original Java returns 401188; stdout prevents JSON decoding |

Thus 31 Java exceptions are reproduced independently of our input construction.
The remaining case is a confirmed harness output-protocol problem.

### Null-array representation

Affected tests: `CombSort.test0`, `CountList.test0`, `CountingSort.test1`,
`DiffEvenOdd.test3`, `LeftInsertion.test1`, `MaxDifference.test1`,
`MaxSumSubseq.test2`, `MultiplyElements.test1`, `OddLengthSum.test2`,
`PairWise.test1`, `RadixSort.test1`, `SumArray.test1`, and `TupleToInt.test2`.
Test names here omit the `_ESTest` suffix for readability.

The input JSON correctly contains null arrays. The Java harness passes `null`.
The C harness passes an array/matrix struct with a null pointer and zero length.
The same struct representation is used for empty arrays. Many C translations
then skip loops or return early, whereas Java throws when it accesses a null
array. This is a limitation of the C representation and translation contract,
not an invented null in Java input extraction.

`MoveFirst.test2` is another consequence: both languages return, but Java returns
null and the C serializer emits an empty JSON array. It is outside the 32 cases
above because Java did produce a return value.

### Array bounds errors

Eight previously returning C calls produced sanitizer memory-access errors:

| Test | C diagnostic |
| --- | --- |
| CountWays.test3 | Heap buffer overflow |
| FindPeak.test2 | Stack buffer overflow |
| MaxDifference.test2 | Stack buffer overflow |
| MaxSubArraySum.test3 | Stack buffer overflow |
| MaximumSegments.test0 | Heap buffer overflow |
| MinCost.test5 | Stack buffer overflow |
| MinJumps.test5 | Stack buffer overflow |
| SumRangeArray.test3 | Stack buffer underflow |

For example, the original `FindPeak.test2` passes an array of length 5 and a
requested size of 1539. Its first array index is 769. The original test explicitly
expects a bounds exception. The reported C return of 1508 came from an execution
with invalid memory access, so it is not a reliable semantic result.

The other three cases, `MaxProduct.test5`, `MaxSumOfThreeConsecutive.test7`, and
`RadixSort.test2`, return through C guards for zero sizes/empty arrays that are
absent from the Java implementation. Their sanitizer reruns returned normally.

### Negative array sizes

Affected tests: `CountWays.test2`, `MaxProduct.test4`,
`MaxSumOfThreeConsecutive.test5`, `MaximumSegments.test2`, `MinCost.test4`,
`MinJumps.test4`, and `SumOfPrimes.test2`.

The negative values are present in the original tests. Java attempts an array
allocation and throws. Six translations have explicit guards returning a
sentinel for negative sizes. `CountWays` instead attempts allocation through
`calloc` and has an allocation-failure return path. These C return paths differ
from Java exception behavior.

### SumOfPrimes output protocol

`SumOfPrimes.test3`, with input 2422, returns 401188 in the independent Java
replay, exactly matching C. Its Java source also prints primes to stdout. The
runner attempts to decode all stdout as one JSON document, causing a false
protocol mismatch.

The second protocol error, `SumOfPrimes.test1`, has input 401188. The original
Java call actually throws ArrayIndexOutOfBoundsException; printed primes mask
that exception in the existing report. The C sanitizer build reports an invalid
access in `sumOfPrimes`. Separating logs from result JSON would expose this
exception/crash comparison, not turn this test into a successful numeric match.

## Java stack overflow versus C process crash

The original report has 18 Java stack-overflow step records paired with C crashes.
There are only seven independently reached failure sites:

| Original test | First failing call | Cause visible in source |
| --- | --- | --- |
| DealnnoyNum.test2 | call_0 | Arguments 1 and -13 enter unbounded recursive descent |
| DealnnoyNum.test4 | call_1 | Arguments -1 and -347 enter unbounded recursive descent |
| Fibonacci.test0 | call_0 | Input -1 recurses away from its base cases |
| NewmanPrime.test0 | call_0 | Input -1 recurses away from its base cases |
| MinCoins.test04 | call_0 | A zero coin leads to a recursive call with the same remaining value |
| MinCoins.test11 | call_0 | Zero-filled coins lead to recursion without progress |
| MinCoins.test13 | call_0 | Negative coins initially increase the remaining value and cause excessive recursion |

All seven reproduce StackOverflowError when replaying the original Java test
bodies. All seven corresponding C sanitizer runs identify stack overflow.
These are consistent resource failures for these inputs. A bare SIGSEGV is not
sufficient to classify arbitrary C crashes as stack overflow; the sanitizer
evidence supports these specific cases.

## Harness attribution error: unreached calls

Each requested step is executed by replaying the whole prefix of its test.
Java catches exceptions around the entire dispatch, and the Python collector
attaches the resulting outcome to the requested step. Neither records which
prefix call actually failed.

For example, `DealnnoyNum.test4.call_3` is `dealnnoyNum(0, -3882)`, which would
return immediately. Its recorded stack overflow actually occurs in `call_1`,
before `call_3` is reached.

The independent replay identified 24 unreachable later steps:

| Test | Unreached calls | Original misleading label |
| --- | --- | --- |
| DealnnoyNum.test0 | call_7 through call_19 | 13 timeouts |
| DealnnoyNum.test4 | call_2 through call_8 | 7 stack overflows |
| MinCoins.test04 | call_1 through call_2 | 2 stack overflows |
| MinCoins.test11 | call_1 through call_2 | 2 stack overflows |

Consequently, 18 recorded stack overflows include 11 repeated-prefix failures,
and 17 recorded timeouts include 13 repeated-prefix timeouts. These later steps
should be marked not reached, with the preceding failure identified. They should
not count as independently tested matching or mismatching calls.

## Input extraction findings

For this dataset, actual argument values agreed at every reached call. The only
array-element argument is `MinCoins.test13`'s `intArray0[6]`; the original setup
assigns 2125 before the first call, and extraction correctly stores 2125.

However, small in-memory counterexamples confirm broader extraction limitations:

1. **Assignments lose timing.** `Demo.consume(a); a[0] = 7; Demo.consume(a);`
   mutates the single stored fixture during extraction. Both generated calls
   therefore start from the final fixture, even if the first should see zero.
2. **Array-element reads can become stale.** `Demo.mutate(a); Demo.consume(a[0]);`
   is extracted using the pre-execution element value. Extraction does not run
   `mutate`, so the second argument can be wrong. The earlier explanation of
   `array_access` needs this qualification: static resolution is valid only when
   the value is known not to have changed through earlier calls.
3. **Catch-and-continue semantics are lost.** A call inside a caught exception
   block followed by another call is flattened into an unconditional sequence.
   The harness stops at the first exception instead of continuing as the original
   test does.

The current dataset does not exhibit these three patterns in a way that changes
the reached argument values: assignments after earlier calls initialize newly
created fixtures before their first use; the element read precedes any mutation;
and no target calls follow catch blocks. Initializing all fixtures before the
first generated call still means `state_after` can include fixtures that the
original test had not yet allocated.

## Recommended implementation order

1. Give result JSON a dedicated channel and capture program stdout separately.
2. Record the active call and mark later calls not reached after prefix failures.
3. Add sanitizer-aware C outcomes and distinguish verified stack overflow from
   an otherwise unclassified native crash.
4. Preserve assignments, element reads, and exception continuation in the input
   schema, or reject unsupported sequencing explicitly during extraction.
5. Define the intended treatment of nulls, invalid sizes, and exceptional inputs
   before interpreting these results as translation accuracy.

No execution, extraction, translation, or comparison implementation was changed
during this investigation. The existing comparison files remain the original
run; the observations above are additional diagnostic evidence.
