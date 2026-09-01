
import java.io.*;
import java.lang.*;
import java.util.*;

class LongestIncreasingSubsequence {

	public static int longestIncreasingSubsequence(int[] arr) {
		int n = arr.length;
		int[] dp = new int[n];
		int max = 1;
		int last = 0;
		while (last < n) {
			dp[last] = 1;
			for (int j = 0; j < last; j++) {
				if (arr[last] > arr[j]) {
					dp[last] = Math.max(dp[last], dp[j] + 1);
				}
			}
			max = Math.max(max, dp[last]);
			last++;
		}
		return max;
	}
}
