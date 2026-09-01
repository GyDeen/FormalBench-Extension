
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

		int count = 2;
		while (count <= n) {
			dp[count] = Math.max(dp[count - 1],
					dp[count - 2] + a[count - 1]);
			count++;
		}
		return dp[n];
	}
}
