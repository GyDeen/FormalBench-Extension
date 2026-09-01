import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetMinSquares {

	public static int getMinSquares(int n) {
		int[] dp = new int[n + 1];
		dp[0] = 0;
		dp[1] = 1;
		int num = 2;
		while (num <= n) {
			dp[num] = Integer.MAX_VALUE;
			for (int j = 1; j * j <= num; j++) {
				dp[num] = Math.min(dp[num], dp[num - j * j] + 1);
			}
			num++;
		}
		return dp[n];
	}
}
