
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LargNnum {
    
    public static int[] largNnum(int[] arr, int n) {
        int[] sortedArr = Arrays.copyOf(arr, arr.length);
        
        Arrays.sort(sortedArr);
        for (int i = 0; i < sortedArr.length / 2; i++) {
            int temp = sortedArr[i];
            sortedArr[i] = sortedArr[sortedArr.length - 1 - i];
            sortedArr[sortedArr.length - 1 - i] = temp;
        }
        
        int[] result = Arrays.copyOf(sortedArr, n);
        return result;
    }
}

