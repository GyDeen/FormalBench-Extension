
import java.io.*;
import java.lang.*;
import java.math.*;

class CountList {
    
    /*@ public normal_behavior
      @ requires inputArray != null && (\forall int k; 0 <= k < inputArray.length; inputArray[k] != null);
      @ assignable \nothing;
      @ ensures \result == (\num_of int k; 0 <= k < inputArray.length; inputArray[k].length > 0);
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
          @ loop_invariant count == (\num_of int k; 0 <= k < \count; inputArray[k].length > 0);
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

