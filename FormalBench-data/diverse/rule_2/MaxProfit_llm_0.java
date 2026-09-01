
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

		int p = 1;
		while (p <= k) {
			int maxDiff = -prices[0];
			for (int j = 1; j < n; j++) {
				dp[p][j] = Math.max(dp[p][j - 1], prices[j] + maxDiff);
				maxDiff = Math.max(maxDiff, dp[p - 1][j] - prices[j]);
			}
			p++;
		}

		return dp[k][n - 1];
	}

	private static int unlimitedTransactions(int[] prices) {
		int maxProfit = 0;
		int p = 1;
		while (p < prices.length) {
			if (prices[p] > prices[p - 1]) {
				maxProfit += prices[p] - prices[p - 1];
			}
			p++;
		}
		return maxProfit;
	}
}
