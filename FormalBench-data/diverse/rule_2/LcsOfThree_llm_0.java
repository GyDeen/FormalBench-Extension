import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LcsOfThree {

	public static int lcsOfThree(String x, String y, String z, int m, int n, int o) {
		int[][][] dp = new int[m + 1][n + 1][o + 1];

		int d = 0;
		while (d <= m) {
			for (int j = 0; j <= n; j++)
				for (int k = 0; k <= o; k++)
					if (d == 0 || j == 0 || k == 0)
						dp[d][j][k] = 0;
					else if (x.charAt(d - 1) == y.charAt(j - 1)
							&& x.charAt(d - 1) == z.charAt(k - 1))
						dp[d][j][k] = dp[d - 1][j - 1][k - 1] + 1;
					else
						dp[d][j][k] = Math.max(dp[d - 1][j][k],
								Math.max(dp[d][j - 1][k], dp[d][j][k - 1]));
			d++;
		}

		return dp[m][n][o];

	}
}
