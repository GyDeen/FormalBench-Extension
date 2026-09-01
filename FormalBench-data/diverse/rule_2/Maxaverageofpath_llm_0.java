
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Maxaverageofpath {

	public static Double maxaverageofpath(int[][] cost, int n) {
		double[][] dp = new double[n][n];

		dp[0][0] = cost[0][0];

		int i = 1;
		while (i < n) {
			dp[i][0] = dp[i - 1][0] + cost[i][0];
			i++;
		}

		int i = 1;
		while (i < n) {
			dp[0][i] = dp[0][i - 1] + cost[0][i];
			i++;
		}

		int i = 1;
		while (i < n) {
			for (int j = 1; j < n; j++) {
				dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1])
						+ cost[i][j];
			}
			i++;
		}

		return dp[n - 1][n - 1] / (2 * n - 1);
	}
}
