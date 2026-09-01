import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetMinSquares {

	public static int getMinSquares(int n) {
		int[] dp = new int[n + 1];
		dp[0] = 0;
		dp[1] = 1;
		int arr = 2;
		while (arr <= n) {
			dp[arr] = Integer.MAX_VALUE;
			for (int j = 1; j * j <= arr; j++) {
				dp[arr] = Math.min(dp[arr], dp[arr - j * j] + 1);
			}
			arr++;
		}
		return dp[n];
	}
}
