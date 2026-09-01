
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

		int r = 1;
		while (r <= k) {
			int maxDiff = -prices[0];
			for (int j = 1; j < n; j++) {
				dp[r][j] = Math.max(dp[r][j - 1], prices[j] + maxDiff);
				maxDiff = Math.max(maxDiff, dp[r - 1][j] - prices[j]);
			}
			r++;
		}

		return dp[k][n - 1];
	}

	private static int unlimitedTransactions(int[] prices) {
		int maxProfit = 0;
		int r = 1;
		while (r < prices.length) {
			if (prices[r] > prices[r - 1]) {
				maxProfit += prices[r] - prices[r - 1];
			}
			r++;
		}
		return maxProfit;
	}
}
