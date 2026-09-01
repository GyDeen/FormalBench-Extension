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
		int w = 3;
		while (w <= n) {
			dp[w] = ((k - 1) * (dp[w - 1] + dp[w - 2])) % mod;
			w++;
		}
		return dp[n];
	}
}
