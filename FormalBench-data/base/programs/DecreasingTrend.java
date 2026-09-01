
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DecreasingTrend {
    
    public static Boolean decreasingTrend(int[] nums) {
        int prev = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < prev) {
                return false;
            }
            prev = nums[i];
        }
        return true;
    }
}

