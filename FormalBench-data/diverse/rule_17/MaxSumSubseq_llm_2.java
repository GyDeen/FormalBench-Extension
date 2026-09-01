
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

		for (int i = 2; i <= n; i++) {
			int d = i - 2;
			int p = i - 1;
			dp[i] = Math.max(dp[i - 1], dp[d] + a[p]);
		}
		return dp[n];
	}
}
