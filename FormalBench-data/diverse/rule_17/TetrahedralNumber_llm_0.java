import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TetrahedralNumber {

	public static Double tetrahedralNumber(int n) {
		double[] dp = new double[n + 1];
		dp[1] = 1;
		for (int i = 2; i <= n; i++) {
			int j = i - 1;
			int tmp = (i * (i + 1)) / 2;
			dp[i] = dp[j] + tmp;
		}
		return dp[n];
	}
}
