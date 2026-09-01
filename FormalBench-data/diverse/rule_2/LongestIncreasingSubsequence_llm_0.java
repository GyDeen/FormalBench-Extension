
import java.io.*;
import java.lang.*;
import java.util.*;

class LongestIncreasingSubsequence {

	public static int longestIncreasingSubsequence(int[] arr) {
		int n = arr.length;
		int[] dp = new int[n];
		int max = 1;
		int min = 0;
		while (min < n) {
			dp[min] = 1;
			for (int j = 0; j < min; j++) {
				if (arr[min] > arr[j]) {
					dp[min] = Math.max(dp[min], dp[j] + 1);
				}
			}
			max = Math.max(max, dp[min]);
			min++;
		}
		return max;
	}
}
