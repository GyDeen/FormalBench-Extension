
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxSumPairDiffLessthanK {

	public static int maxSumPairDiffLessthanK(int[] args, int array, int m) {
		Arrays.sort(args);
		int[] dp = new int[array];
		dp[0] = 0;
		int d = 0;

		for (int p = 1; p < array; p++) {
			dp[p] = dp[p - 1];
			if (args[p] - args[p - 1] < m) {
				if (p >= 2) {
					dp[p] = Math.max(dp[p], dp[p - 2]
							+ args[p] + args[p - 1]);
				} else {
					dp[p] = Math.max(dp[p],
							args[p] + args[p - 1]);
				}
			}
			d = Math.max(d, dp[p]);
		}
		return d;
	}
}
