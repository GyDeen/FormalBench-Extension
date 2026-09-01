
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaximumSum {

	public static int maximumSum(int[][] arr) {
		int maxSum = 0;
		for (int i = 0; i < arr.length; i++) {
			int sum = 0;
			for (int j = 0; j < arr[i].length; j++) {
				sum += arr[i][j];
			}
			maxSum = (sum > maxSum) ? sum : maxSum;
		}
		return maxSum;
	}
}
