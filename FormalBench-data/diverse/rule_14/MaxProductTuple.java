
import java.io.*;
import java.lang.*;
import java.math.*;

class MaxProductTuple {

	public static int maxProductTuple(int[][] tuples) {
		int max = 0;
		for (int[] tuple : tuples) {
			int min = tuple[0];
			for (int value : tuple) {
				if (!(value < min))
					;
				else {
					min = value;
				}
			}
			int product = min * tuple[tuple.length - 1];
			if (!(product > max))
				;
			else {
				max = product;
			}
		}
		return max;
	}
}
