
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumRangeArray {
    
    /*@ public normal_behavior
      @ ensures \result == ((x % 4294967296L + 6442450944L) % 4294967296L) - 2147483648L;
      @ model public static pure \bigint fb_wrap(\bigint x);
      @*/
    /*@ public normal_behavior
      @ requires m > n || (nums != null && 0 <= m && n < nums.length);
      @ assignable \nothing;
      @ ensures \result == fb_wrap((\sum int k; m <= k && k <= n; (\bigint)nums[k]));
      @ also
      @ public exceptional_behavior
      @ requires m <= n && nums == null;
      @ assignable \nothing;
      @ signals_only NullPointerException;
      @ also
      @ public exceptional_behavior
      @ requires m <= n && nums != null && (m < 0 || n >= nums.length);
      @ assignable \nothing;
      @ signals_only ArrayIndexOutOfBoundsException;
      @*/
    //@ code_java_math
    public static int sumRangeArray(int[] nums, int m, int n) {
        int sum = 0;
        /*@ loop_invariant m <= i && (m > n ? i == m : i <= n + 1);
          @ loop_invariant sum == fb_wrap((\sum int k; m <= k && k < i; (\bigint)nums[k]));
          @ loop_writes i, sum;
          @ decreases (\bigint)n - i + 1;
          @*/
        for (int i = m; i <= n; i++) {
            sum += nums[i];
        }
        return sum;
    }
}

