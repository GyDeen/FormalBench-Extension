import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaximumSegments {

	public static int maximumSegments(int n, int a, int b, int c) {
		int dp[] = new int[n + 1];
		dp[0] = 0;
		for (int i = 1; i <= n; i++) {
			dp[i] = -1;
			if (i >= a) {
				if (dp[i - a] != -1) {
					dp[i] = Math.max(dp[i], dp[i - a] + 1);
				}
			}
			if (i >= b) {
				if (dp[i - b] != -1) {
					dp[i] = Math.max(dp[i], dp[i - b] + 1);
				}
			}
			if (i >= c) {
				if (dp[i - c] != -1) {
					dp[i] = Math.max(dp[i], dp[i - c] + 1);
				}
			}
		}
		return dp[n];
	}
}
