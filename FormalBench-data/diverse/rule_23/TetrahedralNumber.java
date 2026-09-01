import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TetrahedralNumber {

	public static Double tetrahedralNumber(int n) {
		double[] dp = new double[n + 1];
		dp[1] = 1;
		for (int i = 2; n >= i; i++) {
			dp[i] = dp[i - 1] + (i * (i + 1)) / 2;
		}
		return dp[n];
	}
}
