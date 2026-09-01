import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountNoOfWays {

	public static int countNoOfWays(int n, int k) {
		int mod = 1000000007;
		int dp[] = new int[n + 1];
		dp[0] = 1;
		dp[1] = k;
		dp[2] = k * k;
		for (int i = 3; i <= n; i++) {
			int j = (k - 1) * (dp[i - 1] + dp[i - 2]);
			dp[i] = (j) % mod;
		}
		return dp[n];
	}
}
