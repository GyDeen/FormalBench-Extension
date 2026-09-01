import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LcsOfThree {

	public static int lcsOfThree(String x, String y, String Z, int num, int p,
			int c) {
		int[][][] dp = new int[num + 1][p + 1][c + 1];

		for (int d = 0; d <= num; d++)
			for (int j = 0; j <= p; j++)
				for (int k = 0; k <= c; k++)
					if (d == 0 || j == 0 || k == 0)
						dp[d][j][k] = 0;
					else if (x.charAt(d - 1) == y.charAt(j - 1)
							&& x.charAt(d - 1) == Z.charAt(k - 1))
						dp[d][j][k] = dp[d - 1][j - 1][k - 1] + 1;
					else
						dp[d][j][k] = Math.max(dp[d - 1][j][k],
								Math.max(dp[d][j - 1][k], dp[d][j][k - 1]));

		return dp[num][p][c];

	}
}
