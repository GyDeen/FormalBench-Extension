
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class OddLengthSum {
    
    /*@ public normal_behavior
      @ ensures \result == ((x % 4294967296L + 6442450944L) % 4294967296L) - 2147483648L;
      @ model public static pure \bigint fb_wrap(\bigint x);
      @*/
    /*@ public normal_behavior
      @ requires arr != null;
      @ assignable \nothing;
      @ 
      @ ensures \result == fb_wrap((\sum int k; 0 <= k < arr.length; (\bigint)\java_math((((k + 1) * (arr.length - k) + 1) / 2) * arr[k])));
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
          @ loop_invariant sum == fb_wrap((\sum int k; 0 <= k < i; (\bigint)\java_math((((k + 1) * (l - k) + 1) / 2) * arr[k])));
          @ loop_writes i, sum;
          @ decreases l - i;
          @*/
        for (int i = 0; i < l; i++) {
            sum += ((((i + 1) * (l - i) + 1) / 2) * arr[i]);
        }
        return sum;
    }
}

