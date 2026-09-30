
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MoveFirst {
    
    /*@ public normal_behavior
      @ assignable \nothing;
      @ ensures testArray == null ==> \result == null;
      @ ensures testArray != null && testArray.length == 0 ==> \result == testArray;
      @ ensures testArray != null && testArray.length > 0 ==> \result != null && \fresh(\result) && \result.length == testArray.length;
      @ ensures testArray != null && testArray.length > 0 ==> \result[0] == testArray[testArray.length - 1];
      @ ensures testArray != null && testArray.length > 0 ==> (\forall int k; 1 <= k < testArray.length; \result[k] == testArray[k - 1]);
      @*/
    //@ code_java_math
    public static int[] moveFirst(int[] testArray) {
        if (testArray == null || testArray.length == 0) {
            return testArray; // return the original if null or empty
        }
        
        int[] res = new int[testArray.length];
        res[0] = testArray[testArray.length - 1]; // Move last element to first position
        
        System.arraycopy(testArray, 0, res, 1, testArray.length - 1);
        
        return res;
    }
}

