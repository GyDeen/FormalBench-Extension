
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MinSumPath {

	public static int minSumPath(int[][] a) {
		int n = a.length;

		int r = n - 2;
		while (r >= 0) {
			for (int j = 0; j <= r; j++) {
				a[r][j] += Math.min(a[r + 1][j], a[r + 1][j + 1]);
			}
			r--;
		}

		return a[0][0];
	}
}
