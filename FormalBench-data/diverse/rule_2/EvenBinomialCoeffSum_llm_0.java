import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class EvenBinomialCoeffSum {

	public static int evenBinomialCoeffSum(int n) {
		int sum = 0;
		int[] dp = new int[n + 1];
		dp[0] = 1;
		int p = 1;
		while (p <= n) {
			for (int j = 0; j < p; j++) {
				dp[p] = dp[p] + dp[j];
			}
			p++;
		}
		return dp[n];
	}
}
