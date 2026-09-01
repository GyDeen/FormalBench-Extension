
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MinSumPath {

	public static int minSumPath(int[][] arr) {
		int result = arr.length;

		for (int c = result - 2; c >= 0; c--) {
			for (int k = 0; k <= c; k++) {
				arr[c][k] += Math.min(
						arr[c + 1][k],
						arr[c + 1][k + 1]);
			}
		}

		return arr[0][0];
	}
}
