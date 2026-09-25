
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CombSort {
    
    /*@
    public normal_behavior
      requires g >= 0;
      ensures \result == (int)((double)g / 1.3);
    model public static pure int fb_shrink(int g);
    public normal_behavior
      requires g >= 0;
      ensures \result == (g <= 1 ? 1 : 1 + fb_steps(fb_shrink(g)));
      measured_by g;
    model public static pure int fb_steps(int g);
    public normal_behavior
      requires n >= 0 && t >= 0;
      ensures \result == (t == 0 ? n : fb_shrink(fb_gap(n, t - 1)));
      measured_by t;
    model public static pure int fb_gap(int n, int t);
    public normal_behavior
      requires a != null && 0 <= t <= fb_steps(a.length) && 0 <= p < a.length;
      requires 0 <= k <= a.length - fb_gap(a.length, t);
      ensures \result == (t == 0 ? a[p] : k == 0 || fb_gap(a.length, t) == 0 ?
        fb_value(a, t - 1, a.length - fb_gap(a.length, t - 1), p) :
        p == k - 1 ?
          (fb_value(a, t, k - 1, k - 1) <= fb_value(a, t, k - 1, k - 1 + fb_gap(a.length, t)) ? fb_value(a, t, k - 1, k - 1) : fb_value(a, t, k - 1, k - 1 + fb_gap(a.length, t))) :
        p == k - 1 + fb_gap(a.length, t) ?
          (fb_value(a, t, k - 1, k - 1) >= fb_value(a, t, k - 1, k - 1 + fb_gap(a.length, t)) ? fb_value(a, t, k - 1, k - 1) : fb_value(a, t, k - 1, k - 1 + fb_gap(a.length, t))) :
        fb_value(a, t, k - 1, p));
      measured_by (\bigint)t * ((\bigint)a.length + 1) + k;
    model public static pure int fb_value(int[] a, int t, int k, int p);

    public normal_behavior
      requires nums != null;
      assignable nums[*];
      ensures \result == nums;
      ensures (\forall int p; 0 <= p < nums.length;
        nums[p] == \old(fb_value(nums, fb_steps(nums.length), nums.length, p)));
      ensures nums.length > 0 ==> (\forall int p; 0 <= p < nums.length; nums[p] <= nums[nums.length - 1]);
      also
    public exceptional_behavior
      requires nums == null;
      assignable \nothing;
      signals_only NullPointerException;
    @*/
    //@ code_java_math
    public static int[] combSort(int[] nums) {
        int n = nums.length;
        int gap = n;
        boolean swapped = true;
        //@ ghost int fb_pass = 0;
        
        /*@
        loop_invariant 0 <= fb_pass <= fb_steps(n) && gap == fb_gap(n, fb_pass) && 0 <= gap <= n;
        loop_invariant (\forall int t, p; t == fb_pass && 0 <= p < n; nums[p] == \old(fb_value(nums, t, nums.length - fb_gap(nums.length, t), p)));
        loop_writes gap, swapped, nums[*], fb_pass;
        decreases 2 * (\bigint)gap + (swapped ? 1 : 0);
        @*/
        while (gap > 1 || swapped) {
            gap = (int) (gap / 1.3);
            swapped = false;
            //@ set fb_pass = fb_pass + 1;
            
            /*@
            loop_invariant 0 <= i <= n - gap;
            loop_invariant 1 <= fb_pass <= fb_steps(n) && gap == fb_gap(n, fb_pass);
            loop_invariant (\forall int t, k, p; t == fb_pass && k == i && 0 <= p < n; nums[p] == \old(fb_value(nums, t, k, p)));
            loop_writes i, swapped, nums[*];
            decreases (\bigint)n - gap - i;
            @*/
            for (int i = 0; i + gap < n; i++) {
                if (nums[i] > nums[i + gap]) {
                    int temp = nums[i];
                    nums[i] = nums[i + gap];
                    nums[i + gap] = temp;
                    swapped = true;
                }
            }
        }
        return nums;
    }
}

