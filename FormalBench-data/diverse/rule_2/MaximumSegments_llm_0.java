import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaximumSegments {

	public static int maximumSegments(int n, int a, int b, int c) {
		int dp[] = new int[n + 1];
		dp[0] = 0;
		int d = 1;
		while (d <= n) {
			dp[d] = -1;
			if (d >= a && dp[d - a] != -1) {
				dp[d] = Math.max(dp[d], dp[d - a] + 1);
			}
			if (d >= b && dp[d - b] != -1) {
				dp[d] = Math.max(dp[d], dp[d - b] + 1);
			}
			if (d >= c && dp[d - c] != -1) {
				dp[d] = Math.max(dp[d], dp[d - c] + 1);
			}
			d++;
		}
		return dp[n];
	}
}
