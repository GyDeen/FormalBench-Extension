
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NthNums {
    
    public static int[] nthNums(int[] nums, int n) {
        int[] nth = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            nth[i] = (int) (Math.pow(nums[i], n));
        }
        return nth;
    }
}

