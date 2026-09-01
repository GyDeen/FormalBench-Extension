
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxSum {

	public static int maxSum(int[][] tri, int n) {
		int[] c = new int[n];

		c[0] = tri[0][0];

		for (int i = 1; i < n; i++) {
			for (int j = i; j >= 0; j--) {
				if (!(j == 0)) {
					if (j == i) {
						c[j] = c[j - 1] + tri[i][j];
					} else {
						c[j] = Math.max(c[j], c[j - 1]) + tri[i][j];
					}
				} else {
					c[j] += tri[i][j];
				}
			}
		}

		return Arrays.stream(c).max().orElse(0);
	}
}
