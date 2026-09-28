
import java.io.*;
import java.lang.*;
import java.math.*;

class CountList {
    /*@ public normal_behavior
      @ requires a != null && 0 <= k <= a.length;
      @ requires (\forall int i; 0 <= i < k; a[i] != null);
      @ ensures \result == (k == 0 ? (\bigint)0 : fb_nonempty(a, k - 1) + (a[k - 1].length > 0 ? 1 : 0));
      @ measured_by k;
      @ model public static pure \bigint fb_nonempty(int[][] a, int k);
      @*/
    
    /*@ public normal_behavior
      @ requires inputArray != null && (\forall int k; 0 <= k < inputArray.length; inputArray[k] != null);
      @ assignable \nothing;
      @ ensures \result == fb_nonempty(inputArray, inputArray.length);
      @ also
      @ public exceptional_behavior
      @ requires inputArray == null || (\exists int k; 0 <= k < inputArray.length; inputArray[k] == null);
      @ assignable \nothing;
      @ signals_only NullPointerException;
      @*/
    //@ code_java_math
    public static int countList(int[][] inputArray) {
        int count = 0;
        /*@ loop_invariant 0 <= \count <= inputArray.length;
          @ loop_invariant count == fb_nonempty(inputArray, \count);
          @ loop_writes count;
          @ decreases inputArray.length - \count;
          @*/
        for (int[] array : inputArray) {
            if (array.length > 0) {
                count++;
            }
        }
        return count;
    }
}
