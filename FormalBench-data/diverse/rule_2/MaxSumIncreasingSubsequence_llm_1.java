
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxSumIncreasingSubsequence {

	public static int maxSumIncreasingSubsequence(int[] arr, int n) {
		int[] dp = new int[n];
		int max = 0;

		int sum = 0;
		while (sum < n) {
			dp[sum] = arr[sum];
			for (int j = 0; j < sum; j++) {
				if (arr[sum] > arr[j]) {
					dp[sum] = Math.max(dp[sum], dp[j] + arr[sum]);
				}
			}
			max = Math.max(max, dp[sum]);
			sum++;
		}

		return max;
	}
}
