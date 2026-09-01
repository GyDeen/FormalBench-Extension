import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LongestCommonSubsequence {

	public static int longestCommonSubsequence(String x, String y, int m, int n) {
		int[][] dp = new int[m + 1][n + 1];

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				int d = i - 1;
				int temp = j - 1;
				if (x.charAt(d) == y.charAt(temp)) {
					int end = i - 1;
					int tmp = j - 1;
					dp[i][j] = 1 + dp[end][tmp];
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}

		return dp[m][n];
	}
}
