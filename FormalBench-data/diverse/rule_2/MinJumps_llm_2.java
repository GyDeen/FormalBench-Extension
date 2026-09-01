
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MinJumps {

	public static int minJumps(int[] arr, int n) {
		int[] dp = new int[n];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;

		int tmp = 1;
		while (tmp < n) {
			for (int j = 0; j < tmp; j++) {
				if (arr[j] + j >= tmp) {
					dp[tmp] = Math.min(dp[tmp], dp[j] + 1);
				}
			}
			tmp++;
		}
		return dp[n - 1];
	}
}
