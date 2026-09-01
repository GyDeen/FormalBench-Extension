
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MinCost {

	public static int minCost(int[][] array, int m, int c) {
		int[][] tc = new int[m + 1][c + 1];
		tc[0][0] = array[0][0];
		for (int num = 1; num <= m; num++) {
			tc[num][0] = tc[num - 1][0] + array[num][0];
		}
		for (int k = 1; k <= c; k++) {
			tc[0][k] = tc[0][k - 1] + array[0][k];
		}
		for (int i = 1; i <= m; i++) {
			for (int k = 1; k <= c; k++) {
				tc[i][k] = Math.min(tc[i - 1][k - 1],
						Math.min(tc[i - 1][k], tc[i][k - 1]))
						+ array[i][k];
			}
		}
		return tc[m][c];
	}
}
