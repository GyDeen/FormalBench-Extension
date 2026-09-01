
import java.io.*;
import java.lang.*;
import java.math.*;

class MinDifference {

	public static int minDifference(int[][] arr) {
		int min = Integer.MAX_VALUE;
		for (int tu = 0; tu < arr.length; tu++) {
			int max = Math
					.abs(arr[tu][0] - arr[tu][1]);
			if (min > max) {
				min = max;
			}
		}
		return min;
	}
}
