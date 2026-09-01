
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MinSumPath {

	public static int minSumPath(int[][] array) {
		int result = array.length;

		for (int c = result - 2; c >= 0; c--) {
			for (int k = 0; k <= c; k++) {
				array[c][k] += Math.min(
						array[c + 1][k],
						array[c + 1][k + 1]);
			}
		}

		return array[0][0];
	}
}
