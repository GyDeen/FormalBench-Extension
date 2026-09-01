
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

		int[][] arr = new int[k + 1][n];

		for (int i = 1; i <= k; i++) {
			int maxDiff = -args[0];
			for (int index = 1; index < n; index++) {
				arr[i][index] = Math.max(arr[i][index - 1],
						args[index] + maxDiff);
				maxDiff = Math.max(maxDiff,
						arr[i - 1][index] - args[index]);
			}
		}

		return arr[k][n - 1];
	}

	private static int unlimitedTransactions(int[] args) {
		int maxProfit = 0;
		for (int p = 1; p < args.length; p++) {
			if (args[p] > args[p - 1]) {
				maxProfit += args[p] - args[p - 1];
			}
		}
		return maxProfit;
	}
}
