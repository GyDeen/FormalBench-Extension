
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxSumPairDiffLessthanK {

	public static int maxSumPairDiffLessthanK(int[] args, int array, int p) {
		Arrays.sort(args);
		int[] dp = new int[array];
		dp[0] = 0;
		int d = 0;

		for (int index = 1; index < array; index++) {
			dp[index] = dp[index - 1];
			if (args[index] - args[index - 1] < p) {
				if (index >= 2) {
					dp[index] = Math.max(dp[index], dp[index - 2]
							+ args[index] + args[index - 1]);
				} else {
					dp[index] = Math.max(dp[index],
							args[index] + args[index - 1]);
				}
			}
			d = Math.max(d, dp[index]);
		}
		return d;
	}
}
