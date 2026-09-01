
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

		int p = 1;
		while (p < n) {
			dp[p] = dp[p - 1];
			if (arr[p] - arr[p - 1] < k) {
				if (p >= 2) {
					dp[p] = Math.max(dp[p],
							dp[p - 2] + arr[p] + arr[p - 1]);
				} else {
					dp[p] = Math.max(dp[p],
							arr[p] + arr[p - 1]);
				}
			}
			result = Math.max(result, dp[p]);
			p++;
		}
		return result;
	}
}
