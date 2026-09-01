
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class GetInvCount {
    
    public static int getInvCount(int[] arr, int n) {
        int invCount = 0;
        
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] > arr[j]) {
                    invCount++;
                }
            }
        }
        return invCount;
    }
}

