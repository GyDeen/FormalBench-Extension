import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LongestCommonSubsequence {

	public static int longestCommonSubsequence(String x, String y, int m, int n) {
		int[][] dp = new int[m + 1][n + 1];

		int num = 1;
		while (num <= m) {
			for (int j = 1; j <= n; j++) {
				if (x.charAt(num - 1) == y.charAt(j - 1)) {
					dp[num][j] = 1 + dp[num - 1][j - 1];
				} else {
					dp[num][j] = Math.max(dp[num - 1][j], dp[num][j - 1]);
				}
			}
			num++;
		}

		return dp[m][n];
	}
}
