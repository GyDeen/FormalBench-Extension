
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LongestSubseqWithDiffOne {

	public static int longestSubseqWithDiffOne(int[] arr, int n) {
		int[] dp = new int[n];
		int result = 1;

		int end = 0;
		while (end < n) {
			dp[end] = 1;
			for (int j = 0; j < end; j++) {
				if (arr[end] == arr[j] + 1 || arr[end] == arr[j] - 1) {
					dp[end] = Math.max(dp[end], dp[j] + 1);
				}
			}
			result = Math.max(result, dp[end]);
			end++;
		}
		return result;
	}
}
