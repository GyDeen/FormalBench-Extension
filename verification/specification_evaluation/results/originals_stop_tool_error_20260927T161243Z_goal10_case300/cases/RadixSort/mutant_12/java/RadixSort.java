
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RadixSort {
    
    /*@ public normal_behavior
      @ requires a != null && 0 <= k <= a.length;
      @ ensures \result == (k == 0 ? (\bigint)0 : fb_count(a, k - 1, v) + (a[k - 1] == v ? 1 : 0));
      @ measured_by k;
      @ model public static pure \bigint fb_count(int[] a, int k, \bigint v);
      @*/
    /*@ public normal_behavior
      @ requires a != null && 0 <= k <= a.length;
      @ ensures \result == (k == 0 ? (\bigint)0 : fb_less(a, k - 1, v) + (a[k - 1] < v ? 1 : 0));
      @ measured_by k;
      @ model public static pure \bigint fb_less(int[] a, int k, \bigint v);
      @*/
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
      @ ensures (\forall int v; fb_count(\result, \result.length, v) == \old(fb_count(nums, nums.length, v)));
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

        /*@ loop_invariant 0 <= ((\count + 0) + 0) <= nums.length;
          @ loop_invariant min == fb_minval(nums, ((\count + 0) + 0) > 0 ? ((\count + 0) + 0) : 1);
          @ loop_invariant max == fb_maxval(nums, ((\count + 0) + 0) > 0 ? ((\count + 0) + 0) : 1);
          @ loop_writes min, max;
          @ decreases nums.length - ((\count + 0) + 0);
          @*/
        for (int num : nums) {
            if (num > max) max = num;
            if (num < min) min = num;
        }

        int range = max / min + 1;
        int[] bucket = new int[range];
        
        /*@ loop_invariant 0 <= ((\count + 0) + 0) <= nums.length;
          @ loop_invariant (\forall int v; 0 <= v < bucket.length; bucket[v] == fb_count(nums, ((\count + 0) + 0), (\bigint)min + v));
          @ loop_writes bucket[*];
          @ decreases nums.length - ((\count + 0) + 0);
          @*/
        for (int num : nums) {
            bucket[num - min]++;
        }

        int pos = 0;
        /*@ loop_invariant 0 <= i <= bucket.length && 0 <= pos <= nums.length;
          @ loop_invariant (\forall \bigint threshold; threshold == (\bigint)min + i; pos == \old(fb_less(nums, nums.length, threshold)));
          @ loop_invariant (\forall int p, q; 0 <= p < q < pos; nums[p] <= nums[q]);
          @ loop_invariant (\forall int v; fb_count(nums, pos, v) == (v < (\bigint)min + i ? \old(fb_count(nums, nums.length, v)) : 0));
          @ loop_writes i, pos, nums[*];
          @ decreases bucket.length - i;
          @*/
        for (int i = 0; i < range; i++) {
            /*@ loop_invariant 0 <= j <= bucket[i] && 0 <= pos <= nums.length;
              @ loop_invariant (\forall \bigint threshold; threshold == (\bigint)min + i; pos == \old(fb_less(nums, nums.length, threshold)) + j);
              @ loop_invariant (\forall int p, q; 0 <= p < q < pos; nums[p] <= nums[q]);
              @ loop_invariant (\forall int v; fb_count(nums, pos, v) == (v < (\bigint)min + i ? \old(fb_count(nums, nums.length, v)) : v == (\bigint)min + i ? j : 0));
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

