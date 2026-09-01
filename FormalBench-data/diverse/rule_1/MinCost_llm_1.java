
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MinCost {

	public static int minCost(int[][] array, int m, int d) {
		int[][] tc = new int[m + 1][d + 1];
		tc[0][0] = array[0][0];
		for (int c = 1; c <= m; c++) {
			tc[c][0] = tc[c - 1][0] + array[c][0];
		}
		for (int k = 1; k <= d; k++) {
			tc[0][k] = tc[0][k - 1] + array[0][k];
		}
		for (int i = 1; i <= m; i++) {
			for (int k = 1; k <= d; k++) {
				tc[i][k] = Math.min(tc[i - 1][k - 1],
						Math.min(tc[i - 1][k], tc[i][k - 1]))
						+ array[i][k];
			}
		}
		return tc[m][d];
	}
}
