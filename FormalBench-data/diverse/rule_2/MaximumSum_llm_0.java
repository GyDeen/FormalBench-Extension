
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaximumSum {

	public static int maximumSum(int[][] arr) {
		int maxSum = 0;
		int max = 0;
		while (max < arr.length) {
			int sum = 0;
			for (int j = 0; j < arr[max].length; j++) {
				sum += arr[max][j];
			}
			if (sum > maxSum) {
				maxSum = sum;
			}
			max++;
		}
		return maxSum;
	}
}
