
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MinJumps {

	public static int minJumps(int[] arr, int n) {
		int[] dp = new int[n];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;

		int d = 1;
		while (d < n) {
			for (int j = 0; j < d; j++) {
				if (arr[j] + j >= d) {
					dp[d] = Math.min(dp[d], dp[j] + 1);
				}
			}
			d++;
		}
		return dp[n - 1];
	}
}
