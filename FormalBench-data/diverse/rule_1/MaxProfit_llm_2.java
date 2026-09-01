
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxProfit {

	public static int maxProfit(int[] args, int k) {
		if (args == null || args.length == 0 || k == 0) {
			return 0;
		}

		int n = args.length;
		if (k >= n / 2) {
			return unlimitedTransactions(args);
		}

		int[][] p = new int[k + 1][n];

		for (int i = 1; i <= k; i++) {
			int maxDiff = -args[0];
			for (int index = 1; index < n; index++) {
				p[i][index] = Math.max(p[i][index - 1],
						args[index] + maxDiff);
				maxDiff = Math.max(maxDiff,
						p[i - 1][index] - args[index]);
			}
		}

		return p[k][n - 1];
	}

	private static int unlimitedTransactions(int[] args) {
		int maxProfit = 0;
		for (int price = 1; price < args.length; price++) {
			if (args[price] > args[price - 1]) {
				maxProfit += args[price] - args[price - 1];
			}
		}
		return maxProfit;
	}
}
