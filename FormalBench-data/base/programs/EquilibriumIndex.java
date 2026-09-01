
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class EquilibriumIndex {
    
    public static int equilibriumIndex(int[] arr) {
        int sum = 0;
        int leftSum = 0;

        for (int value : arr) {
            sum += value;
        }

        for (int i = 0; i < arr.length; i++) {
            sum -= arr[i];
            if (leftSum == sum) {
                return i;
            }
            leftSum += arr[i];
        }
        return -1;
    }
}

