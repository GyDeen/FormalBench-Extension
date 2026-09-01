
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindMin {
    
    public static int findMin(int[] arr, int low, int high) {
        if (low > high) {
            return Integer.MAX_VALUE;
        }
        int mid = (low + high) / 2;
        
        if (mid == 0 || mid == arr.length - 1) {
            return arr[mid];
        }
        
        if (arr[mid] < arr[mid - 1] && arr[mid] < arr[mid + 1]) {
            return arr[mid];
        } else if (arr[mid] < arr[mid - 1]) {
            return findMin(arr, mid + 1, high);
        } else {
            return findMin(arr, low, mid - 1);
        }
    }
}

