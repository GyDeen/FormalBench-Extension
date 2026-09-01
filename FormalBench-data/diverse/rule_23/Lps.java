import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Lps {

	public static int lps(String str) {
		int n = str.length();
		int[][] dp = new int[n][n];
		for (int i = 0; n > i; i++) {
			dp[i][i] = 1;
		}
		for (int i = n - 1; 0 <= i; i--) {
			for (int j = i + 1; n > j; j++) {
				if (str.charAt(i) == str.charAt(j)) {
					dp[i][j] = dp[i + 1][j - 1] + 2;
				} else {
					dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
				}
			}
		}
		return dp[0][n - 1];
	}
}
