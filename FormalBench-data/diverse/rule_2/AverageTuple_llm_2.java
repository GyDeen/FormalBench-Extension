
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AverageTuple {

	public static double[] averageTuple(int[][] nums) {
		int rows = nums.length;
		int cols = nums[0].length;
		double[] result = new double[cols];

		int start = 0;
		while (start < cols) {
			double sum = 0;
			for (int j = 0; j < rows; j++) {
				sum += nums[j][start];
			}
			result[start] = sum / rows;
			start++;
		}
		return result;
	}
}
