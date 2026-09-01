
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CombSort {
    
    public static int[] combSort(int[] nums) {
        int n = nums.length;
        int gap = n;
        boolean swapped = true;
        
        while (gap > 1 || swapped) {
            gap = (int) (gap / 1.3);
            swapped = false;
            
            for (int i = 0; i + gap < n; i++) {
                if (nums[i] > nums[i + gap]) {
                    int temp = nums[i];
                    nums[i] = nums[i + gap];
                    nums[i + gap] = temp;
                    swapped = true;
                }
            }
        }
        return nums;
    }
}

