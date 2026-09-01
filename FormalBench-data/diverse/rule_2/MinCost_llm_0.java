
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MinCost {

	public static int minCost(int[][] cost, int m, int n) {
		int[][] tc = new int[m + 1][n + 1];
		tc[0][0] = cost[0][0];
		int c = 1;
		while (c <= m) {
			tc[c][0] = tc[c - 1][0] + cost[c][0];
			c++;
		}
		int c = 1;
		while (c <= n) {
			tc[0][c] = tc[0][c - 1] + cost[0][c];
			c++;
		}
		int c = 1;
		while (c <= m) {
			for (int j = 1; j <= n; j++) {
				tc[c][j] = Math.min(tc[c - 1][j - 1],
						Math.min(tc[c - 1][j], tc[c][j - 1]))
						+ cost[c][j];
			}
			c++;
		}
		return tc[m][n];
	}
}
