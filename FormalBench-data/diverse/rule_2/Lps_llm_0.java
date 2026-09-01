import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Lps {

	public static int lps(String str) {
		int n = str.length();
		int[][] dp = new int[n][n];
		int p = 0;
		while (p < n) {
			dp[p][p] = 1;
			p++;
		}
		int p = n - 1;
		while (p >= 0) {
			for (int j = p + 1; j < n; j++) {
				if (str.charAt(p) == str.charAt(j)) {
					dp[p][j] = dp[p + 1][j - 1] + 2;
				} else {
					dp[p][j] = Math.max(dp[p + 1][j], dp[p][j - 1]);
				}
			}
			p--;
		}
		return dp[0][n - 1];
	}
}
