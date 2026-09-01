import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TetrahedralNumber {

	public static Double tetrahedralNumber(int n) {
		double[] dp = new double[n + 1];
		dp[1] = 1;
		int i = 2;
		while (i <= n) {
			dp[i] = dp[i - 1] + (i * (i + 1)) / 2;
			i++;
		}
		return dp[n];
	}
}
