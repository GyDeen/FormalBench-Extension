import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class SumOfPrimes {
    
    /*@
    public normal_behavior
      ensures \result == (k >= 2 && (\forall int d; 2 <= d && (\bigint)d * d <= k; k % d != 0));
    model public static pure boolean fb_prime(int k);
    public normal_behavior
      ensures \result == (n < 2 ? 0 : fb_primeSum(n - 1) + (fb_prime(n) ? n : 0));
      measured_by n < 2 ? 0 : n;
    model public static pure \bigint fb_primeSum(int n);
    public normal_behavior
      ensures \result == (\forall int d; 2 <= d < limit && (\bigint)d * d <= k; k % d != 0);
    model public static pure boolean fb_unmarked(int k, int limit);

    public normal_behavior
      requires -1 <= n <= 1;
      assignable \nothing;
      ensures \result == 0;
      also
    public normal_behavior
      requires 2 <= n < 46349 && System.out != null;
      assignable System.out.outputText, System.out.endsInNewLine;
      ensures \result == fb_primeSum(n);
      ensures System.out.outputText.startsWith(\old(System.out.outputText));
      ensures System.out.endsInNewLine;
      also
    public exceptional_behavior
      requires n < -1 || n == Integer.MAX_VALUE;
      assignable \nothing;
      signals_only NegativeArraySizeException;
      also
    public exceptional_behavior
      requires 2 <= n < Integer.MAX_VALUE && System.out == null;
      assignable \nothing;
      signals_only NullPointerException;
      also
    public exceptional_behavior
      requires 46349 <= n < Integer.MAX_VALUE && System.out != null;
      assignable System.out.outputText, System.out.endsInNewLine;
      signals_only ArrayIndexOutOfBoundsException;
    @*/
    //@ code_java_math
    public static int sumOfPrimes(int n) {
        int sum = 0;
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        /*@
        loop_invariant 2 <= i <= 46349 && (\bigint)i <= (n < 2 ? 2 : (\bigint)n + 1);
        loop_invariant sum == fb_primeSum(i - 1);
        loop_invariant (\forall int k; 2 <= k <= n; isPrime[k] == fb_unmarked(k, i));
        loop_writes i, sum, isPrime[*], System.out.outputText, System.out.endsInNewLine;
        decreases n < 2 ? 0 : (\bigint)n - i + 1;
        @*/
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                //@ assert fb_prime(i);
                System.out.println(i);
                sum += i;
                /*@
                loop_invariant n < 46349 ==> (\bigint)i * i <= j && (\bigint)j <= (n < (\bigint)i * i ? (\bigint)i * i : (\bigint)n + i);
                loop_invariant n < 46349 ==> j % i == 0;
                loop_invariant n < 46349 ==> (\forall int k; 2 <= k <= n; isPrime[k] == (fb_unmarked(k, i) && !((\bigint)i * i <= k < j && k % i == 0)));
                loop_writes j, isPrime[*];
                decreases 4294967296L - (j < 0 ? (\bigint)j + 4294967296L : (\bigint)j);
                @*/
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        return sum;
    }
}

