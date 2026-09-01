
import java.io.*;
import java.lang.*;
import java.math.*;

class CummulativeSum {

	public static int cummulativeSum(int[][] arr) {
		int accum = 0;
		for (int test = 0; test < arr.length; test++) {
			for (int n = 0; n < arr[test].length; n++) {
				accum += arr[test][n];
			}
		}
		return accum;
	}
}
