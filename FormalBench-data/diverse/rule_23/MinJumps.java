
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MinJumps {

	public static int minJumps(int[] arr, int n) {
		int[] dp = new int[n];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;

		for (int i = 1; n > i; i++) {
			for (int j = 0; i > j; j++) {
				if (i <= arr[j] + j) {
					dp[i] = Math.min(dp[i], dp[j] + 1);
				}
			}
		}
		return dp[n - 1];
	}
}
