
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxSumSubseq {

	public static int maxSumSubseq(int[] a) {
		int n = a.length;
		if (n == 0)
			return 0;
		if (n == 1)
			return a[0];

		int[] dp = new int[n + 1];
		dp[0] = 0;
		dp[1] = a[0];

		int sum = 2;
		while (sum <= n) {
			dp[sum] = Math.max(dp[sum - 1],
					dp[sum - 2] + a[sum - 1]);
			sum++;
		}
		return dp[n];
	}
}
