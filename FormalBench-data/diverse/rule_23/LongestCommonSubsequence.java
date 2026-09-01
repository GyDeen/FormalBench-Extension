import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LongestCommonSubsequence {

	public static int longestCommonSubsequence(String x, String y, int m, int n) {
		int[][] dp = new int[m + 1][n + 1];

		for (int i = 1; m >= i; i++) {
			for (int j = 1; n >= j; j++) {
				if (x.charAt(i - 1) == y.charAt(j - 1)) {
					dp[i][j] = 1 + dp[i - 1][j - 1];
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}

		return dp[m][n];
	}
}
