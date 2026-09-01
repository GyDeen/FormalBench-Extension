
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class KthElement {
    
    public static int kthElement(int[] arr, int n, int k) {
        if (k > n) {
            k = k - n;
        }
        return arr[k - 1];
    }
}

