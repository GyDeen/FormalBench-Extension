
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class OddLengthSum {
    
    /*@ public normal_behavior
      @ requires a != null && 0 <= k <= a.length;
      @ ensures \result == (k == 0 ? (\bigint)0 : fb_odd_prefix(a, k - 1) + (\bigint)\java_math(((k * (a.length - (k - 1)) + 1) / 2) * a[k - 1]));
      @ measured_by k;
      @ model public static pure \bigint fb_odd_prefix(int[] a, int k);
      @*/
    /*@ public normal_behavior
      @ ensures \result == ((x % 4294967296L + 6442450944L) % 4294967296L) - 2147483648L;
      @ model public static pure \bigint fb_wrap(\bigint x);
      @*/
    /*@ public normal_behavior
      @ requires arr != null;
      @ assignable \nothing;
      @ 
      @ ensures \result == fb_wrap(fb_odd_prefix(arr, arr.length));
      @ 
      @ also
      @ public exceptional_behavior
      @ requires arr == null;
      @ assignable \nothing;
      @ signals_only NullPointerException;
      @*/
    //@ code_java_math
    public static int oddLengthSum(int[] arr) {
        int sum = 0;
        int l = arr.length;
        /*@ loop_invariant 0 <= i <= l && l == arr.length;
          @ loop_invariant sum == fb_wrap(fb_odd_prefix(arr, i));
          @ loop_writes i, sum;
          @ decreases l - i;
          @*/
        for (int i = 0; i < l; i++) {
            sum += ((((i + 1) * (l - i) + 1) / 2) * arr[i]);
        }
        return sum;
    }
}

