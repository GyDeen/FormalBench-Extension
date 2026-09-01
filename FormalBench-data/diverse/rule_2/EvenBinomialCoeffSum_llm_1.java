import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class EvenBinomialCoeffSum {

	public static int evenBinomialCoeffSum(int n) {
		int sum = 0;
		int[] dp = new int[n + 1];
		dp[0] = 1;
		int d = 1;
		while (d <= n) {
			for (int j = 0; j < d; j++) {
				dp[d] = dp[d] + dp[j];
			}
			d++;
		}
		return dp[n];
	}
}
