
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MultiplyElements {
    
    /*@ public normal_behavior
      @ requires testTup != null;
      @ assignable \nothing;
      @ 
      @ ensures \result != null && \fresh(\result);
      @ ensures \result.length == (testTup.length < 2 ? 0 : testTup.length - 1);
      @ ensures (\forall int k; 0 <= k < \result.length; \result[k] == \java_math(testTup[k] * testTup[k + 1]));
      @ 
      @ also
      @ public exceptional_behavior
      @ requires testTup == null;
      @ assignable \nothing;
      @ signals_only NullPointerException;
      @*/
    //@ code_java_math
    public static int[] multiplyElements(int[] testTup) {
        if (testTup.length < 2) {
            return new int[0]; // return empty array if input has less than 2 elements
        }
        
        int[] result = new int[testTup.length - 1];
        /*@ loop_invariant 0 <= i <= result.length && result.length == testTup.length - 1;
          @ loop_invariant (\forall int k; 0 <= k < i; result[k] == \java_math(testTup[k] * testTup[k + 1]));
          @ loop_writes i, result[*];
          @ decreases result.length - i;
          @*/
        for (int i = 0; i < testTup.length - 1; i++) {
            result[i] = testTup[i] * testTup[i + 1];
        }
        return result;
    }
}

