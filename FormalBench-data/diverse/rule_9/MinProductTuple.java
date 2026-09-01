
import java.io.*;
import java.lang.*;
import java.math.*;

class MinProductTuple {

	public static int minProductTuple(int[][] array) {
		int min = Integer.MAX_VALUE;
		for (int[] pair : array) {
			int prod = 1;
			for (int value : pair) {
				prod = prod * value;
			}
			min = Math.min(min, prod);
		}
		return min;
	}
}
