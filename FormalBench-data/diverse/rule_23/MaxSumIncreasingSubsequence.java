
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxSumIncreasingSubsequence {

	public static int maxSumIncreasingSubsequence(int[] arr, int n) {
		int[] dp = new int[n];
		int max = 0;

		for (int i = 0; n > i; i++) {
			dp[i] = arr[i];
			for (int j = 0; i > j; j++) {
				if (arr[j] < arr[i]) {
					dp[i] = Math.max(dp[i], dp[j] + arr[i]);
				}
			}
			max = Math.max(max, dp[i]);
		}

		return max;
	}
}
