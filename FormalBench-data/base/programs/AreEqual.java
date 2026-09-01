
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AreEqual {
    
    public static Boolean areEqual(int[] arr1, int[] arr2, int n, int m) {
        if (arr1 == arr2) {
            return true;
        }
        
        if (n != m) {
            return false;
        }
        
        int sum1 = 0, sum2 = 0;
        for (int i = 0; i < n; i++) {
            sum1 += arr1[i];
            sum2 += arr2[i];
        }
        
        return sum1 == sum2;
    }
}

