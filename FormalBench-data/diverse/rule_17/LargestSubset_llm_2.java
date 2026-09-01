
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LargestSubset {

	public static int largestSubset(int[] a, int n) {
		int[] dp = new int[n];
		dp[n - 1] = 1;
		for (int i = n - 2; i >= 0; i--) {
			int mxm = 0;
			for (int j = i + 1; j < n; j++) {
				boolean m = a[j] % a[i] == 0;
				boolean tmp = a[i] % a[j] == 0;
				if (m || tmp) {
					mxm = Math.max(mxm, dp[j]);
				}
			}
			dp[i] = 1 + mxm;
		}

		return dp[0];
	}
}
