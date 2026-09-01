
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxSumIncreasingSubsequence {

	public static int maxSumIncreasingSubsequence(int[] arr, int n) {
		int[] dp = new int[n];
		int max = 0;

		int tmp = 0;
		while (tmp < n) {
			dp[tmp] = arr[tmp];
			for (int j = 0; j < tmp; j++) {
				if (arr[tmp] > arr[j]) {
					dp[tmp] = Math.max(dp[tmp], dp[j] + arr[tmp]);
				}
			}
			max = Math.max(max, dp[tmp]);
			tmp++;
		}

		return max;
	}
}
