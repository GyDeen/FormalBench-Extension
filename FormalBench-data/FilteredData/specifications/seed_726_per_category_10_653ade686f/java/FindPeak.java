
import java.io.*;
import java.lang.*;
import java.math.*;

class FindPeak {
    
    /*@ public normal_behavior
      @ requires a != null && 0 <= lo <= hi < a.length;
      @ ensures \result == (lo == hi ? lo : a[lo + (hi - lo) / 2] < a[lo + (hi - lo) / 2 + 1] ? fb_peak(a, lo + (hi - lo) / 2 + 1, hi) : fb_peak(a, lo, lo + (hi - lo) / 2));
      @ measured_by hi - lo;
      @ model public static pure int fb_peak(int[] a, int lo, int hi);
      @*/
    /*@ public normal_behavior
      @ requires n >= 0;
      @ requires n <= 1 || (arr != null && n <= arr.length);
      @ assignable \nothing;
      @ ensures n <= 1 ==> \result == 0;
      @ ensures n > 1 ==> \result == fb_peak(arr, 0, n - 1);
      @ ensures n > 1 ==> 0 <= \result < n;
      @ ensures n > 1 && \result > 0 ==> arr[\result] >= arr[\result - 1];
      @ ensures n > 1 && \result < n - 1 ==> arr[\result] >= arr[\result + 1];
      @*/
    //@ code_java_math
    public static int findPeak(int[] arr, int n) {
        int left = 0, right = n - 1;
        /*@ loop_invariant n <= 1 ==> left == 0 && right == n - 1;
          @ loop_invariant n > 1 ==> 0 <= left <= right < n;
          @ loop_invariant n > 1 ==> fb_peak(arr, left, right) == fb_peak(arr, 0, n - 1);
          @ loop_invariant n > 1 && left > 0 ==> arr[left] >= arr[left - 1];
          @ loop_invariant n > 1 && right < n - 1 ==> arr[right] >= arr[right + 1];
          @ loop_writes left, right;
          @ decreases (\bigint)right - left;
          @*/
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] < arr[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}

