import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class CountWays {
    
    /*@ public normal_behavior
      @ ensures \result == ((x % 4294967296L + 6442450944L) % 4294967296L) - 2147483648L;
      @ model public static pure \bigint fb_wrap(\bigint x);
      @*/
    /*@ public normal_behavior
      @ requires k >= 0;
      @ ensures \result == (k == 0 ? 1 : k == 1 ? 0 : fb_A(k - 2) + 2 * fb_B(k - 1));
      @ measured_by k;
      @ model public static pure \bigint fb_A(\bigint k);
      @*/
    /*@ public normal_behavior
      @ requires k >= 0;
      @ ensures \result == (k == 0 ? 0 : k == 1 ? 1 : fb_A(k - 1) + fb_B(k - 2));
      @ measured_by k;
      @ model public static pure \bigint fb_B(\bigint k);
      @*/
    /*@ public normal_behavior
      @ requires 1 <= n < Integer.MAX_VALUE;
      @ assignable \nothing;
      @ ensures \result == fb_wrap(fb_A(n));
      @ also
      @ public exceptional_behavior
      @ requires n == -1 || n == 0;
      @ assignable \nothing;
      @ signals_only ArrayIndexOutOfBoundsException;
      @ also
      @ public exceptional_behavior
      @ requires n < -1 || n == Integer.MAX_VALUE;
      @ assignable \nothing;
      @ signals_only NegativeArraySizeException;
      @*/
    //@ code_java_math
    public static int countWays(int n) {
        int[] A = new int[n+1];
        int[] B = new int[n+1];
        A[0] = 1;
        A[1] = 0;
        B[0] = 0;
        B[1] = 1;
        /*@ loop_invariant 2 <= i <= n + 1;
          @ loop_invariant (\forall int k; 0 <= k < i; A[k] == fb_wrap(fb_A(k)) && B[k] == fb_wrap(fb_B(k)));
          @ loop_writes i, A[*], B[*];
          @ decreases (\bigint)n - i + 1;
          @*/
        for(int i = 2; i <= n; i++) {
            A[i] = A[i-2] + 2 * B[i-1];
            B[i] = A[i-1] + B[i-2];
        }
        return A[n];
    }
}

