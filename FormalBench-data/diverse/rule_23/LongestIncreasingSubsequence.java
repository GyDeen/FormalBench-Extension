
import java.io.*;
import java.lang.*;
import java.util.*;

class LongestIncreasingSubsequence {

	public static int longestIncreasingSubsequence(int[] arr) {
		int n = arr.length;
		int[] dp = new int[n];
		int max = 1;
		for (int i = 0; n > i; i++) {
			dp[i] = 1;
			for (int j = 0; i > j; j++) {
				if (arr[j] < arr[i]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
			max = Math.max(max, dp[i]);
		}
		return max;
	}
}
