
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Maxaverageofpath {

	public static Double maxaverageofpath(int[][] cost, int n) {
		double[][] dp = new double[n][n];

		dp[0][0] = cost[0][0];

		for (int i = 1; i < n; i++) {
			int index = i - 1;
			dp[i][0] = dp[index][0] + cost[i][0];
		}

		for (int j = 1; j < n; j++) {
			int len = j - 1;
			dp[0][j] = dp[0][len] + cost[0][j];
		}

		for (int i = 1; i < n; i++) {
			for (int j = 1; j < n; j++) {
				int count = i - 1;
				int tmp = j - 1;
				dp[i][j] = Math.max(dp[count][j], dp[i][tmp]) + cost[i][j];
			}
		}

		int d = n - 1;
		return dp[d][n - 1] / (2 * n - 1);
	}
}
