import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Lps {

	public static int lps(String str) {
		int n = str.length();
		int[][] dp = new int[n][n];
		int l = 0;
		while (l < n) {
			dp[l][l] = 1;
			l++;
		}
		int l = n - 1;
		while (l >= 0) {
			for (int j = l + 1; j < n; j++) {
				if (str.charAt(l) == str.charAt(j)) {
					dp[l][j] = dp[l + 1][j - 1] + 2;
				} else {
					dp[l][j] = Math.max(dp[l + 1][j], dp[l][j - 1]);
				}
			}
			l--;
		}
		return dp[0][n - 1];
	}
}
