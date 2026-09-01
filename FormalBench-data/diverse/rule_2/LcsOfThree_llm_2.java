import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LcsOfThree {

	public static int lcsOfThree(String x, String y, String z, int m, int n, int o) {
		int[][][] dp = new int[m + 1][n + 1][o + 1];

		int p = 0;
		while (p <= m) {
			for (int j = 0; j <= n; j++)
				for (int k = 0; k <= o; k++)
					if (p == 0 || j == 0 || k == 0)
						dp[p][j][k] = 0;
					else if (x.charAt(p - 1) == y.charAt(j - 1)
							&& x.charAt(p - 1) == z.charAt(k - 1))
						dp[p][j][k] = dp[p - 1][j - 1][k - 1] + 1;
					else
						dp[p][j][k] = Math.max(dp[p - 1][j][k],
								Math.max(dp[p][j - 1][k], dp[p][j][k - 1]));
			p++;
		}

		return dp[m][n][o];

	}
}
