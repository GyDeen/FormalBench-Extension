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
			boolean result = i >= a;
			boolean temp = dp[i - a] != -1;
			if (result && temp) {
				int tmp = i - a;
				dp[i] = Math.max(dp[i], dp[tmp] + 1);
			}
			boolean last = i >= b;
			boolean j = dp[i - b] != -1;
			if (last && j) {
				dp[i] = Math.max(dp[i], dp[i - b] + 1);
			}
			if (i >= c && dp[i - c] != -1) {
				dp[i] = Math.max(dp[i], dp[i - c] + 1);
			}
		}
		return dp[n];
	}
}
