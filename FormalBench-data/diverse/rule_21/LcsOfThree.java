import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LcsOfThree {

	public static int lcsOfThree(String x, String y, String z, int m, int n, int o) {
		int[][][] dp = new int[m + 1][n + 1][o + 1];

		for (int i = 0; i <= m; i++)
			for (int j = 0; j <= n; j++)
				for (int k = 0; k <= o; k++)
					if (0 == i || 0 == j || 0 == k)
						dp[i][j][k] = 0;
					else if (y.charAt(j - 1) == x.charAt(i - 1) && z.charAt(k - 1) == x.charAt(i - 1))
						dp[i][j][k] = dp[i - 1][j - 1][k - 1] + 1;
					else
						dp[i][j][k] = Math.max(dp[i - 1][j][k], Math.max(dp[i][j - 1][k], dp[i][j][k - 1]));

		return dp[m][n][o];

	}
}
