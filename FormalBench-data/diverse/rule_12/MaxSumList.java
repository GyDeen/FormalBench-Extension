
import java.io.*;
import java.lang.*;
import java.math.*;

class MaxSumArray {

	public static int[] maxSumArray(int[][] arrays) {
		int maxSum = Integer.MIN_VALUE;
		int[] result = new int[0];

		for (int[] array : arrays) {
			int sum = 0;
			for (int value : array) {
				sum += value;
			}
			if (sum > maxSum) {
				result = array;
				maxSum = sum;
			}
		}
		return result;
	}
}
