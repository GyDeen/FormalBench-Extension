
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxSumIncreasingSubsequence {

	public static int maxSumIncreasingSubsequence(int[] arr, int n) {
		int max = 0;
		int[] dp = new int[n];

		for (int i = 0; i < n; i++) {
			dp[i] = arr[i];
			for (int j = 0; j < i; j++) {
				if (arr[i] > arr[j]) {
					dp[i] = Math.max(dp[i], dp[j] + arr[i]);
				}
			}
			max = Math.max(max, dp[i]);
		}

		return max;
	}
}
