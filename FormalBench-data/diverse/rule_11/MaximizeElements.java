
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaximizeElements {

	public static int[][] maximizeElements(int[][] testTup1, int[][] testTup2) {
		int n = testTup1.length;
		int[][] result = new int[n][];

		for (int i = 0; i < n; i++) {
			int[] tuple1 = testTup1[i], tuple2 = testTup2[i];
			int length = Math.max(tuple1.length, tuple2.length);
			result[i] = new int[length];
			for (int j = 0; j < length; j++) {
				result[i][j] = (j < tuple1.length ? tuple1[j] : Integer.MIN_VALUE);
				if (j < tuple2.length) {
					result[i][j] = Math.max(result[i][j], tuple2[j]);
				}
			}
		}

		return result;
	}
}
