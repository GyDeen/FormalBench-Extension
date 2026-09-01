
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AverageTuple {

	public static double[] averageTuple(int[][] nums) {
		int rows = nums.length;
		int cols = nums[0].length;
		double[] result = new double[cols];

		int index = 0;
		while (index < cols) {
			double sum = 0;
			for (int j = 0; j < rows; j++) {
				sum += nums[j][index];
			}
			result[index] = sum / rows;
			index++;
		}
		return result;
	}
}
