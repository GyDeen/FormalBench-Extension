
import java.io.*;
import java.lang.*;
import java.math.*;

class MaxProductTuple {

	public static int maxProductTuple(int[][] tuples) {
		int max = 0;
		for (int[] tuple : tuples) {
			int min = tuple[0];
			for (int value : tuple) {
				min = (value < min) ? value : min;
			}
			int product = min * tuple[tuple.length - 1];
			max = (product > max) ? product : max;
		}
		return max;
	}
}
