
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class MinJumps {
    
    /*@ public normal_behavior
      @ ensures \result == ((x % 4294967296L + 6442450944L) % 4294967296L) - 2147483648L;
      @ model public static pure \bigint fb_wrap(\bigint x);
      @*/
    /*@ public normal_behavior
      @ ensures \result == (a <= b ? a : b);
      @ model public static pure \bigint fb_min(\bigint a, \bigint b);
      @*/
    /*@ public normal_behavior
      @ requires a != null && 0 <= j <= i <= a.length;
      @ ensures \result == (i == 0 ? 0 : j == 0 ? Integer.MAX_VALUE : fb_min(fb_jumps(a, i, j - 1), \java_math(a[j - 1] + (j - 1)) >= i ? fb_wrap(fb_jumps(a, j - 1, j - 1) + 1) : Integer.MAX_VALUE));
      @ measured_by (\bigint)i * (a.length + 1) + j;
      @ model public static pure \bigint fb_jumps(int[] a, int i, int j);
      @*/
    /*@ public normal_behavior
      @ requires n >= 1 && (n == 1 || (arr != null && n - 1 <= arr.length));
      @ assignable \nothing;
      @ ensures \result == (n == 1 ? 0 : fb_jumps(arr, n - 1, n - 1));
      @*/
    //@ code_java_math
    public static int minJumps(int[] arr, int n) {
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        /*@ loop_invariant 1 <= i <= n;
          @ loop_invariant (\forall int k; 0 <= k < i; dp[k] == (k == 0 ? 0 : fb_jumps(arr, k, k)));
          @ loop_invariant (\forall int k; i <= k < n; dp[k] == Integer.MAX_VALUE);
          @ loop_writes i, dp[*];
          @ decreases n - i;
          @*/
        for (int i = 1; i < n; i++) {
            /*@ loop_invariant 0 <= j <= i;
              @ loop_invariant dp[i] == fb_jumps(arr, i, j);
              @ loop_writes j, dp[i];
              @ decreases i - j;
              @*/
            for (int j = 0; j < i; j++) {
                if (arr[j] + j >= i) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[n - 1];
    }
}

