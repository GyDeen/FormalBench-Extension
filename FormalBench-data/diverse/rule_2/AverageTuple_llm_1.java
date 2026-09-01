
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AverageTuple {

	public static double[] averageTuple(int[][] nums) {
		int rows = nums.length;
		int cols = nums[0].length;
		double[] result = new double[cols];

		int offset = 0;
		while (offset < cols) {
			double sum = 0;
			for (int j = 0; j < rows; j++) {
				sum += nums[j][offset];
			}
			result[offset] = sum / rows;
			offset++;
		}
		return result;
	}
}
