import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class JacobsthalNum {

	public static int jacobsthalNum(int n) {
		int dp[] = new int[n + 1];
		dp[0] = 0;
		dp[1] = 1;
		int i = 2;
		while (i <= n) {
			dp[i] = dp[i - 1] + 2 * dp[i - 2];
			i++;
		}
		return dp[n];
	}
}
