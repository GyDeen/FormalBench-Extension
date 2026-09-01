
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LongestSubseqWithDiffOne {

	public static int longestSubseqWithDiffOne(int[] arr, int n) {
		int[] dp = new int[n];
		int result = 1;

		int ret = 0;
		while (ret < n) {
			dp[ret] = 1;
			for (int j = 0; j < ret; j++) {
				if (arr[ret] == arr[j] + 1 || arr[ret] == arr[j] - 1) {
					dp[ret] = Math.max(dp[ret], dp[j] + 1);
				}
			}
			result = Math.max(result, dp[ret]);
			ret++;
		}
		return result;
	}
}
