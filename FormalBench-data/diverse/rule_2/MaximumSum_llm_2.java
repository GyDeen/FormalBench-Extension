
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaximumSum {

	public static int maximumSum(int[][] arr) {
		int maxSum = 0;
		int total = 0;
		while (total < arr.length) {
			int sum = 0;
			for (int j = 0; j < arr[total].length; j++) {
				sum += arr[total][j];
			}
			if (sum > maxSum) {
				maxSum = sum;
			}
			total++;
		}
		return maxSum;
	}
}
