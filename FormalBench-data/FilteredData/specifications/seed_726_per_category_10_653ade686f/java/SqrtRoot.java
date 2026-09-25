import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class SqrtRoot {
    
    /*@
    public normal_behavior
      ensures \result == ((x % 4294967296L + 6442450944L) % 4294967296L) - 2147483648L;
    model public static pure \bigint fb_wrap(\bigint x);

        public normal_behavior
          requires 0 <= lo <= hi;
          ensures \result == lo + ((\bigint)hi - lo) / 2;
        model public static pure int fb_mid(int lo, int hi);
        public normal_behavior
          requires 0 <= n < Integer.MAX_VALUE && 0 <= lo <= (\bigint)n + 1 && -1 <= hi <= n && lo <= (\bigint)hi + 1;
          ensures \result == (lo > hi ? hi : fb_wrap((\bigint)fb_mid(lo, hi) * fb_mid(lo, hi)) == n ? fb_mid(lo, hi) :
            fb_wrap((\bigint)fb_mid(lo, hi) * fb_mid(lo, hi)) < n ? fb_root(n, fb_mid(lo, hi) + 1, hi) : fb_root(n, lo, fb_mid(lo, hi) - 1));
          measured_by (\bigint)hi - lo + 1;
        model public static pure int fb_root(int n, int lo, int hi);

        public normal_behavior
          assignable \nothing;
          diverges num == Integer.MAX_VALUE;
          ensures num < Integer.MAX_VALUE;
          ensures num < 0 ==> \result == -1;
          ensures 0 <= num < Integer.MAX_VALUE ==> \result == fb_root(num, 0, num);
          ensures 0 <= num <= 46340 ==> 0 <= \result && (\bigint)\result * \result <= num && num < ((\bigint)\result + 1) * ((\bigint)\result + 1);
    @*/
    //@ code_java_math
    public static int sqrtRoot(int num) {
        if (num < 0) {
            return -1;
        }
        int left = 0;
        int right = num;
        /*@
        loop_invariant num < Integer.MAX_VALUE ==> 0 <= left <= (\bigint)num + 1 && -1 <= right <= num && left <= (\bigint)right + 1;
        loop_invariant num < Integer.MAX_VALUE ==> fb_root(num, 0, num) == fb_root(num, left, right);
        loop_writes left, right;
        @*/
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (mid * mid == num) {
                return mid;
            } else if (mid * mid < num) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right;
    }
}

