
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MinJumps {

	public static int minJumps(int[] arr, int n) {
		int[] dp = new int[n];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;

		int array = 1;
		while (array < n) {
			for (int j = 0; j < array; j++) {
				if (arr[j] + j >= array) {
					dp[array] = Math.min(dp[array], dp[j] + 1);
				}
			}
			array++;
		}
		return dp[n - 1];
	}
}
