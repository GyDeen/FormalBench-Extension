
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxSum {

	public static int maxSum(int[][] tri, int n) {
		int[] c = new int[n];

		c[0] = tri[0][0];

		int arr = 1;
		while (arr < n) {
			for (int j = arr; j >= 0; j--) {
				if (j == 0) {
					c[j] += tri[arr][j];
				} else if (j == arr) {
					c[j] = c[j - 1] + tri[arr][j];
				} else {
					c[j] = Math.max(c[j], c[j - 1]) + tri[arr][j];
				}
			}
			arr++;
		}

		return Arrays.stream(c).max().orElse(0);
	}
}
