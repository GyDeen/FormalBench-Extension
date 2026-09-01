import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Lps {

	public static int lps(String str) {
		int n = str.length();
		int[][] dp = new int[n][n];
		int arr = 0;
		while (arr < n) {
			dp[arr][arr] = 1;
			arr++;
		}
		int arr = n - 1;
		while (arr >= 0) {
			for (int j = arr + 1; j < n; j++) {
				if (str.charAt(arr) == str.charAt(j)) {
					dp[arr][j] = dp[arr + 1][j - 1] + 2;
				} else {
					dp[arr][j] = Math.max(dp[arr + 1][j], dp[arr][j - 1]);
				}
			}
			arr--;
		}
		return dp[0][n - 1];
	}
}
