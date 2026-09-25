import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class MaximumSegments {
    
    /*@ public normal_behavior
      @ ensures \result == (a >= b ? a : b);
      @ model public static pure \bigint fb_max(\bigint a, \bigint b);
      @*/
    /*@ public normal_behavior
      @ requires value >= -1;
      @ ensures \result == (value == -1 ? -1 : value + 1);
      @ model public static pure \bigint fb_extend(\bigint value);
      @*/
    /*@ public normal_behavior
      @ requires a > 0 && b > 0 && c > 0;
      @ ensures \result == (n < 0 ? -1 : n == 0 ? 0 : fb_max(fb_extend(fb_segments(n - a, a, b, c)), fb_max(fb_extend(fb_segments(n - b, a, b, c)), fb_extend(fb_segments(n - c, a, b, c)))));
      @ ensures \result >= -1;
      @ measured_by (n > 0 ? n : 0);
      @ model public static pure \bigint fb_segments(\bigint n, int a, int b, int c);
      @*/
    /*@ public normal_behavior
      @ requires 0 <= n < Integer.MAX_VALUE && a > 0 && b > 0 && c > 0;
      @ assignable \nothing;
      @ ensures \result == fb_segments(n, a, b, c);
      @ ensures -1 <= \result <= n;
      @*/
    //@ code_java_math
    public static int maximumSegments(int n, int a, int b, int c) {
        int dp[] = new int[n+1];
        dp[0] = 0;
        /*@ loop_invariant 1 <= i <= n + 1;
          @ loop_invariant (\forall int k; 0 <= k < i; dp[k] == fb_segments(k, a, b, c) && -1 <= dp[k] <= k);
          @ loop_writes i, dp[*];
          @ decreases (\bigint)n - i + 1;
          @*/
        for(int i = 1; i <= n; i++){
            dp[i] = -1;
            if(i >= a && dp[i-a] != -1){
                dp[i] = Math.max(dp[i], dp[i-a]+1);
            }
            if(i >= b && dp[i-b] != -1){
                dp[i] = Math.max(dp[i], dp[i-b]+1);
            }
            if(i >= c && dp[i-c] != -1){
                dp[i] = Math.max(dp[i], dp[i-c]+1);
            }
        }
        return dp[n];
    }
}

