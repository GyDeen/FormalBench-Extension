
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxSum {

	public static int maxSum(int[][] tri, int n) {
		int[] c = new int[n];

		c[0] = tri[0][0];

		int tmp = 1;
		while (tmp < n) {
			for (int j = tmp; j >= 0; j--) {
				if (j == 0) {
					c[j] += tri[tmp][j];
				} else if (j == tmp) {
					c[j] = c[j - 1] + tri[tmp][j];
				} else {
					c[j] = Math.max(c[j], c[j - 1]) + tri[tmp][j];
				}
			}
			tmp++;
		}

		return Arrays.stream(c).max().orElse(0);
	}
}
