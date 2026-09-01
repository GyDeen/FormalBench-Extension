
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaximizeElements {

	public static int[][] maximizeElements(int[][] testTup1, int[][] testTup2) {
		int n = testTup1.length;
		int[][] result = new int[n][];

		int index = 0;
		while (index < n) {
			int[] tuple1 = testTup1[index];
			int[] tuple2 = testTup2[index];
			int length = Math.max(tuple1.length, tuple2.length);
			result[index] = new int[length];
			for (int j = 0; j < length; j++) {
				result[index][j] = (j < tuple1.length ? tuple1[j] : Integer.MIN_VALUE);
				if (j < tuple2.length) {
					result[index][j] = Math.max(result[index][j], tuple2[j]);
				}
			}
			index++;
		}

		return result;
	}
}
