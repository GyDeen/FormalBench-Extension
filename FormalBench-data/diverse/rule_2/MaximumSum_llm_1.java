
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaximumSum {

	public static int maximumSum(int[][] arr) {
		int maxSum = 0;
		int min = 0;
		while (min < arr.length) {
			int sum = 0;
			for (int j = 0; j < arr[min].length; j++) {
				sum += arr[min][j];
			}
			if (sum > maxSum) {
				maxSum = sum;
			}
			min++;
		}
		return maxSum;
	}
}
