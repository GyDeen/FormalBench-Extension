
import java.io.*;
import java.lang.*;
import java.util.*;

class MaxOfNth {
    
    public static int maxOfNth(int[][] testArray, int n) {
        int max = Integer.MIN_VALUE; 
        for (int i = 0; i < testArray.length; i++) {
            max = Math.max(max, testArray[i][n]);
        }
        return max;
    }
}

