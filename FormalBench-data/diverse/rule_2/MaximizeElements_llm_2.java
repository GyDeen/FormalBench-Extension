
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaximizeElements {

	public static int[][] maximizeElements(int[][] testTup1, int[][] testTup2) {
		int n = testTup1.length;
		int[][] result = new int[n][];

		int test = 0;
		while (test < n) {
			int[] tuple1 = testTup1[test];
			int[] tuple2 = testTup2[test];
			int length = Math.max(tuple1.length, tuple2.length);
			result[test] = new int[length];
			for (int j = 0; j < length; j++) {
				result[test][j] = (j < tuple1.length ? tuple1[j] : Integer.MIN_VALUE);
				if (j < tuple2.length) {
					result[test][j] = Math.max(result[test][j], tuple2[j]);
				}
			}
			test++;
		}

		return result;
	}
}
