
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxDifference {
    
    /*@ public normal_behavior
      @ ensures \result == (\java_math(x - y) < 0 ? \java_math(-(x - y)) : \java_math(x - y));
      @ model public static pure int fb_absdiff(int x, int y);
      @*/
    /*@ public normal_behavior
      @ requires a != null && 0 <= k <= a.length;
      @ requires (\forall int j; 0 <= j < k; a[j] != null && a[j].length >= 2);
      @ ensures \result == (k == 0 ? 0 : fb_difference(a, k - 1) >= fb_absdiff(a[k - 1][0], a[k - 1][1]) ? fb_difference(a, k - 1) : fb_absdiff(a[k - 1][0], a[k - 1][1]));
      @ measured_by k;
      @ model public static pure int fb_difference(int[][] a, int k);
      @*/
    /*@ public normal_behavior
      @ requires testArray != null && (\forall int k; 0 <= k < testArray.length; testArray[k] != null && testArray[k].length >= 2);
      @ assignable \nothing;
      @ ensures \result == fb_difference(testArray, testArray.length > 0 ? testArray.length - 1 : 0);
      @ also
      @ public exceptional_behavior
      @ requires testArray == null || (\exists int k; 0 <= k < testArray.length; testArray[k] == null || testArray[k].length < 2);
      @ assignable \nothing;
      @ signals_only NullPointerException, ArrayIndexOutOfBoundsException;
      @*/
    //@ code_java_math
    public static int maxDifference(int[][] testArray) {
        int maxDiff = 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        
        /*@ loop_invariant 0 <= i <= testArray.length;
          @ loop_invariant maxDiff == fb_difference(testArray, i < testArray.length ? i : (testArray.length > 0 ? testArray.length - 1 : 0));
          @ loop_writes i, maxDiff, min, max;
          @ decreases testArray.length - i;
          @*/
        for (int i = 0; i < testArray.length; i++) {
            int a = testArray[i][0];
            int b = testArray[i][1];
            
            min = Math.min(min, a);
            min = Math.min(min, b);
            max = Math.max(max, a);
            max = Math.max(max, b);
            
            if (i != testArray.length - 1) {
                maxDiff = Math.max(maxDiff, Math.abs(a - b));
            }
        }
        
        return maxDiff;
    }
}

