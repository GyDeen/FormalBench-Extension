
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RadixSort {
    
    /*@ public normal_behavior
      @ ensures \result == (a <= b ? a : b);
      @ model public static pure \bigint fb_min(\bigint a, \bigint b);
      @*/
    /*@ public normal_behavior
      @ ensures \result == (a >= b ? a : b);
      @ model public static pure \bigint fb_max(\bigint a, \bigint b);
      @*/
    /*@ public normal_behavior
      @ requires a != null && 1 <= k <= a.length;
      @ ensures \result == (k == 1 ? a[0] : fb_min(fb_minval(a, k - 1), a[k - 1]));
      @ measured_by k;
      @ model public static pure \bigint fb_minval(int[] a, int k);
      @*/
    /*@ public normal_behavior
      @ requires a != null && 1 <= k <= a.length;
      @ ensures \result == (k == 1 ? a[0] : fb_max(fb_maxval(a, k - 1), a[k - 1]));
      @ measured_by k;
      @ model public static pure \bigint fb_maxval(int[] a, int k);
      @*/
    /*@ public normal_behavior
      @ requires nums != null && nums.length > 0 && fb_maxval(nums, nums.length) - fb_minval(nums, nums.length) < Integer.MAX_VALUE;
      @ assignable nums[*];
      @ ensures \result == nums;
      @ ensures (\forall int p, q; 0 <= p < q < \result.length; \result[p] <= \result[q]);
      @ ensures (\forall int v; (\num_of int k; 0 <= k < \result.length; \result[k] == v) == \old(\num_of int k; 0 <= k < nums.length; nums[k] == v));
      @ also
      @ public exceptional_behavior
      @ requires nums == null;
      @ assignable \nothing;
      @ signals_only NullPointerException;
      @ also
      @ public exceptional_behavior
      @ requires nums != null && nums.length > 0 && Integer.MAX_VALUE <= fb_maxval(nums, nums.length) - fb_minval(nums, nums.length) && fb_maxval(nums, nums.length) - fb_minval(nums, nums.length) < 4294967295L;
      @ assignable \nothing;
      @ signals_only NegativeArraySizeException;
      @ also
      @ public exceptional_behavior
      @ requires nums != null && nums.length > 0 && fb_maxval(nums, nums.length) - fb_minval(nums, nums.length) == 4294967295L;
      @ assignable \nothing;
      @ signals_only ArrayIndexOutOfBoundsException;
      @ 
      @ also
      @ public exceptional_behavior
      @ requires nums != null && nums.length == 0;
      @ assignable \nothing;
      @ signals_only ArrayIndexOutOfBoundsException;
      @*/
    //@ code_java_math
    public static int[] radixSort(int[] nums) {
        int max = nums[0];
        int min = nums[0];

        /*@ loop_invariant 0 <= \count <= nums.length;
          @ loop_invariant min == fb_minval(nums, \count > 0 ? \count : 1);
          @ loop_invariant max == fb_maxval(nums, \count > 0 ? \count : 1);
          @ loop_writes min, max;
          @ decreases nums.length - \count;
          @*/
        for (int num : nums) {
            if (num > max) max = num;
            if (num < min) min = num;
        }

        int range = max - min + 1;
        int[] bucket = new int[range];
        
        /*@ loop_invariant 0 <= \count <= nums.length;
          @ loop_invariant (\forall int v; 0 <= v < bucket.length; bucket[v] == (\num_of int k; 0 <= k < \count; nums[k] == (\bigint)min + v));
          @ loop_writes bucket[*];
          @ decreases nums.length - \count;
          @*/
        for (int num : nums) {
            bucket[num - min]++;
        }

        int pos = 0;
        /*@ loop_invariant 0 <= i <= bucket.length && 0 <= pos <= nums.length;
          @ loop_invariant pos == ((\num_of int k; 0 <= k < nums.length; \old(nums[k]) < (\bigint)min + i));
          @ loop_invariant (\forall int p, q; 0 <= p < q < pos; nums[p] <= nums[q]);
          @ loop_invariant (\forall int v; (\num_of int k; 0 <= k < pos; nums[k] == v) == (v < (\bigint)min + i ? (\num_of int k; 0 <= k < nums.length; \old(nums[k]) == v) : 0));
          @ loop_writes i, pos, nums[*];
          @ decreases bucket.length - i;
          @*/
        for (int i = 0; i < range; i++) {
            /*@ loop_invariant 0 <= j <= bucket[i] && 0 <= pos <= nums.length;
              @ loop_invariant pos == ((\num_of int k; 0 <= k < nums.length; \old(nums[k]) < (\bigint)min + i)) + j;
              @ loop_invariant (\forall int p, q; 0 <= p < q < pos; nums[p] <= nums[q]);
              @ loop_invariant (\forall int v; (\num_of int k; 0 <= k < pos; nums[k] == v) == (v < (\bigint)min + i ? (\num_of int k; 0 <= k < nums.length; \old(nums[k]) == v) : v == (\bigint)min + i ? j : 0));
              @ loop_writes j, pos, nums[*];
              @ decreases bucket[i] - j;
              @*/
            for (int j = 0; j < bucket[i]; j++) {
                nums[pos++] = i + min;
            }
        }

        return nums;
    }
}

