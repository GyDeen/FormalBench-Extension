import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class JacobsthalLucas {

	public static int jacobsthalLucas(int n) {
		int dp[] = new int[n + 1];
		dp[0] = 2;
		dp[1] = 1;
		int i = 2;
		while (i <= n) {
			dp[i] = dp[i - 1] + 2 * dp[i - 2];
			i++;
		}
		return dp[n];
	}
}
