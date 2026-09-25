
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxSumOfThreeConsecutive {
    
    /*@ public normal_behavior
      @ ensures \result == ((x % 4294967296L + 6442450944L) % 4294967296L) - 2147483648L;
      @ model public static pure \bigint fb_wrap(\bigint x);
      @*/
    /*@ public normal_behavior
      @ ensures \result == (a >= b ? a : b);
      @ model public static pure \bigint fb_max(\bigint a, \bigint b);
      @*/
    /*@ public normal_behavior
      @ requires a != null && 0 <= k < a.length;
      @ ensures \result == (k == 0 ? a[0] : k == 1 ? fb_wrap((\bigint)a[0] + a[1]) : k == 2 ? fb_max(fb_three(a, 1), fb_max(fb_wrap((\bigint)a[1] + a[2]), fb_wrap((\bigint)a[0] + a[2]))) : fb_max(fb_max(fb_three(a, k - 1), fb_wrap(fb_three(a, k - 2) + a[k])), fb_wrap((\bigint)a[k] + a[k - 1] + fb_three(a, k - 3))));
      @ measured_by k;
      @ model public static pure \bigint fb_three(int[] a, int k);
      @*/
    /*@ public normal_behavior
      @ requires arr != null && 1 <= n <= arr.length;
      @ assignable \nothing;
      @ ensures \result == fb_three(arr, n - 1);
      @ also
      @ public exceptional_behavior
      @ requires n < 0;
      @ assignable \nothing;
      @ signals_only NegativeArraySizeException;
      @ also
      @ public exceptional_behavior
      @ requires n == 0 || (n > 0 && arr != null && n > arr.length);
      @ assignable \nothing;
      @ signals_only ArrayIndexOutOfBoundsException;
      @ also
      @ public exceptional_behavior
      @ requires n > 0 && arr == null;
      @ assignable \nothing;
      @ signals_only NullPointerException;
      @*/
    //@ code_java_math
    public static int maxSumOfThreeConsecutive(int[] arr, int n) {
        int[] sum = new int[n];
        if (n >= 1) {
            sum[0] = arr[0];
        }
        if (n >= 2) {
            sum[1] = sum[0] + arr[1];
        }
        if (n > 2) {
            sum[2] = Math.max(sum[1], Math.max(arr[1] + arr[2], arr[0] + arr[2]));
        }
        /*@ loop_invariant 3 <= i && (n < 3 ? i == 3 : i <= n);
          @ loop_invariant (\forall int k; 0 <= k < i && k < n; sum[k] == fb_three(arr, k));
          @ loop_writes i, sum[*];
          @ decreases (\bigint)n - i;
          @*/
        for (int i = 3; i < n; i++) {
            sum[i] = Math.max(Math.max(sum[i - 1], sum[i - 2] + arr[i]), arr[i] + arr[i - 1] + sum[i - 3]);
        }
        return sum[n - 1];
    }
}

