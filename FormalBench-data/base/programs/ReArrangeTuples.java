
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReArrangeTuples {
    
    public static int[][] reArrangeTuples(int[][] testArray, int[] ordArray) {
        int n = ordArray.length;
        int[][] res = new int[n][2]; 
        
        int index = 0; 
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < testArray.length; j++) {
                if (testArray[j][0] == ordArray[i]) {
                    res[index++] = testArray[j];
                    break;
                }
            }
        }
        
        return res;
    }
}

