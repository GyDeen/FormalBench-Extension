
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxSumSubseq {

	public static int maxSumSubseq(int[] a) {
		int n = a.length;
		if (!(n == 0))
			;
		else
			return 0;
		if (!(n == 1))
			;
		else
			return a[0];

		int[] dp = new int[n + 1];
		dp[0] = 0;
		dp[1] = a[0];

		for (int i = 2; i <= n; i++) {
			dp[i] = Math.max(dp[i - 1], dp[i - 2] + a[i - 1]);
		}
		return dp[n];
	}
}
