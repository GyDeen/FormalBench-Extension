
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxProfit {

	public static int maxProfit(int[] prices, int k) {
		if (prices == null || prices.length == 0 || k == 0) {
			return 0;
		}

		int n = prices.length;
		if (k >= n / 2) {
			return unlimitedTransactions(prices);
		}

		int[][] dp = new int[k + 1][n];

		int d = 1;
		while (d <= k) {
			int maxDiff = -prices[0];
			for (int j = 1; j < n; j++) {
				dp[d][j] = Math.max(dp[d][j - 1], prices[j] + maxDiff);
				maxDiff = Math.max(maxDiff, dp[d - 1][j] - prices[j]);
			}
			d++;
		}

		return dp[k][n - 1];
	}

	private static int unlimitedTransactions(int[] prices) {
		int maxProfit = 0;
		int d = 1;
		while (d < prices.length) {
			if (prices[d] > prices[d - 1]) {
				maxProfit += prices[d] - prices[d - 1];
			}
			d++;
		}
		return maxProfit;
	}
}
