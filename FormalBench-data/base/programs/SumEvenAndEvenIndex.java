
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumEvenAndEvenIndex {
    
    public static int sumEvenAndEvenIndex(int[] arr, int n) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                if (arr[i] % 2 == 0) {
                    count += arr[i];
                }
            }
        }
        return count;
    }
}

