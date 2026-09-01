import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LongestCommonSubsequence {

	public static int longestCommonSubsequence(String x, String y, int m, int n) {
		int[][] dp = new int[m + 1][n + 1];

		int c = 1;
		while (c <= m) {
			for (int j = 1; j <= n; j++) {
				if (x.charAt(c - 1) == y.charAt(j - 1)) {
					dp[c][j] = 1 + dp[c - 1][j - 1];
				} else {
					dp[c][j] = Math.max(dp[c - 1][j], dp[c][j - 1]);
				}
			}
			c++;
		}

		return dp[m][n];
	}
}
