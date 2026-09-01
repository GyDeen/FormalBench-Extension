
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
				if (j == 0) {
					c[j] += tri[i][j];
				} else if (j == i) {
					int ret = j - 1;
					c[j] = c[ret] + tri[i][j];
				} else {
					int tmp = j - 1;
					c[j] = Math.max(c[j], c[tmp]) + tri[i][j];
				}
			}
		}

		return Arrays.stream(c).max().orElse(0);
	}
}
