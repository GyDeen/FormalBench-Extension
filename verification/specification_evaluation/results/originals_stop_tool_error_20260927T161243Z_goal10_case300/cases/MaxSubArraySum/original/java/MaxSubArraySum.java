
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxSubArraySum {
    
    /*@
    public normal_behavior
      ensures \result == ((x % 4294967296L + 6442450944L) % 4294967296L) - 2147483648L;
    model public static pure \bigint fb_wrap(\bigint x);

        public normal_behavior
          requires a != null && 0 <= k <= a.length;
          ensures \result == (k == 0 ? 0 : fb_wrap(fb_tail(a, k - 1) + a[k - 1]) < 0 ? 0 : fb_wrap(fb_tail(a, k - 1) + a[k - 1]));
          measured_by k;
        model public static pure \bigint fb_tail(int[] a, int k);
        public normal_behavior
          requires a != null && 0 <= k <= a.length;
          ensures \result == (k == 0 ? 0 : fb_best(a, k - 1) >= fb_wrap(fb_tail(a, k - 1) + a[k - 1]) ? fb_best(a, k - 1) : fb_wrap(fb_tail(a, k - 1) + a[k - 1]));
          measured_by k;
        model public static pure \bigint fb_best(int[] a, int k);
        public normal_behavior
          requires a != null && 0 <= k <= a.length;
          ensures \result == (k == 0 ? 0 : fb_wrap(fb_tail(a, k - 1) + a[k - 1]) < 0 ? k : fb_start(a, k - 1));
          measured_by k;
        model public static pure int fb_start(int[] a, int k);
        public normal_behavior
          requires a != null && 0 <= k <= a.length;
          ensures \result == (k == 0 ? 1 : fb_best(a, k - 1) < fb_wrap(fb_tail(a, k - 1) + a[k - 1]) ? k - fb_start(a, k - 1) : fb_length(a, k - 1));
          measured_by k;
        model public static pure int fb_length(int[] a, int k);

        public normal_behavior
          requires size <= 0 || (a != null && size <= a.length);
          assignable \nothing;
          ensures \result == (size <= 0 ? 1 : fb_length(a, size));
          also
        public exceptional_behavior
          requires size > 0 && a == null;
          assignable \nothing;
          signals_only NullPointerException;
          also
        public exceptional_behavior
          requires a != null && size > a.length;
          assignable \nothing;
          signals_only ArrayIndexOutOfBoundsException;
    @*/
    //@ code_java_math
    public static int maxSubArraySum(int[] a, int size) {
        int max_so_far = 0;
        int max_ending_here = 0;
        int start = 0;
        int end = 0;
        int s = 0;

        /*@
        loop_invariant 0 <= i <= (size < 0 ? 0 : size);
        loop_invariant 0 <= s <= i;
        loop_invariant 0 <= start <= end && end <= (i == 0 ? 0 : i - 1);
        loop_invariant a != null && i <= a.length ==> max_ending_here == fb_tail(a, i) && max_so_far == fb_best(a, i) && s == fb_start(a, i) && end - start + 1 == fb_length(a, i);
        loop_writes i, max_so_far, max_ending_here, start, end, s;
        decreases size <= 0 ? 0 : (\bigint)size - i;
        @*/
        for (int i = 0; i < size; i++) {
            max_ending_here += a[i];
            if (max_so_far < max_ending_here) {
                max_so_far = max_ending_here;
                start = s;
                end = i;
            }
            if (max_ending_here < 0) {
                max_ending_here = 0;
                s = i + 1;
            }
        }
        return end - start + 1; // Returns the length of the maximum sum subarray
    }
}

