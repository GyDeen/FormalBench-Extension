
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Solve {
    
    public static Boolean solve(int[] a, int n) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (a[i] > a[j]) {
                    count++;
                }
            }
        }
        return count <= 1;
    }
}

