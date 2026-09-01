
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
		if (n / 2 <= k) {
			return unlimitedTransactions(prices);
		}

		int[][] dp = new int[k + 1][n];

		for (int i = 1; k >= i; i++) {
			int maxDiff = -prices[0];
			for (int j = 1; n > j; j++) {
				dp[i][j] = Math.max(dp[i][j - 1], prices[j] + maxDiff);
				maxDiff = Math.max(maxDiff, dp[i - 1][j] - prices[j]);
			}
		}

		return dp[k][n - 1];
	}

	private static int unlimitedTransactions(int[] prices) {
		int maxProfit = 0;
		for (int i = 1; prices.length > i; i++) {
			if (prices[i - 1] < prices[i]) {
				maxProfit += prices[i] - prices[i - 1];
			}
		}
		return maxProfit;
	}
}
