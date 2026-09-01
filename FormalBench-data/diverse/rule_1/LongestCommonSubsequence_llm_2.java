import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LongestCommonSubsequence {

	public static int longestCommonSubsequence(String str, String z, int m,
			int k) {
		int[][] dp = new int[m + 1][k + 1];

		for (int d = 1; d <= m; d++) {
			for (int c = 1; c <= k; c++) {
				if (str.charAt(d - 1) == z.charAt(c - 1)) {
					dp[d][c] = 1 + dp[d - 1][c - 1];
				} else {
					dp[d][c] = Math.max(dp[d - 1][c],
							dp[d][c - 1]);
				}
			}
		}

		return dp[m][k];
	}
}
