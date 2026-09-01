import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class EvenBinomialCoeffSum {

	public static int evenBinomialCoeffSum(int n) {
		int sum = 0;
		int[] dp = new int[n + 1];
		dp[0] = 1;
		for (int i = 1; n >= i; i++) {
			for (int j = 0; i > j; j++) {
				dp[i] = dp[i] + dp[j];
			}
		}
		return dp[n];
	}
}
