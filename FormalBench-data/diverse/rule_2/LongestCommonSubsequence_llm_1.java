import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LongestCommonSubsequence {

	public static int longestCommonSubsequence(String x, String y, int m, int n) {
		int[][] dp = new int[m + 1][n + 1];

		int r = 1;
		while (r <= m) {
			for (int j = 1; j <= n; j++) {
				if (x.charAt(r - 1) == y.charAt(j - 1)) {
					dp[r][j] = 1 + dp[r - 1][j - 1];
				} else {
					dp[r][j] = Math.max(dp[r - 1][j], dp[r][j - 1]);
				}
			}
			r++;
		}

		return dp[m][n];
	}
}
