
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MinSumPath {

	public static int minSumPath(int[][] a) {
		int n = a.length;

		for (int i = n - 2; i >= 0; i--) {
			for (int j = 0; j <= i; j++) {
				a[i][j] = a[i][j] + (Math.min(a[i + 1][j], a[i + 1][j + 1]));
			}
		}

		return a[0][0];
	}
}
