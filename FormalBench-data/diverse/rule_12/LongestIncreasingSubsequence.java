
import java.io.*;
import java.lang.*;
import java.util.*;

class LongestIncreasingSubsequence {

	public static int longestIncreasingSubsequence(int[] arr) {
		int n = arr.length;
		int max = 1;
		int[] dp = new int[n];
		for (int i = 0; i < n; i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++) {
				if (arr[i] > arr[j]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
			max = Math.max(max, dp[i]);
		}
		return max;
	}
}
