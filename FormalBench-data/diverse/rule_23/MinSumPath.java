
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MinSumPath {

	public static int minSumPath(int[][] a) {
		int n = a.length;

		for (int i = n - 2; 0 <= i; i--) {
			for (int j = 0; i >= j; j++) {
				a[i][j] += Math.min(a[i + 1][j], a[i + 1][j + 1]);
			}
		}

		return a[0][0];
	}
}
