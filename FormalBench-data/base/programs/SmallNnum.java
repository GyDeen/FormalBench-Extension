
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SmallNnum {
    
    public static int[] smallNnum(int[] inputArray, int n) {
        int[] sortedArray = Arrays.copyOf(inputArray, inputArray.length);
        Arrays.sort(sortedArray);
        
        return Arrays.copyOf(sortedArray, n);
    }
}

