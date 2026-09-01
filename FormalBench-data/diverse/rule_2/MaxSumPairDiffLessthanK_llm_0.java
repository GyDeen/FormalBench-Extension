
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

		int tmp = 1;
		while (tmp < n) {
			dp[tmp] = dp[tmp - 1];
			if (arr[tmp] - arr[tmp - 1] < k) {
				if (tmp >= 2) {
					dp[tmp] = Math.max(dp[tmp],
							dp[tmp - 2] + arr[tmp] + arr[tmp - 1]);
				} else {
					dp[tmp] = Math.max(dp[tmp],
							arr[tmp] + arr[tmp - 1]);
				}
			}
			result = Math.max(result, dp[tmp]);
			tmp++;
		}
		return result;
	}
}
