
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MinCost {

	public static int minCost(int[][] cost, int m, int n) {
		int[][] tc = new int[m + 1][n + 1];
		tc[0][0] = cost[0][0];
		int p = 1;
		while (p <= m) {
			tc[p][0] = tc[p - 1][0] + cost[p][0];
			p++;
		}
		int p = 1;
		while (p <= n) {
			tc[0][p] = tc[0][p - 1] + cost[0][p];
			p++;
		}
		int p = 1;
		while (p <= m) {
			for (int j = 1; j <= n; j++) {
				tc[p][j] = Math.min(tc[p - 1][j - 1],
						Math.min(tc[p - 1][j], tc[p][j - 1]))
						+ cost[p][j];
			}
			p++;
		}
		return tc[m][n];
	}
}
