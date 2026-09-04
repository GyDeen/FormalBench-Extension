
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MoveFirst {
    
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

