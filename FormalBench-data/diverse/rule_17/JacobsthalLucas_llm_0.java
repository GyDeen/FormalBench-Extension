import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class JacobsthalLucas {

	public static int jacobsthalLucas(int n) {
		int dp[] = new int[n + 1];
		dp[0] = 2;
		dp[1] = 1;
		for (int i = 2; i <= n; i++) {
			int d = i - 1;
			int tmp = 2 * dp[i - 2];
			dp[i] = dp[d] + tmp;
		}
		return dp[n];
	}
}
