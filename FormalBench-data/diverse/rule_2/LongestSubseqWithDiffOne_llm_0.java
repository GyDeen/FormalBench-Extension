
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LongestSubseqWithDiffOne {

	public static int longestSubseqWithDiffOne(int[] arr, int n) {
		int[] dp = new int[n];
		int result = 1;

		int r = 0;
		while (r < n) {
			dp[r] = 1;
			for (int j = 0; j < r; j++) {
				if (arr[r] == arr[j] + 1 || arr[r] == arr[j] - 1) {
					dp[r] = Math.max(dp[r], dp[j] + 1);
				}
			}
			result = Math.max(result, dp[r]);
			r++;
		}
		return result;
	}
}
