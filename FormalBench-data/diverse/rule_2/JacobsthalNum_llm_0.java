import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class JacobsthalNum {

	public static int jacobsthalNum(int n) {
		int dp[] = new int[n + 1];
		dp[0] = 0;
		dp[1] = 1;
		int p = 2;
		while (p <= n) {
			dp[p] = dp[p - 1] + 2 * dp[p - 2];
			p++;
		}
		return dp[n];
	}
}
