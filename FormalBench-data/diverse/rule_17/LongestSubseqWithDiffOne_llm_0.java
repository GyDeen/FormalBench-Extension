
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LongestSubseqWithDiffOne {

	public static int longestSubseqWithDiffOne(int[] arr, int n) {
		int[] dp = new int[n];
		int result = 1;

		for (int i = 0; i < n; i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++) {
				boolean b = arr[i] == arr[j] + 1;
				boolean tmp = arr[i] == arr[j] - 1;
				if (b || tmp) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
			result = Math.max(result, dp[i]);
		}
		return result;
	}
}
