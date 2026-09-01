
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

		for (int i = 1; i < n; i++) {
			dp[i] = dp[i - 1];
			int count = arr[i] - arr[i - 1];
			if (count < k) {
				if (i >= 2) {
					int tmp = i - 2;
					int r = i - 1;
					dp[i] = Math.max(dp[i], dp[tmp] + arr[i] + arr[r]);
				} else {
					int break = i - 1;
					dp[i] = Math.max(dp[i], arr[i] + arr[break]);
				}
			}
			result = Math.max(result, dp[i]);
		}
		return result;
	}
}
