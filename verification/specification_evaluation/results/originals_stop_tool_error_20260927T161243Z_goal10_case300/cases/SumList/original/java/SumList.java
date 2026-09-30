
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumArray {
    
    /*@ public normal_behavior
      @ requires arr1 != null && arr2 != null;
      @ assignable \nothing;
      @ 
      @ ensures \result != null && \fresh(\result);
      @ ensures \result.length == (arr1.length < arr2.length ? arr1.length : arr2.length);
      @ ensures (\forall int k; 0 <= k < \result.length; \result[k] == \java_math(arr1[k] + arr2[k]));
      @ 
      @ also
      @ public exceptional_behavior
      @ requires arr1 == null || arr2 == null;
      @ assignable \nothing;
      @ signals_only NullPointerException;
      @*/
    //@ code_java_math
    public static int[] sumList(int[] arr1, int[] arr2) {
        int minLength = Math.min(arr1.length, arr2.length);
        int[] result = new int[minLength];
        /*@ loop_invariant 0 <= i <= minLength && minLength == result.length;
          @ loop_invariant (\forall int k; 0 <= k < i; result[k] == \java_math(arr1[k] + arr2[k]));
          @ loop_writes i, result[*];
          @ decreases minLength - i;
          @*/
        for (int i = 0; i < minLength; i++) {
            result[i] = arr1[i] + arr2[i];
        }
        return result;
    }
}

