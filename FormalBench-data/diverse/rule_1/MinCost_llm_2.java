
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MinCost {

	public static int minCost(int[][] array, int m, int d) {
		int[][] tc = new int[m + 1][d + 1];
		tc[0][0] = array[0][0];
		for (int num = 1; num <= m; num++) {
			tc[num][0] = tc[num - 1][0] + array[num][0];
		}
		for (int c = 1; c <= d; c++) {
			tc[0][c] = tc[0][c - 1] + array[0][c];
		}
		for (int i = 1; i <= m; i++) {
			for (int c = 1; c <= d; c++) {
				tc[i][c] = Math.min(tc[i - 1][c - 1],
						Math.min(tc[i - 1][c], tc[i][c - 1]))
						+ array[i][c];
			}
		}
		return tc[m][d];
	}
}
