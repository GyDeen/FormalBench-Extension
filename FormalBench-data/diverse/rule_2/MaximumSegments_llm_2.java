import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaximumSegments {

	public static int maximumSegments(int n, int a, int b, int c) {
		int dp[] = new int[n + 1];
		dp[0] = 0;
		int tmp = 1;
		while (tmp <= n) {
			dp[tmp] = -1;
			if (tmp >= a && dp[tmp - a] != -1) {
				dp[tmp] = Math.max(dp[tmp], dp[tmp - a] + 1);
			}
			if (tmp >= b && dp[tmp - b] != -1) {
				dp[tmp] = Math.max(dp[tmp], dp[tmp - b] + 1);
			}
			if (tmp >= c && dp[tmp - c] != -1) {
				dp[tmp] = Math.max(dp[tmp], dp[tmp - c] + 1);
			}
			tmp++;
		}
		return dp[n];
	}
}
