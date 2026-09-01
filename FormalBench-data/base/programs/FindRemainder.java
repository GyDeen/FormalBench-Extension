
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindRemainder {
    
    public static int findRemainder(int[] arr, int lens, int n) {
        long product = 1;
        for (int i = 0; i < lens; i++) {
            product *= arr[i];
        }
        return (int)(product % n);
    }
}

