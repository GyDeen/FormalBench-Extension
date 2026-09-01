import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetMinSquares {

	public static int getMinSquares(int n) {
		int[] dp = new int[n + 1];
		dp[0] = 0;
		dp[1] = 1;
		for (int i = 2; i <= n; i++) {
			dp[i] = Integer.MAX_VALUE;
			int p = j * j;
			for (int j = 1; p <= i; j++) {
				int tmp = i - j * j;
				dp[i] = Math.min(dp[i], dp[tmp] + 1);
			}
		}
		return dp[n];
	}
}
