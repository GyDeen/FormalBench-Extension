
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PairWise {
    
    /*@ public normal_behavior
      @ requires l1 != null;
      @ assignable \nothing;
      @ 
      @ ensures \result != null && \fresh(\result);
      @ ensures \result.length == (l1.length < 2 ? 0 : l1.length - 1);
      @ ensures (\forall int k; 0 <= k < \result.length; \result[k] != null && \fresh(\result[k]) && \result[k].length == 2 && \result[k][0] == l1[k] && \result[k][1] == l1[k + 1]);
      @ ensures (\forall int p, q; 0 <= p < q < \result.length; \result[p] != \result[q]);
      @ 
      @ also
      @ public exceptional_behavior
      @ requires l1 == null;
      @ assignable \nothing;
      @ signals_only NullPointerException;
      @*/
    //@ code_java_math
    public static int[][] pairWise(int[] l1) {
        if (l1.length < 2) {
            return new int[0][0]; // Return an empty array
        }
        
        int[][] result = new int[l1.length - 1][2]; // The result will have n-1 pairs
        /*@ loop_invariant 0 <= i <= result.length && result.length == l1.length - 1;
          @ loop_invariant (\forall int k; 0 <= k < i; result[k][0] == l1[k] && result[k][1] == l1[k + 1]);
          @ loop_writes i, result[*][*];
          @ decreases result.length - i;
          @*/
        for (int i = 0; i < l1.length - 1; i++) {
            result[i][0] = l1[i];
            result[i][1] = l1[i + 1];
        }
        return result;
    }
}

