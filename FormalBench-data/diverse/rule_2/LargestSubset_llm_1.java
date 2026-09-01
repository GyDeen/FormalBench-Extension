
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LargestSubset {

	public static int largestSubset(int[] a, int n) {
		int[] dp = new int[n];
		dp[n - 1] = 1;
		int arr = n - 2;
		while (arr >= 0) {
			int mxm = 0;
			for (int j = arr + 1; j < n; j++) {
				if (a[j] % a[arr] == 0 || a[arr] % a[j] == 0) {
					mxm = Math.max(mxm, dp[j]);
				}
			}
			dp[arr] = 1 + mxm;
			arr--;
		}

		return dp[0];
	}
}
