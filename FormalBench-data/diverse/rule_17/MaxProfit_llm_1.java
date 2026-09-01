
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxProfit {

	public static int maxProfit(int[] prices, int k) {
		boolean result = prices == null || prices.length == 0;
		boolean tmp = k == 0;
		if (result || tmp) {
			return 0;
		}

		int n = prices.length;
		int p = n / 2;
		if (k >= p) {
			return unlimitedTransactions(prices);
		}

		int[][] dp = new int[k + 1][n];

		for (int i = 1; i <= k; i++) {
			int maxDiff = -prices[0];
			for (int j = 1; j < n; j++) {
				dp[i][j] = Math.max(dp[i][j - 1], prices[j] + maxDiff);
				int index = i - 1;
				maxDiff = Math.max(maxDiff, dp[index][j] - prices[j]);
			}
		}

		return dp[k][n - 1];
	}

	private static int unlimitedTransactions(int[] prices) {
		int maxProfit = 0;
		for (int i = 1; i < prices.length; i++) {
			int prod = i - 1;
			if (prices[i] > prices[prod]) {
				maxProfit += prices[i] - prices[i - 1];
			}
		}
		return maxProfit;
	}
}
