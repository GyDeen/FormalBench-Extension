
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SmallestMissing {
    
    public static int smallestMissing(int[] a, int leftElement, int rightElement) {
        int i = leftElement;
        int j = rightElement;
        while (i <= j) {
            int mid = (i + j) / 2;
            if (a[mid] == mid) {
                i = mid + 1;
            } else {
                j = mid - 1;
            }
        }
        return i;
    }
}

