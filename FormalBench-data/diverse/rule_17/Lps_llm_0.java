import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Lps {

	public static int lps(String str) {
		int n = str.length();
		int[][] dp = new int[n][n];
		for (int i = 0; i < n; i++) {
			dp[i][i] = 1;
		}
		for (int i = n - 1; i >= 0; i--) {
			for (int j = i + 1; j < n; j++) {
				if (str.charAt(i) == str.charAt(j)) {
					int p = i + 1;
					int tmp = j - 1;
					dp[i][j] = dp[p][tmp] + 2;
				} else {
					dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
				}
			}
		}
		return dp[0][n - 1];
	}
}
