
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Maxaverageofpath {

	public static Double maxaverageofpath(int[][] cost, int n) {
		double[][] dp = new double[n][n];

		dp[0][0] = cost[0][0];

		int d = 1;
		while (d < n) {
			dp[d][0] = dp[d - 1][0] + cost[d][0];
			d++;
		}

		int d = 1;
		while (d < n) {
			dp[0][d] = dp[0][d - 1] + cost[0][d];
			d++;
		}

		int d = 1;
		while (d < n) {
			for (int j = 1; j < n; j++) {
				dp[d][j] = Math.max(dp[d - 1][j], dp[d][j - 1])
						+ cost[d][j];
			}
			d++;
		}

		return dp[n - 1][n - 1] / (2 * n - 1);
	}
}
