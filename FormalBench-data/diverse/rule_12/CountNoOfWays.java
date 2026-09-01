import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountNoOfWays {

	public static int countNoOfWays(int n, int k) {
		int dp[] = new int[n + 1];
		int mod = 1000000007;
		dp[0] = 1;
		dp[1] = k;
		dp[2] = k * k;
		for (int i = 3; i <= n; i++) {
			dp[i] = ((k - 1) * (dp[i - 1] + dp[i - 2])) % mod;
		}
		return dp[n];
	}
}
