
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumArray {
    
    public static int[] sumList(int[] arr1, int[] arr2) {
        int minLength = Math.min(arr1.length, arr2.length);
        int[] result = new int[minLength];
        for (int i = 0; i < minLength; i++) {
            result[i] = arr1[i] + arr2[i];
        }
        return result;
    }
}

