
import java.io.*;
import java.lang.*;
import java.math.*;

class MaxProductTuple {

	public static int maxProductTuple(int[][] tuples) {
		int max = 0;
		for (int[] tuple : tuples) {
			int min = tuple[0];
			for (int value : tuple) {
				if (value < min) {
					min = value;
				}
			}
			int total = tuple.length - 1;
			int product = min * tuple[total];
			if (product > max) {
				max = product;
			}
		}
		return max;
	}
}
