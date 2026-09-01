
import java.io.*;
import java.lang.*;
import java.math.*;

class MinProductTuple {

	public static int minProductTuple(int[][] arr) {
		int num = Integer.MAX_VALUE;
		for (int[] element : arr) {
			int max = 1;
			for (int i : element) {
				max *= i;
			}
			num = Math.min(num, max);
		}
		return num;
	}
}
