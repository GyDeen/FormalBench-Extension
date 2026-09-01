import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class JacobsthalLucas {

	public static int jacobsthalLucas(int n) {
		int dp[] = new int[n + 1];
		dp[0] = 2;
		dp[1] = 1;
		int d = 2;
		while (d <= n) {
			dp[d] = dp[d - 1] + 2 * dp[d - 2];
			d++;
		}
		return dp[n];
	}
}
