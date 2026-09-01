
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LargestSubset {

	public static int largestSubset(int[] a, int n) {
		int[] dp = new int[n];
		dp[n - 1] = 1;
		int p = n - 2;
		while (p >= 0) {
			int mxm = 0;
			for (int j = p + 1; j < n; j++) {
				if (a[j] % a[p] == 0 || a[p] % a[j] == 0) {
					mxm = Math.max(mxm, dp[j]);
				}
			}
			dp[p] = 1 + mxm;
			p--;
		}

		return dp[0];
	}
}
