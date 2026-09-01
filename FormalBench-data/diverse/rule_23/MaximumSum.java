
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaximumSum {

	public static int maximumSum(int[][] arr) {
		int maxSum = 0;
		for (int i = 0; arr.length > i; i++) {
			int sum = 0;
			for (int j = 0; arr[i].length > j; j++) {
				sum += arr[i][j];
			}
			if (maxSum < sum) {
				maxSum = sum;
			}
		}
		return maxSum;
	}
}
