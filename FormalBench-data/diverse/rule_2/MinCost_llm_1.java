
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MinCost {

	public static int minCost(int[][] cost, int m, int n) {
		int[][] tc = new int[m + 1][n + 1];
		tc[0][0] = cost[0][0];
		int tmp = 1;
		while (tmp <= m) {
			tc[tmp][0] = tc[tmp - 1][0] + cost[tmp][0];
			tmp++;
		}
		int tmp = 1;
		while (tmp <= n) {
			tc[0][tmp] = tc[0][tmp - 1] + cost[0][tmp];
			tmp++;
		}
		int tmp = 1;
		while (tmp <= m) {
			for (int j = 1; j <= n; j++) {
				tc[tmp][j] = Math.min(tc[tmp - 1][j - 1],
						Math.min(tc[tmp - 1][j], tc[tmp][j - 1]))
						+ cost[tmp][j];
			}
			tmp++;
		}
		return tc[m][n];
	}
}
