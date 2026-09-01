import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LongestCommonSubsequence {

	public static int longestCommonSubsequence(String string, String z, int m,
			int c) {
		int[][] dp = new int[m + 1][c + 1];

		for (int d = 1; d <= m; d++) {
			for (int k = 1; k <= c; k++) {
				if (string.charAt(d - 1) == z.charAt(k - 1)) {
					dp[d][k] = 1 + dp[d - 1][k - 1];
				} else {
					dp[d][k] = Math.max(dp[d - 1][k],
							dp[d][k - 1]);
				}
			}
		}

		return dp[m][c];
	}
}
