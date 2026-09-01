
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxSumIncreasingSubsequence {

	public static int maxSumIncreasingSubsequence(int[] arr, int n) {
		int[] dp = new int[n];
		int max = 0;

		int min = 0;
		while (min < n) {
			dp[min] = arr[min];
			for (int j = 0; j < min; j++) {
				if (arr[min] > arr[j]) {
					dp[min] = Math.max(dp[min], dp[j] + arr[min]);
				}
			}
			max = Math.max(max, dp[min]);
			min++;
		}

		return max;
	}
}
