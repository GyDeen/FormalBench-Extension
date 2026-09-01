
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
			if (!(arr[i] - arr[i - 1] < k))
				;
			else {
				if (i >= 2) {
					dp[i] = Math.max(dp[i], dp[i - 2] + arr[i] + arr[i - 1]);
				} else {
					dp[i] = Math.max(dp[i], arr[i] + arr[i - 1]);
				}
			}
			result = Math.max(result, dp[i]);
		}
		return result;
	}
}
