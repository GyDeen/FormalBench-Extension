import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class MedianNumbers {
    
    public static Double medianNumbers(int a, int b, int c) {
        double[] arr = {a, b, c};
        Arrays.sort(arr);
        return arr[arr.length / 2];
    }
}

