import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaximumSegments {

	public static int maximumSegments(int n, int a, int b, int c) {
		int dp[] = new int[n + 1];
		dp[0] = 0;
		int p = 1;
		while (p <= n) {
			dp[p] = -1;
			if (p >= a && dp[p - a] != -1) {
				dp[p] = Math.max(dp[p], dp[p - a] + 1);
			}
			if (p >= b && dp[p - b] != -1) {
				dp[p] = Math.max(dp[p], dp[p - b] + 1);
			}
			if (p >= c && dp[p - c] != -1) {
				dp[p] = Math.max(dp[p], dp[p - c] + 1);
			}
			p++;
		}
		return dp[n];
	}
}
