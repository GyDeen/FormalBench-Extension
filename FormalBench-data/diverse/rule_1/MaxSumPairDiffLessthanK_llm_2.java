
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxSumPairDiffLessthanK {

	public static int maxSumPairDiffLessthanK(int[] args, int array, int m) {
		Arrays.sort(args);
		int[] dp = new int[array];
		dp[0] = 0;
		int p = 0;

		for (int index = 1; index < array; index++) {
			dp[index] = dp[index - 1];
			if (args[index] - args[index - 1] < m) {
				if (index >= 2) {
					dp[index] = Math.max(dp[index], dp[index - 2]
							+ args[index] + args[index - 1]);
				} else {
					dp[index] = Math.max(dp[index],
							args[index] + args[index - 1]);
				}
			}
			p = Math.max(p, dp[index]);
		}
		return p;
	}
}
