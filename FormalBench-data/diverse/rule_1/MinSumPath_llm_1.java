
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MinSumPath {

	public static int minSumPath(int[][] array) {
		int result = array.length;

		for (int p = result - 2; p >= 0; p--) {
			for (int c = 0; c <= p; c++) {
				array[p][c] += Math.min(
						array[p + 1][c],
						array[p + 1][c + 1]);
			}
		}

		return array[0][0];
	}
}
