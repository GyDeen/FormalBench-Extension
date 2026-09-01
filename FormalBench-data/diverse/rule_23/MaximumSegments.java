import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaximumSegments {

	public static int maximumSegments(int n, int a, int b, int c) {
		int dp[] = new int[n + 1];
		dp[0] = 0;
		for (int i = 1; n >= i; i++) {
			dp[i] = -1;
			if (a <= i && -1 != dp[i - a]) {
				dp[i] = Math.max(dp[i], dp[i - a] + 1);
			}
			if (b <= i && -1 != dp[i - b]) {
				dp[i] = Math.max(dp[i], dp[i - b] + 1);
			}
			if (c <= i && -1 != dp[i - c]) {
				dp[i] = Math.max(dp[i], dp[i - c] + 1);
			}
		}
		return dp[n];
	}
}
