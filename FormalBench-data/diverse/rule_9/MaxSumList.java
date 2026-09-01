
import java.io.*;
import java.lang.*;
import java.math.*;

class MaxSumArray {

	public static int[] maxSumArray(int[][] arrays) {
		int[] result = new int[0];
		int maxSum = Integer.MIN_VALUE;

		for (int[] array : arrays) {
			int sum = 0;
			for (int value : array) {
				sum = sum + value;
			}
			if (sum > maxSum) {
				maxSum = sum;
				result = array;
			}
		}
		return result;
	}
}
