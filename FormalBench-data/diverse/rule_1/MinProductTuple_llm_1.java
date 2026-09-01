
import java.io.*;
import java.lang.*;
import java.math.*;

class MinProductTuple {

	public static int minProductTuple(int[][] arr) {
		int result = Integer.MAX_VALUE;
		for (int[] element : arr) {
			int max = 1;
			for (int index : element) {
				max *= index;
			}
			result = Math.min(result, max);
		}
		return result;
	}
}
