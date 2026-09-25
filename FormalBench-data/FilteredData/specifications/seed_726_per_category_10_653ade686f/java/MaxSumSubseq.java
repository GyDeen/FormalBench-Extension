
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxSumSubseq {
    
    /*@ public normal_behavior
      @ ensures \result == ((x % 4294967296L + 6442450944L) % 4294967296L) - 2147483648L;
      @ model public static pure \bigint fb_wrap(\bigint x);
      @*/
    /*@ public normal_behavior
      @ requires a != null && 0 <= k <= a.length;
      @ ensures \result == (k == 0 ? 0 : k == 1 ? a[0] : fb_best(a, k - 1) >= fb_wrap(fb_best(a, k - 2) + a[k - 1]) ? fb_best(a, k - 1) : fb_wrap(fb_best(a, k - 2) + a[k - 1]));
      @ measured_by k;
      @ model public static pure \bigint fb_best(int[] a, int k);
      @*/
    /*@ public normal_behavior
      @ requires a != null && a.length < Integer.MAX_VALUE;
      @ assignable \nothing;
      @ ensures \result == fb_best(a, a.length);
      @ also
      @ public exceptional_behavior
      @ requires a == null;
      @ assignable \nothing;
      @ signals_only NullPointerException;
      @ also
      @ public exceptional_behavior
      @ requires a != null && a.length == Integer.MAX_VALUE;
      @ assignable \nothing;
      @ signals_only NegativeArraySizeException;
      @*/
    //@ code_java_math
    public static int maxSumSubseq(int[] a) {
        int n = a.length;
        if (n == 0) return 0;
        if (n == 1) return a[0];
        
        int[] dp = new int[n + 1];
        dp[0] = 0; // No elements picked, sum is 0
        dp[1] = a[0]; // Only one element picked
        
        /*@ loop_invariant 2 <= i <= n + 1;
          @ loop_invariant (\forall int k; 0 <= k < i; dp[k] == fb_best(a, k));
          @ loop_writes i, dp[*];
          @ decreases (\bigint)n - i + 1;
          @*/
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + a[i - 1]);
        }
        return dp[n];
    }
}

