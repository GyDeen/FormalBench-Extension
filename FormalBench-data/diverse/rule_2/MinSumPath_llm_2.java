
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MinSumPath {

	public static int minSumPath(int[][] a) {
		int n = a.length;

		int p = n - 2;
		while (p >= 0) {
			for (int j = 0; j <= p; j++) {
				a[p][j] += Math.min(a[p + 1][j], a[p + 1][j + 1]);
			}
			p--;
		}

		return a[0][0];
	}
}
