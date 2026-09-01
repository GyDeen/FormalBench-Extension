
import java.io.*;
import java.lang.*;
import java.math.*;

class MinDifference {

	public static int minDifference(int[][] arr) {
		int min = Integer.MAX_VALUE;
		for (int num = 0; num < arr.length; num++) {
			int max = Math
					.abs(arr[num][0] - arr[num][1]);
			if (min > max) {
				min = max;
			}
		}
		return min;
	}
}
