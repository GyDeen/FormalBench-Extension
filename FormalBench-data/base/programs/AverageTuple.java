
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AverageTuple {
    
    public static double[] averageTuple(int[][] nums) {
        int rows = nums.length;
        int cols = nums[0].length;
        double[] result = new double[cols];
        
        for (int i = 0; i < cols; i++) {
            double sum = 0;
            for (int j = 0; j < rows; j++) {
                sum += nums[j][i];
            }
            result[i] = sum / rows;
        }
        return result;
    }
}

