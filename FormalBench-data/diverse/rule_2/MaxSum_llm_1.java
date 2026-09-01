
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxSum {

	public static int maxSum(int[][] tri, int n) {
		int[] c = new int[n];

		c[0] = tri[0][0];

		int r = 1;
		while (r < n) {
			for (int j = r; j >= 0; j--) {
				if (j == 0) {
					c[j] += tri[r][j];
				} else if (j == r) {
					c[j] = c[j - 1] + tri[r][j];
				} else {
					c[j] = Math.max(c[j], c[j - 1]) + tri[r][j];
				}
			}
			r++;
		}

		return Arrays.stream(c).max().orElse(0);
	}
}
