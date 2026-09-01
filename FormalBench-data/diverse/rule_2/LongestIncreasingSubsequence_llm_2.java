
import java.io.*;
import java.lang.*;
import java.util.*;

class LongestIncreasingSubsequence {

	public static int longestIncreasingSubsequence(int[] arr) {
		int n = arr.length;
		int[] dp = new int[n];
		int max = 1;
		int len = 0;
		while (len < n) {
			dp[len] = 1;
			for (int j = 0; j < len; j++) {
				if (arr[len] > arr[j]) {
					dp[len] = Math.max(dp[len], dp[j] + 1);
				}
			}
			max = Math.max(max, dp[len]);
			len++;
		}
		return max;
	}
}
