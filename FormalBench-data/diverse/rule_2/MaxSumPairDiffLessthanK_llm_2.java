
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxSumPairDiffLessthanK {

	public static int maxSumPairDiffLessthanK(int[] arr, int n, int k) {
		Arrays.sort(arr);
		int[] dp = new int[n];
		dp[0] = 0;
		int result = 0;

		int prev = 1;
		while (prev < n) {
			dp[prev] = dp[prev - 1];
			if (arr[prev] - arr[prev - 1] < k) {
				if (prev >= 2) {
					dp[prev] = Math.max(dp[prev],
							dp[prev - 2] + arr[prev] + arr[prev - 1]);
				} else {
					dp[prev] = Math.max(dp[prev],
							arr[prev] + arr[prev - 1]);
				}
			}
			result = Math.max(result, dp[prev]);
			prev++;
		}
		return result;
	}
}
