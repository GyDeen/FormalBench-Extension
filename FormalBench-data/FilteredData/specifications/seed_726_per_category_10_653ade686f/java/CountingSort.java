
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountingSort {
    
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
      @ requires myArray != null && (myArray.length == 0 || fb_maxval(myArray, myArray.length) - fb_minval(myArray, myArray.length) < Integer.MAX_VALUE);
      @ assignable \nothing;
      @ ensures \result != null && \fresh(\result) && \result.length == myArray.length;
      @ ensures (\forall int p, q; 0 <= p < q < \result.length; \result[p] <= \result[q]);
      @ ensures (\forall int v; (\num_of int k; 0 <= k < \result.length; \result[k] == v) == \old(\num_of int k; 0 <= k < myArray.length; myArray[k] == v));
      @ also
      @ public exceptional_behavior
      @ requires myArray == null;
      @ assignable \nothing;
      @ signals_only NullPointerException;
      @ also
      @ public exceptional_behavior
      @ requires myArray != null && myArray.length > 0 && Integer.MAX_VALUE <= fb_maxval(myArray, myArray.length) - fb_minval(myArray, myArray.length) && fb_maxval(myArray, myArray.length) - fb_minval(myArray, myArray.length) < 4294967295L;
      @ assignable \nothing;
      @ signals_only NegativeArraySizeException;
      @ also
      @ public exceptional_behavior
      @ requires myArray != null && myArray.length > 0 && fb_maxval(myArray, myArray.length) - fb_minval(myArray, myArray.length) == 4294967295L;
      @ assignable \nothing;
      @ signals_only ArrayIndexOutOfBoundsException;
      @*/
    //@ code_java_math
    public static int[] countingSort(int[] myArray) {
        if (myArray.length == 0) {
            return new int[0]; // return an empty array if input is empty
        }
        
        int max = myArray[0];
        int min = myArray[0];

        /*@ loop_invariant 0 <= \count <= myArray.length;
          @ loop_invariant min == fb_minval(myArray, \count > 0 ? \count : 1);
          @ loop_invariant max == fb_maxval(myArray, \count > 0 ? \count : 1);
          @ loop_writes min, max;
          @ decreases myArray.length - \count;
          @*/
        for (int num : myArray) {
            if (num > max) max = num;
            if (num < min) min = num;
        }

        int range = max - min + 1;
        int[] countArray = new int[range];

        /*@ loop_invariant 0 <= i <= myArray.length;
          @ loop_invariant (\forall int v; 0 <= v < countArray.length; countArray[v] == (\num_of int k; 0 <= k < i; myArray[k] == (\bigint)min + v));
          @ loop_writes countArray[*], i;
          @ decreases myArray.length - i;
          @*/
        for (int i = 0; i < myArray.length; i++) {
            countArray[myArray[i] - min]++;
        }

        int index = 0;
        int[] result = new int[myArray.length];

        /*@ loop_invariant 0 <= i <= countArray.length && 0 <= index <= myArray.length;
          @ loop_invariant index == ((\num_of int k; 0 <= k < myArray.length; \old(myArray[k]) < (\bigint)min + i));
          @ loop_invariant (\forall int p, q; 0 <= p < q < index; result[p] <= result[q]);
          @ loop_invariant (\forall int v; (\num_of int k; 0 <= k < index; result[k] == v) == (v < (\bigint)min + i ? (\num_of int k; 0 <= k < myArray.length; \old(myArray[k]) == v) : 0));
          @ loop_writes i, index, result[*];
          @ decreases countArray.length - i;
          @*/
        for (int i = 0; i < countArray.length; i++) {
            /*@ loop_invariant 0 <= j <= countArray[i] && 0 <= index <= myArray.length;
              @ loop_invariant index == ((\num_of int k; 0 <= k < myArray.length; \old(myArray[k]) < (\bigint)min + i)) + j;
              @ loop_invariant (\forall int p, q; 0 <= p < q < index; result[p] <= result[q]);
              @ loop_invariant (\forall int v; (\num_of int k; 0 <= k < index; result[k] == v) == (v < (\bigint)min + i ? (\num_of int k; 0 <= k < myArray.length; \old(myArray[k]) == v) : v == (\bigint)min + i ? j : 0));
              @ loop_writes j, index, result[*];
              @ decreases countArray[i] - j;
              @*/
            for (int j = 0; j < countArray[i]; j++) {
                result[index++] = i + min;
            }
        }
        return result;
    }
}

