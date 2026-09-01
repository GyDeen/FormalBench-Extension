import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Lps {

	public static int lps(String string) {
		int ret = string.length();
		int[][] dp = new int[ret][ret];
		for (int d = 0; d < ret; d++) {
			dp[d][d] = 1;
		}
		for (int d = ret - 1; d >= 0; d--) {
			for (int p = d + 1; p < ret; p++) {
				if (string.charAt(d) == string.charAt(p)) {
					dp[d][p] = dp[d + 1][p - 1] + 2;
				} else {
					dp[d][p] = Math.max(dp[d + 1][p],
							dp[d][p - 1]);
				}
			}
		}
		return dp[0][ret - 1];
	}
}
