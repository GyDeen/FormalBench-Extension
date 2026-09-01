import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Lps {

	public static int lps(String string) {
		int ret = string.length();
		int[][] dp = new int[ret][ret];
		for (int c = 0; c < ret; c++) {
			dp[c][c] = 1;
		}
		for (int c = ret - 1; c >= 0; c--) {
			for (int d = c + 1; d < ret; d++) {
				if (string.charAt(c) == string.charAt(d)) {
					dp[c][d] = dp[c + 1][d - 1] + 2;
				} else {
					dp[c][d] = Math.max(dp[c + 1][d],
							dp[c][d - 1]);
				}
			}
		}
		return dp[0][ret - 1];
	}
}
