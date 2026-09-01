import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TetrahedralNumber {

	public static Double tetrahedralNumber(int n) {
		double[] dp = new double[n + 1];
		dp[1] = 1;
		int d = 2;
		while (d <= n) {
			dp[d] = dp[d - 1] + (d * (d + 1)) / 2;
			d++;
		}
		return dp[n];
	}
}
