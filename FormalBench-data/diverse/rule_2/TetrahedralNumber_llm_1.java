import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TetrahedralNumber {

	public static Double tetrahedralNumber(int n) {
		double[] dp = new double[n + 1];
		dp[1] = 1;
		int p = 2;
		while (p <= n) {
			dp[p] = dp[p - 1] + (p * (p + 1)) / 2;
			p++;
		}
		return dp[n];
	}
}
