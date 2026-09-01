
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Maxaverageofpath {

	public static Double maxaverageofpath(int[][] cost, int n) {
		double[][] dp = new double[n][n];

		dp[0][0] = cost[0][0];

		int p = 1;
		while (p < n) {
			dp[p][0] = dp[p - 1][0] + cost[p][0];
			p++;
		}

		int p = 1;
		while (p < n) {
			dp[0][p] = dp[0][p - 1] + cost[0][p];
			p++;
		}

		int p = 1;
		while (p < n) {
			for (int j = 1; j < n; j++) {
				dp[p][j] = Math.max(dp[p - 1][j], dp[p][j - 1])
						+ cost[p][j];
			}
			p++;
		}

		return dp[n - 1][n - 1] / (2 * n - 1);
	}
}
