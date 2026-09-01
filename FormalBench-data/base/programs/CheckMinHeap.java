
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckMinHeap {
    
    public static Boolean checkMinHeap(int[] arr, int i) {
        if (i >= arr.length / 2) {
            return true;
        }
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < arr.length && arr[i] > arr[left]) {
            return false;
        }
        if (right < arr.length && arr[i] > arr[right]) {
            return false;
        }
        return checkMinHeap(arr, left) && checkMinHeap(arr, right);
    }
}

