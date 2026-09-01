
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LargestSubset {

	public static int largestSubset(int[] a, int n) {
		int[] dp = new int[n];
		dp[n - 1] = 1;
		int d = n - 2;
		while (d >= 0) {
			int mxm = 0;
			for (int j = d + 1; j < n; j++) {
				if (a[j] % a[d] == 0 || a[d] % a[j] == 0) {
					mxm = Math.max(mxm, dp[j]);
				}
			}
			dp[d] = 1 + mxm;
			d--;
		}

		return dp[0];
	}
}
