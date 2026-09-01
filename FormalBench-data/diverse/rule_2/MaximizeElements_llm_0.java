
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaximizeElements {

	public static int[][] maximizeElements(int[][] testTup1, int[][] testTup2) {
		int n = testTup1.length;
		int[][] result = new int[n][];

		int num = 0;
		while (num < n) {
			int[] tuple1 = testTup1[num];
			int[] tuple2 = testTup2[num];
			int length = Math.max(tuple1.length, tuple2.length);
			result[num] = new int[length];
			for (int j = 0; j < length; j++) {
				result[num][j] = (j < tuple1.length ? tuple1[j] : Integer.MIN_VALUE);
				if (j < tuple2.length) {
					result[num][j] = Math.max(result[num][j], tuple2[j]);
				}
			}
			num++;
		}

		return result;
	}
}
