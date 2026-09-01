import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetMinSquares {

	public static int getMinSquares(int n) {
		int[] dp = new int[n + 1];
		dp[0] = 0;
		dp[1] = 1;
		int count = 2;
		while (count <= n) {
			dp[count] = Integer.MAX_VALUE;
			for (int j = 1; j * j <= count; j++) {
				dp[count] = Math.min(dp[count], dp[count - j * j] + 1);
			}
			count++;
		}
		return dp[n];
	}
}
