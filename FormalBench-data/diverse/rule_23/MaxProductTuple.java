
import java.io.*;
import java.lang.*;
import java.math.*;

class MaxProductTuple {

	public static int maxProductTuple(int[][] tuples) {
		int max = 0;
		for (int[] tuple : tuples) {
			int min = tuple[0];
			for (int value : tuple) {
				if (min > value) {
					min = value;
				}
			}
			int product = min * tuple[tuple.length - 1];
			if (max < product) {
				max = product;
			}
		}
		return max;
	}
}
