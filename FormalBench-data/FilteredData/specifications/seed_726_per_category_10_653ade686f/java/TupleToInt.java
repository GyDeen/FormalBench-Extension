
import java.io.*;
import java.lang.*;
import java.math.*;

class TupleToInt {
    
    /*@ public normal_behavior
      @ ensures \result == ((x % 4294967296L + 6442450944L) % 4294967296L) - 2147483648L;
      @ model public static pure \bigint fb_wrap(\bigint x);
      @*/
    /*@ public normal_behavior
      @ requires a != null && 0 <= k <= a.length;
      @ ensures \result == (k == 0 ? 0 : fb_wrap(10 * fb_digits(a, k - 1) + a[k - 1]));
      @ measured_by k;
      @ model public static pure \bigint fb_digits(int[] a, int k);
      @*/
    /*@ public normal_behavior
      @ requires nums != null;
      @ assignable \nothing;
      @ ensures \result == fb_digits(nums, nums.length);
      @ also
      @ public exceptional_behavior
      @ requires nums == null;
      @ assignable \nothing;
      @ signals_only NullPointerException;
      @*/
    //@ code_java_math
    public static int tupleToInt(int[] nums) {
        int result = 0;
        /*@ loop_invariant 0 <= \count <= nums.length;
          @ loop_invariant result == fb_digits(nums, \count);
          @ loop_writes result;
          @ decreases nums.length - \count;
          @*/
        for (int num : nums) {
            result = result * 10 + num;
        }
        return result;
    }
}

