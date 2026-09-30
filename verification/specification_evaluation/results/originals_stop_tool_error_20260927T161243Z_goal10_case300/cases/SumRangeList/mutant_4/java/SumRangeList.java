
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumRangeArray {
    
    /*@ public normal_behavior
      @ requires hi <= lo || (a != null && 0 <= lo && hi <= a.length);
      @ ensures \result == (hi <= lo ? (\bigint)0 : fb_range_sum(a, lo, hi - 1) + a[(int)(hi - 1)]);
      @ measured_by hi > lo ? hi - lo : 0;
      @ model public static pure \bigint fb_range_sum(int[] a, \bigint lo, \bigint hi);
      @*/
    /*@ public normal_behavior
      @ ensures \result == ((x % 4294967296L + 6442450944L) % 4294967296L) - 2147483648L;
      @ model public static pure \bigint fb_wrap(\bigint x);
      @*/
    /*@ public normal_behavior
      @ requires m > n || (nums != null && 0 <= m && n < nums.length);
      @ assignable \nothing;
      @ ensures \result == fb_wrap(fb_range_sum(nums, m, (\bigint)n + 1));
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
          @ loop_invariant sum == fb_wrap(fb_range_sum(nums, m, i));
          @ loop_writes i, sum;
          @ decreases (\bigint)n - i + 1;
          @*/
        for (int i = m; i <= n; i++) {
            ;
        }
        return sum;
    }
}

