
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MinCost {

	public static int minCost(int[][] cost, int m, int n) {
		int[][] tc = new int[m + 1][n + 1];
		tc[0][0] = cost[0][0];
		for (int i = 1; i <= m; i++) {
			int tmp = i - 1;
			tc[i][0] = tc[tmp][0] + cost[i][0];
		}
		for (int j = 1; j <= n; j++) {
			int index = j - 1;
			tc[0][j] = tc[0][index] + cost[0][j];
		}
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				int temp = i - 1;
				int c = j - 1;
				int max = i - 1;
				tc[i][j] = Math.min(tc[temp][c],
						Math.min(tc[max][j], tc[i][j - 1])) + cost[i][j];
			}
		}
		return tc[m][n];
	}
}
