
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaximizeElements {

	public static int[][] maximizeElements(int[][] testTup1, int[][] testTup2) {
		int n = testTup1.length;
		int[][] result = new int[n][];

		for (int i = 0; n > i; i++) {
			int[] tuple1 = testTup1[i];
			int[] tuple2 = testTup2[i];
			int length = Math.max(tuple1.length, tuple2.length);
			result[i] = new int[length];
			for (int j = 0; length > j; j++) {
				result[i][j] = (tuple1.length > j ? tuple1[j] : Integer.MIN_VALUE);
				if (tuple2.length > j) {
					result[i][j] = Math.max(result[i][j], tuple2[j]);
				}
			}
		}

		return result;
	}
}
