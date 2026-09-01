
import java.io.*;
import java.lang.*;
import java.math.*;

class CummulativeSum {

	public static int cummulativeSum(int[][] array) {
		int accum = 0;
		for (int test = 0; test < array.length; test++) {
			for (int n = 0; n < array[test].length; n++) {
				accum += array[test][n];
			}
		}
		return accum;
	}
}
