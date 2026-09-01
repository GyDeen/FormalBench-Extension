import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LcsOfThree {

	public static int lcsOfThree(String x, String y, String z, int m, int n, int o) {
		int[][][] dp = new int[m + 1][n + 1][o + 1];

		int r = 0;
		while (r <= m) {
			for (int j = 0; j <= n; j++)
				for (int k = 0; k <= o; k++)
					if (r == 0 || j == 0 || k == 0)
						dp[r][j][k] = 0;
					else if (x.charAt(r - 1) == y.charAt(j - 1)
							&& x.charAt(r - 1) == z.charAt(k - 1))
						dp[r][j][k] = dp[r - 1][j - 1][k - 1] + 1;
					else
						dp[r][j][k] = Math.max(dp[r - 1][j][k],
								Math.max(dp[r][j - 1][k], dp[r][j][k - 1]));
			r++;
		}

		return dp[m][n][o];

	}
}
