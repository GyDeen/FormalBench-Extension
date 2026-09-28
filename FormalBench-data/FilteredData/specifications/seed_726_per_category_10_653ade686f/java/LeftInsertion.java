
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LeftInsertion {
    
    /*@
    public normal_behavior
      ensures \result == ((x % 4294967296L + 6442450944L) % 4294967296L) - 2147483648L;
    model public static pure \bigint fb_wrap(\bigint x);

        public normal_behavior
          ensures \result == fb_wrap((\bigint)lo + hi) / 2;
        model public static pure int fb_mid(int lo, int hi);
        public normal_behavior
          requires a != null && 0 <= lo <= a.length && -1 <= hi < a.length && lo <= (\bigint)hi + 1;
          ensures \result == (lo > hi ? lo : fb_mid(lo, hi) < 0 || fb_mid(lo, hi) >= a.length ? -1 :
            a[fb_mid(lo, hi)] == x ? fb_mid(lo, hi) : a[fb_mid(lo, hi)] < x ?
            fb_search(a, x, fb_mid(lo, hi) + 1, hi) : fb_search(a, x, lo, fb_mid(lo, hi) - 1));
          measured_by (\bigint)hi - lo + 1;
        model public static pure int fb_search(int[] a, int x, int lo, int hi);

        public normal_behavior
          requires a != null && fb_search(a, x, 0, a.length - 1) >= 0;
          assignable \nothing;
          ensures \result == fb_search(a, x, 0, a.length - 1);
          ensures 0 <= \result <= a.length;
          ensures (\forall int p, q; 0 <= p < q < a.length; a[p] <= a[q]) ==>
            (\forall int k; 0 <= k < \result; a[k] <= x) && (\forall int k; \result <= k < a.length; x <= a[k]);
          also
        public exceptional_behavior
          requires a == null;
          assignable \nothing;
          signals_only NullPointerException;
          also
        public exceptional_behavior
          requires a != null && fb_search(a, x, 0, a.length - 1) == -1;
          assignable \nothing;
          signals_only ArrayIndexOutOfBoundsException;
    @*/
    //@ code_java_math
    public static int leftInsertion(int[] a, int x) {
        int left = 0;
        int right = a.length - 1;
        /*@
        loop_invariant 0 <= left <= a.length && -1 <= right < a.length && left <= (\bigint)right + 1;
        loop_invariant fb_search(a, x, 0, a.length - 1) == fb_search(a, x, left, right);
        loop_writes left, right;
        decreases (\bigint)right - left + 1;
        @*/
        while (left <= right) {
            int mid = (left + right) / 2;
            if (a[mid] == x) {
                return mid;
            } else if (a[mid] < x) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}

